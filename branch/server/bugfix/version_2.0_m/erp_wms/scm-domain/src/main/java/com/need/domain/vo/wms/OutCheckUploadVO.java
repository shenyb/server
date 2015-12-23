package com.need.domain.vo.wms;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liaozhisong
 * Date: 13-3-27
 * Time: 下午2:26
 * To change this template use File | Settings | File Templates.
 */
public class OutCheckUploadVO implements Serializable {

    private Long id;
    private String uploadNo;
    private Integer uploadState;
    private String comment;
    private Long createBy;
    private Date createAt;
    private List<OutCheckInventoryRecordVO> invCheckInventoryRecordVO;


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

	public List<OutCheckInventoryRecordVO> getInvCheckInventoryRecordVO() {
		return invCheckInventoryRecordVO;
	}

	public void setInvCheckInventoryRecordVO(List<OutCheckInventoryRecordVO> invCheckInventoryRecordVO) {
		this.invCheckInventoryRecordVO = invCheckInventoryRecordVO;
	}
    
}
