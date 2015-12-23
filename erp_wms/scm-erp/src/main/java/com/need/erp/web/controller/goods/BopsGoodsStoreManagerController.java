package com.need.erp.web.controller.goods;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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

import com.need.common.validate.ValidatorUtil;
import com.need.domain.common.constant.ERPBizReturnCode;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.vo.goods.GoodsStorePageVO;
import com.need.domain.vo.goods.GoodsStoreResultVO;
import com.need.erp.constant.ControllerMappings;
import com.need.erp.constant.ViewMappings;
import com.need.erp.pub.ConstantsProConfig;
import com.need.service.bops.goods.BopsGoodsStoreService;
import com.need.service.constant.BizReturnCode;
import com.need.service.constant.Constants;
import com.need.utils.NumberUtil;

@Controller
@RequestMapping(ControllerMappings.GOODS_STORE_MANGER)
public class BopsGoodsStoreManagerController {

	private static final Logger logger = Logger.getLogger(BopsGoodsStoreManagerController.class);

	@Autowired
	private BopsGoodsStoreService bopsGoodsStoreService;
	
	@Autowired
	private ConstantsProConfig constantsProConfig;
//	@Autowired
//	private BopsGoodsService bopsGoodsService;
//	@Autowired
//	private GoodsMainDAO goodsMainDAO;

	/**
	 * 
	 * @author xiehao 2015年8月18日 下午7:18:45 @Method:
	 *         addBopsGoodsStoreManager @Description: TODO增加库存记录 @param
	 *         store @return @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/addNewStore")
	public Message addBopsGoodsStoreManager(GoodsStorePageVO store, 
			HttpServletRequest request) {
		logger.info("in store addBopsGoodsStoreManager store: " + store);

		Message message = Message.success();

		Set<ConstraintViolation<GoodsStorePageVO>> result = ValidatorUtil.getInstance().validate(store);
		if (result.size() > 0) {
			for (ConstraintViolation<?> c : result) {
				return Message.error(BizReturnCode.FIELD_VALIDATE_ERR, c.getMessage());
			}
		}

		/**
		 * 添加库存数不能为空
		 */
		if (store.getNowStoreCount() == null) {
			Message error = Message.error(ERPBizReturnCode.GOODS_3010);
			return error;
		}
		/**
		 * 添加库存不允许小于等于0
		 */
//		if (store.getNowStoreCount() <= 0) {
//			Message error = Message.error(3019);
//			return error;
//		}
		/**
		 * 库存数量只能为正整数 
		 */
		if(!NumberUtil.isInteger(String.valueOf(store.getNowStoreCount()))){
			return Message.error(ERPBizReturnCode.GOODS_3021);
		}
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		store.setAuthorId(user.getUserId());
		bopsGoodsStoreService.insertNewStore(store);// 添加一条新的库存记录
//		message.addData("object", store);
		logger.info("out store addBopsGoodsStoreManager store: " + store);
		return message;
	}

	/**
	 * 
	 * @author xiehao 2015年8月18日 下午7:19:02 @Method: addStore @Description:
	 *         TODO @param goodsId @return @throws 此方法没有用
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/addStore/{goodsId}")
	public Message addStore(@PathVariable String goodsId) {
		logger.info("in store addStore goodsId: " + goodsId);

		Message message = Message.success();
		message.addData("object", bopsGoodsStoreService.getGoodsMainData(goodsId));
		logger.info("out store addStore goodsId: " + goodsId);
		return message;
	}

	/**
	 * 
	 * @author xiehao 2015年8月18日 下午7:19:19 @Method: checkStore @Description:
	 *         TODO 进入添加库存页面
	 * @param goodsId
	 * 			@return @throws
	 */
//	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/storeDetail")
	public String checkStore(String goodsId, Integer page, Model model) {
		logger.info("in store checkStore goodsId: " + goodsId);

//		Message message = Message.success();
//		message.addData("object", bopsGoodsStoreService.getGoodsStore(goodsId));
		model.addAttribute("goods", bopsGoodsStoreService.getGoodsStore(goodsId));
		model.addAttribute("page", page);
		model.addAttribute("picAddress", constantsProConfig.getPic_domain());
		logger.info("out store checkStore goodsId: " + goodsId);
//		return message;
		return ViewMappings.STROE_DETAIL;
	}

	/**
	 * 
	 * @author xiehao 2015年8月18日 下午7:28:09 @Method: storeLog @Description:
	 * TODO查看库存日志 @param goodsId @param storePageVO @param page @param
	 * pageSize @return @throws
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/log/stores")
	public String storeLog(String goodsId, Integer page, Model model) {
		logger.info("in store storeLog goodsId: " + goodsId + "\t storePageVO: " + goodsId);
//		Message message = Message.success();

//		message.addData("list", bopsGoodsStoreService.pageStoreLog(goodsId));
		model.addAttribute("list", bopsGoodsStoreService.pageStoreLog(goodsId));
		model.addAttribute("page", page);
		logger.info("out store storeLog goodsId: " + goodsId + "\t storePageVO: " + goodsId);
//		return message;
		return ViewMappings.STORE_LOG_LIST;
	}

	/**
	 * 
	 * @author xiehao 2015年8月18日 下午7:19:42 @Method:
	 *         auditGoodsStore @Description: TODO修改库存 @param
	 *         storePageVO @return @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/{goodsId}", method = RequestMethod.PUT)
	public Message auditGoodsStore(@RequestBody GoodsStorePageVO storePageVO) {
		logger.info("in store auditGoodsStore storePageVO: " + storePageVO);

		Message message = Message.success();
		bopsGoodsStoreService.updateAudit(storePageVO);
		message.addData("object", bopsGoodsStoreService.getGoodsStore(storePageVO.getGoodsId()));
		logger.info("out store auditGoodsStore storePageVO: " + storePageVO);
		return message;
	}

	/**
	 * 
	 * @author xiehao 2015年8月18日 下午7:20:14 @Method:
	 *         deleteBopsGoodsStoreById @Description: TODO删除库存记录 @param
	 *         goodsId @return @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.DELETE, value = "/{goodsId}")
	public Message deleteBopsGoodsStoreById(@PathVariable String goodsId) {
		logger.info("in store deleteBopsGoodsStoreById  goodsId: " + goodsId);
		Message message = Message.success();
		bopsGoodsStoreService.deleteGoodsStoreById(goodsId);
		logger.info("out store deleteBopsGoodsStoreById  goodsId: " + goodsId);
		return message;
	}

	/**
	 * 
	 * @author xiehao 2015年8月18日 下午7:20:35 @Method:
	 *         getPageOfGoodsStoreInfo @Description: TODO获取库存分页列表 @param
	 *         goodsPageVO @param page @param pageSize @return @throws
	 */
	
	@RequestMapping(method = RequestMethod.GET, value = "/page")
	public String getPageOfGoodsStoreInfo(GoodsStorePageVO goodsPageVO, Model model) {
		logger.info("in store getPageOfGoodsStoreInfo goodsPageVO: " + goodsPageVO);
//		Message success = Message.success();
		if (!StringUtils.hasText(goodsPageVO.getGoodsBarcode())) {
			goodsPageVO.setGoodsBarcode(null);
		}
		if (!StringUtils.hasText(goodsPageVO.getGoodsCode())) {
			goodsPageVO.setGoodsCode(null);
		}
		if (goodsPageVO.getPage() == null || "".equals(goodsPageVO.getPage()) || goodsPageVO.getPageSize() == null || "".equals(goodsPageVO.getPageSize())) {
			goodsPageVO.setPage(1);
			goodsPageVO.setPageSize(Integer.MAX_VALUE);
//			success.addData("list", bopsGoodsStoreService.pageBopsGoodsStore(goodsPageVO));
			model.addAttribute("list", bopsGoodsStoreService.pageBopsGoodsStore(goodsPageVO));
			model.addAttribute("picAddress", constantsProConfig.getPic_domain());
		} else {
//			success.addData("list", bopsGoodsStoreService.pageBopsGoodsStore(goodsPageVO));
//			success.addData("page", goodsPageVO);
			List<GoodsStoreResultVO> list = bopsGoodsStoreService.pageBopsGoodsStore(goodsPageVO);
			
			model.addAttribute("list", list);
			model.addAttribute("page", goodsPageVO);
			model.addAttribute("picAddress", constantsProConfig.getPic_domain());
		}
		logger.info("out store getPageOfGoodsStoreInfo goodsPageVO: " + goodsPageVO);
//		return success;
		return ViewMappings.GOODS_STORE_LIST;
	}

	/**
	 * @author xiehao 2015年9月1日 下午5:47:11 @Method: exportStore @Description:
	 * TODO 导出库存 @param goodsPageVO @param page @param pageSize @param
	 * response @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/export")
	public void exportStore(GoodsStorePageVO store, @RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer pageSize, HttpServletResponse response) {
		logger.info("in BopsGoodsStoreManagerController exportStore");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=" + System.currentTimeMillis() + ".xls");
			response.setContentType("application /  vnd.ms-excel; charset=utf-8");
			if(null == store.getPageSize()){
				store.setPageSize(Integer.MAX_VALUE);
			}
			HSSFWorkbook workbook = bopsGoodsStoreService.exportStore(store);
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
	
	@RequestMapping(method = RequestMethod.GET, value = "toExportPage")
	public String toExportPage(){
		
		return ViewMappings.GOODS_STORE_TO_EXPORT_PAGE;
	}

}
