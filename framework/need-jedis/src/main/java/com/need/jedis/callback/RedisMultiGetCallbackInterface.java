package com.need.jedis.callback;

import java.util.List;
import java.util.Map;

public interface RedisMultiGetCallbackInterface<V> {
   public Map<Object[],V> getMultiFromDB(List<Object[]> dbKeysList);
}
