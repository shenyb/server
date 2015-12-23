package com.need.customer.web.controller.trade;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.need.biz.utils.CurrencyUtil;
import com.need.customer.constant.ControllerMappings;
import com.need.customer.constant.ViewMappings;
import com.need.customer.pub.ConstantsProConfig;
import com.need.dao.api.goods.GoodsMainDAO;
import com.need.dao.api.trade.TradeAddressDAO;
import com.need.dao.api.trade.TradeBaseDAO;
import com.need.dao.api.trade.TradeRetrieveStatusRecordDAO;
import com.need.dao.api.user.UserBaseDAO;
import com.need.dao.bops.trade.BopsTradeLogisticsDAO;
import com.need.dao.bops.trade.BopsTradePushPullDAO;
import com.need.domain.common.enums.PayChannelEnum;
import com.need.domain.common.enums.ServiceProviderEnum;
import com.need.domain.common.enums.TrackingCodeEnum;
import com.need.domain.po.api.goods.GoodsMainPO;
import com.need.domain.po.api.trade.TradeAddressPO;
import com.need.domain.po.api.trade.TradeBasePO;
import com.need.domain.po.api.user.UserBase;
import com.need.domain.po.bops.trade.BopsTradeLogistics;
import com.need.domain.po.bops.trade.BopsTradePushPull;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.vo.trade.BatchSendTradeParamVO;
import com.need.domain.vo.trade.BopsTradeLogisticsVO;
import com.need.domain.vo.trade.CallcenterPageVO;
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
import com.need.domain.vo.trade.TradeRetrieveStatusRecordResultVO;
import com.need.domain.vo.trade.TradeVO;
import com.need.service.bops.trade.BopsTradeBaseService;
import com.need.service.common.exception.ServiceException;
import com.need.service.constant.Constants;
import com.need.trade.enums.TradeStatus;
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
	@Autowired
	private TradeBaseDAO portalTradeBaseDAO;
	@Autowired
	private TradeRetrieveStatusRecordDAO tradeRetrieveStatusRecordDAO;
	@Autowired
	private ConstantsProConfig constantsProConfig;
	/**
	 * 
	 * @author shenyb 2015年8月15日 下午9:31:56 @Method:
	 *         queryPageByParam @Description: 分页带条件查询 1交易号2交易时间 @param
	 *         param @return @throws
	 */

	@RequestMapping(value = "/callcenter/page", method = RequestMethod.GET)
	public String callcenterqueryTrade(TradeBaseParam param,Model model) {
		logger.info("in TradeBaseController queryPageByParam param: " + param);
		   
			List<TradeBaseVO> list = bopsTradeBaseService.queryPageByParamForCallCenter(param);
			model.addAttribute("list", list);
			model.addAttribute("page", param);
		    model.addAttribute("mobile", param.getOriginCallNo());
		return ViewMappings.CALLCENTER_TRADE_LIST;
	}
	/**
	 * 
	 * @author peiboli 2015年11月6日 下午5:50:28
	 * @Method: callcenterqueryTrade 
	 * @Description: TODO弹屏页面,定位到用户的情况
	 * @param param
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/callcenter/screen", method = RequestMethod.GET)
	public String callcenterLocation(CallcenterPageVO param,Model model) {
		logger.info("in TradeBaseController queryPageByParam param: " + param);
			List<TradeBaseVO> list = bopsTradeBaseService.queryPageCallCenterByMobile(param);
			model.addAttribute("list", list);
			model.addAttribute("page", param);
		    model.addAttribute("mobile", param.getOriginCallNo());
		return ViewMappings.CALLCENTER_SCREEN_TRADE_LIST;
	}
//	/**
//	 * 
//	 * @author peiboli 2015年11月7日 下午2:43:51
//	 * @Method: callcenterqueryTrade 
//	 * @Description: TODO未定位到用户的情况，弹屏
//	 * @param param
//	 * @param model
//	 * @return 
//	 * @throws
//	 */
//	@RequestMapping(value = "/callcenter/notLocation", method = RequestMethod.GET)
//	public String callcenterNotLocation(CallcenterPageVO param,Model model) {
//		logger.info("in TradeBaseController queryPageByParam param: " + param);
//		    if(!StringUtils.isEmpty(param.getCustNo())){
//		    	param.setOriginCallNo(param.getCustNo());
//		    }
//			List<TradeBaseVO> list = bopsTradeBaseService.queryPageCallCenterByMobile(param);
//			model.addAttribute("list", list);
//			model.addAttribute("page", param);
//		    model.addAttribute("mobile", param.getOriginCallNo());
//		return ViewMappings.CALLCENTER_TRADE_LIST;
//	}
//	
//	
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public String queryPageByParam(TradeBaseParam param,Model model) {
		logger.info("in TradeBaseController queryPageByParam param: " + param);
		Integer page = param.getPage();
		Integer pageSize = param.getPageSize();
		if (page == null || "".equals(page) || pageSize == null || "".equals(pageSize)) {
			param.setPage(0);
			param.setPageSize(Integer.MAX_VALUE);
			List<TradeBaseVO> list = bopsTradeBaseService.queryPageByParam(param);
			model.addAttribute("list", list);
		} else {
			List<TradeBaseVO> list = bopsTradeBaseService.queryPageByParam(param);
			model.addAttribute("list", list);
			model.addAttribute("page", param);
		}
		// return success;
		return ViewMappings.TRADE_LIST;
	}
	
	@ResponseBody
	@RequestMapping(value = "/page/search", method = RequestMethod.GET)
	public Message searchPageByParam(TradeBaseParam param) {
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
	@RequestMapping(value = "/{userTradeNo}", method = RequestMethod.GET)
	public String getByTradeNo(@PathVariable(value = "userTradeNo") String userTradeNo, Model model) {
		logger.info("getByTradeNo in ..userTradeNo: "+userTradeNo);
		List<TradeVO> list = tradeBaseDAO.getByUserTradeNo(userTradeNo);
		logger.info("getByTradeNo in ..list: "+list);
		TradeBaseDetailVO vo = new TradeBaseDetailVO();
		if (list != null && list.size() > 0) {
			getTradeInfoVO(list.get(0).getTradeNo(), list, vo);
		}
		model.addAttribute("trade", vo);
		// modify by liuhongyang 20151028 增加交易详情页面流转信息
		List<TradeRetrieveStatusRecordResultVO> tradeStatusList = tradeRetrieveStatusRecordDAO.selectByUserTradeNo(userTradeNo);
		if (tradeStatusList != null && tradeStatusList.size() > 0) {
			for(TradeRetrieveStatusRecordResultVO record : tradeStatusList){
				record.setTrackingStatus(TrackingCodeEnum.getDesc(StringUtils.isBlank(record.getTrackingCode()) ? "" : record.getTrackingCode()));
			}
			
			model.addAttribute("tradeStatusList", tradeStatusList);
		}
		model.addAttribute("picAddress", constantsProConfig.getPic_domain());
		return ViewMappings.TRADE_DETAIL;
	}
	/**
	 * 
	 * @author peiboli 2015年10月19日 下午7:47:30
	 * @Method: getByTradeNo 
	 * @Description: TODO弹屏
	 * @param userTradeNo
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/callcenter/{userTradeNo}", method = RequestMethod.GET)
	public String getByTradeNoCallcenter(@PathVariable(value = "userTradeNo") String userTradeNo, Model model) {
		logger.info("getByTradeNo in ..userTradeNo: "+userTradeNo);
		List<TradeVO> list = tradeBaseDAO.getByUserTradeNo(userTradeNo);
		logger.info("getByTradeNo in ..list: "+list);
		TradeBaseDetailVO vo = new TradeBaseDetailVO();
		if (list != null && list.size() > 0) {
			getTradeInfoVO(list.get(0).getTradeNo(), list, vo);
		}
		model.addAttribute("trade", vo);
		// modify by liuhongyang 20151028 增加交易详情页面流转信息
		List<TradeRetrieveStatusRecordResultVO> tradeStatusList = tradeRetrieveStatusRecordDAO.selectByUserTradeNo(userTradeNo);
		if (tradeStatusList != null && tradeStatusList.size() > 0) {
			for(TradeRetrieveStatusRecordResultVO record : tradeStatusList){
				record.setTrackingStatus(TrackingCodeEnum.getDesc(StringUtils.isBlank(record.getTrackingCode()) ? "" : record.getTrackingCode()));
			}
			
			model.addAttribute("tradeStatusList", tradeStatusList);
		}
		model.addAttribute("picAddress", constantsProConfig.getPic_domain());
		return ViewMappings.CALLCENTER_TRADE_DETAIL;
	}

	/**
	 * 
	 * @author shenyb 2015年8月16日 下午2:20:16 @Method: send @Description:
	 *         获取发货页面信息 @param logistics @param snNoVO @param
	 *         logisticsInfo @return @throws
	 */
	@RequestMapping(value = "/send/{tradeNo}", method = RequestMethod.GET)
	public ModelAndView toSendPage(@PathVariable(value = "tradeNo") String tradeNo, ModelAndView mv) {
		mv = new ModelAndView("/trade/send");
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
		mv.addObject("trade", vo);
		return mv;
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
	public Message saveSendInfo(BopsTradeLogisticsVO logistics, @RequestParam(required = true) String[] goodsSnNo,
			/* @RequestParam(required = true) String[] materialNo, */
			@RequestParam(required = true) String[] logisticsServiceId,
			@RequestParam(required = true) String[] logisticsNums) {

		List<LogisticsInfoJsonVO> logisticsInfolist = new ArrayList<LogisticsInfoJsonVO>();
		List<GoodsSnNoVO> goodsSnNoList = new ArrayList<GoodsSnNoVO>();
		/*
		 * if (materialNo == null | materialNo.length == 0) { return
		 * Message.error(7014); }
		 */
		if (logisticsNums == null | logisticsNums.length == 0) {
			return Message.error(7013);
		}
		/*
		 * for (int i = 0; i < logisticsServiceId.length; i++) {
		 * goodsSnNoList.add(new GoodsSnNoVO(goodsSnNo[i], materialNo[i])); }
		 */
		for (int i = 0; i < logisticsServiceId.length; i++) {
			logisticsInfolist.add(new LogisticsInfoJsonVO(logisticsServiceId[i], logisticsNums[i]));
		}
		Message checkMessage = checkSendParams(logisticsInfolist, goodsSnNoList);
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
			logistics.setAddressId(po.getAddressId() == null ? "" : po.getAddressId());
			logistics.setUserId(po.getBuyerId());
			logistics.setAddress(po.getAddress());
		}
		// 设置商品sn码
		logistics.setGoodsSnNo(JSONArray.toJSONString(goodsSnNoList));
		// 设置物流信息保存为json
		logistics.setLogisticsInfo(JSONArray.toJSONString(logisticsInfolist));
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

	@RequestMapping(value = "/send/batch/tradeList", method = RequestMethod.POST)
	public String batchSaveSendInfo(@RequestParam("files") MultipartFile[] files, HttpServletRequest request,
			Model model) throws Exception {
		List<BatchSendTradeParamVO> batchSendTradeList = null;
		List<BatchSendTradeParamVO> batchSendTradeListSuccess = new ArrayList<BatchSendTradeParamVO>();
		List<BatchSendTradeParamVO> batchSendTradeListFail = new ArrayList<BatchSendTradeParamVO>();
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				InputStream in = file.getInputStream();
				Boolean is07or10 = file.getOriginalFilename().endsWith("xlsx");
				batchSendTradeList = bopsTradeBaseService.excelToList(in, is07or10);
				for (BatchSendTradeParamVO batchSendTrade : batchSendTradeList) {
					String userTradeNo = batchSendTrade.getOrderNo();
					if (!hasTextOfBatchSendTradeList(batchSendTrade)) {
						batchSendTrade.setMessage("列表中有空白字段");
						batchSendTradeListFail.add(batchSendTrade);
						continue;
					}
					if (!isExistServiceProvider(batchSendTrade)) {
						batchSendTrade.setMessage("快递公司不存在");
						batchSendTradeListFail.add(batchSendTrade);
						continue;
					}
					List<TradeVO> list = tradeBaseDAO.getByUserTradeNo(userTradeNo);
					if (list == null || list.size() == 0) {
						logger.error(String.format("batchSend userTradeNo:%s not exists", userTradeNo));
						batchSendTrade.setMessage("此订单不存在");
						batchSendTradeListFail.add(batchSendTrade);
						continue;
					}
					TradeVO tradePO = list.get(0);
					Message success = batchSend(batchSendTrade, tradePO);
					if (success.getCode() != Message.SUCCESS) {
						batchSendTrade.setMessage(success.getMsg());
						batchSendTradeListFail.add(batchSendTrade);
					} else {
						batchSendTrade.setMessage("发货成功");
						batchSendTradeListSuccess.add(batchSendTrade);
					}
				}
			} else {
				return ViewMappings.BATCH_SEND_TABLE_CONTENT;
			}
		}
		model.addAttribute("listSuccess", batchSendTradeListSuccess);
		model.addAttribute("listFail", batchSendTradeListFail);
		return ViewMappings.BATCH_SEND_TABLE_CONTENT;
	}

	/**
	 * 
	 * @author xiehao 2015年11月6日 下午6:47:22
	 * @Method: hasTextOfBatchSendTradeList
	 * @Description: TODO 检查发货的EXCEL中是否有空字段
	 * @param batchSendTrade
	 */
	private boolean hasTextOfBatchSendTradeList(BatchSendTradeParamVO batchSendTrade) {
		if (!org.springframework.util.StringUtils.hasText(batchSendTrade.getLogisticsNo()))
			return false;
		if (!org.springframework.util.StringUtils.hasText(batchSendTrade.getOrderNo()))
			return false;
		if (!org.springframework.util.StringUtils.hasText(batchSendTrade.getServiceProvider()))
			return false;
		return true;
	}

	/**
	 * 
	 * @author xiehao 2015年11月6日 下午6:47:54
	 * @Method: isExistServiceProvider
	 * @Description: TODO 检查快递公司Id是否存在，因为目前只有1,2,3
	 * @param batchSendTrade
	 */
	private boolean isExistServiceProvider(BatchSendTradeParamVO batchSendTrade) {
		if ("1".equals(batchSendTrade.getServiceProvider()))
			return true;
		if ("2".equals(batchSendTrade.getServiceProvider()))
			return true;
		if ("3".equals(batchSendTrade.getServiceProvider()))
			return true;
		return false;
	}

	/**
	 * 
	 * @author xiehao 2015年11月6日 下午6:49:01
	 * @Method: batchSend
	 * @Description: TODO 发货
	 * @param batchSendTrade
	 * @param tradePO
	 */
	private Message batchSend(BatchSendTradeParamVO batchSendTrade, TradeVO tradePO) {
		Message message = Message.success();
		BopsTradeLogistics po = new BopsTradeLogistics();
		TradeAddressPO address = tradeAddressDAO.selectByPrimaryKey(tradePO.getAddressId());
		GoodsMainPO goods = goodsMainDAO.selectByPrimaryKey(tradePO.getGoodsId());
		LogisticsInfoJsonVO logisticsInfoVO = new LogisticsInfoJsonVO();
		logisticsInfoVO.setLogisticsServiceId(batchSendTrade.getServiceProvider());
		logisticsInfoVO.setLogisticsNums(batchSendTrade.getLogisticsNo());

		if (address == null) {
			return Message.error(7018);
		}
		if (goods == null) {
			return Message.error(7019);
		}
		po.setAddress(address.getAddress());
		po.setAddressId(address.getAddressId());
		po.setCreateTime(Calendar.getInstance().getTime());
		po.setGoodsSnNo(goods.getGoodsBarcode());
		po.setLogisticsInfo(JSON.toJSONString(logisticsInfoVO));
		po.setLogisticsId(address.getLogisticsId());
		po.setUserId(tradePO.getBuyerId());
		po.setTradeNo(tradePO.getTradeNo());
		message = bopsTradeBaseService.send(po);
		return message;
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
		/*
		 * if (GoodsSnNoList == null || GoodsSnNoList.size() == 0) { return
		 * Message.error(7014); }
		 */
		for (LogisticsInfoJsonVO LogisticsInfo : LogisticsInfolist) {
			if (StringUtils.isBlank(LogisticsInfo.getLogisticsNums())
					|| StringUtils.isBlank(LogisticsInfo.getLogisticsServiceId())) {
				return Message.error(7013);
			}

		}
		/*
		 * for (GoodsSnNoVO goodsSnNoVO : GoodsSnNoList) { if
		 * (StringUtils.isBlank(goodsSnNoVO.getMaterialNo())) { return
		 * Message.error(7014); }
		 * 
		 * }
		 */
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
	@RequestMapping(value = "/refund/{tradeNo}", method = RequestMethod.GET)
	public ModelAndView toRefundPage(@PathVariable(value = "tradeNo") String tradeNo, ModelAndView mv) {
		mv = new ModelAndView("/trade/refund");
		// Message success = Message.success();
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
			vo.setTradeStatus(TradeStatus.getDesc(po.getTradeStatus()));
			vo.setTradeNo(po.getTradeNo());
			vo.setPayChannel(PayChannelEnum.getMessage(po.getPayChannel()));
		}
		// 每个子订单为一类商品
		int goodsTotalCount = 0;
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
			goodsTotalCount += po.getBuyCount();
			goodsList.add(goods);
		}
		BopsTradeLogistics logisticsPO = bopsTradeLogisticsDAO.selectByPrimaryKey(tradeNo);
		if (logisticsPO != null) {
			vo.setSentTime(logisticsPO.getCreateTime());
		}
		vo.setGoodsList(goodsList);
		vo.setTotalFee(Float.valueOf((float) (sum / 100.0)));
		vo.setGoodsTotalCount(goodsTotalCount);
		// success.addData("trade", vo);
		mv.addObject("trade", vo);
		return mv;
	}

	/**
	 * 
	 * @author shenyb 2015年8月18日 下午7:05:44 @Method: refund @Description:
	 *         退款 @param request @param tradeNo @param refundAmmount @param memo
	 */
	@ResponseBody
	@RequestMapping(value = "/refund", method = RequestMethod.POST)
	public Message refund(HttpServletRequest request, RefundParamVO vo) {
		logger.info(String.format("TradeBaseController refund()in RefundParamVO:", JSON.toJSONString(vo)));
		String refundAmount = vo.getRefundAmount();
		if (StringUtil.isBlank(refundAmount) || "0".equals(refundAmount)) {
			return Message.error(7017);
		}
		int amount = Integer.valueOf(CurrencyUtil.fromYuanToFen(refundAmount));
		String memo = vo.getMemo();
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		if (user == null) {
			return Message.error(7016);
		}
		Message result = bopsTradeBaseService.refund(vo.getTradeNo(), amount, memo, user);
		return result;
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
		BopsTradeLogistics logisticsPO = bopsTradeLogisticsDAO.selectByPrimaryKey(tradeNo);
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
			vo.setCertificationPositiveKey(po.getCertificationPositiveKey());
			vo.setCertificationNegativeKey(po.getCertificationNegativeKey());
			String logisticsNo = "";
			String serviceProvider = "";
			/**
			 * 快递单号 快递公司 TODO bug
			 */
			// if(null != logisticsPO){
			// JSONObject tradeLogistics = JSONObject
			// .parseObject(logisticsPO.getLogisticsInfo());
			// vo.setLogisticsNo(tradeLogistics.getString("logisticsNums"));
			// vo.setServiceProvider(tradeLogistics.getString("logisticsServiceId"));
			// }
			if (null != logisticsPO) {
				try {
					JSONObject tradeLogistics = JSONObject.parseObject(logisticsPO.getLogisticsInfo());
					logisticsNo = tradeLogistics.getString("logisticsNums");
					serviceProvider = tradeLogistics.getString("logisticsServiceId");
				} catch (Exception e) {
					try {
						List<LogisticsInfoJsonVO> array = JSON.parseArray(logisticsPO.getLogisticsInfo(),
								LogisticsInfoJsonVO.class);
						if (array != null && array.size() > 0) {
							logisticsNo = array.get(0).getLogisticsNums();
							serviceProvider = array.get(0).getLogisticsServiceId();
						}
					} catch (Exception e1) {
						logger.error(String.format("json parse error :%s, logisticsPO:%s",e1.getMessage(), JSON.toJSON(logisticsPO)));
					}
				}
			}
			vo.setLogisticsNo(logisticsNo);
			if(!StringUtil.isBlank(serviceProvider)&&serviceProvider.matches("\\d+")){
				vo.setServiceProvider(ServiceProviderEnum.getDesc(serviceProvider));
			}else{
				vo.setServiceProvider(serviceProvider);
			}
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
				goods.setGoodsBarcode(goodsPO.getGoodsBarcode());
			}
			goods.setGoodsCount(po.getBuyCount());
			sum += po.getTotalPrice();
			goodsList.add(goods);
		}

		if (logisticsPO != null) {
			vo.setSentTime(logisticsPO.getCreateTime());
		}
		vo.setGoodsList(goodsList);
		vo.setTotalFee(Float.valueOf((float) (sum / 100.0)));
		// modify by liuhongyang20151102 商品总价修改
		Double yunfei = list.get(0).getYunfei();
		Double duepay = Double.parseDouble(sum.toString()) / 100 + yunfei;
		vo.setDuepay(duepay.toString());
		Integer payPrice = ((Double)(vo.getPayPrice() * 100)).intValue();
		Integer privilege = ((Double)(duepay * 100)).intValue() - payPrice;
		vo.setPrivilege(((Number)privilege).doubleValue() / 100);
		vo.setIsCheap(list.get(0).getIsCheap());
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
	 *         exportOrderExcel_V1_1 @Description: TODO 导出北京仓&香港订单 @param
	 *         orderParamsVO @param response @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/exportOrder_v1_1")
	public void exportOrderExcel_V1_1(TradeOrderParamsVO orderParamsVO, HttpServletResponse response) {
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
	 *         exportOrderExcel_V1_1 @Description: TODO 导出郑州仓订单V1.1 @param
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
		if (orderParamsVO.getPageSize() == null) {
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
			if (org.springframework.util.StringUtils.hasText(orderParamsVO.getTradeTimeEnd().trim())
					&& org.springframework.util.StringUtils.hasText(orderParamsVO.getTradeTimeStart().trim())) {
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

	/**
	 * 下载订单批量发货模板
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/exportTradeBatchTemplate")
	public void exportTradeBatchTemplate(HttpServletResponse response) throws ParseException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");

		logger.info("TradeBaseController exportTradeBatchTemplate");
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			response.reset();
			String fileName = "订单批量发货模板";
			fileName = URLEncoder.encode(fileName, "UTF8");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
			response.setContentType("application / vnd.ms-excel; charset=utf-8");
			XSSFWorkbook workbook = bopsTradeBaseService.exportTradeBatchTemplate();
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

	/**
	 * 
	 * @author xiehao 2015年11月9日 下午12:14:59
	 * @Method: toTradeExportPage
	 * @Description: 进入订单导出页面
	 * @param model
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/toExportPage")
	public String toTradeExportPage(Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(Calendar.getInstance().getTime());
		model.addAttribute("tradeTimeStart", date + " 00:00:00");
		model.addAttribute("tradeTimeEnd", date + " 23:59:59");
		return ViewMappings.TRADE_EXPORT_PAGE;
	}

	/**
	 * 
	 * @author xiehao 2015年11月9日 下午12:14:36
	 * @Method: toBatchSendPage
	 * @Description: 进入批量发货页面
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/toBatchPage")
	public String toBatchSendPage() {

		return ViewMappings.BATCH_TRADE_SEND;
	}
}