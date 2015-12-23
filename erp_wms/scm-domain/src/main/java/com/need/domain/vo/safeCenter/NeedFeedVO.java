package com.need.domain.vo.safeCenter;

import java.io.Serializable;
import java.util.Date;

public class NeedFeedVO implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 6960492858114265557L;
    private String feedId;
    private String nickName;
    private Date createTime; 
    private String memo;
    private String goodsName;
    private String scenePicKey;
    private String feedStatus;
	public String getFeedId() {
		return feedId;
	}
	public void setFeedId(String feedId) {
		this.feedId = feedId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getScenePicKey() {
		return scenePicKey;
	}
	public void setScenePicKey(String scenePicKey) {
		this.scenePicKey = scenePicKey;
	}
	public String getFeedStatus() {
		return feedStatus;
	}
	public void setFeedStatus(String feedStatus) {
		this.feedStatus = feedStatus;
	}
	@Override
	public String toString() {
		return "NeedFeedVO [feedId=" + feedId + ", nickName=" + nickName + ", createTime=" + createTime + ", memo="
				+ memo + ", goodsName=" + goodsName + ", scenePicKey=" + scenePicKey + ", feedStatus=" + feedStatus
				+ "]";
	}
    
    
}
