package com.need.task.dao.jdbc.api.coupon;

import com.need.task.dao.jdbc.api.coupon.po.CouponUserPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
    
    List<CouponUserPO> getCouponUserListByTradeNo(@Param("tradeNo")String tradeNo,@Param("couponStatue")String couponStatue);
    
    List<CouponUserPO> getCouponUserListByUserIdAndCost(Map<String, Object> params);
    
    int getCountByParams(Map<String, Object> param);
}