package com.need.task.pub;

import java.io.Serializable;

/**
 * <p></p>
 * @author Rylan 2015年8月5日 下午4:57:46
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
	
	private Integer page=1;	//当前页 默认第一页
	
	private int pageCount=0;	//总页数
	
	private int total;		//一共数据
	
	private Integer pageSize=10;	//一页数据容量，默认等于10
	
	private int start=0;			//

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		if(page==0){
			this.page=1;
		}else{
			this.page = page;
		}		
	}

	public Integer getPageCount() {
		pageCount=total%pageSize==0?total/pageSize:total/pageSize+1;
		return pageCount;
	}


	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		if(total==0){
			this.total = 1;
		}else{
			this.total = total;
		}		
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public int getStart() {
		if(page<1){
			page=1;
		}
		if(page>getPageCount()){
			page=getPageCount();
		}
		start=(page-1)*pageSize;
		return start;
	}

	
	
	
	@Override
	public String toString() {
		return "Page [page=" + getPage() + ", pageCount=" + getPageCount() + ", total=" + getTotal() + ", pageSize=" + getPageSize()
				+ ", start=" + getStart() + "]";
	}
	

	
	
	
	
}

