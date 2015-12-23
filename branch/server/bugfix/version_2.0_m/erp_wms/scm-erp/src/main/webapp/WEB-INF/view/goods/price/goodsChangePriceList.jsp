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

<link rel="shortcut icon" href="/resources/img/common/favicon.ico" type="image/x-icon" />

<title>ERP管理系统</title>

<!-- 主体部分样式表 -->
<link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/bootstrap/button.css">
<link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.css">
<link rel="stylesheet" href="/resources/css/font-awesome.min.css">
<link rel="stylesheet" href="/resources/css/layout.css">
<link rel="stylesheet" href="/resources/css/ops.css">
<link rel="stylesheet" href="/resources/css/sidebar.css">
<link rel="stylesheet" href="/resources/css/content-header.css">
<link rel="stylesheet" href="/resources/css/datap age.css">

<!-- 分页插件 css 样式 -->
<link rel="stylesheet" href="/resources/css/pagination.css" />
<!--  我的内容样式-->
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
				            <h3 class="m-b-less">商品价格修改</h3>
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
				                    <a href="/goodsNew/toChangePricePage" class="button button-primary button-raised button-small">商品修改价格</a>
				                   
				                </div>
				            </div>
				            <div class="search-form">
				            <form id="searchGoodsPriceContent" method="get" action="/goodsNew/page" class="form-inline">
								<div class="row">
									<div class="form-group col-xs-3">
				                        <label for="form_start_time">申请时间</label>
				                       <input type="text" id="form_start_time" name="startTime" onfocus="WdatePicker({firstDayOfWeek:1})">														
				                    </div>
				                    <div class="form-group col-xs-3">
				                        <label for="form_end_time">至</label>
				                        <input type="text" id="form_end_time" name="endTime" onfocus="WdatePicker({firstDayOfWeek:1})">
				                    </div>
									<div class="form-group col-xs-3">
				                        <label for="form_pricechange_id">申请编号</label>
				                        <input type="text" class="form-control" placeholder="" id="form_pricechange_id" name="pricechangeId">
				                    </div>
										<div class="form-group col-xs-3">
					                        <label for="form_user_id">申请人</label>
					                        <select class="form-control" name="userId" id="form_user_id">
					                            <option value="">全部</option>
													<c:forEach items="${userList}" var="user"
														varStatus="status">
												<option value="${user.userId}">${user.userRealName}</option>
													</c:forEach>
					                        </select>
					                   	 </div>
									 </div>
									<div class="row">
									<div class="form-group col-xs-3">
				                        <label for="form_pricechange_status">审核状态</label>
				                        <select class="form-control" id="form_pricechange_status" name="pricechangeStatus">
				                          <option value="">全部</option>
										<option value="DRAFT">待审核</option>
										<option value="VALID">审核通过</option>
										<option value="FAIL">审核驳回</option>
				                        </select>
				                    </div>
				                     <div class="form-group col-xs-3">
				                        <label for="form_excuted">启用状态</label>
				                        <select class="form-control" id="form_excuted" name="excuted">
				                           <option value="">全部</option>
											<option value="NEITHER">未启用</option>
											<option value="STARTED">已启用</option>
											<option value="ENDED">已恢复原价</option>
											<!-- <option value="FROZEN">已作废</option> -->
				                        </select> 
				                    </div>
				                    <div class="form-group col-xs-3">
				                        <label for="form_pricechange_type">启用类型</label>
				                        <select class="form-control" id="form_pricechange_type" name="pricechangeType">
				                           <option value="">全部</option>
											<option value="EXCUTE">立即启用</option>
											<option value="SCHEDULE">时间段启用</option>
				                        </select>
				                    </div>
									 <div class="form-group col-xs-3">
									 <a href="javascript:void(0);" id="searchGoodsPrice"
										class="button button-primary button-raised button-small">搜索</a>
									 </div> 
								 </div>
							</form>
				           
				            </div>
				            <!--biaoge-->
				            <table class="table table-bordered table-hover table-condensed table-responsive">
				                <thead>
				                <tr>
				                    <th>申请编号</th>
				                    <th>审核状态</th>
				                    <th>启用状态</th>
				                    <th>修改商品数</th>
				                    <th>启用类型</th>
				                    <th>申请人</th>
				                    <th>申请部门</th>
				                    <th>申请时间</th>
				                    <th>审批人</th>
				                    <th>审批部门</th>
				                    <th>审批时间</th>	
				                    <th>操作</th>				                    
				                </tr>
				                </thead>
			                <tbody>
				               <c:forEach items="${list}" var="goods" varStatus="status">
									<tr>
										<td class="sorting_1">
											<a href="/goodsNew/goodsPriceDetails?pricechangeId=${goods.pricechangeId}">
												&nbsp;${goods.pricechangeId}&nbsp; </a>
										</td>
										<td><c:choose>
												<c:when test="${goods.pricechangeStatus == 'DRAFT'}">
													待审核
												</c:when>
												<c:when test="${goods.pricechangeStatus == 'VALID'}">
													审核成功
												</c:when>
												<c:otherwise>
				                                	驳回
				                              	</c:otherwise>
											</c:choose>
										</td>
										<td><c:choose>
												<c:when test="${goods.excuted == 'NEITHER'}">
													未启用
												</c:when>
												<c:when test="${goods.excuted == 'STARTED'}">
													已启用
												</c:when>
												<c:when test="${goods.excuted == 'ENDED'}">
													已恢复原价
												</c:when>
												<c:otherwise>
				                                	已作废
				                              	</c:otherwise>
											</c:choose>
										</td>
										<td>${goods.goodsCount}</td>
										<td><c:choose>
												<c:when test="${goods.pricechangeType == 'EXCUTE'}">
													立即启用
												</c:when>
												<c:otherwise>
				                                	时间段启用
				                              	</c:otherwise>
											</c:choose>
										</td>
										<td>${goods.userName}</td>
										<td>${goods.userDept}</td>
										<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
												value="${goods.userTime}" type="both" />
										</td>
										<td>${goods.auditorName}</td>
										<td>${goods.auditDept}</td>
										<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
												value="${goods.auditTime}" type="both" />
										</td>
										<td><c:if test="${goods.excuted != 'FROZEN'}">
												<c:choose>
													<c:when test="${goods.pricechangeStatus == 'DRAFT'}">
														<a href="javascript:void(0);"
															onclick="showShenheDialog('${goods.pricechangeId}','${goods.mark}');">审核</a>|
														<a href="javascript:void(0);"
															onclick="execute('${goods.pricechangeId}', 'FROZEN');">作废</a>
													</c:when>
													<c:when test="${goods.pricechangeStatus == 'VALID'}">
														<c:if test="${goods.excuted == 'NEITHER'}">
															<a href="javascript:void(0);"
																onclick="execute('${goods.pricechangeId}', 'STARTED')">立即启用</a>|
															<a href="javascript:void(0);"
																onclick="execute('${goods.pricechangeId}', 'FROZEN');">作废</a>
														</c:if>
														<c:if test="${goods.excuted == 'STARTED'}">
															<c:if
																test="${goods.pricechangeType == 'SCHEDULE' }">
																<a href="javascript:void(0);"
																	onclick="execute('${goods.pricechangeId}', 'ENDED');">恢复原价</a>
																<a href="javascript:void(0);"
																	onclick="execute('${goods.pricechangeId}', 'FROZEN');">作废</a>
															</c:if>
														</c:if>
													</c:when>
													<c:otherwise>
														<a href="javascript:void(0);"
															onclick="showShenheDialog('${goods.pricechangeId}','${goods.mark}');">&nbsp;审核&nbsp;</a>
														<a href="javascript:void(0);"
															onclick="execute('${goods.pricechangeId}', 'FROZEN');">&nbsp;作废&nbsp;</a>
													</c:otherwise>
												</c:choose>
											</c:if>
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
					
					<!-- 
                            这部分写各自页面对应的内容
                            注意锁紧格式
                            范围 end -
                         -->
				</div>
			</div>
		</div>
	</section>



	<div class="modal fade" id="goodsPriceModalDiv" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">商品价格审核</h4>
				</div>
				<div class="modal-body">
					<div>
						<input type="hidden" value="" id="hidden_shenhe_pricechange_id">
						申请编号: <span id="span_pricechange_id"></span>
					</div>
					<div style="margin: 10px 0px 10px 0px;">
						申请备注: <span id="span_mark"></span>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" onclick="shenhe('VALID');"
						class="btn btn-primary">审核通过</button>
					<button type="button" onclick="shenhe('FAIL');"
						class="btn btn-primary">审核驳回</button>
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="close_modal">关闭</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
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
	$('#light-pagination').pagination({
		pages : "${page.pageCount}",
		cssStyle : 'light-theme',
		currentPage : "${page.page}",
		onPageClick : function(pageNumber, event) {
			var startTime = "${page.startTime}";
			var endTime = "${page.endTime}";
			var pricechangeId = "${page.pricechangeId}";
			var userId = "${page.userId}";
			var pricechangeStatus = "${page.pricechangeStatus}";
			var excuted = "${page.excuted}";
			var pricechangeType = "${page.pricechangeType}";
			window.location.href = 'page?page=' + pageNumber
				+ "&startTime=" + startTime
				+ "&endTime=" + endTime
				+ "&pricechangeId=" + pricechangeId
				+ "&userId=" + userId
				+ "&pricechangeStatus=" + pricechangeStatus
				+ "&excuted=" + excuted
				+ "&pricechangeType=" +pricechangeType;
		}
	});
		
	</script>

	<script type="text/javascript">
	$(function(){
		$("#searchGoodsPrice").click(function(){
			if($("#form_start_time").val().trim() == ''
				&& $("#form_end_time").val().trim() == ''
				&& $("#form_pricechange_id").val().trim() == ''
				&& $("#form_user_id").val().trim() == ''
				&& $("#form_pricechange_status").val().trim() == ''
				&& $("#form_excuted").val().trim() == ''
				&& $("#form_pricechange_type").val().trim() == ''){
				return ;
			}
			else{
				$("#searchGoodsPriceContent").submit();
			}
		});
		
		$(window).bind("keydown",function(e){
            var key = e.which;
            if (key == 13) {
                $("#searchGoodsPrice").click();
            }
        })
	});
	
	
	</script>

	<script type="text/javascript">
	$(function(){
		$("#form_start_time").val("${page.startTime}");
		$("#form_end_time").val("${page.endTime}");
		$("#form_pricechange_id").val("${page.pricechangeId}");
		$("#form_user_id").find("option[value='${page.userId}']").attr("selected", true);
		$("#form_pricechange_status").find("option[value='${page.pricechangeStatus}']").attr("selected", true);
		$("#form_excuted").find("option[value='${page.excuted}']").attr("selected", true);
		$("#form_pricechange_type").find("option[value='${page.pricechangeType}']").attr("selected", true);
	});
	</script>
	<script type="text/javascript">
	function showShenheDialog(id, mark){
		$("#span_pricechange_id").text(id);
		$("#hidden_shenhe_pricechange_id").val(id);
		$("#span_mark").text(mark);
		$("#goodsPriceModalDiv").modal("show");
	}
	function shenhe(audit){
		var pricechangeId = $("#hidden_shenhe_pricechange_id").val();
		$.ajax({
			type : "post",
			url : "/goodsNew/auditGoodsPrice",
			datatype : "json",
			data : {
				pricechangeId : pricechangeId,
				pricechangeStatus : audit
			},
			success : function(data) {
				//alert("code: " + data.code + "\n" + data.msg);
				if(200 == data.code){
					$("#close_modal").trigger("click");
					window.location.href = "/goodsNew/page?page="
							+ $("#pageNumber").val();
					/* alert(data.msg); */
				}
				else{
					$("#close_modal").trigger("click");
					alert(data.msg);
				}
			}
		});
	}
	function execute(id ,audit){
		$.messager.confirm("商品改价", "您确定要进行此操作？", function() {
			$.ajax({
				type : "post",
				url : "/goodsNew/execute",
				datatype : "json",
				data : {
					pricechangeId : id,
					excuted : audit
				},
				success : function(data) {
					if(data.code == 200){
						window.location.href = "/goodsNew/page?page="
							+ $("#pageNumber").val();
						/* alert("code: " + data.code + "\n" + data.msg); */
					}
					else{
						alert("code: " + data.code + "\n" + data.msg);
					}
				}
			});
		});
	}
	</script>
</body>
</html>