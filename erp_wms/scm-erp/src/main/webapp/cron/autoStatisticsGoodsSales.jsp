<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.need.service.bops.goods.impl.GoodsTaskServiceImpl"%>
<%@page import="com.need.service.bops.goods.impl.BopsGoodsSalesServiceImpl"%>
<%@page import="com.need.service.bops.wms.impl.ErpCommonServiceImpl"%>

<%@page import="org.springframework.web.context.WebApplicationContext,org.springframework.web.context.support.* "%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
	out.println("begin!!"+context+"<br>");
	long begin1 = System.currentTimeMillis();
	Map<String,List<String>> resultMap = null;
    BopsGoodsSalesServiceImpl bopsGoodsSalesServiceImpl = (BopsGoodsSalesServiceImpl)context.getBean("bopsGoodsSalesServiceImpl");
	bopsGoodsSalesServiceImpl.saveGoodsSales();
	out.println("统计销量成功");
%>

