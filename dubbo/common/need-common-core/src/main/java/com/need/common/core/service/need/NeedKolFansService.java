package com.need.common.core.service.need;

import com.need.common.domain.pub.Message;

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
	
	public Message countUerExternInfo(String userId);

}
