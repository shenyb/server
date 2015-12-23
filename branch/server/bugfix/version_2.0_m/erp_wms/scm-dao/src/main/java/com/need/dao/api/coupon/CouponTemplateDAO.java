package com.need.dao.api.coupon;

import com.need.domain.po.api.coupon.CouponTemplatePO;
import java.util.List;


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

    int updateFrozen(String couponTemplateId);
    
    List<CouponTemplatePO> queryByStatus(String status);
}