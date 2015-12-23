package com.need.domain.po.api.need;

import java.io.Serializable;
import java.util.Date;

public class NeedFeedCommentPO implements Serializable {
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 4184755051453850573L;

	private String commentId;

    private String authorId;

    private String feedId;

    private String comment;

    private Date createTime;
    
    private String feedStatus;

  

	public String getFeedStatus() {
		return feedStatus;
	}

	public void setFeedStatus(String feedStatus) {
		this.feedStatus = feedStatus;
	}

	public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public String toString() {
		return "NeedFeedCommentPO [commentId=" + commentId + ", authorId=" + authorId + ", feedId=" + feedId
				+ ", comment=" + comment + ", createTime=" + createTime + "]";
	}
    
    
}