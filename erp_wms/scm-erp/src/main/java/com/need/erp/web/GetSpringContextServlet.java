package com.need.erp.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.need.service.bops.wms.impl.ErpCommonServiceImpl;

/**
 * 这个servlet配置在web.xml，用于获取存放WebApplicationContext，保证和jsp中WebApplicationContext是同一个。
 * @author KK
 *
 */
public class GetSpringContextServlet extends HttpServlet {
    
    /**
	 * serialVersionUID
	 */
   private static final long serialVersionUID = 1L;
   
   /**
    * WebApplicationContext
    */
   private static WebApplicationContext ctx = null ;
   public void init(){
	   System.out.println("开始 初始化WebApplicationContext");
       ctx =WebApplicationContextUtils.getWebApplicationContext(getServletContext());
       System.out.println("完成 初始化WebApplicationContext");
       boolean isThreadLoop = true;
       System.out.println("是否启动临时库存更新线程: "+isThreadLoop);
       InputStream in = getClass().getClassLoader().getResourceAsStream("/properties/wmsToErpTopic.properties");
       Properties p = new Properties();
       try {
		p.load(in);
	} catch (IOException e) {
		e.printStackTrace();
	}
       if(isThreadLoop){
    	   System.out.println("开始 启动临时库存更新线程");
    	   ErpCommonServiceImpl erpCommonServiceImpl = (ErpCommonServiceImpl) ctx.getBean("erpCommonServiceImpl");
    	   erpCommonServiceImpl.wmsToErpResult(isThreadLoop,p.getProperty("201"));
    	   erpCommonServiceImpl.wmsToErpResult(isThreadLoop,p.getProperty("101"));
    	   erpCommonServiceImpl.wmsToErpResult(isThreadLoop,p.getProperty("001"));
    	   erpCommonServiceImpl.wmsToErpResult(isThreadLoop,p.getProperty("002"));
	       erpCommonServiceImpl.wmsToErpResult(isThreadLoop,p.getProperty("212"));
	       erpCommonServiceImpl.wmsToErpResult(isThreadLoop,p.getProperty("100"));
       }
       //初始化EDI系统
      // EdiSystem.init("edi/edi" + Constants.WAREHOUSE_ID + ".xml");
       
       
   }
  
   public void doGet(HttpServletRequest request, HttpServletResponse response) {
   }
   
   /**
    * 获取WebApplicationContext，没有set方法，保证singleton
    * @return WebApplicationContext
    */
   public static WebApplicationContext getWebApplicationContext(){
	   return ctx ;
   }
}