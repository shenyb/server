package com.need.service.bops.goods.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.need.dao.api.goods.GoodsBrandDAO;
import com.need.dao.bops.goods.BopsGoodsBrandDAO;
import com.need.dao.bops.user.BopsUserDAO;
import com.need.domain.po.api.goods.GoodsBrandPO;
import com.need.domain.po.bops.goods.BopsGoodsBrandPO;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.vo.goods.BrandPageVO;
import com.need.service.bops.goods.BopsGoodsBrandService;
import com.need.service.constant.BizReturnCode;
@Service
public class BopsGoodsBrandServiceImpl implements BopsGoodsBrandService {

	@Autowired
	private BopsGoodsBrandDAO bopsGoodsBrandDAO;
	@Autowired
	private GoodsBrandDAO goodsBrandDAO;
	@Autowired
	private BopsUserDAO bopsUserDAO;
	/**
	 * 
	 * @author peiboli 2015年11月18日 下午6:10:20	
	 * @Method: getBrandList 
	 * @Description: TODO getBrandList
	 * @param params
	 * @return 
	 * @see com.need.service.bops.goods.BopsGoodsBrandService#getBrandList(com.need.domain.vo.goods.BrandPageVO)
	 */
	@Override
	public List<BrandPageVO> getBrandList(BrandPageVO params) {
		// TODO Auto-generated method stub
		params.setTotal(bopsGoodsBrandDAO.count(params));
		List<BrandPageVO> list =bopsGoodsBrandDAO.queryByPage(params);
		for(BrandPageVO brand:list){
			if(brand.getUpdateId()!=null){
				BopsUser user= bopsUserDAO.selectByPrimaryKey(brand.getUpdateId());
				if(user!=null){
				brand.setUpdateName(user.getUserRealName());
				}
			}
		}
		return list;
	}
	/**
	 * 
	 * @author peiboli 2015年11月18日 下午6:10:45
	 * @Method: save 
	 * @Description: TODO
	 * @param bopsGoodsBrandPO
	 * @return 
	 * @see com.need.service.bops.goods.BopsGoodsBrandService#save(com.need.domain.po.bops.goods.BopsGoodsBrandPO)
	 */
	@Override
	public int save(BopsGoodsBrandPO bopsGoodsBrandPO) {
		int success= bopsGoodsBrandDAO.addBrand(bopsGoodsBrandPO);
		if(success==1){
			GoodsBrandPO brand = new GoodsBrandPO();
			 BeanUtils.copyProperties(bopsGoodsBrandPO,brand );
			goodsBrandDAO.insert(brand);	
		}
		return success;
	}
	/**
	 * 
	 * @author peiboli 2015年11月18日 下午6:10:52
	 * @Method: getBrandByBrandName 
	 * @Description: TODO
	 * @param brandName
	 * @return 
	 * @see com.need.service.bops.goods.BopsGoodsBrandService#getBrandByBrandName(java.lang.String)
	 */
	@Override
	public Boolean getBrandByBrandName(String brandName) {
		// TODO Auto-generated method stub		
		if(bopsGoodsBrandDAO.getBrandByBrandName(brandName)!=null){
			return Boolean.TRUE;
		}else{
			return Boolean.FALSE;
		}
		
	}
	@Transactional
	@Override
	public int update(BopsGoodsBrandPO bopsGoodsBrandPO) {
		// TODO Auto-generated method stub
		int isSuccess= bopsGoodsBrandDAO.updateByPrimaryKey(bopsGoodsBrandPO);
		if(isSuccess==1){
			GoodsBrandPO brand = new GoodsBrandPO();
		    BeanUtils.copyProperties(bopsGoodsBrandPO,brand );
			goodsBrandDAO.updateByPrimaryKey(brand);
			return isSuccess;
		}else{
			return BizReturnCode.SYSTEM_OPERATE_ERR;
		}
		
	}

}
