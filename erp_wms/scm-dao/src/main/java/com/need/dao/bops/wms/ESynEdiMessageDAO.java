package com.need.dao.bops.wms;

import com.need.domain.po.bops.wms.ESynEdiMessage;
import com.need.domain.po.bops.wms.ESynEdiReceiveMessage;

public interface ESynEdiMessageDAO {
    int deleteByPrimaryKey(Long id);

    int insert(ESynEdiMessage record);

    int insertSelective(ESynEdiMessage record);

    ESynEdiMessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ESynEdiMessage record);

    int updateByPrimaryKeyWithBLOBs(ESynEdiMessage record);

    int updateByPrimaryKey(ESynEdiMessage record);
    
    int updateStatus(long id);
    
    int updateMessage(ESynEdiReceiveMessage record);
    
    ESynEdiMessage selectPurchaseByBillid(String purchaseId);
    
}