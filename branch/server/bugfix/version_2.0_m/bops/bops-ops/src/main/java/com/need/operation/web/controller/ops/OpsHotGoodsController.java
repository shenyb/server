package com.need.operation.web.controller.ops;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

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
import com.need.common.validate.ValidatorUtil;
import com.need.dao.bops.goods.BopsGoodsDAO;
import com.need.dao.bops.ops.BopsOpsHotgoodsDAO;
import com.need.domain.po.bops.goods.BopsGoods;
import com.need.domain.po.bops.ops.BopsOpsHotgoods;
import com.need.domain.pub.Message;
import com.need.domain.vo.ops.HotGoodsVO;
import com.need.operation.constant.ControllerMappings;
import com.need.service.bops.goods.BopsGoodsService;
import com.need.service.bops.ops.BopsOpsHotGoodsService;
import com.need.service.constant.BizReturnCode;

/**
 * 
 * <p></p>
 * @author LXD 2015年8月5日 下午6:59:50
 * @ClassName OpsHotGoodsController
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年8月5日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.OPS_HOT_GOODS)
public class OpsHotGoodsController {
	private static final Logger logger = Logger.getLogger(OpsHotGoodsController.class);
	@Autowired
	private BopsOpsHotGoodsService bopsOpsHotGoodsService;
	@Autowired
	private BopsGoodsService bopsGoodsService;
	@Autowired
	private BopsGoodsDAO bopsGoodsDAO;
	
	@Autowired
	private BopsOpsHotgoodsDAO bopsOpsHotgoodsDAO; 
	/**
	 * 
	 * @author LXD 2015年8月6日 上午11:10:57
	 * @Method: addhotGoods 
	 * @Description: TODO 添加人气商品
	 * @param goodsIds
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public Message addhotGoods(@RequestBody HotGoodsVO hotGoodsVO ){
		logger.info(String.format("in hotgoods  addhotGoods hotGoodsVO: %s", JSON.toJSONString(hotGoodsVO)));
		Message success = Message.success();
		String [] goodsIds =hotGoodsVO.getGoodsIds();
		if(goodsIds==null){
			Message errorMessage=Message.error(3009);
			return errorMessage;	
		}
		List<BopsOpsHotgoods> list=new ArrayList<BopsOpsHotgoods>();
		for(int i=0;i<goodsIds.length;i++){
			//校验该商品是否已经添加到运营位了
			BopsOpsHotgoods bopsGoodsSceneExisted=bopsOpsHotGoodsService.checkIsAdd(goodsIds[i]);
			if(bopsGoodsSceneExisted!=null){
				continue;
			}
			BopsGoods goods= bopsGoodsService.getGoodsById(goodsIds[i]);
			BopsOpsHotgoods hotGoods=new BopsOpsHotgoods();
			hotGoods.setPopularityId(BizCodeUtil.generatePopularityId(goodsIds[i]));
			hotGoods.setGoodsId(goodsIds[i]);
			hotGoods.setGoodsName(goods.getGoodsName());
			hotGoods.setGoodsProfilePicKey(goods.getScenePicKey());
			hotGoods.setGoodsStatus(goods.getGoodsStatus());
			list.add(hotGoods);
		}
		bopsOpsHotGoodsService.addBopsOpsHotGoods(list);
		success.addData("list", list);
		logger.info("out hotgoods  addhotGoods hotGoodsVO: " + hotGoodsVO);
		return success;
		
	}
	/**
	 * 
	 * @author LXD 2015年8月6日 上午11:11:15
	 * @Method: getPageOfOpsInfo 
	 * @Description: TODO 获取人气分页信息
	 * @param currentPage
	 * @param pageSize
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public Message getPageOfOpsInfo(
			@RequestParam(required=false) Integer page,
			@RequestParam(required=false) Integer pageSize,
			@RequestParam(required=false) String goodsCode,
			@RequestParam(required=false) String goodsName
			){
		logger.info(String.format("in hotgoods  getPageOfOpsInfo goodsCode&&&&goodsName: %s",goodsCode+"&&&&"+goodsName ));
		Message message = Message.success();
		List<BopsOpsHotgoods> bopsOpsHotgoodsList =new ArrayList<BopsOpsHotgoods>();
		HotGoodsVO hotGoods=new HotGoodsVO();
		hotGoods.setPage(page);
		hotGoods.setPageSize(pageSize);
		if(StringUtils.isNotBlank(goodsCode)){
			
			hotGoods.setGoodsCode(goodsCode);
			bopsOpsHotgoodsList = bopsOpsHotGoodsService.searchHotGoods(hotGoods);
			message.addData("list",bopsOpsHotgoodsList);
			message.addData("page", hotGoods);
			
		}else if(StringUtils.isNotBlank(goodsName)){
			
			hotGoods.setGoodsName(goodsName);
			bopsOpsHotGoodsService.searchHotGoods(hotGoods);
			bopsOpsHotgoodsList = bopsOpsHotGoodsService.searchHotGoods(hotGoods);
			message.addData("list",bopsOpsHotgoodsList);
			message.addData("page", hotGoods);
		}else{
        bopsOpsHotgoodsList = bopsOpsHotGoodsService.getPageOfBopsOps(hotGoods);
        if(bopsOpsHotgoodsList!=null&&bopsOpsHotgoodsList.size()>0){
        	for(BopsOpsHotgoods Hotgoods: bopsOpsHotgoodsList){
        		BopsGoods goods=bopsGoodsDAO.selectByPrimaryKey(Hotgoods.getGoodsId());
        		if(goods!=null){
        		Hotgoods.setGoodsCode(goods.getGoodsCode());
        		Hotgoods.setGoodsId(goods.getGoodsId());
        		Hotgoods.setGoodsName(goods.getGoodsName());
        		Hotgoods.setGoodsProfilePicKey(goods.getScenePicKey());
        		}
        	}
        	
        }
		message.addData("list",bopsOpsHotgoodsList);
		message.addData("page", hotGoods);
		}
		logger.info("out hotgoods getPageOfOpsInfo goodsCode: " + goodsCode);
		return message;
		
		
	}
	
	/**
	 * 
	 * @author LXD 2015年8月6日 上午11:11:31
	 * @Method: getHotGoodsInfo 
	 * @Description: TODO 获取人气信息
	 * @param popularityId
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET,value = "/{popularityId}")
	public Message getHotGoodsInfo(@PathVariable String popularityId){
		logger.info("in hotgoods getHotGoodsInfo popularityId: " + popularityId);
		Message message =Message.success();
		message.addData("bopsOpsHotgoods", bopsOpsHotGoodsService.getHotgoodsById(popularityId));
		return message;
	}
	/**
	 * 
	 * @author LXD 2015年8月6日 上午11:12:14
	 * @Method: deleteBopsGoodsById 
	 * @Description: TODO 根据ID删除人气商品
	 * @param popularityId
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.DELETE, value = "/{popularityId}")
	public Message deleteBopsGoodsById(@PathVariable String popularityId){
		logger.info("in hotgoods deleteBopsGoodsById popularityId: " + popularityId);
		Message message = Message.success();
		bopsOpsHotGoodsService.deletehotGoodsById(popularityId);
		return message;
	}
	/**
	 * 
	 * @author LXD 2015年8月6日 上午11:13:00
	 * @Method: updateHotGoodsInfo 
	 * @Description: TODO  更新人气信息
	 * @param popularityId
	 * @param goosId
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT,value="/{popularityId}") 
	public Message updateHotGoodsInfo(@RequestBody HotGoodsVO hotGoodsVO ){
		logger.info(String.format("in hotgoods updateHotGoodsInfo hotGoodsVO: %s", JSON.toJSONString(hotGoodsVO)));
		Set<ConstraintViolation<HotGoodsVO>> result = ValidatorUtil.getInstance().validate(hotGoodsVO);
		if(result.size()>0){
			for(ConstraintViolation<?> c:result){
				return Message.error(BizReturnCode.FIELD_VALIDATE_ERR, c.getMessage());
			}
		}
		Message message =Message.success();
	    int maxSort=bopsOpsHotgoodsDAO.queryMaxSort();
	    //校验修改的位序是否大于最大序号
	    if(maxSort<Integer.parseInt(hotGoodsVO.getGoodsSort())){
	       Message errorMessage= Message.error(3003);
	    	return errorMessage;
	    }
		
		BopsGoods goods= bopsGoodsService.getGoodsById(hotGoodsVO.getGoodsId());
		if(goods!=null){
		BopsOpsHotgoods hotgoods=bopsOpsHotGoodsService.getHotgoodsById(hotGoodsVO.getPopularityId());
		hotgoods.setGoodsId(hotGoodsVO.getGoodsId());
		hotgoods.setGoodsName(goods.getGoodsName());
		hotgoods.setGoodsProfilePicKey(goods.getTopPicKeys());
		hotgoods.setGoodsStatus(goods.getGoodsStatus());
		hotgoods.setGoodsSort(Integer.parseInt(hotGoodsVO.getGoodsSort()));
		bopsOpsHotGoodsService.editOpsHotgoods(hotgoods);
		}
		
		
		return message;
	}
	/**
	 * 
	 * @author LXD 2015年8月6日 上午11:13:56
	 * @Method: getAllHotGoods 
	 * @Description: TODO 获取所有的人气商品
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET,value="/all")
	public Message getAllHotGoods(){
		logger.info("in hotgoods getAllHotGoods");
		Message message = Message.success();
		message.addData("bopsOpsHotgoods", bopsOpsHotGoodsService.getallHotbops());
		return message;
	}
	/**
	 * 
	 * @author LXD 2015年8月6日 下午2:28:26
	 * @Method: searchHotgoods 
	 * @Description: TODO 搜索人气商品
 	 * @param popularityId
	 * @param goodsName
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST,value="/searchHot")
	public Message searchHotgoods(@RequestBody HotGoodsVO hotGoodsVO ){
		logger.info(String.format("in hotgoods searchHotgoods hotGoodsVO: %s", JSON.toJSONString(hotGoodsVO)));
		Message message = Message.success();
		message.addData("bopsOpsHotgoods", bopsOpsHotGoodsService.searchHotGoods(hotGoodsVO));		
		return message;
	}
	
}
