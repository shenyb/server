package com.need.marketing.dao.jdbc.cheap.po;

public class CheapOpenUserPOKey {
    
    private Integer cheapOpenId;
    private String mobile;
    private String profilePicKey;
    private String openId;
    private String cheapNo;
    
    
    
    
    
    
    public String getCheapNo() {
		return cheapNo;
	}

	public void setCheapNo(String cheapNo) {
		this.cheapNo = cheapNo;
	}

	public String getProfilePicKey() {
		return profilePicKey;
	}

	public void setProfilePicKey(String profilePicKey) {
		this.profilePicKey = profilePicKey;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

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