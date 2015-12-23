package com.need.api.web.controller.cheap;

import com.github.pagehelper.PageHelper;
import com.need.api.constant.ControllerMappings;
import com.need.common.core.pub.ConstantsProConfig;
import com.need.common.core.service.cheap.CheapService;
import com.need.common.domain.po.api.cheap.CheapBasePO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.pub.Page;
import com.need.common.domain.vo.cheap.CheapBaseBymobileVO;
import com.need.utils.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = ControllerMappings.CHEAP_LIST)
public class GetCheapListController {

	private static final Logger logger = Logger.getLogger(GetCheapListController.class);

	@Autowired
	private CheapService cheapService;
	@Autowired
	private ConstantsProConfig constantsProConfig;
	/**
	 * 
	 * @author peiboli 2015年10月24日 下午1:31:36
	 * @Method: getcheapList 
	 * @Description: TODO获取团便宜列表
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.3",method = RequestMethod.GET)
	public Message getcheapList_1_3(Integer pageNum,
			Integer pageSize,String mobile) {
		Message success = Message.success();
		logger.info("getcheapList.....in ");			
		if(!StringUtil.isBlank(mobile)){
		Page cheapPage = new Page();
		if(pageNum!=null){
			cheapPage.setCurrentPage(pageNum);
		}
		if(pageSize!=null){
			cheapPage.setPageSize(pageSize);
		}
		List<CheapBaseBymobileVO> list=cheapService.getCheapListByMobile(mobile,cheapPage);
//		if(page!=null){
//		List<CheapBaseBymobileVO> list = page.getResult();
		success.addData("cheapList", list);
		success.addData("topPicKey", constantsProConfig.getCheapListTopPicKey());
		success.addData("totalCount", cheapPage.getTotalCount());
//		}
		}else{
		PageHelper.startPage(pageNum, pageSize);
		com.github.pagehelper.Page<CheapBasePO> page=(com.github.pagehelper.Page<CheapBasePO>)cheapService.getCheapList();
		List<CheapBasePO> list = page.getResult();
		success.addData("cheapList", list);
		success.addData("topPicKey", constantsProConfig.getCheapListTopPicKey());
		success.addData("totalCount", page.getTotal());
		}
		
		
		logger.info("getcheapList.....out ");
		return success;
	}


}
