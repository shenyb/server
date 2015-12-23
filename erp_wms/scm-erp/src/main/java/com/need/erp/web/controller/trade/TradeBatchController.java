 package com.need.erp.web.controller.trade;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.need.dao.bops.trade.BopsTradePushPullDAO;
import com.need.domain.po.bops.goods.BopsGoodsBrandPO;
import com.need.domain.po.bops.trade.BopsTradePushPull;
import com.need.domain.vo.trade.TradeBatchNoParamsVO;
import com.need.erp.constant.ControllerMappings;
import com.need.erp.constant.ViewMappings;
import com.need.erp.web.controller.goods.BopsBrandManagerController;
import com.need.service.bops.trade.BopsTradePushPullService;

/**
 * 
 * <p></p>
 * @author LXD 2015年9月24日 下午2:37:07
 * @ClassName TradeBatchController
 * @Description TODO 订单批次管理
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年9月24日
 * @modify by reason:{方法名}:{原因}
 */
 
@Controller
@RequestMapping(ControllerMappings.TRADEBATCH)
public class TradeBatchController {
	private static final Logger logger = Logger.getLogger(TradeBatchController.class);
	@Autowired
	private BopsTradePushPullDAO bopsTradePushPullDAO;
	@Autowired
	private BopsTradePushPullService BopsTradePushPullService;
	
	@RequestMapping(method = RequestMethod.GET, value="/page")
	public String queryTradeBatchNoInfoList(TradeBatchNoParamsVO param,
			Model model) {
//		Message success = Message.success();

		Integer page = param.getPage();
		Integer pageSize = param.getPageSize();
		if (page == null || "".equals(page) || pageSize == null || "".equals(pageSize)) {
			List<BopsTradePushPull> list = bopsTradePushPullDAO.queryBatchInfoList(param);
//			success.addData("list", list);
			model.addAttribute("list", list);
		} else {
			param.setTotal(bopsTradePushPullDAO.getBatchInfoCount(param));
			List<BopsTradePushPull> list =  bopsTradePushPullDAO.queryBatchInfoList(param);
//			success.addData("list", list);
//			success.addData("page", param);
			model.addAttribute("list", list);
			model.addAttribute("page", param);
		}
//		return success;
		return ViewMappings.BATCH_TRADE_LIST;
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/{batchNo}")
	public ModelAndView queryByBatchNo(@PathVariable(value = "batchNo") String batchNo,ModelAndView mv) {
		mv = new ModelAndView("/trade/batchTradeDetail");
		//Message success = Message.success();
        List<BopsTradePushPull>  list=BopsTradePushPullService.queryByBatchNo(batchNo);
       // success.addData("list", list);
		//return success;
        mv.addObject("list", list);
        mv.addObject("batchNo", batchNo);
        return mv;
	}

	/**
	 * 
	 * @author liuhongyang 2015年12月16日 下午8:13:07
	 * @Method: exportBatch 
	 * @Description: 批次详情列表导出
	 * @param response 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/exportBatch")
	public void exportBatch(String batchNo,HttpServletResponse response) {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		logger.info("exportBatch exportBatchExcel params:　%s");
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=" + System.currentTimeMillis() + ".xls");
			response.setContentType("application /  vnd.ms-excel; charset=utf-8");
			List<BopsTradePushPull>  list=BopsTradePushPullService.queryByBatchNo(batchNo);
			HSSFWorkbook workbook = BopsTradePushPullService.exportBatch(list);
			try {
				workbook.write(os);
			} catch (IOException e) {
				logger.error(String.format("exportBatch exportBatchExcel write error", e.toString()));
				e.printStackTrace();
			}
			os.flush();
		} catch (Exception e) {
			logger.error(String.format("exportBatch exportBatchExcel error", e.toString()));
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					logger.error(String.format("exportBrand exportBrandExcel close error", e.toString()));
					e.printStackTrace();
				}
			}
		}

	}
	
}
