package com.need.integration.common.haimeiSdk.domain.out;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.need.integration.common.haimeiSdk.domain.base.PackageDetails;

public class Order {
	String iscsOrderNo;
	String weight;
	Long stockId;
	String outIds;
	Long needInvoice;
	String receiverZip;
	String requestShipDate;
	String receiverAddress;
	String orderPayTime;
	Long needCard;
	String receiverPhone;
	String outSid;
	String invoiceContent;
	String receiverCounty;
	String cardContent;
	String createDate;
	/**
	 * 总金额，应收金额，商品总价＋运费
	 */
	Double totalFee;
	String receiverCountry;
	/**
	 * 优惠金额
	 */
	Double discountFee;
	Long shopId; 
	
	/**
	 * 支付金额
	 */
	Double payment;
	
	
	Long status;
	String orderNo;
	String transporterId;
	String invoiceName;
	String receiverCity;
	String receiverMobile;
	/**
	 * 运费
	 */
	Double postFee;
	
	String receiverName;
	String buyerNick;
	String shipDate;
	String orderCreateTime;
	String receiverProvince;
	Long flag ;
	Date dateUpdated ; 
	String buyerMessage;
	String sellerMemo;
	/**
	 * 0
	 */
	String orderStatus;
	/**
	 * 1
	 */
	String transporterFlag;
	
	List<OrderDet> skus=new ArrayList<OrderDet>();
	@JsonProperty("packageDetails")
	List<PackageDetails> packageDetails ;
	  
	 
	public List<PackageDetails> getPackageDetails() {
		return packageDetails;
	}
	public void setPackageDetails(List<PackageDetails> packageDetails) {
		this.packageDetails = packageDetails;
	}
	String reason;
 
	public Long getFlag() {
		return flag;
	}
	public void setFlag(Long flag) {
		this.flag = flag;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getTransporterId() {
		return transporterId;
	}
	public void setTransporterId(String transporterId) {
		this.transporterId = transporterId;
	}
	public String getIscsOrderNo() {
		return iscsOrderNo;
	}
	public void setIscsOrderNo(String iscsOrderNo) {
		this.iscsOrderNo = iscsOrderNo;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public Long getNeedInvoice() {
		return needInvoice;
	}
	public void setNeedInvoice(Long needInvoice) {
		this.needInvoice = needInvoice;
	}
	public String getReceiverZip() {
		return receiverZip;
	}
	public void setReceiverZip(String receiverZip) {
		this.receiverZip = receiverZip;
	}
	public String getRequestShipDate() {
		return requestShipDate;
	}
	public void setRequestShipDate(String requestShipDate) {
		this.requestShipDate = requestShipDate;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public String getOrderPayTime() {
		return orderPayTime;
	}
	public void setOrderPayTime(String orderPayTime) {
		this.orderPayTime = orderPayTime;
	}
	public Long getNeedCard() {
		return needCard;
	}
	public void setNeedCard(Long needCard) {
		this.needCard = needCard;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	
	public String getOutSid() {
		return outSid;
	}
	public void setOutSid(String outSid) {
		this.outSid = outSid;
	}
	public String getInvoiceContent() {
		return invoiceContent;
	}
	public void setInvoiceContent(String invoiceContent) {
		this.invoiceContent = invoiceContent;
	}
	public String getReceiverCounty() {
		return receiverCounty;
	}
	public void setReceiverCounty(String receiverCounty) {
		this.receiverCounty = receiverCounty;
	}
	public String getCardContent() {
		return cardContent;
	}
	public void setCardContent(String cardContent) {
		this.cardContent = cardContent;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public Double getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}
	public String getReceiverCountry() {
		return receiverCountry;
	}
	public void setReceiverCountry(String receiverCountry) {
		this.receiverCountry = receiverCountry;
	}
	public Double getDiscountFee() {
		return discountFee;
	}
	public void setDiscountFee(Double discountFee) {
		this.discountFee = discountFee;
	}
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public Double getPayment() {
		return payment;
	}
	public void setPayment(Double payment) {
		this.payment = payment;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public String getInvoiceName() {
		return invoiceName;
	}
	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}
	public String getReceiverCity() {
		return receiverCity;
	}
	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}
	public String getReceiverMobile() {
		return receiverMobile;
	}
	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}
	public Double getPostFee() {
		return postFee;
	}
	public void setPostFee(Double postFee) {
		this.postFee = postFee;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getBuyerNick() {
		return buyerNick;
	}
	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}
	public String getShipDate() {
		return shipDate;
	}
	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}
	public String getOrderCreateTime() {
		return orderCreateTime;
	}
	public void setOrderCreateTime(String orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}
	public String getReceiverProvince() {
		return receiverProvince;
	}
	public void setReceiverProvince(String receiverProvince) {
		this.receiverProvince = receiverProvince;
	}
	public List<OrderDet> getSkus() {
		return skus;
	}
	public void setSkus(List<OrderDet> skus) {
		this.skus = skus;
	}
	public Long getStockId() {
		return stockId;
	}
	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}
	public Date getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	public String getOutIds() {
		return outIds;
	}
	public void setOutIds(String outIds) {
		this.outIds = outIds;
	}
	public String getBuyerMessage() {
		return buyerMessage;
	}
	public void setBuyerMessage(String buyerMessage) {
		this.buyerMessage = buyerMessage;
	}
	public String getSellerMemo() {
		return sellerMemo;
	}
	public void setSellerMemo(String sellerMemo) {
		this.sellerMemo = sellerMemo;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getTransporterFlag() {
		return transporterFlag;
	}
	public void setTransporterFlag(String transporterFlag) {
		this.transporterFlag = transporterFlag;
	}
	 
	
	
}
