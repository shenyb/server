package com.need.share.common.enums;

import java.util.Locale;

import com.need.share.constant.Constants;

/**
 * 
 * 类ShareTypeEnum.java的实现描述：分享类型枚举类 
 * @author sunwenjuan 2015年3月10日 下午3:25:47
 */
public enum ShareTypeEnum {

    GOODS;
    
    /**
     * 获取小写方式
     * for path
     * @return
     */
    public String getLowerValue(){
        return this.toString().toLowerCase(Locale.getDefault());
    }
    
    public String getSharePathPre(){
        switch (this) {
        case GOODS:
            return Constants.GOODS_PAGES_PATH;
        
        default:
            return "UNKONWN";
        }
    }
}
