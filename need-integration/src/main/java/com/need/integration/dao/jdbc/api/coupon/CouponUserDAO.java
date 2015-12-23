package com.need.integration.dao.jdbc.api.coupon;

import com.need.integration.dao.jdbc.api.coupon.po.CouponUserPO;

/**
 * 
 * @author 庆凯 2015-9-9 2015-9-9 11:56:58
 * @ClassName CouponUserDAO
 * @Description TODO
 * @version V1.1
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-9-9
 * @modify by reason:{方法名}:{原因}
 */

public interface CouponUserDAO {

	CouponUserPO getByTradeNo(String tradeNo);
}