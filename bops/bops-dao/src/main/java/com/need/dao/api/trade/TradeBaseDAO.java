package com.need.dao.api.trade;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.need.domain.po.api.trade.TradeBasePO;
import com.need.domain.vo.trade.CallcenterPageVO;
import com.need.domain.vo.trade.OrderExportParamVO;
import com.need.domain.vo.trade.OrderExportResultVO;
import com.need.domain.vo.trade.OrderExportVO;
import com.need.domain.vo.trade.TradeBaseParam;
import com.need.domain.vo.trade.TradeBaseVO;
import com.need.domain.vo.trade.TradeExportItemVO;
import com.need.domain.vo.trade.TradeExportVO;
import com.need.domain.vo.trade.TradeOrderParamsVO;
import com.need.domain.vo.trade.TradeSendVO;
import com.need.domain.vo.trade.TradeVO;

public interface TradeBaseDAO {
	int deleteByPrimaryKey(String orderNo);

	int insert(TradeBasePO record);

	int insertSelective(TradeBasePO record);

	TradeBasePO selectByPrimaryKey(String orderNo);

	int updateByPrimaryKeySelective(TradeBasePO record);

	int updateByPrimaryKey(TradeBasePO record);

	/**
	 * @author xiehao 2015年9月5日 下午2:23:22 @Method:
	 * getGoodsSoldCount @Description: TODO 已销售数量 @param goodsId @return @throws
	 */
	int getGoodsSoldCount(String goodsId);

	/**
	 * @author xiehao 2015年9月5日 下午2:23:46 @Method:
	 * getGoodsOutStoreCount @Description: TODO 出库数量 @param
	 * goodsId @return @throws
	 */
	int getGoodsOutStoreCount(String goodsId);

	/**
	 * @author xiehao 2015年9月16日 下午3:30:28
	 * @Method: getTradeLockedCount 
	 * @Description: TODO 查询交易锁定数
	 * @param goodsId
	 */
	int getTradeLockedCount(String goodsId);

	/**
	 * @param param
	 * @return
	 */
	List<TradeBaseVO> queryPageByParam(TradeBaseParam param);

	int getCountByPage(TradeBaseParam param);
	/**
	 * 
	 * @author peiboli 2015年11月6日 下午6:12:52
	 * @Method: getCountCallcenter 
	 * @Description: TODO弹屏统计数量
	 * @param param
	 * @return 
	 * @throws
	 */
	int getCountCallcenter(CallcenterPageVO param);
	
	/**
	 * @author xiehao 2015年9月16日 下午3:31:11
	 * @Method: queryTradeExport 
	 * @Description: TODO 查询要导出的订单
	 * @param paramVO
	 */
	List<OrderExportResultVO> queryTradeExport(OrderExportParamVO paramVO);
	
	int countTradeExport(OrderExportParamVO paramVO);

	List<TradeBasePO> getByTradeNo(String tradeNo);
	
	List<TradeVO> getByInfoTradeNo(String tradeNo);
	
	
	List<TradeVO> getByUserTradeNo(String userTradeNo);

	/**
	 * @author Rylan 2015年8月18日 上午11:32:10 @Method:
	 * queryTradeBaseByTradeState @Description: TODO 根据交易状态查询交易信息 @param
	 * tradeState @return @throws
	 */
	List<TradeBasePO> queryTradeBaseByTradeState(@Param("tradeState") String tradeState);

	/**
	 * @author Rylan 2015年8月18日 下午2:30:40 @Method:
	 * updateTradeStatelPriceByTradeNo @Description: TODO 交易关闭 @param
	 * orderNo @param tradeState @return @throws
	 */
	int updateTradeCloseTradeNo(@Param("tradeNo") String tradeNo);

	/**
	 * @author Rylan 2015年8月18日 下午5:21:41 @Method:
	 * updateTradeSuccessByTradeNo @Description: TODO 交易成功并且修改交易时间 @param
	 * tradeNo @param tradeState @return @throws
	 */
	int updateTradeSuccessByTradeNo(@Param("tradeNo") String tradeNo);

	/**
	 * 
	 * @author LXD 2015年8月24日 上午11:57:53 @Method: queryByPage @Description: TODO
	 * 交易列表导出用 @param param @return @throws
	 */
	List<TradeSendVO> queryByPage(TradeBaseParam param);

	/**
	 * 
	 * @author LXD 2015年8月24日 上午11:58:25 @Method: queryCountByPage @Description:
	 * TODO 交易列表导出查询总记录数用 @param param @return @throws
	 */
	int queryCountByPage(TradeBaseParam param);

	/**
	 * @author xiehao 2015年9月16日 上午10:29:50
	 * @Method: queryOrderByPage 
	 * @Description: TODO 导出明细
	 * @param orderParamsVO
	 */
	List<OrderExportVO> queryOrderByPage(TradeOrderParamsVO orderParamsVO);
	
	Integer queryOrderCountByPage(TradeOrderParamsVO orderParamsVO);
	/**
	 * @author xiehao 2015年9月16日 上午10:30:37
	 * @Method: queryOrderByPage_v_1_1 
	 * @Description: TODO 导出明细 V1.1
	 * @param orderParamsVO
	 * @return 
	 * @throws
	 */
	List<OrderExportVO> queryOrderByPage_v_1_1(TradeOrderParamsVO orderParamsVO);
	
	int queryOrderCountByPage_v_1_1(TradeOrderParamsVO orderParamsVO);

	/**
	 * @author Rylan 2015年10月29日 下午6:22:54
	 * @Method: queryPageByMobileParam 
	 * @Description: 根据用户手机号查询订单
	 * @param param
	 * @return 
	 * @throws
	 */
	List<TradeBaseVO> queryPageByMobileParam(TradeBaseParam param);

	List<TradeBaseVO> queryPageCallcenter(CallcenterPageVO param);
	
	List<TradeVO> getByUserTradeNoWms(String userTradeNo);
	
	TradeBasePO getByUserOrderNoWms(String userOrderNo);

	int getGoodsCountByBatch(String groupBatch);
	/**
	 * 
	 * @author liuhongyang 2015年12月1日 上午11:11:25
	 * @Method: selectByTradeAndGoods 
	 * @Description: 根基订单号商品id查询need价
	 * @param tradeBasePO
	 * @return 
	 * @throws
	 */
	TradeBasePO selectByTradeAndGoods(TradeBasePO tradeBasePO);
	/**
	 * 查询订单导出的数据
	 * @author zhangmengbin
	 * @param param
	 * @return
	 */
	List<TradeExportVO> queryTradeExportByPage(TradeBaseParam param);
	/**
	 * 查询订单导出的条数
	 * @param param
	 * @return
	 */
	int queryCountTradeExportByPage(TradeBaseParam param);
	/**
	 * 查询订单明细的数据
	 * @param orderParamsVO
	 * @return
	 * @author zhangmengbin
	 */
	List<TradeExportItemVO> queryTradeExportItemByPage(TradeOrderParamsVO orderParamsVO);
	/**
	 * 查询订单明细的条数
	 * @param orderParamsVO
	 * @return
	 * @author zhangmengbin
	 */
	int queryCountTradeExportItemByPage(TradeOrderParamsVO orderParamsVO);

}