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

<title>订单列表</title>

<!-- 主体部分样式表 -->
<link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/bootstrap/button.css">
<link rel="stylesheet" href="/resources/css/font-awesome.min.css">
<link rel="stylesheet" href="/resources/css/layout.css">
<link rel="stylesheet" href="/resources/css/ops.css">
<link rel="stylesheet" href="/resources/css/sidebar.css">
<link rel="stylesheet" href="/resources/css/content-header.css">
<!-- <link rel="stylesheet" href="/resources/css/data page.css"> -->
<!-- 分页插件 css 样式 -->
<link rel="stylesheet" href="/resources/css/pagination.css" />
<link rel="stylesheet" href="/resources/css/list-content.css">
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
					<div class="data-page-wrap">
						<div class="con">
				        <div class="page-head">
				            <h3 class="m-b-less">订单列表</h3>
				            <div class="state-information">
				                <ol class="breadcrumb m-b-less bg-less">
				                    <li><a href="#">主页</a></li>
				                    <li><a href="#">交易管理</a></li>
				                    <li class="active">订单列表</li>
				                </ol>
				            </div>
				        </div>
				
				        <div class="info">
				            <div class="info-top">
				                <span>订单列表</span>
				                <div class="back">
				                    <a href="javascript:void(0);" onclick="exportTradeList()" class="button button-primary button-raised button-small">导出</a>
				                   <a href="/trade/toBatchPage" class="button button-primary button-raised button-small">批量发货</a>
				                </div>
				            </div>
				            <div class="search-form">
				            <form method="get" action="/trade/page" class="form-inline"  id="form">
								<div class="row">
									<div class="form-group col-xs-3">
				                        <label for="tradeTimeStart">订单时间</label>
				                        <input type="text" class="form-control" id="tradeTimeStart" name="tradeTimeStart" value="${page.tradeTimeStart}"
											onfocus="WdatePicker({firstDayOfWeek:1})" tabindex="5">
				                    </div>
				                     <div class="form-group col-xs-3">
				                        <label for="tradeTimeEnd">至</label>
				                        <input type="text" class="form-control" id="tradeTimeEnd" name="tradeTimeEnd" value="${page.tradeTimeEnd}"
											onfocus="WdatePicker({firstDayOfWeek:1})" tabindex="6">
				                    </div>
									<div class="form-group col-xs-3">
				                        <label for="mobile">买家手机号</label>
				                       <input type="text" class="form-control" id="mobile" name="mobile" value="${page.mobile}" tabindex="1">														
				                    </div>
				                    
									 </div>
									<div class="row">
									<div class="form-group col-xs-3">
				                        <label for="telephone">收件人手机号</label>
				                        <input type="text"  class="form-control" id="telephone" name="telephone" value="${page.telephone}" tabindex="2">
				                    </div>
									<div class="form-group col-xs-3">
				                        <label for="userTradeNo">订单号</label>
				                        <input type="text" class="form-control" id="userTradeNo" name="userTradeNo" value="${page.userTradeNo}" tabindex="3">
				                    </div>
									
									<div class="form-group col-xs-3">
					                        <label for="goodsCode">商品编码</label>
					                       <input type="text" class="form-control" id="goodsCode" name="goodsCode" value="${page.goodsCode}" tabindex="4">
					                 </div>
									
				                    
				                    </div>
				                    <div class="row">
				                     <div class="form-group col-xs-3">
				                        <label for="tradeStatus">订单状态</label>
				                        <select id="tradeStatus" class="form-control" name="tradeStatus" tabindex="8">
											<option value="">全部</option>
											<option value="WAIT_PAY"
												<c:if test='${page.tradeStatus == "WAIT_PAY"}'> selected </c:if>>待付款</option>
											<option value="WAIT_PLATFORM_SEND"
												<c:if test='${page.tradeStatus == "WAIT_PLATFORM_SEND"}'> selected </c:if>>待发货</option>
											<option value="WAIT_RECEIVE"
												<c:if test='${page.tradeStatus == "WAIT_RECEIVE"}'> selected </c:if>>待收货</option>
											<option value="TRADE_SUCCESS"
												<c:if test='${page.tradeStatus == "TRADE_SUCCESS"}'> selected </c:if>>交易成功</option>
											<option value="TRADE_CLOSE"
												<c:if test='${page.tradeStatus == "TRADE_CLOSE"}'> selected </c:if>>交易关闭</option>
											<option value="REFUND_SUCCESS"
												<c:if test='${page.tradeStatus == "REFUND_SUCCESS"}'> selected </c:if>>退款成功</option>
										</select>
				                    </div>
				                    <div class="form-group col-xs-3">
				                        <label for="warehouseType">订单仓库</label>
				                        <select id="warehouseType" class="form-control" name="warehouseType" tabindex="7">
											<option value="">全部</option>
											<option value="SELF_WAREHOUSE"
												<c:if test='${page.warehouseType == "SELF_WAREHOUSE"}'> selected </c:if>>自营仓</option>
											<option value="TAX_WAREHOUSE"
												<c:if test='${page.warehouseType == "TAX_WAREHOUSE"}'> selected </c:if>>保税仓</option>
											<option value="OVERSEA_WAREHOUSE"
												<c:if test='${page.warehouseType == "OVERSEA_WAREHOUSE"}'> selected </c:if>>海外直邮</option>
										</select>
				                    </div>
				                   
				                   
										 <div class="form-group col-xs-3">
											 <button type="submit" class="button button-primary button-raised button-small">搜索</button>
										 </div> 
								 	</div>
							</form>
				           
				            </div>
				            <!--biaoge-->
				            <table class="table table-bordered table-hover table-condensed table-responsive">
				                <thead>
				                <tr>
				                   <th>订单号</th>
									<th>商品总价</th>
									<th>买家名</th>
									<th>买家手机号</th>
									<th>订单状态</th>
									<th>支付渠道</th>
									<th>订单时间</th>
									<th>订单仓库</th>
									<th>流转状态</th>
									<th>收货区域</th>
									<th>操作</th>			                    
				                </tr>
				                </thead>
			                <tbody>
				               <c:forEach items="${list}" var="trade" varStatus="status">
									<tr>
											<td>${trade.userTradeNo}</td>
											<td>${trade.totalFee}</td>
											<td>${trade.nickName}</td>
											<td>${trade.mobile}</td>
											<td><c:choose>
													<c:when test="${trade.tradeStatus=='WAIT_PAY'}">待付款</c:when>
													<c:when test="${trade.tradeStatus=='WAIT_PLATFORM_SEND'}">待发货</c:when>
													<c:when test="${trade.tradeStatus=='WAIT_RECEIVE'}">待收货</c:when>
													<c:when test="${trade.tradeStatus=='TRADE_SUCCESS'}">交易成功</c:when>
													<c:when test="${trade.tradeStatus=='TRADE_CLOSE'}">交易关闭</c:when>
													<c:otherwise>退款成功 </c:otherwise>
													</c:choose>
											</td>
											<td>
													<c:if test="${trade.payChannel=='ALIPAY'}">支付宝</c:if>
													<c:if test="${trade.payChannel=='WECHAT'}">微信</c:if>
											</td>
											<td>
												<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${trade.tradeTime}" type="both" />
											</td>
											<td><c:if test="${trade.warehouseType=='TAX_WAREHOUSE'}">保税仓</c:if>
												<c:if test="${trade.warehouseType=='SELF_WAREHOUSE'}">自营仓</c:if>
												<c:if test="${trade.warehouseType=='OVERSEA_WAREHOUSE'}">海外直邮</c:if>
											</td>
											<td>${trade.trackingStatus}</td>
											<td>${trade.logisticsValue}</td>
											<td>
												<a href="/trade/${trade.userTradeNo}?page=${page.page}">查看</a> 
												<c:if test="${trade.tradeStatus=='WAIT_PLATFORM_SEND'}">|
												<a href="/trade/send/${trade.tradeNo}?page=${page.page}">发货</a></c:if> 
												<c:if test="${trade.tradeStatus=='WAIT_RECEIVE'}">|
													<a href="/trade/refund/${trade.tradeNo}?page=${page.page}">退款</a>
												</c:if>
												<c:if test="${trade.tradeStatus=='WAIT_PLATFORM_SEND'}">|
													<a href="/trade/refund/${trade.tradeNo}?page=${page.page}">退款</a>
												</c:if>
												</td>
										</tr>
									</c:forEach>
				                </tbody>
				            </table>
				             <div class="page">					               
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
				        </div>
				    </div>					
				</div>
			</div>
		</div>
	</section>


	<div class="modal fade" id="batchSendTradeDialogDiv" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">批量发货</h4>
				</div>
				<form id="batchSendContent_form" method="post"
					enctype="multipart/form-data">
					<div class="modal-body">
						<div>
							请选择一个EXCEL文件: <label for="excel_files"> <input
								type="file" id="excel_files" name="files">
							</label>
						</div>
					</div>
				</form>
				<div class="modal-footer">
					<button type="button" onclick="batchSend();"
						class="btn btn-primary">发货</button>
					<button type="button" id="close_batchSend" class="btn btn-default"
						data-dismiss="modal">关闭</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
	</div>


	<!-- 在 body的最底部引入js文件且一定保持 jquery 在 bootstrap 之前引入 -->
	<script src="/resources/js/jquery/jquery-2.1.4.min.js"></script>
	<script src="/resources/js/bootstrap/bootstrap.min.js"></script>
	<script src="/resources/js/jquery/jquery.bootstrap.min.js"></script>
	<script src="/resources/js/My97DatePicker/WdatePicker.js"></script>
	<!-- 侧面导航栏组件 js -->
	<script src="/resources/js/sidebar.js"></script>
	<script src="/resources/js/jquery.pagination.js"></script>
	<script type="text/javascript">
		$('#light-pagination')
				.pagination(
						{
							pages : "${page.pageCount}",
							cssStyle : 'light-theme',
							currentPage : "${page.page}",
							onPageClick : function(pageNumber, event) {
								window.location.href = 'page?page='
										+ pageNumber
										+ '&tradeNo=${page.tradeNo}&goodsCode=${page.goodsCode}&mobile=${page.mobile}&telephone=${page.telephone}&userTradeNo=${page.userTradeNo}&tradeTimeStart=${page.tradeTimeStart}&tradeTimeEnd=${page.tradeTimeEnd}&warehouseType=${page.warehouseType}&tradeStatus=${page.tradeStatus}';
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
		function doLoadData(data) {
			alert(data.code);
		}
		var autoDowload = function( _url ){
			// var _params = $.param( _obj );
			var iframEle = $('<ifram></ifram>');
			    iframEle.css({
			        'width': 0,
			        'height': 0,
			        'display': 'none'
			    });
			    iframEle.appendTo('body');
			    // var url = '/need-bops/bops-api/goods/export?'+_params;
			    // console.info( url);
			var formEle = $('<form action="'+_url+'" method="post"></form>');
			var inputEle = $('<input type="test" name="file" value="">');
	        	inputEle.appendTo( formEle );
			    formEle.appendTo( iframEle ).submit().remove();
			    iframEle.remove();
		}
		function exportTradeList(){
		
			var trade_TimeStart = "tradeTimeStart=" + $("#tradeTimeStart").val();
			var trade_TimeEnd = "tradeTimeEnd=" + $("#tradeTimeEnd").val();
			var userTradeNo = "userTradeNo=" + $("#userTradeNo").val();
			var goodsCode = "goodsCode=" + $("#goodsCode").val();
			var mobile = "mobile=" + $("#mobile").val();
			var warehouseType = "warehouseType=" + $("#warehouseType").val();
			var telephone="telephone="+ $("#telephone").val();
			var tradeStatus="tradeStatus="+ $("#tradeStatus").val();
			
			var url = '';
				url = "/trade/exportTrade?";
			url = url
					+ trade_TimeStart + "&" 
					+ trade_TimeEnd + "&"
					+ userTradeNo + "&"
					+ goodsCode + "&"
					+ mobile + "&"
					+ warehouseType + "&"
					+ tradeStatus + "&"
					+ telephone;
			
			autoDowload(url);
			
			 /*  $.ajax({
	    		     type: 'POST',
	    		     url: '/trade/exportTrade',
	    		     data:{'tradeNo':tradeNo,'mobile':mobile,'userTradeNo':userTradeNo,'tradeTimeStart':tradeTimeStart,'tradeTimeEnd':tradeTimeEnd,'warehouseType':warehouseType,'tradeStatus':tradeStatus},
	    		     dataType: 'json',
	    		    success : function(data) {
	    		    	if(data.code==200){
	    		    		alert('修改成功');
	    		    		window.location.reload();	
	    		    	}else{	
	    		    		 alert(data.msg);
	    		    	}
	   			     },
	   			    error : function() {
	   				    alert("系统异常");
	   			}

	    		   }); */ 
		}
	</script>
	
</body>
</html>