<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
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
        <title>购物成功，邀请好友领生活费</title>
        <link rel="stylesheet" type="text/css" href="/need-share/app/css/reset.css">
        <link rel="stylesheet" type="text/css" href="/need-share/app/css/coupons_v0.1.1.css">
        <script src="/need-share/app/script/baiduStatistics.js" type="text/javascript"></script>
        <script src="/need-share/app/script/jquery.js" type="text/javascript"></script>
    </head>

    <body>
        <div class="container">
            <header class="header">
                <img src="/need-share/app/images/coupon/share_coupon_640x680.jpg" alt="">
                <div class="coupons-detail">
                    <img src="/need-share/app/images/coupon/share_conpon_detail_640x261.png" alt="">
                    <h2>
                        <p>${value}元</p>
                        <span>生活费</span>
                    </h2>
                </div>
                <div class="coupons-quilt">
                    <img src="/need-share/app/images/coupon/share_coupon_quilt_640x299.png" alt="">
                </div>
            </header>
            <div class="content">
                <section class="des" style="display:${receive};">
                    <section class="inpt">
                        <form action="/need-share/coupon/login" method="get">
                            <p>
                                <input id="mobileInput" class="mobile-inpt" type="tel" placeholder="输入手机号领取优惠券" name="mobile">			
                                <input id="couponTemplateId" type="text" name="couponTemplateId" value="${couponTemplateId}" style="display:none;">
                                <input id="shareUserId" type="text" name="shareUserId"  value="${shareUserId}" style="display:none;">
                                <input id="tradeNo" type="text" name="tradeNo"  value="${tradeNo}" style="display:none;">
                            </p>
                            <p>
                                <button id="submitButton" type="submit" value="Submit" class="btn" >
                                    领取生活费
                                </button>
                            </p>
                        </form>
                    </section>
                </section>
                <section class="des" style="display:${received};">
                    <p>你抽到了<span>&nbsp;${value}元&nbsp;</span>生活费</p>
                    <p>已放入手机账号<span>&nbsp;${mobile}&nbsp;</span>中</p>
                    <p>使用该手机号登录即可使用</p>
                    <a id="download" class="btn">去 Need 使用</a>
                </section>
                <section class="partner">
                    <div class="tips-title">
                        <span></span>
                        <h5>领取红包的小伙伴们</h5>
                        <span></span>
                    </div>
                    <div class="partner-list">
                        <ul>
                            <c:forEach items="${receilveCouponUserList}" var="couponUser">
                                <li>
                                    <img src="${couponUser.iconUrl}">
                                    <p>${couponUser.nickName}<span>${couponUser.receiveDate}</span></p><span class="money">${couponUser.value}元</span>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </section>
            </div>
            <footer class="footer">
                <div class="tips-title">
                    <h5>使用规则</h5>
                    <span></span>
                </div>
                <div class="statement">
                    <p>1.领取成功后生活费将作为优惠券发送至领取时输入的手机号账号中，使用该手机登录和注册Need，即可使用优惠券；</p>
                    <p>2.生活费可以在Need App购买时抵用相应现金，具体规则请使用时查看券面描述，每张订单限使用一张优惠券；</p>
                    <p>3.Need App保留在法律允许的范围内对活动最终解释权；</p>
                </div>
            </footer>
        </div>
        <script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan style='display:none' id='cnzz_stat_icon_1256383882'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "w.cnzz.com/q_stat.php%3Fid%3D1256383882%26l%3D3' type='text/javascript'%3E%3C/script%3E"));</script>               
                        
        <script>
            $(function (){
                if(${code} != 200) {
                    alert('${message}');
                }
                $('#submitButton').click(function (){
                    var mobile = $('#mobileInput').val();
                    if(mobile.length <= 8) {
                        alert("请输入正确的手机号");
                    } else {
                        var couponTemplateId = $('#couponTemplateId').val();
                        var shareUserId = $('#shareUserId').val();
                        var tradeNo = $('#tradeNo').val();
                         $.ajax({
                             type: "GET",
                             url: "/need-share/coupon/login",
                             data:{"mobile":mobile,"couponTemplateId":couponTemplateId,"shareUserId":shareUserId,"tradeNo":tradeNo},
                             dataType: "JSONP",
                             error: function() {
                             },
                             success: function(data) {
                                 if(data.code === 200) {
                                     alert("您的生活费已到账，请登录Need App查看");
                                 } else {
                                     alert(data.msg);
                                 }
                             }
                         });
                     }
                });
                $('#download').click(function(){
                    var guide = "";
                    if (/ipad|iphone|mac/i.test(navigator.userAgent)){
                        //ios
                        guide = 1;
                    }else{
                        //android
                        guide = 2;
                    }
                    var ua = navigator.userAgent.toLowerCase();
                    if(ua.match(/MicroMessenger/i)=="micromessenger") {
                        $(this).attr('href','http://share.needapp.cn/need-share/mp.html');
                    }else {
                        if (guide == 1) {
                            $(this).attr('href','https://itunes.apple.com/cn/app/need-quan-shi-jie-dou-shi-ni-de/id1033089957?mt=8');
                        } else{
                            $(this).attr('href','http://share.needapp.cn/mp.html');
                        }
                    }
                });
            });
        </script>
    </body>
</html>