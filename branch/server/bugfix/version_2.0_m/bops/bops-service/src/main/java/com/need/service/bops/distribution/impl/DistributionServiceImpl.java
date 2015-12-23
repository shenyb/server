package com.need.service.bops.distribution.impl;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.need.biz.utils.CurrencyUtil;
import com.need.dao.api.distribution.ApiDistributionGoodsDAO;
import com.need.dao.api.distribution.UserCommissionDAO;
import com.need.dao.api.goods.GoodsMainDAO;
import com.need.dao.bops.distribution.DistributionGoodsDAO;
import com.need.dao.bops.goods.BopsGoodsDAO;
import com.need.domain.common.enums.DistributionStatusEnums;
import com.need.domain.po.api.goods.GoodsDetailPO;
import com.need.domain.po.api.goods.GoodsMainPO;
import com.need.domain.po.bops.distribution.DistributionGoodsPO;
import com.need.domain.po.bops.goods.BopsGoods;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.vo.distribution.DistributionEditVO;
import com.need.domain.vo.distribution.DistributionGoodsStatisticsVO;
import com.need.domain.vo.distribution.DistributionListVO;
import com.need.domain.vo.distribution.DistributionPageVO;
import com.need.domain.vo.distribution.DistributionUserStatisticsVO;
import com.need.domain.vo.distribution.DistributionVO;
import com.need.service.bops.distribution.DistributionService;
import com.need.service.constant.BizReturnCode;
import com.need.utils.StringUtil;

@Service
public class DistributionServiceImpl implements DistributionService{
	@Autowired
	private DistributionGoodsDAO distributionGoodsDAO;
	@Autowired
	private BopsGoodsDAO bopsGoodsDAO;
	@Autowired
	private ApiDistributionGoodsDAO apiDistributionGoodsDAO;
	@Autowired
	private UserCommissionDAO userCommissionDAO;
	@Autowired
	private GoodsMainDAO goodsMainDAO;
	
	public static HSSFWorkbook demoWorkBook = new HSSFWorkbook();

	@Override
	public XSSFWorkbook exportPriceTemplate() {
		// TODO Auto-generated method stub
				XSSFWorkbook workBook = null;
				workBook = new XSSFWorkbook();// 创建工作薄
				XSSFSheet sheet = workBook.createSheet();
				workBook.setSheetName(0, "社会化分销模板");
				XSSFFont font = workBook.createFont();
				font.setColor(XSSFFont.COLOR_NORMAL);
				font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
				XSSFCellStyle cellStyle = workBook.createCellStyle();// 创建格式
				  HSSFDataFormat format = demoWorkBook.createDataFormat();  
		            cellStyle.setDataFormat(format.getFormat("@"));  
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
				cell2.setCellValue("佣金");
				XSSFCell cell3 = titleRow.createCell(2, 0);
				cell3.setCellStyle(cellStyle);
				cell3.setCellType(XSSFCell.CELL_TYPE_STRING);
				cell3.setCellValue("分销开始时间");
				XSSFCell cell4 = titleRow.createCell(3, 0);
				cell4.setCellStyle(cellStyle);
				cell4.setCellType(XSSFCell.CELL_TYPE_STRING);
				cell4.setCellValue("结束时间");
				
				return workBook;
	}

	@Override
	public List<DistributionVO> excelToList(InputStream in, Boolean is07Or10) {
		// TODO Auto-generated method stub
		List<DistributionVO> list= new ArrayList<DistributionVO>();
		try{
			if(is07Or10){
				XSSFWorkbook xssfWorkbook = new XSSFWorkbook(in);
				return read07or10Excel(xssfWorkbook);
			}
			else{
				POIFSFileSystem fs = new POIFSFileSystem(in);
				return read03Excel(fs);
			}
		}catch(IllegalStateException | IOException e){
			e.printStackTrace();
		}
		if (in != null) {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	private List<DistributionVO> read07or10Excel(XSSFWorkbook xssfWorkbook){
		List<DistributionVO> list = new ArrayList<DistributionVO>();
		for(int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++){
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			if (xssfSheet == null) {
				continue;
			}
			for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if(null == xssfRow){
					continue;
				}
				DistributionVO distributionVO = new DistributionVO();
				if(null != xssfRow.getCell(0)){
					xssfRow.getCell(0).setCellType(XSSFCell.CELL_TYPE_STRING);
					distributionVO.setGoodsCode(xssfRow.getCell(0).getStringCellValue());
				}
				if(null != xssfRow.getCell(1)){
					xssfRow.getCell(1).setCellType(XSSFCell.CELL_TYPE_STRING);
					distributionVO.setCommission(xssfRow.getCell(1).getStringCellValue());
				}
				if(!(XSSFCell.CELL_TYPE_BLANK == xssfRow.getCell(2).getCellType()))
				{
				if(null != xssfRow.getCell(2)){
						xssfRow.getCell(02).setCellType(XSSFCell.CELL_TYPE_NUMERIC);
						distributionVO.setStartTimeString(new SimpleDateFormat("yyyy:MM:dd HH:mm:ss")
								.format(HSSFDateUtil.getJavaDate(xssfRow.getCell(2).getNumericCellValue())));
				}
				}
				if(!(XSSFCell.CELL_TYPE_BLANK == xssfRow.getCell(3).getCellType()))
				{
					if(null != xssfRow.getCell(3)){
						xssfRow.getCell(03).setCellType(XSSFCell.CELL_TYPE_NUMERIC);
						distributionVO.setEndTimeString(new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").format(HSSFDateUtil.getJavaDate(xssfRow.getCell(3).getNumericCellValue())));
					}
				}
 				
				list.add(distributionVO);
			}
		}
		return list;
	}
	private List<DistributionVO> read03Excel(POIFSFileSystem fs){
		HSSFWorkbook wb = null;
		try {
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<DistributionVO> list = new ArrayList<DistributionVO>();
		for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
			HSSFSheet st = wb.getSheetAt(sheetIndex);
			for (int rowIndex = 1; rowIndex <= st.getLastRowNum(); rowIndex++) {
				HSSFRow row = st.getRow(rowIndex);
				if (row == null) {
					continue;
				}
				DistributionVO distributionVO = new DistributionVO();
				if(null != row.getCell(0)){
					row.getCell(0).setCellType(XSSFCell.CELL_TYPE_STRING);
					distributionVO.setGoodsCode(row.getCell(0).getStringCellValue());
				}
				if(null != row.getCell(1)){
					row.getCell(1).setCellType(XSSFCell.CELL_TYPE_STRING);
					distributionVO.setCommission(row.getCell(1).getStringCellValue());
				}
				if(null != row.getCell(2)){
					row.getCell(02).setCellType(XSSFCell.CELL_TYPE_NUMERIC);
					distributionVO.setStartTimeString(new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").format(HSSFDateUtil.getJavaDate(row.getCell(2).getNumericCellValue())));
				}
				if(null != row.getCell(3)){
					row.getCell(03).setCellType(XSSFCell.CELL_TYPE_NUMERIC);
					distributionVO.setEndTimeString(new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").format(HSSFDateUtil.getJavaDate(row.getCell(3).getNumericCellValue())));
				}
				list.add(distributionVO);
			}
		}
		return list;
	}

	@Override
	public int addDistributionGoods(DistributionVO distribution,DistributionGoodsPO distributionGoods)throws Exception {
		BopsGoods goods= bopsGoodsDAO.getGoodsDetail(distribution.getGoodsCode());
		int isSuccess=0;
		if(goods!=null){
		distributionGoods.setCommission(Integer.parseInt(CurrencyUtil.fromYuanToFen(distribution.getCommission())));
		distributionGoods.setDistributionStatus(DistributionStatusEnums.WAIT_TO_AUDIT.code);
		distributionGoods.setEndTime(new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").parse(distribution.getEndTimeString()));
		distributionGoods.setStartTime(new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").parse(distribution.getStartTimeString()));
		distributionGoods.setGoodsId(goods.getGoodsId());
		distributionGoods.setGoodsName(goods.getGoodsName());
		isSuccess= distributionGoodsDAO.insert(distributionGoods);
		}
		return isSuccess;
	}

	/**
	 * 
	 * @author peiboli 2015年12月1日 上午11:00:04
	 * @Method: getDistributionList 
	 * @Description: TODO获取分销列表
	 * @param params
	 * @return 
	 * @see com.need.service.bops.distribution.DistributionService#getDistributionList(com.need.domain.vo.distribution.DistributionPageVO)
	 */
	@Override
	public List<DistributionListVO> getDistributionList(DistributionPageVO params) {
		params.setTotal(distributionGoodsDAO.count(params));
		List<DistributionListVO> list=   distributionGoodsDAO.queryByPage(params);
		return list;
	}

	/**
	 * 
	 * @author peiboli 2015年12月1日 下午4:20:25
	 * @Method: audit 
	 * @Description: TODO商品审核
	 * @param id
	 * @return 
	 * @see com.need.service.bops.distribution.DistributionService#audit(java.lang.String)
	 */
	@Transactional
	@Override
	public Message audit(String id) {
		// TODO Auto-generated method stub
		Message message= Message.success();
		DistributionGoodsPO distribution= distributionGoodsDAO.selectByPrimaryKey(Long.parseLong(id));
        if(distribution!=null){
        DistributionGoodsPO goods=distributionGoodsDAO.getDistributionByGoodsId(distribution.getGoodsId());
        if(goods==null){
		int isSuccess= distributionGoodsDAO.updateAudit(id);
		if(isSuccess!=0){
			apiDistributionGoodsDAO.insert(distribution);
			goodsMainDAO.updateShareStatus(distribution.getGoodsId(),Boolean.TRUE.toString().toUpperCase());
		}else{
		 return	Message.error(BizReturnCode.SYSTEM_OPERATE_ERR);
		}
        }else{
        	return Message.error(BizReturnCode.DISTRIBUTION_EXIST);
        }
        }
		return message;
	}

	@Transactional
	@Override
	public Message frozen(String id) {
		Message message= Message.success();
		int isSuccess= distributionGoodsDAO.updateFrozen(id);
		if(isSuccess!=0){
			apiDistributionGoodsDAO.frozen(id);
		}else{
			return Message.error(BizReturnCode.SYSTEM_OPERATE_ERR);
		}
		return message;
	}

	/**
	 * 
	 * @author peiboli 2015年12月1日 下午7:07:36
	 * @Method: edit 
	 * @Description: TODO编辑
	 * @param params
	 * @return 
	 * @throws
	 */
	@Override
	public Message edit(DistributionEditVO params,BopsUser user) {
		// TODO Auto-generated method stub
		Message message= Message.success();
		DistributionGoodsPO distributionEdit=new DistributionGoodsPO();
		
		if(StringUtil.isBlank(user.getUserRealName())){
			distributionEdit.setOperatorName("admin");
		}else{
			distributionEdit.setOperatorName(user.getUserRealName());
		}
		distributionEdit.setOperatorId(user.getUserId().toString());
		distributionEdit.setCommission(Integer.parseInt(params.getCommission()));
		distributionEdit.setEndTime(params.getEndTime());
		distributionEdit.setStartTime(params.getStartTime());
		distributionEdit.setId(Long.parseLong(params.getId()));
		int isSuccess= distributionGoodsDAO.updateEdit(distributionEdit);
		return message;
	}

	/**
	 * 
	 * @author peiboli 2015年12月1日 下午7:38:39
	 * @Method: getDistributionById 
	 * @Description: TODO根据id获取详情，跳转编辑页面用
	 * @param id
	 * @return 
	 * @see com.need.service.bops.distribution.DistributionService#getDistributionById(java.lang.String)
	 */
	@Override
	public DistributionListVO getDistributionById(String id) {
		//id为distribution_goods表的id
		DistributionListVO distribution = distributionGoodsDAO.getDistributionById(id);
		return distribution;
	}

	/**
	 * 
	 * @author peiboli 2015年12月2日 下午8:51:14
	 * @Method: getDistributionGoodsStatisticsList 
	 * @Description: TODO获得分销商品统计列表
	 * @param params
	 * @return 
	 * @see com.need.service.bops.distribution.DistributionService#getDistributionGoodsStatisticsList(com.need.domain.vo.distribution.DistributionPageVO)
	 */
	@Override
	public List<DistributionGoodsStatisticsVO> getDistributionGoodsStatisticsList(DistributionPageVO params) {
		params.setTotal(userCommissionDAO.countGoods(params));
		List<DistributionGoodsStatisticsVO> list= userCommissionDAO.queryDistributionGoodsStatisticsList(params);
		return list;
	}

	@Override
	public List<DistributionUserStatisticsVO> getDistributionUserStatisticsList(DistributionPageVO params) {
		params.setTotal(userCommissionDAO.countUser(params));
		List<DistributionUserStatisticsVO> list= userCommissionDAO.queryDistributionUserStatisticsList(params);
		return list;
	}
	
}
