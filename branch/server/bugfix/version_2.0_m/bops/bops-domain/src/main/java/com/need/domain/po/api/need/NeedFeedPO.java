package com.need.domain.po.api.need;

import java.io.Serializable;
import java.util.Date;

public class NeedFeedPO implements Serializable {
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -2177605240241686443L;

	private String feedId;

    private String authorId;

    private String goodsId;

    private String memo;

    private Date createTime;
    
    private String feedStatus;
    

	public String getFeedStatus() {
		return feedStatus;
	}

	public void setFeedStatus(String feedStatus) {
		this.feedStatus = feedStatus;
	}

	public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public String toString() {
		return "NeedFeedPO [feedId=" + feedId + ", authorId=" + authorId + ", goodsId=" + goodsId + ", memo=" + memo
				+ ", createTime=" + createTime + "]";
	}
    
    
}