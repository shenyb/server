package com.need.domain.vo.trade;

public class GoodsSnNoVO {
	/**
	 * 商品编号
	 */
	private String goodsSnNo;
	/**
	 * 商品物料号
	 */
	private String materialNo;
	public String getGoodsSnNo() {
		return goodsSnNo;
	}
	public void setGoodsSnNo(String goodsSnNo) {
		this.goodsSnNo = goodsSnNo;
	}
	public String getMaterialNo() {
		return materialNo;
	}
	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}
	public GoodsSnNoVO(String goodsSnNo, String materialNo) {
		super();
		this.goodsSnNo = goodsSnNo;
		this.materialNo = materialNo;
	}
	public GoodsSnNoVO(){
		super();
	}
}
