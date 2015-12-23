package com.need.dao.bops.user;

import java.util.List;
import java.util.Map;

import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.vo.user.QueryBopsUserVO;


/**
 * <p></p>
 * @author Rylan 2015年7月30日 上午11:40:03
 * @ClassName BopsUseDAO
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年7月30日
 * @modify by reason:{方法名}:{原因}
 */
public interface BopsUserDAO {
	
    int deleteByPrimaryKey(Integer userId);
    int updateByPrimaryKey(BopsUser user);
    int updatePwdByPrimaryKey(Map<String, Object> user);
    int insert(BopsUser user);
    
    /**
     * @author Rylan 2015年7月30日 上午11:44:58
     * @Method: selectByPrimaryKey 
     * @Description: TODO 根据用户id查询用户信息
     * @param userId
     * @return 
     * @throws
     */
    BopsUser selectByPrimaryKey( Integer userId);

    int updateByPrimaryKeySelective(BopsUser record);

    /**
     * @author Rylan 2015年7月30日 上午11:44:45
     * @Method: selectByUserName 
     * @Description: TODO 根据用户名查询用户信息
     * @param username
     * @return 
     * @throws
     */
    BopsUser selectByUserName( String userName);

	/**
	 * @author shenyb 2015年8月1日 上午10:34:13
	 * @Method: updateUserToken 
	 * @Description: TODO
	 * @param bopsUserByUserName 
	 * @throws 
	 */
	void updateUserToken(BopsUser bopsUser);

	/**
	 * @author shenyb 2015年8月1日 上午11:14:29
	 * @Method: getBopsUserByToken 
	 * @Description: TODO
	 * @param token
	 * @return 
	 * @throws 
	 */
	BopsUser getBopsUserByToken( String token);
    
    /**
     * 
     * @author xiehao 2015年8月4日 下午3:14:22
     * @Method: countBospUser 
     * @Description: TODO 统计用户表的总条数
     * @return 
     * @throws
     */
    int countBospUser();
    
    int queryCountBospUser(QueryBopsUserVO queryBopsUserVO);
    
    /**
     * 
     * @author xiehao 2015年8月4日 下午3:14:52
     * @Method: getPageOfBopsUser 
     * @Description: TODO 获得用户的分页数据
     * @param pageBopsUserVO
     * @return 
     * @throws
     */
    List<BopsUser> getPageOfBopsUser(QueryBopsUserVO queryBopsUserVO);
    
    List<BopsUser> queryBopsUer(QueryBopsUserVO queryBopsUser);
    
    List<BopsUser> getAllUser();
	List<BopsUser> getOpsUser();
}