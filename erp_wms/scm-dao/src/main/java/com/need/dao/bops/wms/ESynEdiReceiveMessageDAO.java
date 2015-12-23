package com.need.dao.bops.wms;

import java.util.List;

import com.need.domain.po.bops.wms.ESynEdiReceiveMessage;

public interface ESynEdiReceiveMessageDAO {
    int deleteByPrimaryKey(Long id);

    int insert(ESynEdiReceiveMessage record);

    int insertSelective(ESynEdiReceiveMessage record);

    ESynEdiReceiveMessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ESynEdiReceiveMessage record);

    int updateByPrimaryKeyWithBLOBs(ESynEdiReceiveMessage record);

    int updateByPrimaryKey(ESynEdiReceiveMessage record);
    List<ESynEdiReceiveMessage> queryListByStatus(Long status);
}