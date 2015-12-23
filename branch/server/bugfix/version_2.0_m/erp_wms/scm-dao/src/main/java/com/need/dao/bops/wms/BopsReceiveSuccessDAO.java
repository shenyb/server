package com.need.dao.bops.wms;

import com.need.domain.po.bops.wms.BopsReceiveSuccess;

public interface BopsReceiveSuccessDAO {
    int deleteByPrimaryKey(Integer receiveId);

    int insert(BopsReceiveSuccess record);

    int insertSelective(BopsReceiveSuccess record);

    BopsReceiveSuccess selectByPrimaryKey(Integer receiveId);
    
    BopsReceiveSuccess selectByMsgId(String msgId);

    int updateByPrimaryKeySelective(BopsReceiveSuccess record);

    int updateByPrimaryKey(BopsReceiveSuccess record);
}