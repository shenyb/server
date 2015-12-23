package com.need.common.core.dao.jdbc.ops;

import com.need.common.domain.po.api.ops.OpsTopicCategoryPO;

import java.util.List;

public interface OpsTopicCategoryDAO {

 List<OpsTopicCategoryPO> queryByPage();

}
