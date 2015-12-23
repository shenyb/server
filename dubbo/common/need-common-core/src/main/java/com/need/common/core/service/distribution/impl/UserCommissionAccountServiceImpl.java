package com.need.common.core.service.distribution.impl;

import com.need.biz.utils.BizCodeUtil;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.constant.Constants;
import com.need.common.core.dao.jdbc.distribution.UserCommissionAccountDAO;
import com.need.common.core.dao.jdbc.distribution.UserCommissionAccountLogDAO;
import com.need.common.core.service.distribution.UserCommissionAccountService;
import com.need.common.domain.enums.UserCommissionAccountOperateTypeEnum;
import com.need.common.domain.enums.UserCommissionAccountStatusEnum;
import com.need.common.domain.po.api.distribution.UserCommissionAccountLogPO;
import com.need.common.domain.po.api.distribution.UserCommissionAccountPO;
import com.need.common.domain.po.api.trade.TradeBasePO;
import com.need.common.domain.pub.Message;
import com.need.utils.StringUtil;
import com.need.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author shenyb 2015年12月4日 下午1:58:41
 * @version V1.0
 * @ClassName UserCommissionAccountServiceImpl
 * @Description 用户佣金账户操作
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年12月4日
 * @modify by reason:{方法名}:{原因}
 */
@Component
public class UserCommissionAccountServiceImpl implements UserCommissionAccountService {
    @Autowired
    private UserCommissionAccountDAO userCommissionAccountDAO;
    @Autowired
    private UserCommissionAccountLogDAO userCommissionAccountLogDAO;

    /**
     * @param userId     用户id
     * @param totalPrice 总价
     * @return
     * @author shenyb 2015年12月4日 下午1:58:38
     * @Method: getAvailableBalance
     * @Description: 按照比例获取可用金额
     */
    @Override
    public int getAvailableBalance(String userId, int totalPrice) {
        int result = 0;
        //参数检查
        if (totalPrice <= 0 || StringUtil.isBlank(userId)) {
            return result;
        }
        //账户检查
        UserCommissionAccountPO account = userCommissionAccountDAO.getByUserId(userId);
        if (account == null || (account.getEndTime().getTime() < Calendar.getInstance().getTime().getTime())
                || !UserCommissionAccountStatusEnum.USE.status.equals(account.getAccountStatus())) {
            return result;
        }
        //余额检查
        int inCommission = account.getAccountBalance();
        if (inCommission <= 0) {
            return result;
        }
        //可用佣金计算
        int useCommission = new BigDecimal(totalPrice + "").multiply(BigDecimal.valueOf(Constants.DISCOUNT))
                .setScale(0, BigDecimal.ROUND_HALF_DOWN).intValue();
        return inCommission >= useCommission ? useCommission : inCommission;
    }

    /**
     * @param userId
     * @param commission
     * @return
     * @author shenyb 2015年12月4日 下午1:59:43
     * @Method: decreaseAccount
     * @Description: 出账
     */
    @Override
    @Transactional
    public Message decreaseAccount(String userId, int commission, UserCommissionAccountOperateTypeEnum operateType,String tradeNo) {
        Message message = Message.success();
        UserCommissionAccountPO account = userCommissionAccountDAO.getByUserId(userId);
        if (account == null) {
            return Message.error(BizReturnCode.USER_ACCOUNT_NOT_EXISTS);
        }
        if (account.getAccountBalance() < commission) {
            return Message.error(BizReturnCode.TRADE_COMMISSION_BALANCE_NOT_ENOUGH);
        }
        account.setAccountBalance(account.getAccountBalance() - commission);
        userCommissionAccountDAO.updateByPrimaryKey(account);
        // 记录日日志
        userCommissionAccountLogRecord(userId, commission, account, operateType,tradeNo);
        return message;

    }

    /**
     * @param userId
     * @param commission
     * @return
     * @author shenyb 2015年12月3日 下午2:07:56
     * @Method: increaseCommission
     * @Description:入账
     */
    @Override
    @Transactional
    public Message increaseAccount(String userId, int commission, UserCommissionAccountOperateTypeEnum operateType,String tradeNo) {
        Message message = Message.success();
        UserCommissionAccountPO account = userCommissionAccountDAO.getByUserId(userId);
        if (account == null) {
            account = new UserCommissionAccountPO();
            account.setAccountId(BizCodeUtil.generateAccountId(userId));
            account.setAccountBalance(commission);
            account.setUserId(userId);
            account.setAccountStatus(UserCommissionAccountStatusEnum.USE.status);
        } else {
            account.setAccountBalance(account.getAccountBalance() + commission);
        }
        userCommissionAccountDAO.updateByPrimaryKey(account);

        // 记录日日志
        userCommissionAccountLogRecord(userId, commission, account, operateType,tradeNo);
        return message;
    }

    @Override
    @Transactional
    public Message returnCommissionToUserAccount(List<TradeBasePO> trades,UserCommissionAccountOperateTypeEnum operateTypeEnum) {
        int useCommission = 0;
        for(TradeBasePO tradePO:trades){
            useCommission+=tradePO.getCommission();
        }
        if (useCommission > 0) {
            increaseAccount(trades.get(0).getBuyerId(), useCommission, operateTypeEnum,trades.get(0).getTradeNo());
        }
        return Message.success();
    }

    /**
     * @author shenyb 2015年12月4日 下午1:55:52 @Method:
     * userCommissionAccountLogRecord @Description: 记录日志 @param
     * userId @param commisson @param account @throws
     */

    protected void userCommissionAccountLogRecord(String userId, int commission, UserCommissionAccountPO account,
                                                  UserCommissionAccountOperateTypeEnum operateType,String tradeNo) {
        UserCommissionAccountLogPO record = new UserCommissionAccountLogPO();
        record.setAccountId(account.getAccountId());
        record.setOperateStatus(operateType);
        record.setUserId(userId);
        record.setPrice(commission);
        record.setTradeNo(tradeNo);
        userCommissionAccountLogDAO.insert(record);
        return;
    }
}
