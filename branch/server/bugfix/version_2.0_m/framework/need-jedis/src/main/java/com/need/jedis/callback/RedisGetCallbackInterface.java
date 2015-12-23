package com.need.jedis.callback;


public interface RedisGetCallbackInterface<V> {
   
    public V getFromDB(Object... parameters);
    
}
