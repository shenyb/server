package com.need.operation.web.controller.ops;

import java.text.ParseException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.domain.po.bops.ops.BopsOps;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.operation.constant.ControllerMappings;
import com.need.operation.pub.ConstantsProConfig;
import com.need.service.bops.ops.BopsOpsService;
import com.need.service.constant.Constants;
import com.need.utils.DateUtil;

@Controller
@RequestMapping(value = ControllerMappings.OPS_ASSIGNMENT_TOPIC)
public class OpsAssignTopicController {
	private static final Logger logger = Logger.getLogger(OpsAssignController.class);
	@Autowired
	private BopsOpsService bopsOpsService;
	@Autowired
	private ConstantsProConfig ConstantsProConfig;
	/**
	 * 
	 * @author LXD 2015年9月14日 上午11:34:34
	 * @Method: getByopsNumber 
	 * @Description: TODO need1.1 根据运营位ID回显运营数据
	 * @param opsId
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/{opsNumber}", method = RequestMethod.GET)
	public Message getByopsNumber(@PathVariable(value = "opsNumber") String opsNumber) {
		logger.info("in OpsAssignTopicController   getByopsNumber opsId: " + opsNumber);
		Message message = Message.success();
		message.addData("bopsOps", bopsOpsService.getOpsByopsNumber(opsNumber));
		return message;
	}
	
	/***
	 * 
	 * @author LXD 2015年9月14日 上午11:35:12
	 * @Method: editOps 
	 * @Description: TODO need1.1 保存或编辑运营信息
	 * @param opsId
	 * @return 
	 * @throws
	 */
	
	@ResponseBody
	@RequestMapping( method = RequestMethod.POST)
	public Message editOps(BopsOps bopsOps, HttpServletRequest request) {
		logger.info("in OpsAssignTopicController   editOps bopsOps: " + bopsOps.toString());
	
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		if(StringUtils.isNotBlank(bopsOps.getEffDateString())&&StringUtils.isNotBlank(bopsOps.getExpDateString())){
		try {
			bopsOps.setEffDate(DateUtil.formatStrToDate(bopsOps.getEffDateString(), "yyyy-MM-dd HH:mm:ss"));
        } catch (ParseException ex) {
        	logger.error(ex.getMessage(), ex);
        }
        try {
        	bopsOps.setExpDate(DateUtil.formatStrToDate(bopsOps.getExpDateString(), "yyyy-MM-dd HH:mm:ss"));
        } catch (ParseException ex) {
        	logger.error(ex.getMessage(), ex);
        }
		}else{
			 bopsOps.setEffDate(Calendar.getInstance().getTime());
			
			 try {
		        	bopsOps.setExpDate(DateUtil.formatStrToDate(ConstantsProConfig.getExpDate(), "yyyy-MM-dd HH:mm:ss"));
		        } catch (ParseException ex) {
		        	logger.error(ex.getMessage(), ex);
		        }
		}
		bopsOps.setPublisherId(user.getUserId());
		return bopsOpsService.editOps(bopsOps);
	}
	
}
