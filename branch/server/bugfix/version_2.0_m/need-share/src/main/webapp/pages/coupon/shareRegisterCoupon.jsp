<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head lang="zh-cmn">
		<meta charset="utf-8">
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">  <meta content="portrait" name="x5-orientation">
		<meta content="true" name="x5-fullscreen">
		<meta content="portrait" name="screen-orientation">
		<meta content="yes" name="full-screen">
		<meta content="webkit" name="renderer">
		<meta content="IE=Edge" http-equiv="X-UA-Compatible">
		<meta content="no-siteapp" http-equiv="Cache-Control">
		<meta content="网页描述" name="description"> 
		<meta content="网页关键词" name="keywords">
		<link rel="stylesheet" href="../../app/css/normalize.css">
		<link rel="stylesheet" href="../../app/css/main.css">
		<script type="text/javascript" src="../../app/script/auto_fontsize.js"></script>
        <script src="/need-share/app/script/jquery.js" type="text/javascript"></script>
        <script src="/need-share/app/script/baiduStatistics.js" type="text/javascript"></script>
		<title>邀请好友赚取生活费</title>
		<style>
			html {
				height: 100%;
			}
			body {
				position: relative;
				width: 100%;
				height: 100%;
				background-color: #ffffff;
			}
			img {
				display: block;
				width: 100%;
			}
			.content {
				position: absolute;
				width: 100%;
				height: 100%;
				max-width: 750px;
				margin: 0 auto;
				overflow-y: auto;
			}
			.header {
				height: 10.5rem;
				position: relative;
			}
			.desc {
				padding: 0 1.25rem;
			}
			.desc p {
				font-size: 0.875rem;
				text-align: center;
				color: #5B5B5B;
			}
			.pic-wrap {
				width: 13.125rem;
				position: absolute;
				top: 50%;
				left: 50%;
			    -webkit-transform: translateX(-50%) translateY(-50%);
				-moz-transform: translateX(-50%) translateY(-50%);
				-o-transform: translateX(-50%) translateY(-50%);
				transform: translateX(-50%) translateY(-50%);
			}
			.background {
				position: absolute;
				top: 0;
				z-index: -1;
			}
			.inpt p {
				text-align: center;
			}
			.inpt-i {
				width: 18.75rem;
				height: 3.125rem;
				background-color: transparent;
				border: 1px solid #979797;
				text-align: center;
			}
			.inpt-b {
				width: 7.6875rem;
				height: 2.125rem;
				background-color: #F17E87;
				border: 0;
				font-size: 1.125rem;
				color: #ffffff;
				border-radius: 0.375rem;
				outline: 0;
				vertical-align: middle;
			}
			.list {
				margin-top: 2.4375rem;
			}
			.list .title {
				border-bottom: 1px solid #D8D8D8;
			}
			.list .title span {
				position: absolute;
				color: #9F9F9F;
				font-size: 1rem;
				margin-top: -0.6rem;
			    padding: 0 0.625rem;
			    left: 50%;
			    -webkit-transform: translateX(-50%);
				-moz-transform: translateX(-50%);
				-o-transform: translateX(-50%);
				transform: translateX(-50%);
			}
			.list .item {
				margin-top: 2rem;
				padding: 0 1.5625rem;
			}
			.list .item .l {
				width: 2.5rem;
				height: 2.5rem;
				border-radius: 100%;
				overflow: hidden;
				float: left;
				margin-right: 0.75rem;
			}
			.list .item .m {
				float: left;
			}
			.list .item .m p {
				margin: 0;
			}
			.list .item .m p .name {
				font-size: 0.875rem;
				color: #2A2A2A;
				margin-right: 0.875rem;
			}
			.list .item .m p .date {
				font-size: 0.6875rem;
				color: #C5C5C5;
			}
			.list .item .r {
				float: right;
			}
			.list .item .r span {
				color: #EE4F4E;
				font-size: 1.125rem;
				line-height: 2.5rem;
			}
			.list .item .reply {
				color: #7B7B7B;
				font-size: 0.875rem;
			}
			.footer {
				margin-top: 2.6875rem;
				margin-bottom: 2.6875rem;
				padding: 0 1.5625rem;
			}
			.footer p {
				margin: 0;
				color: #5B5B5B;
			}
		</style>
	</head>
	<body>
		<div class="background">
			<img src="../../app/images/coupon/share_bg@2x.jpg" alt="">
		</div>
		<div class="content">
			<section class="header">
				<div class="pic-wrap">
					<img src="../../app/images/coupon/share_guster_giftbox_img@2x.png" alt="">
				</div>
			</section>
			<section class="desc">
				<p>
					好友注册Need且完成首次购物，双方即可获得${value}元生活费！
				</p>
			</section>
			<section class="input">
                    <section class="inpt">
                        <p>
                            <input id="mobileInput" class="inpt-i" type="number" placeholder="输入手机号领取优惠券" name="mobile">			
                            <input id="couponTemplateId" type="text" name="couponTemplateId" value="${couponTemplateId}" style="display:none;"> 
                            <input id="shareUserId" type="text" name="shareUserId"  value="${shareUserId}" style="display:none;"> 						
                        </p>
                        <p>
                            <button id="submitButton" class="inpt-b" >
                                领取
                            </button>
                        </p>
                    </section>
			</section>
			<!-- <section class="background">
				<img src="../images/coupon/share_bg@2x.jpg" alt="">
			</section> -->
			<section class="list">
				<div class="title">
					<span>领取红包的小伙伴</span>
				</div>
				
				<c:forEach items="${receilveCouponUserList}" var="couponUser">
					<div class="item clearfix">
						<div class="l">
							<img src="${couponUser.profilePicKey}" alt="">
						</div>
						<div class="m">
							<p>
								<span class="name">${couponUser.nickName}</span>
								<span class="date">${couponUser.receiveDate}</span>
							</p>
							<p class="reply">
								<span class="mobile">${couponUser.mobile}</span>
							</p>
						</div>
						<div class="r">
							<span> ${couponUser.value}元</span>
						</div>
					</div>
				</c:forEach>
				
			</section>
			<section class="footer">
				<p>
					Need生活费说明：
				</p>
				<p>
					1.每个用户可获得10次推荐有礼奖励；
				</p>
				<p>
					2.${value}元生活费（满${minCost}元可用）可登陆Need App“我的-设置-我的生活费”查看，逾期作废；
				</p>
				<p>
					3.单笔订单仅限一张券，不能与其他优惠叠加使用，且不找零
				</p>
				<p>
					4.Need生活费仅限于在1.1版本客户端使用
				</p>
			</section>
		</div>
        <script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan style='display:none' id='cnzz_stat_icon_1256383882'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "w.cnzz.com/q_stat.php%3Fid%3D1256383882%26l%3D3' type='text/javascript'%3E%3C/script%3E"));</script>               
                        
        <script>
            $(function (){
                $('#submitButton').click(function (){
                    var mobile = $('#mobileInput').val();
                    var couponTemplateId = $('#couponTemplateId').val();
                    var shareUserId = $('#shareUserId').val();
                	 $.ajax({
                         type: "POST",
                         url:"/need-share/coupon/receive",
                         data:{"mobile":mobile,"couponTemplateId":couponTemplateId,"shareUserId":shareUserId},
                         dataType: "json",
                         error: function() {
                             alert("Connection error");
                         },
                         success: function(data) {
                             if(data.code === 200) {
                                 alert("您的生活费已到账，请登录Need App查看");
                             } else {
                                 alert(data.msg);
                             }
                         }
                     });
                });
            });
        </script>
	</body>
</html>
