package com.need.dao.api.ops;

import com.need.domain.po.api.ops.OpsSelectedPO;

public interface OpsSelectedDAO {
    int deleteById(String selectionId);

    int insert(OpsSelectedPO record);

    OpsSelectedPO getById(String selectionId);


    int update(OpsSelectedPO record);

	void updateSort(Integer goodsSort);

	void updategoodsSort(Integer currentSort, Integer newSort);

	void updategoodsSortRise(Integer goodsSort, Integer goodsSort2);
}