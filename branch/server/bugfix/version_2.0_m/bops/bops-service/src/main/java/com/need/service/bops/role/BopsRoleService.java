package com.need.service.bops.role;

import java.util.List;

import com.need.domain.po.bops.role.BopsRole;
import com.need.domain.vo.role.PageBopsRoleVO;
import com.need.domain.vo.role.RoleParamVO;
import com.need.domain.vo.role.RoleResultVO;

public interface BopsRoleService {
	
	BopsRole addBopsRole(BopsRole role);
	
	RoleParamVO addNewBopsRle(RoleParamVO role);
	
	BopsRole getBopsRoleById(Integer roleId);
	
	RoleResultVO updateBopsRole(BopsRole role);
	
	void deleteBopsRoleById(Integer roleId);
	
	List<RoleResultVO> getPageOfBopsRole(PageBopsRoleVO pageBopsRoleVO);
	
	public List<BopsRole> searchRole(PageBopsRoleVO pageBopsRoleVO);

}
