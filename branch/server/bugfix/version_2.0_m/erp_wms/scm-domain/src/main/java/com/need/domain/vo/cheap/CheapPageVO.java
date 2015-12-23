package com.need.domain.vo.cheap;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.need.domain.po.bops.cheap.BopsCheapBase;
import com.need.domain.pub.Page;

public class CheapPageVO extends Page implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -3609584104290183444L;
	
	private String cheapNo;
    @NotBlank(message="goodsId不能为空")
    private String goodsId;
    @NotBlank(message="商品名称不能为空")
    private String goodsName;
    
    private String goodsPicKey;
    @NotBlank(message="商品介绍不能为空")
    private String goodsBrief;
    private Integer cheapCount;
    @NotBlank(message="团便宜价格不能为空")
    private String cheapPrice;
    private String cheapSharePicKey;
    @NotBlank(message="团分享title不能为空")
    private String cheapShareTitle;
    @NotBlank(message="团分享内容不能为空")
    private String cheapShareContent;
    
    private Date endTime;
    private Date startTime;
    @NotNull(message="持续时间不能为空")
    private Integer duringTime;
    @NotBlank(message="团图片不能为空")
    private String cheapPicKey;
    @NotBlank(message="活动说明不能为空")
    private String cheapDescription;
    private String joinDescription;
    private String openDescription;

    private String auditStatus;

    private Integer bopsUserId;

    private Date createTime;
    @NotBlank(message="商品原价不能为空")
    private String onsalePrice;
    @NotBlank(message="截止日期不能为空")
    private String endTimeString;
    @NotBlank(message="开始日期不能为空")
    private String startTimeString;
    @NotBlank(message="仓库类型不能为空")
    private String warehouseType;
    private String goodsCode;
    
    
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public String getStartTimeString() {
		return startTimeString;
	}
	public void setStartTimeString(String startTimeString) {
		this.startTimeString = startTimeString;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getWarehouseType() {
		return warehouseType;
	}
	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}
	public String getEndTimeString() {
		return endTimeString;
	}
	public void setEndTimeString(String endTimeString) {
		this.endTimeString = endTimeString;
	}
	public String getCheapNo() {
		return cheapNo;
	}
	public void setCheapNo(String cheapNo) {
		this.cheapNo = cheapNo;
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
	public String getGoodsPicKey() {
		return goodsPicKey;
	}
	public void setGoodsPicKey(String goodsPicKey) {
		this.goodsPicKey = goodsPicKey;
	}
	public String getGoodsBrief() {
		return goodsBrief;
	}
	public void setGoodsBrief(String goodsBrief) {
		this.goodsBrief = goodsBrief;
	}
	public Integer getCheapCount() {
		return cheapCount;
	}
	public void setCheapCount(Integer cheapCount) {
		this.cheapCount = cheapCount;
	}

	public String getCheapSharePicKey() {
		return cheapSharePicKey;
	}
	public void setCheapSharePicKey(String cheapSharePicKey) {
		this.cheapSharePicKey = cheapSharePicKey;
	}
	public String getCheapShareTitle() {
		return cheapShareTitle;
	}
	public void setCheapShareTitle(String cheapShareTitle) {
		this.cheapShareTitle = cheapShareTitle;
	}
	public String getCheapShareContent() {
		return cheapShareContent;
	}
	public void setCheapShareContent(String cheapShareContent) {
		this.cheapShareContent = cheapShareContent;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getDuringTime() {
		return duringTime;
	}
	public void setDuringTime(Integer duringTime) {
		this.duringTime = duringTime;
	}
	public String getCheapPicKey() {
		return cheapPicKey;
	}
	public void setCheapPicKey(String cheapPicKey) {
		this.cheapPicKey = cheapPicKey;
	}
	public String getCheapDescription() {
		return cheapDescription;
	}
	public void setCheapDescription(String cheapDescription) {
		this.cheapDescription = cheapDescription;
	}
	public String getJoinDescription() {
		return joinDescription;
	}
	public void setJoinDescription(String joinDescription) {
		this.joinDescription = joinDescription;
	}
	public String getOpenDescription() {
		return openDescription;
	}
	public void setOpenDescription(String openDescription) {
		this.openDescription = openDescription;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	public Integer getBopsUserId() {
		return bopsUserId;
	}
	public void setBopsUserId(Integer bopsUserId) {
		this.bopsUserId = bopsUserId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


   
	public String getCheapPrice() {
		return cheapPrice;
	}
	public void setCheapPrice(String cheapPrice) {
		this.cheapPrice = cheapPrice;
	}
	public String getOnsalePrice() {
		return onsalePrice;
	}
	public void setOnsalePrice(String onsalePrice) {
		this.onsalePrice = onsalePrice;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "CheapPageVO [cheapNo=" + cheapNo + ", goodsId=" + goodsId + ", goodsName=" + goodsName
				+ ", goodsPicKey=" + goodsPicKey + ", goodsBrief=" + goodsBrief + ", cheapCount=" + cheapCount
				+ ", cheapPrice=" + cheapPrice + ", cheapSharePicKey=" + cheapSharePicKey + ", cheapShareTitle="
				+ cheapShareTitle + ", cheapShareContent=" + cheapShareContent + ", endTime=" + endTime + ", startTime="
				+ startTime + ", duringTime=" + duringTime + ", cheapPicKey=" + cheapPicKey + ", cheapDescription="
				+ cheapDescription + ", joinDescription=" + joinDescription + ", openDescription=" + openDescription
				+ ", auditStatus=" + auditStatus + ", bopsUserId=" + bopsUserId + ", createTime=" + createTime
				+ ", onsalePrice=" + onsalePrice + ", endTimeString=" + endTimeString + ", startTimeString="
				+ startTimeString + ", warehouseType=" + warehouseType + ", goodsCode=" + goodsCode + "]";
	}
    
    

}
