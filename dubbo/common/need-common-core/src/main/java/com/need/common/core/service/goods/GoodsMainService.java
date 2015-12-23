package com.need.common.core.service.goods;

import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.goods.GoodsPageResultVO;
import com.need.common.domain.vo.goods.GoodsProfileResultVO;
import com.need.common.domain.vo.goods.GoodsResultVO;

public interface GoodsMainService {

	
	/**
	 * 
	 * @author xiehao 2015年8月11日 下午3:27:32
	 * @Method: getGoodsById 
	 * @Description: TODO 根据商品ID获得商品信息
	 * @param goodsId
	 * @return 
	 * @throws
	 */
	public GoodsResultVO getGoodsById(String goodsId);
	
	/**
	 * @author xiehao 2015年12月4日 下午3:08:10
	 * @Method: itemsGoodsIsSoldout 
	 * @Description: TODO 检查组合商品是否售罄
	 * @param goodsId
	 */
	public boolean itemsGoodsIsSoldout(String goodsId);
	/**
	 * @author xiehao 2015年12月4日 下午3:55:00
	 * @Method: itemsGoodsPrice 
	 * @Description: TODO 设置组合商品的价格
	 * @param goods 
	 */
	public void setItemsGoodsPrice(GoodsProfileResultVO goods);
	
	public Message getGoodsProfileById(String goodsId, String userId);
	
	public Message getGoodsProfileById_V1_3(String goodsId, String userId);
	
	/**
	 * 根据场景ID获得商品列表
	 * @param sceneId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public GoodsPageResultVO getGoodsListBySceneId(int sceneId, String userId, int pageNum, int pageSize);
	
	/**
	 * @author Rylan 2015年9月20日 上午11:13:30
	 * @Method: checkGoodsIsGlobalState 
	 * @Description: 检查上架商品的 is_global 状态  true 是 false 不是
	 * @param goodsId
	 * @return 
	 * @throws
	 */
	boolean checkGoodsIsGlobalState(String goodsId);

	Message getGoodsProfileById_V2_0(String goodsId, String userId);

	public GoodsProfileResultVO getgoodsProfiled(String goodsId);
	
}
