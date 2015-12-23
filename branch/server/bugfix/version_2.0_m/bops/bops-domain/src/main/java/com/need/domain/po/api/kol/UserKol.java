package com.need.domain.po.api.kol;

import java.io.Serializable;
import java.util.Date;

public class UserKol  implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 8956422719448215478L;

	private String kolId;

    private String kolCategories;

    private String certification;

    private Date createTime;
    
    private String kolBrief;

    public String getKolId() {
        return kolId;
    }

    public void setKolId(String kolId) {
        this.kolId = kolId;
    }

    public String getKolCategories() {
        return kolCategories;
    }

    public void setKolCategories(String kolCategories) {
        this.kolCategories = kolCategories;
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
		return "UserKolPO [kolId=" + kolId + ", kolCategories=" + kolCategories + ", certification=" + certification
				+ ", createTime=" + createTime + ", kolBrief=" + kolBrief + "]";
	}
}