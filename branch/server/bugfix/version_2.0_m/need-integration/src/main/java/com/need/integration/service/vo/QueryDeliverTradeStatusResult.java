package com.need.integration.service.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>
 * </p>
 * 
 * @author shenyb 2015年10月27日 上午9:45:35
 * @ClassName CreateDeliverTradeParamVO
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年10月27日
 * @modify by reason:{方法名}:{原因}
 */
public class QueryDeliverTradeStatusResult {
	private String totalCount;
	private String pageNo;
	private String pageSize;
	private String pageNum;
	private List<Trades> trade = new ArrayList<Trades>();

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public List<Trades> getTrade() {
		return trade;
	}

	public void setTrade(List<Trades> trade) {
		this.trade = trade;
	}

	public class Trades {
		String orderNo;
		String status;
		String transporterId;
		String outIds;

		public String getOrderNo() {
			return orderNo;
		}

		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getTransporterId() {
			return transporterId;
		}

		public void setTransporterId(String transporterId) {
			this.transporterId = transporterId;
		}

		public String getOutIds() {
			return outIds;
		}

		public void setOutIds(String outIds) {
			this.outIds = outIds;
		}

	}

}
