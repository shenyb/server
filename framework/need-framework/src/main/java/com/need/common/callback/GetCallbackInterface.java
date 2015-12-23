package com.need.common.callback;

/**
 * <p></p>
 * @author Rylan 2015年11月22日 下午5:24:48
 * @ClassName GetCallbackInterface
 * @Description 回调接口
 * @version V1.0   
 * @param <T>
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年11月22日
 * @modify by reason:{方法名}:{原因}
 */
public interface GetCallbackInterface<T> {
       
    public T executor(Object... parameters);
}
