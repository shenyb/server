package com.need.dao.api.xinhuan;

import com.need.domain.po.api.xinhuan.OpsMain;
import com.need.domain.vo.xinhuan.OpsXinhuanParamVO;

public interface OpsMainDAO {
    int deleteByPrimaryKey(String opsId);

    int insert(OpsMain record);

    int insertSelective(OpsMain record);

    OpsMain selectByPrimaryKey(String opsId);

    int updateByPrimaryKeySelective(OpsMain record);

    int updateByPrimaryKey(OpsMain record);
    
    /**
     * @author xiehao 2015年9月11日 下午4:28:53
     * @Method: insertNewOpsPosition 
     * @Description: TODO 添加运营位
     * @param xinhuan
     * @return 
     * @throws
     */
    int insertNewOpsPosition(OpsXinhuanParamVO xinhuan);
    
    /**
	 * @author xiehao 2015年9月10日 上午11:21:11
	 * @Method: updateOpsPosition 
	 * @Description: TODO 更新运营位信息
	 * @param xinhuan
	 * @return 
	 * @throws
	 */
    int updateOpsPosition(OpsXinhuanParamVO xinhuan);
}