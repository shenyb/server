package com.need.common.domain.vo.feed;

import java.io.Serializable;

/**
 * 
 * @author YAN 2015-12-14 12:10:47
 * @ClassName SimpleFeedVO
 * @Description TODO
 * @version V2.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: YAN 2015-12-14
 * @modify by reason:{方法名}:{原因}
 */
public class SimpleFeedVO implements Serializable {

    private static final long serialVersionUID = -4271467282995256373L;
    
    private String feedId;
    private String feedPicKey;

    /**
     * @return the feedId
     */
    public String getFeedId() {
        return feedId;
    }

    /**
     * @param feedId the feedId to set
     */
    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    /**
     * @return the feedPicKey
     */
    public String getFeedPicKey() {
        return feedPicKey;
    }

    /**
     * @param feedPicKey the feedPicKey to set
     */
    public void setFeedPicKey(String feedPicKey) {
        this.feedPicKey = feedPicKey;
    }

    @Override
    public String toString() {
        return "SimpleFeedVO{" + "feedId=" + feedId + ", feedPicKey=" + feedPicKey + '}';
    }

}