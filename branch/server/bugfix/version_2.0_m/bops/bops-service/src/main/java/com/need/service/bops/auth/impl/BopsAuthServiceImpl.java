package com.need.service.bops.auth.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.need.dao.api.user.UserBaseDAO;
import com.need.dao.bops.auth.BopsAuthDAO;
import com.need.domain.po.api.user.UserBase;
import com.need.domain.po.bops.auth.BopsAuth;
import com.need.domain.vo.auth.BopsAuthVO;
import com.need.service.bops.auth.BopsAuthService;

@Service
public class BopsAuthServiceImpl implements BopsAuthService {
	
	@Autowired
	private BopsAuthDAO bopsAuthDAO;
	@Autowired
	private UserBaseDAO userBaseDAO;
	
	@Override
	public int addBopsAuth(BopsAuth user) {
		return bopsAuthDAO.insert(user);
	}
	
	@Override
	public BopsAuth getBopsAuthById(Integer id) {
		return bopsAuthDAO.selectByPrimaryKey(id);
	}
	
	/**
	 * @author shenyb 2015年7月31日 下午11:16:22
	 * @Method: delete
	 * @Description: 
	 * @param id
	 * @return
	 * @see com.need.bops.service.auth.BopsAuthService#delete(java.lang.Integer)
	 */
	
	@Override
	public int delete(Integer id) {
		return bopsAuthDAO.deleteByPrimaryKey(id);
	}
	
	/**
	 * @author shenyb 2015年7月31日 下午11:16:22
	 * @Method: selectByPage
	 * @Description: 
	 * @param bopsAuthParam
	 * @return
	 * @see com.need.bops.service.auth.BopsAuthService#selectByPage(com.need.bops.web.controller.vo.auth.BopsAuthParam)
	 */
	
	@Override
	public List<BopsAuth> selectByPage(BopsAuthVO bopsAuthVO) {
		bopsAuthVO.setTotal(bopsAuthDAO.selectCount(bopsAuthVO));
		return bopsAuthDAO.selectByPage(bopsAuthVO);
	}
	
	/**
	 * @author shenyb 2015年7月31日 下午11:16:22
	 * @Method: update
	 * @Description: TODO
	 * @param user
	 * @return
	 * @see com.need.bops.service.auth.BopsAuthService#update(com.need.bops.dao.jdbc.auth.po.BopsAuth)
	 */
	
	@Override
	public int update(BopsAuth user) {
		return bopsAuthDAO.updateByPrimaryKey(user);
	}
	
	@Override
	public UserBase getUserBase(String id) {
		return userBaseDAO.selectByPrimaryKey(id);
	}
	
	/**
	 * @author shenyb 2015年8月2日 上午3:07:24
	 * @Method: getAll
	 * @Description: TODO
	 * @return
	 * @see com.need.bops.service.auth.BopsAuthService#getAll()
	 */
	
	@Override
	public List<BopsAuth> getAll() {
		return bopsAuthDAO.getAll();
	}
	
}
