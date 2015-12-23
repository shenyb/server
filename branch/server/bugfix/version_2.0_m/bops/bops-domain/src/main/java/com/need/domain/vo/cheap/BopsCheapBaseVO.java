package com.need.domain.vo.cheap;

import java.io.Serializable;
import java.util.Date;

public class BopsCheapBaseVO  implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -1845779706897061582L;

	private String cheapNo;

    private String goodsId;

    private String goodsName;

    private String goodsPicKey;

    private String goodsBrief;

    private Integer cheapCount;

    private String cheapPrice;

    private String cheapSharePicKey;

    private String cheapShareTitle;

    private String cheapShareContent;

    private Date endTime;
    private Date startTime;
     
    private Integer duringTime;

    private String cheapPicKey;

    private String cheapDescription;

    private String joinDescription;

    private String openDescription;

    private String auditStatus;

    private Integer bopsUserId;

    private Date createTime;
    private Integer onsalePrice;
    private String warehouseType;
    private String goodsCode;
    
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
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
	
	public String getCheapPrice() {
		return cheapPrice;
	}
	public void setCheapPrice(String cheapPrice) {
		this.cheapPrice = cheapPrice;
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
	public Integer getOnsalePrice() {
		return onsalePrice;
	}
	public void setOnsalePrice(Integer onsalePrice) {
		this.onsalePrice = onsalePrice;
	}
	public String getWarehouseType() {
		return warehouseType;
	}
	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "BopsCheapBaseVO [cheapNo=" + cheapNo + ", goodsId=" + goodsId + ", goodsName=" + goodsName
				+ ", goodsPicKey=" + goodsPicKey + ", goodsBrief=" + goodsBrief + ", cheapCount=" + cheapCount
				+ ", cheapPrice=" + cheapPrice + ", cheapSharePicKey=" + cheapSharePicKey + ", cheapShareTitle="
				+ cheapShareTitle + ", cheapShareContent=" + cheapShareContent + ", endTime=" + endTime
				+ ", duringTime=" + duringTime + ", cheapPicKey=" + cheapPicKey + ", cheapDescription="
				+ cheapDescription + ", joinDescription=" + joinDescription + ", openDescription=" + openDescription
				+ ", auditStatus=" + auditStatus + ", bopsUserId=" + bopsUserId + ", createTime=" + createTime
				+ ", onsalePrice=" + onsalePrice + ", warehouseType=" + warehouseType + ", goodsCode=" + goodsCode
				+ "]";
	}
    
    
    
}
