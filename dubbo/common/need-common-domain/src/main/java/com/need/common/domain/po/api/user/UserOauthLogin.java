package com.need.common.domain.po.api.user;

import java.util.Date;

public class UserOauthLogin {
    private String userId;

    private String weiboPid;

    private String qqPid;

    private String weixinPid;

    private Date createTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWeiboPid() {
        return weiboPid;
    }

    public void setWeiboPid(String weiboPid) {
        this.weiboPid = weiboPid;
    }

    public String getQqPid() {
        return qqPid;
    }

    public void setQqPid(String qqPid) {
        this.qqPid = qqPid;
    }

    public String getWeixinPid() {
        return weixinPid;
    }

    public void setWeixinPid(String weixinPid) {
        this.weixinPid = weixinPid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}