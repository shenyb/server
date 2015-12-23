package com.need.api.web.controller.ops;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.ops.OpsXinhuanService;
import com.need.common.domain.pub.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * </p>
 * 
 * @author xiehao 2015年9月11日 下午6:09:13
 * @ClassName XinhuanOpsGoodsController
 * @Description TODO 获取新欢某个运营位的商品列表
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: xiehao 2015年9月11日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.XINHUAN_GOODS_LIST)
public class XinhuanOpsGoodsController {

	private static final Logger logger = Logger.getLogger(XinhuanOpsPositionController.class);

	@Autowired
	private OpsXinhuanService opsXinhuanService;

	@ResponseBody
	@RequestMapping(params = "apiVersion=1.1", method = RequestMethod.GET)
	public Message getOpsXinhuanGoods(
			String opsId, 
			String userId,
			Integer pageNum,
			Integer pageSize) {
		logger.info("in XinhuanOpsGoodsController getOpsXinhuanGoods");
		if (!StringUtils.hasText(userId)) {
			userId = "";
		}
		if(pageNum == null){
			pageNum = 1;
		}
		if(pageSize == null){
			pageSize = Integer.MAX_VALUE;
		}
		
		return opsXinhuanService.getOpsXinhuanGoods(opsId, userId, pageNum, pageSize);
	}
	/**
	 * 
	 * @author LXD 2015年10月22日 下午3:44:38
	 * @Method: getOpsXinhuanGoods_V1_3 
	 * @Description: TODO 1.3 新欢scroll 运营位下的goodslist列表
	 * @param opsId
	 * @param userId
	 * @param pageNum
	 * @param pageSize
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.3", method = RequestMethod.GET)
	public Message getOpsXinhuanGoods_V1_3(
			String opsId, 
			String userId,
			Integer pageNum,
			Integer pageSize) {
		logger.info("in XinhuanOpsGoodsController getOpsXinhuanGoods");
		if (!StringUtils.hasText(userId)) {
			userId = "";
		}
		if(pageNum == null){
			pageNum = 1;
		}
		if(pageSize == null){
			pageSize = Integer.MAX_VALUE;
		}
		
		return opsXinhuanService.getOpsXinhuanGoods_V1_3(opsId, userId, pageNum, pageSize);
	}
}
