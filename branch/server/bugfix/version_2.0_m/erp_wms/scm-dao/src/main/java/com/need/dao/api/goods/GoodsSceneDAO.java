package com.need.dao.api.goods;

import com.need.domain.po.bops.basedata.BopsScene;

public interface GoodsSceneDAO {
    int deleteByPrimaryKey(Integer sceneId);

	void insert(BopsScene apiScene);

	void removeScenebySceneid(String sceneId);

	void update(BopsScene bopsScene);
	
}