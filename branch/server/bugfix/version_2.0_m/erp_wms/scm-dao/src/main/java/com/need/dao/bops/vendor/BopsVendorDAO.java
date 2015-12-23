package com.need.dao.bops.vendor;

import java.util.List;

import com.need.domain.po.bops.vendor.BopsVendorPO;

public interface BopsVendorDAO {
    int deleteByPrimaryKey(Integer vendorId);

    int insert(BopsVendorPO record);

    int insertSelective(BopsVendorPO record);

    BopsVendorPO selectByPrimaryKey(Integer vendorId);

    int updateByPrimaryKeySelective(BopsVendorPO record);

    int updateByPrimaryKey(BopsVendorPO record);
    
    List<BopsVendorPO> selectVendorAll();
}