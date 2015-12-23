package com.need.operation.web.controller.device;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.need.dao.api.device.DeviceFeedBackDAO;
import com.need.domain.pub.Message;
import com.need.domain.pub.Page;
import com.need.domain.vo.device.FeedBackParamVO;
import com.need.domain.vo.device.FeedBackVO;
import com.need.operation.constant.ControllerMappings;
import com.need.operation.constant.ViewMappings;
import com.need.operation.web.controller.kolcategory.KolCategoryManagerController;
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
	
	private static final Logger logger = LoggerFactory.getLogger(KolCategoryManagerController.class);

	@Autowired
	DeviceFeedBackDAO feedBackDao;
	/**
	 * 
	 * @author peiboli 2015年10月12日 下午6:02:16
	 * @Method: getFeedBackList 
	 * @Description: TODO
	 * @param mobile
	 * @param startTime
	 * @param endTime
	 * @param page
	 * @param pageSize
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String getFeedBackList(FeedBackParamVO feedback,Model model) {
		logger.info("getFeedBackList....in...params:"+feedback.toString());
//		Message message = Message.success();
		int total = feedBackDao.count(feedback.getMobile(),feedback.getStartTime(),feedback.getEndTime());
		feedback.setTotal(total);
		
 		List<FeedBackVO> feedBackPO=  feedBackDao.queryByPage(feedback.getMobile(),feedback.getStartTime(),feedback.getEndTime(),feedback);
// 		message.addData("list", feedBackPO);
 //		message.addData("page", feedBackpage);
 		model.addAttribute("list", feedBackPO);
 		model.addAttribute("page", feedback);
		logger.info(String.format("getFeedBackList....out...params: "+feedback.toString()));
		return ViewMappings.FEED_BACK_LIST;

	}
}
