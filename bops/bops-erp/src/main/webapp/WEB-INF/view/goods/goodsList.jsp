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
<link rel="stylesheet" href="/resources/css/data page.css">
<link rel="stylesheet" href="/resources/css/list-content2.css">
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
				<div class="data-page-wrap">
					<div class="con">
				        <div class="page-head">
				            <h3 class="m-b-less">商品管理</h3>
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
				                <div class="back">
				                    <a href="/goods/toAddNewGoodsPage" class="button button-primary button-raised button-small">新增商品</a>
				                    <a href="javascript:;" id="exportGoods" class="button button-primary button-raised button-small">导出商品</a>
				                    <a href="/goods/toAddNewGoodsGroup" class="button button-primary button-raised button-small">新增套装</a>
				                </div>
				            </div>
				            <div class="search-form">
				                <form class="form-inline" id="searchGoodsContent" method="get" action="/goods/page">
				                    <div class="row">
					                    <div class="form-group col-xs-3">
					                        <label for="form_goodsCode">商品编号</label>
					                        <input type="text" class="form-control" placeholder="" id="form_goodsCode" name="goodsCode">
					                    </div>
					                    <div class="form-group col-xs-3">
					                        <label for="form_goodsBarcode">国际条形码</label>
					                        <input type="text" class="form-control" placeholder="" id="form_goodsBarcode" name="goodsBarcode">
					                    </div>       
					                    <div class="form-group col-xs-3">
					                        <label for="form_goodsName">商品名称</label>
					                        <input type="text" class="form-control" placeholder="" id="form_goodsName" name="goodsName">
					                    </div>
					                    <div class="form-group col-xs-3">
					                        <label for="form_shortName">商品短名称</label>
					                        <input type="text" class="form-control" placeholder="" id="form_shortName" name="shortName">
					                    </div>
				                    </div>
				                    
				                    <div class="row">
				                   			                    
				                    <div class="form-group col-xs-3">
				                        <label for="form_auditStatus">审核状态</label>
				                        <select class="form-control"  name="auditStatus" id="form_auditStatus">
				                            <option selected="selected" value="">全部</option>
											<option value="WAIT_AUDIT">待审核</option>
											<option value="SUCCESS">通过</option>
											<option value="FAIL">未通过</option>
				                        </select>
				                    </div>
				                    <div class="form-group col-xs-3">
				                        <label for="form_goodsStatus">上下架状态</label>
				                        <select class="form-control"  id="form_goodsStatus" name="goodsStatus">				                            
				                            <option value="">全部</option>
											<option value="ONLINE">上架</option>
											<option value="OFFLINE">下架</option>
				                        </select> 
				                    </div>
				                     <div class="form-group col-xs-3">
				                        <label for="form_warehouseType">仓库类型</label>
				                        <select class="form-control"  id="form_warehouseType" name="warehouseType">
				                            <option value="">全部</option>
											<option value="SELF_WAREHOUSE">自营仓</option>
											<option value="TAX_WAREHOUSE">保税仓</option>
											<option value="OVERSEA_WAREHOUSE">海外直邮</option>
				                        </select>
				                    </div>
				                    <div class="form-group col-xs-3" >
					                 <label for="form_goods_type">商品类型</label> 
			    						<select class="form-control" id="form_goods_type" name="goodsType"> 
			    							<option value="">全部</option>
			    							<option value="SINGLE">单品</option>
			    							<option value="MORE">套装</option>
			    						</select>
		    						</div>
				                    <div class="form-group col-xs-3">
				                  		 <button type="submit" id="searchGoods" class="button button-primary button-raised button-small"><i class="fa fa-search"></i>&nbsp;&nbsp;搜索</button>
				                    </div>
				                    </div>
				                </form>
				            </div>
				            <!--biaoge-->
				            <table class="table table-bordered table-hover table-condensed table-responsive">
				                <thead>
				                <tr>
				                	<th>商品主图</th>
				                    <th>商品编号</th>
				                    <th>商品类型</th>
				                    <th>国际条形码</th>
				                    <th>商品名称</th>
				                    <th>仓库类型</th>
				                    <th>原价</th>
				                    <th>Need价</th>
				                    <th>采购价</th>
				                    <th>上下架状态</th>
				                    <th>审核状态</th>
				                    <th>操作</th>				                    
				                </tr>
				                </thead>
			                <tbody>
				              <c:forEach items="${list}" var="goods" varStatus="status">
									<tr>
									   <td class="sorting_1">
										 <c:if test="${!empty goods.scenePicKey}">
										  <img width="60px" alt="商品图片" src="${picAddress}${goods.scenePicKey}">
										 </c:if>
										 <c:if test="${empty goods.scenePicKey}">
										   未上传图
										 </c:if>
										</td>
										<td>
											<a href="/goods/goodsProfile?goodsId=${goods.goodsId}&page=${page.page}">${goods.goodsCode}</a>
										</td>
										<td style="text-align:left;">
										<c:if test="${goods.goodsType == 'MORE'}">套装</c:if>
										<c:if test="${goods.goodsType != 'MORE'}">单品</c:if>
										</td>
										<td style="text-align:left;">${goods.goodsBarcode}</td>
										<td style="text-align:left;">${goods.goodsName}</td>
										<td><c:choose>
												<c:when test="${goods.warehouseType=='TAX_WAREHOUSE'}">
					                                	保税仓
					                            </c:when>
												<c:when
													test="${goods.warehouseType=='SELF_WAREHOUSE'}">
					                                	自营仓
					                            </c:when>
												<c:otherwise>
				                                	海外直邮
				                              	</c:otherwise>
											</c:choose>
										</td>
										<td>${goods.onsalePrice}</td>
										<td>${goods.discountPrice}</td>
										<td>${goods.purchasePrice}</td>
										<td><c:choose>
												<c:when test="${goods.goodsStatus=='ONLINE'}">上架 </c:when>
												<c:otherwise>下架</c:otherwise>
												</c:choose>
										</td>
										<td><c:choose>
												<c:when test="${goods.auditStatus=='WAIT_AUDIT'}">待审核</c:when>
												<c:when test="${goods.auditStatus=='SUCCESS'}">通过</c:when>
												<c:otherwise>驳回</c:otherwise>
										</c:choose>
										</td>
										<td>
										<c:choose>
										      <c:when test="${goods.auditStatus=='WAIT_AUDIT'}">
										      		<a href="javascript:void(0);"onclick="showShenheDialog('${goods.goodsId}', '${fn:replace(goods.goodsName, '\'' , '')}', '${goods.goodsCode}', '${goods.scenePicKey}')">审核</a>
										      </c:when>
												<c:when test="${goods.goodsStatus=='ONLINE'}">
													<a href="javascript:void(0)" onclick="xiajia('${goods.goodsId}')"> 下架 </a>
												</c:when>
												<c:otherwise>
												<a href="/goods/goodsProfileEdit?goodsId=${goods.goodsId}&page=${page.page}"> 编辑</a>
												<c:if test="${goods.auditStatus!='FAIL'}">
												<a href="javascript:void(0)" onclick="shangjia('${goods.goodsId}','${goods.auditStatus}')"> | 上架</a>
												</c:if>
												</c:otherwise>
											</c:choose>
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
					</div>
				</div>
			</div>
		</div>
	</section>


	<div class="modal fade" id="editGoodsModalDiv" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">商品审核</h4>
				</div>
				<div class="modal-body">
					<div>
						<input id="div_goodsId" value="" type="hidden"> 商品名称: <input
							type="text" id="div_goodsName" value="" readonly="readonly"
							style="width: 400px;">
					</div>
					<div style="margin: 10px 0px 10px 0px;">
						商品编码: <input type="text" id="div_goodsCode" value=""
							readonly="readonly">
					</div>
					<div>
						商品图片: <img src="" width="60px" id="div_scenePicKey">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" onclick="shenhe('SUCCESS');"
						class="btn btn-primary">审核通过</button>
					<button type="button" onclick="shenhe('FAIL');"
						class="btn btn-primary">审核驳回</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
	</div>


	<div class="modal fade" id="exportGoodsDialogDiv" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">商品导出</h4>
				</div>
				<form id="exportGoodsContent_form">
					<div class="modal-body">
						<div>
							审核状态: <label for="label_auditStatus_success"> <input
								type="checkbox" id="label_auditStatus_success"
								name="auditStatus" value="SUCCESS" checked="checked"> 通过
							</label> <label for="label_auditStatus_fail"> <input
								type="checkbox" id="label_auditStatus_fail" name="auditStatus"
								value="FAIL" checked="checked"> 未通过
							</label> <label for="label_auditStatus_waitAudit"> <input
								type="checkbox" id="label_auditStatus_waitAudit"
								name="auditStatus" value="WAIT_AUDIT" checked="checked">
								待审核
							</label>
						</div>
						<div>
							商品状态: <label for="label_goodsStatus_online"> <input
								type="checkbox" id="label_goodsStatus_online" name="goodsStatus"
								value="ONLINE" checked="checked"> 上架
							</label> <label for="label_goodsStatus_offline"> <input
								type="checkbox" id="label_goodsStatus_offline"
								name="goodsStatus" value="OFFLINE" checked="checked"> 下架
							</label>
						</div>
						<div>
							商品仓库: <label for="label_warehouseType_self"> <input
								type="checkbox" id="label_warehouseType_self" name="warehouseType"
								value="SELF_WAREHOUSE" checked="checked"> 自营仓
							</label> <label for="label_warehouseType_tax"> <input
								type="checkbox" id="label_warehouseType_tax"
								name="warehouseType" value="TAX_WAREHOUSE" checked="checked"> 保税仓
							</label> <label for="label_warehouseType_oversea"> <input
								type="checkbox" id="label_warehouseType_oversea"
								name="warehouseType" value="OVERSEA_WAREHOUSE" checked="checked"> 海外直邮
							</label>
						</div>
						<div>
							导出条数: <select name="pageSize">
								<option value="">全部</option>
								<option value="100">100</option>
							</select>
						</div>
					</div>
				</form>
				<div class="modal-footer">
					<button type="button" onclick="daochuGoods();"
						class="btn btn-primary">导出</button>
					<button type="button" id="close_exportGoods"
						class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
	</div>

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
				var shortName = "${page.shortName}";
				var goodsType = "${page.goodsType}";
				window.location.href = 'page?page=' + pageNumber 
						+ "&goodsCode=" + goodsCode
						+ "&auditStatus=" + auditStatus
						+ "&warehouseType=" + warehouseType
						+ "&goodsBarcode=" + goodsBarcode
						+ "&goodsName=" + goodsName
						+ "&goodsStatus=" + goodsStatus
						+ "&goodsType=" + goodsType
						+ "&shortName=" + shortName;
			}
		});
		/** 
		 *	上架商品
		 */
		function shangjia(goodsId,auditStatus) {
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
									/* window.location.href = "/goods/page?page="
										+ $("#pageNumber").val(); */
									window.location.reload();
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
							/* window.location.href = "/goods/page?page="
								+ $("#pageNumber").val(); */
							window.location.reload();
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
					if(200 == data.code){
						/* window.location.href = "/goods/page?page="
								+ $("#pageNumber").val(); */
						window.location.reload();
					}
					else{
						alert(data.msg);
					}
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
		$("#form_goods_type").find("option[value='${page.goodsType}']").attr("selected", true);
		$("#form_goodsBarcode").val("${page.goodsBarcode}");
		$("#form_goodsName").val("${page.goodsName}");
		$("#form_shortName").val("${page.shortName}");
	</script>

	<script type="text/javascript">
	
		function daochuGoods(){
			var param = $("#exportGoodsContent_form").serializeArray();
			console.info(param);
			var exportParam_auditStatus = '';
			var exportParam_goodsStatus = '';
			var exportParam_warehouseType = '';
			var exportParm_pageSize = '';
			
			for(var i in param){
				//tempArr.push(param[i].name)
				if(param[i].name == 'auditStatus'){
					exportParam_auditStatus = exportParam_auditStatus + param[i].value + ",";
				}
				if(param[i].name == "goodsStatus"){
					exportParam_goodsStatus = exportParam_goodsStatus + param[i].value + ",";
				}
				if(param[i].name == "warehouseType")
				{
					exportParam_warehouseType = exportParam_warehouseType + param[i].value + ",";
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
			if(exportParam_warehouseType.charAt(exportParam_warehouseType.length-1) == ','){
				exportParam_warehouseType = exportParam_warehouseType.substring(0, exportParam_warehouseType.length-1);
			}
			var url = "/goods/export?";
			url = url + "auditStatus=" + exportParam_auditStatus
					+ "&goodsStatus=" +  exportParam_goodsStatus
					+ "&warehouseType=" + exportParam_warehouseType
					+ "&pageSize=" + exportParm_pageSize;
			/* console.info(exportParam_auditStatus);
			console.info(exportParam_goodsStatus);
			console.info(exportParm_pageSize);*/
			console.info(url); 
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
				if($("#form_goodsCode").val().trim() == '' 
						&& $("#form_auditStatus").val().trim() == '' 
						&& $("#form_warehouseType").val().trim() == ''
						&& $("#form_goodsBarcode").val().trim() == ''
						&& $("#form_goodsName").val().trim() == ''
						&& $("#form_shortName").val().trim() == ''
						&& $("#form_goodsStatus").val().trim() == '' 
						&& $("#form_goods_type").val().trim() == ''){
					return;
				}
				else{
					$("#searchGoodsContent").submit();
				}
			});
			
			$("#exportGoods").click(function(){
				/* alert("H"); */
				$("#exportGoodsDialogDiv").modal("show");
				//autoDowload("/goods/export?goodsStatus=ONLINE,OFFLINE&auditStatus=SUCCESS,FAIL,WAIT_AUDIT");
			});
			

		});
	</script>
</body>
</html>