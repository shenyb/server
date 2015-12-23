package com.need.common.domain.vo.goodsgroup;

import java.io.Serializable;

public class GroupRuleVO  implements Serializable, Comparable<GroupRuleVO>{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -4016068790945727217L;
	
	private Integer number;
	private String discount;
	private Integer fixedPrice;
	
    
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public Integer getFixedPrice() {
		return fixedPrice;
	}
	public void setFixedPrice(Integer fixedPrice) {
		this.fixedPrice = fixedPrice;
	}
	
	@Override
    public int compareTo(GroupRuleVO arg0) {
        return arg0.getNumber().compareTo(this.getNumber());
    }
	
	
}
