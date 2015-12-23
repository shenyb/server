package com.need.jedis.util;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.need.jedis.config.Node;

/**
 * 
 * <p></p>
 * @author david.tan 2015年8月7日 下午5:18:13
 * @ClassName BundleUtil
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: david.tan 2015年8月7日
 * @modify by reason:{方法名}:{原因}
 */
public class BundleUtil {
    public static final ResourceBundle bundle = ResourceBundle.getBundle("config");
    public static List<Node> nodes = new ArrayList<Node>();
    public static List<String> sentinels = new ArrayList<String>();
//    static {
//        try {
//            String sentinelConfig = bundle.getString("sentinel_nodes");
//            String[]  sentinelNodes= sentinelConfig.split(",");
//            for(String sentinel : sentinelNodes){
//                sentinels.add(sentinel);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    
    public static final List<Node> getNodes(){
        String nodesStr = bundle.getString("nodes");
        String[] tmps = nodesStr.split(",");
        for(String s : tmps){
            String[] ipPort = s.split(":");
            Node node = new Node();
            node.setIp(ipPort[0]);
            String[] portPwd = ipPort[1].split("&");
            node.setPort(portPwd[0]);
            if(portPwd.length==2){
               node.setPassword(portPwd[1]);
            }            
            nodes.add(node);
        }
        return nodes;
    }
    
    public static final List<String> getSentinels(){   	
    	String nodesStr = bundle.getString("sentinel_nodes");
        String[] tmps = nodesStr.split(",");
        for(String s : tmps){
        	sentinels.add(s);   
        }    	
        return sentinels;
    }
    
    public static String getValue(String name){
        return bundle.getString(name);
    }
    
    public static String getMasterName(){
        return bundle.getString("master_name");
    }
    
    public static String getMasterPwd(){
        return bundle.getString("master_pwd");
    }
    
    public static String getConnTimeOut(){
        return bundle.getString("connection_timeout");
    }
}
