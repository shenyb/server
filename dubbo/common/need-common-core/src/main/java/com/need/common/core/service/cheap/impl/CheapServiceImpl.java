package com.need.common.core.service.cheap.impl;

import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.constant.Constants;
import com.need.common.core.dao.jdbc.cheap.CheapBaseDAO;
import com.need.common.core.dao.jdbc.cheap.CheapOpenDAO;
import com.need.common.core.dao.jdbc.cheap.CheapOpenUserDAO;
import com.need.common.core.dao.jdbc.goods.GoodsMainDAO;
import com.need.common.core.dao.jdbc.user.UserBaseDAO;
import com.need.common.core.pub.ConstantsProConfig;
import com.need.common.core.service.cheap.CheapService;
import com.need.common.core.service.trade.TradeBaseService;
import com.need.common.domain.enums.CheapShowStatusEnum;
import com.need.common.domain.enums.CheapStatusEnum;
import com.need.common.domain.enums.CheckCheapEnum;
import com.need.common.domain.po.api.cheap.CheapBasePO;
import com.need.common.domain.po.api.cheap.CheapOpenPO;
import com.need.common.domain.po.api.cheap.CheapOpenUserPO;
import com.need.common.domain.po.api.cheap.CheapOpenUserPOKey;
import com.need.common.domain.po.api.user.UserBasePO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.pub.Page;
import com.need.common.domain.vo.cheap.CheapBaseBymobileVO;
import com.need.common.domain.vo.cheap.CheapShowStatus;
import com.need.common.domain.vo.cheap.CheapVO;
import com.need.common.domain.vo.goods.GoodsResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
public class CheapServiceImpl implements CheapService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheapServiceImpl.class);

	@Autowired
	private CheapBaseDAO cheapBaseDao;

	@Autowired
	private CheapOpenDAO cheapOpenDAO;
	@Autowired
	private GoodsMainDAO goodsMainDAO;

	@Autowired
	private CheapOpenUserDAO cheapOpenUserDAO;
    
    @Autowired
    private UserBaseDAO userBaseDAO;
    
   // @Autowired
    @Resource(name="constantsProConfig")
    private ConstantsProConfig constantsProConfig;
    
	@Autowired
	private TradeBaseService tradeBaseService;
	/**
	 * 
	 * @author peiboli 2015年10月23日 下午6:47:38
	 * @Method: getCheapList 
	 * @Description: TODO获取团便宜列表
	 * @return 
	 * @see com.need.api.service.cheap.CheapService#getCheapList()
	 */
	@Override
	public List<CheapBasePO> getCheapList() {
		
		List<CheapBasePO> list= cheapBaseDao.queryCheapPage();
		for(CheapBasePO cheapBasePO:list){
			cheapBasePO.setCheapUrl(constantsProConfig.getCheapUrl()+cheapBasePO.getCheapNo()+"&mobile=");
			//分享图片使用商品主图
			cheapBasePO.setCheapSharePicKey(cheapBasePO.getGoodsPicKey());
		}
		return list;
	}
	/**
	 * 
	 * @author peiboli 2015年10月24日 下午1:31:10
	 * @Method: getClosedCheapList
	 * @Description: TODO用户获取已关闭的团便宜列表
	 * @return 
	 * @throws
	 */
	@Override
	public List<CheapBasePO> getClosedCheapList(String mobile) {
		List<CheapBasePO> list= cheapOpenDAO.queryCloseCheapByMobile(mobile);
		for(CheapBasePO cheapBasePO:list){
			cheapBasePO.setCheapUrl(constantsProConfig.getCheapUrl()+cheapBasePO.getCheapNo()+"&mobile=");
			//分享图片使用商品主图
			cheapBasePO.setCheapSharePicKey(cheapBasePO.getGoodsPicKey());
		}
		return list;
	}
	/**
	 * 
	 * @author peiboli 2015年10月24日 下午4:50:34
	 * @Method: getAvailableCheapList 
	 * @Description: TODO我的进行中团便宜列表
	 * @param mobile
	 * @return 
	 * @see com.need.api.service.cheap.CheapService#getAvailableCheapList(java.lang.String)
	 */
	@Override
	public List<CheapBasePO> getAvailableCheapList(String mobile) {
		List<CheapBasePO> list= cheapOpenDAO.queryAvailableCheapByMobile(mobile);
		for(CheapBasePO cheapBasePO:list){
			cheapBasePO.setCheapUrl(constantsProConfig.getCheapUrl()+cheapBasePO.getCheapNo()+"&mobile=");
			//分享图片使用商品主图
			cheapBasePO.setCheapSharePicKey(cheapBasePO.getGoodsPicKey());
		}
		return list;
	}

    @Override
    @Transactional
    public Message createTrade(Integer cheapOpenId, String userId, String addressId) {
        CheapOpenPO cheapOpenPO = cheapOpenDAO.selectByPrimaryKey(cheapOpenId);
        if(cheapOpenPO == null) {
            return Message.error(BizReturnCode.CHEAP_NOT_OPEN);
        }
        if(CheapStatusEnum.CLOSED.status.equals(cheapOpenPO.getCheapStatus())) {
            return Message.error(BizReturnCode.CHEAP_CLOSED);
        }
        if(CheapStatusEnum.OPEN.status.equals(cheapOpenPO.getCheapStatus())) {
            return Message.error(BizReturnCode.CHEAP_NOT_COMPLETE);
        }
        if(cheapOpenPO.getCompleteTime() == null || System.currentTimeMillis() - cheapOpenPO.getCompleteTime().getTime() > Constants.CHEAP_TRADE_EXPIRE) {
            return Message.error(BizReturnCode.CHEAP_TRADE_EXPIRED);
        }
        UserBasePO user = userBaseDAO.selectByPrimaryKey(userId);
        if(user == null) {
            return Message.error(BizReturnCode.USER_NOT_EXIST);
        }
        List<CheapOpenUserPO> tradedList = cheapOpenUserDAO.queryByParams(user.getMobile(), cheapOpenPO.getCheapNo(), Boolean.TRUE.toString().toUpperCase());
        if(!tradedList.isEmpty()) {
            return Message.error(BizReturnCode.CHEAP_TRADED);
        }
        CheapOpenUserPOKey cheapOpenUserPOKey = new CheapOpenUserPOKey(cheapOpenId, user.getMobile());
        CheapOpenUserPO cheapOpenUserPO = cheapOpenUserDAO.selectByPrimaryKey(cheapOpenUserPOKey);
        if(cheapOpenUserPO == null) {
            return Message.error(BizReturnCode.CHEAP_NOT_JOIN);
        }
        if(Boolean.TRUE.toString().equals(cheapOpenUserPO.getTraded())) {
            return Message.error(BizReturnCode.CHEAP_TRADED);
        }
        CheapBasePO cheapBasePO = cheapBaseDao.selectById(cheapOpenPO.getCheapNo());
        Message message = tradeBaseService.createCheapTrade(userId, cheapBasePO.getGoodsId(), cheapBasePO.getCheapPrice(), addressId);
        if(message.getCode()==Message.SUCCESS) {
            cheapOpenUserPO.setTraded(Boolean.TRUE.toString().toUpperCase());
            cheapOpenUserDAO.updateByPrimaryKey(cheapOpenUserPO);
        }
        return message;
    }
    /**
     * 
     * @author peiboli 2015年10月26日 上午10:14:31
     * @Method: checkCheapStatus 
     * @Description: TODO校验团便宜状态
     * @param cheapNo
     * @param mobile
     * @return 
     * @see com.need.api.service.cheap.CheapService#checkCheapStatus(java.lang.String, java.lang.String)
     */
	@Override
	public String checkCheapStatus(String cheapNo, String mobile) {
		CheapBasePO cheap = cheapBaseDao.selectById(cheapNo);
		GoodsResultVO GoodsResultVO = goodsMainDAO.getGoodsById(cheap.getGoodsId());		
		if(GoodsResultVO.getStoreCount()==0){
			return CheckCheapEnum.SOLDOUT.status;
		}
		List<CheapVO> list= cheapBaseDao.getCheapByMobile(cheapNo,mobile);
		
		if(list==null){
			return CheckCheapEnum.OPEN.status; 
		}
		if(CheapStatusEnum.OPEN.status.equals(list.get(0).getOpenCheapStatus())){
			return CheckCheapEnum.CALL.status;
		}
		if(CheapStatusEnum.COMPLETE.status.equals(list.get(0).getCheapStatus())&&list.get(0).getTraded().equals(Boolean.FALSE)){
			
			return CheckCheapEnum.NOTPAY.status;
		}
		if(CheapStatusEnum.COMPLETE.status.equals(list.get(0).getCheapStatus())&&list.get(0).getTraded().equals(Boolean.TRUE)){
			return CheckCheapEnum.PAY.status;
		}
		return null;
	}
	/**
	 * 
	 * @author peiboli 2015年11月9日 下午8:18:29
	 * @Method: getCheapListByMobile 
	 * @Description: TODO获得cheapList，传mobile时
	 * @param mobile
	 * @return 
	 * @see com.need.api.service.cheap.CheapService#getCheapListByMobile(java.lang.String)
	 */
	@Override
	public List<CheapBaseBymobileVO> getCheapListByMobile(String mobile,Page page) {
		// TODO Auto-generated method stub
		List<CheapShowStatus> showList = cheapBaseDao.queryShowList(mobile);
		page.setTotalCount(cheapBaseDao.Cheapcount());//当传手机号的时候，得到的团便宜总数
		List<CheapBaseBymobileVO> list = cheapBaseDao.queryCheapPageByMobile(page);//传手机号是得到的cheapList
		for(CheapBaseBymobileVO cheapBaseBymobileVO:list){
			cheapBaseBymobileVO.setCheapShowStatus(CheapShowStatusEnum.NORMAL.status);
			cheapBaseBymobileVO.setCheapUrl(constantsProConfig.getCheapUrl()+cheapBaseBymobileVO.getCheapNo()+"&mobile=");
			//分享图片使用商品主图
			cheapBaseBymobileVO.setCheapSharePicKey(cheapBaseBymobileVO.getGoodsPicKey());
		}
		int listLength= list.size();
		int showListLength= showList.size();
		System.out.println(Boolean.TRUE.toString().toUpperCase());
		for(int i=0;i<listLength;i++){
			for(int j=0;j<showListLength;j++){
				if(showList.get(j).getCheapNo().equals(list.get(i).getCheapNo())){
					if(showList.get(j).getTraded().equals(Boolean.TRUE.toString().toUpperCase())){
						//已下单
						list.get(i).setCheapShowStatus(CheapShowStatusEnum.ORDERS.status);
					} else {
						//未下单
						if(CheapStatusEnum.OPEN.status.equals(showList.get(j).getCheapStatus())){
							//开团中
							list.get(i).setCheapShowStatus(CheapShowStatusEnum.OPEN.status);
						}
						if(CheapStatusEnum.COMPLETE.status.equals(showList.get(j).getCheapStatus())){
							//待支付
							list.get(i).setCheapShowStatus(CheapShowStatusEnum.WAITPAY.status);
						}
						
					}	
				}
			}
		}
		return list;
	}

}
