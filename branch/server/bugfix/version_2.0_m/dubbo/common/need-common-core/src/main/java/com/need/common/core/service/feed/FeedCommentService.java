/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.common.core.service.feed;

import com.need.common.domain.pub.Message;

/**
 *
 * @author 庆凯
 */
public interface FeedCommentService {

    public Message addFeedComment(String userId, String feedId, String commenttContent, String replyId);

    public Message delFeedComment(String userId, String feedId, String feedCommentId);

    public Message listFeedComment(String userId, String feedId, String feedCommentId, String refreshType, int pageSize);
    
}
