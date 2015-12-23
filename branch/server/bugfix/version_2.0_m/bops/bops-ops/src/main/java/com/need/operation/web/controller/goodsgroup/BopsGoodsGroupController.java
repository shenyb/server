package com.need.operation.web.controller.goodsgroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.need.biz.utils.BizCode;
import com.need.biz.utils.MD5Util;
import com.need.dao.api.trade.TradeBaseDAO;
import com.need.dao.bops.goodsgroup.BopsGoodsGroupBaseDAO;
import com.need.dao.bops.goodsgroup.BopsGoodsGroupDAO;
import com.need.dao.bops.user.BopsUserDAO;
import com.need.domain.common.enums.GoodsGroupStatusEnums;
import com.need.domain.po.bops.coupon.BopsCouponTemplatePO;
import com.need.domain.po.bops.goodsgroup.BopsGoodsGroupBase;
import com.need.domain.po.bops.goodsgroup.BopsGoodsGroupKey;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.vo.goods.AuditGoodsVO;
import com.need.domain.vo.goodsgroup.GoodsGroupParaVO;
import com.need.domain.vo.goodsgroup.GoodsGroupResultVO;
import com.need.domain.vo.goodsgroup.GoodsGroupVO;
import com.need.domain.vo.goodsgroup.GoodsShowVO;
import com.need.domain.vo.xinhuan.XinhuanGoodsParamPageVO;
import com.need.operation.constant.ControllerMappings;
import com.need.operation.constant.UrlMappings;
import com.need.operation.constant.ViewMappings;
import com.need.operation.pub.ConstantsProConfig;
import com.need.service.bops.goodsGroup.GoodsGroupService;
import com.need.service.constant.Constants;
import com.need.utils.DateUtil;

@Controller
@RequestMapping(ControllerMappings.BOPS_GOODS_GROUP)
public class BopsGoodsGroupController {

	private static final Logger logger = Logger.getLogger(BopsGoodsGroupController.class);
	
	@Autowired
	private GoodsGroupService goodsGroupService;
	@Autowired
	private ConstantsProConfig ConstantsProConfig;
	@Autowired
	private BopsUserDAO bopsUserDAO;
   
	@Autowired
	private BopsGoodsGroupDAO bopsGoodsGroupDAO;
	
	@Autowired
	private TradeBaseDAO tradeBaseDAO;
	
	

	
    /***
     * 
     * @author LXD 2015年11月24日 下午5:01:32
     * @Method: getListByPage 
     * @Description: TODO 组合分页信息
     * @param goodsGroupVO
     * @param model
     * @return 
     * @throws
     */
	@RequestMapping(method = RequestMethod.GET, value="page")
	public String getListByPage(GoodsGroupVO goodsGroupVO, Model model){

		logger.info("in BopsGoodsGroupController getListByPage goodsStoreDetailVO: "+goodsGroupVO.toString());
		List<GoodsGroupResultVO> list= goodsGroupService.getAll(goodsGroupVO);
		for (GoodsGroupResultVO goodsGroupResultVO : list) {
			BopsUser bopsUser=bopsUserDAO.selectByPrimaryKey(goodsGroupResultVO.getCreateId());
			if(null != bopsUser){
				goodsGroupResultVO.setUserRealName(bopsUser.getUserRealName());
			}
			int count= bopsGoodsGroupDAO.getCount(goodsGroupResultVO.getGroupBatch());
			int seleCount =tradeBaseDAO.getGoodsCountByBatch(goodsGroupResultVO.getGroupBatch());
			goodsGroupResultVO.setGoodsNumber(count);
			goodsGroupResultVO.setSaledNumber(seleCount);
		}
		List<BopsUser> userList=bopsUserDAO.getOpsUser();
		model.addAttribute("userList", userList);
		model.addAttribute("list", list);
		model.addAttribute("page", goodsGroupVO);
		return ViewMappings.GOODS_GROUP_LIST;
	
	}
	
	
	
    /***
     * 
     * @author LXD 2015年11月24日 下午5:01:51
     * @Method: toAddGoods 
     * @Description: TODO 跳转新增页面
     * @return 
     * @throws
     */
	@RequestMapping(method = RequestMethod.GET, value="toAdd")
	public String toAddGoods(){

		logger.info("toAddGoods ");
		
		return ViewMappings.TO_ADD_GOODS_GROUP;
	
	}
	
	/***
     * 
     * @author LXD 2015年11月24日 下午5:01:51
     * @Method: toAddGoods 
     * @Description: TODO 新增组合
     * @return 
     * @throws
     */

	@RequestMapping(method = RequestMethod.POST, value="addGoods")
	public String AddGoods(GoodsGroupVO goodsGroupVO, HttpServletRequest request){
		logger.info("AddGoods in BopsGoodsGroupController  ");
		goodsGroupVO.setGroupStatus(GoodsGroupStatusEnums.INVALID.code);
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);// 得到当前登录的用户信息
		goodsGroupVO.setCreateId(user.getUserId());
		/*if(StringUtils.isNotBlank(goodsGroupVO.getStartTimeString())&&StringUtils.isNotBlank(goodsGroupVO.getEndTimeString())){
			try {
				goodsGroupVO.setStartTime(DateUtil.formatStrToDate(goodsGroupVO.getStartTimeString(), "yyyy-MM-dd HH:mm:ss"));
	        } catch (ParseException ex) {
	        	logger.error(ex.getMessage(), ex);
	        }
	        try {
	        	goodsGroupVO.setEndTime(DateUtil.formatStrToDate(goodsGroupVO.getEndTimeString(), "yyyy-MM-dd HH:mm:ss"));
	        } catch (ParseException ex) {
	        	logger.error(ex.getMessage(), ex);
	        }
			}*/
		goodsGroupVO.setStartTime(Calendar.getInstance().getTime());

		 try {
			 goodsGroupVO.setEndTime(DateUtil.formatStrToDate(ConstantsProConfig.getExpDate(), "yyyy-MM-dd HH:mm:ss"));
	        } catch (ParseException ex) {
	        	logger.error(ex.getMessage(), ex);
	        }
		goodsGroupService.addGroup(goodsGroupVO);
		return UrlMappings.GOODS_GROUP_LIST;
	
	}
	/***
	 * 
	 * @author LXD 2015年11月25日 下午4:23:22
	 * @Method: auditGroup 
	 * @Description: TODO 审核
	 * @param groupStatus
	 * @param groupBatch
	 * @param request
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/audit")
	public Message auditGroup(String groupStatus,String groupBatch, HttpServletRequest request) {

		logger.info("in BopsGoodsGroupController auditGroup groupBatch: " + groupBatch);

		Message message = Message.success();
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		goodsGroupService.audit(groupBatch,user.getUserId());
		return message;
	}
	
	/***
	 * 
	 * @author LXD 2015年11月25日 下午4:23:52
	 * @Method: addGroupGoods 
	 * @Description: TODO 添加商品
	 * @param groupStatus
	 * @param groupBatch
	 * @param request
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/addGroupGoods")
	public Message addGroupGoods(String groupBatch, String goodsCodes, HttpServletRequest request) {

		logger.info("in BopsGoodsGroupController addGroupGoods groupBatch: " + groupBatch);

		Message message = Message.success();
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		goodsGroupService.addGroupGoods(groupBatch,goodsCodes,user.getUserId());
		return message;
	}
	
	/***
	 * 
	 * @author LXD 2015年11月28日 下午4:40:47
	 * @Method: checkGoods 
	 * @Description: TODO 校验商品
	 * @param goodsCodes
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/checkGoods")
	public Message checkGoods(String goodsCodes) {
		logger.info("in BopsGoodsGroupController checkGoods goodsCodes: " + goodsCodes);
		 Message message=goodsGroupService.checkGoods(goodsCodes);
		return message;
	}
	
	/***
	 * 
	 * @author LXD 2015年11月28日 下午4:41:12
	 * @Method: viewGoods 
	 * @Description: TODO 查看商品
	 * @param goodsGroupVO
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.GET, value="viewGoods")
	public String viewGoods(GoodsGroupParaVO goodsGroupVO, Model model){
		GoodsGroupVO groupVO=goodsGroupService.getToEdit(goodsGroupVO.getGroupBatch());
		List<GoodsGroupParaVO> list= goodsGroupService.getBybatch(goodsGroupVO);
		model.addAttribute("imgUrl",ConstantsProConfig.getPic_domain() );
		model.addAttribute("list", list);
		model.addAttribute("page", goodsGroupVO);
		model.addAttribute("group", groupVO);
		return ViewMappings.GOODS_GROUP_GOODS_LIST;
	
	}
	
    /***
     * 
     * @author LXD 2015年11月28日 下午4:41:50
     * @Method: toEditGroup 
     * @Description: TODO 跳转编辑页面
     * @param groupBatch
     * @param model
     * @return 
     * @throws
     */
	@RequestMapping(method = RequestMethod.GET, value="toEdit")
	public String toEditGroup(String groupBatch,Model model){

		logger.info("toEdit  groupBatch="+groupBatch);
		
		GoodsGroupVO goodsGroupVO=goodsGroupService.getToEdit(groupBatch);
		
		
		
		model.addAttribute("group", goodsGroupVO);
		return ViewMappings.TO_EDIT_GOODS_GROUP;
	
	}
	/**
	 * 
	 * @author LXD 2015年11月28日 下午4:42:10
	 * @Method: editGroupGoods 
	 * @Description: TODO 编辑促销组合
	 * @param goodsGroupVO
	 * @param request
	 * @return 
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/editGroupGoods")
	public String editGroupGoods(GoodsGroupVO goodsGroupVO, HttpServletRequest request) {
		logger.info("in BopsGoodsGroupController editGroupGoods groupBatch: " + goodsGroupVO.toString());
		goodsGroupVO.setGroupStatus(GoodsGroupStatusEnums.INVALID.code);
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);// 得到当前登录的用户信息
		goodsGroupVO.setUpdateId(user.getUserId());
		goodsGroupService.editGroup(goodsGroupVO);
		return UrlMappings.GOODS_GROUP_LIST;
	}
	
	/***
	 * 
	 * @author LXD 2015年11月28日 下午4:42:34
	 * @Method: deleteGoods 
	 * @Description: TODO 删除促销组合的商品
	 * @param goodsId
	 * @param groupBatch
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/deleteGoods")
	public Message deleteGoods(String goodsId,String groupBatch) {
		logger.info("in BopsGoodsGroupController deleteGoods goodsId: " + goodsId+"&groupBatch="+groupBatch);
		BopsGoodsGroupKey Key=new BopsGoodsGroupKey();
		Key.setGoodsId(goodsId);
		Key.setGroupBatch(groupBatch);
		Message message=goodsGroupService.deleteGoods(Key);
		return message;
	}


}
