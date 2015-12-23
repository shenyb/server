package com.need.share.dao.jdbc.api.need;

import org.apache.ibatis.annotations.Param;

public interface NeedKolFansDAO {
	
     /**
      * 
      * @author LXD 2015年8月21日 下午3:06:28
      * @Method: getConcernCount 
      * @Description: TODO 用户的关注数
      * @param userId
      * @return 
      * @throws
      */
	int getConcernCount(@Param("userId") String userId);
	
	/**
	 * 
	 * @author xiehao 2015年8月15日 下午2:49:44
	 * @Method: countConcerned 
	 * @Description: TODO 得到行家的粉丝数
	 * @param kolId
	 * @return 
	 * @throws
	 */
	int getKolFansCount(@Param("kolId")String kolId);
   
}