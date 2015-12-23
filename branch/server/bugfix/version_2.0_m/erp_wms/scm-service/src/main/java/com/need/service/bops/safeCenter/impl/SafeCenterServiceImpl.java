package com.need.service.bops.safeCenter.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.need.dao.api.need.NeedFeedDAO;
import com.need.domain.pub.Message;
import com.need.domain.vo.safeCenter.NeedFeedParamsVO;
import com.need.domain.vo.safeCenter.NeedFeedVO;
import com.need.service.bops.safeCenter.SafeCenterService;
@Service
public class SafeCenterServiceImpl implements SafeCenterService {
   
	@Autowired
	private NeedFeedDAO needFeedDAO;
	
	
	@Override
	public Message queryFeedsPage(NeedFeedParamsVO needFeedParamsVO) {
		Message success =Message.success();
		int count= needFeedDAO.getCount(needFeedParamsVO);
		needFeedParamsVO.setTotal(count);
		List<NeedFeedVO> list= needFeedDAO.queryByPage(needFeedParamsVO);
		success.addData("list", list);
		success.addData("page", needFeedParamsVO);
		return success;
	}

    @Transactional
	@Override
	public Message deleteFeed(String feedId) {
    	needFeedDAO.updateStatus(feedId);
		return Message.success();
	}
	
}
