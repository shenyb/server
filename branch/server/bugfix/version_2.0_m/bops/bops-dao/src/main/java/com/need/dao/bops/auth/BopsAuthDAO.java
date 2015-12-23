package com.need.dao.bops.auth;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.need.domain.po.bops.auth.BopsAuth;
import com.need.domain.vo.auth.BopsAuthVO;

/**
 * <p>
 * </p>
 * @author Rylan 2015年7月27日 下午7:08:33
 * @ClassName BopsAuthDAO
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年7月27日
 * @modify by reason:{方法名}:{原因}
 */
public interface BopsAuthDAO {
	
	int deleteByPrimaryKey(Integer authId);
	
	int insert(BopsAuth record);
	
	int insertSelective(BopsAuth record);
	
	BopsAuth selectByPrimaryKey(Integer authId);
	
	int updateByPrimaryKeySelective(BopsAuth record);
	
	int updateByPrimaryKey( BopsAuth record);
	
	int selectCount(BopsAuthVO pageVO);
	
	List<BopsAuth> getAuthsByIds(@Param("authIds")String authIds);

	/**
	 * @author shenyb 2015年7月31日 下午11:52:12
	 * @Method: selectByPage 
	 * @Description: TODO
	 * @param bopsAuthParam
	 * @return 
	 * @throws 
	 */
	List<BopsAuth> selectByPage(BopsAuthVO pageVO);

	/**
	 * @author shenyb 2015年8月2日 上午3:09:06
	 * @Method: getAll 
	 * @Description: TODO
	 * @return 
	 * @throws 
	 */
	List<BopsAuth> getAll();
	/**
	 * @author Rylan 2015年8月5日 下午3:50:25
	 * @Method: selectBopsAuthsByPrimaryKeys 
	 * @Description: TODO
	 * @param auths
	 * @return 
	 * @throws
	 */
	public  List<BopsAuth> selectBopsAuthsByPrimaryKeys(String auths);
	/**
	 * @author Rylan 2015年8月5日 下午3:50:32
	 * @Method: selectauthScopesByPrimaryKeys 
	 * @Description: TODO
	 * @param auths
	 * @return 
	 * @throws
	 */
	public  List<String> selectAuthScopesByPrimaryKeys(@Param("auths")String auths);
	
}
