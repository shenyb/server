package com.need.api.web.controller.need;


import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.dao.jdbc.need.NeedGoodsDAO;
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
 * @Description TODO  行家need商品
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年8月8日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.USER_NEED_GOODS)
public class UserNeedGoodsController {
	private static final Logger logger = Logger.getLogger(UserNeedGoodsController.class);
	@Autowired
	private NeedService needService;
	
	@Autowired
	private NeedGoodsDAO needGoodsDAO;
	
	/**
	 * 
	 * @author LXD 2015年8月9日 上午10:29:56
	 * @Method: GetHotGoods 
	 * @Description: 用户need商品
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
	public Message userNeedGoods(@RequestParam(required = true) String userId,
			@RequestParam(required = true) String goodsId) {
		logger.info(String.format("userNeedGoods: %s", "userId"+userId+"&&&goodsId:"+goodsId));
		Message success = Message.success();
		NeedGoodsPO needGoods = needGoodsDAO.queryByParams(userId, goodsId, UserNeedStatus.NEED.code);
		if (needGoods != null) {
			//已经need过
			return Message.error(BizReturnCode.NEED_USER_NEEDED_GOODS);
		}
		NeedGoodsPO needGoodsPO = needGoodsDAO.queryByParams(userId, goodsId, UserNeedStatus.CANCEL.code);
		if (needGoodsPO != null) {
			//need过，单状态是“CANCEL”，将状态改为“NEED”
			needGoodsPO.setNeedStatus(UserNeedStatus.NEED.code);
			needService.changeNeedStatus(needGoodsPO);
			int needCount = needGoodsDAO.getNeedGoodsCount(needGoodsPO.getGoodsId());
			success.addData("needCount", needCount);
		} else {
			//没有NEED过，创建一条NEED
			needGoodsPO = new NeedGoodsPO();
			needGoodsPO.setGoodsId(goodsId);
			needGoodsPO.setUserId(userId);
			needGoodsPO.setNeedStatus(UserNeedStatus.NEED.code);
			int needCount = needService.addNeedGoods(needGoodsPO);
			success.addData("needCount", needCount);
		}

		return success;

	}
	
}
