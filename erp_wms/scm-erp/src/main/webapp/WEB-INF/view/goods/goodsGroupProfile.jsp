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

<title>ERP管理系统</title>

<!-- 主体部分样式表 -->
<link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/bootstrap/button.css">
<link rel="stylesheet" href="/resources/css/font-awesome.min.css">
<link rel="stylesheet" href="/resources/css/layout.css">
<link rel="stylesheet" href="/resources/css/ops.css">
<link rel="stylesheet" href="/resources/css/sidebar.css">
<link rel="stylesheet" href="/resources/css/content-header.css">
<link rel="stylesheet" href="/resources/css/datapage.css">
<link rel="stylesheet" href="/resources/css/form.css">
<link rel="stylesheet" href="/resources/css/pagination.css">
<link rel="stylesheet" href="/resources/css/profile-table.css">
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
								<h3 class="m-b-less">商品详情</h3>
								<!--<span class="sub-title">Welcome to Static Table</span>-->
								<div class="state-information">
									<ol class="breadcrumb m-b-less bg-less">
										<li><a href="#">主页</a></li>
										<li><a href="#">采销管理</a></li>
										<li class="active">商品编辑</li>
									</ol>
								</div>
							</div>
							<div class="wrapper">
								<div class="row">
									<div class="col-sm-12">
									<form id="goodsEditProfile" class="form-horizontal">
											<div class="info">
									            <table class="table table-bordered table-hover table-condensed table-responsive">
									            <header class="panel-heading">
													套装商品主信息
													<div class="back">
														<a href="javascript:javascript:window.history.back(-1);"class="button button-primary button-raised button-small" role="button">返回</a> 
													</div>
												</header>
														<tr>	
									                    	<td><i class="fa fa-asterisk"></i>商品编号</td>
									                        <td>${bopsGoods.goodsCode}</td>
									                        <td><i class="fa fa-asterisk"></i>国际条形码</td>
									                        <td>${bopsGoods.goodsBarcode}</td>                     
									                    </tr>
									                    <tr>
									                        <td><i class="fa fa-asterisk"></i>商品名称</td>
									                        <td>${bopsGoods.goodsName}</td>
									                        <td><i class="fa fa-asterisk"></i>商品仓库</td>
									                        <td>${bopsGoods.warehouseType}</td>
									                    </tr>
									                    <tr>	
									                    	<td><i class="fa fa-asterisk"></i>商品短名</td>
									                        <td>${bopsGoods.shortName}</td>
									                    </tr>
														<tr>
									                        <td><i class="fa fa-asterisk"></i>商品原价</td>
									                        <td><span id="onsalePrice">${goods.onsalePrice}</span></td>
									                        <td><i class="fa fa-asterisk"></i>Need价</td>
									                        <td><span id="discountPrice">${goods.discountPrice}</span></td>                        
									                    </tr>
									                    <tr>
									                        <td><i class="fa fa-asterisk"></i>商品采购价</td>
									                        <td><span id="purchasePrice">${goods.purchasePrice}</span></td>
									                        <td><i class="fa fa-asterisk"></i>商品重量</td>
									                        <td><span id="weight">${goods.weight}</span>kg</td>                        
									                    </tr>
									                    <tr>
									                        <td><i class="fa fa-asterisk"></i>最后上架人</td>
									                        <td><span>${goods.updateOnlineName}</span></td>
									                        <td><i class="fa fa-asterisk"></i>最后上架时间</td>
									                        <td><span ><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
																	value="${goods.updateOnlineTime}" type="both" /></span></td>                        
									                    </tr>
									                  </table>
											
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
									       <table id="goods" class="table table-bordered table-hover table-condensed table-responsive">    									         
									               	<tr class="table-title"><td colspan="4">商品详情</td><td colspan="4"><!-- <input style="float:right;" type="button" onclick="addGoods();" value="添加" /> --></td></tr>
											<tr>
							                    	<td>
							                    		商品主图
							                    	</td>
							                    	<td>
							                    		<c:forEach items="${goods.goodsTopPics}" var="picKey">
														<c:if test="${!empty picKey}">
															<a href="${picAddress}${picKey}" target="_blank"> <img
																height="60px" alt="商品场景图" src="${picAddress}${picKey}">
															</a>
														</c:if>
														</c:forEach>
							                    	</td>
							                    	</tr>
							                    	<tr>
							                    	<td>
							                    		场景图
							                    	</td>
							                    	<td colspan="4">
							                    		<c:if test="${!empty goods.scenePicKey}">
														<a href="${picAddress}${goods.scenePicKey}"
															target="_blank"> <img height="60px" alt="商品场景图"
															src="${picAddress}${goods.scenePicKey}">
														</a>
													</c:if>
							                    	</td>
							                    </tr>
											<tr>
												<td>详情大图</td>
												<td>
													<c:forEach items="${goods.detailPicKeys}" var="detailPicKey">
														<c:if test="${!empty detailPicKey}">
															<a href="${picAddress}${detailPicKey}" target="_blank">
																<img height="60px" alt="商品场景图"
																src="${picAddress}${detailPicKey}">
															</a>
														</c:if>
													</c:forEach>
												</td>
											</tr>
									       </table>
									       
									       <div class="form-group btnlist">
												<!-- <a class="button button-primary button-raised button-small pull-right" href="javascript:void(0);"
													onClick="doIt()">提交</a> -->
										   </div>	
									       </div>
										</form> 
									</div>
								</div>
							</div>
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
	<script src="/resources/js/goods/goods.js"></script>
	<!-- 侧面导航栏组件 js -->
	<script src="/resources/js/sidebar.js"></script>
	<script src="/resources/js/jquery.pagination.js"></script>

	<script type="text/javascript">
	</script>
</body>
</html>