package com.need.domain.vo.goods;

import java.io.Serializable;

public class GoodsParamsVO implements Serializable {
	
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 426887714240663680L;
	
	private String size;
	private String color;
	private String originPlace;
	private String weight;
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getOriginPlace() {
		return originPlace;
	}
	public void setOriginPlace(String originPlace) {
		this.originPlace = originPlace;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "GoodsParamsVO [size=" + size + ", color=" + color + ", originPlace=" + originPlace + ", weight="
				+ weight + "]";
	}
	
}
