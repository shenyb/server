package com.need.service.bops.goods;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.need.domain.po.bops.goods.BopsGoodsBrandPO;
import com.need.domain.vo.goods.BrandPageVO;
import com.need.domain.vo.trade.TradeOrderParamsVO;

public interface BopsGoodsBrandService {

	/**
	 * 
	 * @author peiboli 2015年11月18日 下午6:08:53
	 * @Method: getBrandList 
	 * @Description: TODO获得品牌列表
	 * @param params
	 * @return 
	 * @throws
	 */
	List<BrandPageVO> getBrandList(BrandPageVO params);

	/**
	 * 
	 * @author peiboli 2015年11月18日 下午6:09:14
	 * @Method: save 
	 * @Description: TODO新增品牌
	 * @param bopsGoodsBrandPO
	 * @return 
	 * @throws
	 */
	int save(BopsGoodsBrandPO bopsGoodsBrandPO);

	/**
	 * 
	 * @author peiboli 2015年11月18日 下午6:09:30
	 * @Method: getBrandByBrandName 
	 * @Description: TODO根据name判断是否存在
	 * @param brandName
	 * @return 
	 * @throws
	 */
	Boolean getBrandByBrandName(String brandName);

	int update(BopsGoodsBrandPO bopsGoodsBrandPO);
	
	HSSFWorkbook exportBrand(BopsGoodsBrandPO bopsGoodsBrandPO);

}
