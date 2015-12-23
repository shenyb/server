package com.need.dao.bops.xinhuan;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.need.domain.po.bops.xinhuan.BopsOpsXinhuanGoods;
import com.need.domain.vo.xinhuan.OpsXinhuanGoodsResultVO;
import com.need.domain.vo.xinhuan.XinhuanGoodsParamPageVO;
import com.need.domain.vo.xinhuan.XinhuanGoodsVO;

public interface BopsOpsXinhuanGoodsDAO {

	/**
	 * @author xiehao 2015年9月11日 下午6:04:48 @Method:
	 * deleteByPrimaryKey @Description: TODO 删除运营位商品 @param id @return @throws
	 */
	int deleteByPrimaryKey(String id);

	int deletByOpsId(String opsId);

	int deleteByGoodsId(String goodsId);

	int insert(BopsOpsXinhuanGoods record);

	int insertSelective(BopsOpsXinhuanGoods record);

	/**
	 * @author xiehao 2015年9月11日 下午4:39:14 @Method:
	 * getByOpsIdAndGoodsId @Description: TODO
	 * 通过运营位ID和商品ID查询该商品是否已经添加到该运营位 @param opsId @param goodsId @return @throws
	 */
	BopsOpsXinhuanGoods getByOpsIdAndGoodsId(@Param("opsId") String opsId, @Param("goodsId") String goodsId);

	/**
	 * @author xiehao 2015年9月11日 下午5:05:04 @Method: countOpsGoods @Description:
	 * TODO 统计某个运营位的商品的数量 @param opsId @return @throws
	 */
	int countOpsGoods(XinhuanGoodsParamPageVO pageVO);

	BopsOpsXinhuanGoods selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(BopsOpsXinhuanGoods record);

	int updateByPrimaryKey(BopsOpsXinhuanGoods record);

	/**
	 * @author xiehao 2015年9月11日 下午4:40:23 @Method:
	 * insertNewXinhuanGoods @Description: TODO 添加运营位商品 @param
	 * xinhuanGoods @return @throws
	 */
	int insertNewXinhuanGoods(BopsOpsXinhuanGoods xinhuanGoods);

	/**
	 * @author xiehao 2015年9月11日 下午6:01:28 @Method:
	 * queryXinhuanGoods @Description: TODO 查询该运营位商品的分页数据 @param
	 * pageVO @return @throws
	 */
	List<OpsXinhuanGoodsResultVO> queryXinhuanGoods(XinhuanGoodsParamPageVO pageVO);
	
	
	List<OpsXinhuanGoodsResultVO>  queryXinhuanGoodsNoPage(String opsId);

	int updateGoods(XinhuanGoodsVO xinhuanGoods);
}