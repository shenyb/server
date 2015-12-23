<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <!-- IE=edge告诉IE使用最新的引擎渲染网页 -->
        <!-- chrome=1则可以激活Chrome Frame , Chrome Frame可以让旧版IE浏览器使用Chrome的WebKit渲染引擎处理网页 -->
        <meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" name="viewport">
        <!-- 强制让文档的宽度与设备的宽度保持1:1，并且文档最大的宽度比例是1.0，且不允许用户点击屏幕放大浏览 -->

        <meta content="yes" name="apple-mobile-web-app-capable" />
        <!-- iphone设备中的safari私有meta标签，它表示：允许全屏模式浏览 -->

        <meta content="black" name="apple-mobile-web-app-status-bar-style" />
        <!-- iphone的私有标签，它指定的iphone中safari顶端的状态条的样式 -->

        <meta content="telephone=no" name="format-detection" />
        <!-- 告诉设备忽略将页面中的数字识别为电话号码 -->

        <meta content="email=no" name="format-detection" />
        <!-- android设备忽略将页面中的email识别为电子邮箱 -->
        
        <link rel="shortcut icon" href="/resources/img/common/favicon.ico" type="image/x-icon" />

        <title>客服管理系统</title>

        <link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css" >
        <link rel="stylesheet" href="/resources/css/login.css" >

        <!-- 此部分注释内容兼容IE低版本 H5相关 **不要删除**-->
        <!--[if lt IE 9]>
          <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        
           
 <!-- 在 body的最底部引入js文件且一定保持 jquery 在 bootstrap 之前引入 -->
        <script src="/resources/js/jquery/jquery-2.1.4.min.js"></script>
        <script src="/resources/js/bootstrap/bootstrap.min.js"></script>
        <script src="/resources/js/login.js"></script>
        <script type="text/javascript">
        $(document).ready(function(){
        	//$("#login-btn").click(clickLoginButton());
        	function clickLoginButton(){
        		var form=$("#login-form").serialize();
          	    $.ajax({
                     type: 'POST',
                     url:"/session",
                     data:form,
                     dataType: 'json',
                     success: function(data){
                     	alert(11);
                     },
                     error: function(xhr, type){
                     	alert(22);
                     }
                 });
        	};
        	
        	
        });    
         
        </script>
        
    </head>
 
    <body>
        <div class="login-wrap">
            <div class="login-box">
                <div class="logo">
                    <img src="/resources/img/common/CallCenter－770-86.png" alt="">
                </div>
                <form class="login-form" id="login-form" action="/session" method="post">
                    <div class="row preroll">
                        ${message} 
                        <label class="login-form-label" for="username"
                               id="username-label">Email address</label>
                        <input id="username" 
                               class="login-form-inpt" 
                               type="text" name="username" />
                      
                       <input id="return_url" 
                               class="login-form-inpt" 
                               type="hidden" name="return_url" value="${return_url}"  />   
                                    
                    </div>
                    <div class="row preroll">
                        <label class="login-form-label" for="password"
                               id="password-label">Password</label>
                        <input id="password" 
                               class="login-form-inpt" 
                               type="password" name="password" />
                    </div>
                    <div class="row preroll">
                        <button id="login-btn" class="login-form-btn" type="submit"  >
                            <div id="login-span" class="login-span">登录</div>
                            <div id="login-spinner" class="spinner hide">
                                <div class="spinner-container container1">
                                    <div class="circle1"></div>
                                    <div class="circle2"></div>
                                    <div class="circle3"></div>
                                    <div class="circle4"></div>
                                </div>
                                <div class="spinner-container container2">
                                    <div class="circle1"></div>
                                    <div class="circle2"></div>
                                    <div class="circle3"></div>
                                    <div class="circle4"></div>
                                </div>
                                <div class="spinner-container container3">
                                    <div class="circle1"></div>
                                    <div class="circle2"></div>
                                    <div class="circle3"></div>
                                    <div class="circle4"></div>
                                </div>
                            </div>
                        </button>
                    </div>
                </form>
            </div>
        </div>
        
    </body>
</html>