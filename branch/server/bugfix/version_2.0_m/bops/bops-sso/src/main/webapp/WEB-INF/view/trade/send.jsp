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

<title>客服系统</title>

<!-- 主体部分样式表 -->
<link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/font-awesome.min.css">
<link rel="stylesheet" href="/resources/css/layout.css">
<link rel="stylesheet" href="/resources/css/ops.css">
<link rel="stylesheet" href="/resources/css/sidebar.css">
<link rel="stylesheet" href="/resources/css/content-header.css">
<link rel="stylesheet" href="/resources/css/datapage.css">
<link rel="stylesheet" href="/resources/css/form.css">
<link rel="stylesheet" href="/resources/css/pagination.css">
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
								<h3 class="m-b-less">发货</h3><span class="tools pull-right"> <a
									class="btn btn-primary"
									href="javascript:javascript:window.history.back(-1);">返回</a><a
									class="btn btn-primary"
									href="javascript:javascript:window.location.reload();">刷新</a>
								</span>
								<!--<span class="sub-title">Welcome to Static Table</span>-->
								<!-- <div class="state-information">
									<ol class="breadcrumb m-b-less bg-less">
										<li><a href="#">主页</a></li>
										<li><a href="#">采销管理</a></li>
										<li class="active">商品编辑</li>
									</ol>
								</div> -->
							</div>
							<div class="wrapper">
								<div class="row">
									<div class="col-sm-12">
										<form id="tradeSendForm"
											action="/trade/send/${tradeNo}" method="post"
											class="form-horizontal">
											<section class="panel">
												<header class="panel-heading">商品</header>
												<div class="panel-body">
													<div class="form-horizontal">
														<div class="form-group">
															<label for="" class="col-lg-2">交易编号</label>
															<div class="col-lg-9">${trade.tradeNo}</div>
														</div>
														<div class="form-group">
															<label for="" class="col-lg-2">商品名称</label>
															<div class="col-lg-9">
																<c:forEach items="${trade.goodsList}" var="goods"
																	varStatus="status">
														${goods.goodsName} x ${goods.goodsCount}</br>
																</c:forEach>
															</div>
														</div>
														<div class="form-group">
															<label for="" class="col-lg-2">商品总价</label>
															<div class="col-lg-9">${trade.totalFee}</div>
														</div>
														<div class="form-group">
															<label for="" class="col-lg-2">支付渠道</label>
															<div class="col-lg-9">${trade.payChannel}</div>
														</div>
														<div class="form-group">
															<label for="" class="col-lg-2">买家用户名</label>
															<div class="col-lg-9">${trade.nickName}</div>
														</div>
														<div class="form-group">
															<label for="" class="col-lg-2">手机号</label>
															<div class="col-lg-9">${trade.mobile}</div>
														</div>
														<div class="form-group">
															<label for="" class="col-lg-2">买家收货地址</label>
															<div class="col-lg-9">${trade.address}</div>
														</div>
														<div class="form-group">
															<label for="" class="col-lg-2">交易状态</label>
															<div class="col-lg-9">
															<c:if test='${trade.tradeStatus == "WAIT_PAY"}'> 待付款</c:if>
															<c:if test='${trade.tradeStatus == "WAIT_PLATFORM_SEND"}'> 待发货 </c:if>
															<c:if test='${trade.tradeStatus == "WAIT_RECEIVE"}'> 待收货 </c:if>
															<c:if test='${trade.tradeStatus == "TRADE_SUCCESS"}'> 交易成功 </c:if>
															<c:if test='${trade.tradeStatus == "TRADE_CLOSE"}'> 交易关闭 </c:if>
															<c:if test='${trade.tradeStatus == "REFUND_SUCCESS"}'> 退款成功 </c:if>
															</div>
														</div>
														<c:forEach items="${trade.goodsList}" var="goods"
															varStatus="status">
															<div class="form-group">
																<label for="" class="col-lg-2">商品编号</label> <span
																	class="col-lg-1"><input type="hidden"
																	name="goodsSnNo" value="${goods.goodsCode}" />
																	${goods.goodsCode} </span> <label for="" class="col-lg-2">国际条形码</label>
																<span class="col-lg-1">${goods.goodsBarcode}</span><!--  <label
																	for="" class="col-lg-2">物料号</label> <span
																	class="col-lg-2"><input name="materialNo">
																</span> -->
															</div>
														</c:forEach>
													</div>
												</div>
											</section>
											<section class="panel">
												<header class="panel-heading">物流</header>
												<div class="panel-body">

													<div class="form-horizontal">
														<div class="form-group">
															<label for="" class="col-lg-2">服务商</label>
															<div class="col-lg-4">
																<select id="logisticsServiceId" name="logisticsServiceId">
									    							<option value="1">百世汇通</option>
									    							<option value="2">河南圆通</option>
									    							<option value="3">笨鸟物流</option>
									    						</select>
															</div>
															<label for="" class="col-lg-2">物流编号</label>
															<div class="col-lg-2">
																<input name="logisticsNums" />
															</div>
														</div>
													</div>
												</div>
											</section>
											<hr>
											<input type="button" onclick="send();"
												class="btn btn-primary" value="发货">
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
	<script src="/resources/js/jquery_upload/vendor/jquery.ui.widget.js"></script>
	<script src="/resources/js/jquery_upload/jquery.iframe-transport.js"></script>
	<script src="/resources/js/jquery_upload/jquery.fileupload.js"></script>
	<!-- 侧面导航栏组件 js -->
	<script src="/resources/js/sidebar.js"></script>
	<script src="/resources/js/jquery.pagination.js"></script>
	<script type="text/javascript">
	function send(){
		$.ajax({
			type : "post",
			url : "/trade/send/${tradeNo}",
			data: $("#tradeSendForm").serialize(),
			cache : false,
			dataType : 'json',
			success : function(data) {
				if(data.code == 200){
					alert(data.msg);
					window.location.href = "/trade/page";
				}
				else{
					alert("code: " + data.code + "\n" + data.msg);
				}
			}
		}); 
	}
	</script>
</body>
</html>
