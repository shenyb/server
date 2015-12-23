package com.need.task.dao.jdbc.api.trade;

import com.need.task.dao.jdbc.api.trade.po.TradeBasePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;



public interface TradeBaseDAO {
   
	/**
	 * @author Rylan 2015年8月18日 上午11:32:10
	 * @Method: queryTradeBaseByTradeState 
	 * @Description: TODO 根据交易状态查询交易信息
	 * @param tradeState
	 * @param interval 时间间隔
	 * @return 
	 * @throws
	 */
	List<String>  queryTradeBaseByTradeState(@Param("tradeState")String tradeState,@Param("interval")Integer interval,@Param("start")Integer start,@Param("pageSize")Integer pageSize);

	/**
	 * @author Rylan 2015年8月18日 下午2:30:40
	 * @Method: updateTradeStatelPriceByTradeNo 
	 * @Description: TODO 交易关闭
	 * @param orderNo
	 * @param tradeState
	 * @return 
	 * @throws
	 */
	int updateTradeCloseTradeNo(@Param("list")List<String> list); 
	
	/**
	 * @author Rylan 2015年8月18日 下午5:21:41
	 * @Method: updateTradeSuccessByTradeNo 
	 * @Description: TODO 交易成功并且修改交易时间
	 * @param tradeNo
	 * @param tradeState
	 * @return 
	 * @throws
	 */
	int updateTradeSuccessByTradeNo(@Param("list")List<String> list); 
	
	/**
	 * @author Rylan 2015年9月15日 上午11:57:19
	 * @Method: countTradeBaseByTradeState 
	 * @Description: 取得记录数
	 * @param tradeState
	 * @return 
	 * @throws
	 */
	int countTradeBaseByTradeState(@Param("tradeState")String tradeState,@Param("interval")Integer interval);
	/**
	 * 
	 * @author shenyb 2015年12月3日 下午5:19:57
	 * @Method: queryByTradeNo 
	 * @Description: 根据交易号获取列表
	 * @param tradeNo
	 * @return 
	 * @throws
	 */
	List<TradeBasePO> queryByTradeNo(String tradeNo);
	/*
	 * 修改订单的状态
	 *  @author zhangmengbin
	 */
	int updateISNormalByTradeNo(@Param("tradeNo") String tradeNo,@Param("isNormal") String isNormal);
	/**
	 * 查询缺货的订单
	 * @author zhangmengbin
	 * @return
	 */
	List<String> queryOutOfStoreTrade();
	List<TradeBasePO> queryTradeBaseByTradeStatus(String tradeStatus);
}