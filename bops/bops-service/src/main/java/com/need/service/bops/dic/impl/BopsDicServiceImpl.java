package com.need.service.bops.dic.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.need.dao.bops.dic.BopsDicDAO;
import com.need.domain.po.bops.dic.BopsDic;
import com.need.domain.vo.dic.BopsDicVO;
import com.need.service.bops.dic.BopsDicService;
/**
 * 
 * <p></p>
 * @author LXD 2015年8月7日 下午3:14:42
 * @ClassName BopsDicServiceImpl
 * @Description TODO 根据codeValue查询数据对象
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年8月7日
 * @modify by reason:{方法名}:{原因}
 */
@Service
public class BopsDicServiceImpl implements BopsDicService {
    @Autowired
	private BopsDicDAO bopsDicDAO;
	
	@Override
	public List<BopsDicVO> getDicByDicType(String codeType) {
		List<BopsDic> listbopsDic =bopsDicDAO.getDicByCodeType(codeType);
		
		List<BopsDicVO> bopsDicVO=new ArrayList<BopsDicVO>();
		for(BopsDic bopsDic:listbopsDic){
			BopsDicVO bopsDivVO= new BopsDicVO();
			BeanUtils.copyProperties(bopsDic, bopsDivVO);
			bopsDicVO.add(bopsDivVO);
		}
		return bopsDicVO;
	}

}
