package com.need.operation.web.controller.scene;

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
import com.need.dao.bops.basedata.BopsSceneDAO;
import com.need.domain.po.bops.basedata.BopsScene;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.pub.Page;
import com.need.operation.constant.ControllerMappings;
import com.need.service.bops.scene.BopsSceneService;
import com.need.service.constant.BizReturnCode;
import com.need.service.constant.Constants;
import com.need.utils.StringUtil;

/**
 * 
 * <p>
 * </p>
 * 
 * @author peiboli 2015年8月4日 上午10:20:07
 * @ClassName SceneManagerController
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年8月4日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.SCENE)
public class SceneManagerController {

	private static final Logger logger = Logger.getLogger(SceneManagerController.class);
	@Autowired
	private BopsSceneService bopsSceneService;

	@Autowired
	private BopsSceneDAO bopsSceneDAO;

	/**
	 * 
	 * @author peiboli 2015年8月4日 上午10:20:26 @Method: addBopsScene @Description:
	 * TODO插入操作 @param bopsScene @param request @return @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public Message addBopsScene(@RequestBody BopsScene bopsScene, HttpServletRequest request) {
		logger.info("addBopsScene in...."+JSON.toJSONString(bopsScene));
		Message message = Message.success();
		if (StringUtil.isBlank(bopsScene.getSceneName())) {
			return Message.error(5005);
		}
		int isexist = bopsSceneDAO.selectCategoryName(bopsScene.getSceneName());
		if (isexist == 1) {//1代表场景分类名存在
			return Message.error(5006);
		}
		Set<ConstraintViolation<BopsScene>> result = ValidatorUtil.getInstance().validate(bopsScene);
		if(result.size()>0){
			for(ConstraintViolation<?> c:result){
				return Message.error(BizReturnCode.FIELD_VALIDATE_ERR, c.getMessage());
			}
		}
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		bopsScene.setPublisherId(user.getUserId());
		BopsScene scene = bopsSceneService.addScene(bopsScene);
		message.addData("object", scene);
		logger.info("addBopsScene...out..."+JSON.toJSONString(bopsScene));
		return message;

	}

	/**
	 * 
	 * @author peiboli 2015年8月4日 上午10:20:38 @Method:
	 * selectBopsScene @Description: TODO获得场景列表 @return @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public Message selectBopsScene(@RequestParam(required = false) String search,
			@RequestParam(required = false) Integer page, 
			@RequestParam(required = false) Integer pageSize) {
		logger.info(String.format("selectBopsScene..in...params:%s %d %d ", search,page,pageSize));
		Message message = Message.success();
		Page kolcatPage = new Page();
		if (page == null || "".equals(page) || pageSize == null || "".equals(pageSize)) {
			kolcatPage.setPageSize(Integer.MAX_VALUE);
		} else {
			kolcatPage.setPage(page);
			kolcatPage.setPageSize(pageSize);
		}
		message.addData("list", bopsSceneService.getSceneList(search, kolcatPage));
		message.addData("page", kolcatPage);
		logger.info(String.format("selectBopsScene..out...params:%s %d %d ", search,page,pageSize));
		return message;

	}

	/**
	 * 
	 * @author peiboli 2015年8月4日 上午10:21:00 @Method:
	 * deleteBopsScene @Description: TODO删除数据 @param sceneId @return @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.DELETE, value = "/{sceneId}")
	public Message deleteBopsScene(@PathVariable(value = "sceneId") String sceneId) {
		logger.info(String.format("deleteBopsScene...in....sceneId:", sceneId));
		Message message = Message.success();
		bopsSceneService.deleteBopsScene(sceneId);
		logger.info(String.format("deleteBopsScene...out....sceneId:", sceneId));
		return message;

	}

	/**
	 * 
	 * @author peiboli 2015年8月4日 上午10:21:11 @Method:
	 * updateBopsScene @Description: TODO更新操作 @param bopsScene @return @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT, value = "/{sceneId}")
	public Message updateBopsScene(@RequestBody BopsScene bopsScene) {
		logger.info("updateBopsScene...in...."+JSON.toJSONString(bopsScene));
		Message message = Message.success();
		if (StringUtil.isBlank(bopsScene.getSceneName())) {
			return Message.error(5005);
		}
		int isexist = bopsSceneDAO.selectCategoryName(bopsScene.getSceneName());
		if (isexist == 1) {//1代表场景分类名存在
			return Message.error(5006);
		}
		Set<ConstraintViolation<BopsScene>> result = ValidatorUtil.getInstance().validate(bopsScene);
		if(result.size()>0){
			for(ConstraintViolation<?> c:result){
				return Message.error(BizReturnCode.FIELD_VALIDATE_ERR, c.getMessage());
			}
		}
		bopsSceneService.updateBopsScene(bopsScene);
		logger.info("updateBopsScene...out...."+JSON.toJSONString(bopsScene));
		return message;

	}

}
