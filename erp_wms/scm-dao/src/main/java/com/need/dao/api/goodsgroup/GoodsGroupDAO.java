package com.need.dao.api.goodsgroup;

import java.util.List;

import com.need.domain.po.api.goodsgroup.GoodsGroup;
import com.need.domain.po.api.goodsgroup.GoodsGroupKey;
import com.need.domain.vo.goodsgroup.GoodsShowVO;

public interface GoodsGroupDAO {
    int deleteByPrimaryKey(GoodsGroupKey key);

    int insert(GoodsGroup record);

    int insertSelective(GoodsGroup record);

    GoodsGroup selectByPrimaryKey(GoodsGroupKey key);

    int updateByPrimaryKeySelective(GoodsGroup record);

    int updateByPrimaryKey(GoodsGroup record);

    List<GoodsShowVO> getByBatch(String groupBatch);

	
}