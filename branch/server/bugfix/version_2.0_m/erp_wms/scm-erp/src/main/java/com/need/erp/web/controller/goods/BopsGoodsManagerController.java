package com.need.erp.web.controller.goods;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.need.biz.utils.BizCodeUtil;
import com.need.biz.utils.CurrencyUtil;
import com.need.common.validate.ValidatorUtil;
import com.need.dao.api.goods.GoodsMainDAO;
import com.need.dao.bops.goods.BopsGoodsBrandDAO;
import com.need.dao.bops.goods.BopsGoodsCategoriesDAO;
import com.need.dao.bops.goods.BopsGoodsCategoryDAO;
import com.need.dao.bops.goods.BopsGoodsDAO;
import com.need.dao.bops.goods.BopsGoodsDetailDAO;
import com.need.dao.bops.goods.BopsGoodsSalesDAO;
import com.need.dao.bops.store.BopsInventoryDAO;
import com.need.dao.bops.user.BopsUserDAO;
import com.need.domain.common.constant.ERPBizReturnCode;
import com.need.domain.common.enums.AuditStatusEnums;
import com.need.domain.common.enums.CheckStatusEnums;
import com.need.domain.common.enums.GoodsStatusEnums;
import com.need.domain.common.enums.SaveOrAuditGoodsEnum;
import com.need.domain.common.enums.WarehouseTypeEnum;
import com.need.domain.po.api.goods.GoodsMainPO;
import com.need.domain.po.bops.goods.BopsGoods;
import com.need.domain.po.bops.goods.BopsGoodsBrandPO;
import com.need.domain.po.bops.goods.BopsGoodsCategoriesPO;
import com.need.domain.po.bops.goods.BopsGoodsCategory;
import com.need.domain.po.bops.goods.BopsGoodsDetail;
import com.need.domain.po.bops.goods.BopsGoodsSales;
import com.need.domain.po.bops.store.BopsInventoryPO;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.vo.goods.AuditGoodsVO;
import com.need.domain.vo.goods.BopsGoodsCategoriesVO;
import com.need.domain.vo.goods.BopsGoodsItemsVO;
import com.need.domain.vo.goods.BopsGoodsVO;
import com.need.domain.vo.goods.BrandPageVO;
import com.need.domain.vo.goods.GoodsDetailVO;
import com.need.domain.vo.goods.GoodsPageVO;
import com.need.domain.vo.goods.GoodsParamsVO;
import com.need.domain.vo.goods.GoodsStoreDetailResultVO;
import com.need.domain.vo.goods.GoodsStoreDetailVO;
import com.need.domain.vo.store.BopsInventoryVO;
import com.need.erp.constant.ControllerMappings;
import com.need.erp.constant.ViewMappings;
import com.need.erp.pub.ConstantsProConfig;
import com.need.service.bops.goods.BopsGoodsService;
import com.need.service.constant.BizReturnCode;
import com.need.service.constant.Constants;
import com.need.utils.NumberUtil;
import com.need.utils.StringUtil;

/**
 * @author xiehao 2015年8月5日 下午3:11:38
 * @ClassName BopsGoodsManagerController
 * @Description TODO 商品管理
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: xiehao 2015年8月5日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.BOPS_GOODS_MANAGER)
public class BopsGoodsManagerController {

	private static final Logger logger = Logger.getLogger(BopsGoodsManagerController.class);

	@Autowired
	private BopsGoodsService bopsGoodsService;
	@Autowired
	private BopsGoodsDAO bopsGoodsDAO;
	@Autowired
	private BopsGoodsDetailDAO bopsGoodsDetailDAO;
	@Autowired
	private ConstantsProConfig constantsProConfig;
	@Autowired
	private BopsGoodsCategoryDAO bopsGoodsCategoryDAO;
	@Autowired
	private BopsUserDAO bopsUserDAO;
	@Autowired
	private BopsGoodsCategoriesDAO bopsGoodsCategoriesDAO;
	@Autowired
	private BopsGoodsBrandDAO bopsGoodsBrandDAO;
	// @Autowired
	// private BopsGoodsStoreDAO bopsGoodsStoreDAO;
	@Autowired
	private GoodsMainDAO goodsMainDAO;
	@Autowired
	private BopsGoodsSalesDAO bopsGoodsSalesDAO;
	@Autowired
	private BopsInventoryDAO bopsInventoryDAO;

	/**
	 * @author xiehao 2015年11月13日 下午5:27:12
	 * @Method: addBopsGoodsrManager
	 * @Description: TODO 新增商品
	 * @param goodsStoreDetailVO
	 * @param request
	 * @return
	 * @throws NumberFormatException
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "addNewGoods")
	public Message addBopsGoodsrManager(GoodsStoreDetailVO goodsStoreDetailVO, HttpServletRequest request)
			throws NumberFormatException, ParseException {

		logger.info("in goods addBopsGoodsrManager goodsStoreDetailVO: " + goodsStoreDetailVO);
		Set<ConstraintViolation<GoodsStoreDetailVO>> result = ValidatorUtil.getInstance().validate(goodsStoreDetailVO);
		if (result.size() > 0) {
			for (ConstraintViolation<?> c : result) {
				return Message.error(BizReturnCode.FIELD_VALIDATE_ERR, c.getMessage());
			}
		}

		// 判断商品的参数是否为空
		if (!goodsParamsIsNull(goodsStoreDetailVO, SaveOrAuditGoodsEnum.SAVE_GOODS.code)) {
			Message error = Message.error(ERPBizReturnCode.GOODS_3011);
			return error;
		}

		// 校验weight字段
		if (goodsStoreDetailVO.getWeight() != null) {
			if (!NumberUtil.isNumber(goodsStoreDetailVO.getWeight())) {
				Message error = Message.error(ERPBizReturnCode.GOODS_3020);
				return error;
			}
		}
		BopsGoods bopsGoods = new BopsGoods();
		String goodsId = BizCodeUtil.generateGoodsId(goodsStoreDetailVO.getGoodsName());// 生成商品主键
		BopsGoods selectGoods = bopsGoodsDAO.selectByPrimaryKey(goodsId);
		if (goodsStoreDetailVO.getOnsalePrice() < goodsStoreDetailVO.getDiscountPrice()) {
			Message error = Message.error(ERPBizReturnCode.GOODS_3008);// 商品的折扣价不能大于原价
			return error;
		}

		if (selectGoods != null) {// 如果该商品已经存在则返回错误码
			Message errorMessage = Message.error(ERPBizReturnCode.GOODS_3001);
			return errorMessage;
		}
		// BopsGoods goodsGetByGoodsCode =
		// bopsGoodsDAO.getByGoodsCode(goodsStoreDetailVO.getGoodsCode());
		// if (goodsGetByGoodsCode != null) {// 如果该商品的编号已经存在则返回错误码
		// Message errorMessage = Message.error(ERPBizReturnCode.GOODS_3002);
		// return errorMessage;
		// }
		// 商品国际条形码重复
		BopsGoods goodsGetByGoodsBarcode = bopsGoodsDAO.getByGoodsBarcode(goodsStoreDetailVO.getGoodsBarcode());
		if (null != goodsGetByGoodsBarcode) {
			return Message.error(ERPBizReturnCode.GOODS_3023);
		}

		// 当商品的部分参数为空时，加一些默认值
		goodsStoreDetailVO.setDiscountPrice( // 录入商品时，若没有填写折扣价，则让商品的折扣价等于销售价
				goodsStoreDetailVO.getDiscountPrice() == null || goodsStoreDetailVO.getDiscountPrice() == 0
						? goodsStoreDetailVO.getOnsalePrice() : goodsStoreDetailVO.getDiscountPrice());
		goodsStoreDetailVO
				.setGoodsDesc(goodsStoreDetailVO.getGoodsDesc() == null ? " " : goodsStoreDetailVO.getGoodsDesc());
		// 商品的一些属性没有填写时，加一些默认值
		goodsStoreDetailVO
				.setColor(!StringUtils.hasText(goodsStoreDetailVO.getColor()) ? " " : goodsStoreDetailVO.getColor());
		goodsStoreDetailVO.setOriginPlace(
				!StringUtils.hasText(goodsStoreDetailVO.getOriginPlace()) ? " " : goodsStoreDetailVO.getOriginPlace());
		goodsStoreDetailVO
				.setSize(!StringUtils.hasText(goodsStoreDetailVO.getSize()) ? " " : goodsStoreDetailVO.getSize());
		goodsStoreDetailVO
				.setWeight(!StringUtils.hasText(goodsStoreDetailVO.getWeight()) ? " " : goodsStoreDetailVO.getWeight());

		Message message = Message.success();

		GoodsParamsVO goodsParamsVO = new GoodsParamsVO();
		GoodsDetailVO goodsDetailVO = new GoodsDetailVO();
		BeanUtils.copyProperties(goodsStoreDetailVO, bopsGoods);
		// 商品的价格在数据库中是以分为单位储存
		bopsGoods.setOnsalePrice(
				Integer.parseInt(CurrencyUtil.fromYuanToFen(String.valueOf(goodsStoreDetailVO.getOnsalePrice()))));
		bopsGoods.setDiscountPrice(
				Integer.parseInt(CurrencyUtil.fromYuanToFen(String.valueOf(goodsStoreDetailVO.getDiscountPrice()))));
		// modify by liuhongyang 进货价
		bopsGoods.setPurchasePrice(
				Integer.parseInt(CurrencyUtil.fromYuanToFen(String.valueOf(goodsStoreDetailVO.getPurchasePrice()))));
		// 商品的主图在数据库中是以逗号分隔来存储的
		String topPickeys = StringUtil.arrayToFormatString(goodsStoreDetailVO.getTopPicKeys(), ",");
		bopsGoods.setTopPicKeys(topPickeys);

		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);// 得到当前登录的用户信息
		bopsGoods.setPublisherId(user != null ? user.getUserId() : 1);
		BeanUtils.copyProperties(goodsStoreDetailVO, goodsParamsVO);
		BeanUtils.copyProperties(goodsStoreDetailVO, goodsDetailVO);

		// 一切验证通过后则插入数据库
		bopsGoods.setGoodsId(goodsId);
		bopsGoods.setGoodsStatus(GoodsStatusEnums.OFFLINE.code);// 第一次添加商品为下架状态
		bopsGoods.setAuditStatus(CheckStatusEnums.WAIT.code);// 第一次添加商品为待审核状态
		bopsGoods.setSceneId(0);// 首次插入商品信息没有场景分类
		message.addData("object", bopsGoodsService.insertNewGoods(bopsGoods, goodsParamsVO, goodsDetailVO));
		logger.info("out goods addBopsGoodsrManager goodsStoreDetailVO: " + goodsStoreDetailVO);

		return message;
	}

	/**
	 * @author xiehao 2015年8月21日 下午9:47:59 @Method:
	 *         goodsParamsIsNull @Description: TODO 检查商品的属性是否为空 @param
	 *         goodsStoreDetailVO @return @throws
	 */
	private boolean goodsParamsIsNull(GoodsStoreDetailVO goodsStoreDetailVO, String type) {

		/*
		 * if (!StringUtils.hasText(goodsStoreDetailVO.getBrief())) { return
		 * false; }
		 */
		// if (!StringUtils.hasText(goodsStoreDetailVO.getGoodsCode())) {
		// return false;
		// }
		if (!StringUtils.hasText(goodsStoreDetailVO.getGoodsBarcode())) {
			return false;
		}
		if (!StringUtils.hasText(goodsStoreDetailVO.getGoodsName())) {
			return false;
		}
		/**
		 * 只有在新增商品的时候才去校验warehouse_type字段，因为编辑商品的时候，海关相关的字段没有在
		 * 页面展示，在保存的时候也就没有传到服务器，
		 */
		if (SaveOrAuditGoodsEnum.SAVE_GOODS.code.equals(type)
				&& WarehouseTypeEnum.TAX_WAREHOUSE.code.equals(goodsStoreDetailVO.getWarehouseType())) {
			if (!StringUtils.hasText(goodsStoreDetailVO.getHaiguanCount())) {
				return false;
			}
			if (!StringUtils.hasText(goodsStoreDetailVO.getGuojianCount())) {
				return false;
			}
			if (!StringUtils.hasText(goodsStoreDetailVO.getGuojianCountryCode())) {
				return false;
			}
			if (!StringUtils.hasText(goodsStoreDetailVO.getHaiguanCountryCode())) {
				return false;
			}
		}
		if (goodsStoreDetailVO.getOnsalePrice() == null) {
			return false;
		}
		if (SaveOrAuditGoodsEnum.SAVE_GOODS.code.equals(type)) {
			if (!StringUtils.hasText(goodsStoreDetailVO.getShortName())) {
				return false;
			}
			if (!StringUtils.hasText(goodsStoreDetailVO.getPurchaseManager())) {
				return false;
			}
			if (!StringUtils.hasText(goodsStoreDetailVO.getPurchaseLeader())) {
				return false;
			}
			if (goodsStoreDetailVO.getPurchasePrice() == null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @author xiehao 2015年11月13日 下午5:29:49
	 * @Method: getBopsGoodsInfo
	 * @Description: TODO 获取某个商品的详细信息, 进入商品详情页
	 * @param goodsId
	 * @param model
	 * @param page
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/goodsProfile")
	public String getBopsGoodsInfo(String goodsCode, Model model, Integer page) {

		logger.info("in goods getBopsGoodsInfo goodsCode: " + goodsCode);
		BopsGoods bopsGoods = bopsGoodsDAO.selectByGoodsCode(goodsCode);
		GoodsStoreDetailVO goodsStoreDetailVO = bopsGoodsService.viewGoods(bopsGoods.getGoodsId());// 根据ID获取某个商品的详细信息
		// 判断商品类型是否为组合商品
		if ("MORE".equals(goodsStoreDetailVO.getGoodsType())) {
			bopsGoods.setWarehouseType(WarehouseTypeEnum.getDesc(bopsGoods.getWarehouseType()));
			Double onsalePrice = Double.parseDouble(bopsGoods.getOnsalePrice().toString()) / 100.0;
			Double discountPrice = Double.parseDouble(bopsGoods.getDiscountPrice().toString()) / 100.0;
			if (null != bopsGoods.getPurchasePrice()) {
				Double purchasePrice = Double.parseDouble(bopsGoods.getPurchasePrice().toString()) / 100.0;
				goodsStoreDetailVO.setPurchasePrice(purchasePrice);
			}
			goodsStoreDetailVO.setOnsalePrice(onsalePrice);
			goodsStoreDetailVO.setDiscountPrice(discountPrice);
			List<BopsGoodsItemsVO> itemList = bopsGoodsService.selectGoodsItemDetail(bopsGoods.getGoodsId());
			model.addAttribute("bopsGoods", bopsGoods);
			model.addAttribute("itemList", itemList);
			model.addAttribute("goods", goodsStoreDetailVO);
			model.addAttribute("picAddress", constantsProConfig.getPic_domain());
/*			//统计销量
			BopsGoodsSales bopsGoodsSales = bopsGoodsSalesDAO.selectByPrimaryKey(bopsGoods.getGoodsId());
			model.addAttribute("bopsGoodsSales", bopsGoodsSales);
			//统计库存
			BopsInventoryPO bopsInventoryPO = bopsInventoryDAO.selectByGoodsId(bopsGoods.getGoodsId());
			BopsInventoryVO bopsInventoryVO = new BopsInventoryVO();
			BeanUtils.copyProperties(bopsInventoryPO, bopsInventoryVO);
			bopsInventoryVO.setSaleQuantity(bopsInventoryVO.getNormalQuantity()-bopsInventoryVO.getOrderFreeze()-bopsInventoryVO.getOtherFreeze());
			if(bopsInventoryVO.getSaleQuantity().intValue()>0){
				bopsInventoryVO.setIsHavest("1");
			}else{
				bopsInventoryVO.setIsHavest("2");
			}
			model.addAttribute("bopsInventoryVO", bopsInventoryVO);*/
			// 组合商品
			return ViewMappings.GOODS_GROUP_PROFILE;
		} else {
			// 查询所有用户
			List<BopsUser> userList = bopsUserDAO.getAllUser();
			if (userList.size() > 0) {
				model.addAttribute("userList", userList);
			}
			BopsGoodsCategoriesPO threeCategoer = bopsGoodsCategoriesDAO
					.selectByPrimaryKey(goodsStoreDetailVO.getGoodsCategoryId());
			if (threeCategoer != null) {
				goodsStoreDetailVO.setGoodsCategoryIdTwo(threeCategoer.getParentId());
				BopsGoodsCategoriesPO twoCategoer = bopsGoodsCategoriesDAO
						.selectByPrimaryKey(threeCategoer.getParentId());
				if (twoCategoer != null) {
					goodsStoreDetailVO.setGoodsCategoryIdOne(twoCategoer.getParentId());
				}
			}
			List<BopsGoodsCategoriesVO> goodsCategoryoneList = bopsGoodsCategoriesDAO.selectCategoryLevel("1");
			List<BopsGoodsCategoriesVO> goodsCategorytwoList = bopsGoodsCategoriesDAO.selectCategoryLevel("2");
			List<BopsGoodsCategoriesVO> goodsCategorythreeList = bopsGoodsCategoriesDAO.selectCategoryLevel("3");
			model.addAttribute("goodsCategoryoneList", goodsCategoryoneList);
			model.addAttribute("goodsCategorytwoList", goodsCategorytwoList);
			model.addAttribute("goodsCategorythreeList", goodsCategorythreeList);
			model.addAttribute("goods", goodsStoreDetailVO);
			model.addAttribute("picAddress", constantsProConfig.getPic_domain());
			model.addAttribute("page", page);
			List<BrandPageVO> brandList = bopsGoodsBrandDAO.queryall();
			model.addAttribute("brandList", brandList);
			logger.info("out goods getBopsGoodsInfo goodsCode: " + goodsCode);
			//统计销量
			BopsGoodsSales bopsGoodsSales = bopsGoodsSalesDAO.selectByPrimaryKey(bopsGoods.getGoodsId());
			model.addAttribute("bopsGoodsSales", bopsGoodsSales);
			//统计库存
			BopsInventoryPO bopsInventoryPO = bopsInventoryDAO.selectByGoodsId(bopsGoods.getGoodsId());
			BopsInventoryVO bopsInventoryVO = new BopsInventoryVO();
			if(null != bopsInventoryPO){
				BeanUtils.copyProperties(bopsInventoryPO, bopsInventoryVO);
				bopsInventoryVO.setSaleQuantity(bopsInventoryVO.getNormalQuantity().intValue()-bopsInventoryVO.getOrderFreeze().intValue()-bopsInventoryVO.getOtherFreeze().intValue());
				if(bopsInventoryVO.getSaleQuantity().intValue()>0){
				bopsInventoryVO.setIsHavest("1");
				}else{
					bopsInventoryVO.setIsHavest("2");
				}
			}
			model.addAttribute("bopsInventoryVO", bopsInventoryVO);
			// return message;
			return ViewMappings.GOODS_PROFILE;
		}

	}

	/**
	 * 
	 * @author xiehao 2015年10月13日 下午7:08:24
	 * @Method: getBopsGoodsInfoToEdit
	 * @Description: TODO 进入商品编辑页面
	 * @param goodsId
	 * @param model
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/goodsProfileEdit")
	public String getBopsGoodsInfoToEdit(String goodsId, Integer page, Model model) {

		logger.info("in goods getBopsGoodsInfo goodsId: " + goodsId);

		GoodsStoreDetailVO goodsStoreDetailVO = bopsGoodsService.viewGoods(goodsId);// 根据ID获取某个商品的详细信息
		if ("MORE".equals(goodsStoreDetailVO.getGoodsType())) {
			// 查询商品信息
			BopsGoods bopsGoods = bopsGoodsDAO.selectByPrimaryKey(goodsId);
			List<BopsGoodsItemsVO> itemList = bopsGoodsService.selectGoodsItemDetail(goodsId);
			Double onsalePrice = Double.parseDouble(bopsGoods.getOnsalePrice().toString()) / 100.0;
			Double discountPrice = Double.parseDouble(bopsGoods.getDiscountPrice().toString()) / 100.0;
			if (null != bopsGoods.getPurchasePrice()) {
				Double purchasePrice = Double.parseDouble(bopsGoods.getPurchasePrice().toString()) / 100.0;
				goodsStoreDetailVO.setPurchasePrice(purchasePrice);
			}
			goodsStoreDetailVO.setOnsalePrice(onsalePrice);
			goodsStoreDetailVO.setDiscountPrice(discountPrice);
			model.addAttribute("bopsGoods", bopsGoods);
			model.addAttribute("goods", goodsStoreDetailVO);
			model.addAttribute("itemList", itemList);
			// 组合商品
			return ViewMappings.GOODS_GROUP_EDIT;
		} else {
			List<BopsGoodsCategory> goodsCategoryList = bopsGoodsCategoryDAO.queryGoodsCategory();
			// 查询所有用户
			List<BopsUser> userList = bopsUserDAO.getErpUser();
			if (userList.size() > 0) {
				model.addAttribute("userList", userList);
			}
			BopsGoodsCategoriesPO threeCategoer = bopsGoodsCategoriesDAO
					.selectByPrimaryKey(goodsStoreDetailVO.getGoodsCategoryId());
			if (threeCategoer != null) {
				goodsStoreDetailVO.setGoodsCategoryIdTwo(threeCategoer.getParentId());
				BopsGoodsCategoriesPO twoCategoer = bopsGoodsCategoriesDAO
						.selectByPrimaryKey(threeCategoer.getParentId());
				if (twoCategoer != null) {
					goodsStoreDetailVO.setGoodsCategoryIdOne(twoCategoer.getParentId());
				}
			}
			List<BopsGoodsCategoriesVO> goodsCategoryoneList = bopsGoodsCategoriesDAO.selectCategoryLevel("1");
			// List<BopsGoodsCategoriesVO> goodsCategorytwoList
			// =bopsGoodsCategoriesDAO.selectCategoryLevel("2");
			// List<BopsGoodsCategoriesVO> goodsCategorythreeList
			// =bopsGoodsCategoriesDAO.selectCategoryLevel("3");
			model.addAttribute("goodsCategoryoneList", goodsCategoryoneList);
			BopsGoodsCategoriesPO categoryTwo = bopsGoodsCategoriesDAO
					.selectByPrimaryKey(goodsStoreDetailVO.getGoodsCategoryIdTwo());
			BopsGoodsCategoriesPO categoryThree = bopsGoodsCategoriesDAO
					.selectByPrimaryKey(goodsStoreDetailVO.getGoodsCategoryId());
			model.addAttribute("categoryTwo", categoryTwo);
			model.addAttribute("categoryThree", categoryThree);
			BopsGoodsBrandPO bopsGoodsBrandPO=null;
			if(goodsStoreDetailVO!=null && goodsStoreDetailVO.getBrandId()!=null){
				bopsGoodsBrandPO = bopsGoodsBrandDAO.selectByPrimaryKey(goodsStoreDetailVO.getBrandId());
			}
			List<BrandPageVO> brandList = bopsGoodsBrandDAO.queryall();
			String jsonObject = JSONObject.toJSON(brandList).toString();
			model.addAttribute("bopsGoodsBrandPO", bopsGoodsBrandPO);
			//model.addAttribute("brandList", brandList);
			model.addAttribute("jsonObject",jsonObject);
			model.addAttribute("goodsCategoryList", goodsCategoryList);
			// message.addData("bopsGoods", goodsStoreDetailVO);
			model.addAttribute("goods", goodsStoreDetailVO);
			model.addAttribute("goodsTopPicLength", goodsStoreDetailVO.getGoodsTopPics().length);
			model.addAttribute("goodsDetailPicLength", goodsStoreDetailVO.getDetailPicKeys().length);
			model.addAttribute("page", page);
			model.addAttribute("picAddress", constantsProConfig.getPic_domain());
			logger.info("out goods getBopsGoodsInfo goodsId: " + goodsId);

			// return message;
			return ViewMappings.GOODS_EDIT;
		}

	}

	/**
	 * @author xiehao 2015年10月15日 下午5:39:05
	 * @Method: toAddNewGoodsPage
	 * @Description: TODO 进入商品添加页面
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/toAddNewGoodsPage")
	public String toAddNewGoodsPage(Model model) {
		// List<BopsGoodsCategory> goodsCategoryList =
		// bopsGoodsCategoryDAO.queryGoodsCategory();
		List<BopsGoodsCategoriesVO> goodsCategoryList = bopsGoodsCategoriesDAO.selectCategoryLevel("1");
		model.addAttribute("goodsCategoryList", goodsCategoryList);
		List<BopsUser> userList = bopsUserDAO.getErpUser();
		if (userList.size() > 0) {
			model.addAttribute("userList", userList);
		}
		List<BrandPageVO> brandList = bopsGoodsBrandDAO.queryall();
		String jsonObject = JSONObject.toJSON(brandList).toString();
		//model.addAttribute("bopsGoodsBrandPO", bopsGoodsBrandPO);
		//model.addAttribute("brandList", brandList);
		model.addAttribute("jsonObject",jsonObject);
		//model.addAttribute("brandList", brandList);
		model.addAttribute("picAddress", constantsProConfig.getPic_domain());
		return ViewMappings.GOODS_ADD_PAGE;
	}

	/**
	 * @author xiehao 2015年11月13日 下午5:30:30
	 * @Method: updateBopsGoodsInfo
	 * @Description: TODO 编辑商品，商品重新变为带审核 , 保存商品编辑后的信息
	 * @param bopsGoodsVO
	 * @param request
	 * @throws NumberFormatException
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "goodsEditSave")
	public Message updateBopsGoodsInfo(GoodsStoreDetailVO bopsGoodsVO, HttpServletRequest request)
			throws NumberFormatException, ParseException {

		logger.info("in goods updateBopsGoodsInfo bopsGoodsVO: " + bopsGoodsVO);

		BopsGoods selectGoods = bopsGoodsDAO.selectByPrimaryKey(bopsGoodsVO.getGoodsId());
		// 判断商品的参数是否为空
		if (!goodsParamsIsNull(bopsGoodsVO, SaveOrAuditGoodsEnum.AUDIT_GOODS.code)) {
			Message error = Message.error(ERPBizReturnCode.GOODS_3011);
			return error;
		}

		Message message = Message.success();
		if (bopsGoodsVO.getOnsalePrice() < bopsGoodsVO.getDiscountPrice()) {
			Message error = Message.error(ERPBizReturnCode.GOODS_3008);// 商品的折扣价不能大于原价
			return error;
		}
		/**
		 * 先检查国际条形码是否做过修改
		 */
		if (!selectGoods.getGoodsBarcode().equals(bopsGoodsVO.getGoodsBarcode())) {
			BopsGoods goodsGetByGoodsBarcode = bopsGoodsDAO.getByGoodsBarcode(bopsGoodsVO.getGoodsBarcode());
			if (null != goodsGetByGoodsBarcode) {
				return Message.error(ERPBizReturnCode.GOODS_3023);// 商品国际条形码重复
			}
		}

		if (!bopsGoodsVO.getGoodsName().equals(selectGoods.getGoodsName())) { // 检查是否修改了商品的名称，以及修改后的名称是否以及存在
			String goodsId = BizCodeUtil.generateGoodsId(bopsGoodsVO.getGoodsName());// 生成商品主键
			if (bopsGoodsDAO.selectByPrimaryKey(goodsId) != null) {
				return Message.error(ERPBizReturnCode.GOODS_3001);
			}
		}
		// 校验weight字段
		if (bopsGoodsVO.getWeight() != null) {
			if (!NumberUtil.isNumber(bopsGoodsVO.getWeight())) {
				Message error = Message.error(ERPBizReturnCode.GOODS_3020);
				return error;
			}

		}
		bopsGoodsService.editGoods(bopsGoodsVO);

		logger.info("out goods updateBopsGoodsInfo bopsGoodsVO: " + bopsGoodsVO);

		return message;
	}

	/**
	 * @author xiehao 2015年11月13日 下午5:31:15
	 * @Method: deleteBopsGoodsById
	 * @Description: TODO 此方法没有用,此方法用于删除商品
	 * @param goodsId
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.DELETE, value = "/{goodsId}")
	public Message deleteBopsGoodsById(@PathVariable String goodsId) {
		logger.info("in goods deleteBopsGoodsById goodsId: " + goodsId);
		Message message = Message.success();
		bopsGoodsService.deleteById(goodsId);
		logger.info("out goods deleteBopsGoodsById goodsId: " + goodsId);
		return message;
	}

	/**
	 * @author xiehao 2015年11月13日 下午5:31:52
	 * @Method: getPageOfGoodsInfo
	 * @Description: TODO 获取商品的分页信息以及查询商品
	 * @param goodsPageVO
	 * @param model
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/page")
	public String getPageOfGoodsInfo(GoodsPageVO goodsPageVO, Model model) {

		logger.info("in goods getPageOfGoodsInfo goodsPageVO: " + goodsPageVO);

		if (!StringUtils.hasText(goodsPageVO.getAuditStatus())) {
			goodsPageVO.setAuditStatus(null);
		}
		if (!StringUtils.hasText(goodsPageVO.getGoodsCode())) {
			goodsPageVO.setGoodsCode(null);
		}
		if (!StringUtils.hasText(goodsPageVO.getGoodsBarcode())) {
			goodsPageVO.setGoodsBarcode(null);
		}
		if (!StringUtils.hasText(goodsPageVO.getIsGlobal())) {
			goodsPageVO.setIsGlobal(null);
		}
		if (!StringUtils.hasText(goodsPageVO.getShortName())) {
			goodsPageVO.setShortName(null);
		}

		List<GoodsStoreDetailResultVO> bopsGoodsList = null;
		if (goodsPageVO.getPage() == null || goodsPageVO.getPageSize() == null) {
			goodsPageVO.setPage(1);
			goodsPageVO.setPageSize(Integer.MAX_VALUE);
		} else {
			// message.addData("page", goodsPageVO);
		}
		bopsGoodsList = bopsGoodsService.pageOfBopsGoods(goodsPageVO);
		for (GoodsStoreDetailResultVO bopsGoods : bopsGoodsList) {
			if (bopsGoods.getTopPicKeys() != null) {
				String[] goodsPics = bopsGoods.getTopPicKeys().split(",");
				bopsGoods.setGoodsTopPics(goodsPics);
			}
			if (bopsGoods.getPurchasePrice() != null) {
				// 鏁版嵁搴撳崟浣嶄负鍒�杞崲涓哄厓
				Double price = bopsGoods.getPurchasePrice() / 100;
				bopsGoods.setPurchasePrice(price);
			}
			/**
			 * @author zhangmengbin 獲取多品的原價，need價，采购价2015-12-05
			 */
			/*
			 * if(bopsGoods!=null &&
			 * GoodsTypeEnums.MORE.code.equals(bopsGoods.getGoodsType())){
			 * SuitGoodsPriceVO suitGoodsPriceVO =
			 * bopsGoodsService.querySuitGoodsPrice(bopsGoods.getGoodsId(),
			 * "weight");
			 * bopsGoods.setOnsalePrice(suitGoodsPriceVO.getOnsalePrice());
			 * bopsGoods.setPurchasePrice(suitGoodsPriceVO.getPurchasePrice());
			 * bopsGoods.setDiscountPrice(suitGoodsPriceVO.getDiscountPrice());
			 * Double onsalePrice=bopsGoods.getOnsalePrice(); Double
			 * discountPrice=bopsGoods.getDiscountPrice(); Double
			 * purchasePrice=bopsGoods.getPurchasePrice();
			 * suitGoodsPriceVO.setOnsalePrice(onsalePrice);
			 * suitGoodsPriceVO.setDiscountPrice(discountPrice);
			 * suitGoodsPriceVO.setPurchasePrice(purchasePrice); }else{
			 * if(bopsGoods.getPurchasePrice() !=null){ //鏁版嵁搴撳崟浣嶄负鍒�杞崲涓哄厓
			 * Double price=bopsGoods.getPurchasePrice()/100;
			 * bopsGoods.setPurchasePrice(price); } }
			 */
		}
		// message.addData("list", bopsGoodsList);

		logger.info("out goods getPageOfGoodsInfo goodsPageVO: " + goodsPageVO);
		model.addAttribute("list", bopsGoodsList);
		model.addAttribute("page", goodsPageVO);
		model.addAttribute("picAddress", constantsProConfig.getPic_domain());
		return ViewMappings.GOODS_LIST;
	}

	/**
	 * 
	 * @author xiehao 2015年8月10日 下午12:22:21 @Method: auditGoods @Description:
	 *         TODO 审核商品
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/auditGoods")
	public Message auditGoods(AuditGoodsVO auditGoods, HttpServletRequest request, Model model) {

		logger.info("in goods auditGoods bopsGoods: " + auditGoods);
		/**
		 * 先检查商品的图片属性是否有值
		 */
		Message message = Message.success();
		/*
		 * boolean hasTextBopsGoodsParam =
		 * hasTextAuditGoodsParams(auditGoods.getGoodsId());
		 * if(!hasTextBopsGoodsParam &&
		 * AuditStatusEnums.SUCCESS.code.equals(auditGoods.getAuditStatus())){
		 * return Message.error(ERPBizReturnCode.GOODS_3022);//商品的图片不能为空 }
		 */

		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		auditGoods.setAuditorId(user != null ? user.getUserId() : 1);
		bopsGoodsService.auditGoods(auditGoods);
		logger.info("out goods auditGoods bopsGoods: " + auditGoods);
		return message;
	}

	private boolean hasTextAuditGoodsParams(String goodsId) {
		BopsGoods bopsGoods = bopsGoodsDAO.selectByGoodsId(goodsId);
		BopsGoodsDetail goodsDetail = bopsGoodsDetailDAO.selectByPrimaryKey(goodsId);
		if (!StringUtils.hasText(bopsGoods.getScenePicKey())) {
			return false;
		}
		if (!StringUtils.hasText(bopsGoods.getTopPicKeys())) {
			return false;
		}
		if (!StringUtils.hasText(goodsDetail.getDetailPicKeys())) {
			return false;
		}

		return true;
	}

	/**
	 * @author xiehao 2015年8月10日 下午2:36:35 @Method: uoOrLoadGoods @Description:
	 *         TODO 上架商品 @param bopsGoods @param request @return @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "online")
	public Message AddedGoods(BopsGoods bopsGoods, HttpServletRequest request) {
		logger.info("in goods AddedGoods bopsGoods: " + bopsGoods);
		BopsGoods goods = bopsGoodsDAO.selectByGoodsId(bopsGoods.getGoodsId());
		goods.setGoodsStatus("ONLINE");
		Message message = Message.success();
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		goods.setUpdateOnlineId(user != null ? user.getUserId() : 1);
		// 判断商品类型
		if ("MORE".equals(goods.getGoodsType())) {
			// 查询商品库存
			List<BopsGoodsItemsVO> itemList = bopsGoodsService.selectGoodsItemDetail(bopsGoods.getGoodsId());
			for (BopsGoodsItemsVO bopsGoodsItemsVO : itemList) {
				if (null != bopsGoodsItemsVO.getStoreCount()) {
					int storeCount = bopsGoodsItemsVO.getStoreCount().intValue();
					if (storeCount <= 0) {// 库存为0，不允许上架
						return Message.error(ERPBizReturnCode.GOODS_3012);
					}
				} else {
					return Message.error(ERPBizReturnCode.GOODS_3012);
				}
				BopsGoods offGoods = bopsGoodsDAO.selectByGoodsId(bopsGoodsItemsVO.getGoodsId());
				if ("OFFLINE".equals(offGoods.getGoodsStatus())) {
					// 下架商品不能添加为组合商品
					return Message.error(ERPBizReturnCode.GOODS_OFFLINE_ONLINE_3034);
				}
			}
			bopsGoodsService.updateGoodsStatus(goods);
			logger.info("out goods AddedGoods bopsGoods: " + bopsGoods);
		} else {
			if (!AuditStatusEnums.SUCCESS.code.equals(goods.getAuditStatus())) {// 只有审核通过的商品才能进行上下架操作
				return Message.error(ERPBizReturnCode.GOODS_3013);
			}
			int storeCount = bopsGoodsService.getGoodsMainStore(bopsGoods.getGoodsId());
			if (storeCount <= 0) {// 库存为0，不允许上架
				return Message.error(ERPBizReturnCode.GOODS_3012);
			}
			bopsGoodsService.updateGoodsStatus(goods);
			logger.info("out goods AddedGoods bopsGoods: " + bopsGoods);
		}

		return message;
	}

	/**
	 * 
	 * @author LXD 2015年8月10日 下午2:36:35 @Method: uoOrLoadGoods @Description:
	 *         TODO 商品下架、 @param bopsGoods @param request @return @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "offline")
	public Message takeOffGoods(BopsGoods bopsGoods, HttpServletRequest request) {
		logger.info("in goods takeOffGoods bopsGoods: " + bopsGoods);
		BopsGoods goods = bopsGoodsDAO.selectByGoodsId(bopsGoods.getGoodsId());
		if (!AuditStatusEnums.SUCCESS.code.equals(goods.getAuditStatus())) {// 只有审核通过的商品才能进行上下架操作
			return Message.error(ERPBizReturnCode.GOODS_3013);
		}

		Message message = Message.success();
		goods.setGoodsStatus("OFFLINE");
		bopsGoodsService.updateGoodsStatus(goods);
		logger.info("out goods takeOffGoods bopsGoods: " + bopsGoods);
		return message;

	}

	/**
	 * 
	 * @author LXD 2015年8月10日 下午2:36:35 @Method: uoOrLoadGoods @Description:
	 *         TODO 商品冻结 @param bopsGoods @param request @return @throws 此方法没有用
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT, value = "freeze/{goodsId}")
	public Message freezeGoods(@RequestBody BopsGoods bopsGoods, HttpServletRequest request) {
		logger.info("in goods freezeGoods bopsGoods: " + bopsGoods);

		Message message = Message.success();
		bopsGoodsService.AddedOrtakeOrfreezeGoods(bopsGoods);
		logger.info("out goods freezeGoods bopsGoods: " + bopsGoods);
		return message;

	}

	/**
	 * 
	 * @author LXD 2015年8月23日 下午4:15:02 @Method: exportGoodsExcel @Description:
	 *         TODO 商品到处EXCEL @param page @param pageSize @param
	 *         response @throws
	 */
	@RequestMapping(method = RequestMethod.POST, value = "export")
	public void exportGoodsExcel(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) String goodsCode, @RequestParam(required = false) String goodsName,
			@RequestParam(required = false) String goodsStatus, @RequestParam(required = false) String auditStatus,
			String warehouseType, @RequestParam(required = false) Integer pageSize, HttpServletResponse response) {

		response.setHeader("Content-type", "text/html;charset=UTF-8");
		GoodsPageVO goodsPageVO = new GoodsPageVO();
		goodsPageVO.setPage(page == null ? 1 : page);
		goodsPageVO.setPageSize(pageSize == null ? Integer.MAX_VALUE : pageSize);
		goodsPageVO.setAuditStatus(StringUtils.hasText(auditStatus) ? auditStatus : null);
		goodsPageVO.setGoodsCode(StringUtils.hasText(goodsCode) ? goodsCode : null);
		goodsPageVO.setGoodsStatus(StringUtils.hasText(goodsStatus) ? goodsStatus : null);
		goodsPageVO.setWarehouseType(StringUtils.hasText(warehouseType) ? warehouseType : null);
		goodsPageVO.setGoodsName(StringUtils.hasText(goodsName) ? goodsName : null);
		logger.info(String.format("export goods params:　%s", goodsPageVO.toString()));
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=" + System.currentTimeMillis() + ".xls");
			response.setContentType("application /  vnd.ms-excel; charset=utf-8");
			HSSFWorkbook workbook = bopsGoodsService.exportGoods(goodsPageVO);
			try {
				workbook.write(os);
			} catch (IOException e) {
				logger.error(String.format("export goods write:　%s", e.toString()));
				StringWriter sw = new StringWriter();
				e.printStackTrace(new PrintWriter(sw, true));
				String str = sw.toString();
				logger.info("printStackTrace :" + str);// 记录异常的堆栈信息到log4j
				e.printStackTrace();

			}

			os.flush();
		} catch (Exception e) {
			logger.error(String.format("export goods exportGoods:　%s", e.toString()));
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw, true));
			String str = sw.toString();
			logger.info("printStackTrace :" + str);// 记录异常的堆栈信息到log4j
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					logger.error(String.format("export goods close:　%s", e.toString()));
					StringWriter sw = new StringWriter();
					e.printStackTrace(new PrintWriter(sw, true));
					String str = sw.toString();
					logger.info("printStackTrace :" + str);// 记录异常的堆栈信息到log4j
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 
	 * @author liuhongyang 2015年12月3日 上午11:43:10 @Method:
	 * toAddNewGoodsGroup @Description: 进入组合商品新增 @param model @return @throws
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/toAddNewGoodsGroup")
	public String toAddNewGoodsGroup(Model model) {
		return ViewMappings.GOODS_GROUP_ADD;
	}

	/**
	 * 
	 * @author liuhongyang 2015年12月4日 上午11:23:26 @Method:
	 * getGoodsDetail @Description: 根据商品code查询商品信息 @param goodsCode @param
	 * model @return @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/getGoodsDetail")
	public Message getGoodsDetail(String goodsCode, String warehouseType, Model model) {
		logger.info("getGoodsDetail........: " + goodsCode+"......"+warehouseType);
		GoodsStoreDetailVO goodsStoreDetailVO = bopsGoodsService.getGoodsDetail(goodsCode, warehouseType);
		Message message = Message.success();
		if (null == goodsStoreDetailVO) {
			return Message.error(ERPBizReturnCode.GOODS_EXIST_NOT_3030);
		} else {
			if (!StringUtils.hasText(goodsStoreDetailVO.getGoodsName())) {
				return Message.error(ERPBizReturnCode.GOODS_EXIST_NOT_3030);
			}
		}
		BopsGoods goods = bopsGoodsDAO.selectByGoodsCode(goodsCode);
		if ("OFFLINE".equals(goods.getGoodsStatus())) {
			// 下架商品不能添加为组合商品
			return Message.error(ERPBizReturnCode.GOODS_GROUP_ADD_3028);
		}
		if ("FREEZE".equals(goods.getGoodsStatus())) {
			// 冻结商品不能添加为组合商品
			return Message.error(ERPBizReturnCode.GOODS_GROUP_ADD_3029);
		}
		message.addData("data", goodsStoreDetailVO);
		return message;
	}

	/**
	 * 
	 * @author liuhongyang 2015年12月4日 下午6:36:42 @Method:
	 * AddNewGoodsGroup @Description: 组合商品新增 @param dataAll @param
	 * goodsName @param warehouseType @param shortName @param
	 * request @return @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/AddNewGoodsGroup")
	public Message AddNewGoodsGroup(String dataAll, String onsalePrice, String discountPrice, String purchasePrice,
			String goodsName, String warehouseType, String shortName, String weight, HttpServletRequest request) {
		logger.info("AddNewGoodsGroup......."+dataAll+"..."+onsalePrice+"..."+discountPrice+"..."+purchasePrice+"..."+purchasePrice+"..."+goodsName+"..."+warehouseType+"..."+shortName+"..."+weight);
		// 商品名称和短名不能为空
		if (!StringUtils.hasText(goodsName.trim())) {
			return Message.error(ERPBizReturnCode.GOODS_NAME_NULL_3032);
		}
		if (!StringUtils.hasText(shortName.trim())) {
			return Message.error(ERPBizReturnCode.GOODS_SHORTNAME_NULL_3033);
		}
		String goodsId = BizCodeUtil.generateGoodsId(goodsName);
		BopsGoods selectGoods = bopsGoodsDAO.selectByPrimaryKey(goodsId);
		if (selectGoods != null) {// 如果该商品已经存在则返回错误码
			Message errorMessage = Message.error(ERPBizReturnCode.GOODS_3001);
			return errorMessage;
		}
		BopsGoods bopsGoods = new BopsGoods();
		bopsGoods.setGoodsName(goodsName);
		bopsGoods.setShortName(shortName);
		bopsGoods.setWarehouseType(warehouseType);
		// 验证库存
		String[] goodsArray = dataAll.split("@");
		String[] newArray;
		List<BopsGoodsItemsVO> dataList = new ArrayList<BopsGoodsItemsVO>();
		for (int i = 0; i < goodsArray.length; i++) {
			logger.info("AddNewGoodsGroup...........goodsArray="+goodsArray.toString());
			if (i == 0) {
				newArray = goodsArray[i].split(",");
			} else {
				String moreArray = goodsArray[i].substring(1);
				newArray = moreArray.split(",");
			}
			BopsGoodsItemsVO bopsGoodsItemsVO = new BopsGoodsItemsVO();
			bopsGoodsItemsVO.setGoodsCode(newArray[0]);
			bopsGoodsItemsVO.setGoodsCount(Integer.parseInt(newArray[1]));
			// 根据商品code查询库存 需修改
			BopsGoods goods = bopsGoodsDAO.selectByGoodsCode(bopsGoodsItemsVO.getGoodsCode());
			if (null == goods) {
				return Message.error(ERPBizReturnCode.GOODS_EXIST_NOT_3030);
			}
			if ("OFFLINE".equals(goods.getGoodsStatus())) {
				// 下架商品不能添加为组合商品
				return Message.error(ERPBizReturnCode.GOODS_GROUP_ADD_3028);
			}
			if ("FREEZE".equals(goods.getGoodsStatus())) {
				// 冻结商品不能添加为组合商品
				return Message.error(ERPBizReturnCode.GOODS_GROUP_ADD_3029);
			}
			// 查询api库
			GoodsMainPO GoodsMainPO = goodsMainDAO.selectByPrimaryKey(goods.getGoodsId());
			if (bopsGoodsItemsVO.getGoodsCount().intValue() > GoodsMainPO.getStoreCount().intValue()) {
				return Message.error(ERPBizReturnCode.GOODS_STORE_NOT_3027);
			}
			// 商品类型与仓库类型是否一致
			if (!bopsGoods.getWarehouseType().equals(goods.getWarehouseType())) {
				return Message.error(ERPBizReturnCode.GOODS_WAREHOUSETYPE_NOT_3031);
			}
			bopsGoodsItemsVO.setGoodsId(goods.getGoodsId());// 当前登录用户
			BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
			bopsGoodsItemsVO.setCreateId(user != null ? user.getUserId() : 1);
			dataList.add(bopsGoodsItemsVO);
		}
		bopsGoods.setOnsalePrice(Integer.parseInt(CurrencyUtil.fromYuanToFen(onsalePrice)));
		bopsGoods.setDiscountPrice(Integer.parseInt(CurrencyUtil.fromYuanToFen(discountPrice)));
		bopsGoods.setPurchasePrice(Integer.parseInt(CurrencyUtil.fromYuanToFen(purchasePrice)));
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		bopsGoods.setPublisherId(user != null ? user.getUserId() : 1);
		bopsGoods.setGoodsId(goodsId);
		GoodsParamsVO paramsVO = new GoodsParamsVO();
		paramsVO.setWeight(weight);
		bopsGoodsService.insertGoodsItems(dataList, bopsGoods, paramsVO);
		Message message = Message.success();
		return message;

	}

	/**
	 * 
	 * @author liuhongyang 2015年12月4日 下午6:36:42 @Method:
	 * AddNewGoodsGroup @Description: 组合商品编辑 @param dataAll @param
	 * goodsName @param warehouseType @param shortName @param
	 * request @return @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/ModGoodsGroup")
	public Message ModGoodsGroup(String dataAll, String goodsCode, String onsalePrice, String discountPrice,
			String purchasePrice, String goodsName, String warehouseType, String weight, String shortName,
			HttpServletRequest request) {
		logger.info("ModGoodsGroup......."+dataAll+"..."+goodsCode+"..."+onsalePrice+"..."+discountPrice+"..."+purchasePrice+"..."+goodsName+"..."+warehouseType+"..."+weight+"..."+shortName);
		// 商品名称和短名不能为空
		if (!StringUtils.hasText(goodsName.trim())) {
			return Message.error(ERPBizReturnCode.GOODS_NAME_NULL_3032);
		}
		if (!StringUtils.hasText(shortName.trim())) {
			return Message.error(ERPBizReturnCode.GOODS_SHORTNAME_NULL_3033);
		}
		BopsGoods existGoods = bopsGoodsDAO.selectByGoodsCode(goodsCode);
		if(!goodsName.trim().equals(existGoods.getGoodsName())){
			String goodsId = BizCodeUtil.generateGoodsId(goodsName);
			BopsGoods selectGoods = bopsGoodsDAO.selectByPrimaryKey(goodsId);
			if (selectGoods != null) {// 如果该商品已经存在则返回错误码
				Message errorMessage = Message.error(ERPBizReturnCode.GOODS_3001);
				return errorMessage;
			}
		}
		BopsGoods bopsGoods = new BopsGoods();
		bopsGoods.setGoodsName(goodsName);
		bopsGoods.setShortName(shortName);
		bopsGoods.setWarehouseType(warehouseType);
		// 验证库存
		// 查询原信息
		BopsGoods preGoods = bopsGoodsDAO.selectByGoodsCode(goodsCode);
		// 删除商品详细信息
		String[] goodsArray = dataAll.split("@");
		String[] newArray;
		List<BopsGoodsItemsVO> dataList = new ArrayList<BopsGoodsItemsVO>();
		for (int i = 0; i < goodsArray.length; i++) {
			logger.info("ModGoodsGroup...........goodsArray="+goodsArray.toString());
			if (i == 0) {
				newArray = goodsArray[i].split(",");
			} else {
				String moreArray = goodsArray[i].substring(1);
				newArray = moreArray.split(",");
			}
			BopsGoodsItemsVO bopsGoodsItemsVO = new BopsGoodsItemsVO();
			bopsGoodsItemsVO.setGoodsCode(newArray[0]);
			bopsGoodsItemsVO.setGoodsCount(Integer.parseInt(newArray[1]));
			// 根据商品code查询库存 需修改
			BopsGoods goods = bopsGoodsDAO.selectByGoodsCode(bopsGoodsItemsVO.getGoodsCode());
			if (null == goods) {
				return Message.error(ERPBizReturnCode.GOODS_EXIST_NOT_3030);
			}
			if ("OFFLINE".equals(goods.getGoodsStatus())) {
				// 下架商品不能添加为组合商品
				return Message.error(ERPBizReturnCode.GOODS_GROUP_ADD_3028);
			}
			if ("FREEZE".equals(goods.getGoodsStatus())) {
				// 冻结商品不能添加为组合商品
				return Message.error(ERPBizReturnCode.GOODS_GROUP_ADD_3029);
			}
			// 查询api库
			GoodsMainPO GoodsMainPO = goodsMainDAO.selectByPrimaryKey(goods.getGoodsId());
			if (bopsGoodsItemsVO.getGoodsCount().intValue() > GoodsMainPO.getStoreCount().intValue()) {
				return Message.error(ERPBizReturnCode.GOODS_STORE_NOT_3027);
			}
			// 商品类型与仓库类型是否一致
			if (!bopsGoods.getWarehouseType().equals(goods.getWarehouseType())) {
				return Message.error(ERPBizReturnCode.GOODS_WAREHOUSETYPE_NOT_3031);
			}
			bopsGoodsItemsVO.setGoodsId(goods.getGoodsId());// 当前登录用户
			BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
			bopsGoodsItemsVO.setUpdateId(user != null ? user.getUserId() : 1);
			bopsGoodsItemsVO.setCreateId(preGoods.getPublisherId());
			bopsGoodsItemsVO.setCreateTime(preGoods.getCreateTime());
			dataList.add(bopsGoodsItemsVO);
		}
		bopsGoods.setGoodsCode(goodsCode);
		bopsGoods.setOnsalePrice(Integer.parseInt(CurrencyUtil.fromYuanToFen(onsalePrice)));
		bopsGoods.setDiscountPrice(Integer.parseInt(CurrencyUtil.fromYuanToFen(discountPrice)));
		bopsGoods.setPurchasePrice(Integer.parseInt(CurrencyUtil.fromYuanToFen(purchasePrice)));
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		bopsGoods.setPublisherId(user != null ? user.getUserId() : 1);
		GoodsParamsVO godsParamsVO = new GoodsParamsVO();
		godsParamsVO.setWeight(weight);
		bopsGoodsService.updateGoodsItems(dataList, bopsGoods, preGoods, godsParamsVO);
		Message message = Message.success();
		return message;

	}
	/**
	 * 
	 * @author liuhongyang 2015年12月12日 下午3:03:33
	 * @Method: changePrice 
	 * @Description: 修改商品价格
	 * @param goodsCode
	 * @param warehouseType
	 * @param model
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/changePrice")
	public Message changePrice(String dataAll,Model model) {
		logger.info("changePrice...........dataAll="+dataAll);
		Message message = Message.success();
		BopsGoodsVO priceGoods=new BopsGoodsVO();
		if(StringUtils.hasText(dataAll.trim())){
			String[] goodsArray=dataAll.split("@");
			String[] newArray;
			int onsaleP = 0;  
			int needP = 0;  
			int purchaseP = 0;  
			int weightP = 0;  
			for (int i = 0; i < goodsArray.length; i++) {
				if(i==0){
					newArray=goodsArray[i].split(",");
				}else{
					String moreArray=goodsArray[i].substring(1);
					newArray=moreArray.split(",");
				}
				BopsGoodsItemsVO bopsGoodsItemsVO=new BopsGoodsItemsVO();
				bopsGoodsItemsVO.setGoodsCode(newArray[0]);
				bopsGoodsItemsVO.setGoodsCount(Integer.parseInt(newArray[1]));
				//根据商品code查询商品价格信息
				BopsGoods bopsGoods=bopsGoodsDAO.selectByGoodsCode(bopsGoodsItemsVO.getGoodsCode());
				if(null != bopsGoods){
					BigDecimal onsalePrice= new BigDecimal(bopsGoods.getOnsalePrice()+"").multiply(BigDecimal.valueOf(1L));
					BigDecimal needPrice= new BigDecimal(bopsGoods.getDiscountPrice()+"").multiply(BigDecimal.valueOf(1L));
					BigDecimal purchasePrice= new BigDecimal(bopsGoods.getPurchasePrice()+"").multiply(BigDecimal.valueOf(1L));
					GoodsStoreDetailVO goodsStoreDetailVO = bopsGoodsService.viewGoods(bopsGoods.getGoodsId());
					if(null != goodsStoreDetailVO){
						BigDecimal weight= new BigDecimal(goodsStoreDetailVO.getWeight()).multiply(BigDecimal.valueOf(1000L));
						onsaleP =onsaleP + onsalePrice.intValue() * bopsGoodsItemsVO.getGoodsCount();
						needP =needP + needPrice.intValue() * bopsGoodsItemsVO.getGoodsCount();
						purchaseP =purchaseP + purchasePrice.intValue() * bopsGoodsItemsVO.getGoodsCount();
						weightP =weightP + weight.intValue() * bopsGoodsItemsVO.getGoodsCount();
						String oPrice=CurrencyUtil.fromFenToYuan(String.valueOf(onsaleP));
						String nPrice=CurrencyUtil.fromFenToYuan(String.valueOf(needP));
						String pPrice=CurrencyUtil.fromFenToYuan(String.valueOf(purchaseP));
						String wPrice=fromQgToG(String.valueOf(weightP));
						priceGoods.setoPrice(oPrice);
						priceGoods.setnPrice(nPrice);
						priceGoods.setpPrice(pPrice);
						priceGoods.setwPrice(wPrice);
						message.addData("priceGoods", priceGoods);
					}
					
				}
			}
		}else{
			priceGoods.setoPrice("");
			priceGoods.setnPrice("");
			priceGoods.setpPrice("");
			priceGoods.setwPrice("");
			message.addData("priceGoods", priceGoods);
		}
		
		return message;
	}
		
	/**
	 * 
	 * @author liuhongyang 2015年12月12日 下午3:55:52
	 * @Method: fromQgToG 
	 * @Description: 千克转为克
	 * @param fen
	 * @return 
	 * @throws
	 */
    public static String fromQgToG(final String fen) {  
        if(StringUtils.isEmpty(fen)||fen.equals("0")){
        	return fen;
        }   	
    	String yuan ="";  
        final int MULTIPLIER = 1000;  
        Pattern pattern = Pattern.compile("^[1-9][0-9][0-9]*{1}");  
        Matcher matcher = pattern.matcher(fen);  
        if (matcher.matches()) {  
            yuan = new BigDecimal(fen).divide(new BigDecimal(MULTIPLIER)).setScale(3).toString();  
        } else {  
            System.out.println("参数格式不正确!");  
        }  
        return yuan;  
    }  
}
