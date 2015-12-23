package com.need.service.bops.ops.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.need.dao.api.ops.GoodsSceneRelationDAO;
import com.need.dao.bops.goods.BopsGoodsSceneDAO;
import com.need.domain.common.enums.CheckStatusEnums;
import com.need.domain.po.bops.basedata.GoodsSceneResultPO;
import com.need.domain.po.bops.goods.BopsGoodsScene;
import com.need.domain.pub.Page;
import com.need.domain.vo.ops.GoodsSceneSearchParam;
import com.need.domain.vo.ops.GoodsSceneVo;
import com.need.service.bops.ops.GoodsSceneService;
@Service
public class GoodSceneServiceImpl implements GoodsSceneService{
    @Autowired
	BopsGoodsSceneDAO bopsGoodsSceneDAO;
    
    @Autowired
    GoodsSceneRelationDAO goodsSceneRelationDAO;

    /**
     * 
     * @author peiboli 2015年8月5日 下午5:16:56
     * @Method: getgoodsSceneVoList 
     * @Description: TODO获得商品场景管理表
     * @return 
     * @see com.need.service.bops.ops.GoodsSceneService#getgoodsSceneVoList()
     */
	@Override
	public List<GoodsSceneVo> getgoodsSceneVoList() {
		// TODO Auto-generated method stub
		List<GoodsSceneVo> goodsSceneVoList = bopsGoodsSceneDAO.getgoodsSceneVoList();
		return goodsSceneVoList;
	}

	@Override
	public BopsGoodsScene checkIsAdd(String goodsId,Integer senceId) {
		// TODO Auto-generated method stub
         return  bopsGoodsSceneDAO.checkIsAdd(goodsId,senceId);
	}

	@Override
	@Transactional
	public BopsGoodsScene addGoodsScene(BopsGoodsScene bopsGoodsScene) {
		// TODO Auto-generated method stub
		bopsGoodsScene.setAuditStatus(CheckStatusEnums.SUCCESS.code);
		bopsGoodsSceneDAO.insert(bopsGoodsScene);
		this.addApiGoodsScene(bopsGoodsScene);
		return bopsGoodsScene;
	}

	@Override
	@Transactional
	public void deleteGoodsScene(String Id) {
		// TODO Auto-generated method stub
		bopsGoodsSceneDAO.deleteByPrimaryKey(Id);
	}

	@Override
	@Transactional
	public void updateGoodsScene(BopsGoodsScene bopsGoodsScene) {
		// TODO Auto-generated method stub
		bopsGoodsSceneDAO.updateByPrimaryKeySelective(bopsGoodsScene);
	}

	@Override
	public List<GoodsSceneVo> searchGoodsScene(String search,Page goodsScenepage) {
		// TODO Auto-generated method stub
		goodsScenepage.setTotal(bopsGoodsSceneDAO.searchCount(search));
		List<GoodsSceneVo> goodsSceneVoList = bopsGoodsSceneDAO.searchGoodsScene(search,goodsScenepage);
		return goodsSceneVoList;
	}

	@Override
	public List<GoodsSceneVo> GoodsSceneList(Page goodsScenepage) {
		// TODO Auto-generated method stub
		goodsScenepage.setTotal(bopsGoodsSceneDAO.count());		
		return bopsGoodsSceneDAO.goodsSceneList(goodsScenepage);
	}


	@Override
	public void addApiGoodsScene(BopsGoodsScene bopsGoodsScene) {
		// TODO Auto-generated method stub
		goodsSceneRelationDAO.insertSelective(bopsGoodsScene);
	}


	@Override
	public List<GoodsSceneResultPO> searchGoodsNameOrGoodsCode(GoodsSceneSearchParam goodsSceneSearchParam) {
		// TODO Auto-generated method stub
		Integer count=  bopsGoodsSceneDAO.searchCount(goodsSceneSearchParam.getSearchKey());
		if(count ==0){
			return null;
		}
		goodsSceneSearchParam.setTotal(count);
		return bopsGoodsSceneDAO.searchGoodsNameOrGoodsCode(goodsSceneSearchParam);
	}


	@Override
	public BopsGoodsScene getGoodsScene(Integer id) {
		// TODO Auto-generated method stub
		return bopsGoodsSceneDAO.selectByPrimaryKey(id);
	}


	@Override
	public List<GoodsSceneResultPO> getGoodsSceneDetail(String goodsId,String sceneId) {
		// TODO Auto-generated method stub
		return bopsGoodsSceneDAO.getGoodsSceneDetail(goodsId,sceneId);
	}


	@Override
	public void updateApiGoodsScene(BopsGoodsScene bopsGoodsScene) {
		// TODO Auto-generated method stub
		goodsSceneRelationDAO.updateByPrimaryKeySelective(bopsGoodsScene);
	}

	@Override
	public void deleteApiGoodsScene(String id) {
		// TODO Auto-generated method stub
		goodsSceneRelationDAO.deleteByPrimaryKey(id);
	}

	@Override
	public List<GoodsSceneResultPO> getGoodsSceneByGoodsId(String goodsId) {
		
		return bopsGoodsSceneDAO.getGoodsSceneByGoodsId(goodsId);
	}

	@Override
	public void deleteGoodsSceneBygoodsId(String goodsId) {
		bopsGoodsSceneDAO.deleteByGoodsId(goodsId);
		goodsSceneRelationDAO.deleteBygoodsId(goodsId);
		
	}

	
	

}
