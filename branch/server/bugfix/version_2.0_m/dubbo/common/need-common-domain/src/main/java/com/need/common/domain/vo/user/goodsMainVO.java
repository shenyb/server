package com.need.common.domain.vo.user;

import java.io.Serializable;

public class goodsMainVO implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -2525344346386923865L;
	
	 private String goodsId;

	    private String goodsName;


	    private String brief;

	    private String needPicKey;


	    private Integer onsalePrice;

	    private Integer storeCount;
	    
	    private Integer discountPrice;
	    

		public String getNeedPicKey() {
			return needPicKey;
		}

		public void setNeedPicKey(String needPicKey) {
			this.needPicKey = needPicKey;
		}

		public Integer getDiscountPrice() {
			return discountPrice;
		}

		public void setDiscountPrice(Integer discountPrice) {
			this.discountPrice = discountPrice;
		}

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

		public String getBrief() {
			return brief;
		}

		public void setBrief(String brief) {
			this.brief = brief;
		}

	

		public Integer getOnsalePrice() {
			return onsalePrice;
		}

		public void setOnsalePrice(Integer onsalePrice) {
			this.onsalePrice = onsalePrice;
		}

		public Integer getStoreCount() {
			return storeCount;
		}

		public void setStoreCount(Integer storeCount) {
			this.storeCount = storeCount;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}



}
