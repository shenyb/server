package com.need.dao.api.feed;

import com.need.domain.po.api.feed.FeedUserPO;
import com.need.domain.vo.feed.FeedPageVO;
import com.need.domain.vo.feed.FeedVO;
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

    int queryPageFeedCount(FeedPageVO feedPageVO);

    List<FeedVO> queryPageFeed(FeedPageVO feedPageVO);
}