package com.need.common.core.dao.jdbc.need;

import com.need.common.domain.po.api.need.NeedFeedCommentPO;

import java.util.List;

public interface NeedFeedCommentDAO {

    /**
     * 
     * @author LXD 2015年8月21日 下午2:43:27
     * @Method: deleteById 
     * @Description: TODO 根据Id删除
     * @param commentId
     * @return 
     * @throws
     */
	int deleteById(String commentId);
    /**
     * 
     * @author LXD 2015年8月21日 下午2:43:42
     * @Method: insert 
     * @Description: TODO 插入
     * @param record
     * @return 
     * @throws
     */
    int insert(NeedFeedCommentPO record);

     /**
      * 
      * @author LXD 2015年8月21日 下午2:44:11
      * @Method: getById 
      * @Description: TODO 根据ID获取
      * @param commentId
      * @return 
      * @throws
      */
    NeedFeedCommentPO getById(String commentId);
    /**
     * 
     * @author LXD 2015年8月21日 下午2:44:30
     * @Method: update 
     * @Description: TODO 更新
     * @param record 
     * @return 
     * @throws
     */
    int update(NeedFeedCommentPO record);
     /**
      *     
      * @author LXD 2015年8月21日 下午2:44:47
      * @Method: getcommentsByfeedId 
      * @Description: TODO 根据ID获取评论列表
      * @param feedId
      * @return 
      * @throws
      */
	List<NeedFeedCommentPO> getcommentsByfeedId(String feedId);
     /**
      * 
      * @author LXD 2015年8月21日 下午2:45:23
      * @Method: getcountByfeedId 该feed的评论数
      * @Description: TODO
      * @param feedId
      * @return 
      * @throws
      */
	int getcountByfeedId(String feedId);
}