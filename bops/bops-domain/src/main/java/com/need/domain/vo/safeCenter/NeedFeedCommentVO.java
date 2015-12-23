package com.need.domain.vo.safeCenter;

import java.io.Serializable;
import java.util.Date;

public class NeedFeedCommentVO implements Serializable{

	
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 5020912991163241135L;
	private String feedId;
	private String commentId;
	private String comment;
    private Date createTime;
    private String feedStatus;
	public String getFeedId() {
		return feedId;
	}
	public void setFeedId(String feedId) {
		this.feedId = feedId;
	}
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
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
	public String getFeedStatus() {
		return feedStatus;
	}
	public void setFeedStatus(String feedStatus) {
		this.feedStatus = feedStatus;
	} 
    
   
   
    
}
