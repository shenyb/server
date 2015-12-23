/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.common.domain.vo.feed;

import java.util.List;

/**
 *
 * @author 庆凯
 */
public class FeedDetailVO extends FeedVO {
    
    private static final long serialVersionUID = 4380286555029998732L;
    
    private List<FeedCommentVO> feedCommentList;

    /**
     * @return the feedCommentList
     */
    public List<FeedCommentVO> getFeedCommentList() {
        return feedCommentList;
    }

    /**
     * @param feedCommentList the feedCommentList to set
     */
    public void setFeedCommentList(List<FeedCommentVO> feedCommentList) {
        this.feedCommentList = feedCommentList;
    }
    
}
