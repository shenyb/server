/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.common.core.service.feed;


import com.need.common.domain.po.api.feed.FeedCommentPO;
import com.need.common.domain.po.api.feed.FeedLabelPO;
import com.need.common.domain.po.api.feed.FeedLikePO;
import com.need.common.domain.po.api.feed.FeedUserPO;
import com.need.common.domain.vo.feed.FeedCommentVO;
import com.need.common.domain.vo.feed.FeedDetailVO;
import com.need.common.domain.vo.feed.FeedVO;
import com.need.common.domain.vo.feed.SimpleFeedVO;

import java.util.List;
import java.util.Map;


/**
 *
 * @author 庆凯
 */
public interface FeedCacheService {
    
    Map<String, String> add(FeedUserPO feed);

    void addLabel(FeedLabelPO feedLabelPO);
    
    void addLike(FeedLikePO feedLikePO);
    
    void addComment(FeedCommentPO feedCommentPO);
    
    void removeById(String feedId, String userId);
    
    void removeLikeById(String feedId, String userId);
    
    void removeCommentById(String feedId, String commentId);
    
    Map<String, String> getById(String feedId);
    
    Map<String, String> getCommentById(String commentId);
    
    List<String> getLikeUserIdsByFeedId(String feedId);
    
    List<String> getCommentIdsByFeedId(String feedId);
    
    List<String> getLastCommentIdsByFeedId(String feedId, int size);
    
    int getCommentCount(String feedId);
    
    int getLikeCount(String feedId);
    
    long getExpires(String feedId);

    FeedVO getFeedVO(String feedId, String userId);

    SimpleFeedVO getSimpleFeedVO(String feedId, String userId);

    FeedDetailVO getFeedDetailVO(String feedId, String userId);
    
    FeedCommentVO getFeedCommentVO(String feedCommentId);
    
    void init();
    
}
