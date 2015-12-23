package com.need.service.bops.dic;

import java.util.List;

import com.need.domain.vo.dic.BopsDicVO;

public interface BopsDicService {
	
	public List<BopsDicVO> getDicByDicType(String codeType);

}
