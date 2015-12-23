package com.need.api.web.controller.user;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.need.api.constant.Constants;
import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.user.UserService;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.user.FansInfoVO;
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
 * @author peiboli 2015年9月18日 下午9:53:51
 * @ClassName GetFansListController
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年9月18日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.FANSLIST)
public class GetFansListController {
	private static final Logger logger = Logger.getLogger(GetFansListController.class);
	
	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0",method = RequestMethod.GET)
	public Message getFansList(
			@RequestParam(required = true) String userId,
			Integer pageNum,
			Integer pageSize) {
		if(pageNum == null)
			pageNum = 1;
		if(pageSize == null)
			pageSize = Constants.APP_EVERY_PAGE_SIZE;
		Message success = Message.success();
		logger.info(String.format("getFansList...in..params:%s %d %d", userId,pageNum,pageSize));
		PageHelper.startPage(pageNum, pageSize);
		Page<FansInfoVO> page = (Page<FansInfoVO>)userService.getfansList(userId);
		List<FansInfoVO> fanslist = page.getResult();
		
		success.addData("fansList", fanslist);
		success.addData("total", page.getTotal());
		logger.info(String.format("getFansList...out..params:%s %d %d", userId,pageNum,pageSize));
		return success;
	}

}
