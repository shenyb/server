 package com.need.customer.web.controller.trade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.need.customer.constant.ControllerMappings;
import com.need.customer.constant.ViewMappings;
import com.need.dao.bops.trade.BopsTradePushPullDAO;
import com.need.domain.po.bops.trade.BopsTradePushPull;
import com.need.domain.vo.trade.TradeBatchNoParamsVO;
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
        return mv;
	}

}
