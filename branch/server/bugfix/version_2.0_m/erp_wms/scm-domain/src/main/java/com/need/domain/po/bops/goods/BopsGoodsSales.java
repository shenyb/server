package com.need.domain.po.bops.goods;

public class BopsGoodsSales {
    private String goodsId;

    private Integer salesServen;

    private Integer salesFifteen;

    private Integer salesSixty;

    private Integer salesNinety;

    private Integer salesThirty;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getSalesServen() {
        return salesServen;
    }

    public void setSalesServen(Integer salesServen) {
    	if(salesServen==null){
    		 this.salesServen=0;
    	}
        this.salesServen = salesServen;
    }

    public Integer getSalesFifteen() {
        return salesFifteen;
    }

    public void setSalesFifteen(Integer salesFifteen) {
    	if(salesFifteen==null){
   		 this.salesFifteen=0;
    	}
        this.salesFifteen = salesFifteen;
    }

    public Integer getSalesSixty() {
        return salesSixty;
    }

    public void setSalesSixty(Integer salesSixty) {
    	if(salesSixty==null){
      		 this.salesSixty=0;
       	}
        this.salesSixty = salesSixty;
    }

    public Integer getSalesNinety() {
        return salesNinety;
    }

    public void setSalesNinety(Integer salesNinety) {
    	if(salesNinety==null){
     		 this.salesNinety=0;
      	}
        this.salesNinety = salesNinety;
    }

    public Integer getSalesThirty() {
        return salesThirty;
    }

    public void setSalesThirty(Integer salesThirty) {
    	if(salesThirty==null){
    		 this.salesThirty=0;
     	}
        this.salesThirty = salesThirty;
    }
}