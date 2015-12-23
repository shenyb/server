package com.need.common.core.dao.jdbc.ops;

import com.need.common.domain.po.api.ops.OpsXinhuanGoodsPO;
import com.need.common.domain.vo.ops.GoodsVO;

import java.util.List;


public interface OpsXinhuanGoodsDAO {
	int deleteByPrimaryKey(String id);

	int insert(OpsXinhuanGoodsPO record);

	int insertSelective(OpsXinhuanGoodsPO record);

	OpsXinhuanGoodsPO selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(OpsXinhuanGoodsPO record);

	int updateByPrimaryKey(OpsXinhuanGoodsPO record);

	/**
	 * @author xiehao 2015年9月11日 下午6:14:16
	 * @Method: queryXinhuanGoods
	 * @Description: TODO 获取商品属性
	 * @param opsId
	 */
	List<GoodsVO> queryXinhuanGoods(String opsId);
    /***
     * 
     * @author LXD 2015年10月22日 下午3:29:28
     * @Method: queryXinhuanGoods_V1_3  1.3 运营位商品list
     * @Description: TODO
     * @param opsId
     * @return 
     * @throws 
     */
	List<GoodsVO> queryXinhuanGoods_V1_3(String opsId);
	
	
	List<GoodsVO> queryPrefectureGoods_V1_3();
	
}