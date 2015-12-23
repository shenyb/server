package com.need.domain.vo.goodsgroup;

import java.io.Serializable;
import java.util.Date;

import com.need.domain.pub.Page;

/**
 * <p></p>
 * @author LXD 2015年11月25日 上午10:38:36
 * @ClassName GoodsGroupVO
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年11月25日
 * @modify by reason:{方法名}:{原因}
 */
public class GoodsGroupVO extends Page implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -8721181511046798556L;
	
	 private String groupBatch;
	 private String groupName;
	 private String groupStatus;
	 private String startTimeString;
	 private String endTimeString;
	 private String groupBrief;
	 private String ruleType;
	 private String ruleOne;
	 private String ruleOneDisc;
	 private String ruleTwo;
	 private String ruleTwoDisc;
	 private String ruleThree;
	 private String ruleThreeDisc;
	 private String ruleFixed;
	 private String ruleFixedPrice;
	 private Integer createId;
	 private Date startTime;
	 private Date endTime;
	 private String goodsCode;
	 private String goodsCodes;
	 private Integer updateId;
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	public Integer getUpdateId() {
		return updateId;
	}
	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}
	public String getGoodsCodes() {
		return goodsCodes;
	}
	public void setGoodsCodes(String goodsCodes) {
		this.goodsCodes = goodsCodes;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getCreateId() {
		return createId;
	}
	public void setCreateId(Integer createId) {
		this.createId = createId;
	}
	public String getStartTimeString() {
		return startTimeString;
	}
	public void setStartTimeString(String startTimeString) {
		this.startTimeString = startTimeString;
	}
	public String getEndTimeString() {
		return endTimeString;
	}
	public void setEndTimeString(String endTimeString) {
		this.endTimeString = endTimeString;
	}
	public String getGroupBrief() {
		return groupBrief;
	}
	public void setGroupBrief(String groupBrief) {
		this.groupBrief = groupBrief;
	}
   
	public String getRuleType() {
		return ruleType;
	}
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	public String getRuleOne() {
		return ruleOne;
	}
	public void setRuleOne(String ruleOne) {
		this.ruleOne = ruleOne;
	}
	public String getRuleOneDisc() {
		return ruleOneDisc;
	}
	public void setRuleOneDisc(String ruleOneDisc) {
		this.ruleOneDisc = ruleOneDisc;
	}
	public String getRuleTwo() {
		return ruleTwo;
	}
	public void setRuleTwo(String ruleTwo) {
		this.ruleTwo = ruleTwo;
	}
	public String getRuleTwoDisc() {
		return ruleTwoDisc;
	}
	public void setRuleTwoDisc(String ruleTwoDisc) {
		this.ruleTwoDisc = ruleTwoDisc;
	}
	public String getRuleThree() {
		return ruleThree;
	}
	public void setRuleThree(String ruleThree) {
		this.ruleThree = ruleThree;
	}
	public String getRuleThreeDisc() {
		return ruleThreeDisc;
	}
	public void setRuleThreeDisc(String ruleThreeDisc) {
		this.ruleThreeDisc = ruleThreeDisc;
	}
	public String getRuleFixed() {
		return ruleFixed;
	}
	public void setRuleFixed(String ruleFixed) {
		this.ruleFixed = ruleFixed;
	}
	public String getRuleFixedPrice() {
		return ruleFixedPrice;
	}
	public void setRuleFixedPrice(String ruleFixedPrice) {
		this.ruleFixedPrice = ruleFixedPrice;
	}
	public String getGroupBatch() {
		return groupBatch;
	}
	public void setGroupBatch(String groupBatch) {
		this.groupBatch = groupBatch;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupStatus() {
		return groupStatus;
	}
	public void setGroupStatus(String groupStatus) {
		this.groupStatus = groupStatus;
	}
	 
	 


	
	
}
