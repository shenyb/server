package com.need.dao.api.device;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.need.domain.pub.Page;
import com.need.domain.vo.device.FeedBackVO;

/**
 * 
 * <p></p>
 * @author peiboli 2015年8月28日 下午7:07:24
 * @ClassName FeedBackDAO
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年8月28日
 * @modify by reason:{方法名}:{原因}
 */
public interface DeviceFeedBackDAO {

	List<FeedBackVO> queryByPage(@Param("mobile")String mobile,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("feedBackpage")Page feedBackpage);
    
	int count(@Param("mobile")String mobile,@Param("startTime")String startTime,@Param("endTime")String endTime);

	
}
