package com.need.service.bops.goods.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.need.dao.api.goods.GoodsBrandDAO;
import com.need.dao.bops.goods.BopsGoodsBrandDAO;
import com.need.dao.bops.user.BopsUserDAO;
import com.need.domain.po.api.goods.GoodsBrandPO;
import com.need.domain.po.bops.goods.BopsGoodsBrandPO;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.vo.goods.BopsGoodsBrandVO;
import com.need.domain.vo.goods.BrandPageVO;
import com.need.service.bops.goods.BopsGoodsBrandService;
import com.need.service.constant.BizReturnCode;
import com.need.service.constant.Constants;
import com.need.trade.enums.TradeStatus;
import com.need.utils.ExcelUtil;
@Service
public class BopsGoodsBrandServiceImpl implements BopsGoodsBrandService {

	@Autowired
	private BopsGoodsBrandDAO bopsGoodsBrandDAO;
	@Autowired
	private GoodsBrandDAO goodsBrandDAO;
	@Autowired
	private BopsUserDAO bopsUserDAO;
	/**
	 * 
	 * @author peiboli 2015年11月18日 下午6:10:20	
	 * @Method: getBrandList 
	 * @Description: TODO getBrandList
	 * @param params
	 * @return 
	 * @see com.need.service.bops.goods.BopsGoodsBrandService#getBrandList(com.need.domain.vo.goods.BrandPageVO)
	 */
	@Override
	public List<BrandPageVO> getBrandList(BrandPageVO params) {
		// TODO Auto-generated method stub
		params.setTotal(bopsGoodsBrandDAO.count(params));
		List<BrandPageVO> list =bopsGoodsBrandDAO.queryByPage(params);
		for(BrandPageVO brand:list){
			if(brand.getUpdateId()!=null){
				BopsUser user= bopsUserDAO.selectByPrimaryKey(brand.getUpdateId());
				if(user!=null){
				brand.setUpdateName(user.getUserRealName());
				}
			}
		}
		return list;
	}
	/**
	 * 
	 * @author peiboli 2015年11月18日 下午6:10:45
	 * @Method: save 
	 * @Description: TODO
	 * @param bopsGoodsBrandPO
	 * @return 
	 * @see com.need.service.bops.goods.BopsGoodsBrandService#save(com.need.domain.po.bops.goods.BopsGoodsBrandPO)
	 */
	@Override
	public int save(BopsGoodsBrandPO bopsGoodsBrandPO) {
		int success= bopsGoodsBrandDAO.addBrand(bopsGoodsBrandPO);
		if(success==1){
			GoodsBrandPO brand = new GoodsBrandPO();
			 BeanUtils.copyProperties(bopsGoodsBrandPO,brand );
			goodsBrandDAO.insert(brand);	
		}
		return success;
	}
	/**
	 * 
	 * @author peiboli 2015年11月18日 下午6:10:52
	 * @Method: getBrandByBrandName 
	 * @Description: TODO
	 * @param brandName
	 * @return 
	 * @see com.need.service.bops.goods.BopsGoodsBrandService#getBrandByBrandName(java.lang.String)
	 */
	@Override
	public Boolean getBrandByBrandName(String brandName) {
		// TODO Auto-generated method stub		
		if(bopsGoodsBrandDAO.getBrandByBrandName(brandName)!=null){
			return Boolean.TRUE;
		}else{
			return Boolean.FALSE;
		}
		
	}
	@Transactional
	@Override
	public int update(BopsGoodsBrandPO bopsGoodsBrandPO) {
		// TODO Auto-generated method stub
		int isSuccess= bopsGoodsBrandDAO.updateByPrimaryKey(bopsGoodsBrandPO);
		if(isSuccess==1){
			GoodsBrandPO brand = new GoodsBrandPO();
		    BeanUtils.copyProperties(bopsGoodsBrandPO,brand );
			goodsBrandDAO.updateByPrimaryKey(brand);
			return isSuccess;
		}else{
			return BizReturnCode.SYSTEM_OPERATE_ERR;
		}
		
	}
	/**
	 * 
	 * @author liuhongyang 2015年12月16日 下午2:22:53
	 * @Method: exportBrand 
	 * @Description: 品牌导出
	 * @param bopsGoodsBrandPO
	 * @return 
	 * @see com.need.service.bops.goods.BopsGoodsBrandService#exportBrand(com.need.domain.po.bops.goods.BopsGoodsBrandPO)
	 */
	@Override
	public HSSFWorkbook exportBrand(BopsGoodsBrandPO bopsGoodsBrandPO) {
		List<BopsGoodsBrandPO> goodsBrandList = bopsGoodsBrandDAO.queryExportBrand(bopsGoodsBrandPO);
		List<BopsGoodsBrandVO> brandList = new ArrayList<BopsGoodsBrandVO>();
		if(goodsBrandList.size()>0){
			for (BopsGoodsBrandPO goodsBrandPO : goodsBrandList) {
				BopsGoodsBrandVO bopsGoodsBrandVO = new BopsGoodsBrandVO();
				BeanUtils.copyProperties(goodsBrandPO, bopsGoodsBrandVO);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				bopsGoodsBrandVO.setCreateTime(sdf.format(goodsBrandPO.getCreateTime()));
				if(null != goodsBrandPO.getUpdateTime()){
					bopsGoodsBrandVO.setUpdateTime(sdf.format(goodsBrandPO.getUpdateTime()));
				}
				brandList.add(bopsGoodsBrandVO);
				//查询创建人和修改人
				if(null != bopsGoodsBrandVO.getCreateId()){
					if(0 == bopsGoodsBrandVO.getCreateId().intValue()){
						bopsGoodsBrandVO.setCreateName(Constants.SUPERADMIN);
					}else{
						BopsUser BopsUser=bopsUserDAO.selectByPrimaryKey(bopsGoodsBrandVO.getCreateId());
						bopsGoodsBrandVO.setCreateName(BopsUser.getUserRealName());
					}
				}
				if(null != bopsGoodsBrandVO.getUpdateId()){
					if(0 == bopsGoodsBrandVO.getUpdateId().intValue()){
						bopsGoodsBrandVO.setUpdateName(Constants.SUPERADMIN);
					}else{
						BopsUser BopsUser=bopsUserDAO.selectByPrimaryKey(bopsGoodsBrandVO.getUpdateId());
						bopsGoodsBrandVO.setUpdateName(BopsUser.getUserRealName());
					}
				}
			}
		}
		HSSFWorkbook workbook = getBrandBook(brandList);
		return workbook;
	}

	public HSSFWorkbook getBrandBook(List<BopsGoodsBrandVO> brandList) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = ExcelUtil.createSheet(workbook, "商品品牌数据");
		// 创建表头
		Font font = ExcelUtil.createFont(workbook, (short) 5, HSSFColor.BLACK.index, (short) 10);
		CellStyle cellStylec = ExcelUtil.createBorderCellStyle(workbook, HSSFColor.WHITE.index, HSSFColor.WHITE.index,
				(short) 30, font);
		HSSFRow row = ExcelUtil.createRow(sheet, 0, 300);
		row.getSheet().setColumnWidth(0, 8000);
		row.getSheet().setColumnWidth(15, 8000);
		row.getSheet().setColumnWidth(17, 8000);
		row.getSheet().setColumnWidth(2, 12000);
		HSSFCell cell = ExcelUtil.createCell(row, 0, cellStylec);
		cell.setCellValue("品牌ID");
		cell = ExcelUtil.createCell(row, 1, cellStylec);
		cell.setCellValue("品牌名称");
		cell = ExcelUtil.createCell(row, 2, cellStylec);
		cell.setCellValue("品牌介绍");
		cell = ExcelUtil.createCell(row, 3, cellStylec);
		cell.setCellValue("品牌所属地");
		cell = ExcelUtil.createCell(row, 4, cellStylec);
		cell.setCellValue("新增人");
		cell = ExcelUtil.createCell(row, 5, cellStylec);
		cell.setCellValue("新增时间");
		cell = ExcelUtil.createCell(row, 6, cellStylec);
		cell.setCellValue("最后修改人");
		cell = ExcelUtil.createCell(row, 7, cellStylec);
		cell.setCellValue("最后修改时间");
		// 插入实体数据
		BopsGoodsBrandVO bopsGoodsBrandVO =new BopsGoodsBrandVO();
		if(brandList.size()>0){
			for (int i = 1; i < brandList.size()+1; i++) {
				bopsGoodsBrandVO = brandList.get(i-1);
				row = ExcelUtil.createRow(sheet, i, 500);
				cell = ExcelUtil.createCell(row, 0, cellStylec);
				cell.setCellValue(bopsGoodsBrandVO.getBrandId());
				cell = ExcelUtil.createCell(row, 1, cellStylec);
				cell.setCellValue(bopsGoodsBrandVO.getBrandName());
				cell = ExcelUtil.createCell(row, 2, cellStylec);
				if(StringUtils.hasText(bopsGoodsBrandVO.getBrandDescriptionText())){
					cell.setCellValue(bopsGoodsBrandVO.getBrandDescriptionText());
				}else{
					cell.setCellValue("");
				}
				cell = ExcelUtil.createCell(row, 3, cellStylec);
				cell.setCellValue(bopsGoodsBrandVO.getBrandArea());
				cell = ExcelUtil.createCell(row, 4, cellStylec);
				cell.setCellValue(bopsGoodsBrandVO.getCreateName());
				cell = ExcelUtil.createCell(row, 5, cellStylec);
				cell.setCellValue(bopsGoodsBrandVO.getCreateTime());
				cell = ExcelUtil.createCell(row, 6, cellStylec);
				if(StringUtils.hasLength(bopsGoodsBrandVO.getUpdateName())){
					cell.setCellValue(bopsGoodsBrandVO.getUpdateName());
				}else{
					cell.setCellValue("");
				}
				cell = ExcelUtil.createCell(row, 7, cellStylec);
				if(null != bopsGoodsBrandVO.getUpdateTime()){
					cell.setCellValue(bopsGoodsBrandVO.getUpdateTime());				
				}else{
					bopsGoodsBrandVO.setUpdateTime("");
				}
			}
		}
		return workbook;
	}
}
