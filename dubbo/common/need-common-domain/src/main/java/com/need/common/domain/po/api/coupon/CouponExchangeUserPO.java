package com.need.common.domain.po.api.coupon;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author 庆凯
 */
public class CouponExchangeUserPO implements Serializable {

    private static final long serialVersionUID = 2352327927694508561L;
    
    private Date createTime;
    private Integer couponExchangeId;
    private String userId;

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the couponExchangeId
     */
    public Integer getCouponExchangeId() {
        return couponExchangeId;
    }

    /**
     * @param couponExchangeId the couponExchangeId to set
     */
    public void setCouponExchangeId(Integer couponExchangeId) {
        this.couponExchangeId = couponExchangeId;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CouponExchangeUserPO{" + "createTime=" + createTime + ", couponExchangeId=" + couponExchangeId + ", userId=" + userId + '}';
    }
}