package com.need.common.domain.vo.need;

import java.io.Serializable;
/**
 * 
 * <p></p>
 * @author LXD 2015年8月12日 下午5:16:45
 * @ClassName FeedNeedVO
 * @Description TODO 首页needVO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年8月12日
 * @modify by reason:{方法名}:{原因}
 */
public class FeedNeedVO implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 6568690910945124551L;
	
	private Integer needCount;
	
	public Integer getNeedCount() {
		return needCount;
	}
	public void setNeedCount(Integer needCount) {
		this.needCount = needCount;
	}
	
	

}
