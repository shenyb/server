package com.need.dao.api.xinhuan;

import com.need.domain.po.api.xinhuan.OpsXinhuanGoods;
import com.need.domain.po.bops.xinhuan.BopsOpsXinhuanGoods;
import com.need.domain.vo.xinhuan.XinhuanGoodsVO;

public interface OpsXinhuanGoodsDAO {

	/**
	 * @author xiehao 2015年9月11日 下午6:04:48 @Method:
	 *         deleteByPrimaryKey @Description: TODO 删除运营位商品 @param
	 *         id @return @throws
	 */
	int deleteByPrimaryKey(String id);

	int deletByOpsId(String opsId);

	int deleteByGoodsId(String goodsId);

	int insert(OpsXinhuanGoods record);

	int insertSelective(OpsXinhuanGoods record);

	OpsXinhuanGoods selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(OpsXinhuanGoods record);

	int updateByPrimaryKey(OpsXinhuanGoods record);

	/**
	 * @author xiehao 2015年9月11日 下午4:40:46 @Method:
	 *         insertNewXinhuanGoods @Description: TODO 添加运营位商品 @param
	 *         goods @return @throws
	 */
	int insertNewXinhuanGoods(BopsOpsXinhuanGoods goods);

	int updateGoods(XinhuanGoodsVO xinhuanGoods);
}