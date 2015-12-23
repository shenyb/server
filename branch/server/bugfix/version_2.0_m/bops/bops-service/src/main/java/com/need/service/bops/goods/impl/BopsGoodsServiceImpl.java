package com.need.service.bops.goods.impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.need.biz.utils.BizCodeUtil;
import com.need.biz.utils.CurrencyUtil;
import com.need.dao.api.goods.GoodsDetailDAO;
import com.need.dao.api.goods.GoodsItemsDAO;
import com.need.dao.api.goods.GoodsMainDAO;
import com.need.dao.bops.goods.BopsGoodsCategoriesDAO;
import com.need.dao.bops.goods.BopsGoodsDAO;
import com.need.dao.bops.goods.BopsGoodsDetailDAO;
import com.need.dao.bops.goods.BopsGoodsItemsDAO;
import com.need.dao.bops.goods.BopsGoodsStoreDAO;
import com.need.dao.bops.user.BopsUserDAO;
import com.need.domain.common.enums.CheckStatusEnums;
import com.need.domain.common.enums.GoodsStatusEnums;
import com.need.domain.common.enums.WarehouseTypeEnum;
import com.need.domain.po.api.goods.GoodsDetailPO;
import com.need.domain.po.api.goods.GoodsItemsPO;
import com.need.domain.po.api.goods.GoodsMainPO;
import com.need.domain.po.bops.goods.BopsGoods;
import com.need.domain.po.bops.goods.BopsGoodsCategoriesPO;
import com.need.domain.po.bops.goods.BopsGoodsDetail;
import com.need.domain.po.bops.goods.BopsGoodsItemsPO;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.vo.goods.AuditGoodsVO;
import com.need.domain.vo.goods.BopsGoodsItemsVO;
import com.need.domain.vo.goods.BopsGoodsVO;
import com.need.domain.vo.goods.GoodsDetailVO;
import com.need.domain.vo.goods.GoodsPageVO;
import com.need.domain.vo.goods.GoodsParamsVO;
import com.need.domain.vo.goods.GoodsPriceChangeVO;
import com.need.domain.vo.goods.GoodsStoreDetailResultVO;
import com.need.domain.vo.goods.GoodsStoreDetailVO;
import com.need.domain.vo.goods.GroupGoodsDetailVo;
import com.need.domain.vo.goods.OpsGoodsParamVO;
import com.need.domain.vo.goods.SuitGoodsPriceVO;
import com.need.framework.utils.ImageUtils;
import com.need.service.bops.goods.BopsGoodsService;
import com.need.service.bops.ops.BopsOpsHotGoodsService;
import com.need.service.bops.ops.BopsOpsSelectedService;
import com.need.service.bops.wms.ErpSkuToWmsService;
import com.need.service.bops.xinhuan.OpsXinhuaGoodsService;
import com.need.utils.ExcelUtil;
import com.need.utils.StringUtil;

@Service
public class BopsGoodsServiceImpl implements BopsGoodsService {

	private static final Logger logger = Logger.getLogger(BopsGoodsServiceImpl.class);

	@Autowired
	private BopsGoodsDAO bopsGoodsDAO;

	@Autowired
	private BopsGoodsDetailDAO bopsGoodsDetailDAO;

	@Autowired
	private GoodsMainDAO goodsMainDAO;

	@Autowired
	private GoodsDetailDAO goodsDetailDAO;
	@Autowired
	private BopsOpsHotGoodsService bopsOpsHotGoodsService;
	@Autowired
	private BopsOpsSelectedService bopsOpsSelectedService;
	@Autowired
	private ErpSkuToWmsService erpSkuToWmsService;
	@Autowired
	private OpsXinhuaGoodsService opsXinhuaGoodsService;
	@Autowired
	private BopsGoodsCategoriesDAO bopsGoodsCategoriesDAO;
	@Autowired
	private BopsUserDAO bopsUserDAO;
	@Autowired
	private GoodsItemsDAO goodsItemsDAO;
	@Autowired
	private BopsGoodsItemsDAO bopsGoodsItemsDAO;
	@Autowired
	private BopsGoodsStoreDAO bopsGoodsStoreDAO;

	@Override
	@Transactional
	public GoodsStoreDetailVO insertNewGoods(BopsGoods goods, GoodsParamsVO goodsParamsVO,
			GoodsDetailVO goodsDetailVO) {
		// TODO Auto-generated method stub
		/**
		 * 一定要去掉首尾空格
		 */
		goods.setBrief(!StringUtils.hasText(goods.getBrief()) ? "" : goods.getBrief().trim());
		goods.setGoodsBarcode(!StringUtils.hasText(goods.getGoodsBarcode()) ? "" : goods.getGoodsBarcode().trim());
		/**
		 * 为商品的is_global字段赋值，确保客户端的1.2版本可用
		 */
		if (WarehouseTypeEnum.SELF_WAREHOUSE.code.equals(goods.getWarehouseType())) {
			goods.setIsGlobal(Boolean.FALSE.toString().toUpperCase());
		} else {
			goods.setIsGlobal(Boolean.TRUE.toString().toUpperCase());
		}
		bopsGoodsDAO.insertValue(goods);// 插入商品主表
		BopsGoodsDetail bopsGoodsDetail = new BopsGoodsDetail();
		String goodsParamsJSONString = JSONObject.toJSONString(goodsParamsVO);// 把商品的参数信息转成JSON
		bopsGoodsDetail.setGoodsParams(goodsParamsJSONString);
		bopsGoodsDetail.setGoodsId(goods.getGoodsId());
		String detailPicKeys = StringUtil.arrayToFormatString(goodsDetailVO.getDetailPicKeys(), ",");// 以逗号分割多张商品图片
		bopsGoodsDetail.setDetailPicKeys(detailPicKeys);
		/**
		 * 一定要去掉首尾空格
		 */
		bopsGoodsDetail.setGoodsDesc(
				!StringUtils.hasText(goodsDetailVO.getGoodsDesc()) ? "" : goodsDetailVO.getGoodsDesc().trim());
		bopsGoodsDetail.setAuditorId(goods.getAuditorId());
		bopsGoodsDetail
				.setGoodsDesc(bopsGoodsDetail.getGoodsDesc() == null ? "" : bopsGoodsDetail.getGoodsDesc().trim());
		bopsGoodsDetailDAO.insertNewGoods(bopsGoodsDetail);// 插入商品扩展表
		GoodsStoreDetailVO goodsStoreDetailVO = new GoodsStoreDetailVO();
		BeanUtils.copyProperties(goods, goodsStoreDetailVO);
		BeanUtils.copyProperties(goodsParamsVO, goodsStoreDetailVO);
		BeanUtils.copyProperties(goodsDetailVO, goodsDetailVO);

		/**
		 * 把商品信息同步到WMS
		 */
		erpSkuToWmsService.skuInfoToWms(goods.getGoodsId());

		addOrEditPortalGoods(goods.getGoodsId());

		return goodsStoreDetailVO;
	}

	@Override
	public BopsGoods getGoodsById(String goodsId) {
		// TODO Auto-generated method stub
		return bopsGoodsDAO.selectByPrimaryKey(goodsId);
	}

	@Override
	public void updateById(BopsGoods goods) throws NumberFormatException, ParseException {
		// TODO Auto-generated method stub
		// goods.setOnsalePrice(goods.getOnsalePrice() *
		// Constants.PRICE_TO_FEN_OR_YUAN);
		goods.setOnsalePrice(Integer.parseInt(CurrencyUtil.fromYuanToFen(String.valueOf(goods.getOnsalePrice()))));
		// goods.setDiscountPrice(goods.getDiscountPrice() *
		// Constants.PRICE_TO_FEN_OR_YUAN);
		goods.setDiscountPrice(Integer.parseInt(CurrencyUtil.fromYuanToFen(String.valueOf(goods.getDiscountPrice()))));
		bopsGoodsDAO.updateById(goods);
	}

	@Override
	public void deleteById(String goodsId) {
		// TODO Auto-generated method stub
		bopsGoodsDAO.deleteByPrimaryKey(goodsId);
	}

	@Override
	public int updateChangeGoodsPrice(String goodsId, Integer discountPrice) {
		bopsGoodsDAO.updateGoodsPrice(goodsId, discountPrice);
		goodsMainDAO.updateGoodsPrice(goodsId, discountPrice);
		/**
		 * 更改组合商品价格
		 */
		updateGroupGoodsPrice(goodsId);
		return 0;
	}

	@Override
	public List<GoodsStoreDetailResultVO> pageOfBopsGoods(GoodsPageVO goodsPageVO) {
		// TODO Auto-generated method stub
		if (StringUtils.hasText(goodsPageVO.getGoodsCode())) {
			goodsPageVO.setGoodsCode(goodsPageVO.getGoodsCode().trim());
		}
		if (StringUtils.hasText(goodsPageVO.getGoodsBarcode())) {
			goodsPageVO.setGoodsBarcode(goodsPageVO.getGoodsBarcode().trim());
		}
		goodsPageVO.setTotal(bopsGoodsDAO.countPageGoods(goodsPageVO));

		return bopsGoodsDAO.queryPageOfBopsGoods(goodsPageVO);
	}

	/**
	 * 
	 * @author xiehao 2015年8月16日 上午11:55:02
	 * @Method: auditGoods
	 * @Description: TODO 审核通过，添加到前端库（前端通过上下架字段控制商品上下架）
	 * @param bopsGoods
	 * @see com.need.bops.service.goods.BopsGoodsService#auditGoods(com.need.bops.dao.jdbc.bops.goods.po.BopsGoods)
	 */
	@Override
	@Transactional
	public void auditGoods(AuditGoodsVO bopsGoods) {
		// 跟新后台库
		bopsGoods.setUpdateTime(Calendar.getInstance().getTime());
		bopsGoodsDAO.updateAuditGoods(bopsGoods);
		// 审核通过将商品添加到前端库
		addOrEditPortalGoods(bopsGoods.getGoodsId());

		/**
		 * 把商品信息同步到WMS
		 */
		/* erpSkuToWmsService.skuInfoToWms(bopsGoods.getGoodsId()); */
	}

	/**
	 * 
	 * @author LXD 2015年8月14日 下午2:03:30 @Method:
	 *         addOrEditPortalGoods @Description: TODO 添加或是更新前端商品信息 @param
	 *         bopsGoods @throws
	 */

	public void addOrEditPortalGoods(String bopsGoodsId) {
		// TODO Auto-generated method stub
		BopsGoods bopsGoods = new BopsGoods();
		bopsGoods = bopsGoodsDAO.selectByPrimaryKey(bopsGoodsId);
		if ("MORE".equals(bopsGoods.getGoodsType())) {
			BopsGoodsDetail bopsGoodsDetail = bopsGoodsDetailDAO.selectByPrimaryKey(bopsGoods.getGoodsId());
			GoodsDetailPO goodsDetail = new GoodsDetailPO();
			GoodsMainPO goodsMain = new GoodsMainPO();
			BeanUtils.copyProperties(bopsGoods, goodsMain);// 属性COPY
			if (null != bopsGoodsDetail) {
				BeanUtils.copyProperties(bopsGoodsDetail, goodsDetail);
			}
			// 根据id查询商品详细信息
			List<BopsGoodsItemsVO> bopsItemList = bopsGoodsItemsDAO.selectItemList(bopsGoodsId);
			GoodsMainPO goodsPortal = goodsMainDAO.selectByPrimaryKey(bopsGoodsId);
			if (goodsPortal != null) {
				// 根据groupid删除详细信息,重新插入
				goodsMainDAO.deleteByPrimaryKey(bopsGoodsId);
				goodsMainDAO.insertGroupGoods(goodsMain);
				if (null != bopsGoodsDetail) {
					goodsDetailDAO.deleteByPrimaryKey(bopsGoodsId);
					goodsDetailDAO.insertSelective(goodsDetail);
				}
				goodsItemsDAO.deleteByGroupId(bopsGoodsId);
				for (BopsGoodsItemsVO bopsGoodsItemsVO : bopsItemList) {
					GoodsItemsPO GoodsItemsPO = new GoodsItemsPO();
					BeanUtils.copyProperties(bopsGoodsItemsVO, GoodsItemsPO);
					goodsItemsDAO.insert(GoodsItemsPO);
				}
			} else {
				goodsMainDAO.insertSelective(goodsMain);
				if (null != bopsGoodsDetail) {
					goodsDetail.setGoodsDesc(" ");
					goodsDetailDAO.insertSelective(goodsDetail);
				}
				for (BopsGoodsItemsVO bopsGoodsItemsVO : bopsItemList) {
					GoodsItemsPO GoodsItemsPO = new GoodsItemsPO();
					BeanUtils.copyProperties(bopsGoodsItemsVO, GoodsItemsPO);
					goodsItemsDAO.insert(GoodsItemsPO);
				}

			}
		} else {
			BopsGoodsDetail bopsGoodsDetail = bopsGoodsDetailDAO.selectByPrimaryKey(bopsGoods.getGoodsId());
			GoodsDetailPO goodsDetail = new GoodsDetailPO();
			GoodsMainPO goodsMain = new GoodsMainPO();
			BeanUtils.copyProperties(bopsGoods, goodsMain);// 属性COPY
			BeanUtils.copyProperties(bopsGoodsDetail, goodsDetail);
			GoodsMainPO goodsPortal = goodsMainDAO.selectByPrimaryKey(bopsGoodsId);
			if (goodsPortal != null) {
				// 如果该商品已存在，只是更新信息
				BeanUtils.copyProperties(bopsGoods, goodsPortal);
				BeanUtils.copyProperties(bopsGoodsDetail, goodsDetail);
				goodsMainDAO.updateByPrimaryKey(goodsPortal);
				goodsDetailDAO.updateByPrimaryKey(goodsDetail);
			} else {
				goodsMainDAO.insertSelective(goodsMain);
				goodsDetailDAO.insertSelective(goodsDetail);
			}
			// 判断组合商品中是否包含该商品,更新组合商品价格
			List<BopsGoodsItemsPO> itemList = bopsGoodsItemsDAO.selectItemListPrice(bopsGoods.getGoodsId());
			if (itemList.size() > 0) {
				for (BopsGoodsItemsPO bopsGoodsItemsPO : itemList) {
					// 查询商品详细信息,更新前台表
					BopsGoodsDetail goodsDetailVO = bopsGoodsDetailDAO
							.selectByPrimaryKey(bopsGoodsItemsPO.getGoodsGroupId());
					GoodsDetailPO detail = new GoodsDetailPO();
					BeanUtils.copyProperties(goodsDetailVO, detail);
					goodsDetailDAO.updateByPrimaryKey(detail);
				}
			}
			auditGroupPrice(bopsGoods);
		}

	}

	@Override
	public boolean getPortalGoods(String goodsId) {
		// TODO Auto-generated method stub
		if (goodsMainDAO.selectByPrimaryKey(goodsId) != null)
			return true;
		return false;
	}

	@Override
	@Transactional
	public void AddedOrtakeOrfreezeGoods(BopsGoods bopsGoods) {
		String goodsId = bopsGoods.getGoodsId();
		bopsGoodsDAO.updateOnlineOrOffline(bopsGoods);
		// 上架商品，客户端商品也上架
		if ("ONLINE".equals(bopsGoods.getGoodsStatus())) {
			bopsGoods.setGoodsStatus(GoodsStatusEnums.ONLINE.code);
			/***
			 * 上架商品 ，在前端记录第一上架时间
			 */
			onLineGoods(goodsId);

			// 下架商品，客户端商品也下架,如果该商品存在人气或精选中，删除掉
		} else if ("OFFLINE".equals(bopsGoods.getGoodsStatus())) {
			bopsGoods.setGoodsStatus(GoodsStatusEnums.OFFLINE.code);
			addOrEditPortalGoods(goodsId);
			bopsOpsHotGoodsService.deletehotGoodsByGoodsId(goodsId);
			bopsOpsSelectedService.deleteBopsOpsSelectedByGoodsId(goodsId);
			opsXinhuaGoodsService.deleteXinhuanGoodsByGoodsId(goodsId);
			// 冻结商品，客户端商品冻结
		} else {
			bopsGoods.setGoodsStatus(GoodsStatusEnums.FREEZE.code);
			addOrEditPortalGoods(goodsId);

		}

	}

	/**
	 * 上架商品 @author LXD 2015年12月3日 下午8:19:52 @Method: onLineGoods @Description:
	 * TODO @param goodsId @throws
	 */
	private void onLineGoods(String goodsId) {
		/** TODO Auto-generated method stub */
		GoodsMainPO goodsPortal = goodsMainDAO.selectByPrimaryKey(goodsId);
		if (goodsPortal != null) {

			if (goodsPortal.getGoodsOnlineTime() == null) {
				goodsPortal.setGoodsOnlineTime(Calendar.getInstance().getTime());

			}
			goodsMainDAO.updateByPrimaryKey(goodsPortal);
		}

	}

	@Override
	public List<GoodsPriceChangeVO> excelToList(InputStream in, Boolean is07Or10, String pricechangeType) {
		// TODO Auto-generated method stub
		List<GoodsPriceChangeVO> goodsPriceList = new ArrayList<GoodsPriceChangeVO>();
		try {
			if (is07Or10) {
				XSSFWorkbook xssfWorkbook = new XSSFWorkbook(in);
				return read07or10Excel(xssfWorkbook, pricechangeType);
			} else {
				POIFSFileSystem fs = new POIFSFileSystem(in);
				return read03Excel(fs, pricechangeType);
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
		return goodsPriceList;
	}

	private List<GoodsPriceChangeVO> read03Excel(POIFSFileSystem fs, String pricechangeType) {
		HSSFWorkbook wb = null;
		try {
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<GoodsPriceChangeVO> list = new ArrayList<GoodsPriceChangeVO>();
		for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
			HSSFSheet st = wb.getSheetAt(sheetIndex);
			for (int rowIndex = 1; rowIndex <= st.getLastRowNum(); rowIndex++) {
				HSSFRow row = st.getRow(rowIndex);
				if (row == null) {
					continue;
				}
				GoodsPriceChangeVO goodsPriceChangeVO = new GoodsPriceChangeVO();
				if (null != row.getCell(0)) {
					row.getCell(0).setCellType(HSSFCell.CELL_TYPE_STRING);
					goodsPriceChangeVO.setGoodsCode(row.getCell(0).getStringCellValue());
				}
				if (null != row.getCell(1)) {
					row.getCell(1).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					goodsPriceChangeVO.setDiscountPrice(row.getCell(1).getNumericCellValue());
				}
				if ("SCHEDULE".equals(pricechangeType) && null != row.getCell(2)) {
					row.getCell(2).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					goodsPriceChangeVO.setOriginalPrice(row.getCell(2).getNumericCellValue());
				}
				list.add(goodsPriceChangeVO);
			}
		}
		return list;
	}

	private List<GoodsPriceChangeVO> read07or10Excel(XSSFWorkbook xssfWorkbook, String pricechangeType) {
		List<GoodsPriceChangeVO> list = new ArrayList<GoodsPriceChangeVO>();
		for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			if (xssfSheet == null) {
				continue;
			}
			for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (null == xssfRow) {
					continue;
				}
				GoodsPriceChangeVO goodsPriceChangeVO = new GoodsPriceChangeVO();
				if (null != xssfRow.getCell(0)) {
					xssfRow.getCell(0).setCellType(XSSFCell.CELL_TYPE_STRING);
					goodsPriceChangeVO.setGoodsCode(xssfRow.getCell(0).getStringCellValue());
				}
				if (null != xssfRow.getCell(1)) {
					xssfRow.getCell(1).setCellType(XSSFCell.CELL_TYPE_STRING);
					goodsPriceChangeVO.setDiscountPrice(Double.parseDouble(xssfRow.getCell(1).getStringCellValue()));
				}
				if ("SCHEDULE".equals(pricechangeType) && null != xssfRow.getCell(2)) {
					xssfRow.getCell(02).setCellType(XSSFCell.CELL_TYPE_STRING);
					goodsPriceChangeVO.setOriginalPrice(Double.parseDouble(xssfRow.getCell(2).getStringCellValue()));
				}
				list.add(goodsPriceChangeVO);
			}
		}
		return list;
	}

	@Override
	public void deletePortalGoods(String goodsId) {
		/** TODO Auto-generated method stub */
		goodsMainDAO.deleteByPrimaryKey(goodsId);
		goodsDetailDAO.deleteByPrimaryKey(goodsId);
	}

	/**
	 * 
	 * @author LXD 2015年8月13日 上午1:22:13
	 * @Method: viewGoods
	 * @Description: TODO 查看某一商品详情
	 * @param goodsId
	 * @return
	 * @see com.need.bops.service.goods.BopsGoodsService#viewGoods(java.lang.String)
	 */
	@Override
	public GoodsStoreDetailVO viewGoods(String goodsId) {

		BopsGoods bopsGoods = bopsGoodsDAO.selectByPrimaryKey(goodsId);// 从商品主表中查出商品信息
		BopsGoodsDetail goodsdetail = bopsGoodsDetailDAO.selectByPrimaryKey(goodsId);// 从商品的扩展表中获取商品的信息
		GoodsStoreDetailVO goodsStoreDetailVO = new GoodsStoreDetailVO();

		BeanUtils.copyProperties(bopsGoods, goodsStoreDetailVO);
		// goodsStoreDetailVO.setOnsalePrice((double)bopsGoods.getOnsalePrice()
		// / Constants.PRICE_TO_FEN_OR_YUAN); //传给页面的单位是元
		goodsStoreDetailVO.setOnsalePrice(
				Double.parseDouble(CurrencyUtil.fromFenToYuan(String.valueOf(bopsGoods.getOnsalePrice()))));
		// goodsStoreDetailVO.setDiscountPrice((double)bopsGoods.getDiscountPrice()
		// / Constants.PRICE_TO_FEN_OR_YUAN);
		goodsStoreDetailVO.setDiscountPrice(
				Double.parseDouble(CurrencyUtil.fromFenToYuan(String.valueOf(bopsGoods.getDiscountPrice()))));
		if (null != bopsGoods.getPurchasePrice()) {
			goodsStoreDetailVO.setPurchasePrice(
					Double.parseDouble(CurrencyUtil.fromFenToYuan(String.valueOf(bopsGoods.getPurchasePrice()))));
		}
		goodsStoreDetailVO.setTopPicKeysString(bopsGoods.getTopPicKeys());
		if (bopsGoods != null) {
			if (bopsGoods.getTopPicKeys() != null) {
				String[] goodsPics = bopsGoods.getTopPicKeys().split(",");
				goodsStoreDetailVO.setGoodsTopPics(goodsPics);// 把商品图片以数组形式给前端
			}
		}
		if (goodsdetail != null) {
			if (goodsdetail.getDetailPicKeys() != null) {
				String[] detailPics = goodsdetail.getDetailPicKeys().split(",");
				goodsStoreDetailVO.setDetailPicKeys(detailPics);
				goodsStoreDetailVO.setDetailPicKeysString(goodsdetail.getDetailPicKeys());
			}
			goodsStoreDetailVO.setGoodsDesc(goodsdetail.getGoodsDesc());
			JSONObject jsonObject = JSONObject.parseObject(goodsdetail.getGoodsParams());// 把JSON字符串转换成对象属性
			goodsStoreDetailVO.setColor(jsonObject.getString("color"));
			goodsStoreDetailVO.setOriginPlace(jsonObject.getString("originPlace"));
			goodsStoreDetailVO.setSize(jsonObject.getString("size"));
			goodsStoreDetailVO.setWeight(jsonObject.getString("weight"));
		}

		return goodsStoreDetailVO;
	}

	@Override
	@Transactional
	public void editGoods(GoodsStoreDetailVO bopsGoodsVO) throws NumberFormatException, ParseException {
		/** TODO Auto-generated method stub */
		// 对商品进行编辑，组装VO
		BopsGoods bopsGoods = bopsGoodsDAO.selectByPrimaryKey(bopsGoodsVO.getGoodsId());
		BopsGoodsDetail bopsGoodsDetailbops = bopsGoodsDetailDAO.selectByPrimaryKey(bopsGoodsVO.getGoodsId());
		if (bopsGoodsDetailbops == null) {
			bopsGoodsDetailbops = new BopsGoodsDetail();
		}
		BeanUtils.copyProperties(bopsGoodsVO, bopsGoods);
		// bopsGoods.setOnsalePrice((int)(bopsGoodsVO.getOnsalePrice() *
		// Constants.PRICE_TO_FEN_OR_YUAN));//数据库中保存商品的价格单位是分
		bopsGoods.setOnsalePrice(
				Integer.parseInt(CurrencyUtil.fromYuanToFen(String.valueOf(bopsGoodsVO.getOnsalePrice()))));
		// bopsGoods.setDiscountPrice((int)(bopsGoodsVO.getDiscountPrice() *
		// Constants.PRICE_TO_FEN_OR_YUAN));
		bopsGoods.setDiscountPrice(
				Integer.parseInt(CurrencyUtil.fromYuanToFen(String.valueOf(bopsGoodsVO.getDiscountPrice()))));
		bopsGoods.setPurchasePrice(Integer.parseInt(CurrencyUtil.fromYuanToFen(
				String.valueOf(bopsGoodsVO.getPurchasePrice() != null ? bopsGoodsVO.getPurchasePrice() : 0D))));
		BeanUtils.copyProperties(bopsGoodsVO, bopsGoodsDetailbops);
		if (bopsGoodsVO.getTopPicKeys() != null) {
			String topPickeys = StringUtil.arrayToFormatString(bopsGoodsVO.getTopPicKeys(), ",");// 把页面传过来的字符串数组转化成一个以逗号分隔的字符串
			bopsGoods.setTopPicKeys(topPickeys);
		}
		if (bopsGoodsVO.getDetailPicKeys() != null) {
			String detailPicKeys = StringUtil.arrayToFormatString(bopsGoodsVO.getDetailPicKeys(), ",");// 把页面传过来的字符串数组转化成一个以逗号分隔的字符串
			bopsGoodsDetailbops.setDetailPicKeys(detailPicKeys);
		}
		GoodsParamsVO goodsParamsVO = new GoodsParamsVO();// 商品属性的在数据库中是以一个JSON字符串保存，把商品的各种属性封装成VO，在转换成JSON
		BeanUtils.copyProperties(bopsGoodsVO, goodsParamsVO);
		String goodsParamsJSONString = JSONObject.toJSONString(goodsParamsVO);
		bopsGoods.setAuditStatus(CheckStatusEnums.WAIT.code);
		bopsGoodsDetailbops.setGoodsParams(goodsParamsJSONString);
		/**
		 * 去掉首尾空格
		 */
		bopsGoods.setBrief(!StringUtils.hasText(bopsGoods.getBrief()) ? "" : bopsGoods.getBrief().trim());
		bopsGoods.setGoodsBarcode(
				!StringUtils.hasText(bopsGoods.getGoodsBarcode()) ? "" : bopsGoods.getGoodsBarcode().trim());
		bopsGoodsDetailbops.setGoodsDesc(!StringUtils.hasText(bopsGoodsDetailbops.getGoodsDesc()) ? ""
				: bopsGoodsDetailbops.getGoodsDesc().trim());
		bopsGoodsDAO.updateById(bopsGoods);
		bopsGoodsDetailDAO.updateByPrimaryKey(bopsGoodsDetailbops);
		// 判断该商品是否为组合商品,如果是,修改组合商品价格
		// 组合商品修改重量
		List<BopsGoodsItemsPO> itemList = bopsGoodsItemsDAO.selectItemListPrice(bopsGoods.getGoodsId());
		if (itemList.size() > 0) {
			for (BopsGoodsItemsPO bopsGoodsItemsPO : itemList) {
				// 根据groupid查询goodscode
				BopsGoods goods = bopsGoodsDAO.selectByGoodsId(bopsGoodsItemsPO.getGoodsGroupId());
				SuitGoodsPriceVO suitGoodsPriceVO = querySuitGoodsPrice(goods.getGoodsId(), "weight");
				if (suitGoodsPriceVO != null) {
					double weight = suitGoodsPriceVO.getWeight();
					GoodsParamsVO paramVO = new GoodsParamsVO();
					paramVO.setWeight(String.valueOf(weight));
					String jsonString = JSONObject.toJSONString(paramVO);
					// 更新商品表
					BopsGoodsDetail goodsDetail = bopsGoodsDetailDAO
							.selectByPrimaryKey(bopsGoodsItemsPO.getGoodsGroupId());
					goodsDetail.setGoodsParams(jsonString);
					bopsGoodsDetailDAO.updateByPrimaryKey(goodsDetail);
				}

			}
		}
		updateGroupPrice(bopsGoods);
	}

	@Override
	public HSSFWorkbook exportGoods(GoodsPageVO goodsPageVO) {
		if (StringUtils.hasText(goodsPageVO.getGoodsStatus())) {
			goodsPageVO.setGoodsStatus(splitString(goodsPageVO.getGoodsStatus()));
		}
		if (StringUtils.hasText(goodsPageVO.getAuditStatus())) {
			goodsPageVO.setAuditStatus(splitString(goodsPageVO.getAuditStatus()));
		}
		if (StringUtils.hasText(goodsPageVO.getWarehouseType())) {
			goodsPageVO.setWarehouseType(splitString(goodsPageVO.getWarehouseType()));
		}
		if (!StringUtils.hasText(goodsPageVO.getAuditStatus()) && !StringUtils.hasText(goodsPageVO.getGoodsStatus())
				&& !StringUtils.hasText(goodsPageVO.getWarehouseType())) {
			goodsPageVO.setTotal(bopsGoodsDAO.countBopsGoods());
		} else {
			goodsPageVO.setTotal(bopsGoodsDAO.queryCountGoods(goodsPageVO));
		}

		List<GoodsStoreDetailResultVO> list = bopsGoodsDAO.queryPageOfBopsGoodsExport(goodsPageVO);
		/*
		 * if(null!=list && list.size()>0){ for(GoodsStoreDetailResultVO
		 * goodsStoreDetailResultVO :list){
		 * if(GoodsTypeEnums.MORE.code.equals(goodsStoreDetailResultVO.
		 * getGoodsType())){ SuitGoodsPriceVO suitGoodsPriceVO=
		 * bopsGoodsDAO.querySuitGoodsPrice(goodsStoreDetailResultVO.getGoodsId(
		 * )); if(suitGoodsPriceVO!=null){
		 * goodsStoreDetailResultVO.setDiscountPrice(suitGoodsPriceVO.
		 * getDiscountPrice());
		 * goodsStoreDetailResultVO.setOnsalePrice(suitGoodsPriceVO.
		 * getOnsalePrice());
		 * goodsStoreDetailResultVO.setPurchasePrice(suitGoodsPriceVO.
		 * getPurchasePrice()); } } } }
		 */ HSSFWorkbook workbook = getGoodsbook(list);
		return workbook;
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
	public int getGoodsMainStore(String goodsId) {
		// TODO Auto-generated method stub
		GoodsMainPO goodsMain = goodsMainDAO.selectByPrimaryKey(goodsId);// 查询前台商品信息
		int store = goodsMain.getStoreCount() == null ? 0 : goodsMain.getStoreCount();
		return store;
	}

	public HSSFWorkbook getGoodsbook(List<GoodsStoreDetailResultVO> data) {

		HSSFWorkbook workbook = new HSSFWorkbook();
		// SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		HSSFSheet sheet = ExcelUtil.createSheet(workbook, "商品数据");
		// 创建表头
		Font font = ExcelUtil.createFont(workbook, (short) 5, HSSFColor.BLACK.index, (short) 15);
		CellStyle cellStylec = ExcelUtil.createBorderCellStyle(workbook, HSSFColor.WHITE.index, HSSFColor.WHITE.index,
				(short) 30, font);
		HSSFRow row = ExcelUtil.createRow(sheet, 0, 300);
		row.getSheet().setColumnWidth(1, 8000);
		row.getSheet().setColumnWidth(6, 6000);
		HSSFCell cell = ExcelUtil.createCell(row, 0, cellStylec);
		cell.setCellValue("商品编码");
		cell = ExcelUtil.createCell(row, 1, cellStylec);
		cell.setCellValue("商品名称");
		cell = ExcelUtil.createCell(row, 2, cellStylec);
		cell.setCellValue("商品短名称");
		cell = ExcelUtil.createCell(row, 3, cellStylec);
		cell.setCellValue("商品原价");
		cell = ExcelUtil.createCell(row, 4, cellStylec);
		cell.setCellValue("商品折扣价");
		cell = ExcelUtil.createCell(row, 5, cellStylec);
		cell.setCellValue("采购价");
		cell = ExcelUtil.createCell(row, 6, cellStylec);
		cell.setCellValue("采购经理");
		cell = ExcelUtil.createCell(row, 7, cellStylec);
		cell.setCellValue("采购主管");
		cell = ExcelUtil.createCell(row, 8, cellStylec);
		cell.setCellValue("商品审核状态");
		cell = ExcelUtil.createCell(row, 9, cellStylec);
		cell.setCellValue("商品状态");
		cell = ExcelUtil.createCell(row, 10, cellStylec);
		cell.setCellValue("国际条形码");
		// cell = ExcelUtil.createCell(row, 11, cellStylec);
		// cell.setCellValue("商品分类");
		cell = ExcelUtil.createCell(row, 11, cellStylec);
		cell.setCellValue("仓库类型");
		cell = ExcelUtil.createCell(row, 12, cellStylec);
		cell.setCellValue("商品类型");
		cell = ExcelUtil.createCell(row, 13, cellStylec);
		cell.setCellValue("一级分类");
		cell = ExcelUtil.createCell(row, 14, cellStylec);
		cell.setCellValue("二级分类");
		cell = ExcelUtil.createCell(row, 15, cellStylec);
		cell.setCellValue("三级分类");

		List<BopsGoodsCategoriesPO> categoryList = bopsGoodsCategoriesDAO.queryAll();

		// 插入实体数据
		GoodsStoreDetailResultVO po = null;
		for (int i = 1; i < data.size() + 1; i++) {
			po = data.get(i - 1);
			row = ExcelUtil.createRow(sheet, i, 500);
			cell = ExcelUtil.createCell(row, 0, cellStylec);
			cell.setCellValue(Long.parseLong(po.getGoodsCode().trim()));
			cell = ExcelUtil.createCell(row, 1, cellStylec);
			cell.setCellValue(po.getGoodsName());
			cell = ExcelUtil.createCell(row, 2, cellStylec);
			cell.setCellValue(StringUtils.hasText(po.getShortName()) ? po.getShortName() : " ");// 短商品名称
			cell = ExcelUtil.createCell(row, 3, cellStylec);
			cell.setCellValue(po.getOnsalePrice());
			cell = ExcelUtil.createCell(row, 4, cellStylec);
			cell.setCellValue(po.getDiscountPrice());
			cell = ExcelUtil.createCell(row, 5, cellStylec);
			cell.setCellValue(po.getPurchasePrice() == null ? 0D : po.getPurchasePrice());
			cell = ExcelUtil.createCell(row, 6, cellStylec);

			if (null != po.getPurchaseManager()) {
				BopsUser purchaseManagerUser = bopsUserDAO
						.selectByPrimaryKey(Integer.parseInt(po.getPurchaseManager()));
				if (purchaseManagerUser != null) {
					cell.setCellValue(StringUtil.isBlank(purchaseManagerUser.getUserRealName()) ? ""
							: purchaseManagerUser.getUserRealName());// 采购经理
				} else {
					cell.setCellValue(" ");
				}
			} else {
				cell.setCellValue(" ");
			}
			cell = ExcelUtil.createCell(row, 7, cellStylec);
			if (null != po.getPurchaseLeader()) {
				BopsUser purchaseLeaderUser = bopsUserDAO.selectByPrimaryKey(Integer.parseInt(po.getPurchaseLeader()));
				if (purchaseLeaderUser != null) {
					cell.setCellValue(StringUtil.isBlank(purchaseLeaderUser.getUserRealName()) ? ""
							: purchaseLeaderUser.getUserRealName());// 采购主管
				} else {
					cell.setCellValue(" ");
				}
			} else {

			}
			cell = ExcelUtil.createCell(row, 8, cellStylec);
			cell.setCellValue(CheckStatusEnums.getDesc(po.getAuditStatus()));
			cell = ExcelUtil.createCell(row, 9, cellStylec);
			cell.setCellValue(GoodsStatusEnums.getDesc(po.getGoodsStatus()));
			cell = ExcelUtil.createCell(row, 10, cellStylec);
			cell.setCellValue(po.getGoodsBarcode());
			// cell = ExcelUtil.createCell(row, 11, cellStylec);
			// String goodsCategoryId = po.getGoodsCategoryId();
			// try{
			// String goodsCategoryName = "";
			// if(StringUtils.hasText(goodsCategoryId)){
			// goodsCategoryName =
			// bopsGoodsCategoriesDAO.selectByPrimaryKey(Integer.parseInt(goodsCategoryId)).getCategoryName();
			// }
			// cell.setCellValue(!StringUtils.hasText(goodsCategoryId) ? " " :
			// StringUtils.hasText(goodsCategoryName) ? goodsCategoryName : "
			// ");
			// } catch(NumberFormatException e) {}
			cell = ExcelUtil.createCell(row, 11, cellStylec);
			String warehouseType = po.getWarehouseType();
			warehouseType = WarehouseTypeEnum.getDesc(warehouseType);
			cell.setCellValue(warehouseType);
			cell = ExcelUtil.createCell(row, 12, cellStylec);
			if (!StringUtil.isBlank(po.getGoodsType())) {
				cell.setCellValue("MORE".equals(po.getGoodsType()) ? "套装" : "单品");
			} else {
				cell.setCellValue("单品");
			}

			List<String> categoryNameList = null;
			if (null != po.getGoodsCategories()) {
				categoryNameList = getCategoryNameList(categoryList, Integer.parseInt(po.getGoodsCategories()));
			}
			if (null != categoryNameList && categoryNameList.size() == 3) {
				cell = ExcelUtil.createCell(row, 13, cellStylec);
				cell.setCellValue(categoryNameList.get(2));
				cell = ExcelUtil.createCell(row, 14, cellStylec);
				cell.setCellValue(categoryNameList.get(1));
				cell = ExcelUtil.createCell(row, 15, cellStylec);
				cell.setCellValue(categoryNameList.get(0));
			} else {
				cell = ExcelUtil.createCell(row, 13, cellStylec);
				cell.setCellValue(" ");
				cell = ExcelUtil.createCell(row, 14, cellStylec);
				cell.setCellValue(" ");
				cell = ExcelUtil.createCell(row, 15, cellStylec);
				cell.setCellValue(" ");
			}

		}
		return workbook;
	}

	private List<String> getCategoryNameList(List<BopsGoodsCategoriesPO> categoryList, int categoryId) {
		List<String> categoryNameList = new ArrayList<String>();
		out: for (BopsGoodsCategoriesPO bopsGoodsCategoriesPOThree : categoryList) {
			if (categoryId == bopsGoodsCategoriesPOThree.getCategoryId()) {
				categoryNameList.add(bopsGoodsCategoriesPOThree.getCategoryName());
				for (BopsGoodsCategoriesPO bopsGoodsCategoriesPOTwo : categoryList) {
					if (bopsGoodsCategoriesPOThree.getParentId() == bopsGoodsCategoriesPOTwo.getCategoryId()) {
						categoryNameList.add(bopsGoodsCategoriesPOTwo.getCategoryName());
						for (BopsGoodsCategoriesPO bopsGoodsCategoriesPOOne : categoryList) {
							if (bopsGoodsCategoriesPOTwo.getParentId() == bopsGoodsCategoriesPOOne.getCategoryId()) {
								categoryNameList.add(bopsGoodsCategoriesPOOne.getCategoryName());
								break out;
							}
						}
					}
				}
			}
		}

		return categoryNameList;
	}

	/**
	 * 
	 * @author LXD 2015年10月26日 下午4:02:46 @Method: getBygoodsIds @Description:
	 *         TODO 根据商品ID数组，返回商品list @param goodsIds @return @throws
	 */
	@Override
	public List<GoodsStoreDetailResultVO> getBygoodsIds(String[] goodsCodes) {
		String[] newGoodsCodes = array_unique(goodsCodes);
		List<GoodsStoreDetailResultVO> list = new ArrayList<GoodsStoreDetailResultVO>();
		if (newGoodsCodes != null) {
			for (int i = 0; i < newGoodsCodes.length; i++) {
				logger.info("newGoodsCodes[i] : " + newGoodsCodes[i]);
				GoodsStoreDetailResultVO result = bopsGoodsDAO.getByParames(newGoodsCodes[i]);
				if (result != null) {
					result.setGoodsTopPics(ImageUtils.imageSplit(result.getTopPicKeys()));
					// 金额装换
					logger.info("begin CurrencyUtil to deal result :" + result);
					String discountPrice = subZeroAndDot(String.valueOf(result.getDiscountPrice()));
					String onsalePrice = subZeroAndDot(String.valueOf(result.getOnsalePrice()));
					result.setDiscountPrice(Double.parseDouble(CurrencyUtil.fromFenToYuan(discountPrice)));
					result.setOnsalePrice(Double.parseDouble(CurrencyUtil.fromFenToYuan(onsalePrice)));
					logger.info("end CurrencyUtil to deal result :" + result);
					list.add(result);
				}
			}
		}
		return list;
	}

	// 去除数组中重复的记录
	public static String[] array_unique(String[] a) {
		// array_unique
		List<String> list = new LinkedList<String>();
		for (int i = 0; i < a.length; i++) {
			if (!list.contains(a[i])) {
				list.add(a[i]);
			}
		}
		return (String[]) list.toArray(new String[list.size()]);
	}

	public static String subZeroAndDot(String s) {
		if (s.indexOf(".") > 0) {
			s = s.replaceAll("0+?$", "");// 去掉多余的0
			s = s.replaceAll("[.]$", "");// 如最后一位是.则去掉
		}
		return s;
	}

	public static void main(String[] args) {
		double a = 1.0;
		System.out.println(String.valueOf(a));
		System.out.println(BopsGoodsServiceImpl.subZeroAndDot(String.valueOf(a)));

		Double b = 12.3;
		System.out.println(b.intValue());

	}

	@Override
	public SuitGoodsPriceVO querySuitGoodsPrice(String goodsGroupId, String type) {

		SuitGoodsPriceVO suitGoodsPriceVO = bopsGoodsDAO.querySuitGoodsPrice(goodsGroupId);
		if ((!StringUtil.isEmptyOrNull(type)) && "weight".equals(type)) {
			List<GroupGoodsDetailVo> goodsParams = bopsGoodsDetailDAO.queryListByGroupGoodsId(goodsGroupId);
			double weight = 0.0;
			if (null != goodsParams && goodsParams.size() > 0) {
				for (GroupGoodsDetailVo groupGoodsDetailVo : goodsParams) {
					JSONObject jsonObject = JSONObject.parseObject(groupGoodsDetailVo.getParam());// 把JSON字符串转换成对象属性
					if (!jsonObject.getString("weight").contains("g")) {
						weight += groupGoodsDetailVo.getCount() * (Double.parseDouble(jsonObject.getString("weight")));
					}

				}

			}
			suitGoodsPriceVO.setWeight(weight);
		}

		return suitGoodsPriceVO;
	}

	@Override
	public void editOpsGoods(OpsGoodsParamVO bopsGoodsVO) {
		// 对商品进行编辑，组装VO
		BopsGoods bopsGoods = bopsGoodsDAO.selectByPrimaryKey(bopsGoodsVO.getGoodsId());
		BopsGoodsDetail bopsGoodsDetailbops = bopsGoodsDetailDAO.selectByPrimaryKey(bopsGoodsVO.getGoodsId());
		if (bopsGoodsDetailbops == null) {
			bopsGoodsDetailbops = new BopsGoodsDetail();
		}
		BeanUtils.copyProperties(bopsGoodsVO, bopsGoods);
		// bopsGoods.setOnsalePrice((int)(bopsGoodsVO.getOnsalePrice() *
		// Constants.PRICE_TO_FEN_OR_YUAN));//数据库中保存商品的价格单位是分
		bopsGoods.setOnsalePrice(
				Integer.parseInt(CurrencyUtil.fromYuanToFen(String.valueOf(bopsGoodsVO.getOnsalePrice()))));
		// bopsGoods.setDiscountPrice((int)(bopsGoodsVO.getDiscountPrice() *
		// Constants.PRICE_TO_FEN_OR_YUAN));
		bopsGoods.setDiscountPrice(
				Integer.parseInt(CurrencyUtil.fromYuanToFen(String.valueOf(bopsGoodsVO.getDiscountPrice()))));
		bopsGoods.setPurchasePrice(Integer.parseInt(CurrencyUtil.fromYuanToFen(
				String.valueOf(bopsGoodsVO.getPurchasePrice() != null ? bopsGoodsVO.getPurchasePrice() : 0D))));
		BeanUtils.copyProperties(bopsGoodsVO, bopsGoodsDetailbops);
		if (bopsGoodsVO.getTopPicKeys() != null) {
			String topPickeys = StringUtil.arrayToFormatString(bopsGoodsVO.getTopPicKeys(), ",");// 把页面传过来的字符串数组转化成一个以逗号分隔的字符串
			bopsGoods.setTopPicKeys(topPickeys);
		}
		if (bopsGoodsVO.getDetailPicKeys() != null) {
			String detailPicKeys = StringUtil.arrayToFormatString(bopsGoodsVO.getDetailPicKeys(), ",");// 把页面传过来的字符串数组转化成一个以逗号分隔的字符串
			bopsGoodsDetailbops.setDetailPicKeys(detailPicKeys);
		}
		GoodsParamsVO goodsParamsVO = new GoodsParamsVO();// 商品属性的在数据库中是以一个JSON字符串保存，把商品的各种属性封装成VO，在转换成JSON
		BeanUtils.copyProperties(bopsGoodsVO, goodsParamsVO);
		String goodsParamsJSONString = JSONObject.toJSONString(goodsParamsVO);
		bopsGoods.setAuditStatus(CheckStatusEnums.WAIT.code);
		bopsGoodsDetailbops.setGoodsParams(goodsParamsJSONString);
		/**
		 * 去掉首尾空格
		 */
		bopsGoods.setBrief(!StringUtils.hasText(bopsGoods.getBrief()) ? "" : bopsGoods.getBrief().trim());
		bopsGoods.setGoodsBarcode(
				!StringUtils.hasText(bopsGoods.getGoodsBarcode()) ? "" : bopsGoods.getGoodsBarcode().trim());
		bopsGoodsDetailbops.setGoodsDesc(!StringUtils.hasText(bopsGoodsDetailbops.getGoodsDesc()) ? ""
				: bopsGoodsDetailbops.getGoodsDesc().trim());
		bopsGoodsDAO.updateById(bopsGoods);
		bopsGoodsDetailDAO.updateByPrimaryKey(bopsGoodsDetailbops);

	}

	@Override
	public OpsGoodsParamVO viewOpsGoods(String goodsId) {
		BopsGoods bopsGoods = bopsGoodsDAO.selectByPrimaryKey(goodsId);// 从商品主表中查出商品信息
		BopsGoodsDetail goodsdetail = bopsGoodsDetailDAO.selectByPrimaryKey(goodsId);// 从商品的扩展表中获取商品的信息
		OpsGoodsParamVO goodsStoreDetailVO = new OpsGoodsParamVO();

		BeanUtils.copyProperties(bopsGoods, goodsStoreDetailVO);
		// goodsStoreDetailVO.setOnsalePrice((double)bopsGoods.getOnsalePrice()
		// / Constants.PRICE_TO_FEN_OR_YUAN); //传给页面的单位是元
		goodsStoreDetailVO.setOnsalePrice(
				Double.parseDouble(CurrencyUtil.fromFenToYuan(String.valueOf(bopsGoods.getOnsalePrice()))));
		// goodsStoreDetailVO.setDiscountPrice((double)bopsGoods.getDiscountPrice()
		// / Constants.PRICE_TO_FEN_OR_YUAN);
		goodsStoreDetailVO.setDiscountPrice(
				Double.parseDouble(CurrencyUtil.fromFenToYuan(String.valueOf(bopsGoods.getDiscountPrice()))));
		if (null != bopsGoods.getPurchasePrice()) {
			goodsStoreDetailVO.setPurchasePrice(
					Double.parseDouble(CurrencyUtil.fromFenToYuan(String.valueOf(bopsGoods.getPurchasePrice()))));
		}
		goodsStoreDetailVO.setTopPicKeysString(bopsGoods.getTopPicKeys());
		if (bopsGoods != null) {
			if (bopsGoods.getTopPicKeys() != null) {
				String[] goodsPics = bopsGoods.getTopPicKeys().split(",");
				goodsStoreDetailVO.setGoodsTopPics(goodsPics);// 把商品图片以数组形式给前端
			}
		}
		if (goodsdetail != null) {
			if (goodsdetail.getDetailPicKeys() != null) {
				String[] detailPics = goodsdetail.getDetailPicKeys().split(",");
				goodsStoreDetailVO.setDetailPicKeys(detailPics);
				goodsStoreDetailVO.setDetailPicKeysString(goodsdetail.getDetailPicKeys());
			}
			goodsStoreDetailVO.setGoodsDesc(goodsdetail.getGoodsDesc());
			JSONObject jsonObject = JSONObject.parseObject(goodsdetail.getGoodsParams());// 把JSON字符串转换成对象属性
			goodsStoreDetailVO.setColor(jsonObject.getString("color"));
			goodsStoreDetailVO.setOriginPlace(jsonObject.getString("originPlace"));
			goodsStoreDetailVO.setSize(jsonObject.getString("size"));
			goodsStoreDetailVO.setWeight(jsonObject.getString("weight"));
		}

		return goodsStoreDetailVO;
	}

	@Override
	public GoodsStoreDetailVO getGoodsDetail(String goodsCode, String warehouseType) {
		BopsGoods goods = new BopsGoods();
		goods.setGoodsCode(goodsCode);
		goods.setWarehouseType(warehouseType);
		GoodsStoreDetailVO goodsStoreDetailVO = new GoodsStoreDetailVO();
		BopsGoodsVO bopsGoods = bopsGoodsDAO.selectGoodsDetailByCode(goods);// 从商品主表中查出商品信息
		if (null != bopsGoods) {
			BopsGoodsDetail goodsdetail = bopsGoodsDetailDAO.selectByPrimaryKey(bopsGoods.getGoodsId());// 从商品的扩展表中获取商品的信息
			BeanUtils.copyProperties(bopsGoods, goodsStoreDetailVO);
			goodsStoreDetailVO.setOnsalePrice(
					Double.parseDouble(CurrencyUtil.fromFenToYuan(String.valueOf(bopsGoods.getOnsalePrice()))));
			goodsStoreDetailVO.setDiscountPrice(
					Double.parseDouble(CurrencyUtil.fromFenToYuan(String.valueOf(bopsGoods.getDiscountPrice()))));
			if (null != bopsGoods.getPurchasePrice()) {
				goodsStoreDetailVO.setPurchasePrice(
						Double.parseDouble(CurrencyUtil.fromFenToYuan(String.valueOf(bopsGoods.getPurchasePrice()))));
			}
			goodsStoreDetailVO.setTopPicKeysString(bopsGoods.getTopPicKeys());
			if (bopsGoods != null) {
				if (bopsGoods.getTopPicKeys() != null) {
					String[] goodsPics = bopsGoods.getTopPicKeys().split(",");
					goodsStoreDetailVO.setGoodsTopPics(goodsPics);// 把商品图片以数组形式给前端
				}
			}
			if (goodsdetail != null) {
				if (goodsdetail.getDetailPicKeys() != null) {
					String[] detailPics = goodsdetail.getDetailPicKeys().split(",");
					goodsStoreDetailVO.setDetailPicKeys(detailPics);
					goodsStoreDetailVO.setDetailPicKeysString(goodsdetail.getDetailPicKeys());
				}
				goodsStoreDetailVO.setGoodsDesc(goodsdetail.getGoodsDesc());
				JSONObject jsonObject = JSONObject.parseObject(goodsdetail.getGoodsParams());// 把JSON字符串转换成对象属性
				goodsStoreDetailVO.setColor(jsonObject.getString("color"));
				goodsStoreDetailVO.setOriginPlace(jsonObject.getString("originPlace"));
				goodsStoreDetailVO.setSize(jsonObject.getString("size"));
				goodsStoreDetailVO.setWeight(jsonObject.getString("weight"));
				goodsStoreDetailVO.setGoodsStatus(GoodsStatusEnums.getDesc(goodsStoreDetailVO.getGoodsStatus()));
				// 根据商品id查询库存
				GoodsMainPO goodsMainPO = goodsMainDAO.selectByPrimaryKey(bopsGoods.getGoodsId());
				goodsStoreDetailVO.setNowStoreCount(goodsMainPO.getStoreCount());
			}
		}
		return goodsStoreDetailVO;
	}

	@Override
	@Transactional("bops_txManager")
	public BopsGoodsItemsPO insertGoodsItems(List<BopsGoodsItemsVO> list, BopsGoods bopsGoods, GoodsParamsVO paramsVO) {
		bopsGoods.setGoodsStatus("OFFLINE");
		bopsGoods.setSceneId(0);
		bopsGoods.setAuditStatus("WAIT_AUDIT");
		bopsGoods.setIsGlobal("FALSE");
		bopsGoods.setGoodsType("MORE");
		// 查询goodsCode
		Integer goodsCode = bopsGoodsDAO.selectGoodsCode();
		bopsGoods.setGoodsCode(goodsCode.toString());
		bopsGoods.setGoodsBarcode(goodsCode.toString());
		bopsGoodsDAO.insertGroupGoods(bopsGoods);
		BopsGoodsDetail bopsGoodsDetail = new BopsGoodsDetail();
		BeanUtils.copyProperties(bopsGoods, bopsGoodsDetail);
		String goodsParamsJSONString = JSONObject.toJSONString(paramsVO);// 把商品的参数信息转成JSON
		bopsGoodsDetail.setGoodsDesc("");
		bopsGoodsDetail.setGoodsParams(goodsParamsJSONString);
		bopsGoodsDetailDAO.insertNewGoods(bopsGoodsDetail);// 插入商品扩展表
		for (BopsGoodsItemsVO bopsGoodsItemsVO : list) {
			bopsGoodsItemsVO.setGoodsGroupId(bopsGoods.getGoodsId());
			bopsGoodsItemsDAO.insertReGoodsItem(bopsGoodsItemsVO);
		}
		return null;
	}

	private GoodsParamsVO getGoodsParams() {
		GoodsParamsVO goodsParamsVO = new GoodsParamsVO();
		goodsParamsVO.setColor("");
		goodsParamsVO.setOriginPlace("");
		goodsParamsVO.setSize("");
		goodsParamsVO.setWeight("");
		return goodsParamsVO;
	}

	/**
	 * 
	 * @author liuhongyang 2015年12月5日 上午11:43:43
	 * @Method: selectGoodsItemDetail
	 * @Description: 组合商品详情查询
	 * @param goodsId
	 * @return
	 * @see com.need.service.bops.goods.BopsGoodsService#selectGoodsItemDetail(java.lang.String)
	 */
	@Override
	public List<BopsGoodsItemsVO> selectGoodsItemDetail(String goodsGroupId) {
		List<BopsGoodsItemsVO> itemList = bopsGoodsItemsDAO.selectItemList(goodsGroupId);
		for (BopsGoodsItemsVO bopsGoodsItemsVO : itemList) {
			// 查询商品上下架状态,库存,need价
			BopsGoods bopsGoods = bopsGoodsDAO.selectByGoodsCode(bopsGoodsItemsVO.getGoodsCode());
			double discountPrice = bopsGoods.getDiscountPrice().intValue() / 100.0;
			bopsGoodsItemsVO.setDiscountPrice(discountPrice);
			bopsGoodsItemsVO.setGoodsStatus(GoodsStatusEnums.getDesc(bopsGoods.getGoodsStatus()));
			bopsGoodsItemsVO.setGoodsName(bopsGoods.getGoodsName());
			bopsGoodsItemsVO.setOnsalePrice(bopsGoods.getOnsalePrice().doubleValue() / 100.0);
			bopsGoodsItemsVO.setPurchasePrice(bopsGoods.getPurchasePrice().doubleValue() / 100.0);
			// 根据商品id查询库存
			GoodsMainPO goodsMainPO = goodsMainDAO.selectByPrimaryKey(bopsGoods.getGoodsId());
			// 查询重量
			BopsGoodsDetail goodsdetail = bopsGoodsDetailDAO.selectByPrimaryKey(bopsGoods.getGoodsId());
			if (goodsdetail != null) {
				JSONObject jsonObject = JSONObject.parseObject(goodsdetail.getGoodsParams());// 把JSON字符串转换成对象属性
				bopsGoodsItemsVO.setWeight(jsonObject.getString("weight"));
			}

			bopsGoodsItemsVO.setStoreCount(goodsMainPO.getStoreCount());
		}
		return itemList;
	}

	@Override
	@Transactional("bops_txManager")
	public BopsGoodsItemsPO updateGoodsItems(List<BopsGoodsItemsVO> list, BopsGoods bopsGoods, BopsGoods preGoods,
			GoodsParamsVO paramsVO) {
		// 更新商品信息
		bopsGoods.setGoodsId(preGoods.getGoodsId());
		bopsGoods.setAuditStatus("WAIT_AUDIT");
		bopsGoodsDAO.updateGroupGoods(bopsGoods);
		BopsGoodsDetail bopsGoodsDetail = new BopsGoodsDetail();
		bopsGoodsDetail.setGoodsId(preGoods.getGoodsId());
		String goodsParamsJSONString = JSONObject.toJSONString(paramsVO);
		bopsGoodsDetail.setGoodsParams(goodsParamsJSONString);
		bopsGoodsDetailDAO.updateWeight(bopsGoodsDetail);
		// 删除items信息
		bopsGoodsItemsDAO.deleteByGroupId(preGoods.getGoodsId());
		for (BopsGoodsItemsVO bopsGoodsItemsVO : list) {
			bopsGoodsItemsVO.setGoodsGroupId(bopsGoods.getGoodsId());
			bopsGoodsItemsDAO.insertReGoodsItem(bopsGoodsItemsVO);
		}
		return null;
	}

	/**
	 * 
	 * @author liuhongyang 2015年12月7日 下午4:15:23
	 * @Method: updateGoodsStatus
	 * @Description: 商品上下架
	 * @param bopsGoods
	 * @see com.need.service.bops.goods.BopsGoodsService#updateGoodsStatus(com.need.domain.po.bops.goods.BopsGoods)
	 */
	@Override
	@Transactional
	public void updateGoodsStatus(BopsGoods bopsGoods) {
		bopsGoodsDAO.updateGoodsStatus(bopsGoods);
		// GoodsMainPO goodsMainPO=new GoodsMainPO();
		GoodsMainPO goodsMainPO = goodsMainDAO.selectByPrimaryKey(bopsGoods.getGoodsId());
		BeanUtils.copyProperties(bopsGoods, goodsMainPO);
		if (GoodsStatusEnums.ONLINE.code.equals(goodsMainPO.getGoodsStatus())) {
			if (goodsMainPO.getGoodsOnlineTime() == null) {
				goodsMainPO.setGoodsOnlineTime(Calendar.getInstance().getTime());
			}

		}
		goodsMainDAO.updateGoodsStatus(goodsMainPO);
		// 组合商品包含此商品,组合商品下架
		List<BopsGoodsItemsPO> itemList = bopsGoodsItemsDAO.selectItemListPrice(bopsGoods.getGoodsId());
		if (null != itemList) {
			for (BopsGoodsItemsPO bopsGoodsItemsPO : itemList) {
				// 根据groupid查询后台表
				GoodsMainPO goods = new GoodsMainPO();
				goods.setGoodsStatus("OFFLINE");
				goods.setGoodsId(bopsGoodsItemsPO.getGoodsGroupId());
				// 修改组合商品状态
				goodsMainDAO.updateGoodsStatus(goods);
				BopsGoods goodsPO = new BopsGoods();
				BeanUtils.copyProperties(goods, goodsPO);
				bopsGoodsDAO.updateGoodsStatus(goodsPO);
			}
		}
	}

	/**
	 * 
	 * @author liuhongyang 2015年12月8日 下午6:25:50
	 * @Method: updateGroupPrice
	 * @Description: 修改组合商品价格,需要传goodsId,
	 * @param goodsId
	 * @see com.need.service.bops.goods.BopsGoodsService#updateGroupPrice(java.lang.String)
	 */
	@Override
	@Transactional("bops_txManager")
	public void updateGroupPrice(BopsGoods bopsGoods) {
		// 组合商品中是否有该商品
		List<BopsGoodsItemsPO> itemList = bopsGoodsItemsDAO.selectItemListPrice(bopsGoods.getGoodsId());
		if (itemList.size() > 0) {
			for (BopsGoodsItemsPO bopsGoodsItemsPO : itemList) {
				// 根据groupid查询goodscode
				BopsGoods goods = bopsGoodsDAO.selectByGoodsId(bopsGoodsItemsPO.getGoodsGroupId());
				if (null != goods) {
					SuitGoodsPriceVO suitGoodsPriceVO = querySuitGoodsPrice(goods.getGoodsId(), "weight");
					if (suitGoodsPriceVO != null) {
						String onsalePrice = Double.toString(suitGoodsPriceVO.getOnsalePrice());
						String discountPrice = Double.toString(suitGoodsPriceVO.getDiscountPrice());
						String purchasePrice = Double.toString(suitGoodsPriceVO.getPurchasePrice());
						goods.setOnsalePrice(Integer.parseInt(CurrencyUtil.fromYuanToFen(onsalePrice)));
						goods.setDiscountPrice(Integer.parseInt(CurrencyUtil.fromYuanToFen(discountPrice)));
						goods.setPurchasePrice(Integer.parseInt(CurrencyUtil.fromYuanToFen(purchasePrice)));
						// 更新商品表
						bopsGoodsDAO.updateById(goods);
					}
				}

			}
		}
	}

	/**
	 * 
	 * @author liuhongyang 2015年12月8日 下午8:08:22 @Method:
	 *         auditGroupPrice @Description: 审核通过,修改组合商品价格 @throws
	 */

	@Override
	@Transactional("api_txManager")
	public void auditGroupPrice(BopsGoods bopsGoods) {
		// 组合商品中是否有该商品
		List<BopsGoodsItemsPO> itemList = bopsGoodsItemsDAO.selectItemListPrice(bopsGoods.getGoodsId());
		if (itemList.size() > 0) {
			for (BopsGoodsItemsPO bopsGoodsItemsPO : itemList) {
				// 根据groupid查询后台表
				BopsGoods goods = bopsGoodsDAO.selectByGoodsId(bopsGoodsItemsPO.getGoodsGroupId());
				// 查询商品信息,修改价格
				GoodsMainPO portalGoods = goodsMainDAO.selectByPrimaryKey(goods.getGoodsId());
				if (null != portalGoods) {
					GoodsMainPO goodsMain = new GoodsMainPO();
					goodsMain.setOnsalePrice(goods.getOnsalePrice());
					goodsMain.setDiscountPrice(goods.getDiscountPrice());
					goodsMain.setGoodsId(goods.getGoodsId());
					goodsMainDAO.updateGroupPrice(goodsMain);
				}
			}
		}
	}

	@Override
	public void updateGroupGoodsPrice(String goodsId) {
		// TODO Auto-generated method stub
		logger.info("update groupGoodsPrice: goodsId: " + goodsId);
		List<BopsGoodsItemsPO> itemList = bopsGoodsItemsDAO.selectItemListPrice(goodsId);
		if (itemList.size() > 0) {
			for (BopsGoodsItemsPO bopsGoodsItemsPO : itemList) {
				// 根据groupid查询goodscode
				BopsGoods goods = bopsGoodsDAO.selectByGoodsId(bopsGoodsItemsPO.getGoodsGroupId());
				if (null != goods) {
					SuitGoodsPriceVO suitGoodsPriceVO = querySuitGoodsPrice(goods.getGoodsId(), "weight");
					if (suitGoodsPriceVO != null) {
						String onsalePrice = Double.toString(suitGoodsPriceVO.getOnsalePrice());
						String discountPrice = Double.toString(suitGoodsPriceVO.getDiscountPrice());
						String purchasePrice = Double.toString(suitGoodsPriceVO.getPurchasePrice());
						goods.setOnsalePrice(Integer.parseInt(CurrencyUtil.fromYuanToFen(onsalePrice)));
						goods.setDiscountPrice(Integer.parseInt(CurrencyUtil.fromYuanToFen(discountPrice)));
						goods.setPurchasePrice(Integer.parseInt(CurrencyUtil.fromYuanToFen(purchasePrice)));
						// 更新商品表
						bopsGoodsDAO.updateById(goods);
					}
				}

			}
		}

		if (itemList.size() > 0) {
			for (BopsGoodsItemsPO bopsGoodsItemsPO : itemList) {
				// 根据groupid查询后台表
				BopsGoods goods = bopsGoodsDAO.selectByGoodsId(bopsGoodsItemsPO.getGoodsGroupId());
				GoodsMainPO portalGoods = goodsMainDAO.selectByPrimaryKey(goods.getGoodsId());
				// 查询商品信息,修改价格
				if (portalGoods != null) {
					GoodsMainPO goodsMain = new GoodsMainPO();
					goodsMain.setOnsalePrice(goods.getOnsalePrice());
					goodsMain.setDiscountPrice(goods.getDiscountPrice());
					goodsMain.setGoodsId(goods.getGoodsId());
					goodsMainDAO.updateGroupPrice(goodsMain);
				}
			}
		}

	}

	/**
	 * 
	 * @author peiboli 2015年12月11日 下午4:52:20
	 * @Method: editGoodsCategory
	 * @Description: TODO批量倒数商品分类
	 * @param categoryId
	 * @param goodsCode
	 * @return
	 * @see com.need.service.bops.goods.BopsGoodsService#editGoodsCategory(java.lang.String,
	 *      java.lang.String[])
	 */

	@Override
	@Transactional
	public Message editGoodsCategory(String goodsIndexCategory, String[] goodsCode) {
		// TODO Auto-generated method stub
		Message message= Message.success();
		StringBuilder badGoodsCode = new StringBuilder();
		for (String code : goodsCode) {
			int isSuccess = bopsGoodsDAO.updateCategory(goodsIndexCategory, code.trim());
			if (isSuccess == 1) {
				goodsMainDAO.updateCategory(goodsIndexCategory, code.trim());
			} else {
				badGoodsCode.append(code.trim() + ",");
			}
		}
		message.addData("badGoodsCode", badGoodsCode);
		return message;
	}

}
