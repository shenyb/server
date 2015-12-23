package com.need.common.core.dao.jdbc.trade;

import com.need.common.domain.po.api.trade.TradeAddressPO;
import com.need.common.domain.vo.trade.AddressParamVO;
import com.need.common.domain.vo.trade.AddressResultVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TradeAddressDAO {
    int deleteByPrimaryKey(String addressId);
    
    List<AddressResultVO> queryAddressList(String userId);
    
    List<AddressResultVO> queryAddressList_V1_3(String userId);

    int insertNewAddress(AddressParamVO addressParamVO);
    
    int insertNewAddress_V1_3(AddressParamVO addressParamVO);
    
    int CountUserAddress(String userId);

    int insertSelective(TradeAddressPO record);

    TradeAddressPO selectByPrimaryKey(String addressId);

    int updateByPrimaryKeySelective(TradeAddressPO record);

    int updateByPrimaryKey(TradeAddressPO record);

	void removeAddressList(String addressList);
	
	int deleteByAddressId(String addressId);
	
    void updateByAddressId(AddressParamVO addressParamVO);
    
    int updateByAddressId_V1_3(AddressParamVO addressParamVO);
    
	void updateDefaultByAddressId(@Param("addressId")String addressId ,@Param("isDefault")String isDefault);
	

}