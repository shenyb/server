package com.need.common.core.dao.jdbc.goods;

import com.need.common.domain.po.api.goods.GoodsScenePO;

public interface GoodsSceneDAO {
    int deleteByPrimaryKey(Integer sceneId);

    int insert(GoodsScenePO record);

    int insertSelective(GoodsScenePO record);

    GoodsScenePO selectByPrimaryKey(Integer sceneId);

    int updateByPrimaryKeySelective(GoodsScenePO record);

    int updateByPrimaryKey(GoodsScenePO record);
}