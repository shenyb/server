/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.common.core.service.feed.impl;

import com.need.biz.utils.BizCodeUtil;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.dao.jdbc.feed.FeedCommentDAO;
import com.need.common.core.service.feed.FeedCacheService;
import com.need.common.core.service.feed.FeedCommentService;
import com.need.common.core.service.user.UserCacheService;
import com.need.common.core.utils.PaginationUtil;
import com.need.common.domain.enums.FeedStatusEnums;
import com.need.common.domain.po.api.feed.FeedCommentPO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.feed.FeedCommentVO;
import com.need.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 *
 * @author YAN
 */
@Service
public class FeedCommentServiceImpl implements FeedCommentService {
    
    @Autowired
    private FeedCommentDAO feedCommentDAO;
    
    @Autowired
    private UserCacheService userCacheService;
    
    @Autowired
    private FeedCacheService feedCacheService;

    @Override
    public Message addFeedComment(String userId, String feedId, String commenttContent, String replyId) {
        if(!StringUtil.isBlank(replyId)) {
            Map<String, String> replyUser = userCacheService.getById(replyId);
            if(replyUser == null || replyUser.isEmpty()) {
                return Message.error(BizReturnCode.USER_NOT_EXIST);
            }
            if(userId.equals(replyId)) {
                return Message.error(BizReturnCode.FEED_COMMENT_NOT_SELF);
            }
        }
        FeedCommentPO feedCommentPO = new FeedCommentPO();
        feedCommentPO.setCommentContent(commenttContent);
        feedCommentPO.setCommentStatus(FeedStatusEnums.VALID.status);
        feedCommentPO.setCreateTime(Calendar.getInstance().getTime());
        String feedCommentId = BizCodeUtil.generateFeedCommentId(feedId, userId);
        feedCommentPO.setFeedCommentId(feedCommentId);
        feedCommentPO.setFeedId(feedId);
        feedCommentPO.setReplyUserId(replyId);
        feedCommentPO.setUserId(userId);
        feedCommentDAO.insert(feedCommentPO);
        feedCacheService.addComment(feedCommentPO);
        Message message = Message.success();
        FeedCommentVO feedComment = feedCacheService.getFeedCommentVO(feedCommentId);
        message.addData("feedComment", feedComment);
        return message;
    }

    @Override
    public Message delFeedComment(String userId, String feedId, String feedCommentId) {
        Map<String, String> commentMap = feedCacheService.getCommentById(feedId);
        if(commentMap != null && !commentMap.isEmpty()) {
            if(!userId.equals(commentMap.get("userId"))) {
                return Message.error(BizReturnCode.FEED_COMMENT_DEL_NOT_SELF);
            }
            feedCommentDAO.deleteByPrimaryKey(feedCommentId);
            feedCacheService.removeCommentById(feedId, feedCommentId);
        }
        return Message.success();
    }

    @Override
    public Message listFeedComment(String userId, String feedId, String feedCommentId, String refreshType, int pageSize) {
        Message message = Message.success();
        List<String> feedCommentIds = feedCacheService.getCommentIdsByFeedId(feedId);
        feedCommentIds = PaginationUtil.getPaginationList(feedCommentIds, feedCommentId, pageSize, refreshType, message);
        List<FeedCommentVO> feedComments = new ArrayList<FeedCommentVO>();
        for(String commentId : feedCommentIds) {
            FeedCommentVO feedComment = feedCacheService.getFeedCommentVO(commentId);
            if(feedComment != null) {
                feedComments.add(feedComment);
            }
        }
        message.addData("feedCommentList", feedComments);
        return message;
    }
}
