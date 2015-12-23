package com.need.common.core.service.trade.impl;

import com.need.biz.utils.BizCodeUtil;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.dao.jdbc.trade.TradeAddressDAO;
import com.need.common.core.service.trade.TradeAddressService;
import com.need.common.domain.po.api.trade.TradeAddressPO;
import com.need.common.domain.vo.trade.AddressParamVO;
import com.need.common.domain.vo.trade.AddressResultVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class TradeAddressServiceImpl implements TradeAddressService {

	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private TradeAddressDAO tradeAddressDAO;


	@Override
	@Transactional
	public int deleteAddress(String addressId) {
		// TODO Auto-generated method stub
		TradeAddressPO tradeAddressPO = tradeAddressDAO.selectByPrimaryKey(addressId);
		int addressCount = tradeAddressDAO.CountUserAddress(tradeAddressPO.getUserId());
		if(addressCount == 1){
			return tradeAddressDAO.deleteByAddressId(addressId);
		}
		else{//可以没有默认地址， TODO，经过和志强商量，暂时先不改，没有默认地址客户端会崩溃
			if(String.valueOf(Boolean.TRUE).toUpperCase().equals(tradeAddressPO.getIsDefault())){
				List<AddressResultVO> tradeAddressList = tradeAddressDAO.queryAddressList(tradeAddressPO.getUserId());
				for(AddressResultVO tradeAddress : tradeAddressList){
					
					if(!tradeAddressPO.getAddressId().equals(tradeAddress.getAddressId())){
						tradeAddressDAO.updateDefaultByAddressId(tradeAddress.getAddressId(), String.valueOf(Boolean.TRUE).toUpperCase());
						return tradeAddressDAO.deleteByAddressId(addressId);
//						break;
					}
				}
				return 0;
			}
			else{
				return tradeAddressDAO.deleteByAddressId(addressId);
			}
		}
	}

	/**
	 * @author xiehao 2015年8月19日 下午4:02:26
	 * @Method: getAddressList 
	 * @Description: TODO 获取用户的地址列表
	 * @param userId
	 * @return 
	 * @see com.need.api.service.trade.TradeAddressService#getAddressList(java.lang.String)
	 */
	@Override
	public List<AddressResultVO> queryAddressList(String userId) {
		// TODO Auto-generated method stub
		return tradeAddressDAO.queryAddressList(userId);
	}
	
	@Override
	public List<AddressResultVO> queryAddressList_V1_3(String userId) {
		// TODO Auto-generated method stub
		List<AddressResultVO> tradeAddress= tradeAddressDAO.queryAddressList_V1_3(userId);
		if(tradeAddress==null){		
			return null;
		}
//		//Addy liyongran 身份证和手机号 隐藏处理
//		for (AddressResultVO addressResultVO : tradeAddress) {			
//			addressResultVO.setTelephone(StringUtil.subMobileCentre(addressResultVO.getTelephone()));
//			addressResultVO.setCertificationCard(StringUtil.subCertificationCardCentre(addressResultVO.getCertificationCard()));		
//		}		
		
		return tradeAddress;
	}

	/**
	 * 添加地址
	 */
	@Override
	@Transactional
	public String addAddress(AddressParamVO addressParamVO) {
		// TODO Auto-generated method stub
		String addressId = BizCodeUtil.generateAddressId(addressParamVO.getUserId(), addressParamVO.getAddress());//调用工具类生成地址ID
		addressParamVO.setAddressId(addressId);
		String userId = addressParamVO.getUserId();
		if(tradeAddressDAO.CountUserAddress(userId) == 0)//用户首次添加地址，把其设置为默认地址
			addressParamVO.setIsDefault(String.valueOf(Boolean.TRUE).toUpperCase());
		else{//非首次添加地址，设置为非默认
			addressParamVO.setIsDefault(String.valueOf(Boolean.FALSE).toUpperCase());
		}
		int result = tradeAddressDAO.insertNewAddress(addressParamVO);
		if(result > 0){//插入数据库成功，则返回地址ID
			return addressId;
		}
		//插入数据库失败则返回FALSE
		return String.valueOf(Boolean.FALSE).toUpperCase();
	}
	
	@Override
	public int hasBlankofAddress(AddressParamVO addressParamVO){
		if(addressParamVO.getUserId()==null){
			return BizReturnCode.USERID_NOT_EXIST;	
		}
		if(addressParamVO.getLogisticsId()==null){
			return BizReturnCode.LOGISTICSID_NOT_NULL;	
		}
		if(!StringUtils.hasText(addressParamVO.getAddress())){
			return BizReturnCode.ADDRESS_NOT_NULL;
		}
		if(!StringUtils.hasText(addressParamVO.getLogisticsValue())){
			return BizReturnCode.LOGISTICS_VALUE_NOT_NULL;
		}
		if(!StringUtils.hasText(addressParamVO.getTelephone())){
			return BizReturnCode.TELEPHONE_NOT_NULL;
		}
		if(!StringUtils.hasText(addressParamVO.getCertificationCard())){
			return BizReturnCode.CERTIFICATIONCARD_NOT_NULL;
		}
		if(!StringUtils.hasText(addressParamVO.getCertificationPositiveKey())){
			return BizReturnCode.CERTIFICATIONPOSITIVEKEY_NOT_NULL;
		}
		if(!StringUtils.hasText(addressParamVO.getCertificationNegativeKey())){
			return BizReturnCode.CERTIFICATIONNEGATIVEKEY_NOT_NULL;
		}
		if(!StringUtils.hasText(addressParamVO.getReceiver())){
			return BizReturnCode.RECEIVER_NOT_NULL;
		}
//		if(!StringUtils.hasText(addressParamVO.getCertificationCard())){
//			return true;
//		}
		//0代表验证通过
		return 200;
	}
	
	@Override
	@Transactional
	public String addAddress_V1_3(AddressParamVO addressParamVO) {
		// TODO Auto-generated method stub
		String addressId = BizCodeUtil.generateAddressId(addressParamVO.getUserId(), addressParamVO.getAddress());//调用工具类生成地址ID
		addressParamVO.setAddressId(addressId);
		String userId = addressParamVO.getUserId();
		if(tradeAddressDAO.CountUserAddress(userId) == 0)//用户首次添加地址，把其设置为默认地址
			addressParamVO.setIsDefault(String.valueOf(Boolean.TRUE).toUpperCase());
		else{//非首次添加地址，设置为非默认
			addressParamVO.setIsDefault(String.valueOf(Boolean.FALSE).toUpperCase());
		}
		int result = tradeAddressDAO.insertNewAddress_V1_3(addressParamVO);
		if(result > 0){//插入数据库成功，则返回地址ID
			return addressId;
		}
		//插入数据库失败则返回FALSE
		return String.valueOf(Boolean.FALSE).toUpperCase();
	}

	@Override
	@Transactional
	public void updateAddress(AddressParamVO addressParamVO) {
		// TODO Auto-generated method stub
		tradeAddressDAO.updateByAddressId(addressParamVO);
	}
	
	@Override
	@Transactional
	public void updateAddress_V1_3(AddressParamVO addressParamVO) {
		// TODO Auto-generated method stub
		tradeAddressDAO.updateByAddressId_V1_3(addressParamVO);
	}

	/**
	 * @author xiehao 2015年8月19日 下午4:07:26
	 * @Method: updateDefaultAddress 
	 * @Description: TODO 设置默认地址
	 * @param oldAddressId
	 * @param addressId
	 * @param userId 
	 * @see com.need.api.service.trade.TradeAddressService#updateDefaultAddress(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public void updateDefaultAddress(String oldAddressId, String addressId, String userId) {
		// TODO Auto-generated method stub
		String isDefault = String.valueOf(Boolean.FALSE).toUpperCase();
		tradeAddressDAO.updateDefaultByAddressId(oldAddressId,isDefault);
		isDefault = String.valueOf(Boolean.TRUE).toUpperCase();
		tradeAddressDAO.updateDefaultByAddressId(addressId,isDefault);
	}

}
