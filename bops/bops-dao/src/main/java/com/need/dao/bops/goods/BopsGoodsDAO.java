package com.need.dao.bops.goods;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.need.domain.po.bops.goods.BopsGoods;
import com.need.domain.vo.goods.AuditGoodsVO;
import com.need.domain.vo.goods.BopsGoodsVO;
import com.need.domain.vo.goods.GoodsPageVO;
import com.need.domain.vo.goods.GoodsStoreDetailResultVO;
import com.need.domain.vo.goods.SuitGoodsPriceVO;

public interface BopsGoodsDAO {

	int deleteByPrimaryKey(String goodsId);

	int insertValue(BopsGoods goods);

	int insertSelective(BopsGoodsDAO bopsGoods);

	BopsGoods selectByPrimaryKey(String goodsId);

	void updateOnlineOrOffline(BopsGoods bopsGoods);

	BopsGoods selectByGoodsId(String goodsId);

	int updateByPrimaryKeySelective(BopsGoods bopsGoods);

	void updateAuditGoods(AuditGoodsVO bopsGoods);

	int updatePriceById(@Param("price") Integer price, @Param("goodsId") String goodsId,
			@Param("updateTime") Date updateTime);

	int updateById(BopsGoods goods);

	List<GoodsStoreDetailResultVO> queryPageOfBopsGoods(GoodsPageVO goodsPageVO);

	List<GoodsStoreDetailResultVO> queryPageOfBopsGoodsExport(GoodsPageVO goodsPageVO);

	int countBopsGoods();

	int queryCountGoods(GoodsPageVO goodsPageVO);

	int countPageGoods(GoodsPageVO goodsPageVO);

	BopsGoods getByGoodsCode(String goodsCode);

	BopsGoods getByGoodsBarcode(String goodsBarcode);

	int updateGoodsPrice(@Param("goodsId") String goodsId, @Param("discountPrice") Integer discountPrice);

	GoodsStoreDetailResultVO getByParames(String goodsCode);

	/**
	 * 
	 * @author LXD 2015年11月5日 下午6:52:14 @Method: getByGoodsCodes @Description:
	 * TODO 根据goodsCode查询商品列表 @param goodsCodes @return @throws
	 */
	List<GoodsStoreDetailResultVO> getByGoodsCodes(@Param("goodsCodes") List<String> goodsCodes);

	/**
	 * 
	 * @author peiboli 2015年11月30日 下午6:42:31 @Method:
	 * getGoodsDetail @Description: TODO根据goodsCode获得商品详情 @param
	 * goodsCode @return @throws
	 */
	BopsGoods getGoodsDetail(String goodsCode);

	BopsGoods selectByGoodsCode(String goodsCode);

	/**
	 * 查询组装商品的原价和need价
	 * 
	 * @param goodsGroupId
	 * @return
	 * @author zhangmengbin
	 */
	SuitGoodsPriceVO querySuitGoodsPrice(String goodsGroupId);

	BopsGoodsVO selectGoodsDetailByCode(BopsGoods bopsGoods);

	int insertGroupGoods(BopsGoods goods);

	int selectGoodsCode();

	void updateGroupGoods(BopsGoods bopsGoods);

	void updateGoodsStatus(BopsGoods bopsGoods);
	/*
	 * 查询所有的商品
	 */
	List<BopsGoodsVO> queryAllgoods();

	/**
	 * 
	 * @author peiboli 2015年12月11日 下午5:05:27
	 * @Method: updateCategory 
	 * @Description: TODO用于批量导入商品分类
	 * @param categoryId
	 * @param trim
	 * @return 
	 * @throws
	 */
	int updateCategory(@Param("categoryId")String categoryId, @Param("goodsCode")String goodsCode);

}