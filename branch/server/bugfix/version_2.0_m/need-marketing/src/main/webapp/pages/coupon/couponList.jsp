<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<script src="../resources/app/js/jquery-2.1.4.min.js"></script>
<%@ page isELIgnored="false"%>
<!DOCTYPE HTML>
<html lang="zh-cmn-Hans">
    <head>
        <meta charset="utf-8">

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

        <meta content="" name="description">
        <meta content="" name="keywords">
        <!-- seo 描述文字 关键字文字 -->

        <title>活动优惠券</title>
        <link rel="stylesheet" type="text/css" href="../resources/app/css/reset.css">
        <link rel="stylesheet" type="text/css" href="../resources/app/css/style.css">
        <link rel="stylesheet" type="text/css" href="../resources/app/css/style2.css">
        <link rel="stylesheet" type="text/css" href="../resources/app/css/2.css">
		<script type="text/javascript">
	    function getNow(couponTemplateId){
	    	var userId=$("#userId").val();
	    	if(userId==null||userId==""){
	    		 window.location.href="com.needapp.app://need?receiveCoupe";
	    	}else{
	    	var data = {"userId":userId,"couponTemplateId":couponTemplateId};
	    	$.ajax({
					type : "post",
					url : "./getCoupon/",
					data: data,
					cache : false,
					dataType : 'json',
					success : function(result) {
						if(result.code == 200){
							/* document.getElementById("fix").style.display="block"; */
							var get = document.getElementsByClassName('getit')[0];
					        var success = document.getElementsByClassName('success')[0];
			                success.style.display="block";
			                setTimeout(function(){
			                   success.style.display="none";
			                },800);
			                $("#"+couponTemplateId+"a").css("background","#DADADA");
							$("#"+couponTemplateId+"b").css("background","#DADADA");
							$("#"+couponTemplateId+"c").css("color","#DADADA");
							$("#"+couponTemplateId+"b").removeAttr("onclick");
						}
						else{
							alert(result.msg);
						}
					}
				});
		}
	    }
	   /*  function fixNone(){
			document.getElementById("fix").style.display="none";
		} */
		
    </script>
    </head>

    <body style="background:#FFFFFF;">
        <script src="../resources/app/js/check_app.js"></script>
        <div class="banner">
            <img src="../resources/app/img/instructions_banner_img2x-1.png">
        </div>
        <c:forEach items="${list}" var="list" varStatus="status">
        	<form name="" id="" action="">
        	<input type="hidden" id="userId" name="userId" value="${userId}">
	        <section class="discount15">
	            <p id="${list.couponTemplateId}a" class="
	            	<c:if test="${list.flag !='1'}">left backgroundDefault</c:if>
	                <c:if test="${list.flag =='1'}">left backgroundGray</c:if>
	            ">
	                <span class="underline" style="top:10px;text-decoration:none;">生活费</span>
	            </p>
	            <span class="circle-top"></span>
	            <span class="circle-down"></span>
	            <div id="${list.couponTemplateId}b" 
	            		<c:if test="${list.flag =='1'}"></c:if>
	                	<c:if test="${list.flag !='1'}"> onClick="getNow('${list.couponTemplateId}')"</c:if>
	                class="
	            	<c:if test="${list.flag !='1'}">right backgroundDefault</c:if>
	                <c:if test="${list.flag =='1'}">right backgroundGray</c:if>
	            ">
	                <span class="rmb">¥</span>
	                <span class="num" style="left:1.3rem;font-size: 3.5rem;">${list.value}</span>
	                <a id="${list.couponTemplateId}c" style="text-decoration:none;padding-left:10%;" class="
	                	<c:if test="${list.flag !='1'}">use-now colorDefault</c:if>
	                	<c:if test="${list.flag =='1'}">use-now colorGray</c:if>
	                " 
	                	>立即领取</a>
	                <p class="explain" name="minCost"> 单笔订单满${list.minCost}元可用
	                	<span style="font-size: 0.7rem;">
	                      	  截止日期：${list.endMonth}
	                    </span>
	                </p>
	            </div>
	        </section>
	        </form>
        </c:forEach>
       <!--  <div class="instruction">
            <img src="../resources/app/img/instru.png">
        </div> -->
        <!-- <div id="fix" class="fixed" style="display:none;">
            <div class="shade"></div>
            <section>
                <h3>领取成功，已存入我的生活费</h3>
                <p></p>
                <p>
                    <input class="queding" type="button" onClick="fixNone();" value="确定"/>
                </p>
            </section>
        </div> -->
         <section class="success">
            <p class="getit">领取成功
            </p>
        </section>
        <script type="text/javascript" src="../resources/app/js/auto_fontsize.js"></script>
    </body>
</html>