package com.need.integration.service.vo;

public class TradePushParamVO {
	private String orderNo;
	private String charge;
	private String goodsValue;
	private String consignee;
	private String consigneeProvince;
	private String consigneeCity;
	private String consigneeZone;
	private String consigneeAddress;
	private String consigneeTelephone;
	private String idType;
	private String idNumber;
	private String modifyMark;
	private String goodsNo;
	private String shelfGoodsName;
	private String quantity;
	private String priceTotal;
	private String paymentNo;
	private String paymentName;
	private String paymentCode;
	// 国捡和海关计量单位
	public String unitCiq;
	public String unitCus;

	// 原产国编码
	private String originCountryCiq;
	private String originCountryCus;
	// 批次
	private String batchNo;
	
	private String payPrice;
	
	private String price;//单价
	private String weight;//整个包裹的重量
	private String netWeight;//毛重
	
	private String optType;
	
	

	public String getOptType() {
		return optType;
	}

	public void setOptType(String optType) {
		this.optType = optType;
	}

	public String getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(String netWeight) {
		this.netWeight = netWeight;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(String payPrice) {
		this.payPrice = payPrice;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getUnitCiq() {
		return unitCiq;
	}

	public void setUnitCiq(String unitCiq) {
		this.unitCiq = unitCiq;
	}

	public String getUnitCus() {
		return unitCus;
	}

	public void setUnitCus(String unitCus) {
		this.unitCus = unitCus;
	}

	public String getOriginCountryCiq() {
		return originCountryCiq;
	}

	public void setOriginCountryCiq(String originCountryCiq) {
		this.originCountryCiq = originCountryCiq;
	}

	public String getOriginCountryCus() {
		return originCountryCus;
	}

	public void setOriginCountryCus(String originCountryCus) {
		this.originCountryCus = originCountryCus;
	}

	public String getPaymentName() {
		return paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public String getGoodsValue() {
		return goodsValue;
	}

	public void setGoodsValue(String goodsValue) {
		this.goodsValue = goodsValue;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getConsigneeProvince() {
		return consigneeProvince;
	}

	public void setConsigneeProvince(String consigneeProvince) {
		this.consigneeProvince = consigneeProvince;
	}

	public String getConsigneeCity() {
		return consigneeCity;
	}

	public void setConsigneeCity(String consigneeCity) {
		this.consigneeCity = consigneeCity;
	}

	public String getConsigneeZone() {
		return consigneeZone;
	}

	public void setConsigneeZone(String consigneeZone) {
		this.consigneeZone = consigneeZone;
	}

	public String getConsigneeAddress() {
		return consigneeAddress;
	}

	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

	public String getConsigneeTelephone() {
		return consigneeTelephone;
	}

	public void setConsigneeTelephone(String consigneeTelephone) {
		this.consigneeTelephone = consigneeTelephone;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getModifyMark() {
		return modifyMark;
	}

	public void setModifyMark(String modifyMark) {
		this.modifyMark = modifyMark;
	}

	public String getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	public String getShelfGoodsName() {
		return shelfGoodsName;
	}

	public void setShelfGoodsName(String shelfGoodsName) {
		this.shelfGoodsName = shelfGoodsName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPriceTotal() {
		return priceTotal;
	}

	public void setPriceTotal(String priceTotal) {
		this.priceTotal = priceTotal;
	}

	public String getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}

	@Override
	public String toString() {
		return "TradePushParamVO [orderNo=" + orderNo + ", charge=" + charge + ", goodsValue=" + goodsValue
				+ ", consignee=" + consignee + ", consigneeProvince=" + consigneeProvince + ", consigneeCity="
				+ consigneeCity + ", consigneeZone=" + consigneeZone + ", consigneeAddress=" + consigneeAddress
				+ ", consigneeTelephone=" + consigneeTelephone + ", idType=" + idType + ", idNumber=" + idNumber
				+ ", modifyMark=" + modifyMark + ", goodsNo=" + goodsNo + ", shelfGoodsName=" + shelfGoodsName
				+ ", quantity=" + quantity + ", priceTotal=" + priceTotal + ", paymentNo=" + paymentNo
				+ ", paymentName=" + paymentName + ", paymentCode=" + paymentCode + ", unitCiq=" + unitCiq
				+ ", unitCus=" + unitCus + ", originCountryCiq=" + originCountryCiq + ", originCountryCus="
				+ originCountryCus + ", batchNo=" + batchNo + ", payPrice=" + payPrice + ", price=" + price
				+ ", weight=" + weight + ", netWeight=" + netWeight + ", optType=" + optType + "]";
	}

}
