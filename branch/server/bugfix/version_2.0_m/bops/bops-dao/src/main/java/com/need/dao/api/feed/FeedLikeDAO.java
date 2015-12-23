package com.need.dao.api.feed;

import com.need.domain.po.api.feed.FeedLikePO;
import com.need.domain.po.api.feed.FeedLikePOKey;
import java.util.Date;
import java.util.List;

public interface FeedLikeDAO {
    int deleteByPrimaryKey(FeedLikePOKey key);

    int insert(FeedLikePO record);

    int insertSelective(FeedLikePO record);

    FeedLikePO selectByPrimaryKey(FeedLikePOKey key);

    int updateByPrimaryKeySelective(FeedLikePO record);

    int updateByPrimaryKey(FeedLikePO record);

    int updateDelete(FeedLikePOKey key);
    
    List<FeedLikePO> queryByTime(Date time);

    List<FeedLikePO> queryByFeedId(String feedId);
}