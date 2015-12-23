package com.need.domain.po.bops.coupon;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author 庆凯 2015-9-9 2015-9-9 11:56:58
 * @ClassName BopsCouponAuditPO
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-9-9
 * @modify by reason:{方法名}:{原因}
 */

public class BopsCouponAuditPO implements Serializable {
    
    private static final long serialVersionUID = 4169167555349939095L;
    
    private Integer couponAuditId;
    @NotNull(message = "不能为空")
    private String couponTemplateId;
    private Integer auditUserId;
    @NotNull(message = "不能为空")
    private String couponStatus;
    private String description;
    private Date createTime;

    /**
     * @return the couponAuditId
     */
    public Integer getCouponAuditId() {
        return couponAuditId;
    }

    /**
     * @param couponAuditId the couponAuditId to set
     */
    public void setCouponAuditId(Integer couponAuditId) {
        this.couponAuditId = couponAuditId;
    }

    /**
     * @return the couponTemplateId
     */
    public String getCouponTemplateId() {
        return couponTemplateId;
    }

    /**
     * @param couponTemplateId the couponTemplateId to set
     */
    public void setCouponTemplateId(String couponTemplateId) {
        this.couponTemplateId = couponTemplateId;
    }

    /**
     * @return the auditUserId
     */
    public Integer getAuditUserId() {
        return auditUserId;
    }

    /**
     * @param auditUserId the auditUserId to set
     */
    public void setAuditUserId(Integer auditUserId) {
        this.auditUserId = auditUserId;
    }

    /**
     * @return the couponStatus
     */
    public String getCouponStatus() {
        return couponStatus;
    }

    /**
     * @param couponStatus the couponStatus to set
     */
    public void setCouponStatus(String couponStatus) {
        this.couponStatus = couponStatus;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

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
    
    @Override
    public String toString() {
        return "CouponUser [couponAuditId=" + couponAuditId + ", couponTemplateId=" + couponTemplateId + ", auditUserId=" + auditUserId + 
                ", description=" + description + ", couponStatus=" + couponStatus + ", createTime=" + createTime + "]";
    }
}