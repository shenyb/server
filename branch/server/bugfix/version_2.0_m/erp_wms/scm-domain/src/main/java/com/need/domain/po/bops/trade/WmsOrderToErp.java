package com.need.domain.po.bops.trade;

public class WmsOrderToErp {
    private Long id;

    private Long skuid;

    private String bathno;

    private String productdate;

    private String effectivedate;

    private Integer weight;

    private Integer wrapnum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSkuid() {
        return skuid;
    }

    public void setSkuid(Long skuid) {
        this.skuid = skuid;
    }

    public String getBathno() {
        return bathno;
    }

    public void setBathno(String bathno) {
        this.bathno = bathno;
    }

    public String getProductdate() {
        return productdate;
    }

    public void setProductdate(String productdate) {
        this.productdate = productdate;
    }

    public String getEffectivedate() {
        return effectivedate;
    }

    public void setEffectivedate(String effectivedate) {
        this.effectivedate = effectivedate;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getWrapnum() {
        return wrapnum;
    }

    public void setWrapnum(Integer wrapnum) {
        this.wrapnum = wrapnum;
    }
}