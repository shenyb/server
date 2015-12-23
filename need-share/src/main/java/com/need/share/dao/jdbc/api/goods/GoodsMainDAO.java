package com.need.share.dao.jdbc.api.goods;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.need.share.web.controller.goods.vo.GoodsResultVO;

public interface GoodsMainDAO {
  
	GoodsResultVO selectByGoodId(String goodsId);
     	
	String getDoodsDetail(); 
	
	GoodsResultVO selectByGoodIdAndUpdateTime(@Param("goodsId")String goodsId,@Param("updateTime")Date updateTime);
}