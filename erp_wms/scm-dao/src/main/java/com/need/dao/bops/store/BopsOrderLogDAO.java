package com.need.dao.bops.store;

import com.need.domain.po.bops.store.BopsOrderLogPO;

public interface BopsOrderLogDAO {
    int deleteByPrimaryKey(Long id);

    int insert(BopsOrderLogPO record);

    int insertSelective(BopsOrderLogPO record);

    BopsOrderLogPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BopsOrderLogPO record);

    int updateByPrimaryKey(BopsOrderLogPO record);
}