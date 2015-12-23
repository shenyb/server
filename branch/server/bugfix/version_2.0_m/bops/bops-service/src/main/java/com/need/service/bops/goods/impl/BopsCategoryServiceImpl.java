package com.need.service.bops.goods.impl;


import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.need.dao.api.goods.GoodsCategoriesDAO;
import com.need.dao.bops.goods.BopsGoodsCategoriesDAO;
import com.need.dao.bops.user.BopsUserDAO;
import com.need.domain.po.api.goods.GoodsCategoriesPO;
import com.need.domain.po.bops.goods.BopsGoodsCategoriesPO;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.vo.goods.BopsGoodsCategoriesVO;
import com.need.service.bops.goods.BopsCategoryService;
import com.need.service.constant.Constants;

@Service
public class BopsCategoryServiceImpl implements BopsCategoryService {

	@Autowired
	private BopsGoodsCategoriesDAO bopsGoodsCategoriesDAO;
	@Autowired
	private BopsUserDAO bopsUserDAO;
	@Autowired
	private GoodsCategoriesDAO goodsCategoriesDAO;

	@Override
	@Transactional
	public BopsGoodsCategoriesVO insertNewCategory(BopsGoodsCategoriesVO bopsGoodsCategoriesVO) {
		GoodsCategoriesPO goodsCategoriesPO=new GoodsCategoriesPO();
		bopsGoodsCategoriesVO.setCategoryStatus("VALID");
		//插入后台表
		bopsGoodsCategoriesDAO.insert(bopsGoodsCategoriesVO);
		BeanUtils.copyProperties(bopsGoodsCategoriesVO, goodsCategoriesPO);
		//前台表
		goodsCategoriesDAO.insert(goodsCategoriesPO);
		return bopsGoodsCategoriesVO;
	}

	@Override
	public BopsGoodsCategoriesVO getCategoryDetail(String categoryId) {
		BopsGoodsCategoriesVO bopsGoodsCategoriesVO = new BopsGoodsCategoriesVO();
		BopsGoodsCategoriesPO bopsGoodsCategoriesPO = bopsGoodsCategoriesDAO.selectByPrimaryKey(Integer.parseInt(categoryId));
		BeanUtils.copyProperties(bopsGoodsCategoriesPO, bopsGoodsCategoriesVO);
		/*//判断为几级分类
		if("2".equals(bopsGoodsCategoriesPO.getCategoryLevel())){
			BopsGoodsCategoriesPO categoriesOnePO = bopsGoodsCategoriesDAO.selectByPrimaryKey(bopsGoodsCategoriesPO.getParentId());
			bopsGoodsCategoriesVO.setLevelOne(categoriesOnePO.getCategoryName());
			bopsGoodsCategoriesVO.setLevelTwo(bopsGoodsCategoriesPO.getCategoryName());
		}else if("3".equals(bopsGoodsCategoriesPO.getCategoryLevel())){
			//根据三级分类查询二级分类名称
			BopsGoodsCategoriesPO categoriesTwoPO = bopsGoodsCategoriesDAO.selectByPrimaryKey(bopsGoodsCategoriesPO.getParentId());
			//根据二级分类查询一级分类名称
			BopsGoodsCategoriesPO categoriesOnePO = bopsGoodsCategoriesDAO.selectByPrimaryKey(categoriesTwoPO.getParentId());
			bopsGoodsCategoriesVO.setLevelOne(categoriesOnePO.getCategoryName());
			bopsGoodsCategoriesVO.setLevelTwo(categoriesTwoPO.getCategoryName());
			bopsGoodsCategoriesVO.setLevelThree(bopsGoodsCategoriesPO.getCategoryName());
		}else{
			bopsGoodsCategoriesVO.setLevelOne(bopsGoodsCategoriesPO.getCategoryName());
		}*/
		//查询新增人
		if(0 == bopsGoodsCategoriesPO.getCreateId()){
			bopsGoodsCategoriesVO.setCreateUserName(Constants.SUPERADMIN);
		}else{
			BopsUser BopsUser=bopsUserDAO.selectByPrimaryKey(bopsGoodsCategoriesPO.getCreateId());
			bopsGoodsCategoriesVO.setCreateUserName(BopsUser.getUserRealName());
		}
		//查询修改人
		if(null != bopsGoodsCategoriesPO.getUpdateId()){
			if(0 == bopsGoodsCategoriesPO.getUpdateId()){
				bopsGoodsCategoriesVO.setUpdateUserName(Constants.SUPERADMIN);
			}else{
				BopsUser updateBopsUser=bopsUserDAO.selectByPrimaryKey(bopsGoodsCategoriesPO.getUpdateId());
				bopsGoodsCategoriesVO.setUpdateUserName(updateBopsUser.getUserRealName());
			}
		}
		
		return bopsGoodsCategoriesVO;
	}
	
	@Override
	public BopsGoodsCategoriesVO toUpdateCategory(String categoryId) {
		BopsGoodsCategoriesPO bopsGoodsCategoriesPO=bopsGoodsCategoriesDAO.selectByPrimaryKey(Integer.parseInt(categoryId));
		BopsGoodsCategoriesVO bopsGoodsCategoriesVO = new BopsGoodsCategoriesVO();
		BeanUtils.copyProperties(bopsGoodsCategoriesPO, bopsGoodsCategoriesVO);
		return bopsGoodsCategoriesVO;
	}

	@Override
	@Transactional
	public BopsGoodsCategoriesVO updateCategory(BopsGoodsCategoriesVO bopsGoodsCategoriesVO) {
		//更新后台表
		bopsGoodsCategoriesDAO.updateCategory(bopsGoodsCategoriesVO);
		//更新前台表
		goodsCategoriesDAO.updateCategory(bopsGoodsCategoriesVO);
		return bopsGoodsCategoriesVO;
	}

	@Override
	public List<BopsGoodsCategoriesVO> selectCategoryList(BopsGoodsCategoriesVO bopsCategoriesVO) {
		List<BopsGoodsCategoriesVO> cateogryList=bopsGoodsCategoriesDAO.selectCategoryList(bopsCategoriesVO);
		for (BopsGoodsCategoriesVO bopsGoodsCategoriesVO : cateogryList) {
			//查询新增人
			if(0 == bopsGoodsCategoriesVO.getCreateId()){
				bopsGoodsCategoriesVO.setCreateUserName(Constants.SUPERADMIN);
			}else{
				BopsUser BopsUser=bopsUserDAO.selectByPrimaryKey(bopsGoodsCategoriesVO.getCreateId());
				bopsGoodsCategoriesVO.setCreateUserName(BopsUser.getUserRealName());
			}
			//查询修改人
			if(null != bopsGoodsCategoriesVO.getUpdateId()){
				if(0 == bopsGoodsCategoriesVO.getUpdateId()){
					bopsGoodsCategoriesVO.setUpdateUserName(Constants.SUPERADMIN);
				}else{
					BopsUser updateBopsUser=bopsUserDAO.selectByPrimaryKey(bopsGoodsCategoriesVO.getUpdateId());
					bopsGoodsCategoriesVO.setUpdateUserName(updateBopsUser.getUserRealName());
				}
			}
		}
		return cateogryList;
	}


	
	

}
