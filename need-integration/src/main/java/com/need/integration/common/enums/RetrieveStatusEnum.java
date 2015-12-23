package com.need.integration.common.enums;

public enum RetrieveStatusEnum {
	/**
	 * 以下非e贸易返回状态，是为了给客户展示
	 */
	RECEIVED("RECEIVED","已受理","您的订单已受理"),
	PAY_PUSH("PAY_PUSH","支付单已推送到海关","支付单已推送到海关"),
	LOGISTICS_PUSH("LOGISTICS_PUSH","运单已推送到海关","运单已推送到海关"),
	DELIVER_DONE("DELIVER_DONE","已发货","订单已交接给快递公司"),

	
	
	INTERIM("INTERIM", "暂存","订单已暂存，待申报"), 
	DECLARATION("DECLARATION", "申报","报关行已推送给海关，等待审核"),
	EXAM_PASS("EXAM_PASS", "审批通过","海关审核通过，等待放行"),
	INSPECTION_DETENTION("INSPECTION_DETENTION", "查验扣留"),
	INSPECTION_PASS("INSPECTION_PASS", "查验放行"),
	OK("OK", "放行","订单已放行，进入仓库生产"),
	END("NO", "结关","该订单已结关，请耐心等待发货"),
	BACK("BACK", "退单","海关审核不通过，客服稍后联系您");
	public String code;
	public String desc;
	public String userDesc;

	private RetrieveStatusEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	private RetrieveStatusEnum(String code, String desc,String userDesc) {
		this.code = code;
		this.desc = desc;
		this.userDesc=userDesc;
	}

}
