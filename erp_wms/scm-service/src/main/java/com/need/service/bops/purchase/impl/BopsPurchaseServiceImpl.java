package com.need.service.bops.purchase.impl;


import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.need.biz.utils.CurrencyUtil;
import com.need.dao.bops.goods.BopsGoodsDAO;
import com.need.dao.bops.purchase.BopsPurchaseDAO;
import com.need.dao.bops.purchase.BopsPurchaseInfoDAO;
import com.need.dao.bops.store.BopsCreditMemoDAO;
import com.need.dao.bops.store.BopsCreditMemoItemDAO;
import com.need.dao.bops.store.BopsInventoryDAO;
import com.need.dao.bops.user.BopsUserDAO;
import com.need.dao.bops.wms.BopsReceiveSuccessDAO;
import com.need.dao.bops.wms.ESynEdiMessageDAO;
import com.need.dao.bops.wms.ESynEdiReceiveMessageDAO;
import com.need.domain.common.enums.ReportTypeEnum;
import com.need.domain.po.bops.goods.BopsGoods;
import com.need.domain.po.bops.purchase.BopsPurchaseInfoPO;
import com.need.domain.po.bops.purchase.BopsPurchasePO;
import com.need.domain.po.bops.store.BopsCreditMemoItemPO;
import com.need.domain.po.bops.store.BopsCreditMemoPO;
import com.need.domain.po.bops.store.BopsInventoryPO;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.po.bops.wms.BopsReceiveSuccess;
import com.need.domain.po.bops.wms.ESynEdiMessage;
import com.need.domain.po.bops.wms.ESynEdiReceiveMessage;
import com.need.domain.vo.purchase.BopsPurchaseVO;
import com.need.domain.vo.purchase.PurchaseInVO;
import com.need.domain.vo.purchase.PurchaseItemInVO;
import com.need.domain.vo.purchase.PurchaseItemOutVO;
import com.need.domain.vo.purchase.PurchaseOutVO;
import com.need.domain.vo.wms.StoreChangeVO;
import com.need.kafka.services.producer.NeedProducer;
import com.need.service.bops.goods.BopsGoodsStoreService;
import com.need.service.bops.purchase.BopsPurchaseService;
import com.need.service.constant.Constants;
import com.need.utils.JsonUtils;



@Service
public class BopsPurchaseServiceImpl implements BopsPurchaseService {
	private static final Logger logger = Logger.getLogger(BopsPurchaseServiceImpl.class);
	
	@Autowired
	private BopsGoodsDAO bopsGoodsDAO;
	@Autowired
	private BopsPurchaseDAO bopsPurchaseDAO;
	@Autowired
	private BopsPurchaseInfoDAO bopsPurchaseInfoDAO;
	@Autowired
	private BopsUserDAO bopsUserDAO;
	@Autowired
	private BopsGoodsStoreService bopsGoodsStoreService;
	@Autowired
	private ESynEdiMessageDAO eSynEdiMessageDAO;
	@Autowired
	private BopsReceiveSuccessDAO bopsReceiveSuccessDAO;
	@Autowired
	private ESynEdiReceiveMessageDAO eSynEdiReceiveMessageDAO;
	@Autowired
	private BopsCreditMemoDAO bopsCreditMemoDAO;
	@Autowired
	private BopsInventoryDAO bopsInventoryDAO;
	@Autowired
	private BopsCreditMemoItemDAO bopsCreditMemoItemDAO;
	/**
	 * 
	 * @author liuhongyang 2015年11月19日 下午4:12:11
	 * @Method: exportPurchaseTemplate 
	 * @Description: 采购单模板下载
	 * @return 
	 * @see com.need.service.bops.purchase.BopsPurchaseService#exportPurchaseTemplate()
	 */
	@Override
	public XSSFWorkbook exportPurchaseTemplate() {
		XSSFWorkbook workBook = null;
		workBook = new XSSFWorkbook();// 创建工作薄
		XSSFSheet sheet = workBook.createSheet();
		workBook.setSheetName(0, "导入采购单模板");
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
		cell2.setCellValue("采购单价");
		XSSFCell cell3 = titleRow.createCell(2, 0);
		cell3.setCellStyle(cellStyle);
		cell3.setCellType(XSSFCell.CELL_TYPE_STRING);
		cell3.setCellValue("采购数量");
		return workBook;
	}
	/**
	 * 
	 * @author liuhongyang 2015年11月19日 下午4:31:43
	 * @Method: excelToList 
	 * @Description: 解析excel
	 * @param in
	 * @param is07Or10
	 * @return 
	 * @see com.need.service.bops.purchase.BopsPurchaseService#excelToList(java.io.InputStream, java.lang.Boolean)
	 */
	@Override
	public List<BopsPurchaseVO> excelToList(InputStream in, Boolean is07Or10) {
		List<BopsPurchaseVO> batchPurchaseList = new ArrayList<BopsPurchaseVO>();
		try {
			// 07 2010版本
			if (is07Or10) {
				XSSFWorkbook xssfWorkbook = new XSSFWorkbook(in);
				return read07Excel(xssfWorkbook, batchPurchaseList);
			} else {
				POIFSFileSystem fs = new POIFSFileSystem(in);
				return read03Excel(fs, batchPurchaseList);
			}

		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		if (in != null) {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return batchPurchaseList;
	}
	
	private List<BopsPurchaseVO> read03Excel(POIFSFileSystem fs,
			List<BopsPurchaseVO> batchPurchaseList) {
		HSSFWorkbook wb = null;
		try {
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		HSSFCell cell = null;
		for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
			HSSFSheet st = wb.getSheetAt(sheetIndex);
			// 第一行为标题，不取
			for (int rowIndex = 1; rowIndex <= st.getLastRowNum(); rowIndex++) {
				BopsPurchaseVO bopsPurchaseVO = new BopsPurchaseVO();
				HSSFRow row = st.getRow(rowIndex);
				if (row == null) {
					continue;
				}
				for (int columnIndex = 0; columnIndex < row.getLastCellNum(); columnIndex++) {
					cell = row.getCell(columnIndex);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					if (cell != null && columnIndex == 0) {
						bopsPurchaseVO.setGoodsCode(StringTrim(cell.getStringCellValue()));
					} else if (cell != null && columnIndex == 1) {
						bopsPurchaseVO.setPurchasePrice(Integer.parseInt(StringTrim(cell.getStringCellValue())));
					} else {
						bopsPurchaseVO.setPurchaseCount(Integer.parseInt(StringTrim(cell.getStringCellValue())));
					}
				}
				batchPurchaseList.add(bopsPurchaseVO);

			}
		}
		return batchPurchaseList;
	}

	private List<BopsPurchaseVO> read07Excel(XSSFWorkbook xssfWorkbook, List<BopsPurchaseVO> list) {
		for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			if (xssfSheet == null) {
				continue;
			}
			// Read the Row
			for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow != null) {
					BopsPurchaseVO vo = new BopsPurchaseVO();
					if (null != xssfRow.getCell(0)) {
						xssfRow.getCell(0).setCellType(XSSFCell.CELL_TYPE_STRING);
						vo.setGoodsCode(xssfRow.getCell(0).getStringCellValue());
					}
					if (null != xssfRow.getCell(1)) {
						xssfRow.getCell(1).setCellType(XSSFCell.CELL_TYPE_STRING);
						vo.setPurchasePrice(Integer.parseInt(xssfRow.getCell(1).getStringCellValue()));
					}
					if (null != xssfRow.getCell(2)) {
						xssfRow.getCell(2).setCellType(XSSFCell.CELL_TYPE_STRING);
						vo.setPurchaseCount(Integer.parseInt(xssfRow.getCell(2).getStringCellValue()));
					}
					list.add(vo);
				}
			}
		}
		return list;
	}

	private String StringTrim(String value) {
		if (StringUtils.hasText(value)) {
			return value.trim();
		} else {
			return "";
		}
	}
	/**
	 * 
	 * @author liuhongyang 2015年11月20日 上午10:29:45
	 * @Method: hasNullPurchaseList 
	 * @Description: 列表中是否有空字段
	 * @param bopsPurchaseVO
	 * @return 
	 * @see com.need.service.bops.purchase.BopsPurchaseService#hasNullPurchaseList(com.need.domain.vo.trade.BopsPurchaseVO)
	 */
	@Override
	public boolean hasNullPurchaseList(BopsPurchaseVO bopsPurchaseVO) {
		if (null == bopsPurchaseVO.getGoodsCode()){
			return false;
		}else if(null == bopsPurchaseVO.getPurchasePrice()){
			return false;
		}else if(null == bopsPurchaseVO.getPurchaseCount()){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * 
	 * @author liuhongyang 2015年11月20日 上午10:30:04
	 * @Method: isExistGoods 
	 * @Description: TODO
	 * @param bopsPurchaseVO
	 * @return 
	 * @see com.need.service.bops.purchase.BopsPurchaseService#isExistGoods(com.need.domain.vo.trade.BopsPurchaseVO)
	 */
	@Override
	public boolean isExistGoods(BopsPurchaseVO bopsPurchaseVO) {
		BopsGoods bopsGoods=new BopsGoods();
		if(null != bopsPurchaseVO.getGoodsCode()){
			bopsGoods=bopsGoodsDAO.selectByGoodsCode(bopsPurchaseVO.getGoodsCode().toString());
		}
		if(null == bopsGoods){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * 
	 * @author liuhongyang 2015年11月20日 下午2:05:22
	 * @Method: getSuccessData 
	 * @Description: 采购数据拼装
	 * @param bopsPurchaseVO
	 * @return 
	 * @see com.need.service.bops.purchase.BopsPurchaseService#getSuccessData(com.need.domain.vo.trade.BopsPurchaseVO)
	 */
	@Override
	public BopsPurchaseVO getSuccessData(BopsPurchaseVO bopsPurchaseVO) {
		if(null != bopsPurchaseVO.getGoodsCode()){
			BopsGoods bopsGoods=bopsGoodsDAO.selectByGoodsCode(bopsPurchaseVO.getGoodsCode());
			if(null != bopsGoods){
				bopsPurchaseVO.setGoodsName(bopsGoods.getGoodsName());
				bopsPurchaseVO.setGoodsBarcode(bopsGoods.getGoodsBarcode());
			}
		
		}
		return bopsPurchaseVO;
	}
	/**
	 * 
	 * @author liuhongyang 2015年11月20日 下午7:23:03
	 * @Method: savePurchase 
	 * @Description: 新增采购单
	 * @param bopsPurchaseVO 
	 * @see com.need.service.bops.purchase.BopsPurchaseService#savePurchase(com.need.domain.vo.trade.BopsPurchaseVO)
	 */
	@Override
	@Transactional("bops_txManager")
	public void savePurchase(BopsPurchaseVO bopsPurchaseVO) {
		logger.info("in savePurchase bopsPurchaseVO: " +bopsPurchaseVO);
		//插入采购单表
		BopsPurchasePO bopsPurchasePO = new BopsPurchasePO();
		BopsPurchaseVO[] goodsArray = JSON.parseObject(bopsPurchaseVO.getSuccessList(), new TypeReference<BopsPurchaseVO[]>() {});
		BopsPurchaseVO BopsPurchaseVOAll=(BopsPurchaseVO) JSON.parseObject(bopsPurchaseVO.getBopsPurchaseVOAll(),BopsPurchaseVO.class);
		Double priceAll=BopsPurchaseVOAll.getPurchasePriceAll();
		bopsPurchasePO.setPurchasePriceAll(Integer.parseInt(CurrencyUtil.fromYuanToFen(priceAll.toString())));
		bopsPurchasePO.setPurchaseCountAll(BopsPurchaseVOAll.getPurchaseCountAll());
		if(StringUtils.hasText(bopsPurchaseVO.getPurchasePriceOther())){
			Double otherPrice=Double.parseDouble(bopsPurchaseVO.getPurchasePriceOther());
			bopsPurchasePO.setPurchasePriceOther(Integer.parseInt(CurrencyUtil.fromYuanToFen(otherPrice.toString())));
		}
		if(StringUtils.hasText(bopsPurchaseVO.getPurchaseFreight())){
			Double freight=Double.parseDouble(bopsPurchaseVO.getPurchaseFreight());
			bopsPurchasePO.setPurchaseFreight(Integer.parseInt(CurrencyUtil.fromYuanToFen(freight.toString())));
		}
		//下发至wms
		PurchaseOutVO purchaseOutVO=new PurchaseOutVO();
		List<PurchaseItemOutVO> itemsList=new ArrayList<PurchaseItemOutVO>();
		bopsPurchasePO.setProviderId(bopsPurchaseVO.getProviderId());
		bopsPurchasePO.setWarehouseId(Integer.parseInt(bopsPurchaseVO.getWarehouseId()));
		bopsPurchasePO.setPurchaseCurrency(bopsPurchaseVO.getPurchaseCurrency());
		SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
		Date deliveryTime;
		try {
			deliveryTime = formatter.parse(bopsPurchaseVO.getDeliveryTime());
			bopsPurchasePO.setDeliveryTime(deliveryTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bopsPurchasePO.setPurchaseRemark(bopsPurchaseVO.getPurchaseRemark());
		bopsPurchasePO.setPurchaseStatus("WAIT_HARVEST");
		bopsPurchasePO.setPurchaseUserId(bopsPurchaseVO.getPurchaseUserId());
		bopsPurchasePO.setPurchaseDepartment(bopsPurchaseVO.getPurchaseDepartment());
		//未收货
		bopsPurchasePO.setPurchaseIsHavest("0");
		bopsPurchaseDAO.insertSelective(bopsPurchasePO);
		for (int i = 0; i < goodsArray.length; i++) {
			BopsPurchaseInfoPO bopsGoods=new BopsPurchaseInfoPO();
			BeanUtils.copyProperties(goodsArray[i], bopsGoods);
			bopsGoods.setPurchaseId(bopsPurchasePO.getPurchaseId());
			BopsGoods bopsGood=bopsGoodsDAO.selectByGoodsCode(goodsArray[i].getGoodsCode().toString());
			bopsGoods.setGoodsId(bopsGood.getGoodsId());
			bopsGoods.setPurchasePrice(Integer.parseInt(CurrencyUtil.fromYuanToFen(bopsGoods.getPurchasePrice().toString())));
			bopsPurchaseInfoDAO.insertSelective(bopsGoods);
			//组装下发至wms商品数据
			PurchaseItemOutVO purchaseItemOutVO = new PurchaseItemOutVO();
			purchaseItemOutVO.setSkuId(Long.parseLong(bopsGoods.getGoodsCode()));
			purchaseItemOutVO.setName(bopsGoods.getGoodsName());
			purchaseItemOutVO.setQuantity(Long.parseLong(bopsGoods.getPurchaseCount().toString()));
			itemsList.add(purchaseItemOutVO);
		}
		//根据仓库id查询仓库是否安装wms系统
		String flag=bopsPurchaseDAO.selectWareHouseDredge(bopsPurchasePO.getWarehouseId());
		//1为安装
		if("1".equals(flag)){
			/**
			 * 采购单下发至wms
			 */
			logger.info("in savePurchase flag: " +flag);
			purchaseOutVO.setId(Long.parseLong(bopsPurchasePO.getPurchaseId().toString()));
			purchaseOutVO.setWarehouseId(Long.parseLong(bopsPurchasePO.getWarehouseId().toString()));
			purchaseOutVO.setPurchaseNo(bopsPurchasePO.getPurchaseId().toString());
			purchaseOutVO.setVendorId(Long.parseLong(bopsPurchasePO.getProviderId().toString()));
			Date date=new Date();
			purchaseOutVO.setCreateDate(date);
			//根据用户id查询真实姓名
			if(0 == bopsPurchaseVO.getPurchaseUserId()){
				purchaseOutVO.setCreateBy(Constants.SUPERADMIN);
			}else{
				BopsUser bopsUser=bopsUserDAO.selectByPrimaryKey(bopsPurchaseVO.getPurchaseUserId());
				purchaseOutVO.setCreateBy(bopsUser.getUserRealName());
			}
			purchaseOutVO.setRemark(bopsPurchasePO.getPurchaseRemark());
			purchaseOutVO.setItems(itemsList);
			//保存到报文发送表
			ESynEdiMessage eSynEdiMessage = new ESynEdiMessage(bopsPurchasePO.getPurchaseId().toString(), "101",JSONObject.toJSONString(purchaseOutVO), purchaseOutVO.getWarehouseId());
			logger.info("in savePurchase eSynEdiMessage: " +JSONObject.toJSONString(purchaseOutVO));
			eSynEdiMessageDAO.insert(eSynEdiMessage);
			//发送到wms
			NeedProducer needProducer = NeedProducer.getInstance();
			Boolean result = needProducer.sendSync("zz_erp2wms_101", eSynEdiMessage.getId()+"",JSONObject.toJSONString(eSynEdiMessage));
			Long status;
			//改变结果状态
			if(result){
				status=2L;
			}else{
				status=9L;
			}
			eSynEdiMessage.setStatus(status);
			eSynEdiMessageDAO.updateByPrimaryKey(eSynEdiMessage);
		}
		
	}
	@Override
	public List<BopsPurchaseVO> selectPurchaseList(BopsPurchaseVO bopsPurchaseVO) {
		List<BopsPurchaseVO> purchaseList=bopsPurchaseDAO.selectPurchaseList(bopsPurchaseVO);
		for (BopsPurchaseVO purchaseVO : purchaseList) {
			//预约仓库查询
			String wareHouseName=bopsPurchaseDAO.selectWareHouseTypeById(purchaseVO);
			if(StringUtils.hasText(wareHouseName)){
				purchaseVO.setWareHouseName(wareHouseName);
			}
			//是否安装wms查询
			String dredge = bopsPurchaseDAO.selectWareHouseDredge(Integer.parseInt(purchaseVO.getWarehouseId()));
			if(StringUtils.hasText(dredge)){
				purchaseVO.setDredge(dredge);
			}
			//供应商查询
			String vendorName=bopsPurchaseDAO.selectVendorName(purchaseVO);
			purchaseVO.setVendorName(vendorName);
			//采购人查询
			if(null != purchaseVO.getPurchaseUserId()){
				if(0 == purchaseVO.getPurchaseUserId()){
					purchaseVO.setPurchaseUserName(Constants.SUPERADMIN);
				}else{
					BopsUser bopsUser=bopsUserDAO.selectByPrimaryKey(purchaseVO.getPurchaseUserId());
					purchaseVO.setPurchaseUserName(bopsUser.getUserRealName());
				}
			}
			Integer newPriceAll=(int) purchaseVO.getPurchasePriceAll();
			String priceAll=CurrencyUtil.fromFenToYuan(newPriceAll.toString());
			purchaseVO.setPurchasePriceAll(Double.parseDouble(priceAll));
		}
		return purchaseList;
	}
	@Override
	public BopsPurchaseVO getPurchaseDetail(String id) {
		BopsPurchasePO bopsPurchasePO=bopsPurchaseDAO.selectByPrimaryKey(Integer.parseInt(id));
		BopsPurchaseVO bopsPurchaseVO=new BopsPurchaseVO();
		BeanUtils.copyProperties(bopsPurchasePO, bopsPurchaseVO);
		bopsPurchaseVO.setPurchasePriceAll(Double.parseDouble(CurrencyUtil.fromFenToYuan(bopsPurchasePO.getPurchasePriceAll().toString())));
		if(null != bopsPurchasePO.getPurchasePriceOther()){
			bopsPurchaseVO.setPurchasePriceOther(CurrencyUtil.fromFenToYuan(bopsPurchasePO.getPurchasePriceOther().toString()));
		}
		if(null != bopsPurchasePO.getPurchasePriceReal()){
			bopsPurchaseVO.setPurchasePriceReal(Double.parseDouble(CurrencyUtil.fromFenToYuan(bopsPurchasePO.getPurchasePriceReal().toString())));
		}
		if(null != bopsPurchasePO.getDifferencePrice()){
			String diffence=Math.abs(bopsPurchasePO.getDifferencePrice().intValue())+"";
			bopsPurchaseVO.setDifferencePrice(Double.parseDouble(CurrencyUtil.fromFenToYuan(diffence)));
		}
		if(null != bopsPurchasePO.getDifferenceCount()){
			bopsPurchaseVO.setDifferenceCount(Math.abs(bopsPurchasePO.getDifferenceCount()));
		}
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");  
		if(null !=bopsPurchasePO.getDeliveryTime()){
			String deliveryTime=sdf1.format(bopsPurchasePO.getDeliveryTime());  
			//预计送达时间
			bopsPurchaseVO.setDeliveryTime(deliveryTime);
		}
		//创建时间 收货时间
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String createTime=sdf2.format(bopsPurchasePO.getCreateTime()); 
		if(null != bopsPurchasePO.getHavestTime()){
			String havestTime=sdf2.format(bopsPurchasePO.getHavestTime());
			bopsPurchaseVO.setHavestTime(havestTime);
		}
		bopsPurchaseVO.setCreateTime(createTime);
		//所属供应商查询
		String vendorName=bopsPurchaseDAO.selectVendorName(bopsPurchaseVO);
		bopsPurchaseVO.setVendorName(vendorName);
		//预约仓库查询
		String wareHouseName=bopsPurchaseDAO.selectWareHouseTypeByWarehouseId(bopsPurchasePO.getWarehouseId());
		bopsPurchaseVO.setWareHouseName(wareHouseName);
		//采购人查询
		if(null != bopsPurchasePO.getPurchaseUserId()){
			if(0 == bopsPurchasePO.getPurchaseUserId()){
				bopsPurchaseVO.setPurchaseUserName(Constants.SUPERADMIN);
			}else{
				BopsUser bopsUser=bopsUserDAO.selectByPrimaryKey(bopsPurchaseVO.getPurchaseUserId());
				bopsPurchaseVO.setPurchaseUserName(bopsUser.getUserRealName());
			}
		}
		//收货人查询
		if(null != bopsPurchasePO.getHavestUserid()){
			if("0".equals(bopsPurchasePO.getHavestUserid())){
				bopsPurchaseVO.setHavestUsername(Constants.SUPERADMIN);
			}else if("-1".equals(bopsPurchasePO.getHavestUserid())){
				bopsPurchaseVO.setHavestUsername(bopsPurchasePO.getHavestUsername());
			}else{
				BopsUser bopsUser=bopsUserDAO.selectByPrimaryKey(Integer.parseInt(bopsPurchasePO.getHavestUserid()));
				bopsPurchaseVO.setHavestUsername(bopsUser.getUserRealName());
			}
		}
		return bopsPurchaseVO;
	}
	@Override
	public List<BopsPurchaseVO> getGoodsDetail(Integer purchaseId) {
		List<BopsPurchaseInfoPO> purchaseInfoList=bopsPurchaseInfoDAO.selectGoodsList(purchaseId);
		List<BopsPurchaseVO> bopsPurchaseVOList=new ArrayList<BopsPurchaseVO>();
		for (BopsPurchaseInfoPO bopsPurchaseInfoPO : purchaseInfoList) {
			BopsPurchaseVO bopsPurchaseVO=new BopsPurchaseVO();
			BeanUtils.copyProperties(bopsPurchaseInfoPO, bopsPurchaseVO);
			//
			bopsPurchaseVO.setPurchasePrice2(Double.parseDouble(CurrencyUtil.fromFenToYuan(bopsPurchaseInfoPO.getPurchasePrice().toString())));
			bopsPurchaseVOList.add(bopsPurchaseVO);
		}
		
		return bopsPurchaseVOList;
	}
	@Override
	@Transactional("bops_txManager")
	public BopsPurchaseVO updatePurchase(BopsPurchaseVO bopsPurchaseVO) {
		//用@将参数分割为多个对象
		String[] array=bopsPurchaseVO.getDataAll().split("@");
		BopsPurchasePO bopsPurchasePO=new BopsPurchasePO();
		//实际收货数量
		Integer purchaseCountReal=0;
		//实际收货总金额
		Integer purchasePriceReal=0;
		String[] newArray;
		List<BopsPurchaseInfoPO> dataList=new ArrayList<BopsPurchaseInfoPO>();
		for (int i = 0; i < array.length; i++) {
			if(i==0){
				newArray=array[i].split(",");
			}else{
				String moreArray=array[i].substring(1);
				newArray=moreArray.split(",");
			}
			
			BopsPurchaseInfoPO bopsPurchaseInfoPO=new BopsPurchaseInfoPO();
			bopsPurchaseInfoPO.setPurchaseId(Integer.parseInt(newArray[0]));
			if(StringUtils.hasText(newArray[1])){
				bopsPurchaseInfoPO.setHavestInfoNormal(Integer.parseInt(newArray[1]));
			}else{
				bopsPurchaseInfoPO.setHavestInfoNormal(0);
			}
			if(StringUtils.hasText(newArray[2])){
				bopsPurchaseInfoPO.setHavestInfoDisability(Integer.parseInt(newArray[2]));
			}else{
				bopsPurchaseInfoPO.setHavestInfoDisability(0);
			}
			bopsPurchaseInfoPO.setGoodsCode(newArray[3]);
			BopsGoods goodsStore= bopsGoodsDAO.selectByGoodsCode(bopsPurchaseInfoPO.getGoodsCode());
			bopsPurchaseInfoPO.setGoodsId(goodsStore.getGoodsId());
			bopsPurchaseInfoPO.setGoodsName(goodsStore.getGoodsName());
			bopsPurchaseInfoPO.setHavestInfoCount(bopsPurchaseInfoPO.getHavestInfoDisability()+bopsPurchaseInfoPO.getHavestInfoNormal());
			bopsPurchaseInfoDAO.updatePurchaseInfoCount(bopsPurchaseInfoPO);
			//实际总数量
			purchaseCountReal=purchaseCountReal+bopsPurchaseInfoPO.getHavestInfoDisability()+bopsPurchaseInfoPO.getHavestInfoNormal();
			//根据商品code和采购单号查询采购单价
			BopsPurchaseInfoPO priceInfo=bopsPurchaseInfoDAO.selectPrice(bopsPurchaseInfoPO);
			//实际总金额
			purchasePriceReal=purchasePriceReal+priceInfo.getPurchasePrice()*purchaseCountReal;
			bopsPurchaseVO.setPurchaseId(bopsPurchaseInfoPO.getPurchaseId());
			//库存流水记录
			BopsPurchaseVO receivePurchaseVO=new BopsPurchaseVO();
			BeanUtils.copyProperties(bopsPurchaseVO, receivePurchaseVO);
			BeanUtils.copyProperties(bopsPurchaseInfoPO, receivePurchaseVO);
			receivePurchaseVO.setGoodsName(priceInfo.getGoodsName());
			receivePurchaseVO.setPurchaseInfoId(priceInfo.getPurchaseInfoId());
			receivePurchaseVO.setReceiveNo(bopsPurchaseVO.getPurchaseId().toString());
			receivePurchaseVO.setGoodsCode(priceInfo.getGoodsName());
			bopsPurchaseDAO.insertbopsReceiveProduct(receivePurchaseVO);
			//更新商品对应的库存数量
			StoreChangeVO storeChangeVO=new StoreChangeVO();
			storeChangeVO.setGoodsId(priceInfo.getGoodsId());
			storeChangeVO.setDefectiveStore(bopsPurchaseInfoPO.getHavestInfoDisability());
			storeChangeVO.setNowStoreCount(bopsPurchaseInfoPO.getHavestInfoNormal());
			storeChangeVO.setStoreType("PURCHASE");
			storeChangeVO.setStoreNo(bopsPurchaseInfoPO.getPurchaseId().toString());
			//根据purchaseid查询仓库类型
			Integer warehouse=bopsPurchaseDAO.getWarehouseType(bopsPurchaseVO.getPurchaseId());
			storeChangeVO.setWarehouseId(warehouse);
			storeChangeVO.setAuthorId(bopsPurchaseVO.getPurchaseUserId());
			bopsGoodsStoreService.saveStoreChange(storeChangeVO);
			dataList.add(bopsPurchaseInfoPO);
		}
		BeanUtils.copyProperties(bopsPurchaseVO, bopsPurchasePO);
		bopsPurchasePO.setPurchaseCountReal(purchaseCountReal);
		bopsPurchasePO.setPurchasePriceReal(purchasePriceReal);
		//计算差异总金额,差异总数量
		BopsPurchasePO differrentPO=bopsPurchaseDAO.selectDifferrent(bopsPurchaseVO);
		differrentPO.setDifferenceCount(differrentPO.getPurchaseCountAll()-purchaseCountReal);
		differrentPO.setDifferencePrice(differrentPO.getPurchasePriceAll()-purchasePriceReal);
		differrentPO.setPurchaseStatus("HAVEST_ALREADY");
		differrentPO.setHavestUserid(bopsPurchaseVO.getPurchaseUserId().toString());
		//收货完成
		differrentPO.setPurchaseIsHavest("1");
		if(differrentPO.getPurchaseCountAll().intValue() == bopsPurchasePO.getPurchaseCountReal().intValue()){
			differrentPO.setPurchaseIsDifferent("0");
		}else{
			differrentPO.setPurchaseIsDifferent("1");
		}
		differrentPO.setPurchaseId(bopsPurchaseVO.getPurchaseId());
		differrentPO.setPurchaseCountReal(purchaseCountReal);
		differrentPO.setPurchasePriceReal(purchasePriceReal);
		bopsPurchaseDAO.updatePurchase(differrentPO);
		creditStore(bopsPurchaseVO.getPurchaseId(),bopsPurchaseVO.getPurchaseUserId().toString(),dataList);
		return bopsPurchaseVO;
	}
	/**
	 * 
	 * @author liuhongyang 2015年11月24日 下午7:29:00
	 * @Method: getMessageToSave 
	 * @Description: 根据wms回传修改库存及采购单
	 * @param eSynEdiReceiveMessage 
	 * @throws
	 */
	@Transactional("bops_txManager")
	public void saveMessageToSave(ESynEdiReceiveMessage eSynEdiReceiveMessage){
		BopsReceiveSuccess bopsReceiveSuccess=bopsReceiveSuccessDAO.selectByMsgId(eSynEdiReceiveMessage.getId().toString());
		if(null == bopsReceiveSuccess){
			try {
				BopsReceiveSuccess bopsReceive=new BopsReceiveSuccess();
				bopsReceive.setMsgId(eSynEdiReceiveMessage.getId().toString());
				bopsReceiveSuccessDAO.insert(bopsReceive);
				String body=eSynEdiReceiveMessage.getBody();
				PurchaseInVO purchaseInVO=(PurchaseInVO) JSONObject.parseObject(body, PurchaseInVO.class);
				logger.info("saveMessageToSave............purchaseInVO"+JSONObject.parseObject(body, PurchaseInVO.class).toString());
				BopsPurchasePO purchPO = new BopsPurchasePO();
				purchPO.setPurchaseId(Integer.parseInt(purchaseInVO.getBillId().toString()));
				//商品级别
				List<PurchaseItemInVO> itemList=purchaseInVO.getItems();
				//实际收货数量 
				int purchaseCountReal=0;
				//实际收货总金额
				Integer purchasePriceReal=0;
				List<BopsPurchaseInfoPO> dataList=new ArrayList<BopsPurchaseInfoPO>();
				for (PurchaseItemInVO purchaseItemInVO : itemList) {
					//修改采购单状态
					BopsPurchaseInfoPO bopsGoods=new BopsPurchaseInfoPO();
					bopsGoods.setPurchaseId(purchPO.getPurchaseId());
					BopsGoods bopsGood=bopsGoodsDAO.selectByGoodsCode(purchaseItemInVO.getSkuId().toString());
					bopsGoods.setGoodsCode(purchaseItemInVO.getSkuId().toString());
					bopsGoods.setGoodsId(bopsGood.getGoodsId());
					bopsGoods.setGoodsName(bopsGood.getGoodsName());
					//根据回传的收货信息,查询原来收货信息,进行相加
					BopsPurchaseInfoPO bopsGoodsPre = bopsPurchaseInfoDAO.selectPrice(bopsGoods);
					if(null != bopsGoodsPre){
						bopsGoods.setHavestInfoNormal(bopsGoodsPre.getHavestInfoNormal().intValue()+purchaseItemInVO.getQuantity().intValue());
						bopsGoods.setHavestInfoCount(bopsGoodsPre.getHavestInfoCount().intValue()+purchaseItemInVO.getQuantity().intValue());
					}else{
						bopsGoods.setHavestInfoNormal(Integer.parseInt(purchaseItemInVO.getQuantity().toString()));
						bopsGoods.setHavestInfoCount(Integer.parseInt(purchaseItemInVO.getQuantity().toString()));
					}
					bopsPurchaseInfoDAO.updatePurchaseInfoCountFromWMS(bopsGoods);
					//更新商品对应的库存数量
					StoreChangeVO storeChangeVO=new StoreChangeVO();
					storeChangeVO.setGoodsId(bopsGood.getGoodsId());
					storeChangeVO.setDefectiveStore(bopsGoods.getHavestInfoDisability());
					storeChangeVO.setNowStoreCount(purchaseItemInVO.getQuantity().intValue());
					storeChangeVO.setStoreType("PURCHASE");
					storeChangeVO.setStoreNo(bopsGoods.getPurchaseId().toString());
					//根据purchaseid查询仓库类型
					Integer warehouse=bopsPurchaseDAO.getWarehouseType(bopsGoods.getPurchaseId());
					storeChangeVO.setWarehouseId(warehouse);
					storeChangeVO.setAuthorId(Constants.WMS_USER_ID);
					logger.info("saveStoreChange.............."+storeChangeVO.toString());
					bopsGoodsStoreService.saveStoreChange(storeChangeVO);
					dataList.add(bopsGoods);
				}
				//修改实际收货数量和金额
				List<BopsPurchaseInfoPO> infoPOList= bopsPurchaseInfoDAO.selectGoodsList(purchPO.getPurchaseId());
				for (BopsPurchaseInfoPO bopsPurchaseInfoPO : infoPOList) {
					//实际总数量
					purchaseCountReal=purchaseCountReal+bopsPurchaseInfoPO.getHavestInfoDisability().intValue()+bopsPurchaseInfoPO.getHavestInfoNormal().intValue();
					BopsGoods priceGoods = new BopsGoods();
					//实际总金额
					purchasePriceReal=purchasePriceReal+bopsPurchaseInfoPO.getPurchasePrice().intValue()*bopsPurchaseInfoPO.getHavestInfoCount().intValue();
				}
				purchPO.setPurchaseCountReal(purchaseCountReal);
				purchPO.setPurchasePriceReal(purchasePriceReal);
				//计算差异总金额,差异总数量
				BopsPurchaseVO bopsPurchaseVO=new BopsPurchaseVO();
				bopsPurchaseVO.setPurchaseId(purchPO.getPurchaseId());
				//查询采购金额和采购数量
				BopsPurchasePO differrentPO=bopsPurchaseDAO.selectDifferrent(bopsPurchaseVO);
				purchPO.setDifferenceCount(differrentPO.getPurchaseCountAll().intValue()-purchaseCountReal);
				purchPO.setDifferencePrice(differrentPO.getPurchasePriceAll().intValue()-purchasePriceReal);
				//收货状态
				if(purchaseInVO.getReceiveStatus().equals("2")){
					//部分收货
					purchPO.setPurchaseStatus("HAVEST_HALF");
					purchPO.setPurchaseIsHavest("0"); 
				}else if(purchaseInVO.getReceiveStatus().equals("4")){
					//全部收货
					purchPO.setPurchaseStatus("HAVEST_ALREADY");
					purchPO.setPurchaseIsHavest("1");
				}
				purchPO.setHavestUserid(Constants.WMS_USER_ID.toString());
				purchPO.setHavestUsername(purchaseInVO.getUserName());
				//收货差异
				if(purchPO.getPurchaseCountAll().intValue()==purchPO.getPurchaseCountReal().intValue()){
					purchPO.setPurchaseIsDifferent("0");
				}else{
					purchPO.setPurchaseIsDifferent("1");
				}
				bopsPurchaseDAO.updatePurchaseFromWMS(purchPO);
				eSynEdiReceiveMessage.setStatus(2L);
				eSynEdiReceiveMessage.setHandleDate(new Date());
				eSynEdiReceiveMessageDAO.updateByPrimaryKeySelective(eSynEdiReceiveMessage);
				creditStore(purchPO.getPurchaseId(),Constants.WMS_USER_DEEFAULT,dataList);
			} catch (Exception e) {
				eSynEdiReceiveMessage.setStatus(9L);
				eSynEdiReceiveMessage.setHandleDate(new Date());
				eSynEdiReceiveMessageDAO.updateByPrimaryKeySelective(eSynEdiReceiveMessage);
			}
		}
		
	}
	/**
	 * 
	 * @author liuhongyang 2015年12月17日 下午2:42:54
	 * @Method: handlerSendData 
	 * @Description: 手动同步数据到wms
	 * @param purchaseId 
	 * @see com.need.service.bops.purchase.BopsPurchaseService#handlerSendData(java.lang.Integer)
	 */
	@Override
	public void handlerSendData(Integer purchaseId) {
		BopsPurchasePO bopsPurchasePO = bopsPurchaseDAO.selectByPrimaryKey(purchaseId);
		List<BopsPurchaseInfoPO> goodsList=bopsPurchaseInfoDAO.selectGoodsList(purchaseId);
		PurchaseOutVO purchaseOutVO = new PurchaseOutVO();
		List<PurchaseItemOutVO> itemsList=new ArrayList<PurchaseItemOutVO>();
		if(goodsList.size()>0){
			for (BopsPurchaseInfoPO bopsPurchaseInfoPO : goodsList) {
				PurchaseItemOutVO purchaseItemOutVO = new PurchaseItemOutVO();
				purchaseItemOutVO.setSkuId(Long.parseLong(bopsPurchaseInfoPO.getGoodsCode()));
				purchaseItemOutVO.setName(bopsPurchaseInfoPO.getGoodsName());
				purchaseItemOutVO.setQuantity(Long.parseLong(bopsPurchaseInfoPO.getPurchaseCount().toString()));
				itemsList.add(purchaseItemOutVO);
			}
		}
		//根据仓库id查询仓库是否安装wms系统
		String flag=bopsPurchaseDAO.selectWareHouseDredge(bopsPurchasePO.getWarehouseId());
		//1为安装
		if("1".equals(flag)){
			/**
			 * 采购单下发至wms
			 */
			logger.info("in savePurchase flag: " +flag);
			purchaseOutVO.setId(Long.parseLong(bopsPurchasePO.getPurchaseId().toString()));
			purchaseOutVO.setWarehouseId(Long.parseLong(bopsPurchasePO.getWarehouseId().toString()));
			purchaseOutVO.setPurchaseNo(bopsPurchasePO.getPurchaseId().toString());
			purchaseOutVO.setVendorId(Long.parseLong(bopsPurchasePO.getProviderId().toString()));
			Date date=new Date();
			purchaseOutVO.setCreateDate(date);
			//根据用户id查询真实姓名
			if(0 == bopsPurchasePO.getPurchaseUserId()){
				purchaseOutVO.setCreateBy(Constants.SUPERADMIN);
			}else{
				BopsUser bopsUser=bopsUserDAO.selectByPrimaryKey(bopsPurchasePO.getPurchaseUserId());
				purchaseOutVO.setCreateBy(bopsUser.getUserRealName());
			}
			purchaseOutVO.setRemark(bopsPurchasePO.getPurchaseRemark());
			purchaseOutVO.setItems(itemsList);
			//保存到报文发送表
			ESynEdiMessage eSynEdiMessage = new ESynEdiMessage(bopsPurchasePO.getPurchaseId().toString(), "101",JSONObject.toJSONString(purchaseOutVO), purchaseOutVO.getWarehouseId());
			logger.info("in savePurchase eSynEdiMessage: " +JSONObject.toJSONString(purchaseOutVO));
			eSynEdiMessageDAO.insert(eSynEdiMessage);
			//发送到wms
			NeedProducer needProducer = NeedProducer.getInstance();
			Boolean result = needProducer.sendSync("zz_erp2wms_101", eSynEdiMessage.getId()+"",JSONObject.toJSONString(eSynEdiMessage));
			Long status;
			//改变结果状态
			if(result){
				status=2L;
			}else{
				status=9L;
			}
			eSynEdiMessage.setStatus(status);
			eSynEdiMessageDAO.updateByPrimaryKey(eSynEdiMessage);
		}
		
	}
	/**
	 * 
	 * @author liuhongyang 2015年12月18日 下午4:18:07
	 * @Method: creditStore 
	 * @Description: 库存流水记录
	 * @param purchaseId 
	 * @throws
	 */
	private void creditStore(Integer purchaseId,String havestUserId,List<BopsPurchaseInfoPO> dataList){
		//库存流水记录
		BopsPurchasePO purchase = bopsPurchaseDAO.selectByPrimaryKey(purchaseId);
		BopsCreditMemoPO bopsCreditMemoPO = new BopsCreditMemoPO();
		bopsCreditMemoPO.setReportType(ReportTypeEnum.PURCHASE.code);
		bopsCreditMemoPO.setSerialNumber(purchase.getPurchaseId().toString());
		bopsCreditMemoPO.seteWarehouseId(purchase.getWarehouseId());
		bopsCreditMemoPO.setRemark(purchase.getPurchaseRemark());
		bopsCreditMemoPO.setCreatedBy(havestUserId);
		bopsCreditMemoPO.setCreatedAt(new Date());
		bopsCreditMemoDAO.insert(bopsCreditMemoPO);
		int changeCount = 0;
		int changeTotalCost=0;
		int changeCreditAmount=0;
		if(dataList.size()>0){
			for (BopsPurchaseInfoPO purchaseInfo : dataList) {
				BopsCreditMemoItemPO bopsCreditMemoItemPO = new BopsCreditMemoItemPO();
				bopsCreditMemoItemPO.setBopsCreditMemoId(bopsCreditMemoPO.getId());
				bopsCreditMemoItemPO.setGoodsCode(purchaseInfo.getGoodsCode());
				//根据goodsId查询库存id
				BopsInventoryPO bopsInventoryPO = bopsInventoryDAO.selectByGoodsId(purchaseInfo.getGoodsId());
				if(null != bopsInventoryPO){
					bopsCreditMemoItemPO.setBopsInventoryId(bopsInventoryPO.getId());
					bopsCreditMemoItemPO.setNormalChange(bopsInventoryPO.getNormalQuantity());
					bopsCreditMemoItemPO.setDemageQuantity(bopsInventoryPO.getDemageQuantity());
					bopsCreditMemoItemPO.setCost(bopsInventoryPO.getCost());
					bopsCreditMemoItemPO.setCreatedAt(new Date());
				}
				bopsCreditMemoItemPO.setGoodsName(purchaseInfo.getGoodsName());
				bopsCreditMemoItemPO.setNormalChange(purchaseInfo.getHavestInfoNormal());
				bopsCreditMemoItemPO.setDemageChange(purchaseInfo.getHavestInfoDisability());
				bopsCreditMemoItemPO.setPrice(bopsInventoryPO.getPurchasePrice());
				bopsCreditMemoItemPO.setCreatedBy(havestUserId);
				bopsCreditMemoItemDAO.insert(bopsCreditMemoItemPO);
				changeCount = changeCount + bopsCreditMemoItemPO.getDemageChange().intValue() + bopsCreditMemoItemPO.getNormalChange().intValue();
				changeTotalCost = changeTotalCost + changeCount*bopsCreditMemoItemPO.getPrice().intValue();
				changeCreditAmount = changeCreditAmount + changeCount*bopsCreditMemoItemPO.getCost().intValue();
			}
			bopsCreditMemoPO.setTotalCost(changeTotalCost);
			bopsCreditMemoPO.setCreditAmount(changeCreditAmount);
			bopsCreditMemoDAO.updateByPrimaryKey(bopsCreditMemoPO);
		}
	
	}

}
