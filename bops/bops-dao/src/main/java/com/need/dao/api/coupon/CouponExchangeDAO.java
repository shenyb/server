package com.need.dao.api.coupon;

import com.need.domain.po.api.coupon.CouponExchangePO;
import java.util.List;

public interface CouponExchangeDAO {
    int deleteByPrimaryKey(Integer couponExchangeId);

    int insert(CouponExchangePO record);

    int insertSelective(CouponExchangePO record);

    CouponExchangePO selectByPrimaryKey(Integer couponExchangeId);

    int updateByPrimaryKeySelective(CouponExchangePO record);

    int updateByPrimaryKey(CouponExchangePO record);
    
    List<CouponExchangePO> queryByCouponExchangeCode(String couponExchangeCode);

    int updateFrozenByTemplateId(String couponExchangeTemplateId);
    
    int updateFrozen(Integer couponExchangeId);

    List<CouponExchangePO> queryByCouponExchangeTemplateId(String couponExchangeTemplateId);
}