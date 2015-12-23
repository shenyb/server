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
import com.need.service.bops.safeCenter.FeedCommentService;

@Controller
@RequestMapping(value = ControllerMappings.FEED_MANAGE_COMMENT)
public class FeedCommentController {
	private static final Logger logger = Logger.getLogger(FeedCommentController.class);
	@Autowired
    private FeedCommentService feedCommentService;
	
	
	@RequestMapping(method = RequestMethod.GET,value = "/page")
	public String queryPageFeedComment(NeedFeedParamsVO needFeedParamsVO,Page categoryPage, Model model){
		//needFeedParamsVO.setFeedStatus("VALID");
		Message success =feedCommentService.queryFeedCommentPage(needFeedParamsVO);
		logger.info(String.format("queryPageFeedComment in FeedCommentController: %s", needFeedParamsVO.toString()));
		model.addAttribute("list", success.getData().get("list"));
		model.addAttribute("page", success.getData().get("page"));
		return ViewMappings.SAFE_TOPIC;	
	}
	
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.DELETE, value = "/{commentId}")
	public Message deleteComment(@PathVariable(value = "commentId") String commentId){
		logger.info(String.format("deleteComment in FeedCommentController: %s", commentId));
		Message success =feedCommentService.deleteFeedComment(commentId);
		return success;
	}
}
