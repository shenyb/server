package com.need.dao.bops.ops;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.need.domain.po.bops.ops.BopsOps;
import com.need.domain.vo.ops.PageBopsOpsVO;
/***
 * 
 * <p></p>
 * @author LXD 2015年8月5日 下午12:01:28
 * @ClassName BopsOpsDAO
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年8月5日
 * @modify by reason:{方法名}:{原因}
 */
public interface BopsOpsDAO {
	
    int deleteById(String opsId);
	
	
    int insert(BopsOps ops);
	
	
    BopsOps getById(String opsId);
   
    
    int update(BopsOps ops);
    
    
    
	int getCountById(PageBopsOpsVO page);
    
    
   
	List<BopsOps> queryByPage(PageBopsOpsVO page);


	BopsOps queryByTypeId(String typeId);


	BopsOps getByOpsNumber(String opsNumber);

	BopsOps queryByParams(@Param("typeId")String typeId, @Param("opsNumber")String opsNumber);


	void deleteByTypeId(String typeId);
    
    
}