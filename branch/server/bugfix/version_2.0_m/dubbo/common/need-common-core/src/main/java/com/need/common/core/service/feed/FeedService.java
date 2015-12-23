/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.common.core.service.feed;

import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.feed.FeedLabelVO;

import java.util.List;

/**
 *
 * @author 庆凯
 */
public interface FeedService {

    //发布feed
    Message addFeed(String userId, String feedContent, String feedPicKey, List<FeedLabelVO> feedLabels);

    //删除feed
    Message delFeed(String userId, String feedId);

    //feed流列表
    Message listFeed(String userId, String feedId, String refreshType, int pageSize);

    //用户发布的feed列表
    Message listUserFeed(String userId, String feedId, String refreshType, int pageSize);

    //feed点赞
    Message likeFeed(String userId, String feedId);

    //feed取消点赞
    Message dislikeFeed(String userId, String feedId);

    //feed详情
    Message getFeedDetail(String userId, String feedId);

    //feed举报
    Message reportFeed(String userId, String feedId, String content);
}
