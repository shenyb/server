package com.need.domain.po.api.cheap;

import java.io.Serializable;

public class CheapOpenUserKey implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 8474485194078575203L;

	private Integer cheapOpenId;

    private String mobile;

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
}