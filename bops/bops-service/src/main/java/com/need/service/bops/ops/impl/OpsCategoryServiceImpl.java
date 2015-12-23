package com.need.service.bops.ops.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.need.dao.api.ops.GoodsIndexCategoryDAO;
import com.need.dao.bops.ops.BopsIndexCategoryDAO;
import com.need.dao.bops.user.BopsUserDAO;
import com.need.domain.po.api.goods.GoodsCategoriesPO;
import com.need.domain.po.api.ops.GoodsIndexCategoryPO;
import com.need.domain.po.bops.ops.BopsIndexCategoryPO;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.vo.ops.BopsIndexCategoryParam;
import com.need.service.bops.ops.OpsCategoryService;
import com.need.service.constant.Constants;

import scala.runtime.TraitSetter;

@Service
public class OpsCategoryServiceImpl implements OpsCategoryService {

	@Autowired
	private BopsIndexCategoryDAO bopsIndexCategoryDAO;
	@Autowired
	private GoodsIndexCategoryDAO goodsIndexCategoryDAO;
	
	@Autowired
	private BopsUserDAO bopsUserDAO;
	
	@Transactional
	@Override
	public void insertNewCategory(BopsIndexCategoryParam categoriesVO) {
		/** TODO Auto-generated method stub*/
		BopsIndexCategoryPO bopsIndexCategoryPO =new BopsIndexCategoryPO();
		GoodsIndexCategoryPO goodsCategoriesPO=new GoodsIndexCategoryPO();
		//插入后台表
		BeanUtils.copyProperties(categoriesVO, bopsIndexCategoryPO);
		bopsIndexCategoryDAO.insert(bopsIndexCategoryPO);
		BeanUtils.copyProperties(bopsIndexCategoryPO, goodsCategoriesPO);
		goodsCategoriesPO.setCategoryId(bopsIndexCategoryPO.getCategoryId());
		//前台表
		goodsIndexCategoryDAO.insert(goodsCategoriesPO);
		
	}

	@Override
	public BopsIndexCategoryParam getBycategoryId(String categoryId) {
		BopsIndexCategoryPO bopsIndexCategoryPO=bopsIndexCategoryDAO.selectByPrimaryKey(Integer.parseInt(categoryId));
		BopsIndexCategoryParam bopsIndexCategoryParam=new BopsIndexCategoryParam();
		BeanUtils.copyProperties(bopsIndexCategoryPO, bopsIndexCategoryParam);
		if(0 == bopsIndexCategoryPO.getCreateId()){
			bopsIndexCategoryParam.setCreateUserName(Constants.SUPERADMIN);
		}else{
			BopsUser BopsUser=bopsUserDAO.selectByPrimaryKey(bopsIndexCategoryPO.getCreateId());
			bopsIndexCategoryParam.setCreateUserName(BopsUser.getUserRealName());
		}
		//查询修改人
		if(null != bopsIndexCategoryPO.getUpdateId()){
			if(0 == bopsIndexCategoryPO.getUpdateId()){
				bopsIndexCategoryParam.setUpdateUserName(Constants.SUPERADMIN);
			}else{
				BopsUser updateBopsUser=bopsUserDAO.selectByPrimaryKey(bopsIndexCategoryPO.getUpdateId());
				bopsIndexCategoryParam.setUpdateUserName(updateBopsUser.getUserRealName());
			}
		}
		return bopsIndexCategoryParam;
	}

	@Override
	public BopsIndexCategoryParam toUpdateCategory(String categoryId) {
		BopsIndexCategoryPO bopsIndexCategoryPO=bopsIndexCategoryDAO.selectByPrimaryKey(Integer.parseInt(categoryId));
		BopsIndexCategoryParam bopsIndexCategoryParam=new BopsIndexCategoryParam();
		BeanUtils.copyProperties(bopsIndexCategoryPO, bopsIndexCategoryParam);
		return bopsIndexCategoryParam;
	}
    
	@Transactional
	@Override
	public void updateCategory(BopsIndexCategoryParam categoriesVO) {
		bopsIndexCategoryDAO.update(categoriesVO);
		goodsIndexCategoryDAO.update(categoriesVO);
		
	}
    
	@Transactional("bops_txManager")
	@Override
	public Message removecategory(Integer categoryId, String level) {
		//删除父级，则还要删除的它的子级
		if("1".equals(level)){
			bopsIndexCategoryDAO.deleteByPrimaryKey(categoryId);
			bopsIndexCategoryDAO.deleteByParentId(categoryId+"");
			goodsIndexCategoryDAO.deleteByPrimaryKey(categoryId);
			goodsIndexCategoryDAO.deleteByParentId(categoryId+"");
			
		}else{
			bopsIndexCategoryDAO.deleteByPrimaryKey(categoryId);
			goodsIndexCategoryDAO.deleteByPrimaryKey(categoryId);
			
		}
		return Message.success();
	}
	
}
