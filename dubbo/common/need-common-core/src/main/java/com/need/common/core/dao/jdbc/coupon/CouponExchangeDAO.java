package com.need.common.core.dao.jdbc.coupon;

import com.need.common.domain.po.api.coupon.CouponExchangePO;

import java.util.List;

public interface CouponExchangeDAO {
    int deleteByPrimaryKey(Integer couponExchangeId);

    int insert(CouponExchangePO record);

    int insertSelective(CouponExchangePO record);

    CouponExchangePO selectByPrimaryKey(Integer couponExchangeId);

    int updateByPrimaryKeySelective(CouponExchangePO record);

    int updateByPrimaryKey(CouponExchangePO record);
    
    List<CouponExchangePO> queryByCouponExchangeCode(String couponExchangeCode);
}