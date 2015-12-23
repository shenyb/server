package com.need.dao.bops.goods;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.need.domain.po.bops.basedata.GoodsSceneResultPO;
import com.need.domain.po.bops.goods.BopsGoodsScene;
import com.need.domain.pub.Page;
import com.need.domain.vo.ops.GoodsSceneSearchParam;
import com.need.domain.vo.ops.GoodsSceneVo;

public interface BopsGoodsSceneDAO {
    int deleteByPrimaryKey(String id);

    int insert(BopsGoodsScene record);

    int insertSelective( BopsGoodsScene record);

    BopsGoodsScene selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BopsGoodsScene record);

    int updateByPrimaryKey(BopsGoodsScene record);

	List<GoodsSceneVo> getgoodsSceneVoList();
    /**
     * @author Rylan 2015年8月10日 下午12:25:23
     * @Method: checkIsAdd 
     * @Description: TODO 检查商品和该场景
     * @param goodsId
     * @param sceneId
     * @return 
     * @throws
     */
	BopsGoodsScene  checkIsAdd(String goodsId,Integer sceneId);
    
	void addGoodsScene( BopsGoodsScene bopsGoodsScene);
   
	void deleteByGoodsId( String goodsId);

	void updateGoodsScene( BopsGoodsScene bopsGoodsScene);

    /**
     * 
     * @author peiboli 2015年8月6日 上午11:24:38
     * @Method: searchGoodsScene 
     * @Description: TODO搜索
     * @param search
     * @return 
     * @throws
     */
	List<GoodsSceneVo> searchGoodsScene(@Param("search")String search,@Param("goodsScenepage")Page goodsScenepage);

	/**
	 * @author Rylan 2015年8月10日 下午6:18:18
	 * @Method: searchCount 
	 * @Description: TODO
	 * @param search
	 * @return 
	 * @throws
	 */
	Integer searchCount(@Param("searchKey")String search);
	
	Integer count();
	
	List<GoodsSceneVo> goodsSceneList(@Param("goodsScenepage") Page goodsScenepage);
	
	
	/**
	 * @author Rylan 2015年8月10日 下午6:18:13
	 * @Method: searchGoodsNameOrGoodsCode 
	 * @Description: TODO
	 * @param goodsSceneSearchParam
	 * @return 
	 * @throws
	 */
	List<GoodsSceneResultPO>  searchGoodsNameOrGoodsCode(GoodsSceneSearchParam goodsSceneSearchParam);
	
	/**
	 * @author Rylan 2015年8月10日 下午6:19:26
	 * @Method: getGoodsSceneDetail 
	 * @Description: TODO
	 * @param goodsId
	 * @return 
	 * @throws
	 */
	List<GoodsSceneResultPO>  getGoodsSceneDetail(@Param("goodsId")  String goodsId,@Param("sceneId")  String sceneId);

	List<GoodsSceneResultPO> getGoodsSceneByGoodsId(String goodsId);
	
}