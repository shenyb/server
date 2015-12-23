package com.need.dao.bops.basedata;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.need.domain.po.bops.basedata.BopsScene;


public interface BopsSceneDAO {

	   /**
	    * 
	    * @author peiboli 2015年8月26日 下午3:04:06
	    * @Method: insert 
	    * @Description: TODO插入场景分类
	    * @param bopsScene
	    * @return 
	    * @throws
	    */
	    int insert( BopsScene bopsScene);
		/**
		 * 
		 * @author peiboli 2015年8月26日 下午3:04:24
		 * @Method: queryByPage 
		 * @Description: TODO获得场景分类列表
		 * @param paramMap
		 * @return 
		 * @throws
		 */
		List<BopsScene> queryByPage(Map<String,Object> paramMap);

		/**
		 * 
		 * @author peiboli 2015年8月26日 下午3:05:29
		 * @Method: removeScenebySceneid 
		 * @Description: TODO根据id删除场景分类
		 * @param sceneId
		 * @return 
		 * @throws
		 */
		int removeScenebySceneid( String sceneId);

		/**
		 * 
		 * @author peiboli 2015年8月26日 下午3:05:49
		 * @Method: update 
		 * @Description: TODO更新
		 * @param bopsScene
		 * @return 
		 * @throws
		 */
		int update( BopsScene bopsScene);

		/**
		 * 
		 * @author peiboli 2015年8月26日 下午3:07:25
		 * @Method: selectBySceneName 
		 * @Description: TODO根据名称查询一条数据
		 * @param sceneName
		 * @return 
		 * @throws
		 */
		BopsScene selectBySceneName( String sceneName);
		/**
		 * 
		 * @author peiboli 2015年8月26日 下午3:07:44
		 * @Method: selectCount 
		 * @Description: TODO获得场景分类总条数
		 * @param search
		 * @return 
		 * @throws
		 */
		Integer selectCount(@Param("search") String search);

		/**
		 * 
		 * @author peiboli 2015年8月26日 下午3:09:43
		 * @Method: selectCategoryName 
		 * @Description: TODO校验场景分类名是否存在
		 * @param sceneName
		 * @return 
		 * @throws
		 */
		int selectCategoryName(String sceneName);

}