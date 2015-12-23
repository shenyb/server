package com.need.dao.bops.purchase;

import java.util.List;

import com.need.domain.po.bops.purchase.BopsPurchasePO;
import com.need.domain.po.bops.wms.ESynEdiMessage;
import com.need.domain.vo.purchase.BopsPurchaseVO;

public interface BopsPurchaseDAO {
    int deleteByPrimaryKey(Integer purchaseId);

    int insert(BopsPurchasePO bopsPurchasePO);

    int insertSelective(BopsPurchasePO bopsPurchasePO);

    BopsPurchasePO selectByPrimaryKey(Integer purchaseId);

    int updateByPrimaryKeySelective(BopsPurchasePO record);

    int updateByPrimaryKey(BopsPurchasePO record);
    
    List selectWareHouseType();
    
    String selectWareHouseTypeById(BopsPurchaseVO bopsPurchaseVO);
    
    String selectVendorName(BopsPurchaseVO bopsPurchaseVO);
    
    String selectWareHouseTypeByWarehouseId(Integer wareHouseId);
    
    int selectPurchaseCount(BopsPurchaseVO bopsPurchaseVO);
    
    List<BopsPurchaseVO> selectPurchaseList(BopsPurchaseVO bopsPurchaseVO);
    
    int updatePurchase(BopsPurchasePO bopsPurchasePO);
    
    BopsPurchasePO selectDifferrent(BopsPurchaseVO bopsPurchaseVO);
    
    int insertbopsReceiveProduct(BopsPurchaseVO bopsPurchaseVO);
    
    Integer getWarehouseType(Integer id);
    
    String selectProviderName(Integer id);
    
    List selectProvider();
    
    int insertESynEdiMessage(ESynEdiMessage eSynEdiMessage);
    
    int updatePurchaseFromWMS(BopsPurchasePO bopsPurchasePO);
    
    String selectWareHouseDredge(Integer warehouseId);
}