package com.need.erp.web.controller.goods;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.need.dao.bops.goods.BopsGoodsDAO;
import com.need.dao.bops.user.BopsUserDAO;
import com.need.domain.common.enums.GoodsTypeEnums;
import com.need.domain.po.bops.goods.BopsGoods;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.vo.goods.GoodsPageVO;
import com.need.domain.vo.goods.GoodsPriceChangeParamVO;
import com.need.domain.vo.goods.GoodsPriceChangeResultVO;
import com.need.domain.vo.goods.GoodsPriceChangeVO;
import com.need.domain.vo.goods.GoodsPriceDetailParamVO;
import com.need.domain.vo.goods.GoodsPriceDetailResultVO;
import com.need.domain.vo.goods.GoodsPriceResultVO;
import com.need.domain.vo.goods.GoodsStoreDetailResultVO;
import com.need.erp.constant.ControllerMappings;
import com.need.erp.constant.ViewMappings;
import com.need.service.bops.goods.BopsGoodsService;
import com.need.service.bops.goods.BopsPricechangeService;
import com.need.service.bops.wms.ErpSkuToWmsService;
import com.need.service.constant.Constants;

import scala.math.BigDecimal;

/**
 * 
 * @author xiehao
 * 2015年11月22日下午6:20:21
 * @description 商品价格修改
 */
@Controller
@RequestMapping(ControllerMappings.BOPS_GOODS_NEW)
public class BopsGoodsNewController {

	private static final Logger logger = Logger.getLogger(BopsGoodsNewController.class);

	@Autowired
	private BopsGoodsService bopsGoodsService;
	@Autowired
	private BopsGoodsDAO bopsGoodsDAO;
	// @Autowired
	// private ConstantsProConfig constantsProConfig;
	@Autowired
	private BopsUserDAO bopsUserDAO;
	@Autowired
	private BopsPricechangeService bopsPricechangeService;
	@Autowired
	private ErpSkuToWmsService erpSkuToWmsService;
	

	
	/**
	 * 
	 * @author xiehao
	 * 2015年11月22日下午6:21:34
	 * @description 进入上传商品价格EXCEL页面
	 */
	@RequestMapping(method = RequestMethod.GET, value = "toChangePricePage")
	public String toBatchChangePrice(HttpServletRequest request) {
		logger.info("in goods BopsGoodsNewController toBatchChangePrice: ");

		logger.info("out goods BopsGoodsNewController toBatchChangePrice: ");

		return ViewMappings.GOODS_CHANGE_PRICE;
	}

	/**
	 * 
	 * @author xiehao
	 * 2015年11月22日下午6:22:46
	 * @description 解析从浏览器传过来的EXCEL文件，并把EXCLE的内容返回页面
	 */
	@RequestMapping(method = RequestMethod.POST, value = "changeGoodsPrice")
	public String batchChangePrice(@RequestParam("files") MultipartFile[] files, String pricechangeType,
			String startTime, String endTime, String mark, Model model, HttpServletRequest request) throws Exception {
		List<GoodsPriceChangeVO> changeGoodsPriceList = new ArrayList<GoodsPriceChangeVO>();
		List<GoodsPriceChangeVO> badChangeGoodsPriceList = new ArrayList<GoodsPriceChangeVO>();
		for (MultipartFile file : files) {
			InputStream in = file.getInputStream();
			Boolean is07Or10 = file.getOriginalFilename().endsWith("xlsx");
			changeGoodsPriceList = bopsGoodsService.excelToList(in, is07Or10, pricechangeType);
			int badSize = 0;
			for (GoodsPriceChangeVO goodsPriceChangeVO : changeGoodsPriceList) {
				if (hasBlankofGoodsPrice(goodsPriceChangeVO, pricechangeType)) {
					goodsPriceChangeVO.setMessage("列表中有空白字段");
					badSize++;
					badChangeGoodsPriceList.add(goodsPriceChangeVO);
					continue;
				}
				BopsGoods bopsGoods = bopsGoodsDAO.getByGoodsCode(goodsPriceChangeVO.getGoodsCode());
				if (null == bopsGoods) {
					goodsPriceChangeVO.setMessage("该商品不存在");
					badSize++;
					badChangeGoodsPriceList.add(goodsPriceChangeVO);
					continue;
				}
				if (GoodsTypeEnums.MORE.code.equals(bopsGoods.getGoodsType())) {
					goodsPriceChangeVO.setMessage("套装商品不允许批量改价");
					badSize++;
					badChangeGoodsPriceList.add(goodsPriceChangeVO);
					continue;
				}
//				if (bopsGoods.getOnsalePrice() / 100 < goodsPriceChangeVO.getDiscountPrice()) {
//					goodsPriceChangeVO.setMessage("商品的折扣价不能大于销售价");
//					badSize++;
//					badChangeGoodsPriceList.add(goodsPriceChangeVO);
//					continue;
//				}
//				if(goodsPriceChangeVO.getDiscountPrice() < bopsGoods.getPurchasePrice() / 100){
//					goodsPriceChangeVO.setMessage("商品的折扣价不能小采购价");
//					badSize++;
//					badChangeGoodsPriceList.add(goodsPriceChangeVO);
//					continue;
//				}
				if (goodsPriceChangeVO.getDiscountPrice() <= 0) {
					goodsPriceChangeVO.setMessage("商品的折扣价不能小于等于0");
					badSize++;
					badChangeGoodsPriceList.add(goodsPriceChangeVO);
					continue;
				}
				DecimalFormat df = new DecimalFormat("#.00");
				goodsPriceChangeVO.setGoodsId(bopsGoods.getGoodsId());
				goodsPriceChangeVO.setGoodsName(bopsGoods.getGoodsName());
				goodsPriceChangeVO.setPurchasePrice(((double) bopsGoods.getPurchasePrice()) / 100);
				goodsPriceChangeVO.setHistoryPurchasePrice(bopsGoods.getPurchasePrice());
				goodsPriceChangeVO.setProfit(Double.valueOf(df.format(
						goodsPriceChangeVO.getDiscountPrice() - ((double) bopsGoods.getPurchasePrice()) / 100)));
			}
			model.addAttribute("badList", badChangeGoodsPriceList);
			model.addAttribute("list", changeGoodsPriceList);
			if (badSize > 0) {
				return ViewMappings.GOODS_PRICE_BAD_LIST;
			} else {
				BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);// 得到当前登录的用户信息
				bopsPricechangeService.savePricechange(changeGoodsPriceList, user != null ? user.getUserId() : 1,
						pricechangeType, startTime, endTime, mark);
				return ViewMappings.GOODS_PRICE_LIST;
			}
		}

		return ViewMappings.GOODS_PRICE_LIST;
	}

	/**
	 * 
	 * @author xiehao
	 * 2015年11月22日下午6:23:12
	 * @description 校验上传的EXCEL中是否有空字段
	 * 时间段启用时，恢复价格非空
	 */
	private boolean hasBlankofGoodsPrice(GoodsPriceChangeVO goodsPriceChangeVO, String priceType) {
		if (!StringUtils.hasText(goodsPriceChangeVO.getGoodsCode())) {
			return true;
		}
		if (null == goodsPriceChangeVO.getDiscountPrice()) {
			return true;
		}
		if ("SCHEDULE".equals(priceType) && null == goodsPriceChangeVO.getOriginalPrice()) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @author xiehao
	 * 2015年11月22日下午6:27:41
	 * @description 申请商品价格修改审批列表
	 */
	@RequestMapping(method = RequestMethod.GET, value = "page")
	public String query(HttpServletRequest request, Model model, GoodsPriceChangeParamVO paramVO) {
		logger.info("in goods BopsGoodsNewController toBatchChangePrice: ");
		List<BopsUser> userList = bopsUserDAO.getAllUser();
		if (userList.size() > 0) {
			model.addAttribute("userList", userList);
		}
		List<GoodsPriceChangeResultVO> goodsPriceList = bopsPricechangeService.queryPriceChange(paramVO);

		model.addAttribute("page", paramVO);
		model.addAttribute("list", goodsPriceList);

		logger.info("out goods BopsGoodsNewController toBatchChangePrice: ");
		return ViewMappings.GOODS_CHANGE_PRICE_LIST;
	}

	/**
	 * @author xiehao 2015年11月21日 下午7:13:11
	 * @Method: audit
	 * @Description: TODO 审核
	 * @param request
	 * @param pricechangeId
	 * @param pricechangeStatus
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "auditGoodsPrice")
	public Message audit(HttpServletRequest request, Integer pricechangeId, String pricechangeStatus) {
		logger.info("in goods BopsGoodsNewController toBatchChangePrice: ");
		Message message = Message.success();
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);// 得到当前登录的用户信息
		bopsPricechangeService.updateAudit(pricechangeStatus, pricechangeId, user != null ? user.getUserId() : 1);
		logger.info("out goods BopsGoodsNewController toBatchChangePrice: ");
		return message;
	}

	/**
	 * @author xiehao 2015年11月21日 下午7:13:22
	 * @Method: execute
	 * @Description: TODO 启用，废弃，恢复原价
	 * @param request
	 * @param pricechangeId
	 * @param excuted
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "execute")
	public Message execute(HttpServletRequest request, Integer pricechangeId, String excuted) {
		logger.info("in goods BopsGoodsNewController toBatchChangePrice: ");
		Message message = Message.success();
		bopsPricechangeService.updateExecute(excuted, pricechangeId);

		logger.info("out goods BopsGoodsNewController toBatchChangePrice: ");
		return message;
	}

	/**
	 * 
	 * @author xiehao
	 * 2015年11月22日下午6:29:38
	 * @description 本次审批的商品列表
	 */
	@RequestMapping(method = RequestMethod.GET, value = "goodsPriceDetails")
	public String goodsPriceDetails(HttpServletRequest request, Integer pricechangeId, Model model) {
		logger.info("in goods BopsGoodsNewController toBatchChangePrice: ");
		GoodsPriceChangeResultVO goodsPriceChangeResultVO = bopsPricechangeService.getPriceChangeById(pricechangeId);
		model.addAttribute("priceChange", goodsPriceChangeResultVO);
		List<GoodsPriceResultVO> priceList = bopsPricechangeService.queryPriceList(pricechangeId);
		model.addAttribute("list", priceList);
		logger.info("out goods BopsGoodsNewController toBatchChangePrice: ");
		return ViewMappings.GOODS_PRICE_DETAILS;
	}

	/**
	 * 
	 * @author xiehao
	 * 2015年11月22日下午6:30:53
	 * @description 所有的商品价格修改列表
	 */
	@RequestMapping(method = RequestMethod.GET, value = "goodsPriceDetailsList")
	public String goodsPriceDetailsList(HttpServletRequest request, GoodsPriceDetailParamVO param, Model model) {
		logger.info("in goods BopsGoodsNewController toBatchChangePrice: ");
		
		List<BopsUser> userList = bopsUserDAO.getAllUser();
		if (userList.size() > 0) {
			model.addAttribute("userList", userList);
		}
		
		List<GoodsPriceDetailResultVO> resultList = bopsPricechangeService.queryGoodsPriceDetailsList(param);
		model.addAttribute("list", resultList);
		model.addAttribute("page", param);
		logger.info("out goods BopsGoodsNewController toBatchChangePrice: ");
		return ViewMappings.GOODS_PRICE_DETAIL_LIST;
	}

	/**
	 * 
	 * @author xiehao
	 * 2015年11月22日下午6:31:31
	 * @description 下载修改商品价格模板
	 */
	@RequestMapping(method = RequestMethod.POST, value = "templateDownload")
	public void pricetemplateDownload(HttpServletResponse response) {
		response.setHeader("Content-type", "text/html;charset=UTF-8");

		logger.info("TradeBaseController exportTradeBatchTemplate");
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			response.reset();
			String fileName = "修改商品价格模板";
			fileName = URLEncoder.encode(fileName, "UTF8");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
			response.setContentType("application / vnd.ms-excel; charset=utf-8");
			XSSFWorkbook workbook = bopsPricechangeService.exportPriceTemplate();
			try {
				workbook.write(os);
			} catch (IOException e) {
				logger.error(String.format("TradeBaseController exportTradeExcel_V1_1 write error", e.toString()));
				e.printStackTrace();
			}

			os.flush();
		} catch (Exception e) {
			logger.error(String.format("TradeBaseController exportTradeExcel_V1_1 exportTrade error", e.toString()));
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					logger.error(
							String.format("TradeBaseController exportTradeExcel_V1_1 os close error", e.toString()));
					e.printStackTrace();
				}
			}
		}

	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "suk2wms")
	public Message sku2wms(String goodsId){
		erpSkuToWmsService.skuInfoToWms(goodsId);
		
		return Message.success();
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "allSku2wms")
	public Message allSku2wms(){
		GoodsPageVO pageVO = new GoodsPageVO();
		pageVO.setPageSize(Integer.MAX_VALUE);
		List<GoodsStoreDetailResultVO> goodsList = bopsGoodsService.pageOfBopsGoods(pageVO);
		for(GoodsStoreDetailResultVO goods : goodsList){
			erpSkuToWmsService.skuInfoToWms(goods.getGoodsId());
		}
		return Message.success();
	}
}
