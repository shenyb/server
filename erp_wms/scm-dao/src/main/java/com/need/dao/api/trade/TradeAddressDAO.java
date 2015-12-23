package com.need.dao.api.trade;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.need.domain.po.api.trade.TradeAddressPO;
import com.need.domain.vo.trade.address.AddressParamVO;
import com.need.domain.vo.trade.address.AddressResultVO;

public interface TradeAddressDAO {
    int deleteByPrimaryKey(String addressId);
    
    List<AddressResultVO> queryAddressList(String userId);

    int insert(TradeAddressPO record);
    
    void insertNewAddress(AddressParamVO addressParamVO);
    
    int CountUserAddress(String userId);

    int insertSelective(TradeAddressPO record);

    TradeAddressPO selectByPrimaryKey(String addressId);

    int updateByPrimaryKeySelective(TradeAddressPO record);

    int updateByPrimaryKey(TradeAddressPO record);

	void removeAddressList(String addressList);
	
	void deleteByAddressId(String addressId);

    void updateByAddressId(AddressParamVO addressParamVO);

	void updateDefaultByAddressId(@Param("addressId")String addressId ,@Param("isDefault")String isDefault);

	TradeAddressPO getDefaultAddress(String userId);
	
}