package com.need.api.web.controller.goods;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.goods.GoodsCategoryService;
import com.need.common.domain.pub.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/***
 * 
 * <p></p>
 * @author LXD 2015年11月30日 上午11:21:21
 * @ClassName GetGoodsCategoryList
 * @Description TODO 展示检索分类
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年11月30日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.GOODS_CATEGORY_LIST)
public class GetGoodsCategoryListController {

	private static final Logger logger = Logger.getLogger(GetGoodsCategoryListController.class);
	
	@Autowired
	private GoodsCategoryService goodsCategoryService;
	
	
	@ResponseBody
	@RequestMapping(params = "apiVersion=2.0", method = RequestMethod.GET)
	public Message getGoodsCatagoryList_V2(){
		logger.info("getGoodsCatagoryList_V2 in GetGoodsCategoryListController");
		 Message success= goodsCategoryService.getAllCategory();
		 return success;
		 
	}
	

}
