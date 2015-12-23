package com.need.dao.bops.ops;

import java.util.List;

import com.need.domain.po.bops.ops.BopsOpsTopic;
import com.need.domain.vo.ops.PageTopicVO;

/**
 * 
 * <p></p>
 * @author peiboli 2015年8月6日 上午10:38:30
 * @ClassName BopsOpsTopicDAO
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年8月6日
 * @modify by reason:{方法名}:{原因}
 */
public interface BopsOpsTopicDAO {
	
    int deleteByTopicId(String topicId);

    int insertTopic(BopsOpsTopic record);

    int updateByTopicIdSelective(BopsOpsTopic record);
  
    /**
     * 
     * @author peiboli 2015年8月7日 下午4:23:36
     * @Method: selectOpsTopicList 
     * @Description: TODO获得专题列表
     * @param opsTopicPage
     * @return 
     * @throws
     */
	List<BopsOpsTopic> queryByPage( PageTopicVO opsTopicPage);
	
	/**
	 * 
	 * @author peiboli 2015年8月7日 上午10:00:00
	 * @Method: selectCount 
	 * @Description: TODO获得总的记录条数
	 * @return 
	 * @throws
	 */
	int selectCount(PageTopicVO opsTopicPage); 

	BopsOpsTopic selectByTopicId(int topicId);

	void updateByTopicId(BopsOpsTopic bopsOpsTopic);

}