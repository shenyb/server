package com.need.share.service.need;

/**
 * <p></p>
 * @author Rylan 2015年8月25日 下午1:45:42
 * @ClassName NeedKolFansService
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年8月25日
 * @modify by reason:{方法名}:{原因}
 */
public interface NeedKolFansService {

	/**
	 * 
	 * @author xiehao 2015年8月15日 下午2:55:32
	 * @Method: countKolFans 
	 * @Description: TODO 得到行家的粉丝数
	 * @param kolId
	 * @return 
	 * @throws
	 */
	public int countKolFans(String kolId);
	
	/**
	 * 
	 * @author xiehao 2015年8月15日 下午2:56:01
	 * @Method: countUserConcern 
	 * @Description: TODO 得到用户关注的行家的数
	 * @param userId
	 * @return 
	 * @throws
	 */
	public int countUserConcern(String userId);
}
