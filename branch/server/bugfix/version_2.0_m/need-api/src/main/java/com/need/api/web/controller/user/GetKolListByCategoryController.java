package com.need.api.web.controller.user;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.user.UserService;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.user.KolVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/**
 * 
 * <p>
 * </p>
 * 
 * @author peiboli 2015年8月13日 下午10:06:22
 * @ClassName GetKolListByCategoryController
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年8月13日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.GET_KOL_LIST)
public class GetKolListByCategoryController {
	private static final Logger logger = Logger.getLogger(GetKolListByCategoryController.class);

	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.GET)
	public Message getKolByCategoryId(@RequestParam(required = true) String kolCategoryId,
			@RequestParam(required = false) Integer pageNum, @RequestParam(required = false) Integer pageSize) {
		Message success = Message.success();
		logger.info("getKolByCategoryId...in..userId :" + kolCategoryId);
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		PageHelper.startPage(pageNum, pageSize);
		Page<KolVo> page = (Page<KolVo>) userService.getKolList(kolCategoryId);
		List<KolVo> kolInfoVolist = page.getResult();
		success.addData("kolList", kolInfoVolist);
		success.addData("total", page.getTotal());
		logger.info("getKolByCategoryId..out...userId :" + kolCategoryId);
		return success;
	}
}
