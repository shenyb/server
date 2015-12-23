package com.need.kafka.services;

/**
 * Created by LightHuangfu on 2015/11/21.
 */
public enum KafkaConstantEnum {

    GROUP_ID("group.id");

    private String str;

    KafkaConstantEnum(String s) {
        this.str = s;
    }

    public String getValue() {
        return str;
    }
    }
