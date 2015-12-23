package com.need.dao.api.goods;

import org.apache.ibatis.annotations.Param;

import com.need.domain.po.api.goods.GoodsMainPO;

import java.util.Date;
import java.util.List;

public interface GoodsMainDAO {
	int deleteByPrimaryKey(String goodsId);

	int insert(GoodsMainPO goodsMain);

	int insertSelective(GoodsMainPO goodsMain);

	GoodsMainPO selectByPrimaryKey(@Param("goodsId") String goodsId);

	int updateLockcount(@Param("goodsId") String goodsId, @Param("count") int count);

	int updateByPrimaryKeySelective(GoodsMainPO goodsMain);

	int updateByPrimaryKey(GoodsMainPO goodsMain);

	int updatePriceById(@Param("price") Integer price, @Param("goodsId") String goodsId,
			@Param("updateTime") Date updateTime);

	int updateStoreCount(GoodsMainPO goodsMain);

	int updateGoodsPrice(@Param("goodsId") String goodsId, @Param("discountPrice") Integer discountPrice);

	/**
	 * @author Rylan 2015年8月18日 下午12:10:07 @Method:
	 *         getDiscountPriceByGoodsId @Description: TODO @param
	 *         goodsId @return @throws
	 */
	int getDiscountPriceByGoodsId(String goodsId);

	int updateLockCountByGoodsId(@Param("goodsId") String goodsId, @Param("buyCount") int buyCount);

	List<GoodsMainPO> queryByGoodsCode(String goodsCode);

	public int insertGroupGoods(GoodsMainPO goodsMain);

	/**
	 * 根据套装id 查询该套装的可售库存
	 * 
	 * @param goodsGroupId
	 * @return
	 * @author zhangmengbin
	 */
	int queryMinStoreByGoodsGroupId(String goodsGroupId);

	void updateGoodsStatus(GoodsMainPO goodsMain);

	void updateGroupPrice(GoodsMainPO goodsMain);

	/**
	 * 
	 * @author peiboli 2015年12月11日 下午5:14:25
	 * @Method: updateCategory 
	 * @Description: TODO用于批量导入商品分类
	 * @param categoryId
	 * @param trim 
	 * @throws
	 */
	int updateCategory(@Param("categoryId")String categoryId, @Param("goodsCode")String goodsCode);

}