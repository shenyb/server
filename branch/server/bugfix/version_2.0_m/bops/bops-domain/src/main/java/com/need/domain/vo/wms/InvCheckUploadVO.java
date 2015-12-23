package com.need.domain.vo.wms;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author liuhongyang 2015年11月30日 下午4:03:46
 * @ClassName InvCheckUploadVO
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: liuhongyang 2015年11月30日
 * @modify by reason:{方法名}:{原因}
 */
public class InvCheckUploadVO implements Serializable {

    /** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -6554401857421844642L;
	private Long id;
    private String uploadNo;//盘点上传单号
    private Integer uploadState;//上传状态
    private String comment;//上传说明
    private Long createBy;//创建人编号
    private Date createAt;//创建日期
    private List<InvCheckInventoryRecordVO> invCheckInventoryRecordVO;

    public List<InvCheckInventoryRecordVO> getInvCheckInventoryRecordVO() {
		return invCheckInventoryRecordVO;
	}

	public void setInvCheckInventoryRecordVO(List<InvCheckInventoryRecordVO> invCheckInventoryRecordVO) {
		this.invCheckInventoryRecordVO = invCheckInventoryRecordVO;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUploadNo() {
        return uploadNo;
    }

    public void setUploadNo(String uploadNo) {
        this.uploadNo = uploadNo;
    }

    public Integer getUploadState() {
        return uploadState;
    }

    public void setUploadState(Integer uploadState) {
        this.uploadState = uploadState;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

}
