package com.need.dao.api.ops;

import com.need.domain.po.api.ops.OpsHotGooodsPO;



public interface OpsHotGooodsDAO {
    int deleteById(String popularityId);

    int insert(OpsHotGooodsPO record);

    OpsHotGooodsPO getById(String popularityId);

    int update(OpsHotGooodsPO record);

	void updateSort(Integer goodsSort);

	void updategoodsSort(Integer currentSort, Integer newSort);

	void updategoodsSortRise(Integer currentSort, Integer newSort);



}