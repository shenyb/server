package com.need.common.core.service.trade;

import com.need.common.core.pub.ServiceException;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.trade.CreateTradeBaseParamVO;
/**
 * 
 * <p></p>
 * @author shenyb 2015年12月3日 下午2:33:48
 * @ClassName IConfirmBuy
 * @Description 下单统一接口
 * @version V2.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年12月3日
 * @modify by reason:{方法名}:{原因}
 */
public interface IConfirmBuy {
	public Message createTrade(CreateTradeBaseParamVO param) throws ServiceException;
}
