package com.need.marketing.dao.jdbc.api.coupon.po;

public class CouponOpsListPO {
    private Integer couponId;

    private String couponTemplateId;

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getCouponTemplateId() {
        return couponTemplateId;
    }

    public void setCouponTemplateId(String couponTemplateId) {
        this.couponTemplateId = couponTemplateId;
    }
}