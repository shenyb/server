package com.need.jdbc.datasource;


/**
 * 
 * @author david.tan
 *
 */
public class RoutingDataSourceCommon {
    
    public enum DBTypeEnum {
        write, read;
    }
    
    private static ThreadLocal<DBTypeEnum> types = new ThreadLocal<DBTypeEnum>();

    public static void markWrite() {
        types.set(DBTypeEnum.write);
    }
    
    public static void markRead() {
        types.set(DBTypeEnum.read);
    }
    
    public static void reset() {
        types.set(null);
    }
    
    public static boolean isChoiceNone() {
        return null == types.get(); 
    }
    
    public static boolean isChoiceWrite() {
        return DBTypeEnum.write == types.get();
    }
    
    public static boolean isChoiceRead() {
        return DBTypeEnum.read == types.get();
    }

}
