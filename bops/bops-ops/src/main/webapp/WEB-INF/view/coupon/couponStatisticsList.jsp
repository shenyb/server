<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
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

        <title>优惠券核销管理</title>

        <!-- 主体部分样式表 -->
        <link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css" >
        <link rel="stylesheet" href="/resources/css/bootstrap/button.css" >
        <link rel="stylesheet" href="/resources/css/font-awesome.min.css" >
        <link rel="stylesheet" href="/resources/css/layout.css" >
        <link rel="stylesheet" href="/resources/css/ops.css" >
        <link rel="stylesheet" href="/resources/css/sidebar.css" >
        <link rel="stylesheet" href="/resources/css/content-header.css" >
		 <link rel="stylesheet" href="/resources/css/list-content.css">
        <link rel="stylesheet" href="/resources/css/modal.css" >
        <!-- <link rel="stylesheet" href="/resources/css/datapage.css" > -->
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
                <%@ include file="/resources/html/sidebar.jsp" %>
            </div>
            <div class="layout-right">
                <div class="layout-right-inner">
                    <div class="layout-right-top">
                        <%@ include file="/resources/html/content_header.jsp" %>
                    </div>
                    <div class="layout-right-bottom">
                        <!-- 每页的部分 -->
                        <div class="data-page-wrap">
                        <div class="con">
				        <div class="page-head">				           
				            <div class="state-information">
				                <ol class="breadcrumb m-b-less bg-less">
				                    <li><a href="#">主页</a></li>
				                    <li><a href="#">优惠券核销管理</a></li>
				                    <li class="active">优惠券核销列表</li>
				                </ol>
				            </div>
				        </div>
				
				        <div class="info">
				            <div class="info-top">
				                <span>优惠券核销列表</span>
				                <div class="back">				                    
				                    <a class="button button-rounded button-tiny" href="/coupon/export">优惠券导出</a>			                   
				                </div>
				            </div>
				            <div class="search-form">
				                <form class="form-inline" action="/coupon/statistics" method="get">
				                    <div class="row">
					                    <div class="form-group col-xs-3">
					                        <label for="couponTemplateNo">券模板编号</label>					                       
					                   		<input type="text" class="form-control" id="couponTemplateNo" name="couponTemplateNo" value="${page.couponTemplateNo}">
					                    </div>
					                     
					                    <div class="form-group col-xs-3">      					                   				                 
				                  		 <button type="submit" class="button button-primary button-raised button-small"><i class="fa fa-search"></i>&nbsp;&nbsp;搜索</button>
				                  		</div>	
				                    </div>
				                </form>
				            </div>
				            <!--biaoge-->
				            <table class="table table-bordered table-striped table-condensed table-responsive">
				                <thead>
				                <tr>
				                	<th>券模版编号</th>
                                    <th>申请数量</th>
                                    <th>领取数</th>
                                    <th>使用数</th>
                                    <th>使用率</th>
                                    <th>面额</th>
                                    <th>使用限额</th>
                                    <th>面向品类</th>
                                    <th>状态</th>
                                    <th>开始时间</th>
                                    <th>结束时间</th>
                                    <th>查阅详情</th>				                    
				                </tr>
				                </thead>
			                <tbody>
							      <c:forEach items="${list}" var="coupon" varStatus="status">
                                       <tr>
                                           <td>${coupon.couponTemplateNo}</td>
                                           <td>${coupon.maxCount}</td>
                                           <td>${coupon.receiveCount}</td>
                                           <td>${coupon.useCount}</td>
                                           <td>${coupon.useRate}</td>
                                           <td>${coupon.value}</td>
                                           <td>${coupon.minCost}</td>
                                           <td>通用</td>
                                           <td>
                                               <c:choose>
                                                   <c:when test="${coupon.couponStatus=='draft'}">
                                                       草稿
                                                   </c:when>
                                                   <c:when test="${coupon.couponStatus=='reject'}">
                                                       驳回
                                                   </c:when>
                                                   <c:when test="${coupon.couponStatus=='invalid'}">
                                                       待审核
                                                   </c:when>
                                                   <c:when test="${coupon.couponStatus=='valid'}">
                                                       生效
                                                   </c:when>
                                                   <c:when test="${coupon.couponStatus=='frozen'}">
                                                       冻结
                                                   </c:when>
                                               </c:choose> 
                                           </td>
                                           <td>
                                               <fmt:formatDate value="${coupon.startTime}" type="both" pattern='yyyy-MM-dd HH:mm:ss'/>
                                           </td>
                                           <td>
                                               <fmt:formatDate value="${coupon.endTime}" type="both" pattern='yyyy-MM-dd HH:mm:ss'/>
                                           </td>
                                           <td>
                                               <a href="./statistics/detail?couponTemplateId=${coupon.couponTemplateId}">查阅详情</a>
                                               <c:choose>
                                                   <c:when test="${coupon.couponStatus!='frozen'}">
                                                       | <a onclick="freeze('./freeze/${coupon.couponTemplateId}')">冻结</a>
                                                   </c:when>
                                               </c:choose> 
                                           </td>
                                       </tr>
                                   </c:forEach>
				                </tbody>
				            </table>
				             <div class="page">					               
								<div class="pagenum">
									<div aria-live="polite" role="status" id="DataTables_Table_0_info"class="dataTables_info">
										共有 ${page.total}条, 每页显示  ${page.pageSize} 条 
									</div> 
								</div>								
								<div class="tbl-pagin pull-right">
									<div id="light-pagination"></div>
								</div>
				            </div>
				        </div>
				    </div> 
                        
                       <%--  <!-- 22222222222222 -->
                            <div class="page-head">
                                <h3 class="m-b-less">
                                    优惠券管理
                                </h3>
                                <!--<span class="sub-title">Welcome to Static Table</span>-->
                                <div class="state-information">
                                    <ol class="breadcrumb m-b-less bg-less">
                                        <li><a href="#">主页</a></li>
                                        <li><a href="#">优惠券核销管理</a></li>
                                        <li class="active">优惠券核销列表</li>
                                    </ol>
                                </div>
                            </div>
                            <div class="wrapper">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <section class="panel">
                                            <header class="panel-heading ">
                                                <span class="tools pull-right">
                                                    <a class="fa fa-repeat box-refresh" href="javascript:;"></a>
                                                    <a class="t-close fa fa-times" href="javascript:;"></a>
                                                     <a href="/coupon/export" class="btn btn-info pull-right">优惠券导出</a>
                                                </span>
                                            </header>
                                            <div class="dataTables_wrapper form-inline dt-bootstrap no-footer" 
                                                 id="DataTables_Table_0_wrapper">
                                                <div class="tbl-head">
                                                    <form action="/coupon/statistics" method="get">
                                                        <input type="text" id="couponTemplateNo" name="couponTemplateNo"
                                                            value="${page.couponTemplateNo}" placeholder="输入优惠券模板编号进行搜索">
                                                        <input type="submit" class="btn btn-info" value="搜索" />
                                                    </form>
                                                </div>
                                                <table aria-describedby="DataTables_Table_0_info" 
                                                       role="grid" id="DataTables_Table_0" 
                                                       class="table convert-data-table data-table dataTable no-footer">
                                                    <thead>
                                                        <tr>
                                                            <th>券模版编号</th>
                                                            <th>申请数量</th>
                                                            <th>领取数</th>
                                                            <th>使用数</th>
                                                            <th>使用率</th>
                                                            <th>面额</th>
                                                            <th>使用限额</th>
                                                            <th>面向品类</th>
                                                            <th>状态</th>
                                                            <th>开始时间</th>
                                                            <th>结束时间</th>
                                                            <th>查阅详情</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${list}" var="coupon" varStatus="status">
                                                            <tr>
                                                                <td>${coupon.couponTemplateNo}</td>
                                                                <td>${coupon.maxCount}</td>
                                                                <td>${coupon.receiveCount}</td>
                                                                <td>${coupon.useCount}</td>
                                                                <td>${coupon.useRate}</td>
                                                                <td>${coupon.value}</td>
                                                                <td>${coupon.minCost}</td>
                                                                <td>通用</td>
                                                                <td>
                                                                    <c:choose>
                                                                        <c:when test="${coupon.couponStatus=='draft'}">
                                                                            草稿
                                                                        </c:when>
                                                                        <c:when test="${coupon.couponStatus=='reject'}">
                                                                            驳回
                                                                        </c:when>
                                                                        <c:when test="${coupon.couponStatus=='invalid'}">
                                                                            待审核
                                                                        </c:when>
                                                                        <c:when test="${coupon.couponStatus=='valid'}">
                                                                            生效
                                                                        </c:when>
                                                                        <c:when test="${coupon.couponStatus=='frozen'}">
                                                                            冻结
                                                                        </c:when>
                                                                    </c:choose> 
                                                                </td>
                                                                <td>
                                                                    <fmt:formatDate value="${coupon.startTime}" type="both" pattern='yyyy-MM-dd HH:mm:ss'/>
                                                                </td>
                                                                <td>
                                                                    <fmt:formatDate value="${coupon.endTime}" type="both" pattern='yyyy-MM-dd HH:mm:ss'/>
                                                                </td>
                                                                <td>
                                                                    <a href="./statistics/detail?couponTemplateId=${coupon.couponTemplateId}">查阅详情</a>
                                                                    <c:choose>
                                                                        <c:when test="${coupon.couponStatus!='frozen'}">
                                                                            | <a onclick="freeze('./freeze/${coupon.couponTemplateId}')">冻结</a>
                                                                        </c:when>
                                                                    </c:choose> 
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                                <div class="tbl-footer clearfix">
                                                    <div class="tbl-info pull-left">
                                                        <div aria-live="polite" 
                                                             role="status" 
                                                             id="DataTables_Table_0_info" 
                                                             class="dataTables_info">共有 ${page.total}条, 每页显示  ${page.pageSize} 条</div>
                                                    </div>
                                                    <div class="tbl-pagin pull-right">
                                                        <div id="light-pagination"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </section>
                                    </div>
                                </div>
                            </div>          
                        <!--  222222222222222--> --%>
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
                pages: "${page.pageCount}",
                cssStyle: 'light-theme',
                currentPage: "${page.page}",
                onPageClick: function (pageNumber, event) {
                    window.location.href = 'statistics?page=' + pageNumber;
                }
            });

            function freeze(url) {
                $.messager.confirm("冻结优惠券", "您确定要冻结该优惠券？", function() {
                    $.ajax({
                        type: "PUT",
                        url: url,
                        dataType: "json",
                        success: function (data) {
                            if(data.code == 200) {
                                window.location.reload();
                            } else {
                                alert(data.message);
                            }
                        }
                    });
                });
            };

        </script>
    </body>
</html>