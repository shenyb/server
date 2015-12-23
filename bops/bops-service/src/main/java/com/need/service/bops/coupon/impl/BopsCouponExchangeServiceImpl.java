/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Exchanges
 * and open the template in the editor.
 */

package com.need.service.bops.coupon.impl;

import com.need.biz.utils.BizCodeUtil;
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
import com.need.dao.bops.coupon.BopsCouponTemplateDAO;
import com.need.domain.common.enums.CouponExchangeStatusEnum;
import com.need.domain.po.api.coupon.CouponExchangePO;
import com.need.domain.po.bops.coupon.BopsCouponExchangeTemplateAuditPO;
import com.need.domain.po.bops.coupon.BopsCouponExchangeTemplatePO;
import com.need.domain.po.bops.coupon.BopsCouponTemplatePO;
import com.need.domain.pub.Message;
import com.need.domain.vo.coupon.CouponExchangeTemplateVO;
import com.need.domain.vo.coupon.CouponTemplateDetailVO;
import com.need.service.bops.coupon.BopsCouponExchangeService;
import com.need.service.constant.BizReturnCode;
import com.need.dao.bops.coupon.BopsCouponExchangeTemplateAuditDAO;
import com.need.dao.bops.coupon.BopsCouponExchangeTemplateDAO;
import com.need.domain.common.enums.PayChannelEnum;
import com.need.domain.vo.coupon.CouponPageVO;
import com.need.service.constant.Constants;
import com.need.utils.ExcelUtil;
import com.need.utils.RandomStringUtil;
import com.need.utils.StringUtil;
import java.text.SimpleDateFormat;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

/**
 * 
 * @author 庆凯 2015-9-14 2015-9-14 13:29:19
 * @ClassName BopsCouponExchangeServiceImpl
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-9-14
 * @modify by reason:{方法名}:{原因}
 */
@Service
public class BopsCouponExchangeServiceImpl implements BopsCouponExchangeService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BopsCouponExchangeServiceImpl.class);
    
    @Autowired
    private BopsCouponExchangeTemplateDAO bopsCouponExchangeTemplateDAO;
    
    @Autowired
    private BopsCouponExchangeTemplateAuditDAO bopsCouponExchangeAuditDAO;
    
    @Autowired
    private CouponExchangeDAO couponExchangeDAO;
    
    @Autowired
    private BopsCouponTemplateDAO bopsCouponTemplateDAO;

    @Override
    public Message addCouponExchange(BopsCouponExchangeTemplatePO bopsCouponExchangeTemplatePO) {
        if(!CouponExchangeStatusEnum.INVALID.status.equals(bopsCouponExchangeTemplatePO.getAuditStatus()) && 
                !CouponExchangeStatusEnum.DRAFT.status.equals(bopsCouponExchangeTemplatePO.getAuditStatus())) {
            return Message.error(BizReturnCode.COUPON_VALID_UPDATE);
        }
        if(bopsCouponExchangeTemplatePO.getCouponExchangeCount() > Constants.MAX_COUPON_EXCHANGE_COUNT) {
            return Message.error(BizReturnCode.COUPON_EXCHANGE_COUNT_TOO_MUCH);
        }
        List<CouponExchangePO> couponExchangeList = couponExchangeDAO.queryByCouponExchangeCode(bopsCouponExchangeTemplatePO.getCouponExchangeCode());
        if(couponExchangeList.size() > 0) {
            return Message.error(BizReturnCode.COUPON_EXCHANGE_CODE_DUPLICATE);
        }
        bopsCouponExchangeTemplatePO.setCreateTime(Calendar.getInstance().getTime());
        bopsCouponExchangeTemplatePO.setCouponExchangeTemplateId(BizCodeUtil.generateCouponTemplateId(bopsCouponExchangeTemplatePO.getCouponTemplateIds()));
        try {
            bopsCouponExchangeTemplateDAO.insert(bopsCouponExchangeTemplatePO);
        } catch(Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Message.error(BizReturnCode.SYSTEM_ERR);
        }
        return Message.success();
    }

    @Override
    public Message updateCouponExchange(BopsCouponExchangeTemplatePO bopsCouponExchangeTemplatePO) {
        if(!CouponExchangeStatusEnum.DRAFT.status.equals(bopsCouponExchangeTemplatePO.getAuditStatus()) && 
                !CouponExchangeStatusEnum.INVALID.status.equals(bopsCouponExchangeTemplatePO.getAuditStatus())) {
            return Message.error(BizReturnCode.COUPON_VALID_UPDATE);
        }
        if(bopsCouponExchangeTemplatePO.getCouponExchangeCount() > Constants.MAX_COUPON_EXCHANGE_COUNT) {
            return Message.error(BizReturnCode.COUPON_EXCHANGE_COUNT_TOO_MUCH);
        }
        List<BopsCouponExchangeTemplatePO> bopsCouponExchangeList = bopsCouponExchangeTemplateDAO.queryByCouponExchangeCode(bopsCouponExchangeTemplatePO.getCouponExchangeCode());
        if(bopsCouponExchangeList.size() > 0 && !bopsCouponExchangeList.get(0).getCouponExchangeTemplateId().equals(bopsCouponExchangeTemplatePO.getCouponExchangeTemplateId())) {
            return Message.error(BizReturnCode.COUPON_EXCHANGE_CODE_DUPLICATE);
        }
        try {
            bopsCouponExchangeTemplateDAO.updateByPrimaryKey(bopsCouponExchangeTemplatePO);
        } catch(Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Message.error(BizReturnCode.SYSTEM_ERR);
        }
        return Message.success();
    }

    @Override
    public Message freezeCouponExchangeTemplate(String couponExchangeTemplateId) {
        bopsCouponExchangeTemplateDAO.updateFrozen(couponExchangeTemplateId);
        couponExchangeDAO.updateFrozenByTemplateId(couponExchangeTemplateId);
        return Message.success();
    }

	private String splitString(String value) {
		String[] valueArray = value.split(",");
		StringBuffer changeValue = new StringBuffer("");
		for (int i = 0; i < valueArray.length; i++) {
			if (i != valueArray.length - 1) {
				changeValue.append("'").append(valueArray[i]).append("',");
			} else {
				changeValue.append("'").append(valueArray[i]).append("'");
			}
		}

		return changeValue.toString();
	}

    @Override
    public HSSFWorkbook exportCouponExchangeTemplate(String couponExchangeTemplateId) {
		HSSFWorkbook workbook = new HSSFWorkbook();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HSSFSheet sheet = ExcelUtil.createSheet(workbook, "1-sheet");
		// 创建表头
        Font font = ExcelUtil.createFont(workbook, (short) 5, HSSFColor.BLACK.index, (short) 10);
        CellStyle cellStylec = ExcelUtil.createBorderCellStyle(workbook, HSSFColor.WHITE.index, HSSFColor.WHITE.index, (short) 30, font);
        HSSFRow row = ExcelUtil.createRow(sheet, 0, 500);
		row.getSheet().setColumnWidth(0, 8000);
		row.getSheet().setColumnWidth(1, 3000);
		row.getSheet().setColumnWidth(2, 16000);
		row.getSheet().setColumnWidth(3, 5000);
		row.getSheet().setColumnWidth(4, 5000);
        HSSFCell cell = ExcelUtil.createCell(row, 0, cellStylec);
		cell.setCellValue("批次ID");
		cell = ExcelUtil.createCell(row, 1, cellStylec);
		cell.setCellValue("优惠券兑换码");
		cell = ExcelUtil.createCell(row, 2, cellStylec);
		cell.setCellValue("优惠券模板名称");
		cell = ExcelUtil.createCell(row, 3, cellStylec);
		cell.setCellValue("开始时间");
		cell = ExcelUtil.createCell(row, 4, cellStylec);
		cell.setCellValue("结束时间");
        BopsCouponExchangeTemplatePO bopsCouponExchangeTemplatePO = bopsCouponExchangeTemplateDAO.selectByPrimaryKey(couponExchangeTemplateId);
        if(bopsCouponExchangeTemplatePO == null || StringUtil.isBlank(bopsCouponExchangeTemplatePO.getCouponTemplateIds())) {
            return workbook;
        }
        String ids = splitString(bopsCouponExchangeTemplatePO.getCouponTemplateIds());
        CouponPageVO couponPageVO = new CouponPageVO();
        couponPageVO.setCouponTemplateIds(ids);
        List<BopsCouponTemplatePO> templates = bopsCouponTemplateDAO.queryCouponTemplateByTemplateIds(couponPageVO);
        StringBuilder tempalteNames = new StringBuilder();
        for(BopsCouponTemplatePO template : templates) {
            tempalteNames.append(template.getCouponName());
            tempalteNames.append(" | ");
        }
        List<CouponExchangePO> couponExchangeList = couponExchangeDAO.queryByCouponExchangeTemplateId(couponExchangeTemplateId);
		for (int i = 1; i < couponExchangeList.size() + 1; i++) {
			row = ExcelUtil.createRow(sheet, i, 500);
			cell = ExcelUtil.createCell(row, 0, cellStylec);
			cell.setCellValue(couponExchangeTemplateId);
			cell = ExcelUtil.createCell(row, 1, cellStylec);
            CouponExchangePO couponExchangePO = couponExchangeList.get(i - 1);
			cell.setCellValue(couponExchangePO.getCouponExchangeCode());
			cell = ExcelUtil.createCell(row, 2, cellStylec);
			cell.setCellValue(tempalteNames.toString());
			cell = ExcelUtil.createCell(row, 3, cellStylec);
			cell.setCellValue(sf.format(bopsCouponExchangeTemplatePO.getStartTime()));
			cell = ExcelUtil.createCell(row, 4, cellStylec);
			cell.setCellValue(sf.format(bopsCouponExchangeTemplatePO.getEndTime()));
			
		}
		return workbook;
    }

    @Override
    @Transactional
    public Message auditCouponExchange(BopsCouponExchangeTemplateAuditPO bopsCouponExchangeAuditPO, Integer userId) {
        bopsCouponExchangeAuditPO.setAuditUserId(userId);
        bopsCouponExchangeAuditPO.setAuditTime(Calendar.getInstance().getTime());
        bopsCouponExchangeAuditDAO.insert(bopsCouponExchangeAuditPO);
        BopsCouponExchangeTemplatePO bopsCouponExchangeTemplatePO = bopsCouponExchangeTemplateDAO.selectByPrimaryKey(bopsCouponExchangeAuditPO.getCouponExchangeTemplateId());
        if(bopsCouponExchangeTemplatePO == null) {
            return Message.error(BizReturnCode.COUPON_NOT_EXIST);
        }
        if(bopsCouponExchangeTemplatePO.getCouponExchangeCount() > Constants.MAX_COUPON_EXCHANGE_COUNT) {
            return Message.error(BizReturnCode.COUPON_EXCHANGE_COUNT_TOO_MUCH);
        }
        bopsCouponExchangeTemplatePO.setAuditStatus(bopsCouponExchangeAuditPO.getAuditStatus());
        bopsCouponExchangeTemplateDAO.updateByPrimaryKey(bopsCouponExchangeTemplatePO);
        if(CouponExchangeStatusEnum.VALID.status.equals(bopsCouponExchangeTemplatePO.getAuditStatus())) {
            long time = System.currentTimeMillis();
            LOGGER.info("------------------------start create " + bopsCouponExchangeTemplatePO.getCouponExchangeCount() + " couponExchange------------------------");
            for(int i = 0; i < bopsCouponExchangeTemplatePO.getCouponExchangeCount(); i++) {
                CouponExchangePO couponExchangePO = new CouponExchangePO();
                couponExchangePO.setCouponExchangeTemplateId(bopsCouponExchangeTemplatePO.getCouponExchangeTemplateId());
                if(bopsCouponExchangeTemplatePO.getCouponExchangeCount() == 1) {
                    couponExchangePO.setCouponExchangeCode(bopsCouponExchangeTemplatePO.getCouponExchangeCode());
                } else {
                    int j = 10;
                    while(j > 0) {
                        String code = RandomStringUtil.getRandom(6);
                        if(couponExchangeDAO.queryByCouponExchangeCode(code).isEmpty()) {
                            couponExchangePO.setCouponExchangeCode(code);
                            break;
                        }
                        j--;
                        LOGGER.info("random coupon exchange code duplicate" + (10 - j) + "times which code is " + code);
                    }
                }
                couponExchangePO.setCouponExchangeType(bopsCouponExchangeTemplatePO.getCouponExchangeType());
                couponExchangePO.setCouponTemplateIds(bopsCouponExchangeTemplatePO.getCouponTemplateIds());
                couponExchangePO.setStartTime(bopsCouponExchangeTemplatePO.getStartTime());
                couponExchangePO.setEndTime(bopsCouponExchangeTemplatePO.getEndTime());
                couponExchangePO.setCouponExchangeStatus(CouponExchangeStatusEnum.VALID.status);
                couponExchangeDAO.insert(couponExchangePO);
            }
            LOGGER.info("------------------------end create couponExchange cost:" + (System.currentTimeMillis() - time) + "------------------------");
        }
        return Message.success();
    }
    
    /**
     * 
     * @author liuhongyang 2015年10月27日 上午11:47:30
     * @Method: selectCouponExchangeDetial 
     * @Description: 优惠券兑换码编辑
     * @param couponExchangeTemplateId
     * @return 
     * @see com.need.service.bops.coupon.BopsCouponExchangeService#selectCouponExchangeDetial(java.lang.String)
     */
	@Override
	@Transactional
	public CouponExchangeTemplateVO selectCouponExchangeDetial(String couponExchangeTemplateId) {
		BopsCouponExchangeTemplatePO bopsCouponExchangeTemplatePO = bopsCouponExchangeTemplateDAO.selectByPrimaryKey(couponExchangeTemplateId);
		String couponTemplateIds =bopsCouponExchangeTemplatePO.getCouponTemplateIds();
		String [] couponTemplateIdArray=couponTemplateIds.split(",");
		List<BopsCouponTemplatePO> couponTemplateList=new ArrayList<BopsCouponTemplatePO>();
		CouponExchangeTemplateVO couponExchangeVO =new CouponExchangeTemplateVO();
        for (String couponTemplateId : couponTemplateIdArray) {
            CouponTemplateDetailVO bopsCouponTemplatePO = bopsCouponTemplateDAO.selectByPrimaryKey(couponTemplateId);
            couponTemplateList.add(bopsCouponTemplatePO);
        }
		couponExchangeVO.setCouponTemplateList(couponTemplateList);
		BeanUtils.copyProperties(bopsCouponExchangeTemplatePO, couponExchangeVO);
		return couponExchangeVO;
	}

}
