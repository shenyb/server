package com.need.marketing.service.user;

import com.need.marketing.dao.jdbc.user.po.UserBasePO;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * <p></p>
 * @author Rylan 2015年8月25日 上午1:02:45
 * @ClassName UserBaseService
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年8月25日
 * @modify by reason:{方法名}:{原因}
 */
public interface UserBaseService {


	/**
	 * @author shenyb 2015年7月25日 上午3:25:09 @Method:
	 *         selectByMobileAndLoginPwd @Description: 根据手机号和登录密码查询 @param
	 *         user @throws
	 * mapper
	 */
	List<UserBasePO> selectByMobile(@Param("mobile")String mobile);
    
    UserBasePO selectByPrimaryKey(String userId);
	
}
