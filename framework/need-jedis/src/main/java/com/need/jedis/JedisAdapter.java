package com.need.jedis;


/**
 * 
 * <p></p>
 * @author david.tan 2015年8月7日 下午5:18:58
 * @ClassName JedisAdapter
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: david.tan 2015年8月7日
 * @modify by reason:{方法名}:{原因}
 */
public class JedisAdapter {
	private ConfigHandler result;
	public JedisAdapter(ConfigHandler result) {
		this.result = result;
	}
	
	/**
	 * 获取jedis操作的管理者
	 * @return
	 */
	protected ConfigHandler getJedisCase(){
		return this.result.getDefaultHandler();
	}
	
	public ConfigHandler getConfigHandler(){
	    return this.result;
	}
}