package com.need.integration.dao.jdbc.api.goods;

import com.need.integration.dao.jdbc.api.goods.po.GoodsDetailPO;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsDetailDAO {

    GoodsDetailPO selectByPrimaryKey(String goodsId);

}