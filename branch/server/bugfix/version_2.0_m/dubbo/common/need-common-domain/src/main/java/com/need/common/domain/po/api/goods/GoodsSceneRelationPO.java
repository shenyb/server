package  com.need.common.domain.po.api.goods;

import java.util.Date;

public class GoodsSceneRelationPO {
    private Integer id;

    private Integer scene;
    
    private String goodsId;

    private String scenePicKey;

    private String memo;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScene() {
		return scene;
	}

	public void setScene(Integer scene) {
		this.scene = scene;
	}

	public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getScenePicKey() {
        return scenePicKey;
    }

    public void setScenePicKey(String scenePicKey) {
        this.scenePicKey = scenePicKey;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}