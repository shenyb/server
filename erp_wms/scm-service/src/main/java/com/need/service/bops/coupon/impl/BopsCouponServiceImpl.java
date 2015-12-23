/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.need.service.bops.coupon.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.need.biz.utils.BizCodeUtil;
import com.need.dao.api.coupon.CouponTemplateDAO;
import com.need.dao.api.coupon.CouponUserDAO;
import com.need.dao.api.user.UserBaseDAO;
import com.need.dao.bops.coupon.BopsCouponAuditDAO;
import com.need.dao.bops.coupon.BopsCouponTemplateDAO;
import com.need.domain.common.enums.CouponStatusEnum;
import com.need.domain.common.enums.CouponTypeEnum;
import com.need.domain.common.enums.WarehouseTypeEnum;
import com.need.domain.po.api.coupon.CouponTemplatePO;
import com.need.domain.po.api.coupon.CouponUserPO;
import com.need.domain.po.api.user.UserBase;
import com.need.domain.po.bops.coupon.BopsCouponAuditPO;
import com.need.domain.po.bops.coupon.BopsCouponTemplatePO;
import com.need.domain.po.bops.goods.BopsGoodsCategoriesPO;
import com.need.domain.pub.Message;
import com.need.domain.vo.coupon.CouponTemplateDetailVO;
import com.need.domain.vo.coupon.CouponTemplateResultVO;
import com.need.domain.vo.trade.TradeSendVO;
import com.need.service.bops.coupon.BopsCouponService;
import com.need.service.constant.BizReturnCode;
import com.need.trade.enums.TradeStatus;
import com.need.utils.ExcelUtil;

/**
 * 
 * @author 庆凯 2015-9-14 2015-9-14 13:29:19
 * @ClassName BopsCouponServiceImpl
 * @Description TODO
 * @version V1.1   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: 庆凯 2015-9-14
 * @modify by reason:{方法名}:{原因}
 */
@Service
public class BopsCouponServiceImpl implements BopsCouponService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BopsCouponServiceImpl.class);
    
    @Autowired
    private BopsCouponTemplateDAO bopsCouponTemplateDAO;
    
    @Autowired
    private BopsCouponAuditDAO bopsCouponAuditDAO;
    
    @Autowired
    private CouponTemplateDAO couponTemplateDAO;
    
    @Autowired
    private CouponUserDAO couponUserDAO;
    
    @Autowired
    private UserBaseDAO userBaseDAO;

    @Override
    public Message addCouponTemplate(BopsCouponTemplatePO bopsCouponTemplatePO) {
        if(!CouponStatusEnum.INVALID.status.equals(bopsCouponTemplatePO.getCouponStatus()) && 
                !CouponStatusEnum.DRAFT.status.equals(bopsCouponTemplatePO.getCouponStatus())) {
            return Message.error(BizReturnCode.COUPON_VALID_UPDATE);
        }
        bopsCouponTemplatePO.setCreateTime(Calendar.getInstance().getTime());
        bopsCouponTemplatePO.setCouponTemplateId(BizCodeUtil.generateCouponTemplateId(bopsCouponTemplatePO.getCouponTemplateNo()));
        List<BopsCouponTemplatePO> bopsCouponTemplateList = bopsCouponTemplateDAO.selectByCouponTemplateNo(bopsCouponTemplatePO.getCouponTemplateNo());
        if(bopsCouponTemplateList.size() > 0) {
            return Message.error(BizReturnCode.COUPON_NO_DUPLICATE);
        }
        if(bopsCouponTemplatePO.getMaxReceiveCount() == null) {
            bopsCouponTemplatePO.setMaxReceiveCount(0);
        }
        if(bopsCouponTemplatePO.getDailyCount()== null) {
            bopsCouponTemplatePO.setDailyCount(0);
        }
        if(bopsCouponTemplatePO.getMaxCount()== null) {
            bopsCouponTemplatePO.setMaxCount(0);
        }
        if(bopsCouponTemplatePO.getMinCost() == null) {
            bopsCouponTemplatePO.setMinCost(0);
        }
        try {
            bopsCouponTemplateDAO.insert(bopsCouponTemplatePO);
        } catch(Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Message.error(BizReturnCode.SYSTEM_ERR);
        }
        return Message.success();
    }

    @Override
    public Message updateCouponTemplate(BopsCouponTemplatePO bopsCouponTemplatePO) {
        if(!CouponStatusEnum.DRAFT.status.equals(bopsCouponTemplatePO.getCouponStatus()) && 
                !CouponStatusEnum.INVALID.status.equals(bopsCouponTemplatePO.getCouponStatus()) &&
                !CouponStatusEnum.REJECT.status.equals(bopsCouponTemplatePO.getCouponStatus())) {
            return Message.error(BizReturnCode.COUPON_VALID_UPDATE);
        }
        List<BopsCouponTemplatePO> bopsCouponTemplateList = bopsCouponTemplateDAO.selectByCouponTemplateNo(bopsCouponTemplatePO.getCouponTemplateNo());
        if(bopsCouponTemplateList.size() > 0 && !bopsCouponTemplateList.get(0).getCouponTemplateId().equals(bopsCouponTemplatePO.getCouponTemplateId())) {
            return Message.error(BizReturnCode.COUPON_NO_DUPLICATE);
        }
        if(bopsCouponTemplatePO.getMaxReceiveCount() == null) {
            bopsCouponTemplatePO.setMaxReceiveCount(0);
        }
        if(bopsCouponTemplatePO.getDailyCount()== null) {
            bopsCouponTemplatePO.setDailyCount(0);
        }
        if(bopsCouponTemplatePO.getMaxCount()== null) {
            bopsCouponTemplatePO.setMaxCount(0);
        }
        if(bopsCouponTemplatePO.getMinCost()== null) {
            bopsCouponTemplatePO.setMinCost(0);
        }
        try {
            bopsCouponTemplateDAO.updateByPrimaryKey(bopsCouponTemplatePO);
        } catch(Exception e) {
            return Message.error(BizReturnCode.SYSTEM_ERR);
        }
        return Message.success();
    }

    @Override
    @Transactional
    public Message auditCouponTemplate(BopsCouponAuditPO bopsCouponAuditPO, Integer userId) {
        bopsCouponAuditPO.setAuditUserId(userId);
        bopsCouponAuditPO.setCreateTime(Calendar.getInstance().getTime());
        bopsCouponAuditDAO.insert(bopsCouponAuditPO);
        CouponTemplateDetailVO bopsCouponTemplatePO = bopsCouponTemplateDAO.selectByPrimaryKey(bopsCouponAuditPO.getCouponTemplateId());
        if(bopsCouponTemplatePO == null) {
            return Message.error(BizReturnCode.COUPON_NOT_EXIST);
        }
        bopsCouponTemplatePO.setCouponStatus(bopsCouponAuditPO.getCouponStatus());
        bopsCouponTemplateDAO.updateByPrimaryKey(bopsCouponTemplatePO);
        if(CouponStatusEnum.VALID.status.equals(bopsCouponTemplatePO.getCouponStatus())) {
            CouponTemplatePO couponTemplatePO = new CouponTemplatePO();
            couponTemplatePO.setCouponTemplateId(bopsCouponTemplatePO.getCouponTemplateId());
            couponTemplatePO.setChannelIds(bopsCouponTemplatePO.getChannelIds());
            couponTemplatePO.setCouponName(bopsCouponTemplatePO.getCouponName());
            couponTemplatePO.setCouponPicKey(bopsCouponTemplatePO.getCouponPicKey());
            couponTemplatePO.setDisabledPicKey(bopsCouponTemplatePO.getDisabledPicKey());
            couponTemplatePO.setCouponRecommend(bopsCouponTemplatePO.getCouponRecommend());
            couponTemplatePO.setCouponRule(bopsCouponTemplatePO.getCouponRule());
            couponTemplatePO.setCouponShareTitle(bopsCouponTemplatePO.getCouponShareTitle());
            couponTemplatePO.setCouponStatus(bopsCouponTemplatePO.getCouponStatus());
            couponTemplatePO.setCouponTemplateNo(bopsCouponTemplatePO.getCouponTemplateNo());
            couponTemplatePO.setCouponType(bopsCouponTemplatePO.getCouponType());
            couponTemplatePO.setDailyCount(bopsCouponTemplatePO.getDailyCount());
            couponTemplatePO.setDescription(bopsCouponTemplatePO.getDescription());
            couponTemplatePO.setEndTime(bopsCouponTemplatePO.getEndTime());
            couponTemplatePO.setGoodsCategoryIds(bopsCouponTemplatePO.getGoodsCategoryIds());
            couponTemplatePO.setGoodsId(bopsCouponTemplatePO.getGoodsId());
            couponTemplatePO.setMaxCount(bopsCouponTemplatePO.getMaxCount());
            couponTemplatePO.setMaxReceiveCount(bopsCouponTemplatePO.getMaxReceiveCount());
            couponTemplatePO.setMinCost(bopsCouponTemplatePO.getMinCost());
            couponTemplatePO.setStartTime(bopsCouponTemplatePO.getStartTime());
            couponTemplatePO.setValue(bopsCouponTemplatePO.getValue());
            couponTemplateDAO.insert(couponTemplatePO);
        }
        return Message.success();
    }

    @Override
    public void useCouponPayedFail(String tradeNo) {
        List<CouponUserPO> couponList = couponUserDAO.getCouponUserListByTradeNo(tradeNo);
        if (couponList == null || couponList.isEmpty()) {
            return;
        }
        if (couponList.size() > 1) {
            LOGGER.error("multi coupon used in one trade which tradeNo:{}", tradeNo);
        }
        CouponUserPO coupon = couponList.get(0);
        if (!CouponStatusEnum.PRE_PAY.status.equals(coupon.getCouponStatus())) {
            return;
        }
        if (coupon.getTradeNo() == null) {
            return;
        }
        LOGGER.debug("useCouponPayedFail tradeNo:{}", tradeNo);
        coupon.setCouponStatus(CouponStatusEnum.NOT_USE.status);
        couponUserDAO.updateByPrimaryKey(coupon);
    }

    @Override
    public Message freeze(String couponTemplateId) {
        bopsCouponTemplateDAO.updateFrozen(couponTemplateId);
        couponTemplateDAO.updateFrozen(couponTemplateId);
        return Message.success();
    }

    @Override
    public Message sendCoupon(String couponTemplateId, String mobile) {
        CouponTemplatePO couponTemplate = couponTemplateDAO.selectByPrimaryKey(couponTemplateId);
        if(couponTemplate == null) {
            return Message.error(BizReturnCode.COUPONNO_NOT_EXIST);
        }
        if(!CouponStatusEnum.VALID.status.equals(couponTemplate.getCouponStatus())) {
            return Message.error(BizReturnCode.COUPON_INVALID);
        }
        UserBase user = userBaseDAO.selectUserBaseByMobile(mobile);
        if(user == null) {
            return Message.error(BizReturnCode.USERID_NOT_EXIST);
        }
        createCouponUserByTemplate(couponTemplate, user.getMobile(), user.getUserId(), null);
        return Message.success();
    }

    private void createCouponUserByTemplate(CouponTemplatePO couponTemplate, String mobile, String userId, String shareUserId) {
        CouponUserPO couponUserPO = new CouponUserPO();
        couponUserPO.setCouponName(couponTemplate.getCouponName());
        couponUserPO.setCouponNo(BizCodeUtil.generateCouponNo(mobile,couponTemplate.getCouponTemplateId()));
        couponUserPO.setCouponPicKey(couponTemplate.getCouponPicKey());
        couponUserPO.setDisabledPicKey(couponTemplate.getDisabledPicKey());
        couponUserPO.setCouponStatus(CouponStatusEnum.NOT_USE.status);
        couponUserPO.setCouponTemplateId(couponTemplate.getCouponTemplateId());
        couponUserPO.setCreateTime(Calendar.getInstance().getTime());
        couponUserPO.setDescription(couponTemplate.getDescription());
        couponUserPO.setEndTime(couponTemplate.getEndTime());
        couponUserPO.setMinCost(couponTemplate.getMinCost());
        couponUserPO.setStartTime(couponTemplate.getStartTime());
        couponUserPO.setCouponType(couponTemplate.getCouponType());
        couponUserPO.setGoodsId(couponTemplate.getGoodsId());
        if (couponTemplate.getCouponType().equals(CouponTypeEnum.USE_RETURN.status)) {
            couponUserPO.setShareUserId(shareUserId);
        }
        couponUserPO.setUserId(userId);
        couponUserPO.setValue(couponTemplate.getValue());
        couponUserDAO.insert(couponUserPO);
    }

    /**
     * 
     * @author peiboli 2015年12月2日 下午2:51:52
     * @Method: exportCoupon 
     * @Description: TODO导出优惠券
     * @return 
     * @see com.need.service.bops.coupon.BopsCouponService#exportCoupon()
     */
	@Override
	public HSSFWorkbook exportCoupon( List<CouponTemplateResultVO> couponTemplateList) {

		HSSFWorkbook workbook = getCoupon(couponTemplateList);
		return workbook;
	}

	public HSSFWorkbook getCoupon(List<CouponTemplateResultVO> data) {

		HSSFWorkbook workbook = new HSSFWorkbook();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(CouponTemplateResultVO couponTemplateResultVO:data){
			if(CouponStatusEnum.DRAFT.status.equals(couponTemplateResultVO.getCouponStatus())){
				couponTemplateResultVO.setCouponStatus(CouponStatusEnum.DRAFT.desc);	
			}
			if(CouponStatusEnum.REJECT.status.equals(couponTemplateResultVO.getCouponStatus())){
				couponTemplateResultVO.setCouponStatus(CouponStatusEnum.REJECT.desc);	
			}
			if(CouponStatusEnum.INVALID.status.equals(couponTemplateResultVO.getCouponStatus())){
				couponTemplateResultVO.setCouponStatus(CouponStatusEnum.INVALID.desc);	
			}
			if(CouponStatusEnum.VALID.status.equals(couponTemplateResultVO.getCouponStatus())){
				couponTemplateResultVO.setCouponStatus(CouponStatusEnum.VALID.desc);	
			}
			if(CouponStatusEnum.FROZEN.status.equals(couponTemplateResultVO.getCouponStatus())){
				couponTemplateResultVO.setCouponStatus(CouponStatusEnum.FROZEN.desc);	
			}
	
		}
		HSSFSheet sheet = ExcelUtil.createSheet(workbook, "1-sheet");
		// 创建表头
		Font font = ExcelUtil.createFont(workbook, (short) 5, HSSFColor.BLACK.index, (short) 10);
		CellStyle cellStylec = ExcelUtil.createBorderCellStyle(workbook, HSSFColor.WHITE.index, HSSFColor.WHITE.index,
				(short) 30, font);
		HSSFRow row = ExcelUtil.createRow(sheet, 0, 500);
		row.getSheet().setColumnWidth(0, 7000);
		row.getSheet().setColumnWidth(2, 7000);
		row.getSheet().setColumnWidth(3, 8000);
		HSSFCell cell = ExcelUtil.createCell(row, 0, cellStylec);
		cell.setCellValue("券模版编号");
		cell = ExcelUtil.createCell(row, 1, cellStylec);
		cell.setCellValue("申请数量");
		cell = ExcelUtil.createCell(row, 2, cellStylec);
		cell.setCellValue("领取数");
		cell = ExcelUtil.createCell(row, 3, cellStylec);
		cell.setCellValue("使用数");
		cell = ExcelUtil.createCell(row, 4, cellStylec);
		cell.setCellValue("使用率");
		cell = ExcelUtil.createCell(row, 5, cellStylec);
		cell.setCellValue("面额");
		cell = ExcelUtil.createCell(row, 6, cellStylec);
		cell.setCellValue("使用限额");
		cell = ExcelUtil.createCell(row, 7, cellStylec);
		cell.setCellValue("面向品类");
		cell = ExcelUtil.createCell(row, 8, cellStylec);
		cell.setCellValue("状态");
		cell = ExcelUtil.createCell(row, 9, cellStylec);
		cell.setCellValue("开始时间");
		cell = ExcelUtil.createCell(row, 10, cellStylec);
		cell.setCellValue("结束时间");
		
		// 插入实体数据
		CouponTemplateResultVO po = null;
		for (int i = 1; i < data.size() + 1; i++) {
			po = data.get(i - 1);
			row = ExcelUtil.createRow(sheet, i, 500);
			cell = ExcelUtil.createCell(row, 0, cellStylec);
			cell.setCellValue(po.getCouponTemplateNo());
			cell = ExcelUtil.createCell(row, 1, cellStylec);
			cell.setCellValue(po.getMaxCount());
			cell = ExcelUtil.createCell(row, 2, cellStylec);
			cell.setCellValue(po.getReceiveCount());
			cell = ExcelUtil.createCell(row, 3, cellStylec);
			cell.setCellValue(po.getUseCount());
			cell = ExcelUtil.createCell(row, 4, cellStylec);
			cell.setCellValue(po.getUseRate());
			cell = ExcelUtil.createCell(row, 5, cellStylec);
			cell.setCellValue(po.getValue());
			cell = ExcelUtil.createCell(row, 6, cellStylec);
			cell.setCellValue(po.getMinCost());
			cell = ExcelUtil.createCell(row, 7, cellStylec);
			cell.setCellValue("通用");
			cell = ExcelUtil.createCell(row, 8, cellStylec);
			cell.setCellValue(po.getCouponStatus());
			cell = ExcelUtil.createCell(row, 9, cellStylec);
			cell.setCellValue(sf.format(po.getStartTime()));
			cell = ExcelUtil.createCell(row, 10, cellStylec);
			cell.setCellValue(sf.format(po.getEndTime()));

		}
		return workbook;
	}
}
