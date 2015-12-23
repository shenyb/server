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

        <title>优惠券模板管理</title>

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
       <!--  <link rel="stylesheet" href="/resources/css/datapage.css" > -->
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
				                    <li><a href="#">feed管理</a></li>
				                    <li class="active">feed已处理举报管理</li>
				                </ol>
				            </div>
				        </div>
				
				        <div class="info">
				            <div class="info-top">
				                <span>feed已处理举报管理</span>
				                <div class="back">
				                    <a href="" class="ops-pub-btn button button-rounded button-tiny">刷新</a>	
				                    		                   
				                </div>
				            </div>
				            <div class="search-form">
				                <form class="form-inline" action="/user/feed/handledReport/page" method="get">
				                    <div class="row">
					                    <div class="form-group col-xs-3">
					                        <label for="nickName">用户昵称</label>
					                        <input type="text" id="nickName" name="nickName" value="${page.nickName}" class="form-control" tabindex="1"> 
					                    </div>
					                    <div class="form-group col-xs-3">
					                        <label for="mobile">用户手机号</label>					                       
					                        <input type="text" id="mobile" name="mobile" value="${page.mobile}" class="form-control" tabindex="2"> 
					                    </div> 
					                 </div>
					                 <div class="row">
					                 <div class="form-group col-xs-3">
					                        <label for="startTimeString">开始时间</label>
					                        <input type="text"  id="startTimeString" name="startTimeString"
                                                      value="${page.startTimeString}" class="form-control" tabindex="3"
                                              onfocus="WdatePicker({firstDayOfWeek: 1, dateFmt: 'yyyy-MM-dd HH:mm:ss'})"> 
					                    </div>
					                    <div class="form-group col-xs-3">
					                        <label for="endTimeString">结束时间</label>					                       
					                        <input type="text" id="endTimeString" name="endTimeString" value="${page.endTimeString}" class="form-control" tabindex="4"
                                                onfocus="WdatePicker({firstDayOfWeek: 1, dateFmt: 'yyyy-MM-dd HH:mm:ss'})">
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
				                	<th>feed编号</th>
                                    <th>用户昵称</th>
                                    <th>用户手机号</th>
                                    <th>文字内容</th>
                                    <th>图片地址</th>
                                    <th>发布时间</th>
                                    <th>举报内容</th>
                                    <th>操作</th>			                    
				                </tr>
				                </thead>
			                <tbody>
							       <c:forEach items="${list}" var="feed" varStatus="status">
                                        <tr>
                                            <td>${feed.feedId}</td>
                                            <td>${feed.nickName}</td>
                                            <td>${feed.mobile}</td>
                                            <td>${feed.feedContent}</td>
                                            <td><img width="60px" alt="feed图片" 
                                                     src="${picHost}${feed.feedPicKey}"></td>
                                            <td>
                                                <fmt:formatDate value="${feed.createTime}" type="both" pattern='yyyy-MM-dd HH:mm:ss'/>
                                            </td>
                                            <td>${feed.reportContent}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${feed.reportStatus=='FROZEN'}">
                                                        <a href="javascript:void(0);" onclick="resetUser('../reset/${feed.feedReportId}/${feed.userId}', '${feed.nickName}')">恢复登陆</a>
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
                                    feed管理
                                </h3>
                                <!--<span class="sub-title">Welcome to Static Table</span>-->
                                <div class="state-information">
                                    <ol class="breadcrumb m-b-less bg-less">
                                        <li><a href="#">主页</a></li>
                                        <li><a href="#">feed管理</a></li>
                                        <li class="active">feed列表</li>
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
                                                </span>
                                            </header>
                                            <div class="dataTables_wrapper form-inline dt-bootstrap no-footer" 
                                                 id="DataTables_Table_0_wrapper">
                                                <div class="tbl-head">
                                                    <form action="/user/feed/report/page" method="get">
                                                        <input type="text" id="nickName" name="nickName"
                                                               value="${page.nickName}" placeholder="输入用户昵称进行搜索"> 
                                                        <input type="text" id="mobile" name="mobile"
                                                               value="${page.mobile}" placeholder="输入用户手机号进行搜索"> 
                                                        <input type="text" id="startTimeString"
                                                               name="startTimeString" placeholder="开始时间"
                                                               value="${page.startTimeString}" onfocus="WdatePicker({firstDayOfWeek: 1, dateFmt: 'yyyy-MM-dd HH:mm:ss'})">
                                                        <input type="text" id="endTimeString" name="endTimeString"
                                                               placeholder="结束时间" value="${page.endTimeString}" onfocus="WdatePicker({firstDayOfWeek: 1, dateFmt: 'yyyy-MM-dd HH:mm:ss'})">
                                                        <input type="submit" class="btn btn-info" value="搜索" />
                                                    </form>
                                                </div>
                                                <table aria-describedby="DataTables_Table_0_info" 
                                                       role="grid" id="DataTables_Table_0" 
                                                       class="table convert-data-table data-table dataTable no-footer">
                                                    <thead>
                                                        <tr>
                                                            <th>feedId</th>
                                                            <th>用户昵称</th>
                                                            <th>用户手机号</th>
                                                            <th>文字内容</th>
                                                            <th>图片地址</th>
                                                            <th>发布时间</th>
                                                            <th>操作</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${list}" var="feed" varStatus="status">
                                                            <tr>
                                                                <td>${feed.feedId}</td>
                                                                <td>${feed.nickName}</td>
                                                                <td>${feed.mobile}</td>
                                                                <td>${feed.feedContent}</td>
                                                                <td><img width="60px" alt="feed图片" 
                                                                         src="${picHost}${feed.feedPicKey}"></td>
                                                                <td>
                                                                    <fmt:formatDate value="${feed.createTime}" type="both" pattern='yyyy-MM-dd HH:mm:ss'/>
                                                                </td>
                                                                <td>
                                                                    <a href="javascript:void(0);" onclick="freezeUser('./freezeReport/${feed.feedReportId}', '${feed.nickName}')">禁止登陆</a>
                                                                    <a href="javascript:void(0);" onclick="freezeUser('./ignoreReport/${feed.feedReportId}', '${feed.nickName}')">暂不处理</a>
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
                        <!-- 222222222222222 --> --%>
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
        <script src="/resources/js/My97DatePicker/WdatePicker.js"></script>
        <script type="text/javascript">
            var mobile = $("#mobile").val();
            var nickName = $("#nickName").val();
            var startTimeString = $("#startTimeString").val();
            var endTimeString = $("#endTimeString").val();
            $('#light-pagination').pagination({
                pages: "${page.pageCount}",
                cssStyle: 'light-theme',
                currentPage: "${page.page}",
                onPageClick: function (pageNumber, event) {
                    window.location.href = 'page?page=' + pageNumber + '&mobile=' + mobile +
                            '&nickName=' + nickName + '&startTimeString=' + startTimeString + '&endTimeString=' + endTimeString;
                }
            });

            function resetUser(url, nickName) {
                $.messager.confirm("恢复用户登陆", "您确定要恢复" + nickName + "登陆？", function() {
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