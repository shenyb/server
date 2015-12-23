package com.need.common.core.dao.jdbc.ops;

import com.need.common.domain.po.api.ops.OpsMainPO;
import com.need.common.domain.vo.ops.XinhuanOpsResultVO;

import java.util.List;

public interface OpsMainDAO {
	int deleteByPrimaryKey(String opsId);

	int insert(OpsMainPO record);

	int insertSelective(OpsMainPO record);

	OpsMainPO selectByPrimaryKey(String opsId);

	int updateByPrimaryKeySelective(OpsMainPO record);

	int updateByPrimaryKey(OpsMainPO record);

	/**
	 * @author xiehao 2015年9月11日 下午6:10:33
	 * @Method: getOpsPosition
	 * @Description: TODO 获取新欢的运营位
	 */
	List<XinhuanOpsResultVO> queryOpsPosition();
	
	/***
	 * 
	 * @author LXD 2015年10月22日 下午2:51:25
	 * @Method: queryXinhuanPosition 
	 * @Description: TODO 新欢scroll运营位
	 * @return 
	 * @throws
	 */
	List<XinhuanOpsResultVO> queryXinhuanPosition();
}