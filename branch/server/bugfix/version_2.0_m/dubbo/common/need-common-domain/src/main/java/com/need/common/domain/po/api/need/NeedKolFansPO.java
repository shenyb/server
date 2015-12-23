package com.need.common.domain.po.api.need;

import java.io.Serializable;
import java.util.Date;

public class NeedKolFansPO implements Serializable {
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -7109863344407207431L;

	private Integer id;

    private String kolId;

    private String userId;

    private Date createTime;
    
    private String focusStatus;
    

    public String getFocusStatus() {
		return focusStatus;
	}

	public void setFocusStatus(String focusStatus) {
		this.focusStatus = focusStatus;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKolId() {
        return kolId;
    }

    public void setKolId(String kolId) {
        this.kolId = kolId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}