package com.need.common.core.service.distribution;

import com.need.common.domain.enums.UserCommissionAccountOperateTypeEnum;
import com.need.common.domain.po.api.trade.TradeBasePO;
import com.need.common.domain.pub.Message;

import java.util.List;

/**
 * 
 * <p></p>
 * @author shenyb 2015年11月30日 上午11:06:23
 * @ClassName UserCommissionService
 * @Description 用户佣金账户
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年11月30日
 * @modify by reason:{方法名}:{原因}
 */
public interface UserCommissionAccountService {

	int getAvailableBalance(String userId, int totalPrice);

	Message decreaseAccount(String userId, int commission,
							UserCommissionAccountOperateTypeEnum operateType,
							String tradeNo);
	
	Message increaseAccount(String userId, int commission, UserCommissionAccountOperateTypeEnum operateType,String tradeNo);

	Message returnCommissionToUserAccount(List<TradeBasePO> trades,UserCommissionAccountOperateTypeEnum operateType);
}
