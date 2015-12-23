package com.need.dao.bops.coupon;

import com.need.domain.po.bops.coupon.BopsCouponExchangePO;
import com.need.domain.vo.coupon.CouponExchangePageVO;
import com.need.domain.vo.coupon.CouponExchangeVO;
import java.util.List;

public interface BopsCouponExchangeDAO {
    int deleteByPrimaryKey(Integer couponExchangeId);

    int insert(BopsCouponExchangePO record);

    int insertSelective(BopsCouponExchangePO record);

    BopsCouponExchangePO selectByPrimaryKey(Integer couponExchangeId);

    int updateByPrimaryKeySelective(BopsCouponExchangePO record);

    int updateByPrimaryKey(BopsCouponExchangePO record);
    
    List<BopsCouponExchangePO> queryByCouponExchangeCode(String couponExchangeCode);

    CouponExchangeVO getCouponExchangeDetail(Integer couponExchangeId);

    Integer queryPageCouponExchangeCount(CouponExchangePageVO couponExchangePageVO);

    List<CouponExchangeVO> queryPageCouponExchange(CouponExchangePageVO couponExchangePageVO);

    int updateFrozen(Integer couponExchangeId);
}