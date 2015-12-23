package com.need.common.core.dao.jdbc.user;

import com.need.common.domain.po.api.user.UserRegisterInfoPO;

public interface UserRegisterInfoDAO {
    int deleteByPrimaryKey(String userId);

    int insert(UserRegisterInfoPO record);

    int insertSelective(UserRegisterInfoPO record);

    UserRegisterInfoPO selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(UserRegisterInfoPO record);

    int updateByPrimaryKey(UserRegisterInfoPO record);
}