package com.need.dao.bops.ops;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.need.domain.po.bops.ops.BopsOpsHotgoods;
import com.need.domain.pub.Page;
import com.need.domain.vo.ops.HotGoodsVO;

/**
 * 
 * <p></p>
 * @author LXD 2015年8月5日 下午12:03:31
 * @ClassName BopsOpsHotgoodsDAO
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年8月5日
 * @modify by reason:{方法名}:{原因}
 */
public interface BopsOpsHotgoodsDAO {
	
    int deleteById(String popularityId);
    
	
    int insert(BopsOpsHotgoods bopsOpsHotgoods);

   
    BopsOpsHotgoods getById(String popularityId);

    
    int update(BopsOpsHotgoods bopsOpsHotgoods);
    
   
	int getCount();
    
   
    
   
	List<BopsOpsHotgoods> getAll();
    
	
	List<BopsOpsHotgoods> queryByParams(HotGoodsVO searchHotGoods);


	int getCountByParams(HotGoodsVO hotGoodsVO);


	BopsOpsHotgoods getBygoodsId(String goodsId);


	List<BopsOpsHotgoods> queryByPage(Page page);


	int queryMaxSort();


	List<BopsOpsHotgoods> queryBycheck();
	
	void updateSort(int currrentSort);


	void updategoodsSortDrop(@Param("currentSort")Integer currentSort, @Param("newSort")Integer newSort);


	void updategoodsSortRise(@Param("currentSort")Integer currentSort, @Param("newSort")Integer newSort);
    
    
    
}