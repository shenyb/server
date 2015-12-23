package com.need.api.web.controller.need;


import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.service.need.NeedService;
import com.need.common.domain.enums.FocusStatusEnum;
import com.need.common.domain.po.api.need.NeedKolFansPO;
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
 * @Description TODO  用户关注行家
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年8月8日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.USER_FOCUS_KOL)
public class UserFocusKolController {
	private static final Logger logger = Logger.getLogger(UserFocusKolController.class);
	@Autowired
	private NeedService needService;
	
	/**
	 * 
	 * @author LXD 2015年8月9日 上午10:29:56
	 * @Method: GetHotGoods 
	 * @Description: 用户关注行家
 	 * @param userId
	 * @return 
	 * @throws
	 */
	@ParamsValidate(value={
			@ParamValidate(name="userId",notNull=true,code=BizReturnCode.NEED_USERID_NOT_EXIST),
			@ParamValidate(name="kolId",notNull=true,code=BizReturnCode.NEED_KOLID_NOT_EXIST)
	})
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.POST)
	public Message userFocusKol(@RequestParam(required = true)String userId,@RequestParam(required = true)String kolId){
		logger.info(String.format("userFocusKol....userId &&& kolId :%s", userId+"&&&"+kolId));
		 Message message = Message.success();
		 /**
		  * 不能自己关注自己的
		  */
		 if(userId.equals(kolId)){
			 return Message.error(BizReturnCode.NEED_USER_FOCUSED_KOL_ERROR);			 
		 }
		 
		 /**
		  * 校验是否关注过
		  */
		 boolean isFocus = needService.getNeedKolFansPO(userId,kolId,FocusStatusEnum.FOCUS.code);
		 if(isFocus){
			 /**
			  * 已关注过，返回业务码
			  */
			 return Message.error(BizReturnCode.NEED_USER_FOCUSED_KOL); 
			 
		 }
		 NeedKolFansPO needKolFansPO= needService.getByParams(userId, kolId, FocusStatusEnum.CANCEL.code);
		 /**
		  * 更改狀態 
		  */
		 if(needKolFansPO!=null){
			  needKolFansPO.setFocusStatus(FocusStatusEnum.FOCUS.code);
			  needService.updatNeedKolFansPO(needKolFansPO);  
		  } else{
			/**
			 * 創建關注
			 */
			  needKolFansPO=new NeedKolFansPO();
			 needKolFansPO.setUserId(userId);
			 needKolFansPO.setKolId(kolId);
			 needKolFansPO.setFocusStatus(FocusStatusEnum.FOCUS.code);
			 needService.addFocusKol(needKolFansPO);
		 } 
		 /**
		  * 返回粉丝数
		  */
		 int  concernCount =needService.getConcernCount(kolId);
		 logger.info(String.format("concernCount : %d", concernCount));
		 message.addData("concernCount", concernCount);
		return  message;
		
	}
	
}
