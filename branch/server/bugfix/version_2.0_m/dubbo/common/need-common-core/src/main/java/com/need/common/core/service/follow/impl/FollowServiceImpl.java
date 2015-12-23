/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.common.core.service.follow.impl;

import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.dao.jdbc.follow.FollowUserDAO;
import com.need.common.core.service.feed.FeedListCacheService;
import com.need.common.core.service.follow.FollowCacheService;
import com.need.common.core.service.follow.FollowService;
import com.need.common.core.service.user.UserCacheService;
import com.need.common.core.utils.FollowStatusUtil;
import com.need.common.core.utils.PaginationUtil;
import com.need.common.domain.enums.FollowStatusEnum;
import com.need.common.domain.enums.RefreshTypeEnum;
import com.need.common.domain.po.api.follow.FollowUserPO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.follow.FollowUserVO;
import com.need.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 庆凯
 */
@Service
public class FollowServiceImpl implements FollowService {
    
    @Autowired
    private FollowUserDAO followUserDAO;
    
    @Autowired
    private FollowCacheService followCacheService;
    
    @Autowired
    private UserCacheService userCacheService;
    
    @Autowired
    private FeedListCacheService feedListCacheService;

    @Override
    public Message addFollow(String userId, String followerId) {
        if(StringUtil.isBlank(userId) || StringUtil.isBlank(followerId)) {
            return Message.error(BizReturnCode.USERID_NOT_EXIST);
        }
        if(followerId.equals(userId)) {
            return Message.error(BizReturnCode.FOLLOW_SELF);
        }
        String status;
        FollowUserPO followUserPO = new FollowUserPO();
        boolean needReverse = FollowStatusUtil.needReverse(userId, followerId);
        if(!needReverse) {
            followUserPO.setFollowId(followerId);
            followUserPO.setUserId(userId);
            status = FollowStatusEnum.FOLLOW.status;
        } else {
            followUserPO.setFollowId(userId);
            followUserPO.setUserId(followerId);
            status = FollowStatusEnum.FOLLOWED.status;
        }
        FollowUserPO follow = followUserDAO.selectByPrimaryKey(followUserPO);
        if(follow != null) {
            String oldStatus = follow.getFollowStatus();
            status = FollowStatusUtil.addStatus(oldStatus, needReverse);
        }
        followUserPO.setCreateTime(Calendar.getInstance().getTime());
        followUserPO.setFollowStatus(status);
        followUserDAO.insert(followUserPO);//插入到数据库
        followCacheService.addFollow(followUserPO);//插入到缓存中
        feedListCacheService.addFollowUserFeed(userId, followerId);//feed流中加入被关注人的feed
        return Message.success();
    }

    @Override
    public Message delFollow(String userId, String followerId) {
        if(StringUtil.isBlank(userId) || StringUtil.isBlank(followerId)) {
            return Message.error(BizReturnCode.USERID_NOT_EXIST);
        }
        if(followerId.equals(userId)) {
            return Message.error(BizReturnCode.FOLLOW_SELF);
        }
        FollowUserPO followUserPO = new FollowUserPO();
        boolean needReverse = FollowStatusUtil.needReverse(userId, followerId);
        if(!needReverse) {
            followUserPO.setFollowId(followerId);
            followUserPO.setUserId(userId);
        } else {
            followUserPO.setFollowId(userId);
            followUserPO.setUserId(followerId);
        }
        FollowUserPO follow = followUserDAO.selectByPrimaryKey(followUserPO);
        if(follow == null || FollowStatusEnum.NEITHER.status.equals(follow.getFollowStatus())) {
            return Message.success();
        }
        String oldStatus = follow.getFollowStatus();
        String status = FollowStatusUtil.delStatus(oldStatus, needReverse);
        followUserPO.setFollowStatus(status);
        followUserDAO.updateByPrimaryKey(followUserPO);
        followCacheService.changeState(followUserPO.getUserId(), followUserPO.getFollowId(), status);
        feedListCacheService.delFollowUserFeed(userId, followerId);//feed流中删除被取消关注人的feed
        return Message.success();
    }

    @Override
    public Message listFollow(String userId, String targetId, String followType, String refreshType, String followerId, Integer pageSize) {
        if (pageSize == null) {
            pageSize = 10;
        }
        if (StringUtil.isBlank(refreshType)) {
            refreshType = RefreshTypeEnum.DOWN.status;
        }
        Message message = Message.success();
        List<String> followList = followCacheService.listByUserId(targetId, followType);
        List<String> follwerIdList = PaginationUtil.getPaginationList(followList, followerId, pageSize, refreshType, message);
        List<FollowUserVO> userList = new ArrayList<FollowUserVO>();
        for(String follower : follwerIdList) {
            Map<String, String> userMap = userCacheService.getById(follower);
            if(userMap != null && !userMap.isEmpty()) {
                FollowUserVO userInfoVO = new FollowUserVO();
                String anotherId = userMap.get("userId");
                userInfoVO.setUserId(anotherId);
                userInfoVO.setNickName(userMap.get("nickName"));
                userInfoVO.setProfilePicKey(userMap.get("profilePicKey"));
                Boolean isFollow = false;
                if(!StringUtil.isBlank(userId)) {
                    isFollow = followCacheService.isFollow(userId, anotherId);
                }
                userInfoVO.setIsFollow(isFollow.toString().toUpperCase());
                userList.add(userInfoVO);
            }
        }
        message.addData("userList", userList);
        return message;
    }
    
}
