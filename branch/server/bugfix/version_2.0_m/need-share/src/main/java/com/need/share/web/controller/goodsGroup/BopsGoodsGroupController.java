package com.need.share.web.controller.goodsGroup;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.share.constant.ControllerMappings;
import com.need.share.pub.Message;
import com.need.share.service.goodsGroup.GoodsGroupService;


@Controller
@RequestMapping(ControllerMappings.BOPS_GOODS_GROUP)
public class BopsGoodsGroupController {

	private static final Logger logger = Logger.getLogger(BopsGoodsGroupController.class);
	
	@Autowired
	private GoodsGroupService goodsGroupService;

	
	/***
	 * 
	 * @author LXD 2015年11月25日 下午4:23:52
	 * @Method: addGroupGoods 
	 * @Description: TODO 添加商品
	 * @param groupStatus
	 * @param groupBatch
	 * @param request
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/{groupBatch}")
	public Message getGoodsGroup(@PathVariable String groupBatch) {

		logger.info("in BopsGoodsGroupController addGroupGoods groupBatch: " + groupBatch);

		Message success= goodsGroupService.getGoodsGroupByBatch(groupBatch);
		return success;
	}




}
