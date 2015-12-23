<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh">
	<head>
		<meta charset="UTF-8">
		<title>${userInfoVO.nickName}</title>
		<meta name="keywords" content="页面关键字" />
		<meta name="description" content="页面的描述" />
		<meta http-equiv="Cache-Control" content="no-cache" />
		<meta name="apple-mobile-web-app-title" content="Need H5">
		<meta name="format-detection" content="telephone=no">
		<!-- 强制让文档的宽度与设备的宽度保持1:1，并且文档最大的宽度比例是1.0，且不允许用户点击屏幕放大浏览； -->
		<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0"/>
		<!-- <meta name="viewport" content="initial-scale=0.5,maximum-scale=1.0,user-scalable=no" 
			  media="(device-height: 568px)"> -->
		<!-- ios 中Safari允许全屏浏览 -->
		<meta name="apple-mobile-web-app-capable" content="yes">
		<!-- ios 中Safari顶端状态条样式 -->
		<meta name="apple-mobile-web-app-status-bar-style" content="grey">
		<!-- ios/android 设备忽略将页面中的数字识别为电话号码 -->
		<meta content="telephone=no" name="format-detection" />
		<!-- android 设备禁止识别页面的邮箱地址 -->
		<meta content="email=no" name="format-detection" />
		<link rel="apple-touch-icon-precomposed" href="../app/images/common/logo_57x57.png">
		<link rel="apple-touch-icon-precomposed" sizes="114x114" href="../app/images/common/logo_114x114.png">
        <link rel="shortcut icon" href="../app/images/common/favicon.ico" type="image/x-icon" />
		<!-- 屏幕文字大小自适应大小 -->
		<script type="text/javascript" src="../app/script/auto_fontsize.js"></script>
		<script type="text/javascript" src="../app/script/lib/zepto/zepto.min.js"></script>
		
		<link rel="stylesheet" href="../app/css/normalize.css">
		<link rel="stylesheet" href="../app/css/main.css">
		<link rel="stylesheet" href="../app/css/expert.css">
	</head>
	<body>
		<header></header>
		<div class="content">
			<!-- 行家个人信息 -->
			<section class="expert-info clearfix">
				<div class="info-left">
					<div class="info-head-wrap">
						<img class="info-head" src="${picDomain}/${userInfoVO.profilePicKey}" alt="">
					</div>
					<div class="info-inner-wrap">
						<div class="info-name">
							${userInfoVO.nickName }
						</div>
						<div class="info-expert-classify">
							${userInfoVO.kolCategories }
						</div>
					</div>
					<div class="info-desc">
						${userInfoVO.kolBrief}
					</div>
				</div>
				<div class="info-right">
					<div class="info-pat-wrap">
						<p>
							<span class="info-num">${concernCount }</span>
							<span>关注</span>
						</p>
						<p>
							<span class="info-num">${fansCount}</span>
							<span>粉丝</span>
						</p>
					</div>
				</div>
			</section>
			<!-- 图片列表 -->
			<section class="imglist">
				<div class="imglist-title clearfix">
					<div class="active">
						<span>Need</span>
					</div>
					<div>
						<span>拥有</span>
					</div>
				</div>
				<div id="imglist-cont" class="imglist-cont clearfix">
					<div class="imglist-cont-l">
						<c:forEach items="${needList}" var="needGoods" step="2" >
							<div class="imglist-item">
								<div class="item-img-wrap">
									<img src="${picDomain}/${needGoods.needPicKey}" alt="">
								</div>
								<div class="item-goods-title">${needGoods.goodsName}</div>
								<div class="item-goods-price">
									<span class="now">¥${needGoods.discountPriceStr}</span>
									<span class="market">¥${needGoods.onsalePriceStr}</span>
								</div>
							</div>
						</c:forEach>
					</div>
					<div class="imglist-cont-r">
						<c:forEach items="${needList}" var="tradeGoods" step="2" begin="1">
							<div class="imglist-item">
								<div class="item-img-wrap">
									<img src="${picDomain}/${tradeGoods.needPicKey}" alt="">
								</div>
								<div class="item-goods-title">${tradeGoods.goodsName }</div>
								<div class="item-goods-price">
									<span class="now">¥${tradeGoods.discountPriceStr}</span>
									<span class="market">¥${tradeGoods.onsalePriceStr}</span>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
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