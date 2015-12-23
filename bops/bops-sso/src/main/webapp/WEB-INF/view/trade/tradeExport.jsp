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
								<h3 class="m-b-less">订单导出</h3>
								<!--<span class="sub-title">Welcome to Static Table</span>-->
								<div class="state-information">
									<ol class="breadcrumb m-b-less bg-less">
										<li><a href="#">主页</a></li>
										<li><a href="#">交易管理</a></li>
										<li class="active">订单导出</li>
									</ol>
								</div>
							</div>
							<div class="wrapper">
								<div class="row">
									<div class="col-sm-12">
										<section class="panel">
											<header class="panel-heading">交易列表导出
											<a href="/trade/page?page=1" class="btn btn-info pull-right">返回</a>
											</header>
											<div class="panel-body">
												<div class="form-horizontal">
													<div class="form-group">
														<label for="" class="col-lg-2">日期</label>
														<div class="col-lg-9">
															<input id="tradeTimeStart" value="${tradeTimeStart}" type="text" onfocus="WdatePicker({firstDayOfWeek:1})"> 到 
															<input id="tradeTimeEnd" value="${tradeTimeEnd}" onfocus="WdatePicker({firstDayOfWeek:1})" type="text">
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">状态</label>
														<div class="col-lg-9" id="exportDingdan_div">
															<label for="label_WAIT_PAY">
															<input type="checkbox" id="label_WAIT_PAY" checked="checked" name="tradeStatus" value="WAIT_PAY"> 待付款
															</label>
															<label for="label_WAIT_PLATFORM_SEND">
															<input type="checkbox" id="label_WAIT_PLATFORM_SEND" checked="checked" name="tradeStatus" value="WAIT_PLATFORM_SEND"> 待发货
															</label>
															 <label for="label_WAIT_RECEIVE">
															<input type="checkbox" id="label_WAIT_RECEIVE" checked="checked" name="tradeStatus" value="WAIT_RECEIVE"> 待收货
															</label>
															<label for="label_TRADE_SUCCESS">
															<input type="checkbox" id="label_TRADE_SUCCESS" checked="checked" name="tradeStatus" value="TRADE_SUCCESS"> 交易成功
															</label>
															<label for="label_TRADE_CLOSE">
															<input type="checkbox" id="label_TRADE_CLOSE" checked="checked" name="tradeStatus" value="TRADE_CLOSE"> 交易结束
															</label>
															<label for="label_REFUND_SUCCESS">
															<input type="checkbox" id="label_REFUND_SUCCESS" checked="checked" name="tradeStatus" value="REFUND_SUCCESS"> 退款成功
															</label>
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">导出条数</label>
														<div class="col-lg-9">
															<select name="pageSzie" id="exportCount">
																<option value="">全部</option>
																<option value="100"> 100 </option>
															</select>
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2"></label>
														<div class="col-lg-9">
															<a class="btn btn-info" href="javascript:void(0);" onclick="daochuDingdan('dingdan');">
																导出订单</a>&nbsp;&nbsp;
															<a class="btn btn-info" href="javascript:void(0);" onclick="daochuDingdan('mingxi')">
																导出明细</a>
															<a class="btn btn-info" href="javascript:void(0);" onclick="daochuDingdan('beijing')">
																导出北京仓</a>
															<a class="btn btn-info" href="javascript:void(0);" onclick="daochuDingdan('xianggang')">
																导出香港仓</a>
														</div>
													</div>
												</div>
											</div>
										</section>
										<section class="panel">
											<header class="panel-heading">保税仓导出</header>
											<div class="panel-body">
												<div class="form-horizontal">
													<div class="form-group">
														<label for="" class="col-lg-2">日期</label>
														<div class="col-lg-9">
															<input id="trade_TimeStart" value="${tradeTimeStart}" type="text" onfocus="WdatePicker({firstDayOfWeek:1})"> 到 
															<input id="trade_TimeEnd" value="${tradeTimeEnd}" type="text" onfocus="WdatePicker({firstDayOfWeek:1})">
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">保税仓库</label>
														<div class="col-lg-9">
															<input type="radio" name="" value="" checked="checked"> 郑州保税仓
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">状态</label>
														<div class="col-lg-9">
															<label for="label_retrieveStatus_ZHONG">
															<input type="radio" id="label_retrieveStatus_ZHONG" name="retrieveStatus" value="ZHONG"> 清关中
															</label>
															<label for="label_retrieveStatus_FANGXING">
															<input type="radio" id="label_retrieveStatus_FANGXING" checked="checked" name="retrieveStatus" value="FANGXING"> 清关放行
															</label>
															<label for="label_retrieveStatus_TONGGUO">
															<input type="radio" id="label_retrieveStatus_TONGGUO" name="retrieveStatus" value="TONGGUO"> 审核通过
															</label>
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">导出条数</label>
														<div class="col-lg-9">
															<select name="pageSzie" id="exportCount_baoshui">
																<option value="">全部</option>
																<option value="100"> 100 </option>
															</select>
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2"></label>
														<div class="col-lg-9">
															<a class="btn btn-info" href="javascript:void(0);" onclick="daochuBaoshui();">
																导出保税仓</a>
														</div>
													</div>
												</div>
											</div>
										</section>
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
	<script src="/resources/js/My97DatePicker/WdatePicker.js"></script>
	<!-- 侧面导航栏组件 js -->
	<script src="/resources/js/sidebar.js"></script>
	<script src="/resources/js/jquery.pagination.js"></script>
	<script type="text/javascript">
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
	
		/**
		 * 导出订单
		 */
		function daochuDingdan(leixing){
			var tradeStatus = '';
			var tradeTimeStart = '';
			var tradeTimeEnd = '';
			var exportCount = '';
			$("#exportDingdan_div").find("input[name='tradeStatus']:checked").each(function(){
				tradeStatus = tradeStatus + $(this).val() + ",";
			});
			if(tradeStatus.charAt(tradeStatus.length-1) == ','){
				tradeStatus = tradeStatus.substring(0, tradeStatus.length-1);
			}
			console.info(tradeStatus);
			tradeStatus = "tradeStatus=" + tradeStatus;
			tradeTimeStart = "tradeTimeStart=" + $("#tradeTimeStart").val();
			tradeTimeEnd = "tradeTimeEnd=" + $("#tradeTimeEnd").val();
			exportCount = "pageSize=" + $("#exportCount").val();	
			var url = '';
			if(leixing == 'dingdan'){
				url = "/trade/export?";
			}
			else if(leixing == 'mingxi'){
				url = "/trade/exportOrder?";
			}
			else if(leixing ==  'beijing'){
				url = "/trade/exportOrder_v1_1?warehouseType=SELF_WAREHOUSE&";
			}
			else{
				url = "/trade/exportOrder_v1_1?warehouseType=OVERSEA_WAREHOUSE&"
			}
			url = url
					+ tradeStatus + "&" 
					+ tradeTimeStart + "&"
					+ tradeTimeEnd + "&"
					+ exportCount;
			console.info(url);
			autoDowload(url);
		}
		function daochuBaoshui(){
			var trade_TimeStart = '';
			var trade_TimeEnd = '';
			var retrieveStatus = '';
			var exportCount_baoshui = '';
			trade_TimeStart = "tradeTimeStart=" + $("#trade_TimeStart").val();
			trade_TimeEnd = "tradeTimeEnd=" + $("#trade_TimeEnd").val();
			retrieveStatus = "retrieveStatus=" + $("input[name='retrieveStatus']:checked").val();
			exportCount_baoshui = "pageSize=" + $("#exportCount_baoshui").val();
			var url = '/trade/exportTradeOrder_v1_1?';
			url = url +
				trade_TimeStart + "&" +
				trade_TimeEnd + "&" +
				retrieveStatus + "&" +
				exportCount_baoshui;
			console.info(url);
			autoDowload(url);
		}
	</script>
	<script type="text/javascript">
		/* alert($("#goodsEditProfile").serialize()); */
		
	</script>
	
</body>
</html>