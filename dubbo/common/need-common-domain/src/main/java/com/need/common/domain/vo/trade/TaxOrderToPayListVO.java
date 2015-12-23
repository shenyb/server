package com.need.common.domain.vo.trade;

import java.io.Serializable;

public class TaxOrderToPayListVO implements Serializable {

	private static final long serialVersionUID = 5315369074248979352L;
	private String orderNo;
	private TaxOrderToPayGoodsVO goods = new TaxOrderToPayGoodsVO();
	private Integer buyCount;
	private Integer totalPrice;
	private Integer buyPrice;

	public class TaxOrderToPayGoodsVO {
		String goodsId;
		String goodsName;
		String topPicKey;
		String brief;
		int discountPrice;
		int onsalePrice;

		public String getGoodsId() {
			return goodsId;
		}

		public void setGoodsId(String goodsId) {
			this.goodsId = goodsId;
		}

		public String getGoodsName() {
			return goodsName;
		}

		public void setGoodsName(String goodsName) {
			this.goodsName = goodsName;
		}

		

		public String getTopPicKey() {
			return topPicKey;
		}

		public void setTopPicKey(String topPicKey) {
			this.topPicKey = topPicKey;
		}

		public int getDiscountPrice() {
			return discountPrice;
		}

		public void setDiscountPrice(int discountPrice) {
			this.discountPrice = discountPrice;
		}

		public int getOnsalePrice() {
			return onsalePrice;
		}

		public void setOnsalePrice(int onsalePrice) {
			this.onsalePrice = onsalePrice;
		}

		public String getBrief() {
			return brief;
		}

		public void setBrief(String brief) {
			this.brief = brief;
		}

	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public TaxOrderToPayGoodsVO getGoods() {
		return goods;
	}

	public void setGoods(TaxOrderToPayGoodsVO goods) {
		this.goods = goods;
	}

	public Integer getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Integer buyPrice) {
		this.buyPrice = buyPrice;
	}

}
