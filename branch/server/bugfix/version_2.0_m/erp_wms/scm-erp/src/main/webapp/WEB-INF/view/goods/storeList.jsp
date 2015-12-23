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
<!-- 分页插件 css 样式 -->
<link rel="stylesheet" href="/resources/css/pagination.css" />
<link rel="stylesheet" href="/resources/css/list-content2.css">
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
				            <h3 class="m-b-less">库存管理</h3>
				            <div class="state-information">
				                <ol class="breadcrumb m-b-less bg-less">
				                    <li><a href="#">主页</a></li>
				                    <li><a href="#">采销管理</a></li>
				                    <li class="active">商品库存列表</li>
				                </ol>
				            </div>
				        </div>
				
				        <div class="info">
				            <div class="info-top">
				                <span>商品库存列表</span>
				                <div class="back">
				                    <a href="/store/page?page=1" class="button button-primary button-raised button-small">刷新</a>
				                    <a href="javascript:;" class="button button-primary button-raised button-small" onclick="daochuStore();">导出</a>
				                </div>
				            </div>
				            <div class="search-form">
				            <form class="form-inline" method="get" action="/store/page" id="goodsStoreContent">
								
		    						<div class="row">
					                    <div class="form-group col-xs-3">   
					                        <label for="form_goodsCode">商品编号</label>                     
					                        <input type="text" class="form-control" id="form_goodsCode" name="goodsCode">
					                    </div>
					                    <div class="form-group col-xs-3">  
					                        <label for="form_goodsBarcode">国际条形码</label>                       
					                        <input type="text" class="form-control" id="form_goodsBarcode" name="goodsBarcode">
					                    </div>					                    
				                    </div>
		    						<div class="row">	
				                    <div class="form-group col-xs-3">  
					                        <label for="form_goodsName">商品名称</label>                       
					                        <input type="text" class="form-control" id="form_goodsName" name="goodsName">
					                 </div>
					                <div class="form-group col-xs-3" >
					                 <label for="form_warehouse_type">仓库类型</label> 
			    						<select class="form-control" id="form_warehouse_type" name="warehouseType">
			    							<option value="">全部</option>
			    							<option value="SELF_WAREHOUSE">自营仓</option>
			    							<option value="TAX_WAREHOUSE">保税仓</option>
			    							<option value="OVERSEA_WAREHOUSE">海外直邮</option>
			    						</select>
		    						</div>
		    						
		    						<a href="javascript:void(0);" id="searchGoodsStore" class="button button-primary button-raised button-small"><i class="fa fa-search"></i>&nbsp;&nbsp;搜索</a>
		    						</div>
		    					
		    				</form>
				            </div>
				            <!--biaoge-->
				            <table class="table table-bordered table-hover table-condensed table-responsive">				
				                <thead>
				                <tr>
				                    <th>商品主图</th>
				                    <th>商品编号</th>
				                    <th>国际条形码</th>
				                    <th>商品名称</th>
				                    <th>仓库类型</th>
				                    <th>原价</th>
				                    <th>Need价</th>
				                    <th>正品库存</th>
				                    <th>残品库存</th>
				                    <th>冻结数</th>
				                    <th>订单出库数</th>
				                    <th>操作</th>
				                </tr>
				                </thead>
				                <tbody>
				                <c:forEach items="${list}" var="goods" varStatus="status">
										<tr>
											<td class="sorting_1"><img width="60px" alt="商品图片"
												src="${picAddress}${goods.scenePicKey}">
											</td>
											<td style="text-align:left;">${goods.goodsCode}</td>
											<td style="text-align:left;">${goods.goodsBarcode}</td>
											<td style="text-align:left;">${goods.goodsName}</td>
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
											<td>${goods.nowStoreCount}</td>
											<td>${goods.defectiveStore}</td>
											<td>${goods.lockedCount}</td>
											<td>${goods.onWayCount}</td>

											<td>
				                            <c:if test="${goods.goodsType=='SINGLE'}">
				                             	<a href="/store/storeDetail?goodsId=${goods.goodsId}&page=${page.page}">
													添加库存 
												</a>|
											</c:if>
												<a href="/store/log/stores?goodsId=${goods.goodsId}&page=${page.page}">
													库存历史记录
												</a>
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
				var warehouseType = "${page.warehouseType}";
				window.location.href = 'page?page=' + pageNumber
						+ "&goodsCode=" + goodsCode
						+ "&goodsBarcode=" + goodsBarcode
						+ "&goodsName=" + goodsName
						+ "&warehouseType=" + warehouseType;
			}
		});
		function daochuStore(){
			window.location.href = "/store/toExportPage";
			/* autoDowload("/store/export"); */
		}
		
		$(function(){
			$("#searchGoodsStore").click(function(){
				if($("#form_goodsCode").val().trim() == '' 
						&& $("#form_goodsBarcode").val().trim() == ''
						&& $("#form_goodsName").val().trim() == ''
						&& $("#form_warehouse_type").val().trim() == ''){
					return;
				}
				else{
					$("#goodsStoreContent").submit();
				}
			});
			
			$(window).bind("keydown",function(e){
	            var key = e.which;
	            if (key == 13) {
	                $("#searchGoodsStore").click();
	            }
	        })
		});
	</script>
	<script type="text/javascript">
		$(function(){
			$("#form_goodsCode").val("${page.goodsCode}");
			$("#form_goodsBarcode").val("${page.goodsBarcode}");
			$("#form_goodsName").val("${page.goodsName}");
			$("#form_warehouse_type").find("option[value='${page.warehouseType}']").attr("selected", true);
		});
	</script>
</body>
</html>