package com.need.dao.bops.coupon;

import com.need.domain.po.bops.coupon.BopsCouponExchangeAuditPO;
import com.need.domain.vo.coupon.CouponExchangeAuditUserVO;
import java.util.List;

public interface BopsCouponExchangeAuditDAO {
    int deleteByPrimaryKey(Integer couponExchangeId);

    int insert(BopsCouponExchangeAuditPO record);

    int insertSelective(BopsCouponExchangeAuditPO record);

    BopsCouponExchangeAuditPO selectByPrimaryKey(Integer couponExchangeId);

    int updateByPrimaryKeySelective(BopsCouponExchangeAuditPO record);

    int updateByPrimaryKey(BopsCouponExchangeAuditPO record);

    List<CouponExchangeAuditUserVO> queryByCouponExchangeId(String couponExchangeId);
}