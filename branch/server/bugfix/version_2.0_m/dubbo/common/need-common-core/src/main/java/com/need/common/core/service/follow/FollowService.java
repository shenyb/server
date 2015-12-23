/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.common.core.service.follow;

import com.need.common.domain.pub.Message;

/**
 *
 * @author 庆凯
 */
public interface FollowService {

    //userId关注followerId
    Message addFollow(String userId, String followerId);

    //userId取消关注followerId
    Message delFollow(String userId, String followerId);

    //列出user的关注列表
    Message listFollow(String userId, String targetId, String followType, String refreshType, String followerId, Integer pageSize);
    
}
