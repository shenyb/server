package com.need.common.core.dao.jdbc.distribution;

import com.need.common.domain.po.api.distribution.UserCommissionAccountLogPO;

public interface UserCommissionAccountLogDAO {
    int deleteByPrimaryKey(Long id);

    int insert(UserCommissionAccountLogPO record);

    int insertSelective(UserCommissionAccountLogPO record);

    UserCommissionAccountLogPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserCommissionAccountLogPO record);

    int updateByPrimaryKey(UserCommissionAccountLogPO record);
}