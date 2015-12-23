package com.need.dao.bops.coupon;

import com.need.domain.po.bops.coupon.BopsCouponExchangeTemplateAuditPO;
import com.need.domain.vo.coupon.CouponExchangeAuditUserVO;
import java.util.List;

public interface BopsCouponExchangeTemplateAuditDAO {
    int deleteByPrimaryKey(Integer couponExchangeAuditId);

    int insert(BopsCouponExchangeTemplateAuditPO record);

    int insertSelective(BopsCouponExchangeTemplateAuditPO record);

    BopsCouponExchangeTemplateAuditPO selectByPrimaryKey(Integer couponExchangeAuditId);

    int updateByPrimaryKeySelective(BopsCouponExchangeTemplateAuditPO record);

    int updateByPrimaryKey(BopsCouponExchangeTemplateAuditPO record);

    List<CouponExchangeAuditUserVO> queryByCouponExchangeTemplateId(String couponExchangeTemplateId);
}