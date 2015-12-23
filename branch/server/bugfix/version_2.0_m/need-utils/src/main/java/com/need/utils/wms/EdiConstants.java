package com.need.utils.wms;

public class EdiConstants {
	   //传输类型常量
		public static final String TYPE_REPLY = "reply";
	   
		//以下是消息常量
		public static final String MSG_REPEAT = "repeat";
		public static final String MSG_RECEIVED_SUCCESS = "success";
		
		//以下是消息接收状态
		public static final long MSG_STATUS_RECEIVED = 1; //消息已接收，接收到的消息的初始状态，接收方初始状态
		public static final long MSG_STATUS_RECEIVED_SUCCESS = 2; //消息已接收，并处理成功
		public static final long MSG_STATUS_RECEIVED_CANCEL = 3; //消息取消处理
		public static final long MSG_STATUS_RECEIVED_FAILURE = 9; //消息已接收，但处理失败

		//以下是消息发送状态
		public static final long MSG_STATUS_SENDER = 1; //消息发送状态，初始状态，默认为这个
		public static final long MSG_STATUS_SENDER_SUCCESS = 2; //消息已被接收
		public static final long MSG_STATUS_SENDER_CANCEL = 3; //消息取消发送
		public static final long MSG_STATUS_SENDER_FAILURE = 9; //消息发送失败
		//缺省仓库
		public static final long WAREHOUSE_ID = 1;
}
