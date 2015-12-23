/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.api.web.controller.feed;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.service.feed.FeedService;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.feed.FeedLabelVO;
import com.need.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *
 * @author 庆凯
 */
@Controller
@RequestMapping(ControllerMappings.FEED_ADD)
public class AddFeedController {
    
	private static final Logger LOGGER = LoggerFactory.getLogger(AddFeedController.class);
    
    @Autowired
    private FeedService feedService;
    
	@ResponseBody
	@RequestMapping(params = "apiVersion=2.0", method = RequestMethod.POST)
	public Message addFeed(@RequestParam(required = true) String userId, @RequestParam(required = true) String feedContent, 
            @RequestParam(required = true) String feedPicKey, String feedLabels){
		LOGGER.info("addFeed userId : {} feedContent : {} feedPicKey : {} feedLabels : {}", userId, feedContent, feedPicKey, feedLabels);
        List<FeedLabelVO> feedLabelList = null;
        try {
            feedLabelList = JSON.parseObject(feedLabels, new TypeReference<List<FeedLabelVO>>() {});
            for(FeedLabelVO feedLabelVO : feedLabelList) {
                if(StringUtil.isBlank(feedLabelVO.getFeedLabelContent())) {
                    return Message.error(BizReturnCode.FEED_LABEL_NO_CONTENT);
                }
            }
        } catch(Exception e) {
            
        }
        Message message = feedService.addFeed(userId, feedContent, feedPicKey, feedLabelList);
        return message;
    }
}
