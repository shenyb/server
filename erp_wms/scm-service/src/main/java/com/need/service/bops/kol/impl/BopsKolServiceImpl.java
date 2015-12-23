package com.need.service.bops.kol.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esotericsoftware.minlog.Log;
import com.need.dao.api.kol.UserKolDAO;
import com.need.dao.api.user.UserBaseDAO;
import com.need.dao.bops.basedata.BopsKolCategoryDAO;
import com.need.dao.bops.kol.BopsKolDAO;
import com.need.domain.common.enums.AuditStatusEnums;
import com.need.domain.common.enums.UserTypeEnum;
import com.need.domain.po.api.kol.UserKol;
import com.need.domain.po.api.user.UserBase;
import com.need.domain.po.bops.basedata.BopsKolCategory;
import com.need.domain.po.bops.kol.BopsKol;
import com.need.domain.vo.kol.BopsKolParamVO;
import com.need.domain.vo.kol.BopsKolResultVO;
import com.need.domain.vo.kol.BopsKolUpdateParamVO;
import com.need.service.bops.kol.BopsKolService;

@Service
public class BopsKolServiceImpl implements BopsKolService {

	@Autowired
	private BopsKolDAO bopsKolDAO;
	@Autowired
	private UserKolDAO userKolDAO;
	@Autowired
	private UserBaseDAO userBaseDAO;
	@Autowired
	private BopsKolCategoryDAO bopsKolCategoryDAO;
	
	private static final Logger logger = Logger.getLogger(BopsKolServiceImpl.class);
	
	@Override
	public int addBopsKol(BopsKolParamVO bopsKolVO) {
		// TODO Auto-generated method stub
		if(bopsKolVO==null){
			return 0;
		}
		BopsKol bopsKol=new BopsKol();		
		BeanUtils.copyProperties(bopsKolVO,bopsKol);
		bopsKol.setAuditorStatus(AuditStatusEnums.WAIT_AUDIT.name());
		return bopsKolDAO.insert(bopsKol);
	}

	@Override
	@Transactional
	public int updateBopsKol(BopsKolParamVO bopsKolVO) {
		// TODO Auto-generated method stub
		BopsKol bopsKol=new BopsKol();	
		BeanUtils.copyProperties(bopsKolVO,bopsKol);
		return bopsKolDAO.updateKol(bopsKol);
	}

	@Override
	public BopsKolResultVO getBopsKolById(String kolId) {
		// TODO Auto-generated method stub
		BopsKol bopsKol =bopsKolDAO.selectByPrimaryKey(kolId);
		if(bopsKol==null){
			return null;
		}
		BopsKolResultVO bopsKolResultVO=new BopsKolResultVO();
		BeanUtils.copyProperties(bopsKol,bopsKolResultVO);
		if(bopsKol.getKolCategories()!=null&&bopsKol.getKolCategories().contains("[")){
		 String newKolCategories= 	bopsKol.getKolCategories().replace("[", "").replace("]", "");
		 List<BopsKolCategory> categoryList=bopsKolCategoryDAO.selectByIds(newKolCategories);
			bopsKolResultVO.setCategoryList(categoryList);
		}
		
		return bopsKolResultVO;
	}

	@Override
	public List<BopsKolResultVO> getListBopsKol(BopsKolParamVO bopsKolParamVO) {
		// TODO Auto-generated method stub
 
		bopsKolParamVO.setTotal(bopsKolDAO.selectTotalCount(bopsKolParamVO));//设置总记录数
		List<BopsKol> bopsKolResults= bopsKolDAO.selectAll(bopsKolParamVO);
		if(bopsKolResults==null){
			Log.error("bopsKolResults is null");
			return null;
		}
		List<BopsKolResultVO> results=new ArrayList<BopsKolResultVO>();
		for (BopsKol bopsKol : bopsKolResults) {
			UserBase user = userBaseDAO.selectByPrimaryKey(bopsKol.getKolId());
            if(user == null) {
                continue;
            }
			BopsKolResultVO bopsKolResultVO =new BopsKolResultVO();
			BeanUtils.copyProperties(bopsKol,bopsKolResultVO);	
			bopsKolResultVO.setProfilePicKey(user.getProfilePicKey());
			if(bopsKol.getKolCategories()!=null&&bopsKol.getKolCategories().contains("[")){
				String newKolCategories=  bopsKol.getKolCategories().replace("[", "").replace("]", "");
				List<BopsKolCategory> categoryList=bopsKolCategoryDAO.selectByIds(newKolCategories);
				StringBuilder sb=new StringBuilder();
				for(BopsKolCategory category:categoryList){
					sb=sb.append(category.getCategoryName()+" | ");
				}
//				bopsKolResultVO.setCategoryList(categoryList);
				bopsKolResultVO.setKolCategories(sb.toString().substring(0, sb.length()-3));
			}else{
				Log.error("bopsKol.getKolCategories() is null");
			}
			
			
			results.add(bopsKolResultVO);
		}
		bopsKolResults=null;
	    
		return results;
	}

	@Override
	public int deleteBopsKol(String kolId) {
		// TODO Auto-generated method stub
		deleteUserKol(kolId);
		return bopsKolDAO.deleteByPrimaryKey(kolId);
	}

	@Override
	@Transactional
	public void auditKolService(BopsKolParamVO bopsKolVO) {
		// TODO Auto-generated method stub	
		//审核通过时,添加到前端行家表
		BopsKol bopsKol=new BopsKol();	
		BeanUtils.copyProperties(bopsKolVO,bopsKol);
		deleteUserKol(bopsKolVO.getKolId());
		int isSuccess= bopsKolDAO.updateKol(bopsKol);
		if(isSuccess!=1){
			Log.error("updateKol fail");
		}
		if(bopsKolVO.getAuditorStatus()!=null&&AuditStatusEnums.SUCCESS.code.equals(bopsKolVO.getAuditorStatus())){
		
		    bopsKol =bopsKolDAO.selectByPrimaryKey(bopsKolVO.getKolId());
		    
		    userKolDAO.updateSuccess(bopsKolVO.getKolId(),UserTypeEnum.KOL.code);
			String cats[] = bopsKol.getKolCategories().replaceAll("[\"\\[\\]]", "").split(",");
			UserKol userKol =new UserKol();
			userKol.setKolId(bopsKol.getKolId());
			userKol.setCertification(bopsKol.getCertification());
			userKol.setKolBrief(bopsKol.getKolIntroduct());
			for (int i = 0; i < cats.length; i++) {
				userKol.setKolCategories(cats[i]);
				System.out.println(userKol);
				userKolDAO.insertApiKOL(userKol);
			}
		}
	}

	@Override
	public int insertUserKol(UserKol userKol) {
		// TODO Auto-generated method stub
		return userKolDAO.insertApiKOL(userKol);
	}

	@Override
	public int deleteUserKol(String kolId) {
		// TODO Auto-generated method stub	
		userKolDAO.updateBykolId(kolId,UserTypeEnum.COMMON.name());
		userKolDAO.deleteByKolId(kolId);
		return userKolDAO.deleteByPrimaryKey(kolId);
	}

	@Override
	public List<BopsKolCategory> selectByIds(String categoryIds) {
		// TODO Auto-generated method stub
		return bopsKolCategoryDAO.selectByIds(categoryIds);
	}

	@Override
	public void addApiKol(BopsKolUpdateParamVO bopsKolVO) {
		// TODO Auto-generated method stub
		
	}


}
