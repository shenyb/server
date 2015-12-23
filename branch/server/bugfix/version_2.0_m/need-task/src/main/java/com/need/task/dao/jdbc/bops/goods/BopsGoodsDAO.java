package com.need.task.dao.jdbc.bops.goods;

import com.need.task.dao.jdbc.bops.goods.po.BopsGoods;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface BopsGoodsDAO {
	        
    BopsGoods selectByGoodsId(String goodsId);
    
    int updatePriceById(@Param("price") Integer price,@Param("goodsId")String goodsId,@Param("updateTime") Date updateTime);
    
            
}