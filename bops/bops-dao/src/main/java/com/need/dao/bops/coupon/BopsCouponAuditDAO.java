package com.need.dao.bops.coupon;

import java.util.List;

import com.need.domain.po.bops.coupon.BopsCouponAuditPO;
import com.need.domain.vo.coupon.CouponAuditUserVO;

/**
 * 
 * @author 庆凯 2015-9-9 2015-9-9 11:56:58
 * @ClassName BopsCouponAuditDAO
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-9-9
 * @modify by reason:{方法名}:{原因}
 */

public interface BopsCouponAuditDAO {
    
    int deleteByPrimaryKey(Integer couponAuditId);

    int insert(BopsCouponAuditPO record);

    int insertSelective(BopsCouponAuditPO record);

    BopsCouponAuditPO selectByPrimaryKey(Integer couponAuditId);

    int updateByPrimaryKeySelective(BopsCouponAuditPO record);

    int updateByPrimaryKey(BopsCouponAuditPO record);
    
    List<CouponAuditUserVO> selectByCouponTemplateId(String couponTemplateId);
}