package com.need.marketing.web.controller.cheap;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.jedis.JedisSentinelClient;
import com.need.jedis.JedisSentinelClient;
import com.need.marketing.constant.BizReturnCode;
import com.need.marketing.constant.ControllerMappings;
import com.need.marketing.constant.ViewMappings;
import com.need.marketing.dao.jdbc.cheap.CheapOpenDAO;
import com.need.marketing.dao.jdbc.cheap.po.CheapOpenPO;
import com.need.marketing.dao.jdbc.cheap.po.CheapOpenUserPOKey;
import com.need.marketing.service.cheap.CheapManagermentService;
import com.need.marketing.web.controller.cheap.vo.CheapVO;
import com.need.marketing.web.controller.cheap.vo.JoinUserVO;
import com.need.marketing.pub.ConstantsProConfig;
import com.need.marketing.pub.Message;
import com.need.marketing.service.weixin.WeixinService;
import com.need.utils.PropertiesUtil;
import com.need.utils.StringUtil;
import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;






@Controller
@RequestMapping(value =ControllerMappings.CHEAP)
public class CheapManagermentController {
	private static final Logger logger = Logger.getLogger(CheapManagermentController.class);
	@Autowired
	private CheapManagermentService CheapManagermentService;
	@Autowired
	private ConstantsProConfig constantsProConfig;
	

    
	@Autowired
	private CheapOpenDAO cheapOpenDAO;
    
    @Autowired
    private WeixinService weixinService;
    
	
	@RequestMapping(method = RequestMethod.GET,value="/cheapInfo")
	public String getCheapInfo(@RequestParam(required = true) String cheapNo,
			@RequestParam(required = false) String mobile,@RequestParam(required = false) String userId,@RequestParam(required = false) String cheapPosition, HttpServletRequest request, Model model) {
		/**
		 * 根据cheapID获取cheap的详细信息 
		 * 1、校验此商品的库存
		 * 2、游客登录，只是展示此cheap的详细信息，提示其开团
		 * 3、用户登录，根据此用户获取其与该cheap的关系：未开此团；已开团；已参此团；
		 */
		// 根据cheapID获取cheap的详细信息
		logger.info("getCheapInfo in CheapManagermentController...cheapNo:"+cheapNo+",mobile="+mobile+",userId="+userId);
		CheapVO cheapVO = CheapManagermentService.updateCheap(cheapNo, mobile,userId,cheapPosition);
		logger.info("getCheapById end ...cheapVO is :"+cheapVO.toString());
		model.addAttribute("picDomain", constantsProConfig.getPicDomain());
		model.addAttribute("cheap", cheapVO);
		if(StringUtils.isNotBlank(userId)){
		model.addAttribute("userId", userId);
		}else{
			model.addAttribute("userId", "");	
		}
		if (StringUtils.isNotBlank(mobile)) {
			model.addAttribute("mobile", mobile);
		}
		if(cheapVO!=null&&cheapVO.getCheapOpenId()!=null){
	    //参团用户信息		
		JoinUserVO openUser= CheapManagermentService.getOpenUserInfo(cheapVO.getCheapOpenId());
		List<JoinUserVO> list= CheapManagermentService.getJoinUserInfo(cheapVO.getCheapOpenId());
		if(list!=null&&list.size()>0){
			  model.addAttribute("joinCount", 1+list.size());
			  model.addAttribute("count", cheapVO.getCheapCount()-(1+list.size()));
			}else{
				model.addAttribute("joinCount", 1);
				model.addAttribute("count", cheapVO.getCheapCount()-1);
			}
		model.addAttribute("openUser", openUser);
		model.addAttribute("list", list);
		}
		model.addAttribute("picDomain", constantsProConfig.getPicDomain());

		return ViewMappings.CHEAP_DETAIL;

	}
	/**
	 * 
	 * @author LXD 2015年10月28日 下午6:13:02
	 * @Method: openCheap 
	 * @Description: TODO 开团
	 * @param cheapNo
	 * @param userId
	 * @param request
	 * @param model
	 * @return 
	 * @throws
	 */
	
	@RequestMapping(method = RequestMethod.GET,value="/openCheap")
	public String openCheap(@RequestParam(required = true) String cheapNo,
			@RequestParam(required = true) String userId, HttpServletRequest request, Model model) {
		logger.info("getCheapInfo in CheapManagermentController...cheapNo:"+cheapNo+",userId="+userId);
		/**
         * 1、校验此用户打开或参与的此团关闭没有，关闭则开新团
         */
		CheapVO tmpCheapVO =CheapManagermentService.openCheap(cheapNo,userId);		
		logger.info("cheapVO :"+tmpCheapVO.toString());
		model.addAttribute("picDomain", constantsProConfig.getPicDomain());
		model.addAttribute("cheap", tmpCheapVO);
		return ViewMappings.OPEN_CHEAP;

	}
	/**
	 * 
	 * @author LXD 2015年10月28日 下午6:57:06
	 * @Method: shareCheap 
	 * @Description: TODO 分享开的团
	 * @param cheapOpenId
	 * @param request
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.GET,value="/shareCheap")
	public String shareCheap(@RequestParam(required = true) Integer cheapOpenId,@RequestParam(required = true) String cheapNo,HttpServletRequest request, Model model) {
        String code = request.getParameter("code");
        if(StringUtil.isBlank(code)) {
        	/**
        	 * code为空，微信未登录
        	 */
        	//团的基本信息
        	CheapVO cheapVO= CheapManagermentService.updateCheapOpenId(cheapOpenId);
        	logger.info("shareCheap in CheapManagermentController cheapVO:"+cheapVO.toString());
        	//参团用户信息
        	JoinUserVO openUser= CheapManagermentService.getOpenUserInfo(cheapOpenId);
    		List<JoinUserVO> list= CheapManagermentService.getJoinUserInfo(cheapOpenId);
    		//封装model
    		model.addAttribute("picDomain", constantsProConfig.getPicDomain());
    		model.addAttribute("cheapVO", cheapVO);
    		model.addAttribute("openUser", openUser);
    		model.addAttribute("list", list);
    		model.addAttribute("joinStatus", "NORMAL");    		
    		//参团人数信息
    		if(list!=null&&list.size()>0){
    			  model.addAttribute("joinCount", 1+list.size());
    			  model.addAttribute("count", cheapVO.getCheapCount()-(1+list.size()));
    			}else{
    				model.addAttribute("joinCount", 1);
    				model.addAttribute("count", cheapVO.getCheapCount()-1);
    			}
        	
            return ViewMappings.SHARE_CHEAP;
        } else {
        	/***
        	 * code 存在，微信回调
        	 */
        	/**
        	 * 获取微信用户信息
        	 */
            JSONObject userObject = this.weixinService.getWeiXinUserInfo(code);
            if (userObject == null) {
                model.addAttribute("code", "-1");
                model.addAttribute("message", "微信登陆失败请重试");
                return ViewMappings.SHARE_CHEAP;
            }
            
            String mobile = request.getParameter("mobile");
        	//封装CheapOpenUserPOKey
            CheapOpenUserPOKey key =new CheapOpenUserPOKey(cheapOpenId, mobile);
        	key.setCheapNo(cheapNo);
            if(userObject.containsKey("headimgurl")) {
            	key.setProfilePicKey(userObject.getString("headimgurl"));
            }
            if(userObject.containsKey("openid")) {
                key.setOpenId(userObject.getString("openid"));
            }
            //参团方法，返回相应的message
            Message message = CheapManagermentService.joinOpenCheap(key);
            //封装model
            model.addAttribute("message", message.getMsg());   	 
            model.addAttribute("code", message.getCode());
            model.addAttribute("joinStatus", message.getData().get("joinStatus"));
            if(message.getCode() != Message.SUCCESS) {
                model.addAttribute("message", message.getMsg());
            }
            CheapVO cheapVO= CheapManagermentService.updateCheapOpenId(cheapOpenId);
    		JoinUserVO openUser= CheapManagermentService.getOpenUserInfo(cheapOpenId);
    		List<JoinUserVO> list= CheapManagermentService.getJoinUserInfo(cheapOpenId);
    		model.addAttribute("picDomain", constantsProConfig.getPicDomain());
    		model.addAttribute("cheapVO", cheapVO);
    		model.addAttribute("openUser", openUser);
    		model.addAttribute("list", list);
    		if(list!=null&&list.size()>0){
    			  model.addAttribute("joinCount", 1+list.size());
    			  model.addAttribute("count", cheapVO.getCheapCount()-(1+list.size()));
    			}else{
    				model.addAttribute("joinCount", 1);
    				model.addAttribute("count", cheapVO.getCheapCount()-1);
    			}
            model.addAttribute("mobile", mobile);
            
            return ViewMappings.SHARE_CHEAP;
        }

	}
	/***
	 * 
	 * @author LXD 2015年10月28日 下午9:27:23
	 * @Method: sendVerification 
	 * @Description: TODO 发验证码
	 * @param mobile
	 * @param request
     * @param cheapOpenId
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST,value="/sendVerification")
	public Message sendVerification(@RequestParam(required = true) String mobile,@RequestParam(required = true) String cheapOpenId,HttpServletRequest request) {
		logger.info(String.format("sendVerification in CheapManagermentController param:mobile:%s", mobile));
		Message message=CheapManagermentService.sendGeneralValidateCode(mobile, cheapOpenId);
		if(message.getCode() == 0){
			logger.info(String.format("CheckMobileAndSendValidateCode...out....param:mobile:%s", mobile));
		}
        return message;

	}
	/**
	 * 
	 * @author LXD 2015年10月29日 上午11:15:43
	 * @Method: CheckvalidateCode 
	 * @Description: TODO 
	 * @param mobile
	 * @param validateCode
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST,value="/check")
	public Message CheckvalidateCode(
	        @RequestParam(required = true) String mobile,
	        @RequestParam(required = true) String validateCode) {
		logger.info(String.format("CheckvalidateCode...in...params:%s %s", mobile,validateCode));
		Message success = Message.success();
		Object code = JedisSentinelClient.getString(mobile);
		if (code != null && code.equals(validateCode)) {//验证码正确
			JedisSentinelClient.del(mobile);	
			success.addData("isRight", "true");
			return success;
		}	
		logger.info(String.format("CheckvalidateCode...out...params:%s %s", mobile,validateCode));
		return Message.error(BizReturnCode.CODE_ERROR);
	}
    
	
	/**
	 * 
	 * @author LXD 2015年10月30日 下午12:04:50
	 * @Method: weiXinLogin 
	 * @Description: TODO 微信登录
	 * @param cheapOpenId
	 * @param cheapNo
	 * @param mobile
	 * @param model
	 * @param response
	 * @param request
	 * @return 
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.GET, value="/login")
	public String weiXinLogin(
			@RequestParam(required = true) String cheapOpenId,
            @RequestParam(required = true) String cheapNo,
            @RequestParam(required = true) String mobile,
            Model model, HttpServletResponse response, HttpServletRequest request) {
		CheapOpenPO cheapOpenPO = null;
        try {
            cheapOpenPO = cheapOpenDAO.selectByPrimaryKey(Integer.valueOf(cheapOpenId));
        } catch(NumberFormatException e) {
        }
        if(cheapOpenPO == null) {
        	logger.info("weiXinLogin in CheapManagermentController cheapOpenPO is NULL");
            return null;
        }
        try {
            String time = ""+System.currentTimeMillis();
            String host = constantsProConfig.getMarketingHost();
            String url = host + "/cheap/shareCheap?cheapOpenId="+cheapOpenId+"&cheapNo="+cheapNo+"&mobile="+mobile;
            String redirect = URLEncoder.encode(url,"utf-8");
            String appid = PropertiesUtil.getProperty("/properties/wechat.properties", "app_id");
            /***
             * 微信回调地址
             */
            String redirectUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri="+redirect+"&response_type=code&scope=snsapi_userinfo&state="+time+"#wechat_redirect";
            logger.info(redirectUrl);
            response.sendRedirect(redirectUrl);
        } catch (IOException e) { 	
            // TODO Auto-generated catch block
            logger.error(e.getMessage(), e);
        }
        return null;
    }
	
	
	/***
	 * 
	 * @author LXD 2015年10月30日 下午12:10:21
	 * @Method: shareCheap 
	 * @Description: TODO 分享团便宜
	 * @param cheapOpenId
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST,value="/share")
	public String shareCheap(@RequestParam(required = true) Integer cheapOpenId,Model model) {
		logger.info(String.format("CheckvalidateCode...in...params:%s %s", cheapOpenId));
		/***
		 * 1、团的基本信息
		 * 2、参团人信息
		 */
		/***
		 * 团的基本信息
		 */
		CheapVO cheapVO= CheapManagermentService.updateCheapOpenId(cheapOpenId);
		logger.info("cheapVO:"+cheapVO.toString());
		/**
		 * 参团人信息
		 */
		JoinUserVO openUser= CheapManagermentService.getOpenUserInfo(cheapOpenId);
		List<JoinUserVO> list= CheapManagermentService.getJoinUserInfo(cheapOpenId);
		/***
		 * 封装model
		 */
		model.addAttribute("picDomain", constantsProConfig.getPicDomain());
		model.addAttribute("cheapVO", cheapVO);
		model.addAttribute("openUser", openUser);
		model.addAttribute("list", list);
		if(list!=null&&list.size()>0){
		  model.addAttribute("joinCount", 1+list.size());
		}else{
			model.addAttribute("joinCount", 1);
		}
		return ViewMappings.SHARE_CHEAP;
		
	}
	
	
	
	
	
}
