package com.need.oscar.web.controller.kol;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.need.common.validate.ValidatorUtil;
import com.need.domain.common.enums.AuditStatusEnums;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.vo.kol.BopsKolAddParamVO;
import com.need.domain.vo.kol.BopsKolParamVO;
import com.need.domain.vo.kol.BopsKolResultVO;
import com.need.domain.vo.kol.BopsKolUpdateParamVO;
import com.need.oscar.constant.ControllerMappings;
import com.need.service.bops.kol.BopsKolService;
import com.need.service.common.exception.ServiceException;
import com.need.service.constant.BizReturnCode;
import com.need.service.constant.Constants;


@Controller
@RequestMapping(value = ControllerMappings.KOL_MANAGER)
public class KolManagerController {

	private static final Logger logger = Logger.getLogger(KolManagerController.class);

	@Autowired
	private BopsKolService bopsKolService;

	/**
	 * @author Rylan 2015年8月5日 下午10:15:15 @Method: addKolInfo @Description:
	 * TODO @return @throws
	 */
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Message addKolInfo(@RequestBody BopsKolAddParamVO bopsKolVO, HttpServletRequest request)
			throws ServiceException {
		logger.info("addKolInfo...in..bopsKolVO:" + JSON.toJSONString(bopsKolVO));
		Message success = Message.success();
		Set<ConstraintViolation<BopsKolAddParamVO>> result = ValidatorUtil.getInstance().validate(bopsKolVO);
		if(result.size()>0){
			for(ConstraintViolation<?> c:result){
				return Message.error(BizReturnCode.FIELD_VALIDATE_ERR, c.getMessage());
			}
		}
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		bopsKolVO.setPublisherId(user.getUserId());// 创建者id
		bopsKolVO.setKolId(bopsKolVO.getUserId());
		BopsKolParamVO bopsKolParamVO = new BopsKolParamVO();
		bopsKolParamVO.setKolCategories(bopsKolVO.getKolCategories());
		BeanUtils.copyProperties(bopsKolVO, bopsKolParamVO);
		int isSuccess= bopsKolService.addBopsKol(bopsKolParamVO);
		if(isSuccess!=0){
			logger.info("addKolInfo...in..bopsKolVO:" + JSON.toJSONString(bopsKolVO));
			return success;
		}else{
			return Message.error(5014);
		}
		
	}

	/**
	 * @author Rylan 2015年8月5日 下午11:05:18 @Method: getKolInfo @Description:
	 * TODO @param kolId @return @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/{kolId}", method = RequestMethod.GET)
	public Message getKolInfo(@PathVariable(value = "kolId") String kolId) throws ServiceException {
		logger.info(String.format("getKolInfo in..kolId:%s", kolId));
		Message success = Message.success();
		BopsKolResultVO bopsKolResultVO = bopsKolService.getBopsKolById(kolId);
		bopsKolResultVO.setKolCategories(bopsKolResultVO.getKolCategories());
		success.addData("bopsKolResult", bopsKolResultVO);
		logger.info(String.format("getKolInfo...out..kolId:%s", kolId));
		return success;
	}

	/**
	 * @author Rylan 2015年8月5日 下午11:07:46 @Method: getKolInfoList @Description:
	 * TODO 分页操作,支持查询分页 @param request @param bopsKolParamVO @return @throws
	 */
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Message getKolInfoList(HttpServletRequest request, BopsKolParamVO bopsKolParamVO) throws ServiceException {
		logger.info("getKolInfoList in..bopsKolParamVO:" + JSON.toJSONString(bopsKolParamVO));
		Message success = Message.success();
		List<BopsKolResultVO> list = bopsKolService.getListBopsKol(bopsKolParamVO);
		success.addData("list", list);
		success.addData("page", bopsKolParamVO);
		return success;
	}

	/**
	 * @author Rylan 2015年8月5日 下午11:12:08 @Method: deleteKolInfo @Description:
	 * TODO @param kolId @return @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/{kolId}", method = RequestMethod.DELETE)
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
	@RequestMapping(value = "/{kolId}", method = RequestMethod.PUT)
	public Message updateKolInfo(@RequestBody BopsKolUpdateParamVO bopsKolVO, String[] kolCategories,
			HttpServletRequest request) throws ServiceException {
		logger.info("updateKolInfo in..bopsKolVO" + JSON.toJSONString(bopsKolVO));
		Message success = Message.success();
		Set<ConstraintViolation<BopsKolUpdateParamVO>> result = ValidatorUtil.getInstance().validate(bopsKolVO);
		if(result.size()>0){
			for(ConstraintViolation<?> c:result){
				return Message.error(BizReturnCode.FIELD_VALIDATE_ERR, c.getMessage());
			}
		}
		BopsKolParamVO bopsKolParamVO = new BopsKolParamVO();
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
	@RequestMapping(value = "/audit/{kolId}", method = RequestMethod.PUT)
	public Message updateKolStatus(@RequestBody BopsKolParamVO bopsKolVO, HttpServletRequest request)
			throws ServiceException {
		logger.info("updateKolInfo in..bopsKolVO:" + JSON.toJSONString(bopsKolVO));
		Message success = Message.success();
		BopsKolParamVO bopsKolParamVO = new BopsKolParamVO();
		BeanUtils.copyProperties(bopsKolVO, bopsKolParamVO);
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		bopsKolParamVO.setAuditorId(user.getUserId());
		bopsKolService.auditKolService(bopsKolParamVO);
		logger.info("updateKolInfo in..bopsKolVO:" + JSON.toJSONString(bopsKolVO));
		return success;
	}

}
