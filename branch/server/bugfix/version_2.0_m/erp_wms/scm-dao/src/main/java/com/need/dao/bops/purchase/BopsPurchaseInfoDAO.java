package com.need.dao.bops.purchase;

import java.util.List;

import com.need.domain.po.bops.purchase.BopsPurchaseInfoPO;
import com.need.domain.vo.purchase.BopsPurchaseVO;

public interface BopsPurchaseInfoDAO {
    int deleteByPrimaryKey(Integer purchaseInfoId);

    int insert(BopsPurchaseInfoPO record);

    int insertSelective(BopsPurchaseInfoPO record);

    BopsPurchaseInfoPO selectByPrimaryKey(Integer purchaseInfoId);

    int updateByPrimaryKeySelective(BopsPurchaseInfoPO record);

    int updateByPrimaryKey(BopsPurchaseInfoPO record);
    
    List<BopsPurchaseInfoPO> selectGoodsList(Integer purchaseId);
    
    int updatePurchaseInfoCount(BopsPurchaseInfoPO record);
    
    BopsPurchaseInfoPO selectPrice(BopsPurchaseInfoPO record);
    
    int updatePurchaseInfoCountFromWMS(BopsPurchaseInfoPO record);
}