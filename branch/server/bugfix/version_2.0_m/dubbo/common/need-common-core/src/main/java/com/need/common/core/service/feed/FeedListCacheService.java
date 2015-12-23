/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.common.core.service.feed;

import java.util.List;

/**
 *
 * @author 庆凯
 */
public interface FeedListCacheService {
    
    public List<String> getFeedIdList(String userId);
    
    public void addFeedId(String feedId);
    
    public void addFeedId(String feedId, Long time, String feedUserId);
    
    public void removeFeedId(String feedId);
    
    public void removeFeedId(String feedId, String feedUserId);

    public List<String> getUserFeedIds(String userId);

    public void addFollowUserFeed(String userId, String targetId);

    public void delFollowUserFeed(String userId, String targetId);
}
