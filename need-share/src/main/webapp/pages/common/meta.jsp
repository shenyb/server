<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
	String path = request.getContextPath();
    String serverName = request.getServerName();
    if(serverName.startsWith("blf_share")){
    	serverName = "www.boluofan.me";
    }
    String basePath=request.getScheme()+"://"+serverName+":"+request.getServerPort()+path+"/";
    //部署测试或线上环境将上面注释掉
    //String basePath = "http://www.boluofan.me/blf_share/";
%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="keywords" content="菠萝饭,腐女,耽美,耽美漫画,耽美小说,BL,盗墓笔记,全职高手" />
<meta name="description" content="菠萝饭，满足你的一切幻想，找基友看同人下资源，都在菠萝饭！" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
	
<link rel="shortcut icon" href="<%=basePath%>/images/title.png" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/share.css" media="screen" />

<%@ include file="hm.jsp" %>
<%
_HMT _hmt = new _HMT("d67b7fd293f3742e48045d12738d0ab4");
_hmt.setDomainName("www.boluofan.me/blf_share");
_hmt.setHttpServletObjects(request, response);
String _hmtPixel = _hmt.trackPageview();
%>
<img src="<%= _hmtPixel %>" width="0" height="0"  />

<script>
	window.shantoo = {"url":"<%=basePath%>"};
</script>
<script type="text/javascript" src="<%=basePath%>js/zepto.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/share.js"></script>

