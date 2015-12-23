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
<link rel="stylesheet" href="/resources/css/form.css">
<link rel="stylesheet" href="/resources/css/pagination.css">
<!-- 分页插件 css 样式 -->
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

					<div class="form-page-wrap">
						<div class="data-page-inner">
							<div class="page-head">
								<h3 class="m-b-less">采购单新增</h3>
								<!--<span class="sub-title">Welcome to Static Table</span>-->
								<div class="state-information">
									<ol class="breadcrumb m-b-less bg-less">
										<li><a href="#">主页</a></li>
										<li><a href="#">采销管理</a></li>
										<li class="active">采购单编辑</li>
									</ol>
								</div>
							</div>
							<div class="wrapper">
								<div class="row">
									<div class="col-sm-12">
										<form id="form" class="form-horizontal">
											<section class="panel">
												<header class="panel-heading">
													采购单概要
													<div class="back">
									                    <a href="/purchase/page?page=1" class="button button-primary button-raised button-small">返回</a>									                   
									                </div>													 
												</header>
												<div class="panel-body">
														<div class="form-group large-group">
															<label for="" class="col-lg-2">供应商</label>
															<div class="col-lg-9">
																<select class="form-control" name="providerId" id="providerId">
																	<c:forEach items="${providerList}" var="list" varStatus="status">
																		<option value="${list.vendorId}">${list.vendorName}</option>
																	</c:forEach>
																</select>
															</div>
														</div>
														<div class="form-group">
															<label for="" class="col-lg-2">预约仓库</label>
															<div class="col-lg-9">
																<select class="form-control" name="warehouseId" id="warehouseId">
																	<c:forEach items="${wareHouseList}" var="list" varStatus="status">
																		<option value="${list.warehouseId}">${list.wareHouseName}</option>
																	</c:forEach>
																</select>
															</div>
														</div>
														<div class="form-group">
															<label for="" class="col-lg-2">结算币种</label>
															<div class="col-lg-9">
																<select class="form-control" name="purchaseCurrency" id="purchaseCurrency">
																		<option value="CNY">人民币</option>
																</select>
															</div>
														</div>
														<div class="form-group large-group">
															<label for="" class="col-lg-2">其他费用</label>
															<div class="col-lg-9">
																<input type="text" id="purchasePriceOther" name="purchasePriceOther" value="" required class="form-control">
															</div>
														</div>
														<div class="form-group large-group">
															<label for="" class="col-lg-2">运费</label>
															<div class="col-lg-9">
																<input class="form-control" type="text" id="purchaseFreight" name="purchaseFreight" value="" required>
															</div>
														</div>
														<div class="form-group small-group">
															<label for="" class="col-lg-2">预计送达时间</label>
															<div class="col-lg-9">
																<input	type="text" id="deliveryTime" name="deliveryTime" value=""	class="form-control" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
															</div>
														</div>
														<div class="form-group">
															<label for="" class="col-lg-2">备注</label>
															<div class="col-lg-9">
																<textarea name="purchaseRemark" class="form-control" rows="3"></textarea>
															</div>
														</div>
														<div class="form-group">
															<label for="" class="col-lg-2">导入文件</label>
															<div class="col-lg-9">
																<div class="dataTables_wrapper form-inline dt-bootstrap no-footer" id="DataTables_Table_0_wrapper">
																	<form id="batchSendContent_form">																		
																		<div>
																			请选择一个EXCEL文件: 
																			<label for="excel_files"> 
																			<input type="file" id="excel_files" name="files">
																			</label>																		
																		</div>																		
																	</form>
																</div>
															</div>															
														</div>
														<div class="form-group btnlist">
															<a class="button button-primary button-raised button-small pull-right" id="batch_trade_send_form">确定</a>	
															<a class="button button-primary button-raised button-small pull-right" id="batch_trade_send_template_download">下载采购单模板</a>																 													
														</div>
														<div id="batch_send_list_div" style="margin-top: 36px;">
									                    </div>
													</div>
											</section>
										</form>			
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
		
	</section>
	<!-- 在 body的最底部引入js文件且一定保持 jquery 在 bootstrap 之前引入 -->
	<script src="/resources/js/jquery/jquery-2.1.4.min.js"></script>
	<script src="/resources/js/bootstrap/bootstrap.min.js"></script>
	<script src="/resources/js/jquery/jquery.validate.js"></script>
	<script src="/resources/js/jquery/jquery-validate-message.js"></script>
	<script src="/resources/js/jquery_upload/vendor/jquery.ui.widget.js"></script>
	<script src="/resources/js/jquery_upload/jquery.iframe-transport.js"></script>
	<script src="/resources/js/jquery_upload/jquery.fileupload.js"></script>
	 <script src="/resources/js/My97DatePicker/WdatePicker.js"></script>
	<script src="/resources/js/goods/goods.js"></script>
	<!-- 侧面导航栏组件 js -->
	<script src="/resources/js/sidebar.js"></script>
	<script src="/resources/js/jquery.pagination.js"></script>
	<script type="text/javascript">
	/* 下载 */
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
	$("#batch_trade_send_template_download").click(function(){
		var url = "/purchase/downloadPurchaseTemplate";
		autoDowload(url);
	});
	/* 批量上传 */
	$("#batch_trade_send_form").click(function() {
		var formData = new FormData($("#form")[0]);
		if('' == $("#excel_files").val())
			return ;
		if($("#excel_files").val().indexOf('.xlsx')<0 
				&& $("#excel_files").val().indexOf('.xls')<0){
			alert("请上传EXCEL文件");
			return ;
		} 
		$.ajax({
			url : '/purchase/batchPurchase',
			type : 'post',
			data : formData,
			async : false,
			cache : false,
			dataType : "html",
			contentType : false,
			processData : false,
			success : function(data) {
				$("#batch_send_list_div").html(data);
				 var flag = "1";
				 $("#save").click(function () {
					 if("1" == flag){
						 flag = "0";
						 $.ajax({
					            type: "POST",
					            url: "/purchase/addPurchase",
					            data: $("#form").serialize(),
					            cache: false,
					            dataType: 'json',
					            success: function (data) {
					                if (data.code == 200) {
					                    window.location.href = "/purchase/page";
					                } else {
					                    alert("code: " + data.code + "\n" + data.msg);
					                }
					            }
					        });
					 }
				       
				    });
			}
		});
	})
	
   
	</script>
</body>
</html>