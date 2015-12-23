package com.need.common.core.service.goods;

import com.need.common.domain.pub.Message;

public interface GoodsCategoryService {

	Message getAllCategory();

	Message getGoodsListByCategory(Integer categoryId, Integer pageNum, Integer pageSize);

}
