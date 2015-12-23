package com.need.dao.bops.goods;

import java.util.List;

import com.need.domain.po.api.goods.GoodsBrandPO;
import com.need.domain.po.bops.goods.BopsGoodsBrandPO;
import com.need.domain.vo.goods.BrandPageVO;

public interface BopsGoodsBrandDAO {
    int deleteByPrimaryKey(Integer brandId);

    int insert(BopsGoodsBrandPO record);

    int insertSelective(BopsGoodsBrandPO record);

    BopsGoodsBrandPO selectByPrimaryKey(Integer brandId);

    int updateByPrimaryKeySelective(BopsGoodsBrandPO record);

    int updateByPrimaryKey(BopsGoodsBrandPO record);

    /**
     * 
     * @author peiboli 2015年11月18日 下午2:06:59
     * @Method: count 
     * @Description: TODO获得品牌总数，用于分页
     * @param params
     * @return 
     * @throws
     */
	Integer count(BrandPageVO params);

	/**
	 * 
	 * @author peiboli 2015年11月18日 下午2:07:27
	 * @Method: queryByPage 
	 * @Description: TODO分页查询，获得品牌列表
	 * @param params
	 * @return 
	 * @throws
	 */
	List<BrandPageVO> queryByPage(BrandPageVO params);

	/**
	 * 
	 * @author peiboli 2015年11月18日 下午4:39:26
	 * @Method: add 
	 * @Description: TODO添加品牌
	 * @param bopsGoodsBrandPO
	 * @return 
	 * @throws
	 */
	int addBrand(BopsGoodsBrandPO bopsGoodsBrandPO);

	/**
	 * 
	 * @author peiboli 2015年11月18日 下午4:53:25
	 * @Method: getBrandByBrandName 
	 * @Description: TODO通过Name获得brand，用于检验是否已经存在
	 * @param brandName
	 * @return 
	 * @throws
	 */
	BopsGoodsBrandPO getBrandByBrandName(String brandName);

	List<BrandPageVO> queryall();
}