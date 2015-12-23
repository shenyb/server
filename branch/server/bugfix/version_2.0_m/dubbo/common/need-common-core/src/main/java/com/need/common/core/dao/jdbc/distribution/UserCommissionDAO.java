package com.need.common.core.dao.jdbc.distribution;

import com.need.common.domain.po.api.distribution.UserCommissionPO;

import java.util.List;

public interface UserCommissionDAO {
    int deleteByPrimaryKey(Long id);

    int insert(UserCommissionPO record);

    int insertSelective(UserCommissionPO record);

    UserCommissionPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserCommissionPO record);

    int updateByPrimaryKey(UserCommissionPO record);


	List<UserCommissionPO> queryGoodsByUserId(String userId);

	Integer getSumIncome(String userId);
}