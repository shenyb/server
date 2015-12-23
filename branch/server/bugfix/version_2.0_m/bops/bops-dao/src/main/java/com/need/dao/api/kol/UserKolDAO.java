package com.need.dao.api.kol;

import org.apache.ibatis.annotations.Param;

import com.need.domain.po.api.kol.UserKol;

public interface UserKolDAO {
    int deleteByPrimaryKey(String kolId);

    int insert(UserKol record);

    int insertApiKOL(UserKol record);

    UserKol selectByPrimaryKey(String kolId);

    int updateByPrimaryKeySelective(UserKol record);

    int updateByPrimaryKey(UserKol record);

	void updateSuccess(String kolId, String userType);
	/**
	 * 
	 * @author peiboli 2015年8月22日 下午6:48:04
	 * @Method: updateBykolId 
	 * @Description: TODO删除行家的同时，更新用户列表该行家变为普通用户
	 * @param kolId 
	 * @throws
	 */
	void updateBykolId(@Param("kolId")String kolId,@Param("commen")String commen);

	/**
	 * 
	 * @author peiboli 2015年9月6日 下午12:06:24
	 * @Method: deleteByKolId 
	 * @Description: TODO删除行家的同时，删除粉丝表里的该行家
	 * @param kolId 
	 * @throws
	 */
	void deleteByKolId(String kolId);

}