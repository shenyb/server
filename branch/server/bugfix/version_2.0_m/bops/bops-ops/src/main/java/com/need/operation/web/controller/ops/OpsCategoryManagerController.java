package com.need.operation.web.controller.ops;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.dao.bops.ops.BopsIndexCategoryDAO;
import com.need.domain.po.bops.goods.BopsGoodsCategoriesPO;
import com.need.domain.po.bops.ops.BopsIndexCategoryPO;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.vo.goods.BopsGoodsCategoriesVO;
import com.need.domain.vo.ops.BopsIndexCategoryParam;
import com.need.operation.constant.ControllerMappings;
import com.need.operation.constant.OPSBizReturnCode;
import com.need.operation.constant.ViewMappings;
import com.need.operation.pub.ConstantsProConfig;
import com.need.service.bops.ops.OpsCategoryService;
import com.need.service.constant.Constants;


@Controller
@RequestMapping(ControllerMappings.OPS_INDEX_CATEGORY)
public class OpsCategoryManagerController {
	
	private static final Logger logger = Logger.getLogger(OpsCategoryManagerController.class);
	
	@Autowired
	private OpsCategoryService opsCategoryService;
	@Autowired
	private BopsIndexCategoryDAO bopsIndexCategoryDAO;
	@Autowired
	private ConstantsProConfig ConstantsProConfig;
	
	/***
	 * 
	 * @author LXD 2015年12月2日 下午5:13:35
	 * @Method: getCategoriesList 
	 * @Description: TODO 查询所有分类
	 * @param bopsGoodsCategoriesVO
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/page")
	public String getCategoriesList(BopsIndexCategoryParam bopsGoodsCategoriesVO, Model model) {
		logger.info("in BopsCategoriesManagerController getCategoriesList bopsGoodsCategoriesVO: " + bopsGoodsCategoriesVO);
		//获取一级分类
		List<BopsIndexCategoryParam> levelList=bopsIndexCategoryDAO.selectCategoryLevel("1");
		if(levelList.size()>0){
			for (BopsIndexCategoryParam categoriesOne : levelList) {
				//获取子集分类
				List<BopsIndexCategoryParam> levelTwoList=bopsIndexCategoryDAO.selectPreLevel(categoriesOne.getCategoryId().toString());
				categoriesOne.setLevelTwoList(levelTwoList);
			}
		}
		model.addAttribute("levelList", levelList);
		model.addAttribute("imgUrl", ConstantsProConfig.getPic_domain());
		return ViewMappings.INDEX_CATEGORY_LIST;
	}
	
	/**
	 * 
	 * @author LXD 2015年12月2日 下午5:13:54
	 * @Method: toAddCategory 
	 * @Description: TODO 跳转新增页面
	 * @param categorityLevel
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/toAddCategory")
	public String toAddCategory(String categorityLevel,Model model) {
		return ViewMappings.INDEX_TO_ADD_CATEGORY;
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "addCategory")
	public Message addBopsGoodsManager(BopsIndexCategoryParam categoriesVO, HttpServletRequest request)
			throws NumberFormatException, ParseException {
		logger.info("in addBopsGoodsManager BopsCategoriesManagerController categoriesVO: " + categoriesVO);
		Message message = Message.success();
		//名称不能为空
		if (StringUtils.isEmpty(categoriesVO.getCategoryName())) {
			Message error = Message.error(OPSBizReturnCode.CATEGORY_NAME_NOT_NULL);
			return error;
		}
		//一级分类暂时不加图片
		if("1".equals(categoriesVO.getCategoryLevel())){
			categoriesVO.setCategoryPicKey(" ");
			
		}else{
			if (StringUtils.isEmpty(categoriesVO.getCategoryPicKey())) {
				Message error = Message.error(OPSBizReturnCode.CATEGORY_PICKEY_EXIST);
				return error;
			}
			
		}
		// 商品名称是否已被使用
		BopsIndexCategoryPO bopsGoodsCategoriesPO=bopsIndexCategoryDAO.selectBycategoryName(categoriesVO);
		if(null != bopsGoodsCategoriesPO){
			Message error = Message.error(OPSBizReturnCode.CATEGORY_NAME_EXIST);
			return error;
		}
		//当前登录用户
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		categoriesVO.setCreateId(user.getUserId());
		opsCategoryService.insertNewCategory(categoriesVO);
		return message;
	}
	/**
	 * 
	 * @author LXD 2015年12月2日 下午6:08:02
	 * @Method: getNextLevel 
	 * @Description: TODO 获取子分类
	 * @param categoryLevel
	 * @param parentId
	 * @param model
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/getNextLevel")
	public Message getNextLevel(String categoryLevel,String parentId,Model model) {
		logger.info("in goods getNextLevel categoryLevel.....parentId: " + categoryLevel+"........."+parentId);
		BopsIndexCategoryPO bopsIndexCategoriesPO=new BopsIndexCategoryPO();
		bopsIndexCategoriesPO.setCategoryLevel(categoryLevel);
		if(null != parentId){
			bopsIndexCategoriesPO.setParentId(Integer.parseInt(parentId));
		}
		List<BopsIndexCategoryPO> levelList=bopsIndexCategoryDAO.selectCategoryLevelByLevel(bopsIndexCategoriesPO);
		model.addAttribute("levelList", levelList);
		Message message = Message.success();
		message.addData("list", levelList);
		return message;
	}
	/***
	 * 
	 * @author LXD 2015年12月2日 下午7:40:16
	 * @Method: getDetailByCategoryId 
	 * @Description: TODO 分类详情
	 * @param categoryId
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/categoryDetial", method = RequestMethod.GET)
	public String getDetailByCategoryId(String categoryId, Model model) {
		BopsIndexCategoryParam indexCategoryVO=opsCategoryService.getBycategoryId(categoryId);
		model.addAttribute("bopsGoodsCategoriesVO", indexCategoryVO);
		model.addAttribute("imgUrl", ConstantsProConfig.getPic_domain());
		return ViewMappings.INDEX_CATEGORY_DETAIL;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/toEditCategory")
	public String toModCategory(String categoryId,Model model) {
		logger.info("in BopsCategoriesManagerController toModCategory categoryId: " + categoryId);
		BopsIndexCategoryParam indexCategoryVO=opsCategoryService.toUpdateCategory(categoryId);
		model.addAttribute("bopsGoodsCategoriesVO", indexCategoryVO);
		model.addAttribute("imgUrl", ConstantsProConfig.getPic_domain());
		return ViewMappings.INDEX_CATEGORY_EDIT;
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "editCategory")
	public Message modBopsCategoryManager(BopsIndexCategoryParam categoriesVO, HttpServletRequest request)
			throws NumberFormatException, ParseException {
		logger.info("in goods modBopsCategoryManager categoriesVO: " + categoriesVO);
		Message message = Message.success();
		//名称不能为空
			if (StringUtils.isEmpty(categoriesVO.getCategoryName())) {
				Message error = Message.error(OPSBizReturnCode.CATEGORY_NAME_NOT_NULL);
				return error;
			}
			//一级分类暂时不加图片
			if("1".equals(categoriesVO.getCategoryLevel())){
				categoriesVO.setCategoryPicKey(" ");
				
			}else{
				if (StringUtils.isEmpty(categoriesVO.getCategoryPicKey())) {
					Message error = Message.error(OPSBizReturnCode.CATEGORY_PICKEY_EXIST);
					return error;
				}
				
			}
			// 商品名称是否已被使用
			BopsIndexCategoryPO bopsGoodsCategoriesPO=bopsIndexCategoryDAO.selectBycategoryName(categoriesVO);
			if(null != bopsGoodsCategoriesPO){
				if(bopsGoodsCategoriesPO.getCategoryId()!=categoriesVO.getCategoryId()){
				Message error = Message.error(OPSBizReturnCode.CATEGORY_NAME_EXIST);
				return error;
				}
			}
		//当前登录用户
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		categoriesVO.setUpdateId(user.getUserId());
		opsCategoryService.updateCategory(categoriesVO);
		return message;
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/getPreLevel")
	public Message getPreLevel(String parentId,Model model) {
		logger.info("in goods getPreLevel parentId: " + parentId);
		List<BopsIndexCategoryParam> preList=bopsIndexCategoryDAO.selectPreLevel(parentId);
		Message message = Message.success();
		message.addData("preList", preList);
		return message;
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/deleteCategory")
	public Message deleteCategory(Integer categoryId, String level) {
		logger.info("in OpsCategoryManagerController deleteCategory categoryId $$$$$ level" + categoryId+"$$$$"+level);
		 Message message=opsCategoryService.removecategory(categoryId,level);
		 return message;
	}
	
}
