package com.need.utils;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtils {
	

	/***
     * 将List对象序列化为JSON文本
     */
    public static <T> String toJSONString(List<T> list){
        JSONArray jsonArray = JSONArray.fromObject(list);
        
        return jsonArray.toString();
    }
   
    /***
     * 将对象序列化为JSON文本
     * @param object
     * @return
     */
    public static String toJSONString(Object object)
    {
        JSONArray jsonArray = JSONArray.fromObject(object);
        return jsonArray.toString();
    }

    public static String toJSONString2(Object object)
    {
        return  JSONObject.fromObject(object).toString();
    }
    /***
     * 将JSON对象数组序列化为JSON文本
     * @param jsonArray
     * @return
     */
    public static String toJSONString(JSONArray jsonArray)
    {
        return jsonArray.toString();
    }
    
    /***
     * 将JSON对象转换为传入类型的对象
     * @param <T>
     * @param jsonObject
     * @param beanClass
     * @return
     */
    @SuppressWarnings("unchecked")
	public static <T> T toBean(JSONObject jsonObject, Class<T> beanClass)
    {
        return (T) JSONObject.toBean(jsonObject, beanClass);
    }
    
    
    public static Object getDtoFromJsonObjStr(String jsonObjStr, Class clazz) {  
        return  JSONObject.toBean(JSONObject.fromObject(jsonObjStr), clazz);  
    }
    

}
