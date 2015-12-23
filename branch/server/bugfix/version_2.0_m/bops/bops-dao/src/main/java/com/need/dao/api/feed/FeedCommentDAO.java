package com.need.dao.api.feed;

import com.need.domain.po.api.feed.FeedCommentPO;
import java.util.Date;
import java.util.List;

public interface FeedCommentDAO {
    int deleteByPrimaryKey(String feedCommentId);

    int insert(FeedCommentPO record);

    int insertSelective(FeedCommentPO record);

    FeedCommentPO selectByPrimaryKey(String feedCommentId);

    int updateByPrimaryKeySelective(FeedCommentPO record);

    int updateByPrimaryKey(FeedCommentPO record);

    int updateDelete(String feedCommentId);
    
    List<FeedCommentPO> queryByTime(Date time);

    List<FeedCommentPO> queryByFeedId(String feedId);
}