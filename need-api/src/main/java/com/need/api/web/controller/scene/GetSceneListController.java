package com.need.api.web.controller.scene;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.scene.GoodsSceneService;
import com.need.common.domain.po.api.goods.GoodsScenePO;
import com.need.common.domain.pub.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = ControllerMappings.GETGOODSSCENELIST)
public class GetSceneListController {
	
	private static final Logger logger = Logger.getLogger(GetSceneListController.class);
	
	@Autowired
	private GoodsSceneService goodsSceneService;

	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0",method = RequestMethod.GET)
	public Message getUserInfo() {
		Message success = Message.success();
		logger.info("getUserInfo.....");
		List<GoodsScenePO> sceneList= goodsSceneService.getGoodsSceneList();
		success.addData("sceneList", sceneList);
		return success;
	}


}
