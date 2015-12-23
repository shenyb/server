package com.need.common.core.dao.jdbc.coupon;

import com.need.common.domain.po.api.coupon.CouponExchangeUserPO;
import com.need.common.domain.po.api.coupon.CouponExchangeUserPOKey;

public interface CouponExchangeUserDAO {
    int deleteByPrimaryKey(CouponExchangeUserPOKey key);

    int insert(CouponExchangeUserPO record);

    int insertSelective(CouponExchangeUserPO record);

    CouponExchangeUserPO selectByPrimaryKey(CouponExchangeUserPOKey key);

    int updateByPrimaryKeySelective(CouponExchangeUserPO record);

    int updateByPrimaryKey(CouponExchangeUserPO record);
}