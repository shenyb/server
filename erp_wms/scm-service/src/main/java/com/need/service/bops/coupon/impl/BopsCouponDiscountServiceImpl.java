/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Exchanges
 * and open the template in the editor.
 */

package com.need.service.bops.coupon.impl;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.need.dao.api.coupon.CouponExchangeDAO;
import com.need.dao.api.coupon.CouponOpsListDAO;
import com.need.dao.api.user.UserBaseDAO;
import com.need.dao.bops.coupon.BopsCouponExchangeAuditDAO;
import com.need.dao.bops.coupon.BopsCouponExchangeDAO;
import com.need.dao.bops.coupon.BopsCouponTemplateDAO;
import com.need.domain.common.enums.CouponExchangeStatusEnum;
import com.need.domain.po.api.coupon.CouponExchangePO;
import com.need.domain.po.api.coupon.CouponOpsListPO;
import com.need.domain.po.api.coupon.CouponTemplatePO;
import com.need.domain.po.bops.coupon.BopsCouponExchangeAuditPO;
import com.need.domain.po.bops.coupon.BopsCouponExchangePO;
import com.need.domain.po.bops.coupon.BopsCouponTemplatePO;
import com.need.domain.pub.Message;
import com.need.domain.vo.coupon.CouponExchangeVO;
import com.need.domain.vo.coupon.CouponOpsListVO;
import com.need.service.bops.coupon.BopsCouponDiscountService;
import com.need.service.bops.coupon.BopsCouponExchangeService;
import com.need.service.constant.BizReturnCode;


@Service
public class BopsCouponDiscountServiceImpl implements BopsCouponDiscountService {
    @Autowired
	private CouponOpsListDAO couponOpsListDAO;
	
    private static final Logger LOGGER = LoggerFactory.getLogger(BopsCouponDiscountServiceImpl.class);

	@Override
	public List<CouponOpsListVO> getCouponOpsList() {
		List<CouponOpsListVO> couponOpsList=couponOpsListDAO.selectCouponOpsList();
		if(couponOpsList.size()>0){
			for (int i = 0; i < couponOpsList.size(); i++) {
				CouponOpsListVO couponOpsListVO=couponOpsList.get(i);
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd");
				String endMonth=sdf.format(couponOpsListVO.getEndTime());
				String startMonth=sdf.format(couponOpsListVO.getStartTime());
				couponOpsListVO.setEndMonth(endMonth);
				couponOpsListVO.setStartMonth(startMonth);
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
			
			}
		}
		return couponOpsList;
	}

	@Override
	public List<CouponOpsListVO> toCouponOpsListEdit() {
		//模板列表
		List<CouponOpsListVO> couponOpsList=couponOpsListDAO.selectCouponTemplate();
		//查询优惠券列表
		List<CouponOpsListVO> couponTemplateList=couponOpsListDAO.selectCouponOpsList();
		CouponOpsListVO couponOpsListVO=new CouponOpsListVO();
		CouponOpsListVO couponTemplateVO=new CouponOpsListVO();
		if(couponOpsList.size()>0){
			for (int i = 0; i < couponOpsList.size(); i++) {
				couponOpsListVO=couponOpsList.get(i);
				if(couponTemplateList.size()>0){
					for (int j = 0; j < couponTemplateList.size(); j++) {
						couponTemplateVO=couponTemplateList.get(j);
						if(couponOpsListVO.getCouponTemplateId().equals(couponTemplateVO.getCouponTemplateId())){
							couponOpsListVO.setFlag("1");
						}
					}
				}
			
			}
		}
		return couponOpsList;
	}

	@Override
	@Transactional
	public Message updateCouponDidscount(CouponOpsListPO couponOpsListPO) {
		String templateList=couponOpsListPO.getCouponTemplateId();
		couponOpsListDAO.deleteCouponOpsList();
		String[] arrayList=templateList.split(",");
		for (int i = 0; i < arrayList.length; i++) {
			couponOpsListPO.setCouponTemplateId(arrayList[i]);
			couponOpsListDAO.insert(couponOpsListPO);
		}
		return Message.success();
	}

}
