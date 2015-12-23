package com.need.task.dao.jdbc.api.distribution;

import com.need.task.dao.jdbc.api.distribution.po.UserCommissionAccountLogPO;
import org.apache.ibatis.annotations.Param;

public interface UserCommissionAccountLogDAO {
    int deleteByPrimaryKey(Long id);

    int insert(UserCommissionAccountLogPO record);

    int insertSelective(UserCommissionAccountLogPO record);

    UserCommissionAccountLogPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserCommissionAccountLogPO record);

    int updateByPrimaryKey(UserCommissionAccountLogPO record);

    UserCommissionAccountLogPO getByUserIdAndOperateStatusAndTradeNo(@Param("userId") String userId, @Param("operateStatus") String operateStatus,@Param("tradeNo") String tradeNo);
}