package com.need.common.core.dao.jdbc.ops;

import com.need.common.domain.po.api.ops.OpsPositionPO;

import java.util.List;

public interface OpsPositionDAO {
	/**
	 * 
	 * @author LXD 2015年8月21日 下午3:12:09
	 * @Method: deleteById 
	 * @Description: TODO 根据ID删除
	 * @param opsId
	 * @return 
	 * @throws
	 */
    int deleteById(String opsId);
    /***
     * 
     * @author LXD 2015年8月21日 下午3:12:24
     * @Method: insert 
     * @Description: TODO 插入
     * @param record
     * @return 
     * @throws
     */
    int insert(OpsPositionPO record);
     /**
      * 
      * @author LXD 2015年8月21日 下午3:12:35
      * @Method: getById 
      * @Description: TODO 根据ID获取对象
      * @param opsId
      * @return 
      * @throws
      */

    OpsPositionPO getById(String opsId);
     /**
      * 
      * @author LXD 2015年8月21日 下午3:12:51
      * @Method: update 
      * @Description: TODO 更新
      * @param record
      * @return 
      * @throws
      */

    int update(OpsPositionPO record);
     /**
      * 
      * @author LXD 2015年8月21日 下午3:13:05
      * @Method: querBypageHome 
      * @Description: TODO 首页运营位列表
      * @return 
      * @throws
      */
	List<OpsPositionPO> querBypageHome();
	 /**
	  * 
	  * @author LXD 2015年8月21日 下午3:13:23
	  * @Method: querBypageOther 
	  * @Description: TODO 商店运营位列表
	  * @return 
	  * @throws
	  */
	List<OpsPositionPO> querBypageOther();
	/**
	 * 
	 * @author LXD 2015年9月9日 下午6:13:25
	 * @Method: queryHomeOps 
	 * @Description: TODO need1.1首页运营位
	 * @return 
	 * @throws
	 */
	List<OpsPositionPO> queryHomeOps();
	/**
	 * 
	 * @author LXD 2015年9月9日 下午6:32:07
	 * @Method: queryShijianOps 
	 * @Description: TODO need1.1 世间运营位
	 * @return 
	 * @throws
	 */
	List<OpsPositionPO> queryShijianOps();
	/**
	 * 
	 * @author LXD 2015年9月9日 下午7:04:41
	 * @Method: queryShijianScrollOps 
	 * @Description: TODO need 1.1 scroll运营位
	 * @return 
	 * @throws
	 */
	List<OpsPositionPO> queryShijianScrollOps();
	/**
	 * 
	 * @author LXD 2015年10月22日 上午11:32:34
	 * @Method: queryKolOps 
	 * @Description: TODO 行家运营位
	 * @param categoryId
	 * @return 
	 * @throws
	 */
	List<OpsPositionPO> queryKolOps(int categoryId);
	/**
	 * 
	 * @author LXD 2015年10月22日 上午11:32:48
	 * @Method: queryStartBannerOps 
	 * @Description: TODO 启动页运营位
	 * @return 
	 * @throws
	 */
	List<OpsPositionPO> queryStartBannerOps();
	/***
	 * 
	 * @author LXD 2015年10月22日 下午12:16:37
	 * @Method: queryXinhuanBannerOps 
	 * @Description: TODO 获取新欢banner运营位
	 * @return 
	 * @throws
	 */
	List<OpsPositionPO> queryXinhuanBannerOps();
	/***
	 * 
	 * @author LXD 2015年10月22日 下午2:38:15
	 * @Method: queryXinhuanScrollOps 
	 * @Description: TODO 新欢scroll运营位
	 * @return 
	 * @throws
	 */
	List<OpsPositionPO> queryXinhuanScrollOps();
	List<OpsPositionPO> queryXinhuanScrollOps_2_0();

}