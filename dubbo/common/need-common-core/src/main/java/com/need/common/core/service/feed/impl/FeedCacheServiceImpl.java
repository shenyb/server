/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.common.core.service.feed.impl;

import com.need.common.core.constant.Constants;
import com.need.common.core.dao.jdbc.feed.FeedCommentDAO;
import com.need.common.core.dao.jdbc.feed.FeedLabelDAO;
import com.need.common.core.dao.jdbc.feed.FeedLikeDAO;
import com.need.common.core.dao.jdbc.feed.FeedUserDAO;
import com.need.common.core.service.feed.FeedCacheService;
import com.need.common.core.service.feed.FeedListCacheService;
import com.need.common.core.service.user.UserCacheService;
import com.need.common.core.utils.BeanUtil;
import com.need.common.core.utils.MapConverter;
import com.need.common.domain.enums.FeedStatusEnums;
import com.need.common.domain.po.api.feed.FeedCommentPO;
import com.need.common.domain.po.api.feed.FeedLabelPO;
import com.need.common.domain.po.api.feed.FeedLikePO;
import com.need.common.domain.po.api.feed.FeedUserPO;
import com.need.common.domain.vo.feed.FeedCommentVO;
import com.need.common.domain.vo.feed.FeedDetailVO;
import com.need.common.domain.vo.feed.FeedVO;
import com.need.common.domain.vo.feed.SimpleFeedVO;
import com.need.common.domain.vo.user.UserVO;
import com.need.jedis.JedisSentinelClient;
import com.need.jedis.constants.RedisKeyConstant;
import com.need.utils.PropertiesUtil;
import com.need.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *
 * @author YAN
 */
@Service
public class FeedCacheServiceImpl implements FeedCacheService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedCacheServiceImpl.class);

    @Autowired
    private FeedListCacheService feedListCacheService;

    @Autowired
    private UserCacheService userCacheService;

    @Autowired
    private FeedUserDAO feedUserDAO;

    @Autowired
    private FeedLabelDAO feedLabelDAO;

    @Autowired
    private FeedLikeDAO feedLikeDAO;

    @Autowired
    private FeedCommentDAO feedCommentDAO;

    @Override
    public Map<String, String> add(FeedUserPO feed) {
        if (feed == null) {
            return null;
        }
        String feedId = feed.getFeedId();
        String userId = feed.getUserId();
        if (FeedStatusEnums.DELETED.status.equals(feed.getFeedStatus())) {
            removeById(feedId, userId);
            return null;
        }
        long expires = Constants.FEED_EXPIRES;
        long createTime = feed.getCreateTime().getTime();
        expires = (expires * 1000 + createTime - System.currentTimeMillis()) / 1000;
        expires = Math.max(expires, Constants.EXPIRED_FEED_EXPIRES);
        String key = RedisKeyConstant.FEED_OF_ID.concat(feedId);
        Map<String, String> stringMap = MapConverter.convert(BeanUtil.toMap(feed));
        JedisSentinelClient.hmset(key, stringMap);
        JedisSentinelClient.expire(key, (int) expires);
        feedListCacheService.addFeedId(feedId, createTime, userId);
        return stringMap;
    }

    @Override
    public void addLabel(FeedLabelPO feedLabelPO) {
        if (feedLabelPO == null) {
            return;
        }
        String labelId = feedLabelPO.getFeedLabelId();
        String feedId = feedLabelPO.getFeedId();
        int expires = (int) getExpires(feedId);
        if(expires == 0 || expires == -2) {
            return;
        }
        String key = RedisKeyConstant.FEED_LABEL_OF_ID.concat(labelId);
        JedisSentinelClient.hmset(key, MapConverter.convert(BeanUtil.toMap(feedLabelPO)));
        LOGGER.info("add feed label to redis, expires:{} and lable:{}", expires, feedLabelPO);
        JedisSentinelClient.expire(key, expires);
        String listKey = RedisKeyConstant.FEED_LABEL_IDS_OF_FEED_ID.concat(feedId);
        JedisSentinelClient.sadd(listKey, labelId);
        JedisSentinelClient.expire(listKey, expires);
    }

    @Override
    public void addLike(FeedLikePO feedLikePO) {
        if (feedLikePO == null) {
            return;
        }
        String likeUserId = feedLikePO.getUserId();
        String feedId = feedLikePO.getFeedId();
        long createTime = feedLikePO.getCreateTime().getTime();
        String listKey = RedisKeyConstant.FEED_LIKE_USER_IDS_OF_FEED_ID.concat(feedId);
        JedisSentinelClient.zadd(listKey, createTime, likeUserId);
        JedisSentinelClient.expire(listKey, (int) getExpires(feedId));
    }

    @Override
    public void addComment(FeedCommentPO feedCommentPO) {
        if (feedCommentPO == null) {
            return;
        }
        String commentId = feedCommentPO.getFeedCommentId();
        String feedId = feedCommentPO.getFeedId();
        String key = RedisKeyConstant.FEED_COMMENT_OF_ID.concat(commentId);
        JedisSentinelClient.hmset(key, MapConverter.convert(BeanUtil.toMap(feedCommentPO)));
        int expires = (int) getExpires(feedId);
        JedisSentinelClient.expire(key, expires);
        String listKey = RedisKeyConstant.FEED_COMMENTIDS_OF_FEED_ID.concat(feedId);
        JedisSentinelClient.zadd(listKey, feedCommentPO.getCreateTime().getTime(), commentId);
        JedisSentinelClient.expire(listKey, expires);
    }

    @Override
    public void removeById(String feedId, String userId) {
        LOGGER.info("remove feed which feedId is {} and userId is {}", feedId, userId);
        feedListCacheService.removeFeedId(feedId, userId);
        JedisSentinelClient.del(RedisKeyConstant.FEED_OF_ID.concat(feedId));
        JedisSentinelClient.del(RedisKeyConstant.FEED_COMMENTIDS_OF_FEED_ID.concat(feedId));
        JedisSentinelClient.del(RedisKeyConstant.FEED_LIKE_USER_IDS_OF_FEED_ID.concat(feedId));
        JedisSentinelClient.del(RedisKeyConstant.FEED_LABEL_IDS_OF_FEED_ID.concat(feedId));
    }

    @Override
    public void removeLikeById(String feedId, String userId) {
        JedisSentinelClient.zrem(RedisKeyConstant.FEED_LIKE_USER_IDS_OF_FEED_ID.concat(feedId), userId);
    }

    @Override
    public void removeCommentById(String feedId, String commentId) {
        JedisSentinelClient.zrem(RedisKeyConstant.FEED_COMMENTIDS_OF_FEED_ID.concat(feedId), commentId);
        JedisSentinelClient.del(RedisKeyConstant.FEED_COMMENT_OF_ID.concat(commentId));
    }

    @Override
    public Map<String, String> getById(String feedId) {
        if (StringUtil.isBlank(feedId)) {
            return null;
        }
        Map<String, String> cache = JedisSentinelClient.hgetAll(RedisKeyConstant.FEED_OF_ID.concat(feedId));
        if (cache == null || cache.isEmpty()) {
            FeedUserPO feed = feedUserDAO.selectByPrimaryKey(feedId);
            if (feed == null) {
                return null;
            }
            LOGGER.info("feed reload which feed is {}", feed);
            reloadFeed(feed);
            return MapConverter.convert(BeanUtil.toMap(feed));
        }
        return cache;
    }

    private Map<String, String> getLabelById(String labelId) {
        if (StringUtil.isBlank(labelId)) {
            return null;
        }
        Map<String, String> cache = JedisSentinelClient.hgetAll(RedisKeyConstant.FEED_LABEL_OF_ID.concat(labelId));
        if (cache == null || cache.isEmpty()) {
            FeedLabelPO label = feedLabelDAO.selectByPrimaryKey(labelId);
            if (label == null) {
                return null;
            }
            addLabel(label);
            return MapConverter.convert(BeanUtil.toMap(label));
        }
        return cache;
    }

    @Override
    public Map<String, String> getCommentById(String commentId) {
        if (StringUtil.isBlank(commentId)) {
            return null;
        }
        Map<String, String> cache = JedisSentinelClient.hgetAll(RedisKeyConstant.FEED_COMMENT_OF_ID.concat(commentId));
        if (cache == null || cache.isEmpty()) {
            FeedCommentPO comment = feedCommentDAO.selectByPrimaryKey(commentId);
            if (comment == null) {
                return null;
            }
            addComment(comment);
            return MapConverter.convert(BeanUtil.toMap(comment));
        }
        return cache;
    }

    private void reloadFeed(FeedUserPO feed) {
        Map<String, String> feedMap = add(feed);
        if (feedMap == null) {
            return;
        }
        String feedId = feed.getFeedId();
        LOGGER.info("reload feed which feedId is {}", feedId);
        JedisSentinelClient.del(RedisKeyConstant.FEED_LABEL_IDS_OF_FEED_ID.concat(feedId));
        for (FeedLabelPO feedLabelPO : feedLabelDAO.queryByFeedId(feedId)) {
            addLabel(feedLabelPO);
        }
        JedisSentinelClient.del(RedisKeyConstant.FEED_LIKE_USER_IDS_OF_FEED_ID.concat(feedId));
        for (FeedLikePO feedLikePO : feedLikeDAO.queryByFeedId(feedId)) {
            addLike(feedLikePO);
        }
        JedisSentinelClient.del(RedisKeyConstant.FEED_COMMENTIDS_OF_FEED_ID.concat(feedId));
        for (FeedCommentPO feedCommentPO : feedCommentDAO.queryByFeedId(feedId)) {
            addComment(feedCommentPO);
        }
    }

    @Override
    public long getExpires(String feedId) {
        return JedisSentinelClient.ttl(RedisKeyConstant.FEED_OF_ID.concat(feedId));
    }
    
    @Override
    public List<String> getLikeUserIdsByFeedId(String feedId) {
        String key = RedisKeyConstant.FEED_LIKE_USER_IDS_OF_FEED_ID.concat(feedId);
        return getItemIdsByFeedId(key);
    }

    private List<String> getItemIdsByFeedId(String key) {
        return new ArrayList<String>(JedisSentinelClient.zrevall(key));
    }

    @Override
    public List<String> getCommentIdsByFeedId(String feedId) {
        String key = RedisKeyConstant.FEED_COMMENTIDS_OF_FEED_ID.concat(feedId);
        return new ArrayList<String>(JedisSentinelClient.zrevall(key));
    }

    private Set<String> getLabelIdsByFeedId(String feedId) {
        String key = RedisKeyConstant.FEED_LABEL_IDS_OF_FEED_ID.concat(feedId);
        return JedisSentinelClient.smembers(key);
    }

    @Override
    public List<String> getLastCommentIdsByFeedId(String feedId, int size) {
        String key = RedisKeyConstant.FEED_COMMENTIDS_OF_FEED_ID.concat(feedId);
        return new ArrayList<String>(JedisSentinelClient.zrange(key, -size, -1));
    }

    @Override
    public int getCommentCount(String feedId) {
        String key = RedisKeyConstant.FEED_COMMENTIDS_OF_FEED_ID.concat(feedId);
        return (int) JedisSentinelClient.zcount(key, Double.MIN_VALUE, Double.MAX_VALUE);
    }

    @Override
    public int getLikeCount(String feedId) {
        String key = RedisKeyConstant.FEED_LIKE_USER_IDS_OF_FEED_ID.concat(feedId);
        return (int) JedisSentinelClient.zcount(key, Double.MIN_VALUE, Double.MAX_VALUE);
    }

    @Override
    public FeedVO getFeedVO(String feedId, String userId) {
        FeedVO feedVO = buildFeedVO(feedId);
        if(feedVO == null) {
            return null;
        }
        Boolean isLike = StringUtil.isBlank(userId) ? false : getLikeUserIdsByFeedId(feedId).contains(userId);
        feedVO.setIsLike(isLike.toString().toUpperCase());
        return feedVO;
    }

    @Override
    public SimpleFeedVO getSimpleFeedVO(String feedId, String userId) {
        Map<String, String> feedMap = getById(feedId);
        if (feedMap == null || feedMap.isEmpty()) {
            return null;
        }
        SimpleFeedVO simpleFeedVO = new SimpleFeedVO();
        simpleFeedVO.setFeedId(feedId);
        simpleFeedVO.setFeedPicKey(feedMap.get("feedPicKey"));
        return simpleFeedVO;
    }

    private FeedDetailVO buildFeedVO(String feedId) {
        Map<String, String> feedMap = getById(feedId);
        if (feedMap == null || feedMap.isEmpty()) {
            return null;
        }
        String feedUserId = feedMap.get("userId");
        Map<String, String> userMap = userCacheService.getById(feedUserId);
        if (userMap == null || userMap.isEmpty()) {
            return null;
        }
        FeedDetailVO feedDetailVO = new FeedDetailVO();
        feedDetailVO.setFeedId(feedId);
        feedDetailVO.setCreateTime(Long.valueOf(feedMap.get("createTime")));
        feedDetailVO.setFeedCommentCount(getCommentCount(feedId));
        feedDetailVO.setFeedContent(feedMap.get("feedContent"));
        feedDetailVO.setFeedLikeCount(getLikeCount(feedId));
        feedDetailVO.setFeedPicKey(feedMap.get("feedPicKey"));
        UserVO user = new UserVO();
        user.setNickName(userMap.get("nickName"));
        user.setProfilePicKey(userMap.get("profilePicKey"));
        user.setUserType(userMap.get("userType"));
        user.setUserId(feedUserId);
        feedDetailVO.setUser(user);
        List<FeedLabelPO> labelList = new ArrayList<FeedLabelPO>();
        for (String labelId : getLabelIdsByFeedId(feedId)) {
            Map<String, String> labelMap = getLabelById(labelId);
            FeedLabelPO feedLabelPO = new FeedLabelPO();
            feedLabelPO.setFeedId(labelMap.get("feedId"));
            feedLabelPO.setFeedLabelContent(labelMap.get("feedLabelContent"));
            feedLabelPO.setFeedLabelType(labelMap.get("feedLabelType"));
            feedLabelPO.setFeedLabelId(labelMap.get("feedLabelId"));
            feedLabelPO.setHeightPercent(labelMap.get("heightPercent"));
            feedLabelPO.setWidthPercent(labelMap.get("widthPercent"));
            labelList.add(feedLabelPO);
        }
        feedDetailVO.setFeedLabels(labelList);
        return feedDetailVO;
    }

    @Override
    public FeedDetailVO getFeedDetailVO(String feedId, String userId) {
        FeedDetailVO feedDetailVO = buildFeedVO(feedId);
        if(feedDetailVO == null) {
            return null;
        }
        Boolean isLike = StringUtil.isBlank(userId) ? false : getLikeUserIdsByFeedId(feedId).contains(userId);
        feedDetailVO.setIsLike(isLike.toString().toUpperCase());
        List<FeedCommentVO> commentList = new ArrayList<FeedCommentVO>();
        for (String commentId : getCommentIdsByFeedId(feedId)) {
            FeedCommentVO feedComment = getFeedCommentVO(commentId);
            if(feedComment != null) {
                commentList.add(feedComment);
                if(commentList.size() >= Constants.FEED_DETAIL_COMMENT_COUNT) {
                    break;
                }
            }
        }
        feedDetailVO.setFeedCommentList(commentList);
        return feedDetailVO;
    }

    @Override
    public FeedCommentVO getFeedCommentVO(String feedCommentId) {
        Map<String, String> commentMap = getCommentById(feedCommentId);
        String commentUserId = commentMap.get("userId");
        Map<String, String> commentUserMap = userCacheService.getById(commentUserId);
        if (commentUserMap == null || commentUserMap.isEmpty()) {
            return null;
        }
        FeedCommentVO feedComment = new FeedCommentVO();
        feedComment.setCommentContent(commentMap.get("commentContent"));
        feedComment.setCommentUserId(commentUserId);
        feedComment.setCommentUserName(commentUserMap.get("nickName"));
        feedComment.setProfilePicKey(commentUserMap.get("profilePicKey"));
        feedComment.setCreateTime(Long.valueOf(commentMap.get("createTime")));
        feedComment.setFeedCommentId(commentMap.get("feedCommentId"));
        feedComment.setFeedId(commentMap.get("feedId"));
        String replyId = commentMap.get("replyUserId");
        if (!StringUtil.isBlank(replyId)) {
            Map<String, String> replyUserMap = userCacheService.getById(replyId);
            if (replyUserMap == null || replyUserMap.isEmpty()) {
                return null;
            }
            feedComment.setReplyId(replyId);
            feedComment.setReplyName(replyUserMap.get("nickName"));
        }
        return feedComment;
    }

    @Override
    public void init() {
        String initFeed = PropertiesUtil.getProperty("/properties/constants.properties", "initFeed");
        if(!initFeed.equals(Boolean.TRUE.toString().toUpperCase())) {
            return;
        }
        long time = System.currentTimeMillis();
        LOGGER.info("-+-+-+-+-+-+-+-+-+-+-+-+-+-+feed redis cache init start-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        long expires = Constants.FEED_EXPIRES;
        Date createTime = Calendar.getInstance().getTime();
        createTime.setTime(time - expires * 1000);
        List<FeedUserPO> feedList = feedUserDAO.queryByTime(createTime);
        for (FeedUserPO feed : feedList) {
            add(feed);
        }
        List<FeedLabelPO> labelList = feedLabelDAO.queryByTime(createTime);
        LOGGER.info("load feedLabel size:{}", labelList.size());
        for (FeedLabelPO label : labelList) {
            addLabel(label);
        }
        List<FeedLikePO> likeList = feedLikeDAO.queryByTime(createTime);
        for (FeedLikePO like : likeList) {
            addLike(like);
        }
        List<FeedCommentPO> commentList = feedCommentDAO.queryByTime(createTime);
        for (FeedCommentPO comment : commentList) {
            addComment(comment);
        }
        long cost = System.currentTimeMillis() - time;
        LOGGER.info("-+-+-+-+-+-+-+-+-+-+-+-+-+-+feed redis cache init end cost : {}-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+", cost);
        if(cost > 120000) {
            LOGGER.error("-+-+-+-+-+-+-+-+-+-+-+-+-+-+feed redis cache init cost too much-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        }
    }
}
