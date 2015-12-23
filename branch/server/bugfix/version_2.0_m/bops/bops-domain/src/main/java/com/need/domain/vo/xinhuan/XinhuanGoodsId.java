package com.need.domain.vo.xinhuan;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.need.utils.UUIDUtils;

public class XinhuanGoodsId {
	
	public static String generateXinhuanGoodsId(){
		
		String xinhuanGoodsId = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		xinhuanGoodsId = xinhuanGoodsId + sdf.format(Calendar.getInstance().getTime());
		xinhuanGoodsId = xinhuanGoodsId + UUIDUtils.getUUID().substring(0, 20);
		return xinhuanGoodsId;
	}

}
