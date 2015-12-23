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
<link rel="stylesheet" href="/resources/css/datapage.css">

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
						<div class="data-page-inner">
							<div class="page-head">
								<!-- 页面标题 -->
								<h3 class="m-b-less">商品价格修改</h3>
								<!--面包屑导航-->
								<div class="state-information">
									<ol class="breadcrumb m-b-less bg-less">
										<li><a href="#">主页</a></li>
										<li><a href="#">采销管理</a></li>
										<li class="active">商品价格修改</li>
									</ol>
								</div>
							</div>
							<div class="wrapper">
								<div class="row">
									<div class="col-sm-12">
										<section class="panel">
											<div class="dataTables_wrapper form-inline dt-bootstrap no-footer" id="DataTables_Table_0_wrapper">
												<table aria-describedby="DataTables_Table_0_info"
													role="grid" id="DataTables_Table_0"
													class="table convert-data-table data-table dataTable no-footer">
													<form id="goods_price_form_content">
													<tbody>
													<tr>
														<td><i class="fa fa-asterisk"></i>启用类型</td>
														<td><label class="col-lg-2"><input type="radio" id="pricechangeType_EXCUTE" name="pricechangeType" value="EXCUTE" checked="checked" />立即启用</label>
															<label class="col-lg-2"><input type="radio" id="pricechangeType_SCHEDULE" name="pricechangeType" value="SCHEDULE"/>时间段启用</label></td>
													</tr>
													<tr id="td_time_period" style="display: none;">
														<td><i class="fa fa-asterisk"></i>生效时间段</td>
														<td><input type="text" id="startTime" class="form-control" name="startTime" onfocus="WdatePicker({firstDayOfWeek:1})"/>
														至
														<input type="text" id="endTime" class="form-control" name="endTime" onfocus="WdatePicker({firstDayOfWeek:1})" /></td>
													</tr>
													<tr>
														<td><i class="fa fa-asterisk"></i>修改说明</td>
														<td colspan="2"><textarea id="mark" name="mark" required class="form-control" cols="60"
														rows="2"></textarea></td>
													</tr>
													<tr>
														<td><i class="fa fa-asterisk"></i>导入文件</td>
														<td><input type="file" name="files" id="goodsPriceFile" required /></td>
														
													</tr>
													<tr>
														<td></td>
														<td>
															<span class="button button-primary button-raised pull-right button-small" id="batch_goods_price_change">提交</span>
															<a class="button button-primary button-raised pull-right button-small" href="javascript:void(0);" onclick="downloadPriceTemplate();">价格修改模板下载</a>									
															<!-- <input type="submit" id="subimt_form" style="display: none;" value="提交"> -->											
														</td>
													</tr>
													</tbody>
													</form>
												</table>
											</div>
										</section>
										<div id="change_goods_price_list_div" style="margin-top: 36px;">
										</div>
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
	<script src="/resources/js/jquery/jquery.bootstrap.min.js"></script>
	<script src="/resources/js/jquery/jquery.validate.js"></script>
	<script src="/resources/js/jquery/jquery-validate-message.js"></script>
	<script src="/resources/js/My97DatePicker/WdatePicker.js"></script>
	<!-- 侧面导航栏组件 js -->
	<script src="/resources/js/sidebar.js"></script>
	<script src="/resources/js/jquery.pagination.js"></script>
	<script type="text/javascript">
	var isSubmit = false;
	$(function(){
		$("#batch_goods_price_change").click(function(){
			if(isSubmit){
				alert("再次提交请刷新页面");				
				return ;
			}
			if('' == $("#goodsPriceFile").val()){
				alert("请上传EXCEL文件");
				return ;
			}
			//alert($("#goodsPriceFile").val().indexOf('.xlsx'));
			if($("#goodsPriceFile").val().indexOf('.xlsx')<0 
					&& $("#goodsPriceFile").val().indexOf('.xls')<0){
				alert("请上传EXCEL文件");
				return ;
			}
			/* checkValidity() */
			if (!document.getElementById("goods_price_form_content").checkValidity()) {
				$("#subimt_form").trigger("click");
				return;
			}
			var formData = new FormData();
			formData.append("files", $("#goodsPriceFile")[0].files[0]);
			formData.append("pricechangeType", $("input[name='pricechangeType']:checked").val());
			formData.append("startTime", $("#startTime").val());
			formData.append("endTime", $("#endTime").val());
			formData.append("mark", $("#mark").val());
			$.ajax({
				url : '/goodsNew/changeGoodsPrice',
				type : 'post',
				data : formData,
				async : false,
				cache : false,
				dataType : "html",
				contentType : false,
				processData : false,
				success : function(data) {
					$("#change_goods_price_list_div").html(data);
					isSubmit = true;
				}
			});
		});
	});
	</script>

	<script type="text/javascript">
	$(function(){
		$("#pricechangeType_EXCUTE").click(function(){
			$("#startTime").removeAttr("required");
			$("#endTime").removeAttr("required");
			$("#td_time_period").hide();
		});
		$("#pricechangeType_SCHEDULE").click(function(){
			$("#startTime").attr("required", true);
			$("#endTime").attr("required", true);
			$("#td_time_period").show();
		});
	});
	</script>

	<script type="text/javascript">
	var autoDownload = function( _url ){
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
	function downloadPriceTemplate(){
		var url = "/goodsNew/templateDownload";
		autoDownload(url);
	}
	</script>
</body>
</html>