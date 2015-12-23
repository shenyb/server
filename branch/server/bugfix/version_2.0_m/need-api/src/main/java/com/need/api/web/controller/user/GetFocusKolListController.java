package com.need.api.web.controller.user;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.need.api.constant.Constants;
import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.user.UserService;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.user.KolInfoVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/**
 * 
 * <p></p>
 * @author peiboli 2015年8月8日 下午6:35:45
 * @ClassName GetFocusKolListController
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年8月8日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.FOCUSKOLLIST)
public class GetFocusKolListController {
	private static final Logger logger = Logger.getLogger(GetFocusKolListController.class);
	
	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0",method = RequestMethod.GET)
	public Message getFocusKolList(
			@RequestParam(required = true) String userId,
			Integer pageNum,
			Integer pageSize) {
		if(pageNum == null)
			pageNum = Constants.APP_EVERY_PAGE_NUM;
		if(pageSize == null)
			pageSize = Constants.APP_EVERY_PAGE_SIZE;
		Message success = Message.success();
		logger.info(String.format("getFocusKolList...in..params:%s %d %d", userId,pageNum,pageSize));
		PageHelper.startPage(pageNum, pageSize);
		Page<KolInfoVo> page = (Page<KolInfoVo>)userService.getfocusKolList(userId);
		List<KolInfoVo> kolInfoVolist = page.getResult();
		
		success.addData("kolList", kolInfoVolist);
		success.addData("total", page.getTotal());
		logger.info(String.format("getFocusKolList...out..params:%s %d %d", userId,pageNum,pageSize));
		return success;
	}

}
