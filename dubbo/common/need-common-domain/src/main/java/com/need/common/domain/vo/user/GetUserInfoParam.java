package com.need.common.domain.vo.user;

import java.io.Serializable;

/**
 * <p></p>
 * @author Rylan 2015年7月25日 下午12:33:15
 * @ClassName GetUserInfoParam
 * @Description TODO app参数层
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年7月25日
 * @modify by reason:{方法名}:{原因}
 */
public class GetUserInfoParam implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -5072647727338718082L;

	/** 
	* @Fields currentPage : TODO 当前页
	*/ 
	private Integer currentPage;
	
	/** 
	* @Fields pageSize : TODO 一页数据容量
	*/ 
	private Integer pageSize;

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
}


