package com.need.integration.common.birdex.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class PaymentItem implements Serializable {

	/**
	 * <p>
	 * </p>
	 * 
	 * @author LV 2015年10月23日 上午11:42:59
	 * @ClassName PaymentItem
	 * @Description http://openapi.birdex.cn/help/Api/POST/order/create
	 * @version V1.0
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: LV 2015年10月23日
	 * @modify by reason:{方法名}:{原因}
	 */
	private static final long serialVersionUID = 1L;

	private String paymentmName;
	private BigDecimal paymentmPrice;
	private String paymentmPriceUnit;
	
	public String getPaymentmName() {
		return paymentmName;
	}

	public void setPaymentmName(String paymentmName) {
		this.paymentmName = paymentmName;
	}

	public BigDecimal getPaymentmPrice() {
		return paymentmPrice;
	}

	public void setPaymentmPrice(BigDecimal paymentmPrice) {
		this.paymentmPrice = paymentmPrice;
	}

	public String getPaymentmPriceUnit() {
		return paymentmPriceUnit;
	}

	public void setPaymentmPriceUnit(String paymentmPriceUnit) {
		this.paymentmPriceUnit = paymentmPriceUnit;
	}

}
