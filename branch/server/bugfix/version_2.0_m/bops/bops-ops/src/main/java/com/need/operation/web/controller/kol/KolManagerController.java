package com.need.operation.web.controller.kol;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.need.common.validate.ValidatorUtil;
import com.need.domain.common.enums.AuditStatusEnums;
import com.need.domain.po.bops.basedata.BopsKolCategory;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.vo.kol.BopsKolAddParamVO;
import com.need.domain.vo.kol.BopsKolParamVO;
import com.need.domain.vo.kol.BopsKolResultVO;
import com.need.domain.vo.kol.BopsKolUpdateParamVO;
import com.need.operation.constant.ControllerMappings;
import com.need.operation.constant.ViewMappings;
import com.need.operation.pub.ConstantsProConfig;
import com.need.service.bops.kol.BopsKolService;
import com.need.service.bops.kolcategory.BopsKolCategoryService;
import com.need.service.common.exception.ServiceException;
import com.need.service.constant.BizReturnCode;
import com.need.service.constant.Constants;
import com.need.utils.StringUtil;

@Controller
@RequestMapping(value = ControllerMappings.KOL_MANAGER)
public class KolManagerController {

	private static final Logger logger = Logger.getLogger(KolManagerController.class);

	@Autowired
	private BopsKolService bopsKolService;
	@Autowired
	private ConstantsProConfig constantsProConfig;
	@Autowired
	private BopsKolCategoryService bopsKolCategoryService;

	/**
	 * @author Rylan 2015年8月5日 下午10:15:15 @Method: addKolInfo @Description:
	 * TODO @return @throws
	 */
	@ResponseBody
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public Message addKolInfo(BopsKolParamVO bopsKolParamVO, HttpServletRequest request)
			throws ServiceException {
		logger.info("addKolInfo...in..bopsKolVO:" + JSON.toJSONString(bopsKolParamVO));
		Message success = Message.success();
		Set<ConstraintViolation<BopsKolParamVO>> result = ValidatorUtil.getInstance().validate(bopsKolParamVO);
		if(result.size()>0){
			for(ConstraintViolation<?> c:result){
				return Message.error(BizReturnCode.FIELD_VALIDATE_ERR, c.getMessage());
			}
		}
		String  img[]= bopsKolParamVO.getProfilePicKey().split("/");
		String imgurl=img[img.length-1];
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		bopsKolParamVO.setPublisherId(user.getUserId());// 创建者id
		bopsKolParamVO.setProfilePicKey(imgurl);
		bopsKolParamVO.setCertification(bopsKolParamVO.getCertification().trim());
		bopsKolParamVO.setKolIntroduct(bopsKolParamVO.getKolIntroduct().trim());
		int isSuccess= bopsKolService.addBopsKol(bopsKolParamVO);
		if(isSuccess!=0){
			logger.info("addKolInfo...in..bopsKolVO:" + JSON.toJSONString(bopsKolParamVO));
			return success;
		}else{
			return Message.error(5014);
		}
		
	}

	/**
	 * @author Rylan 2015年8月5日 下午11:05:18 @Method: getKolInfo @Description:
	 * TODO @param kolId @return @throws
	 */
	@RequestMapping(value = "/{kolId}", method = RequestMethod.GET)
	public String getKolInfo(@PathVariable(value = "kolId") String kolId,Model model) throws ServiceException {
		logger.info(String.format("getKolInfo in..kolId:%s", kolId));
		List<BopsKolCategory> kolCategoryList =bopsKolCategoryService.getKolCategoryList();
		BopsKolResultVO bopsKolResultVO = bopsKolService.getBopsKolById(kolId);
		bopsKolResultVO.setKolCategories(bopsKolResultVO.getKolCategories());
		String categorys[]= bopsKolResultVO.getKolCategories().replaceAll("[\"\\[\\]]", "").split(",");
		bopsKolResultVO.setProfilePicKey(constantsProConfig.getPic_domain()+bopsKolResultVO.getProfilePicKey());
		model.addAttribute("kol", bopsKolResultVO);
		model.addAttribute("categorys",categorys);
		model.addAttribute("kolCategoryList", kolCategoryList);
		logger.info(String.format("getKolInfo...out..kolId:%s", kolId));
		return ViewMappings.KOL_CHECK;
	}
	/**
	 * 
	 * @author peiboli 2015年10月16日 下午3:05:25
	 * @Method: toupdatePage 
	 * @Description: TODO跳编辑页面
	 * @param kolId
	 * @param model
	 * @return
	 * @throws ServiceException 
	 * @throws
	 */
	@RequestMapping(value = "/toUpdate/{kolId}", method = RequestMethod.GET)
	public String toupdatePage(@PathVariable(value = "kolId") String kolId,Model model) throws ServiceException {
		logger.info(String.format("getKolInfo in..kolId:%s", kolId));
		List<BopsKolCategory> kolCategoryList =bopsKolCategoryService.getKolCategoryList();
		BopsKolResultVO bopsKolResultVO = bopsKolService.getBopsKolById(kolId);
		bopsKolResultVO.setKolCategories(bopsKolResultVO.getKolCategories());
		String categorys[]= bopsKolResultVO.getKolCategories().replaceAll("[\"\\[\\]]", "").split(",");
		bopsKolResultVO.setProfilePicKey(constantsProConfig.getPic_domain()+bopsKolResultVO.getProfilePicKey());
		model.addAttribute("kol", bopsKolResultVO);
		model.addAttribute("categorys",categorys);
		model.addAttribute("kolCategoryList", kolCategoryList);
		logger.info(String.format("getKolInfo...out..kolId:%s", kolId));
		return ViewMappings.KOL_UPDATE;
	}
	/**
	 * 
	 * @author peiboli 2015年10月16日 下午3:05:25
	 * @Method: toupdatePage 
	 * @Description: TODO跳编审核页面
	 * @param kolId
	 * @param model
	 * @return
	 * @throws ServiceException 
	 * @throws
	 */
	@RequestMapping(value = "/toAudit/{kolId}", method = RequestMethod.GET)
	public String toAuditPage(@PathVariable(value = "kolId") String kolId,Model model) throws ServiceException {
		logger.info(String.format("getKolInfo in..kolId:%s", kolId));
		List<BopsKolCategory> kolCategoryList =bopsKolCategoryService.getKolCategoryList();
		BopsKolResultVO bopsKolResultVO = bopsKolService.getBopsKolById(kolId);
		bopsKolResultVO.setKolCategories(bopsKolResultVO.getKolCategories());
		String categorys[]= bopsKolResultVO.getKolCategories().replaceAll("[\"\\[\\]]", "").split(",");
		bopsKolResultVO.setProfilePicKey(constantsProConfig.getPic_domain()+bopsKolResultVO.getProfilePicKey());
		model.addAttribute("kol", bopsKolResultVO);
		model.addAttribute("categorys",categorys);
		model.addAttribute("kolCategoryList", kolCategoryList);
		logger.info(String.format("getKolInfo...out..kolId:%s", kolId));
		return ViewMappings.KOL_AUDIT;
	}

	/**
	 * @author Rylan 2015年8月5日 下午11:07:46 @Method: getKolInfoList @Description:
	 * TODO 分页操作,支持查询分页 @param request @param bopsKolParamVO @return @throws
	 */
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public String getKolInfoList(HttpServletRequest request, BopsKolParamVO bopsKolParamVO,Model model) throws ServiceException {
		logger.info("getKolInfoList in..bopsKolParamVO:" + bopsKolParamVO.toString());
		List<BopsKolResultVO> list = bopsKolService.getListBopsKol(bopsKolParamVO);
		for(BopsKolResultVO kol:list){
			kol.setProfilePicKey(constantsProConfig.getPic_domain()+kol.getProfilePicKey());
			
		}
		List<BopsKolCategory> kolCategoryList =bopsKolCategoryService.getKolCategoryList();
		model.addAttribute("list", list);
		model.addAttribute("kolCategoryList", kolCategoryList);
		model.addAttribute("page", bopsKolParamVO);
		return ViewMappings.KOL_LIST;
	}

	/**
	 * @author Rylan 2015年8月5日 下午11:12:08 @Method: deleteKolInfo @Description:
	 * TODO @param kolId @return @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/del/{kolId}", method = RequestMethod.POST)
	public Message deleteKolInfo(@PathVariable(value = "kolId") String kolId) throws ServiceException {
		logger.info(String.format("deleteKolInfo in..kolId:%s", kolId));
		Message success = Message.success();
		bopsKolService.deleteBopsKol(kolId);
		logger.info(String.format("deleteKolInfo in..kolId:%s", kolId));
		return success;
	}

	/**
	 * @author Rylan 2015年8月5日 下午11:13:12 @Method: updateKolInfo
	 * 修改行家信息,根据小马需求 @Description: TODO @param bopsKolVO @return @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Message updateKolInfo(BopsKolUpdateParamVO bopsKolVO, String[] kolCategories, HttpServletRequest request)
			throws ServiceException {
		logger.info("updateKolInfo in..bopsKolVO" + JSON.toJSONString(bopsKolVO));
		Message success = Message.success();
		Set<ConstraintViolation<BopsKolUpdateParamVO>> result = ValidatorUtil.getInstance().validate(bopsKolVO);
		if (result.size() > 0) {
			for (ConstraintViolation<?> c : result) {
				return Message.error(BizReturnCode.FIELD_VALIDATE_ERR, c.getMessage());
			}
		}
		BopsKolParamVO bopsKolParamVO = new BopsKolParamVO();
		bopsKolVO.setCertification(bopsKolVO.getCertification().trim());
		bopsKolVO.setKolIntroduct(bopsKolVO.getKolIntroduct().trim());
		BeanUtils.copyProperties(bopsKolVO, bopsKolParamVO);
		bopsKolParamVO.setAuditorStatus(AuditStatusEnums.WAIT_AUDIT.code);
		bopsKolService.updateBopsKol(bopsKolParamVO);
		logger.info("updateKolInfo in..bopsKolVO" + JSON.toJSONString(bopsKolVO));
		return success;
	}

	/**
	 * @author Rylan 2015年8月5日 下午11:13:12 
	 * @Method: updateKolInfo 
	 * @Description:TODO 审核行家信息 
	 * @param bopsKolVO 
	 * @return @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/audit", method = RequestMethod.POST)
	public Message updateKolStatus(BopsKolParamVO bopsKolVO, HttpServletRequest request) throws ServiceException {
		logger.info("updateKolInfo in..bopsKolVO:" + JSON.toJSONString(bopsKolVO));
		Message success = Message.success();
		BopsKolParamVO bopsKolParamVO = new BopsKolParamVO();
		BeanUtils.copyProperties(bopsKolVO, bopsKolParamVO);
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		bopsKolParamVO.setAuditorId(user.getUserId());
		bopsKolParamVO.setCertification(bopsKolParamVO.getCertification().trim());
		bopsKolParamVO.setKolIntroduct(bopsKolParamVO.getKolIntroduct().trim());
		bopsKolService.auditKolService(bopsKolParamVO);
		logger.info("updateKolInfo in..bopsKolVO:" + JSON.toJSONString(bopsKolVO));
		return success;
	}
	/**
	 * 
	 * @author peiboli 2015年10月15日 上午10:38:48
	 * @Method: updateKolStatus 
	 * @Description: TODO
	 * @param bopsKolVO
	 * @param request
	 * @return
	 * @throws ServiceException 
	 * @throws
	 */
	@RequestMapping(value = "/toAddKolPage", method = RequestMethod.GET)
	public String toAddKolPage(HttpServletRequest request) throws ServiceException {
		logger.info("toAddKolPage");

		return ViewMappings.ADD_KOL_PAGE;
	}

}
