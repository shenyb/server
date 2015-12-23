package com.need.erp.web.controller.purchase;

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
import com.need.service.constant.Constants;

import net.sf.json.JSONArray;

/**
 * 
 * <p></p>
 * @author liuhongyang 2015年11月11日 下午7:04:19
 * @ClassName BopsCategoriesManagerController
 * @Description 采购单管理
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: liuhongyang 2015年11月11日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.BOPS_PURCHASE)
public class BopsPurchaseManagerController {
	
	private static final Logger logger = Logger.getLogger(BopsPurchaseManagerController.class);
	
	@Autowired
	private BopsPurchaseService bopsPurchaseService;
	@Autowired
	private BopsPurchaseDAO bopsPurchaseDAO;
	/**
	 * 
	 * @author liuhongyang 2015年11月12日 上午10:30:47
	 * @Method: getCategoriesList 
	 * @Description: 采购单列表
	 * @param categoriesPO
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/page")
	public String getPurchaseList(BopsPurchaseVO bopsPurchaseVO,Model model) {
		logger.info("in goods getPurchaseList levelList: " +bopsPurchaseVO);
		List wareHouseList=bopsPurchaseDAO.selectWareHouseType();
		model.addAttribute("wareHouseList", wareHouseList);
		List providerList=bopsPurchaseDAO.selectProvider();
		model.addAttribute("providerList", providerList);
		bopsPurchaseVO.setTotal(bopsPurchaseDAO.selectPurchaseCount(bopsPurchaseVO));
		List<BopsPurchaseVO> purchaseList=bopsPurchaseService.selectPurchaseList(bopsPurchaseVO);
		model.addAttribute("list", purchaseList);
		model.addAttribute("page", bopsPurchaseVO);
		return ViewMappings.PURCHASE_LIST;
	}
	/**
	 * 
	 * @author liuhongyang 2015年11月19日 下午4:00:31
	 * @Method: downloadPurchaseTemplate 
	 * @Description: 采购单模板下载
	 * @param response
	 * @throws ParseException 
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/downloadPurchaseTemplate")
	public void downloadPurchaseTemplate(HttpServletResponse response) throws ParseException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		logger.info("BopsPurchaseManagerController downloadPurchaseTemplate");
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			response.reset();
			String fileName = "导入采购单模板";
			fileName = URLEncoder.encode(fileName, "UTF8");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
			response.setContentType("application / vnd.ms-excel; charset=utf-8");
			XSSFWorkbook workbook = bopsPurchaseService.exportPurchaseTemplate();
			try {
				workbook.write(os);
			} catch (IOException e) {
				logger.error("BopsPurchaseManagerController write error"+ e.toString());
				e.printStackTrace();
			}

			os.flush();
		} catch (Exception e) {
			logger.error(String.format("BopsPurchaseManagerController exportPurchase error", e.toString()));
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					logger.error("BopsPurchaseManagerController  close error"+ e.toString());
					e.printStackTrace();
				}
			}
		}

	}
	@RequestMapping(value = "/batchPurchase", method = RequestMethod.POST)
	public String batchPurchase(@RequestParam("files") MultipartFile[] files,String purchaseCurrency, HttpServletRequest request,
			Model model) throws Exception {
		List<BopsPurchaseVO> batchPurchaseList = null;
		List<BopsPurchaseVO> pruchaseFailList = new ArrayList<BopsPurchaseVO>();
		List<BopsPurchaseVO> pruchaseSuccessList = new ArrayList<BopsPurchaseVO>();
		BopsPurchaseVO bopsPurchaseVOAll=new BopsPurchaseVO();
		Message message = Message.success();
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				InputStream in = file.getInputStream();
				Boolean is07or10 = file.getOriginalFilename().endsWith("xlsx");
				batchPurchaseList = bopsPurchaseService.excelToList(in, is07or10);
				Integer purchasePriceAll = 0; 
				Integer purchaseCountAll = 0; 
				for (BopsPurchaseVO bopsPurchaseVO : batchPurchaseList) {
					if(bopsPurchaseService.hasNullPurchaseList(bopsPurchaseVO) && bopsPurchaseService.isExistGoods(bopsPurchaseVO)){
						//正确数据
						bopsPurchaseVO.setSuccess(true);
						pruchaseSuccessList.add(bopsPurchaseVO);
					}else{
						bopsPurchaseVO.setSuccess(false);
						//是否有空字段
						if (!bopsPurchaseService.hasNullPurchaseList(bopsPurchaseVO)) {
							bopsPurchaseVO.setMessage(Constants.REQUIRED_IS_NULL);
							pruchaseFailList.add(bopsPurchaseVO);
						}else{
							//检查商品是否存在
							if (!bopsPurchaseService.isExistGoods(bopsPurchaseVO)) {
								bopsPurchaseVO.setMessage(Constants.GOODS_IS_NOT_EXIST);
								pruchaseFailList.add(bopsPurchaseVO);
							}
						}
					}
					if(null != bopsPurchaseVO.getGoodsCode()){
						bopsPurchaseVO=bopsPurchaseService.getSuccessData(bopsPurchaseVO);
						if(null != bopsPurchaseVO.getGoodsName()){
							purchasePriceAll = purchasePriceAll+bopsPurchaseVO.getPurchasePrice()*bopsPurchaseVO.getPurchaseCount();
							purchaseCountAll = purchaseCountAll+bopsPurchaseVO.getPurchaseCount();
						}
					}
				}
				String flag="0";
				for (BopsPurchaseVO bopsPurchaseVO : batchPurchaseList) {
					if(null != bopsPurchaseVO.getMessage()){
						flag="1";
					}
				}
				if("0".equals(flag)){
					//数据正确,计算采购总金额,总数量
					bopsPurchaseVOAll.setPurchaseCountAll(purchaseCountAll);
					bopsPurchaseVOAll.setPurchasePriceAll(purchasePriceAll);
				}
				bopsPurchaseVOAll.setFlag(flag);
			}
		}
		bopsPurchaseVOAll.setPurchaseCurrency(CurrencyEnums.getDesc(purchaseCurrency).toString());
		JSONArray successJson=JSONArray.fromObject(pruchaseSuccessList);
		model.addAttribute("failList", pruchaseFailList);
		model.addAttribute("successList", pruchaseSuccessList);
		model.addAttribute("successJson", successJson);
		model.addAttribute("bopsPurchaseVOAll", bopsPurchaseVOAll);
		model.addAttribute("purchaseAll", JSONObject.toJSON(bopsPurchaseVOAll));
		return ViewMappings.PURCHASE_BATCH;
	}
	
	/**
	 * 
	 * @author liuhongyang 2015年11月12日 上午10:29:02
	 * @Method: toAddPurchase 
	 * @Description: 跳转到采购单新增页
	 * @param 
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/toAddPurchase")
	public String toAddPurchase(Model model) {
		List wareHouseList=bopsPurchaseDAO.selectWareHouseType();
		List providerList=bopsPurchaseDAO.selectProvider();
		model.addAttribute("wareHouseList", wareHouseList);
		model.addAttribute("providerList",providerList);
		return ViewMappings.PURCHASE_ADD;
	}
	/**
	 * 
	 * @author liuhongyang 2015年11月12日 上午10:29:19
	 * @Method: addBopsGoodsrManager 
	 * @Description: 新增采购单
	 * @param categoriesVO
	 * @param request
	 * @return
	 * @throws NumberFormatException
	 * @throws ParseException 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/addPurchase")
	public Message addBopsPurchase( HttpServletRequest request,BopsPurchaseVO bopsPurchaseVO)
			throws NumberFormatException, ParseException {
		logger.info("in addBopsPurchase bopsPurchaseVO: " +bopsPurchaseVO);
		//当前登录用户
 		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		bopsPurchaseVO.setPurchaseUserId(user != null ? user.getUserId() : 1);
		if(null != user){
			bopsPurchaseVO.setPurchaseDepartment(user.getUserDept());
		}
		bopsPurchaseService.savePurchase(bopsPurchaseVO);
		Message message = Message.success();
		return message;
	}
	/**
	 * 
	 * @author liuhongyang 2015年11月23日 下午8:55:40
	 * @Method: getDetail 
	 * @Description: 采购单详情
	 * @param purchaseId
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/purchaseDetial", method = RequestMethod.GET)
	public String getDetail(String purchaseId, Model model) {
		BopsPurchaseVO bopsPurchaseVO=bopsPurchaseService.getPurchaseDetail(purchaseId);
		model.addAttribute("bopsPurchaseVO", bopsPurchaseVO);
		List<BopsPurchaseVO> purchaseInfoList=bopsPurchaseService.getGoodsDetail(Integer.parseInt(purchaseId));
		if(purchaseInfoList.size()>0){
			model.addAttribute("purchaseInfoList", purchaseInfoList);
		}
		return ViewMappings.PURCHASE_DETAIL;
	}
	/**
	 * 
	 * @author liuhongyang 2015年11月12日 上午10:30:19
	 * @Method: 跳转到采购单编辑页
	 * @Description: TODO
	 * @param categorityLevel
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/toModPurchase")
	public String toModPurchase(String purchaseId,Model model) {
		logger.info("in goods toModPurchase PurchaseId: " +purchaseId);
		BopsPurchaseVO bopsPurchaseVO=bopsPurchaseService.getPurchaseDetail(purchaseId);
		model.addAttribute("bopsPurchaseVO", bopsPurchaseVO);
		List<BopsPurchaseVO> purchaseInfoList=bopsPurchaseService.getGoodsDetail(Integer.parseInt(purchaseId));
		if(purchaseInfoList.size()>0){
			model.addAttribute("purchaseInfoList", purchaseInfoList);
		}
		return ViewMappings.PURCHASE_EDIT;
	}
	/**
	 * 
	 * @author liuhongyang 2015年11月13日 上午10:43:35
	 * @Method: modBopsGoodsrManager 
	 * @Description: 编辑采购单
	 * @param categoriesVO
	 * @param request
	 * @return
	 * @throws NumberFormatException
	 * @throws ParseException 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/modPurchase")
	public Message modBopsPurchaseManager(String dataAll,HttpServletRequest request)
			throws NumberFormatException, ParseException {
		logger.info("in goods modBopsPurchaseManager bopsPurchaseVO: ");
		if(null !=dataAll){
			BopsPurchaseVO bopsPurchaseVO =new BopsPurchaseVO();
			//当前登录用户
	 		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
			bopsPurchaseVO.setPurchaseUserId(user != null ? user.getUserId() : 1);
			if(null != user){
				bopsPurchaseVO.setPurchaseDepartment(user.getUserDept());
			}
			bopsPurchaseVO.setDataAll(dataAll);
			bopsPurchaseService.updatePurchase(bopsPurchaseVO);
		}
		Message message = Message.success();
		return message;
	}
	
	
}
