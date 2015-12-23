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
<link rel="stylesheet" href="/resources/css/callcenter/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/callcenter/font-awesome.min.css">
<link rel="stylesheet" href="/resources/css/callcenter/ops.css">
<link rel="stylesheet" href="/resources/css/callcenter/datapage.css">
<!-- 分页插件 css 样式 -->
<link rel="stylesheet" href="/resources/css/callcenter/pagination.css" />
<!-- 此部分注释内容兼容IE低版本 H5相关 **不要删除**-->
<!--[if lt IE 9]>
          <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
</head>

<body>
	<section class="layout-wrap">
		
		<div class="layout-right">
			<div class="layout-right-inner">
				
				<div class="layout-right-bottom">
					<!-- 
                            这部分写各自页面对应的内容
                            注意锁紧格式
                            范围 start -
                         -->
					<div class="data-page-wrap">
						<div class="data-page-inner">
							<div class="wrapper">
								<div class="row">
									<div class="col-sm-12">
										<header class="panel-heading ">
											来电号码：${mobile}
										</header>
										<div
											class="dataTables_wrapper form-inline dt-bootstrap no-footer"
											id="DataTables_Table_0_wrapper">
											<div class="tbl-head">
												<form action="/trade/callcenter/page" method="get">
													<input type="text" id="userTradeNo" name="userTradeNo"
														value="${page.userTradeNo}" placeholder="订单号"> <input
														type="text" id="telephone" name="telephone"
														value="${page.telephone}" placeholder="收件人手机号">
													
													<input type="submit" class="btn btn-info" value="搜索" />
													<!-- <button type="button" onclick="javascript:search();"
														class="btn btn-info">异步搜索</button> -->
												</form>
											</div>
										</div>
										<table id="data" class="table table-hover">
											<thead>
												<tr>
													
													<th>订单号</th>
													<th>商品总价</th>
													<th>买家名</th>
													<th>买家手机号</th>
													<th>交易状态</th>
													<th>支付渠道</th>
													<th>交易时间</th>
													<th>订单仓库</th>
													<th>流转状态</th>
													<th>操作</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${list}" var="trade" varStatus="status">
													<tr>
														
														<td>${trade.userTradeNo}</td>
														<td>${trade.totalFee}</td>
														<td>${trade.nickName}</td>
														<td>${trade.telephone}</td>
														<td><c:choose>
																<c:when test="${trade.tradeStatus=='WAIT_PAY'}">
                                	待付款
                              </c:when>
																<c:when
																	test="${trade.tradeStatus=='WAIT_PLATFORM_SEND'}">
                                	待发货
                              </c:when>
																<c:when test="${trade.tradeStatus=='WAIT_RECEIVE'}">
                                	待收货
                              </c:when>
																<c:when test="${trade.tradeStatus=='TRADE_SUCCESS'}">
                                	交易成功
                              </c:when>
																<c:when test="${trade.tradeStatus=='TRADE_CLOSE'}">
                                	交易关闭
                              </c:when>
																<c:otherwise>
                                	退款成功
                              </c:otherwise>
															</c:choose></td>
														<td><c:choose>
																<c:when test="${trade.payChannel=='ALIPAY'}">
                                	支付宝
                              </c:when>
																<c:otherwise>
                                	微信
                              </c:otherwise>
															</c:choose></td>
														<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
																value="${trade.tradeTime}" type="both" /></td>
														<td><c:if
																test="${trade.warehouseType=='TAX_WAREHOUSE'}">保税仓</c:if>
															<c:if test="${trade.warehouseType=='SELF_WAREHOUSE'}">自营仓</c:if>
															<c:if test="${trade.warehouseType=='OVERSEA_WAREHOUSE'}">海外直邮</c:if>
														</td>
														<td>${trade.trackingStatus}
														</td>		
														<td><a href="/trade/callcenter/${trade.userTradeNo}">查看 </a></td>
													</tr>
												</c:forEach>
											</tbody>
											<tfoot>
												<tr>
													<td colspan="20">
														<div class="pagination-holder clearfix">
															<div id="light-pagination" class="pagination"></div>
														</div>
														<div class="tbl-footer clearfix">
														<div class="tbl-info pull-left">
															<div aria-live="polite" role="status"
																id="DataTables_Table_0_info" class="dataTables_info">共有
																${page.total} 条,${page.pageCount}页,每页显示 10 条</div>
															<input type="hidden" value="${page.page}" id="pageNumber">
														</div>
														<div class="tbl-pagin pull-right">
															<div id="light-pagination"></div>
														</div>
													</div>
													</td>
												</tr>
											</tfoot>
										</table>
									</div>
	</section>
	
	<!-- 
                            这部分写各自页面对应的内容
                            注意锁紧格式
                            范围 end -
                         -->
	<!-- 在 body的最底部引入js文件且一定保持 jquery 在 bootstrap 之前引入 -->
	<script src="/resources/js/jquery/jquery-2.1.4.min.js"></script>
	<script src="/resources/js/bootstrap/bootstrap.min.js"></script>
	<script src="/resources/js/jquery/jquery.bootstrap.min.js"></script>
	<script src="/resources/js/My97DatePicker/WdatePicker.js"></script>
	<!-- 侧面导航栏组件 js -->
	<script src="/resources/js/sidebar.js"></script>
	<script src="/resources/js/jquery.pagination.js"></script>
	<script type="text/javascript">
		$('#light-pagination').pagination({
			pages : "${page.pageCount}",
			cssStyle : 'light-theme',
			currentPage : "${page.page}",
			onPageClick : function(pageNumber, event) {
				window.location.href = 'page?page=' + pageNumber+'&telephone=${page.telephone}&userTradeNo=${page.userTradeNo}';
			}
		});
		function search() { 
			var tradeNo = $("#tradeNo").val();
			var mobile = $("#mobile").val();
			var userTradeNo = $("#userTradeNo").val();
			var tradeTimeStart = $("#tradeTimeStart").val();
			var tradeTimeEnd = $("#tradeTimeEnd").val();
			$.get("/trade/page/search", {
				tradeNo : tradeNo,
				mobile : mobile,
				userTradeNo : userTradeNo,
				tradeTimeStart : tradeTimeStart,
				tradeTimeEnd : tradeTimeEnd
			}, function(data) {
				doLoadData(data);
			});
		}
		function doLoadData(data){
			alert(data.code);
		}
	</script>
</body>
</html>