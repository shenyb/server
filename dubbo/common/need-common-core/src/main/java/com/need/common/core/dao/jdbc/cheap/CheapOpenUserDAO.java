package com.need.common.core.dao.jdbc.cheap;

import com.need.common.domain.po.api.cheap.CheapOpenUserPO;
import com.need.common.domain.po.api.cheap.CheapOpenUserPOKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheapOpenUserDAO {
    int deleteByPrimaryKey(CheapOpenUserPOKey key);

    int insert(CheapOpenUserPO record);

    int insertSelective(CheapOpenUserPO record);

    CheapOpenUserPO selectByPrimaryKey(CheapOpenUserPOKey key);

    int updateByPrimaryKeySelective(CheapOpenUserPO record);

    int updateByPrimaryKey(CheapOpenUserPO record);
    
    List<CheapOpenUserPO> queryByParams(@Param("mobile") String mobile, @Param("cheapNo") String cheapNo,
			@Param("traded") String traded);
}