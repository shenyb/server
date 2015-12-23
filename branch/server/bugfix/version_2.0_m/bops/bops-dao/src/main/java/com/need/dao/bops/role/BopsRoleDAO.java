package com.need.dao.bops.role;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.need.domain.po.bops.role.BopsRole;
import com.need.domain.vo.role.PageBopsRoleVO;
import com.need.domain.vo.role.RoleParamVO;
import com.need.domain.vo.role.RoleResultVO;

public interface BopsRoleDAO {
	
	int deleteByPrimaryKey( Integer roleId);
	
	int insert(BopsRole role);
	
	int insertNewRole(RoleParamVO role);
	
	BopsRole selectByPrimaryKey( Integer roleId);
	
	int updateById( BopsRole role);
	
	/**
	 * 
	 * @author xiehao 2015年8月4日 下午3:15:35
	 * @Method: getCountBopsRole 
	 * @Description: TODO 获得角色表的总条数
	 * @return 
	 * @throws
	 */
	int getCountBopsRole();
	
	int queryCountBopsRole(PageBopsRoleVO pageBopsRoleVO);
	
	/**
	 * 
	 * @author xiehao 2015年8月4日 下午3:16:03
	 * @Method: getPageOfBopsRole 
	 * @Description: TODO 获得角色的分页数据
	 * @param pageBopsRoleVO
	 * @return 
	 * @throws
	 */
	List<RoleResultVO> getPageOfBopsRole( PageBopsRoleVO pageBopsRoleVO);
	/**
	 * @author Rylan 2015年8月5日 下午3:19:51
	 * @Method: selectBopsRoleByPrimaryKeys 
	 * @Description: TODO
	 * @param roleIds
	 * @return 
	 * @throws
	 */
	List<BopsRole> selectBopsRoleByPrimaryKeys(@Param("roleIds")String roleIds);
	
	/**
	 * @author Rylan 2015年8月5日 下午3:19:55
	 * @Method: selectAuthIdsByPrimaryKeys 
	 * @Description: TODO
	 * @param roleIds
	 * @return 
	 * @throws
	 */
	List<String> selectAuthIdsByPrimaryKeys(@Param("roleIds")String roleIds);
	
	/**
	 * 
	 * @author xiehao 2015年8月7日 下午2:24:04
	 * @Method: searchRoleByName  
	 * @Description: TODO
	 * @param roleName
	 * @return 
	 * @throws
	 */
	List<BopsRole> findRoleByName(PageBopsRoleVO pageBopsRoleVO);
	
	/**
	 * @author Rylan 2015年8月11日 下午5:55:37
	 * @Method: selectAuthByPrimaryKeys 
	 * @Description: TODO
	 * @param roleIs
	 * @return 
	 * @throws
	 */
	List<BopsRole> selectAuthByPrimaryKeys(@Param("roleIs")String roleIs);

	/**
	 * 
	 * @author peiboli 2015年11月4日 下午4:46:09
	 * @Method: getAllRole 
	 * @Description: TODO get all  role
	 * @return 
	 * @throws
	 */
	List<RoleResultVO> getAllRole();
}
