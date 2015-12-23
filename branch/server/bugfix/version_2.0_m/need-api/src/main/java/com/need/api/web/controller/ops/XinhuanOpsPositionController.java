package com.need.api.web.controller.ops;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.ops.OpsXinhuanService;
import com.need.common.domain.pub.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * </p>
 * 
 * @author xiehao 2015年9月11日 下午6:08:31
 * @ClassName XinhuanOpsPositionController
 * @Description TODO 获取新欢页的运营位
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: xiehao 2015年9月11日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.XINHUAN_OPS_POSITION)
public class XinhuanOpsPositionController {

	private static final Logger logger = Logger.getLogger(XinhuanOpsPositionController.class);

	@Autowired
	private OpsXinhuanService opsXinhuanService;

	@ResponseBody
	@RequestMapping(params = "apiVersion=1.1", method = RequestMethod.GET)
	public Message getOpsPosition() {
		logger.info("in XinhuanOpsPositionController XinhuanOpsPositionController ");

		return opsXinhuanService.getOpsPosition();
	}

}
