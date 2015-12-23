<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<link rel="stylesheet" href="/resources/css/font-awesome.min.css">
<link rel="stylesheet" href="/resources/css/layout.css">
<link rel="stylesheet" href="/resources/css/ops.css">
<link rel="stylesheet" href="/resources/css/sidebar.css">
<link rel="stylesheet" href="/resources/css/content-header.css">
<!-- <link rel="stylesheet" href="/resources/css/datap age.css"> -->

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
					            <h3 class="m-b-less">商品价格修改列表</h3>
					            <div class="state-information">
					                <ol class="breadcrumb m-b-less bg-less">
					                    <li><a href="#">主页</a></li>
					                    <li><a href="#">采销管理</a></li>
					                    <li class="active">价格列表</li>
					                </ol>
					            </div>
					        </div>	
					        <div class="info">
					        	<div class="info-top">
					              <span>商品价格申请信息</span>
					             </div>
								<table class="table table-bordered table-hover table-condensed table-responsive list-table" >
					                <!--<tbody>-->
					               
					               
					                   <tr>
											<td>申请编号:</td>
											<td>${priceChange.pricechangeId}</td>
											<td>生效类型:</td>
											<td>
												<c:if test="${priceChange.pricechangeType == 'EXCUTE' }">立即执行</c:if>
												<c:if test="${priceChange.pricechangeType == 'SCHEDULE' }">时间段启用</c:if>
											</td>
											<td>启用时间段:</td>
											<td>
												<c:if test="${priceChange.pricechangeType == 'SCHEDULE' }">
													<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${priceChange.startTime}" type="both" />
													至
													<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${priceChange.endTime}" type="both" />
												</c:if>
											</td>
										</tr>
										<tr>
											<td>申请人:</td>
											<td>${priceChange.userName}</td>
											<td>申请部门:</td>
											<td>${priceChange.userDept}</td>
											<td>申请时间:</td>
											<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${priceChange.userTime}" type="both" /></td>
										</tr>
										<tr>
											<td>审批人:</td>
											<td>${priceChange.auditorName}</td>
											<td>审批部门:</td>
											<td>${priceChange.auditDept}</td>
											<td>审批时间:</td>
											<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${priceChange.auditTime}" type="both" /></td>
										</tr>
										<tr>
											<td>修改商品数:</td>
											<td>${priceChange.goodsCount}</td>
											<td>审核状态:</td>
											<td>
												<c:if test="${priceChange.pricechangeStatus == 'DRAFT' }">待审核</c:if>
												<c:if test="${priceChange.pricechangeStatus == 'VALID' }">审核通过</c:if>
												<c:if test="${priceChange.pricechangeStatus == 'FAIL' }">驳回</c:if>
											</td>
											<td>启用状态:</td>
											<td>
												<c:if test="${priceChange.excuted == 'NEITHER'}">未启用</c:if>
												<c:if test="${priceChange.excuted == 'STARTED'}">已启用</c:if>
												<c:if test="${priceChange.excuted == 'ENDED'}">恢复原价</c:if>
												<c:if test="${priceChange.excuted == 'FROZEN'}">作废</c:if>
											</td>
										</tr>
										<tr>
											<td>修改说明:</td>
											<td colspan="5">${priceChange.mark }</td>																					
										</tr>
					            </table>			
					        </div>
					        <div class="info">
					            <div class="info-top">
					                <span>价格修改列表</span>
					             </div>
					            <!--biaoge-->
					            <table class="table table-bordered table-hover">					
					                <thead>
					                <tr>
										<th>商品编号</th>
										<th>国际条形码</th>
										<th>商品名称</th>
										<th>Need价</th>										
										<th>恢复价格</th>
										<th>采购价</th>										
										<th>毛利额</th>
									</tr>
					                </thead>
					                <tbody>
					               <c:forEach items="${list}" var="price" varStatus="status">
									<tr class="odd" role="row">
										<td>${price.goodsCode}</td>
										<td>${price.goodsBarcode}</td>
										<td>${price.goodsName}</td>
										<td>${price.discountPrice}</td>
										<td>
											<c:if test="${price.originalPrice != 0}">${price.originalPrice}</c:if>
										</td>
										<td>${price.purchasePrice}</td>
										<td>${price.profit}</td>
									</tr>
									</c:forEach>
					                </tbody>				            	
					            </table> 
					          <div class="page">		 			               
														            
					       	    </div>
					        </div>
					    </div>
				
					<%-- <!--  11111111111111-->
						<div class="data-page-inner">
							<div class="page-head">
								<!-- 页面标题 -->
								<h3 class="m-b-less">商品价格修改列表</h3>
								<!--面包屑导航-->
								<div class="state-information">
									<ol class="breadcrumb m-b-less bg-less">
										<li><a href="#">主页</a></li>
										<li><a href="#">采销管理</a></li>
										<li class="active">价格列表</li>
									</ol>
								</div>
							</div>
							<div class="wrapper">
								<div class="row">
									<div class="col-sm-12">
									<section class="panel">
											<header class="panel-heading ">
												<!-- 列表名称 -->
												商品价格申请信息 <span class="pull-right">
												</span>
											</header>
											<div
												class="dataTables_wrapper form-inline dt-bootstrap no-footer"
												id="DataTables_Table_0_wrapper">
												<table aria-describedby="DataTables_Table_0_info"
													role="grid" id="DataTables_Table_0"
													class="table convert-data-table data-table dataTable no-footer">
													<tbody>
														<tr>
															<td>申请编号:</td>
															<td>${priceChange.pricechangeId}</td>
															<td>生效类型:</td>
															<td>
																<c:if test="${priceChange.pricechangeType == 'EXCUTE' }">立即执行</c:if>
																<c:if test="${priceChange.pricechangeType == 'SCHEDULE' }">时间段启用</c:if>
															</td>
															<td>启用时间段:</td>
															<td>
																<c:if test="${priceChange.pricechangeType == 'SCHEDULE' }">
																	<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${priceChange.startTime}" type="both" />
																	至
																	<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${priceChange.endTime}" type="both" />
																</c:if>
															</td>
														</tr>
														<tr>
															<td>申请人:</td>
															<td>${priceChange.userName}</td>
															<td>申请部门:</td>
															<td>${priceChange.userDept}</td>
															<td>申请时间:</td>
															<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${priceChange.userTime}" type="both" /></td>
														</tr>
														<tr>
															<td>审批人:</td>
															<td>${priceChange.auditorName}</td>
															<td>审批部门:</td>
															<td>${priceChange.auditDept}</td>
															<td>审批时间:</td>
															<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${priceChange.auditTime}" type="both" /></td>
														</tr>
														<tr>
															<td>修改商品数:</td>
															<td>${priceChange.goodsCount}</td>
															<td>审核状态:</td>
															<td>
																<c:if test="${priceChange.pricechangeStatus == 'DRAFT' }">待审核</c:if>
																<c:if test="${priceChange.pricechangeStatus == 'VALID' }">审核通过</c:if>
																<c:if test="${priceChange.pricechangeStatus == 'FAIL' }">驳回</c:if>
															</td>
															<td>启用状态:</td>
															<td>
																<c:if test="${priceChange.excuted == 'NEITHER'}">未启用</c:if>
																<c:if test="${priceChange.excuted == 'STARTED'}">已启用</c:if>
																<c:if test="${priceChange.excuted == 'ENDED'}">恢复原价</c:if>
																<c:if test="${priceChange.excuted == 'FROZEN'}">作废</c:if>
															</td>
														</tr>
														<tr>
															<td>修改说明:</td>
															<td colspan="3">${priceChange.mark }</td>
														</tr>
													</tbody>
												</table>
											</div>
										</section>
									
									
										<section class="panel">
											<header class="panel-heading ">
												<!-- 列表名称 -->
												价格修改列表 <span class="pull-right">
												</span>
											</header>
											<div
												class="dataTables_wrapper form-inline dt-bootstrap no-footer"
												id="DataTables_Table_0_wrapper">
												<table aria-describedby="DataTables_Table_0_info"
													role="grid" id="DataTables_Table_0"
													class="table convert-data-table data-table dataTable no-footer">
													<thead>
														<tr role="row">
															<th
																aria-label="Region : activate to sort column ascending"
																style="width: 90px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">商品编号</th>
															<th aria-label="Rep : activate to sort column ascending"
																style="width: 120px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">国际条形码</th>
															<th aria-label="Item : activate to sort column ascending"
																style="width: 180px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">商品名称</th>
															<th aria-label="Item : activate to sort column ascending"
																style="width: 60px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">Need价</th>
															<th aria-label="Item : activate to sort column ascending"
																style="width: 70px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">恢复价格</th>
															<th aria-label="Item : activate to sort column ascending"
																style="width: 70px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">采购价</th>
															<th aria-label="Item : activate to sort column ascending"
																style="width: 70px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">毛利</th>
															
														</tr>
													</thead>
													<tbody>
													<c:forEach items="${list}" var="price" varStatus="status">
													<tr class="odd" role="row">
														<td>${price.goodsCode}</td>
														<td>${price.goodsBarcode}</td>
														<td>${price.goodsName}</td>
														<td>${price.discountPrice}</td>
														<td>${price.originalPrice}</td>
														<td>${price.purchasePrice}</td>
														<td>${price.profit}</td>
													</tr>
													</c:forEach>
													</tbody>
												</table>
											</div>
										</section>
									</div>
								</div>
							</div>
						</div>
					<!-- 22222222222 --> --%>
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
	<script src="/resources/js/jquery/jquery.bootstrap.min.js"></script>
	<!-- 侧面导航栏组件 js -->
	<script src="/resources/js/sidebar.js"></script>
	<script src="/resources/js/jquery.pagination.js"></script>
	<script type="text/javascript">
	</script>

	<script type="text/javascript">
	</script>

	<script type="text/javascript">
	</script>
</body>
</html>