package com.need.service.bops.trade;

import java.io.InputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.need.domain.po.api.trade.TradeBasePO;
import com.need.domain.po.bops.trade.BopsTradeLogistics;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.vo.trade.BatchSendTradeParamVO;
import com.need.domain.vo.trade.CallcenterPageVO;
import com.need.domain.vo.trade.OrderExportParamVO;
import com.need.domain.vo.trade.TradeBaseParam;
import com.need.domain.vo.trade.TradeBaseVO;
import com.need.domain.vo.trade.TradeOrderParamsVO;
import com.need.service.common.exception.ServiceException;

/**
 * 
 * <p></p>
 * @author shenyb 2015年8月15日 下午12:01:55
 * @ClassName TradeBaseService
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年8月15日
 * @modify by reason:{方法名}:{原因}
 */
@Service
public interface BopsTradeBaseService {
	List<TradeBaseVO> queryPageByParam(TradeBaseParam param);
	
	/**
	 * @author Rylan 2015年10月29日 下午6:24:12
	 * @Method: queryPageByParamForCallCenter 
	 * @Description: 针对callcenter系统查询用户手机号
	 * @param param
	 * @return 
	 * @throws
	 */
	List<TradeBaseVO> queryPageByParamForCallCenter(TradeBaseParam param);
	
	/**
	 *业务逻辑内部封装 
	 * 
	 * @param po
	 * @return
	 * @throws ServiceException
	 */
	Message send(BopsTradeLogistics po) throws ServiceException;
	
	/**
	 * 
	 * @author xiehao 2015年12月17日 下午7:33:11
	 * @Method: reduceStore 
	 * @Description: TODO 发货时扣减库存和记录库存流水
	 * @param tradeNo
	 */
	public Message reduceStore(String tradeNo);
	
	/**
	 * 
	 * @author xiehao 2015年12月18日 下午3:20:48
	 * @Method: reduceStoreOfFefund 
	 * @Description: TODO 待发货时客户退款扣减冻结库存
	 * @param tradeList
	 */
	public Message reduceStoreOfFefund(List<TradeBasePO> tradeList);

	Message refund(String tradeNo, int refundAmmount, String memo,BopsUser user);

	HSSFWorkbook exportTrade(TradeBaseParam param);

	HSSFWorkbook exportOrder(TradeOrderParamsVO orderParamsVO);
	
	/**
	 * @author xiehao 2015年9月16日 上午10:36:19
	 * @Method: exportOrder_v1_1 
	 * @Description: TODO 导出明细v1.1
	 * @param orderParamsVO
	 */
	HSSFWorkbook exportOrder_v1_1(TradeOrderParamsVO orderParamsVO);
	
	HSSFWorkbook exportTradeOrder_v1_1(OrderExportParamVO orderParamsVO);
	
	XSSFWorkbook exportTradeBatchTemplate();

	List<BatchSendTradeParamVO> excelToList(InputStream in, Boolean is07Or10);

	List<TradeBaseVO> queryPageCallCenterByMobile(CallcenterPageVO param);
	/**
	 * 导出订单列表
	 * @author zhangmengbin
	 * @param param
	 * @return
	 */
	HSSFWorkbook exportTradeList(TradeBaseParam param);
}
