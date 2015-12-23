package com.need.common.core.dao.jdbc.user;

import com.need.common.domain.po.api.user.UserContactsPO;
import com.need.common.domain.po.api.user.UserContactsPOKey;
import com.need.common.domain.vo.user.ContactsBookVO;
import org.apache.ibatis.annotations.Param;

public interface UserContactsDAO {
    int deleteByPrimaryKey(UserContactsPOKey key);


    int insertSelective(UserContactsPO record);

    UserContactsPO selectByPrimaryKey(UserContactsPOKey key);

    int updateByPrimaryKeySelective(UserContactsPO record);

    int updateByPrimaryKey(UserContactsPO record);

	int insert(@Param("userId") String userId,@Param("contactUser") ContactsBookVO contactUser);
}