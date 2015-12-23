package com.need.integration.common.birdex.bean.orderResult;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrderResultVO implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 6110037673487141224L;
	/**
	 * 笨鸟唯一标识，由笨鸟提供
	 */
	private String appKey;
	/**
	 * 发送报文的系统时间
	 * UTC yyyy-MM-dd HH:mm:ss
	 */
	private Date eventTime;
	/**
	 * 业务发生时间
	 */
	private Date occurTime;
	/**
	 * 出库物流订单号，唯一
		当订单不拆分时回传logisticsId
		当订单进行拆分时，回传logisticsId-拆分总数量-当前包裹
	 */
	private String logisticsId;
	/**
	 * 处理状态码
		状态码明细
		0：处理成功
		42001：订单号不存在（更新接口）
		1004：请求数据格式不对
	 */
	private String code;
	/**
	 * 操作备注  笨鸟提供文字描述
	 */
	private String message;
	/**
	 * 订单问题商品列表
	 */
	private List<ItemVO> items;

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public Date getEventTime() {
		return eventTime;
	}

	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

	public Date getOccurTime() {
		return occurTime;
	}

	public void setOccurTime(Date occurTime) {
		this.occurTime = occurTime;
	}

	public String getLogisticsId() {
		return logisticsId;
	}

	public void setLogisticsId(String logisticsId) {
		this.logisticsId = logisticsId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<ItemVO> getItems() {
		return items;
	}

	public void setItems(List<ItemVO> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "OrderResultVO [appKey=" + appKey + ", eventTime=" + eventTime + ", occurTime=" + occurTime
				+ ", logisticsId=" + logisticsId + ", code=" + code + ", message=" + message + ", items=" + items + "]";
	} 
}
