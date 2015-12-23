package com.need.service.bops.cheap.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.need.biz.utils.CurrencyUtil;
import com.need.dao.api.cheap.CheapBaseDAO;
import com.need.dao.bops.cheap.BopsCheapAuditDAO;
import com.need.dao.bops.cheap.BopsCheapBaseDAO;
import com.need.domain.common.enums.CheapStatusEnum;
import com.need.domain.po.api.cheap.CheapBasePO;
import com.need.domain.po.bops.cheap.BopsCheapAuditPO;
import com.need.domain.po.bops.cheap.BopsCheapBase;
import com.need.domain.pub.Message;
import com.need.domain.pub.Page;
import com.need.domain.vo.cheap.BopsCheapBaseVO;
import com.need.domain.vo.cheap.CheapPageVO;
import com.need.domain.vo.cheap.CheapStatVO;
import com.need.service.bops.cheap.BopsCheapBaseService;
import com.need.service.constant.BizReturnCode;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.beanutils.PropertyUtils;

@Service
public class BopsCheapBaseServiceImpl implements BopsCheapBaseService {

    @Autowired
    private BopsCheapBaseDAO cheapBaseDAO;
    @Autowired
    private CheapBaseDAO apicheapBaseDAO;
    @Autowired
    private BopsCheapAuditDAO bopsCheapAuditDAO;

    /**
     *
     * @author peiboli 2015年10月26日 下午7:54:48
     * @Method: getCheapList
     * @Description: TODO获得团便宜列表
     * @return
     * @see com.need.service.bops.cheap.BopsCheapBaseService#getCheapList()
     */
    @Override
    public List<BopsCheapBaseVO> getCheapList(CheapPageVO page) {
        page.setTotal(cheapBaseDAO.count(page));
        List<BopsCheapBaseVO> list = cheapBaseDAO.queryCheapList(page);
        return list;
    }

    @Override
    public int addCheap(BopsCheapBase bopsCheapBase) {
        // TODO Auto-generated method stub
        int isSuccess = cheapBaseDAO.insert(bopsCheapBase);
        return isSuccess;
    }

    @Override
    public BopsCheapBaseVO getCheapByid(String cheapNo) {
        BopsCheapBaseVO bopsCheapBase = cheapBaseDAO.selectByPrimaryKey(cheapNo);
        
        return bopsCheapBase;
    }
    /**
     *
     * @author peiboli 2015年10月28日 下午2:41:05
     * @Method: auditSuccess
     * @Description: TODO
     * @param cheapNo
     * @return
     * @see
     * com.need.service.bops.cheap.BopsCheapBaseService#auditSuccess(java.lang.String)
     */
    @Override
    public Message audit(String cheapNo, String auditStatus, String goodsId, Integer bopsUserId) {
        // TODO Auto-generated method stub
        if (CheapStatusEnum.VALID.status.equals(auditStatus)) {
            //检查商品是否重复
        	List<BopsCheapBase> cheap = cheapBaseDAO.getValidCheapList(goodsId);
            if (cheap.size() > 0) {
                return Message.error(BizReturnCode.CHEAP_EXISTE);
            }
            CheapBasePO cheapBasePO = cheapBaseDAO.selectByCheapNo(cheapNo);
          //   PropertyUtils.copyProperties(cheapBasePO, bopsCheapBase);
                
            //设置状态,插入前台库
            cheapBasePO.setCheapStatus(CheapStatusEnum.VALID.status);
            apicheapBaseDAO.insert(cheapBasePO);
        }
        Message message = Message.success();
        BopsCheapAuditPO bopsCheapAuditPO = new BopsCheapAuditPO();
        bopsCheapAuditPO.setAuditStatus(auditStatus);
        bopsCheapAuditPO.setAuditUserId(bopsUserId);
        bopsCheapAuditPO.setCheapNo(cheapNo);
        bopsCheapAuditPO.setCreateTime(Calendar.getInstance().getTime());
        bopsCheapAuditDAO.insert(bopsCheapAuditPO);//插入审核历史表      
        cheapBaseDAO.updateAudit(cheapNo, auditStatus);
        return message;
    }

    /**
     *
     * @author peiboli 2015年10月28日 下午6:06:24
     * @Method: update
     * @Description: TODO编辑
     * @param bopsCheapBase
     * @return
     * @see
     * com.need.service.bops.cheap.BopsCheapBaseService#update(com.need.domain.po.bops.cheap.BopsCheapBase)
     */
    @Override
    public Boolean update(BopsCheapBase bopsCheapBase) {
        // TODO Auto-generated method stub
        int count = cheapBaseDAO.update(bopsCheapBase);
        if (count == 1) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
	/**
	 * 
	 * @author peiboli 2015年11月7日 下午6:08:30
	 * @Method: frozen 
	 * @Description: TODO冻结操作
	 * @param cheapNo
	 * @param userId
	 * @return 
	 * @see com.need.service.bops.cheap.BopsCheapBaseService#frozen(java.lang.String, java.lang.Integer)
	 */
	@Override
	@Transactional
	public Message frozen(String cheapNo, Integer userId) {
		// TODO Auto-generated method stub
		Message message = Message.success();
		int isSuccess= cheapBaseDAO.updateFrozen(cheapNo,userId);
		if(isSuccess==1){
			apicheapBaseDAO.updateFrozen(cheapNo);
		return 	message;
		}else{
			return Message.error(BizReturnCode.SYSTEM_OPERATE_ERR);	
		}
		
	}

	@Override
	public List<CheapStatVO> getCheapStatList(CheapStatVO param) {
		
		param.setTotal(apicheapBaseDAO.cheapStatCount(param));
		//团便宜统计大部分数据
		List<CheapStatVO> list1= apicheapBaseDAO.queryCheapStatPage(param);
		int index = list1.size();
		//团便宜参加人数和支付人数
		List<CheapStatVO> list2= apicheapBaseDAO.queryJoinAndPay(param);
		for(int i=0;i<index;i++){
			list1.get(i).setJoinCount(list2.get(i).getJoinCount()-list1.get(i).getOpenCount());
			list1.get(i).setPayCount(list2.get(i).getPayCount());
			
		}
		return list1;
	}

}
