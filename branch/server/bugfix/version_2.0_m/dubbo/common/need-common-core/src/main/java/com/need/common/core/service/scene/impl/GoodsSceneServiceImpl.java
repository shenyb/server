package com.need.common.core.service.scene.impl;

import com.need.common.core.dao.jdbc.scene.ScenePODAO;
import com.need.common.core.service.scene.GoodsSceneService;
import com.need.common.domain.po.api.goods.GoodsScenePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsSceneServiceImpl  implements GoodsSceneService{

	@Autowired
	ScenePODAO scenePODAO;
	

	@Override
	public List<GoodsScenePO> getGoodsSceneList() {
		// TODO Auto-generated method stub
	    List<GoodsScenePO> getSceneIdList = scenePODAO.queryScene();
		return getSceneIdList;
	}

}
