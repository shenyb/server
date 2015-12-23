package com.need.dao.bops.store;

import com.need.domain.po.bops.store.BopsCreditMemoPO;

public interface BopsCreditMemoDAO {
    int deleteByPrimaryKey(Long id);

    int insert(BopsCreditMemoPO record);

    int insertSelective(BopsCreditMemoPO record);

    BopsCreditMemoPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BopsCreditMemoPO record);

    int updateByPrimaryKey(BopsCreditMemoPO record);
    
    int updateCount(BopsCreditMemoPO record);
}