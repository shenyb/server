package com.need.task.dao.jdbc.api.distribution;

import com.need.task.dao.jdbc.api.distribution.po.UserCommissionPO;

import java.util.List;
import java.util.Map;

public interface UserCommissionDAO {
	int deleteByPrimaryKey(Long id);

	int insert(UserCommissionPO record);

	int insertSelective(UserCommissionPO record);

	UserCommissionPO selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(UserCommissionPO record);

	int updateByPrimaryKey(UserCommissionPO record);

	List<UserCommissionPO> queryGoodsByUserId(String userId);

	Integer getSumIncome(String userId);

	List<UserCommissionPO> queryByWaitToInList();

	int updateBatchById(Map<String,Object> map);
}