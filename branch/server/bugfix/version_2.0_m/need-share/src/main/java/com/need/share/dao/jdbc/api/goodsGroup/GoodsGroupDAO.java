package com.need.share.dao.jdbc.api.goodsGroup;

import java.util.List;

import com.need.share.dao.jdbc.api.goodsGroup.po.GoodsGroup;
import com.need.share.dao.jdbc.api.goodsGroup.po.GoodsGroupKey;
import com.need.share.web.controller.goodsGroup.vo.GoodsShowVO;



public interface GoodsGroupDAO {
    int deleteByPrimaryKey(GoodsGroupKey key);

    int insert(GoodsGroup record);

    int insertSelective(GoodsGroup record);

    GoodsGroup selectByPrimaryKey(GoodsGroupKey key);

    int updateByPrimaryKeySelective(GoodsGroup record);

    int updateByPrimaryKey(GoodsGroup record);

    List<GoodsShowVO> getByBatch(String groupBatch);

	
}