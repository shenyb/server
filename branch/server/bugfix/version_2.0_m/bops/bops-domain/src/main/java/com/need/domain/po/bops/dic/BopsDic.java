package com.need.domain.po.bops.dic;

import java.io.Serializable;
import java.util.Date;

public class BopsDic implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 986685620661256043L;

	private Integer codeId;

    private String codeValue;

    private String codeName;

    private String codeNameDesc;

    private String parentCodeValue;

    private String codeType;

    private String state;

    private Date stateDatetime;

    private Integer codeSort;

    public Integer getCodeId() {
        return codeId;
    }

    public void setCodeId(Integer codeId) {
        this.codeId = codeId;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getCodeNameDesc() {
        return codeNameDesc;
    }

    public void setCodeNameDesc(String codeNameDesc) {
        this.codeNameDesc = codeNameDesc;
    }

    public String getParentCodeValue() {
        return parentCodeValue;
    }

    public void setParentCodeValue(String parentCodeValue) {
        this.parentCodeValue = parentCodeValue;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getStateDatetime() {
        return stateDatetime;
    }

    public void setStateDatetime(Date stateDatetime) {
        this.stateDatetime = stateDatetime;
    }

    public Integer getCodeSort() {
        return codeSort;
    }

    public void setCodeSort(Integer codeSort) {
        this.codeSort = codeSort;
    }

	@Override
	public String toString() {
		return "BopsDic [codeId=" + codeId + ", codeValue=" + codeValue + ", codeName=" + codeName + ", codeNameDesc="
				+ codeNameDesc + ", parentCodeValue=" + parentCodeValue + ", codeType=" + codeType + ", state=" + state
				+ ", stateDatetime=" + stateDatetime + ", codeSort=" + codeSort + "]";
	}
}