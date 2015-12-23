package com.need.dao.bops.cheap;

import com.need.domain.po.bops.cheap.BopsCheapAuditPO;

public interface BopsCheapAuditDAO {
    int deleteByPrimaryKey(Integer cheapAuditId);

    int insert(BopsCheapAuditPO record);

    int insertSelective(BopsCheapAuditPO record);

    BopsCheapAuditPO selectByPrimaryKey(Integer cheapAuditId);

    int updateByPrimaryKeySelective(BopsCheapAuditPO record);

    int updateByPrimaryKey(BopsCheapAuditPO record);
}