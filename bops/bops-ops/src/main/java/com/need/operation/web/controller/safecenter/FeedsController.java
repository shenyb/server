package com.need.operation.web.controller.safecenter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.domain.pub.Message;
import com.need.domain.pub.Page;
import com.need.domain.vo.safeCenter.NeedFeedParamsVO;
import com.need.operation.constant.ControllerMappings;
import com.need.operation.constant.ViewMappings;
import com.need.operation.pub.ConstantsProConfig;
import com.need.service.bops.safeCenter.SafeCenterService;

/**
   * 
   * <p></p>
   * @author LXD 2015年8月28日 下午2:38:07
   * @ClassName FeedsController
   * @Description TODO feed流管理
   * @version V1.0   
   * @modificationHistory=========================逻辑或功能性重大变更记录
   * @modify by user: LXD 2015年8月28日
   * @modify by reason:{方法名}:{原因}
   */
@Controller
@RequestMapping(value = ControllerMappings.FEED_MANAGE)
 public class FeedsController {
	private static final Logger logger = Logger.getLogger(FeedsController.class);
	@Autowired
	private SafeCenterService  safeCenterService;
	@Autowired
	private ConstantsProConfig constantsProConfig;
	
	@RequestMapping(method = RequestMethod.GET,value = "/page")
	public String queryPageFeeds(NeedFeedParamsVO needFeedParamsVO,Page categoryPage, Model model){
		//needFeedParamsVO.setFeedStatus("VALID");
		Message success =safeCenterService.queryFeedsPage(needFeedParamsVO);
		logger.info(String.format("queryPageFeeds in FeedsController: %s",needFeedParamsVO.toString()));
		model.addAttribute("list", success.getData().get("list"));
		model.addAttribute("page", success.getData().get("page"));
		model.addAttribute("location", "12");
		model.addAttribute("picHost", constantsProConfig.getPic_domain());
		return ViewMappings.SAFE_FEED;	
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.DELETE, value = "/{feedId}")
	public Message deletefeed(@PathVariable(value = "feedId") String feedId){
		logger.info(String.format("deletefeed in FeedsController: %s", feedId));
		Message success =safeCenterService.deleteFeed(feedId);
		return success;
	}
	
}
