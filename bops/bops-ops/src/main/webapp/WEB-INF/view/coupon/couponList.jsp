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
        <link rel="stylesheet" href="/resources/css/modal.css" >

        <!-- <link rel="stylesheet" href="/resources/css/datapage.css" > -->
        <link rel="stylesheet" href="/resources/css/list-content.css">
        <link rel="stylesheet" href="/resources/css/modal.css" >
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
				                    <li><a href="#">优惠券模板管理</a></li>
				                    <li class="active">优惠券模板列表</li>
				                </ol>
				            </div>
				        </div>
				
				        <div class="info">
				            <div class="info-top">
				                <span>优惠券模板列表</span>
				                <div class="back">
				                    <a href="" class="ops-pub-btn button button-rounded button-tiny">刷新</a>	
				                    <a class="bops-pub-btn button button-rounded button-tiny" href="/coupon/add">新增</a>			                   
				                </div>
				            </div>
				            <div class="search-form">
				                <form class="form-inline"  action="/coupon/page" method="get">
				                    <div class="row">
					                    <div class="form-group col-xs-3">
					                        <label for="couponTemplateNo">优惠券编号</label>
					                        <input type="text" class="form-control" id="couponTemplateNo" name="couponTemplateNo" value="${page.couponTemplateNo}" tabindex="1">
					                    </div>
					                    <div class="form-group col-xs-3">
					                        <label for="couponStatus">审核状态</label>					                       
					                        <select class="form-control" name="couponStatus" id="couponStatus" value="${page.couponStatus}" tabindex="2">
				                                <option value="">全部</option>
				                                <option value="draft" <c:if test="${page.couponStatus=='draft'}">selected</c:if>>草稿</option>
				                                <option value="valid" <c:if test="${page.couponStatus=='valid'}">selected</c:if>>通过</option>
				                                <option value="reject" <c:if test="${page.couponStatus=='reject'}">selected</c:if>>驳回</option>
				                                <option value="invalid" <c:if test="${page.couponStatus=='invalid'}">selected</c:if>>待审核</option>
				                            </select>
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
				                  		 <button type="submit" class="button button-primary button-raised button-small"><i class="fa fa-search"></i>&nbsp;&nbsp;搜索</button>
				                  		</div>	
				                    </div>
				                </form>
				            </div>
				            <!--biaoge-->
				            <table class="table table-bordered table-striped table-condensed table-responsive">
				                <thead>
				                <tr>
				                	<th>优惠劵编号</th>
                                    <th>优惠券名称</th>
                                    <th>申请人</th>
                                    <th>开始时间</th>
                                    <th>结束时间</th>
                                    <th>面额</th>
                                    <th>使用限额</th>
                                    <th>面向品类</th>
                                    <th>类型</th>
                                    <th>状态</th>
                                    <th>操作</th>				                    
				                </tr>
				                </thead>
			                <tbody>
							     <c:forEach items="${list}" var="coupon" varStatus="status">
                                        <tr>
                                            <td>${coupon.couponTemplateNo}</td>
                                            <td>${coupon.couponName}</td>
                                            <td>${coupon.userRealName}</td>
                                            <td>
                                                <fmt:formatDate value="${coupon.startTime}" type="both" pattern='yyyy-MM-dd HH:mm:ss'/>
                                            </td>
                                            <td>
                                                <fmt:formatDate value="${coupon.endTime}" type="both" pattern='yyyy-MM-dd HH:mm:ss'/>
                                            </td>
                                            <td>${coupon.value}</td>
                                            <td>${coupon.minCost}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${coupon.couponType=='useReturn'}">
                                                        使用返还
                                                    </c:when>
                                                    <c:when test="${coupon.couponType=='newUser'}">
                                                        新人注册
                                                    </c:when>
                                                    <c:when test="${coupon.couponType=='cash'}">
                                                        现金券
                                                    </c:when>
                                                    <c:when test="${coupon.couponType=='fullCut'}">
                                                        普通满减
                                                    </c:when>
                                                </c:choose> 
                                            </td>
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
                                                <a href="./view/${coupon.couponTemplateId}">查看</a>
                                                <c:choose>
                                                    <c:when test="${coupon.couponStatus=='draft' || coupon.couponStatus=='reject'}">
                                                        | <a href="./edit/${coupon.couponTemplateId}">编辑</a>
                                                    </c:when>
                                                    <c:when test="${coupon.couponStatus=='invalid'}">
                                                        | <a href="javascript:void(0);" onclick="showAuditDialog('${coupon.couponTemplateId}', '${coupon.couponTemplateNo}')">审核</a>
                                                    </c:when>
                                                    <c:when test="${coupon.couponStatus=='valid'}">
                                                        | <a href="javascript:void(0);" onclick="showSendDialog('${coupon.couponTemplateId}', '${coupon.couponTemplateNo}')">派发</a>
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
                        
                        
                       <%--  <!--  33333333333333333-->
                            <div class="page-head">
                                <h3 class="m-b-less">
                                    优惠券管理
                                </h3>
                                <!--<span class="sub-title">Welcome to Static Table</span>-->
                                <div class="state-information">
                                    <ol class="breadcrumb m-b-less bg-less">
                                        <li><a href="#">主页</a></li>
                                        <li><a href="#">优惠券模板管理</a></li>
                                        <li class="active">优惠券模板列表</li>
                                    </ol>
                                </div>
                            </div>
                            <div class="wrapper">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <section class="panel">
                                            <header class="panel-heading ">
                                                <span class="tools pull-right">
                                                    <a href="/coupon/add" class="btn btn-info pull-right">新增</a>
                                                   
                                                    <a class="fa fa-repeat box-refresh" href="javascript:;"></a>
                                                    <a class="t-close fa fa-times" href="javascript:;"></a>
                                                </span>
                                            </header>
                                            <div class="dataTables_wrapper form-inline dt-bootstrap no-footer" 
                                                 id="DataTables_Table_0_wrapper">
                                                <div class="tbl-head">
                                                    <form action="/coupon/page" method="get">
                                                        <input type="text" id="couponTemplateNo" name="couponTemplateNo"
                                                            value="${page.couponTemplateNo}" placeholder="输入优惠券模板编号进行搜索"> 
                                                         <select class="form-control" name="couponStatus" id="couponStatus" value="${page.couponStatus}">
                                                            <option value>请选择审核状态</option>
                                                            <option value="draft" <c:if test="${page.couponStatus=='draft'}">selected</c:if>>草稿</option>
                                                            <option value="valid" <c:if test="${page.couponStatus=='valid'}">selected</c:if>>通过</option>
                                                            <option value="reject" <c:if test="${page.couponStatus=='reject'}">selected</c:if>>驳回</option>
                                                            <option value="invalid" <c:if test="${page.couponStatus=='invalid'}">selected</c:if>>待审核</option>
                                                        </select>
                                                         <select class="form-control" name="bopsUserId" id="bopsUserId" value="${page.bopsUserId}">
                                                            <option value>申请人姓名</option>
                                                             <c:forEach items="${userList }" var="user">
                                                            <option value="${user.userId }" <c:if test="${page.bopsUserId==user.userId}">selected</c:if>>${user.userRealName}</option>
                                                            </c:forEach>                                                      
                                                          </select>
                                                        <input type="submit" class="btn btn-info" value="搜索" />
                                                    </form>
                                                </div>
                                                <table aria-describedby="DataTables_Table_0_info" 
                                                       role="grid" id="DataTables_Table_0" 
                                                       class="table convert-data-table data-table dataTable no-footer">
                                                    <thead>
                                                        <tr>
                                                            <th>券模券编号</th>
                                                            <th>优惠券名称</th>
                                                            <th>申请人</th>
                                                            <th>开始时间</th>
                                                            <th>结束时间</th>
                                                            <th>面额</th>
                                                            <th>使用限额</th>
                                                            <th>面向品类</th>
                                                            <th>类型</th>
                                                            <th>状态</th>
                                                            <th>操作</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${list}" var="coupon" varStatus="status">
                                                            <tr>
                                                                <td>${coupon.couponTemplateNo}</td>
                                                                <td>${coupon.couponName}</td>
                                                                <td>${coupon.userRealName}</td>
                                                                <td>
                                                                    <fmt:formatDate value="${coupon.startTime}" type="both" pattern='yyyy-MM-dd HH:mm:ss'/>
                                                                </td>
                                                                <td>
                                                                    <fmt:formatDate value="${coupon.endTime}" type="both" pattern='yyyy-MM-dd HH:mm:ss'/>
                                                                </td>
                                                                <td>${coupon.value}</td>
                                                                <td>${coupon.minCost}</td>
                                                                <td>
                                                                    <c:choose>
                                                                        <c:when test="${coupon.couponType=='useReturn'}">
                                                                            使用返还
                                                                        </c:when>
                                                                        <c:when test="${coupon.couponType=='newUser'}">
                                                                            新人注册
                                                                        </c:when>
                                                                        <c:when test="${coupon.couponType=='cash'}">
                                                                            现金券
                                                                        </c:when>
                                                                        <c:when test="${coupon.couponType=='fullCut'}">
                                                                            普通满减
                                                                        </c:when>
                                                                    </c:choose> 
                                                                </td>
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
                                                                    <a href="./view/${coupon.couponTemplateId}">查看</a>
                                                                    <c:choose>
                                                                        <c:when test="${coupon.couponStatus=='draft' || coupon.couponStatus=='reject'}">
                                                                            | <a href="./edit/${coupon.couponTemplateId}">编辑</a>
                                                                        </c:when>
                                                                        <c:when test="${coupon.couponStatus=='invalid'}">
                                                                            | <a href="javascript:void(0);" onclick="showAuditDialog('${coupon.couponTemplateId}', '${coupon.couponTemplateNo}')">审核</a>
                                                                        </c:when>
                                                                        <c:when test="${coupon.couponStatus=='valid'}">
                                                                            | <a href="javascript:void(0);" onclick="showSendDialog('${coupon.couponTemplateId}', '${coupon.couponTemplateNo}')">派发</a>
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
                        <!-- 33333333333333333 --> --%>
                        
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <div class="modal fade" id="auditCouponTemplateDiv" tabindex="-1" role="dialog"  aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <h4 class="modal-title" id="myModalLabel">
                            优惠券审核
                        </h4>
                    </div>
                     <div class="modal-body">
				    <!-- form表单或各种input -->
				    <form class="form-horizontal">
				    	<fieldset>
					    	<div class="control-group">
	                              <!-- Text input-->
	                              <label class="control-label col-lg-4" for="">优惠券编码</label>                              
	                              <div class="controls col-lg-5">	                                
	                              	 	<input id="auditCouponTemplateId"  class="form-control" value="" type="hidden">
                        				<input type="text" id="auditCouponTemplateNo"  class="form-control" value="" readonly="readonly" >
	    							
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
                   <!--  <div style="margin : 10px 0px 10px 0px;">
                        优惠券编码:
                        <input id="auditCouponTemplateId" value="" type="hidden">
                        <input type="text" id="auditCouponTemplateNo" value="" readonly="readonly" >
                    </div>
                    <div >
                        操作备注:
                        <textarea class="form-control" id="description" rows="3"></textarea>
                    </div> -->
                    <div class="modal-footer">
                        <button type="button" onclick="audit('valid');" class="button button-capitalize button-rounded button-primary button-small">
                            审核通过
                        </button>
                        <button type="button" onclick="audit('reject');" class="button button-capitalize button-rounded button-primary button-small">
                            审核驳回
                        </button>
                    </div>
                </div><!-- /.modal-content -->
            </div>
        </div>
        <div class="modal fade" id="sendCouponTemplateDiv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <h4 class="modal-title" id="myModalLabel">
                            优惠券派发
                        </h4>
                    </div>
                    
                    <div class="modal-body">
				    <!-- form表单或各种input -->
				    <form class="form-horizontal">
				    	<fieldset>
					    	<div class="control-group">
	                              <!-- Text input-->
	                              <label class="control-label col-lg-4" for="">优惠券编码</label>                              
	                              <div class="controls col-lg-5">	                                
	                              	 <input id="sendCouponTemplateId" value="" class="form-control" type="hidden">
                        			<input type="text" id="sendCouponTemplateNo" value="" class="form-control" readonly="readonly" disabled="disabled">
	    							
	                              </div>
	                              <div style="clear: both;"></div>
	                        </div>
	                        <div class="control-group">
	                              <!-- Text input-->
	                              <label class="control-label col-lg-4" for="">用户手机号</label>                              
	                              <div class="controls col-lg-5">	                                
	                              	<input id="mobile" value="" class="form-control">
	                              </div>
	                              <div style="clear: both;"></div>
	                        </div>
	                       </fieldset>
	                  </form>
                   <!--  <div style="margin : 10px 0px 10px 0px;">
                        优惠券编码:
                        <input id="sendCouponTemplateId" value="" type="hidden">
                        <input type="text" id="sendCouponTemplateNo" value="" readonly="readonly" disabled="disabled">
                        用户手机号:
                        <input id="mobile" value="">
                    </div> -->
                    <div class="modal-footer">
                        <button type="button" onclick="send();" class="button button-capitalize button-rounded button-primary button-small">
                            派发
                        </button>
                        <button type="button" data-dismiss="modal" class="button button-capitalize button-rounded button-primary button-small">
                            取消
                        </button>
                    </div>
                </div><!-- /.modal-content -->
            </div>
        </div>
        </div>
        <!-- 在 body的最底部引入js文件且一定保持 jquery 在 bootstrap 之前引入 -->
        <script src="/resources/js/jquery/jquery-2.1.4.min.js"></script>
        <script src="/resources/js/bootstrap/bootstrap.min.js"></script>
        <!-- 侧面导航栏组件 js -->
        <script src="/resources/js/sidebar.js"></script>
        <script src="/resources/js/jquery.pagination.js"></script>
        <script type="text/javascript">
            var couponStatus = $("#couponStatus").val();
            var bopsUserId = $("#bopsUserId").val();
            var couponTemplateNo = $("#couponTemplateNo").val();
            $('#light-pagination').pagination({
                pages: "${page.pageCount}",
                cssStyle: 'light-theme',
                currentPage: "${page.page}",
                onPageClick: function (pageNumber, event) {
                    window.location.href = 'page?page=' + pageNumber + '&couponStatus=' + couponStatus + 
                            '&bopsUserId=' + bopsUserId + '&couponTemplateNo=' + couponTemplateNo;
                }
            });

            function showAuditDialog(couponTemplateId, couponTemplateNo) {
                $("#auditCouponTemplateId").val(couponTemplateId);
                $("#auditCouponTemplateNo").val(couponTemplateNo);
                $("#auditCouponTemplateDiv").modal("show");
            }
            
            function audit(couponStatus) {
                var couponTemplateId = $("#auditCouponTemplateId").val();
                var description = $("#description").val();
                $.ajax({
                    type : "POST",
                    url : "/coupon/audit/",
                    datatype : "json",
                    data : {
                        couponStatus : couponStatus,
                        couponTemplateId : couponTemplateId,
                        description : description
                    },
                    success : function(data) {
                        if (data.code == 200) {
                            window.location.href = "/coupon/page";
                        } else {
                            alert("code: " + data.code + "\n" + data.msg);
                        }
                    }
                });
            }

            function showSendDialog(couponTemplateId, couponTemplateNo) {
                $("#sendCouponTemplateId").val(couponTemplateId);
                $("#sendCouponTemplateNo").val(couponTemplateNo);
                $("#sendCouponTemplateDiv").modal("show");
            }
            
            function send() {
                var couponTemplateId = $("#sendCouponTemplateId").val();
                var mobile = $("#mobile").val();
                $.ajax({
                    type : "POST",
                    url : "/coupon/send",
                    datatype : "json",
                    data : {
                        couponTemplateId : couponTemplateId,
                        mobile : mobile
                    },
                    success : function(data) {
                        if (data.code == 200) {
                            window.location.href = "/coupon/page";
                        } else {
                            alert("code: " + data.code + "\n" + data.msg);
                        }
                    }
                });
            }
            $(window).bind("keydown",function(e){
	            var key = e.which;
	            if (key == 13) {
	                $(".search-form .button").click();
	            }
	        })
        </script>
    </body>
</html>