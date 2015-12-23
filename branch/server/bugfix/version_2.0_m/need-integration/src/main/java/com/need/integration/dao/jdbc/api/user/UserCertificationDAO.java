package com.need.integration.dao.jdbc.api.user;

import com.need.integration.dao.jdbc.api.user.po.UserCertificationPO;

public interface UserCertificationDAO {
    int deleteById(String id);

    int insert(UserCertificationPO record);

    UserCertificationPO getById(String id);

    int updateById(UserCertificationPO record);
    
    UserCertificationPO getByIdCard(String idCard);
    
}