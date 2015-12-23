package com.need.service.bops.goods;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.need.domain.po.bops.goods.BopsGoods;
import com.need.domain.po.bops.goods.BopsGoodsStore;
import com.need.domain.pub.Message;
import com.need.domain.vo.goods.GoodsStorePageVO;
import com.need.domain.vo.goods.GoodsStoreResultVO;
import com.need.domain.vo.wms.StoreChangeVO;

public interface BopsGoodsStoreService {
	
	/**
	 * 
	 * @author xiehao 2015年8月5日 下午8:44:04
	 * @Method: insertNewStore 添加一新的库存 
	 * @Description: TODO
	 * @param store 
	 * @throws
	 */
	public void insertNewStore(GoodsStorePageVO store);
	
	/**
	 * @author xiehao 2015年12月18日 下午4:57:30
	 * @Method: addBopsStore 
	 * @Description: TODO 添加后台库存的正品或残品
	 * @param store
	 */
	public int addBopsStore(GoodsStorePageVO store);

	/**
	 * 
	 * @author xiehao 2015年8月5日 下午6:34:01
	 * @Method: getStoreById 
	 * @Description: TODO 通过ID获得库存
	 * @param goodsId
	 * @return 
	 * @throws
	 */
	public BopsGoodsStore getStoreById(int goodsId);
	
	/**
	 * 
	 * @author xiehao 2015年8月5日 下午6:34:21
	 * @Method: deleteGoodsStoreById 
	 * @Description: TODO 通过ID删除一条库存信息
	 * @param goodsId 
	 * @throws
	 */
	public void deleteGoodsStoreById(String goodsId);
	
	/**
	 * 
	 * @author xiehao 2015年8月5日 下午10:18:15
	 * @Method: updateStore 
	 * @Description: TODO 更新库存
	 * @param store 
	 * @throws
	 */
	public void updateStore(BopsGoodsStore store);
	
	/**
	 * 
	 * @author xiehao 2015年8月10日 下午1:36:50
	 * @Method: updateAudit 
	 * @Description: TODO 审核
	 * @param auditStatus 
	 * @throws
	 */
	public void updateAudit(GoodsStorePageVO goodsStorePageVO);
	
	/**
	 * 
	 * @author xiehao 2015年8月10日 下午1:36:54
	 * @Method: frozenStore 
	 * @Description: TODO 冻结
	 * @param auditStatus 
	 * @throws
	 */
	public void frozenStore(GoodsStorePageVO goodsStorePageVO);
	
	public BopsGoods selectByGoodsId(String goodsId);
	
	/**
	 * 
	 * @author xiehao 2015年8月6日 下午11:38:41
	 * @Method: pageBopsGoodsStore 
	 * @Description: TODO 分页查询
	 * @param page
	 * @return 
	 * @throws
	 */
	public List<GoodsStoreResultVO> pageBopsGoodsStore(GoodsStorePageVO page);
	
	public void addPortalGoodsCount(String goodsId, int storeCount);
	
	public void addPortalGoodsCount(String goodsId, int storeCount, int changeCount);
	
	public GoodsStoreResultVO getGoodsMainData(String goodsId);
	
	public GoodsStoreResultVO getGoodsStore(String goodsId);
	
	public List<GoodsStoreResultVO> pageStoreLog(String goodsId);
	
	public HSSFWorkbook exportStore(GoodsStorePageVO storePageVO);
	
	public Message saveStoreChange(StoreChangeVO storeChangeVO);
	
}
