package com.need.task.service.distribution;

import com.need.task.common.enums.UserCommissionAccountOperateTypeEnum;
import com.need.task.dao.jdbc.api.trade.po.TradeBasePO;
import com.need.task.pub.Message;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author shenyb 2015年11月30日 上午11:06:23
 * @version V1.0
 * @ClassName UserCommissionService
 * @Description 用户佣金账户
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年11月30日
 * @modify by reason:{方法名}:{原因}
 */
public interface UserCommissionAccountService {
    Message increaseAccount(String userId, int commission, UserCommissionAccountOperateTypeEnum operateType, String tradeNo);
    Message returnCommissionToUserAccount(List<TradeBasePO> trades, UserCommissionAccountOperateTypeEnum operateType);

}
