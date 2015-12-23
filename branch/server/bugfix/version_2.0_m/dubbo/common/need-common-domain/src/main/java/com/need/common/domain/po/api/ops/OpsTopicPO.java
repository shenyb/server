package com.need.common.domain.po.api.ops;

import java.util.Date;

public class OpsTopicPO {
    private Integer topicId;

    private String topicName;

    private String coverKey;

    private String topicStatus;

    private Date createTime;

    private Integer publisherId;

    private Integer auditorId;

    private String content;

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getCoverKey() {
        return coverKey;
    }

    public void setCoverKey(String coverKey) {
        this.coverKey = coverKey;
    }

    public String getTopicStatus() {
        return topicStatus;
    }

    public void setTopicStatus(String topicStatus) {
        this.topicStatus = topicStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public Integer getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Integer auditorId) {
        this.auditorId = auditorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}