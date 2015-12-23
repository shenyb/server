/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.need.common.core.service.feed.impl;

import com.need.common.core.constant.Constants;
import com.need.common.core.service.feed.FeedCacheService;
import com.need.common.core.service.feed.FeedListCacheService;
import com.need.common.core.service.follow.FollowCacheService;
import com.need.common.core.service.user.UserCacheService;
import com.need.common.domain.enums.FollowStatusEnum;
import com.need.common.domain.enums.UserTypeEnum;
import com.need.jedis.JedisSentinelClient;
import com.need.jedis.constants.RedisKeyConstant;
import com.need.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author YAN
 */
@Service
public class FeedListCacheServiceImpl implements FeedListCacheService {
    
    @Autowired
    private FeedCacheService feedCacheService;
    
    @Autowired
    private FollowCacheService followCacheService;
    
    @Autowired
    private UserCacheService userCacheService;

    @Override
    public List<String> getFeedIdList(String userId) {
        String key;
        if(StringUtil.isBlank(userId)) {
            key = RedisKeyConstant.FEED_LIST_IDS_OF_KOL;
        } else {
            key = RedisKeyConstant.FEED_LIST_IDS_OF_USER_ID.concat(userId);
        }
        return getFeedIds(key);
    }

    private List<String> getFeedIds(String key) {
        Set<String> feeds = JedisSentinelClient.zrevall(key);
        return new ArrayList<String>(feeds);
    }

    @Override
    public List<String> getUserFeedIds(String userId) {
        String key = RedisKeyConstant.USER_FEED_LIST_IDS_OF_USER_ID.concat(userId);
        return getFeedIds(key);
    }

    @Override
    public void addFeedId(String feedId) {
        Map<String, String> feedMap = feedCacheService.getById(feedId);
        addFeedId(feedId, Long.valueOf(feedMap.get("createTime")), feedMap.get("userId"));
    }

    @Override
    public void addFeedId(String feedId, Long time, String feedUserId) {
        Map<String, String> userMap = userCacheService.getById(feedUserId);
        if(userMap == null) {
            return;
        }
        JedisSentinelClient.zadd(RedisKeyConstant.USER_FEED_LIST_IDS_OF_USER_ID.concat(feedUserId), time, feedId);
        addFixedLengthFeed(RedisKeyConstant.FEED_LIST_IDS_OF_USER_ID.concat(feedUserId), time, feedId);
        addFixedLengthFeed(RedisKeyConstant.FEED_LIST_IDS_OF_ALL, time, feedId);
        if(userMap.get("userType").equals(UserTypeEnum.KOL.code)) {
            addFixedLengthFeed(RedisKeyConstant.FEED_LIST_IDS_OF_KOL, time, feedId);
        }
        for(String targetId : followCacheService.listByUserId(feedUserId, FollowStatusEnum.FOLLOWED.status)) {
            addFixedLengthFeed(RedisKeyConstant.FEED_LIST_IDS_OF_USER_ID.concat(targetId), time, feedId);
        }
    }

    private void addFixedLengthFeed(String key, Long time, String feedId) {
        JedisSentinelClient.zadd(key, time, feedId);
        if(JedisSentinelClient.zcount(key, Double.MIN_VALUE, Double.MIN_VALUE) >= Constants.MAX_FEED_LIST_LENGTH) {
            JedisSentinelClient.zremrangeByRank(key, Constants.BUFFERED_FEED_LIST_LENGTH + 1, -1);
        }
    }

    @Override
    public void removeFeedId(String feedId) {
        for(String key : JedisSentinelClient.keys(RedisKeyConstant.FEED_LIST_IDS_OF.concat("*"))) {
            JedisSentinelClient.zrem(key, feedId);
        }
    }

    @Override
    public void removeFeedId(String feedId, String feedUserId) {
        removeFeedId(feedId);
        JedisSentinelClient.zrem(RedisKeyConstant.USER_FEED_LIST_IDS_OF_USER_ID.concat(feedUserId), feedId);
    }

    @Override
    public void addFollowUserFeed(String userId, String targetId) {
        String key = RedisKeyConstant.USER_FEED_LIST_IDS_OF_USER_ID.concat(targetId);
        String feedKey = RedisKeyConstant.FEED_LIST_IDS_OF_USER_ID.concat(userId);
        JedisSentinelClient.zunionstore(feedKey, feedKey, key);
        if(JedisSentinelClient.zcount(key, Double.MIN_VALUE, Double.MIN_VALUE) > Constants.MAX_FEED_LIST_LENGTH) {
            JedisSentinelClient.zremrangeByRank(key, Constants.BUFFERED_FEED_LIST_LENGTH + 1, -1);
        }
    }

    @Override
    public void delFollowUserFeed(String userId, String targetId) {
        Set<String> userFeedIds = JedisSentinelClient.zrange(RedisKeyConstant.USER_FEED_LIST_IDS_OF_USER_ID.concat(targetId), 0, Constants.BUFFERED_FEED_LIST_LENGTH);
        for(String feedId : userFeedIds) {
            JedisSentinelClient.zrem(RedisKeyConstant.FEED_LIST_IDS_OF_USER_ID.concat(userId), feedId);
        }
    }
    
}
