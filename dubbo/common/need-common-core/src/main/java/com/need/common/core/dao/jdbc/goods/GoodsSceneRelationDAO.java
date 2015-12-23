package com.need.common.core.dao.jdbc.goods;

import com.need.common.domain.po.api.goods.GoodsSceneRelationPO;

import java.util.List;

public interface GoodsSceneRelationDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsSceneRelationPO record);

    int insertSelective(GoodsSceneRelationPO record);

    GoodsSceneRelationPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsSceneRelationPO record);

    int updateByPrimaryKey(GoodsSceneRelationPO record);
    
    List<String> getGoodsIdsBySceneId(int sceneId);
    
}