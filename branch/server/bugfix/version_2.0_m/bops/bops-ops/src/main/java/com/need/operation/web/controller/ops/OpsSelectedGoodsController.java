package com.need.operation.web.controller.ops;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.need.biz.utils.BizCodeUtil;
import com.need.dao.bops.ops.BopsOpsSelectedDAO;
import com.need.domain.po.bops.goods.BopsGoods;
import com.need.domain.po.bops.ops.BopsOpsSelected;
import com.need.domain.pub.Message;
import com.need.domain.vo.ops.SelectedGoodsVO;
import com.need.operation.constant.ControllerMappings;
import com.need.service.bops.goods.BopsGoodsService;
import com.need.service.bops.ops.BopsOpsSelectedService;

@Controller
@RequestMapping(value = ControllerMappings.OPS_SELECTED_GOODS)
public class OpsSelectedGoodsController {
	private static final Logger logger = Logger.getLogger(OpsSelectedGoodsController.class);
	
	@Autowired
	private BopsOpsSelectedService bopsOpsSelectedService; 
	@Autowired
	private BopsGoodsService bopsGoodsService;
	@Autowired
	private BopsOpsSelectedDAO bopsOpsSelectedDAO;
	/**
	 * 
	 * @author LXD 2015年8月10日 上午10:36:09
	 * @Method: addSelectedGoods 
	 * @Description: TODO 添加精选商品
	 * @param selectedGoodsVO
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public Message addSelectedGoods(@RequestBody SelectedGoodsVO selectedGoodsVO){
		logger.info(String.format("in OpsSelectedGoodsController addSelectedGoods selectedGoodsVO: %s", JSON.toJSONString(selectedGoodsVO)));
		Message success = Message.success();
		List<BopsOpsSelected> list=new ArrayList<BopsOpsSelected>();
		String [] goodsIds=selectedGoodsVO.getGoodsIds();
		if(goodsIds==null){
			Message errorMessage=Message.error(3009);
			return errorMessage;	
		}
		for(int i=0;i<goodsIds.length;i++){
			//校验该商品是否已经添加到运营位了
			BopsOpsSelected bopsGoodsSceneExisted=bopsOpsSelectedService.checkIsAdd(goodsIds[i]);
			if(bopsGoodsSceneExisted!=null){
				continue;
			}
			
			BopsGoods goods= bopsGoodsService.getGoodsById(goodsIds[i]);
			BopsOpsSelected selectedGoods=new BopsOpsSelected();
			selectedGoods.setSelectionId(BizCodeUtil.generateSelectionId(goodsIds[i]));
			selectedGoods.setGoodsId(goods.getGoodsId());
			selectedGoods.setGoodsName(goods.getGoodsName());
			selectedGoods.setGoodsPrice(goods.getOnsalePrice());
			selectedGoods.setGoodsProfilePicKey(goods.getScenePicKey());
			selectedGoods.setGoodsStatus(goods.getGoodsStatus());
			list.add(selectedGoods);
		}
		bopsOpsSelectedService.addSelectedGoods(list);
		success.addData("list", list);
		return success;
		
		
		
	}
	
	/**
	 * 
	 * @author LXD 2015年8月10日 上午10:36:36
	 * @Method: getPageOfOpsInfo 
	 * @Description: TODO 获取分页数据
	 * @param page
	 * @param pageSize
	 * @param goodsId
	 * @param goodsName
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public Message getPageOfOpsInfo(
			@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer pageSize,
			@RequestParam(required=false) String goodsCode,
			@RequestParam(required=false) String goodsName){
		logger.info(String.format("in hotgoods  getPageOfOpsInfo goodsCode&&&&goodsName: %s",goodsCode+"&&&&"+goodsName ));
		Message message = Message.success();
		List<BopsOpsSelected> selectedgoodsList =new ArrayList<BopsOpsSelected>();
		SelectedGoodsVO selectedGoodsVO = new SelectedGoodsVO();
		selectedGoodsVO.setPage(page);
		selectedGoodsVO.setPageSize(pageSize);
       if(StringUtils.isNotBlank(goodsCode)){
			
    	   selectedGoodsVO.setGoodsCode(goodsCode);
    	   selectedgoodsList = bopsOpsSelectedService.searchSelectedGoods(selectedGoodsVO);
		   message.addData("list",selectedgoodsList);
		   message.addData("page", selectedGoodsVO);
			
		}else if(StringUtils.isNotBlank(goodsName)){
			
			selectedGoodsVO.setGoodsName(goodsName);
			bopsOpsSelectedService.searchSelectedGoods(selectedGoodsVO);
			selectedgoodsList = bopsOpsSelectedService.searchSelectedGoods(selectedGoodsVO);
			message.addData("list",selectedgoodsList);
			message.addData("page", selectedGoodsVO);
		}
		List<BopsOpsSelected> BopsOpsSelectedList = bopsOpsSelectedService.getPageOfBopsOps(selectedGoodsVO);
		if(BopsOpsSelectedList!=null&&BopsOpsSelectedList.size()>0){
			for(BopsOpsSelected selectedGoods:BopsOpsSelectedList){
				BopsGoods goods= bopsGoodsService.getGoodsById(selectedGoods.getGoodsId());
				if(goods!=null){
				selectedGoods.setGoodsName(goods.getGoodsName());
				selectedGoods.setGoodsCode(goods.getGoodsCode());
				selectedGoods.setGoodsProfilePicKey(goods.getScenePicKey());
				}
				
			}
			
			
		}
		
		
		message.addData("list",BopsOpsSelectedList);
		message.addData("page", selectedGoodsVO);
		return message;
	}
	
	/**
	 * 
	 * @author LXD 2015年8月10日 上午10:36:55
	 * @Method: getSelectedGoodsInfo 
	 * @Description: TODO 获取某一精选
	 * @param selectionId
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET,value = "/{selectionId}")
	public Message getSelectedGoodsInfo(@PathVariable String selectionId){
		logger.info("in OpsSelectedGoodsController getSelectedGoodsInfo selectionId: " + selectionId);
		Message message = Message.success();
		message.addData("bopsOpsSelected", bopsOpsSelectedService.getSelectedById(selectionId));
		return message;
	}
	/**
	 * 
	 * @author LXD 2015年8月10日 上午10:37:16
	 * @Method: deleteBopsGoodsById 
	 * @Description: TODO 删除精选
	 * @param selectionId
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.DELETE, value = "/{selectionId}")
	public Message deleteBopsGoodsById(@PathVariable String selectionId){
		logger.info("in OpsSelectedGoodsController deleteBopsGoodsById selectionId: " + selectionId);
		Message message = Message.success();
		bopsOpsSelectedService.deleteBopsOpsSelected(selectionId);
		return message;
	}
	
	/**
	 * 
	 * @author LXD 2015年8月6日 上午11:13:00
	 * @Method: updateHotGoodsInfo 
	 * @Description: TODO  更新精选信息
	 * @param popularityId
	 * @param goosId
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT,value = "/{selectionId}") 
	public Message updateHotGoodsInfo(@RequestBody SelectedGoodsVO selectedGoodsVO ){
		logger.info(String.format("in OpsSelectedGoodsController updateHotGoodsInfo selectedGoodsVO: %s", JSON.toJSONString(selectedGoodsVO)));
		Message message = Message.success();
		if(Integer.parseInt(selectedGoodsVO.getGoodsSort())>bopsOpsSelectedDAO.queryMaxSort()){
			Message errorMessage=Message.error(3003);
			return errorMessage;
		}
		BopsGoods goods= bopsGoodsService.getGoodsById(selectedGoodsVO.getGoodsId());
		if(goods!=null){
		BopsOpsSelected selectedgoods=bopsOpsSelectedService.getSelectedById(selectedGoodsVO.getSelectionId());
		selectedgoods.setGoodsId(selectedGoodsVO.getGoodsId());
		selectedgoods.setGoodsName(goods.getGoodsName());
		selectedgoods.setGoodsProfilePicKey(goods.getTopPicKeys());
		selectedgoods.setGoodsStatus(goods.getGoodsStatus());
		selectedgoods.setGoodsSort(Integer.parseInt(selectedGoodsVO.getGoodsSort()));
		bopsOpsSelectedService.editSelectedGoods(selectedgoods);
		}
		return message;
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET,value="/all")
	public Message getAllSelectedGoods(){
		logger.info("in OpsSelectedGoodsController getAllSelectedGoods");
		Message message = Message.success();
		message.addData("bopsOpsSelectedgoods", bopsOpsSelectedService.getAll());
		return message;
	}
	
	/**
	 * 
	 * @author LXD 2015年8月6日 下午2:28:26
	 * @Method: searchHotgoods 
	 * @Description: TODO 搜索精品商品
 	 * @param popularityId
	 * @param goodsName
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST,value="/searchSelected")
	public Message searchSelectedgoods(@RequestBody SelectedGoodsVO selectedGoodsVO){
		logger.info(String.format("in OpsSelectedGoodsController searchSelectedgoods selectedGoodsVO: %s", JSON.toJSONString(selectedGoodsVO)));
		Message message = Message.success();
		message.addData("bopsOpsSelectedgoods", bopsOpsSelectedService.searchSelectedGoods(selectedGoodsVO));
		
		return message;
	}
	
	
}
