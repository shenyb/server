package com.need.domain.vo.purchase;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class PurchaseInVO implements Serializable{
	
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 2234493506156520521L;
	private String billId;  //单据ID
	private String billNo;  //单据编号
	private String userName;   //用户名
	private List<PurchaseItemInVO> items;  //采购单明细
	private String receiveStatus;
	private Long warehouseId;
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public List<PurchaseItemInVO> getItems() {
		return items;
	}
	public void setItems(List<PurchaseItemInVO> items) {
		this.items = items;
	}
	public String getReceiveStatus() {
		return receiveStatus;
	}
	public void setReceiveStatus(String receiveStatus) {
		this.receiveStatus = receiveStatus;
	}
	public Long getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	
	
}
