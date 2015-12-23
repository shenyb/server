package com.need.erp.web.controller.vendor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson.TypeReference;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.record.FooterRecord;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.need.dao.bops.goods.BopsGoodsCategoriesDAO;
import com.need.dao.bops.purchase.BopsPurchaseDAO;
import com.need.domain.common.enums.CurrencyEnums;
import com.need.domain.po.bops.purchase.BopsPurchaseInfoPO;
import com.need.domain.po.bops.purchase.BopsPurchasePO;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.vo.goods.BopsGoodsCategoriesVO;
import com.need.domain.vo.purchase.BopsPurchaseVO;
import com.need.domain.vo.trade.BatchSendTradeParamVO;
import com.need.domain.vo.trade.TradeVO;
import com.need.erp.constant.ControllerMappings;
import com.need.erp.constant.ViewMappings;
import com.need.service.bops.purchase.BopsPurchaseService;
import com.need.service.bops.vendor.BopsVendorService;
import com.need.service.constant.Constants;

import net.sf.json.JSONArray;
/**
 * 
 * @author liuhongyang 2015年11月26日 下午12:10:15
 * @ClassName BopsVendorManagerController
 * @Description 供应商物流商管理
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: liuhongyang 2015年11月26日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.BOPS_VENDOR)
public class BopsVendorManagerController {
	
	private static final Logger logger = Logger.getLogger(BopsVendorManagerController.class);
	@Autowired
	BopsVendorService bopsVendorService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/page")
	public String getVendorList(Model model) {
		return ViewMappings.VENDOR_LIST;
	}
	/**
	 * 
	 * @author liuhongyang 2015年11月26日 下午3:10:48
	 * @Method: sendVendorToWMS 
	 * @Description: 同步供应商物流商信息到wms
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/sendVendor")
	public Message sendVendorToWMS(){
		Message message=bopsVendorService.sendVendorToWMS();
		return message;
	}
	
}
