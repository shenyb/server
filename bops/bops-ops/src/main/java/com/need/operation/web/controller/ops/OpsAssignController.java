package com.need.operation.web.controller.ops;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.need.biz.utils.MD5Util;
import com.need.dao.api.goods.GoodsMainDAO;
import com.need.dao.api.kol.UserKolDAO;
import com.need.dao.api.template.OpsTopicTemplateDAO;
import com.need.dao.bops.kol.BopsKolDAO;
import com.need.dao.bops.ops.BopsOpsDAO;
import com.need.dao.bops.xinhuan.BopsOpsMainDAO;
import com.need.domain.common.enums.CheckStatusEnums;
import com.need.domain.po.api.goods.GoodsMainPO;
import com.need.domain.po.api.kol.UserKol;
import com.need.domain.po.api.template.OpsTopicTemplate;
import com.need.domain.po.bops.kol.BopsKol;
import com.need.domain.po.bops.ops.BopsOps;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.po.bops.xinhuan.BopsOpsMain;
import com.need.domain.pub.Message;
import com.need.domain.vo.ops.PageBopsOpsVO;
import com.need.operation.constant.ControllerMappings;
import com.need.operation.constant.ViewMappings;
import com.need.service.bops.ops.BopsOpsService;
import com.need.service.constant.Constants;
/***
 * 
 * <p>
 * </p>
 * 
 * @author LXD 2015年8月5日 下午5:24:47
 * @ClassName OpsManagerController
 * @Description TODO 运营位管理
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年8月5日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.OPS_ASSIGNMENT)
public class OpsAssignController {
	private static final Logger logger = Logger.getLogger(OpsAssignController.class);
	@Autowired
	private BopsOpsService bopsOpsService;
	@Autowired
	private BopsKolDAO userKolDAO;
	@Autowired
	private GoodsMainDAO goodsMainDAO;

	@Autowired
	private BopsOpsDAO bopsOpsDAO;
	@Autowired
	private OpsTopicTemplateDAO TopicTemplateDAO;
	@Autowired
	private BopsOpsMainDAO bopsOpsMainDAO;


	/**
	 * 
	 * @author LXD 2015年8月6日 上午11:09:05 @Method: addOps @Description: TODO
	 * 分配一个运营位 @param bopsOps @return @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public Message addOps(@RequestBody BopsOps bopsOps) {
		logger.info(String.format("in addOps bopsOps: %s", JSON.toJSONString(bopsOps)));
		
		Message message = Message.success();
		//校验参数是否为空
		if(StringUtils.isBlank(bopsOps.getOpsType())||StringUtils.isBlank(bopsOps.getOpsNumber())||StringUtils.isBlank(bopsOps.getTypeId())){
			Message errorMessage=Message.error(3009);
			return errorMessage;
			
		}
		// 校验此typeId是否已经添加
		BopsOps ops = bopsOpsDAO.queryByTypeId(bopsOps.getTypeId());
		if (ops != null) {
			Message errorMessage=Message.error(3007);
			return errorMessage;
		}
		bopsOps.setOpsId(generateOpsId(bopsOps.getTypeId(),bopsOps.getOpsNumber()));
		bopsOps.setAuditStatus(CheckStatusEnums.WAIT.code);
		switch (bopsOps.getOpsType()) {
		case "KOL":
			BopsKol user = userKolDAO.selectKOL(bopsOps.getTypeId());
			if (user == null) {
				Message errorMessage=Message.error(5028);
				return errorMessage;
			}
			break;
		case "GOODS":
			GoodsMainPO goods = goodsMainDAO.selectByPrimaryKey(bopsOps.getTypeId());
			if (goods == null) {
				Message errorMessage=Message.error(3005);
				return errorMessage;
			}

			break;
		case "TOPIC":
			//OpsTopicPO topic = opsTopicDAO.getById(Integer.parseInt(bopsOps.getTypeId()));
			OpsTopicTemplate tmplate =TopicTemplateDAO.selectById(Integer.parseInt(bopsOps.getTypeId()));
			if (tmplate==null) {
				Message errorMessage=Message.error(3006);
				return errorMessage;
			}
			break;
		}
		bopsOpsService.addBopsOps(bopsOps);
		message.addData("object", bopsOps);
		return message;

	}

	/**
	 * 
	 * @author LXD 2015年8月6日 上午11:09:20 @Method: getOpsInfo @Description: TODO
	 * 根据运营ID获取运营信息 @param opsId @return @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/{opsId}", method = RequestMethod.GET)
	public Message getOpsInfo(@PathVariable(value = "opsId") String opsId) {
		logger.info("in opassign   getOpsInfo opsId: " + opsId);
		Message message = Message.success();
		message.addData("bopsOps", bopsOpsService.getBopsOpsById(opsId));
		return message;
	}

	/**
	 * 
	 * @author LXD 2015年8月6日 上午11:09:42 @Method: updateOpsInfo @Description:
	 * TODO 更新运营位信息 @param bopsOps @return @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT, value = "/{opsId}")
	public Message updateOpsInfo(@RequestBody BopsOps bopsOps, HttpServletRequest request) {
		logger.info(String.format("in updateOpsInfo bopsOps: %s", JSON.toJSONString(bopsOps)));
		Message message = Message.success();
		// 校验此typeId是否已经添加
		BopsOps ops = bopsOpsDAO.queryByTypeId(bopsOps.getTypeId());
		if (ops != null&&!ops.getOpsId().equals(bopsOps.getOpsId())) {
			Message errorMessage=Message.error(3007);
			return errorMessage;
		}
		switch (bopsOps.getOpsType()) {
		case "KOL":
			BopsKol user = userKolDAO.selectKOL(bopsOps.getTypeId());
			if (user == null) {
				Message errorMessage=Message.error(5028);
				return errorMessage;
			}
			break;
		case "GOODS":
			GoodsMainPO goods = goodsMainDAO.selectByPrimaryKey(bopsOps.getTypeId());
			if (goods == null) {
				Message errorMessage=Message.error(3005);
				return errorMessage;
			}

			break;
		case "TOPIC":
			//OpsTopicPO topic = opsTopicDAO.getById(Integer.parseInt(bopsOps.getTypeId()));
			OpsTopicTemplate tmplate =TopicTemplateDAO.selectById(Integer.parseInt(bopsOps.getTypeId()));
			if (tmplate==null) {
				Message errorMessage=Message.error(3006);
				return errorMessage;
			}
			break;
		}
		bopsOps.setAuditStatus(CheckStatusEnums.WAIT.code);
		bopsOpsService.updateBopsOps(bopsOps);
		return message;

	}

	/**
	 * 
	 * @author LXD 2015年8月6日 上午11:10:02 @Method:
	 * deleteBopsGoodsById @Description: TODO 删除运营信息，同步到portal数据库 @param
	 * opsId @return @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/{opsId}")
	public Message deleteBopsGoodsById(@PathVariable(value = "opsId") String opsId) {
		logger.info("in opassign deleteBopsGoodsById opsId: " + opsId);
		Message message = Message.success();
		bopsOpsService.deleteBopsOpsById(opsId);
		return message;
	}

	/**
	 * 
	 * @author LXD 2015年8月6日 上午11:10:18 @Method: getPageOfOpsInfo @Description:
	 * TODO 获取运营分页信息 @param currentPage @param pageSize @return @throws
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String getPageOfOpsInfo(PageBopsOpsVO pageVo,  Model model) {
		logger.info("in opassign getPageOfOpsInfo pageVo" + pageVo.toString());
		
		List<BopsOps> bopsOpsList = new ArrayList<BopsOps>();
        
		List<BopsOpsMain> opsList=bopsOpsMainDAO.queryAllOps();
		model.addAttribute("opsList", opsList);
		bopsOpsList = bopsOpsService.getPageOfBopsOps(pageVo);
		model.addAttribute("bopsOpsList", bopsOpsList);
		model.addAttribute("page", pageVo);
		return ViewMappings.OPS_CHECK_LIST;
	}
	
	/****
	 * 
	 * @author LXD 2015年8月6日 上午11:08:16 @Method: checkOps @Description: TODO
	 * 审核运营位 @param opsId @param checkStatus @return @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/audit/{opsId}")
	public Message checkOps(@PathVariable(value = "opsId")String opsId,String auditStatus,HttpServletRequest request) {
		logger.info(String.format("in opassign checkOps: %s", opsId+auditStatus));
		
		Message message = Message.success();
		BopsOps ops = bopsOpsService.getBopsOpsById(opsId);
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		ops.setAuditorId(user.getUserId());
		if (auditStatus != null && "SUCCESS".equals(auditStatus)) {
			ops.setAuditStatus(CheckStatusEnums.SUCCESS.code);

		} else {

			ops.setAuditStatus(CheckStatusEnums.FAIL.code);
		}

		bopsOpsService.auditBopsOps(ops);
		return message;

	}
	
    public static String generateOpsId(String topicId,String opsId) {
    	String SPLIT_SIGN = "|";
    	 SimpleDateFormat DF_LONG_DATETIME = new SimpleDateFormat("yyyyMMddHHmm");
    	 String dateFormat = DF_LONG_DATETIME.format(Calendar.getInstance().getTime());
    	long timefator=Calendar.getInstance().getTimeInMillis();
        String md5Random=MD5Util.MD5Encode(topicId+SPLIT_SIGN+timefator+SPLIT_SIGN+ opsId).substring(0,13);
			
		
		return dateFormat+md5Random;	
    }
    
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
	@RequestMapping(value = "/opsNumber/{opsId}", method = RequestMethod.GET)
	public Message getByopsNumber(@PathVariable(value = "opsId") String opsId) {
		logger.info("in opassign   getOpsInfo opsId: " + opsId);
		Message message = Message.success();
		message.addData("bopsOps", bopsOpsService.getOpsByopsNumber(opsId));
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
	@RequestMapping(value = "/opsNumber/{opsId}", method = RequestMethod.PUT)
	public Message editOps(@RequestBody BopsOps bopsOps, HttpServletRequest request) {
		logger.info("in opassign   editOps bopsOps: " + bopsOps.toString());
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		bopsOps.setPublisherId(user.getUserId());
		return bopsOpsService.editOps(bopsOps);
	}

}
