package com.need.service.bops.user;

import java.util.List;

import com.need.domain.po.bops.auth.BopsAuth;
import com.need.domain.po.bops.role.BopsRole;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.vo.user.QueryBopsUserVO;

public interface BopsUserService {

	/**
	 * @author Rylan 2015年7月30日 上午11:36:22
	 * @Method: loginService 
	 * @Description: TODO 提供登陆业务,如果登陆成功保持到redis中
	 * @param username
	 * @param password
	 * @return 
	 * @throws
	 */
	public boolean loginService(String username,String password,StringBuffer bopsUserKey,BopsUser bopsUser);
	
	/**
	 * @author Rylan 2015年7月30日 上午11:36:40
	 * @Method: getBopsUserInfo 
	 * @Description: TODO 查询用户信息
	 * @param id
	 * @return 
	 * @throws
	 */
	public BopsUser getBopsUserInfoById(Integer id);
	
	/**
	 * @author Rylan 2015年7月30日 上午11:36:55
	 * @Method: addBopsUserInfo 
	 * @Description: TODO 添加用户信息
	 * @param bopsUser
	 * @return 
	 * @throws
	 */
	public BopsUser addBopsUserInfo(BopsUser bopsUser);
	
	/**
	 * @author Rylan 2015年7月30日 下午5:20:42
	 * @Method: saveBopsUserInfoToRedis 
	 * @Description: TODO 向redis中保存用户信息
	 * @param bopsUser
	 * @return 
	 * @throws
	 */
	public String saveBopsUserInfoToRedis(BopsUser bopsUser);
	
	/**
	 * @author Rylan 2015年7月30日 下午6:01:47
	 * @Method: getBopsUserByUserName 
	 * @Description: TODO
	 * @param name
	 * @return 
	 * @throws
	 */
	public BopsUser getBopsUserByUserName(String name);
	
	/**
	 * @author Rylan 2015年7月30日 下午6:07:33
	 * @Method: saveBopsUserByUserNameToRedis 
	 * @Description: TODO 
	 * @param userName
	 * @return 
	 * @throws
	 */
	public String saveBopsUserByUserNameToRedis(String userName);
 
	
	/**
	 * @author shenyb 2015年8月1日 上午10:30:44
	 * @Method: updateToken 
	 * @Description: TODO
	 * @param bopsUserByUserName 
	 * @throws 
	 */
	public void updateBospUserInfo(BopsUser user);
    
    public int updateBospUserPwd(Integer userId, String oldPwd, String newPwd);

	public void updateToken(BopsUser bopsUser);

	/**
	 * @author shenyb 2015年8月1日 上午11:13:22
	 * @Method: getBopsUserByToken 
	 * @Description: TODO
	 * @param token
	 * @return 
	 * @throws 
	 */
	public void deleteBospUserById(Integer userId);
	
	

	public BopsUser getBopsUserByToken(String token);

	/**
	 * @author shenyb 2015年8月1日 下午3:28:08
	 * @Method: getBopsUserAuths 
	 * @Description: TODO
	 * @param token
	 * @return 
	 * @throws 
	 */
	public List<BopsAuth> getBopsUserAuths(BopsUser user);
	
	/**
	 * 
	 * @author xiehao 2015年8月4日 下午3:12:43
	 * @Method: getPageOfBopsUser 
	 * @Description: TODO 获得用户的分页数据
	 * @param pageBopsUserVO
	 * @return 
	 * @throws
	 */
	public List<BopsUser> getPageOfBopsUser(QueryBopsUserVO queryBopsUserVO);
	
	/**
	 * 
	 * @author xiehao 2015年8月5日 上午9:38:40
	 * @Method: queryBopsUser 
	 * @Description: TODO 根据条件查询用户
	 * @param queryBopsUser
	 * @return 
	 * @throws
	 */
	public List<BopsUser> queryBopsUser(QueryBopsUserVO queryBopsUser);
	
	
	/**
	 * @author Rylan 2015年8月5日 下午2:38:20
	 * @Method: getBopsAuthsByUserId 
	 * @Description: TODO 通过用户id获得用户的所有权限对象信息
	 * @param userId
	 * @return 
	 * @throws
	 */
	public List<BopsAuth> getBopsAuthsByUserId(Integer userId);

	
	/**
	 * @author Rylan 2015年8月5日 下午2:38:22
	 * @Method: getAuthIdsByUserId 
	 * @Description: TODO 通过用户id获得用户的所有权限内容信息
	 * @param userId
	 * @return 
	 * @throws
	 */
	public  List<String> getAuthScopesByUserId(Integer userId);

	
	/**
	 * @author Rylan 2015年8月11日 下午6:10:42
	 * @Method: getBopsRolesByUserId 
	 * @Description: TODO
	 * @param roles
	 * @return 
	 * @throws
	 */
	public List<BopsRole> getBopsRolesByRoleId(String roles);
	
	
	public  List<String> getAuthScopesByRoleIds(String roleIds);
	
}
