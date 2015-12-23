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

<title>运营管理系统</title>

<!-- 主体部分样式表 -->
<link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css">
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
                            范围 start --->

					<div class="data-page-wrap">
						<div class="page-head">
							<h3 class="m-b-less">安全中心</h3>
							<!--<span class="sub-title">Welcome to Static Table</span>-->
							<div class="state-information">
								<ol class="breadcrumb m-b-less bg-less">
									<li><a href="#">安全中心</a></li>
									<li><a href="#">评论管理</a></li>
									<li class="active">评论列表</li>
								</ol>
							</div>
						</div>

						<div class="bops-pub-param-body">
							<div>
								<table class="bops-pub-param-table">
								 <header class="panel-heading ">
                                                    
                                                    <span class="tools pull-right">
                                                      <a href="javascript :;" onclick="javascript :history.back(-1);" class="ops-pub-btn">
																<i class="fa fa-arrow-left"></i>
																<span>返回</span>
															</a>
															<a class="ops-pub-btn" href="" >
																<i class="fa fa-refresh"></i>
																<span>刷新</span>
															</a>
                                                    </span>
                                                </header>
									<tbody>
										<tr>
											<th>角色名称</th>
											<td><input id="roleName" name="roleName" type="text"
												placeholder="请输入角色名称" ng-model="roleData.roleName"
												class="ng-pristine ng-untouched ng-valid"></td>
										</tr>
										<tr>
											<th>所属部门</th>
											<td><select id="roleDept" name="roleDept"
												ng-model="roleData.roleDept"
												class="ng-pristine ng-untouched ng-valid">
													<option value="财务部">财务部</option>
													<option value="人事行政部">人事行政部</option>
													<option value="产品技术部">产品技术部</option>
													<option value="平台运营部">平台运营部</option>
													<option value="采购供应部">采购供应部</option>
											</select></td>
										</tr>
										<tr>
											<th style="vertical-align: top">权限列表</th>
											<td>
												<div id="authList" class="clear">
													<!-- 插入列 -->
													<c:forEach var="item_list" items="${result}">
													<c:forEach var="item" items="${item_list}">
														<!-- 插入标题-->
														<dl style="float: left; margin: 0 15px 20px 0;">
															<dt style="font-size: 16px; font-weight: blod;">
																<input type="checkbox" value="0"
																	style="margin-right: 5px">
																	<c:out value="${item.key}"></c:out>
															</dt>
															<!-- 插入标题 end-->
															<c:forEach var="i" items="${item.value}">
																<!-- 插入元素-->
																<dd style="padding-left: 15px;">
																	<input type="checkbox" value="${i.authId}"
																		style="margin-right: 5px">${i.authName}
																</dd>

																<!-- 插入元素end-->
																</c:forEach>
															</c:forEach>
														</dl>
													</c:forEach>
													<!-- 插入列  end-->
													
													<!-- 插入列  end-->
												
													
													<!-- 插入列  end-->
												</div>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div style="text-align: center;">
								<c:choose>
									<c:when test="${role_action =='add' }">
										<input type="button" onclick="submit_add()"
											value="<<<     保存      >>>" />
									</c:when>
									<c:when test="${role_action =='put' }">
										<input type="button" onclick="submit_put()"
											value="<<<     保存      >>>" />
									</c:when>
									<c:when test="${role_action =='page' }"></c:when>
									<c:otherwise>
										<input type="button" onclick="submit_put()"
											value="<<<     保存      >>>" />
									</c:otherwise>
								</c:choose>

							</div>
							<br /> <br />

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
	<script src="/resources/js/ajax.js"></script>
	<!-- 侧面导航栏组件 js -->
	<script src="/resources/js/sidebar.js"></script>
	<script src="/resources/js/jquery.pagination.js"></script>
	<script type="text/javascript">
		$('#light-pagination').pagination({
			pages : "${page.pageCount}",
			cssStyle : 'light-theme',
			currentPage : "${page.page}",
			onPageClick : function(pageNumber, event) {
				window.location.href = 'page?page=' + pageNumber;
			}
		});
		//删除方法
		function deleteById(roleId) {
			$.messager.confirm("删除角色", "您确定要删除此角色!", function() {
				$.ajax({
					type : 'POST',
					url : '/role/' + roleId,
					dataType : 'json',
					success : function(data) {
						window.location.reload();
					},
					error : function() {
						alert("系统异常");
					}

				});
			});

		}
	</script>

	<!-- 初始化数据 -->
	<script type="text/javascript">
	var $min = '${min}';
	var $max = '${max}';
		//初始化数据
		(function() {
			var $role_json = JSON.parse('${role}');
			var $idsList = eval($role_json.authIds);
			var $roleName = $role_json.roleName;
			var $roleDept = $role_json.roleDept;
			var sign = '${role_action}';
			if (sign == 'page' || sign == "put") {
				$(document).ready(function() {
					$(":checkbox").each(function() {
						$(this).prop("checked", false);
					});
					$("#roleName").val($roleName);
					$("#roleDept").val($roleDept);
					if (sign == 'page') {
						$("#roleName").attr("disabled", "disabeld");
						$("#roleDept").attr("disabled", "disabeld");
						$(":checkbox").each(function() {
							$(this).attr("disabled", "disabeld");
						});
					}
					for (var i = parseInt($min); i < parseInt($max); i++) {
						var ids = "[value='" + $idsList[i] + "']";
						$(ids).prop("checked", true);
					}
				});
			}
		})();

		//新建数据
		function submit_add() {
			var roleName = $("#roleName").val();
			var roleDept = $("#roleDept").val();
			var authIds = "[";
			var k = 0;
			for (var m = $min; m <= $max; m++) {
				var ids = "[value='" + m + "']";
				if ($(ids).prop('checked')) {
					var tmp = "\"";
					tmp += m;
					tmp += "\",";
					authIds += tmp;
					k++;
				}
			}
			authIds = authIds.substring(0, authIds.length - 1);
			authIds += "]";
			var datas = {
				"roleName" : roleName,
				"roleDept" : roleDept,
				"authIds" : authIds
			};
			to_ajax('/role/add/', 'POST', datas, callback_add, '服务忙，请稍后重试！');

		}

		function callback_add(data) {
			//alert(JSON.stringify(data));
			alert(data.msg);
			document.location.href = '/role/page';
		}

		//修改数据
		function submit_put() {
			var $role_id = JSON.parse('${role}').roleId;
			var roleName = $("#roleName").val();
			var roleDept = $("#roleDept").val();
			var authIds = "[";
			var k = 0;
			for (var m = parseInt($min); m <= parseInt($max); m++) {
				var ids = "[value='" + m + "']";
				if ($(ids).prop('checked')) {
					var tmp = "\"";
					tmp += m;
					tmp += "\",";
					authIds += tmp;
					k++;
				}
			}
			authIds = authIds.substring(0, authIds.length - 1);
			authIds += "]";
			var datas = {
				"roleName" : roleName,
				"roleDept" : roleDept,
				"authIds" : authIds
			};
			to_ajax('/role/put/' + $role_id, 'POST', datas, callback_put,
					'服务忙，请稍后重试！');

		}

		function callback_put(data) {
			//alert(JSON.stringify(data));
			alert(data.msg);
			document.location.href = '/role/page';
		}
	</script>

</body>
</html>