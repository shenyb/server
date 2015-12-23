package com.need.service.bops.ops.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.need.dao.api.ops.OpsSelectedDAO;
import com.need.dao.bops.ops.BopsOpsSelectedDAO;
import com.need.domain.po.api.ops.OpsSelectedPO;
import com.need.domain.po.bops.ops.BopsOpsSelected;
import com.need.domain.pub.Page;
import com.need.domain.vo.ops.SelectedGoodsVO;
import com.need.service.bops.ops.BopsOpsSelectedService;
@Service
public class BopsOpsSelectedServiceImpl implements BopsOpsSelectedService {

	@Autowired
	private BopsOpsSelectedDAO bopsOpsSelectedDAO;
	@Autowired
	private OpsSelectedDAO opsSelectedDAO;
	/**
	 * 
	 * @author LXD 2015年8月19日 下午3:09:44
	 * @Method: addBopsOpsSelected 
	 * @Description: TODO 添加精选
	 * @param bopsOpsSelected 
	 * @see com.need.service.bops.ops.BopsOpsSelectedService#addBopsOpsSelected(com.need.bops.dao.jdbc.bops.ops.po.BopsOpsSelected)
	 */
	@Override
	@Transactional
	public void addBopsOpsSelected(BopsOpsSelected bopsOpsSelected) {
		bopsOpsSelectedDAO.insert(bopsOpsSelected);
		
		
	}
	/**
	 * 
	 * @author LXD 2015年8月19日 下午3:10:06
	 * @Method: getSelectedById 
	 * @Description: TODO 根据ID获取单个信息
	 * @param bopsOpsSelectedId
	 * @return 
	 * @see com.need.service.bops.ops.BopsOpsSelectedService#getSelectedById(java.lang.String)
	 */
	@Override
	public BopsOpsSelected getSelectedById(String bopsOpsSelectedId) {
		return bopsOpsSelectedDAO.getById(bopsOpsSelectedId);
	}
    /**
     * 
     * @author LXD 2015年8月19日 下午3:11:11
     * @Method: updateBopsOpsSelected 
     * @Description: TODO 更新精选
     * @param bopsOpsSelected 
     * @see com.need.service.bops.ops.BopsOpsSelectedService#updateBopsOpsSelected(com.need.bops.dao.jdbc.bops.ops.po.BopsOpsSelected)
     */
	@Override
	@Transactional
	public void updateBopsOpsSelected(BopsOpsSelected bopsOpsSelected) {
		bopsOpsSelectedDAO.update(bopsOpsSelected);
	}
    /**
     * 
     * @author LXD 2015年8月15日 下午5:33:02
     * @Method: deleteBopsOpsSelected 
     * @Description: TODO 移除操作，同时删除前端数据
     * @param bopsOpsSelectedId 
     * @see com.need.service.bops.ops.BopsOpsSelectedService#deleteBopsOpsSelected(java.lang.String)
     */
	@Transactional
	@Override
	public void deleteBopsOpsSelected(String bopsOpsSelectedId) {
		BopsOpsSelected hotGoods= bopsOpsSelectedDAO.getById(bopsOpsSelectedId);
		if(hotGoods!=null){
		bopsOpsSelectedDAO.updateSort(hotGoods.getGoodsSort());
		bopsOpsSelectedDAO.deleteById(bopsOpsSelectedId);
		deletePortalSelectedGoods(bopsOpsSelectedId);
		}
	}
    /**
     * 
     * @author LXD 2015年8月19日 下午3:11:41
     * @Method: getPageOfBopsOps 
     * @Description: TODO 分页
     * @param page
     * @return 
     * @see com.need.service.bops.ops.BopsOpsSelectedService#getPageOfBopsOps(com.need.bops.pub.Page)
     */
	@Override
	public List<BopsOpsSelected> getPageOfBopsOps(Page page) {
		page.setTotal(bopsOpsSelectedDAO.getCount());
		return bopsOpsSelectedDAO.queryByPage(page);
	}
       /**
        * 
        * @author LXD 2015年8月19日 下午3:11:54
        * @Method: getAll 
        * @Description: TODO 所有信息
        * @return 
        * @see com.need.service.bops.ops.BopsOpsSelectedService#getAll()
        */
	@Override
	public List<BopsOpsSelected> getAll() {
		/** TODO Auto-generated method stub*/
		return bopsOpsSelectedDAO.getAll();
	}
     /**
      * 
      * @author LXD 2015年8月19日 下午3:12:09
      * @Method: searchSelectedGoods 
      * @Description: TODO 搜索
      * @param selectedGoodsVO
      * @return 
      * @see com.need.service.bops.ops.BopsOpsSelectedService#searchSelectedGoods(com.need.bops.web.controller.vo.ops.SelectedGoodsVO)
      */
	@Override
	public List<BopsOpsSelected> searchSelectedGoods(SelectedGoodsVO selectedGoodsVO) {
		/** TODO Auto-generated method stub*/
		selectedGoodsVO.setTotal(bopsOpsSelectedDAO.getCountByParams(selectedGoodsVO));
		return bopsOpsSelectedDAO.queryByParams(selectedGoodsVO);
	}
       /**
        * 
        * @author LXD 2015年8月19日 下午3:12:38
        * @Method: insertPortalselectedGoods 
        * @Description: TODO 插入前端库
        * @param list 
        * @see com.need.service.bops.ops.BopsOpsSelectedService#insertPortalselectedGoods(java.util.List)
        */
	@Override
	public void insertPortalselectedGoods(List<BopsOpsSelected> list) {
		/** TODO Auto-generated method stub*/
		for(BopsOpsSelected selectionGoods:list){
			
			OpsSelectedPO SelectedPO =new OpsSelectedPO();
			BeanUtils.copyProperties(selectionGoods, SelectedPO);
			opsSelectedDAO.insert(SelectedPO);
			
		}
		
	}
    /**
     * 
     * @author LXD 2015年8月19日 下午3:33:32
     * @Method: updatePortalselectedGoods 
     * @Description: TODO 更新前端库
     * @param selectedgoods 
     * @see com.need.service.bops.ops.BopsOpsSelectedService#updatePortalselectedGoods(com.need.bops.dao.jdbc.bops.ops.po.BopsOpsSelected)
     */
	@Override
	public void updatePortalselectedGoods(BopsOpsSelected selectedgoods) {
		/** TODO Auto-generated method stub*/
		OpsSelectedPO SelectedPO =new OpsSelectedPO();
		BeanUtils.copyProperties(selectedgoods, SelectedPO);
		opsSelectedDAO.update(SelectedPO);
		
	}
    /**
     * 
     * @author LXD 2015年8月19日 下午3:33:51
     * @Method: deletePortalSelectedGoods 
     * @Description: TODO 删除前端库
     * @param selectionId 
     * @see com.need.service.bops.ops.BopsOpsSelectedService#deletePortalSelectedGoods(java.lang.String)
     */
	public void deletePortalSelectedGoods(String selectionId) {
		OpsSelectedPO selectedGoods=opsSelectedDAO.getById(selectionId);
		opsSelectedDAO.updateSort(selectedGoods.getGoodsSort());
		opsSelectedDAO.deleteById(selectionId);
	}
    /**
     * 
     * @author LXD 2015年8月19日 下午3:34:13
     * @Method: checkIsAdd 
     * @Description: TODO 校验该商品是否添加过
     * @param goodsId
     * @return 
     * @see com.need.service.bops.ops.BopsOpsSelectedService#checkIsAdd(java.lang.String)
     */
	@Override
	public BopsOpsSelected checkIsAdd(String goodsId) {
		/** TODO Auto-generated method stub*/
		return bopsOpsSelectedDAO.getSelectedGoods(goodsId);
	}
   /**
    *  
    * @author LXD 2015年8月15日 下午4:24:05
    * @Method: addSelectedGoods 
    * @Description: TODO 添加精选商品 同时同步到前台
    * @param list 
    * @see com.need.service.bops.ops.BopsOpsSelectedService#addSelectedGoods(java.util.List)
    */
	@Override
	@Transactional
	public void addSelectedGoods(List<BopsOpsSelected> list) {
		 //初始化序位
		List<BopsOpsSelected> checkList= bopsOpsSelectedDAO.queryBycheck();

		if(checkList.size()==0){
			for(int i=0;i<list.size();i++){
				BopsOpsSelected selectgoods= list.get(i);
				selectgoods.setGoodsSort(i+1);
				bopsOpsSelectedDAO.insert(selectgoods);
				OpsSelectedPO opsSelected=new OpsSelectedPO();
				BeanUtils.copyProperties(selectgoods, opsSelected);
				opsSelectedDAO.insert(opsSelected);
				
			}	
		}else{
		 //后添加的人气序位累加
		 int maxSort=bopsOpsSelectedDAO.queryMaxSort();
		 for(int i=0;i<list.size();i++){
			 BopsOpsSelected selectgoods= list.get(i);
			     selectgoods.setGoodsSort(maxSort+i+1);
			     bopsOpsSelectedDAO.insert(selectgoods);
			     OpsSelectedPO opsSelected=new OpsSelectedPO();
				 BeanUtils.copyProperties(selectgoods, opsSelected);
				 opsSelectedDAO.insert(opsSelected);
			}
			
		}
	}
/**
 * 
 * @author LXD 2015年8月16日 下午6:55:33
 * @Method: editSelectedGoods 
 * @Description: TODO 调整序位
 * @param selectedgoods 
 * @see com.need.service.bops.ops.BopsOpsSelectedService#editSelectedGoods(com.need.bops.dao.jdbc.bops.ops.po.BopsOpsSelected)
 */
@Override
@Transactional
public void editSelectedGoods(BopsOpsSelected selectedgoods) {
	BopsOpsSelected opsHottGoods= bopsOpsSelectedDAO.getById(selectedgoods.getSelectionId());
	if(opsHottGoods.getGoodsSort()<selectedgoods.getGoodsSort()){
		//下降
    bopsOpsSelectedDAO.updategoodsSortDrop(opsHottGoods.getGoodsSort(),selectedgoods.getGoodsSort());
    bopsOpsSelectedDAO.update(selectedgoods);
        //同步前端库
    OpsSelectedPO opsHotGoods =convert(selectedgoods);
    OpsSelectedPO portalHotGoods=opsSelectedDAO.getById(selectedgoods.getSelectionId());
    opsSelectedDAO.updategoodsSort(portalHotGoods.getGoodsSort(),selectedgoods.getGoodsSort());
    opsSelectedDAO.update(opsHotGoods);
	}else{
		//上升
		bopsOpsSelectedDAO.updategoodsSortRise(opsHottGoods.getGoodsSort(),selectedgoods.getGoodsSort());
		bopsOpsSelectedDAO.update(selectedgoods);
		   //同步前端库
		 OpsSelectedPO opsHotGoods =convert(selectedgoods);
		 OpsSelectedPO portalHotGoods=opsSelectedDAO.getById(selectedgoods.getSelectionId());
		 opsSelectedDAO.updategoodsSortRise(portalHotGoods.getGoodsSort(),selectedgoods.getGoodsSort());
		 opsSelectedDAO.update(opsHotGoods);			
	}
	
}
  /**
   * 
   * @author LXD 2015年8月19日 下午3:36:32
   * @Method: convert 
   * @Description: TODO 封装前端对象
   * @param selectedgoods
   * @return 
   * @throws
   */
private OpsSelectedPO convert(BopsOpsSelected selectedgoods) {
	OpsSelectedPO opsSelectedPO =new OpsSelectedPO();
	opsSelectedPO.setSelectionId(selectedgoods.getSelectionId());
	opsSelectedPO.setGoodsId(selectedgoods.getGoodsId());
	opsSelectedPO.setGoodsName(selectedgoods.getGoodsName());
	opsSelectedPO.setGoodsProfilePicKey(selectedgoods.getGoodsProfilePicKey());
	opsSelectedPO.setGoodsStatus(selectedgoods.getGoodsStatus());
	opsSelectedPO.setGoodsSort(selectedgoods.getGoodsSort());
	return opsSelectedPO;
}

/**
 * 
 * @author LXD 2015年8月15日 下午5:33:02
 * @Method: deleteBopsOpsSelected 
 * @Description: TODO 如果商品下架，精选中的商品数据删除
 * @param bopsOpsSelectedId 
 * @see com.need.service.bops.ops.BopsOpsSelectedService#deleteBopsOpsSelected(java.lang.String)
 */
@Override
public void deleteBopsOpsSelectedByGoodsId(String goodsId) {
	BopsOpsSelected selectedGoods= bopsOpsSelectedDAO.getSelectedGoods(goodsId);
	if(selectedGoods==null){
		return;			
	}else{
	bopsOpsSelectedDAO.updateSort(selectedGoods.getGoodsSort());
	bopsOpsSelectedDAO.deleteById(selectedGoods.getSelectionId());
	deletePortalSelectedGoods(selectedGoods.getSelectionId());
	}
}
	
}
