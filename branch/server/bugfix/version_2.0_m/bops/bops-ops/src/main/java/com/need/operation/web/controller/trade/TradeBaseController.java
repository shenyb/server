package com.need.operation.web.controller.trade;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.need.biz.utils.CurrencyUtil;
import com.need.dao.api.goods.GoodsMainDAO;
import com.need.dao.api.trade.TradeAddressDAO;
import com.need.dao.api.trade.TradeBaseDAO;
import com.need.dao.api.user.UserBaseDAO;
import com.need.dao.bops.trade.BopsTradeLogisticsDAO;
import com.need.dao.bops.trade.BopsTradePushPullDAO;
import com.need.domain.common.enums.PayChannelEnum;
import com.need.domain.po.api.goods.GoodsMainPO;
import com.need.domain.po.api.trade.TradeAddressPO;
import com.need.domain.po.api.trade.TradeBasePO;
import com.need.domain.po.api.user.UserBase;
import com.need.domain.po.bops.trade.BopsTradeLogistics;
import com.need.domain.po.bops.trade.BopsTradePushPull;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.vo.trade.BopsTradeLogisticsVO;
import com.need.domain.vo.trade.GoodsSnNoVO;
import com.need.domain.vo.trade.LogisticsInfoJsonVO;
import com.need.domain.vo.trade.OrderExportParamVO;
import com.need.domain.vo.trade.RefundParamVO;
import com.need.domain.vo.trade.TradeBaseDetailVO;
import com.need.domain.vo.trade.TradeBaseDetailVO.TradeBaseDetailGoodsVO;
import com.need.domain.vo.trade.TradeBaseParam;
import com.need.domain.vo.trade.TradeBaseSentVO;
import com.need.domain.vo.trade.TradeBaseVO;
import com.need.domain.vo.trade.TradeBatchNoParamsVO;
import com.need.domain.vo.trade.TradeOrderParamsVO;
import com.need.domain.vo.trade.TradeRefundDetailVO;
import com.need.domain.vo.trade.TradeRefundDetailVO.TradeRefundGoodsVO;
import com.need.domain.vo.trade.TradeVO;
import com.need.operation.constant.ControllerMappings;
import com.need.service.bops.trade.BopsTradeBaseService;
import com.need.service.common.exception.ServiceException;
import com.need.service.constant.Constants;
import com.need.utils.StringUtil;

/**
 * 
 * <p>
 * </p>
 * 
 * @author shenyb 2015年8月15日 上午11:58:40
 * @ClassName TradeBaseController
 * @Description 提供后台交易接口
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年8月15日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.TRADE)
public class TradeBaseController {
	private Logger logger = Logger.getLogger(TradeBaseController.class);

	@Autowired
	private BopsTradeBaseService bopsTradeBaseService;
	@Autowired
	private TradeBaseDAO tradeBaseDAO;
	@Autowired
	private GoodsMainDAO goodsMainDAO;
	@Autowired
	private UserBaseDAO userBaseDAO;
	@Autowired
	private BopsTradeLogisticsDAO bopsTradeLogisticsDAO;

	@Autowired
	private TradeAddressDAO tradeAddressDAO;
	@Autowired
	private BopsTradePushPullDAO bopsTradePushPullDAO;

	/**
	 * 
	 * @author shenyb 2015年8月15日 下午9:31:56 @Method:
	 *         queryPageByParam @Description: 分页带条件查询 1交易号2交易时间 @param
	 *         param @return @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Message queryPageByParam(TradeBaseParam param) {
		logger.info("in TradeBaseController queryPageByParam param: " + param);
		Message success = Message.success();
		Integer page = param.getPage();
		Integer pageSize = param.getPageSize();
		if (page == null || "".equals(page) || pageSize == null || "".equals(pageSize)) {
			param.setPage(0);
			param.setPageSize(Integer.MAX_VALUE);
			List<TradeBaseVO> list = bopsTradeBaseService.queryPageByParam(param);
			success.addData("list", list);
		} else {
			List<TradeBaseVO> list = bopsTradeBaseService.queryPageByParam(param);
			success.addData("list", list);
			success.addData("page", param);
		}
		return success;
	}

	/**
	 * 
	 * @author shenyb 2015年8月15日 下午9:35:16 @Method:
	 *         getByTradeNo @Description:根据交易号查看交易信息 @param
	 *         tradeNo @return @throws modify By LXD 修改查询参数为用户交易编号userTradeNo
	 */
	@ResponseBody
	@RequestMapping(value = "/{userTradeNo}", method = RequestMethod.GET)
	public Message getByTradeNo(@PathVariable(value = "userTradeNo") String userTradeNo) {
		Message success = Message.success();
		List<TradeVO> list = tradeBaseDAO.getByUserTradeNo(userTradeNo);
		TradeBaseDetailVO vo = new TradeBaseDetailVO();
		if (list != null && list.size() > 0) {
			getTradeInfoVO(list.get(0).getTradeNo(), list, vo);
		}
		success.addData("trade", vo);
		return success;
	}

	/**
	 * 
	 * @author shenyb 2015年8月16日 下午2:20:16 @Method: send @Description:
	 *         获取发货页面信息 @param logistics @param snNoVO @param
	 *         logisticsInfo @return @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/send/{tradeNo}", method = RequestMethod.GET)
	public Message toSendPage(@PathVariable(value = "tradeNo") String tradeNo) {
		Message success = Message.success();
		List<TradeBasePO> list = tradeBaseDAO.getByTradeNo(tradeNo);
		TradeBaseSentVO vo = new TradeBaseSentVO();
		List<TradeBaseDetailGoodsVO> goodsList = new ArrayList<TradeBaseDetailGoodsVO>();
		TradeBaseDetailGoodsVO goods = null;
		GoodsMainPO goodsPO = null;
		UserBase userBase = null;
		Integer sum = 0;
		// 拆单后所有子订单支付时间，交易时间，状态，交易号和渠道都相同，所以只取第一个
		if (list != null & list.size() > 0) {
			TradeBasePO po = list.get(0);
			vo.setPayTime(po.getPayTime());
			vo.setTradeTime(po.getCreateTime());
			vo.setTradeStatus(po.getTradeStatus());
			vo.setTradeNo(po.getTradeNo());
			vo.setPayChannel(PayChannelEnum.getMessage(po.getPayChannel()));
			userBase = userBaseDAO.selectByPrimaryKey(po.getBuyerId());
			if (userBase != null) {
				vo.setMobile(userBase.getMobile());
				vo.setNickName(userBase.getNickName());
				TradeAddressPO address = tradeAddressDAO.selectByPrimaryKey(po.getAddressId());
				if (address != null && StringUtils.isBlank(vo.getAddress())) {
					vo.setAddress(address.getLogisticsValue() + address.getAddress());
				}
			}
		}
		// 每个子订单为一类商品
		for (TradeBasePO po : list) {
			goods = new TradeBaseDetailGoodsVO();
			goodsPO = goodsMainDAO.selectByPrimaryKey(po.getGoodsId());
			if (goodsPO != null) {
				goods.setGoodsName(goodsPO.getGoodsName());
				goods.setGoodsCode(goodsPO.getGoodsCode());
				goods.setGoodsBarcode(goodsPO.getGoodsBarcode());
			}
			goods.setGoodsCount(po.getBuyCount());
			sum += po.getTotalPrice();
			goodsList.add(goods);
		}
		vo.setGoodsList(goodsList);
		vo.setTotalFee(Float.valueOf((float) (sum / 100.0)));
		success.addData("trade", vo);
		return success;
	}

	/**
	 * 
	 * @author shenyb 2015年8月16日 下午2:20:16
	 * @Method: saveSendInfo
	 * @Description: 发货页面保存操作按钮
	 * @param logistics
	 * @param snNoVO
	 * @param logisticsInfo
	 * @return @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/send/{tradeNo}", method = RequestMethod.POST)
	public Message saveSendInfo(@RequestBody BopsTradeLogisticsVO logistics) {
		List<LogisticsInfoJsonVO> LogisticsInfolist = logistics.getLogisticsList();
		List<GoodsSnNoVO> GoodsSnNoList = logistics.getGoodsSnNoList();
		Message checkMessage = checkSendParams(LogisticsInfolist, GoodsSnNoList);
		if (checkMessage.getCode() != Message.SUCCESS) {
			return checkMessage;
		}
		Message result = Message.success();
		// 查询订单
		List<TradeBasePO> tradeList = tradeBaseDAO.getByTradeNo(logistics.getTradeNo());
		// 设置收货地址，用户信息
		if (tradeList != null & tradeList.size() > 0) {
			TradeBasePO po = tradeList.get(0);
			TradeAddressPO address = tradeAddressDAO.selectByPrimaryKey(po.getAddressId());
			if (address != null && logistics.getLogisticsId() == null) {
				logistics.setLogisticsId(address.getLogisticsId());
			}
			if (logistics.getLogisticsId() == null) {
				return Message.error(7006);
			}
			if (logistics.getAddress() == null) {
				return Message.error(7005);
			}
			logistics.setAddressId(po.getAddressId() == null ? "" : po.getAddressId());
			logistics.setUserId(po.getBuyerId());
		}
		// 设置商品sn码
		logistics.setGoodsSnNo(JSONArray.toJSONString(logistics.getGoodsSnNoList()));
		// 设置物流信息保存为json
		logistics.setLogisticsInfo(JSONArray.toJSONString(logistics.getLogisticsList()));
		BopsTradeLogistics po = new BopsTradeLogistics();
		try {
			// org.apache.commons.beanutils.ConversionException: No value
			// specified for 'Date'
			logistics.setCreateTime(Calendar.getInstance().getTime());
			BeanUtils.copyProperties(po, logistics);
			result = bopsTradeBaseService.send(po);
		} catch (ServiceException e) {
			return Message.error(e.getCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @author LXD 2015年8月26日 下午9:19:59 @Method: checkSendParams @Description:
	 *         TODO 校验参数 @param LogisticsInfolist @param
	 *         GoodsSnNoList @return @throws
	 */
	private Message checkSendParams(List<LogisticsInfoJsonVO> LogisticsInfolist, List<GoodsSnNoVO> GoodsSnNoList) {
		if (LogisticsInfolist == null || LogisticsInfolist.size() == 0) {
			return Message.error(7013);
		}
		if (GoodsSnNoList == null || GoodsSnNoList.size() == 0) {
			return Message.error(7014);
		}
		for (LogisticsInfoJsonVO LogisticsInfo : LogisticsInfolist) {
			if (StringUtils.isBlank(LogisticsInfo.getLogisticsNums())
					|| StringUtils.isBlank(LogisticsInfo.getLogisticsServiceId())) {
				return Message.error(7013);
			}

		}
		for (GoodsSnNoVO goodsSnNoVO : GoodsSnNoList) {
			if (StringUtils.isBlank(goodsSnNoVO.getMaterialNo())) {
				return Message.error(7014);
			}

		}
		return Message.success();
	}

	/**
	 * 
	 * @author shenyb 2015年8月18日 下午7:05:44 @Method:
	 *         toRefundPage @Description:进入退款页面
	 * @param request
	 * @param tradeNo
	 * @param refundAmmount
	 * @param memo
	 * @return @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/refund/{tradeNo}", method = RequestMethod.GET)
	public Message toRefundPage(@PathVariable(value = "tradeNo") String tradeNo) {
		Message success = Message.success();
		List<TradeBasePO> list = tradeBaseDAO.getByTradeNo(tradeNo);
		TradeRefundDetailVO vo = new TradeRefundDetailVO();
		List<TradeRefundGoodsVO> goodsList = new ArrayList<TradeRefundGoodsVO>();
		TradeRefundGoodsVO goods = null;
		GoodsMainPO goodsPO = null;
		UserBase userBase = null;
		int sum = 0;
		// 拆单后所有子订单支付时间，交易时间，状态，交易号和渠道都相同，所以只取第一个
		if (list != null & list.size() > 0) {
			TradeBasePO po = list.get(0);
			vo.setPayTime(po.getPayTime());
			vo.setTradeTime(po.getCreateTime());
			vo.setTradeStatus(po.getTradeStatus());
			vo.setTradeNo(po.getTradeNo());
			vo.setPayChannel(PayChannelEnum.getMessage(po.getPayChannel()));
		}
		// 每个子订单为一类商品
		for (TradeBasePO po : list) {
			goods = new TradeRefundGoodsVO();
			goodsPO = goodsMainDAO.selectByPrimaryKey(po.getGoodsId());
			if (goodsPO != null) {
				goods.setGoodsName(goodsPO.getGoodsName());
				goods.setGoodsCode(goodsPO.getGoodsCode());
			}
			userBase = userBaseDAO.selectByPrimaryKey(po.getBuyerId());
			if (userBase != null && !StringUtil.isBlank(userBase.getNickName())) {
				vo.setMobile(userBase.getMobile());
				vo.setNickName(userBase.getNickName());
			}
			goods.setGoodsCount(po.getBuyCount());
			sum += po.getTotalPrice();
			goodsList.add(goods);
		}
		BopsTradeLogistics logisticsPO = bopsTradeLogisticsDAO.selectByPrimaryKey(tradeNo);
		if (logisticsPO != null) {
			vo.setSentTime(logisticsPO.getCreateTime());
		}
		vo.setGoodsList(goodsList);
		vo.setTotalFee(Float.valueOf((float) (sum / 100.0)));
		success.addData("trade", vo);
		return success;
	}

	/**
	 * 
	 * @author shenyb 2015年8月18日 下午7:05:44 @Method: refund @Description:
	 *         退款 @param request @param tradeNo @param refundAmmount @param
	 *         memo @return @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/refund/{tradeNo}", method = RequestMethod.POST)
	public Message refund(HttpServletRequest request, @RequestBody RefundParamVO vo) {
		logger.info(String.format("TradeBaseController refund()in RefundParamVO:", JSON.toJSONString(vo)));
		String refundAmount = vo.getRefundAmount();
		int amount = Integer.valueOf(CurrencyUtil.fromYuanToFen(refundAmount));
		String memo = vo.getMemo();
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		Message result = bopsTradeBaseService.refund(vo.getTradeNo(), amount, memo, user);
		return result;
	}

	private void getTradeVO(String tradeNo, List<TradeBasePO> list, TradeBaseDetailVO vo) {
		List<TradeBaseDetailGoodsVO> goodsList = new ArrayList<TradeBaseDetailGoodsVO>();
		TradeBaseDetailGoodsVO goods = null;
		GoodsMainPO goodsPO = null;
		UserBase userBase = null;
		Integer sum = 0;
		// 拆单后所有子订单支付时间，交易时间，状态，交易号和渠道都相同，所以只取第一个
		if (list != null & list.size() > 0) {
			TradeBasePO po = list.get(0);
			vo.setPayTime(po.getPayTime());
			vo.setTradeTime(po.getCreateTime());
			vo.setTradeStatus(po.getTradeStatus());
			vo.setTradeNo(po.getTradeNo());
			vo.setPayChannel(PayChannelEnum.getMessage(po.getPayChannel()));
			userBase = userBaseDAO.selectByPrimaryKey(po.getBuyerId());
			if (userBase != null && !StringUtil.isBlank(userBase.getNickName())) {
				vo.setMobile(userBase.getMobile());
				vo.setNickName(userBase.getNickName());
			}
		}
		// 每个子订单为一类商品
		for (TradeBasePO po : list) {
			goods = new TradeBaseDetailGoodsVO();
			goodsPO = goodsMainDAO.selectByPrimaryKey(po.getGoodsId());
			if (goodsPO != null) {
				goods.setGoodsName(goodsPO.getGoodsName());
				goods.setGoodsCode(goodsPO.getGoodsCode());
			}
			goods.setGoodsCount(po.getBuyCount());
			sum += po.getTotalPrice();
			goodsList.add(goods);
		}
		BopsTradeLogistics logisticsPO = bopsTradeLogisticsDAO.selectByPrimaryKey(tradeNo);
		if (logisticsPO != null) {
			vo.setSentTime(logisticsPO.getCreateTime());
		}
		vo.setGoodsList(goodsList);
		vo.setTotalFee(Float.valueOf((float) (sum / 100.0)));
	}

	/**
	 * @author xiehao 2015年9月23日 上午11:32:01
	 * @Method: getTradeInfoVO
	 * @Description: TODO 组装前端的数据
	 * @param tradeNo
	 * @param list
	 * @param vo
	 */
	private void getTradeInfoVO(String tradeNo, List<TradeVO> list, TradeBaseDetailVO vo) {
		List<TradeBaseDetailGoodsVO> goodsList = new ArrayList<TradeBaseDetailGoodsVO>();
		TradeBaseDetailGoodsVO goods = null;
		GoodsMainPO goodsPO = null;
		UserBase userBase = null;
		Integer sum = 0;
		// 拆单后所有子订单支付时间，交易时间，状态，交易号和渠道都相同，所以只取第一个
		if (list != null & list.size() > 0) {
			TradeVO po = list.get(0);
			vo.setPayTime(po.getPayTime());
			vo.setTradeTime(po.getCreateTime());
			vo.setTradeStatus(po.getTradeStatus());
			vo.setTradeNo(po.getTradeNo());
			vo.setUserTradeNo(po.getUserTradeNo());
			vo.setReceiver(po.getReceiver());
			/**
			 * 隐藏身份证后六位，身份证没有区分15位和18位 mofify By lxd 20150923
			 * 使用StringUtils.isNotBlank判断非空，防止客户端传空字符串导致数组截取越界
			 */
			if (StringUtils.isNotBlank(po.getCertificationCard())) {
				vo.setCertificationCard(po.getCertificationCard().substring(0, 12) + "******");
			}
			vo.setTelephone(po.getTelephone());
			vo.setAddress(po.getAddress());
			vo.setLogisticsValue(po.getLogisticsValue());
			vo.setYunfei(po.getYunfei());
			vo.setYouhuiquan(po.getYouhuiquan());
			vo.setPayPrice(po.getPayPrice());
			/**
			 * 按照海关的要求拼装数据格式
			 */
			vo.setYouhuiquanDescription("满" + po.getMinCost() + "(元)可用" + po.getYouhuiquan() + "(元)");
			vo.setPayChannel(PayChannelEnum.getMessage(po.getPayChannel()));
			userBase = userBaseDAO.selectByPrimaryKey(po.getBuyerId());
			if (userBase != null && !StringUtil.isBlank(userBase.getNickName())) {
				vo.setMobile(userBase.getMobile());
				vo.setNickName(userBase.getNickName());
			}
		}
		// 每个子订单为一类商品
		for (TradeVO po : list) {
			goods = new TradeBaseDetailGoodsVO();
			goodsPO = goodsMainDAO.selectByPrimaryKey(po.getGoodsId());
			if (goodsPO != null) {
				goods.setGoodsName(goodsPO.getGoodsName());
				goods.setGoodsCode(goodsPO.getGoodsCode());
			}
			goods.setGoodsCount(po.getBuyCount());
			sum += po.getTotalPrice();
			goodsList.add(goods);
		}
		BopsTradeLogistics logisticsPO = bopsTradeLogisticsDAO.selectByPrimaryKey(tradeNo);
		if (logisticsPO != null) {
			vo.setSentTime(logisticsPO.getCreateTime());
		}
		vo.setGoodsList(goodsList);
		vo.setTotalFee(Float.valueOf((float) (sum / 100.0)));
	}

	/**
	 * 
	 * @author LXD 2015年8月24日 下午4:27:39 @Method: exportTradeExcel @Description:
	 *         TODO @param param @param response @throws 订单导出
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/export")
	public void exportTradeExcel(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) String tradeStatus, @RequestParam(required = false) String tradeTimeStart,
			@RequestParam(required = false) String tradeTimeEnd, @RequestParam(required = false) Integer pageSize,
			HttpServletResponse response) {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		TradeBaseParam param = new TradeBaseParam();
		if (StringUtils.isNotBlank(tradeStatus)) {
			param.setTradeStatus(getString(tradeStatus));
		}
		param.setTradeTimeStart(tradeTimeStart);
		param.setTradeTimeEnd(tradeTimeEnd);
		param.setPage(1);
		param.setPageSize(pageSize);
		if (pageSize == null) {
			param.setPageSize(Integer.MAX_VALUE);
		}
		logger.info(String.format("TradeBaseController exportTradeExcel params:　%s", param.toString().toString()));
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=" + System.currentTimeMillis() + ".xls");
			response.setContentType("application /  vnd.ms-excel; charset=utf-8");

			HSSFWorkbook workbook = bopsTradeBaseService.exportTrade(param);
			try {
				workbook.write(os);
			} catch (IOException e) {
				logger.error(String.format("TradeBaseController exportTradeExcel write error", e.toString()));
				e.printStackTrace();
			}

			os.flush();
		} catch (Exception e) {
			logger.error(String.format("TradeBaseController exportTradeExcel exportTrade error", e.toString()));
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					logger.error(String.format("TradeBaseController exportTradeExcel os close error", e.toString()));
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * @author xiehao 2015年9月16日 上午10:10:46 @Method:
	 *         exportOrderExcel @Description: TODO 明细导出 @param
	 *         orderParamsVO @param response @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/exportOrder")
	public void exportOrderExcel(TradeOrderParamsVO orderParamsVO, HttpServletResponse response) {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		if (orderParamsVO.getPageSize() == null) {
			orderParamsVO.setPageSize(Integer.MAX_VALUE);
		}
		logger.info(
				String.format("TradeBaseController exportTradeExcel params:　%s", orderParamsVO.toString().toString()));
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=" + System.currentTimeMillis() + ".xls");
			response.setContentType("application /  vnd.ms-excel; charset=utf-8");
			orderParamsVO.setTradeStatus(getString(orderParamsVO.getTradeStatus()));
			HSSFWorkbook workbook = bopsTradeBaseService.exportOrder(orderParamsVO);
			try {
				workbook.write(os);
			} catch (IOException e) {
				logger.error(String.format("TradeBaseController exportTradeExcel write error", e.toString()));
				e.printStackTrace();
			}

			os.flush();
		} catch (Exception e) {
			logger.error(String.format("TradeBaseController exportTradeExcel exportTrade error", e.toString()));
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					logger.error(String.format("TradeBaseController exportTradeExcel os close error", e.toString()));
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * @author xiehao 2015年9月16日 上午10:35:10 @Method:
	 *         exportOrderExcel_V1_1 @Description: TODO 导出明细V1.1 @param
	 *         orderParamsVO @param response @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/exportOrder_v1_1")
	public void exportOrderExcel_V1_1(TradeOrderParamsVO orderParamsVO, HttpServletResponse response) {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		if (orderParamsVO.getPageSize().intValue() != 100) {
			orderParamsVO.setPageSize(Integer.MAX_VALUE);
		}
		logger.info(
				String.format("TradeBaseController exportTradeExcel params:　%s", orderParamsVO.toString().toString()));
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=" + System.currentTimeMillis() + ".xls");
			response.setContentType("application /  vnd.ms-excel; charset=utf-8");
			orderParamsVO.setTradeStatus(getString(orderParamsVO.getTradeStatus()));
			HSSFWorkbook workbook = bopsTradeBaseService.exportOrder_v1_1(orderParamsVO);
			try {
				workbook.write(os);
			} catch (IOException e) {
				logger.error(String.format("TradeBaseController exportTradeExcel write error", e.toString()));
				e.printStackTrace();
			}

			os.flush();
		} catch (Exception e) {
			logger.error(String.format("TradeBaseController exportTradeExcel exportTrade error", e.toString()));
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					logger.error(String.format("TradeBaseController exportTradeExcel os close error", e.toString()));
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * @throws ParseException
	 * @author xiehao 2015年9月16日 上午10:35:10 @Method:
	 *         exportOrderExcel_V1_1 @Description: TODO 导出海关订单V1.1 @param
	 *         orderParamsVO @param response @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/exportTradeOrder_v1_1")
	public void exportTradeExcel_V1_1(OrderExportParamVO orderParamsVO, HttpServletResponse response)
			throws ParseException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		if (orderParamsVO.getPage() == null) {
			orderParamsVO.setPage(1);
		}
		if (orderParamsVO.getPageSize().intValue() != 100) {
			orderParamsVO.setPageSize(Integer.MAX_VALUE);
		}

		logger.info(String.format("TradeBaseController exportTradeExcel_V1_1 params:　%s",
				orderParamsVO.toString().toString()));
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			response.reset();
			if (orderParamsVO.getTradeTimeEnd() != null && orderParamsVO.getTradeTimeStart() != null) {
				response.setHeader("Content-Disposition",
						"attachment; filename=" + sdf2.format(sdf1.parse(orderParamsVO.getTradeTimeStart())) + "-"
								+ sdf2.format(sdf1.parse(orderParamsVO.getTradeTimeEnd())) + "_zhengzhou.xls");
			} else {
				response.setHeader("Content-Disposition",
						"attachment; filename=" + System.currentTimeMillis() + "_zhengzhou.xls");
			}
			response.setContentType("application / vnd.ms-excel; charset=utf-8");
			HSSFWorkbook workbook = bopsTradeBaseService.exportTradeOrder_v1_1(orderParamsVO);
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

	private String getString(String tradeStatus) {
		String[] status = tradeStatus.split(",");
		String statusResult = JSON.toJSONString(status);
		String tradeStatusString = statusResult.replace("[", "").replace("]", "");
		return tradeStatusString;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/queryBatchNoList")
	public Message queryTradeBatchNoInfoList(TradeBatchNoParamsVO param) {
		Message success = Message.success();

		Integer page = param.getPage();
		Integer pageSize = param.getPageSize();
		if (page == null || "".equals(page) || pageSize == null || "".equals(pageSize)) {
			List<BopsTradePushPull> list = bopsTradePushPullDAO.queryBatchInfoList(param);
			success.addData("list", list);
		} else {
			PageHelper.startPage(page, pageSize);
			Page<BopsTradePushPull> list = (Page<BopsTradePushPull>) bopsTradePushPullDAO.queryBatchInfoList(param);
			success.addData("list", list);
			success.addData("page", param);
		}
		return success;
	}

}