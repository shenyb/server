package com.need.operation.web.controller.kolcategory;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.common.validate.ValidatorUtil;
import com.need.dao.api.kol.KolCategoryDAO;
import com.need.domain.po.bops.basedata.BopsKolCategory;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.vo.kol.BopsKolCategoryParamVO;
import com.need.operation.constant.ControllerMappings;
import com.need.operation.constant.ViewMappings;
import com.need.operation.pub.ConstantsProConfig;
import com.need.service.bops.kolcategory.BopsKolCategoryService;
import com.need.service.constant.BizReturnCode;
import com.need.service.constant.Constants;
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
 * @modify by reason:{方法名}: {原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.EXPERTCLASSIFY)
public class KolCategoryManagerController {
	private static final Logger logger = Logger.getLogger(KolCategoryManagerController.class);
	@Autowired
	private BopsKolCategoryService bopsKolCategoryService;

	@Autowired
	private KolCategoryDAO kolCategoryDAO;
	@Autowired
	private ConstantsProConfig constantsProConfig;

	/**
	 * 
	 * @author peiboli 2015年8月3日 上午9:42:43 @Method:
	 *         addBopsKolCategory @Description: TODO增加 @param
	 *         bopsKolCategory @param request @return @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public Message addBopsKolCategory(BopsKolCategory bopsKolCategory, HttpServletRequest request) {

		logger.info(String.format("addBopsKolCategory....in...params:%s %s", bopsKolCategory.getCategoryName(),
				bopsKolCategory.getCategoryProfileKey()));

		Message message = Message.success();
		if (StringUtil.isBlank(bopsKolCategory.getCategoryName())) {
			return Message.error(5003);
		}
		int isexist = kolCategoryDAO.selectCategoryName(bopsKolCategory.getCategoryName());
		if (isexist == 1) {// 1代表行家分类名存在
			return Message.error(5004);
		}
		Set<ConstraintViolation<BopsKolCategory>> result = ValidatorUtil.getInstance().validate(bopsKolCategory);
		if (result.size() > 0) {
			for (ConstraintViolation<?> c : result) {
				return Message.error(BizReturnCode.FIELD_VALIDATE_ERR, c.getMessage());
			}
		}
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		bopsKolCategory.setPublisherId(user.getUserId());
		BopsKolCategory kolCategory = bopsKolCategoryService.addBopsKolCategory(bopsKolCategory);
		message.addData("object", kolCategory);
		logger.info(String.format("addBopsKolCategory....out...params:%s %s", bopsKolCategory.getCategoryName(),
				bopsKolCategory.getCategoryProfileKey()));
		return message;

	}

	/**
	 * 
	 * @author peiboli 2015年10月12日 上午10:58:07 @Method:
	 * getKolCategoryList @Description: TODO @param search @param
	 * model @return @throws
	 */
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public String getKolCategoryList(BopsKolCategoryParamVO page, Model model) {

		logger.info(String.format("getKolCategoryList....in...params:", page));
		List<BopsKolCategory> kolCategoryList = bopsKolCategoryService.getKolCategoryList(page.getSearch(), page);
		model.addAttribute("list", kolCategoryList);
		model.addAttribute("page", page);
		model.addAttribute("imgurl", constantsProConfig.getPic_domain());
		logger.info(String.format("getKolCategoryList....out...params: ", page));
		return ViewMappings.KOL_CATEGORY_LIST;

	}

	/**
	 * 
	 * @author peiboli 2015年8月3日 上午9:43:12 @Method:
	 *         deleteBopsKolCategory @Description: TODO根据ID删除行家分类 @param
	 *         categoryId @return @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/delete/{categoryId}")
	public Message deleteBopsKolCategory(@PathVariable(value = "categoryId") String categoryId) {

		logger.info(String.format("deleteBopsKolCategory...in....params:%s", categoryId));

		Message message = Message.success();
		int isSuccess = bopsKolCategoryService.deleteBopsKolCategory(categoryId);
		if (isSuccess != 0) {
			logger.info(String.format("deleteBopsKolCategory....out....params:%s", categoryId));
			return message;
		} else {
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
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Message updateBopsKolCategory(BopsKolCategory bopsKolCategory) {

		logger.info(String.format("updateBopsKolCategory...in....params:%s %s", bopsKolCategory.getCategoryName(),
				bopsKolCategory.getCategoryProfileKey()));

		Message message = Message.success();
		if (StringUtil.isBlank(bopsKolCategory.getCategoryName())) {

			return Message.error(5003);
		}
		Set<ConstraintViolation<BopsKolCategory>> result = ValidatorUtil.getInstance().validate(bopsKolCategory);
		if (result.size() > 0) {
			for (ConstraintViolation<?> c : result) {
				return Message.error(BizReturnCode.FIELD_VALIDATE_ERR, c.getMessage());
			}
		}
		String categoryName = kolCategoryDAO.selectCategoryNameByCategoryId(bopsKolCategory.getCategoryId());
		if (bopsKolCategory.getCategoryName().equals(categoryName)) {
			bopsKolCategoryService.updateBopsKolCategory(bopsKolCategory);
			return message;
		} else {
			int isexist = kolCategoryDAO.selectCategoryName(bopsKolCategory.getCategoryName());
			if (isexist == 1) {// 1代表行家分类名存在
				return Message.error(5004);
			}
			bopsKolCategoryService.updateBopsKolCategory(bopsKolCategory);
			logger.info(String.format("updateBopsKolCategory...out...params:", bopsKolCategory.getCategoryName(),
					bopsKolCategory.getCategoryProfileKey()));
			return message;
		}

	}

}
