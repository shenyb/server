package com.need.service.bops.auth;

import java.util.List;

import com.need.domain.po.api.user.UserBase;
import com.need.domain.po.bops.auth.BopsAuth;
import com.need.domain.vo.auth.BopsAuthVO;

public interface BopsAuthService {
	
	/**
	 * @author Rylan 2015年7月27日 下午7:33:12 @Method: addBopsAuth @Description: TODO 添加权限信息 @param user @return @throws
	 */
	public int addBopsAuth(BopsAuth user);
	
	/**
	 * @author Rylan 2015年7月27日 下午7:42:35 @Method: getBopsAuthById @Description: TODO @param id @return @throws
	 */
	public BopsAuth getBopsAuthById(Integer id);
	public int delete(Integer id);
	public List<BopsAuth> selectByPage(BopsAuthVO bopsAuthVO);
	public int update(BopsAuth user);
	/**
	 * @author Rylan 2015年7月30日 上午12:47:18
	 * @Method: getUserBase 
	 * @Description: TODO
	 * @param id
	 * @return 
	 * @throws
	 */
	public  UserBase getUserBase(String id);

	/**
	 * @author shenyb 2015年8月2日 上午3:06:27
	 * @Method: getAll 
	 * @Description: TODO
	 * @return 
	 * @throws 
	 */
	public List<BopsAuth> getAll();
	
	
}
