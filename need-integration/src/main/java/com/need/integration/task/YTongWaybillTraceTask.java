/*package com.need.integration.task;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.need.integration.dao.jdbc.api.trade.TradeLogisticsInfoDAO;
import com.need.integration.dao.jdbc.api.trade.TradeRetrieveStatusRecordDAO;
import com.need.integration.dao.jdbc.api.trade.po.TradeLogisticsInfo;
import com.need.integration.dao.jdbc.api.trade.po.TradeRetrieveStatusRecord;
import com.need.integration.util.DateUtil;
import com.need.utils.StringUtil;
@Service
public class YTongWaybillTraceTask {
	@Autowired
	TradeLogisticsInfoDAO tradeLogisticsInfoDAO;
	@Autowired
	TradeRetrieveStatusRecordDAO tradeRetrieveStatusRecordDAO;
	*//**
	 * 定时查询圆通的走件状态
	 *//*
	
	//[{"Waybill_No":"805796035627","Upload_Time":"2015-12-1 19:51:43",
	//"ProcessInfo":"【北京市顺义区城区公司】 取件人 : 赵凤萍 已收件"},{"Waybill_No":"805796035627","Upload_Time":"2015-12-1 20:19:23","ProcessInfo":"【北京市顺义区城区公司】 已收件"},{"Waybill_No":"805796035627","Upload_Time":"2015-12-2 0:09:23","ProcessInfo":"【北京转运中心】 已收入"},{"Waybill_No":"805796035627","Upload_Time":"2015-12-2 1:50:55","ProcessInfo":"【北京转运中心】 已拆包"},{"Waybill_No":"805796035627","Upload_Time":"2015-12-2 1:55:04","ProcessInfo":"【北京转运中心】 已发出 下一站 【北京市海淀区人民大学公司】"},{"Waybill_No":"805796035627","Upload_Time":"2015-12-2 8:11:48","ProcessInfo":"【北京市海淀区人民大学公司】 已收入"},{"Waybill_No":"805796035627","Upload_Time":"2015-12-2 8:13:18","ProcessInfo":"【北京市海淀区人民大学公司】 派件人 : 程鑫 派件中 派件员电话13911286447"},{"Waybill_No":"805796035627","Upload_Time":"2015-12-2 12:34:11","ProcessInfo":"客户 签收人 : 本人签收 已签收 感谢使用圆通速递，期待再次为您服务"}]

   @Scheduled(cron = "${YtongTime}")
	public void queryOrderStatusByYTong() {
		 
		//查詢
		List<TradeLogisticsInfo> tradeLogisticsInfos = tradeLogisticsInfoDAO.queryTradeListByLogisticsServiceId("2");
		if(null!=tradeLogisticsInfos&& tradeLogisticsInfos.size()>0){
			for(TradeLogisticsInfo tradeLogisticsInfo :tradeLogisticsInfos){
				doOrderStatusByYTong(tradeLogisticsInfo);
			}
		}
			//System.out.println(a);	
		
	}
	 //@Scheduled(cron = "${YtongTime}")
	public void doOrderStatusByYTong(TradeLogisticsInfo tradeLogisticsInfo){
		 String Url = "http://58.32.246.70:8002";
			try {
				String a = sender(Data(),Url);
				if(!StringUtil.isEmptyOrNull(a)){
					List<JSONObject> list = JSONObject.parseObject(a, List.class);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					if(list!=null && list.size()>0){
						for(JSONObject jsonObject:list){
							TradeRetrieveStatusRecord tradeRetrieveStatusRecord  =new TradeRetrieveStatusRecord();
							//tradeRetrieveStatusRecord.setTradeNo(tradeLogisticsInfo.getUserTradeNo());
							//tradeRetrieveStatusRecord.setOrderNo(tradeLogisticsInfo.getTradeNo());
							tradeRetrieveStatusRecord.setTradeNo(tradeLogisticsInfo.getTradeNo());
							tradeRetrieveStatusRecord.setTrackingDesc(jsonObject.getString("ProcessInfo"));
							System.out.println(jsonObject.getString("Upload_Time"));
							Date createTime =null;
							if(jsonObject.getString("Upload_Time").contains("/")){
								createTime=sdFormat.parse(jsonObject.getString("Upload_Time"));
							}else{
								createTime =sdf.parse(jsonObject.getString("Upload_Time"));
							}
							
							tradeRetrieveStatusRecord.setCreateTime(createTime);
							tradeRetrieveStatusRecord.setUpdateTime(createTime);
							TradeRetrieveStatusRecord info = tradeRetrieveStatusRecordDAO.getByTradeNoAndCreateTime("15120222757383",jsonObject.getString("ProcessInfo"));
							if(info==null){
								tradeRetrieveStatusRecordDAO.insert(tradeRetrieveStatusRecord);
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
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
    
	public static String Data()
	{
		String method = "yto.Marketing.WaybillTrace";
        String app_key = "qAzsXQ";
        String user_id = "DT20151202";
        String Format = "json";
        String Secret_Key = "Fz389D";//Secret_Key 私钥
        String WaybillNumber = "805796035627";//单号
        
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
	*//**
     * 向指定 URL 发送POST方法的请求
     * 
     *//*	public static String sender(String xmlBuilder,String url) {
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
*/