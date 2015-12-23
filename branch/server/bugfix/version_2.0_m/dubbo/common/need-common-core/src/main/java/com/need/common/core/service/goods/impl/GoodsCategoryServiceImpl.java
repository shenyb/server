package com.need.common.core.service.goods.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.need.common.core.dao.jdbc.goods.GoodsIndexCategoryDAO;
import com.need.common.core.dao.jdbc.goods.GoodsMainDAO;
import com.need.common.core.service.goods.GoodsCategoryService;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.goods.CategoryVO;
import com.need.common.domain.vo.goods.GoodsCategoryVO;
import com.need.common.domain.vo.goods.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

@Autowired
private GoodsIndexCategoryDAO goodsIndexCategoryDAO;
@Autowired
private GoodsMainDAO GoodsMainDAO;
	/***
	 * 
	 * @author LXD 2015年11月30日 下午2:32:49
	 * @Method: getAllCategory 
	 * @Description: TODO 获取分类集合
	 * @return 
	 * @see com.need.api.service.goods.GoodsCategoryService#getAllCategory()
	 */
	@Override
	public Message getAllCategory() {
		Message success =Message.success();
		//获取所有的一级分类
		List<CategoryVO> categoryList= goodsIndexCategoryDAO.getLevelOne("1");
		for(CategoryVO category:categoryList){
			//根据父类ID查子集合
			List<GoodsCategoryVO> sonList= goodsIndexCategoryDAO.getSonCategory(category.getCategoryId());
			category.setCategoryList(sonList);
			
		}
		success.addData("categoryList", categoryList);
		return success;
	}
    /**
     * 
     * @author LXD 2015年11月30日 下午2:33:09
     * @Method: getGoodsListByCategory 
     * @Description: TODO 获取某一分类下的商品集合
     * @param categoryId
     * @param pageNum
     * @param pageSize
     * @return 
     * @see com.need.api.service.goods.GoodsCategoryService#getGoodsListByCategory(java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
	@Override
	public Message getGoodsListByCategory(Integer categoryId, Integer pageNum, Integer pageSize) {
		Message message=Message.success();
		PageHelper.startPage(pageNum, pageSize);//用分页工具对结果进行分页
		Page<GoodsVO> page = (Page<GoodsVO>) GoodsMainDAO.getByCategory(categoryId);
		message.addData("goodsList", page.getResult());
		message.addData("totalCount", page.getTotal());
		return message;
	}
	
}
