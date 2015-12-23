package com.need.service.bops.goods;

import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.need.domain.po.bops.goods.BopsGoods;
import com.need.domain.po.bops.goods.BopsGoodsItemsPO;
import com.need.domain.pub.Message;
import com.need.domain.vo.goods.AuditGoodsVO;
import com.need.domain.vo.goods.BopsGoodsItemsVO;
import com.need.domain.vo.goods.GoodsDetailVO;
import com.need.domain.vo.goods.GoodsPageVO;
import com.need.domain.vo.goods.GoodsParamsVO;
import com.need.domain.vo.goods.GoodsPriceChangeVO;
import com.need.domain.vo.goods.GoodsStoreDetailResultVO;
import com.need.domain.vo.goods.GoodsStoreDetailVO;
import com.need.domain.vo.goods.SuitGoodsPriceVO;
import com.need.domain.vo.goods.OpsGoodsParamVO;

public interface BopsGoodsService {
	
	/**
	 * 
	 * @author xiehao 2015年8月5日 下午2:36:15
	 * @Method: insertNewGoods 
	 * @Description: TODO 插入新的产品数据
	 * @param bopsGoods 
	 * @throws
	 */
	public GoodsStoreDetailVO insertNewGoods(BopsGoods goods, GoodsParamsVO goodsParamsVO, GoodsDetailVO goodsDetailVO);
	
	/**
	 * @author xiehao 2015年12月10日 下午2:53:23
	 * @Method: updateGroupGoodsPrice 
	 * @Description: 更改组合商品的价格
	 * @param goodsId 
	 */
	public void updateGroupGoodsPrice(String goodsId);
	/**
	 * 
	 * @author xiehao 2015年12月18日 下午3:52:43
	 * @Method: insertStore 
	 * @Description: TODO 添加新的商品时在后台库存主表添加一条记录
	 * @param goods
	 */
	public int insertStore(String goodsId);
	
	/**
	 * 
	 * @author xiehao 2015年8月5日 下午3:12:49
	 * @Method: getGoodsById 
	 * @Description: TODO 根据ID查商品
	 * @param goodsId 
	 * @throws
	 */
	public BopsGoods getGoodsById(String goodsId);
	
	/**
	 * @author xiehao 2015年11月18日 下午4:55:05
	 * @Method: excelToList 
	 * @Description: TODO 把商品价格EXCEL变成List
	 * @param in
	 * @param is07Or10
	 */
	public List<GoodsPriceChangeVO> excelToList(InputStream in, Boolean is07Or10, String pricechangeType);
	
	/**
	 * 
	 * @author xiehao 2015年8月5日 下午3:36:50
	 * @Method: updateById 
	 * @Description: TODO 更新产品信息
	 * @param goods 
	 * @throws
	 */
	public void updateById(BopsGoods goods) throws NumberFormatException, ParseException;
	
	/**
	 * 
	 * @author xiehao 2015年8月5日 下午3:47:30
	 * @Method: deleteById 
	 * @Description: TODO 根据ID删除一个商品
	 * @param goodsId 
	 * @throws
	 */
	public void deleteById(String goodsId);
	
	/**
	 * 
	 * @author xiehao 2015年8月5日 下午4:34:52
	 * @Method: pageOfBopsGoods 
	 * @Description: TODO 获得分页数据
	 * @param page
	 * @return 
	 * @throws
	 */
	public List<GoodsStoreDetailResultVO> pageOfBopsGoods(GoodsPageVO goodsPageVO);
	/**
	 * 
	 * @author LXD 2015年8月10日 下午2:33:56
	 * @Method: auditGoods 
	 * @Description: TODO 审核商品
 	 * @param bopsGoods 
	 * @throws
	 */
	public void auditGoods(AuditGoodsVO bopsGoods);
	/**
	 * 
	 * @author LXD 2015年8月10日 下午4:32:51
	 * @Method: AddedGoods 
	 * @Description: TODO 上架商品/下架/冻结商品
	 * @param bopsGoods 
	 * @throws
	 */
	public void AddedOrtakeOrfreezeGoods(BopsGoods bopsGoods);
	
	
	public boolean getPortalGoods(String goodsId);
	/**
	 * 
	 * @author LXD 2015年8月10日 下午5:23:08
	 * @Method: deletePortalGoods 
	 * @Description: TODO 删除前端数据
	 * @param goodsId 
	 * @throws
	 */
	public void deletePortalGoods(String goodsId);
    /**
     * 
     * @author LXD 2015年8月13日 上午12:01:49
     * @Method: viewGoods 
     * @Description: TODO 查看商品详情
     * @param goodsId
     * @return 
     * @throws
     */
	public GoodsStoreDetailVO viewGoods(String goodsId);
	
	/**
	 * @author xiehao 2015年8月27日 下午4:47:48
	 * @Method: getGoodsMainStore 
	 * @Description: TODO 查询前台商品的库存数
	 * @param goodsId
	 * @return 
	 * @throws
	 */
	public int getGoodsMainStore(String goodsId);

	public void editGoods(GoodsStoreDetailVO bopsGoodsVO) throws NumberFormatException, ParseException ;

	public HSSFWorkbook exportGoods(GoodsPageVO goodsPageVO);
    
	/**
	 * 
	 * @author LXD 2015年10月26日 下午4:02:46
	 * @Method: getBygoodsIds 
	 * @Description: TODO 根据商品ID数组，返回商品list
	 * @param goodsIds
	 * @return 
	 * @throws
	 */
	public List<GoodsStoreDetailResultVO> getBygoodsIds(String[] goodsCode);
	
	public int updateChangeGoodsPrice(String goodsId, Integer discountPrice);
	
	public GoodsStoreDetailVO getGoodsDetail(String goods,String warehousetype);
	/**
	 * 查询组装商品的原价和need价
	 * @param goodsGroupId
	 * @param type 如果需要商品的重量传weight,
	 * @return
	 * @author zhangmengbin
	 */
	public SuitGoodsPriceVO querySuitGoodsPrice(String goodsGroupId,String type);

	public void editOpsGoods(OpsGoodsParamVO bopsGoodsVO);
	public OpsGoodsParamVO viewOpsGoods(String goodsId);
	
	
	public BopsGoodsItemsPO insertGoodsItems(List<BopsGoodsItemsVO> list,BopsGoods bopsGoods,GoodsParamsVO paramsVO); 
	
	public List<BopsGoodsItemsVO> selectGoodsItemDetail(String goodsId);
	
	public BopsGoodsItemsPO updateGoodsItems(List<BopsGoodsItemsVO> list,BopsGoods bopsGoods,BopsGoods preGoods,GoodsParamsVO paramsVO);
	
	void updateGoodsStatus(BopsGoods bopsGoods);
	
	public void updateGroupPrice(BopsGoods bopsGoods);
	
	public void auditGroupPrice(BopsGoods bopsGoods);

	public Message editGoodsCategory(String categoryId, String[] goodsCode);
}
