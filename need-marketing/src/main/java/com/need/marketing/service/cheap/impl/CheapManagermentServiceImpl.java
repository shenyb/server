package com.need.marketing.service.cheap.impl;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.need.gateway.sms.SMSService;
import com.need.jedis.JedisSentinelClient;
import com.need.jedis.JedisSentinelClient;
import com.need.marketing.common.enums.CheapPositionEnum;
import com.need.marketing.common.enums.CheapStatusEnum;
import com.need.marketing.common.enums.CheckCheapEnum;
import com.need.marketing.constant.BizReturnCode;
import com.need.marketing.constant.Constants;
import com.need.marketing.dao.jdbc.cheap.CheapBaseDAO;
import com.need.marketing.dao.jdbc.cheap.CheapOpenDAO;
import com.need.marketing.dao.jdbc.cheap.CheapOpenUserDAO;
import com.need.marketing.dao.jdbc.cheap.po.CheapBasePO;
import com.need.marketing.dao.jdbc.cheap.po.CheapOpenPO;
import com.need.marketing.dao.jdbc.cheap.po.CheapOpenUserPO;
import com.need.marketing.dao.jdbc.cheap.po.CheapOpenUserPOKey;
import com.need.marketing.service.cheap.CheapManagermentService;
import com.need.marketing.web.controller.cheap.vo.CheapUserVO;
import com.need.marketing.web.controller.cheap.vo.CheapVO;
import com.need.marketing.web.controller.cheap.vo.JoinUserVO;
import com.need.utils.StringUtil;
import com.need.marketing.dao.jdbc.goods.GoodsMainDAO;
import com.need.marketing.dao.jdbc.goods.po.GoodsMainPO;
import com.need.marketing.dao.jdbc.user.UserBaseDAO;
import com.need.marketing.dao.jdbc.user.po.UserBasePO;
import com.need.marketing.pub.Message;
import com.need.marketing.pub.SMSProConfig;

@Service
public class CheapManagermentServiceImpl implements CheapManagermentService {
	private static final Logger logger = Logger.getLogger(CheapManagermentServiceImpl.class);
	@Autowired
	private CheapBaseDAO cheapBaseDAO;
	@Autowired
	private CheapOpenDAO cheapOpenDAO;
	@Autowired
	private CheapOpenUserDAO cheapOpenUserDAO;
	@Autowired
	private GoodsMainDAO GoodsMainDAO;
	@Autowired
	private UserBaseDAO userBaseDAO;
	@Autowired
	private SMSProConfig smsProConfig;
	@Autowired
	private SMSService smsService;
	
	
	/**
     * 
     * @author LXD 2015年10月27日 下午3:41:53
     * @Method: getCheapById 
     * @Description: TODO 根据cheapID获取cheap的详细信息
     * @param cheapId
     * @param userId
     * @return 
     * @see com.need.marketing.service.cheap.CheapManagermentService#getCheapById(java.lang.String, java.lang.String)
     */
	@Transactional
	@Override
	public CheapVO updateCheap(String cheapNo, String mobile, String userId, String cheapStatus) {
		CheapBasePO cheapBase = cheapBaseDAO.selectById(cheapNo);
		CheapVO cheapVO = new CheapVO();
		BeanUtils.copyProperties(cheapBase, cheapVO);
		/***
		 * 我的已经截止的团
		 */
		if (CheapPositionEnum.CLOSE.status.equals(cheapStatus)) {
			cheapVO.setOpenCheapStatus(CheckCheapEnum.MYCLOSE.status);
			return cheapVO;
		}

		/***
		 * 游客登录,状态全部开团
		 */

		if (StringUtils.isBlank(userId)) {
			cheapVO.setOpenCheapStatus(CheckCheapEnum.OPEN.status);
			return cheapVO;

		} else {
			/***
			 * 
			 * 用户登录 
			 * 1、校验库存，是否售罄，售罄去别的团看看
			 * 2、没有参与此团，去开团 
			 * 3、参与此团： 
			 *    a、集齐好友，去支付
			 *    b、时间到了，人没有集齐，去别的团看看 
			 *    c、人集齐了，持续时间结束了 
			 *    d、完成支付，去别的团看看
			 */

			if (cheapBase.getEndTime().getTime() < Calendar.getInstance().getTime().getTime()) {
				/**
				 * 截止时间小于当前时间，活动结束
				 */
				cheapVO.setOpenCheapStatus(CheckCheapEnum.CLOSE.status);
				if (CheapPositionEnum.VALID.status.equals(cheapStatus)) {
					cheapVO.setOpenCheapStatus(CheckCheapEnum.VALIDCLOSE.status);
				}
				return cheapVO;
			}

			GoodsMainPO goods = GoodsMainDAO.getById(cheapBase.getGoodsId());
			/***
			 * 商品售罄
			 */
			if (goods != null) {
				if (goods.getStoreCount() <= 0) {
					logger.info("goods StoreCount 0.");
					cheapVO.setOpenCheapStatus(CheckCheapEnum.SOLDOUT.status);
					if (CheapPositionEnum.VALID.status.equals(cheapStatus)) {
						cheapVO.setOpenCheapStatus(CheckCheapEnum.VALIDCLOSE.status);
					}
					return cheapVO;
				}
			} else {
				cheapVO.setOpenCheapStatus(CheckCheapEnum.SOLDOUT.status);
				if (CheapPositionEnum.VALID.status.equals(cheapStatus)) {
					cheapVO.setOpenCheapStatus(CheckCheapEnum.VALIDCLOSE.status);
				}
				return cheapVO;

			}
			/***
			 * 校验用户是否已经下过此团的订单
			 */
			List<CheapOpenUserPO> tradedList = cheapOpenUserDAO.queryByParams(mobile, cheapNo,
					Boolean.TRUE.toString().toUpperCase());
			if (!tradedList.isEmpty()) {
				cheapVO.setOpenCheapStatus(CheckCheapEnum.PAY.status);
				cheapVO.setCheapOpenId(tradedList.get(0).getCheapOpenId());
				if (CheapPositionEnum.VALID.status.equals(cheapStatus)) {
					cheapVO.setOpenCheapStatus(CheckCheapEnum.VALIDCPAY.status);
				}
				return cheapVO;
			}
			/**
			 * 查出我参与的团，再判断团的状态
			 */
			List<CheapUserVO> list = cheapOpenUserDAO.getByParames(mobile, cheapNo);
			if (list != null && list.size() > 0) {
				CheapUserVO openUser = list.get(0);
				/**
				 * 参团了,判断此团的状态
				 */
				if (CheapStatusEnum.OPEN.status.equals(openUser.getCheapStatus())) {
					/***
					 * 如果当前时间大于了截至时间，更新团便宜的状态，返回相应的状态
					 */
					logger.info("begin to deal OPEN status cheap.");
					logger.info("now :" + Calendar.getInstance().getTime().getTime() + " createTime :"
							+ openUser.getCreateTime().getTime() + " duringTime" + cheapBase.getDuringTime());
					if (Calendar.getInstance().getTime().getTime() - openUser.getCreateTime().getTime() >= cheapBase
							.getDuringTime()) {
						CheapOpenPO cheapOpenPO = new CheapOpenPO();
						BeanUtils.copyProperties(openUser, cheapOpenPO);
						cheapOpenPO.setCheapStatus(CheapStatusEnum.CLOSED.status);
						cheapOpenDAO.updateByPrimaryKey(cheapOpenPO);
						cheapVO.setOpenCheapStatus(CheckCheapEnum.OPEN.status);
						cheapVO.setCheapOpenId(openUser.getCheapOpenId());
						if (CheapPositionEnum.VALID.status.equals(cheapStatus)) {
							cheapVO.setOpenCheapStatus(CheckCheapEnum.VALIDCLOSE.status);
						}
						return cheapVO;
					} else {
						/**
						 * 团未截止
						 */
						cheapVO.setOpenCheapStatus(CheckCheapEnum.CALL.status);
						cheapVO.setCheapOpenId(openUser.getCheapOpenId());
						logger.info("residue time :" + ((float) (cheapBase.getDuringTime()
								- (Calendar.getInstance().getTime().getTime() - openUser.getCreateTime().getTime()))
								/ (float) (60 * 60 * 1000)));
						cheapVO.setDownTime(((float) cheapBase.getDuringTime()
								- (Calendar.getInstance().getTime().getTime() - openUser.getCreateTime().getTime()))
								/ (float) (60 * 60 * 1000) + "");
						return cheapVO;

					}

				} else if (CheapStatusEnum.COMPLETE.status.equals(openUser.getCheapStatus())) {
					/***
					 * 已经成团
					 * 如果超过了持续时间，更新团便宜的状态，返回相应的状态
					 */
					logger.info("begin to deal COMPLETE status cheap.");
					if (Calendar.getInstance().getTime().getTime() - openUser.getCompleteTime().getTime() >= 4 * 60 * 60
							* 1000) {
						CheapOpenPO cheapOpenPO = new CheapOpenPO();
						BeanUtils.copyProperties(openUser, cheapOpenPO);
						cheapOpenPO.setCheapStatus(CheapStatusEnum.CLOSED.status);
						cheapOpenDAO.updateByPrimaryKey(cheapOpenPO);
						cheapVO.setOpenCheapStatus(CheckCheapEnum.CLOSE.status);
						cheapVO.setCheapOpenId(openUser.getCheapOpenId());
						if (CheapPositionEnum.VALID.status.equals(cheapStatus)) {
							cheapVO.setOpenCheapStatus(CheckCheapEnum.VALIDCLOSE.status);
						}
						return cheapVO;
					} else {
						/**
						 * 判断支付没支付 ,去支付
						 */
						if (Boolean.FALSE.toString().equalsIgnoreCase(openUser.getTraded())) {
							cheapVO.setOpenCheapStatus(CheckCheapEnum.NOTPAY.status);
							cheapVO.setCheapOpenId(openUser.getCheapOpenId());
							logger.info("residue time :"
									+ ((float) (4 * 60 * 60 * 1000) - (Calendar.getInstance().getTime().getTime()
											- openUser.getCompleteTime().getTime())) / (float) (60 * 60 * 1000)
									+ "");
							cheapVO.setDownTime(
									(float) ((4 * 60 * 60 * 1000) - (Calendar.getInstance().getTime().getTime()
											- openUser.getCompleteTime().getTime())) / (float) (60 * 60 * 1000) + "");
							return cheapVO;
						} else {
							/***
							 * 已经支付过，看别的团吧
							 */
							cheapVO.setOpenCheapStatus(CheckCheapEnum.PAY.status);
							cheapVO.setCheapOpenId(openUser.getCheapOpenId());
							if (CheapPositionEnum.VALID.status.equals(cheapStatus)) {
								cheapVO.setOpenCheapStatus(CheckCheapEnum.VALIDCPAY.status);
							}
							return cheapVO;
						}

					}

				}

			}

			cheapVO.setOpenCheapStatus(CheckCheapEnum.OPEN.status);
			return cheapVO;

		}
	}

    /***
     * 
     * @author LXD 2015年10月28日 上午10:27:24
     * @Method: openCheap 
     * @Description: TODO 去开团
     * @param cheapNo
     * @param userId
     * @param model 
     * @see com.need.marketing.service.cheap.CheapManagermentService#openCheap(java.lang.String, java.lang.String, org.springframework.ui.Model)
     */
	@Transactional
	@Override
	public CheapVO openCheap(String cheapNo, String userId) {
		
		UserBasePO user= userBaseDAO.selectByPrimaryKey(userId);
		CheapVO cheapVO =new CheapVO();
		CheapBasePO cheapBase= cheapBaseDAO.selectById(cheapNo);
		BeanUtils.copyProperties(cheapBase, cheapVO);
		//优惠券被冻结
		if(CheapStatusEnum.FROZEN.equals(cheapBase.getCheapStatus())){
			cheapVO.setOpenCheapStatus(CheckCheapEnum.FROZEN.status);
			return cheapVO;
		}
		if(user!=null){
			List<CheapUserVO> list=cheapOpenUserDAO.getByParames(user.getMobile(), cheapNo);
			logger.info("list :"+list.size());
			if(list.isEmpty()){	
				//没有参与过并且没有支付过才能继续参开此团
				CheapOpenPO cheapOpen=new CheapOpenPO();
				cheapOpen.setCheapNo(cheapNo);
				cheapOpen.setCheapStatus(CheapStatusEnum.OPEN.status);
				cheapOpen.setUserId(userId);
				cheapOpenDAO.insert(cheapOpen);
				CheapOpenUserPO newCheapUser=new CheapOpenUserPO();
				newCheapUser.setCheapNo(cheapNo); 
				newCheapUser.setCheapOpenId(cheapOpen.getCheapOpenId());
				newCheapUser.setMobile(user.getMobile());
				newCheapUser.setTraded(Boolean.FALSE.toString().toUpperCase());
				newCheapUser.setProfilePicKey(user.getProfilePicKey());
				cheapOpenUserDAO.insert(newCheapUser);				
				logger.debug("cheapOpen :"+cheapOpen);
				BeanUtils.copyProperties(cheapOpen, cheapVO);
				cheapVO.setOpenCheapStatus(CheckCheapEnum.OPEN.status);
				return cheapVO; 				
			}		
			cheapVO.setOpenCheapStatus(CheckCheapEnum.OPENFAIL.status);			
			return cheapVO;
		}
		return null;
	}

	@Override
	public CheapBasePO getBaseCheapInfo(String cheapNo) {
		  CheapBasePO cheapBase= cheapBaseDAO.selectById(cheapNo);
		return cheapBase;
	}
    /**
     * 
     * @author LXD 2015年10月28日 下午8:49:29
     * @Method: sharaCheap 
     * @Description: TODO 分享
     * @param cheapNo
     * @param cheapOpenId
     * @return 
     * @see com.need.marketing.service.cheap.CheapManagermentService#sharaCheap(java.lang.String, java.lang.Integer)
     */
	@Transactional
	@Override
	public CheapVO sharaCheap(String cheapNo, Integer cheapOpenId) {
		//分享openID
	    //商品是否售罄
		//此团是否关闭，未关闭，是否时间已经截止，截止更新状态
		//此团是否成团，成团
	  CheapBasePO cheapBase= cheapBaseDAO.selectById(cheapNo);
	  CheapVO cheapVO =new CheapVO();
	  BeanUtils.copyProperties(cheapBase, cheapVO);	
	  GoodsMainPO goods= GoodsMainDAO.getById(cheapBase.getGoodsId());
		//售罄
		if(goods.getStoreCount()<=0){
			 cheapVO.setOpenCheapStatus(CheapStatusEnum.CLOSED.status);
			 return cheapVO;
		}
		 //查出分享的团，再判断团的状态
		CheapOpenPO openUser= cheapOpenDAO.selectByPrimaryKey(cheapOpenId);
		if(openUser!=null){
			  //参团了,判断此团的状态
			  if(CheapStatusEnum.OPEN.status.equals(openUser.getCheapStatus())){
				  //如果当前时间大于了截至时间，更新团便宜的状态，返回相应的状态
				  if(Calendar.getInstance().getTime().getTime()-openUser.getCreateTime().getTime()>=cheapBase.getDuringTime()){
					  CheapOpenPO cheapOpenPO =new CheapOpenPO();
					  BeanUtils.copyProperties(openUser, cheapOpenPO);
					  cheapOpenPO.setCheapStatus(CheapStatusEnum.CLOSED.status);
					  cheapOpenDAO.updateByPrimaryKey(cheapOpenPO); 
					  cheapVO.setOpenCheapStatus(CheapStatusEnum.CLOSED.status);
					  return cheapVO;  
				  }else{
				   //团未截止	 
					  cheapVO.setOpenCheapStatus(CheapStatusEnum.OPEN.status);
					  return cheapVO;  
					  
				  }
				  
			  }else if(CheapStatusEnum.COMPLETE.status.equals(openUser.getCheapStatus())){
				  //已经成团
				  cheapVO.setOpenCheapStatus(CheapStatusEnum.CLOSED.status);
				  return cheapVO;
                  
				  
			  }	

				
			}
		return cheapVO;
		
	}

	@Override
	public Message sendGeneralValidateCode(String mobile, String cheapOpenId) {
        try {
            CheapOpenUserPO cheapOpenUserPO = cheapOpenUserDAO.selectByPrimaryKey(new CheapOpenUserPOKey(Integer.valueOf(cheapOpenId), mobile));
            if(cheapOpenUserPO != null) {
                return Message.error(BizReturnCode.CHEAP_JOINED);
            }
        } catch(NumberFormatException e) {
            
        }
		String validateCode = null;
		String code = JedisSentinelClient.getString(mobile);// 验证码
		boolean validateCodeFlag = false;// 验证码存在标示
		if (code != null) {
			validateCode = code;
			validateCodeFlag = true;// 标记验证码已存在
		} else {
			validateCode = StringUtil.random(Constants.VALIDATE_CODE_LENGTH); // 生成一个随机验证码  //modify by liyongran  20150902  修改验证码位数
		}
		String content = smsProConfig.getGeneralSMSContent(); // 发送的内容
		content = content.replace("#", validateCode);
		content = content.replace("*", Constants.APP_TIME_OUT_MIN / 60 + "");

		int result = -1;
		try {
			result=smsService.distributeSMSService(content,mobile);//短信提供者
			logger.info("result is :"+result);
			if (result == 0 && !validateCodeFlag) {
				JedisSentinelClient.setString(mobile, validateCode, Constants.APP_TIME_OUT_MIN);// 把验证码放在缓存里，失效时间30分钟;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error(BizReturnCode.SYSTEM_SEND_VALIDATE_CODE_ERR);
		} // 发送手机验证码
		
	  return Message.success();
	}
    
	@Transactional
	@Override
	public CheapVO updateCheapOpenId(Integer cheapOpenId) {
		/**
		 * 超时了；
		 * 该商品已经卖完了；
		 * 人数已经够了；
		 * 其余情况：招呼好友
		 */
		CheapVO cheapVO=cheapOpenDAO.getByOpenId(cheapOpenId);
		
		if(cheapVO!=null){
			GoodsMainPO goods= GoodsMainDAO.getById(cheapVO.getGoodsId());
			//售罄
			if(goods!=null){
			if(goods.getStoreCount()<=0){
				 cheapVO.setOpenCheapStatus(CheckCheapEnum.SOLDOUT.status);
				 return cheapVO;
			}
			}else{
				cheapVO.setOpenCheapStatus(CheckCheapEnum.SOLDOUT.status);
				 return cheapVO;
				
			}
			//已经成团
			if(CheapStatusEnum.COMPLETE.status.equals(cheapVO.getCheapStatus())){
				cheapVO.setOpenCheapStatus(CheapStatusEnum.COMPLETE.status);
				 return cheapVO;
			}else if(CheapStatusEnum.CLOSED.status.equals(cheapVO.getCheapStatus())){
				cheapVO.setOpenCheapStatus(CheapStatusEnum.CLOSED.status);
				 return cheapVO;
				
			}
			
			else{
				  if(Calendar.getInstance().getTime().getTime()-cheapVO.getCreateTime().getTime()>=cheapVO.getDuringTime()){
					  CheapOpenPO cheapOpenPO =cheapOpenDAO.selectByPrimaryKey(cheapOpenId);
					  cheapOpenPO.setCheapStatus(CheapStatusEnum.CLOSED.status);
					  cheapOpenDAO.updateByPrimaryKey(cheapOpenPO);      
					  cheapVO.setOpenCheapStatus(CheapStatusEnum.CLOSED.status);
					  return cheapVO;  
				  }else{
				   //团未截止	 
					  cheapVO.setOpenCheapStatus(CheapStatusEnum.OPEN.status);
					  logger.info("residue time:"+((float)(cheapVO.getDuringTime())-(Calendar.getInstance().getTime().getTime()-cheapVO.getCreateTime().getTime()))/(float)(60*60*1000)+"");
					  cheapVO.setDownTime((float)((cheapVO.getDuringTime())-(Calendar.getInstance().getTime().getTime()-cheapVO.getCreateTime().getTime()))/(float)(60*60*1000)+"");
					  return cheapVO;  
					  
				  }
			}
			
		}
		return null;
	}
    /**
     * 
     * @author LXD 2015年10月30日 下午12:38:49
     * @Method: getOpenUserInfo 
     * @Description: TODO 开团人信息
     * @param cheapOpenId
     * @return 
     * @see com.need.marketing.service.cheap.CheapManagermentService#getOpenUserInfo(java.lang.String)
     */
	@Override
	public JoinUserVO getOpenUserInfo(Integer cheapOpenId) {
		JoinUserVO userShow=new JoinUserVO();
		UserBasePO user= userBaseDAO.getuserInfo(cheapOpenId);
		if(user!=null){
		userShow.setProfilePicKey(user.getProfilePicKey());
		}else{
			List<JoinUserVO> list= cheapOpenUserDAO.getJoinUser(cheapOpenId);
			if(list!=null&&list.size()>0)
			 return  list.get(0);
		}
		return userShow;
	}
    /***
     * 
     * @author LXD 2015年10月30日 下午12:41:01
     * @Method: getJoinUserInfo 
     * @Description: TODO 参团人信息
     * @param cheapOpenId
     * @return 
     * @see com.need.marketing.service.cheap.CheapManagermentService#getJoinUserInfo(java.lang.String)
     */
	@Override
	public List<JoinUserVO> getJoinUserInfo(Integer cheapOpenId) {
		List<JoinUserVO> list= cheapOpenUserDAO.getJoinUser(cheapOpenId);
		if(list!=null&&list.size()>0)
		 list.remove(0);
		return list;
	}
    /***
     * 
     * @author LXD 2015年10月31日 下午3:16:43
     * @Method: joinOpenCheap 
     * @Description: TODO 参团
     * @param mobile
     * @param cheapOpenId
     * @return 
     * @see com.need.marketing.service.cheap.CheapManagermentService#joinOpenCheap(java.lang.String, java.lang.String)
     */
	@Transactional
	@Override
	public Message joinOpenCheap(CheapOpenUserPOKey key) {
		Message message = Message.success();
		// 校验此号是否参加过此团
		// 校验是否成团
		// 校验通过，参团
		// 校验人数，参团
		// 人数够了，更新状态，发短信
		
		// 校验此号是否参加过此团
		//已经参加过了
		List<CheapOpenUserPO> joinUserList = cheapOpenUserDAO.getBycheapNoAndMobile(key);
		if (joinUserList != null && joinUserList.size() > 0) {
			if (joinUserList.get(0) != null) {
				return Message.error(BizReturnCode.CHEAP_JOINED);
			}
		}
		//是否交易过此团
		CheapOpenUserPO userPO = cheapOpenUserDAO.selectByPrimaryKey(key);
		if (userPO != null) {
			if (Boolean.TRUE.toString().equals(userPO.getTraded()))
				return Message.error(BizReturnCode.CHEAP_TRADED);
		}

		CheapVO cheapOpen = cheapOpenDAO.getByOpenId(key.getCheapOpenId());
		if (CheapStatusEnum.OPEN.status.equals(cheapOpen.getCheapStatus())) {
			// 正在开团
			List<CheapOpenUserPO> list = cheapOpenUserDAO.getListByOpenId(key.getCheapOpenId());
			if (!list.isEmpty()) {
				CheapBasePO cheapBase = cheapBaseDAO.selectById(list.get(0).getCheapNo());
				if (cheapBase.getCheapCount() - list.size() > 1) {
					CheapOpenUserPO cheapUser = new CheapOpenUserPO();
					cheapUser.setCheapNo(list.get(0).getCheapNo());
					cheapUser.setCheapOpenId(key.getCheapOpenId());
					cheapUser.setMobile(key.getMobile());
					cheapUser.setTraded(Boolean.FALSE.toString().toUpperCase());
					cheapUser.setProfilePicKey(key.getProfilePicKey());
					cheapOpenUserDAO.insert(cheapUser);
					message.addData("joinStatus", "JOIN");
				} else if (cheapBase.getCheapCount() - list.size() == 1) {
					CheapOpenUserPO cheapUser = new CheapOpenUserPO();
					cheapUser.setCheapNo(list.get(0).getCheapNo());
					cheapUser.setCheapOpenId(key.getCheapOpenId());
					cheapUser.setMobile(key.getMobile());
					cheapUser.setTraded(Boolean.FALSE.toString().toUpperCase());
					cheapUser.setProfilePicKey(key.getProfilePicKey());
					cheapOpenUserDAO.insert(cheapUser);
					cheapOpenDAO.updateStatus(key.getCheapOpenId());
					message.addData("joinStatus", "SUCCESS");
					List<JoinUserVO> userlist = cheapOpenUserDAO.getJoinUser(key.getCheapOpenId());
					logger.info("smsProConfig :" + smsProConfig);
					for (JoinUserVO joinUserVO : userlist) {
						sendCheapSuccess(joinUserVO.getMobile(), smsProConfig.getCheapSuccessSMSContent(),
								joinUserVO.getGoodsName());
					}
				}

			}
		}

		return message;
	}


	@Override
	public void sendCheapSuccess(String mobile,String content,String goodsName) {
		logger.info("mobile:"+mobile+"content:"+content+"goodsName:"+goodsName);
		content = content.replace("#", goodsName);
		try {
			smsService.distributeSMSService(content,mobile);//短信提供者
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
	}
	
	
	
}
