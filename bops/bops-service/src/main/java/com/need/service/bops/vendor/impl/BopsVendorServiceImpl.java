package com.need.service.bops.vendor.impl;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.need.dao.bops.vendor.BopsVendorDAO;
import com.need.dao.bops.wms.ESynEdiMessageDAO;
import com.need.domain.po.bops.vendor.BopsVendorPO;
import com.need.domain.po.bops.wms.ESynEdiMessage;
import com.need.domain.pub.Message;
import com.need.domain.vo.wms.VendorOutVO;
import com.need.kafka.services.producer.NeedProducer;
import com.need.service.bops.vendor.BopsVendorService;
import com.need.utils.UUIDUtils;



@Service
public class BopsVendorServiceImpl implements BopsVendorService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private BopsVendorDAO bopsVendorDAO;
	@Autowired
	ESynEdiMessageDAO eSynEdiMessageDAO;
	
	@Override
	@Transactional
	public Message  sendVendorToWMS() {
		//查询所有供应商物流商
		List<BopsVendorPO> vendorList=bopsVendorDAO.selectVendorAll();
		for (BopsVendorPO bopsVendorPO : vendorList) {
			VendorOutVO vendorOutVO=new VendorOutVO();
			vendorOutVO.setId(Long.parseLong(bopsVendorPO.getVendorId().toString()));
			if("supplier".equals(bopsVendorPO.getvType())){
				vendorOutVO.setCtype(0L);
			}else if("deliver".equals(bopsVendorPO.getvType())){
				vendorOutVO.setCtype(1L);
			}
			vendorOutVO.setClientName(bopsVendorPO.getVendorName());
			vendorOutVO.setContactPerson(bopsVendorPO.getLinkMan());
			vendorOutVO.setConPersonTel(bopsVendorPO.getLinkPhone());
			vendorOutVO.setClientAddTel(bopsVendorPO.getLinkAddress());
		/*	vendorOutVO.setZipCode(zipCode);
			vendorOutVO.setFaxNo(faxNo);
			vendorOutVO.setProvince(province);
			vendorOutVO.setRemark(remark);
			vendorOutVO.setCreateTime(createTime);
			vendorOutVO.setCreatedBy(createdBy);*/
			vendorOutVO.setIdentification(bopsVendorPO.getIdentification());
			//记录发送信息
			ESynEdiMessage eSynEdiMessage = new ESynEdiMessage(UUIDUtils.getUUID(), "002", JSONObject.toJSONString(vendorOutVO), 99L);
			eSynEdiMessageDAO.insert(eSynEdiMessage);
			//发送到wms
			NeedProducer needProducer = NeedProducer.getInstance();
			Boolean result = needProducer.sendSync("vendor_erp2wms_002", eSynEdiMessage.getId()+"",JSONObject.toJSONString(eSynEdiMessage));
			Long status;
			//改变结果状态
			if(result){
				status=2L;
			}else{
				status=9L;
			}
			eSynEdiMessage.setStatus(status);
			eSynEdiMessageDAO.updateByPrimaryKey(eSynEdiMessage);
		}
		return Message.success();
	}

}
