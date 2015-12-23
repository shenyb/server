package com.need.oscar.web.controller.kolcategory;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.common.validate.ValidatorUtil;
import com.need.dao.api.kol.KolCategoryDAO;
import com.need.domain.po.bops.basedata.BopsKolCategory;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.pub.Page;
import com.need.oscar.constant.Constants;
import com.need.oscar.constant.ControllerMappings;
import com.need.service.bops.kolcategory.BopsKolCategoryService;
import com.need.service.constant.BizReturnCode;
import com.need.utils.StringUtil;



/**
 * 
 * <p>
 * </p>
 * 
 * @author peiboli 2015年8月1日 上午10:30:09
 * @ClassName KolCategoryManagerController
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年8月1日
 * @modify by reason:{方法名}:  {原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.EXPERTCLASSIFY)
public class KolCategoryManagerController {
	private static final Logger logger = Logger.getLogger(KolCategoryManagerController.class);
	@Autowired
	private BopsKolCategoryService bopsKolCategoryService;

	@Autowired
	private KolCategoryDAO kolCategoryDAO;

	/**
	 * 
	 * @author peiboli 2015年8月3日 上午9:42:43 @Method:
	 *         addBopsKolCategory @Description: TODO增加 @param
	 *         bopsKolCategory @param request @return @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public Message addBopsKolCategory(@RequestBody BopsKolCategory bopsKolCategory, HttpServletRequest request) {

		logger.info(String.format("addBopsKolCategory....in...params:%s %s",
				bopsKolCategory.getCategoryName(),
				bopsKolCategory.getCategoryProfileKey()));

		Message message = Message.success();
		if (StringUtil.isBlank(bopsKolCategory.getCategoryName())) {
			return Message.error(5003);
		}
		int isexist = kolCategoryDAO.selectCategoryName(bopsKolCategory.getCategoryName());
		if (isexist == 1) {//1代表行家分类名存在
			return Message.error(5004);
		}
		Set<ConstraintViolation<BopsKolCategory>> result = ValidatorUtil.getInstance().validate(bopsKolCategory);
		if(result.size()>0){
			for(ConstraintViolation<?> c:result){
				return Message.error(BizReturnCode.FIELD_VALIDATE_ERR, c.getMessage());
			}
		}
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		bopsKolCategory.setPublisherId(user.getUserId());
		BopsKolCategory kolCategory = bopsKolCategoryService.addBopsKolCategory(bopsKolCategory);
		message.addData("object", kolCategory);
		logger.info(String.format("addBopsKolCategory....out...params:%s %s",
				bopsKolCategory.getCategoryName(),
				bopsKolCategory.getCategoryProfileKey()));
		return message;

	}

	/**
	 * 
	 * @author peiboli 2015年8月3日 上午9:42:54 @Method:
	 *         selectBopsKolCategory @Description: TODO获得行家类别列表 @return @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public Message getKolCategoryList(@RequestParam(required = false) String search,
			@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {

		logger.info(String.format("getKolCategoryList....in...params:%s %d %d", search,page,pageSize));

		Message message = Message.success();
		Page kolcatPage = new Page();
		if (page == null || "".equals(page) || pageSize == null || "".equals(pageSize)) {
			kolcatPage.setPageSize(Integer.MAX_VALUE);
		} else {
			kolcatPage.setPage(page);
			kolcatPage.setPageSize(pageSize);
		}
		message.addData("list", bopsKolCategoryService.getKolCategoryList(search, kolcatPage));
		message.addData("page", kolcatPage);
		logger.info(String.format("getKolCategoryList....out...params:%s %s %s", search,page,pageSize));
		return message;

	}

	/**
	 * 
	 * @author peiboli 2015年8月4日 上午10:52:37 @Method:
	 *         selectBopsKolCategoryByCategoryName @Description:
	 *         TODO查询一条数据 @param categoryName @return @throws
	 */
//	@ResponseBody
//	@RequestMapping(method = RequestMethod.GET, value = "/search/{categoryName}")
//	public Message selectBopsKolCategoryByCategoryName(@PathVariable(value = "categoryName") String categoryName) {
//		logger.info("selectBopsKolCategoryBycategoryName in ...");
//		Message message = Message.success();
//		BopsKolCategory kolCategory = bopsKolCategoryService.getKolCategory(categoryName);
//		message.addData("categoryName", kolCategory);
//		return message;
//	}
//	/**
//	 * 
//	 * @author peiboli 2015年8月7日 下午6:14:50 @Method:
//	 *         selectBopsKolCategoryByCategoryName @Description:
//	 *         TODO根据id获取一条数据 @param categoryName @return @throws
//	 */

	/**
	 * 
	 * @author peiboli 2015年8月3日 上午9:43:12 @Method:
	 *         deleteBopsKolCategory @Description: TODO根据ID删除行家分类 @param
	 *         categoryId @return @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.DELETE, value = "/{categoryId}")
	public Message deleteBopsKolCategory(@PathVariable(value = "categoryId") String categoryId) {

		logger.info(String.format("deleteBopsKolCategory...in....params:%s",categoryId ));

		Message message = Message.success();
		int isSuccess = bopsKolCategoryService.deleteBopsKolCategory(categoryId);
		if(isSuccess!=0){
			logger.info(String.format("deleteBopsKolCategory....out....params:%s",categoryId ));
			return message;
		}else{
			return Message.error(5013);
		}
		

	}

	/**
	 * 
	 * @author peiboli 2015年8月3日 上午9:43:40 @Method:
	 *         updateBopsKolCategory @Description: TODO更新一条数据 @param
	 *         bopsKolCategory @return @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT, value = "/{categoryId}")
	public Message updateBopsKolCategory(@RequestBody BopsKolCategory bopsKolCategory) {

		logger.info(String.format("updateBopsKolCategory...in....params:%s %s", bopsKolCategory.getCategoryName(),bopsKolCategory.getCategoryProfileKey()));

		Message message = Message.success();
		if (StringUtil.isBlank(bopsKolCategory.getCategoryName())) {

			return Message.error(5003);
		}
		Set<ConstraintViolation<BopsKolCategory>> result = ValidatorUtil.getInstance().validate(bopsKolCategory);
		if(result.size()>0){
			for(ConstraintViolation<?> c:result){
				return Message.error(BizReturnCode.FIELD_VALIDATE_ERR, c.getMessage());
			}
		}
		String  categoryName = kolCategoryDAO.selectCategoryNameByCategoryId(bopsKolCategory.getCategoryId());
		if(bopsKolCategory.getCategoryName().equals(categoryName)){
			bopsKolCategoryService.updateBopsKolCategory(bopsKolCategory);
			return message;	
		}else{
			int isexist = kolCategoryDAO.selectCategoryName(bopsKolCategory.getCategoryName());
			if (isexist == 1) {//1代表行家分类名存在
				return Message.error(5004);
			}	
			bopsKolCategoryService.updateBopsKolCategory(bopsKolCategory);
			logger.info(String.format("updateBopsKolCategory...out...params:", bopsKolCategory.getCategoryName(),bopsKolCategory.getCategoryProfileKey()));
			return message;
		}
		
	

	}

}
