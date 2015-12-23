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
        <!-- <link rel="stylesheet" href="/resources/css/datapage.css">
        <link rel="stylesheet" href="/resources/css/form.css"> -->
        <link rel="stylesheet" href="/resources/css/profile-table.css">
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
                        <!-- 每页的部分 -->
                        <div class="form-page-wrap">
                            <div class="data-page-inner">
                                <div class="page-head">                                  
                                    <div class="state-information">
                                        <ol class="breadcrumb m-b-less bg-less">
                                            <li><a href="#">主页</a></li>
                                            <li><a href="#">优惠券兑换管理</a></li>
                                            <li class="active">优惠券兑换列表</li>
                                        </ol>
                                    </div>
                                </div>

                                <div class="info">
                                    <div class="info-top">
                                        <span>基本信息</span>
                                        <div class="back">
                                            <a href="javascript :;" onclick="javascript :history.back(-1);" class="button button-rounded button-tiny">返回</a>				                 			                   					                	
                                        </div>
                                    </div>

                                    <form id="couponForm" class="form-horizontal" method="post">
                                        <table class="table table-bordered table-hover table-condensed table-responsive">											
                                            <tr>
                                                <td>优惠券兑换编码</td>
                                                <td>
                                                    <input class="form-control" name="couponExchangeTemplateId" value="${couponExchange.couponExchangeTemplateId}" type="hidden">
                                                    <input class="form-control" name="auditStatus" id="auditStatusInput" value="${couponExchange.auditStatus}" type="hidden">
                                                    <input class="form-control" type="text" name="couponExchangeCode" value="${couponExchange.couponExchangeCode}" >
                                                </td>
                                                <td>优惠券兑换类型</td>
                                                <td>
                                                    <select class="form-control" name="couponExchangeType" id="couponExchangeType" value="${couponExchange.couponExchangeType}">
                                                        <option value="ALL"<c:if test="${couponExchange.couponExchangeType=='ALL'}">selected</c:if>>全场领取</option>
                                                        <option value="SINGLE"<c:if test="${couponExchange.couponExchangeType=='SINGLE'}">selected</c:if>>单用户领取</option>
                                                        <option value="NOTRADE"<c:if test="${couponExchange.couponExchangeType=='NOTRADE'}">selected</c:if>>无购买用户领取</option>
                                                        </select>
                                                    </td>                     
                                                </tr>
                                                <tr>
                                                    <td>有效时间(精确到秒)</td>
                                                    <td colspan="3">
                                                        <div class="col-xs-5">
                                                            <b>开始时间</b>
                                                            <input type="text" id="startTimeString" name="startTimeString" class="form-control"
                                                                   value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' value='${couponExchange.startTime}' type='both' />" onfocus="WdatePicker({firstDayOfWeek: 1, dateFmt: 'yyyy-MM-dd HH:mm:ss'})">
                                                    </div>
                                                    <div class="col-xs-5">
                                                        <b>结束时间</b><input type="text" id="endTimeString" name="endTimeString" class="form-control"
                                                                          value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' value='${couponExchange.endTime}' type='both' />" onfocus="WdatePicker({firstDayOfWeek: 1, dateFmt: 'yyyy-MM-dd HH:mm:ss'})">
                                                    </div>
                                                </td>			
                                            </tr>
                                            <tr>
                                                <td>优惠券模板</td>
                                                <td colspan="2" class="large-group">
                                                    <select class="form-control" style="float:left;" name="couponTemplateId" id="couponTemplateId" value="">
                                                        <c:forEach items="${list}" var="item" varStatus="status">
                                                            <option value="${item.couponTemplateId}">${item.couponName}</option>
                                                        </c:forEach> 
                                                    </select>
                                                    <a href="javascript:void(0);" onClick="saveTemplate()" id="saveCouponTemplate"  class="button button-primary button-raised button-small">添加</a>
                                                </td>
                                                <td>	
                                                    <div id="saveTemplateValue" class="col-lg-9"></div>

                                                    <input type="hidden" name="couponTemplateIds" id="couponTemplateIds" value=""/>
                                                </td>						                                          
                                            </tr>
                                            <tr>	                                          
                                                <td>批量生成</td>	                                          
                                                <td colspan="3"><input class="form-control" type="number" name="couponExchangeCount" value="1" ></td>
                                            </tr>
                                            <tr>
                                                <td colspan="4">
                                                    <a class="button button-primary button-raised button-small pull-right" href="javascript:void(0);" id="save">保存</a>
                                                    <a class="button button-primary button-raised button-small pull-right" href="javascript:void(0);" id="submit">提交审核</a>								            	
                                                </td>
                                            </tr>
                                        </table>																																												
                                    </form>
                                </div>

                                <%-- <!-- 22222222222222 -->
                                <div class="wrapper">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <form id="couponForm" class="form-horizontal" method="post">
                                                <section class="panel">
                                                    <header class="panel-heading">基本信息<a href="javascript:history.go(-1)" class="btn btn-info pull-right">返回</a></header>
                                                    <div class="panel-body">
                                                        <div class="form-group">
                                                            <label for="" class="col-lg-2">优惠券兑换编码</label>
                                                            <div class="col-lg-9">
                                                                <input name="couponExchangeTemplateId" value="${couponExchange.couponExchangeTemplateId}" type="hidden">
                                                                <input name="auditStatus" id="auditStatusInput" value="${couponExchange.auditStatus}" type="hidden">
                                                                <input type="text" name="couponExchangeCode" value="${couponExchange.couponExchangeCode}" >
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="" class="col-lg-2">优惠券兑换类型</label>
                                                            <div class="col-lg-9">
                                                                <select class="form-control" name="couponExchangeType" id="couponExchangeType" value="${couponExchange.couponExchangeType}">
                                                                    <option value="ALL"<c:if test="${couponExchange.couponExchangeType=='ALL'}">selected</c:if>>全场领取</option>
                                                                    <option value="SINGLE"<c:if test="${couponExchange.couponExchangeType=='SINGLE'}">selected</c:if>>单用户领取</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="" class="col-lg-2">有效时间精确到秒</label>
                                                            <div class="col-lg-9">
                                                                <input type="text" id="startTimeString" name="startTimeString"
                                                                       placeholder="开始时间" value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' value='${couponExchange.startTime}' type='both' />" onfocus="WdatePicker({firstDayOfWeek:1,dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                                                                <input type="text" id="endTimeString" name="endTimeString"
                                                                       placeholder="结束时间" value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' value='${couponExchange.endTime}' type='both' />" onfocus="WdatePicker({firstDayOfWeek:1,dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                                                            </div>
                                                        </div>
                                                         <div class="form-group">
                                                            <label for="" class="col-lg-2">优惠券模板</label>
                                                            <div class="col-lg-9">
                                                                <select class="form-control" style="width:90%;float:left;" name="couponTemplateId" id="couponTemplateId" value="">
                                                                    <c:forEach items="${list}" var="item" varStatus="status">
                                                                                                                                                <option value="${item.couponTemplateId}">${item.couponName}</option>
                                                                                                                                        </c:forEach> 
                                                                </select>
                                                                <a class="btn btn-info" href="javascript:void(0);" onClick="saveTemplate()" id="saveCouponTemplate">添加</a>
                                                            </div>
                                                        </div>
                                                        <div id="saveTemplateValue" class="col-lg-9" style="margin-left:16%;">
                                                        </div>
                                                        <input type="hidden" name="couponTemplateIds" id="couponTemplateIds" value=""/>
                                                    </div>
                                                </section>
                                                <section class="panel">
                                                    <div class="panel-body">
                                                        <div class="form-group btnlist">
                                                            <a class="btn btn-info" href="javascript:void(0);" id="save">保存</a>
                                                            <a class="btn btn-info" href="javascript:void(0);" id="submit">提交审核</a>
                                                        </div>
                                                    </div>
                                                </section>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <!-- 2222222222222 --> --%>
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
        <!-- 侧面导航栏组件 js -->
        <script src="/resources/js/sidebar.js"></script>
        <script src="/resources/js/jquery_upload/vendor/jquery.ui.widget.js"></script>
        <script src="/resources/js/jquery_upload/jquery.iframe-transport.js"></script>
        <script src="/resources/js/jquery_upload/jquery.fileupload.js"></script>
        <script src="/resources/js/My97DatePicker/WdatePicker.js"></script>
        <script type="text/javascript">
            function saveTemplate() {
                var myselect = document.getElementById("couponTemplateId");
                var index = myselect.selectedIndex;
                var templateIndex = myselect.options[index].value;
                var templateValue = myselect.options[index].text;
                $("#saveTemplateValue").append("<span id='" + templateIndex + "'>" + templateValue +
                        "<a href='javascript:void(0);' onclick=removeThis(this)> x</a>" +
                        "</span>");
                var couponTemplateIds = $("#couponTemplateIds").val();
                if (couponTemplateIds == "") {
                    document.getElementById("couponTemplateIds").value = couponTemplateIds + templateIndex;
                } else {
                    document.getElementById("couponTemplateIds").value = couponTemplateIds + "," + templateIndex;
                }

            }
            function removeThis(obj) {
                var couponParent = $(obj).parent();
                $(couponParent).remove();
            }
            ;
            $("#save").click(function () {
                document.getElementById("couponTemplateIds").value = "";
                var couponTemplateIdsHidden = $("#saveTemplateValue span").text();
                var tempIds = $("#saveTemplateValue span").attr("id");
                $("#saveTemplateValue span").each(function () {
                    var tempId = $(this).attr("id");
                    var couponTemplateIds = $("#couponTemplateIds").val();
                    if (couponTemplateIds == "") {
                        document.getElementById("couponTemplateIds").value = couponTemplateIds + tempId;
                    } else {
                        document.getElementById("couponTemplateIds").value = couponTemplateIds + "," + tempId;
                    }
                });
                $("#auditStatusInput").val("DRAFT");
                $.ajax({
                    type: "POST",
                    url: "/coupon/exchange/add",
                    data: $("#couponForm").serialize(),
                    cache: false,
                    dataType: 'json',
                    success: function (data) {
                        if (data.code == 200) {
                            window.location.href = "/coupon/exchange/page";
                        } else {
                            alert("code: " + data.code + "\n" + data.msg);
                        }
                    }
                });
            });

            $("#submit").click(function () {
                document.getElementById("couponTemplateIds").value = "";
                var couponTemplateIdsHidden = $("#saveTemplateValue span").text();
                var tempIds = $("#saveTemplateValue span").attr("id");
                $("#saveTemplateValue span").each(function () {
                    var tempId = $(this).attr("id");
                    var couponTemplateIds = $("#couponTemplateIds").val();
                    if (couponTemplateIds == "") {
                        document.getElementById("couponTemplateIds").value = couponTemplateIds + tempId;
                    } else {
                        document.getElementById("couponTemplateIds").value = couponTemplateIds + "," + tempId;
                    }
                });
                $("#auditStatusInput").val("INVALID");
                $.ajax({
                    type: "POST",
                    url: "/coupon/exchange/add",
                    data: $("#couponForm").serialize(),
                    cache: false,
                    dataType: 'json',
                    success: function (data) {
                        if (data.code == 200) {
                            window.location.href = "/coupon/exchange/page";
                        } else {
                            alert("code: " + data.code + "\n" + data.msg);
                        }
                    }
                });
            });
        </script>

    </body>
</html>