package com.need.operation.web.controller.ops;

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

import com.alibaba.fastjson.JSON;
import com.need.common.validate.ValidatorUtil;
import com.need.domain.common.enums.AuditStatusEnums;
import com.need.domain.po.bops.ops.BopsOpsTopic;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.vo.ops.PageTopicVO;
import com.need.operation.constant.ControllerMappings;
import com.need.service.bops.ops.OpsTopicService;
import com.need.service.constant.BizReturnCode;
import com.need.service.constant.Constants;

/**
 * 
 * <p>
 * </p>
 * 
 * @author peiboli 2015年8月5日 下午10:18:32
 * @ClassName OpsTopicManager
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年8月5日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.TOPIC)
public class OpsTopicManager {
	private static final Logger logger = Logger.getLogger(OpsTopicManager.class);
	@Autowired
	OpsTopicService opsTopicService;

	/**
	 * 
	 * @author peiboli 2015年8月5日 下午10:30:20 @Method:
	 * selectGoodsSceneList @Description: TODO获得专题列表 @return @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public Message getOpsTopicList(PageTopicVO PageTopicVO,
			@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer pageSize) {
		logger.info("selectOpsTopicList in....params："+JSON.toJSONString(PageTopicVO));
		Message message = Message.success();
		if (page == null ||pageSize == null ) {
			PageTopicVO.setPage(0);
			PageTopicVO.setPageSize(Integer.MAX_VALUE);
		}
		message.addData("list", opsTopicService.getOpsTopicList(PageTopicVO));
		message.addData("page", PageTopicVO);
		logger.info("selectOpsTopicListm...out....params："+JSON.toJSONString(PageTopicVO));
		return message;

	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/{topicId}")
	public Message getOpsTopic(@PathVariable(value = "topicId") String topicId) {
		logger.info("selectOpsTopicList in....");
		Message message = Message.success();
		BopsOpsTopic bopsOpsTopic = opsTopicService.getTopic(topicId);
		message.addData("object", bopsOpsTopic);
		return message;

	}

	/**
	 * 
	 * @author peiboli 2015年8月5日 下午10:40:34 @Method: addGoodsScene @Description:
	 * TODO新增一条数据 @param bopsGoodsScene @param request @return @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public Message addOpstopic(@RequestBody BopsOpsTopic bopsOpsTopic, HttpServletRequest request) {
		logger.info("addOpstopic in....bopsOpsTopic:" + JSON.toJSONString(bopsOpsTopic));
		Message message = Message.success();
//		if (StringUtil.isBlank(bopsOpsTopic.getTopicName())) {
//			return Message.error(5002);
//		}
//		if (StringUtil.isBlank(bopsOpsTopic.getCoverKey())) {
//			return Message.error(5010);
//		}
//		if (StringUtil.isBlank(bopsOpsTopic.getContent())) {
//			return Message.error(5011);
//		}
//		if (StringUtil.isBlank(bopsOpsTopic.getTopicDesc())) {
//			return Message.error(5012);
//		}
		Set<ConstraintViolation<BopsOpsTopic>> result = ValidatorUtil.getInstance().validate(bopsOpsTopic);
		if(result.size()>0){
			for(ConstraintViolation<?> c:result){
				return Message.error(BizReturnCode.FIELD_VALIDATE_ERR, c.getMessage());
			}
		}
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		bopsOpsTopic.setPublisherId(user.getUserId());
		BopsOpsTopic topic = opsTopicService.addOpsTopic(bopsOpsTopic);
		message.addData("object", topic);
		logger.info("addOpstopic...out....bopsOpsTopic:" + JSON.toJSONString(bopsOpsTopic));
		return message;
	}

	/**
	 * 
	 * @author peiboli 2015年8月5日 下午10:52:46 @Method:
	 * deleteGoodsScene @Description: TODO删除一条数据 @param goodsId @return @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.DELETE, value = "/{topicId}")
	public Message deleteOpsTopic(@PathVariable(value = "topicId") String topicId) {
		logger.info(String.format("deleteOpsTopic...in...topicId:%s", topicId));
		Message message = Message.success();
		opsTopicService.deleteOpsTopic(topicId);
		logger.info(String.format("deleteOpsTopic...out...topicId:%s", topicId));
		return message;
	}
	/**
	 * 
	 * @author peiboli 2015年8月5日 下午10:57:08 @Method:
	 * updateGoodsScene @Description: TODO更新一条数据 @param
	 * bopsGoodsScene @return @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT, value = "/{topicId}")
	public Message updateOpsTopic(@RequestBody BopsOpsTopic bopsOpsTopic, HttpServletRequest request) {
		logger.info("updateGoodsScene..in...params:"+JSON.toJSONString(bopsOpsTopic));
		Message message = Message.success();
		Set<ConstraintViolation<BopsOpsTopic>> result = ValidatorUtil.getInstance().validate(bopsOpsTopic);
		if(result.size()>0){
			for(ConstraintViolation<?> c:result){
				return Message.error(BizReturnCode.FIELD_VALIDATE_ERR, c.getMessage());
			}
		}
		bopsOpsTopic.setTopicStatus(AuditStatusEnums.WAIT_AUDIT.code);
		opsTopicService.updateOpsTopic(bopsOpsTopic);
		logger.info("updateGoodsScene..out...params:"+JSON.toJSONString(bopsOpsTopic));
		return message;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT, value = "/audit/{topicId}")
	public Message audit(@RequestBody BopsOpsTopic bopsOpsTopic, HttpServletRequest request) {
		logger.info("audit..in....params:"+JSON.toJSONString(bopsOpsTopic));
		Message message = Message.success();
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		bopsOpsTopic.setAuditorId(user.getUserId());
		opsTopicService.auditOpsTopic(bopsOpsTopic);
		logger.info("audit..out....params:"+JSON.toJSONString(bopsOpsTopic));
		return message;
	}

}
