
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
<link rel="stylesheet" href="/resources/css/bootstrap/button.css">
<link rel="stylesheet" href="/resources/css/font-awesome.min.css">
<link rel="stylesheet" href="/resources/css/layout.css">
<link rel="stylesheet" href="/resources/css/ops.css">
<link rel="stylesheet" href="/resources/css/sidebar.css">
<link rel="stylesheet" href="/resources/css/content-header.css">
<!-- <link rel="stylesheet" href="/resources/css/dat apage.css"> -->
<link rel="stylesheet" href="/resources/css/list-content.css">

<!-- 分页插件 css 样式 -->
<link rel="stylesheet" href="/resources/css/pagination.css" />
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
				            <h3 class="m-b-less">采购单列表</h3>
				            <div class="state-information">
				                <ol class="breadcrumb m-b-less bg-less">
				                    <li><a href="#">主页</a></li>
				                    <li><a href="#">交易管理</a></li>
				                    <li class="active">采购单列表</li>
				                </ol>
				            </div>
				        </div>
				
				        <div class="info">
				            <div class="info-top">
				                <span>采购单列表</span>
				                <div class="back">
				                    <a href="/purchase/toAddPurchase" class="button button-primary button-raised button-small">新增</a>
				                   
				                </div>
				            </div>
				            <div class="search-form">
				            <form id="searchList" method="get" action="/purchase/page" class="form-inline">
								<div class="row">
									<div class="form-group col-xs-3">
				                        <label for="createTimeStart">采购时间</label>
				                        <input type="text" id="createTimeStart" name="createTimeStart" value="${page.createTimeStart}" value="" 
				                         		class="form-control" onfocus="WdatePicker({firstDayOfWeek:1})" tabindex="1"/>
														                        
				                    </div>
				                     <div class="form-group col-xs-3">
				                        <label for="createTimeEnd">至</label>
				                       <input type="text" id="createTimeEnd" name="createTimeEnd" value="${page.createTimeEnd}"  value="" 
										 		class="form-control" onfocus="WdatePicker({firstDayOfWeek:1})" tabindex="2"/>
				                    </div>
									<div class="form-group col-xs-3">
				                        <label for="warehouseId">预约仓库</label>
				                       <select name="warehouseId" id="warehouseId" class="form-control" tabindex="3">
												<option value="">全部</option>
												<c:forEach items="${wareHouseList}" var="list" varStatus="status">
													<option value="${list.warehouseId}" 
														<c:if test='${page.warehouseId == "list.warehouseId"}'> selected </c:if>
													>${list.wareHouseName}</option>
												</c:forEach>
										</select>													
				                    </div>
				                    
									 </div>
									<div class="row">
									<div class="form-group col-xs-3">
				                        <label for="purchaseId">采购单号</label>
				                        <input type="text" id="purchaseId" class="form-control" name="purchaseId" value="${page.purchaseId}" tabindex="4">
				                    </div>
									<div class="form-group col-xs-3">
				                        <label for="purchaseStatus">收货状态</label>
				                        <select name="purchaseStatus" id="purchaseStatus" class="form-control" tabindex="5">
												<option value="">全部 </option>
												<option value="WAIT_HARVEST" <c:if test='${page.purchaseStatus == "WAIT_HARVEST"}'> selected </c:if>>待收货 </option>
												<option value="HAVEST_HALF" <c:if test='${page.purchaseStatus == "HAVEST_HALF"}'> selected </c:if>>部分收货</option>
												<option value="HAVEST_ALREADY" <c:if test='${page.purchaseStatus == "HAVEST_ALREADY"}'> selected </c:if>>收货完成</option>
										</select>
				                    </div>
				                    <div class="form-group col-xs-3">
					                        <label for="goodsCode">商品编号</label>
					                       <input type="text" id="goodsCode" name="goodsCode" value="${page.goodsCode}" class="form-control" tabindex="7">										
					                 </div>
									</div>
									<div class="row">
										<div class="form-group col-xs-6 large-group">
					                        <label for="providerId">供应商</label>
					                       <select name="providerId" id="providerId" class="form-control" tabindex="6">
												<option value="">全部</option>
												<c:forEach items="${providerList}" var="list" varStatus="status">
													<option value="${list.vendorId}"
														<c:if test='${page.providerId == list.vendorId}'> selected </c:if>
													>${list.vendorName}</option>
												</c:forEach>
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
				                   <th>采购单号</th>
									<th>收货状态</th>
									<th>所属供应商</th>
									<th>预约仓库</th>
									<th>采购人</th>
									<th>采购部门</th>
									<th>采购总价</th>
									<th>商品总数量</th>
									<th>创建时间</th>
									<th>是否完成收货</th>
									<th>收货差异</th>
									<th>操作</th>			                    
				                </tr>
				                </thead>
			                <tbody>
				               <c:forEach items="${list}" var="list" varStatus="status">
									<tr class="odd" role="row">
										<td><a href="/purchase/purchaseDetial?purchaseId=${list.purchaseId}">${list.purchaseId}</a></td>
										<td>
											<c:if test='${list.purchaseStatus == "WAIT_HARVEST"}'>待收货</c:if>
											<c:if test='${list.purchaseStatus == "HAVEST_ALREADY"}'>已收货</c:if>
										</td>
										<td>${list.vendorName}</td>
										<td>${list.wareHouseName}</td>
										<td>${list.purchaseUserName}</td>
										<td>${list.purchaseDepartment}</td>
										<td>${list.purchasePriceAll}</td>
										<td>${list.purchaseCountAll}</td>
										<td>${list.createTime}</td>
										<td><c:if test='${list.purchaseIsHavest == "0"}'>否</c:if>
											<c:if test='${list.purchaseIsHavest == "1"}'>是</c:if></td>
										<td><c:if test='${list.purchaseIsDifferent == "0"}'>否</c:if>
											<c:if test='${list.purchaseIsDifferent == "1"}'>是</c:if></td>
										<td>
											<c:if test='${list.purchaseIsHavest == "0"}'><a href="/purchase/toModPurchase?purchaseId=${list.purchaseId}">收货</a></c:if>
										
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
					
					<%-- <!--2222222  -->
						<div class="data-page-inner">
							<div class="page-head">
								<!-- 页面标题 -->
								<h3 class="m-b-less">商品采购单列表</h3>
								<!--面包屑导航-->
								<div class="state-information">
									<ol class="breadcrumb m-b-less bg-less">
										<li><a href="#">主页</a></li>
										<li><a href="#">采销管理</a></li>
										<li class="active">采购单列表</li>
									</ol>
								</div>
							</div>
							<div class="wrapper">
								<div class="row">
									<div class="col-sm-12">
										<section class="panel">
											<header class="panel-heading ">
												<!-- 列表名称 -->
												采购单列表 <span class="pull-right"> <a
													class="btn btn-info" href="/purchase/toAddPurchase">新增</a>
												</span>
											</header>
											<div
												class="dataTables_wrapper form-inline dt-bootstrap no-footer"
												id="DataTables_Table_0_wrapper">
											    <form id="searchList" method="get" action="/purchase/page">
													<div class="tbl-head">
													<input type="text" id="createTimeStart" name="createTimeStart" value="${page.createTimeStart}" placeholder="采购时间" value="" onfocus="WdatePicker({firstDayOfWeek:1})"/>至
													<input type="text" id="createTimeEnd" name="createTimeEnd" value="${page.createTimeEnd}" placeholder="采购时间" value="" onfocus="WdatePicker({firstDayOfWeek:1})"/>
													<select name="warehouseId" id="warehouseId">
															<option value="">预约仓库</option>
															<c:forEach items="${wareHouseList}" var="list" varStatus="status">
																<option value="${list.warehouseId}" 
																	<c:if test='${page.warehouseId == "list.warehouseId"}'> selected </c:if>
																>${list.wareHouseName}</option>
															</c:forEach>
													</select>
													<input type="text" id="purchaseId" name="purchaseId" value="${page.purchaseId}" placeholder="采购单号">
													<select name="purchaseStatus" id="purchaseStatus">
															<option value="">收货状态 </option>
															<option value="WAIT_HARVEST" <c:if test='${page.purchaseStatus == "WAIT_HARVEST"}'> selected </c:if>>未发货 </option>
															<option value="HAVEST_HALF" <c:if test='${page.purchaseStatus == "HAVEST_HALF"}'> selected </c:if>>部分发货</option>
															<option value="HAVEST_ALREADY" <c:if test='${page.purchaseStatus == "HAVEST_ALREADY"}'> selected </c:if>>已发货</option>
													</select>
													<select name="providerId" id="providerId">
															<option value="">供应商</option>
															<c:forEach items="${providerList}" var="list" varStatus="status">
																<option value="${list.vendorId}"
																	<c:if test='${page.providerId == list.vendorId}'> selected </c:if>
																>${list.vendorName}</option>
															</c:forEach>
													</select>
													<input type="text" id="goodsCode" name="goodsCode" value="${page.goodsCode}" placeholder="商品编号">
													<input type="submit" class="btn btn-info" value="搜索" />
													</div>
													
												</form>
												<table aria-describedby="DataTables_Table_0_info"
													role="grid" id="DataTables_Table_0"
													class="table convert-data-table data-table dataTable no-footer">
													<thead>
														<tr role="row">
															<th aria-label="Rep : activate to sort column ascending"
																style="width: 120px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">采购单号</th>
															<th aria-label="Item : activate to sort column ascending"
																style="width: 180px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">收货状态</th>
															<th aria-label="Item : activate to sort column ascending"
																style="width: 180px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">所属供应商</th>
															<th aria-label="Item : activate to sort column ascending"
																style="width: 180px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">预约仓库</th>
															<th aria-label="Item : activate to sort column ascending"
																style="width: 180px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">采购人</th>
															<th aria-label="Item : activate to sort column ascending"
																style="width: 180px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">采购部门</th>
															<th aria-label="Item : activate to sort column ascending"
																style="width: 180px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">采购总价</th>
															<th aria-label="Item : activate to sort column ascending"
																style="width: 180px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">采购单总数量</th>
															<th aria-label="Item : activate to sort column ascending"
																style="width: 180px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">创建时间</th>
															<th aria-label="Item : activate to sort column ascending"
																style="width: 180px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">是否收货完成</th>
															<th aria-label="Item : activate to sort column ascending"
																style="width: 180px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">收货差异</th>
															<th aria-label="Item : activate to sort column ascending"
																style="width: 180px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">操作</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${list}" var="list" varStatus="status">
															<tr class="odd" role="row">
																<td><a href="/purchase/purchaseDetial?purchaseId=${list.purchaseId}">${list.purchaseId}</a></td>
																<td>
																	<c:if test='${list.purchaseStatus == "WAIT_HARVEST"}'>待收货</c:if>
																	<c:if test='${list.purchaseStatus == "HAVEST_ALREADY"}'>已收货</c:if>
																</td>
																<td>${list.vendorName}</td>
																<td>${list.wareHouseName}</td>
																<td>${list.purchaseUserName}</td>
																<td>${list.purchaseDepartment}</td>
																<td>${list.purchasePriceAll}</td>
																<td>${list.purchaseCountAll}</td>
																<td>${list.createTime}</td>
																<td><c:if test='${list.purchaseIsHavest == "0"}'>否</c:if>
																	<c:if test='${list.purchaseIsHavest == "1"}'>是</c:if></td>
																<td><c:if test='${list.purchaseIsDifferent == "0"}'>否</c:if>
																	<c:if test='${list.purchaseIsDifferent == "1"}'>是</c:if></td>
																<td>
																	<c:if test='${list.purchaseStatus == "WAIT_HARVEST"}'><a href="/purchase/toModPurchase?purchaseId=${list.purchaseId}">收货</a></c:if>
																
																</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
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
										</section>
									</div >
								</div> 
							</div>
						</div>
					<!-- 222222222 --> --%>
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


	<div class="modal fade" id="editGoodsModalDiv" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog"></div>
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
								window.location.href = 'page?page='+ pageNumber
								+ '&createTimeStart=${page.createTimeStart}&createTimeEnd=${page.createTimeEnd}&warehouseId=${page.warehouseId}&warehouseName=${page.warehouseName}&purchaseId=${page.purchaseId}&purchaseStatus=${page.purchaseStatus}&providerId=${page.providerId}&goodsCode=${page.goodsCode}&purchaseUserName=${page.purchaseUserName}&purchaseDepartment=${page.purchaseDepartment}&purchasePriceAll=${page.purchasePriceAll}&purchaseCountAll=${page.purchaseCountAll}&createTime=${page.createTime}&purchaseIsHavest=${page.purchaseIsHavest}&purchaseIsDifferent=${page.purchaseIsDifferent}';
							}
						});
		function doLoadData(data) {
			alert(data.code);
		}

		$("#srarch").click(function() {
			$("#searchList").submit();
		});

	</script>
</body>
</html>