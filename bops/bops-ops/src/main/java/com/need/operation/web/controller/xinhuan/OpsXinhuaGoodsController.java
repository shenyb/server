package com.need.operation.web.controller.xinhuan;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.domain.pub.Message;
import com.need.domain.vo.xinhuan.XinhuanGoodsParamPageVO;
import com.need.domain.vo.xinhuan.XinhuanGoodsVO;
import com.need.operation.constant.ControllerMappings;
import com.need.operation.constant.ViewMappings;
import com.need.operation.pub.ConstantsProConfig;
import com.need.service.bops.xinhuan.OpsXinhuaGoodsService;

/**
 * @author xiehao 2015年9月10日 下午1:51:49
 * @ClassName OpsXinhuaGoodsController
 * @Description TODO 添加运营位的商品
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: xiehao 2015年9月10日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.OPS_XINHUAN_GOODS)
public class OpsXinhuaGoodsController {

	private static final Logger logger = Logger.getLogger(OpsXinhuaGoodsController.class);
	
	@Autowired
	private OpsXinhuaGoodsService opsXinhuaGoodsService;
	@Autowired
	private ConstantsProConfig ConstantsProConfig;
	
	/**
	 * @author xiehao 2015年9月11日 下午4:33:13
	 * @Method: addXinhuanGoods 
	 * @Description: TODO 添加运营位商品 此方法已废弃！！！！
	 * @param xinhuanGoods
	 * @return 
	 * @throws
	 */
/*	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public Message addXinhuanGoods(@RequestBody XinhuanGoodsVO xinhuanGoods){
		logger.info("in OpsXinhuaGoodsController addXinhuanGoods  xinhuanGoods: " + xinhuanGoods);
		return opsXinhuaGoodsService.addOpsXinhuanGoods(xinhuanGoods);
	}*/
	
	/**
	 * @author xiehao 2015年9月11日 下午4:43:45
	 * @Method: queryXinhuanGoods 
	 * @Description: TODO 查询某个运营位下的所有商品
	 * @param opsId
	 * @return 
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.GET,value="/xinhuanGoods")
	public String queryXinhuanGoods(XinhuanGoodsParamPageVO pageVO,
			Model model){
		logger.info("in OpsXinhuaGoodsController queryXinhuanGoods  " + pageVO.toString());	
		Message messsage=opsXinhuaGoodsService.queryXinhuanGoods(pageVO);
		model.addAttribute("picDomain", ConstantsProConfig.getPic_domain());
		model.addAttribute("opsGoodslist", messsage.getData().get("list"));
		model.addAttribute("page", messsage.getData().get("page"));
		model.addAttribute("xinhuanOpsId",pageVO.getOpsId() );
	    return ViewMappings.ALLOCATION_GOODS_LIST; 
	}
	
	/**
	 * 
	 * @author LXD 2015年10月17日 上午11:32:47
	 * @Method: editXinhuanGoods 
	 * @Description: TODO 添加或编辑新欢商品
	 * @param xinhuanGoods
	 * @return 
	 * @throws
	 */
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST,value="/editGoods")
	public Message editXinhuanGoods(XinhuanGoodsVO xinhuanGoods){
		return opsXinhuaGoodsService.editGoods(xinhuanGoods);
		
		//return opsXinhuaGoodsService.editXinhuanGoods(xinhuanGoods);
	}
	
	/**
	 * @author xiehao 2015年9月11日 下午6:02:40
	 * @Method: deleteXinhuanGoods 
	 * @Description: TODO 删除运营位商品
	 * @param id
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/deleteGoods")
	public Message deleteXinhuanGoods( String id){
		logger.info("in OpsXinhuaGoodsController deleteXinhuanGoods id: " + id);
		return opsXinhuaGoodsService.deleteXinhuanGoods(id);
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST,value = "/addGoods")
	public Message addXinhuanGoods(XinhuanGoodsVO goodsVO){
		return opsXinhuaGoodsService.addXinhuanGoods(goodsVO);
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/deleteAllGoods")
	public Message deleteAllGoods(String[] id){
		logger.info("in OpsXinhuaGoodsController deleteXinhuanGoods id: " + id);
		Message success=Message.success();
		if(id!=null&&id.length>0){
			for(int i=0;i<id.length;i++){
				opsXinhuaGoodsService.deleteXinhuanGoods(id[i]);
			}
		}
		return success;
		
	}
	
	
}
