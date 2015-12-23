package com.need.common.core.service.trade;

import com.need.common.domain.enums.PayChannelEnum;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.trade.*;

import java.util.List;

/**
 * <p>
 * 交易TradeBase具体业务操作
 * </p>
 * 
 * @author Rylan 2015年8月11日 下午2:11:20
 * @ClassName TradeBaseService
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年8月11日
 * @modify by reason:{方法名}:{原因}
 */
public interface TradeBaseService {
	/**
	 * @author Rylan 2015年8月11日 下午1:58:00 @Method: addTradeBase @Description:
	 *         TODO 新增交易订单 @return @throws
	 */
	public Message addTradeBaseService(TradeCartVO[] record, String userId, String tradeNo);

	/**
	 * @author Rylan 2015年8月11日 下午2:06:15 @Method:
	 *         selectTradeBaseByTradeNo @Description: TODO @param
	 *         tradeNo @return @throws
	 */
	public List<TradeBaseResultVO> selectTradeBaseByTradeNo(String tradeNo);

	/**
	 * @author Rylan 2015年8月11日 下午2:06:37 @Method:
	 *         selectTradeBaseByOrderNo @Description: TODO @param
	 *         orderNo @return @throws
	 */
	public List<TradeBaseResult> selectTradeBaseByOrderNo(String tradeNo, String userId);

	public int countTrade(String userId);

	/**
	 * @author Rylan 2015年8月18日 下午3:53:50 @Method: countGoodsPrice @Description:
	 *         TODO 根据交易编号，查询交易总价 @param tradeNo @return @throws
	 */
	public Message getTradeTotalPrice(String tradeNo);

	/**
	 * @author Rylan 2015年8月18日 下午3:56:55 @Method:
	 *         updateGoodsPriceByOrderNo @Description: TODO
	 *         根据订单编号,实时更新商品价格和总价 @param orderNo @return @throws
	 */
	int updateGoodsPriceByTradeNo(String tradeNo);

	/**
	 * 
	 * @author shenyb 2015年8月20日 下午3:25:09 @Method: createTrade @Description:
	 *         立即购买的创建订单 @param success @param userId @param goodsId @param
	 *         buyCount @return @throws
	 */
	public Message createTrade(Message success, String userId, String goodsId, int buyCount);

	public List<TradeGoodVO> getTradeGoodsList(String userId);

	/**
	 * 客户端支付时的回调
	 * 
	 * @param tradeNo
	 * @param addressId
	 * @param payChannel
	 * @return
	 */
	public Message payMark(String tradeNo, String addressId, PayChannelEnum payChannel);
	
	public void saveTradeItemsGoodsRecord(TradeCartVO[] originalTradeCartVOArray, Message message, String warehouseType);

	/**
	 * @author Rylan 2015年8月25日 上午10:36:41 @Method:
	 *         updateAddressAndChannelByTradeNo @Description: TODO @param
	 *         tradeNo @param addressId @param payChannel @return @throws
	 */
	int updateAddressAndChannelByTradeNo(String tradeNo, String addressId, String payChannel);

	Message updateAddressAndChannelByTradeNo_V1_1(String tradeNo, String addressId, String payChannel);

	/**
	 * 
	 * @author shenyb 2015年10月9日 下午7:09:58 @Method:
	 *         updatePriceByTradeNo @Description: TODO @param tradeNo @param
	 *         payPrice 支付总价 @throws
	 */
	public void updatePriceByTradeNo(String tradeNo, int payPrice);

	/**
	 * @param certificationChannel
	 * @param message
	 * 
	 * @author shenyb 2015年10月12日 上午10:14:58 @Method:
	 *         addTradeBaseService_V1_2 @Description: 确认购买 @param
	 *         tradeCartVOArray @param userId @param tradeNo @param
	 *         couponNo @return @throws
	 */
	public Message addTradeBaseService_V1_2(Message message, TradeCartVO[] tradeCartVOArray, String addressId,
			String userId, String tradeNo, String couponNo, String certificationChannel);

	/**
	 * @author shenyb 2015年10月15日 下午6:25:54 @Method:
	 *         certificationCardVerify @Description: TODO @return @throws
	 */
	public Message certificationCardVerify(String username, String idCard, String userId, String certificationChannel);

	/**
	 * 
	 * @author shenyb 2015年10月23日 下午6:06:44 @Method:
	 *         addTradeBaseService_V1_3 @Description: TODO @param message @param
	 *         tradeCartVOArray @param addressId @param userId @param
	 *         tradeNo @param couponNo @param certificationChannel @param
	 *         warehouseType @return @throws
	 */
	public Message addTradeBaseService_V1_3(Message message, TradeCartVO[] tradeCartVOArray, String addressId,
			String userId, String couponNo, String certificationChannel, String warehouseType);

	/**
	 * 团便宜下单 @author shenyb 2015年10月26日 下午2:40:22 @Method:
	 * createCheapTrade @Description: TODO @param success @param userId @param
	 * goodsId @param buyCount @return @throws
	 * 
	 * @param cheapPrice
	 */
	Message createCheapTrade(String userId, String goodsId, int cheapPrice, String addressId);
    /***
     * 
     * @author LXD 2015年12月4日 下午2:49:16
     * @Method: getConsumeByUserId 
     * @Description: TODO 用户佣金消费记录
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return 
     * @throws
     */
	public Message getConsumeByUserId(String userId, int pageNum, int pageSize);

	/**
	 * 
	 * @author shenyb 2015年12月4日 下午2:26:34 @Method:
	 * checkGoodsStore @Description:检查库存是否充足 @param tradeNo @return @throws
	 */
	public Message checkGoodsStore(String tradeNo);

	Message checkGoodsStoreByList(List<TradeGoodsStoreVO> list);
    
	/**
	 * 
	 * @author 庆凯 2015年12月7日 下午2:54:34 
 getRealTradeCountByUserId @Description:获取用户下单数量
     * @param userId
     * @return 
	 */
    public int getRealTradeCountByUserId(String userId);

	public void testTransaction();
	
	
	/**
	 * @author Rylan 2015年12月8日 下午7:52:48
	 * @Method: WecharPaymentUnifiedOrder 
	 * @Description: 微信统一下单业务处理
	 * @param tradeNo
	 * @return 
	 * @throws
	 */
	public Message wecharPaymentUnifiedOrder(String openId,String tradeNo,String ip);
	
}
