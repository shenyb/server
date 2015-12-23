<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!-- IE=edge告诉IE使用最新的引擎渲染网页 -->
<!-- chrome=1则可以激活Chrome Frame , Chrome Frame可以让旧版IE浏览器使用Chrome的WebKit渲染引擎处理网页 -->
<meta
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0"
	name="viewport">
<!-- 强制让文档的宽度与设备的宽度保持1:1，并且文档最大的宽度比例是1.0，且不允许用户点击屏幕放大浏览 -->

<meta content="yes" name="apple-mobile-web-app-capable" />
<!-- iphone设备中的safari私有meta标签，它表示：允许全屏模式浏览 -->

<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<!-- iphone的私有标签，它指定的iphone中safari顶端的状态条的样式 -->

<meta content="telephone=no" name="format-detection" />
<!-- 告诉设备忽略将页面中的数字识别为电话号码 -->

<meta content="email=no" name="format-detection" />
<!-- android设备忽略将页面中的email识别为电子邮箱 -->

<link rel="shortcut icon" href="/resources/img/common/favicon.ico"
	type="image/x-icon" />

<title>OPS管理系统</title>

<!-- 主体部分样式表 -->
<link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/bootstrap/button.css">
<link rel="stylesheet" href="/resources/css/font-awesome.min.css">
<link rel="stylesheet" href="/resources/css/layout.css">
<link rel="stylesheet" href="/resources/css/ops.css">
<link rel="stylesheet" href="/resources/css/sidebar.css">
<link rel="stylesheet" href="/resources/css/content-header.css">
<!-- <link rel="stylesheet" href="/resources/css/datapage.css">
<link rel="stylesheet" href="/resources/css/form.css"> -->
<link rel="stylesheet" href="/resources/css/pagination.css">
<link rel="stylesheet" href="/resources/css/profile-table.css">
<link rel="stylesheet" href="/resources/css/modal.css" >
<!-- 分页插件 css 样式 -->
<!-- 此部分注释内容兼容IE低版本 H5相关 **不要删除**-->
<!--[if lt IE 9]>
          <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
</head>

<body>
	<section class="layout-wrap">
		<div class="layout-left">
			<%@ include file="/resources/html/sidebar.jsp"%>
		</div>
		<div class="layout-right">
			<div class="layout-right-inner">
				<div class="layout-right-top">
					<%@ include file="/resources/html/content_header.jsp"%>
				</div>
				<div class="layout-right-bottom">
					<!-- 
                            这部分写各自页面对应的内容
                            注意锁紧格式
                            范围 start -
                         -->
                 
					<div class="form-page-wrap">
						<div class="data-page-inner">
							<div class="page-head">								
								<div class="state-information">
									<ol class="breadcrumb m-b-less bg-less">
										<li><a href="#">主页</a></li>
										<li><a href="#">采销管理</a></li>
										<li class="active">商品编辑</li>
									</ol>
								</div>
							</div>
							<div class="info">
					            <div class="info-top">
					                <span>商品概要</span>
					                <div class="back">
					                    <a href="/goods/page?page=${page}" class="button button-rounded button-tiny">返回</a>				                 			                   
					                </div>
					            </div>
				           		<form class="form-horizontal" id="goodsEditProfile">
									<table class="table table-bordered table-hover table-condensed table-responsive">
										<tr>
					                        <td>商品编号</td>
					                        <td>
					                        	<input type="text" name="goodsCode" class="form-control" value="${goods.goodsCode}" readonly="readonly" digits="true" min="0" required>
												<input type="hidden" name="goodsId" value="${goods.goodsId}" class="form-control">
												<input type="hidden" id="hiddenGoodsTopPicLength" value="${goodsTopPicLength}" class="form-control"/>
												<input type="hidden" id="hiddenGoodsDetailPicLength" value="${goodsDetailPicLength}" class="form-control"/>
												<input type="hidden" id="hiddenPicAddress" value="${picAddress}" class="form-control"/>
												<input type="hidden" id="hiddenPage" value="${page}" class="form-control"/>
											</td>
					                        <td>国际条形码</td>
					                        <td>
					                        	<input type="text" class="form-control" value="${goods.goodsBarcode}" name="goodsBarcode" required readonly="readonly">
					                        </td>                       
					                    </tr>
					                    <!-- <tr>
											<td>是否为保税仓</td>
											<td>
												<select class="form-control" name="" id="isGlobal">
													<option value="TRUE">是</option>
													<option value="FALSE">否</option>
												</select>
											</td>
										</tr> -->
										<tr>
					                        <td>商品分类</td>
					                        <td colspan="3">
					                        <div class="col-xs-3">
					                        	<select class="form-control" id="oneGoodsCategoryId" disabled="true">
													<c:forEach items="${goodsCategoryoneList}"
														var="goodsCategory" varStatus="status">
														<option value="${goodsCategory.categoryId}"  <c:if test='${goodsCategory.categoryId == goods.goodsCategoryIdOne}'> selected </c:if> >${goodsCategory.categoryName}</option>
													</c:forEach>
												</select>
											</div>
												<div class="col-xs-3">
											
												<select class="form-control" id="twogoodsCategoryId" disabled="true">
													<c:forEach items="${goodsCategorytwoList}"
														var="goodsCategory" varStatus="status">
														<option value="${goodsCategory.categoryId}" <c:if test='${goodsCategory.categoryId == goods.goodsCategoryIdTwo}'> selected </c:if>>${goodsCategory.categoryName}</option>
													</c:forEach>								
												</select>
												</div>
												<div class="col-xs-3">
												<select class="form-control" name="goodsCategoryId" id="threeGoodsCategoryId" disabled="true">
													<c:forEach items="${goodsCategorythreeList}"
														var="goodsCategory" varStatus="status">
														<option value="${goodsCategory.categoryId}" <c:if test='${goodsCategory.categoryId == goods.goodsCategoryId}'> selected </c:if>>${goodsCategory.categoryName}</option>
													</c:forEach>
												</select>	
												</div>										
											</td>					                                              
					                    </tr>
					                    <tr>
					                        <td>商品品牌</td>
					                        <td>
					                        	<select class="form-control" name="brandId" id="brandId" disabled="true">
													<c:forEach items="${brandList}"
														var="goodsBrand" varStatus="status">
														<option value="${goodsBrand.brandId}" <c:if test='${goodsBrand.brandId == goods.brandId}'> selected </c:if>>${goodsBrand.brandName}</option>
													</c:forEach>
												</select>
					                        </td>
					                        <td>是否有效期管理</td>
					                        <td>
					                        	<div class="form-group col-xs-6">
													<input name="isValidDate" type="radio" value="TRUE" 
													<c:if test="${goods.isValidDate=='TRUE'}">checked='checked'</c:if>  disabled="disabled"/>是
                                                   	<input name="isValidDate" type="radio" value="FALSE" 
                                                   	<c:if test="${goods.isValidDate=='FALSE'}">checked='checked'</c:if>  disabled="disabled" />否
												</div>															
					                        </td>
					                    </tr>
					                    <tr>	
					                    	<td id="validDate">有效期</td>
					                        <td>
					                       		${goods.validDate} 月
					                        </td>							                       
					                        <td>是否赠品</td>
					                        <td>									                        	
												<input name="isGift" type="radio" value="TRUE" 
												<c:if test="${goods.isGift=='TRUE'}">checked='checked'</c:if>  disabled="disabled"/>是 
                                                <input name="isGift" type="radio" value="FALSE" 
                                                <c:if test="${goods.isGift=='FALSE'}">checked='checked'</c:if>  disabled="disabled" />否														
					                        </td>                        
					                    </tr>
					                    <tr style="display: none;">
											<td>是否单品</td>
											<td>
												<input name="goodsType" type="radio" value="SINGLE" value="TRUE" 
												<c:if test="${goods.goodsType=='SINGLE'}">checked='checked'</c:if> disabled="disabled"/>单品 
                                                <input name="goodsType" type="radio" value="MORE" value="TRUE" 
                                                <c:if test="${goods.goodsType=='MORE'}">checked='checked'</c:if> disabled="disabled"/>多品
											</td>
										</tr>
										<tr>
											 <td>仓库类型</td>
						                        <td>
						                        	<select  class="form-control" name="warehouseType" id="warehouseType" onChange="chageWarehouseType()"  disabled="disabled">
																<!-- <option value="">仓库类型</option> -->
								    							<option value="SELF_WAREHOUSE">自营仓</option>
								    							<option value="TAX_WAREHOUSE">保税仓</option>
								    							<option value="OVERSEA_WAREHOUSE">海外直邮</option>
													</select>														
						                        </td>						                     	 
										</tr> 
										<tr class="huanguanGuojian">
						                    	<td>海关计量单位</td>
						                        <td>
						                        	<select name="haiguanCount" class="form-control" ng-model="addData.haiguanCount"  disabled="disabled">
														<option value="006" <c:if test='${goods.haiguanCount== 006}'> selected </c:if>>套</option>
														<option value="007" <c:if test='${goods.haiguanCount== 007}'> selected </c:if>>个</option>
														<option value="008" <c:if test='${goods.haiguanCount== 008}'> selected </c:if>>只</option>
														<option value="010" <c:if test='${goods.haiguanCount== 010}'> selected </c:if>>张</option>
														<option value="011" <c:if test='${goods.haiguanCount== 011}'> selected </c:if>>件</option>
														<option value="012" <c:if test='${goods.haiguanCount== 012}'> selected </c:if>>支</option>
														<option value="014" <c:if test='${goods.haiguanCount== 014}'> selected </c:if>>根</option>
														<option value="015" <c:if test='${goods.haiguanCount== 015}'> selected </c:if>>条</option>
														<option value="017" <c:if test='${goods.haiguanCount== 017}'> selected </c:if>>块</option>
														<option value="018" <c:if test='${goods.haiguanCount== 018}'> selected </c:if>>卷</option>
														<option value="019" <c:if test='${goods.haiguanCount== 019}'> selected </c:if>>副</option>
														<option value="020" <c:if test='${goods.haiguanCount== 020}'> selected </c:if>>片</option>
														<option value="021" <c:if test='${goods.haiguanCount== 021}'> selected </c:if>>组</option>
														<option value="022" <c:if test='${goods.haiguanCount== 022}'> selected </c:if>>份</option>
														<option value="025" <c:if test='${goods.haiguanCount== 025}'> selected </c:if>>双</option>
														<option value="026" <c:if test='${goods.haiguanCount== 026}'> selected </c:if>>对</option>
														<option value="035" <c:if test='${goods.haiguanCount== 035}'> selected </c:if>>千克</option>
														<option value="036" <c:if test='${goods.haiguanCount== 036}'> selected </c:if>>克</option>
														<option value="095" <c:if test='${goods.haiguanCount== 095}'> selected </c:if>>升</option>
														<option value="096" <c:if test='${goods.haiguanCount== 096}'> selected </c:if>>毫升</option>
														<option value="120" <c:if test='${goods.haiguanCount== 120}'> selected </c:if>>箱</option>
														<option value="121" <c:if test='${goods.haiguanCount== 121}'> selected </c:if>>批</option>
														<option value="122" <c:if test='${goods.haiguanCount== 122}'> selected </c:if>>罐</option>
														<option value="123" <c:if test='${goods.haiguanCount== 123}'> selected </c:if>>桶</option>
														<option value="125" <c:if test='${goods.haiguanCount== 125}'> selected </c:if>>包</option>
														<option value="134" <c:if test='${goods.haiguanCount== 134}'> selected </c:if>>枚</option>
														<option value="136" <c:if test='${goods.haiguanCount== 136}'> selected </c:if>>袋</option>
														<option value="139" <c:if test='${goods.haiguanCount== 139}'> selected </c:if>>粒</option>
														<option value="140" <c:if test='${goods.haiguanCount== 140}'> selected </c:if>>盒</option>
														<option value="141" <c:if test='${goods.haiguanCount== 141}'> selected </c:if>>合</option>
														<option value="142" <c:if test='${goods.haiguanCount== 142}'> selected </c:if>>瓶</option>
													</select>
						                        </td>
						                        
							                         <td>国检计量单位</td>
							                        <td>
							                        	<select name="guojianCount" class="form-control" id="" disabled="disabled">                                                           
															<option value="006"<c:if test='${goods.guojianCount == "006"}'> selected </c:if>>套</option>
															<option value="007"<c:if test='${goods.guojianCount == "007"}'> selected </c:if>>个</option>
															<option value="008"<c:if test='${goods.guojianCount == "008"}'> selected </c:if>>只</option>
															<option value="010"<c:if test='${goods.guojianCount == "010"}'> selected </c:if>>张</option>
															<option value="011"<c:if test='${goods.guojianCount == "011"}'> selected </c:if>>件</option>
															<option value="012"<c:if test='${goods.guojianCount == "012"}'> selected </c:if>>支</option>
															<option value="014"<c:if test='${goods.guojianCount == "014"}'> selected </c:if>>根</option>
															<option value="015"<c:if test='${goods.guojianCount == "015"}'> selected </c:if>>条</option>
															<option value="017"<c:if test='${goods.guojianCount == "017"}'> selected </c:if>>块</option>
															<option value="018"<c:if test='${goods.guojianCount == "018"}'> selected </c:if>>卷</option>
															<option value="019"<c:if test='${goods.guojianCount == "019"}'> selected </c:if>>副</option>
															<option value="020"<c:if test='${goods.guojianCount == "020"}'> selected </c:if>>片</option>
															<option value="021"<c:if test='${goods.guojianCount == "021"}'> selected </c:if>>组</option>
															<option value="022"<c:if test='${goods.guojianCount == "022"}'> selected </c:if>>份</option>
															<option value="025"<c:if test='${goods.guojianCount == "025"}'> selected </c:if>>双</option>
															<option value="026"<c:if test='${goods.guojianCount == "026"}'> selected </c:if>>对</option>
															<option value="122"<c:if test='${goods.guojianCount == "122"}'> selected </c:if>>罐</option>
															<option value="123"<c:if test='${goods.guojianCount == "123"}'> selected </c:if>>桶</option>
															<option value="125"<c:if test='${goods.guojianCount == "125"}'> selected </c:if>>包</option>
															<option value="136"<c:if test='${goods.guojianCount == "136"}'> selected </c:if>>袋</option>
															<option value="139"<c:if test='${goods.guojianCount == "139"}'> selected </c:if>>粒</option>
															<option value="140"<c:if test='${goods.guojianCount == "140"}'> selected </c:if>>盒</option>
															<option value="141"<c:if test='${goods.guojianCount == "141"}'> selected </c:if>>合</option>
															<option value="142"<c:if test='${goods.guojianCount == "142"}'> selected </c:if>>瓶</option>
															<option value="503"<c:if test='${goods.guojianCount == "503"}'> selected </c:if>>枚</option>
														</select> 
							                        </td> 
							                    </tr>
						                     <tr class="huanguanGuojian">
						                    	<td>海关国家编码</td>
						                        <td>
						                        	<select name="haiguanCountryCode" class="form-control"  ng-model="addData.haiguanCountryCode"  disabled="disabled">
														<option value="110" <c:if test='${goods.haiguanCountryCode == "110"}'> selected </c:if>>中国香港</option>
														<option value="116" <c:if test='${goods.haiguanCountryCode == "116"}'> selected </c:if>>日本</option>
														<option value="121" <c:if test='${goods.haiguanCountryCode == "121"}'> selected </c:if>>中国澳门</option>
														<option value="122" <c:if test='${goods.haiguanCountryCode == "122"}'> selected </c:if>>马来西亚</option>
														<option value="125" <c:if test='${goods.haiguanCountryCode == "125"}'> selected </c:if>>尼泊尔</option>
														<option value="132" <c:if test='${goods.haiguanCountryCode == "132"}'> selected </c:if>>新加坡</option>
														<option value="133" <c:if test='${goods.haiguanCountryCode == "133"}'> selected </c:if>>韩国</option>
														<option value="136" <c:if test='${goods.haiguanCountryCode == "136"}'> selected </c:if>>泰国</option>
														<option value="141" <c:if test='${goods.haiguanCountryCode == "141"}'> selected </c:if>>越南</option>
														<option value="142" <c:if test='${goods.haiguanCountryCode == "142"}'> selected </c:if>>中国</option>
														<option value="143" <c:if test='${goods.haiguanCountryCode == "143"}'> selected </c:if>>台澎金马关税区</option>
														<option value="301" <c:if test='${goods.haiguanCountryCode == "301"}'> selected </c:if>>比利时</option>
														<option value="302" <c:if test='${goods.haiguanCountryCode == "302"}'> selected </c:if>>丹麦</option>
														<option value="303" <c:if test='${goods.haiguanCountryCode == "303"}'> selected </c:if>>英国</option>
														<option value="304" <c:if test='${goods.haiguanCountryCode == "304"}'> selected </c:if>>德国</option>
														<option value="305" <c:if test='${goods.haiguanCountryCode == "305"}'> selected </c:if>>法国</option>
														<option value="306" <c:if test='${goods.haiguanCountryCode == "306"}'> selected </c:if>>爱尔兰</option>
														<option value="309" <c:if test='${goods.haiguanCountryCode == "309"}'> selected </c:if>>荷兰</option>
														<option value="310" <c:if test='${goods.haiguanCountryCode == "310"}'> selected </c:if>>希腊</option>
														<option value="311" <c:if test='${goods.haiguanCountryCode == "311"}'> selected </c:if>>葡萄牙</option>
														<option value="312" <c:if test='${goods.haiguanCountryCode == "312"}'> selected </c:if>>西班牙</option>
														<option value="315" <c:if test='${goods.haiguanCountryCode == "315"}'> selected </c:if>>奥地利</option>
														<option value="318" <c:if test='${goods.haiguanCountryCode == "318"}'> selected </c:if>>芬兰</option>
														<option value="326" <c:if test='${goods.haiguanCountryCode == "326"}'> selected </c:if>>挪威</option>
														<option value="327" <c:if test='${goods.haiguanCountryCode == "327"}'> selected </c:if>>波兰</option>
														<option value="330" <c:if test='${goods.haiguanCountryCode == "330"}'> selected </c:if>>瑞典</option>
														<option value="331" <c:if test='${goods.haiguanCountryCode == "331"}'> selected </c:if>>瑞士</option>
														<option value="410" <c:if test='${goods.haiguanCountryCode == "410"}'> selected </c:if>>巴西</option>
														<option value="429" <c:if test='${goods.haiguanCountryCode == "429"}'> selected </c:if>>墨西哥</option>
														<option value="501" <c:if test='${goods.haiguanCountryCode == "501"}'> selected </c:if>>加拿大</option>
														<option value="502" <c:if test='${goods.haiguanCountryCode == "502"}'> selected </c:if>>美国</option>
														<option value="601" <c:if test='${goods.haiguanCountryCode == "601"}'> selected </c:if>>澳大利亚</option>
														<option value="609" <c:if test='${goods.haiguanCountryCode == "609"}'> selected </c:if>>新西兰</option>
														<option value="701" <c:if test='${goods.haiguanCountryCode == "701"}'> selected </c:if>>国(地)别不详的</option>
														<option value="702" <c:if test='${goods.haiguanCountryCode == "702"}'> selected </c:if>>联合国及机构和国际组织</option>
														<option value="999" <c:if test='${goods.haiguanCountryCode == "999"}'> selected </c:if>>中性包装原产级别</option>
													</select> 
						                        </td>
						                        
						                         <td>国检国家编码</td>
						                        <td>
						                        	<select name="guojianCountryCode" class="form-control" ng-model="addData.guojianCountryCode"  disabled="disabled">
														<option value="36"<c:if test='${goods.guojianCountryCode == "36"}'> selected </c:if>>澳大利亚</option>
														<option value="40"<c:if test='${goods.guojianCountryCode == "40"}'> selected </c:if>>奥地利</option>
														<option value="56"<c:if test='${goods.guojianCountryCode == "56"}'> selected </c:if>>比利时</option>
														<option value="76"<c:if test='${goods.guojianCountryCode == "76"}'> selected </c:if>>巴西</option>
														<option value="100"<c:if test='${goods.guojianCountryCode == "100"}'> selected </c:if>>保加利亚</option>
														<option value="124"<c:if test='${goods.guojianCountryCode == "124"}'> selected </c:if>>加拿大</option>
														<option value="152"<c:if test='${goods.guojianCountryCode == "152"}'> selected </c:if>>智利</option>
														<option value="156"<c:if test='${goods.guojianCountryCode == "156"}'> selected </c:if>>中国</option>
														<option value="158"<c:if test='${goods.guojianCountryCode == "158"}'> selected </c:if>>中国台湾</option>
														<option value="170"<c:if test='${goods.guojianCountryCode == "170"}'> selected </c:if>>哥伦比亚</option>
														<option value="208"<c:if test='${goods.guojianCountryCode == "208"}'> selected </c:if>>丹麦</option>
														<option value="246"<c:if test='${goods.guojianCountryCode == "246"}'> selected </c:if>>芬兰</option>
														<option value="250"<c:if test='${goods.guojianCountryCode == "250"}'> selected </c:if>>法国</option>
														<option value="276"<c:if test='${goods.guojianCountryCode == "276"}'> selected </c:if>>德国</option>
														<option value="300"<c:if test='${goods.guojianCountryCode == "300"}'> selected </c:if>>希腊</option>
														<option value="344"<c:if test='${goods.guojianCountryCode == "344"}'> selected </c:if>>中国香港</option>
														<option value="356"<c:if test='${goods.guojianCountryCode == "356"}'> selected </c:if>>印度</option>
														<option value="372"<c:if test='${goods.guojianCountryCode == "372"}'> selected </c:if>>爱尔兰</option>
														<option value="380"<c:if test='${goods.guojianCountryCode == "380"}'> selected </c:if>>意大利</option>
														<option value="392"<c:if test='${goods.guojianCountryCode == "392"}'> selected </c:if>>日本</option>
														<option value="410"<c:if test='${goods.guojianCountryCode == "410"}'> selected </c:if>>韩国</option>
														<option value="446"<c:if test='${goods.guojianCountryCode == "446"}'> selected </c:if>>中国澳门</option>
														<option value="458"<c:if test='${goods.guojianCountryCode == "458"}'> selected </c:if>>马来西亚</option>
														<option value="484"<c:if test='${goods.guojianCountryCode == "484"}'> selected </c:if>>墨西哥</option>
														<option value="528"<c:if test='${goods.guojianCountryCode == "528"}'> selected </c:if>>荷兰</option>
														<option value="554"<c:if test='${goods.guojianCountryCode == "554"}'> selected </c:if>>新西兰</option>
														<option value="578"<c:if test='${goods.guojianCountryCode == "578"}'> selected </c:if>>挪威</option>
														<option value="643"<c:if test='${goods.guojianCountryCode == "643"}'> selected </c:if>>俄罗斯</option>
														<option value="702"<c:if test='${goods.guojianCountryCode == "702"}'> selected </c:if>>新加坡</option>
														<option value="704"<c:if test='${goods.guojianCountryCode == "704"}'> selected </c:if>>越南</option>
														<option value="710"<c:if test='${goods.guojianCountryCode == "710"}'> selected </c:if>>南非</option>
														<option value="724"<c:if test='${goods.guojianCountryCode == "724"}'> selected </c:if>>西班牙</option>
														<option value="752"<c:if test='${goods.guojianCountryCode == "752"}'> selected </c:if>>瑞典</option>
														<option value="756"<c:if test='${goods.guojianCountryCode == "756"}'> selected </c:if>>瑞士</option>
														<option value="764"<c:if test='${goods.guojianCountryCode == "764"}'> selected </c:if>>泰国</option>
														<option value="826"<c:if test='${goods.guojianCountryCode == "826"}'> selected </c:if>>英国</option>
														<option value="840"<c:if test='${goods.guojianCountryCode == "840"}'> selected </c:if>>美国</option>
														<option value="903"<c:if test='${goods.guojianCountryCode == "903"}'> selected </c:if>>亚洲</option>
														<option value="906"<c:if test='${goods.guojianCountryCode == "906"}'> selected </c:if>>非洲</option>
														<option value="909"<c:if test='${goods.guojianCountryCode == "909"}'> selected </c:if>>欧洲</option>
														<option value="991"<c:if test='${goods.guojianCountryCode == "991"}'> selected </c:if>>保税区</option>
														<option value="992"<c:if test='${goods.guojianCountryCode == "992"}'> selected </c:if>>未列出的国家或地区</option>
														<option value="0"<c:if test='${goods.guojianCountryCode == "0"}'> selected </c:if>>有国家地区</option>
													</select> 
						                        </td> 
						                    </tr>
						                    <tr class="huanguanGuojian">
						                    	<td>海关商品产地</td>
						                        <td>
						                        	<select name="haiguanGoodsPlace" class="form-control" ng-model="addData.haiguanGoodsPlace"  disabled="disabled">
														<option value="36"<c:if test='${goods.haiguanGoodsPlace == "36"}'> selected </c:if>>澳大利亚</option>
														<option value="56"<c:if test='${goods.haiguanGoodsPlace == "56"}'> selected </c:if>>比利时</option>
														<option value="124"<c:if test='${goods.haiguanGoodsPlace == "124"}'> selected </c:if>>加拿大</option>
														<option value="156"<c:if test='${goods.haiguanGoodsPlace == "156"}'> selected </c:if>>中国</option>
														<option value="158"<c:if test='${goods.haiguanGoodsPlace == "158"}'> selected </c:if>>中国台湾</option>
														<option value="208"<c:if test='${goods.haiguanGoodsPlace == "208"}'> selected </c:if>>丹麦</option>
														<option value="250"<c:if test='${goods.haiguanGoodsPlace == "250"}'> selected </c:if>>法国</option>
														<option value="276"<c:if test='${goods.haiguanGoodsPlace == "276"}'> selected </c:if>>德国</option>
														<option value="300"<c:if test='${goods.haiguanGoodsPlace == "300"}'> selected </c:if>>希腊</option>
														<option value="344"<c:if test='${goods.haiguanGoodsPlace == "344"}'> selected </c:if>>中国香港</option>
														<option value="380"<c:if test='${goods.haiguanGoodsPlace == "380"}'> selected </c:if>>意大利</option>
														<option value="392"<c:if test='${goods.haiguanGoodsPlace == "392"}'> selected </c:if>>日本</option>
														<option value="410"<c:if test='${goods.haiguanGoodsPlace == "410"}'> selected </c:if>>韩国</option>
														<option value="458"<c:if test='${goods.haiguanGoodsPlace == "458"}'> selected </c:if>>马来西亚</option>
														<option value="554"<c:if test='${goods.haiguanGoodsPlace == "554"}'> selected </c:if>>新西兰</option>
														<option value="643"<c:if test='${goods.haiguanGoodsPlace == "643"}'> selected </c:if>>俄罗斯</option>
														<option value="702"<c:if test='${goods.haiguanGoodsPlace == "702"}'> selected </c:if>>新加坡</option>
														<option value="724"<c:if test='${goods.haiguanGoodsPlace == "724"}'> selected </c:if>>西班牙</option>
														<option value="764"<c:if test='${goods.haiguanGoodsPlace == "764"}'> selected </c:if>>泰国</option>
														<option value="826"<c:if test='${goods.haiguanGoodsPlace == "826"}'> selected </c:if>>英国</option>
														<option value="840"<c:if test='${goods.haiguanGoodsPlace == "840"}'> selected </c:if>>美国</option>
													</select>
						                        </td>
						                        
						                         <td>国检商品产地</td>
						                        <td>
						                        	<select name="guojianGoodsPlace" class="form-control" ng-model="addData.guojianGoodsPlace"  disabled="disabled">
														<option value="110"<c:if test='${goods.guojianGoodsPlace == "110"}'> selected </c:if>>中国香港</option>
														<option value="116"<c:if test='${goods.guojianGoodsPlace == "116"}'> selected </c:if>>日本</option>
														<option value="122"<c:if test='${goods.guojianGoodsPlace == "122"}'> selected </c:if>>马来西亚</option>
														<option value="132"<c:if test='${goods.guojianGoodsPlace == "132"}'> selected </c:if>>新加坡</option>
														<option value="133"<c:if test='${goods.guojianGoodsPlace == "133"}'> selected </c:if>>韩国</option>
														<option value="136"<c:if test='${goods.guojianGoodsPlace == "136"}'> selected </c:if>>泰国</option>
														<option value="142"<c:if test='${goods.guojianGoodsPlace == "142"}'> selected </c:if>>中国</option>
														<option value="158"<c:if test='${goods.guojianGoodsPlace == "158"}'> selected </c:if>>中国台湾</option>
														<option value="301"<c:if test='${goods.guojianGoodsPlace == "301"}'> selected </c:if>>比利时</option>
														<option value="302"<c:if test='${goods.guojianGoodsPlace == "302"}'> selected </c:if>>丹麦</option>
														<option value="303"<c:if test='${goods.guojianGoodsPlace == "303"}'> selected </c:if>>英国</option>
														<option value="304"<c:if test='${goods.guojianGoodsPlace == "304"}'> selected </c:if>>德国</option>
														<option value="305"<c:if test='${goods.guojianGoodsPlace == "305"}'> selected </c:if>>法国</option>
														<option value="309"<c:if test='${goods.guojianGoodsPlace == "309"}'> selected </c:if>>荷兰</option>
														<option value="310"<c:if test='${goods.guojianGoodsPlace == "310"}'> selected </c:if>>希腊</option>
														<option value="311"<c:if test='${goods.guojianGoodsPlace == "311"}'> selected </c:if>>葡萄牙</option>
														<option value="312"<c:if test='${goods.guojianGoodsPlace == "312"}'> selected </c:if>>西班牙</option>
														<option value="315"<c:if test='${goods.guojianGoodsPlace == "315"}'> selected </c:if>>奥地利</option>
														<option value="429"<c:if test='${goods.guojianGoodsPlace == "429"}'> selected </c:if>>墨西哥</option>
														<option value="502"<c:if test='${goods.guojianGoodsPlace == "502"}'> selected </c:if>>美国</option>
														<option value="601"<c:if test='${goods.guojianGoodsPlace == "601"}'> selected </c:if>>澳大利亚</option>
														<option value="609"<c:if test='${goods.guojianGoodsPlace == "609"}'> selected </c:if>>新西兰</option>
													</select>
						                        </td> 
						                    </tr>	
						                     <tr>
									            <td>商品名称</td>
						                        <td>
						                        	<input type="text" class="form-control" name="goodsName" required value="${goods.goodsName}" readonly="readonly">
						                        </td> 
						                        <td>商品短名称</td>
						                        <td><input type="text" class="form-control" name="shortName" value="${goods.shortName}" readonly="readonly"></td>                       
						                    </tr>
						                    <tr>
									            <td>商品原价</td>
						                        <td><input type="text" class="form-control" name="onsalePrice" value="${goods.onsalePrice}" number="true" min="0" readonly="readonly" required></td> 
						                        <td>商品折扣价</td>
						                        <td><input type="text" class="form-control" name="discountPrice" value="${goods.discountPrice}" number="true" min="0" readonly="readonly"></td>                       
						                    </tr>
						                    <tr>
									            <td>采购价</td>
						                        <td><input type="text" class="form-control" name="purchasePrice" value="${goods.purchasePrice}" number="true" min="0" readonly="readonly"></td> 
						                        <td>采购经理</td>
						                        <td>
						                        	<select  class="form-control" name="purchaseManager" id="purchaseManager"  disabled="disabled">
														<c:forEach items="${userList}" var="list" varStatus="status">
															<option value="${list.userId}"
																<c:if test='${list.userId == goods.purchaseManager}'> selected </c:if>
															>${list.userRealName}</option>
														</c:forEach>
													</select>
						                        </td>                       
						                    </tr>
						                    <tr>
									            <td>采购主管</td>
						                        <td>
						                        	<select class="form-control" name="purchaseLeader" id="purchaseLeader"  disabled="disabled">
														<c:forEach items="${userList}" var="list" varStatus="status">
															<option value="${list.userId}"
																<c:if test='${list.userId == goods.purchaseLeader}'> selected </c:if>
															>${list.userRealName}</option>
														</c:forEach>
													</select>
						                        </td> 
						                                             
						                    </tr>
						                    					                <c:if test="${goods.goodsType=='MORE'}">
						               <tr class="table-title">
						                 <td colspan="10">
						                        <table id="goods" class="table table-bordered table-hover table-condensed table-responsive">    									         
									               	<tr class="table-title"><td colspan="4">套装中单品商品信息</td><td colspan="4"><!-- <input style="float:right;" type="button" onclick="addGoods();" value="添加" /> --></td></tr>
									               	<tr>
									                    <td>商品编号</td>
									                    <td>商品名称</td>
									                	<td>数量</td>
									                	<td>上下架状态</td>
									                	<td>正品库存</td>
									                	<td>Need价</td>
									                </tr>
									                <c:forEach  items="${itemList}" var="item" varStatus="status">
									                	<tr>
										                	<td>${item.goodsCode}</td>
										                    <td>${item.goodsName}</td>
										                	<td>${item.goodsCount}</td>
										                	<td>${item.goodsStatus}</td>
										                	<td>${item.storeCount}</td>
										                	<td>${item.discountPrice}</td>
									                	</tr>
									                </c:forEach>
									            </table>
						                      </td>
						                    </tr>
						                    </c:if>
						                    
						                     <tr class="table-title">
									         	<td colspan="4">
									         	商品详情
									         	</td>												
											</tr>
											<tr>
						                    	<td><i class="fa fa-asterisk"></i>
						                    		商品主图
						                    	</td>
						                    	<td colspan="3" id="divTopPicKeys">
						               
						                    		<input id="hiddenTopPicKeys" name="topPicKeys" type="hidden" value="${goods.topPicKeysString}" required>
													<input id="topPicKeysUpload" data-url="/publicImageUpload" type="file" name="files" multiple>
													<c:forEach items="${goods.goodsTopPics}" var="picKey" varStatus="status">
														<span id="topPicKey_${picKey}">
														<c:if test="${!empty picKey}">
														<a href='javascript:void(0);' onclick="topPicToLeftOrRight('topPicKey_${picKey}', 'left')">&nbsp;←&nbsp;</a>
														<a href="${picAddress}${picKey}" target="_blank">
														<img id="" src="${picAddress}${picKey}">
														</a>
														<a href='javascript:void(0);' onclick="topPicToLeftOrRight('topPicKey_${picKey}', 'right')">&nbsp;→&nbsp;</a>
														<a href="javascript:void(0);" onclick="shanchu('topPicKey_${picKey}',${status.index})" >&nbsp;x&nbsp;</a>&nbsp;
														</c:if>
														</span>
													</c:forEach>
						                    	</td>
						                    </tr>
						                    <tr>
						                    	<td><i class="fa fa-asterisk"></i>
						                    		场景图
						                    	</td>
						                    	<td colspan="3" id="divScenePicKeys">
						                    		<input id="hiddenScenePicKeys" name="scenePicKey" type="hidden" value="${goods.scenePicKey}" required>
													<input id="scenePicKeysUpload" data-url="/publicImageUpload" type="file" name="files" >
													<span id="span_scenePicKey">
													<c:if test="${!empty goods.scenePicKey}">
													<a href="${picAddress}${goods.scenePicKey}" target="_blank">
													<img  src="${picAddress}${goods.scenePicKey}">
													</a>
													<a href="javascript:void(0);" onclick="shanchuScenePicKey('span_scenePicKey')" >&nbsp;x&nbsp;</a>
													</c:if>
													</span>
						                    	</td>
						                    </tr>
						                    <tr>
						                    	<td><i class="fa fa-asterisk"></i>
						                    		详情大图
						                    	</td>
						                    	<td colspan="3" id="divDetailPicKeys" >
						                    		<input id="hiddenDetailPicKeys" name="detailPicKeys" type="hidden" value="${goods.detailPicKeysString}" required>
													<input id="detailPicKeysUpload" data-url="/publicImageUpload" type="file" name="files" multiple>
													<c:forEach items="${goods.detailPicKeys}" var="detailPicKey" varStatus="status">
														<span id="detailPicKey_${detailPicKey}">
														<c:if test="${!empty detailPicKey}">
														<a href='javascript:void(0);' onclick="detailPicToLeftOrRight('detailPicKey_${detailPicKey}','left')">&nbsp;←&nbsp;</a>
														<a href="${picAddress}${detailPicKey}" target="_blank">
														<img id=""  src="${picAddress}${detailPicKey}">
														</a>
														<a href='javascript:void(0);' onclick="detailPicToLeftOrRight('detailPicKey_${detailPicKey}','right')">&nbsp;→&nbsp;</a>
														<a href="javascript:void(0);" onclick="shanchuDetailPic('detailPicKey_${detailPicKey}',${status.index})" >&nbsp;x&nbsp;</a>&nbsp;
														</c:if>
														</span>
													</c:forEach>
						                    	</td>
						                    </tr>
						                    <tr>
									            <td><i class="fa fa-asterisk"></i>检索分类</td>
						                        <td colspan="3">
						                        <div class="col-xs-3">
						                        	<select class="form-control" id="goodsIndexCategoryOne">
														<c:forEach items="${indexCategoryOneList}"
															var="goodsCategory" varStatus="status">
															<option value="${goodsCategory.categoryId}"  <c:if test='${goodsCategory.categoryId == goods.goodsIndexCategoryOne}'> selected </c:if> >${goodsCategory.categoryName}</option>
														</c:forEach>
													</select>
													</div>
													<div class="col-xs-3">
													
												
													<select style="width: 200px;" class="form-control"
														 id="goodsIndexCategory" name="goodsIndexCategory">
														 <option value="${categoryIndex.categoryId}" selected>${categoryIndex.categoryName }</option>
													</select>
									
													</div>
												</td> 						                                             
						                    </tr>
						                    <tr>
									            <td><i class="fa fa-asterisk"></i>商品简介</td>
						                        <td colspan="3">
						                        	<textarea name="brief" id="brief" class="form-control" rows="3" cols="80" required>${goods.brief}</textarea>
												</td> 						                                             
						                    </tr>
						                     <tr>
									            <td>详情介绍</td>
						                        <td colspan="3">
						                        	<textarea id="goodsDesc" name="goodsDesc" class="form-control" rows="3"cols="80" required>${goods.goodsDesc}</textarea>
												</td> 						                                             
						                    </tr>
											<tr class="table-title">
									         	<td colspan="4">
									         	产品参数
									         	</td>												
											</tr>
											<tr>
									            <td>商品尺寸</td>
						                        <td><input type="text" class="form-control" name="size" value="${goods.size}" readonly="readonly"></td> 
						                        <td>商品颜色</td>
						                        <td><input type="text" class="form-control" name="color" value="${goods.color}" readonly="readonly"></td>                       
						                    </tr>
						                    <tr>
									            <td>商品产地</td>
						                        <td><input type="text" class="form-control" name="originPlace" value="${goods.originPlace}" readonly="readonly"></td> 
						                        <td>商品重量</td>
						                        <td><input type="text" class="form-control" name="weight" value="${goods.weight}" number="true" min="0" required readonly="readonly"></td>                       
						                    </tr>
						                    <tr>
						                    <td colspan="4">
						                    <a class="button button-primary button-raised button-small pull-right" href="javascript:void(0);" id="a_submitGoodsEditSave">提交</a>
						                    </td>
						                    </tr>					
									</table>
									<!-- <div class="form-group btnlist">
										<a class="button button-primary button-raised button-small pull-right" href="javascript:void(0);" id="a_submitGoodsEditSave">提交</a>
									</div>	 -->																		
								</form>
							 </div>
							<%-- <!-- 333333333 -->
							<div class="wrapper">
								<div class="row">
									<div class="col-sm-12">
									<form id="goodsEditProfile" class="form-horizontal"> 
										<section class="panel">
											<header class="panel-heading">商品概要
											<a href="/goods/page?page=${page}" class="btn btn-info pull-right">返回</a>
											</header>
											<div class="panel-body">
											  
												<div class="form-horizontal" >
													<div class="form-group">
														<label for="" class="col-lg-1">商品编号</label>
														<div class="col-lg-9">
															<input type="text" name="goodsCode" value="${goods.goodsCode}" readonly="readonly" digits="true" min="0" required>
															<input type="hidden" name="goodsId" value="${goods.goodsId}">
															<input type="hidden" id="hiddenGoodsTopPicLength" value="${goodsTopPicLength}" />
															<input type="hidden" id="hiddenGoodsDetailPicLength" value="${goodsDetailPicLength}" />
															<input type="hidden" id="hiddenPicAddress" value="${picAddress}"/>
															<input type="hidden" id="hiddenPage" value="${page}"/>
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-1">国际条形码</label>
														<div class="col-lg-9">
															<input type="text" value="${goods.goodsBarcode}" name="goodsBarcode" required readonly="readonly">
														</div>
													</div>
													<!-- <div class="form-group">
														<label for="" class="col-lg-1">是否为保税仓</label>
														<div class="col-lg-9">
															<select style="width: 150px;" class="form-control" name="isGlobal" id="isGlobal">
																<option value="TRUE">是</option>
																<option value="FALSE">否</option>
															</select>
														</div>
													</div> -->
													<div class="form-group">
														<label for="" class="col-lg-1">商品分类</label>
															<div class="col-lg-3">
																<select style="width: 200px;" class="form-control"
																  id="oneGoodsCategoryId" disabled="true">
																	<c:forEach items="${goodsCategoryoneList}"
																		var="goodsCategory" varStatus="status">
																		<option value="${goodsCategory.categoryId}"  <c:if test='${goodsCategory.categoryId == goods.goodsCategoryIdOne}'> selected </c:if> >${goodsCategory.categoryName}</option>
																	</c:forEach>
																</select>
															</div>
															<div class="col-lg-3">
																<select style="width: 200px;" class="form-control"
																	 id="twogoodsCategoryId" disabled="true">
																	<c:forEach items="${goodsCategorytwoList}"
																		var="goodsCategory" varStatus="status">
																		<option value="${goodsCategory.categoryId}" <c:if test='${goodsCategory.categoryId == goods.goodsCategoryIdTwo}'> selected </c:if>>${goodsCategory.categoryName}</option>
																	</c:forEach>
												
																</select>
															</div>
															<div class="col-lg-3">
																<select style="width: 200px;" class="form-control"
																	name="goodsCategoryId" id="threeGoodsCategoryId" disabled="true">
																	<c:forEach items="${goodsCategorythreeList}"
																		var="goodsCategory" varStatus="status">
																		<option value="${goodsCategory.categoryId}" <c:if test='${goodsCategory.categoryId == goods.goodsCategoryId}'> selected </c:if>>${goodsCategory.categoryName}</option>
																	</c:forEach>
																</select>
															</div>
													</div>
													
													
													 <!-- 选择品牌 -->
													   <div class="form-group">
															<label for="" class="col-lg-1">商品品牌</label>
															<div class="col-lg-9">
															  <select style="width: 200px;" class="form-control"
																	name="brandId" id="brandId" disabled="true">
																	<c:forEach items="${brandList}"
																		var="goodsBrand" varStatus="status">
																		<option value="${goodsBrand.brandId}" <c:if test='${goodsBrand.brandId == goods.brandId}'> selected </c:if>>${goodsBrand.brandName}</option>
																	</c:forEach>
																</select>	
															</div>
														</div>
														
														<!--是否有效期管理  -->
														 <div class="form-group">
															<label for="" class="col-lg-1">是否效期管理</label>
															<div class="col-lg-1">
															   <label><input name="isValidDate" type="radio" value="TRUE" <c:if test="${goods.isValidDate=='TRUE'}">checked='checked'</c:if>  disabled="disabled"/>是 </label> 
                                                               <label><input name="isValidDate" type="radio" value="FALSE" <c:if test="${goods.isValidDate=='FALSE'}">checked='checked'</c:if>  disabled="disabled" />否 </label> 	
															</div>
															<div id="validDate" >
															<label for="" class="col-lg-1">有效期</label>
															<div class="col-lg-2">
															   ${goods.validDate} 月
															</div>
															</div>
														</div>
														<div class="form-group">
															<label for="" class="col-lg-1">是否赠品</label>
															<div class="col-lg-1">
															   <label><input name="isGift" type="radio" value="TRUE" <c:if test="${goods.isGift=='TRUE'}">checked='checked'</c:if>  disabled="disabled"/>是 </label> 
                                                               <label><input name="isGift" type="radio" value="FALSE" <c:if test="${goods.isGift=='FALSE'}">checked='checked'</c:if>  disabled="disabled" />否 </label> 	
															</div>
														</div>
														
														<div class="form-group" style="display: none;">
															<label for="" class="col-lg-1">是否单品</label>
															<div class="col-lg-1">
															   <label><input name="goodsType" type="radio" value="SINGLE" value="TRUE" <c:if test="${goods.goodsType=='SINGLE'}">checked='checked'</c:if> disabled="disabled"/>单品 </label> 
                                                               <label><input name="goodsType" type="radio" value="MORE" value="TRUE" <c:if test="${goods.goodsType=='MORE'}">checked='checked'</c:if> disabled="disabled"/>多品 </label> 	
													    </div>
													    </div>
													<div class="form-group">
														<label for="" class="col-lg-1">仓库类型</label>
														<div class="col-lg-9">
															<select style="width:200px;" class="form-control" name="warehouseType" id="warehouseType" onChange="chageWarehouseType()"  disabled="disabled">
																<!-- <option value="">仓库类型</option> -->
								    							<option value="SELF_WAREHOUSE">自营仓</option>
								    							<option value="TAX_WAREHOUSE">保税仓</option>
								    							<option value="OVERSEA_WAREHOUSE">海外直邮</option>
															</select>
														</div>
													</div>
													<div style="display: none;" id="huanguanGuojian">
															<div class="form-group">
																<label for="" class="col-lg-1">海关计量单位</label>
																<div class="col-lg-9">
																	<select name="haiguanCount" class="form-control"
																		style="width: 200px;" ng-model="addData.haiguanCount"  disabled="disabled">
																		<option value="006" <c:if test='${goods.haiguanCount== 006}'> selected </c:if>>套</option>
																		<option value="007" <c:if test='${goods.haiguanCount== 007}'> selected </c:if>>个</option>
																		<option value="008" <c:if test='${goods.haiguanCount== 008}'> selected </c:if>>只</option>
																		<option value="010" <c:if test='${goods.haiguanCount== 010}'> selected </c:if>>张</option>
																		<option value="011" <c:if test='${goods.haiguanCount== 011}'> selected </c:if>>件</option>
																		<option value="012" <c:if test='${goods.haiguanCount== 012}'> selected </c:if>>支</option>
																		<option value="014" <c:if test='${goods.haiguanCount== 014}'> selected </c:if>>根</option>
																		<option value="015" <c:if test='${goods.haiguanCount== 015}'> selected </c:if>>条</option>
																		<option value="017" <c:if test='${goods.haiguanCount== 017}'> selected </c:if>>块</option>
																		<option value="018" <c:if test='${goods.haiguanCount== 018}'> selected </c:if>>卷</option>
																		<option value="019" <c:if test='${goods.haiguanCount== 019}'> selected </c:if>>副</option>
																		<option value="020" <c:if test='${goods.haiguanCount== 020}'> selected </c:if>>片</option>
																		<option value="021" <c:if test='${goods.haiguanCount== 021}'> selected </c:if>>组</option>
																		<option value="022" <c:if test='${goods.haiguanCount== 022}'> selected </c:if>>份</option>
																		<option value="025" <c:if test='${goods.haiguanCount== 025}'> selected </c:if>>双</option>
																		<option value="026" <c:if test='${goods.haiguanCount== 026}'> selected </c:if>>对</option>
																		<option value="035" <c:if test='${goods.haiguanCount== 035}'> selected </c:if>>千克</option>
																		<option value="036" <c:if test='${goods.haiguanCount== 036}'> selected </c:if>>克</option>
																		<option value="095" <c:if test='${goods.haiguanCount== 095}'> selected </c:if>>升</option>
																		<option value="096" <c:if test='${goods.haiguanCount== 096}'> selected </c:if>>毫升</option>
																		<option value="120" <c:if test='${goods.haiguanCount== 120}'> selected </c:if>>箱</option>
																		<option value="121" <c:if test='${goods.haiguanCount== 121}'> selected </c:if>>批</option>
																		<option value="122" <c:if test='${goods.haiguanCount== 122}'> selected </c:if>>罐</option>
																		<option value="123" <c:if test='${goods.haiguanCount== 123}'> selected </c:if>>桶</option>
																		<option value="125" <c:if test='${goods.haiguanCount== 125}'> selected </c:if>>包</option>
																		<option value="134" <c:if test='${goods.haiguanCount== 134}'> selected </c:if>>枚</option>
																		<option value="136" <c:if test='${goods.haiguanCount== 136}'> selected </c:if>>袋</option>
																		<option value="139" <c:if test='${goods.haiguanCount== 139}'> selected </c:if>>粒</option>
																		<option value="140" <c:if test='${goods.haiguanCount== 140}'> selected </c:if>>盒</option>
																		<option value="141" <c:if test='${goods.haiguanCount== 141}'> selected </c:if>>合</option>
																		<option value="142" <c:if test='${goods.haiguanCount== 142}'> selected </c:if>>瓶</option>
																	</select>
																</div>
															</div>
															<div class="form-group">
																<label for="" class="col-lg-1">国检计量单位</label>
																<div class="col-lg-9">
																	<select name="guojianCount" class="form-control"
																		style="width: 200px;" id=""
																		 disabled="disabled">                                                           
																		<option value="006"<c:if test='${goods.guojianCount == "006"}'> selected </c:if>>套</option>
																		<option value="007"<c:if test='${goods.guojianCount == "007"}'> selected </c:if>>个</option>
																		<option value="008"<c:if test='${goods.guojianCount == "008"}'> selected </c:if>>只</option>
																		<option value="010"<c:if test='${goods.guojianCount == "010"}'> selected </c:if>>张</option>
																		<option value="011"<c:if test='${goods.guojianCount == "011"}'> selected </c:if>>件</option>
																		<option value="012"<c:if test='${goods.guojianCount == "012"}'> selected </c:if>>支</option>
																		<option value="014"<c:if test='${goods.guojianCount == "014"}'> selected </c:if>>根</option>
																		<option value="015"<c:if test='${goods.guojianCount == "015"}'> selected </c:if>>条</option>
																		<option value="017"<c:if test='${goods.guojianCount == "017"}'> selected </c:if>>块</option>
																		<option value="018"<c:if test='${goods.guojianCount == "018"}'> selected </c:if>>卷</option>
																		<option value="019"<c:if test='${goods.guojianCount == "019"}'> selected </c:if>>副</option>
																		<option value="020"<c:if test='${goods.guojianCount == "020"}'> selected </c:if>>片</option>
																		<option value="021"<c:if test='${goods.guojianCount == "021"}'> selected </c:if>>组</option>
																		<option value="022"<c:if test='${goods.guojianCount == "022"}'> selected </c:if>>份</option>
																		<option value="025"<c:if test='${goods.guojianCount == "025"}'> selected </c:if>>双</option>
																		<option value="026"<c:if test='${goods.guojianCount == "026"}'> selected </c:if>>对</option>
																		<option value="122"<c:if test='${goods.guojianCount == "122"}'> selected </c:if>>罐</option>
																		<option value="123"<c:if test='${goods.guojianCount == "123"}'> selected </c:if>>桶</option>
																		<option value="125"<c:if test='${goods.guojianCount == "125"}'> selected </c:if>>包</option>
																		<option value="136"<c:if test='${goods.guojianCount == "136"}'> selected </c:if>>袋</option>
																		<option value="139"<c:if test='${goods.guojianCount == "139"}'> selected </c:if>>粒</option>
																		<option value="140"<c:if test='${goods.guojianCount == "140"}'> selected </c:if>>盒</option>
																		<option value="141"<c:if test='${goods.guojianCount == "141"}'> selected </c:if>>合</option>
																		<option value="142"<c:if test='${goods.guojianCount == "142"}'> selected </c:if>>瓶</option>
																		<option value="503"<c:if test='${goods.guojianCount == "503"}'> selected </c:if>>枚</option>
																	</select>                                                       
																</div>                     
															</div>                         
															<div class="form-group">       
																<label for="" class="col-lg-1">海关国家编码</label>
																<div class="col-lg-9"> 
																	<select name="haiguanCountryCode" class="form-control"
																		style="width: 200px;"
																		ng-model="addData.haiguanCountryCode"  disabled="disabled">
																		<option value="110" <c:if test='${goods.haiguanCountryCode == "110"}'> selected </c:if>>中国香港</option>
																		<option value="116" <c:if test='${goods.haiguanCountryCode == "116"}'> selected </c:if>>日本</option>
																		<option value="121" <c:if test='${goods.haiguanCountryCode == "121"}'> selected </c:if>>中国澳门</option>
																		<option value="122" <c:if test='${goods.haiguanCountryCode == "122"}'> selected </c:if>>马来西亚</option>
																		<option value="125" <c:if test='${goods.haiguanCountryCode == "125"}'> selected </c:if>>尼泊尔</option>
																		<option value="132" <c:if test='${goods.haiguanCountryCode == "132"}'> selected </c:if>>新加坡</option>
																		<option value="133" <c:if test='${goods.haiguanCountryCode == "133"}'> selected </c:if>>韩国</option>
																		<option value="136" <c:if test='${goods.haiguanCountryCode == "136"}'> selected </c:if>>泰国</option>
																		<option value="141" <c:if test='${goods.haiguanCountryCode == "141"}'> selected </c:if>>越南</option>
																		<option value="142" <c:if test='${goods.haiguanCountryCode == "142"}'> selected </c:if>>中国</option>
																		<option value="143" <c:if test='${goods.haiguanCountryCode == "143"}'> selected </c:if>>台澎金马关税区</option>
																		<option value="301" <c:if test='${goods.haiguanCountryCode == "301"}'> selected </c:if>>比利时</option>
																		<option value="302" <c:if test='${goods.haiguanCountryCode == "302"}'> selected </c:if>>丹麦</option>
																		<option value="303" <c:if test='${goods.haiguanCountryCode == "303"}'> selected </c:if>>英国</option>
																		<option value="304" <c:if test='${goods.haiguanCountryCode == "304"}'> selected </c:if>>德国</option>
																		<option value="305" <c:if test='${goods.haiguanCountryCode == "305"}'> selected </c:if>>法国</option>
																		<option value="306" <c:if test='${goods.haiguanCountryCode == "306"}'> selected </c:if>>爱尔兰</option>
																		<option value="309" <c:if test='${goods.haiguanCountryCode == "309"}'> selected </c:if>>荷兰</option>
																		<option value="310" <c:if test='${goods.haiguanCountryCode == "310"}'> selected </c:if>>希腊</option>
																		<option value="311" <c:if test='${goods.haiguanCountryCode == "311"}'> selected </c:if>>葡萄牙</option>
																		<option value="312" <c:if test='${goods.haiguanCountryCode == "312"}'> selected </c:if>>西班牙</option>
																		<option value="315" <c:if test='${goods.haiguanCountryCode == "315"}'> selected </c:if>>奥地利</option>
																		<option value="318" <c:if test='${goods.haiguanCountryCode == "318"}'> selected </c:if>>芬兰</option>
																		<option value="326" <c:if test='${goods.haiguanCountryCode == "326"}'> selected </c:if>>挪威</option>
																		<option value="327" <c:if test='${goods.haiguanCountryCode == "327"}'> selected </c:if>>波兰</option>
																		<option value="330" <c:if test='${goods.haiguanCountryCode == "330"}'> selected </c:if>>瑞典</option>
																		<option value="331" <c:if test='${goods.haiguanCountryCode == "331"}'> selected </c:if>>瑞士</option>
																		<option value="410" <c:if test='${goods.haiguanCountryCode == "410"}'> selected </c:if>>巴西</option>
																		<option value="429" <c:if test='${goods.haiguanCountryCode == "429"}'> selected </c:if>>墨西哥</option>
																		<option value="501" <c:if test='${goods.haiguanCountryCode == "501"}'> selected </c:if>>加拿大</option>
																		<option value="502" <c:if test='${goods.haiguanCountryCode == "502"}'> selected </c:if>>美国</option>
																		<option value="601" <c:if test='${goods.haiguanCountryCode == "601"}'> selected </c:if>>澳大利亚</option>
																		<option value="609" <c:if test='${goods.haiguanCountryCode == "609"}'> selected </c:if>>新西兰</option>
																		<option value="701" <c:if test='${goods.haiguanCountryCode == "701"}'> selected </c:if>>国(地)别不详的</option>
																		<option value="702" <c:if test='${goods.haiguanCountryCode == "702"}'> selected </c:if>>联合国及机构和国际组织</option>
																		<option value="999" <c:if test='${goods.haiguanCountryCode == "999"}'> selected </c:if>>中性包装原产级别</option>
																	</select>             
																</div>                    
															</div>
															<div class="form-group">
																<label for="" class="col-lg-1">国检国家编码</label>
																<div class="col-lg-9">
																	<select name="guojianCountryCode" class="form-control"
																		style="width: 200px;"
																		ng-model="addData.guojianCountryCode"  disabled="disabled">
																		<option value="36"<c:if test='${goods.guojianCountryCode == "36"}'> selected </c:if>>澳大利亚</option>
																		<option value="40"<c:if test='${goods.guojianCountryCode == "40"}'> selected </c:if>>奥地利</option>
																		<option value="56"<c:if test='${goods.guojianCountryCode == "56"}'> selected </c:if>>比利时</option>
																		<option value="76"<c:if test='${goods.guojianCountryCode == "76"}'> selected </c:if>>巴西</option>
																		<option value="100"<c:if test='${goods.guojianCountryCode == "100"}'> selected </c:if>>保加利亚</option>
																		<option value="124"<c:if test='${goods.guojianCountryCode == "124"}'> selected </c:if>>加拿大</option>
																		<option value="152"<c:if test='${goods.guojianCountryCode == "152"}'> selected </c:if>>智利</option>
																		<option value="156"<c:if test='${goods.guojianCountryCode == "156"}'> selected </c:if>>中国</option>
																		<option value="158"<c:if test='${goods.guojianCountryCode == "158"}'> selected </c:if>>中国台湾</option>
																		<option value="170"<c:if test='${goods.guojianCountryCode == "170"}'> selected </c:if>>哥伦比亚</option>
																		<option value="208"<c:if test='${goods.guojianCountryCode == "208"}'> selected </c:if>>丹麦</option>
																		<option value="246"<c:if test='${goods.guojianCountryCode == "246"}'> selected </c:if>>芬兰</option>
																		<option value="250"<c:if test='${goods.guojianCountryCode == "250"}'> selected </c:if>>法国</option>
																		<option value="276"<c:if test='${goods.guojianCountryCode == "276"}'> selected </c:if>>德国</option>
																		<option value="300"<c:if test='${goods.guojianCountryCode == "300"}'> selected </c:if>>希腊</option>
																		<option value="344"<c:if test='${goods.guojianCountryCode == "344"}'> selected </c:if>>中国香港</option>
																		<option value="356"<c:if test='${goods.guojianCountryCode == "356"}'> selected </c:if>>印度</option>
																		<option value="372"<c:if test='${goods.guojianCountryCode == "372"}'> selected </c:if>>爱尔兰</option>
																		<option value="380"<c:if test='${goods.guojianCountryCode == "380"}'> selected </c:if>>意大利</option>
																		<option value="392"<c:if test='${goods.guojianCountryCode == "392"}'> selected </c:if>>日本</option>
																		<option value="410"<c:if test='${goods.guojianCountryCode == "410"}'> selected </c:if>>韩国</option>
																		<option value="446"<c:if test='${goods.guojianCountryCode == "446"}'> selected </c:if>>中国澳门</option>
																		<option value="458"<c:if test='${goods.guojianCountryCode == "458"}'> selected </c:if>>马来西亚</option>
																		<option value="484"<c:if test='${goods.guojianCountryCode == "484"}'> selected </c:if>>墨西哥</option>
																		<option value="528"<c:if test='${goods.guojianCountryCode == "528"}'> selected </c:if>>荷兰</option>
																		<option value="554"<c:if test='${goods.guojianCountryCode == "554"}'> selected </c:if>>新西兰</option>
																		<option value="578"<c:if test='${goods.guojianCountryCode == "578"}'> selected </c:if>>挪威</option>
																		<option value="643"<c:if test='${goods.guojianCountryCode == "643"}'> selected </c:if>>俄罗斯</option>
																		<option value="702"<c:if test='${goods.guojianCountryCode == "702"}'> selected </c:if>>新加坡</option>
																		<option value="704"<c:if test='${goods.guojianCountryCode == "704"}'> selected </c:if>>越南</option>
																		<option value="710"<c:if test='${goods.guojianCountryCode == "710"}'> selected </c:if>>南非</option>
																		<option value="724"<c:if test='${goods.guojianCountryCode == "724"}'> selected </c:if>>西班牙</option>
																		<option value="752"<c:if test='${goods.guojianCountryCode == "752"}'> selected </c:if>>瑞典</option>
																		<option value="756"<c:if test='${goods.guojianCountryCode == "756"}'> selected </c:if>>瑞士</option>
																		<option value="764"<c:if test='${goods.guojianCountryCode == "764"}'> selected </c:if>>泰国</option>
																		<option value="826"<c:if test='${goods.guojianCountryCode == "826"}'> selected </c:if>>英国</option>
																		<option value="840"<c:if test='${goods.guojianCountryCode == "840"}'> selected </c:if>>美国</option>
																		<option value="903"<c:if test='${goods.guojianCountryCode == "903"}'> selected </c:if>>亚洲</option>
																		<option value="906"<c:if test='${goods.guojianCountryCode == "906"}'> selected </c:if>>非洲</option>
																		<option value="909"<c:if test='${goods.guojianCountryCode == "909"}'> selected </c:if>>欧洲</option>
																		<option value="991"<c:if test='${goods.guojianCountryCode == "991"}'> selected </c:if>>保税区</option>
																		<option value="992"<c:if test='${goods.guojianCountryCode == "992"}'> selected </c:if>>未列出的国家或地区</option>
																		<option value="0"<c:if test='${goods.guojianCountryCode == "0"}'> selected </c:if>>有国家地区</option>
																	</select>           
																</div>                  
															</div>                      
															<div class="form-group">
																<label for="" class="col-lg-1">海关商品产地</label>
																<div class="col-lg-9">
																	<select name="haiguanGoodsPlace" class="form-control"
																		style="width: 200px;"
																		ng-model="addData.haiguanGoodsPlace"  disabled="disabled">
																		<option value="36"<c:if test='${goods.haiguanGoodsPlace == "36"}'> selected </c:if>>澳大利亚</option>
																		<option value="56"<c:if test='${goods.haiguanGoodsPlace == "56"}'> selected </c:if>>比利时</option>
																		<option value="124"<c:if test='${goods.haiguanGoodsPlace == "124"}'> selected </c:if>>加拿大</option>
																		<option value="156"<c:if test='${goods.haiguanGoodsPlace == "156"}'> selected </c:if>>中国</option>
																		<option value="158"<c:if test='${goods.haiguanGoodsPlace == "158"}'> selected </c:if>>中国台湾</option>
																		<option value="208"<c:if test='${goods.haiguanGoodsPlace == "208"}'> selected </c:if>>丹麦</option>
																		<option value="250"<c:if test='${goods.haiguanGoodsPlace == "250"}'> selected </c:if>>法国</option>
																		<option value="276"<c:if test='${goods.haiguanGoodsPlace == "276"}'> selected </c:if>>德国</option>
																		<option value="300"<c:if test='${goods.haiguanGoodsPlace == "300"}'> selected </c:if>>希腊</option>
																		<option value="344"<c:if test='${goods.haiguanGoodsPlace == "344"}'> selected </c:if>>中国香港</option>
																		<option value="380"<c:if test='${goods.haiguanGoodsPlace == "380"}'> selected </c:if>>意大利</option>
																		<option value="392"<c:if test='${goods.haiguanGoodsPlace == "392"}'> selected </c:if>>日本</option>
																		<option value="410"<c:if test='${goods.haiguanGoodsPlace == "410"}'> selected </c:if>>韩国</option>
																		<option value="458"<c:if test='${goods.haiguanGoodsPlace == "458"}'> selected </c:if>>马来西亚</option>
																		<option value="554"<c:if test='${goods.haiguanGoodsPlace == "554"}'> selected </c:if>>新西兰</option>
																		<option value="643"<c:if test='${goods.haiguanGoodsPlace == "643"}'> selected </c:if>>俄罗斯</option>
																		<option value="702"<c:if test='${goods.haiguanGoodsPlace == "702"}'> selected </c:if>>新加坡</option>
																		<option value="724"<c:if test='${goods.haiguanGoodsPlace == "724"}'> selected </c:if>>西班牙</option>
																		<option value="764"<c:if test='${goods.haiguanGoodsPlace == "764"}'> selected </c:if>>泰国</option>
																		<option value="826"<c:if test='${goods.haiguanGoodsPlace == "826"}'> selected </c:if>>英国</option>
																		<option value="840"<c:if test='${goods.haiguanGoodsPlace == "840"}'> selected </c:if>>美国</option>
																	</select>              
																</div>                     
															</div>                         
															<div class="form-group">       
																<label for="" class="col-lg-1">国检商品产地</label>
																<div class="col-lg-9">     
																	<select name="guojianGoodsPlace" class="form-control"
																		style="width: 200px;"
																		ng-model="addData.guojianGoodsPlace"  disabled="disabled">
																		<option value="110"<c:if test='${goods.guojianGoodsPlace == "110"}'> selected </c:if>>中国香港</option>
																		<option value="116"<c:if test='${goods.guojianGoodsPlace == "116"}'> selected </c:if>>日本</option>
																		<option value="122"<c:if test='${goods.guojianGoodsPlace == "122"}'> selected </c:if>>马来西亚</option>
																		<option value="132"<c:if test='${goods.guojianGoodsPlace == "132"}'> selected </c:if>>新加坡</option>
																		<option value="133"<c:if test='${goods.guojianGoodsPlace == "133"}'> selected </c:if>>韩国</option>
																		<option value="136"<c:if test='${goods.guojianGoodsPlace == "136"}'> selected </c:if>>泰国</option>
																		<option value="142"<c:if test='${goods.guojianGoodsPlace == "142"}'> selected </c:if>>中国</option>
																		<option value="158"<c:if test='${goods.guojianGoodsPlace == "158"}'> selected </c:if>>中国台湾</option>
																		<option value="301"<c:if test='${goods.guojianGoodsPlace == "301"}'> selected </c:if>>比利时</option>
																		<option value="302"<c:if test='${goods.guojianGoodsPlace == "302"}'> selected </c:if>>丹麦</option>
																		<option value="303"<c:if test='${goods.guojianGoodsPlace == "303"}'> selected </c:if>>英国</option>
																		<option value="304"<c:if test='${goods.guojianGoodsPlace == "304"}'> selected </c:if>>德国</option>
																		<option value="305"<c:if test='${goods.guojianGoodsPlace == "305"}'> selected </c:if>>法国</option>
																		<option value="309"<c:if test='${goods.guojianGoodsPlace == "309"}'> selected </c:if>>荷兰</option>
																		<option value="310"<c:if test='${goods.guojianGoodsPlace == "310"}'> selected </c:if>>希腊</option>
																		<option value="311"<c:if test='${goods.guojianGoodsPlace == "311"}'> selected </c:if>>葡萄牙</option>
																		<option value="312"<c:if test='${goods.guojianGoodsPlace == "312"}'> selected </c:if>>西班牙</option>
																		<option value="315"<c:if test='${goods.guojianGoodsPlace == "315"}'> selected </c:if>>奥地利</option>
																		<option value="429"<c:if test='${goods.guojianGoodsPlace == "429"}'> selected </c:if>>墨西哥</option>
																		<option value="502"<c:if test='${goods.guojianGoodsPlace == "502"}'> selected </c:if>>美国</option>
																		<option value="601"<c:if test='${goods.guojianGoodsPlace == "601"}'> selected </c:if>>澳大利亚</option>
																		<option value="609"<c:if test='${goods.guojianGoodsPlace == "609"}'> selected </c:if>>新西兰</option>
																	</select>
																</div>
															</div>
														</div>
													<div class="form-group">
														<label for="" class="col-lg-1">商品名称</label>
														<div class="col-lg-9">
															<input style="width: 500px;" type="text" name="goodsName" required value="${goods.goodsName}" readonly="readonly">
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-1">商品短名称</label>
														<div class="col-lg-9">
															<input style="width:400px;" type="text" name="shortName" value="${goods.shortName}" readonly="readonly">
														</div>
													</div>
													
													<div class="form-group">
														<label for="" class="col-lg-1" >商品原价</label>
														<div class="col-lg-9">
															<input type="text" name="onsalePrice" value="${goods.onsalePrice}" number="true" min="0" readonly="readonly" required>
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-1">商品折扣价</label>
														<div class="col-lg-9">
															<input type="text" name="discountPrice" value="${goods.discountPrice}" number="true" min="0" readonly="readonly">
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-1">采购价</label>
														<div class="col-lg-9">
															<input type="text" name="purchasePrice" value="${goods.purchasePrice}" number="true" min="0">
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-1">采购经理</label>
														<div class="col-lg-9">
															<select style="width:200px;" class="form-control" name="purchaseManager" id="purchaseManager"  disabled="disabled">
																<c:forEach items="${userList}" var="list" varStatus="status">
																	<option value="${list.userId}"
																		<c:if test='${list.userId == goods.purchaseManager}'> selected </c:if>
																	>${list.userRealName}</option>
																</c:forEach>
															</select>
														</div>
														
													</div>
													<div class="form-group">
														<label for="" class="col-lg-1">采购主管</label>
														<div class="col-lg-9">
															<select style="width:200px;" class="form-control" name="purchaseLeader" id="purchaseLeader"  disabled="disabled">
																<c:forEach items="${userList}" var="list" varStatus="status">
																	<option value="${list.userId}"
																		<c:if test='${list.userId == goods.purchaseLeader}'> selected </c:if>
																	>${list.userRealName}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													
												</div>
											</div>
										</section>
										<section class="panel">
											<header class="panel-heading">商品详情</header>
											<div class="panel-body">
												<div class="form-horizontal">
													
													
													<div class="form-group" >
														<label for="" class="col-lg-1"><i style="color:red;">* </i>商品主图</label>
														<div class="col-lg-9" id="divTopPicKeys">
															<input id="hiddenTopPicKeys" name="topPicKeys" type="hidden" value="${goods.topPicKeysString}" required>
															<input id="topPicKeysUpload" data-url="/publicImageUpload" type="file" name="files" multiple>
															<c:forEach items="${goods.goodsTopPics}" var="picKey" varStatus="status">
																<span id="topPicKey_${picKey}">
																<c:if test="${!empty picKey}">
																<a href='javascript:void(0);' onclick="topPicToLeftOrRight('topPicKey_${picKey}', 'left')">&nbsp;←&nbsp;</a>
																<a href="${picAddress}${picKey}" target="_blank">
																<img id="" width="120px;" src="${picAddress}${picKey}">
																</a>
																<a href='javascript:void(0);' onclick="topPicToLeftOrRight('topPicKey_${picKey}', 'right')">&nbsp;→&nbsp;</a>
																<a href="javascript:void(0);" onclick="shanchu('topPicKey_${picKey}',${status.index})" >&nbsp;x&nbsp;</a>&nbsp;
																</c:if>
																</span>
															</c:forEach>
														</div>
													</div>
													<div class="form-group" >
														<label for="" class="col-lg-1"><i style="color:red;">* </i>场景图</label>
														<div class="col-lg-9" id="divScenePicKeys">
															<input id="hiddenScenePicKeys" name="scenePicKey" type="hidden" value="${goods.scenePicKey}" required>
															<input id="scenePicKeysUpload" data-url="/publicImageUpload" type="file" name="files" >
															<span id="span_scenePicKey">
															<c:if test="${!empty goods.scenePicKey}">
															<a href="${picAddress}${goods.scenePicKey}" target="_blank">
															<img width='120px;' src="${picAddress}${goods.scenePicKey}">
															</a>
															<a href="javascript:void(0);" onclick="shanchuScenePicKey('span_scenePicKey')" >&nbsp;x&nbsp;</a>
															</c:if>
															</span>
														</div>
													</div>
																										<div class="form-group">
														<label for="" class="col-lg-1"><i style="color:red;">* </i>详情大图</label>
														<div class="col-lg-9" id="divDetailPicKeys" >
															<input id="hiddenDetailPicKeys" name="detailPicKeys" type="hidden" value="${goods.detailPicKeysString}" required>
															<input id="detailPicKeysUpload" data-url="/publicImageUpload" type="file" name="files" multiple>
															<c:forEach items="${goods.detailPicKeys}" var="detailPicKey" varStatus="status">
																<span id="detailPicKey_${detailPicKey}">
																<c:if test="${!empty detailPicKey}">
																<a href='javascript:void(0);' onclick="detailPicToLeftOrRight('detailPicKey_${detailPicKey}','left')">&nbsp;←&nbsp;</a>
																<a href="${picAddress}${detailPicKey}" target="_blank">
																<img id="" width="120px;" src="${picAddress}${detailPicKey}">
																</a>
																<a href='javascript:void(0);' onclick="detailPicToLeftOrRight('detailPicKey_${detailPicKey}','right')">&nbsp;→&nbsp;</a>
																<a href="javascript:void(0);" onclick="shanchuDetailPic('detailPicKey_${detailPicKey}',${status.index})" >&nbsp;x&nbsp;</a>&nbsp;
																</c:if>
																</span>
															</c:forEach>
														</div>
													</div>
										            <!-- 商品检索分类 -->
												  <div class="form-group">
														<label for="" class="col-lg-1"><i style="color:red;">* </i> 检索分类</label>
															<div class="col-lg-3">
																<select style="width: 200px;" class="form-control"
																  id="goodsIndexCategoryOne">
																	<c:forEach items="${indexCategoryOneList}"
																		var="goodsCategory" varStatus="status">
																		<option value="${goodsCategory.categoryId}"  <c:if test='${goodsCategory.categoryId == goods.goodsIndexCategoryOne}'> selected </c:if> >${goodsCategory.categoryName}</option>
																	</c:forEach>
																</select>
															</div>
															<div class="col-lg-3">
																<select style="width: 200px;" class="form-control"
																	 id="goodsIndexCategory" name="goodsIndexCategory">
																	 <option value="${categoryIndex.categoryId}" selected>${categoryIndex.categoryName }</option>
																</select>
															</div>
													</div> 
													<div class="form-group">
														<label for="" class="col-lg-1"><i style="color:red;">* </i>商品简介</label>
														<div class="col-lg-9">
															<textarea name="brief" id="brief" class="form-control" rows="3" required>${goods.brief}</textarea>
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-1">详情介绍</label>
														<div class="col-lg-9">
															<textarea id="goodsDesc" name="goodsDesc" class="form-control" rows="3" required>${goods.goodsDesc}</textarea>
														</div>
													</div>
												</div>
											</div>
										</section>
										<section class="panel">
											<header class="panel-heading">产品参数</header>
											<div class="panel-body">
												<div class="form-horizontal">
													<div class="form-group">
														<label for="" class="col-lg-1">商品尺寸</label>
														<div class="col-lg-9">
															<input type="text" name="size" value="${goods.size}" readonly="readonly">
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-1">商品颜色</label>
														<div class="col-lg-9">
															<input type="text" name="color" value="${goods.color}" readonly="readonly">
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-1">商品产地</label>
														<div class="col-lg-9">
															<input type="text" name="originPlace" value="${goods.originPlace}" readonly="readonly">
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-1">商品重量</label>
														<div class="col-lg-9">
															<input type="text" name="weight" value="${goods.weight}" number="true" min="0" required readonly="readonly">
														</div>
													</div>
													<div class="form-group btnlist">
														<input type="submit" id="submitGoodsEidt" style="display:none;" value="提交" class="btn btn-info">
														<!-- <button id="goodsEditSave" class="btn btn-info" >提交</button> -->
														<a class="btn btn-info" href="javascript:void(0);" id="a_submitGoodsEditSave">提交</a>
													</div>
												</div>
											</div>
										</section>
										</form> 
									</div>
								</div>
							</div>
							<!-- 333333 --> --%>
						</div>
					</div>
				
					<!-- 
                            这部分写各自页面对应的内容
                            注意锁紧格式
                            范围 end -
                         -->
				</div>
			</div>
		</div>
	</section>
	<!-- 在 body的最底部引入js文件且一定保持 jquery 在 bootstrap 之前引入 -->
	<script src="/resources/js/jquery/jquery-2.1.4.min.js"></script>
	<script src="/resources/js/bootstrap/bootstrap.min.js"></script>
    <script src="/resources/js/jquery/jquery.validate.js"></script>
	<script src="/resources/js/jquery/jquery-validate-message.js"></script>
	<script src="/resources/js/jquery_upload/vendor/jquery.ui.widget.js"></script>
    <script src="/resources/js/jquery_upload/jquery.iframe-transport.js"></script>
    <script src="/resources/js/jquery_upload/jquery.fileupload.js"></script>
    <script src="/resources/js/goods/goodsEdit.js"></script>
	<!-- 侧面导航栏组件 js -->
	<script src="/resources/js/sidebar.js"></script>
	<script src="/resources/js/jquery.pagination.js"></script>
	<script type="text/javascript">
		$("#warehouseType").find("option[value='${goods.warehouseType}']").attr("selected",true);
		//$("#warehouseType").attr("disabled", true);
		$("#goodsCategoryId").find("option[value='${goods.goodsCategoryId}']").attr("selected",true);
		//$("#goodsCategoryId").attr("disabled", true);
	</script>
	<script type="text/javascript">
	$(document).ready(function() {
		var warehouseType = $("#warehouseType").val();
		if (warehouseType == 'TAX_WAREHOUSE') {
			$(".huanguanGuojian").show();
		}
	});
	function chageWarehouseType(){
		var warehouseType = $("#warehouseType").val();
		if (warehouseType == 'TAX_WAREHOUSE') {
			$(".huanguanGuojian").show();
		} else {
			$(".huanguanGuojian").hide();
		}
	}
	
	//二级联动
	$('#goodsIndexCategoryOne').change(function(){
	 var parentId =$("#goodsIndexCategoryOne").val();
	 $("#goodsIndexCategory option").remove();
  	  $.ajax({
		     type: 'GET',
		     url: '/indexCategory/getPreLevel',
		     data: {'parentId':parentId},
		     dataType: 'json',
		     success : function(data) {
            if(data.code==200){
            	 $.each(data.data.preList, function(key, val) {
            		 var $option = $("<option></option>");
            			$option.attr("value",val.categoryId);
            			$option.text(val.categoryName);
            			console.info( $("#goodsIndexCategory") )
            			$("#goodsIndexCategory").append($option);
            		 
            	 })
            }else{
         	   
         	   alert(data.msg);
            }
		     },
		    error : function() {
			    alert("系统异常");
		}

		   }); 
	})
	</script>
	
</body>
</html>