package com.need.erp.web.controller.goods;

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

import com.need.dao.bops.goods.BopsGoodsCategoriesDAO;
import com.need.domain.po.bops.goods.BopsGoodsCategoriesPO;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.vo.goods.BopsGoodsCategoriesVO;
import com.need.erp.constant.ControllerMappings;
import com.need.erp.constant.ERPBizReturnCode;
import com.need.erp.constant.ViewMappings;
import com.need.service.bops.goods.BopsCategoryService;
import com.need.service.constant.Constants;
/**
 * 
 * <p></p>
 * @author liuhongyang 2015年11月11日 下午7:04:19
 * @ClassName BopsCategoriesManagerController
 * @Description 商品分类
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: liuhongyang 2015年11月11日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.BOPS_CATEGORIES)
public class BopsCategoriesManagerController {
	
	private static final Logger logger = Logger.getLogger(BopsCategoriesManagerController.class);
	
	@Autowired
	private BopsGoodsCategoriesDAO bopsGoodsCategoriesDAO;
	@Autowired
	private BopsCategoryService bopsCategoryService;
	/**
	 * 
	 * @author liuhongyang 2015年11月12日 上午10:30:47
	 * @Method: getCategoriesList 
	 * @Description: 分类列表
	 * @param categoriesPO
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/page")
	public String getCategoriesList(BopsGoodsCategoriesVO bopsGoodsCategoriesVO, Model model) {
		logger.info("in goods getCategoriesList bopsGoodsCategoriesVO: " + bopsGoodsCategoriesVO);
		/*bopsGoodsCategoriesVO.setTotal(bopsGoodsCategoriesDAO.selectCategoryListCount(bopsGoodsCategoriesVO));
		List<BopsGoodsCategoriesVO> categoryList=bopsCategoryService.selectCategoryList(bopsGoodsCategoriesVO);
		model.addAttribute("list", categoryList);
		model.addAttribute("page", bopsGoodsCategoriesVO);*/
		//查询所有一级分类
		List<BopsGoodsCategoriesVO> levelList=bopsGoodsCategoriesDAO.selectCategoryLevel("1");
		if(levelList.size()>0){
			for (BopsGoodsCategoriesVO categoriesOne : levelList) {
				List<BopsGoodsCategoriesVO> levelTwoList=bopsGoodsCategoriesDAO.selectPreLevel(categoriesOne.getCategoryId().toString());
				categoriesOne.setLevelTwoList(levelTwoList);
				if(levelTwoList.size()>0){
					for (BopsGoodsCategoriesVO categoriesTwo : levelTwoList) {
						List<BopsGoodsCategoriesVO> levelThreeList=bopsGoodsCategoriesDAO.selectPreLevel(categoriesTwo.getCategoryId().toString());
						categoriesTwo.setLevelThreeList(levelThreeList);
					}
				}
			}
		}
		logger.info("in goods getCategoriesList levelList: " + levelList);
		model.addAttribute("levelList", levelList);
		return ViewMappings.CATEGORIES_LIST;
	}
	/**
	 * 
	 * @author liuhongyang 2015年11月12日 上午10:29:02
	 * @Method: toAddCategory 
	 * @Description: 跳转到分类新增页
	 * @param categorityLevel
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/toAddCategory")
	public String toAddCategory(String categorityLevel,Model model) {
		return ViewMappings.CATEGORIES_ADD;
	}
	/**
	 * 
	 * @author liuhongyang 2015年11月12日 上午10:29:19
	 * @Method: addBopsGoodsrManager 
	 * @Description: 新增分类
	 * @param categoriesVO
	 * @param request
	 * @return
	 * @throws NumberFormatException
	 * @throws ParseException 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "addCategory")
	public Message addBopsGoodsManager(BopsGoodsCategoriesVO categoriesVO, HttpServletRequest request)
			throws NumberFormatException, ParseException {
		logger.info("in goods addBopsGoodsManager categoriesVO: " + categoriesVO);
		Message message = Message.success();
		//商品名称不能为空
		if (!StringUtils.hasText(categoriesVO.getCategoryName())) {
			Message error = Message.error(ERPBizReturnCode.CATEGORY_3024);
			return error;
		}
		// 商品名称是否已被使用
		BopsGoodsCategoriesPO bopsGoodsCategoriesPO=bopsGoodsCategoriesDAO.selectBycategoryName(categoriesVO);
		if(null != bopsGoodsCategoriesPO){
			Message error = Message.error(ERPBizReturnCode.CATEGORY_3025);
			return error;
		}
		//当前登录用户
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		categoriesVO.setCreateId(user.getUserId());
		categoriesVO.setUpdateId(user.getUserId());
		categoriesVO.setAuditStatus("WAIT_AUDIT");
		bopsCategoryService.insertNewCategory(categoriesVO);
		return message;
	}
	/**
	 * 
	 * @author liuhongyang 2015年11月12日 上午10:29:35
	 * @Method: getNextLevel 
	 * @Description: 获取父级分类
	 * @param categorityLevel
	 * @param model
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/getNextLevel")
	public Message getNextLevel(String categoryLevel,String parentId,Model model) {
		logger.info("in goods getNextLevel categoryLevel.....parentId: " + categoryLevel+"........."+parentId);
		BopsGoodsCategoriesPO bopsGoodsCategoriesPO=new BopsGoodsCategoriesPO();
		bopsGoodsCategoriesPO.setCategoryLevel(categoryLevel);
		if(null != parentId){
			bopsGoodsCategoriesPO.setParentId(Integer.parseInt(parentId));
		}
		List<BopsGoodsCategoriesPO> levelList=bopsGoodsCategoriesDAO.selectCategoryLevelByLevel(bopsGoodsCategoriesPO);
		model.addAttribute("levelList", levelList);
		Message message = Message.success();
		message.addData("list", levelList);
		return message;
	}
	
	/**
	 * 
	 * @author liuhongyang 2015年11月13日 下午3:58:07
	 * @Method: getPreLevel 
	 * @Description: 获取子级分类
	 * @param parentId
	 * @param model
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/getPreLevel")
	public Message getPreLevel(String parentId,Model model) {
		logger.info("in goods getPreLevel parentId: " + parentId);
		List<BopsGoodsCategoriesVO> preList=bopsGoodsCategoriesDAO.selectPreLevel(parentId);
		Message message = Message.success();
		message.addData("preList", preList);
		return message;
	}
	/**
	 * 
	 * @author liuhongyang 2015年11月12日 上午10:30:19
	 * @Method: 跳转到分类编辑页
	 * @Description: TODO
	 * @param categorityLevel
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/toModCategory")
	public String toModCategory(String categoryId,Model model) {
		logger.info("in goods toModCategory categoryId: " + categoryId);
		BopsGoodsCategoriesVO bopsGoodsCategoriesVO=bopsCategoryService.toUpdateCategory(categoryId);
		model.addAttribute("bopsGoodsCategoriesVO", bopsGoodsCategoriesVO);
		return ViewMappings.CATEGORIES_MOD;
	}
	/**
	 * 
	 * @author liuhongyang 2015年11月19日 下午6:28:56
	 * @Method: getDetailByCategoryId 
	 * @Description: 查看分类详情
	 * @param categoryId
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(value = "/goodsDetial", method = RequestMethod.GET)
	public String getDetailByCategoryId(String categoryId, Model model) {
		BopsGoodsCategoriesVO bopsGoodsCategoriesVO=bopsCategoryService.getCategoryDetail(categoryId);
		model.addAttribute("bopsGoodsCategoriesVO", bopsGoodsCategoriesVO);
		return ViewMappings.CATEGORIES_DETAIL;
	}
	/**
	 * 
	 * @author liuhongyang 2015年11月13日 上午10:43:35
	 * @Method: modBopsGoodsrManager 
	 * @Description: 编辑分类
	 * @param categoriesVO
	 * @param request
	 * @return
	 * @throws NumberFormatException
	 * @throws ParseException 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "modCategory")
	public Message modBopsCategoryManager(BopsGoodsCategoriesVO categoriesVO, HttpServletRequest request)
			throws NumberFormatException, ParseException {
		logger.info("in goods modBopsCategoryManager categoriesVO: " + categoriesVO);
		Message message = Message.success();
		//商品名称不能为空
		if(null == categoriesVO.getCategoryName()){
			Message error = Message.error(ERPBizReturnCode.CATEGORY_3024);
			return error;
		}
		// 商品名称是否已被使用
		BopsGoodsCategoriesPO bopsGoodsCategoriesPO=bopsGoodsCategoriesDAO.selectBycategoryName(categoriesVO);
		if(null != bopsGoodsCategoriesPO){
			Message error = Message.error(ERPBizReturnCode.CATEGORY_3025);
			return error;
		}
		//当前登录用户
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		categoriesVO.setUpdateId(user.getUserId());
		bopsCategoryService.updateCategory(categoriesVO);
		return message;
	}

}
