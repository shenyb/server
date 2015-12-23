package com.need.share.dao.jdbc.api.goods;

import com.need.share.dao.jdbc.api.goods.po.GoodsDetailPO;

public interface GoodsDetailDAO {

    GoodsDetailPO getGoodsDetailByGoodsId(String goodsId);

    
}