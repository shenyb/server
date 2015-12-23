package com.need.dao.api.ops;

import java.util.List;

import com.need.domain.po.api.ops.OpsPositionPO;



public interface OpsPositionDAO {
    int deleteById(String opsId);

    int insert(OpsPositionPO record);


    OpsPositionPO getById(String opsId);

 
    int update(OpsPositionPO record);

	List<OpsPositionPO> queryAll();

	void deleteByTypeId(String topicId);

}