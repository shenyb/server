package com.need.api.web.controller.kol;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.kol.KolCategoryService;
import com.need.common.domain.po.api.kol.KolCategoryPO;
import com.need.common.domain.pub.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = ControllerMappings.GETKOLCATEGORYLIST)
public class GetKolCategoryListController {
	
	private static final Logger logger = Logger.getLogger(GetKolCategoryListController.class);
	
	@Autowired
	private KolCategoryService kolCategoryService;

	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0",method = RequestMethod.GET)
	public Message getUserInfo() {
		Message success = Message.success();
		logger.info("getUserInfo.....");
		List<KolCategoryPO> kolCategoryPO= kolCategoryService.kolCategoryList();
		success.addData("categoryList", kolCategoryPO);
		success.addData("defaultCatId", kolCategoryPO.get(0).getCategoryId());
		return success;
	}


}
