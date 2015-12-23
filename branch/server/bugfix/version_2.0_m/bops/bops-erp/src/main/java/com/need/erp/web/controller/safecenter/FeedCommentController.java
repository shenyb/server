package com.need.erp.web.controller.safecenter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.erp.constant.ControllerMappings;
import com.need.domain.pub.Message;
import com.need.domain.vo.safeCenter.NeedFeedParamsVO;
import com.need.service.bops.safeCenter.FeedCommentService;

@Controller
@RequestMapping(value = ControllerMappings.FEED_MANAGE_COMMENT)
public class FeedCommentController {
	private static final Logger logger = Logger.getLogger(FeedCommentController.class);
	@Autowired
    private FeedCommentService feedCommentService;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET,value = "/page")
	public Message queryPageFeedComment(NeedFeedParamsVO needFeedParamsVO){
		Message success =feedCommentService.queryFeedCommentPage(needFeedParamsVO);
		logger.info(String.format("queryPageFeedComment in FeedCommentController: %s", needFeedParamsVO.toString()));
		return success;	
	}
	
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.DELETE, value = "/{commentId}")
	public Message deleteComment(@PathVariable(value = "commentId") String commentId){
		logger.info(String.format("deleteComment in FeedCommentController: %s", commentId));
		Message success =feedCommentService.deleteFeedComment(commentId);
		return success;
	}
}
