/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.need.operation.web.controller.coupon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.common.validate.ValidatorUtil;
import com.need.dao.api.coupon.CouponMobileDAO;
import com.need.dao.api.coupon.CouponUserDAO;
import com.need.dao.bops.coupon.BopsCouponAuditDAO;
import com.need.dao.bops.coupon.BopsCouponTemplateDAO;
import com.need.dao.bops.goods.BopsGoodsDAO;
import com.need.dao.bops.user.BopsUserDAO;
import com.need.domain.common.enums.CouponStatusEnum;
import com.need.domain.po.bops.coupon.BopsCouponAuditPO;
import com.need.domain.po.bops.coupon.BopsCouponTemplatePO;
import com.need.domain.po.bops.goods.BopsGoods;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.vo.coupon.CouponAuditUserVO;
import com.need.domain.vo.coupon.CouponPageVO;
import com.need.domain.vo.coupon.CouponTemplateDetailVO;
import com.need.domain.vo.coupon.CouponTemplateResultVO;
import com.need.domain.vo.coupon.CouponUserResultVO;
import com.need.domain.vo.trade.TradeBaseParam;
import com.need.operation.constant.ControllerMappings;
import com.need.operation.constant.ViewMappings;
import com.need.operation.pub.ConstantsProConfig;
import com.need.service.bops.coupon.BopsCouponService;
import com.need.service.constant.BizReturnCode;
import com.need.service.constant.Constants;
import com.need.utils.DateUtil;
import com.need.utils.StringUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

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
@RequestMapping(value = ControllerMappings.COUPON)
public class CouponManagerController {
    
	private static final Logger LOGGER = LoggerFactory.getLogger(CouponManagerController.class);
    
    @Autowired
    private BopsCouponAuditDAO bopsCouponAuditDAO;
    
    @Autowired
    private BopsCouponTemplateDAO bopsCouponTemplateDAO;
    
    @Autowired
    private CouponUserDAO couponUserDAO;
    
    @Autowired
    private CouponMobileDAO couponMobileDAO;
    
    @Autowired
    private BopsCouponService bopsCouponService;
    
	@Autowired
	private ConstantsProConfig constantsProConfig;
    
	@Autowired
	private BopsUserDAO bopsUserDAO;
    
    @Autowired
    private BopsGoodsDAO goodsMainDAO;

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value="/add")
	public Message addCouponTemplate(CouponTemplateDetailVO bopsCouponTemplatePO, HttpServletRequest request) {
		LOGGER.info("in couponTemplate addCouponTemplate bopsCouponTemplatePO: " + bopsCouponTemplatePO);
        try {
            bopsCouponTemplatePO.setStartTime(DateUtil.formatStrToDate(bopsCouponTemplatePO.getStartTimeString(), "yyyy-MM-dd HH:mm:ss"));
        } catch (ParseException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        try {
            bopsCouponTemplatePO.setEndTime(DateUtil.formatStrToDate(bopsCouponTemplatePO.getEndTimeString(), "yyyy-MM-dd HH:mm:ss"));
        } catch (ParseException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        Message message = checkCouponTemplate(bopsCouponTemplatePO);
        if(message.getCode() != Message.SUCCESS) {
            return message;
        }
        if(!StringUtil.isBlank(bopsCouponTemplatePO.getGoodsSku())) {
            BopsGoods goods = goodsMainDAO.getByGoodsCode(bopsCouponTemplatePO.getGoodsSku());
            if(goods == null) {
                return Message.error(BizReturnCode.GOODS_NOT_EXIST);
            }
            bopsCouponTemplatePO.setGoodsId(goods.getGoodsId());
        }
        BopsUser bopsUser = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
        bopsCouponTemplatePO.setBopsUserId(bopsUser.getUserId());
        message = bopsCouponService.addCouponTemplate(bopsCouponTemplatePO);
        return message;
    }
    
    private Message checkCouponTemplate(CouponTemplateDetailVO bopsCouponTemplatePO) {
		Set<ConstraintViolation<CouponTemplateDetailVO>> result = ValidatorUtil.getInstance().validate(bopsCouponTemplatePO);
		if(result.size()>0){
			for(ConstraintViolation<?> c:result){
				return Message.error(BizReturnCode.FIELD_VALIDATE_ERR, c.getMessage());
			}
		}
        if(bopsCouponTemplatePO.getMinCost() != null && bopsCouponTemplatePO.getMinCost() > 0 && bopsCouponTemplatePO.getMinCost() <= bopsCouponTemplatePO.getValue()) {
            return Message.error(BizReturnCode.COUPON_VALUE_ERROR);
        }
        if(bopsCouponTemplatePO.getStartTime().after(bopsCouponTemplatePO.getEndTime()) || 
                bopsCouponTemplatePO.getStartTime().equals(bopsCouponTemplatePO.getEndTime()) || 
                bopsCouponTemplatePO.getEndTime().getTime() < System.currentTimeMillis()) {
            return Message.error(BizReturnCode.COUPON_TIME_ERROR);
        }
        return Message.success();
    }

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value="/update")
	public Message updateCouponTemplate(
            CouponTemplateDetailVO bopsCouponTemplatePO, HttpServletRequest request) {
		LOGGER.info("in couponTemplate updateCouponTemplate bopsCouponTemplatePO: " + bopsCouponTemplatePO);
        if(bopsCouponTemplatePO.getCouponTemplateId() == null) {
            return Message.error(BizReturnCode.FIELD_VALIDATE_ERR);
        }
        try {
            bopsCouponTemplatePO.setStartTime(DateUtil.formatStrToDate(bopsCouponTemplatePO.getStartTimeString(), "yyyy-MM-dd HH:mm:ss"));
        } catch (ParseException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        try {
            bopsCouponTemplatePO.setEndTime(DateUtil.formatStrToDate(bopsCouponTemplatePO.getEndTimeString(), "yyyy-MM-dd HH:mm:ss"));
        } catch (ParseException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        Message message = checkCouponTemplate(bopsCouponTemplatePO);
        if(message.getCode() != Message.SUCCESS) {
            return message;
        }
        if(!StringUtil.isBlank(bopsCouponTemplatePO.getGoodsSku())) {
            BopsGoods goods = goodsMainDAO.getByGoodsCode(bopsCouponTemplatePO.getGoodsSku());
            if(goods == null) {
                return Message.error(BizReturnCode.GOODS_NOT_EXIST);
            }
            bopsCouponTemplatePO.setGoodsId(goods.getGoodsId());
        }
        BopsUser bopsUser = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
        bopsCouponTemplatePO.setBopsUserId(bopsUser.getUserId());
        message = bopsCouponService.updateCouponTemplate(bopsCouponTemplatePO);
        return message;
    }

	@RequestMapping(method = RequestMethod.GET, value="/view/{couponTemplateId}")
	public String getCouponTemplate(@PathVariable String couponTemplateId, Model model) {
		LOGGER.info("in couponTemplate getCouponTemplate couponTemplateId: " + couponTemplateId);
        CouponTemplateDetailVO couponTemplateDetailVO = bopsCouponTemplateDAO.getCouponTemplateDetail(couponTemplateId);
        if(!StringUtil.isBlank(couponTemplateDetailVO.getGoodsId())) {
            BopsGoods goods = goodsMainDAO.selectByGoodsId(couponTemplateDetailVO.getGoodsId());
            if(goods != null) {
                couponTemplateDetailVO.setGoodsSku(goods.getGoodsCode());
            }
        }
        if(CouponStatusEnum.REJECT.status.equals(couponTemplateDetailVO.getCouponStatus())) {
            List<CouponAuditUserVO> bopsCouponAuditPOList = bopsCouponAuditDAO.selectByCouponTemplateId(couponTemplateId);
            if(bopsCouponAuditPOList != null && !bopsCouponAuditPOList.isEmpty()) {
                CouponAuditUserVO couponAudit = bopsCouponAuditPOList.get(0);
                couponTemplateDetailVO.setBopsUserId(couponAudit.getUserId());
                couponTemplateDetailVO.setUserName(couponAudit.getUserName());
                couponTemplateDetailVO.setUserDept(couponAudit.getUserDept());
                couponTemplateDetailVO.setRejectDescription(couponAudit.getDescription());
            }
        }
        model.addAttribute("coupon", couponTemplateDetailVO);
		model.addAttribute("picHost", constantsProConfig.getPic_domain());
        return ViewMappings.COUPON_VIEW;
    }

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value="/detail/{couponTemplateId}")
	public Message getCouponTemplateDetail(String couponTemplateId) {
		LOGGER.info("in couponTemplate detail couponTemplateId: " + couponTemplateId);
        CouponTemplateDetailVO couponTemplateDetailVO = bopsCouponTemplateDAO.getCouponTemplateDetail(couponTemplateId);
        if(!StringUtil.isBlank(couponTemplateDetailVO.getGoodsId())) {
            BopsGoods goods = goodsMainDAO.selectByGoodsId(couponTemplateDetailVO.getGoodsId());
            if(goods != null) {
                couponTemplateDetailVO.setGoodsSku(goods.getGoodsCode());
            }
        }
        Message message = Message.success();
        message.addData("couponTemplateDetail", couponTemplateDetailVO);
        return message;
    }

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value="/audit")
	public Message auditCouponTemplate(BopsCouponAuditPO bopsCouponAuditPO, HttpServletRequest request) {

		LOGGER.info("in couponTemplate addCouponTemplate bopsCouponAuditPO: " + bopsCouponAuditPO);
		Set<ConstraintViolation<BopsCouponAuditPO>> result = ValidatorUtil.getInstance().validate(bopsCouponAuditPO);
		if(result.size()>0){
			for(ConstraintViolation<?> c:result){
				return Message.error(BizReturnCode.FIELD_VALIDATE_ERR, c.getMessage());
			}
		}
        BopsUser bopsUser = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
        Message message = bopsCouponService.auditCouponTemplate(bopsCouponAuditPO, bopsUser.getUserId());
        return message;
    }
    
	@RequestMapping(method = RequestMethod.GET, value="/edit/{couponTemplateId}")
	public String editCouponTemplate(@PathVariable String couponTemplateId, Model model) {

		LOGGER.info("in couponTemplate edit couponTemplateId: " + couponTemplateId);
        CouponTemplateDetailVO couponTemplatePO = bopsCouponTemplateDAO.selectByPrimaryKey(couponTemplateId);
        if(couponTemplatePO == null || couponTemplatePO.getCouponStatus().equals(CouponStatusEnum.VALID.status) || 
                couponTemplatePO.getCouponStatus().equals(CouponStatusEnum.FROZEN.status)) {
            return "";
        }
        if(!StringUtil.isBlank(couponTemplatePO.getGoodsId())) {
            BopsGoods goods = goodsMainDAO.selectByGoodsId(couponTemplatePO.getGoodsId());
            if(goods != null) {
                couponTemplatePO.setGoodsSku(goods.getGoodsCode());
            }
        }
        model.addAttribute("coupon", couponTemplatePO);
		model.addAttribute("picHost", constantsProConfig.getPic_domain());
        if(StringUtil.isBlank(couponTemplatePO.getCouponPicKey())) {
            model.addAttribute("couponPicDisplay", "none");
            model.addAttribute("couponPicUploadDisplay", "block");
        } else {
            model.addAttribute("couponPicDisplay", "block");
            model.addAttribute("couponPicUploadDisplay", "none");
        }
        if(StringUtil.isBlank(couponTemplatePO.getDisabledPicKey())) {
            model.addAttribute("disabledPicDisplay", "none");
            model.addAttribute("disabledPicUploadDisplay", "block");
        } else {
            model.addAttribute("disabledPicDisplay", "block");
            model.addAttribute("disabledPicUploadDisplay", "none");
        }
        return ViewMappings.COUPON_EDIT;
    }
    
	@RequestMapping(method = RequestMethod.GET, value="/add")
	public String addCouponTemplate(Model model) {
		LOGGER.info("in couponTemplate get add couponTemplate");
		model.addAttribute("picHost", constantsProConfig.getPic_domain());
        return ViewMappings.COUPON_ADD;
    }

	@RequestMapping(method = RequestMethod.GET, value="/page")
	public String listCouponTemplate(CouponPageVO couponPageVO, Model model) {
		LOGGER.info("in couponTemplate page couponPageVO: " + couponPageVO);
        if(couponPageVO.getPage() == null) {
            couponPageVO.setPage(1);
        }
        if(couponPageVO.getPageSize() == null) {
            couponPageVO.setPageSize(Integer.MAX_VALUE);
        }
        if(couponPageVO.getCouponStatus() != null && couponPageVO.getCouponStatus().trim().equals("")) {
            couponPageVO.setCouponStatus(null);
        }
        couponPageVO.setTotal(bopsCouponTemplateDAO.queryPageCouponTemplateCount(couponPageVO));
        List<BopsCouponTemplatePO> bopsCouponTemplateList = bopsCouponTemplateDAO.queryPageCouponTemplate(couponPageVO);
        //modify by liuhongyang 修改优惠券列表展示增加申请人字段
        if(bopsCouponTemplateList.size()>0){
        	for (BopsCouponTemplatePO bopsCouponTemplatePO : bopsCouponTemplateList) {
				BopsUser bopsUser=bopsUserDAO.selectByPrimaryKey(bopsCouponTemplatePO.getBopsUserId());
				if(null != bopsUser){
					bopsCouponTemplatePO.setUserRealName(bopsUser.getUserRealName());
				}
			}
        }
        List<BopsUser> userList=bopsUserDAO.getOpsUser();
        model.addAttribute("userList", userList);
        model.addAttribute("list", bopsCouponTemplateList);
        model.addAttribute("page", couponPageVO);
		model.addAttribute("picHost", constantsProConfig.getPic_domain());
        return ViewMappings.COUPON_LIST;
    }

	@RequestMapping(method = RequestMethod.GET, value="/statistics")
	public String listCouponTemplateStatistics(CouponPageVO couponPageVO, Model model) {
		LOGGER.info("in couponTemplate statistics couponPageVO: " + couponPageVO);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("couponStatus", CouponStatusEnum.VALID);
        couponPageVO.setTotal(bopsCouponTemplateDAO.queryPageCouponTemplateCount(couponPageVO));
        List<BopsCouponTemplatePO> bopsCouponTemplateList = bopsCouponTemplateDAO.queryPageCouponTemplate(couponPageVO);
        List<CouponTemplateResultVO> couponTemplateList = new ArrayList<CouponTemplateResultVO>();
        for(BopsCouponTemplatePO bopsCouponTemplatePO : bopsCouponTemplateList) {
            CouponTemplateResultVO couponTemplateResultVO = new CouponTemplateResultVO();
            couponTemplateResultVO.setCouponTemplateNo(bopsCouponTemplatePO.getCouponTemplateNo());
            couponTemplateResultVO.setCouponTemplateId(bopsCouponTemplatePO.getCouponTemplateId());
            couponTemplateResultVO.setMaxCount(bopsCouponTemplatePO.getMaxCount());
            couponTemplateResultVO.setMinCost(bopsCouponTemplatePO.getMinCost());
            couponTemplateResultVO.setValue(bopsCouponTemplatePO.getValue());
            couponTemplateResultVO.setStartTime(bopsCouponTemplatePO.getStartTime());
            couponTemplateResultVO.setEndTime(bopsCouponTemplatePO.getEndTime());
            couponTemplateResultVO.setCouponStatus(bopsCouponTemplatePO.getCouponStatus());
            Map<String, Object> params1 = new HashMap<String, Object>();
            String couponTemplateId = bopsCouponTemplatePO.getCouponTemplateId();
            params1.put("couponTemplateId", couponTemplateId);
            int couponMobileCount = couponMobileDAO.getCountByParams(params1);
            Map<String, Object> params2 = new HashMap<String, Object>();
            params2.put("couponTemplateId", couponTemplateId);
            int couponUserCount = couponUserDAO.getCountByParams(params2);
            params2.put("couponStatus", CouponStatusEnum.USED);
            int useCount = couponUserDAO.getCountByParams(params2);
            couponTemplateResultVO.setReceiveCount(couponUserCount + couponMobileCount);
            couponTemplateResultVO.setUseCount(useCount);
            float rate = couponTemplateResultVO.getReceiveCount() == 0 ? 
                    0 : (float)couponTemplateResultVO.getUseCount() / couponTemplateResultVO.getReceiveCount();
            couponTemplateResultVO.setUseRate(StringUtil.convertToPercent(rate));
            couponTemplateList.add(couponTemplateResultVO);
        }
        model.addAttribute("list", couponTemplateList);
        model.addAttribute("page", couponPageVO);
		model.addAttribute("picHost", constantsProConfig.getPic_domain());
        return ViewMappings.COUPON_STATISTICS_LIST;
    }

	@RequestMapping(method = RequestMethod.GET, value="/statistics/detail")
	public String listCouponTemplateStatisticsDetail(CouponPageVO couponPageVO, Model model) {
		LOGGER.info("in couponTemplate statistics/detail couponPageVO: " + couponPageVO);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("couponStatus", CouponStatusEnum.USED.status);
        params.put("couponTemplateId", couponPageVO.getCouponTemplateId());
        couponPageVO.setTotal(couponUserDAO.getCountByParams(params));
        List<CouponUserResultVO> couponUserPOList = couponUserDAO.queryPageCouponUser(couponPageVO);
        model.addAttribute("list", couponUserPOList);
        model.addAttribute("page", couponPageVO);
		model.addAttribute("picHost", constantsProConfig.getPic_domain());
        return ViewMappings.COUPON_STATISTICS_DETAIL_LIST;
    }

	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT, value="/freeze/{couponTemplateId}")
	public Message frozen(@PathVariable String couponTemplateId) {
		LOGGER.info("in couponTemplate frozen couponTemplateId: " + couponTemplateId);
        Message message = bopsCouponService.freeze(couponTemplateId);
        return message;
    }

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value="/send")
	public Message send(@RequestParam String couponTemplateId,
            @RequestParam String mobile) {
		LOGGER.info("in couponTemplate send couponTemplateId:{} and mobile:{}", couponTemplateId, mobile);
        Message message = bopsCouponService.sendCoupon(couponTemplateId, mobile);
        return message;
    }
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/export")
	public void exportCouponExcel(
			HttpServletResponse response) {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		LOGGER.info("in couponTemplate statistics couponPageVO: " );
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("couponStatus", CouponStatusEnum.VALID);
        List<BopsCouponTemplatePO> bopsCouponTemplateList = bopsCouponTemplateDAO.queryCouponTemplate();
        List<CouponTemplateResultVO> couponTemplateList = new ArrayList<CouponTemplateResultVO>();
        for(BopsCouponTemplatePO bopsCouponTemplatePO : bopsCouponTemplateList) {
            CouponTemplateResultVO couponTemplateResultVO = new CouponTemplateResultVO();
            couponTemplateResultVO.setCouponTemplateNo(bopsCouponTemplatePO.getCouponTemplateNo());
            couponTemplateResultVO.setCouponTemplateId(bopsCouponTemplatePO.getCouponTemplateId());
            couponTemplateResultVO.setMaxCount(bopsCouponTemplatePO.getMaxCount());
            couponTemplateResultVO.setMinCost(bopsCouponTemplatePO.getMinCost());
            couponTemplateResultVO.setValue(bopsCouponTemplatePO.getValue());
            couponTemplateResultVO.setStartTime(bopsCouponTemplatePO.getStartTime());
            couponTemplateResultVO.setEndTime(bopsCouponTemplatePO.getEndTime());
            couponTemplateResultVO.setCouponStatus(bopsCouponTemplatePO.getCouponStatus());
            Map<String, Object> params1 = new HashMap<String, Object>();
            String couponTemplateId = bopsCouponTemplatePO.getCouponTemplateId();
            params1.put("couponTemplateId", couponTemplateId);
            int couponMobileCount = couponMobileDAO.getCountByParams(params1);
            Map<String, Object> params2 = new HashMap<String, Object>();
            params2.put("couponTemplateId", couponTemplateId);
            int couponUserCount = couponUserDAO.getCountByParams(params2);
            params2.put("couponStatus", CouponStatusEnum.USED);
            int useCount = couponUserDAO.getCountByParams(params2);
            couponTemplateResultVO.setReceiveCount(couponUserCount + couponMobileCount);
            couponTemplateResultVO.setUseCount(useCount);
            float rate = couponTemplateResultVO.getReceiveCount() == 0 ? 
                    0 : (float)couponTemplateResultVO.getUseCount() / couponTemplateResultVO.getReceiveCount();
            couponTemplateResultVO.setUseRate(StringUtil.convertToPercent(rate));
            couponTemplateList.add(couponTemplateResultVO);
        }
		
		LOGGER.info("exportCouponExcel.....in...");
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=" + System.currentTimeMillis() + ".xls");
			response.setContentType("application /  vnd.ms-excel; charset=utf-8");

			HSSFWorkbook workbook = bopsCouponService.exportCoupon(couponTemplateList);
			try {
				workbook.write(os);
			} catch (IOException e) {
				LOGGER.error(String.format("CouponManagerController exportTradeExcel write error", e.toString()));
				e.printStackTrace();
			}

			os.flush();
		} catch (Exception e) {
			LOGGER.error(String.format("CouponManagerController exportTradeExcel exportTrade error", e.toString()));
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					LOGGER.error(String.format("CouponManagerController exportTradeExcel os close error", e.toString()));
					e.printStackTrace();
				}
			}
		}

	}
	
}
