package com.need.integration.common.enums;

/**
 * 100 等待审核 120 等待客服审核 130 审核完成 300 等待仓库处理 310 等待打印 320 打印完成 350 正在拣选 400 拣料完成
 * 420 包装完成 430 终检完成 450 下料完成 500 交接完成
 * 
 * <p>
 * </p>
 * 
 * @author shenyb 2015年10月26日 下午10:05:19
 * @ClassName DeliverStatusEnum
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年10月26日
 * @modify by reason:{方法名}:{原因}
 */
public enum DeliverStatusEnum {
	WAIT_AUDIT("100", "等待审核"), WAIT_SERVICE_AUDIT("120", "等待客服审核"),
	EXAM_DONE("130","审核完成"), WAIT_WAREHOUSE_DEAL("300", "等待仓库处理"), 
	WAIT_PRINT("310","等待打印"), PRINT_DONE("320", "打印完成"), 
	NOW_SELECT("350","正在拣选"), SELECT_DONE("400", "拣料完成"), 
	PACKING_DONE("420", "包装完成"),LAST_EXAM_PASS("430", "终检完成"), 
	GET_MATERIAL_DONE("450", "下料完成"), OK("500", "交接完成");
	public String code;
	public String desc;

	private DeliverStatusEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

}
