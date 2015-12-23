package com.need.common.domain.po.api.cheap;

import java.io.Serializable;
import java.util.Date;

public class CheapOpenUserPO implements Serializable {
    private static final long serialVersionUID = 6980987547263762569L;
    
    private Integer cheapOpenId;
    private String mobile;
    private String cheapNo;
    private String profilePicKey;
    private Date createTime;
    private String traded;
    private String openId;

    /**
     * @return the cheapOpenId
     */
    public Integer getCheapOpenId() {
        return cheapOpenId;
    }

    /**
     * @param cheapOpenId the cheapOpenId to set
     */
    public void setCheapOpenId(Integer cheapOpenId) {
        this.cheapOpenId = cheapOpenId;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the cheapNo
     */
    public String getCheapNo() {
        return cheapNo;
    }

    /**
     * @param cheapNo the cheapNo to set
     */
    public void setCheapNo(String cheapNo) {
        this.cheapNo = cheapNo;
    }

    /**
     * @return the profilePicKey
     */
    public String getProfilePicKey() {
        return profilePicKey;
    }

    /**
     * @param profilePicKey the profilePicKey to set
     */
    public void setProfilePicKey(String profilePicKey) {
        this.profilePicKey = profilePicKey;
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

    /**
     * @return the traded
     */
    public String getTraded() {
        return traded;
    }

    /**
     * @param traded the traded to set
     */
    public void setTraded(String traded) {
        this.traded = traded;
    }

    /**
     * @return the openId
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * @param openId the openId to set
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public String toString() {
        return "CheapOpenUserPO{" + "cheapOpenId=" + cheapOpenId + ", mobile=" + mobile + ", cheapNo=" + cheapNo + 
                ", profilePicKey=" + profilePicKey + ", createTime=" + createTime + ", traded=" + traded + ", openId=" + openId + '}';
    }
}