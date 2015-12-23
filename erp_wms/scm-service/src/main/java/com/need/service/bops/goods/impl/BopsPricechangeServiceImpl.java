package com.need.service.bops.goods.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.need.dao.bops.goods.BopsPricechangeDAO;
import com.need.dao.bops.goods.BopsPricechangeGoodsDAO;
import com.need.dao.bops.user.BopsUserDAO;
import com.need.domain.common.enums.PriceChangeAuditEnum;
import com.need.domain.common.enums.PriceChangeExcutedEnums;
import com.need.domain.common.enums.PriceChangeTypeEnum;
import com.need.domain.po.bops.goods.BopsPricechange;
import com.need.domain.po.bops.goods.BopsPricechangeGoods;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.vo.goods.GoodsPriceChangeParamVO;
import com.need.domain.vo.goods.GoodsPriceChangeResultVO;
import com.need.domain.vo.goods.GoodsPriceChangeVO;
import com.need.domain.vo.goods.GoodsPriceDetailParamVO;
import com.need.domain.vo.goods.GoodsPriceDetailResultVO;
import com.need.domain.vo.goods.GoodsPriceResultVO;
import com.need.service.bops.goods.BopsGoodsService;
import com.need.service.bops.goods.BopsPricechangeService;
import com.need.utils.NumberUtil;

@Service
public class BopsPricechangeServiceImpl implements BopsPricechangeService {

	@Autowired
	BopsPricechangeDAO bopsPricechangeDAO;
	@Autowired
	BopsPricechangeGoodsDAO bopsPricechangeGoodsDAO;
	@Autowired
	BopsUserDAO bopsUserDAO;
	@Autowired
	BopsGoodsService bopsGoodsService;
	
	
	@Override
	@Transactional
	public int savePricechange(List<GoodsPriceChangeVO> priceList, int userId, String pricechangeType,String startTime,
			String endTime, String mark) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		BopsPricechange bopsPricechange = new BopsPricechange();
		bopsPricechange.setExcuted(PriceChangeExcutedEnums.NEITHER.code);
		bopsPricechange.setPricechangeStatus(PriceChangeAuditEnum.DRAFT.code);
		bopsPricechange.setPricechangeType(pricechangeType);
		bopsPricechange.setBopsUserId(userId);
		bopsPricechange.setMark(mark);
		if(PriceChangeTypeEnum.SCHEDULE.code.equals(pricechangeType)){
			try {
				bopsPricechange.setStartTime(sdf.parse(startTime));
				bopsPricechange.setEndTime(sdf.parse(endTime));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		bopsPricechangeDAO.insertByPriceType(bopsPricechange);
		
		for(GoodsPriceChangeVO priceVO: priceList){
			BopsPricechangeGoods bopsPricechangeGoods = new BopsPricechangeGoods();
			bopsPricechangeGoods.setPricechangeId(bopsPricechange.getPricechangeId());
			bopsPricechangeGoods.setGoodsId(priceVO.getGoodsId());
			bopsPricechangeGoods.setStartPrice((int)(new BigDecimal(String.valueOf(priceVO.getDiscountPrice())).multiply(new BigDecimal(100D)).doubleValue()));
			bopsPricechangeGoods.setEndPrice(priceVO.getOriginalPrice() == null ? 0 : (int)(new BigDecimal(String.valueOf(priceVO.getOriginalPrice())).multiply(new BigDecimal(100D)).doubleValue()));
			bopsPricechangeGoods.setHistoryPurchasePrice(priceVO.getHistoryPurchasePrice());
			bopsPricechangeGoodsDAO.insertSelective(bopsPricechangeGoods);
		}
		
		return 0;
	}


	@Override
	public List<GoodsPriceChangeResultVO> queryPriceChange(GoodsPriceChangeParamVO goodsPriceChangeParamVO) {
		// TODO Auto-generated method stub
		if(StringUtils.hasText(goodsPriceChangeParamVO.getPricechangeId())){
			goodsPriceChangeParamVO.setPricechangeId(goodsPriceChangeParamVO.getPricechangeId().trim());
		}
		goodsPriceChangeParamVO.setTotal(bopsPricechangeDAO.countPriceChange(goodsPriceChangeParamVO));
		List<GoodsPriceChangeResultVO> priceList = bopsPricechangeDAO.queryPriceChange(goodsPriceChangeParamVO);
		if(priceList != null && priceList.size() > 0){
			for(GoodsPriceChangeResultVO resultVO : priceList){
				resultVO.setGoodsCount(bopsPricechangeGoodsDAO.countGoodsPrice(Integer.parseInt(resultVO.getPricechangeId())));
				if(StringUtils.hasText(resultVO.getAuditorId())){
					BopsUser bopsUser = bopsUserDAO.selectByPrimaryKey(Integer.parseInt(resultVO.getAuditorId()));
					if(bopsUser == null)
						continue;
					resultVO.setAuditorName(bopsUser.getUserRealName());
					resultVO.setAuditDept(bopsUser.getUserDept());
				}
			}
		}
		
		return priceList;
	}


	@Override
	public List<GoodsPriceResultVO> queryPriceList(int pricechangeId) {
		// TODO Auto-generated method stub
		List<GoodsPriceResultVO> priceList = bopsPricechangeGoodsDAO.queryPriceList(pricechangeId);
		if(priceList != null && priceList.size() > 0){
			for(GoodsPriceResultVO resultVO: priceList){
				resultVO.setProfit(NumberUtil.fen2Yuan(
						(resultVO.getDiscountPrice() == null ? 0D : resultVO.getDiscountPrice())
						- (resultVO.getPurchasePrice() == null ? 0D : resultVO.getPurchasePrice())
						).doubleValue());
				resultVO.setDiscountPrice(resultVO.getDiscountPrice() == null ? 0D : NumberUtil.fen2Yuan(resultVO.getDiscountPrice()).doubleValue());
				resultVO.setOriginalPrice(resultVO.getOriginalPrice() == null ? 0D : NumberUtil.fen2Yuan(resultVO.getOriginalPrice()).doubleValue());
				resultVO.setPurchasePrice(resultVO.getPurchasePrice() == null ? 0D : NumberUtil.fen2Yuan(resultVO.getPurchasePrice()).doubleValue());
			}
		}
		
		return priceList;
	}
	
	@Override
	public GoodsPriceChangeResultVO getPriceChangeById(int pricechangeId) {
		// TODO Auto-generated method stub
		GoodsPriceChangeResultVO resultVO =  bopsPricechangeDAO.getPriceChangeById(pricechangeId);
		resultVO.setGoodsCount(bopsPricechangeGoodsDAO.countGoodsPrice(Integer.parseInt(resultVO.getPricechangeId())));
		if(StringUtils.hasText(resultVO.getAuditorId())){
			BopsUser bopsUser = bopsUserDAO.selectByPrimaryKey(Integer.parseInt(resultVO.getAuditorId()));
			if(bopsUser != null)
			{
				resultVO.setAuditorName(bopsUser.getUserRealName());
				resultVO.setAuditDept(bopsUser.getUserDept());
			}
		}
		return resultVO;
	}
	
	@Override
	public List<GoodsPriceDetailResultVO> queryGoodsPriceDetailsList(GoodsPriceDetailParamVO param){
		param.setTotal(bopsPricechangeGoodsDAO.countGoodsPriceDetailList(param));
		List<GoodsPriceDetailResultVO> resultList = bopsPricechangeGoodsDAO.queryPriceDetailList(param);
		if(resultList != null && resultList.size() > 0){
			for(GoodsPriceDetailResultVO result: resultList){
//				result.setProfit(((result.getDiscountPrice() == null ? 0D : result.getDiscountPrice()) 
//						- (result.getPurchasePrice() == null ? 0D : result.getPurchasePrice()))/100);
//				result.setDiscountPrice(result.getDiscountPrice() == null ? 0D : result.getDiscountPrice()/100);
//				result.setOriginalPrice(result.getOriginalPrice() == null ? 0D : result.getOriginalPrice()/100);
//				result.setPurchasePrice(result.getPurchasePrice() == null ? 0D : result.getPurchasePrice()/100);
				
				result.setProfit(NumberUtil.fen2Yuan(
						(result.getDiscountPrice() == null ? 0D : result.getDiscountPrice())
						- (result.getPurchasePrice() == null ? 0D : result.getPurchasePrice())
						).doubleValue());
				result.setDiscountPrice(result.getDiscountPrice() == null ? 0D : NumberUtil.fen2Yuan(result.getDiscountPrice()).doubleValue());
				result.setOriginalPrice(result.getOriginalPrice() == null ? 0D : NumberUtil.fen2Yuan(result.getOriginalPrice()).doubleValue());
				result.setPurchasePrice(result.getPurchasePrice() == null ? 0D : NumberUtil.fen2Yuan(result.getPurchasePrice()).doubleValue());
			}
		}
		return resultList;
	}
	
	private int changPrice(Integer pricechangeId, String type){
		List<BopsPricechangeGoods> goodsPriceList =  bopsPricechangeGoodsDAO.queryPricePO(pricechangeId);
		
		if(PriceChangeExcutedEnums.STARTED.code.equals(type)){
			if(null != goodsPriceList && goodsPriceList.size() > 0){
				for(BopsPricechangeGoods pricechangeGoods : goodsPriceList){
					bopsGoodsService.updateChangeGoodsPrice(pricechangeGoods.getGoodsId(), pricechangeGoods.getStartPrice());
				}
			}
		}
		else{
			if(null != goodsPriceList && goodsPriceList.size() > 0){
				for(BopsPricechangeGoods pricechangeGoods : goodsPriceList){
					bopsGoodsService.updateChangeGoodsPrice(pricechangeGoods.getGoodsId(), pricechangeGoods.getEndPrice());
				}
			}
		}
		
		
		return 0;
	}
	
	@Override
	@Transactional
	public int updateExecute(String excuted, Integer pricechangeId){
		if(PriceChangeExcutedEnums.ENDED.code.equals(excuted)){
			changPrice(pricechangeId, PriceChangeExcutedEnums.ENDED.code);
			bopsPricechangeDAO.updateEndTime(pricechangeId);
		}
		else if(PriceChangeExcutedEnums.STARTED.code.equals(excuted)){
			changPrice(pricechangeId, PriceChangeExcutedEnums.STARTED.code);
			bopsPricechangeDAO.updateStartTime(pricechangeId);
		}
		bopsPricechangeDAO.updateExcuted(excuted, pricechangeId);
		
		return 0;
	}
	
	@Override
	public XSSFWorkbook exportPriceTemplate() {
		// TODO Auto-generated method stub
		XSSFWorkbook workBook = null;
		workBook = new XSSFWorkbook();// 创建工作薄
		XSSFSheet sheet = workBook.createSheet();
		workBook.setSheetName(0, "商品价格修改模板");
		XSSFFont font = workBook.createFont();
		font.setColor(XSSFFont.COLOR_NORMAL);
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		XSSFCellStyle cellStyle = workBook.createCellStyle();// 创建格式
		cellStyle.setFont(font);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		XSSFRow titleRow = sheet.createRow(0);// 第一行标题
		XSSFCell cell = titleRow.createCell(0, 0);
		cell.setCellStyle(cellStyle);
		cell.setCellType(XSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("商品编号");
		XSSFCell cell2 = titleRow.createCell(1, 0);
		cell2.setCellStyle(cellStyle);
		cell2.setCellType(XSSFCell.CELL_TYPE_STRING);
		cell2.setCellValue("Need价");
		XSSFCell cell3 = titleRow.createCell(2, 0);
		cell3.setCellStyle(cellStyle);
		cell3.setCellType(XSSFCell.CELL_TYPE_STRING);
		cell3.setCellValue("恢复价格");

		return workBook;
	}
	
	@Override
	@Transactional
	public int updateAudit(String audit, Integer pricechangeId, Integer auditorId){
		BopsPricechange pricechange = bopsPricechangeDAO.selectByPrimaryKey(pricechangeId);
		int result = bopsPricechangeDAO.updatePricechangeStatus(audit, pricechangeId, auditorId);
		if(PriceChangeAuditEnum.VALID.code.equals(audit) && PriceChangeTypeEnum.EXCUTE.code.equals(pricechange.getPricechangeType())){
			changPrice(pricechangeId, PriceChangeExcutedEnums.STARTED.code);
			bopsPricechangeDAO.updateExcuted(PriceChangeExcutedEnums.STARTED.code, pricechangeId);
		}
		return result;
	}

}
