package com.need.common.domain.pub;

import java.io.Serializable;


/**
 * <p></p>
 * @author Rylan 2015年8月5日 下午5:02:54
 * @ClassName Page
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年8月5日
 * @modify by reason:{方法名}:{原因}
 */
public class Page implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -8631592551281305163L;
	
	private Integer currentPage=1;	//当前页 默认第一页
	
	private Integer totalPageCount;	//页数
	
	private Integer totalCount;		//一共数据
	
	private Integer pageSize=10;	//一页数据容量，默认等于10
	
	private Integer start;			//
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		if(currentPage==null){
			this.currentPage=1;
		}else{
			this.currentPage = currentPage;
		}
	}
	public Integer getTotalPageCount() {
		totalPageCount=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
		return totalPageCount;
	}
	
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		if(totalCount==0){
			this.totalCount=1;
		}else{
			this.totalCount = totalCount;
		}
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getStart() {
		if(currentPage<1){
			currentPage=1;
		}
		/*if(currentPage>getTotalPageCount()){
			currentPage=getTotalPageCount();
		}*/
		start=(currentPage-1)*pageSize;
		return start;
	}
	@Override
	public String toString() {
		return "Page [currentPage=" + currentPage + ", totalPageCount="
				+ totalPageCount + ", totalCount=" + totalCount + ", pageSize="
				+ pageSize + ", start=" + start + "]";
	}
	
}

