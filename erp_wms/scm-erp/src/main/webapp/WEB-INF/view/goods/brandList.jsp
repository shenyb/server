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
<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" name="viewport">
<!-- 强制让文档的宽度与设备的宽度保持1:1，并且文档最大的宽度比例是1.0，且不允许用户点击屏幕放大浏览 -->
<meta content="yes" name="apple-mobile-web-app-capable" />
<!-- iphone设备中的safari私有meta标签，它表示：允许全屏模式浏览 -->
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<!-- iphone的私有标签，它指定的iphone中safari顶端的状态条的样式 -->
<meta content="telephone=no" name="format-detection" />
<!-- 告诉设备忽略将页面中的数字识别为电话号码 -->
<meta content="email=no" name="format-detection" />
<!-- android设备忽略将页面中的email识别为电子邮箱 -->
<link rel="shortcut icon" href="/resources/img/common/favicon.ico" type="image/x-icon" />
<title>ERP管理系统</title>

<!-- 主体部分样式表 -->
<link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/bootstrap/button.css">
<link rel="stylesheet" href="/resources/css/font-awesome.min.css">
<link rel="stylesheet" href="/resources/css/layout.css">
<link rel="stylesheet" href="/resources/css/ops.css">
<link rel="stylesheet" href="/resources/css/sidebar.css">
<link rel="stylesheet" href="/resources/css/content-header.css">
<link rel="stylesheet" href="/resources/css/data page.css">

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
					            <h3 class="m-b-less">商品品牌列表</h3>
					            <div class="state-information">
					                <ol class="breadcrumb m-b-less bg-less">
					                    <li><a href="#">主页</a></li>
					                    <li><a href="#">采销管理</a></li>
					                    <li class="active">品牌列表</li>
					                </ol>
					            </div>
					        </div>					
					        <div class="info">
					            <div class="info-top">
					                <span>品牌列表</span>
					                <div class="back">
					                    <a href="/brand/toAddBrand" class="button button-primary button-raised button-small">新增品牌</a>
					                    <a href="javascript:;" id="exportBrand" onClick="daochuBrand()" class="button button-primary button-raised button-small">导出品牌</a>
					                </div>
					            </div>
					            <div class="search-form">
					                <form class="form-inline" id="searchForm" action="/brand/page" method="get">
					                    <div class="row">
					                    <div class="form-group col-xs-3">   
					                        <label for="brandName" class="">品牌名称  </label>                  
					                        <input type="text" class="form-control" id="brandName" name="brandName" tabindex="1"
												value="${page.brandName}">
												 
					                    </div>
					                    <div class="form-group col-xs-3">  
					                        <label for="brandId" class="">品牌ID </label>                     
					                        <input type="text" style="ime-mode:disabled" onKeyUp="this.value=this.value.replace(/[^\.\d]/g,'');this.value=this.value.replace('.','');" class="form-control" id="brandId" name="brandId" tabindex="2"
												value="${page.brandId}">
											 
					                    </div>
					                    <button type="submit" class="button button-primary button-raised button-small"><i class="fa fa-search"></i>&nbsp;&nbsp;搜索</button>
					                    </div>
					                </form>
					            </div>
					            <!--biaoge-->
					            <table class="table table-bordered table-hover">					
					                <thead>
					                <tr>
										<th>品牌ID</th>
										<th>品牌名称</th>
										<th>品牌所属地</th>
										<th>新增人</th>
										<th>新增时间</th>
										<th>最后修改人</th>
										<th>最后修改时间</th>
										<th>操作</th>
									</tr>
					                </thead>
					                <tbody>
					                <c:forEach items="${list}" var="brand" varStatus="status">
										<tr>
											<td>${brand.brandId}</td>
											<td><a href="/brand/${brand.brandId}">${brand.brandName}</a></td>
											<td>${brand.brandArea}</td>
											<td>${brand.createName}</td>
											<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
													value="${brand.createTime}" type="both" /></td>
											<td>${brand.updateName}</td>
											<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
													value="${brand.updateTime}" type="both" /></td>
											<td><a href="/brand/toEditPage/${brand.brandId}">编辑</a>
											</td>
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
					window.location.href = 'page?page='+ pageNumber + '&brandName=${page.brandName}&brandId=${page.brandId}';
				}
			});
		function doLoadData(data) {
			alert(data.code);
		}

		$("#srarch").click(function() {
			$("#searchList").submit();
		});

		function changeLevelOne() {
			var levelOne = $("#levelOne").val();
			$("#levelTwo").empty();
			$("#levelTwo")
					.append(
							'<option value="" checked="true" id="option" selected>二级品牌</option>');
			var url = "/categories/getPreLevel?parentId=" + levelOne;
			$.getJSON(url, function(result) {
				$.each(result.data.preList, function(key, val) {
					$("#levelTwo").append(
							'<option value="' + val.categoryId + '" >'
									+ val.categoryName + '</option>');
				});
			});
		}
		function changeLevelTwo() {
			var levelTwo = $("#levelTwo").val();
			$("#levelThree").empty();
			$("#levelThree")
					.append(
							'<option value="" checked="true" id="option" selected>三级品牌</option>');
			var url = "/categories/getPreLevel?parentId=" + levelTwo;
			$.getJSON(url, function(result) {
				$.each(result.data.preList, function(key, val) {
					$("#levelThree").append(
							'<option value="' + val.categoryId + '" >'
									+ val.categoryName + '</option>');
				});
			});
		}
		/* 品牌导出 */
		function daochuBrand(){
			var url = "/brand/exportBrand?";
			url = url + "brandName=" + $("#brandName").val()
					+ "&brandId=" +  $("#brandId").val();
			autoDowload(url);
		}
		var autoDowload = function( _url ){
			var iframEle = $('<ifram></ifram>');
			    iframEle.css({
			        'width': 0,
			        'height': 0,
			        'display': 'none'
			    });
			    iframEle.appendTo('body');
			var formEle = $('<form action="'+_url+'" method="post"></form>');
			var inputEle = $('<input type="test" name="file" value="">');
	        	inputEle.appendTo( formEle );
			    formEle.appendTo( iframEle ).submit().remove();
			    iframEle.remove();
		}
	</script>
</body>
</html>