package com.need.service.bops.goodsGroup;

import java.util.List;

import com.need.domain.po.bops.goodsgroup.BopsGoodsGroupKey;
import com.need.domain.pub.Message;
import com.need.domain.vo.goodsgroup.GoodsGroupParaVO;
import com.need.domain.vo.goodsgroup.GoodsGroupResultVO;
import com.need.domain.vo.goodsgroup.GoodsGroupVO;

public interface GoodsGroupService {

	List<GoodsGroupResultVO> getAll(GoodsGroupVO goodsGroupVO);

	Message addGroup(GoodsGroupVO goodsGroupVO);

	void audit(String groupBatch, Integer userId);

	Message addGroupGoods(String groupBatch, String goodsCodes, Integer userId);

	Message getGoodsGroupByBatch(String groupBatch);

	Message checkGoods(String goodsCodes);

	List<GoodsGroupParaVO> getBybatch(GoodsGroupParaVO goodsGroupVO);

	GoodsGroupVO getToEdit(String groupBatch);

	void editGroup(GoodsGroupVO goodsGroupVO);

	Message deleteGoods(BopsGoodsGroupKey key);


}
