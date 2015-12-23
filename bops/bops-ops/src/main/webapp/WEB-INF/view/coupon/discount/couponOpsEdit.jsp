<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

        <title>优惠券模板管理</title>

        <!-- 主体部分样式表 -->
        <link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css">
         <link rel="stylesheet" href="/resources/css/bootstrap/button.css" >
        <link rel="stylesheet" href="/resources/css/font-awesome.min.css">
        <link rel="stylesheet" href="/resources/css/layout.css">
        <link rel="stylesheet" href="/resources/css/ops.css">
        <link rel="stylesheet" href="/resources/css/sidebar.css">
        <link rel="stylesheet" href="/resources/css/content-header.css">
       <!--  <link rel="stylesheet" href="/resources/css/datapage.css">
        <link rel="stylesheet" href="/resources/css/form.css"> -->
        <link rel="stylesheet" href="/resources/css/list-content.css">
        <link rel="stylesheet" href="/resources/css/pagination.css">
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
                        <!-- 每页的部分 -->
                        <div class="data-page-wrap">
                        <div class="con">
				        <div class="page-head">				           
				            <div class="state-information">
				                <ol class="breadcrumb m-b-less bg-less">
				                    <li><a href="#">主页</a></li>
				                    <li><a href="#">优惠券管理</a></li>
				                    <li class="active">优惠券列表</li>
				                </ol>
				            </div>
				        </div>
				
				        <div class="info">
				            <div class="info-top">
				                <span>优惠券模板</span>
				                <div class="back">
				                    <a href="javascript :;" onclick="javascript :history.back(-1);" class="button button-rounded button-tiny">返回</a>					                  		                   
				                </div>
				            </div>
				            <div class="search-form">
				                
				            </div>
				            <!--biaoge-->
				            <form id="couponForm" class="form-horizontal" method="post">
				            <table class="table table-bordered table-striped table-condensed table-responsive">
				                <thead>
				                <tr>
				                	<th></th>
                                    <th>优惠券模板编号</th>
                                    <th>优惠券模板名称</th>			                    
				                </tr>
				                </thead>
			                <tbody>
							       <c:forEach items="${list}" var="row" varStatus="status">
                                       <tr>
                                           <td><input type="checkbox" name="couponTemplateId" 
                                               <c:if test='${row.flag =="1"}'> checked="true"</c:if>
                                               value="${row.couponTemplateId}"/></td>
                                           <td>${row.couponTemplateId}</td>
                                           <td>${row.couponName}</td>
                                       </tr>
                                   </c:forEach>
				                </tbody>
				            </table>
				            </form>
				             <div class="page">					               
								 <a class="button button-primary button-raised button-small pull-right" href="javascript:void(0);" id="save">保存</a>
				            </div>
				        </div>
				    </div>
                        
                        <%-- <!-- 2222222222222 -->
                            <div class="data-page-inner">
                                <div class="page-head">
                                    <!-- <h3 class="m-b-less">优惠券编辑</h3>
                                    <span class="sub-title">Welcome to Static Table</span> -->
                                    <div class="state-information">
                                        <ol class="breadcrumb m-b-less bg-less">
                                            <li><a href="#">主页</a></li>
                                            <li><a href="#">优惠券管理</a></li>
                                            <li class="active">优惠券列表</li>
                                        </ol>
                                    </div>
                                </div>
                                <div class="wrapper">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <form id="couponForm" class="form-horizontal" method="post">
                                                <section class="panel">
                                                    <header class="panel-heading">优惠券模板<a href="javascript:history.go(-1)" class="btn btn-info pull-right">返回</a></header>
                                                     <table aria-describedby="DataTables_Table_0_info" 
                                                       role="grid" id="DataTables_Table_0" 
                                                       class="table convert-data-table data-table dataTable no-footer">
                                                    <thead>
                                                        <tr>
                                                        	<th></th>
                                                            <th>优惠券模板编号</th>
                                                        	<th>优惠券模板名称</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${list}" var="row" varStatus="status">
                                                            <tr>
                                                            	<td><input type="checkbox" name="couponTemplateId" 
																	<c:if test='${row.flag =="1"}'> checked="true"</c:if>
																	value="${row.couponTemplateId}"/></td>
                                                            	<td>${row.couponTemplateId}</td>
                                                            	<td>${row.couponName}</td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                                </section>
                                                <section class="panel">
                                                    <div class="panel-body">
                                                        <div class="form-group btnlist">
                                                            <a class="btn btn-info" href="javascript:void(0);" id="save">保存</a>
                                                        </div>
                                                    </div>
                                                </section>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        <!--  2222222222222--> --%>
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
        <!-- 侧面导航栏组件 js -->
        <script src="/resources/js/sidebar.js"></script>
        <script src="/resources/js/jquery_upload/vendor/jquery.ui.widget.js"></script>
        <script src="/resources/js/jquery_upload/jquery.iframe-transport.js"></script>
        <script src="/resources/js/jquery_upload/jquery.fileupload.js"></script>
        <script src="/resources/js/My97DatePicker/WdatePicker.js"></script>
        <script type="text/javascript">
            $("#save").click(function () {
                $.ajax({
                    type: "POST",
                    url: "/coupon/discount/update",
                    data: $("#couponForm").serialize(),
                    cache: false,
                    dataType: 'json',
                    success: function (data) {
                        if (data.code == 200) {
                            window.location.href = "/coupon/discount/page";
                        } else {
                        	alert(data.code);
                            alert("code: " + data.code + "\n" + data.msg);
                        }
                    },
                    error: function(XMLHttpRequest, textStatus, errorThrown) {
                        alert(errorThrown);
                    }
                });
            });
        </script>
    </body>
</html>