package  com.need.common.domain.po.api.goods;

import java.util.Date;

public class GoodsIndexCategoryPO {
    private Integer categoryId;

    private String categoryName;

    private String categoryPicKey;

    private String categoryLevel;

    private Integer parentId;

    private Date createTime;

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

    public String getCategoryPicKey() {
        return categoryPicKey;
    }

    public void setCategoryPicKey(String categoryPicKey) {
        this.categoryPicKey = categoryPicKey;
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
}