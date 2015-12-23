package com.need.common.core.dao.jdbc.goods;

import com.need.common.domain.po.api.goods.GoodsMainPO;
import com.need.common.domain.vo.goods.GoodsPicListResultVO;
import com.need.common.domain.vo.goods.GoodsProfileResultVO;
import com.need.common.domain.vo.goods.GoodsResultVO;
import com.need.common.domain.vo.goods.GoodsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMainDAO {
	 int deleteByPrimaryKey(String goodsId);

	    int insert(GoodsMainPO record);

	    int insertSelective(GoodsMainPO record);

	    GoodsMainPO selectByPrimaryKey(String goodsId);
	    
	    GoodsMainPO selectByPrimaryKeyForTrade(String goodsId);
	    
	    GoodsResultVO getGoodsById(String goodsId);
	    
	    GoodsProfileResultVO getGoodsProfileById(String goodsId);
	    
	    GoodsProfileResultVO getGoodsProfileById_V1_3(String goodsId);

	    int updateByPrimaryKeySelective(GoodsMainPO record);

	    int updateByPrimaryKey(GoodsMainPO record);
	    
	    List<GoodsVO> getGoodsListByGoodsIds(@Param("goodsIds")String goodsIds);
	    /**
	     * @author Rylan 2015年8月15日 下午4:56:11
	     * @Method: getDiscountPriceByGoodsId 
	     * @Description: TODO 查询商品价格
	     * @param goodsIds
	     * @return 
	     * @throws
	     */
	    int getDiscountPriceByGoodsId(String goodsId);
	    
	    /**
	     * @author xiehao 2015年8月20日 下午8:00:46
	     * @Method: getGoodsListBySceneId 
	     * @Description: TODO 根据场景ID获得商品列表
	     * @param sceneId
	     * @return 
	     * @throws
	     */
	    List<GoodsVO> queryGoodsListBySceneId(@Param("sceneId")int sceneId);
	    
	    GoodsPicListResultVO getGoodsPicListByGoodsId(String goodsId);

		int updateStoreCountAndLockCountByGoodsId(@Param("goodsId")String goodsId, @Param("buyCount")int buyCount);
		
		
		/**
		 * 根据goodsId和购买数，计算总价
		 * @param goodsId
		 * @param buyCount
		 * @return
		 */
		Integer getByGoodsIdAndBuyCount(@Param("goodsId")String goodsId, @Param("buyCount")int buyCount);
		
	    /**
	     * @author Rylan 2015年9月20日 上午11:09:23
	     * @Method: checkGoodsGlobal 
	     * @Description: 检查商品是否是保税仓商品
	     * @param goodsId
	     * @return 
	     * @throws
	     */
		String getGoodsGlobal(@Param("goodsId")String goodsId);

		List<GoodsMainPO> getgoodsByGoodsIds(List<String> goodsIds);

		GoodsProfileResultVO getGoodsProfileById_V2_0(String goodsId);

		List<GoodsVO> getByCategory(Integer categoryId);
	
}