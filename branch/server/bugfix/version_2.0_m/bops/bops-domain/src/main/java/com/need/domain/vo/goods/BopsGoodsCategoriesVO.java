package com.need.domain.vo.goods;

import java.util.Date;
import java.util.List;

import com.need.domain.po.bops.goods.BopsGoodsCategoriesPO;
import com.need.domain.pub.Page;

public class BopsGoodsCategoriesVO extends Page{
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 4427214152547275190L;

	private Integer categoryId;

    private String categoryName;

    private String categoryLevel;

    private Integer parentId;

    private Date createTime;

    private Date updateTime;

    private String auditStatus;

    private Integer createId;

    private Integer updateId;
    
    private String levelOne;
    
    private String levelTwo;
    
    private String levelThree;
    
    private String levelOneName;
    
    private String levelTwoName;
    
    private String levelThreeName;
    
    private String createUserName;
    
    private String updateUserName;
    
    private String categoryStatus;
     
    private List<BopsGoodsCategoriesVO> levelTwoList;
    
    private List<BopsGoodsCategoriesVO> levelThreeList;
    
	public List<BopsGoodsCategoriesVO> getLevelTwoList() {
		return levelTwoList;
	}

	public void setLevelTwoList(List<BopsGoodsCategoriesVO> levelTwoList) {
		this.levelTwoList = levelTwoList;
	}

	public List<BopsGoodsCategoriesVO> getLevelThreeList() {
		return levelThreeList;
	}

	public void setLevelThreeList(List<BopsGoodsCategoriesVO> levelThreeList) {
		this.levelThreeList = levelThreeList;
	}

	public String getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(String categoryStatus) {
		this.categoryStatus = categoryStatus;
	}

	public String getLevelThree() {
		return levelThree;
	}

	public void setLevelThree(String levelThree) {
		this.levelThree = levelThree;
	}

	public String getLevelThreeName() {
		return levelThreeName;
	}

	public void setLevelThreeName(String levelThreeName) {
		this.levelThreeName = levelThreeName;
	}

	public String getLevelOneName() {
		return levelOneName;
	}

	public void setLevelOneName(String levelOneName) {
		this.levelOneName = levelOneName;
	}

	public String getLevelTwoName() {
		return levelTwoName;
	}

	public void setLevelTwoName(String levelTwoName) {
		this.levelTwoName = levelTwoName;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(String categoryLevel) {
		this.categoryLevel = categoryLevel;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

	public Integer getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}

	public String getLevelOne() {
		return levelOne;
	}

	public void setLevelOne(String levelOne) {
		this.levelOne = levelOne;
	}

	public String getLevelTwo() {
		return levelTwo;
	}

	public void setLevelTwo(String levelTwo) {
		this.levelTwo = levelTwo;
	}

	@Override
	public String toString() {
		return "BopsGoodsCategoriesVO [categoryId=" + categoryId + ", categoryName=" + categoryName + ", categoryLevel="
				+ categoryLevel + ", parentId=" + parentId + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", auditStatus=" + auditStatus + ", createId=" + createId + ", updateId=" + updateId + ", levelOne="
				+ levelOne + ", levelTwo=" + levelTwo + "]";
	}
    
    
}
