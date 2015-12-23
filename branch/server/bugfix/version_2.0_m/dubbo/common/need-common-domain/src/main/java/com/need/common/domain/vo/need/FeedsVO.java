package com.need.common.domain.vo.need;

import java.io.Serializable;

public class FeedsVO implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 5574521049759157550L;
	
	private String feedId;
	private String content;
	private String isNeed;
	private FeedGoodsVO goods;
	private FeedKolVO kol;
	private Integer commentCount;
	private FeedNeedVO need;
	public String getFeedId() {
		return feedId;
	}
	public void setFeedId(String feedId) {
		this.feedId = feedId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIsNeed() {
		return isNeed;
	}
	public void setIsNeed(String isNeed) {
		this.isNeed = isNeed;
	}
	public FeedGoodsVO getGoods() {
		return goods;
	}
	public void setGoods(FeedGoodsVO goods) {
		this.goods = goods;
	}
	public FeedKolVO getKol() {
		return kol;
	}
	public void setKol(FeedKolVO kol) {
		this.kol = kol;
	}
	public Integer getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	public FeedNeedVO getNeed() {
		return need;
	}
	public void setNeed(FeedNeedVO need) {
		this.need = need;
	}
	
	
}
