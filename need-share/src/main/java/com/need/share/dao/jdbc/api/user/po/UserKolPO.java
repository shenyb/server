package com.need.share.dao.jdbc.api.user.po;

import java.io.Serializable;
import java.util.Date;

public class UserKolPO implements Serializable{
	
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -3129205849249802236L;

	private String kolId;

    private String categoryId;

    private String certification;

    private Date createTime;
    
    private String kolBrief;
    
    
    public String getKolId() {
        return kolId;
    }

    public void setKolId(String kolId) {
        this.kolId = kolId;
    }



    public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getKolBrief() {
		return kolBrief;
	}

	public void setKolBrief(String kolBrief) {
		this.kolBrief = kolBrief;
	}

	@Override
	public String toString() {
		return "UserKolPO [kolId=" + kolId + ", categoryId=" + categoryId + ", certification=" + certification
				+ ", createTime=" + createTime + ", kolBrief=" + kolBrief + "]";
	}


	
    
}