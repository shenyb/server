package com.need.common.domain.vo.trade;

/**
 * 
 * <p>
 * </p>
 * 
 * @author shenyb 2015年8月9日 下午2:14:51
 * @ClassName RefundNotifyVO
 * @Description alipay 退款成功后的VO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年8月9日
 * @modify by reason:{方法名}:{原因}
 */
public class RefundNotifyVO {
	private String outPayNo;
	private Integer totalFee;

	public String getOutPayNo() {
		return outPayNo;
	}

	public void setOutPayNo(String outPayNo) {
		this.outPayNo = outPayNo;
	}

	public Integer getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}

}
