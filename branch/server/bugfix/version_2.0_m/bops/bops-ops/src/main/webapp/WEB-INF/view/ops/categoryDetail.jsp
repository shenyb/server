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

<title>OPS管理系统</title>

<!-- 主体部分样式表 -->
<link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/bootstrap/button.css">
<link rel="stylesheet" href="/resources/css/font-awesome.min.css">
<link rel="stylesheet" href="/resources/css/layout.css">
<link rel="stylesheet" href="/resources/css/ops.css">
<link rel="stylesheet" href="/resources/css/sidebar.css">
<link rel="stylesheet" href="/resources/css/content-header.css">
<!-- <link rel="stylesheet" href="/resources/css/data page.css">
<link rel="stylesheet" href="/resources/css/for m.css"> -->
<link rel="stylesheet" href="/resources/css/pagination.css">
<link rel="stylesheet" href="/resources/css/profile-table.css">
<link rel="stylesheet" href="/resources/css/modal.css" >
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
								<div class="state-information">
									<ol class="breadcrumb m-b-less bg-less">
										<li><a href="#">主页</a></li>
										<li><a href="#">检索分类</a></li>
										<li class="active">分类详情</li>
									</ol>
								</div>
							</div>
							<div class="info">
					            <div class="info-top">
					                <span>分类详情 </span>
					                <div class="back">
					                    <a href="javascript:void(0)" onclick="javascript :history.back(-1);" class="button button-rounded button-tiny">返回</a>				                 			                   
					                </div>
					            </div>
				           		<form id="" class="form-horizontal">
										<div class="panel-body">
											<div class="form-group">
												<label for="" class="col-lg-2">分类ID</label>
												<div class="col-lg-9">
													${bopsGoodsCategoriesVO.categoryId}
												</div>
											</div>
											<div class="form-group">
												<label for="" class="col-lg-2">分类名称</label>
												<div class="col-lg-9">
													${bopsGoodsCategoriesVO.categoryName}
												</div>
											</div>
											
											<div class="form-group">
												<label for="" class="col-lg-2">分类权值</label>
												<div class="col-lg-9">
													${bopsGoodsCategoriesVO.categoryScore}
												</div>
											</div>
											
											<div class="form-group">
												<label for="" class="col-lg-2">分类ICON</label>
												<div class="col-lg-9">
													<img alt="" src="${imgUrl }${bopsGoodsCategoriesVO.categoryPicKey}">
												</div>
											</div>
											<div class="form-group">
												<label for="" class="col-lg-2">新增人</label>
												<div class="col-lg-9">
													${bopsGoodsCategoriesVO.createUserName}</div>
											</div>
											<div class="form-group">
												<label for="" class="col-lg-2">新增时间</label>
												<div class="col-lg-9">
													<fmt:formatDate
														value="${bopsGoodsCategoriesVO.createTime}" type="both" />
												</div>
											</div>
											<div class="form-group">
												<label for="" class="col-lg-2">最后修改人</label>
												<div class="col-lg-9">
													${bopsGoodsCategoriesVO.updateUserName}</div>
											</div>
											<div class="form-group">
												<label for="" class="col-lg-2">最后修改时间</label>
												<div class="col-lg-9">
													<fmt:formatDate
														value="${bopsGoodsCategoriesVO.updateTime}" type="both" />
												</div>
												</div>
											</div>
																											
								</form>
							 </div>
						
							<%-- <!-- 222222 -->
							<div class="wrapper">
								<div class="row">
									<div class="col-sm-12">
										<section class="panel">
											<header class="panel-heading">
												分类详情 
											<div class="back">	
											<a href="javascript:void(0)" onclick="javascript :history.back(-1);" class="button button-primary button-raised button-small pull-right">返回</a>
											</div>
											</header>
											<div class="panel-body">
												<div class="form-horizontal">
													<div class="form-group">
														<label for="" class="col-lg-2">分类ID</label>
														<div class="col-lg-4">
															${bopsGoodsCategoriesVO.categoryId}</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">分类名称</label>
														<div class="col-lg-4">
															${bopsGoodsCategoriesVO.categoryName}</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">分类ICON</label>
														<div class="col-lg-4">
															<img width="100px;" alt="" src="${imgUrl }${bopsGoodsCategoriesVO.categoryPicKey}">
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">新增人</label>
														<div class="col-lg-4">
															${bopsGoodsCategoriesVO.createUserName}</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">新增时间</label>
														<div class="col-lg-4">
															<fmt:formatDate
																value="${bopsGoodsCategoriesVO.createTime}" type="both" />
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">最后修改人</label>
														<div class="col-lg-4">
															${bopsGoodsCategoriesVO.updateUserName}</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">最后修改时间</label>
														<div class="col-lg-4">
															<fmt:formatDate
																value="${bopsGoodsCategoriesVO.updateTime}" type="both" />
														</div>
													</div>
												</div>
											</div>
										</section>
									</div>
								</div>
							</div>
							<!--  22222222--> --%>
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
	<script src="/resources/js/jquery/jquery.validate.js"></script>
	<script src="/resources/js/jquery/jquery-validate-message.js"></script>
	<!-- 侧面导航栏组件 js -->
	<script src="/resources/js/sidebar.js"></script>
	<script src="/resources/js/jquery.pagination.js"></script>
</body>
</html>