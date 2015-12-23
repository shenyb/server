/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.need.common.domain.vo.coupon;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author 庆凯 2015-10-14 2015-10-14 18:52:40
 * @ClassName ShareCouponVO
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-10-14
 * @modify by reason:{方法名}:{原因}
 */
    public class ShareCouponVO implements Serializable {
        
    private static final long serialVersionUID = -4179546617634955284L;

    private List<String> couponRule;
    private String couponRecommend;
    private String couponShareTitle;

    /**
     * @return the couponRule
     */
    public List<String> getCouponRule() {
        return couponRule;
    }

    /**
     * @param couponRule the couponRule to set
     */
    public void setCouponRule(List<String> couponRule) {
        this.couponRule = couponRule;
    }

    /**
     * @return the couponRecommend
     */
    public String getCouponRecommend() {
        return couponRecommend;
    }

    /**
     * @param couponRecommend the couponRecommend to set
     */
    public void setCouponRecommend(String couponRecommend) {
        this.couponRecommend = couponRecommend;
    }

    /**
     * @return the couponShareTitle
     */
    public String getCouponShareTitle() {
        return couponShareTitle;
    }

    /**
     * @param couponShareTitle the couponShareTitle to set
     */
    public void setCouponShareTitle(String couponShareTitle) {
        this.couponShareTitle = couponShareTitle;
    }

    @Override
    public String toString() {
        return "ShareCouponVO{" + "couponRule=" + couponRule + ", couponRecommend=" + couponRecommend + ", couponShareTitle=" + couponShareTitle + '}';
    }
}
