package com.need.api.web.controller.cheap;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.cheap.CheapService;
import com.need.common.domain.po.api.cheap.CheapBasePO;
import com.need.common.domain.pub.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = ControllerMappings.CHEAP_CLOSED_LIST)
public class GetClosedCheapListController {

	private static final Logger logger = Logger.getLogger(GetClosedCheapListController.class);

	@Autowired
	private CheapService cheapService;
	/**
	 * 
	 * @author peiboli 2015年10月24日 下午1:31:36
	 * @Method: getcheapList 
	 * @Description: TODO用户获取已关闭团便宜列表
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.3",method = RequestMethod.GET)
	public Message getClosedcheapList_1_3(String mobile,Integer pageNum,
			Integer pageSize) {
		Message success = Message.success();
		logger.info("getcheapList.....in ");
		PageHelper.startPage(pageNum, pageSize);
		Page<CheapBasePO> page=(Page<CheapBasePO>)cheapService.getClosedCheapList(mobile);
		List<CheapBasePO> list = page.getResult();
		success.addData("cheapList", list);
		success.addData("totalCount", page.getTotal());
		logger.info("getcheapList.....out ");
		return success;
	}


}
