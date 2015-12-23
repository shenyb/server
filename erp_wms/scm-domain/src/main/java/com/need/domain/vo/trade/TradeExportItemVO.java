package com.need.domain.vo.trade;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

/**
 * 订单明细导出
 * @author zhangmengbin
 *
 */
public class TradeExportItemVO {
	private String userTradeNo;
	private String goodsId;
	private String goodsCode;
	private String goodsName;
	private String goodsBarcode;//国际条形码
	private BigDecimal buyPrice;//need价
	private Integer buyCount;//销售数量
	private BigDecimal salesAmount;//销售金额
	private BigDecimal purchasePrice;//采购价
	private BigDecimal grossMargin; //毛利率
	private String oneCategoryName;
	private String twoCategoryName;
	private String threeCategoryName;
	private String purchasingManager;//采购经理
	private String purchasingSupervisor;//采购主管
	private String tradeStatus;
	private Date createTime;
	private String warehouseType;
	private Date payTime;
	private BigDecimal discount; //优惠（分SKU）Need价/商品总价*优惠
	private BigDecimal freight; //运费（分SKU）Need价/商品总价*运费
	private BigDecimal realpayPrice;///实际支付金额
	private String isBilling;//是否开票
	private String remark;
	private String goodsCategories;
	private double goodsTotalPrice;//交易的商品总和
	private double goodsDiscount; //交易的优惠
	private double transportFee;//交易的运费
	
	
	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public double getTransportFee() {
		return transportFee;
	}
	public void setTransportFee(double transportFee) {
		this.transportFee = transportFee;
	}
	public String getGoodsCategories() {
		return goodsCategories;
	}
	public void setGoodsCategories(String goodsCategories) {
		this.goodsCategories = goodsCategories;
	}
	
	public String getUserTradeNo() {
		return userTradeNo;
	}
	public void setUserTradeNo(String userTradeNo) {
		this.userTradeNo = userTradeNo;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsBarcode() {
		return goodsBarcode;
	}
	public void setGoodsBarcode(String goodsBarcode) {
		this.goodsBarcode = goodsBarcode;
	}

	public Integer getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}
	
	public BigDecimal getSalesAmount() {
		return salesAmount;
	}
	public void setSalesAmount(BigDecimal salesAmount) {
		this.salesAmount = salesAmount;
	}
	
	
	public BigDecimal getGrossMargin() {
		grossMargin = new BigDecimal((getBuyPrice().doubleValue()-getPurchasePrice().doubleValue())/getBuyPrice().doubleValue()*100);
		grossMargin =grossMargin.setScale(2, BigDecimal.ROUND_HALF_UP);
		return grossMargin;
	}
	public void setGrossMargin(BigDecimal grossMargin) {
		this.grossMargin = grossMargin;
	}
	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}
	public void setRealpayPrice(BigDecimal realpayPrice) {
		this.realpayPrice = realpayPrice;
	}
	public String getOneCategoryName() {
		return oneCategoryName;
	}
	public void setOneCategoryName(String oneCategoryName) {
		this.oneCategoryName = oneCategoryName;
	}
	public String getTwoCategoryName() {
		return twoCategoryName;
	}
	public void setTwoCategoryName(String twoCategoryName) {
		this.twoCategoryName = twoCategoryName;
	}
	public String getThreeCategoryName() {
		return threeCategoryName;
	}
	public void setThreeCategoryName(String threeCategoryName) {
		this.threeCategoryName = threeCategoryName;
	}
	public String getPurchasingManager() {
		return purchasingManager;
	}
	public void setPurchasingManager(String purchasingManager) {
		this.purchasingManager = purchasingManager;
	}
	public String getPurchasingSupervisor() {
		return purchasingSupervisor;
	}
	public void setPurchasingSupervisor(String purchasingSupervisor) {
		this.purchasingSupervisor = purchasingSupervisor;
	}
	public String getTradeStatus() {
		return tradeStatus;
	}
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getWarehouseType() {
		return warehouseType;
	}
	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	
	public String getIsBilling() {
		return isBilling;
	}
	public void setIsBilling(String isBilling) {
		this.isBilling = isBilling;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public double getGoodsTotalPrice() {
		return goodsTotalPrice;
	}
	public void setGoodsTotalPrice(double goodsTotalPrice) {
		this.goodsTotalPrice = goodsTotalPrice;
	}
	public double getGoodsDiscount() {
		return goodsDiscount;
	}
	public void setGoodsDiscount(double goodsDiscount) {
		this.goodsDiscount = goodsDiscount;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public BigDecimal getBuyPrice() {
		return buyPrice;
	}
	public BigDecimal getDiscount() {
		discount = new BigDecimal(getBuyPrice().doubleValue()/getGoodsTotalPrice()*getGoodsDiscount());
		discount = discount.setScale(2,   BigDecimal.ROUND_HALF_UP);
		return discount;
	}
	public BigDecimal getFreight() {
		freight = new BigDecimal(getBuyPrice().doubleValue()/getGoodsTotalPrice()*getTransportFee());
		return freight;
	}
	public BigDecimal getRealpayPrice() {
		realpayPrice = new BigDecimal((getBuyPrice().doubleValue()-getBuyPrice().doubleValue()/getGoodsTotalPrice()*getGoodsDiscount())+(getBuyPrice().doubleValue()/getGoodsTotalPrice()*getTransportFee()));
		realpayPrice = realpayPrice.setScale(2,   BigDecimal.ROUND_HALF_UP);
		return realpayPrice;
	}
	
}
