package com.need.dao.bops.coupon;

import com.need.domain.po.bops.coupon.BopsCouponExchangeTemplatePO;
import com.need.domain.vo.coupon.CouponExchangePageVO;
import com.need.domain.vo.coupon.CouponExchangeTemplateVO;
import java.util.List;

public interface BopsCouponExchangeTemplateDAO {
    int deleteByPrimaryKey(String couponExchangeId);

    int insert(BopsCouponExchangeTemplatePO record);

    int insertSelective(BopsCouponExchangeTemplatePO record);

    BopsCouponExchangeTemplatePO selectByPrimaryKey(String couponExchangeId);

    int updateByPrimaryKeySelective(BopsCouponExchangeTemplatePO record);

    int updateByPrimaryKey(BopsCouponExchangeTemplatePO record);
    
    List<BopsCouponExchangeTemplatePO> queryByCouponExchangeCode(String couponExchangeCode);

    CouponExchangeTemplateVO getCouponExchangeTemplateDetail(String couponExchangeId);

    Integer queryPageCouponExchangeTemplateCount(CouponExchangePageVO couponExchangePageVO);

    List<CouponExchangeTemplateVO> queryPageCouponExchangeTemplate(CouponExchangePageVO couponExchangePageVO);

    int updateFrozen(String couponExchangeId);
}