package com.need.marketing.service.coupon.impl;


import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.need.marketing.dao.jdbc.api.coupon.CouponOpsListDAO;
import com.need.marketing.dao.jdbc.api.coupon.CouponUserDAO;
import com.need.marketing.service.coupon.CouponOpsListService;
import com.need.marketing.web.controller.coupon.vo.CouponOpsListVO;

@Service
public class CouponOpsListServiceImpl implements CouponOpsListService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CouponOpsListServiceImpl.class);
    @Autowired
    private CouponOpsListDAO couponOpsListDAO;
    @Autowired
    private CouponUserDAO couponUserDAO;
	@Override
	public List<CouponOpsListVO> getCouponUserList(String userId) {
		//查询优惠券列表
		List<CouponOpsListVO> CouponOpsList=couponOpsListDAO.selectCouponOpsList();
		//查询用户已领取优惠券
		List<CouponOpsListVO> couponUserList=couponOpsListDAO.selectCouponUserList(userId);
		if(CouponOpsList.size()>0){
			for (int i = 0; i < CouponOpsList.size(); i++) {
				CouponOpsListVO couponOpsListVO=CouponOpsList.get(i);
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd");
				String endMonth=sdf.format(couponOpsListVO.getEndTime());
				couponOpsListVO.setEndMonth(endMonth);
                try {
                    Integer value=Integer.parseInt(couponOpsListVO.getValue());
                    value=value/100;
                    couponOpsListVO.setValue(value.toString());
                    Integer minCost=Integer.parseInt(couponOpsListVO.getMinCost());
                    minCost=minCost/100;
                    couponOpsListVO.setMinCost(minCost.toString());
                } catch(NumberFormatException e) {
                    LOGGER.error("coupon integer cast error " + couponOpsListVO.toString(), e);
                }
				if(couponUserList.size()>0){
					for (int j = 0; j < couponUserList.size(); j++) {
						CouponOpsListVO couponUserVO=couponUserList.get(j);
						if(couponOpsListVO.getCouponId().equals(couponUserVO.getCouponId())){
							//flag为1表示用户已经领取
							couponOpsListVO.setFlag("1");
						}
						
					}
				}
			
			}
		}
		return CouponOpsList;
	}

  
}
