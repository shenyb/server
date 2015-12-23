/**
 * @ProjectName: bops-ops
 * @Copyright: 2015 by Beijin Weplanter Technology co.,ltd.
 * @address: http://www.weplanter.com
 * @Description: 本内容仅限于稻田(北京)科技有限公司内部使用，禁止转发.
 * @author peiboli
 * @date: 2015年11月27日 下午5:30:58
 * @Title: FeedManageController.java
 * @Package com.need.operation.web.controller.feed
 * @Description: TODO
 */
package com.need.operation.web.controller.feed;

import com.need.dao.api.feed.FeedReportDAO;
import com.need.dao.api.feed.FeedUserDAO;
import com.need.dao.api.user.UserBaseDAO;
import com.need.domain.common.enums.FeedReportStatusEnum;
import com.need.domain.common.enums.UserStatusEnum;
import com.need.domain.po.api.user.UserBase;
import com.need.domain.pub.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.need.domain.vo.feed.FeedPageVO;
import com.need.domain.vo.feed.FeedReportVO;
import com.need.domain.vo.feed.FeedVO;
import com.need.operation.constant.ControllerMappings;
import com.need.operation.constant.ViewMappings;
import com.need.operation.pub.ConstantsProConfig;
import com.need.service.bops.user.UserBaseService;
import com.need.utils.DateUtil;
import com.need.utils.StringUtil;
import java.text.ParseException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * </p> @author peiboli 2015年11月27日 下午5:30:58
 * @ClassName FeedManageController
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年11月27日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.USER_FEED_MANAGE)
public class FeedManageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedManageController.class);
    
	@Autowired
	private ConstantsProConfig constantsProConfig;

    @Autowired
    private UserBaseDAO userBaseDAO;

    @Autowired
    private FeedUserDAO feedUserDAO;

    @Autowired
    private FeedReportDAO feedReportDAO;
    
    @Autowired
    private UserBaseService userBaseService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String listFeed(FeedPageVO feedPageVO, Model model) throws ParseException {
        LOGGER.info("listFeed.....in...params:" + feedPageVO);
        if (feedPageVO.getPage() == null) {
            feedPageVO.setPage(1);
        }
        if (feedPageVO.getPageSize() == null) {
            feedPageVO.setPageSize(Integer.MAX_VALUE);
        }
        if (!StringUtil.isBlank(feedPageVO.getMobile())) {
            UserBase user = userBaseDAO.selectUserBaseByMobile(feedPageVO.getMobile());
            if (user == null) {
                return ViewMappings.FEED_LIST;
            }
            feedPageVO.setUserId(user.getUserId());
        }
        if (!StringUtil.isBlank(feedPageVO.getNickName()) && StringUtil.isBlank(feedPageVO.getUserId())) {
            UserBase user = userBaseDAO.selectUserBaseByNickName(feedPageVO.getNickName());
            if (user == null) {
                return ViewMappings.FEED_LIST;
            }
            feedPageVO.setUserId(user.getUserId());
        }
        if(!StringUtil.isBlank(feedPageVO.getStartTimeString())) {
            feedPageVO.setStartTime(DateUtil.formatStrToDate(feedPageVO.getStartTimeString(), "yyyy-MM-dd HH:mm:ss"));
        }
        if(!StringUtil.isBlank(feedPageVO.getEndTimeString())) {
            feedPageVO.setEndTime(DateUtil.formatStrToDate(feedPageVO.getEndTimeString(), "yyyy-MM-dd HH:mm:ss"));
        }
        feedPageVO.setTotal(feedUserDAO.queryPageFeedCount(feedPageVO));
        List<FeedVO> feedList = feedUserDAO.queryPageFeed(feedPageVO);
        model.addAttribute("list", feedList);
        model.addAttribute("page", feedPageVO);
		model.addAttribute("picHost", constantsProConfig.getPic_domain());
        return ViewMappings.FEED_LIST;
    }

    @RequestMapping(value = "/report/page", method = RequestMethod.GET)
    public String listReportFeed(FeedPageVO feedPageVO, Model model) throws ParseException {
        LOGGER.info("listReportFeed.....in...params:" + feedPageVO);
        if (feedPageVO.getPage() == null) {
            feedPageVO.setPage(1);
        }
        if (feedPageVO.getPageSize() == null) {
            feedPageVO.setPageSize(Integer.MAX_VALUE);
        }
        if (!StringUtil.isBlank(feedPageVO.getMobile())) {
            UserBase user = userBaseDAO.selectUserBaseByMobile(feedPageVO.getMobile());
            if (user == null) {
                return ViewMappings.FEED_REPORT_LIST;
            }
            feedPageVO.setUserId(user.getUserId());
        }
        if (!StringUtil.isBlank(feedPageVO.getNickName()) && StringUtil.isBlank(feedPageVO.getUserId())) {
            UserBase user = userBaseDAO.selectUserBaseByNickName(feedPageVO.getNickName());
            if (user == null) {
                return ViewMappings.FEED_REPORT_LIST;
            }
            feedPageVO.setUserId(user.getUserId());
        }
        if(!StringUtil.isBlank(feedPageVO.getStartTimeString())) {
            feedPageVO.setStartTime(DateUtil.formatStrToDate(feedPageVO.getStartTimeString(), "yyyy-MM-dd HH:mm:ss"));
        }
        if(!StringUtil.isBlank(feedPageVO.getEndTimeString())) {
            feedPageVO.setEndTime(DateUtil.formatStrToDate(feedPageVO.getEndTimeString(), "yyyy-MM-dd HH:mm:ss"));
        }
        feedPageVO.setTotal(feedReportDAO.queryPageFeedCount(feedPageVO));
        List<FeedReportVO> feedList = feedReportDAO.queryPageFeed(feedPageVO);
        model.addAttribute("list", feedList);
        model.addAttribute("page", feedPageVO);
		model.addAttribute("picHost", constantsProConfig.getPic_domain());
        return ViewMappings.FEED_REPORT_LIST;
    }

    @RequestMapping(value = "/handledReport/page", method = RequestMethod.GET)
    public String listHandledReportFeed(FeedPageVO feedPageVO, Model model) throws ParseException {
        LOGGER.info("listReportFeed.....in...params:" + feedPageVO);
        if (feedPageVO.getPage() == null) {
            feedPageVO.setPage(1);
        }
        if (feedPageVO.getPageSize() == null) {
            feedPageVO.setPageSize(Integer.MAX_VALUE);
        }
        if (!StringUtil.isBlank(feedPageVO.getMobile())) {
            UserBase user = userBaseDAO.selectUserBaseByMobile(feedPageVO.getMobile());
            if (user == null) {
                return ViewMappings.FEED_REPORT_LIST;
            }
            feedPageVO.setUserId(user.getUserId());
        }
        if (!StringUtil.isBlank(feedPageVO.getNickName()) && StringUtil.isBlank(feedPageVO.getUserId())) {
            UserBase user = userBaseDAO.selectUserBaseByNickName(feedPageVO.getNickName());
            if (user == null) {
                return ViewMappings.FEED_REPORT_LIST;
            }
            feedPageVO.setUserId(user.getUserId());
        }
        if(!StringUtil.isBlank(feedPageVO.getStartTimeString())) {
            feedPageVO.setStartTime(DateUtil.formatStrToDate(feedPageVO.getStartTimeString(), "yyyy-MM-dd HH:mm:ss"));
        }
        if(!StringUtil.isBlank(feedPageVO.getEndTimeString())) {
            feedPageVO.setEndTime(DateUtil.formatStrToDate(feedPageVO.getEndTimeString(), "yyyy-MM-dd HH:mm:ss"));
        }
        feedPageVO.setTotal(feedReportDAO.queryHandledPageFeedCount(feedPageVO));
        List<FeedReportVO> feedList = feedReportDAO.queryHandledPageFeed(feedPageVO);
        model.addAttribute("list", feedList);
        model.addAttribute("page", feedPageVO);
		model.addAttribute("picHost", constantsProConfig.getPic_domain());
        return ViewMappings.FEED_HANDLED_REPORT_LIST;
    }

	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT, value="/freeze/{userId}")
	public Message frozen(@PathVariable String userId) {
		LOGGER.info("in feed frozen userId: " + userId);
        Message message = userBaseService.changeUserStatus(userId, UserStatusEnum.FREEZE.code);
        return message;
    }

	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT, value="reset/{feedReportId}/{userId}")
	public Message reset(@PathVariable String feedReportId, @PathVariable String userId) {
		LOGGER.info("in feed reset userId: " + userId + " and feedReportId: " + feedReportId);
        Message message = userBaseService.changeUserStatus(userId, UserStatusEnum.NORMAL.code);
        feedReportDAO.updateStatus(feedReportId, FeedReportStatusEnum.IGNORE.status);
        return message;
    }

	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT, value="/report/freezeReport/{feedReportId}/{feedUserId}")
	public Message freezeReport(@PathVariable String feedReportId, @PathVariable String feedUserId) {
		LOGGER.info("in feed freezeReport feedReportId: " + feedReportId + " and feedUserId: " + feedUserId);
        feedReportDAO.updateStatus(feedReportId, FeedReportStatusEnum.FROZEN.status);
        Message message = userBaseService.changeUserStatus(feedUserId, UserStatusEnum.FREEZE.code);
        return message;
    }

	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT, value="/report/ignoreReport/{feedReportId}")
	public Message ignoreReport(@PathVariable String feedReportId) {
		LOGGER.info("in feed ignoreReport feedReportId: " + feedReportId);
        feedReportDAO.updateStatus(feedReportId, FeedReportStatusEnum.IGNORE.status);
        return Message.success();
    }
	
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT, value="/del/{feedId}")
	public Message delFeed(@PathVariable String feedId) {
		LOGGER.info("delFeed  in   feedId: " + feedId);
		Message message = userBaseService.delFeed(feedId);
        return message;
    }

}
