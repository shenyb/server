package com.need.domain.po.bops.store;

import java.io.Serializable;

public class BopsWarehouseBasePO implements Serializable {
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -5764168740247502857L;

	private Integer warehouseId;

    private String warehouseName;

    private String warehouseType;

    private String opsType;

    private String warehouseProvince;

    private String warehouseCity;

    private String warehouseArea;

    private String linkman;

    private String telephone;

    private String address;

    private String remark;

    private String dredge;

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseType() {
        return warehouseType;
    }

    public void setWarehouseType(String warehouseType) {
        this.warehouseType = warehouseType;
    }

    public String getOpsType() {
        return opsType;
    }

    public void setOpsType(String opsType) {
        this.opsType = opsType;
    }

    public String getWarehouseProvince() {
        return warehouseProvince;
    }

    public void setWarehouseProvince(String warehouseProvince) {
        this.warehouseProvince = warehouseProvince;
    }

    public String getWarehouseCity() {
        return warehouseCity;
    }

    public void setWarehouseCity(String warehouseCity) {
        this.warehouseCity = warehouseCity;
    }

    public String getWarehouseArea() {
        return warehouseArea;
    }

    public void setWarehouseArea(String warehouseArea) {
        this.warehouseArea = warehouseArea;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDredge() {
        return dredge;
    }

    public void setDredge(String dredge) {
        this.dredge = dredge;
    }

	@Override
	public String toString() {
		return "BopsWarehouseBasePO [warehouseId=" + warehouseId + ", warehouseName=" + warehouseName
				+ ", warehouseType=" + warehouseType + ", opsType=" + opsType + ", warehouseProvince="
				+ warehouseProvince + ", warehouseCity=" + warehouseCity + ", warehouseArea=" + warehouseArea
				+ ", linkman=" + linkman + ", telephone=" + telephone + ", address=" + address + ", remark=" + remark
				+ ", dredge=" + dredge + "]";
	}
    
}