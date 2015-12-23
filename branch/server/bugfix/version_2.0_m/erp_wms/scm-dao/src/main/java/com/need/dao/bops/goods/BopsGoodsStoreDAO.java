package com.need.dao.bops.goods;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.need.domain.po.bops.goods.BopsGoodsStore;
import com.need.domain.vo.goods.GoodsStorePageVO;
import com.need.domain.vo.goods.GoodsStoreResultVO;
import com.need.domain.vo.wms.StoreChangeVO;

public interface BopsGoodsStoreDAO {

	int deleteByPrimaryKey(@Param("goodsId") String goodsId);

	int insertNewStore(GoodsStorePageVO store);

	int insertBadStore(GoodsStorePageVO store);
	
	int insertSelective(GoodsStorePageVO store);

	BopsGoodsStore selectByPrimaryKey(@Param("storeId")int storeId);
	
	BopsGoodsStore getByGoodsId(String goodsId);
	
	int countDefectiveStore(String goodsId);

	int updateByPrimaryKeySelective(BopsGoodsStore store);

	int updateById(BopsGoodsStore store);

	List<GoodsStoreResultVO> queryPageBopsGoodsStore(GoodsStorePageVO page);

	int queryPageBopsGoodsStoreCount(GoodsStorePageVO goodsPageVO);
	
	/**
	 * 
	 * @author xiehao 2015年8月10日 下午1:40:33
	 * @Method: auditStore 
	 * @Description: TODO 审核, 冻结
	 * @param auditStatus 
	 * @throws
	 */
	void updateAuditStore(GoodsStorePageVO goodsStorePageVO);
	
	int saveStoreChange(StoreChangeVO storeChangeVO);
	
	List<GoodsStoreResultVO> queryStoreLog(String goodsId);
	
	List<GoodsStoreResultVO> queryExportStore(GoodsStorePageVO storePageVO);
	
	BopsGoodsStore selectByGoodsId(String goodsId);
}