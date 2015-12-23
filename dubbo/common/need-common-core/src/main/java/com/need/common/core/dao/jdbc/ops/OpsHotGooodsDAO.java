package com.need.common.core.dao.jdbc.ops;

import com.need.common.domain.po.api.ops.OpsHotGooodsPO;

import java.util.List;


public interface OpsHotGooodsDAO {
	/**
	 * 
	 * @author LXD 2015年8月21日 下午3:09:37
	 * @Method: deleteById 
	 * @Description: TODO 根据ID删除
	 * @param popularityId
	 * @return 
	 * @throws
	 */
    int deleteById(String popularityId);
    /**
     * 
     * @author LXD 2015年8月21日 下午3:09:51
     * @Method: insert 
     * @Description: TODO 插入
     * @param record
     * @return 
     * @throws
     */
    int insert(OpsHotGooodsPO record);
    /**
     * 
     * @author LXD 2015年8月21日 下午3:10:03
     * @Method: getById 
     * @Description: TODO 根据ID获取对象
     * @param popularityId
     * @return 
     * @throws
     */

    OpsHotGooodsPO getById(String popularityId);
     /***
      * 
      * @author LXD 2015年8月21日 下午3:10:25
      * @Method: update 
      * @Description: TODO 更新操作
      * @param record
      * @return 
      * @throws
      */

    int update(OpsHotGooodsPO record);
     /**
      * 
      * @author LXD 2015年8月21日 下午3:11:52
      * @Method: queyByPage 
      * @Description: TODO 分页查询
      * @return 
      * @throws
      */
	List<OpsHotGooodsPO> queyByPage();
}