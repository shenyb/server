package com.need.dao.api.feed;

import com.need.domain.po.api.feed.FeedReportPO;
import com.need.domain.vo.feed.FeedPageVO;
import com.need.domain.vo.feed.FeedReportVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FeedReportDAO {
    int deleteByPrimaryKey(String feedReportId);

    int insert(FeedReportPO record);

    int insertSelective(FeedReportPO record);

    FeedReportPO selectByPrimaryKey(String feedReportId);

    int updateByPrimaryKeySelective(FeedReportPO record);

    int updateByPrimaryKey(FeedReportPO record);

    int queryPageFeedCount(FeedPageVO feedPageVO);

    List<FeedReportVO> queryPageFeed(FeedPageVO feedPageVO);

    void updateStatus(@Param("feedReportId") String feedReportId, @Param("reportStatus") String reportStatus);

    int queryHandledPageFeedCount(FeedPageVO feedPageVO);

    List<FeedReportVO> queryHandledPageFeed(FeedPageVO feedPageVO);
}