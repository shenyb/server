package com.need.service.bops.scene.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.need.dao.api.goods.GoodsSceneDAO;
import com.need.dao.bops.basedata.BopsSceneDAO;
import com.need.domain.po.bops.basedata.BopsScene;
import com.need.domain.pub.Page;
import com.need.service.bops.scene.BopsSceneService;
@Service
public class BopsSceneServiceImpl implements BopsSceneService{

	@Autowired
	private BopsSceneDAO bopsSceneDAO;
		
	@Autowired
	private GoodsSceneDAO goodsSceneDAO;
    /**
     * 
     * @author peiboli 2015年8月4日 上午10:16:06
     * @Method: addScene 
     * @Description: TODO
     * @param bopsScene
     * @return 
     * @see com.need.bops.service.scene.BopsSceneService#addScene(com.need.bops.dao.jdbc.basedata.po.BopsScene)
     */
	@Override
	@Transactional
	public BopsScene addScene(BopsScene bopsScene) {
		// TODO Auto-generated method stub
		bopsSceneDAO.insert(bopsScene);
		goodsSceneDAO.insert(bopsScene);//同步前台
		bopsScene.setCreateTime(Calendar.getInstance().getTime());
		return bopsScene;
	}
    /**
     * 
     * @author peiboli 2015年8月4日 上午10:16:00
     * @Method: selectScene 
     * @Description: TODO
     * @return 
     * @see com.need.bops.service.scene.BopsSceneService#selectScene()
     */
	@Override
	public List<BopsScene> getSceneList(String search,Page page) {
		// TODO Auto-generated method stub		
		page.setTotal(bopsSceneDAO.selectCount(search));
		Map<String,Object> paramMap = new HashMap<String,Object>(); 
		paramMap.put("search", search);
		paramMap.put("start", page.getStart());
		paramMap.put("pagesize", page.getPageSize());
		List<BopsScene> bopsSceneList = bopsSceneDAO.queryByPage(paramMap);
		return bopsSceneList;
	}
    /**
     * 
     * @author peiboli 2015年8月4日 上午10:15:56
     * @Method: deleteBopsScene 
     * @Description: TODO
     * @param sceneId
     * @return 
     * @see com.need.bops.service.scene.BopsSceneService#deleteBopsScene(java.lang.String)
     */
	@Override
	@Transactional
	public int deleteBopsScene(String sceneId) {
		// TODO Auto-generated method stub
		int code = bopsSceneDAO.removeScenebySceneid(sceneId);
		goodsSceneDAO.removeScenebySceneid(sceneId);
		return code;
		
	}

	/**
	 * 
	 * @author peiboli 2015年8月4日 上午10:15:50
	 * @Method: updateBopsScene 
	 * @Description: TODO
	 * @param bopsScene
	 * @return 
	 * @see com.need.bops.service.scene.BopsSceneService#updateBopsScene(com.need.bops.dao.jdbc.basedata.po.BopsScene)
	 */
	@Override
	@Transactional
	public int updateBopsScene(BopsScene bopsScene) {
		// TODO Auto-generated method stub
		int code = bopsSceneDAO.update(bopsScene);
		goodsSceneDAO.update(bopsScene);
		return code;
	}
	/**
	 * 
	 * @author peiboli 2015年8月4日 上午11:01:44
	 * @Method: selectBopsSceneBySceneName 
	 * @Description: TODO查一条数据
	 * @param sceneName
	 * @return 
	 * @see com.need.bops.service.scene.BopsSceneService#selectBopsSceneBySceneName(java.lang.String)
	 */
	@Override
	public BopsScene selectBopsSceneBySceneName(String sceneName) {
		// TODO Auto-generated method stub
		BopsScene bopsScene = bopsSceneDAO.selectBySceneName(sceneName);
		return bopsScene;
	}
	
	
}
