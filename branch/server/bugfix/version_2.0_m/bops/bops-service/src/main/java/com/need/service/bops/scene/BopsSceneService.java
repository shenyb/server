package com.need.service.bops.scene;

import java.util.List;

import com.need.domain.po.bops.basedata.BopsScene;
import com.need.domain.pub.Page;

public interface BopsSceneService {

	BopsScene addScene(BopsScene bopsScene);
	
	List<BopsScene> getSceneList(String search,Page page);

	int deleteBopsScene(String scene_id);

	int updateBopsScene(BopsScene bopsScene);
    
	BopsScene selectBopsSceneBySceneName(String sceneName);


}
