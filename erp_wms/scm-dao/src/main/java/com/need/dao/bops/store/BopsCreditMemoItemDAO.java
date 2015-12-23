package com.need.dao.bops.store;

import com.need.domain.po.bops.store.BopsCreditMemoItemPO;

public interface BopsCreditMemoItemDAO {
    int deleteByPrimaryKey(Long id);

    int insert(BopsCreditMemoItemPO record);

    int insertSelective(BopsCreditMemoItemPO record);

    BopsCreditMemoItemPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BopsCreditMemoItemPO record);

    int updateByPrimaryKey(BopsCreditMemoItemPO record);
}