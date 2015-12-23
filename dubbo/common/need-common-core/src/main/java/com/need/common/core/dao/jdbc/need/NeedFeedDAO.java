package com.need.common.core.dao.jdbc.need;

import com.need.common.domain.po.api.need.NeedFeedPO;
import com.need.common.domain.vo.need.FeedsParamsVO;

import java.util.List;

public interface NeedFeedDAO {
     /**
      * 
      * @author LXD 2015年8月21日 下午2:46:03
      * @Method: deleteById 
      * @Description: TODO 根据ID删除
      * @param feedId
      * @return 
      * @throws
      */
	int deleteById(String feedId);
     /**
      * 
      * @author LXD 2015年8月21日 下午2:46:18
      * @Method: insert 
      * @Description: TODO 插入
      * @param record
      * @return 
      * @throws
      */
    int insert(NeedFeedPO record);

     /**
      * 
      * @author LXD 2015年8月21日 下午2:46:30
      * @Method: getById 
      * @Description: TODO 根据ID获取NeedFeedPO
      * @param feedId
      * @return 
      * @throws
      */

    NeedFeedPO getById(String feedId);

     /**
      * 
      * @author LXD 2015年8月21日 下午2:47:02
      * @Method: update 
      * @Description: TODO 更新
      * @param record
      * @return 
      * @throws
      */
    int update(NeedFeedPO record);
        /**
         * 
         * @author LXD 2015年8月21日 下午2:47:14
         * @Method: queryBypageParams 
         * @Description: TODO 分页
         * @param params
         * @return 
         * @throws
         */
	List<NeedFeedPO> queryBypageParams(FeedsParamsVO params);
     /**
      * 
      * @author LXD 2015年8月21日 下午2:47:25
      * @Method: queryByPage 
      * @Description: TODO 分页
      * @param params
      * @return 
      * @throws
      */
	List<NeedFeedPO> queryByPage(FeedsParamsVO params);
      /**
       * 
       * @author LXD 2015年8月21日 下午2:47:48
       * @Method: getCount 
       * @Description: TODO 数量(总)
       * @return 
       * @throws
       */
	int getCount();
       /**
        * 
        * @author LXD 2015年8月21日 下午2:48:04
        * @Method: getCountParam 
        * @Description: TODO 该用关注的行家的发表的feed数
        * @param userId
        * @return 
        * @throws
        */
	int getCountParam(String userId);
	int getCountParam_V1_1(String userId);
	List<NeedFeedPO> queryBypageParams_V1_1(FeedsParamsVO params);
	int getCount_V1_1();
	List<NeedFeedPO> queryByPage_V1_1(FeedsParamsVO param);

	

	
}