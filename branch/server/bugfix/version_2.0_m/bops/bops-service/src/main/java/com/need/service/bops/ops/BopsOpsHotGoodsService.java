package com.need.service.bops.ops;

import java.util.List;

import com.need.domain.po.bops.ops.BopsOpsHotgoods;
import com.need.domain.pub.Page;
import com.need.domain.vo.ops.HotGoodsVO;

/**
 * 
 * <p></p>
 * @author LXD 2015年8月5日 下午6:06:11
 * @ClassName BopsOpsHotGoodsService
 * @Description TODO 人气商品服务层
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年8月5日
 * @modify by reason:{方法名}:{原因}
 */
public interface BopsOpsHotGoodsService {
    /**
     * 
     * @author LXD 2015年8月5日 下午6:15:14
     * @Method: addBopsOpsHotGoods 
     * @Description: TODO 添加一个人气商品
     * @param hotGoods 
     * @throws
     */
	void addBopsOpsHotGoods(List<BopsOpsHotgoods> list);
    /**
     * 
     * @author LXD 2015年8月5日 下午6:15:41
     * @Method: getHotgoodsById 
     * @Description: TODO 根据ID获取一个人气商品
     * @param opsHotgoodsId
     * @return 
     * @throws
     */
	BopsOpsHotgoods getHotgoodsById(String  opsHotgoodsId);
	/**
	 * 
	 * @author LXD 2015年8月5日 下午6:16:12
	 * @Method: updateOpsHotgoods 
	 * @Description: TODO 更新人气商品
	 * @param hotGoods 
	 * @throws
	 */
	void editOpsHotgoods(BopsOpsHotgoods hotGoods);
	
	/**
	 * 
	 * @author LXD 2015年8月5日 下午6:16:37
	 * @Method: deletehotGoodsById 
	 * @Description: TODO 根据ID删除人气商品
	 * @param opsHotgoodsId 
	 * @throws
	 */
	void deletehotGoodsById(String  opsHotgoodsId);
	
	
	/**
	 * 
	 * @author LXD 2015年8月5日 下午6:17:04
	 * @Method: getPageOfBopsOps 
	 * @Description: TODO 获取人气商品的分页数据
	 * @param page
	 * @return 
	 * @throws
	 */
	List<BopsOpsHotgoods> getPageOfBopsOps(Page page);

	List<BopsOpsHotgoods> getallHotbops();
	
	/**
	 * 
	 * @author LXD 2015年8月6日 下午2:23:13
	 * @Method: getallHotbops 
	 * @Description: TODO 搜索人气商品
	 * @return 
	 * @throws
	 */
	List<BopsOpsHotgoods> searchHotGoods(HotGoodsVO hotGoodsVO);

	
	
	BopsOpsHotgoods checkIsAdd(String string);
	
	public void deletehotGoodsByGoodsId(String opsHotgoodsId);

}
