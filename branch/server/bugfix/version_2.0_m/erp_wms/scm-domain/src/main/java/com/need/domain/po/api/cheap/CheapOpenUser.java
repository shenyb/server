package com.need.domain.po.api.cheap;

import java.io.Serializable;
import java.util.Date;

public class CheapOpenUser extends CheapOpenUserKey implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -7057956871668009231L;

	private String cheapNo;

    private String profilePicKey;

    private Date createTime;

    private String traded;

    private String openId;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTraded() {
        return traded;
    }

    public void setTraded(String traded) {
        this.traded = traded;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}