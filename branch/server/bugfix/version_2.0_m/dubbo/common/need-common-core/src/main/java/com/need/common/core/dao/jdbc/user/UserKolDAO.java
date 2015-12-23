package com.need.common.core.dao.jdbc.user;

import com.need.common.domain.po.api.user.UserKolPO;

public interface UserKolDAO {
    int deleteByPrimaryKey(String kolId);

    int insert(UserKolPO record);

    int insertSelective(UserKolPO record);

    UserKolPO selectByPrimaryKey(String kolId);

    int updateByPrimaryKeySelective(UserKolPO record);

    int updateByPrimaryKey(UserKolPO record);
}