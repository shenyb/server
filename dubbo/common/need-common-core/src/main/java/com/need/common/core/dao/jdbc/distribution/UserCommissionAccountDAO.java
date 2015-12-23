package com.need.common.core.dao.jdbc.distribution;

import com.need.common.domain.po.api.distribution.UserCommissionAccountPO;

public interface UserCommissionAccountDAO {

    long insert(UserCommissionAccountPO record);

    UserCommissionAccountPO selectByPrimaryKey(String accountId);

    int updateByPrimaryKey(UserCommissionAccountPO record);

    UserCommissionAccountPO getByUserId(String userId);

	Integer getAvailableByUserId(String userId);
}