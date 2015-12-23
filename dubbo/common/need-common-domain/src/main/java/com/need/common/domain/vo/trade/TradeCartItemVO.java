package com.need.common.domain.vo.trade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TradeCartItemVO implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	private String warehouseType;
	private String warehouseName;
	private List<TradeCartGoodsItemVO> goodsList=new ArrayList<TradeCartGoodsItemVO>();
	public String getWarehouseType() {
		return warehouseType;
	}
	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public List<TradeCartGoodsItemVO> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<TradeCartGoodsItemVO> goodsList) {
		this.goodsList = goodsList;
	}
	
}
