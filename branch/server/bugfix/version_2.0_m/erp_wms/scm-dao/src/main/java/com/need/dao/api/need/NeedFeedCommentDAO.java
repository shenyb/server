package com.need.dao.api.need;

import java.util.List;

import com.need.domain.vo.safeCenter.NeedFeedCommentVO;
import com.need.domain.vo.safeCenter.NeedFeedParamsVO;

public interface NeedFeedCommentDAO {

	int getcount();

	List<NeedFeedCommentVO> queryByPage(NeedFeedParamsVO needFeedParamsVO);

	void updateStatus(String commentId);

    
}