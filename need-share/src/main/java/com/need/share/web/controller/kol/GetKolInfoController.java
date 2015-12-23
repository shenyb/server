package com.need.share.web.controller.kol;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.need.share.constant.Constants;
import com.need.share.constant.ControllerMappings;
import com.need.share.constant.ViewMappings;
import com.need.share.dao.jdbc.api.need.NeedKolFansDAO;
import com.need.share.service.user.UserBaseService;
import com.need.share.util.MD5Util;
import com.need.share.util.PropertiesUtil;
import com.need.share.web.controller.goods.GetGoodsDetailController;
import com.need.share.web.controller.kol.vo.GoodsMainVO;
import com.need.share.web.controller.kol.vo.UserInfoVO;

/**
 * <p>获取行家信息</p>
 * @author Rylan 2015年8月24日 下午9:36:33
 * @ClassName GetKolInfoController
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年8月24日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value=ControllerMappings.WEB_KOL_DETAIL)
public class GetKolInfoController {

	private static final Logger logger = Logger.getLogger(GetGoodsDetailController.class);
	
	@Autowired
	private UserBaseService userBaseService;
	@Autowired
	private NeedKolFansDAO needKolFansDAO;
	
	/**
	 * @author Rylan 2015年8月24日 下午9:41:39
	 * @Method: getKolDetail 
	 * @Description: TODO
	 * @param kolId
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.GET, value="/{kolId}")
	public String getKolDetail(@PathVariable String kolId,Model model) {
		logger.debug("getKolDetail in.. kolId :"+kolId);  		
		UserInfoVO userInfoVO=userBaseService.getUserInfo(kolId);//查询用户信息
		if(userInfoVO!=null){
			List<GoodsMainVO> needList= userBaseService.getNeedGoodsList(kolId, 1, 8);//用户的need商品
			//List<TradeGoodVO> tradeList= userBaseService.getTradeGoodsList(kolId,1, 3);//用户的购买商品
			int concernCount=needKolFansDAO.getConcernCount(kolId);
			int fansCount=needKolFansDAO.getKolFansCount(kolId);
			
			//返回信息
			model.addAttribute("userInfoVO", userInfoVO);
			
			model.addAttribute("needList", needList);
			//model.addAttribute("tradeList",tradeList);	
			
			model.addAttribute("concernCount", concernCount);
			model.addAttribute("fansCount", fansCount);
			model.addAttribute("picDomain", PropertiesUtil.getProperty(Constants.PIC_DOMAIN_KEY, "pic_domain"));
		}
		
		return ViewMappings.KOL_DETAIL;
	}
	
	public static void main(String[] args) {
		System.out.println(MD5Util.MD5Encode("123456"));
	}
	
}
