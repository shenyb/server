package com.need.service.bops.ops;

import java.util.List;

import com.need.domain.po.bops.ops.BopsOpsSelected;
import com.need.domain.pub.Page;
import com.need.domain.vo.ops.SelectedGoodsVO;

public interface BopsOpsSelectedService {
	/**
	 * 
	 * @author LXD 2015年8月5日 下午7:36:28
	 * @Method: addBopsOpsSelected 
	 * @Description: TODO 添加精选商品
	 * @param bopsOpsSelected 
	 * @throws
	 */
	void addBopsOpsSelected(BopsOpsSelected bopsOpsSelected);
    /**
     * 
     * @author LXD 2015年8月5日 下午7:36:31
     * @Method: getSelectedById 
     * @Description: TODO 根据ID获取精选商品
     * @param bopsOpsSelectedId
     * @return 
     * @throws
     */
	BopsOpsSelected getSelectedById(String  bopsOpsSelectedId);
	/**
	 * 
	 * @author LXD 2015年8月5日 下午7:36:35
	 * @Method: updateBopsOpsSelected 
	 * @Description: TODO 更新精选商品
	 * @param bopsOpsSelected 
	 * @throws
	 */
	void updateBopsOpsSelected(BopsOpsSelected bopsOpsSelected);
	
	/**
	 * 
	 * @author LXD 2015年8月5日 下午7:36:38
	 * @Method: deleteBopsOpsSelected 
	 * @Description: TODO 根据ID 删除精选商品
	 * @param bopsOpsSelectedId 
	 * @throws
	 */
	void deleteBopsOpsSelected(String  bopsOpsSelectedId);
	
	/**
	 * 
	 * @author LXD 2015年8月5日 下午7:38:19
	 * @Method: getPageOfBopsOps 
	 * @Description: TODO 获取精选的分页数据
	 * @param page
	 * @return 
	 * @throws
	 */
	List<BopsOpsSelected> getPageOfBopsOps(Page page);
	
	List<BopsOpsSelected> getAll();
	List<BopsOpsSelected> searchSelectedGoods(SelectedGoodsVO selectedGoodsVO);
	
	/**
	 * 
	 * @author LXD 2015年8月9日 下午11:36:58
	 * @Method: insertPortalhotGoods 
	 * @Description: TODO 将新增的精选商品同步到前端
	 * @param list 
	 * @throws
	 */
	public void insertPortalselectedGoods(List<BopsOpsSelected> list);
	/**
	 * 
	 * @author LXD 2015年8月10日 上午12:29:56
	 * @Method: updatePortalHotGoods 
	 * @Description: TODO 更新操作同步前端数据库
	 * @param hotgoods 
	 * @throws
	 */
	void updatePortalselectedGoods(BopsOpsSelected selectedgoods);
	
	/**
	 * 
	 * @author LXD 2015年8月10日 上午12:46:07
	 * @Method: deletePortalHotGoods 
	 * @Description: TODO 后端删除数据，同步到前端数据
	 * @param popularityId 
	 * @throws
	 */
	void deletePortalSelectedGoods(String Id);
	BopsOpsSelected checkIsAdd(String string);
	
	/**
	 * 
	 * @author LXD 2015年8月15日 下午4:06:35
	 * @Method: addSelectedGoods 
	 * @Description: TODO
	 * @param list 
	 * @throws
	 */
	
	void addSelectedGoods(List<BopsOpsSelected> list);
	void editSelectedGoods(BopsOpsSelected selectedgoods);
	
	public void deleteBopsOpsSelectedByGoodsId(String goodsId);
	 
	 

}
