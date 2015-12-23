package com.need.jedis.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 
 * <p></p>
 * @author david.tan 2015年8月7日 下午5:18:20
 * @ClassName SerializableUtil
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: david.tan 2015年8月7日
 * @modify by reason:{方法名}:{原因}
 */
public class SerializableUtil {
    
    /**
     * 
     * @param obj
     * @return
     */
    static public byte[] writeObj (Object obj) {
        byte[] bytes = null;      
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj); 
            oos.flush();         
            bytes = bos.toByteArray ();      
                    
        } catch (Exception ex) {
//            ex.printStackTrace();   
        }finally{
            try {
                if(null != oos){
                    oos.close();
                }
                bos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return bytes;  
    }
    
    
    static public String writeToString (Object obj) {
        byte[] bytes = null;      
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj); 
            oos.flush();         
            bytes = bos.toByteArray ();      
                    
        } catch (Exception ex) {
//            ex.printStackTrace();   
        }finally{
            try {
                if(null != oos){
                    oos.close();
                }
                bos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return bytes.toString();  
    }
    
    /**
     * 
     * @param bytes
     * @return
     */
    static public Object readObj (byte[] bytes) {
        Object obj = null;      
        ByteArrayInputStream bis = null;        
        ObjectInputStream ois = null;
        try {        
            bis = new ByteArrayInputStream (bytes); 
            ois = new ObjectInputStream (bis);
            obj = ois.readObject();      
        } catch (Exception ex) {
//            ex.printStackTrace();   
        }finally{
            try {
                if(null != ois){
                    ois.close();
                }
                bis.close(); 
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return obj;    
    }

}
