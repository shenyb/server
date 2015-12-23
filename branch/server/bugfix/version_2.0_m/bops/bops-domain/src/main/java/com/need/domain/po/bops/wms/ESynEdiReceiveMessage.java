package com.need.domain.po.bops.wms;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import com.need.utils.wms.EdiConstants;
import com.need.utils.wms.EdiUtil;
import com.need.utils.wms.GenerateKeyHandler;

public class ESynEdiReceiveMessage implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 7496042570610451393L;

	private long id; //自动生成的ID，作为主键
	private String billId; //业务单据ID号
	private String type; //传输业务类型，后续根据这个业务类型进行处理匹配
	private String body; //传输的消息内容
	private String sign; //签名，验证消息是否传输正确，避免丢失
	private Date sendDate; //发送时间
	private Date receiveDate; //接收时间
	private Date handleDate; //处理时间
	private Long status;    //0表示准备发送，1表示已接收，2表示已返回，-1表示发送错误，-2表示返回错误，-3表示执行异常
	private String exception; //保持异常信息
	private long warehouseId;  //仓库ID
	private String queue; //接收队列
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getQueue() {
		return queue;
	}

	public void setQueue(String queue) {
		this.queue = queue;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public Date getHandleDate() {
		return handleDate;
	}

	public void setHandleDate(Date handleDate) {
		this.handleDate = handleDate;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public ESynEdiReceiveMessage(String billId, String type, String body, long warehouseId) {
		this.id = GenerateKeyHandler.generateKey();
		this.billId = billId;
		this.type = type;
		this.body = body;
		this.sendDate = Calendar.getInstance().getTime();
		this.sign = EdiUtil.md5Encrypt(id + billId + type + body);
		this.status = EdiConstants.MSG_STATUS_SENDER;
		this.exception = "";
		this.warehouseId = warehouseId;
		this.queue = "";
	}
	public ESynEdiReceiveMessage(){
		
	}
	@Override
	public String toString() {
		return "ESynEdiReceiveMessage [id=" + id + ", billId=" + billId + ", type=" + type + ", sign=" + sign
				+ ", sendDate=" + sendDate + ", exception=" + exception + ", warehouseId=" + warehouseId + ", status="
				+ status + ", queue=" + queue + ", receiveDate=" + receiveDate + ", handleDate=" + handleDate
				+ ", body=" + body + "]";
	}

}