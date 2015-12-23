<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cmn-Hans">
    <head>
        <meta charset="utf-8">
        <meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" name="viewport">
        <meta content="yes" name="apple-mobile-web-app-capable" />
        <meta content="black" name="apple-mobile-web-app-status-bar-style" />
        <meta content="telephone=no" name="format-detection" />
        <meta content="email=no" name="format-detection" />
        <meta content="" name="description">
        <meta content="" name="keywords">
        <title>团便宜</title>
        <link rel="stylesheet" type="text/css" href="../resources/app/css/reset.css">
        <link rel="stylesheet" type="text/css" href="../resources/app/css/public.css">
        <style>
            html, body {
                height: auto;
            }
            body {
                background-color: #efefef;
                font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
                max-width: 750px;
                margin: 0 auto;
                position: relative;
            }
            section {
                margin-bottom: 0.6095rem;
            }
            section:nth-child(1){
                margin-bottom: 0;
                cursor: pointer;
            }
            section img {
                display: block;
                width: 100%;
            }
            .explain {
                background-color: #ffffff;
                padding: 0 1.25rem;
            }
            .explain-title {
                height: 2.75rem;
                line-height: 2.75rem;
                border-bottom: 1px solid #f2f2f2;
                color: #5D5D5D;
            }
            .explain-desc {
                padding: 0.625rem 0 1.5625rem 0;
            }
            .fixed-bottom-wrap {
                height: 7.4375rem;
                margin-bottom: 0;
            }
            .fixed-bottom {
                position: fixed;
                width: 100%;
                height: 7.4375rem;
                bottom: 0;
                max-width: 750px;
            }
            .fixed-bottom-t {
                background-color: #1F191C;
                height: 3.875rem;
            }
            .fixed-bottom-b {
                height: 3.5625rem;
                background-color: #ffffff;
                position: relative;
            }
            .fixed-bottom-t-title {
                float: left;
                width: 3.6rem;
                font-size: 0.875rem;
                color: #ffffff;
                margin: 0.9375rem 0 0 1rem;
                padding-bottom: 0.9375rem;
                border-bottom: 4px solid #EE4F4E;
            }
            .fixed-bottom-t-cont {
                float: right;
                margin-top: .5rem;
            }
            .fixed-bottom-t-cont li {
                float: left;
                width: 2.8125rem;
                height: 2.8125rem;
                border-radius: 50%;
                margin-right: 0.4rem;
                overflow: hidden;
                cursor: pointer;
                border: 2px solid #ffffff;
            }
            .countdown {
                position: absolute;
                padding-left: 1rem;
            }
            .countdown p:nth-child(1) {
                color: #5B5B5B;
                font-size: 0.875rem;
                margin: .5rem 0;
            }
            .countdown p:nth-child(2) {
                color: #639BC9;
                font-size: 0.8125rem;
            }
            .countdown span {
                display: inline-block;
                width: 0.75rem;
                height: 1.125rem;
                text-align: center;
                line-height: 1.125rem;
                background-color: #1F191C;
                color: #ffffff;
                margin-right: 3px;
            }
            .countdown span:nth-child(3),
            .countdown span:nth-child(5){
                margin-left: 3px;
            }
            .cheapinfo-btn {
                border: 0;
                outline: 0;
                color: #FFFFFF;
                font-size: 1.125rem;
                min-width: 13.375rem;
                height: 2.8125rem;
                padding: 0 1.4375rem;
                background-color: #EE4F4E;
                border-radius: 5px;
                cursor: pointer;
                width:13.375rem;
                float: right;
                margin: 0.375rem 0.4rem 0 0;
            }
            .goodsbrief {
                position: relative;
            }
            .goodsbrief p {
                position: absolute;
                left: 5.7668rem;
                top: 50%;
                padding-right: 1.4024rem;
                color: #40401D;
                -webkit-transform: translateY(-50%);
                -moz-transform: translateY(-50%);
                -o-transform: translateY(-50%);
                transform: translateY(-50%);
            }
            .joinin {
                position: fixed;
                width: 100%;
                bottom: 0;
                background-color: #FAFAFA;
                margin-bottom: 0;
                height: 0;
                left: 50%;
                -webkit-transform: translateX(-50%);
                -moz-transform: translateX(-50%);
                -o-transform: translateX(-50%);
                transform: translateX(-50%);
                -webkit-transition: all 0.3s ease-in-out;
                -moz-transition: all 0.3s ease-in-out;
                -o-transition: all 0.3s ease-in-out;
                transition: all 0.3s ease-in-out;
            }
            .joinin div {
                margin-bottom: 1rem;
            }
            .joinin-title {
                position: relative; 
                line-height: 1.375rem;
                color:#40401D;
                cursor: default;
            }
            .joinin-title h3 {
                text-align: center; 
                font-size: 0.975rem;
            }
            .joinin-title span {
                width: 1.375rem; 
                height: 1.375rem; 
                position: absolute; 
                right: 0.625rem; 
                top: 0;
                cursor: pointer;
            }
            .joinin input ,
            .checkcode span:nth-child(2){
                width: 8rem;
                height: 2.5rem;
                border: 1px solid #40401D;
                color: #40401D;
                border-radius: 5rem;
                font-size: 14px;
                padding: 10px 0 10px 16px;
            }
            .checkcode span:nth-child(2) {
                text-align: center;
                padding: 0;
                line-height: 2.5rem;
                color: #FB523D;
                border-color: #FB523D;
                cursor: pointer;
            }
            .joinin div:nth-child(1) {
                margin-top: 1rem;
            }
            .joinin div:nth-child(2) {
                text-align: center; 
            }
            .joinin div:nth-child(2) input {
                width: 17.5rem; 
            }
            .joinin div:nth-child(3) {
                overflow: hidden; 
                width: 17.5em; 
                margin: 0 auto 1rem; 
            }
            .joinin div:nth-child(3) a {
                width: 8rem; 
            }
            .joinin div:nth-child(3) > span {
                display: block; 
                float: left;
            }
            .joinin div:nth-child(3) > span:nth-child(2) {
                float: right;
            }
            .joinin div:nth-child(4) button {
                width: 17.5rem; 
                height: 2.75rem; 
                background: #ee4f4e; 
                color: white; 
                line-height: 2.74rem; 
                font-size: 1.125rem; 
                border: 0; 
                padding: 0; 
                outline: 0; 
                border-radius: 5rem; 
                margin: 0 auto; 
                display: block;
                cursor: pointer;
            }
            .dialog {
                position: fixed;
                width: 16.7143rem;
                min-height: 8.1676rem;
                background-color: #FAFAFA;
                top: 35%;
                left: 50%;
                z-index: 999;
                padding: 0.625rem 0;
                display: none;
                -webkit-transform: translateX(-50%) translateY(-50%);
                -moz-transform: translateX(-50%) translateY(-50%);
                -o-transform: translateX(-50%) translateY(-50%);
                transform: translateX(-50%) translateY(-50%);
            }
            .dialog img {
                width: 50%;
                margin: 0 auto;
            }
            .dialog .content {
                position: absolute;
                width: 100%;
                top: 50%;
                -webkit-transform: translateY(-50%);
                -moz-transform: translateY(-50%);
                -o-transform: translateY(-50%);
                transform: translateY(-50%);
            }
            .dialog .content h3 {
                color: #5B5B5B;
                font-size: 1.125rem;
                text-align: center;
            }
            .dialog .content p {
                color: #5B5B5B;
                margin-top: 0.5625rem;
                font-size: 0.9375rem;
            }
            .dialog .content p button {
                color: #EE4F4E;
                border: 1px solid #EE4F4E;
                border-radius: 3px;
                min-width: 8.4114rem;
                background-color: transparent;
                height: 2.3771rem;
                line-height: 2.3771rem;
                text-align: center;
            }
            .shade {
                position: fixed;
                width: 100%;
                height: 100%;
                top: 0;
                opacity: .6;
                background-color: #333;
                display: none;
            }
        </style>
    </head>
    <body>
        <section id="handleDownLoadBanner" style="display:none;">
            <a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.needapp.need">
                <img src="../resources/app/img/goupcheap/wechat_share_needbanner_bt@2x.png">
            </a>
        </section>
        <section>
            <img src="${picDomain}${cheap.cheapPicKey }" alt="goods" onclick="goGoods()"> 
        </section>
        <section class="goodsbrief unselect">
            <p>
               ${cheap.goodsBrief }
            </p>
            <img src="../resources/app/img/goupcheap/buyreason_img@2x.png"> 
        </section>
        <section>
            <img src="../resources/app/img/goupcheap/goupcheap_rule_img@2x.png"> 
        </section>
        <section class="explain">
            <div class="explain-title">团便宜说明</div>
            <div class="explain-desc">
                <img src="../resources/app/img/goupcheap/groupcheap_instrution_img@2x.png">
            </div>
        </section>
      <c:if test="${cheap.openCheapStatus=='CALL'||cheap.openCheapStatus=='PAY'||cheap.openCheapStatus=='NOTPAY'||cheap.openCheapStatus=='CLOSE'}">
        <section class="fixed-bottom-wrap">
            <div class="fixed-bottom">
                <div class="fixed-bottom-t clear">
                    <p class="fixed-bottom-t-title">
                        	已参团的小伙伴们
                    </p>
                    <ul class="fixed-bottom-t-cont">
                        <li>
                            <img src="${picDomain}${openUser.profilePicKey }">
                        </li>
                        <c:forEach items="${list }" var="user">
                            <c:if test="${!empty user.profilePicKey }">
                              <li>
                                  <img src="${user.profilePicKey }">
                              </li>
                            </c:if>
                            <c:if test="${empty user.profilePicKey}">
                              <li>
                                  <img src="../resources/app/img/goupcheap/default_heat_img@2x.png">
                              </li>
                            </c:if>
                        </c:forEach>
                        <c:forEach begin="1" end="${count }" step="1">
                            <li>
                                <img src="../resources/app/img/goupcheap/default_portrait_b_img@2x.png" >
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="fixed-bottom-b">
                    <div class="countdown">
                        <p>剩余时间:</p>
                        <p>
                          <span id="hour1" >0</span><span id="hour2" >0</span>:<span id="min1" >0</span><span id="min2" >0</span>:<span id="sec1" >0</span><span id="sec2">0</span>
                        </p>
                    </div>
                    <c:if test="${cheap.openCheapStatus=='CALL'}"> 
                        <button  onclick="callFriends()" class="cheapinfo-btn">呼朋唤友</button>
                    </c:if>
                    <c:if test="${cheap.openCheapStatus=='PAY'}">
                        <button onclick="backList()" class="cheapinfo-btn">已下单，看其他团</button>
                    </c:if>
                    <c:if test="${cheap.openCheapStatus=='NOTPAY'}">
                        <button onclick="goPay()" class="cheapinfo-btn">开团成功，去支付</button>
                    </c:if>
                    <c:if test="${cheap.openCheapStatus=='CLOSE'}">
                        <button onclick="backList()" class="cheapinfo-btn">团购结束，看其他团</button>
                    </c:if>
                </div>
            </div>
        </section>
       </c:if> 
      <c:if test="${cheap.openCheapStatus=='MYCLOSE'||cheap.openCheapStatus=='VALIDCLOSE'||cheap.openCheapStatus=='VALIDPAY'||cheap.openCheapStatus=='OPEN'||cheap.openCheapStatus=='SOLDOUT'}">
        <section class="fixed-bottom-wrap" style="height:3.4375rem;">
            <div class="fixed-bottom" style="height:3.4375rem;background-color: #fafafa;padding: .35rem 0 0 0;">
             	<c:if test="${cheap.openCheapStatus=='MYCLOSE'}">
                    <button class="cheapinfo-btn" style="background:#D3D3D3; float: none;margin: 0 auto;display: block;">已过期</button>
                </c:if>
                <c:if test="${cheap.openCheapStatus=='VALIDCLOSE'}">
                    <button class="cheapinfo-btn" style="background:#D3D3D3; float: none;margin: 0 auto;display: block;">已过期</button>
                </c:if>
                <c:if test="${cheap.openCheapStatus=='VALIDPAY'}" >
                    <button class="cheapinfo-btn" style="background:#D3D3D3; float: none;margin: 0 auto;display: block;">已下单</button>
                </c:if>
                <c:if test="${cheap.openCheapStatus=='OPEN'}">
                   <button onclick="openCheap()" class="cheapinfo-btn" style="margin: 0 auto;display: block;float: none;">我要开团</button>
                </c:if>
                <c:if test="${cheap.openCheapStatus=='SOLDOUT'}">
                   <button onclick="backList()" class="cheapinfo-btn" style="margin: 0 auto;display: block;float: none;">售罄了！</button>
                </c:if>
            </div>
        </section>
        </c:if>
        <section id="shade" class="shade"></section>
        <script type="text/javascript" src="../resources/app/js/auto_fontsize.js"></script>
        <script type="text/javascript" src="../resources/app/js/zepto.min.js"></script>
        <script>
        var checkSubmitFlg = false; 
        // 开团倒计时
        var startGroupTime = {
            cheapStatus: "${cheap.openCheapStatus}",
            curentTime: function(p){ 
                var t = new Date();
                var t_s = t.getTime();
                var nt = new Date();
                var plus = p*1000*60*60;
                nt.setTime(t_s+plus);
                return nt;
            },
            countTime: function(){
                var nowTime = new Date();
                var t = startGroupTime.endTime - nowTime.getTime();
                var h = (Math.floor(t/1000/60/60%24)).toString();
                if( h.length<2 ) h= "0"+h;
                var m = Math.floor(t/1000/60%60).toString();
                if( m.length<2 ) m= "0"+m;
                var s = Math.floor(t/1000%60).toString();
                if( s.length<2 ) s= "0"+s;
                document.getElementById("hour1").innerHTML = h[0];
                document.getElementById("hour2").innerHTML = h[1];
                document.getElementById("min1").innerHTML = m[0];
                document.getElementById("min2").innerHTML = m[1];
                document.getElementById("sec1").innerHTML = s[0];
                document.getElementById("sec2").innerHTML = s[1];
            },
            init: function(){
                startGroupTime.startTime = '${cheap.downTime}';
                startGroupTime.endTime = startGroupTime.curentTime( startGroupTime.startTime );
                //开团且时间大于0 开始倒计时
                if( startGroupTime.cheapStatus == "CALL"||startGroupTime.cheapStatus=='PAY'||startGroupTime.cheapStatus=='NOTPAY' ||startGroupTime.cheapStatus=='CLOSE'){
                    if( startGroupTime.startTime > 0 ){
                        var st = setInterval( startGroupTime.countTime, 1000 );
                    }
                }
            }
        };

        var handleDownLoadBanner = function(){
            var ua = window.navigator.userAgent.toLowerCase();
            if (ua.match(/need/i) == "need") {
                $('#handleDownLoadBanner').hide();
            } else {
                $('#handleDownLoadBanner').show();
            } 
        }
        $(document).ready(function(){
       	    // 初始化开团倒计时
            handleDownLoadBanner();
            startGroupTime.init();
        });
        //去开团； 
        function openCheap(){
        	if(!checkSubmitFlg){
        	 var userId="${userId}";
         	 var cheapNo="${cheap.cheapNo}";
         	 if(userId!=null&&userId!=""){	
                window.location.href="/need-marketing/cheap/openCheap?userId="+userId+"&cheapNo="+cheapNo;
                checkSubmitFlg=true;
         	 }else{
         	    window.location.href="com.needapp.app://need?gotoCheapGroup="+cheapNo;
         	    checkSubmitFlg=true;
         	 }
        	}
         }

         //去招呼好友
         function callFriends(){ 
        	var cheapOpenId='${cheap.cheapOpenId}';
        	window.location.href='com.needapp.app://need?shareGroup='+cheapOpenId;
         }
         
         //去支付
         function goPay(){
        	var cheapOpenId='${cheap.cheapOpenId}';
        	window.location.href='com.needapp.app://need?cheapGroupPay='+cheapOpenId;
         }
         
         //返回list
         
         function backList(){       	 
        	window.location.href='com.needapp.app://need?backList';
         }
         
         //跳转商品详情
         
        function goGoods(){
        	 //alert(111);
        	 window.location.href='com.needapp.app://need?goodsId';
        	 
         }
         
        </script>
    </body>
</html>