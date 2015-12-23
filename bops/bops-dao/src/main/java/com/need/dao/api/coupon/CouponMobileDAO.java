package com.need.dao.api.coupon;

import java.util.List;
import java.util.Map;

import com.need.domain.po.api.coupon.CouponMobilePO;

/**
 * 
 * @author 庆凯 2015-9-9 2015-9-9 11:56:58
 * @ClassName CouponMobileDAO
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-9-9
 * @modify by reason:{方法名}:{原因}
 */

public interface CouponMobileDAO {
    
    int deleteByPrimaryKey(Integer couponMobileId);

    int insert(CouponMobilePO record);

    int insertSelective(CouponMobilePO record);

    CouponMobilePO selectByPrimaryKey(String couponMobileId);

    int updateByPrimaryKeySelective(CouponMobilePO record);

    int updateByPrimaryKey(CouponMobilePO record);
    
    List<CouponMobilePO> getCouponMobileListByMobile(String mobile);

    int getCountByParams(Map<String, Object> param);
}