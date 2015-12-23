package com.need.service.bops.ops;

import java.util.List;

import com.need.domain.po.bops.basedata.GoodsSceneResultPO;
import com.need.domain.po.bops.goods.BopsGoodsScene;
import com.need.domain.pub.Page;
import com.need.domain.vo.ops.GoodsSceneSearchParam;
import com.need.domain.vo.ops.GoodsSceneVo;

public interface GoodsSceneService {
	/**
	 * 
	 * @author peiboli 2015年8月5日 下午5:20:45
	 * @Method: getgoodsSceneVoList 
	 * @Description: TODO获得商品场景列表
	 * @return 
	 * @throws
	 */
	List<GoodsSceneVo> getgoodsSceneVoList();

    /**
     * 
     * @author peiboli 2015年8月5日 下午6:09:12
     * @Method: checkIsAdd 
     * @Description: TODO核对商品是否已经添加过场景
     * @param goodsId
     * @return 
     * @throws
     */
    BopsGoodsScene checkIsAdd(String goodsId,Integer senceId);
    
    /**
     * @author Rylan 2015年8月10日 上午1:25:22
     * @Method: addGoodsScene 
     * @Description: TODO 添加商品场景信息
     * @param bopsGoodsScene
     * @return 
     * @throws
     */
    BopsGoodsScene addGoodsScene(BopsGoodsScene bopsGoodsScene);

	void deleteGoodsScene(String Id);
 
	void updateGoodsScene(BopsGoodsScene bopsGoodsScene);

	List<GoodsSceneVo> searchGoodsScene(String search,Page goodsScenepage);

	List<GoodsSceneVo> GoodsSceneList(Page goodsScenepage);


    
	void addApiGoodsScene(BopsGoodsScene bopsGoodsScene);

   
    /**
     * @author Rylan 2015年8月10日 下午5:32:06
     * @Method: searchGoodsNameOrGoodsCode 
     * @Description: TODO
     * @param goodsSceneSearchParam
     * @return 
     * @throws
     */
    List<GoodsSceneResultPO>  searchGoodsNameOrGoodsCode(GoodsSceneSearchParam goodsSceneSearchParam);
    /**
     * @author Rylan 2015年8月10日 下午5:32:03
     * @Method: getGoodsScene 
     * @Description: TODO
     * @param id
     * @return 
     * @throws
     */
    BopsGoodsScene getGoodsScene(Integer id);

	void updateApiGoodsScene(BopsGoodsScene bopsGoodsScene);

	void deleteApiGoodsScene(String id);
    
    /**
	 * @author Rylan 2015年8月10日 下午6:19:26
	 * @Method: getGoodsSceneDetail 
	 * @Description: TODO
	 * @param goodsId
	 * @return 
	 * @throws
	 */
	List<GoodsSceneResultPO>  getGoodsSceneDetail( String goodsId,String sceneId);
    /**
     * 
     * @author LXD 2015年8月10日 下午10:46:09
     * @Method: getGoodsSceneByGoodsId 
     * @Description: TODO 根据商品Id 查找
     * @param goodsId
     * @return 
     * @throws
     */
    
    List<GoodsSceneResultPO> getGoodsSceneByGoodsId(String goodsId);

	void deleteGoodsSceneBygoodsId(String string);

}
