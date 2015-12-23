package com.need.service.bops.goods.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
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

import com.need.dao.api.goods.GoodsMainDAO;
import com.need.dao.api.trade.TradeBaseDAO;
import com.need.dao.bops.goods.BopsGoodsDAO;
import com.need.dao.bops.goods.BopsGoodsStoreDAO;
import com.need.domain.common.enums.GoodsTypeEnums;
import com.need.domain.common.enums.StoreTypeEnum;
import com.need.domain.common.enums.WarehouseTypeEnum;
import com.need.domain.po.api.goods.GoodsMainPO;
import com.need.domain.po.bops.goods.BopsGoods;
import com.need.domain.po.bops.goods.BopsGoodsStore;
import com.need.domain.vo.goods.GoodsStorePageVO;
import com.need.domain.vo.goods.GoodsStoreResultVO;
import com.need.domain.vo.wms.StoreChangeVO;
import com.need.service.bops.goods.BopsGoodsStoreService;
import com.need.service.constant.Constants;
import com.need.utils.ExcelUtil;
import com.need.utils.StringUtil;

@Service
public class BopsGoodsServiceStoreImpl implements BopsGoodsStoreService {

	@Autowired
	private BopsGoodsStoreDAO bopsGoodsStoreDAO;

	@Autowired
	private GoodsMainDAO goodsMainDAO;

	@Autowired
	private BopsGoodsDAO bopsGoodsDAO;

	@Autowired
	private TradeBaseDAO tradeBaseDAO;

	@Override
	public BopsGoodsStore getStoreById(int storeId) {
		// TODO Auto-generated method stub
		return bopsGoodsStoreDAO.selectByPrimaryKey(storeId);
	}

	public BopsGoodsStore getStoreByGoodsId(String goodsId) {
		return bopsGoodsStoreDAO.getByGoodsId(goodsId);
	}

	@Override
	@Transactional
	public void deleteGoodsStoreById(String goodsId) {
		// TODO Auto-generated method stub
		bopsGoodsStoreDAO.deleteByPrimaryKey(goodsId);
	}

	@Override
	@Transactional
	public void insertNewStore(GoodsStorePageVO store) {
		// TODO Auto-generated method stub
		String goodsId = store.getGoodsId();
		GoodsMainPO goodsMain = goodsMainDAO.selectByPrimaryKey(goodsId);
		store.setHistoryStore(goodsMain.getStoreCount());
		if("GOOD".equals(store.getStoreType())){
			bopsGoodsStoreDAO.insertNewStore(store);// 添加后台库存记录
			// 添加前台商品库存数量
			addPortalGoodsCount(goodsId, store.getNowStoreCount());
		}
		else{
			store.setDefectiveStore(store.getNowStoreCount());
			bopsGoodsStoreDAO.insertBadStore(store);
		}
	}

	@Transactional
	public void updateStore(BopsGoodsStore store) {
		bopsGoodsStoreDAO.updateById(store);
	}

	/**
	 * @author xiehao 2015年8月19日 下午5:31:10
	 * @Method: pageBopsGoodsStore
	 * @Description: TODO 获取库存分页数据
	 * @param page
	 * @return
	 * @see com.need.bops.service.goods.BopsGoodsStoreService#pageBopsGoodsStore(com.need.bops.web.controller.vo.goods.GoodsStorePageVO)
	 */
	@Override
	public List<GoodsStoreResultVO> pageBopsGoodsStore(GoodsStorePageVO page) {
		page.setTotal(bopsGoodsStoreDAO.queryPageBopsGoodsStoreCount(page));
		List<GoodsStoreResultVO> storeList = new ArrayList<GoodsStoreResultVO>();
		storeList = bopsGoodsStoreDAO.queryPageBopsGoodsStore(page);
		GoodsMainPO goodsMain = null;
		for (GoodsStoreResultVO store : storeList) {
			// 把商品图片封装成List
			String goodsId = store.getGoodsId();
			String topPicListString = store.getTopPicKeys();
			//暂时注掉
			if (!StringUtils.hasText(topPicListString)) {
				continue;
			}
			if (',' == topPicListString.charAt(0))
				topPicListString = topPicListString.substring(1);
			String[] topPicStringArray = topPicListString.split(",");
			List<String> topPicList = new ArrayList<String>();
			Collections.addAll(topPicList, topPicStringArray);
			store.setTopPicList(topPicList);
			store.setTopPicKeys(null);

			goodsMain = goodsMainDAO.selectByPrimaryKey(goodsId);
			if(goodsMain!=null){
				store.setLockedCount(goodsMain.getLockCount());
				store.setOnWayCount(tradeBaseDAO.getGoodsOutStoreCount(goodsId));
				store.setNowStoreCount(goodsMain.getStoreCount());	
				store.setGoodsType(goodsMain.getGoodsType());
			}
			int defectiveStore = bopsGoodsStoreDAO.countDefectiveStore(goodsId);//残品库存
			store.setDefectiveStore(defectiveStore);
			
		}
		
		if(storeList!=null && storeList.size()>0){
			for (int i = 0; i < storeList.size(); i++) {
				GoodsStoreResultVO goodsStoreResultVO = storeList.get(i);
				if((!StringUtil.isEmptyOrNull(goodsStoreResultVO.getGoodsType()))&&GoodsTypeEnums.MORE.code.equals(goodsStoreResultVO.getGoodsType())){
//					SuitGoodsPriceVO suitGoodsPriceVO= 	bopsGoodsService.querySuitGoodsPrice(goodsStoreResultVO.getGoodsId(),"store");
					//获取组合商品的可售库存
					int storeCount = goodsMainDAO.queryMinStoreByGoodsGroupId(goodsStoreResultVO.getGoodsId());
					goodsStoreResultVO.setNowStoreCount(storeCount);
//					if(suitGoodsPriceVO!=null){
//						goodsStoreResultVO.setDiscountPrice(suitGoodsPriceVO.getDiscountPrice());
//						goodsStoreResultVO.setOnsalePrice(suitGoodsPriceVO.getOnsalePrice());
//					}
				}
			}
		}

		return storeList;
	}

	@Override
	@Transactional
	public void updateAudit(GoodsStorePageVO goodsStorePageVO) {
		String goodsId = goodsStorePageVO.getGoodsId();
		int changeCount = goodsStorePageVO.getNowStoreCount() - getStoreByGoodsId(goodsId).getNowStoreCount();
		bopsGoodsStoreDAO.updateAuditStore(goodsStorePageVO);
		addPortalGoodsCount(goodsStorePageVO.getGoodsId(), goodsStorePageVO.getNowStoreCount(), changeCount);
	}

	@Override
	@Transactional
	public void frozenStore(GoodsStorePageVO goodsStorePageVO) {
		// TODO Auto-generated method stub
		bopsGoodsStoreDAO.updateAuditStore(goodsStorePageVO);
	}

	@Override
	// @Transactional
	public void addPortalGoodsCount(String goodsId, int storeCount) {
		// TODO Auto-generated method stub
		GoodsMainPO goodsMain = goodsMainDAO.selectByPrimaryKey(goodsId);
		int goodsCount = goodsMain.getStoreCount() == null ? 0 : goodsMain.getStoreCount();
		storeCount = storeCount + goodsCount;// 把这次添加的库存数量累加到前台的商品的可销售数量中去
		goodsMain.setStoreCount(storeCount);
		goodsMainDAO.updateStoreCount(goodsMain);
	}

	@Override
	// @Transactional
	public void addPortalGoodsCount(String goodsId, int storeCount, int changeCount) {
		// TODO Auto-generated method stub
		GoodsMainPO goodsMain = goodsMainDAO.selectByPrimaryKey(goodsId);
		int goodsCount = goodsMain.getStoreCount() == null ? 0 : goodsMain.getStoreCount();
		goodsCount = goodsCount + changeCount;
		goodsMain.setStoreCount(goodsCount);
		goodsMainDAO.updateStoreCount(goodsMain);
	}

	@Override
	public GoodsStoreResultVO getGoodsMainData(String goodsId) {
		// TODO Auto-generated method stub
		GoodsMainPO goodsMain = goodsMainDAO.selectByPrimaryKey(goodsId);
		BopsGoods bopsGoods = selectByGoodsId(goodsId);
		bopsGoods.setAuditStatus(null);
		GoodsStoreResultVO resultVO = new GoodsStoreResultVO();
		BeanUtils.copyProperties(bopsGoods, resultVO);
		resultVO.setAllowSellCount(goodsMain.getStoreCount());
		resultVO.setSoldCount(tradeBaseDAO.getGoodsSoldCount(goodsId));
		// resultVO.setTradeOccupyCount(tradeBaseDAO.getTradeLockedCount(goodsId));

		return resultVO;
	}

	@Override
	public BopsGoods selectByGoodsId(String goodsId) {
		// TODO Auto-generated method stub
		return bopsGoodsDAO.selectByGoodsId(goodsId);
	}

	@Override
	public GoodsStoreResultVO getGoodsStore(String goodsId) {
		// TODO Auto-generated method stub
		GoodsMainPO goodsMain = goodsMainDAO.selectByPrimaryKey(goodsId);// 查询前台商品信息
		BopsGoodsStore store = getStoreByGoodsId(goodsId);
		BopsGoods bopsGoods = bopsGoodsDAO.selectByGoodsId(goodsId);
		GoodsStoreResultVO resultVO = new GoodsStoreResultVO();
		int defectiveStore = bopsGoodsStoreDAO.countDefectiveStore(goodsId);//残品库存
		
		// BeanUtils.copyProperties(goodsMain, resultVO);
		resultVO.setGoodsCode(bopsGoods.getGoodsCode());
		resultVO.setGoodsBarcode(bopsGoods.getGoodsBarcode());
		resultVO.setGoodsId(goodsId);
		resultVO.setScenePicKey(bopsGoods.getScenePicKey());
		resultVO.setGoodsName(bopsGoods.getGoodsName());
		resultVO.setOnsalePrice((double) bopsGoods.getOnsalePrice() / Constants.PRICE_TO_FEN_OR_YUAN);
		resultVO.setDiscountPrice((double) bopsGoods.getDiscountPrice() / Constants.PRICE_TO_FEN_OR_YUAN);
		resultVO.setSoldCount(tradeBaseDAO.getGoodsSoldCount(goodsId)); // APP已销售数量
		resultVO.setNowStoreCount(store == null ? 0 : store.getNowStoreCount());// 后台库存数
		resultVO.setAllowSellCount(
				goodsMain == null ? 0 : goodsMain.getStoreCount() == null ? 0 : goodsMain.getStoreCount());// APP获取的可销售数量
		resultVO.setLockedCount(goodsMain.getLockCount());
		resultVO.setDefectiveStore(defectiveStore);
		resultVO.setAllStore(defectiveStore + resultVO.getAllowSellCount() + resultVO.getLockedCount());

		// 把数据库中以逗号分隔的商品图片字符串转换成List
		String topPicListString = bopsGoods.getTopPicKeys();
		if(StringUtils.hasText(topPicListString)){
			if (',' == topPicListString.charAt(0))
				topPicListString = topPicListString.substring(1);
			String[] topPicStringArray = topPicListString.split(",");
			List<String> topPicList = new ArrayList<String>();
			Collections.addAll(topPicList, topPicStringArray);
			resultVO.setTopPicList(topPicList);
		}

		return resultVO;
	}

	/**
	 * 
	 * @author xiehao 2015年8月19日 上午11:57:34
	 * @Method: pageStoreLog
	 * @Description: TODO 查看某个商品的库存历史记录
	 * @param page
	 * @return
	 * @see com.need.bops.service.goods.BopsGoodsStoreService#pageStoreLog(com.need.bops.web.controller.vo.goods.GoodsStorePageVO)
	 */
	@Override
	public List<GoodsStoreResultVO> pageStoreLog(String goodsId) {
		// TODO Auto-generated method stub
		return bopsGoodsStoreDAO.queryStoreLog(goodsId);
	}

	@Override
	public HSSFWorkbook exportStore(GoodsStorePageVO storePageVO) {
		// TODO Auto-generated method stub
		List<GoodsStoreResultVO> storeList = bopsGoodsStoreDAO.queryExportStore(storePageVO);
		GoodsMainPO goodsMain = null;
		for (GoodsStoreResultVO store : storeList) {
			String goodsId = store.getGoodsId();
			// int soldCount = tradeBaseDAO.getGoodsSoldCount(goodsId);//已销售的数量
			// store.setNowStoreCount(store.getNowStoreCount() -
			// soldCount);//导出的库存数量应该是总库存数减去已经销售的数量
			store.setDefectiveStore(bopsGoodsStoreDAO.countDefectiveStore(goodsId));
			goodsMain = goodsMainDAO.selectByPrimaryKey(goodsId);
			store.setLockedCount(goodsMain.getLockCount());// 锁定数
			store.setOnWayCount(tradeBaseDAO.getGoodsOutStoreCount(goodsId));// 出库数
			store.setNowStoreCount(goodsMain.getStoreCount());// 直接取前台库该商品的可销售数量
		}

		HSSFWorkbook workbook = excelStore(storeList);
		return workbook;
	}

	private HSSFWorkbook excelStore(List<GoodsStoreResultVO> data) {

		HSSFWorkbook workbook = new HSSFWorkbook();
		// SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		HSSFSheet sheet = ExcelUtil.createSheet(workbook, "库存数据");
		// 创建表头
		Font font = ExcelUtil.createFont(workbook, (short) 5, HSSFColor.BLACK.index, (short) 10);
		CellStyle cellStylec = ExcelUtil.createBorderCellStyle(workbook, HSSFColor.WHITE.index, HSSFColor.WHITE.index,
				(short) 30, font);
		HSSFRow row = ExcelUtil.createRow(sheet, 0, 300);
		row.getSheet().setColumnWidth(1, 5000);
		row.getSheet().setColumnWidth(2, 8000);
		HSSFCell cell = ExcelUtil.createCell(row, 0, cellStylec);
		cell.setCellValue("商品编码");
		cell = ExcelUtil.createCell(row, 1, cellStylec);
		cell.setCellValue("国际条形码");
		cell = ExcelUtil.createCell(row, 2, cellStylec);
		cell.setCellValue("商品名称");
		cell = ExcelUtil.createCell(row, 3, cellStylec);
		cell.setCellValue("商品原价");
		cell = ExcelUtil.createCell(row, 4, cellStylec);
		cell.setCellValue("商品折扣价");
		cell = ExcelUtil.createCell(row, 5, cellStylec);
		cell.setCellValue("正品库存");
		cell = ExcelUtil.createCell(row, 6, cellStylec);
		cell.setCellValue("残品库存");
		cell = ExcelUtil.createCell(row, 7, cellStylec);
		cell.setCellValue("锁定数");
		cell = ExcelUtil.createCell(row, 8, cellStylec);
		cell.setCellValue("出库数");
		cell = ExcelUtil.createCell(row, 9, cellStylec);
		cell.setCellValue("仓库类型");

		// 插入实体数据
		GoodsStoreResultVO po = null;
		for (int i = 1; i < data.size() + 1; i++) {
			po = data.get(i - 1);
			row = ExcelUtil.createRow(sheet, i, 500);
			cell = ExcelUtil.createCell(row, 0, cellStylec);
			cell.setCellValue(Long.parseLong(po.getGoodsCode().trim()));
			cell = ExcelUtil.createCell(row, 1, cellStylec);
			cell.setCellValue(po.getGoodsBarcode());
			cell = ExcelUtil.createCell(row, 2, cellStylec);
			cell.setCellValue(po.getGoodsName());
			cell = ExcelUtil.createCell(row, 3, cellStylec);
			cell.setCellValue(po.getOnsalePrice());
			cell = ExcelUtil.createCell(row, 4, cellStylec);
			cell.setCellValue(po.getDiscountPrice());
			cell = ExcelUtil.createCell(row, 5, cellStylec);
			cell.setCellValue(po.getNowStoreCount());
			cell = ExcelUtil.createCell(row, 6, cellStylec);
			cell.setCellValue(po.getDefectiveStore());
			cell = ExcelUtil.createCell(row, 7, cellStylec);
			cell.setCellValue(po.getLockedCount());
			cell = ExcelUtil.createCell(row, 8, cellStylec);
			cell.setCellValue(po.getOnWayCount());
			cell = ExcelUtil.createCell(row, 9, cellStylec);
			cell.setCellValue(WarehouseTypeEnum.getDesc(po.getWarehouseType()));
		}

		return workbook;
	}

	@Override
	public boolean saveStoreChange(StoreChangeVO storeChangeVO) {
		// TODO Auto-generated method stub
		String goodsId = storeChangeVO.getGoodsId();
		if(StoreTypeEnum.PURCHASE.code.equals(storeChangeVO.getStoreType()))
		{
			addPortalGoodsCount(goodsId, storeChangeVO.getNowStoreCount());
		}
		GoodsMainPO goodsMain = goodsMainDAO.selectByPrimaryKey(goodsId);
		storeChangeVO.setHistoryStore(goodsMain.getStoreCount());
		bopsGoodsStoreDAO.saveStoreChange(storeChangeVO);
		
		return true;
	}

}
