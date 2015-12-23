package com.need.service.bops.wms;

import com.need.domain.po.bops.wms.ESynEdiReceiveMessage;

public interface WmsToErpService {
	/**
	 * 把wms订单到erp 的数据存到wms_order_erp
	 * @author zhangmengbin
	 */
	public void createWmsOrderToErp(ESynEdiReceiveMessage message);
	/**
	 * 把wms拒收的订单同步到wms
	 * @param message
	 * @author zhangmengbin
	 * 
	 */
	public void createRejectionOrder(ESynEdiReceiveMessage message);
}
