package com.need.share.service.base;

import com.github.pagehelper.PageInfo;

import freemarker.template.SimpleHash;

public interface BaseService<T> {
    
 
    
    public SimpleHash makeContent(String id,String ... otherStr);
}
