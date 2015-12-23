package com.need.api.web.controller.need;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.service.need.NeedService;
import com.need.common.domain.pub.Message;
import com.need.web.core.annotion.ParamValidate;
import com.need.web.core.annotion.ParamsValidate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * <p>
 * </p>
 * 
 * @author LXD 2015年8月8日 下午3:09:08
 * @ClassName GetHomeOps
 * @Description TODO 用户取消关注
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年8月8日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.USER_CANCEL_FOCUS)
public class CancelFocusKolController {
	private static final Logger logger = Logger.getLogger(CancelFocusKolController.class);
	@Autowired
	private NeedService needService;

	/**
	 * 
	 * @author LXD 2015年8月9日 上午10:29:56 @Method: GetHotGoods @Description:
	 *         用户need商品 @param userId @return @throws
	 */
	@ParamsValidate(value={
			@ParamValidate(name="userId",notNull=true,code=BizReturnCode.NEED_USERID_NOT_EXIST),
			@ParamValidate(name="kolId",notNull=true,code=BizReturnCode.NEED_KOLID_NOT_EXIST)
	})
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.POST)
	public Message userCancelFocus(@RequestParam(required = true) String userId,
			@RequestParam(required = true) String kolId) {
		logger.info(String.format("userId && kolId: %s", userId+"&&"+kolId));
		Message message =needService.cancelFocusKol(userId, kolId);
		return message;

	}

}
