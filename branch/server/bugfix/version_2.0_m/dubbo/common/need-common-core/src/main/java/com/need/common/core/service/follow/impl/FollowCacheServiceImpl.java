/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.common.core.service.follow.impl;

import com.need.common.core.dao.jdbc.follow.FollowUserDAO;
import com.need.common.core.service.follow.FollowCacheService;
import com.need.common.core.utils.FollowStatusUtil;
import com.need.common.domain.enums.FollowStatusEnum;
import com.need.common.domain.po.api.follow.FollowUserPO;
import com.need.jedis.JedisSentinelClient;
import com.need.jedis.constants.RedisKeyConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author YAN
 */
@Service
public class FollowCacheServiceImpl implements FollowCacheService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(FollowCacheServiceImpl.class);

    @Autowired
    private FollowUserDAO followUserDAO;

    @Override
    public void addFollow(FollowUserPO follow) {
        String status = follow.getFollowStatus();
        String userId = follow.getUserId();
        String followId = follow.getFollowId();
        JedisSentinelClient.hset(RedisKeyConstant.FOLLOW_OF_USER.concat(userId), followId, status);
        status = FollowStatusUtil.reverseStatus(status);
        JedisSentinelClient.hset(RedisKeyConstant.FOLLOW_OF_USER.concat(followId), userId, status);
    }

    @Override
    public String getStatus(String userId, String followId) {
        String result = JedisSentinelClient.hget(RedisKeyConstant.FOLLOW_OF_USER.concat(userId), followId);
        return result;
    }

    @Override
    public boolean isFollow(String userId, String followerId) {
        String status = getStatus(userId, followerId);
        return FollowStatusEnum.FOLLOW.status.equals(status) || FollowStatusEnum.BOTH.status.equals(status);
    }

    @Override
    public boolean isFollowed(String userId, String followerId) {
        String status = getStatus(userId, followerId);
        return FollowStatusEnum.FOLLOWED.status.equals(status) || FollowStatusEnum.BOTH.status.equals(status);
    }

    @Override
    public void changeState(String userId, String followId, String status) {
        JedisSentinelClient.hset(RedisKeyConstant.FOLLOW_OF_USER.concat(userId), followId, status);
        status = FollowStatusUtil.reverseStatus(status);
        JedisSentinelClient.hset(RedisKeyConstant.FOLLOW_OF_USER.concat(followId), userId, status);
    }

    @Override
    public int getFollowedCount(String userId) {
        int followedCount = 0;
        for (Map.Entry<String, String> entry : listFollowByUserId(userId).entrySet()) {
            String status = entry.getValue();
            if (FollowStatusEnum.FOLLOWED.status.equals(status) || FollowStatusEnum.BOTH.status.equals(status)) {
                followedCount++;
            }
        }
        return followedCount;
    }

    @Override
    public int getFollowCount(String userId) {
        int followedCount = 0;
        for (Map.Entry<String, String> entry : listFollowByUserId(userId).entrySet()) {
            String status = entry.getValue();
            if (FollowStatusEnum.FOLLOW.status.equals(status) || FollowStatusEnum.BOTH.status.equals(status)) {
                followedCount++;
            }
        }
        return followedCount;
    }

    private Map<String, String> listFollowByUserId(String userId) {
        return JedisSentinelClient.hgetAll(RedisKeyConstant.FOLLOW_OF_USER.concat(userId));
    }

    @Override
    public List<String> listByUserId(String userId, String followType) {
        if(followType.equals("FANS")) {
            followType = "FOLLOWED";
        }
        List<String> result = new ArrayList<String>();
        for (Map.Entry<String, String> entry : listFollowByUserId(userId).entrySet()) {
            String status = entry.getValue();
            if (followType.equals(status) || FollowStatusEnum.BOTH.status.equals(status)) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    @Override
    public void init() {
        long time = System.currentTimeMillis();
        LOGGER.info("-+-+-+-+-+-+-+-+-+-+-+-+-+-+follow redis cache init start-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        for (String key : JedisSentinelClient.keys(RedisKeyConstant.FOLLOW_OF_USER.concat("*"))) {
            JedisSentinelClient.del(key);
        }
        for (FollowUserPO follow : followUserDAO.queryAll()) {
            String userId = follow.getUserId();
            String followId = follow.getFollowId();
            String followStatus = follow.getFollowStatus();
            JedisSentinelClient.hset(RedisKeyConstant.FOLLOW_OF_USER.concat(userId), followId, followStatus);
            followStatus = FollowStatusUtil.reverseStatus(followStatus);
            JedisSentinelClient.hset(RedisKeyConstant.FOLLOW_OF_USER.concat(followId), userId, followStatus);
        }
        long cost = System.currentTimeMillis() - time;
        LOGGER.info("-+-+-+-+-+-+-+-+-+-+-+-+-+-+follow redis cache init end cost : {}-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+", cost);
        if(cost > 120000) {
            LOGGER.error("-+-+-+-+-+-+-+-+-+-+-+-+-+-+follow redis cache init cost too much-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        }
    }

}
