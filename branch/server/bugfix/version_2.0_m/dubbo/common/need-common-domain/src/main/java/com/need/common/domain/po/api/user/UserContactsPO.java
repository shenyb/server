package com.need.common.domain.po.api.user;

import java.util.Date;

public class UserContactsPO extends UserContactsPOKey {
    private String relName;

    private Date createTime;

    public String getRelName() {
        return relName;
    }

    public void setRelName(String relName) {
        this.relName = relName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}