package com.need.common.core.service.distribution;

import com.need.common.domain.pub.Message;

/**
 * 
 * <p></p>
 * @author shenyb 2015年11月30日 上午11:06:23
 * @ClassName UserCommissionService
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年11月30日
 * @modify by reason:{方法名}:{原因}
 */
public interface UserCommissionService {
    /***
     * 
     * @author LXD 2015年12月1日 下午6:50:07
     * @Method: getGoodsByuserId 
     * @Description: TODO 用户分销过的商品
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return 
     * @throws
     */
	Message getGoodsByuserId(String userId, int pageNum, int pageSize);
    /****
     *  
     * @author LXD 2015年12月1日 下午6:53:09
     * @Method: getIncomeByUserId 
     * @Description: TODO 根据用户ID查询用户的收益
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return 
     * @throws
     */
	Message getIncomeByUserId(String userId, int pageNum, int pageSize);
	/***
	 * 获取用户的总收益
	 * @author LXD 2015年12月4日 下午4:26:09
	 * @Method: getSumIncomeByUserId 
	 * @Description: TODO
	 * @param userId
	 * @return 
	 * @throws
	 */
	Message getSumIncomeByUserId(String userId);
	Message addGoods(String goodsId, String userId);

}
