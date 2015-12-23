package com.need.domain.common.enums;

import org.apache.commons.lang.StringUtils;

public enum TrackingCodeEnum {

	/**
	 * 笨鸟返回状态
	 */
	IDENTITYNOTPASS("IDENTITYNOTPASS", "身份证照片审核", "身份证照片审核未通过"),
	PULLING("PULLING", "下架", "您的订单已经拣货完成"),
	COMBINE("COMBINE", "合箱称重", "您的订单已打包完成"),
	TURNIN("TURNIN", "打包交接", "您的订单已装袋扫描，出库中"),
	CHECKOUT("CHECKOUT", "出库", "您的订单已完成出库，等待提货【笨鸟海淘：运单号】"),
	AIRLINE("AIRLINE", "等待清关", "宝贝已到达目的口岸，等待清关"),
	CUSTOMS("CUSTOMS", "清关", "清关"),
	DOMESTICEXCHANGE("DOMESTICEXCHANGE", "国内换单", "您的订单已经交给国内快递公司【国内快递：单号】"),
	DISPATCH("DISPATCH", "派送", "派送"),
	SIGN("SIGN", "签收", "签收"),
	OTHER("OTHER", "其他", "其他"),
	
	
	/**
	 * e贸易状态
	 */
	RECEIVED("RECEIVED", "已受理", "您的订单已受理"),
	PAY_PUSH("PAY_PUSH","支付单已推送到海关","支付单已推送到海关"),
	LOGISTICS_PUSH("LOGISTICS_PUSH","运单已推送到海关","运单已推送到海关"),
	DELIVER_DONE("DELIVER_DONE","已发货","订单已提交给快递公司"),
	INTERIM("INTERIM", "暂存","订单已暂存，待申报"), 
	DECLARATION("DECLARATION", "申报","报关行已推送给海关，等待审核"),
	EXAM_PASS("EXAM_PASS", "审批通过","海关审核通过，等待放行"),
	INSPECTION_DETENTION("INSPECTION_DETENTION", "查验扣留", "查验扣留"),
	INSPECTION_PASS("INSPECTION_PASS", "查验放行", "查验放行"),
	OK("OK", "放行","订单已放行，进入仓库生产"),
	END("NO", "结关","该订单已结关，请耐心等待发货"),
	BACK("BACK", "退单","海关审核不通过，客服稍后联系您"),
	REFUND("REFUND","退款","您的订单已退款完成，请及时查看您账户信息"),
	
	/**
	 * 圆通
	 */
	ACCEPT("ACCEPT","接单",""),
	DEPARTURE("DEPARTURE","已发出",""),
	UNACCEPT("UNACCEPT","不接单",""),
	GOT("GOT","揽收成功",""),
	NOT_SEND("NOT_SEND","揽收失败",""),
	ARRIVAL("ARRIVAL","已收入",""),
	PACKAGE ("PACKAGE","已打包",""),
	UNPACK ("UNPACK","已拆包",""),
	SENT_SCAN ("SENT_SCAN","派件",""),
	SIGNED  ("SIGNED","签收成功",""),
	FAILED("FAILED"," 签收失败","");
	
	public String code;
	public String desc;
	public String userDesc;
	
	private TrackingCodeEnum(String code, String desc, String userDesc){
		this.code = code;
		this.desc = desc;
		this.userDesc = userDesc;
	}
	
	public static String getDesc(String code){
		String result = "";
		if(StringUtils.isBlank(code)){
			return result;
		}
		switch(code){
		case "IDENTITYNOTPASS":
			result = IDENTITYNOTPASS.desc;
			break;
		case "PULLING":
			result = PULLING.desc;
			break;
		case "COMBINE":
			result = COMBINE.desc;
			break;
		case "TURNIN":
			result = TURNIN.desc;
			break;
		case "CHECKOUT":
			result = CHECKOUT.desc;
			break;
		case "AIRLINE":
			result = AIRLINE.desc;
			break;
		case "CUSTOMS":
			result = CUSTOMS.desc;
			break;
		case "DOMESTICEXCHANGE":
			result = DOMESTICEXCHANGE.desc;
			break;
		case "DISPATCH":
			result = DISPATCH.desc;
			break;
		case "SIGN":
			result = SIGN.desc;
			break;
		case "OTHER":
			result = OTHER.desc;
			break;
		/**
		 * 
		 */
		case "RECEIVED":
			result = RECEIVED.desc;
			break;
		case "PAY_PUSH":
			result = PAY_PUSH.desc;
			break;
		case "LOGISTICS_PUSH":
			result = LOGISTICS_PUSH.desc;
			break;
		case "DELIVER_DONE":
			result = DELIVER_DONE.desc;
			break;
		case "INTERIM":
			result = INTERIM.desc;
			break;
		case "DECLARATION":
			result = DECLARATION.desc;
			break;
		case "EXAM_PASS":
			result = EXAM_PASS.desc;
			break;
		case "INSPECTION_DETENTION":
			result = INSPECTION_DETENTION.desc;
			break;
		case "INSPECTION_PASS":
			result = INSPECTION_PASS.desc;
			break;
		case "OK":
			result = OK.desc;
			break;
		case "END":
			result = END.desc;
			break;
		case "BACK":
			result = BACK.desc;
			break;
		case "ACCEPT":
			result = ACCEPT.desc;
			break;
		case "DEPARTURE":
			result = DEPARTURE.desc;
			break;
		case "UNACCEPT":
			result = UNACCEPT.desc;
			break;
		case "GOT":
			result = GOT.desc;
			break;
		case "NOT_SEND":
			result = NOT_SEND.desc;
			break;
		case "ARRIVAL":
			result = ARRIVAL.desc;
			break;
		case "PACKAGE":
			result = PACKAGE.desc;
			break;
		case "UNPACK":
			result = UNPACK.desc;
			break;
		case "SENT_SCAN":
			result = SENT_SCAN.desc;
			break;
		case "SIGNED":
			result = SIGNED.desc;
			break;
		case "FAILED":
			result = FAILED.desc;
			break;
		case "REFUND":
			result = REFUND.desc;
			break;
		default:
			result = "";
			break;
		}
		return result;
	}
	
	public static String getUserDesc(String code){
		String result = "";
		if(StringUtils.isBlank(code)){
			return result;
		}
		switch(code){
		case "IDENTITYNOTPASS":
			result = IDENTITYNOTPASS.userDesc;
			break;
		case "PULLING":
			result = PULLING.userDesc;
			break;
		case "COMBINE":
			result = COMBINE.userDesc;
			break;
		case "TURNIN":
			result = TURNIN.userDesc;
			break;
		case "CHECKOUT":
			result = CHECKOUT.userDesc;
			break;
		case "AIRLINE":
			result = AIRLINE.userDesc;
			break;
		case "CUSTOMS":
			result = CUSTOMS.userDesc;
			break;
		case "DOMESTICEXCHANGE":
			result = DOMESTICEXCHANGE.userDesc;
			break;
		case "DISPATCH":
			result = DISPATCH.userDesc;
			break;
		case "SIGN":
			result = SIGN.userDesc;
			break;
		case "OTHER":
			result = OTHER.userDesc;
			break;
		case "REFUND":
			result = REFUND.userDesc;
			break;
		
		/**
		 * 	
		 */
		case "RECEIVED":
			result = RECEIVED.userDesc;
			break;
		case "PAY_PUSH":
			result = PAY_PUSH.userDesc;
			break;
		case "LOGISTICS_PUSH":
			result = LOGISTICS_PUSH.userDesc;
			break;
		case "DELIVER_DONE":
			result = DELIVER_DONE.userDesc;
			break;
		case "INTERIM":
			result = INTERIM.userDesc;
			break;
		case "DECLARATION":
			result = DECLARATION.userDesc;
			break;
		case "EXAM_PASS":
			result = EXAM_PASS.userDesc;
			break;
		case "INSPECTION_DETENTION":
			result = INSPECTION_DETENTION.userDesc;
			break;
		case "INSPECTION_PASS":
			result = INSPECTION_PASS.userDesc;
			break;
		case "OK":
			result = OK.userDesc;
			break;
		case "END":
			result = END.userDesc;
			break;
		case "BACK":
			result = BACK.userDesc;	
			break;
			
		default:
			result = "";
			break;
		}
		return result;
	}
}
