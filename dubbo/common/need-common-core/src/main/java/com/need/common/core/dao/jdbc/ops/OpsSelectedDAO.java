package com.need.common.core.dao.jdbc.ops;

import com.need.common.domain.po.api.ops.OpsSelectedPO;

import java.util.List;

public interface OpsSelectedDAO {
	 /**
	  * 
	  * @author LXD 2015年8月21日 下午3:13:53
	  * @Method: deleteById 
	  * @Description: TODO 根据ID删除
	  * @param selectionId
	  * @return 
	  * @throws
	  */
    int deleteById(String selectionId);
    /**
     * 
     * @author LXD 2015年8月21日 下午3:14:20
     * @Method: insert 
     * @Description: TODO 插入
     * @param record
     * @return 
     * @throws
     */
    int insert(OpsSelectedPO record);
    /**
     * 
     * @author LXD 2015年8月21日 下午3:14:31
     * @Method: getById 
     * @Description: TODO 根据ID获取
     * @param selectionId
     * @return 
     * @throws
     */

    OpsSelectedPO getById(String selectionId);
    /**
     * 
     * @author LXD 2015年8月21日 下午3:14:46
     * @Method: update 
     * @Description: TODO 更新
     * @param record
     * @return 
     * @throws
     */

    int update(OpsSelectedPO record);
     /**
      * 
      * @author LXD 2015年8月21日 下午3:14:59
      * @Method: queyByPage 
      * @Description: TODO 分页
      * @return 
      * @throws
      */
	List<OpsSelectedPO> queyByPage();

	
}