<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.need.service.bops.wms.impl.DoReceiveMessageServiceImpl"%>
<%@page import="org.springframework.web.context.WebApplicationContext,org.springframework.web.context.support.* "%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
	out.println("begin!!"+context+"<br>");
	DoReceiveMessageServiceImpl doReceiveMessageServiceImpl = (DoReceiveMessageServiceImpl) context.getBean("doReceiveMessageServiceImpl");
	doReceiveMessageServiceImpl.doReceiveMessage();
	out.println("处理kafka消息成功");
%>

