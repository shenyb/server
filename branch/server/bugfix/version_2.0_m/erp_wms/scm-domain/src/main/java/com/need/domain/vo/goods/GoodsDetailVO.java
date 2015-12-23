package com.need.domain.vo.goods;

import java.util.Arrays;

public class GoodsDetailVO {

	private String goodsDesc;
	
	private String [] detailPicKeys;

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public String[] getDetailPicKeys() {
		return detailPicKeys;
	}

	public void setDetailPicKeys(String[] detailPicKeys) {
		this.detailPicKeys = detailPicKeys;
	}

	@Override
	public String toString() {
		return "GoodsDetailVO [goodsDesc=" + goodsDesc + ", detailPicKeys=" + Arrays.toString(detailPicKeys) + "]";
	}
	
}
