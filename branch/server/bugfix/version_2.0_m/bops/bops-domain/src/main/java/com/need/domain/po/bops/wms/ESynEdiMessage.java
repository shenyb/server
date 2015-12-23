package com.need.domain.po.bops.wms;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import com.need.utils.wms.EdiConstants;
import com.need.utils.wms.EdiUtil;
import com.need.utils.wms.GenerateKeyHandler;

public class ESynEdiMessage implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 6981074570327838426L;

	private Long id;

	private String billId;

	private String type;

	private String sign;

	private Date sendDate;

	private String exception;

	private Long warehouseId;

	private Long status;

	private String body;

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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public ESynEdiMessage(String billId, String type, String body, long warehouseId) {
		this.id = Long.valueOf(String.valueOf(warehouseId) + String.valueOf(GenerateKeyHandler.generateKey()));
		this.billId = billId;
		this.type = type;
		this.body = body;
		this.sendDate = Calendar.getInstance().getTime();
		this.sign = EdiUtil.md5Encrypt(id + billId + type + body);
		this.status = EdiConstants.MSG_STATUS_SENDER;
		this.exception = "";
		this.warehouseId = warehouseId;
	}

	@Override
	public String toString() {
		return "ESynEdiMessage [id=" + id + ", billId=" + billId + ", type=" + type + ", sign=" + sign + ", sendDate="
				+ sendDate + ", exception=" + exception + ", warehouseId=" + warehouseId + ", status=" + status
				+ ", body=" + body + "]";
	}

}