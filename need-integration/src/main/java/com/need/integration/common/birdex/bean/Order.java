/**
 * @ProjectName: need-integration
 * @Copyright: 2015 by Beijin Weplanter Technology co.,ltd.
 * @address: http://www.weplanter.com
 * @Description: 本内容仅限于稻田(北京)科技有限公司内部使用，禁止转发.
 * @author LV
 * @date: 2015年10月23日 下午5:35:10
 * @Title: Order.java
 * @Package com.need.integration.common.birdex.bean
 * @Description: TODO
 */
package com.need.integration.common.birdex.bean;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * </p>
 * 
 * @author LV 2015年10月23日 下午5:35:10
 * @ClassName Order
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LV 2015年10月23日
 * @modify by reason:{方法名}:{原因}
 */
public class Order implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	
	private String appKey;
	private String eventTime;
	private String occurTime;
	private List<TradeOrder> tradeOrders;

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

	public List<TradeOrder> getTradeOrders() {
		return tradeOrders;
	}

	public void setTradeOrders(List<TradeOrder> tradeOrders) {
		this.tradeOrders = tradeOrders;
	}

}
