package com.need.oscar.web.controller.goods;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.biz.utils.BizCodeUtil;
import com.need.biz.utils.CurrencyUtil;
import com.need.common.validate.ValidatorUtil;
import com.need.dao.bops.goods.BopsGoodsDAO;
import com.need.domain.common.enums.AuditStatusEnums;
import com.need.domain.common.enums.CheckStatusEnums;
import com.need.domain.common.enums.GoodsStatusEnums;
import com.need.domain.po.bops.goods.BopsGoods;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.vo.goods.AuditGoodsVO;
import com.need.domain.vo.goods.GoodsDetailVO;
import com.need.domain.vo.goods.GoodsPageVO;
import com.need.domain.vo.goods.GoodsParamsVO;
import com.need.domain.vo.goods.GoodsStoreDetailResultVO;
import com.need.domain.vo.goods.GoodsStoreDetailVO;
import com.need.domain.vo.goods.GoodsVO;
import com.need.oscar.constant.ControllerMappings;
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

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public Message addBopsGoodsrManager(@RequestBody GoodsStoreDetailVO goodsStoreDetailVO, HttpServletRequest request)
			throws NumberFormatException, ParseException {

		logger.info("in goods addBopsGoodsrManager goodsStoreDetailVO: " + goodsStoreDetailVO);
		Set<ConstraintViolation<GoodsStoreDetailVO>> result = ValidatorUtil.getInstance().validate(goodsStoreDetailVO);
		if (result.size() > 0) {
			for (ConstraintViolation<?> c : result) {
				return Message.error(BizReturnCode.FIELD_VALIDATE_ERR, c.getMessage());
			}
		}

		// 判断商品的参数是否为空
		if (!goodsParamsIsNull(goodsStoreDetailVO)) {
			Message error = Message.error(3011);
			return error;
		}
		// 校验weight字段
		if (goodsStoreDetailVO.getWeight() != null) {
			if (!NumberUtil.isNumber(goodsStoreDetailVO.getWeight())) {
				Message error = Message.error(3020);
				return error;
			}

		}
		// 当商品的部分参数为空时，加一些默认值
		goodsStoreDetailVO.setDiscountPrice( // 录入商品时，若没有填写折扣价，则让商品的折扣价等于销售价
				goodsStoreDetailVO.getDiscountPrice() == null || goodsStoreDetailVO.getDiscountPrice() == 0
						? goodsStoreDetailVO.getOnsalePrice() : goodsStoreDetailVO.getDiscountPrice());
		goodsStoreDetailVO
				.setGoodsDesc(goodsStoreDetailVO.getGoodsDesc() == null ? " " : goodsStoreDetailVO.getGoodsDesc());
		// 商品的一些属性没有填写时，加一些默认值
		goodsStoreDetailVO.setColor(goodsStoreDetailVO.getColor() == null ? " " : goodsStoreDetailVO.getColor());
		goodsStoreDetailVO.setOriginPlace(
				goodsStoreDetailVO.getOriginPlace() == null ? " " : goodsStoreDetailVO.getOriginPlace());
		goodsStoreDetailVO.setSize(goodsStoreDetailVO.getSize() == null ? " " : goodsStoreDetailVO.getSize());
		goodsStoreDetailVO.setWeight(goodsStoreDetailVO.getWeight() == null ? " " : goodsStoreDetailVO.getWeight());

		Message message = Message.success();
		BopsGoods bopsGoods = new BopsGoods();
		GoodsParamsVO goodsParamsVO = new GoodsParamsVO();
		GoodsDetailVO goodsDetailVO = new GoodsDetailVO();
		BeanUtils.copyProperties(goodsStoreDetailVO, bopsGoods);
		// 商品的价格在数据库中是以分为单位储存
		bopsGoods.setOnsalePrice(
				Integer.parseInt(CurrencyUtil.fromYuanToFen(String.valueOf(goodsStoreDetailVO.getOnsalePrice()))));
		bopsGoods.setDiscountPrice(
				Integer.parseInt(CurrencyUtil.fromYuanToFen(String.valueOf(goodsStoreDetailVO.getDiscountPrice()))));
		// 商品的主图在数据库中是以逗号分隔来存储的
		String topPickeys = StringUtil.arrayToFormatString(goodsStoreDetailVO.getTopPicKeys(), ",");
		bopsGoods.setTopPicKeys(topPickeys);

		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);// 得到当前登录的用户信息
		bopsGoods.setPublisherId(user != null ? user.getUserId() : 1);
		BeanUtils.copyProperties(goodsStoreDetailVO, goodsParamsVO);
		BeanUtils.copyProperties(goodsStoreDetailVO, goodsDetailVO);
		String goodsId = BizCodeUtil.generateGoodsId(bopsGoods.getGoodsName());// 生成商品主键
		BopsGoods selectGoods = bopsGoodsDAO.selectByPrimaryKey(goodsId);
		if (bopsGoods.getOnsalePrice() < bopsGoods.getDiscountPrice()) {
			Message error = Message.error(3008);// 商品的折扣价不能大于原价
			return error;
		}
		if (selectGoods != null) {// 如果该商品已经存在则返回错误码
			Message errorMessage = Message.error(3001);
			return errorMessage;
		}
		BopsGoods goods = bopsGoodsDAO.getByGoodsCode(goodsStoreDetailVO.getGoodsCode());
		if (goods != null) {// 如果该商品的编号已经存在则返回错误码
			Message errorMessage = Message.error(3002);
			return errorMessage;
		}
		// 一切验证通过后则插入数据库
		bopsGoods.setGoodsId(goodsId);
		bopsGoods.setGoodsStatus(GoodsStatusEnums.OFFLINE.code);
		bopsGoods.setAuditStatus(CheckStatusEnums.WAIT.code);
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
	private boolean goodsParamsIsNull(GoodsStoreDetailVO goodsStoreDetailVO) {
		if (goodsStoreDetailVO.getDetailPicKeys() == null) {
			return false;
		}
		if (goodsStoreDetailVO.getTopPicKeys() == null) {
			return false;
		}
		if (goodsStoreDetailVO.getScenePicKey() == null) {
			return false;
		}
		if (goodsStoreDetailVO.getBrief() == null) {
			return false;
		}
		if (goodsStoreDetailVO.getGoodsCode() == null) {
			return false;
		}
		// if (goodsStoreDetailVO.getGoodsDesc() == null) {
		// return false;
		// }
		if (goodsStoreDetailVO.getGoodsBarcode() == null) {
			return false;
		}
		if (goodsStoreDetailVO.getIsGlobal() == null) {
			return false;
		}
		if (Boolean.TRUE.toString().toUpperCase().equals(goodsStoreDetailVO.getIsGlobal())) {
			if (goodsStoreDetailVO.getHaiguanCount() == null) {
				return false;
			}
			if (goodsStoreDetailVO.getGuojianCount() == null) {
				return false;
			}
			if (goodsStoreDetailVO.getGuojianCountryCode() == null) {
				return false;
			}
			if (goodsStoreDetailVO.getHaiguanCountryCode() == null) {
				return false;
			}
		}
		// if(goodsStoreDetailVO.getColor() == null){
		// return false;
		// }
		// if(goodsStoreDetailVO.getOriginPlace() == null){
		// return false;
		// }
		// if(goodsStoreDetailVO.getSize() == null){
		// return false;
		// }
		// if(goodsStoreDetailVO.getWeight() == null){
		// return false;
		// }
		if (goodsStoreDetailVO.getOnsalePrice() == null) {
			return false;
		}

		return true;
	}

	/**
	 * @author xiehao 2015年8月19日 下午2:05:37 @Method:
	 *         getBopsGoodsInfo @Description: TODO 获取某个商品的详细信息 @param
	 *         goodsId @return @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/{goodsId}")
	public Message getBopsGoodsInfo(@PathVariable String goodsId) {

		logger.info("in goods getBopsGoodsInfo goodsId: " + goodsId);

		Message message = Message.success();
		GoodsStoreDetailVO goodsStoreDetailVO = bopsGoodsService.viewGoods(goodsId);// 根据ID获取某个商品的详细信息

		message.addData("bopsGoods", goodsStoreDetailVO);

		logger.info("out goods getBopsGoodsInfo goodsId: " + goodsId);

		return message;
	}

	/**
	 * 
	 * @author xiehao 2015年8月13日 下午9:53:13 @Method:
	 *         updateBopsGoodsInfo @Description: TODO 编辑商品，商品重新变为带审核 @param
	 *         bopsGoodsVO @param request @return @throws
	 * @throws ParseException
	 * @throws NumberFormatException
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT, value = "/{goodsId}")
	public Message updateBopsGoodsInfo(@RequestBody GoodsStoreDetailVO bopsGoodsVO, HttpServletRequest request)
			throws NumberFormatException, ParseException {

		logger.info("in goods updateBopsGoodsInfo bopsGoodsVO: " + bopsGoodsVO);

		Message message = Message.success();
		if (bopsGoodsVO.getOnsalePrice() < bopsGoodsVO.getDiscountPrice()) {
			Message error = Message.error(3008);// 商品的折扣价不能大于原价
			return error;
		}
		BopsGoods selectGoods = bopsGoodsDAO.selectByPrimaryKey(bopsGoodsVO.getGoodsId());
		if (!bopsGoodsVO.getGoodsName().equals(selectGoods.getGoodsName())) { // 检查是否修改了商品的名称，以及修改后的名称是否以及存在
			String goodsId = BizCodeUtil.generateGoodsId(bopsGoodsVO.getGoodsName());// 生成商品主键
			if (bopsGoodsDAO.selectByPrimaryKey(goodsId) != null) {
				return Message.error(3001);
			}
		}
		// 校验weight字段
		if (bopsGoodsVO.getWeight() != null) {
			if (!NumberUtil.isNumber(bopsGoodsVO.getWeight())) {
				Message error = Message.error(3020);
				return error;
			}

		}
		bopsGoodsService.editGoods(bopsGoodsVO);

		logger.info("out goods updateBopsGoodsInfo bopsGoodsVO: " + bopsGoodsVO);

		return message;
	}

	/**
	 * 
	 * @author xiehao 2015年8月19日 下午2:18:50 @Method:
	 *         deleteBopsGoodsById @Description: TODO 此方法没有用 @param
	 *         goodsId @return @throws
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
	 * 
	 * @author xiehao 2015年8月19日 下午2:19:42 @Method:
	 *         getPageOfGoodsInfo @Description: TODO 获取商品的分页信息 @param
	 *         goodsPageVO @param page @param pageSize @return @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public Message getPageOfGoodsInfo(GoodsPageVO goodsPageVO, @RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer pageSize) {

		logger.info("in goods getPageOfGoodsInfo goodsPageVO: " + goodsPageVO);

		if ("".equals(goodsPageVO.getAuditStatus())) {
			goodsPageVO.setAuditStatus(null);
		}
		if ("".equals(goodsPageVO.getGoodsCode())) {
			goodsPageVO.setGoodsCode(null);
		}
		if ("".equals(goodsPageVO.getGoodsBarcode())) {
			goodsPageVO.setGoodsBarcode(null);
		}
		if ("".equals(goodsPageVO.getIsGlobal())) {
			goodsPageVO.setIsGlobal(null);
		}

		Message message = Message.success();
		List<GoodsStoreDetailResultVO> bopsGoodsList = null;
		if (page == null || pageSize == null) {
			goodsPageVO.setPage(1);
			goodsPageVO.setPageSize(Integer.MAX_VALUE);
		} else {
			message.addData("page", goodsPageVO);
		}
		bopsGoodsList = bopsGoodsService.pageOfBopsGoods(goodsPageVO);
		for (GoodsStoreDetailResultVO bopsGoods : bopsGoodsList) {
			if (bopsGoods.getTopPicKeys() != null) {
				String[] goodsPics = bopsGoods.getTopPicKeys().split(",");
				bopsGoods.setGoodsTopPics(goodsPics);
			}
		}
		message.addData("list", bopsGoodsList);

		logger.info("out goods getPageOfGoodsInfo goodsPageVO: " + goodsPageVO);

		return message;
	}

	/**
	 * 
	 * @author xiehao 2015年8月10日 下午12:22:21 @Method: auditGoods @Description:
	 *         TODO 审核商品 @param bopsGoods @param request @return @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT, value = "audit/{goodsId}")
	public Message auditGoods(@RequestBody AuditGoodsVO bopsGoods, HttpServletRequest request) {

		logger.info("in goods auditGoods bopsGoods: " + bopsGoods);

		Message message = Message.success();
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		bopsGoods.setAuditorId(user != null ? user.getUserId() : 1);
		bopsGoodsService.auditGoods(bopsGoods);
		logger.info("out goods auditGoods bopsGoods: " + bopsGoods);
		return message;
	}

	/**
	 * @author xiehao 2015年8月10日 下午2:36:35 @Method: uoOrLoadGoods @Description:
	 *         TODO 上架商品 @param bopsGoods @param request @return @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT, value = "online/{goodsId}")
	public Message AddedGoods(@RequestBody BopsGoods bopsGoods, HttpServletRequest request) {
		logger.info("in goods AddedGoods bopsGoods: " + bopsGoods);
		BopsGoods goods = bopsGoodsDAO.selectByGoodsId(bopsGoods.getGoodsId());
		if (!AuditStatusEnums.SUCCESS.code.equals(goods.getAuditStatus())) {// 只有审核通过的商品才能进行上下架操作
			return Message.error(3013);
		}
		int storeCount = bopsGoodsService.getGoodsMainStore(bopsGoods.getGoodsId());
		if (storeCount <= 0) {// 库存为0，不允许上架
			return Message.error(3012);
		}
		Message message = Message.success();
		bopsGoodsService.AddedOrtakeOrfreezeGoods(bopsGoods);
		logger.info("out goods AddedGoods bopsGoods: " + bopsGoods);

		return message;
	}

	/**
	 * 
	 * @author LXD 2015年8月10日 下午2:36:35 @Method: uoOrLoadGoods @Description:
	 *         TODO 商品下架、 @param bopsGoods @param request @return @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT, value = "offline/{goodsId}")
	public Message takeOffGoods(@RequestBody BopsGoods bopsGoods, HttpServletRequest request) {
		logger.info("in goods takeOffGoods bopsGoods: " + bopsGoods);
		BopsGoods goods = bopsGoodsDAO.selectByGoodsId(bopsGoods.getGoodsId());
		if (!AuditStatusEnums.SUCCESS.code.equals(goods.getAuditStatus())) {// 只有审核通过的商品才能进行上下架操作
			return Message.error(3013);
		}

		Message message = Message.success();
		bopsGoodsService.AddedOrtakeOrfreezeGoods(bopsGoods);
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
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "export")
	public void exportGoodsExcel(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) String goodsCode, @RequestParam(required = false) String goodsName,
			@RequestParam(required = false) String goodsStatus, @RequestParam(required = false) String auditStatus,
			@RequestParam(required = false) Integer pageSize, HttpServletResponse response) {

		response.setHeader("Content-type", "text/html;charset=UTF-8");
		GoodsPageVO goodsPageVO = new GoodsPageVO();
		goodsPageVO.setPage(page == null ? 1 : page);
		goodsPageVO.setPageSize(pageSize == null ? Integer.MAX_VALUE : pageSize);
		goodsPageVO.setAuditStatus(StringUtils.hasText(auditStatus) ? auditStatus : null);
		goodsPageVO.setGoodsCode(StringUtils.hasText(goodsCode) ? goodsCode : null);
		goodsPageVO.setGoodsStatus(StringUtils.hasText(goodsStatus) ? goodsStatus : null);
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
				e.printStackTrace();

			}

			os.flush();
		} catch (Exception e) {
			logger.error(String.format("export goods exportGoods:　%s", e.toString()));
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					logger.error(String.format("export goods close:　%s", e.toString()));
					e.printStackTrace();
				}
			}
		}

	}
	
	/***
	 * 
	 * @author LXD 2015年10月26日 下午3:56:00
	 * @Method: getByGoodsIds 
	 * @Description: TODO 根据商品编号数组返回商品list
	 * @param goodsIds
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/goodsCodes")
	public Message getByGoodsIds(@RequestBody GoodsVO goodsVO) {
        Message message=Message.success();
		logger.info("in goods getBopsGoodsInfo getGoodsCodes: " + goodsVO.getGoodsCodes());
		List<GoodsStoreDetailResultVO> list=bopsGoodsService.getBygoodsIds(goodsVO.getGoodsCodes());
		message.addData("list", list);
		return message;
	}

}
