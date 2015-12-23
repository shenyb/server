package com.need.http;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.nio.charset.Charset; 
import java.security.cert.CertificateException; 
import java.security.cert.X509Certificate; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.Map; 
   
import javax.net.ssl.SSLContext; 
import javax.net.ssl.SSLException; 
import javax.net.ssl.SSLSession; 
import javax.net.ssl.SSLSocket; 
import javax.net.ssl.TrustManager; 
import javax.net.ssl.X509TrustManager; 
   
import org.apache.http.Header; 
import org.apache.http.HttpEntity; 
import org.apache.http.HttpResponse; 
import org.apache.http.NameValuePair; 
import org.apache.http.ParseException; 
import org.apache.http.client.ClientProtocolException; 
import org.apache.http.client.HttpClient; 
import org.apache.http.client.entity.UrlEncodedFormEntity; 
import org.apache.http.client.methods.HttpGet; 
import org.apache.http.client.methods.HttpPost; 
import org.apache.http.conn.ConnectTimeoutException; 
import org.apache.http.conn.scheme.Scheme; 
import org.apache.http.conn.ssl.SSLSocketFactory; 
import org.apache.http.conn.ssl.X509HostnameVerifier; 
import org.apache.http.entity.ContentType; 
import org.apache.http.entity.StringEntity; 
import org.apache.http.impl.client.DefaultHttpClient; 
import org.apache.http.message.BasicNameValuePair; 
import org.apache.http.params.CoreConnectionPNames; 
import org.apache.http.protocol.HTTP; 
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;


public class HttpClientProxy {
	
	private static final Logger  logger = Logger.getLogger(HttpClientProxy.class);
	
	 private HttpClientProxy(){} 
     
	    /**
	     * 发送HTTP_GET请求
	     * @see 1)该方法会自动关闭连接,释放资源
	     * @see 2)方法内设置了连接和读取超时时间,单位为毫秒,超时或发生其它异常时方法会自动返回"通信失败"字符串
	     * @see 3)请求参数含中文时,经测试可直接传入中文,HttpClient会自动编码发给Server,应用时应根据实际效果决定传入前是否转码
	     * @see 4)该方法会自动获取到响应消息头中[Content-Type:text/html; charset=GBK]的charset值作为响应报文的解码字符集
	     * @see   若响应消息头中无Content-Type属性,则会使用HttpClient内部默认的ISO-8859-1作为响应报文的解码字符集
	     * @param requestURL 请求地址(含参数)
	     * @return 远程主机响应正文
	     * @throws UnsupportedEncodingException 
	     */ 
	
	 
	    public static String sendGetRequest(String reqURL,Map<String, String> params, String encodeCharset) throws UnsupportedEncodingException{ 
	        String respContent = "通信失败"; //响应内容 
	        HttpClient httpClient = new DefaultHttpClient(); //创建默认的httpClient实例 
	        //设置代理服务器 
	        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000); //连接超时10s 
	        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);         //读取超时20s 
	        StringBuffer requestParams=new StringBuffer("?");
	        int size=params.size();
	        int currSize=0;
	        for(Map.Entry<String,String> entry : params.entrySet()){ 
	        	currSize++;
	        	String param=entry.getKey()+"="+entry.getValue();
	        	requestParams.append(param);
	        	if(currSize != size){
	        		requestParams.append("&");
	        	}
	        	
            } 
	        System.out.println(reqURL+requestParams.toString());
	        HttpGet httpGet = new HttpGet(reqURL+requestParams.toString()); //创建org.apache.http.client.methods.HttpGet 
	        //httpGet.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=" + encodeCharset); 
	        try{ 
	            HttpResponse response = httpClient.execute(httpGet); //执行GET请求 
	            HttpEntity entity = response.getEntity();            //获取响应实体 
	            if(null != entity){ 
	                Charset respCharset = ContentType.getOrDefault(entity).getCharset(); 
                    //response有可能没设置编码格式，应该按参数设置的编码格式编码
                    if(respCharset == null) {
                        respCharset = Charset.forName(encodeCharset);
                    }
	                respContent = EntityUtils.toString(entity, respCharset); 
	                //Consume response content 
	                EntityUtils.consume(entity); 
	            } 
	            System.out.println("-------------------------------------------------------------------------------------------"); 
	            StringBuilder respHeaderDatas = new StringBuilder(); 
	            for(Header header : response.getAllHeaders()){ 
	                respHeaderDatas.append(header.toString()).append("\r\n"); 
	            } 
	            String respStatusLine = response.getStatusLine().toString(); //HTTP应答状态行信息 
	            String respHeaderMsg = respHeaderDatas.toString().trim();    //HTTP应答报文头信息 
	            String respBodyMsg = respContent;                            //HTTP应答报文体信息 
	            System.out.println("HTTP应答完整报文=[" + respStatusLine + "\r\n" + respHeaderMsg + "\r\n\r\n" + respBodyMsg + "]"); 
	            System.out.println("-------------------------------------------------------------------------------------------"); 
	        } catch (Exception cte){ 
	            logger.error("请求通信[" + reqURL + "]时连接超时,堆栈轨迹如下", cte); 
	        }finally{ 
	            //关闭连接,释放资源 
	            httpClient.getConnectionManager().shutdown(); 
	        } 
	        return respContent; 
	    } 
	       
	       
	    /**
	     * 发送HTTP_POST请求
	     * @see 1)该方法允许自定义任何格式和内容的HTTP请求报文体
	     * @see 2)该方法会自动关闭连接,释放资源
	     * @see 3)方法内设置了连接和读取超时时间,单位为毫秒,超时或发生其它异常时方法会自动返回"通信失败"字符串
	     * @see 4)请求参数含中文等特殊字符时,可直接传入本方法,并指明其编码字符集encodeCharset参数,方法内部会自动对其转码
	     * @see 5)该方法在解码响应报文时所采用的编码,取自响应消息头中的[Content-Type:text/html; charset=GBK]的charset值
	     * @see   若响应消息头中未指定Content-Type属性,则会使用HttpClient内部默认的ISO-8859-1
	     * @param reqURL        请求地址
	     * @param params      
	     * @param encodeCharset 编码字符集,默认UTF-8
	     * @return 远程主机响应正文
	     */ 
	    public static String sendPostRequest(String reqURL, Map<String, String> params, String encodeCharset){ 
	        String reseContent = "通信失败"; 
	        if(encodeCharset==null||encodeCharset.equals("")){
	        	encodeCharset="UTF-8";
	        }
	        
	        HttpClient httpClient = new DefaultHttpClient(); 
	        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000); 
	        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000); 
	        HttpPost httpPost = new HttpPost(reqURL); 
	        httpPost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=" + encodeCharset); 
	        try{ 	            
	            if(null != params){ 
	                List<NameValuePair> formParams = new ArrayList<NameValuePair>(); 
	                for(Map.Entry<String,String> entry : params.entrySet()){ 
	                    formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue())); 
	                } 
	                httpPost.setEntity(new UrlEncodedFormEntity(formParams, encodeCharset)); 
	            } 
	            
	            HttpResponse response = httpClient.execute(httpPost); 
	            HttpEntity entity = response.getEntity(); 
	            if (null != entity) { 
                    Charset charset = ContentType.getOrDefault(entity).getCharset();
                    //response有可能没设置编码格式，应该按参数设置的编码格式编码
                    if(charset == null) {
                        charset = Charset.forName(encodeCharset);
                    }
	                reseContent = EntityUtils.toString(entity, charset); 
	                EntityUtils.consume(entity); 
	            } 
	        } catch (Exception cte){ 
	            logger.error("请求通信[" + reqURL + "]时连接超时,堆栈轨迹如下", cte); 	        
	        }finally{ 
	            httpClient.getConnectionManager().shutdown(); 
	        } 
	        return reseContent; 
	    } 
	       
	       
	    /**
	     * 发送HTTP_POST_SSL请求
	     * @see 1)该方法会自动关闭连接,释放资源
	     * @see 2)该方法亦可处理普通的HTTP_POST请求
	     * @see 3)当处理HTTP_POST_SSL请求时,默认请求的是对方443端口,除非reqURL参数中指明了SSL端口
	     * @see 4)方法内设置了连接和读取超时时间,单位为毫秒,超时或发生其它异常时方法会自动返回"通信失败"字符串
	     * @see 5)请求参数含中文等特殊字符时,可直接传入本方法,并指明其编码字符集encodeCharset参数,方法内部会自动对其转码
	     * @see 6)方法内部会自动注册443作为SSL端口,若实际使用中reqURL指定的SSL端口非443,可自行尝试更改方法内部注册的SSL端口
	     * @see 7)该方法在解码响应报文时所采用的编码,取自响应消息头中的[Content-Type:text/html; charset=GBK]的charset值
	     * @see   若响应消息头中未指定Content-Type属性,则会使用HttpClient内部默认的ISO-8859-1
	     * @param reqURL        请求地址
	     * @param params        请求参数
	     * @param encodeCharset 编码字符集,编码请求数据时用之,当其为null时,则取HttpClient内部默认的ISO-8859-1编码请求参数
	     * @return 远程主机响应正文
	     */ 
	    public static String sendPostSSLRequest(String reqURL, Map<String, String> params, String encodeCharset){ 
	        String responseContent = "通信失败"; 
	        if(encodeCharset==null||encodeCharset.equals("")){
	        	encodeCharset="UTF-8";
	        }
	        HttpClient httpClient = new DefaultHttpClient(); 
	        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000); 
	        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000); 
	        //创建TrustManager() 
	        X509TrustManager trustManager = new X509TrustManager(){ 
	            @Override 
	            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {} 
	            @Override 
	            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {} 
	            @Override 
	            public X509Certificate[] getAcceptedIssuers() {return null;} 
	        }; 
	        //创建HostnameVerifier 
	        X509HostnameVerifier hostnameVerifier = new X509HostnameVerifier(){ 
	            @Override 
	            public void verify(String host, SSLSocket ssl) throws IOException {} 
	            @Override 
	            public void verify(String host, X509Certificate cert) throws SSLException {} 
	            @Override 
	            public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {} 
	            @Override 
	            public boolean verify(String arg0, SSLSession arg1) {return true;} 
	        }; 
	        try { 
	            SSLContext sslContext = SSLContext.getInstance(SSLSocketFactory.TLS); 
	            //使用TrustManager来初始化该上下文,TrustManager只是被SSL的Socket所使用 
	            sslContext.init(null, new TrustManager[]{trustManager}, null); 
	            //创建SSLSocketFactory 
	            SSLSocketFactory socketFactory = new SSLSocketFactory(sslContext, hostnameVerifier); 
	            httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory)); 
	            //创建HttpPost 
	            HttpPost httpPost = new HttpPost(reqURL); 
	            //构建POST请求的表单参数 
	            if(null != params){ 
	                List<NameValuePair> formParams = new ArrayList<NameValuePair>(); 
	                for(Map.Entry<String,String> entry : params.entrySet()){ 
	                    formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue())); 
	                } 
	                httpPost.setEntity(new UrlEncodedFormEntity(formParams, encodeCharset)); 
	            } 
	            HttpResponse response = httpClient.execute(httpPost); 
	            HttpEntity entity = response.getEntity(); 
	            if (null != entity) { 
	                responseContent = EntityUtils.toString(entity, ContentType.getOrDefault(entity).getCharset()); 
	                EntityUtils.consume(entity); 
	            } 
	        } catch (Exception cte){ 
	            logger.error("请求通信[" + reqURL + "]时连接超时,堆栈轨迹如下", cte);         
	        } finally { 
	            httpClient.getConnectionManager().shutdown(); 
	        } 
	        return responseContent; 
	    }
	
	    
	    public static String sendPostAsXml(String reqURL, String xmlInfo,String encodeCharset) {
	    	String reseContent = "通信失败"; 
	        if(encodeCharset==null||encodeCharset.equals("")){
	        	encodeCharset="UTF-8";
	        }
	        
	        HttpClient httpClient = new DefaultHttpClient(); 
	        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000); 
	        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000); 
	        HttpPost httpPost = new HttpPost(reqURL); 
	        httpPost.setHeader(HTTP.CONTENT_TYPE, "text/xml; charset=" + encodeCharset); 
	        try{
		        StringEntity requestEntity = new StringEntity(xmlInfo);
		        httpPost.setEntity(requestEntity); 
	            HttpResponse response = httpClient.execute(httpPost); 
	            HttpEntity entity = response.getEntity();
	            
	            if (null != entity) { 
	                Charset charset = ContentType.getOrDefault(entity).getCharset();
	                //response有可能没设置编码格式，应该按参数设置的编码格式编码
	                if(charset == null) {
	                    charset = Charset.forName(encodeCharset);
	                }
	                reseContent = EntityUtils.toString(entity, charset); 
	                EntityUtils.consume(entity); 
	              }
            
	        } catch (Exception cte){ 
	            logger.error("请求通信[" + reqURL + "]时连接超时,堆栈轨迹如下", cte); 	        
	        }finally{ 
	            httpClient.getConnectionManager().shutdown(); 
	        } 
	        return reseContent; 
	    	
	    }	
	
}
