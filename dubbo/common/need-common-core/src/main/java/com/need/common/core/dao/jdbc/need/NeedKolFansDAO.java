package com.need.common.core.dao.jdbc.need;


import com.need.common.domain.po.api.need.NeedKolFansPO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface NeedKolFansDAO {
	/***
	 * 
	 * @author LXD 2015年8月21日 下午3:04:17
	 * @Method: deleteById 
	 * @Description: TODO 根据ID删除
	 * @param id
	 * @return 
	 * @throws
	 */
    int deleteById(Integer id);
    /**
     * 
     * @author LXD 2015年8月21日 下午3:04:54
     * @Method: insert 
     * @Description: TODO 插入
     * @param record
     * @return 
     * @throws
     */
    int insert(NeedKolFansPO record);
     /**
      * 
      * @author LXD 2015年8月21日 下午3:05:07
      * @Method: getById 
      * @Description: TODO 根据ID查询
      * @param id
      * @return 
      * @throws
      */
   

    NeedKolFansPO getById(Integer id);

    /**
     * 
     * @author LXD 2015年8月21日 下午3:05:50
     * @Method: update 
     * @Description: TODO 更新
     * @param record
     * @return 
     * @throws
     */
    int update(NeedKolFansPO record);
    /***
     * 
     * @author LXD 2015年8月21日 下午3:06:04
     * @Method: getNeedKolFansPO 
     * @Description: TODO 根据userID、kolId、focusStatus查询NeedKolFansPO
     * @param userId
     * @param kolId
     * @param focusStatus
     * @return 
     * @throws
     */

	boolean getNeedKolFansPO(@Param("userId")String userId, @Param("kolId")String kolId,@Param("focusStatus")String focusStatus);
     /**
      * 
      * @author LXD 2015年8月21日 下午3:06:28
      * @Method: getConcernCount 
      * @Description: TODO 用户的关注数
      * @param userId
      * @return 
      * @throws
      */
	int getConcernCount(String userId);
	
	/**
	 * 
	 * @author xiehao 2015年8月15日 下午2:49:44
	 * @Method: countConcerned 
	 * @Description: TODO 得到行家的粉丝数
	 * @param kolId
	 * @return 
	 * @throws
	 */
	int countKolFans(String kolId);
    /**
     * 
     * @author LXD 2015年8月20日 下午10:45:35
     * @Method: getByParams 
     * @Description: TODO 根据用户ID和行家ID查处关系对象
     * @param userId
     * @param kolId
     * @return 
     * @throws
     */
	NeedKolFansPO getByParams(@Param("userId")String userId, @Param("kolId")String kolId,@Param("focusStatus")String focusStatus);
	/**
	 * 
	 * @author peiboli 2015年8月28日 下午5:23:43
	 * @Method: getByParams 
	 * @Description: TODO
	 * @param userId
	 * @param kolId
	 * @param focusStatus
	 * @return 
	 * @throws
	 */
	NeedKolFansPO getByKolId(@Param("userId")String userId, @Param("kolId")String kolId);
	/**
	 * 
	 * @author peiboli 2015年8月28日 下午5:37:37
	 * @Method: updateByParam 
	 * @Description: TODO批量添加行家
	 * @param userId
	 * @param kolId
	 * @return 
	 * @throws
	 */
	int updateByParam(@Param("userId")String userId, @Param("kolId")String kolId,@Param("focusStatus")String focusStatus);
	//同步数据
	List<NeedKolFansPO> queryAll();
}