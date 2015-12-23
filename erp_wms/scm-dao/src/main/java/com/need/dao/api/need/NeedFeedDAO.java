package com.need.dao.api.need;

import java.util.List;

import com.need.domain.vo.safeCenter.NeedFeedParamsVO;
import com.need.domain.vo.safeCenter.NeedFeedVO;

public interface NeedFeedDAO {

   List<NeedFeedVO> queryByPage(NeedFeedParamsVO paramsVO);

   void updateStatus(String feedId);

   int getCount(NeedFeedParamsVO needFeedParamsVO);

	

	
}