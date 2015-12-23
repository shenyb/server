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
 * <p></p>
 * @author LXD 2015年8月8日 下午3:09:08
 * @ClassName GetHomeOps
 * @Description TODO  获取某一feed评论列表
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年8月8日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.FEED_COMMENT_LIST)
public class GetCommentListController {
	private static final Logger logger = Logger.getLogger(GetCommentListController.class);
	@Autowired
	private NeedService needService;
	
	/**
	 * 
	 * @author LXD 2015年8月9日 上午10:29:56
	 * @Method: GetHotGoods 
	 * @Description: 获取feed的评论列表
 	 * @param userId
	 * @return 
	 * @throws
	 */
	@ParamsValidate(value={
			@ParamValidate(name="feedId",notNull=true,code=BizReturnCode.NEED_FEEDID_NOT_EXIST)
	})
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.GET)
	public Message getFeedComments(@RequestParam(required = true) String feedId,
			@RequestParam(required = false) Integer pageNum, @RequestParam(required = false) Integer pageSize) {
		logger.info(String.format("feedId: %s", feedId));
		if(pageNum == null){
			pageNum = 1;
		}
		if(pageSize == null){
			pageSize = 10;
		}
		
		Message message = needService.queryFeedComments(feedId,pageNum,pageSize);
		return message;
	}

}
