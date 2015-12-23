package com.need.task.pub;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p></p>
 *
 * @author Rylan 2015年9月10日 上午11:30:46
 * @version V1.0
 * @ClassName ConfigProperties
 * @Description 读取constants.properties属性文件配置信息
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年9月10日
 * @modify by reason:{方法名}:{原因}
 */

@Component
public class ConstantsProConfig {

    @Value("${waitPayToTradeClose}")
    private String waitPayToTradeClose;

    @Value("${waitReceiveToTradeSuccess}")
    private String waitReceiveToTradeSuccess;

    @Value("${tradePageSize}")
    private String tradePageSize;//订单分页参数

    @Value("${goodsPriceStartTime}")
    private String goodsPriceStartTime;
    @Value("${goodsPriceEndTime}")
    private String goodsPriceEndTime;
    @Value("${waitToInTimeLimit}")
    private String waitToInTimeLimit;

    public String getWaitToInTimeLimit() {
        return waitToInTimeLimit;
    }

    public String getWaitPayToTradeClose() {
        return waitPayToTradeClose;
    }

    public String getWaitReceiveToTradeSuccess() {
        return waitReceiveToTradeSuccess;
    }

    public String getTradePageSize() {
        return tradePageSize;
    }

    public String getGoodsPriceStartTime() {
        return goodsPriceStartTime;
    }

    public String getGoodsPriceEndTime() {
        return goodsPriceEndTime;
    }


}
