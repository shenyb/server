package com.need.api.web.controller.need;


import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.service.need.NeedService;
import com.need.common.domain.enums.UserNeedStatus;
import com.need.common.domain.po.api.need.NeedGoodsPO;
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
 * <p></p>
 * @author LXD 2015年8月8日 下午3:09:08
 * @ClassName GetHomeOps
 * @Description TODO  用户取消need
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年8月8日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.USER_CANCEL_NEED)
public class UserCancelNeedController {
	private static final Logger logger = Logger.getLogger(UserCancelNeedController.class);
	@Autowired
	private NeedService needService;
	
	/**
	 * 
	 * @author LXD 2015年8月9日 上午10:29:56
	 * @Method: GetHotGoods 
	 * @Description: 用户取消need商品
 	 * @param userId
	 * @return 
	 * @throws
	 */
	@ParamsValidate(value={
			@ParamValidate(name="userId",notNull=true,code=BizReturnCode.NEED_USERID_NOT_EXIST),
			@ParamValidate(name="goodsId",notNull=true,code=BizReturnCode.NEED_GOODS_ID_NOT_EXIST)
	})
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.POST)
	public Message userCancelNeed(@RequestParam(required = true) String userId,
			@RequestParam(required = true) String goodsId) {
		logger.info(String.format("userCancelNeed....userId &&& goodsId :%s", userId+"&&&"+goodsId));
		Message message = Message.success();
		NeedGoodsPO needGoodsPO = needService.getNeedGoodsPO(userId, goodsId, UserNeedStatus.NEED.code);
		if (needGoodsPO == null) {
			//该用户未need过该商品
			return Message.error(BizReturnCode.NEED_GOODS_USER_NONEED_GOODS);
		}
		//如果need过，将状态置位“CANCEL”
		needGoodsPO.setNeedStatus(UserNeedStatus.CANCEL.code);
		int needCount = needService.updateNeedGoods(needGoodsPO);
		message.addData("needCount", needCount);
		return message;

	}
	
}
