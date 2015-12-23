package com.need.task.service.trade.impl;

import com.need.task.common.enums.UserCommissionAccountOperateTypeEnum;
import com.need.task.constant.ConstantsTimeOut;
import com.need.task.dao.jdbc.api.trade.TradeBaseDAO;
import com.need.task.dao.jdbc.api.trade.po.TradeBasePO;
import com.need.task.pub.ConstantsProConfig;
import com.need.task.pub.Page;
import com.need.task.service.coupon.CouponService;
import com.need.task.service.distribution.UserCommissionAccountService;
import com.need.trade.enums.TradeStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <p>订单过期处理</p>
 *
 * @author Rylan 2015年8月17日 下午3:21:43
 * @version V1.0
 * @ClassName TradeTimeOutDealTask
 * @Description
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年8月17日
 * @modify by reason:{方法名}:{原因}
 */
@Component
public class TradeTimeOutDealTask {

    private static final Logger logger = Logger.getLogger(TradeTimeOutDealTask.class);

    @Autowired
    private TradeBaseDAO tradeBaseDAO;

    @Autowired
    private ConstantsProConfig constantsProConfig;

    @Autowired
    private CouponService couponService;
    @Autowired
    private UserCommissionAccountService userCommissionAccountService;

    /**
     * @throws
     * @author Rylan 2015年8月17日 下午3:55:36
     * @Method: dealWaitPayToTradeClose
     * @Description: TODO  处理待支付的订单到交易关闭—每30分钟处理一次
     */
    //@Scheduled(cron = "${waitPayToTradeClose}")
    @Transactional(value = "api_txManager")
    public void dealWaitPayToTradeClose() {
        logger.info("dealWaitPayToTradeClose  in ..");
        Page page = new Page();//分页参数
        //获取等待付款的记录总数
        int tradeCount = tradeBaseDAO.countTradeBaseByTradeState(TradeStatus.WAIT_PAY.code, ConstantsTimeOut.WAIT_ORDER_EXPIRE_TIME);
        if (tradeCount == 0) {
            logger.info("tradeCount is 0 . continue this ");
            return;
        }
        logger.info("begin to deal WAIT_PAY trade . the count is : " + tradeCount);
        page.setTotal(tradeCount);//设置总数
        page.setPageSize(Integer.parseInt(constantsProConfig.getTradePageSize())); //设置查询个数
        for (int i = 0; i < page.getPageCount(); i++) {//对每页数据进行处理
            logger.info("deal page is :" + page);
            List<String> tradeNoList = tradeBaseDAO.queryTradeBaseByTradeState(TradeStatus.WAIT_PAY.code, ConstantsTimeOut.WAIT_ORDER_EXPIRE_TIME, page.getStart(), page.getPageSize());
            if (tradeNoList == null || tradeNoList.size() == 0) {
                page.setPage(page.getPage() + 1);
                logger.debug("tradeNoList is null continue");
                continue;
            }
            logger.info("tradeNoList  size is :" + tradeNoList.size());
            tradeBaseDAO.updateTradeCloseTradeNo(tradeNoList);//修改状态
            // 返还佣金
            for (String tradeNo : tradeNoList) {
                List<TradeBasePO> trades = tradeBaseDAO.queryByTradeNo(tradeNo);
                if (trades != null && trades.size() > 0) {
                    userCommissionAccountService.returnCommissionToUserAccount(trades, UserCommissionAccountOperateTypeEnum.TRADE_CLOSE_SUCCESS);
                }
            }
            couponService.useCouponPayedFail(tradeNoList);//返还优惠券
            page.setPage(page.getPage() + 1);
        }

        page = null;
    }

    /**
     * @throws
     * @author Rylan 2015年8月17日 下午3:55:49
     * @Method: dealWaitReceiveToTradeSuccess
     * @Description: TODO 处理带收货的订单到交易成功 每天处理一次
     */
    //@Scheduled(cron = "${waitReceiveToTradeSuccess}")
    @Transactional(value = "api_txManager")
    public void dealWaitReceiveToTradeSuccess() {
        logger.info("dealWaitReceiveToTradeSuccess  in ..");
        Page page = new Page();//分页参数
        //获取等待收货的记录总数
        int tradeCount = tradeBaseDAO.countTradeBaseByTradeState(TradeStatus.WAIT_RECEIVE.code, ConstantsTimeOut.WAIT_RECEIVE_EXPIRE_TIME);
        if (tradeCount == 0) {
            logger.info("tradeCount is 0 . continue this ");
            return;
        }
        logger.info("begin to deal WAIT_RECEIVE trade . the count is : " + tradeCount);
        page.setTotal(tradeCount);//设置总数
        page.setPageSize(Integer.parseInt(constantsProConfig.getTradePageSize())); //设置查询个数
        for (int i = 0; i < page.getPageCount(); i++) {//对每页数据进行处理
            logger.info("deal page is :" + page);
            List<String> tradeNoList = tradeBaseDAO.queryTradeBaseByTradeState(TradeStatus.WAIT_RECEIVE.code, ConstantsTimeOut.WAIT_RECEIVE_EXPIRE_TIME, page.getStart(), page.getPageSize());
            if (tradeNoList == null || tradeNoList.size() == 0) {
                page.setPage(page.getPage() + 1);
                logger.debug("tradeNoList is null continue");
                continue;
            }
            logger.info("tradeNoList  size is :" + tradeNoList.size());
            tradeBaseDAO.updateTradeSuccessByTradeNo(tradeNoList);//修改状态
            page.setPage(page.getPage() + 1);
        }

    }

}
