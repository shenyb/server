package com.need.dao.bops.coupon;

import java.util.List;

import com.need.domain.po.bops.coupon.BopsCouponTemplatePO;
import com.need.domain.vo.coupon.CouponPageVO;
import com.need.domain.vo.coupon.CouponTemplateDetailVO;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author 庆凯 2015-9-9 2015-9-9 11:56:58
 * @ClassName BopsCouponTemplateDAO
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-9-9
 * @modify by reason:{方法名}:{原因}
 */

public interface BopsCouponTemplateDAO {
    
    int deleteByPrimaryKey(String couponTemplateId);

    int insert(BopsCouponTemplatePO record);

    int insertSelective(BopsCouponTemplatePO record);

    CouponTemplateDetailVO selectByPrimaryKey(String couponTemplateId);

    int updateByPrimaryKeySelective(BopsCouponTemplatePO record);

    int updateByPrimaryKey(BopsCouponTemplatePO record);
    
    List<BopsCouponTemplatePO> queryPageCouponTemplate(CouponPageVO couponPageVO);
    
    int queryPageCouponTemplateCount(CouponPageVO couponPageVO);
    
    CouponTemplateDetailVO getCouponTemplateDetail(String couponTemplateId);
    
    List<BopsCouponTemplatePO> selectByCouponTemplateNo(String CouponTemplateNo);

    int updateFrozen(String couponTemplateId);
    
    List<BopsCouponTemplatePO> queryCouponTemplate();
    
    List<BopsCouponTemplatePO> queryCouponTemplateByTemplateIds(CouponPageVO couponPageVO);
}