package com.need.task.dao.jdbc.api.goods;

import com.need.task.dao.jdbc.api.goods.po.GoodsMainPO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface GoodsMainDAO {


	GoodsMainPO selectByPrimaryKey(@Param("goodsId") String goodsId);

    int updatePriceById(@Param("price") Integer price,@Param("goodsId")String goodsId,@Param("updateTime") Date updateTime);

	int updateStoreCountAndLockCountByGoodsId(@Param("goodsId")String goodsId, @Param("buyCount")int buyCount);


}