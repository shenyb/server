package com.need.dao.api.coupon;

import java.util.List;

import com.need.domain.po.api.coupon.CouponOpsListPO;
import com.need.domain.po.api.coupon.CouponTemplatePO;
import com.need.domain.vo.coupon.CouponOpsListVO;

public interface CouponOpsListDAO {
    int deleteByPrimaryKey(Integer couponId);

    int insert(CouponOpsListPO record);

    int insertSelective(CouponOpsListPO record);

    CouponOpsListPO selectByPrimaryKey(Integer couponId);

    int updateByPrimaryKeySelective(CouponOpsListPO record);

    int updateByPrimaryKey(CouponOpsListPO record);
    
    List<CouponOpsListVO> selectCouponOpsList();
    //优惠券模板
    List<CouponOpsListVO> selectCouponTemplate();
    //删除优惠券信息
    int deleteCouponOpsList();
    
}