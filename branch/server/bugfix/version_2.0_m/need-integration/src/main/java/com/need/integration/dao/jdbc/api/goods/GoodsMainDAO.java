package com.need.integration.dao.jdbc.api.goods;


import org.apache.ibatis.annotations.Param;

import com.need.integration.dao.jdbc.api.goods.po.GoodsMainPO;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsMainDAO {
	GoodsMainPO getByGoodsId(String goodsId);

	int updateLockCountByGoodsId(@Param("goodsId") String goodsId, @Param("buyCount") int buyCount);
}