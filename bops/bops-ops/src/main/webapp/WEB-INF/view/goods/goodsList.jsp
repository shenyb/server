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

<title>运营管理系统</title>

<!-- 主体部分样式表 -->
<link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/bootstrap/button.css" >
<link rel="stylesheet" href="/resources/css/font-awesome.min.css">
<link rel="stylesheet" href="/resources/css/layout.css">
<link rel="stylesheet" href="/resources/css/ops.css">
<link rel="stylesheet" href="/resources/css/sidebar.css">
<link rel="stylesheet" href="/resources/css/content-header.css">
<!-- <link rel="stylesheet" href="/resources/css/data  page.css"> -->
 <link rel="stylesheet" href="/resources/css/list-content2.css">
 <link rel="stylesheet" href="/resources/css/modal.css" >

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
				            <div class="state-information">
				                <ol class="breadcrumb m-b-less bg-less">
				                    <li><a href="#">主页</a></li>
				                    <li><a href="#">商品管理</a></li>
				                    <li class="active">商品列表</li>
				                </ol>
				            </div>
				        </div>
				
				        <div class="info">
				            <div class="info-top">
				                <span>商品列表</span>					             
				                <div class="back"> 					                   				                 
				                  	 <a href="javascript:void(0);" id="importCategory" class="bops-pub-btn button button-rounded button-tiny">&nbsp;&nbsp;批量导入商品分类</a>
				                 </div>		                
				            </div>
				            <div class="search-form">
				                <form class="form-inline" id="searchGoodsContent" method="get" action="/goods/page">
				                    <div class="row">
					                    <div class="form-group col-xs-3">
					                        <label for="form_goodsCode">商品编号</label>
					                        <input type="text" id="form_goodsCode" name="goodsCode" class="form-control" tabindex="1">
					                    </div>
					                    <div class="form-group col-xs-3">
					                        <label for="form_goodsBarcode">国际条形码</label>					                       
					                        <input type="text" id="form_goodsBarcode" name="goodsBarcode" class="form-control" tabindex="2">
					                    </div>   
					                    <div class="form-group col-xs-3">
					                        <label for="form_goodsName">商品名称</label>					                       
					                        <input type="text" id="form_goodsName" name="goodsName" class="form-control" tabindex="3">
					                    </div> 
					                    
					                 </div>
					                 <div class="row">    
					                 <div class="form-group col-xs-3">
					                        <label for="form_auditStatus">审核状态</label>					                       
					                        <select name="auditStatus" id="form_auditStatus" class="form-control" tabindex="4">
				    							<option selected="selected" value="">全部</option>
				    							<option value="WAIT_AUDIT">待审核</option>
				    							<option value="SUCCESS">通过</option>
				    							<option value="FAIL">未通过</option>
				    						</select>
					                    </div>
					                    <div class="form-group col-xs-3">
					                        <label for="form_goodsStatus">上下架状态</label>					                       
					                       <select id="form_goodsStatus" name="goodsStatus" class="form-control" tabindex="5">
				    							<option value="">全部</option>
				    							<option value="ONLINE">上架</option>
				    							<option value="OFFLINE">下架</option>
				    						</select>
					                    </div> 
					                    <div class="form-group col-xs-3">
					                        <label for="form_warehouseType">仓库类型</label>					                       
					                       <select id="form_warehouseType" name="warehouseType" class="form-control" tabindex="6">
				    							<option value="">全部</option>
				    							<option value="SELF_WAREHOUSE">自营仓</option>
				    							<option value="TAX_WAREHOUSE">保税仓</option>
				    							<option value="OVERSEA_WAREHOUSE">海外直邮</option>
				    						</select>
					                    </div> 
					                   <!--    <div class="form-group col-xs-3"> 					                   				                 
				                  		 <a href="javascript:void(0);" id="searchGoods" class="button button-primary button-raised button-small"><i class="fa fa-search"></i>&nbsp;&nbsp;搜索</a>
				                  		</div> -->
				                    </div>
				                    <div class="row">    
					                 
					                    <div class="form-group col-xs-3">
					                        <label for="form_goodsStatus">商品一级分类</label>					                       
					                       <select  name="goodsIndexCategoryOne" id="goodsIndexCategoryOne" class="form-control" tabindex="5">
				    							<option value="">全部</option>
				    							<c:forEach  items="${categorys1}" var="category1" varStatus="status">
				    							<option value="${category1.categoryId}" <c:if test="${page.goodsIndexCategoryOne==category1.categoryId}">selected</c:if>>${category1.categoryName}</option>
				    							</c:forEach>
				    						</select>
				    						
					                    </div> 
					                     <div class="form-group col-xs-3">
					                        <label for="form_goodsStatus">商品二级分类</label>					                       
					                       <select  name="goodsIndexCategory" id="goodsIndexCategory"  class="form-control" tabindex="5">
				    							<c:if  test="${page.goodsIndexCategory !='' and page.goodsIndexCategory != null}">
				    							<c:forEach  items="${categorys2}" var="category2" varStatus="status">
				    							<option value="${category2.categoryId}" <c:if test="${page.goodsIndexCategory==category2.categoryId}">selected</c:if>>${category2.categoryName}</option>
				    							</c:forEach>
				    							</c:if>
				    						</select>
				    						
					                    </div> 
					                      <div class="form-group col-xs-3"> 					                   				                 
				                  		 <a href="javascript:void(0);" id="searchGoods" class="button button-primary button-raised button-small"><i class="fa fa-search"></i>&nbsp;&nbsp;搜索</a>
				                  		</div>
				                    </div>
				                    
				                </form>
				            </div>
				            <!--biaoge-->
				            <table class="table table-bordered table-striped table-condensed table-responsive">
				                <thead>
				                <tr>
				                	<th>商品主图</th>
				                    <th>商品编号</th>
				                    <th>国际条形码</th>
				                    <th>商品名称</th>				                    		                  
				                    <th>仓库类型</th>
				                    <th>原价</th>
				                    <th>折扣价</th>
				                    <th>上下架状态</th>
				                    <th>审核状态</th>				                    		                  
				                    <th>操作</th>				                    
				                </tr>
				                </thead>
			                <tbody>
							      <c:forEach items="${list}" var="goods" varStatus="status">
										<tr class="odd" role="row">
											<td class="sorting_1">
											 <c:if test="${!empty goods.scenePicKey}">
											  <img alt="商品图片" src="${picAddress}${goods.scenePicKey}">
											 </c:if>
											 <c:if test="${empty goods.scenePicKey}">
											   未上传图
											 </c:if>
											</td>
											<td>${goods.goodsCode}</td>
											<td>${goods.goodsBarcode}</td>
											<td>${goods.goodsName}</td>
											<td><c:choose>
													<c:when test="${goods.warehouseType=='TAX_WAREHOUSE'}">保税仓</c:when>
						                            <c:when test="${goods.warehouseType=='SELF_WAREHOUSE'}">自营仓</c:when>
													<c:otherwise>海外直邮</c:otherwise>
												</c:choose>
											</td>
											<td>${goods.onsalePrice}</td>
											<td>${goods.discountPrice}</td>
											<td><c:choose>
													<c:when test="${goods.goodsStatus=='ONLINE'}">上架</c:when>
													<c:otherwise>下架</c:otherwise>
													</c:choose>
											</td>
											
											<td><c:choose>
													<c:when test="${goods.auditStatus=='WAIT_AUDIT'}">待审核</c:when>
													<c:when test="${goods.auditStatus=='SUCCESS'}">通过 </c:when>
													<c:otherwise>驳回 </c:otherwise>
												</c:choose>
											</td>

											<td>
											
											<a href="/goods/goodsProfile?goodsId=${goods.goodsId}&page=${page.page}">查看</a>
											<a href="/goods/goodsProfileEdit?goodsId=${goods.goodsId}&page=${page.page}">| 编辑</a>
										
											</td>
										</tr>
									</c:forEach>
				                </tbody>
				            </table>
				             <div class="page">					               
								<div class="pagenum">
									<div aria-live="polite" role="status" id="DataTables_Table_0_info"class="dataTables_info">
										共有 ${page.total}条, 每页显示  ${page.pageSize} 条 
									</div> 
								</div>								
								<div class="tbl-pagin pull-right">
									<div id="light-pagination"></div>
								</div>
				            </div>
				        </div>
				    </div> 
                        
					<%-- <!-- 3333333333 -->
						<div class="data-page-inner">
							<div class="page-head">
								<!-- 页面标题 -->
								<h3 class="m-b-less">商品列表</h3>
								<!--面包屑导航-->
								<div class="state-information">
									<ol class="breadcrumb m-b-less bg-less">
										<li><a href="#">主页</a></li>
										<li><a href="#">商品管理</a></li>
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
												商品列表 <!-- <span class="pull-right"> <a class="btn btn-info"
													 href="/goods/toAddNewGoodsPage">新增商品</a> <a
													class="btn btn-info" href="javascript:;" id="exportGoods">导出商品</a>
												</span> -->
											</header>
											<div
												class="dataTables_wrapper form-inline dt-bootstrap no-footer"
												id="DataTables_Table_0_wrapper">
												<form id="searchGoodsContent" method="get" action="/goods/page">
												<div class="tbl-head">
						    						<input type="text" id="form_goodsCode" name="goodsCode" placeholder="商品编号">
						    						<input type="text" id="form_goodsBarcode" name="goodsBarcode" placeholder="国际条形码">
						    						<input type="text" id="form_goodsName" name="goodsName" placeholder="商品名称">
						    						<select name="auditStatus" id="form_auditStatus">
						    							<option selected="selected" value="">审核状态</option>
						    							<option value="WAIT_AUDIT">待审核</option>
						    							<option value="SUCCESS">通过</option>
						    							<option value="FAIL">未通过</option>
						    						</select>
						    						<!-- <select name="isGlobal" id="form_isGlobal" >
						    							<option selected="selected" value="">是否为保税仓</option>
						    							<option value="TRUE">是</option>
						    							<option value="FALSE">否</option>
						    						</select> -->
						    						<select id="form_goodsStatus" name="goodsStatus">
						    							<option value="">上下架状态</option>
						    							<option value="ONLINE">上架</option>
						    							<option value="OFFLINE">下架</option>
						    						</select>
						    						<select id="form_warehouseType" name="warehouseType">
						    							<option value="">仓库类型</option>
						    							<option value="SELF_WAREHOUSE">自营仓</option>
						    							<option value="TAX_WAREHOUSE">保税仓</option>
						    							<option value="OVERSEA_WAREHOUSE">海外直邮</option>
						    						</select>
						    						<a href="javascript:void(0);" id="searchGoods" class="btn btn-info">搜索</a>
						    					</div>
						    					</form>
												<table aria-describedby="DataTables_Table_0_info"
													role="grid" id="DataTables_Table_0"
													class="table convert-data-table data-table dataTable no-footer">
													<thead>
														<tr role="row">
															<th
																aria-label="OrderDate : activate to sort column descending"
																aria-sort="ascending" style="width: 80px;" colspan="1"
																rowspan="1" aria-controls="DataTables_Table_0"
																tabindex="0" class="sorting_asc">商品主图</th>
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
																style="width: 70px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">仓库类型</th>
															<th aria-label="Item : activate to sort column ascending"
																style="width: 70px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">原价</th>
															<th aria-label="Item : activate to sort column ascending"
																style="width: 70px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">折扣价</th>
															<th aria-label="Item : activate to sort column ascending"
																style="width: 50px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">上下架状态</th>
															<th aria-label="Item : activate to sort column ascending"
																style="width: 60px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">审核状态</th>
															<th
																aria-label="Units : activate to sort column ascending"
																style="width: 105px;" colspan="1" rowspan="1"
																aria-controls="DataTables_Table_0" tabindex="0"
																class="sorting">操作</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${list}" var="goods" varStatus="status">
															<tr class="odd" role="row">
																<td class="sorting_1">
																 <c:if test="${!empty goods.scenePicKey}">
																  <img width="60px" alt="商品图片"
																	src="${picAddress}${goods.scenePicKey}">
																 </c:if>
																 <c:if test="${empty goods.scenePicKey}">
																   未上传图
																 </c:if>
																</td>
																<td>${goods.goodsCode}</td>
																<td>${goods.goodsBarcode}</td>
																<td>${goods.goodsName}</td>
																<td><c:choose>
																		<c:when test="${goods.warehouseType=='TAX_WAREHOUSE'}">
											                                	保税仓
											                              </c:when>
											                              <c:when test="${goods.warehouseType=='SELF_WAREHOUSE'}">
											                                	自营仓
											                              </c:when>
																		<c:otherwise>
										                                	海外直邮
										                              </c:otherwise>
																	</c:choose></td>
																<td>${goods.onsalePrice}</td>
																<td>${goods.discountPrice}</td>
																<td><c:choose>
																		<c:when test="${goods.goodsStatus=='ONLINE'}">
                                	上架
                              </c:when>
																		<c:otherwise>
                                	下架
                              </c:otherwise>
																	</c:choose></td>
																
																<td><c:choose>
																		<c:when test="${goods.auditStatus=='WAIT_AUDIT'}">
                                	待审核
                              </c:when>
																		<c:when test="${goods.auditStatus=='SUCCESS'}">
                                	通过
                              </c:when>
																		<c:otherwise>
                                	驳回
                              </c:otherwise>
																	</c:choose></td>

																<td>
																
																<a href="/goods/goodsProfile?goodsId=${goods.goodsId}&page=${page.page}">查看</a>
																<a href="/goods/goodsProfileEdit?goodsId=${goods.goodsId}&page=${page.page}">| 编辑</a>
															
																<c:if test="${goods.auditStatus=='WAIT_AUDIT'}">
																<a href="javascript:void(0);"onclick="showShenheDialog('${goods.goodsId}', '${fn:replace(goods.goodsName, '\'' , '')}', '${goods.goodsCode}', '${goods.scenePicKey}')">| 审核</a>
																</c:if>
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
									</div>
								</div>
							</div>
						</div>
					<!-- 33333333 --> --%>
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
	
	
	<div class="modal fade" id="editGoodsModalDiv" tabindex="-1" role="dialog"
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close"
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               商品审核
            </h4>
         </div>
         <div class="modal-body">
            <div >
            	<input id="div_goodsId" value="" type="hidden">
            	商品名称: 
            	<input type="text" id="div_goodsName" value="" readonly="readonly" style="width:400px;">
            </div>
            <div style="margin : 10px 0px 10px 0px;">
            	商品编码:
            	<input type="text" id="div_goodsCode" value="" readonly="readonly" >
            </div>
            <div>
            	商品图片:
            	<img src="" width="60px" id="div_scenePicKey">
            </div>
         </div>
         <div class="modal-footer">
            <button type="button" onclick="shenhe('SUCCESS');" class="btn btn-primary">
               	审核通过
            </button>
            <button type="button" onclick="shenhe('FAIL');" class="btn btn-primary">
               	审核驳回
            </button>
            <button type="button" class="btn btn-default"
               data-dismiss="modal">关闭
            </button>
         </div>
      </div><!-- /.modal-content -->
</div>
</div>


<div class="modal fade" id="exportGoodsDialogDiv" tabindex="-1" role="dialog"
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close"
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               商品导出
            </h4>
         </div>
         <form id="exportGoodsContent_form">
         <div class="modal-body">
            <div>
            	审核状态: 
            	<label for="label_auditStatus_success">
            	<input type="checkbox" id="label_auditStatus_success" name="auditStatus" value="SUCCESS" checked="checked" > 通过
            	</label>
            	<label for="label_auditStatus_fail">
            	<input type="checkbox" id="label_auditStatus_fail" name="auditStatus" value="FAIL" checked="checked" > 未通过 
            	</label>
            	<label for="label_auditStatus_waitAudit">
            	<input type="checkbox" id="label_auditStatus_waitAudit" name="auditStatus" value="WAIT_AUDIT" checked="checked" > 待审核
            	</label>
            </div>
            <div>
            	商品状态:
            	<label for="label_goodsStatus_online"> 
            	<input type="checkbox" id="label_goodsStatus_online" name="goodsStatus" value="ONLINE" checked="checked" > 上架 
            	</label>
            	<label for="label_goodsStatus_offline">
            	<input type="checkbox" id="label_goodsStatus_offline" name="goodsStatus" value="OFFLINE" checked="checked" > 下架
            	</label>
            </div>
            <div>
            	导出条数:
            	<select name="pageSize">
            		<option value="">全部</option>
            		<option value="100"> 100 </option>
            	</select>
            </div>
         </div>
         </form>
         <div class="modal-footer">
            <button type="button" onclick="daochuGoods();" class="btn btn-primary">
               	导出 
            </button>
            <button type="button" id="close_exportGoods" class="btn btn-default"
               data-dismiss="modal">关闭
            </button>
         </div>
      </div><!-- /.modal-content -->
</div>
</div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		                  &times;
		            </button>
		            <h4 class="modal-title" id="myModalLabel">
		             商品批量分类
		            </h4>
		         </div>
		         <div class="modal-body">
		         <form class="form-horizontal" id="importForm" action="/goods/importGoodsCategory" method="post">
				    	<fieldset>
					    	<div class="control-group">
	                              <!-- Text input-->
	                              <label class="control-label col-lg-4" for="categoryName" style="width:130px">商品一级分类</label>                              
	                             <select  id="goodsIndexCategoryOne-modal"  class="form-control" tabindex="5">
				    							<option value="">请选择商品分类</option>
				    							<c:forEach  items="${categorys1}" var="category" varStatus="status">
				    							<option value="${category.categoryId}">${category.categoryName}</option>
				    							</c:forEach>
				    						</select>
	                        </div>
	                        <div class="control-group">
	                              <!-- Text input-->
	                              <label class="control-label col-lg-4" for="categoryName" style="width:130px">商品二级分类</label>                              
	                             <select  id="goodsIndexCategory-modal" name="goodsIndexCategory" class="form-control" tabindex="5">
				    							
				    						</select>
	                        </div>
	                       
	                        <div class="control-group">
	                              <!-- Text input-->
	                              <label class="control-label col-lg-4" for=""  style="width:130px;">商品编号</label>                              
	                              <div >		                              	
										<textarea  placeholder="请填写商品编号，按英文逗号分隔！" class="form-control" name="goodsCodes" id="goodsCodes" rows="3"></textarea>                             
	                              </div>
	                              <div style="clear: both;"></div>
	                        </div>
	                        <!-- <div class="control-group">
	                              Text input
	                              <label class="control-label col-lg-4" for=""  style="width:100px">图片预览</label>                              
	                              <div class="controls col-lg-5">	
	                              	<div id="imageUpload" class="imgbox">
										ngRepeat: img in imageList
										<img alt="pic" style="display:none;"  id="kolCategoryImg" src="" />
										<input type="text" id="fileName" name="categoryProfileKey" style="visibility:hidden" />
									</div>	                              
	                              </div>
	                              <div style="clear: both;"></div>
	                        </div> -->
	                   	</fieldset>
	               </form>
		          
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="button button-capitalize button-rounded button-primary button-small" data-dismiss="modal">取消 </button>
		            <button type="button" onclick="submit()"  class="button button-capitalize button-rounded button-primary button-small">
		               提交
		            </button>
		         </div>
		      </div><!-- /.modal-content -->
		</div><!-- /.modal -->
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
				var auditStatus = "${page.auditStatus}";
				var warehouseType = "${page.warehouseType}";
				var goodsBarcode = "${page.goodsBarcode}";
				var goodsName = "${page.goodsName}";
				var goodsStatus = "${page.goodsStatus}";
				var goodsIndexCategory= "${page.goodsIndexCategory}";
				window.location.href = 'page?page=' + pageNumber 
						+ "&goodsCode=" + goodsCode
						+ "&auditStatus=" + auditStatus
						+ "&warehouseType=" + warehouseType
						+ "&goodsBarcode=" + goodsBarcode
						+ "&goodsName=" + goodsName
						+ "&goodsStatus=" + goodsStatus
						+ "&goodsIndexCategory=" +goodsIndexCategory;
			}
		});
		/** 
		 *	上架商品
		 */
		function shangjia(goodsId) {
			$.messager.confirm("上架商品", "您确定要上架该商品？", function() {
				$.ajax({
					type : "post",
					url : "/goods/online/",
					dataType : "json",
					data : {
						goodsId : goodsId,
						goodsStatus : "ONLINE"
					},
					success : function(data) {
						//alert("code: " + data.code + "\n" + data.msg);
						if(data.code == 200){
							window.location.href = "/goods/page?page="
								+ $("#pageNumber").val();
						}
						else{
							alert("code: " + data.code + "\n" + data.msg);
						}
					}
				});
			});
		}
		/**
		 *	下架商品
		 */
		function xiajia(goodsId) {
			$.messager.confirm("下架商品", "您确定要下架该商品？", function() {
				$.ajax({
					type : "post",
					url : "/goods/offline/",
					datatype : "json",
					data : {
						goodsId : goodsId,
						goodsStatus : "OFFLINE"
					},
					success : function(data) {
						if(data.code == 200){
							window.location.href = "/goods/page?page="
								+ $("#pageNumber").val();
						}
						else{
							alert("code: " + data.code + "\n" + data.msg);
						}
					}
				});
			});
		}
		function shenhe(auditStatus) {
			var goodsId = $("#div_goodsId").val();
			$.ajax({
				type : "post",
				url : "/goods/auditGoods/",
				datatype : "json",
				data : {
					goodsId : goodsId,
					auditStatus : auditStatus
				},
				success : function(data) {
					//alert("code: " + data.code + "\n" + data.msg);
					window.location.href = "/goods/page?page="
							+ $("#pageNumber").val();
				}
			})
		}
		
		function showShenheDialog(goodsId, goodsName, goodsCode, scenePicKey){
			$("#div_goodsId").val(goodsId);
			$("#div_goodsName").val(goodsName);
			$("#div_goodsCode").val(goodsCode);
			$("#div_scenePicKey").attr("src", "${picAddress}" + scenePicKey);
			$("#editGoodsModalDiv").modal("show");
		}
		
	</script>
	
	<script type="text/javascript">
		$("#form_goodsCode").val("${page.goodsCode}");
		$("#form_auditStatus").find("option[value='${page.auditStatus}']").attr("selected", true);
		$("#form_warehouseType").find("option[value='${page.warehouseType}']").attr("selected", true);
		$("#form_goodsStatus").find("option[value='${page.goodsStatus}']").attr("selected", true);
		$("#form_goodsBarcode").val("${page.goodsBarcode}");
		$("#form_goodsName").val("${page.goodsName}");
	</script>
	
	<script type="text/javascript">
	
		function daochuGoods(){
			//alert("H");
			var param = $("#exportGoodsContent_form").serializeArray();
			console.info(param);
			var exportParam_auditStatus = '';
			var exportParam_goodsStatus = '';
			var exportParm_pageSize = '';
			
			for(var i in param){
				//tempArr.push(param[i].name)
				if(param[i].name == 'auditStatus'){
					exportParam_auditStatus = exportParam_auditStatus + param[i].value + ",";
				}
				if(param[i].name == "goodsStatus"){
					exportParam_goodsStatus = exportParam_goodsStatus + param[i].value + ",";
				}
				if(param[i].name == "pageSize"){
					exportParm_pageSize = exportParm_pageSize + param[i].value;
				}
			}
			if(exportParam_auditStatus.charAt(exportParam_auditStatus.length-1) == ','){
				exportParam_auditStatus = exportParam_auditStatus.substring(0, exportParam_auditStatus.length-1);
			}
			if(exportParam_goodsStatus.charAt(exportParam_goodsStatus.length-1) == ','){
				exportParam_goodsStatus = exportParam_goodsStatus.substring(0, exportParam_goodsStatus.length-1);
			}
			var url = "/goods/export?";
			url = url + "auditStatus=" + exportParam_auditStatus
					+ "&goodsStatus=" +  exportParam_goodsStatus
					+ "&pageSize=" + exportParm_pageSize;
			/* console.info(exportParam_auditStatus);
			console.info(exportParam_goodsStatus);
			console.info(exportParm_pageSize);
			console.info(url); */
			autoDowload(url);
			$("#close_exportGoods").trigger("click");
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
	
		$(function(){
			$("#searchGoods").click(function(){
				/* if($("#form_goodsCode").val().trim() == '' 
						&& $("#form_auditStatus").val().trim() == '' 
						&& $("#form_warehouseType").val().trim() == ''
						&& $("#form_goodsBarcode").val().trim() == ''
						&& $("#form_goodsName").val().trim() == ''
						&& $("#form_goodsStatus").val().trim() == ''){
					return;
				}
				else{ */
					$("#searchGoodsContent").submit();
			//	}
			});
			
			$("#importCategory").click(function(){
				$('#myModal').modal('show') 
			});
			
			$("#exportGoods").click(function(){
				/* alert("H"); */
				$("#exportGoodsDialogDiv").modal("show");
				//autoDowload("/goods/export?goodsStatus=ONLINE,OFFLINE&auditStatus=SUCCESS,FAIL,WAIT_AUDIT");
			});
			//回车
	          $(window).bind("keydown",function(e){
		            var key = e.which;
		            if (key == 13) {
		                $("#searchGoods").click();
		            }
		        })
		});
		
		function submit(){
			
			if($("#goodsCodes").val()==''|| $("#goodsCodes").val()== null){
				alert("请填写商品编号，用英文逗号分隔！");
				return;
			}
			$.ajax({
				type : "post",
				url : "/goods/importGoodsCategory",
				datatype : "json",
				data : $("#importForm").serialize(),
				success : function(data) {
					alert(data.msg);
					if(data.code!=200){
						return;
					}
					if(data.data.badGoodsCode!= '' && data.data.badGoodsCode!= null){
						alert("失败的商品编号："+data.data.badGoodsCode);
					}
					
					 window.location.reload();
				}
			})
		}
		
		//二级联动
		$('#goodsIndexCategoryOne').click(function(){
		 var parentId =$("#goodsIndexCategoryOne").val();
		 $("#goodsIndexCategory option").remove();
	  	  $.ajax({
			     type: 'GET',
			     url: '/indexCategory/getPreLevel',
			     data: {'parentId':parentId},
			     dataType: 'json',
			     success : function(data) {
	            if(data.code==200){
	            	/*  var $option1 = $("<option>全部</option>");
	            	 $("#goodsIndexCategory").append($option1);  */
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
		//二级联动
		$('#goodsIndexCategoryOne-modal').click(function(){
		 var parentId =$("#goodsIndexCategoryOne-modal").val();
		 $("#goodsIndexCategory-modal option").remove();
	  	  $.ajax({
			     type: 'GET',
			     url: '/indexCategory/getPreLevel',
			     data: {'parentId':parentId},
			     dataType: 'json',
			     success : function(data) {
	            if(data.code==200){
	            	/*  var $option1 = $("<option>全部</option>");
	            	 $("#goodsIndexCategory").append($option1);  */
	            	 $.each(data.data.preList, function(key, val) {
	            		 var $option = $("<option></option>");
	            			$option.attr("value",val.categoryId);
	            			$option.text(val.categoryName);
	            			$("#goodsIndexCategory-modal").append($option);
	            		 
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