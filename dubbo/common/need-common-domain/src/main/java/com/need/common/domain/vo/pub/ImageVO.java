package com.need.common.domain.vo.pub;

import java.io.Serializable;

public class ImageVO implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -749458252367582288L;
	private String imageBase64;
	private String imageType;
	public String getImageBase64() {
		return imageBase64;
	}
	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	@Override
	public String toString() {
		return "ImageVO [imageBase64=" + imageBase64 + ", imageType=" + imageType + "]";
	}
	
	
	
}
