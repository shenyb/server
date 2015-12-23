package com.need.jedis.redisTemplate;


import java.io.Serializable;

public class RedisTimeOutValue<V> implements Serializable{
    private static final long serialVersionUID = 7568628120914036262L;
    // 存储的值
    private V value;
    // 过期时间毫秒数
    private long expire;

    public RedisTimeOutValue(V value, long expire) {
        this.value = value;
        this.expire = expire;
    }

   public V getValue() {
      return value;
   }

   public void setValue(V value) {
      this.value = value;
   }

   public long getExpire() {
      return expire;
   }

   public void setExpire(long expire) {
      this.expire = expire;
   }

   @Override
   public String toString() {
      return "RedisTimeOutValue [value=" + value + ", expire=" + expire + "]";
   }
}
