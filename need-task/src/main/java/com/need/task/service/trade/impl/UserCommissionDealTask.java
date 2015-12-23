package com.need.task.service.trade.impl;

import com.github.pagehelper.PageHelper;
import com.need.task.common.enums.UserCommissionAccountOperateTypeEnum;
import com.need.task.common.enums.UserCommissionStatusEnum;
import com.need.task.constant.ConstantsTimeOut;
import com.need.task.dao.jdbc.api.distribution.UserCommissionAccountLogDAO;
import com.need.task.dao.jdbc.api.distribution.UserCommissionDAO;
import com.need.task.dao.jdbc.api.distribution.po.UserCommissionAccountLogPO;
import com.need.task.dao.jdbc.api.distribution.po.UserCommissionPO;
import com.need.task.dao.jdbc.api.trade.TradeBaseDAO;
import com.need.task.dao.jdbc.api.trade.po.TradeBasePO;
import com.need.task.pub.ConstantsProConfig;
import com.need.task.service.distribution.UserCommissionAccountService;
import com.need.trade.enums.TradeStatus;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * </p>
 *
 * @author shenyb 2015年12月3日 下午4:33:28
 * @version V1.0
 * @ClassName UserCommissionDealTask
 * @Description 佣金15天后变为可用状态，并操作余额账户的定时任务
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年12月3日
 * @modify by reason:{方法名}:{原因}
 */
@Component
public class UserCommissionDealTask {

    private static final Logger logger = Logger.getLogger(UserCommissionDealTask.class);
    @Autowired
    private ConstantsProConfig constantsProConfig;
    @Autowired
    private UserCommissionDAO userCommissionDAO;
    @Autowired
    private UserCommissionAccountService userCommissionAccountService;
    @Autowired
    private TradeBaseDAO tradeBaseDAO;
    @Autowired
    private UserCommissionAccountLogDAO userCommissionAccountLogDAO;
    private static int PAGE_SIZE = 20;

    //@Scheduled(cron = "${userCommissionDealTask}")
    @Transactional(value = "api_txManager")
    public void dealWaitToInCommissions() {
        logger.info("dealWaitToInCommissions in ..........................");

        PageHelper.startPage(1, PAGE_SIZE);
        //一 待处理的待入账列表
        List<UserCommissionPO> toDealWaitToInList = new ArrayList<UserCommissionPO>();
        // 待退款列表
        List<UserCommissionPO> toDealRefundList = new ArrayList<UserCommissionPO>();
        // 所有待入账列表
        List<UserCommissionPO> waitToInlist = userCommissionDAO.queryByWaitToInList();
        //处理待入账和退款
        for (UserCommissionPO userCommission : waitToInlist) {
            // 1查看是否退款
            List<TradeBasePO> tradeList = tradeBaseDAO.queryByTradeNo(userCommission.getTradeNo());
            if (tradeList == null || tradeList.size() == 0) {
                logger.info(String.format("dealWaitToInCommissions error:tradeList null or refund,userCommission,TradeNo:%s",
                        userCommission.getTradeNo()));
                continue;
            } else if (TradeStatus.REFUND_SUCCESS.code.equals(tradeList.get(0).getTradeStatus())) {
                toDealRefundList.add(userCommission);
                continue;
            }
            String shareId = tradeList.get(0).getDistributionShareId();
            int commission = 0;
            logger.info(String.format("shareId:%s,commission:%s",
                    shareId, commission));
            // 2查看时间是否超过15天
            if ((System.currentTimeMillis()
                    - userCommission.getCreateTime().getTime()) >Long.valueOf(constantsProConfig.getWaitToInTimeLimit().trim())) {
                toDealWaitToInList.add(userCommission);
                commission = userCommission.getCommission();
                if (commission > 0 && !StringUtils.isBlank(shareId)) {
                    userCommissionAccountService.increaseAccount(shareId, commission, UserCommissionAccountOperateTypeEnum.INCOME_BY_COMMISSION, tradeList.get(0).getTradeNo());
                }
            }
        }
        // 3批量更新用户余额表已入账和退款的状态
        Map<String, Object> map = new HashMap<String, Object>();
        if (toDealWaitToInList.size() > 0) {
            map.clear();
            map.put("list", toDealWaitToInList);
            map.put("commissionStatus", UserCommissionStatusEnum.HAS_INCOME.status);
            userCommissionDAO.updateBatchById(map);
        }
        if (toDealRefundList.size() > 0) {
            map.clear();
            map.put("list", toDealRefundList);
            map.put("commissionStatus", UserCommissionStatusEnum.FAIL_INCOME.status);
            userCommissionDAO.updateBatchById(map);
        }
        //二 扫描交易主表,如果使用了佣金购买,15天内退款了,则返还佣金
        PageHelper.startPage(1, PAGE_SIZE);
        List<TradeBasePO> refundList = tradeBaseDAO.queryTradeBaseByTradeStatus(TradeStatus.REFUND_SUCCESS.code);
        for (TradeBasePO trade : refundList) {
            int commission = trade.getCommission();
            UserCommissionAccountLogPO userCommissionAccountLog = userCommissionAccountLogDAO.getByUserIdAndOperateStatusAndTradeNo(trade.getBuyerId(), UserCommissionAccountOperateTypeEnum.TRADE_REFUND_SUCCESS.code, trade.getTradeNo());
            if (commission > 0 && userCommissionAccountLog == null) {
                userCommissionAccountService.increaseAccount(trade.getBuyerId(), commission, UserCommissionAccountOperateTypeEnum.TRADE_REFUND_SUCCESS, trade.getTradeNo());
            }
        }

        logger.info("dealWaitToInCommissions out ..........................");

    }
}
