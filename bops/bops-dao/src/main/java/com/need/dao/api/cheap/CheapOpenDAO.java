package com.need.dao.api.cheap;

import com.need.domain.po.api.cheap.CheapOpen;

public interface CheapOpenDAO {
    int deleteByPrimaryKey(Integer cheapOpenId);

    int insert(CheapOpen record);

    int insertSelective(CheapOpen record);

    CheapOpen selectByPrimaryKey(Integer cheapOpenId);

    int updateByPrimaryKeySelective(CheapOpen record);

    int updateByPrimaryKey(CheapOpen record);
}