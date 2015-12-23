/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.common.core.service.follow;

import com.need.common.domain.po.api.follow.FollowUserPO;

import java.util.List;

/**
 *
 * @author YAN
 */
public interface FollowCacheService {
    
    //userId关注followerId
    public void addFollow(FollowUserPO followUser);
    
    //修改userId和followerId的关注关系
    public void changeState(String userId, String followerId, String state);
    
    //获取userId和followerId的关注关系
    public String getStatus(String userId, String followerId);
    
    //获取userId的粉丝数量
    public int getFollowedCount(String userId);
    
    //获取userId的关注数量
    public int getFollowCount(String userId);
    
    //列出userId的所有关注(followType=="FOLLOW")被关注(followType=="FOLLOWED")用户id
    public List<String> listByUserId(String userId, String followType);
    
    //userId是否关注followerId
    public boolean isFollow(String userId, String followerId);
    
    //userId是否被followerId关注
    public boolean isFollowed(String userId, String followerId);
    
    //redis缓存初始化
    public void init();
}
