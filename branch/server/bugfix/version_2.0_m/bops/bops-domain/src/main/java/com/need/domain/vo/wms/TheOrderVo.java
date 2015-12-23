package com.need.domain.vo.wms;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 客户订单VO
 *
 */
public class TheOrderVo implements Serializable {
	/**
	 * 客户订单号同billNo
	 */
    private String id;
    /**
     * 库房号
     */
    private Long warehouseId;
    /**
     * 客户订单号
     */
    private String billNo;
    /**
     * 支付方式
     * 1 现金支付(包含POS刷卡)
     */
    private Long payType;
    private Integer orderType;
    private String consignee;
    private String invoice;
    private String invoiceContent;
    private Long invoicePay;
    private Long qty;         //
    private String channelCategory;
    private Long expId;
    private String receiveAddr;
    private String zipCode;
    private String remark;
    private String phone;
    private String businessType;
    private Date createdAt;
    private List<TheOrderItemVo> itemVos;
    private Long totalPay;
    private Long realPay;
    private Long hadPay;
    private Long pointPay;
    private Long deliverPay;
    private Long wrapPay;
    private Long arrivePay;
    private Long deliverPointPay;
    private Long onlinePay;
    private int edit;     //是否是修改订单数量 0不是，1是
    private Integer showPriceInReceipt;
    private Date sendTime;
    /**
     * 1 是POS刷卡
     */
    private String isPos;
    private Integer isBestlogtistic;
    private String wrapMemo;
    private String carryId;
    private String rprovince;
    private String rarea;
    private String rcity;
    private List<TheOrderItemPkgVo> pkgVos;
    private String expWorkCode;//物流编号
    
    
    public String getRcity() {
		return rcity;
	}

	public void setRcity(String rcity) {
		this.rcity = rcity;
	}

	public String getExpWorkCode() {
		return expWorkCode;
	}

	public void setExpWorkCode(String expWorkCode) {
		this.expWorkCode = expWorkCode;
	}

	public String getRprovince() {
		return rprovince;
	}

	public void setRprovince(String rprovince) {
		this.rprovince = rprovince;
	}

	public String getRarea() {
		return rarea;
	}

	public void setRarea(String rarea) {
		this.rarea = rarea;
	}

	public String getCarryId() {
		return carryId;
	}

	public void setCarryId(String carryId) {
		this.carryId = carryId;
	}

	public String getWrapMemo() {
		return wrapMemo;
	}

	public void setWrapMemo(String wrapMemo) {
		this.wrapMemo = wrapMemo;
	}

	public String getIsPos() {
		return isPos;
	}

	public void setIsPos(String isPos) {
		this.isPos = isPos;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Integer getShowPriceInReceipt() {
		return showPriceInReceipt;
	}

	public void setShowPriceInReceipt(Integer showPriceInReceipt) {
		this.showPriceInReceipt = showPriceInReceipt;
	}

	public Long getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(Long totalPay) {
        this.totalPay = totalPay;
    }

    public Long getDeliverPay() {
        return deliverPay;
    }

    public void setDeliverPay(Long deliverPay) {
        this.deliverPay = deliverPay;
    }

    public int getEdit() {
        return edit;
    }

    public void setEdit(int edit) {
        this.edit = edit;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    public String getInvoiceContent() {
        return invoiceContent;
    }

    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public Long getPayType() {
        return payType;
    }

    public void setPayType(Long payType) {
        this.payType = payType;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public String getChannelCategory() {
        return channelCategory;
    }

    public void setChannelCategory(String channelCategory) {
        this.channelCategory = channelCategory;
    }

    public Long getExpId() {
        return expId;
    }

    public void setExpId(Long expId) {
        this.expId = expId;
    }

    public String getReceiveAddr() {
        return receiveAddr;
    }

    public void setReceiveAddr(String receiveAddr) {
        this.receiveAddr = receiveAddr;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public List<TheOrderItemVo> getItemVos() {
        return itemVos;
    }

    public void setItemVos(List<TheOrderItemVo> itemVos) {
        this.itemVos = itemVos;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public Long getRealPay() {
        return realPay;
    }

    public void setRealPay(Long realPay) {
        this.realPay = realPay;
    }

    public Long getPointPay() {
        return pointPay;
    }

    public void setPointPay(Long pointPay) {
        this.pointPay = pointPay;
    }

    public Long getHadPay() {
        return hadPay;
    }

    public void setHadPay(Long hadPay) {
        this.hadPay = hadPay;
    }

    public Long getWrapPay() {
        return wrapPay;
    }

    public void setWrapPay(Long wrapPay) {
        this.wrapPay = wrapPay;
    }

    public Long getArrivePay() {
        return arrivePay;
    }

    public void setArrivePay(Long arrivePay) {
        this.arrivePay = arrivePay;
    }

    public Long getDeliverPointPay() {
        return deliverPointPay;
    }

    public void setDeliverPointPay(Long deliverPointPay) {
        this.deliverPointPay = deliverPointPay;
    }

    public Long getOnlinePay() {
        return onlinePay;
    }

    public void setOnlinePay(Long onlinePay) {
        this.onlinePay = onlinePay;
    }

	public Long getInvoicePay() {
		return invoicePay;
	}

	public void setInvoicePay(Long invoicePay) {
		this.invoicePay = invoicePay;
	}

	public Integer getIsBestlogtistic() {
		return isBestlogtistic;
	}

	public void setIsBestlogtistic(Integer isBestlogtistic) {
		this.isBestlogtistic = isBestlogtistic;
	}

	public List<TheOrderItemPkgVo> getPkgVos() {
		return pkgVos;
	}

	public void setPkgVos(List<TheOrderItemPkgVo> pkgVos) {
		this.pkgVos = pkgVos;
	}
    
}
