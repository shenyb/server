package com.need.common.core.dao.jdbc.follow;

import com.need.common.domain.po.api.follow.FollowUserPO;
import com.need.common.domain.po.api.follow.FollowUserPOKey;

import java.util.List;

public interface FollowUserDAO {
    int deleteByPrimaryKey(FollowUserPOKey key);

    int insert(FollowUserPO record);

    int insertSelective(FollowUserPO record);

    FollowUserPO selectByPrimaryKey(FollowUserPOKey key);

    int updateByPrimaryKeySelective(FollowUserPO record);

    int updateByPrimaryKey(FollowUserPO record);
    
    List<FollowUserPO> queryAll();
}