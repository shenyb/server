package com.need.api.web.controller.need;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.service.need.NeedService;
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
 * <p>
 * </p>
 * 
 * @author LXD 2015年8月8日 下午3:09:08
 * @ClassName GetHomeOps
 * @Description TODO 给feed流添加评论
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年8月8日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.ADD_FEED_COMMENT)
public class AddCommentController {
	private static final Logger logger = Logger.getLogger(AddCommentController.class);
	@Autowired
	private NeedService needService;

	/**
	 * 
	 * @author LXD 2015年8月9日 上午10:29:56 @Method: GetHotGoods @Description:
	 * 给feed流添加评论 @param userId @return @throws
	 */
	@ParamsValidate(value={
			@ParamValidate(name="feedId",notNull=true,code=BizReturnCode.NEED_FEEDID_NOT_EXIST),
			@ParamValidate(name="userId",notNull=true,code=BizReturnCode.NEED_USERID_NOT_EXIST),
			@ParamValidate(name="content",notNull=true,code=BizReturnCode.NEED_FEED_CONTENT_NOT_EXIST)
	})
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.POST)
	public Message addFeedComment(@RequestParam(required = true) String feedId,
			@RequestParam(required = true) String userId, @RequestParam(required = true) String content) {
		logger.info(String.format("addFeedComment in feedId&&&userId&&&content :%s", feedId+"&&&"+userId+"&&&"+content));
		if(content.length()>140){
			return Message.error(BizReturnCode.NEED_FEED_MEMO_COMMENT_LENGTH);
		}
		Message message=needService.addfeedComment(feedId,userId,content);
		return message;
	}

}
