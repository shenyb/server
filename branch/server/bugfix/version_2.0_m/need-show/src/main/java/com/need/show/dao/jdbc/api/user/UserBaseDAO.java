package com.need.show.dao.jdbc.api.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.need.show.dao.jdbc.api.user.po.UserBasePO;



/**
 * <p>
 * </p>
 * 
 * @author Rylan 2015年7月25日 上午11:51:28
 * @ClassName UserBaseDAO
 * @Description UserBase数据访问层 ,此处注意一定不能使用select * .mysql 的sql语句要用小写
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年7月25日
 * @modify by reason:{方法名}:{原因}
 */
public interface UserBaseDAO {

	/**
	 * @author Rylan 2015年11月9日 下午3:06:40
	 * @Method: getCurrentUserCount 
	 * @Description: 得到当前的用户总数
	 * @param beginTime
	 * @return 
	 * @throws
	 */
	int getCurrentUserCount(String beginTime);
	

}