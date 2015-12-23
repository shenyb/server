package com.need.operation.web.controller.xinhuan;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.domain.po.bops.basedata.BopsKolCategory;
import com.need.domain.po.bops.xinhuan.BopsOpsMain;
import com.need.domain.pub.Message;
import com.need.domain.pub.Page;
import com.need.domain.vo.kol.BopsKolCategoryParamVO;
import com.need.domain.vo.kol.BopsKolParamVO;
import com.need.domain.vo.kol.BopsKolResultVO;
import com.need.domain.vo.xinhuan.OpsXinhuanParamVO;
import com.need.operation.constant.ControllerMappings;
import com.need.operation.constant.ViewMappings;
import com.need.service.bops.kol.BopsKolService;
import com.need.service.bops.kolcategory.BopsKolCategoryService;
import com.need.service.bops.xinhuan.OpsXinhuanService;
import com.need.operation.pub.ConstantsProConfig;

/**
 * @author xiehao 2015年9月10日 上午11:18:17
 * @ClassName OpsXinhuanManagerController
 * @Description TODO 运营位管理
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: xiehao 2015年9月10日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.OPS_POSITION_MANAGER)
public class OpsXinhuanManagerController {
	
	private static final Logger logger = Logger.getLogger(OpsXinhuanManagerController.class);
	
	@Autowired
	private OpsXinhuanService opsXinhuanService;
	@Autowired
	private ConstantsProConfig ConstantsProConfig;
	@Autowired
	private BopsKolCategoryService bopsKolCategoryService;
	
	/**
	 * @author xiehao 2015年9月10日 上午11:18:38
	 * @Method: addOpsPosition 
	 * @Description: TODO 添加运营位
	 * @param xinhuan
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public Message addOpsPosition(OpsXinhuanParamVO xinhuan){
		logger.info("in OpsXinhuanManagerController addOpsPosition xinhuan: " + xinhuan);
		
		return opsXinhuanService.addNewOpsPosition(xinhuan);
	}
	
	/**
	 * @author xiehao 2015年9月10日 上午11:19:00
	 * @Method: queryOpsPosition 
	 * @Description: TODO 查询所有的运营位
	 * @return 
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.GET ,value="/page")
	public String queryOpsPosition(OpsXinhuanParamVO categoryPage,  Model model){
		logger.info("in OpsXinhuanManagerController queryOpsPosition");
		int total = opsXinhuanService.getcount(categoryPage);
		categoryPage.setTotal(total);
		List<BopsOpsMain> list=opsXinhuanService.queryOpsPosition(categoryPage);
		BopsKolCategoryParamVO categoryParamVO =new BopsKolCategoryParamVO();
		categoryParamVO.setPageSize(Integer.MAX_VALUE);
		List<BopsKolCategory> categoryList =bopsKolCategoryService.getKolCategoryList(categoryParamVO.getSearch(), categoryParamVO);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("list", list);
		model.addAttribute("page", categoryPage);
		model.addAttribute("picDomain", ConstantsProConfig.getPic_domain());
		return ViewMappings.OPS_LIST;
	}
	
	/**
	 * @author xiehao 2015年9月10日 上午11:21:11
	 * @Method: updateOpsPosition 
	 * @Description: TODO 更新运营位信息
	 * @param xinhuan
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/edit")
	public Message updateOpsPosition(OpsXinhuanParamVO xinhuan){
		logger.info("in OpsXinhuanManagerController updateOpsPosition xinhuan: " + xinhuan);
		return opsXinhuanService.updateOpsPosition(xinhuan);
	}

}
