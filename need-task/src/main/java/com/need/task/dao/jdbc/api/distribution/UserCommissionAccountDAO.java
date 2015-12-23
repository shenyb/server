package com.need.task.dao.jdbc.api.distribution;

import com.need.task.dao.jdbc.api.distribution.po.UserCommissionAccountPO;

public interface UserCommissionAccountDAO {
    int deleteByPrimaryKey(Long accountId);

    long insert(UserCommissionAccountPO record);

    UserCommissionAccountPO selectByPrimaryKey(String accountId);

    int updateByPrimaryKey(UserCommissionAccountPO record);

    UserCommissionAccountPO getByUserId(String userId);

	Integer getAvailableByUserId(String userId);
}