package com.need.common.core.dao.jdbc.trade;

import com.github.pagehelper.Page;
import com.need.common.domain.po.api.trade.TradeBasePO;
import com.need.common.domain.vo.distribution.DistributionTradeVO;
import com.need.common.domain.vo.trade.OrderBaseVO;
import com.need.common.domain.vo.trade.TradeAdrressAndPayChannelParamVO;
import com.need.common.domain.vo.trade.TradeBaseResult;
import com.need.common.domain.vo.trade.TradeGoodVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TradeBaseDAO {
	int deleteByOrderNo(String orderNo);

	int insert(TradeBasePO record);

	TradeBasePO getByorderNo(String orderNo);

	int updateByOrderNo(TradeBasePO record);

	List<TradeBasePO> getByTradeNo(String tradeNo);

	List<TradeBasePO> getByBuyerId(String buyerId);

	Page<TradeBasePO> queryBaseTradeBybuyerId(@Param("buyerId") String buyerId,
			@Param("tradeStatus") String tradeStatus);

	/**
	 * @author Rylan 2015年8月22日 上午11:22:11 @Method:
	 * queryTradeSendAndReceiveBybuyerId @Description: TODO @param
	 * buyerId @return @throws
	 */
	Page<TradeBasePO> queryTradeSendAndReceiveBybuyerId(@Param("buyerId") String buyerId);

	/**
	 * @author Rylan 2015年8月15日 下午1:40:50 @Method:
	 * getTradeBaseByTradeNo @Description: TODO @param tradeNo @param
	 * userId @throws
	 */
	List<TradeBaseResult> getTradeBaseByTradeNo(@Param("tradeNo") String tradeNo, @Param("userId") String userId);

	int countTrade(String userId);

	/**
	 * @author Rylan 2015年8月16日 下午4:34:33 @Method:
	 * queryOrderByTradeNo @Description: TODO 查询基本订单信息 @param
	 * tradeNo @return @throws
	 */
	List<OrderBaseVO> queryOrderBaseByTradeNo(@Param("tradeNo") String tradeNo);

	/**
	 * @author Rylan 2015年8月16日 下午12:00:56 @Method:
	 * updateAddressAndChannelByTradeNo @Description: TODO @param tradeNo @param
	 * addressId @param payChannel @return @throws
	 */
	int updateAddressAndChannelByTradeNo(@Param("tradeNo") String tradeNo, @Param("addressId") String addressId,
			@Param("payChannel") String payChannel);

	/**
	 * @author Rylan 2015年8月18日 下午12:05:35 @Method:
	 * updateBuyPriceAndTotalPriceByTradeNo @Description: TODO
	 * 根据订单id修改商品单价和总价 @param orderNo @param buyPrice @param
	 * totalPrice @return @throws
	 */
	int updateBuyPriceAndTotalPriceByTradeNo(@Param("orderNo") String orderNo, @Param("buyPrice") Integer buyPrice,
			@Param("totalPrice") Integer totalPrice);

	List<TradeGoodVO> selectTradeGoods(@Param("userId") String userId, @Param("tradeStatus") String tradeStatus);

	/**
	 * 根据交易编号变更交易状态
	 *
	 * @param tradeNo
	 * @param tradeStatus
	 * @param orderStatus
	 * @return
	 */
	int updateTradeStatusByTradeNo(@Param("tradeNo") String tradeNo, @Param("tradeStatus") String tradeStatus,
			@Param("orderStatus") String orderStatus);

	int updateAddressAndChannelByTradeNo_V1_1(TradeAdrressAndPayChannelParamVO paramVO);
/**
 * 
 * @author shenyb 2015年10月9日 下午7:25:12
 * @Method: updateBuyPriceAndTotalPriceAndPayPriceByTradeNo 
 * @Description: TODO
 * @param orderNo
 * @param buyPrice 商品单价
 * @param totalPrice商品总价
 * @param payPrice 支付价格
 * @throws
 */
	void updateBuyPriceAndTotalPriceAndPayPriceByTradeNo(@Param("orderNo") String orderNo,
			@Param("buyPrice") int buyPrice, @Param("totalPrice") int totalPrice, @Param("payPrice") int payPrice);

	void updatePayPriceByTradeNo(@Param("userTradeNo")String userTradeNo, @Param("payPrice")int sum);
	void updatePayPriceByUserOrderNo(@Param("userOrderNo")String userOrderNo, @Param("payPrice")int sum);


	List<TradeBasePO> selectByTradeNos(@Param("tradeNos")String tradeNos);

	void updateTransportFeeByUserOrderNo(@Param("userOrderNo")String userOrderNo, @Param("transportFee")int transportFee);

	void updateTransportFeeByUserTradeNo(@Param("userTradeNo")String userTradeNo,@Param("transportFee") int transportFee);

	int insertByBatch(@Param("list")List<TradeBasePO> trades);

	int getTotalPayPrice(String tradeNo);

	List<TradeBasePO> selectByUserTradeNo(String userTradeNo);

	List<DistributionTradeVO> selectBytradeNo(String userOrderNo);

	List<TradeBaseResult> queryConsumeByUserId(String userId);
	/**
	 * 根据交易的id修改交易的是否缺货的状态
	 * @param tradeNo
	 * @return
	 * @author zhangmengbin
	 * @2015-12-03
	 */
	int updateISNormalByTradeNo(@Param("tradeNo") String tradeNo,@Param("isNormal") String isNormal);

	/**
	 * 获取用户成功的订单数量
	 * @param userId
	 * @return
	 * @author 庆凯
	 * @2015-12-08
	 */
    int countRealTrade(String userId);

	TradeBasePO getBytradeNoAndGoodsId(@Param("tradeNo") String tradeNo,@Param("goodsId")String goodsId);

}