package com.need.task.service.distribution.impl;

import com.need.biz.utils.BizCodeUtil;
import com.need.task.common.enums.UserAccountStatusEnum;
import com.need.task.common.enums.UserCommissionAccountOperateTypeEnum;
import com.need.task.dao.jdbc.api.distribution.UserCommissionAccountDAO;
import com.need.task.dao.jdbc.api.distribution.UserCommissionAccountLogDAO;
import com.need.task.dao.jdbc.api.distribution.po.UserCommissionAccountLogPO;
import com.need.task.dao.jdbc.api.distribution.po.UserCommissionAccountPO;
import com.need.task.dao.jdbc.api.trade.po.TradeBasePO;
import com.need.task.pub.Message;
import com.need.task.service.distribution.UserCommissionAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UserCommissionAccountServiceImpl implements UserCommissionAccountService {
    @Autowired
    private UserCommissionAccountDAO userCommissionAccountDAO;
    @Autowired
    private UserCommissionAccountLogDAO userCommissionAccountLogDAO;

    /**
     * 增加账户余额
     *
     * @param userId
     * @param commission
     * @param operateType
     * @param tradeNo
     * @return
     */
    @Override
    @Transactional(value = "api_txManager")
    public Message increaseAccount(String userId, int commission, UserCommissionAccountOperateTypeEnum operateType, String tradeNo) {
        Message message = Message.success();
        UserCommissionAccountPO account = userCommissionAccountDAO.getByUserId(userId);
        if (account == null) {
            account = new UserCommissionAccountPO();
            account.setAccountId(BizCodeUtil.generateAccountId(userId));
            account.setAccountBalance(commission);
            account.setAccountStatus(UserAccountStatusEnum.USE.code);
            account.setUserId(userId);
            userCommissionAccountDAO.insert(account);
        } else {
            account.setAccountBalance(account.getAccountBalance() + commission);
            userCommissionAccountDAO.updateByPrimaryKey(account);
        }
        userAccountLogRecord(userId, commission, account, operateType, tradeNo);
        return message;
    }

    private void userAccountLogRecord(String userId, int commission, UserCommissionAccountPO account, UserCommissionAccountOperateTypeEnum operateType, String tradeNo) {
        UserCommissionAccountLogPO record = new UserCommissionAccountLogPO();
        record.setAccountId(account.getAccountId());
        record.setOperateStatus(operateType);
        record.setUserId(userId);
        record.setPrice(commission);
        record.setTradeNo(tradeNo);
        userCommissionAccountLogDAO.insert(record);
    }


    @Override
    @Transactional(value = "api_txManager")
    public Message returnCommissionToUserAccount(List<TradeBasePO> trades, UserCommissionAccountOperateTypeEnum operateTypeEnum) {
        int useCommission = 0;
        for (TradeBasePO tradePO : trades) {
            useCommission += tradePO.getCommission();
        }
        if (useCommission > 0) {
            increaseAccount(trades.get(0).getBuyerId(), useCommission, operateTypeEnum, trades.get(0).getTradeNo());
        }
        return Message.success();
    }
}


