package com.need.common.domain.po.api.cheap;

public class CheapOpenUserPOKey {
    
    private Integer cheapOpenId;
    private String mobile;
    
    public CheapOpenUserPOKey(Integer cheapOpenId, String mobile) {
        this.cheapOpenId = cheapOpenId;
        this.mobile = mobile;
    }

    public Integer getCheapOpenId() {
        return cheapOpenId;
    }

    public void setCheapOpenId(Integer cheapOpenId) {
        this.cheapOpenId = cheapOpenId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "CheapOpenUserPOKey{" + "cheapOpenId=" + cheapOpenId + ", mobile=" + mobile + '}';
    }
}