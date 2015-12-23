package com.need.integration.dao.jdbc.api.user.po;

import java.io.Serializable;
import java.util.Date;


public class UserCertificationPO implements Serializable{
	
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -5396463649436702065L;

	private String id;

    private String realName;

    private String idCard;

    private String userId;

    private String certificationChannel;

    private String certificationSource;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCertificationChannel() {
        return certificationChannel;
    }

    public void setCertificationChannel(String certificationChannel) {
        this.certificationChannel = certificationChannel;
    }

    public String getCertificationSource() {
        return certificationSource;
    }

    public void setCertificationSource(String certificationSource) {
        this.certificationSource = certificationSource;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public String toString() {
		return "UserCertificationPO [id=" + id + ", realName=" + realName
				+ ", idCard=" + idCard + ", userId=" + userId
				+ ", certificationChannel=" + certificationChannel
				+ ", certificationSource=" + certificationSource
				+ ", createTime=" + createTime + "]";
	}

    
    
    
}