package com.need.marketing.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.need.marketing.dao.jdbc.user.UserBaseDAO;
import com.need.marketing.dao.jdbc.user.po.UserBasePO;
import com.need.marketing.service.user.UserBaseService;

/**
 * <p></p>
 * @author Rylan 2015年8月25日 上午11:03:34
 * @ClassName UserBaseServiceImpl
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年8月25日
 * @modify by reason:{方法名}:{原因}
 */
@Service
public class UserBaseServiceImpl implements UserBaseService {

	@Autowired
	private UserBaseDAO userBaseDAO;

    @Override
    public List<UserBasePO> selectByMobile(String mobile) {
        return userBaseDAO.selectByMobile(mobile);
    }
    
    @Override
    public UserBasePO selectByPrimaryKey(String userId) {
        return userBaseDAO.selectByPrimaryKey(userId);
    }
	
	
	
		
}
