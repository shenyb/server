package com.need.service.bops.safeCenter.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.need.dao.api.need.NeedFeedCommentDAO;
import com.need.domain.pub.Message;
import com.need.domain.vo.safeCenter.NeedFeedCommentVO;
import com.need.domain.vo.safeCenter.NeedFeedParamsVO;
import com.need.service.bops.safeCenter.FeedCommentService;

@Service
public class FeedCommentServiceImpl implements FeedCommentService {
   
	@Autowired
	private NeedFeedCommentDAO needFeedCommentDAO;
	
	@Override
	public Message queryFeedCommentPage(NeedFeedParamsVO needFeedParamsVO) {
		Message success =Message.success();
		int count= needFeedCommentDAO.getcount();
		needFeedParamsVO.setTotal(count);
		List<NeedFeedCommentVO> list= needFeedCommentDAO.queryByPage(needFeedParamsVO);
		success.addData("list", list);
		success.addData("page", needFeedParamsVO);
		return success;
	}
    
	@Transactional
	@Override
	public Message deleteFeedComment(String commentId) {
		Message success =Message.success();
		needFeedCommentDAO.updateStatus(commentId);
		return success;
	}
	
}
