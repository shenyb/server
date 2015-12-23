package com.need.jedis.util;

import java.io.Serializable;

/**
 * 
 * 类User.java的实现描述：TODO 类实现描述 
 * @author dell 2014年11月6日 下午9:51:48
 */
public class User implements Serializable{
    /**
     * 
     */
    String name;
    String code;
    
    private static final long serialVersionUID = 870371507403541064L;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
 
    
}
