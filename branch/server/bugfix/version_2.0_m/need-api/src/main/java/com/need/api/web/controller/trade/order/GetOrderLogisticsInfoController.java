package com.need.api.web.controller.trade.order;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.need.api.constant.ControllerMappings;
import com.need.common.core.dao.jdbc.trade.RetrieveStatusRecordDAO;
import com.need.common.core.dao.jdbc.trade.TradePushPullDAO;
import com.need.common.domain.enums.WarehouseTypeEnum;
import com.need.common.domain.po.api.trade.TradePushPullPO;
import com.need.common.domain.po.api.trade.TradeRetrieveStatusRecord;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.trade.RetrieveStatusVO;
import com.need.utils.StringUtil;

@Controller
@RequestMapping(ControllerMappings.GET_ORDER_LOGISTICS_INFO)
public class GetOrderLogisticsInfoController {
	private static final Logger logger = Logger.getLogger(GetOrderLogisticsInfoController.class);
	@Autowired
	private TradePushPullDAO TradePushPullDAO;

	@Autowired
	private RetrieveStatusRecordDAO retrieveStatusRecordDAO;

	@ResponseBody
	@RequestMapping(params = "apiVersion=1.3", method = RequestMethod.GET)
	public Message getOrderLogisticsInfo(@RequestParam(required = true) String userOrderNo) {
		

		Message message = Message.success();
		List<RetrieveStatusVO> list = new ArrayList<RetrieveStatusVO>();
		List<TradeRetrieveStatusRecord> retrieveList = retrieveStatusRecordDAO.getByTradeNo(userOrderNo);
		TradePushPullPO push = TradePushPullDAO.getByTradeNo(userOrderNo);
		String logisticsNo = push == null ? "" : push.getLogisticsNo();
		for (TradeRetrieveStatusRecord retrieve : retrieveList) {
			RetrieveStatusVO retrieveVO = new RetrieveStatusVO();
			if (!StringUtil.isBlank(retrieve.getTrackingDesc())) {
				retrieveVO.setState(retrieve.getTrackingDesc());
				retrieveVO.setCreateTime(retrieve.getCreateTime());
				list.add(retrieveVO);
			}
		}
		/**
		 * 如果是北京仓的并且是圆通的就查询圆通走件接口
		 * @author zhangmengbin
		 */
		if(push!=null){
			if(!StringUtil.isEmptyOrNull(push.getMemoLogistics())&& !StringUtil.isEmptyOrNull(push.getWarehouseType())){
				logger.debug("===========北京自营仓==================");
					if("2".equals(push.getMemoLogistics())&& WarehouseTypeEnum.SELF_WAREHOUSE.code.equals(push.getWarehouseType()) ){
						String Url = "http://58.32.246.70:8002";
						try {
							String a = sender(Data(push.getLogisticsNo()),Url);
							if(!StringUtil.isEmptyOrNull(a)){
								List<JSONObject> jsonObjects = JSONObject.parseObject(a, List.class);
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
								if(jsonObjects!=null && jsonObjects.size()>0){
									for(JSONObject jsonObject:jsonObjects){
										System.out.println(jsonObject.getString("Upload_Time"));
										Date createTime =null;
										if(jsonObject.getString("Upload_Time").contains("/")){
											createTime=sdFormat.parse(jsonObject.getString("Upload_Time"));
										}else{
											createTime =sdf.parse(jsonObject.getString("Upload_Time"));
										}
										RetrieveStatusVO retrieveVO = new RetrieveStatusVO();
											retrieveVO.setState(jsonObject.getString("ProcessInfo"));
											retrieveVO.setCreateTime(createTime);
											list.add(retrieveVO);
									}
								}
								Collections.reverse(list);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
		}
		message.addData("tradeStatusList", list);
		message.addData("logisticsNo", logisticsNo);
		return message;

	}
	public static String MD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
            System.out.println("MD5(" + sourceStr + ",32) = " + result);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        System.out.println("32位大写md5:"+result.toUpperCase());
        return result.toUpperCase();
    }
    
	public static String Data(String WaybillNumber)
	{
		String method = "yto.Marketing.WaybillTrace";
        String app_key = "qAzsXQ";
        String user_id = "DT20151202";
        String Format = "json";
        String Secret_Key = "Fz389D";//Secret_Key 私钥
       // String WaybillNumber = "805796035627";//单号
        
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date now = Calendar.getInstance().getTime();
		
		String timeStr=time.format(now); 
		
        String Paramet = "app_key=" + app_key + "&format=" + Format + "&method="
        + method + "&timestamp=" + timeStr
        + "&user_id=" + user_id + "&v=1.0";
        
        String param="[{\"Number\":\""+WaybillNumber+"\"}]";
        
        String[] Arr= Paramet.split("&");
        StringBuilder Sb=new StringBuilder();
        Sb.append(Secret_Key);
        for(int i=0;i<Arr.length;i++)
        {
        	if(Arr[i].split("=").length!=2)
        	{
        		System.out.println("参数格式不正确");
        	}
        	String ParName=(Arr[i].split("="))[0].trim();
        	String ParValue=(Arr[i].split("="))[1].trim();
        	Sb.append(ParName+ParValue);
        }
        System.out.println("待加密的sign："+Sb.toString());
        String sign=MD5(Sb.toString());
        
        System.err.println("最终发送的data："+"sign="+sign+"&"+Paramet+"&param="+param);
        
        return "sign="+sign+"&"+Paramet+"&param="+param;
        
	}
	/**
     * 向指定 URL 发送POST方法的请求
     * 
     */	public static String sender(String xmlBuilder,String url) {
		String responsexml = "";
		try {
			// 打开连接
			if(url==null){
				
			}
			URL strUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) strUrl.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setReadTimeout(120*1000);
			connection.setConnectTimeout(120*1000);
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded;charset=utf-8");
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(xmlBuilder);
			out.flush();
			out.close();

			// 获取服务端的反馈
			String responseString = "";
			String strLine = "";
			InputStream in = connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			while ((strLine = reader.readLine()) != null) {
				responseString += strLine + "\n";
			}
			in.close();
			responsexml = responseString;
			
		} catch (Exception e) {
			
		}
		return responsexml;
	}
}
