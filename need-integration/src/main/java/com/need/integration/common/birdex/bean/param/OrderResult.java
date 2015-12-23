/**
 * @ProjectName: need-integration
 * @Copyright: 2015 by Beijin Weplanter Technology co.,ltd.
 * @address: http://www.weplanter.com
 * @Description: 本内容仅限于稻田(北京)科技有限公司内部使用，禁止转发.
 * @author LV
 * @date: 2015年10月23日 下午2:30:55
 * @Title: OrderResult.java
 * @Package com.need.integration.common.birdex.bean.param
 * @Description: TODO
 */
package com.need.integration.common.birdex.bean.param;

import java.io.Serializable;
import java.util.List;

import com.need.integration.common.birdex.bean.Item;

/**
 * <p></p>
 * @author LV 2015年10月23日 下午2:30:55
 * @ClassName OrderResult
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LV 2015年10月23日
 * @modify by reason:{方法名}:{原因}
 */
public class OrderResult implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 1L;
	
	private String appKey;
	private String eventTime;
	private String occurTime;
	private String logisticsId;
	private Integer code;
	private String message;
	private List<Item> items;
	
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getEventTime() {
		return eventTime;
	}
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	public String getOccurTime() {
		return occurTime;
	}
	public void setOccurTime(String occurTime) {
		this.occurTime = occurTime;
	}
	public String getLogisticsId() {
		return logisticsId;
	}
	public void setLogisticsId(String logisticsId) {
		this.logisticsId = logisticsId;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}

}
