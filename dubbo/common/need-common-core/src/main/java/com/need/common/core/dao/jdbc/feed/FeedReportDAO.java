package com.need.common.core.dao.jdbc.feed;

import com.need.common.domain.po.api.feed.FeedReportPO;

public interface FeedReportDAO {
    int deleteByPrimaryKey(String feedReportId);

    int insert(FeedReportPO record);

    int insertSelective(FeedReportPO record);

    FeedReportPO selectByPrimaryKey(String feedReportId);

    int updateByPrimaryKeySelective(FeedReportPO record);

    int updateByPrimaryKey(FeedReportPO record);
}