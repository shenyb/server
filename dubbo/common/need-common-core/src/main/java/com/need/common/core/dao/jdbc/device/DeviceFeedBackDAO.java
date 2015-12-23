package com.need.common.core.dao.jdbc.device;

import com.need.common.domain.po.api.device.DeviceFeedBackPO;

/**
 * 
 * <p></p>
 * @author peiboli 2015年8月19日 下午7:20:36
 * @ClassName UserNeedCommentDAO
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年8月19日
 * @modify by reason:{方法名}:{原因}
 */
public interface DeviceFeedBackDAO {

	 void insertFeedBack(DeviceFeedBackPO comment);

	
}
