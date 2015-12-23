package com.need.dao.bops.xinhuan;

import java.util.List;

import com.need.domain.po.bops.xinhuan.BopsOpsMain;
import com.need.domain.pub.Page;
import com.need.domain.vo.xinhuan.OpsXinhuanParamVO;

public interface BopsOpsMainDAO {
    int deleteByPrimaryKey(String opsId);

    int insert(BopsOpsMain record);

    int insertSelective(BopsOpsMain record);
    
    int countOpsPosition(String opsType);

    /**
     * @author xiehao 2015年9月11日 下午4:38:38
     * @Method: selectByPrimaryKey 
     * @Description: TODO 通过ID查找运营位
     * @param opsId
     * @return 
     * @throws
     */
    BopsOpsMain selectByPrimaryKey(String opsId);

    int updateByPrimaryKeySelective(BopsOpsMain record);

    int updateByPrimaryKey(BopsOpsMain record);
    
    /**
     * @author xiehao 2015年9月11日 下午4:28:31
     * @Method: insertOpsPosition 
     * @Description: TODO 添加运营位
     * @param xinhuan
     * @return 
     * @throws
     */
    int insertOpsPosition(OpsXinhuanParamVO xinhuan);
    
    /**
	 * @author xiehao 2015年9月11日 下午4:29:41
	 * @Method: queryOpsPosition 
	 * @Description: TODO 查询运营位
	 * @return 
	 * @throws
	 */
    List<BopsOpsMain> queryAllOpsPosition(OpsXinhuanParamVO categoryPage);
    
    
    int getCount(OpsXinhuanParamVO categoryPage);
    /**
	 * @author xiehao 2015年9月10日 上午11:21:11
	 * @Method: updateOpsPosition 
	 * @Description: TODO 更新运营位信息
	 * @param xinhuan
	 * @return 
	 * @throws
	 */
    int updateOpsPosition(OpsXinhuanParamVO xinhuan);
    /***
     * 
     * @author LXD 2015年11月5日 下午3:37:25
     * @Method: queryAllOps  
     * @Description: TODO 查询所有的运营位信息
     * @return 
     * @throws
     */
	List<BopsOpsMain> queryAllOps();
}