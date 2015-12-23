package com.need.operation.web.controller.role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.need.common.validate.ValidatorUtil;
import com.need.domain.po.bops.auth.BopsAuth;
import com.need.domain.po.bops.role.BopsRole;
import com.need.domain.pub.Message;
import com.need.domain.vo.auth.BopsAuthVO;
import com.need.domain.vo.role.PageBopsRoleVO;
import com.need.operation.constant.ControllerMappings;
import com.need.operation.constant.ViewMappings;
import com.need.service.bops.auth.BopsAuthService;
import com.need.service.bops.role.BopsRoleService;
import com.need.service.constant.BizReturnCode;

/**
 * @author xiehao 2015年8月4日 下午3:09:53
 * @ClassName RoleManagerController
 * @Description TODO Bops角色管理，对角色表进行增删改查操作，
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: xiehao 2015年8月4日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.ROLE_MANAGER)
public class RoleManagerController {

	private static final Logger logger = Logger.getLogger(RoleManagerController.class);

	@Autowired
	private BopsRoleService bopsRoleService;
	@Autowired
	private BopsAuthService bopsAuthService;

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public Message addRole(BopsRole bopsRole) {
		logger.info("in role addRole bopsRole: " + bopsRole);
		Message message = Message.success();
		Set<ConstraintViolation<BopsRole>> result = ValidatorUtil.getInstance().validate(bopsRole);
		if (result.size() > 0) {
			for (ConstraintViolation<?> c : result) {
				// return Message.error(BizReturnCode.FIELD_VALIDATE_ERR,
				// c.getMessage());
				message.setMsg("增加角色失败！");
				return message;
			}
		}
		message.addData("object", bopsRoleService.addBopsRole(bopsRole));
		message.setMsg("增加角色成功！");
		logger.info("out role addRole bopsRole: " + bopsRole);
		return message;
	}

	// @ResponseBody
	// @RequestMapping(method = RequestMethod.POST)
	// public Message addRole(@RequestBody RoleParamVO bopsRole){
	// logger.info("in role addRole bopsRole: " + bopsRole);
	// Message message = Message.success();
	// message.addData("object", bopsRoleService.addNewBopsRle(bopsRole));
	// message.setMsg("增加角色成功");
	// logger.info("out role addRole bopsRole: " + bopsRole);
	// return message;
	// }

	@RequestMapping(method = RequestMethod.GET, value = "/detail/{tag}/{roleId}")
	public String getRoleInfo(@PathVariable("roleId") String roleId, @PathVariable("tag") String tag,
			HttpServletRequest req, Model model) {
		logger.info("in role getRoleInfo roleId: " + roleId);
		// 对list进行分组排序
		List<BopsAuth> list = bopsAuthService.getAll();
		List<List<BopsAuth>> lists = new ArrayList<List<BopsAuth>>();
		lists.clear();
		List<String> strs = new ArrayList<String>();
		strs.clear();
		int k = -1;
		int min = 0;
		int max = 0;
		for (int i = 0; i < list.size(); i++) {
			String name = list.get(i).getModuleName();
			if(i==0){
				strs.add(name);
			}else{
			if(!list.get(i).getModuleName().equals(list.get(i-1).getModuleName())){
			strs.add(list.get(i).getModuleName());	
			}}
			if (list.get(i).getAuthId() > max) {
				max = list.get(i).getAuthId();
			}
			if (list.get(i).getAuthId() < min) {
				min = list.get(i).getAuthId();
			}
			// names[k+1]= name;
			
			if (lists.size() <= 0 || !lists.get(k).get(0).getModuleName().equals((name))) {
				List<BopsAuth> li = new ArrayList<BopsAuth>();
				li.clear();
				li.add(list.get(i));
				lists.add(li);

				k++;
			} else {
				lists.get(k).add(list.get(i));
			}

		}

		List<Map<String, List<BopsAuth>>> ja = new ArrayList<Map<String, List<BopsAuth>>>();
		for (int j = 0; j < lists.size(); j++) {
			Map<String, List<BopsAuth>> jmap = new HashMap<String, List<BopsAuth>>();
			jmap.clear();
			jmap.put(strs.get(j), lists.get(j));
			ja.add(jmap);
		}

		BopsRole bp = bopsRoleService.getBopsRoleById(Integer.parseInt(roleId));
		bp.setAuthIds(bp.getAuthIds().replaceAll("\"", ""));
		model.addAttribute("role", JSONObject.toJSON(bp));
		model.addAttribute("result", ja);
		model.addAttribute("min", min);
		model.addAttribute("max", max);
		System.out.println(ja);
		logger.info("out role getRoleInfo roleId: " + roleId);
		if ("page".equals(tag)) {
			// Message message = Message.success();
			// message.addData("role",
			// bopsRoleService.getBopsRoleById(Integer.parseInt(roleId)));
			req.getSession().setAttribute("role_action", "page");
		} else if ("put".equals(tag)) {
			req.getSession().setAttribute("role_action", "put");
		}
		// return message;
		return ViewMappings.SYSTEM_ROLE_DETAIL;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/put/{roleId}")
	public Message updateRoleInfo(BopsRole bopsRole) {
		logger.info("in role updateRoleInfo bopsRole: " + bopsRole);
		Message message = Message.success();
		message.addData("object", bopsRoleService.updateBopsRole(bopsRole));
		logger.info("out role updateRoleInfo bopsRole: " + bopsRole);
		return message;
		// return ViewMappings.SYSTEM_ROLE_LIST;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/{roleId}")
	public Message deleteRole(@PathVariable Integer roleId) {
		logger.info("in role deleteRole roleId: " + roleId);
		Message message = Message.success();
		bopsRoleService.deleteBopsRoleById(roleId);
		logger.info("out role deleteRole roleId: " + roleId);
		return message;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/page")
	public String getPageOfRole(PageBopsRoleVO pageBopsRoleVO, Model model) {
		logger.info("in role getPageOfRole pageBopsRoleVO: " + pageBopsRoleVO.toString());

		model.addAttribute("list", bopsRoleService.getPageOfBopsRole(pageBopsRoleVO));
		model.addAttribute("page", pageBopsRoleVO);
		logger.info("out role getPageOfRole pageBopsRoleVO: " + pageBopsRoleVO.toString());
		return ViewMappings.SYSTEM_ROLE_LIST;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/todetail")
	public String toAddPageRoleDetail(HttpServletRequest req, Model model) {
		logger.info("toPageRoleDetail---------------- ");
		// 对list进行分组排序
		List<BopsAuth> list = bopsAuthService.getAll();
		List<List<BopsAuth>> lists = new ArrayList<List<BopsAuth>>();
		lists.clear();
		List<String> strs = new ArrayList<String>();
		strs.clear();
		int k = -1;
		int min = 0;
		int max = 0;
		for (int i = 0; i < list.size(); i++) {
			String name = list.get(i).getModuleName();
			if (list.get(i).getAuthId() > max) {
				max = list.get(i).getAuthId();
			}
			if (list.get(i).getAuthId() < min) {
				min = list.get(i).getAuthId();
			}
			// names[k+1]= name;
			strs.add(name);
			if (lists.size() <= 0 || !lists.get(k).get(0).getModuleName().equals((name))) {
				List<BopsAuth> li = new ArrayList<BopsAuth>();
				li.clear();
				li.add(list.get(i));
				lists.add(li);

				k++;
			} else {
				lists.get(k).add(list.get(i));
			}

		}

		List<Map<String, List<BopsAuth>>> ja = new ArrayList<Map<String, List<BopsAuth>>>();
		for (int j = 0; j < lists.size(); j++) {
			Map<String, List<BopsAuth>> jmap = new HashMap<String, List<BopsAuth>>();
			jmap.clear();
			jmap.put(strs.get(j), lists.get(j));
			ja.add(jmap);
		}
		req.getSession().setAttribute("role_action", "add");
		model.addAttribute("result", ja);
		model.addAttribute("min", min);
		model.addAttribute("max", max);
		return ViewMappings.SYSTEM_ROLE_DETAIL;
	}
}
