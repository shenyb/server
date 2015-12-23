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

<title>交易列表</title>

<!-- 主体部分样式表 -->
<link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/bootstrap/button.css">
<link rel="stylesheet" href="/resources/css/font-awesome.min.css">
<link rel="stylesheet" href="/resources/css/layout.css">
<link rel="stylesheet" href="/resources/css/ops.css">
<link rel="stylesheet" href="/resources/css/sidebar.css">
<link rel="stylesheet" href="/resources/css/content-header.css">
<link rel="stylesheet" href="/resources/css/data page.css">
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
					            <h3 class="m-b-less">批次查询</h3>
					            <div class="state-information">
					                <ol class="breadcrumb m-b-less bg-less">
					                    <li><a href="#">主页</a></li>
					                    <li><a href="#">交易管理</a></li>
					                    <li class="active">批次查询</li>
					                </ol>
					            </div>
					        </div>					
					        <div class="info">
					            <div class="info-top">
					                <span>批次列表</span>
					                <div class="back">
					                    <a href="javascript:javascript:window.location.reload();" class="button button-primary button-raised button-small">刷新</a>
					                </div>
					            </div>
					            <div class="search-form">
					                <form class="form-inline" action="/tradeBatch/page" method="get">
					                    <div class="row">
					                    <div class="form-group col-xs-3">   
					                        <label for="batchNo" class="">批次号 </label>                  
					                        <input type="text" class="form-control" id="batchNo" name="batchNo" value="${page.batchNo}">
												 
					                    </div>
					                    <div class="form-group col-xs-3">  
					                        <label for="tradeNo" class="">用户订单号 </label>                     
					                        <input type="text" class="form-control" id="tradeNo" name="tradeNo" value="${page.tradeNo}">
											 
					                    </div>
					                    <button type="submit" class="button button-primary button-raised button-small"><i class="fa fa-search"></i>&nbsp;&nbsp;搜索</button>
					                    </div>
					                </form>
					            </div>
					            <!--biaoge-->
					            <table class="table table-bordered table-hover">					
					                <thead>
					                <tr>
										<th>批次号</th>
										<th>批次数量</th>
										<th>仓库</th>
										<th>操作</th>
									</tr>
					                </thead>
					                <tbody>
					                <c:forEach items="${list}" var="trade" varStatus="status">
										<tr>
											<td>${trade.batchNo}</td>
											<td>${trade.batchCount}</td>
											<td>郑州保税仓</td>
											<td><a href="/tradeBatch/${trade.batchNo}">查看</a></td>
										</tr>
									</c:forEach>
					                </tbody>				            	
					            </table>
					            <div class="page">					               
									<div class="pagenum">
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
				
					<%-- <!-- 111111111111 -->
						<div class="data-page-inner">
							<div class="wrapper">
								<div class="row">
									<div class="col-sm-12">
										<header class="panel-heading ">
											批次列表 <span class="tools pull-right"><a
												class="btn btn-primary"
												href="javascript:javascript:window.location.reload();">刷新</a>
											</span>
										</header>
										<div
											class="dataTables_wrapper form-inline dt-bootstrap no-footer"
											id="DataTables_Table_0_wrapper">
											<div class="tbl-head">
												<form action="/tradeBatch/page" method="get">
													<input type="text" id="batchNo" name="batchNo"
														value="${page.batchNo}" placeholder="批次号">
														 <input
														type="text" id="tradeNo" name="tradeNo"
														value="${page.tradeNo}" placeholder="用户订单号"> <input
														type="submit" class="btn btn-info" value="搜索" />
												</form>
											</div>
										</div>
										<table class="table table-hover">
											<thead>
												<tr>
													<th>批次号</th>
													<th>批次数量</th>
													<th>仓库</th>
													<th>操作</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${list}" var="trade" varStatus="status">
													<tr>
														<td>${trade.batchNo}</td>
														<td>${trade.batchCount}</td>
														<td>郑州保税仓</td>
														<td><a href="/tradeBatch/${trade.batchNo}">查看</a></td>
													</tr>
												</c:forEach>
											</tbody>
											<tfoot>
												<tr>
													<td colspan="20">
														<div class="pagination-holder clearfix">
															<div id="light-pagination" class="pagination"></div>
														</div>
													</td>
												</tr>
											</tfoot>
										</table>
										˙
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
									</div>

								</div>
							</div>
						</div>
					<!-- 1111111111 --> --%>
					
					</div>
				</div>
						<!-- 
                            这部分写各自页面对应的内容
                            注意锁紧格式
                            范围 end -
                         -->
	</div>
	</div>
	</section>
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
				window.location.href = '?page=' + pageNumber+"&batchNo=${page.batchNo}&userTradeNo=${page.userTradeNo}";
			}
		});
	</script>
</body>
</html>








