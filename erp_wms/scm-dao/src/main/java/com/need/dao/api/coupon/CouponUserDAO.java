package com.need.dao.api.coupon;

import java.util.List;
import java.util.Map;

import com.need.domain.po.api.coupon.CouponUserPO;
import com.need.domain.vo.coupon.CouponPageVO;
import com.need.domain.vo.coupon.CouponUserResultVO;

/**
 * 
 * @author 庆凯 2015-9-9 2015-9-9 11:56:58
 * @ClassName CouponUserDAO
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-9-9
 * @modify by reason:{方法名}:{原因}
 */

public interface CouponUserDAO {
    
    int deleteByPrimaryKey(String couponNo);

    int insert(CouponUserPO record);

    int insertSelective(CouponUserPO record);

    CouponUserPO selectByPrimaryKey(String couponNo);

    int updateByPrimaryKeySelective(CouponUserPO record);

    int updateByPrimaryKey(CouponUserPO record);
    
    List<CouponUserPO> getCouponUserListByUserId(String userId);
    
    List<CouponUserPO> getCouponUserListByUserIdAndCost(Map<String, Object> params);
    
    int getCountByParams(Map<String, Object> param);
    
    List<CouponUserResultVO> queryPageCouponUser(CouponPageVO couponPageVO);
    
    List<CouponUserPO> getCouponUserListByTradeNo(String tradeNo);
}