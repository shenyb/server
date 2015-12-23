package com.need.share.dao.jdbc.api.coupon;

import java.util.List;
import java.util.Map;

import com.need.share.dao.jdbc.api.coupon.po.CouponReceiveInfoPO;

public interface CouponReceiveInfoDAO {
    
    int deleteByPrimaryKey(Integer id);

    int insert(CouponReceiveInfoPO record);

    int insertSelective(CouponReceiveInfoPO record);

    CouponReceiveInfoPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CouponReceiveInfoPO record);

    int updateByPrimaryKey(CouponReceiveInfoPO record);
    
    List<CouponReceiveInfoPO> getCouponReceiveInfoByParam(Map<String, Object> param);
    
    Integer getCountByParams(Map<String, Object> param);
}