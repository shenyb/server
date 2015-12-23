/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.common.core.service.feed.impl;

import com.need.biz.utils.BizCodeUtil;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.constant.Constants;
import com.need.common.core.dao.jdbc.feed.FeedLabelDAO;
import com.need.common.core.dao.jdbc.feed.FeedLikeDAO;
import com.need.common.core.dao.jdbc.feed.FeedReportDAO;
import com.need.common.core.dao.jdbc.feed.FeedUserDAO;
import com.need.common.core.service.feed.FeedCacheService;
import com.need.common.core.service.feed.FeedListCacheService;
import com.need.common.core.service.feed.FeedService;
import com.need.common.core.utils.PaginationUtil;
import com.need.common.domain.enums.FeedReportStatusEnum;
import com.need.common.domain.enums.FeedStatusEnums;
import com.need.common.domain.po.api.feed.*;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.feed.FeedCommentVO;
import com.need.common.domain.vo.feed.FeedLabelVO;
import com.need.common.domain.vo.feed.FeedVO;
import com.need.common.domain.vo.feed.SimpleFeedVO;
import com.need.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 *
 * @author YAN
 */
@Service
public class FeedServiceImpl implements FeedService {
    
    @Autowired
    private FeedUserDAO feedUserDAO;
    
    @Autowired
    private FeedLabelDAO feedLabelDAO;
    
    @Autowired
    private FeedReportDAO feedReportDAO;
    
    @Autowired
    private FeedLikeDAO feedLikeDAO;
    
    @Autowired
    private FeedListCacheService feedListCacheService;
    
    @Autowired
    private FeedCacheService feedCacheService;

    @Override
    @Transactional
    public Message addFeed(String userId, String feedContent, String feedPicKey, List<FeedLabelVO> feedLabels) {
        FeedUserPO feedUserPO = new FeedUserPO();
        String feedId = BizCodeUtil.generateFeedId(userId);
        feedUserPO.setFeedId(feedId);
        Date createTime = Calendar.getInstance().getTime();
        feedUserPO.setCreateTime(createTime);
        feedUserPO.setFeedContent(feedContent);
        feedUserPO.setFeedPicKey(feedPicKey);
        feedUserPO.setFeedStatus(FeedStatusEnums.VALID.status);
        feedUserPO.setUserId(userId);
        feedUserDAO.insert(feedUserPO);
        feedCacheService.add(feedUserPO);
        if(feedLabels != null) {
            for (FeedLabelVO feedLabelParam : feedLabels) {
                FeedLabelPO feedLabelPO = new FeedLabelPO();
                feedLabelPO.setFeedId(feedId);
                feedLabelPO.setFeedLabelContent(feedLabelParam.getFeedLabelContent());
                feedLabelPO.setFeedLabelType(feedLabelParam.getFeedLabelType());
                feedLabelPO.setFeedLabelId(BizCodeUtil.generateFeedLabelId(feedId, userId));
                feedLabelPO.setHeightPercent(feedLabelParam.getHeightPercent());
                feedLabelPO.setWidthPercent(feedLabelParam.getWidthPercent());
                feedLabelPO.setCreateTime(createTime);
                feedLabelDAO.insert(feedLabelPO);
                feedCacheService.addLabel(feedLabelPO);
            }
        }
        Message message = Message.success();
        message.addData("feed", feedCacheService.getFeedVO(feedId, userId));
        return message;
    }

    @Override
    public Message delFeed(String userId, String feedId) {
        Map<String, String> feedMap = feedCacheService.getById(feedId);
        if(feedMap == null || feedMap.isEmpty()) {
            return Message.success();
        }
        if(!feedMap.get("userId").equals(userId)) {
            return Message.error(BizReturnCode.FEED_DEL_NOT_SELF);
        }
        feedUserDAO.updateDelete(feedId);
        feedCacheService.removeById(feedId, userId);
        return Message.success();
    }

    @Override
    public Message listFeed(String userId, String feedId, String refreshType, int pageSize) {
        List<String> feedIdList = feedListCacheService.getFeedIdList(userId);
        Message message = buildFeedList(feedIdList, userId, feedId, refreshType, pageSize);
        return message;
    }

    private Message buildFeedList(List<String> feedIdList, String userId, String feedId, String refreshType, int pageSize) {
        Message message = Message.success();
        feedIdList = PaginationUtil.getPaginationList(feedIdList, feedId, pageSize, refreshType, message);
        List<FeedVO> feedList = new ArrayList<FeedVO>();
        for(String id : feedIdList) {
            FeedVO feedVO = feedCacheService.getFeedVO(id, userId);
            if(feedVO == null) {
                feedListCacheService.removeFeedId(id);
                continue;
            }
            feedList.add(feedVO);
        }
        message.addData("feedList", feedList);
        return message;
    }

    private Message buildSimpleFeedList(List<String> feedIdList, String userId, String feedId, String refreshType, int pageSize) {
        Message message = Message.success();
        feedIdList = PaginationUtil.getPaginationList(feedIdList, feedId, pageSize, refreshType, message);
        List<SimpleFeedVO> feedList = new ArrayList<SimpleFeedVO>();
        for(String id : feedIdList) {
            SimpleFeedVO feedVO = feedCacheService.getSimpleFeedVO(id, userId);
            if(feedVO == null) {
                feedListCacheService.removeFeedId(id);
                continue;
            }
            feedList.add(feedVO);
        }
        message.addData("feedList", feedList);
        return message;
    }

    @Override
    public Message listUserFeed(String userId, String feedId, String refreshType, int pageSize) {
        if(StringUtil.isBlank(userId)) {
            return Message.error(BizReturnCode.USERID_NOT_EXIST);
        }
        List<String> feedIdList = feedListCacheService.getUserFeedIds(userId);
        Message message = buildSimpleFeedList(feedIdList, userId, feedId, refreshType, pageSize);
        return message;
    }

    @Override
    public Message likeFeed(String userId, String feedId) {
        FeedLikePO feedLike = new FeedLikePO();
        feedLike.setCreateTime(Calendar.getInstance().getTime());
        feedLike.setFeedId(feedId);
        feedLike.setUserId(userId);
        feedLike.setFeedLikeStatus(FeedStatusEnums.VALID.status);
        feedLikeDAO.insert(feedLike);
        feedCacheService.addLike(feedLike);
        return Message.success();
    }

    @Override
    public Message dislikeFeed(String userId, String feedId) {
        FeedLikePOKey feedLikePOKey = new FeedLikePOKey(feedId, userId);
        feedLikeDAO.updateDelete(feedLikePOKey);
        feedCacheService.removeLikeById(feedId, userId);
        return Message.success();
    }

    @Override
    public Message getFeedDetail(String userId, String feedId) {
        FeedVO feedVO = feedCacheService.getFeedVO(feedId, userId);
        if(feedVO == null) {
            feedListCacheService.removeFeedId(feedId);
            return Message.error(BizReturnCode.FEED_NOT_EXIST);
        }
        List<FeedCommentVO> commentList = new ArrayList<FeedCommentVO>();
        for (String commentId : feedCacheService.getCommentIdsByFeedId(feedId)) {
            FeedCommentVO feedComment = feedCacheService.getFeedCommentVO(commentId);
            if(feedComment != null) {
                commentList.add(feedComment);
                if(commentList.size() >= Constants.FEED_DETAIL_COMMENT_COUNT) {
                    break;
                }
            }
        }
        Message message = Message.success();
        message.addData("feed", feedVO);
        message.addData("feedCommentList", commentList);
        return message;
    }

    @Override
    public Message reportFeed(String userId, String feedId, String content) {
        FeedReportPO feedReportPO = new FeedReportPO();
        feedReportPO.setFeedId(feedId);
        feedReportPO.setCreateTime(Calendar.getInstance().getTime());
        feedReportPO.setFeedReportId(BizCodeUtil.generateFeedReportId(feedId, userId));
        feedReportPO.setReportContent(content);
        feedReportPO.setReportStatus(FeedReportStatusEnum.VALID.status);
        feedReportPO.setUserId(userId);
        feedReportDAO.insert(feedReportPO);
        return Message.success();
    }
}
