package com.need.common.domain.po.api.user;

public class UserRegisterInfoPO {
    private String userId;

    private String registerChannel;

    private String registerType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRegisterChannel() {
        return registerChannel;
    }

    public void setRegisterChannel(String registerChannel) {
        this.registerChannel = registerChannel;
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }
}