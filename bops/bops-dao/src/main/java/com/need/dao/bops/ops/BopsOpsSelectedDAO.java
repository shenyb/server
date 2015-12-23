package com.need.dao.bops.ops;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.need.domain.po.bops.ops.BopsOpsSelected;
import com.need.domain.pub.Page;
import com.need.domain.vo.ops.SelectedGoodsVO;
/**
 * 
 * <p></p>
 * @author LXD 2015年8月5日 下午12:03:43
 * @ClassName BopsOpsSelectedDAO
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年8月5日
 * @modify by reason:{方法名}:{原因}
 */
public interface BopsOpsSelectedDAO {
	
    int deleteById(String selectionId);
   
    int insert(BopsOpsSelected bopsOpsSelected);
    
    BopsOpsSelected getById(String selectionId);  
    
    int update(BopsOpsSelected bopsOpsSelected);
    
   
	int getCount();
    
    
    
	List<BopsOpsSelected> getAll();
    
	
    List<BopsOpsSelected> queryByParams(SelectedGoodsVO selectedGoodsVO);

	int getCountByParams(SelectedGoodsVO selectedGoodsVO);

	BopsOpsSelected getSelectedGoods(String goodsId);

	List<BopsOpsSelected> queryByPage(Page page);

	List<BopsOpsSelected> queryBycheck();

	int queryMaxSort();

	void updateSort(Integer goodsSort);

	void updategoodsSortDrop(@Param("currentSort")Integer currentSort, @Param("newSort")Integer newSort);

	void updategoodsSortRise(@Param("currentSort")Integer currentSort, @Param("newSort")Integer newSort);
}