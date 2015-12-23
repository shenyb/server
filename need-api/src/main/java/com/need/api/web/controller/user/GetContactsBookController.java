/**
 * @ProjectName: need-api
 * @Copyright: 2015 by Beijin Weplanter Technology co.,ltd.
 * @address: http://www.weplanter.com
 * @Description: 本内容仅限于稻田(北京)科技有限公司内部使用，禁止转发.
 * @author peiboli
 * @date: 2015年11月26日 下午5:29:49
 * @Title: GetContactsBookController.java
 * @Package com.need.api.web.controller.user
 * @Description: TODO
 */
package com.need.api.web.controller.user;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.service.user.UserService;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.user.ContactsBookVO;
import net.sf.json.JSONArray;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p></p>
 * @author peiboli 2015年11月26日 下午5:29:49
 * @ClassName GetContactsBookController
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年11月26日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.USER_CONTACTSBOOK)
public class GetContactsBookController {

	private static final Logger logger= Logger.getLogger(GetContactsBookController.class);
	@Autowired
	private UserService userService;
	/**
	 * 
	 * @author peiboli 2015年11月26日 下午5:33:23
	 * @Method: getcontactsBook 
	 * @Description: TODO获得手机通讯录中的need的用户列表
	 * @param userId
	 * @param mobiles
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(params = "apiVersion=2.0", method = RequestMethod.POST)
	public Message getcontactsBook(String userId,String mobiles){
		logger.info("getcontactsBook...in..params:"+userId);
		JSONArray jsonArray;
		
		try {
			jsonArray= JSONArray.fromObject(mobiles);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.success();
		}
        @SuppressWarnings("unchecked")
		List<ContactsBookVO> list= (List<ContactsBookVO>)JSONArray.toCollection(jsonArray, ContactsBookVO.class);
        if(list.size()>60){
        	return Message.error(BizReturnCode.MOBILE_SIZE_ABOVE_FIFTY);
        }
        Message message=userService.insertContactsBook(userId,list);
		logger.info("getcontactsBook...out..params:"+userId);
		return message;
	};
}
