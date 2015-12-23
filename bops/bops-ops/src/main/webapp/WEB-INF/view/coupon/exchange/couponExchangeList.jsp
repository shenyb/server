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
                                            <li><a href="#">优惠券兑换码管理</a></li>
                                            <li class="active">优惠券兑换码列表</li>
                                        </ol>
                                    </div>
                                </div>

                                <div class="info">
                                    <div class="info-top">
                                        <span>优惠券兑换码列表</span>
                                        <div class="back">
                                            <a href="/coupon/exchange/toAdd"  class="ops-pub-btn button button-rounded button-tiny">新增</a>					                  		                   
                                        </div>
                                    </div>
                                    <div class="search-form">
                                        <form class="form-inline" action="/coupon/exchange/page" method="get">
                                            <div class="row">
                                                <div class="form-group col-xs-3">
                                                    <label for="couponExchangeCode">兑换编码</label>						                         			                       
                                                    <input type="text" id="couponExchangeCode" name="couponExchangeCode" class="form-control" value="${page.couponExchangeCode}" tabindex="1">
                                                </div>
                                                <div class="form-group col-xs-3">
                                                    <label for="bopsUserId">申请人姓名</label>					                       
                                                    <select class="form-control" name="bopsUserId" id="bopsUserId" value="${page.bopsUserId}" tabindex="3">
                                                    <option value>全部</option>
                                                     <c:forEach items="${userList }" var="user">
                                                    <option value="${user.userId }" <c:if test="${page.bopsUserId==user.userId}">selected</c:if>>${user.userRealName}</option>
                                                    </c:forEach>                                                      
                                                  </select>
                                                </div> 
                                                <div class="form-group col-xs-3">
                                                    <label for="auditStatus">审核状态</label>					                       
                                                    <select class="form-control" name="auditStatus" id="auditStatus" value="${page.auditStatus}" tabindex="2">
                                                        <option value>全部</option>
                                                        <option value="DRAFT" <c:if test="${page.auditStatus=='DRAFT'}">selected</c:if>>草稿</option>
                                                        <option value="VALID" <c:if test="${page.auditStatus=='VALID'}">selected</c:if>>通过</option>
                                                        <option value="REJECT" <c:if test="${page.auditStatus=='REJECT'}">selected</c:if>>驳回</option>
                                                        <option value="INVALID" <c:if test="${page.auditStatus=='INVALID'}">selected</c:if>>待审核</option>
                                                        </select>
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
                                                    <th>兑换编码</th>
                                                    <th>兑换码数量</th>
                                                    <th>结束时间</th>
                                                    <th>申请人</th>
                                                    <th>状态</th>
                                                    <th>操作</th>				                    
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${list}" var="couponExchange" varStatus="status">
                                                <tr>
                                                    <td>${couponExchange.couponExchangeCode}</td>
                                                    <td>${couponExchange.couponExchangeCount}</td>
                                                    <td>
                                                        <fmt:formatDate value="${couponExchange.endTime}" type="both" pattern='yyyy-MM-dd HH:mm:ss'/>
                                                    </td>
                                                    <td>${couponExchange.userName}</td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${couponExchange.auditStatus=='DRAFT'}">
                                                                草稿
                                                            </c:when>
                                                            <c:when test="${couponExchange.auditStatus=='REJECT'}">
                                                                驳回
                                                            </c:when>
                                                            <c:when test="${couponExchange.auditStatus=='INVALID'}">
                                                                待审核
                                                            </c:when>
                                                            <c:when test="${couponExchange.auditStatus=='VALID'}">
                                                                生效
                                                            </c:when>
                                                            <c:when test="${couponExchange.auditStatus=='FROZEN'}">
                                                                冻结
                                                            </c:when>
                                                        </c:choose> 
                                                    </td>
                                                    <td>
                                                        <a href="./view/${couponExchange.couponExchangeTemplateId}">查看</a>
                                                        <c:choose>
                                                            <c:when test="${couponExchange.auditStatus=='DRAFT' || couponExchange.auditStatus=='REJECT'}">
                                                                | <a href="./edit/${couponExchange.couponExchangeTemplateId}">编辑</a>
                                                            </c:when>
                                                            <c:when test="${couponExchange.auditStatus=='INVALID'}">
                                                                | <a href="javascript:void(0);" onclick="showAuditDialog('${couponExchange.couponExchangeTemplateId}', '${couponExchange.couponExchangeCode}')">审核</a>
                                                            </c:when>
                                                            <c:when test="${couponExchange.auditStatus=='VALID'}">
                                                                | <a href="javascript:void(0);" onclick="freeze('./freeze/${couponExchange.couponExchangeTemplateId}')">冻结</a>
                                                            </c:when>
                                                        </c:choose> 
                                                        <c:choose>
                                                            <c:when test="${couponExchange.couponExchangeCount>1}">
                                                                | <a href="./export/${couponExchange.couponExchangeTemplateId}">导出</a>
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

                            <%-- <!-- 2222222222222 -->
                                <div class="page-head">
                                    <h3 class="m-b-less">
                                        优惠券兑换码管理
                                    </h3>
                                    <!--<span class="sub-title">Welcome to Static Table</span>-->
                                    <div class="state-information">
                                        <ol class="breadcrumb m-b-less bg-less">
                                            <li><a href="#">主页</a></li>
                                            <li><a href="#">优惠券兑换码管理</a></li>
                                            <li class="active">优惠券兑换码列表</li>
                                        </ol>
                                    </div>
                                </div>
                                <div class="wrapper">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <section class="panel">
                                                <header class="panel-heading ">
                                                    <span class="tools pull-right">
                                                        <a href="/coupon/exchange/toAdd" class="btn btn-info pull-right">新增</a>
                                                        <a class="fa fa-repeat box-refresh" href="javascript:;"></a>
                                                        <a class="t-close fa fa-times" href="javascript:;"></a>
                                                    </span>
                                                </header>
                                                <div class="dataTables_wrapper form-inline dt-bootstrap no-footer" 
                                                     id="DataTables_Table_0_wrapper">
                                                    <div class="tbl-head">
                                                        <form action="/coupon/exchange/page" method="get">
                                                            <input type="text" id="couponExchangeCode" name="couponExchangeCode"
                                                                value="${page.couponExchangeCode}" placeholder="输入优惠券兑换编码进行搜索"> 
                                                            <select class="form-control" name="auditStatus" id="auditStatus" value="${page.auditStatus}">
                                                                <option value>请选择审核状态</option>
                                                                <option value="DRAFT" <c:if test="${page.auditStatus=='DRAFT'}">selected</c:if>>草稿</option>
                                                                <option value="VALID" <c:if test="${page.auditStatus=='VALID'}">selected</c:if>>通过</option>
                                                                <option value="REJECT" <c:if test="${page.auditStatus=='REJECT'}">selected</c:if>>驳回</option>
                                                                <option value="INVALID" <c:if test="${page.auditStatus=='INVALID'}">selected</c:if>>待审核</option>
                                                            </select>
                                                            <input type="submit" class="btn btn-info" value="搜索" />
                                                        </form>
                                                    </div>
                                                    <table aria-describedby="DataTables_Table_0_info" 
                                                           role="grid" id="DataTables_Table_0" 
                                                           class="table convert-data-table data-table dataTable no-footer">
                                                        <thead>
                                                            <tr>
                                                                <th>兑换编号</th>
                                                                <th>兑换码</th>
                                                                <th>结束时间</th>
                                                                <th>申请人</th>
                                                                <th>状态</th>
                                                                <th>操作</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach items="${list}" var="couponExchange" varStatus="status">
                                                                <tr>
                                                                    <td>${couponExchange.couponExchangeTemplateId}</td>
                                                                    <td>${couponExchange.couponExchangeCode}</td>
                                                                    <td>
                                                                        <fmt:formatDate value="${couponExchange.endTime}" type="both" pattern='yyyy-MM-dd HH:mm:ss'/>
                                                                    </td>
                                                                    <td>${couponExchange.userName}</td>
                                                                    <td>
                                                                        <c:choose>
                                                                            <c:when test="${couponExchange.auditStatus=='DRAFT'}">
                                                                                草稿
                                                                            </c:when>
                                                                            <c:when test="${couponExchange.auditStatus=='REJECT'}">
                                                                                驳回
                                                                            </c:when>
                                                                            <c:when test="${couponExchange.auditStatus=='INVALID'}">
                                                                                待审核
                                                                            </c:when>
                                                                            <c:when test="${couponExchange.auditStatus=='VALID'}">
                                                                                生效
                                                                            </c:when>
                                                                            <c:when test="${couponExchange.auditStatus=='FROZEN'}">
                                                                                冻结
                                                                            </c:when>
                                                                        </c:choose> 
                                                                    </td>
                                                                    <td>
                                                                        <a href="./view/${couponExchange.couponExchangeTemplateId}">查看</a>
                                                                        <c:choose>
                                                                            <c:when test="${couponExchange.auditStatus=='DRAFT' || couponExchange.auditStatus=='REJECT'}">
                                                                                | <a href="./edit/${couponExchange.couponExchangeTemplateId}">编辑</a>
                                                                            </c:when>
                                                                            <c:when test="${couponExchange.auditStatus=='INVALID'}">
                                                                                | <a href="javascript:void(0);" onclick="showAuditDialog('${couponExchange.couponExchangeTemplateId}', '${couponExchange.couponExchangeCode}')">审核</a>
                                                                            </c:when>
                                                                            <c:when test="${couponExchange.auditStatus=='VALID'}">
                                                                                | <a href="javascript:void(0);" onclick="freeze('./freeze/${couponExchange.couponExchangeTemplateId}')">冻结</a>
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
                            <!-- 22222222222222 --> --%>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <div class="modal fade" id="auditCouponExchangeDiv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <h4 class="modal-title" id="myModalLabel">
                            优惠券兑换审核
                        </h4>
                    </div>
                    <div class="modal-body">
                        <!-- form表单或各种input -->
                        <form class="form-horizontal">
                            <fieldset>
                                <div class="control-group">
                                    <!-- Text input-->
                                    <label class="control-label col-lg-4" for="">优惠券兑换码</label>                              
                                    <div class="controls col-lg-5">	                                
                                        <input id="auditCouponExchangeId" value="" type="hidden">
                                        <input type="text" class="form-control" id="auditCouponExchangeCode" value="" readonly="readonly" >
                                    </div>
                                    <div style="clear: both;"></div>
                                </div>
                                <div class="control-group">
                                    <!-- Text input-->
                                    <label class="control-label col-lg-4" for="">操作备注</label>                              
                                    <div class="controls col-lg-5">	                                
                                        <textarea class="form-control" id="description" rows="3"></textarea>
                                    </div>
                                    <div style="clear: both;"></div>
                                </div>
                            </fieldset>
                        </form>
                    </div>


                    <!-- <div style="margin : 10px 0px 10px 0px;">
                        优惠券兑换码:
                        <input id="auditCouponExchangeId" value="" type="hidden">
                        <input type="text" id="auditCouponExchangeCode" value="" readonly="readonly" >
                    </div>
                    <div >
                        操作备注:
                        <textarea class="form-control" id="description" rows="3"></textarea>
                    </div> -->
                    <div class="modal-footer">
                        <button type="button" onclick="audit('VALID');" class="button button-capitalize button-rounded button-primary button-small">
                            审核通过
                        </button>
                        <button type="button" onclick="audit('REJECT');" class="button button-capitalize button-rounded button-primary button-small">
                            审核驳回
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

            function showAuditDialog(couponExchangeTemplateId, couponExchangeCode) {
                $("#auditCouponExchangeId").val(couponExchangeTemplateId);
                $("#auditCouponExchangeCode").val(couponExchangeCode);
                $("#auditCouponExchangeDiv").modal("show");
            }

            function audit(auditStatus) {
                var couponExchangeTemplateId = $("#auditCouponExchangeId").val();
                var description = $("#description").val();
                $.ajax({
                    type: "POST",
                    url: "/coupon/exchange/audit/",
                    datatype: "json",
                    data: {
                        auditStatus: auditStatus,
                        couponExchangeTemplateId: couponExchangeTemplateId,
                        description: description
                    },
                    success: function (data) {
                        if (data.code == 200) {
                            window.location.href = "/coupon/exchange/page";
                        } else {
                            alert("code: " + data.code + "\n" + data.msg);
                        }
                    }
                });
            }

            function freeze(url) {
                $.messager.confirm("冻结兑换码", "您确定要冻结该兑换码？", function () {
                    $.ajax({
                        type: "PUT",
                        url: url,
                        dataType: "json",
                        success: function (data) {
                            if (data.code == 200) {
                                window.location.reload();
                            } else {
                                alert(data.message);
                            }
                        }
                    });
                });
            }
            //回车
            $(window).bind("keydown", function (e) {
                var key = e.which;
                if (key == 13) {
                    $(".search-form .button").click();
                }
            });
        </script>
    </body>
</html>