package com.need.share.web.controller.goodsGroup.vo;

import java.io.Serializable;
import java.util.List;

public class GoodsGroupShowVO implements Serializable {
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -1941791112042381549L;
	private String groupBatch; 
	private String ruleType;
	private String groupBrief;
	private String warehouseType;
	private List<GoodsShowVO> goodsShow;
	private List<GroupRuleVO> groupRule;
	
	
	public String getWarehouseType() {
		return warehouseType;
	}
	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}
	public String getGroupBatch() {
		return groupBatch;
	}
	public void setGroupBatch(String groupBatch) {
		this.groupBatch = groupBatch;
	}
	public String getRuleType() {
		return ruleType;
	}
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	public String getGroupBrief() {
		return groupBrief;
	}
	public void setGroupBrief(String groupBrief) {
		this.groupBrief = groupBrief;
	}
	public List<GoodsShowVO> getGoodsShow() {
		return goodsShow;
	}
	public void setGoodsShow(List<GoodsShowVO> goodsShow) {
		this.goodsShow = goodsShow;
	}
	public List<GroupRuleVO> getGroupRule() {
		return groupRule;
	}
	public void setGroupRule(List<GroupRuleVO> groupRule) {
		this.groupRule = groupRule;
	}
	
	
	
	
}
