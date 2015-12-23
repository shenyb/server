package com.need.common.domain.po.api.coupon;

public class CouponExchangeUserPOKey {
    
    private Integer couponExchangeId;
    private String userId;

    public Integer getCouponExchangeId() {
        return couponExchangeId;
    }

    public void setCouponExchangeId(Integer couponExchangeId) {
        this.couponExchangeId = couponExchangeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CouponExchangeUserPOKey{" + "couponExchangeId=" + couponExchangeId + ", userId=" + userId + '}';
    }
}