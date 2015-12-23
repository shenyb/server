package com.need.marketing.dao.jdbc.api.coupon;

import java.util.List;

import com.need.marketing.dao.jdbc.api.coupon.po.CouponOpsListPO;
import com.need.marketing.web.controller.coupon.vo.CouponOpsListVO;

public interface CouponOpsListDAO {
    int deleteByPrimaryKey(Integer couponId);

    int insert(CouponOpsListPO record);

    int insertSelective(CouponOpsListPO record);

    CouponOpsListPO selectByPrimaryKey(Integer couponId);

    int updateByPrimaryKeySelective(CouponOpsListPO record);

    int updateByPrimaryKey(CouponOpsListPO record);

	List<CouponOpsListVO> selectCouponOpsList();
	
	List<CouponOpsListVO> selectCouponUserList(String userId);
}