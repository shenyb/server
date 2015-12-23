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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.common.validate.ValidatorUtil;
import com.need.dao.bops.goods.BopsGoodsBrandDAO;
import com.need.domain.common.enums.BrandStatusEnum;
import com.need.domain.po.api.goods.GoodsBrandPO;
import com.need.domain.po.bops.goods.BopsGoodsBrandPO;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.vo.goods.BrandPageVO;
import com.need.domain.vo.trade.TradeOrderParamsVO;
import com.need.erp.constant.ControllerMappings;
import com.need.erp.constant.ViewMappings;
import com.need.erp.pub.ConstantsProConfig;
import com.need.service.bops.goods.BopsGoodsBrandService;
import com.need.service.constant.BizReturnCode;
import com.need.service.constant.Constants;
import com.need.utils.StringUtil;
/**
 * 
 * <p></p>
 * @author liuhongyang 2015年11月11日 下午7:04:19
 * @ClassName BopsCategoriesManagerController
 * @Description 商品品牌
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: liuhongyang 2015年11月11日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.BOPS_BRAND)
public class BopsBrandManagerController {
	
	private static final Logger logger = Logger.getLogger(BopsBrandManagerController.class);
	@Autowired
	private BopsGoodsBrandService bopsGoodsBrandService;
	@Autowired
	private BopsGoodsBrandDAO bopsGoodsBrandDAO;
	@Autowired
	private ConstantsProConfig ConstantsProConfig;
	
	/**
	 * 
	 * @author liuhongyang 2015年11月12日 上午10:30:47
	 * @Method: getCategoriesList 
	 * @Description: 品牌列表
	 * @param categoriesPO
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/page")
	public String getBrandList(BrandPageVO params, Model model) {
		logger.info(" getBrandList params:" + params.toString());
		List<BrandPageVO> list = bopsGoodsBrandService.getBrandList(params);
		model.addAttribute("list", list);
		model.addAttribute("page", params);
		return ViewMappings.BRAND_LIST;
	}
	/**
	 * 
	 * @author liuhongyang 2015年11月12日 上午10:29:02
	 * @Method: toAddCategory 
	 * @Description: 跳转到品牌新增页
	 * @param categorityLevel
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/toAddBrand")
	public String toAddBrand(Model model) {
		return ViewMappings.BRAND_ADD;
	}
	

	/**
	 * 
	 * @author peiboli 2015年11月18日 下午6:03:24
	 * @Method: toModBrand 
	 * @Description: TODO品牌添加
	 * @param bopsGoodsBrandPO
	 * @param request
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public Message toModBrand(BopsGoodsBrandPO bopsGoodsBrandPO,HttpServletRequest request) {
		logger.info("in toModBrand params: " + bopsGoodsBrandPO.toString());
		Message message = Message.success();
		Set<ConstraintViolation<BopsGoodsBrandPO>> result = ValidatorUtil.getInstance().validate(bopsGoodsBrandPO);
		if(result.size()>0){
			for(ConstraintViolation<BopsGoodsBrandPO> c:result){
				return Message.error(BizReturnCode.FIELD_VALIDATE_ERR, c.getMessage());
			}
		}
		Boolean isExist =bopsGoodsBrandService.getBrandByBrandName(bopsGoodsBrandPO.getBrandName());
		if(isExist){
		 return  Message.error(BizReturnCode.BRAD_EXIST);
		}
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);// 得到当前登录的用户信息
		bopsGoodsBrandPO.setCreateId(user.getUserId());
		bopsGoodsBrandPO.setBrandStatus(BrandStatusEnum.NORMAL.desc);
		bopsGoodsBrandService.save(bopsGoodsBrandPO);
		return message;
	}
	
	/**
	 * 
	 * @author peiboli 2015年11月18日 下午6:50:17
	 * @Method: getDetailByBrandId 
	 * @Description: TODO
	 * @param categoryId
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/{brandId}", method = RequestMethod.GET)
	public String getDetailByBrandId(@PathVariable(value="brandId") int brandId, Model model) {
		logger.info("in toModBrand params: " + brandId);
		BopsGoodsBrandPO brand = bopsGoodsBrandDAO.selectByPrimaryKey(brandId);
		model.addAttribute("brand", brand);
		model.addAttribute("imgUrl", ConstantsProConfig.getPic_domain());
		return ViewMappings.BRAND_DETAIL;
	}
	
	/**
	 * 
	 * @author peiboli 2015年11月18日 下午6:56:30
	 * @Method: toeditPage 
	 * @Description: TODO跳转编辑页面
	 * @param brandId
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/toEditPage/{brandId}", method = RequestMethod.GET)
	public String toeditPage(@PathVariable(value="brandId") int brandId,Model model) {
		logger.info("in toeditPage params: " + brandId);
		BopsGoodsBrandPO brand = bopsGoodsBrandDAO.selectByPrimaryKey(brandId);
		model.addAttribute("brand", brand);
		if(StringUtil.isBlank(brand.getBrandPicKey())){
			model.addAttribute("imgUrl", "");	
		}else{
		model.addAttribute("imgUrl", ConstantsProConfig.getPic_domain());
		}
		return ViewMappings.BRAND_MOD;
	}
	/**
	 * 
	 * @author peiboli 2015年11月18日 下午7:58:54
	 * @Method: editBrand 
	 * @Description: TODO编辑品牌
	 * @param bopsGoodsBrandPO
	 * @param request
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public Message editBrand(BopsGoodsBrandPO bopsGoodsBrandPO,HttpServletRequest request) {
		logger.info("in editBrand params: " + bopsGoodsBrandPO.toString());
		int isSuccess=0;
		Message message= Message.success();
		Set<ConstraintViolation<BopsGoodsBrandPO>> result = ValidatorUtil.getInstance().validate(bopsGoodsBrandPO);
		if(result.size()>0){
			for(ConstraintViolation<BopsGoodsBrandPO> c:result){
				return Message.error(BizReturnCode.FIELD_VALIDATE_ERR, c.getMessage());
			}
		}
		BopsUser user= (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);// 得到当前登录的用户信息
		bopsGoodsBrandPO.setUpdateId(user.getUserId());
		BopsGoodsBrandPO brand = bopsGoodsBrandDAO.selectByPrimaryKey(bopsGoodsBrandPO.getBrandId());
		if(bopsGoodsBrandPO.getBrandName().equals(brand.getBrandName())){
			isSuccess= bopsGoodsBrandService.update(bopsGoodsBrandPO);
			if(isSuccess==1){
				return message;
			}else{
				return Message.error(isSuccess);
			}
		}else{
			Boolean isExist =bopsGoodsBrandService.getBrandByBrandName(bopsGoodsBrandPO.getBrandName());
			if(isExist){
			 return  Message.error(BizReturnCode.BRAD_EXIST);
			}else{
				isSuccess= bopsGoodsBrandService.update(bopsGoodsBrandPO);
				return message;
			}	
		}
		
		
	}
	@ResponseBody
	@RequestMapping(value = "/isExist", method = RequestMethod.POST)
	public Message isExistBrand(HttpServletRequest request){
		Message message= Message.success();
		String brandName=request.getParameter("brandName");
		try {
			BopsGoodsBrandPO  bopsGoodsBrandPO =bopsGoodsBrandDAO.getBrandByBrandName(brandName);
			if(bopsGoodsBrandPO==null){
				 //return  Message.error(BizReturnCode.BRAD_ISEXIST);
			}else{
				message.setMsg(bopsGoodsBrandPO.getBrandId()+"");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}
	/**
	 * 
	 * @author liuhongyang 2015年12月16日 下午8:12:17
	 * @Method: exportBrand 
	 * @Description: 品牌导出
	 * @param bopsGoodsBrandPO
	 * @param response 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/exportBrand")
	public void exportBrand(BopsGoodsBrandPO bopsGoodsBrandPO, HttpServletResponse response) {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		logger.info(
				String.format("exportBrand exportBrandExcel params:　%s", bopsGoodsBrandPO.toString()));
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=" + System.currentTimeMillis() + ".xls");
			response.setContentType("application /  vnd.ms-excel; charset=utf-8");
			HSSFWorkbook workbook = bopsGoodsBrandService.exportBrand(bopsGoodsBrandPO);
			try {
				workbook.write(os);
			} catch (IOException e) {
				logger.error(String.format("exportBrand exportBrandExcel write error", e.toString()));
				e.printStackTrace();
			}
			os.flush();
		} catch (Exception e) {
			logger.error(String.format("exportBrand exportBrandExcel error", e.toString()));
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
