package com.need.common.core.dao.jdbc.feed;

import com.need.common.domain.po.api.feed.FeedUserPO;

import java.util.Date;
import java.util.List;

public interface FeedUserDAO {
    int deleteByPrimaryKey(String feedId);

    int insert(FeedUserPO record);

    int insertSelective(FeedUserPO record);

    FeedUserPO selectByPrimaryKey(String feedId);

    int updateByPrimaryKeySelective(FeedUserPO record);

    int updateByPrimaryKey(FeedUserPO record);

    int updateDelete(String feedId);
    
    List<FeedUserPO> queryByTime(Date time);
}