package com.need.api.web.controller.need;


import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.need.NeedService;
import com.need.common.domain.enums.FocusStatusEnum;
import com.need.common.domain.po.api.need.NeedKolFansPO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.need.FocusKolListParam;
import com.need.utils.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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
@RequestMapping(value = ControllerMappings.USER_FOCUS_KOL_LIST)
public class UserFocusKolListController {
	private static final Logger logger = Logger.getLogger(UserFocusKolListController.class);
	@Autowired
	private NeedService needService;
	
	/**
	 * 
	 * @author LXD 2015年8月9日 上午10:29:56
	 * @Method: GetHotGoods 
	 * @Description: 用户批量关注行家列表
 	 * @param userId
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.POST)
	public Message userFocusKol(HttpServletRequest request, FocusKolListParam param) {
		logger.info(String.format("userFocusKol params: %s", param.toString()));
		Message message = Message.success();
		if (!StringUtil.isBlank(param.getKolIds())) {
			String[] kolId = param.getKolIds().replaceAll("[\"\\[\\]]", "").split(",");
			String userId = param.getUserId();
//			NeedKolFansPO needKolFansPO = new NeedKolFansPO();
//			needKolFansPO.setUserId(userId);
//			for (int i = 0; i < kolId.length; i++) {
//				// 检验是否关注过,如果关注过，跳出本次循环，继续下次。 modifyby lxd 20150819
//				NeedKolFansPO  needKolFans = needService.getNeedKolFans(userId, kolId[i].trim());
//				if ( needKolFans==null) {
//					needKolFansPO.setKolId(kolId[i].trim());
//					needKolFansPO.setFocusStatus(FocusStatusEnum.FOCUS.code);
//					needService.addFocusKol(needKolFansPO);
//					continue;
//				}
//				//判断是否关注过
//				if(needKolFans.getFocusStatus().equals(FocusStatusEnum.FOCUS.code)){
//					continue;
//				}
//				needService.saveNeedKolFans(userId, kolId[i].trim());
//				
//			}
			message=needService.addFocus(userId,kolId);
		}
		return message;

	}
	
}
