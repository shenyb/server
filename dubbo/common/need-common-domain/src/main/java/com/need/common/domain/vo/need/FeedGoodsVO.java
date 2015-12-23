package com.need.common.domain.vo.need;

import java.io.Serializable;
/**
 * 
 * <p></p>
 * @author LXD 2015年8月12日 下午5:13:03
 * @ClassName FeedGoodsVO
 * @Description TODO 首页feed流的商品VO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年8月12日
 * @modify by reason:{方法名}:{原因}
 */
public class FeedGoodsVO implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -2629975992429814258L;
	
	private String goodsId;
	private String goodsName;
	private String topPicKey;
	private String isGlobal;
	
	
	
	public String getIsGlobal() {
		return isGlobal;
	}
	public void setIsGlobal(String isGlobal) {
		this.isGlobal = isGlobal;
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
	public String getTopPicKey() {
		return topPicKey;
	}
	public void setTopPicKey(String topPicKey) {
		this.topPicKey = topPicKey;
	}
	
	
	
}
