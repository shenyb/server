<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh">
	<head>
		<title>${goodsInfo.goodsName}</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="Cache-Control" content="no-cache" />
		<meta name="apple-mobile-web-app-title" content="Need H5">
		<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" name="viewport" />
		<!-- 强制让文档的宽度与设备的宽度保持1:1，并且文档最大的宽度比例是1.0，且不允许用户点击屏幕放大浏览； -->
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<!-- ios 中Safari允许全屏浏览 -->
		<meta name=" apple-mobile-web-app-status-bar-style" content="grey" />
		<!-- ios 中Safari顶端状态条样式 -->
		<meta name="format-detection" content="telephone=no"  />
		<!-- ios/android 设备忽略将页面中的数字识别为电话号码 -->
		<meta name="format-detection" content="email=no" />
		<!-- android 设备禁止识别页面的邮箱地址 -->
        <link rel="shortcut icon" href="../app/images/common/favicon.ico" type="image/x-icon" />
		<link rel="apple-touch-icon-precomposed" href="../images/common/logo_57x57.png">
		<link rel="apple-touch-icon-precomposed" sizes="114x114" href="../images/common/logo_114x114.png">

		<link rel="stylesheet" href="../app/css/normalize.css">
		<link rel="stylesheet" href="../app/css/main.css">
		<link rel="stylesheet" href="../app/css/goodsdetail.css">

		<script type="text/javascript" src="../app/script/auto_fontsize.js"></script>
	</head>
	<body>
		<header></header>
		<div class="content">
			<!-- 商品图轮播 -->
			<div class="primary-swiper">
				<div class="swiper-container">
					<div class="swiper-wrapper">
						<div class="swiper-slide">
							<img src="${picDomain}/${goodsInfo.scenePicKey}" alt="">
						</div>
					</div>
				</div>
			</div>
			<!-- 商品主要信息 -->
			<section class="primary-info">
				<div class="info-title">
					<h3>${goodsInfo.goodsName}</h3>
				</div>
				<div class="info-price">
					<span class="now">¥${goodsInfo.discountPriceStr} </span>
					<span class="market">¥${goodsInfo.onsalePriceStr} </span>
					<!-- <span class="discount">7折</span> -->				
				</div>
				<div class="info-note">
					<p>
						( 本商品不支持港澳台，西藏，新疆地区配送 )
					</p>
				</div>
				<div class="info-desc">
					<p>
						${goodsInfo.brief}
					</p>
				</div>
			</section>
			<!-- 分割线 -->
			<div class="mobile_line_tips"></div>
			<!-- 商品评论 -->
			<section class="comment">
				<div class="comment-head">
					<a href="javascript:;">
						累计评价 (${total})
					</a>
				</div>
				<div class="comment-list">
					<c:if test="${total!=0}">
						<c:forEach  items="${judeList}"  var="judgement" >				
							<div class="list-item clearfix">
								<div class="item-head-wrap">
									<img src="${picDomain}/${judgement.profilePicKey }">
								</div>
								<div class="item-cont">
									<p class="item-cont-n">
									${judgement.userName }
									</p>
									<p class="item-cont-c">
									${judgement.content }
									</p>
									<p class="item-cont-t">					  
									    <fmt:formatDate value="${judgement.dateJudgementTime}" pattern="yyyy-MM-dd  HH:mm:ss" />   
									</p>
								</div>
							</div>
						</c:forEach>
					</c:if>
					<c:if test="${total==0}">
					  <div class="no-comment" >暂无评论!</div>				   
					</c:if>
				</div>
			</section>
			<!-- 分割线 -->
			<div class="mobile_line_tips"></div>
			<!-- 商品详情 -->
			<div class="comment-head">
				<a href="javascript:;">
					商品详情图
				</a>
			</div>
			<section class="detail-goods-images">
				<c:forEach items="${goodsInfo.detailPicKeys}" var="pic">
					<div class="goods-image">
						<img src="${picDomain}/${pic}">
					</div>
				</c:forEach>
			</section>
			<!-- 分割线 -->
			<div class="mobile_line_tips" style="margin-bottom: 2.59489302965942rem"></div>
			<!-- APP底部固定工具条 -->
			<div class="footer-bar">
				<a id="downloadlink" href="/need-share/mp.html">
					<img src="../app/images/common/share-footer-all-1500x170.jpg" alt="">
				</a>
			</div>
		</div>
		<footer></footer>
	</body>
</html>