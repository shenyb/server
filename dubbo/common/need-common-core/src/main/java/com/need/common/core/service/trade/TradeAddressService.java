package com.need.common.core.service.trade;

import com.need.common.domain.vo.trade.AddressParamVO;
import com.need.common.domain.vo.trade.AddressResultVO;

import java.util.List;

public interface TradeAddressService {


	int deleteAddress(String addressId);

	public List<AddressResultVO> queryAddressList(String userId);
	
	public List<AddressResultVO> queryAddressList_V1_3(String userId);
	
	public String addAddress(AddressParamVO addressParamVO);
	
	public String addAddress_V1_3(AddressParamVO addressParamVO);
	
	public void updateAddress(AddressParamVO addressParamVO);
	
	public void updateAddress_V1_3(AddressParamVO addressParamVO);
	
	public int hasBlankofAddress(AddressParamVO addressParamVO);

	void updateDefaultAddress(String oldAddressId, String addressId, String userId);
	

}
