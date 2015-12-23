package com.need.integration.web.controller.trade;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.integration.constant.ControllerMappings;
import com.need.integration.dao.jdbc.api.trade.TradeBaseDAO;
import com.need.integration.dao.jdbc.api.trade.TradeRetrieveStatusRecordDAO;
import com.need.integration.dao.jdbc.api.trade.po.TradeRetrieveStatusRecord;
import com.need.utils.StringUtil;
@Controller
@RequestMapping(value = ControllerMappings.YUANTONG_TRACKING_STATUS)
public class YuanTongController {
	@Autowired
	TradeRetrieveStatusRecordDAO tradeRetrieveStatusRecordDAO;
	@Autowired
	TradeBaseDAO tradeBaseDAO;
	private static Logger logger = Logger.getLogger(YuanTongController.class);
	@ResponseBody

	@RequestMapping(method = RequestMethod.POST, value = "/getTrackingStatus")
	public String updateTrackingStatus(HttpServletRequest request){
		String logistics_interface = request.getParameter("logistics_interface");
		String txLogisticID = null;
		StringBuilder xmlBuilder = new StringBuilder();
		 try {
		
			 logger.info("logistics_interface"+logistics_interface);
			 Map<String, String> map = doXMLParse(java.net.URLDecoder.decode(logistics_interface,"UTF-8"));
			 txLogisticID= map.get("txLogisticID");
			 TradeRetrieveStatusRecord tradeRetrieveStatusRecord = new TradeRetrieveStatusRecord();
			 tradeRetrieveStatusRecord.setTradeNo(txLogisticID);
			 tradeRetrieveStatusRecord.setTrackingCode(map.get("infoContent"));
			 if("SENT_SCAN".equals(map.get("infoContent"))){
				 tradeRetrieveStatusRecord.setTrackingDesc(map.get("remark")+" 派件人："+map.get("deliveryName")+" 派件员电话:"+map.get("contactInfo"));
			 }else if("SIGNED".equals(map.get("infoContent"))){
				 tradeRetrieveStatusRecord.setTrackingDesc("客户签收人："+map.get("signedName")+" 已签收   感谢使用圆通速递，期待再次为您服务");
			 }else if("ACCEPT".equals(map.get("infoContent"))){
				 if(StringUtil.isEmptyOrNull(map.get("remark"))){
					 tradeRetrieveStatusRecord.setTrackingDesc("签单成功");
				 }else{
					 tradeRetrieveStatusRecord.setTrackingDesc(map.get("remark"));
				 }
			 }else{
				 tradeRetrieveStatusRecord.setTrackingDesc(map.get("remark"));
			 }
			 tradeRetrieveStatusRecordDAO.insert(tradeRetrieveStatusRecord);
			 xmlBuilder.append("<Response>");
			 xmlBuilder.append("    <logisticProviderID>YTO</logisticProviderID>");
			 xmlBuilder.append("    <txLogisticID>"+txLogisticID+"</txLogisticID>");
			 xmlBuilder.append("    <success>true</success>");
			 xmlBuilder.append("</Response>");
		} catch (Exception e) {
			 xmlBuilder.append("<Response>");
			 xmlBuilder.append("    <logisticProviderID>YTO</logisticProviderID>");
			 xmlBuilder.append("    <txLogisticID>"+txLogisticID+"</txLogisticID>");
			 xmlBuilder.append("    <success>false</success>");
			 xmlBuilder.append("</Response>");
			 logger.error("圆通推送失败："+e.getMessage());
			e.printStackTrace();
		}
		 return xmlBuilder.toString();
	}
	private Map<String, String> doXMLParse(String strxml) throws JDOMException, IOException {
		strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

		if (null == strxml || "".equals(strxml)) {
			return null;
		}

		Map<String, String> m = new HashMap<String, String>();

		InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List<?> list = root.getChildren();
		Iterator<?> it = list.iterator();
		while (it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List<?> children = e.getChildren();
			if (children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = getChildrenText(children);
			}

			m.put(k, v);
		}
		in.close();
		return m;
	}
	private String getChildrenText(List<?> children) {
		StringBuffer sb = new StringBuffer();
		if (!children.isEmpty()) {
			Iterator<?> it = children.iterator();
			while (it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List<?> list = e.getChildren();
				sb.append("<" + name + ">");
				if (!list.isEmpty()) {
					sb.append(getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}

		return sb.toString();
	}
}
