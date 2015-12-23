package com.need.erp.web.controller.device;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.erp.constant.ControllerMappings;
import com.need.dao.api.device.DeviceFeedBackDAO;
import com.need.domain.pub.Message;
import com.need.domain.pub.Page;
import com.need.domain.vo.device.FeedBackVO;
/**
 * 
 * <p></p>
 * @author peiboli 2015年8月28日 下午6:22:51
 * @ClassName FeedBackController
 * @Description TODO意见反馈
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年8月28日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.FEED_BACK)
public class FeedBackController {
	
	private static final Logger logger = LoggerFactory.getLogger(FeedBackController.class);

	@Autowired
	DeviceFeedBackDAO feedBackDao;
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public Message getFeedBackList(
			@RequestParam(required = false) String mobile, 
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime,
			@RequestParam(required = false) Integer page, 
			@RequestParam(required = false) Integer pageSize) {
		logger.info(String.format("getFeedBackList....in...params: %d %d", page,pageSize));
		Message message = Message.success();
		Page feedBackpage = new Page();
		int total = feedBackDao.count(mobile,startTime,endTime);
		feedBackpage.setTotal(total);
		if (page == null || "".equals(page) || pageSize == null || "".equals(pageSize)) {
			feedBackpage.setPage(1);
			feedBackpage.setPageSize(10);
		} else {
			feedBackpage.setPage(page);
			feedBackpage.setPageSize(pageSize);
		}
 		List<FeedBackVO> feedBackPO=  feedBackDao.queryByPage(mobile,startTime,endTime,feedBackpage);
 		message.addData("list", feedBackPO);
 		message.addData("page", feedBackpage);
		logger.info(String.format("getFeedBackList....out...params: %d %d", page,pageSize));
		return message;

	}
}
