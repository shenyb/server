package com.need.marketing.dao.jdbc.api.coupon;

import com.need.marketing.dao.jdbc.api.coupon.po.CouponTemplatePO;


/**
 * 
 * @author 庆凯 2015-9-9 2015-9-9 11:56:58
 * @ClassName CouponTemplateDAO
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-9-9
 * @modify by reason:{方法名}:{原因}
 */

public interface CouponTemplateDAO {
    
    int deleteByPrimaryKey(String couponTemplateId);

    int insert(CouponTemplatePO record);

    int insertSelective(CouponTemplatePO record);

    CouponTemplatePO selectByPrimaryKey(String couponTemplateId);

    int updateByPrimaryKeySelective(CouponTemplatePO record);

    int updateByPrimaryKey(CouponTemplatePO record);
}