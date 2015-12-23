package com.need.service.bops.xinhuan.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.need.dao.api.xinhuan.OpsMainDAO;
import com.need.dao.bops.xinhuan.BopsOpsMainDAO;
import com.need.domain.common.enums.OpsPositionEnums;
import com.need.domain.po.bops.xinhuan.BopsOpsMain;
import com.need.domain.pub.Message;
import com.need.domain.pub.Page;
import com.need.domain.vo.xinhuan.OpsPositionCountVO;
import com.need.domain.vo.xinhuan.OpsXinhuanParamVO;
import com.need.domain.vo.xinhuan.XinhuanGoodsId;
import com.need.service.bops.xinhuan.OpsXinhuanService;

@Service
public class OpsXinhuanServiceImpl implements OpsXinhuanService {
	
//	@Autowired
//	private BopsOpsXinhuanDAO bopsOpsXinhuanDAO;
//	
//	@Autowired
//	private OpsXinhuanDAO opsXinhuanDAO;
	
	@Autowired
	private OpsMainDAO opsMainDAO;
	
	@Autowired
	private BopsOpsMainDAO bopsOpsMainDAO;

	/**
	 * @author xiehao 2015年9月11日 下午4:27:03
	 * @Method: addNewOpsPosition 
	 * @Description: TODO 添加运营位
	 * @param xinhuan
	 * @return 
	 * @throws
	 */
	@Override
	@Transactional
	public Message addNewOpsPosition(OpsXinhuanParamVO xinhuan) {
		// TODO Auto-generated method stub
		OpsPositionCountVO opsPositionCountVO = countOpsPosition(xinhuan.getOpsType());
		if(!opsPositionCountVO.isRight()){
			return Message.error(Integer.parseInt(opsPositionCountVO.getMessage()));
		}
		
		xinhuan.setCreateTime(Calendar.getInstance().getTime());
		xinhuan.setOpsId(XinhuanGoodsId.generateXinhuanGoodsId());
		/**
		 * 添加后台库运营位
		 */
		bopsOpsMainDAO.insertOpsPosition(xinhuan);
		/**
		 * 添加前台库运营位
		 */
		opsMainDAO.insertNewOpsPosition(xinhuan);
		Message message = Message.success();
		message.addData("object", xinhuan);
		return message;
	}

	private OpsPositionCountVO countOpsPosition(String opsPosition){
		int opsPositionCount = 0;
		OpsPositionCountVO opsPositionCountVO = new OpsPositionCountVO();
		opsPositionCount =  bopsOpsMainDAO.countOpsPosition(opsPosition);
		if(OpsPositionEnums.HOME_BANNER.code.equals(opsPosition)){
			if(opsPositionCount > 1000){
				opsPositionCountVO.setRight(false);
				opsPositionCountVO.setMessage("3015");
			}
			else{
				opsPositionCountVO.setRight(true);
			}
		}
		else if(OpsPositionEnums.SHIJIAN_BANNER.code.equals(opsPosition)){
			if(opsPositionCount > 1000){
				opsPositionCountVO.setRight(false);
				opsPositionCountVO.setMessage("3016");
			}
			else{
				opsPositionCountVO.setRight(true);
			}
		}
		else if(OpsPositionEnums.SHIJIAN_SCROLL.code.equals(opsPosition)){
			if(opsPositionCount == 3){
				opsPositionCountVO.setRight(false);
				opsPositionCountVO.setMessage("3017");
			}
			else{
				opsPositionCountVO.setRight(true);
			}
		}
		else if(OpsPositionEnums.XINHUAN.code.equals(opsPosition)){
			if(opsPositionCount == 3){
				opsPositionCountVO.setRight(false);
				opsPositionCountVO.setMessage("3018");
			}
			else{
				opsPositionCountVO.setRight(true);
			}
		}
		opsPositionCountVO.setRight(true);
		return opsPositionCountVO;
	}
	/**
	 * @author xiehao 2015年9月11日 下午4:29:41
	 * @Method: queryOpsPosition 
	 * @Description: TODO 查询运营位
	 * @return 
	 * @throws
	 */
	@Override
	public List<BopsOpsMain> queryOpsPosition(OpsXinhuanParamVO categoryPage) {
		List<BopsOpsMain> list=bopsOpsMainDAO.queryAllOpsPosition(categoryPage);
		return list;
	}

	/**
	 * @author xiehao 2015年9月10日 上午11:21:11
	 * @Method: updateOpsPosition 
	 * @Description: TODO 更新运营位信息
	 * @param xinhuan
	 * @return 
	 * @throws
	 */
	@Override
	@Transactional
	public Message updateOpsPosition(OpsXinhuanParamVO xinhuan) {
		// TODO Auto-generated method stub
		Message message = Message.success();
		/**
		 * 更新后台库运营位
		 */
		bopsOpsMainDAO.updateOpsPosition(xinhuan);
		/**
		 * 更新前台库运营位
		 */
		opsMainDAO.updateOpsPosition(xinhuan);
		message.addData("object", xinhuan);
		return message;
	}
	@Override
	public int getcount(OpsXinhuanParamVO categoryPage) {
		
		return bopsOpsMainDAO.getCount(categoryPage);
	}
	
}
