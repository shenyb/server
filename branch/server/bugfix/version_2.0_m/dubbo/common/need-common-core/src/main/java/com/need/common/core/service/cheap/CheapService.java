package com.need.common.core.service.cheap;

import com.need.common.domain.po.api.cheap.CheapBasePO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.pub.Page;
import com.need.common.domain.vo.cheap.CheapBaseBymobileVO;

import java.util.List;


public interface CheapService {

	/**
	 * 
	 * @author peiboli 2015年10月24日 下午1:31:10
	 * @Method: getCheapList 
	 * @Description: TODO获取团便宜列表
	 * @return 
	 * @throws
	 */
	List<CheapBasePO> getCheapList();
    

	/**
	 * 
	 * @author peiboli 2015年10月24日 下午1:31:10
	 * @Method: getClosedCheapList
	 * @Description: TODO用户获取已关闭的团便宜列表
	 * @return 
	 * @throws
	 */
	List<CheapBasePO> getClosedCheapList(String mobile);

	/**
	 * 
	 * @author peiboli 2015年10月24日 下午4:48:38
	 * @Method: getAvailableCheapList 
	 * @Description: TODO我的进行中团便宜列表
	 * @param mobile
	 * @return 
	 * @throws
	 */
	List<CheapBasePO> getAvailableCheapList(String mobile);

	/**
	 * 
	 * @author 庆凯 2015年10月24日 下午1:31:10
     * @param cheapOpenId
     * @param userId
	 * @Method: getCheapPriceByCheapOpenId 
	 * @Description: TODO获取团便宜价格和商品id
	 * @return 
	 */
    Message createTrade(Integer cheapOpenId, String userId, String addressId);

    public String checkCheapStatus(String cheapNo,String mobile);


    /**
     * 
     * @author peiboli 2015年11月9日 下午8:10:24
     * @Method: getCheapListByMobile 
     * @Description: TODO用户传mobile时的团便宜列表
     * @param mobile
     * @return 
     * @throws
     */
	List<CheapBaseBymobileVO> getCheapListByMobile(String mobile,Page page);
}
