package com.need.marketing.web.controller.coupon;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.need.marketing.constant.ControllerMappings;
import com.need.marketing.constant.ViewMappings;
import com.need.marketing.dao.jdbc.api.coupon.CouponOpsListDAO;
import com.need.marketing.pub.Message;
import com.need.marketing.service.coupon.CouponOpsListService;
import com.need.marketing.service.coupon.CouponService;
import com.need.marketing.web.controller.coupon.vo.CouponOpsListVO;
import com.need.utils.StringUtil;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value =ControllerMappings.COUPON)
public class CouponManagermentController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CouponManagermentController.class);
	@Autowired
	private CouponOpsListService couponOpsListService;
    @Autowired
    private CouponService couponService;
    
    @Autowired
    private CouponOpsListDAO couponOpsListDAO;
	/**
	 * 
	 * @author liuhongyang 2015年10月29日 下午5:41:21
	 * @Method: getCouponList 
	 * @Description: 优惠券列表
	 * @param userId
	 * @param request
	 * @param model
	 * @return 
	 * @throws
	 */
	
	@RequestMapping(method = RequestMethod.GET, value="/page")
	public String getCouponList(@RequestParam(required = false)String userId, HttpServletRequest request, Model model) {
        if(StringUtil.isBlank(userId)) {
        	//用户没有登录时,默认展示所有优惠券
        	List<CouponOpsListVO> couponOpsList=couponOpsListDAO.selectCouponOpsList();
        	if(couponOpsList.size()>0){
        		 for (CouponOpsListVO couponOpsListVO : couponOpsList) {
                 	SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd");
     				String endMonth=sdf.format(couponOpsListVO.getEndTime());
     				couponOpsListVO.setEndMonth(endMonth);
                   	try {
                         Integer value=Integer.parseInt(couponOpsListVO.getValue());
                         //数据库金额单位为分,转换为元
                         value=value/100;
                         couponOpsListVO.setValue(value.toString());
                         Integer minCost=Integer.parseInt(couponOpsListVO.getMinCost());
                         minCost=minCost/100;
                         couponOpsListVO.setMinCost(minCost.toString());
                     } catch(NumberFormatException e) {
                         LOGGER.error("coupon integer cast error " + couponOpsListVO.toString(), e);
                     }
     			}
        		 model.addAttribute("list", couponOpsList);
             	return ViewMappings.LIST_COUPON;
        	}
        }
		List<CouponOpsListVO> couponList=couponOpsListService.getCouponUserList(userId);
		model.addAttribute("list", couponList);
		model.addAttribute("userId", userId);
		return ViewMappings.LIST_COUPON;
	}
	/**
	 * 
	 * @author liuhongyang 2015年10月30日 下午5:04:13
     * @param couponTemplateId
     * @param userId
	 * @Method: getCoupon 
	 * @Description: 领取优惠券
	 * @param request
	 * @param model
	 * @return	 */
	@RequestMapping(method = RequestMethod.POST, value="/getCoupon")
    @ResponseBody
	public Message getCoupon(
			@RequestParam(required = true) String couponTemplateId,
            @RequestParam(required = true) String userId, HttpServletRequest request, Model model) {
        return couponService.receive(couponTemplateId, userId);
	}
}
