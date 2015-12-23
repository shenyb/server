package com.need.common.core.dao.jdbc.feed;

import com.need.common.domain.po.api.feed.FeedLabelPO;

import java.util.Date;
import java.util.List;

public interface FeedLabelDAO {
    int deleteByPrimaryKey(String feedLabelId);

    int insert(FeedLabelPO record);

    int insertSelective(FeedLabelPO record);

    FeedLabelPO selectByPrimaryKey(String feedLabelId);

    int updateByPrimaryKeySelective(FeedLabelPO record);

    int updateByPrimaryKey(FeedLabelPO record);
    
    List<FeedLabelPO> queryByTime(Date time);

    List<FeedLabelPO> queryByFeedId(String feedId);
}