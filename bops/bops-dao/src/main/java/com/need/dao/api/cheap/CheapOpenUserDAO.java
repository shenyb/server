package com.need.dao.api.cheap;

import com.need.domain.po.api.cheap.CheapOpenUser;
import com.need.domain.po.api.cheap.CheapOpenUserKey;

public interface CheapOpenUserDAO {
    int deleteByPrimaryKey(CheapOpenUserKey key);

    int insert(CheapOpenUser record);

    int insertSelective(CheapOpenUser record);

    CheapOpenUser selectByPrimaryKey(CheapOpenUserKey key);

    int updateByPrimaryKeySelective(CheapOpenUser record);

    int updateByPrimaryKey(CheapOpenUser record);
}