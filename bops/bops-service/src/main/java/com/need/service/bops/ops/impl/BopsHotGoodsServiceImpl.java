package com.need.service.bops.ops.impl;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.need.dao.api.ops.OpsHotGooodsDAO;
import com.need.dao.bops.ops.BopsOpsHotgoodsDAO;
import com.need.domain.po.api.ops.OpsHotGooodsPO;
import com.need.domain.po.bops.ops.BopsOpsHotgoods;
import com.need.domain.pub.Page;
import com.need.domain.vo.ops.HotGoodsVO;
import com.need.service.bops.ops.BopsOpsHotGoodsService;
@Service
public class BopsHotGoodsServiceImpl implements BopsOpsHotGoodsService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private BopsOpsHotgoodsDAO bopsOpsHotgoodsDAO;
	
	@Autowired
	private OpsHotGooodsDAO opsHotGooodsDAO;
	/**
	 * 
	 * @author LXD 2015年8月19日 下午2:59:29
	 * @Method: getHotgoodsById 
	 * @Description: TODO 查看单个人气
	 * @param opsHotgoodsId
	 * @return 
	 * @see com.need.service.bops.ops.BopsOpsHotGoodsService#getHotgoodsById(java.lang.String)
	 */
	@Override
	public BopsOpsHotgoods getHotgoodsById(String opsHotgoodsId) {
		return bopsOpsHotgoodsDAO.getById(opsHotgoodsId);
	}

   /**
    * 
    * @author LXD 2015年8月15日 下午2:32:14
    * @Method: deletehotGoodsById 
    * @Description: TODO 删除人气商品，同时删除前端库
    * @param opsHotgoodsId 
    * @see com.need.service.bops.ops.BopsOpsHotGoodsService#deletehotGoodsById(java.lang.String)
    */
	@Override
	@Transactional
	public void deletehotGoodsById(String opsHotgoodsId) {
		BopsOpsHotgoods hotGoods= bopsOpsHotgoodsDAO.getById(opsHotgoodsId);
		if(hotGoods!=null){
		bopsOpsHotgoodsDAO.updateSort(hotGoods.getGoodsSort());
		bopsOpsHotgoodsDAO.deleteById(opsHotgoodsId);
		deletePortalHotGoods(opsHotgoodsId);
		}
	
	}
   /**
    * 
    * @author LXD 2015年8月19日 下午2:59:56
    * @Method: getPageOfBopsOps 
    * @Description: TODO 人气分页
    * @param page
    * @return 
    * @see com.need.service.bops.ops.BopsOpsHotGoodsService#getPageOfBopsOps(com.need.bops.pub.Page)
    */
	@Override
	public List<BopsOpsHotgoods> getPageOfBopsOps(Page page) {
		page.setTotal(bopsOpsHotgoodsDAO.getCount());
		return bopsOpsHotgoodsDAO.queryByPage(page);
	}
    /**
     * 
     * @author LXD 2015年8月19日 下午3:00:10
     * @Method: getallHotbops 
     * @Description: TODO 所有人气
     * @return 
     * @see com.need.service.bops.ops.BopsOpsHotGoodsService#getallHotbops()
     */
	@Override
	public List<BopsOpsHotgoods> getallHotbops() {
		return bopsOpsHotgoodsDAO.getAll();
	}
     /**
      * 
      * @author LXD 2015年8月16日 上午11:49:26
      * @Method: searchHotGoods 
      * @Description: TODO 搜索人气商品
      * @param hotGoodsVO
      * @return 
      * @see com.need.service.bops.ops.BopsOpsHotGoodsService#searchHotGoods(com.need.bops.web.controller.vo.ops.HotGoodsVO)
      */
	@Override
	public List<BopsOpsHotgoods> searchHotGoods(HotGoodsVO hotGoodsVO) {
		hotGoodsVO.setTotal(bopsOpsHotgoodsDAO.getCountByParams(hotGoodsVO));
		return bopsOpsHotgoodsDAO.queryByParams(hotGoodsVO);
	}
    /**
     * 
     * @author LXD 2015年8月16日 上午11:45:20
     * @Method: insertPortalhotGoods 
     * @Description: TODO 保存到前端库
     * @param list 
     * @throws
     */
	public void insertPortalhotGoods(List<BopsOpsHotgoods> list) {
		/** TODO Auto-generated method stub*/
		for(BopsOpsHotgoods hotGoods:list){
			OpsHotGooodsPO opsHotGoods=new OpsHotGooodsPO();
				opsHotGoods.setPopularityId(hotGoods.getPopularityId().toString());
				opsHotGoods.setGoodsId(hotGoods.getGoodsId());
				opsHotGoods.setGoodsName(hotGoods.getGoodsName());
				opsHotGoods.setGoodsProfilePicKey(hotGoods.getGoodsProfilePicKey());
				opsHotGoods.setGoodsStatus(hotGoods.getGoodsStatus());
				opsHotGoods.setGoodsSort(hotGoods.getGoodsSort());
				opsHotGooodsDAO.insert(opsHotGoods);	
		}
		
	}
  /**
   * 
   * @author LXD 2015年8月16日 上午11:50:01
   * @Method: deletePortalHotGoods 
   * @Description: TODO 删除前端对象
   * @param popularityId 
   * @throws
   * 
   */
	public void deletePortalHotGoods(String popularityId) {
		OpsHotGooodsPO hotGoods=opsHotGooodsDAO.getById(popularityId);
		opsHotGooodsDAO.updateSort(hotGoods.getGoodsSort());
		opsHotGooodsDAO.deleteById(popularityId);
	}
    /**
     * 
     * @author LXD 2015年8月19日 下午3:00:39
     * @Method: checkIsAdd 
     * @Description: TODO 校验该商品是否已添加人气
     * @param goodsId
     * @return 
     * @see com.need.service.bops.ops.BopsOpsHotGoodsService#checkIsAdd(java.lang.String)
     */
	@Override
	public BopsOpsHotgoods checkIsAdd(String goodsId) {
		/** TODO Auto-generated method stub*/
		return bopsOpsHotgoodsDAO.getBygoodsId(goodsId);
	}
    /**
     * 
     * @author LXD 2015年8月15日 下午1:51:24
     * @Method: addBopsOpsHotGoods 
     * @Description: TODO 添加人气 同步前端
     * @param list 
     * @see com.need.service.bops.ops.BopsOpsHotGoodsService#addBopsOpsHotGoods(java.util.List)
     */
	@Override
	@Transactional
	public void addBopsOpsHotGoods(List<BopsOpsHotgoods> list) {
		logger.info("添加人气");
		List<BopsOpsHotgoods> checkList= bopsOpsHotgoodsDAO.queryBycheck();
		 //初始化序位
		if(checkList.size()==0){
			for(int i=0;i<list.size();i++){
				BopsOpsHotgoods hotgoods= list.get(i);
				hotgoods.setGoodsSort(i+1);
				bopsOpsHotgoodsDAO.insert(hotgoods);
				OpsHotGooodsPO opsHotGooodsPO=convert(hotgoods);
				opsHotGooodsDAO.insert(opsHotGooodsPO);
				
			}	
		}else{
		 //后添加的人气序位累加
		 int maxSort=bopsOpsHotgoodsDAO.queryMaxSort();
		 logger.info("添加人气前的最大序位："+maxSort);
		 for(int i=0;i<list.size();i++){
				BopsOpsHotgoods hotgoods= list.get(i);
				hotgoods.setGoodsSort(maxSort+i+1);
				bopsOpsHotgoodsDAO.insert(hotgoods);
				OpsHotGooodsPO opsHotGooodsPO=convert(hotgoods);
				opsHotGooodsDAO.insert(opsHotGooodsPO);
			}
			
		}
		
	}
	/**
	 * 
	 * @author LXD 2015年8月19日 下午3:08:09
	 * @Method: convert 
	 * @Description: TODO 封装前端对象
	 * @param hotgoods
	 * @return 
	 * @throws
	 */
    private OpsHotGooodsPO convert(BopsOpsHotgoods hotgoods) {
    	OpsHotGooodsPO opsHotGoods=new OpsHotGooodsPO();
    	opsHotGoods.setPopularityId(hotgoods.getPopularityId());
		opsHotGoods.setGoodsId(hotgoods.getGoodsId());
		opsHotGoods.setGoodsName(hotgoods.getGoodsName());
		opsHotGoods.setGoodsProfilePicKey(hotgoods.getGoodsProfilePicKey());
		opsHotGoods.setGoodsStatus(hotgoods.getGoodsStatus());
		opsHotGoods.setGoodsSort(hotgoods.getGoodsSort());
		return opsHotGoods;
	}


	/***
     * 
     * @author LXD 2015年8月15日 下午12:34:53
     * @Method: editOpsHotgoods 
     * @Description: TODO 更新人气序位 
     * @param hotGoods 
     * @see com.need.service.bops.ops.BopsOpsHotGoodsService#editOpsHotgoods(com.need.bops.dao.jdbc.bops.ops.po.BopsOpsHotgoods)
     */
	@Override
	@Transactional
	public void editOpsHotgoods(BopsOpsHotgoods hotGoods) {
		BopsOpsHotgoods opsHottGoods= bopsOpsHotgoodsDAO.getById(hotGoods.getPopularityId());
		if(opsHottGoods.getGoodsSort()<hotGoods.getGoodsSort()){
			//如果序位下降
		bopsOpsHotgoodsDAO.updategoodsSortDrop(opsHottGoods.getGoodsSort(),hotGoods.getGoodsSort());
		bopsOpsHotgoodsDAO.update(hotGoods);
		   //更新前端库
		OpsHotGooodsPO opsHotGoods =convert(hotGoods);
		OpsHotGooodsPO portalHotGoods=opsHotGooodsDAO.getById(hotGoods.getPopularityId());
		opsHotGooodsDAO.updategoodsSort(portalHotGoods.getGoodsSort(),hotGoods.getGoodsSort());
		opsHotGooodsDAO.update(opsHotGoods);
		}else{
			//如果序位上升
			bopsOpsHotgoodsDAO.updategoodsSortRise(opsHottGoods.getGoodsSort(),hotGoods.getGoodsSort());
			bopsOpsHotgoodsDAO.update(hotGoods);
			//更新前端库
			OpsHotGooodsPO opsHotGoods =convert(hotGoods);
			OpsHotGooodsPO portalHotGoods=opsHotGooodsDAO.getById(hotGoods.getPopularityId());
			opsHotGooodsDAO.updategoodsSortRise(portalHotGoods.getGoodsSort(),hotGoods.getGoodsSort());
			opsHotGooodsDAO.update(opsHotGoods);	
			
			
		}
		
	}
	/**
	 * 
	 * @author LXD 2015年8月22日 上午11:22:18
	 * @Method: deletehotGoodsByGoodsId 
	 * @Description: TODO  根据商品ID删除人气数据(如果商品下架，删除人气中的数据)
	 * @param opsHotgoodsId 
	 * @see com.need.service.bops.ops.BopsOpsHotGoodsService#deletehotGoodsByGoodsId(java.lang.String)
	 */
	@Override
	public void deletehotGoodsByGoodsId(String goodsId) {
		BopsOpsHotgoods hotGoods= bopsOpsHotgoodsDAO.getBygoodsId(goodsId);
		if(hotGoods==null){
			return;			
		}else{
		bopsOpsHotgoodsDAO.updateSort(hotGoods.getGoodsSort());
		bopsOpsHotgoodsDAO.deleteById(hotGoods.getPopularityId());
		deletePortalHotGoods(hotGoods.getPopularityId());
		}
	}
	
}
