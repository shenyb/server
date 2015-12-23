/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Exchanges
 * and open the template in the editor.
 */

package com.need.operation.web.controller.coupon;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.common.validate.ValidatorUtil;
import com.need.dao.api.coupon.CouponTemplateDAO;
import com.need.dao.bops.coupon.BopsCouponTemplateDAO;
import com.need.domain.common.enums.CouponExchangeStatusEnum;
import com.need.domain.common.enums.CouponStatusEnum;
import com.need.domain.po.bops.coupon.BopsCouponExchangeTemplateAuditPO;
import com.need.domain.po.bops.coupon.BopsCouponExchangeTemplatePO;
import com.need.domain.po.bops.coupon.BopsCouponTemplatePO;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.vo.coupon.CouponExchangePageVO;
import com.need.domain.vo.coupon.CouponExchangeTemplateVO;
import com.need.operation.constant.ControllerMappings;
import com.need.operation.constant.ViewMappings;
import com.need.operation.pub.ConstantsProConfig;
import com.need.service.bops.coupon.BopsCouponExchangeService;
import com.need.service.constant.BizReturnCode;
import com.need.service.constant.Constants;
import com.need.utils.DateUtil;
import com.need.utils.StringUtil;
import java.text.ParseException;
import java.util.Date;

import org.springframework.ui.Model;
import com.need.dao.bops.coupon.BopsCouponExchangeTemplateDAO;
import com.need.dao.bops.user.BopsUserDAO;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 
 * @author 庆凯 2015-9-10 2015-9-10 19:05:47
 * @ClassName CouponManagerController
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-9-10
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.COUPON_EXCHANGE)
public class CouponExchangeManagerController {
    
	private static final Logger LOGGER = LoggerFactory.getLogger(CouponExchangeManagerController.class);
    
    @Autowired
    private BopsCouponExchangeTemplateDAO bopsCouponExchangeDAO;
    
    @Autowired
    private CouponTemplateDAO couponTemplateDAO;
    
    @Autowired
    private BopsCouponExchangeService bopsCouponExchangeService;
    
    @Autowired
    private BopsCouponTemplateDAO bopsCouponTemplateDAO;
    
	@Autowired
	private ConstantsProConfig constantsProConfig;
    
    @Autowired
    private BopsUserDAO bopsUserDAO;
	
	/**
	 * 
	 * @author liuhongyang 2015年10月27日 下午7:45:06
	 * @Method: addCouponExchange 
	 * @Description: 优惠券兑换码新增
	 * @param couponExchangeTemplate
	 * @param request
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value="/add")
	public Message addCouponExchangeTemplate(CouponExchangeTemplateVO couponExchangeTemplate, HttpServletRequest request) {
		LOGGER.info("in couponExchange addCouponExchange couponExchangeVO: " + couponExchangeTemplate);
	    if(StringUtil.isBlank(couponExchangeTemplate.getCouponTemplateIds())) {
            return Message.error(BizReturnCode.FIELD_VALIDATE_ERR);
        }
	    if(StringUtil.isBlank(couponExchangeTemplate.getEndTimeString())) {
            return Message.error(BizReturnCode.FIELD_VALIDATE_ERR);
        }
        try {
            couponExchangeTemplate.setEndTime(DateUtil.formatStrToDate(couponExchangeTemplate.getEndTimeString(), "yyyy-MM-dd HH:mm:ss"));
        } catch (ParseException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        BopsUser bopsUser = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
        couponExchangeTemplate.setBopsUserId(bopsUser.getUserId());
        if(couponExchangeTemplate.getCouponExchangeCount() <= 0) {
            couponExchangeTemplate.setCouponExchangeCount(1);
        }
        Message message = bopsCouponExchangeService.addCouponExchange(couponExchangeTemplate);
        return message;
    }

	/**
	 * 
	 * @author liuhongyang 2015年10月27日 下午7:45:23
	 * @Method: updateCouponExchange 
	 * @Description: 优惠券兑换码编辑
	 * @param couponExchangeTemplateVO
	 * @param request
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value="/update")
	public Message updateCouponExchangeTemplate(
            CouponExchangeTemplateVO couponExchangeTemplateVO, HttpServletRequest request) {
		LOGGER.info("in couponExchange updateCouponExchange couponExchangeTemplateVO: " + couponExchangeTemplateVO);
        if(StringUtil.isBlank(couponExchangeTemplateVO.getCouponExchangeCode())) {
            return Message.error(BizReturnCode.FIELD_VALIDATE_ERR);
        }
        if(StringUtil.isBlank(couponExchangeTemplateVO.getCouponTemplateIds())) {
            return Message.error(BizReturnCode.FIELD_VALIDATE_ERR);
        }
        if(StringUtil.isBlank(couponExchangeTemplateVO.getEndTimeString())) {
            return Message.error(BizReturnCode.FIELD_VALIDATE_ERR);
        }
        try {
            couponExchangeTemplateVO.setEndTime(DateUtil.formatStrToDate(couponExchangeTemplateVO.getEndTimeString(), "yyyy-MM-dd HH:mm:ss"));
        } catch (ParseException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        BopsCouponExchangeTemplatePO bopsCouponExchangePO=new BopsCouponExchangeTemplatePO();
        BeanUtils.copyProperties(couponExchangeTemplateVO, bopsCouponExchangePO);
        BopsUser bopsUser = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
        bopsCouponExchangePO.setBopsUserId(bopsUser.getUserId());
        bopsCouponExchangePO.setCreateTime(new Date());
        if(bopsCouponExchangePO.getCouponExchangeCount() <= 0) {
            bopsCouponExchangePO.setCouponExchangeCount(1);
        }
        Message message = bopsCouponExchangeService.updateCouponExchange(bopsCouponExchangePO);
        return message;
    }

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value="/audit")
	public Message auditCouponExchangeTemplate(BopsCouponExchangeTemplateAuditPO bopsCouponExchangeAuditPO, HttpServletRequest request) {

		LOGGER.info("in couponExchange auditCouponExchange bopsCouponExchangeAuditPO: " + bopsCouponExchangeAuditPO);
		Set<ConstraintViolation<BopsCouponExchangeTemplateAuditPO>> result = ValidatorUtil.getInstance().validate(bopsCouponExchangeAuditPO);
		if(result.size()>0){
			for(ConstraintViolation<?> c:result){
				return Message.error(BizReturnCode.FIELD_VALIDATE_ERR, c.getMessage());
			}
		}
        BopsUser bopsUser = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
        Message message = bopsCouponExchangeService.auditCouponExchange(bopsCouponExchangeAuditPO, bopsUser.getUserId());
        return message;
    }

	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT, value="/freeze/{couponExchangeTemplateId}")
	public Message freezeCouponExchangeTemplate(@PathVariable String couponExchangeTemplateId) {

		LOGGER.info("in couponExchange freezeCouponExchange couponExchangeTemplateId: " + couponExchangeTemplateId);
        try {
            Message message = bopsCouponExchangeService.freezeCouponExchangeTemplate(couponExchangeTemplateId);
            return message;
        } catch(NumberFormatException e) {
            return Message.error(BizReturnCode.FIELD_VALIDATE_ERR);
        }
    }

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value="/export/{couponExchangeTemplateId}")
	public void exportCouponExchangeTemplate(@PathVariable String couponExchangeTemplateId, HttpServletResponse response) {

		LOGGER.info("in couponExchange exportCouponExchange couponExchangeTemplateId: " + couponExchangeTemplateId);
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=" + System.currentTimeMillis() + ".xls");
			response.setContentType("application /  vnd.ms-excel; charset=utf-8");

			HSSFWorkbook workbook = bopsCouponExchangeService.exportCouponExchangeTemplate(couponExchangeTemplateId);
            workbook.write(os);
			os.flush();
		} catch (Exception e) {
			LOGGER.error("exportCouponExchange error", e);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					LOGGER.error(e.getMessage(), e);
				}
			}
		}
    }

	/**
	 * 
	 * @author liuhongyang 2015年10月27日 下午7:01:45
	 * @Method: getCouponExchange 
	 * @Description: 优惠券兑换码详情
	 * @param couponExchangeTemplateId
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.GET, value="/view/{couponExchangeTemplateId}")
	public String getCouponExchangeTemplate(@PathVariable String couponExchangeTemplateId, Model model) {
		LOGGER.info("in getCouponExchange getCouponExchangeDetial couponExchangeTemplateId: " + couponExchangeTemplateId);
		CouponExchangeTemplateVO couponExchangeTemplateVO = bopsCouponExchangeService.selectCouponExchangeDetial(couponExchangeTemplateId);
        List<BopsCouponTemplatePO> bopsCouponTemplateList = bopsCouponTemplateDAO.queryCouponTemplate();
		model.addAttribute("bopsCouponTemplateList", bopsCouponTemplateList);
        model.addAttribute("couponExchange", couponExchangeTemplateVO);
        model.addAttribute("templateList", couponExchangeTemplateVO.getCouponTemplateList());
		model.addAttribute("picHost", constantsProConfig.getPic_domain());
        model.addAttribute("list", couponTemplateDAO.queryByStatus(CouponStatusEnum.VALID.status));
        return ViewMappings.COUPON_EXCHANGE_VIEW;
    }
    
	/**
	 * 
	 * @author liuhongyang 2015年10月27日 下午7:02:04
	 * @Method: editCouponExchange 
	 * @Description: 跳转到编辑页面
	 * @param couponExchangeTemplateId
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.GET, value="/edit/{couponExchangeTemplateId}")
	public String editCouponExchangeTemplate(@PathVariable String couponExchangeTemplateId, Model model) {

		LOGGER.info("in couponExchange edit couponExchangeTemplateId: " + couponExchangeTemplateId);
        CouponExchangeTemplateVO couponExchangeTemplateVO = bopsCouponExchangeService.selectCouponExchangeDetial(couponExchangeTemplateId);
        if(couponExchangeTemplateVO == null || couponExchangeTemplateVO.getAuditStatus().equals(CouponExchangeStatusEnum.VALID.status) || 
        		couponExchangeTemplateVO.getAuditStatus().equals(CouponExchangeStatusEnum.FROZEN.status)) {
            return "";
        }
        List<BopsCouponTemplatePO> bopsCouponTemplateList = bopsCouponTemplateDAO.queryCouponTemplate();
		model.addAttribute("bopsCouponTemplateList", bopsCouponTemplateList);
        model.addAttribute("couponExchange", couponExchangeTemplateVO);
        model.addAttribute("templateList", couponExchangeTemplateVO.getCouponTemplateList());
		model.addAttribute("picHost", constantsProConfig.getPic_domain());
        model.addAttribute("list", couponTemplateDAO.queryByStatus(CouponStatusEnum.VALID.status));
        return ViewMappings.COUPON_EXCHANGE_EDIT;
    }
    
	@RequestMapping(method = RequestMethod.GET, value="/page")
	public String listCouponExchangeTemplate(CouponExchangePageVO couponExchangePageVO, Model model) {
		LOGGER.info("in couponExchange page couponExchange: " + couponExchangePageVO);
		if(couponExchangePageVO.getPage() == null) {
            couponExchangePageVO.setPage(1);
        }
        if(couponExchangePageVO.getPageSize() == null) {
            couponExchangePageVO.setPageSize(Integer.MAX_VALUE);
        }
        if(couponExchangePageVO.getAuditStatus() != null && couponExchangePageVO.getAuditStatus().trim().equals("")) {
            couponExchangePageVO.setAuditStatus(null);
        }
        couponExchangePageVO.setTotal(bopsCouponExchangeDAO.queryPageCouponExchangeTemplateCount(couponExchangePageVO));
        List<CouponExchangeTemplateVO> bopsCouponExchangeList = bopsCouponExchangeDAO.queryPageCouponExchangeTemplate(couponExchangePageVO);
        List<BopsUser> userList=bopsUserDAO.getOpsUser();
        model.addAttribute("userList", userList);
        model.addAttribute("list", bopsCouponExchangeList);
        model.addAttribute("page", couponExchangePageVO);
		model.addAttribute("picHost", constantsProConfig.getPic_domain());
        return ViewMappings.COUPON_EXCHANGE_LIST;
    }
	/**
	 *  
	 * @author liuhongyang 2015年10月27日 下午7:00:18
	 * @Method: toCouponTemplate 
	 * @Description: 跳转到新增页
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/toAdd")
	public String toCouponTemplateTemplate(Model model) {
		List<BopsCouponTemplatePO> bopsCouponTemplateList = bopsCouponTemplateDAO.queryCouponTemplate();
		model.addAttribute("list", bopsCouponTemplateList);
		model.addAttribute("picAddress", constantsProConfig.getPic_domain());
		return ViewMappings.COUPON_EXCHANGE_ADD;
	}
}
