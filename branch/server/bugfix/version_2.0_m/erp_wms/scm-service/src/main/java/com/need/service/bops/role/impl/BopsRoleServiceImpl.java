package com.need.service.bops.role.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.need.dao.bops.auth.BopsAuthDAO;
import com.need.dao.bops.role.BopsRoleDAO;
import com.need.domain.common.enums.RoleStatusEnums;
import com.need.domain.po.bops.auth.BopsAuth;
import com.need.domain.po.bops.role.BopsRole;
import com.need.domain.vo.role.PageBopsRoleVO;
import com.need.domain.vo.role.RoleParamVO;
import com.need.domain.vo.role.RoleResultVO;
import com.need.service.bops.role.BopsRoleService;
@Service
public class BopsRoleServiceImpl implements BopsRoleService {

	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private BopsRoleDAO bopsRoleDAO;
	
	@Autowired
	private BopsAuthDAO bopsAuthDAO;
	
	@Override
	@Transactional
	public BopsRole addBopsRole(BopsRole role) {
		// TODO Auto-generated method stub
		role.setRoleStatus(RoleStatusEnums.NORMAL.code);
		bopsRoleDAO.insert(role);
		return role;
	}
	
	@Override
	@Transactional
	public RoleParamVO addNewBopsRle(RoleParamVO role){
		role.setRoleStatus(RoleStatusEnums.NORMAL.code);
		bopsRoleDAO.insertNewRole(role);
		return role;
	}

	@Override
	public BopsRole getBopsRoleById(Integer roleId) {
		// TODO Auto-generated method stub
		return bopsRoleDAO.selectByPrimaryKey(roleId);
	}

	@Override
	@Transactional
	public RoleResultVO updateBopsRole(BopsRole role) {
		// TODO Auto-generated method stub
		bopsRoleDAO.updateById(role);
		
		RoleResultVO roleResultVO = new RoleResultVO();
		BeanUtils.copyProperties(role, roleResultVO);
		
		String authListString = role.getAuthIds();
		authListString = authListString.replace("[", "").replace("]", "");
		List<BopsAuth> authList = bopsAuthDAO.getAuthsByIds(authListString);
		roleResultVO.setAuthList(authList);
		
		return roleResultVO;
	}

	@Override
	@Transactional
	public void deleteBopsRoleById(Integer roleId) {
		// TODO Auto-generated method stub
		bopsRoleDAO.deleteByPrimaryKey(roleId);
	}

	@Override
	public List<RoleResultVO> getPageOfBopsRole(PageBopsRoleVO pageBopsRoleVO) {
		// TODO Auto-generated method stub
		if(StringUtils.hasText(pageBopsRoleVO.getRoleName())){
			pageBopsRoleVO.setTotal(bopsRoleDAO.queryCountBopsRole(pageBopsRoleVO));
		}
		else{
			pageBopsRoleVO.setTotal(bopsRoleDAO.getCountBopsRole());
		}
		List<RoleResultVO> roleResultVOList = bopsRoleDAO.getPageOfBopsRole(pageBopsRoleVO);
		for(RoleResultVO roleResultVO : roleResultVOList){
			String authListString = roleResultVO.getAuthIds();
			authListString = authListString.replace("[", "").replace("]", "");
			List<BopsAuth> authList = bopsAuthDAO.getAuthsByIds(authListString);
			roleResultVO.setAuthList(authList);
		}
		
		return roleResultVOList;
	}

	@Override
	public List<BopsRole> searchRole(PageBopsRoleVO pageBopsRoleVO) {
		// TODO Auto-generated method stub
		pageBopsRoleVO.setTotal(bopsRoleDAO.getCountBopsRole());
		return bopsRoleDAO.findRoleByName(pageBopsRoleVO);
	}
	
}
