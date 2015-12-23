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

        <title>运营管理系统</title>

        <!-- 主体部分样式表 -->
        <link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css" >
        <link rel="stylesheet" href="/resources/css/font-awesome.min.css" >
        <link rel="stylesheet" href="/resources/css/layout.css" >
        <link rel="stylesheet" href="/resources/css/ops.css" >
        <link rel="stylesheet" href="/resources/css/sidebar.css" >
        <link rel="stylesheet" href="/resources/css/content-header.css" >
        <link rel="stylesheet" href="/resources/css/datapage.css" >
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
                    <div class="layout-right-bottom" >
                        <!-- 
                            这部分写各自页面对应的内容
                            注意锁紧格式
                            范围 start -
                         -->
                        <div class="data-page-wrap">
                            <div class="data-page-inner">
                                <div class="page-head">
                                    <!-- 页面标题 -->
                                    <h3 class="m-b-less">
                                        系统权限管理
                                    </h3>
                                    <!--面包屑导航-->
                                    <div class="state-information">
                                        <ol class="breadcrumb m-b-less bg-less">
                                            <li><a href="#">主页</a></li>
                                            <li><a href="#">系统权限管理</a></li>
                                            <li class="active">系统权限列表</li>
                                        </ol>
                                    </div>
                                </div>
                                <div class="wrapper">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <section class="panel">
                                                <header class="panel-heading ">


                                                    系统权限列表
                                                    <span class="tools pull-right">
                                                    <a class="ops-pub-btn" href="" >
																<i class="fa fa-refresh"></i>
																<span>刷新</span>
															</a>
                                                        <a href="javascript:;" class="btn btn-info pull-right" onclick="add()">新增</a>
                                                       
                                                    </span>
                                                </header>
                                                <div class="dataTables_wrapper form-inline dt-bootstrap no-footer" 
                                                     id="DataTables_Table_0_wrapper">
                                                    <div class="tbl-head">

                                                    </div>
                                                    <table aria-describedby="DataTables_Table_0_info" 
                                                           role="grid" id="DataTables_Table_0" 
                                                           class="table convert-data-table data-table dataTable no-footer">
                                                        <thead>
                                                            <tr role="row">
                                                                <th aria-label="OrderDate : activate to sort column descending" 
                                                                    aria-sort="ascending" style="width: 184px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting_asc">
                                                                    权限码
                                                                </th>
                                                                <th aria-label="Region : activate to sort column ascending" 
                                                                    style="width: 135px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    权限名称
                                                                </th>
                                                                <th aria-label="Rep : activate to sort column ascending" 
                                                                    style="width: 181px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    权限方法
                                                                </th>
                                                                <th aria-label="Item : activate to sort column ascending" 
                                                                    style="width: 133px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    所属模块
                                                                </th>

                                                                <th aria-label="Item : activate to sort column ascending" 
                                                                    style="width: 133px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    创建时间
                                                                </th>

                                                                <th aria-label="Rep : activate to sort column ascending" 
                                                                    style="width: 181px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    操作
                                                                </th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach items="${list}" var="auth" varStatus="status">
                                                                <tr>
                                                                    <td>
                                                                        ${auth.authCode}
                                                                    </td>
                                                                    <td> ${auth.authName}</td>
                                                                    <td> ${auth.authScope}</td>
                                                                    <td> ${auth.moduleName}</td>
                                                                    <td> 
                                                                        <fmt:formatDate value="${auth.createTime}" type="both" />
                                                                    </td>
                                                                    <td>
                                                                        <a id="${auth.authId}" href="javascript:;" onclick="view('${auth.authId}', '${auth.authCode}', 
                                                                            '${auth.authName}', '${auth.authScope}', '${auth.moduleName}', 
                                                                            '${auth.authDesc}')">查看</a> | <a id="${auth.authId}" href="javascript:;" 
                                                                            onclick="update('${auth.authId}', '${auth.authCode}', '${auth.authName}', '${auth.authScope}', 
                                                                                '${auth.moduleName}', '${auth.authDesc}', '${auth.createTime}')">修改</a> | <a id="${auth.authId}" 
                                                                                     href="javascript:;" onclick="deleteById('${auth.authId}')">删除</a>
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
                                                                 class="dataTables_info">共有 ${page.total}条, 每页显示  ${page.pageSize}</div>
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
        <div class="modal fade" id="authOperateDiv" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close"
                                data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <h4 class="modal-title" id="authTitle">
                            新增权限
                        </h4>
                    </div>
                    <input id="authId" value="" type="hidden">
                    <input id="createTime" value="" type="hidden">
                    <div style="margin : 10px 0px 10px 0px;">
                        权限CODE:
                        <input type="text" id="authCode" value="" >
                    </div>
                    <div style="margin : 10px 0px 10px 0px;">
                        权限名称:
                        <input type="text" id="authName" value="" >
                    </div>
                    <div style="margin : 10px 0px 10px 0px;">
                        权限方法:
                        <input type="text" id="authScope" value="" >
                    </div>
                    <div style="margin : 10px 0px 10px 0px;">
                        所属模块:
                        <input type="text" id="moduleName" value="" >
                    </div>
                    <div style="margin : 10px 0px 10px 0px;">
                        权限描述:
                        <input type="text" id="authDesc" value="" >
                    </div>
                    <div class="modal-footer">
                        <button type="button" id="submitButton" onclick="submit();" class="btn btn-primary">
                            确定
                        </button>
                        <button type="button" aria-hidden="true" data-dismiss="modal" class="btn btn-primary">
                            取消
                        </button>
                    </div>
                </div><!-- /.modal-content -->
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
                pages: "${page.pageCount}",
                cssStyle: 'light-theme',
                currentPage: "${page.page}",
                onPageClick: function (pageNumber, event) {
                    window.location.href = 'page?page=' + pageNumber;
                }
            });
            //删除方法
            function deleteById(authId) {
                $.messager.confirm("删除权限", "您确定要删除此权限!", function () {
                    $.ajax({
                        type: 'POST',
                        url: '/auth/delete/' + authId,
                        dataType: 'json',
                        success: function (data) {
                            window.location.reload();
                        },
                        error: function () {
                            alert("系统异常");
                        }

                    });
                });

            };
            //新增方法
            function add() {
                $('#authTitle').html("新增权限");
                $('#authId').val('');
                $('#createTime').val('');
                $('#authCode').attr("disabled", false);
                $('#authCode').val('');
                $('#authName').attr("disabled", false);
                $('#authName').val('');
                $('#authScope').attr("disabled", false);
                $('#authScope').val('');
                $('#moduleName').attr("disabled", false);
                $('#moduleName').val('');
                $('#authDesc').attr("disabled", false);
                $('#authDesc').val('');
                $('#submitButton').show();
                $("#authOperateDiv").modal("show");

            };
            //修改方法
            function update(authId, authCode, authName, authScope, moduleName, authDesc, createTime) {
                $('#authTitle').html("修改权限");
                $('#authId').val(authId);
                $('#createTime').val(createTime);
                $('#authCode').attr("disabled", false);
                $('#authCode').val(authCode);
                $('#authName').attr("disabled", false);
                $('#authName').val(authName);
                $('#authScope').attr("disabled", false);
                $('#authScope').val(authScope);
                $('#moduleName').attr("disabled", false);
                $('#moduleName').val(moduleName);
                $('#authDesc').attr("disabled", false);
                $('#authDesc').val(authDesc);
                $('#submitButton').show();
                $("#authOperateDiv").modal("show");

            };
            //查看方法
            function view(authId, authCode, authName, authScope, moduleName, authDesc) {
                $('#authTitle').html("查看权限");
                $('#authId').val(authId);
                $('#authCode').attr("disabled", true);
                $('#authCode').val(authCode);
                $('#authName').attr("disabled", true);
                $('#authName').val(authName);
                $('#authScope').attr("disabled", true);
                $('#authScope').val(authScope);
                $('#moduleName').attr("disabled", true);
                $('#moduleName').val(moduleName);
                $('#authDesc').attr("disabled", true);
                $('#authDesc').val(authDesc);
                $('#submitButton').hide();
                $("#authOperateDiv").modal("show");

            };
            //提交新增或者修改
            function submit() {
                var title = $('#authTitle').html();
                var url;
                var data;
                var authCode = $('#authCode').val();
                var authName = $('#authName').val();
                var authScope = $('#authScope').val();
                var moduleName = $('#moduleName').val();
                var authDesc = $('#authDesc').val();
                if(title == "新增权限") {
                    url = "/auth/add";
                    data = {"authCode" : authCode,"authName" : authName,"authScope" : authScope,"moduleName" : moduleName,"authDesc" : authDesc};
                } else if(title == "修改权限") {
                    url = "/auth/update";
                    var authId = $('#authId').val();
                    var createTime = $('#createTime').val();
                    data = {"authId" : authId,"authCode" : authCode,"authName" : authName,"authScope" : authScope,"moduleName" : moduleName,"authDesc" : authDesc,"createTime" : createTime};
                }
                $.ajax({
                    type: 'POST',
                    url: url,
                    data: data,
                    dataType: 'json',
                    success: function (data) {
                        window.location.reload();
                    },
                    error: function () {
                        alert("系统异常");
                    }

                });

            };
        </script>
        </script>
    </body>
</html>