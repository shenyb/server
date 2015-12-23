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
<link rel="stylesheet" href="/resources/css/datap age.css">

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
					            <h3 class="m-b-less">商品价格修改历史</h3>
					            <div class="state-information">
					                <ol class="breadcrumb m-b-less bg-less">
					                    <li><a href="#">主页</a></li>
					                    <li><a href="#">采销管理</a></li>
					                    <li class="active">商品列表</li>
					                </ol>
					            </div>
					        </div>					
					        <div class="info">
					            <div class="info-top">
					                <span>商品列表</span>
					            </div>
					            <div class="search-form">
					                <form class="form-inline" id="searchGoodsPriceListContent" method="get" action="/goodsNew/goodsPriceDetailsList">
					                    <div class="row">
					                    <div class="form-group col-xs-3">   
					                        <label for="form_goodsCode" class="">商品编号</label>                  
					                        <input type="text" class="form-control" id="form_goodsCode" name="goodsCode">												 
					                    </div>
					                    <div class="form-group col-xs-3">   
					                        <label for="form_goodsBarcode" class="">国际条形码</label>                  
					                        <input type="text" class="form-control" id="form_goodsBarcode" name="goodsBarcode">												 
					                    </div>
					                  </div>
					                  <div class="row"> 
					                    <div class="form-group col-xs-3">   
					                        <label for="form_goodsName" class="">商品名称</label>                  
					                        <input type="text" class="form-control" id="form_goodsName" name="goodsName">												 
					                    </div>
					                    <div class="form-group col-xs-3">   
					                        <label for="form_userId" class="">操作人</label>                  
					                        <select class="form-control" name="userId" id="form_userId">
				                            <option value="">全部</option>
												<c:forEach items="${userList}" var="user"
													varStatus="status">
											<option value="${user.userId}">${user.userRealName}</option>
												</c:forEach>
				                        </select>	 
					                    </div>
					                    
					                    <!-- 22222222 -->
					                    <a href="javascript:void(0);" id="searchGoodsPriceList" class="button button-primary button-raised button-small"><i class="fa fa-search"></i>&nbsp;&nbsp;搜索</a>
					                    </div>
					                </form>
					            </div>
					            <!--biaoge-->
					            <table class="table table-bordered table-hover">					
					                <thead>
					                <tr>
										<th>SKU</th>
										<th>申请编号</th>										
										<th>商品名称</th>
										<th>Need价</th>
										<th>恢复价格</th>
										<th>采购价</th>
										<th>毛利额</th>
										<th>启用状态</th>
										<th>审核状态</th>										
										<th>启用类型</th>
										<th>生效时间段</th>
										<th>操作人</th>
										<th>操作部门</th>																			
										<th>操作时间</th>
									</tr>
					                </thead>
					                <tbody>
					                <c:forEach items="${list}" var="goods" varStatus="status">
											<tr class="odd" role="row">
												<td>${goods.goodsCode}</td>
												<td><a href="/goodsNew/goodsPriceDetails?pricechangeId=${goods.pricechangeId}">${goods.pricechangeId}</a></td>
												<td>${goods.goodsName}</td>
												<td>${goods.discountPrice}</td>
												<td><c:if test="${price.originalPrice != 0}">${price.originalPrice}</c:if></td>
												<td>${goods.purchasePrice}</td>
												<td>${goods.profit}</td>
												<td><c:choose>
														<c:when test="${goods.excuted=='NEITHER'}">
						                                	未启用
						                              </c:when>
														<c:when test="${goods.excuted=='STARTED'}">
						                                	已启用
						                              </c:when>
						                              <c:when test="${goods.excuted=='ENDED'}">
						                                	恢复价格
						                              </c:when>
														<c:otherwise>
					                                	作废
					                             	 </c:otherwise>
													</c:choose>
												</td>
												<td><c:choose>
													<c:when test="${goods.pricechangeStatus=='DRAFT'}">
						                               	待审核
						                            </c:when>
						                            <c:when test="${goods.pricechangeStatus=='VALID'}">
						                               	审核成功
						                            </c:when>
													<c:otherwise>
						                                                                        驳回
						                             </c:otherwise>
													</c:choose>
												</td>
												<td><c:choose>
														<c:when test="${goods.pricechangeType=='EXCUTE'}">
															立即启用
														</c:when>
														<c:otherwise>
															时间段启用
														</c:otherwise>
													</c:choose></td>
												<td><c:choose>
														<c:when test="${goods.pricechangeType=='EXCUTE'}">
															&nbsp;
														</c:when>
														<c:otherwise>
															<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${goods.startTime}" type="both" />
															<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${goods.endTime}" type="both" />
														</c:otherwise>
													</c:choose></td>
												<td>${goods.userName}</td>
												<td>${goods.userDept}</td>
												<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${goods.userTime}" type="both" /></td>
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
					<%-- <!-- 22222222 -->
						<div class="data-page-inner">
							<div class="page-head">
								<!-- 页面标题 -->
								<h3 class="m-b-less">商品列表</h3>
								<!--面包屑导航-->
								<div class="state-information">
									<ol class="breadcrumb m-b-less bg-less">
										<li><a href="#">主页</a></li>
										<li><a href="#">采销管理</a></li>
										<li class="active">商品列表</li>
									</ol>
								</div>
							</div>
							<div class="wrapper">
								<div class="row">
									<div class="col-sm-12">
										<section class="panel">
											<header class="panel-heading ">
												<!-- 列表名称 -->
												商品列表 <span class="pull-right"> 
												</span>
											</header>
											<div
												class="dataTables_wrapper form-inline dt-bootstrap no-footer"
												id="DataTables_Table_0_wrapper">
												<form id="searchGoodsPriceListContent" method="get"
													action="/goodsNew/goodsPriceDetailsList">
													<div class="tbl-head">
														<input type="text" id="form_goodsCode" name="goodsCode" placeholder="商品编号"> 
														<input type="text" id="form_goodsBarcode" name="goodsBarcode" placeholder="国际条形码"> 
														<input type="text" id="form_goodsName" name="goodsName" placeholder="商品名称">
								                        <select class="form-control" name="userId" id="form_userId">
								                            <option value="">申请人</option>
																<c:forEach items="${userList}" var="user"
																	varStatus="status">
															<option value="${user.userId}">${user.userRealName}</option>
																</c:forEach>
								                        </select>
														<a href="javascript:void(0);" id="searchGoodsPriceList" class="btn btn-info">搜索</a>
													</div>
												</form>
												<table aria-describedby="DataTables_Table_0_info"
													role="grid" id="DataTables_Table_0"
													class="table convert-data-table data-table dataTable no-footer">
													<thead>
														<tr role="row">
															<th
																aria-label="Region : activate to sort column ascending"
																style="width: 70px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">商品编号</th>
															<th aria-label="Rep : activate to sort column ascending"
																style="width: 50px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">申请编号</th>
															<th aria-label="Item : activate to sort column ascending"
																style="width: 180px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">商品名称</th>
															<th aria-label="Item : activate to sort column ascending"
																style="width: 60px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">Need价</th>
															<th aria-label="Item : activate to sort column ascending"
																style="width: 60px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">恢复价格</th>
															<th aria-label="Item : activate to sort column ascending"
																style="width: 60px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">采购价</th>
															<th aria-label="Item : activate to sort column ascending"
																style="width: 60px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">毛利</th>
															<th aria-label="Item : activate to sort column ascending"
																style="width: 80px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">启用状态</th>
															<th aria-label="Item : activate to sort column ascending"
																style="width: 70px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">审核状态</th>
															<th aria-label="Item : activate to sort column ascending"
																style="width: 60px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">启用类型</th>
															<th aria-label="Item : activate to sort column ascending"
																style="width: 120px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">生效时间段</th>
															<th
																aria-label="Units : activate to sort column ascending"
																style="width: 50px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">修改人</th>
															<th
																aria-label="Units : activate to sort column ascending"
																style="width: 80px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">修改部门</th>
															<th
																aria-label="Units : activate to sort column ascending"
																style="width: 130px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">修改时间</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${list}" var="goods" varStatus="status">
															<tr class="odd" role="row">
																<td>${goods.goodsCode}</td>
																<td><a href="/goodsNew/goodsPriceDetails?pricechangeId=${goods.pricechangeId}">${goods.pricechangeId}</a></td>
																<td>${goods.goodsName}</td>
																<td>${goods.discountPrice}</td>
																<td>${goods.originalPrice}</td>
																<td>${goods.purchasePrice}</td>
																<td>${goods.profit}</td>
																<td><c:choose>
																		<c:when test="${goods.excuted=='NEITHER'}">
										                                	未启用
										                              </c:when>
																		<c:when test="${goods.excuted=='STARTED'}">
										                                	已启用
										                              </c:when>
										                              <c:when test="${goods.excuted=='ENDED'}">
										                                	恢复价格
										                              </c:when>
																		<c:otherwise>
									                                	作废
									                             	 </c:otherwise>
																	</c:choose>
																</td>
																<td><c:choose>
																	<c:when test="${goods.pricechangeStatus=='DRAFT'}">
										                               	待审核
										                            </c:when>
										                            <c:when test="${goods.pricechangeStatus=='VALID'}">
										                               	审核成功
										                            </c:when>
																	<c:otherwise>
										                                                                        驳回
										                             </c:otherwise>
																	</c:choose>
																</td>
																<td><c:choose>
																		<c:when test="${goods.pricechangeType=='EXCUTE'}">
																			立即启用
																		</c:when>
																		<c:otherwise>
																			时间段启用
																		</c:otherwise>
																	</c:choose></td>
																<td><c:choose>
																		<c:when test="${goods.pricechangeType=='EXCUTE'}">
																			&nbsp;
																		</c:when>
																		<c:otherwise>
																			<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${goods.startTime}" type="both" />
																			<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${goods.endTime}" type="both" />
																		</c:otherwise>
																	</c:choose></td>
																<td>${goods.userName}</td>
																<td>${goods.userDept}</td>
																<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${goods.userTime}" type="both" /></td>
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
									</div>
								</div>
							</div>
						</div>
					<!-- 2222222 --> --%>
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
		$('#light-pagination').pagination({
			pages : "${page.pageCount}",
			cssStyle : 'light-theme',
			currentPage : "${page.page}",
			onPageClick : function(pageNumber, event) {
				var goodsCode = "${page.goodsCode}";
				var goodsBarcode = "${page.goodsBarcode}";
				var goodsName = "${page.goodsName}";
				var userId = "${page.userId}";
				window.location.href = 'goodsPriceDetailsList?page=' + pageNumber 
						+ "&goodsCode=" + goodsCode
						+ "&goodsBarcode=" + goodsBarcode
						+ "&goodsName=" + goodsName
						+ "&userId=" + userId;
			}
		});
	</script>

	<script type="text/javascript">
	$(function(){
		$("#searchGoodsPriceList").click(function(){
			if($("#form_goodsCode").val().trim() == ''
				&& $("#form_goodsBarcode").val().trim() == ''
				&& $("#form_goodsName").val().trim() == ''
				&& $("#form_userId").val().trim() == ''){
				return ;
			}
			else{
				$("#searchGoodsPriceListContent").submit();
			}
		});
	});
	</script>

	<script type="text/javascript">
	$(function(){
		$("#form_goodsCode").val("${page.goodsCode}");
		$("#form_goodsBarcode").val("${page.goodsBarcode}");
		$("#form_goodsName").val("${page.goodsName}");
		$("#form_userId").find("option[value='${page.userId}']").attr("selected", true);
	});
	</script>
</body>
</html>