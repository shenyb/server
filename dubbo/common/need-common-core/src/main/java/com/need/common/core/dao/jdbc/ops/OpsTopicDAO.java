package com.need.common.core.dao.jdbc.ops;

import com.need.common.domain.po.api.ops.OpsTopicPO;

public interface OpsTopicDAO {
    int deleteByPrimaryKey(Integer topicId);

    int insert(OpsTopicPO record);

    int insertSelective(OpsTopicPO record);

    OpsTopicPO selectByPrimaryKey(Integer topicId);

    int updateByPrimaryKeySelective(OpsTopicPO record);

    int updateByPrimaryKeyWithBLOBs(OpsTopicPO record);

    int updateByPrimaryKey(OpsTopicPO record);
}