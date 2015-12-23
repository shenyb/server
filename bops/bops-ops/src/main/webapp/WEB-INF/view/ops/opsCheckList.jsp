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
        <link rel="stylesheet" href="/resources/css/bootstrap/button.css" >
        <link rel="stylesheet" href="/resources/css/font-awesome.min.css" >
        <link rel="stylesheet" href="/resources/css/layout.css" >
        <link rel="stylesheet" href="/resources/css/ops.css" >
        <link rel="stylesheet" href="/resources/css/sidebar.css" >
        <link rel="stylesheet" href="/resources/css/content-header.css" >
       <!--  <link rel="stylesheet" href="/resources/css/dat  apage.css" > -->
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
                    <div class="layout-right-bottom" >
                        <!-- 
                            这部分写各自页面对应的内容
                            注意锁紧格式
                            范围 start -
                         -->
                        <div class="data-page-wrap">
                        <div class="con">
				        <div class="page-head">				           
				            <div class="state-information">
				                <ol class="breadcrumb m-b-less bg-less">
				                    <li><a href="#">主页</a></li>
				                    <li><a href="#">运营管理</a></li>
				                    <li class="active">专题投放管理</li>
				                </ol>
				            </div>
				        </div>
				
				        <div class="info">
				            <div class="info-top">
				                <span>运营位审核列表</span>
				                <div class="back">
				                    <a href="" class="button button-rounded button-tiny">刷新</a>				                   
				                </div>
				            </div>
				            <div class="search-form">
				                <form class="form-inline" id="searchGoodsContent" method="get" action="/opassign">
				                    <div class="row">
					                    <div class="form-group col-xs-3">
					                        <label for="searchTypeId">专题ID</label>
					                        <input type="text" class="form-control" id="searchTypeId" name="typeId" value="${page.typeId }" tabindex="1">
					                    </div>
					                    <div class="form-group col-xs-3">
					                        <label for="searchOpsName">运营位名称</label>					                       
					                        <select class="form-control" name="opsNumber" id="searchOpsName" tabindex="2">
			    							<option  value="">全部</option>
			    								<c:forEach items="${opsList }" var="ops">
							                       <option value="${ops.opsId }" <c:if test="${page.opsNumber==ops.opsId}">selected</c:if>>${ops.opsName}</option>
							                    </c:forEach>
	    								  </select>
					                    </div>       
					                    <div class="form-group col-xs-3">
					                        <label for="auditStatus">审核状态</label>					                        
					                        <select  id="auditStatus" name="auditStatus" class="form-control" tabindex="3">
			    									<option  value="">全部</option>
			    								   <option value="WAIT_AUDIT" <c:if test="${page.auditStatus=='WAIT_AUDIT'}">selected</c:if>>待审核</option>
													<option value="SUCCESS"    <c:if test="${page.auditStatus=='SUCCESS'}">selected</c:if>>通过</option>
													<option value="FAIL"       <c:if test="${page.auditStatus=='FAIL'}">selected</c:if>>未通过</option>
	    								   </select>
					                    </div>					        
				                  		 <div class="form-group col-xs-3">	
				                  		 <button type="submit" id="searChops" class="button button-primary button-raised button-small"><i class="fa fa-search"></i>&nbsp;&nbsp;搜索</button>
				                  		</div>
				                    </div>
				                </form>
				            </div>
				            <!--biaoge-->
				            <table class="table table-bordered table-striped table-condensed table-responsive">
				                <thead>
				                <tr>
				                	<th>运营位名称</th>
				                    <th>类型</th>
				                    <th>专题ID</th>
				                    <th>审核状态</th>
				                    <th>运营位排序</th>
				                    <th>生效时间</th>
				                    <th>失效时间</th>				                  
				                    <th>操作</th>				                    
				                </tr>
				                </thead>
			                <tbody>
				            	<c:forEach items="${bopsOpsList}" var="bopsOps" varStatus="status">
			                        <tr>
			                            <td>${bopsOps.opsNumber}</td>
			                             <td>
			                             <c:choose>
			                              <c:when test="${bopsOps.opsType=='TOPIC'}">专题</c:when>
			                              <c:when test="${bopsOps.opsType=='KOL'}">行家</c:when>
			                              <c:when test="${bopsOps.opsType=='GOODS'}">商家</c:when>
			                              <c:when test="${bopsOps.opsType=='CHEAP'}">团便宜</c:when>
			                              <c:when test="${bopsOps.opsType=='DISCOUNT'}">组合促销</c:when>
			                              <c:when test="${bopsOps.opsType=='COUPON'}">优惠券</c:when>
			                             </c:choose>
			                            </td>
			                            <td>${bopsOps.typeId}</td>
			                            <td>
			                              <c:choose>
			                              <c:when test="${bopsOps.auditStatus=='WAIT_AUDIT'}">待审核</c:when>
			                              <c:when test="${bopsOps.auditStatus=='SUCCESS'}">成功</c:when>
			                              <c:when test="${bopsOps.auditStatus=='FAIL'}">失败</c:when>
			                             </c:choose>
			                            </td>
			                            <td>${bopsOps.topicScore}</td>
			                            <td><fmt:formatDate value="${bopsOps.effDate}" type="both" /></td>
			                            <td><fmt:formatDate value="${bopsOps.expDate}" type="both" /></td>
			                            <td>
			                              <a  href="javascript:;" onclick="viewOps('${bopsOps.opsId}','${bopsOps.opsNumber}','${bopsOps.opsType}','${bopsOps.auditStatus}')">查看</a>
			                            <c:choose>
			                              <c:when test="${bopsOps.auditStatus=='WAIT_AUDIT'}">
			                                | <a  href="javascript:;" onclick="ballModel('${bopsOps.opsId}','${bopsOps.opsNumber}','${bopsOps.opsType}','${bopsOps.auditStatus}')">审核</a>
			                              </c:when>
			                             </c:choose> 
			                                |  <a id="${bopsOps.opsId}" href="javascript:;" onclick="deleteCategory('${bopsOps.opsId}')">删除</a>
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
                        
                       <%--  <!-- 222222222222 -->
                            <div class="data-page-inner">
                                <div class="page-head">
                                    <!-- 页面标题 -->
                                    <h3 class="m-b-less">
                                        运营位审核管理
                                    </h3>
                                    <!--面包屑导航-->
                                    <div class="state-information">
                                        <ol class="breadcrumb m-b-less bg-less">
                                            <li><a href="#">主页</a></li>
                                            <li><a href="#">运营位审核</a></li>
                                            <li class="active">审核列表</li>
                                        </ol>
                                    </div>
                                </div> 
                                <div class="wrapper">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <section class="panel">
                                                <header class="panel-heading ">
                                                    <!-- 列表名称 -->
													
                                                    运营位审核列表
                                                    <span class="tools pull-right">
                                                        <a class="ops-pub-btn" href="">
														<i class="fa fa-refresh" ></i>
														<span>刷新</span>
														</a>
                                                    </span>
                                                </header>
                                              <!-- 搜索DIV -->                                                  
                                             <div class="tbl-head">
                                                 <form id="searchGoodsContent" method="get" action="/opassign">
									              <div class="bops-pub-param-seach" >
									              <input type="text" id="searchTopicId" style="vertical-align: 1px; 
															   		  width: 200px;
															   		  padding-left: 18px;
															   		  display:inline-block;
															   		  height:32px" id="searchTypeId" name="typeId" value="${page.typeId }" placeholder="专题ID" class="ng-pristine ng-untouched ng-valid">
					    								  <select  style="vertical-align: 1px;width: 200px;padding-left: 18px;display:inline-block;height:32px" name="opsNumber" id="searchOpsName">
							    							<option  value="">运营位名称</option>
							    								<c:forEach items="${opsList }" var="ops">
	    									                       <option value="${ops.opsId }" <c:if test="${page.opsNumber==ops.opsId}">selected</c:if>>${ops.opsName}</option>
	    									                    </c:forEach>
					    								  </select>
												           <select  id="auditStatus" name="auditStatus"  style="vertical-align: 1px;width: 200px;padding-left: 18px;display:inline-block;height:32px">
							    									<option  value="">审核状态</option>
							    								   <option value="WAIT_AUDIT" <c:if test="${page.auditStatus=='WAIT_AUDIT'}">selected</c:if>>待审核</option>
																	<option value="SUCCESS"    <c:if test="${page.auditStatus=='SUCCESS'}">selected</c:if>>通过</option>
																	<option value="FAIL"       <c:if test="${page.auditStatus=='FAIL'}">selected</c:if>>未通过</option>
					    								   </select>
											              <button type="submit" class="btn btn-info" style="vertical-align:middle;margin-top:-2px;"  id="searChops">搜索</button>
													</div> 
													</form>     
                                                    </div>
                                                <div class="dataTables_wrapper form-inline dt-bootstrap no-footer" 
                                                     id="DataTables_Table_0_wrapper">
                                                    <table aria-describedby="DataTables_Table_0_info" 
                                                           role="grid" id="DataTables_Table_0" 
                                                           class="table convert-data-table data-table dataTable no-footer">
                                                        <thead>
                                                            <tr role="row">
                                                                <th aria-label="OrderDate : activate to sort column descending" 
                                                                    aria-sort="ascending" style="width: 150px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting_asc">
                                                                    运营位名称
                                                                </th>
                                                                <th aria-label="Region : activate to sort column ascending" 
                                                                    style="width: 100px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    类型
                                                                </th>
                                                                <th aria-label="Rep : activate to sort column ascending" 
                                                                    style="width: 80px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    ID
                                                                </th>
                                                                <th aria-label="Item : activate to sort column ascending" 
                                                                    style="width: 80px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    审核状态
                                                                </th>
                                                                
                                                                                                          <th aria-label="Item : activate to sort column ascending" 
                                                                    style="width: 80px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    权值
                                                                </th>
                                                                                                                              <th aria-label="Item : activate to sort column ascending" 
                                                                    style="width: 150px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    生效时间
                                                                </th>
                                                                                                                              <th aria-label="Item : activate to sort column ascending" 
                                                                    style="width: 133px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    失效时间
                                                                </th>
                                                                <th aria-label="Units : activate to sort column ascending" 
                                                                    style="width: 105px;" 
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
                                                            <c:forEach items="${bopsOpsList}" var="bopsOps" varStatus="status">
                        <tr>
                            <td>${bopsOps.opsNumber}</td>
                             <td>
                             <c:choose>
                              <c:when test="${bopsOps.opsType=='TOPIC'}">
                                                                                             专题
                              </c:when>
                              <c:when test="${bopsOps.opsType=='KOL'}">
                                                                                             行家
                              </c:when>
                              <c:when test="${bopsOps.opsType=='GOODS'}">
                                                                                             商家
                              </c:when>
                              <c:when test="${bopsOps.opsType=='CHEAP'}">
                                                                                             团便宜
                              </c:when>
                              <c:when test="${bopsOps.opsType=='DISCOUNT'}">
                                                                                             组合促销
                              </c:when>
                              <c:when test="${bopsOps.opsType=='COUPON'}">
                                                                                             优惠券
                              </c:when>
                             </c:choose>
                            </td>
                            <td>${bopsOps.typeId}</td>
                            <td>
                              <c:choose>
                              <c:when test="${bopsOps.auditStatus=='WAIT_AUDIT'}">
                                                                                        待审核
                              </c:when>
                              <c:when test="${bopsOps.auditStatus=='SUCCESS'}">
                                                                                          成功
                              </c:when>
                              <c:when test="${bopsOps.auditStatus=='FAIL'}">
                                                                                           失败
                              </c:when>
                             </c:choose>
                            </td>
                            <td>${bopsOps.topicScore}</td>
                            <td><fmt:formatDate value="${bopsOps.effDate}" type="both" /></td>
                            <td><fmt:formatDate value="${bopsOps.expDate}" type="both" /></td>
                            <td>
                              <a  href="javascript:;" onclick="viewOps('${bopsOps.opsId}','${bopsOps.opsNumber}','${bopsOps.opsType}','${bopsOps.auditStatus}')">查看</a>
                            <c:choose>
                              <c:when test="${bopsOps.auditStatus=='WAIT_AUDIT'}">
                                | <a  href="javascript:;" onclick="ballModel('${bopsOps.opsId}','${bopsOps.opsNumber}','${bopsOps.opsType}','${bopsOps.auditStatus}')">审核</a>
                              </c:when>
                             </c:choose> 
                                |  <a id="${bopsOps.opsId}" href="javascript:;" onclick="deleteCategory('${bopsOps.opsId}')">删除</a>
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
                                                                 class="dataTables_info">共有 ${page.total}条, 每页显示  ${page.pageSize} </div>
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
                       <!-- 22222222222 --> --%>
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
        
      <!-- 审核DIV -->
	<div class="modal fade" id="ballModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">运营位审核</h4>
				</div>
				<div class="modal-body">
				    <form class="form-horizontal" action="/topic/category" method="post">
				     <fieldset>
				    <div class="control-group large-group">
                                <!-- Text input-->
                                <label class="control-label col-lg-4" for="checkOpsName">运营位名称</label>
                                 
                                <div class="controls col-lg-8">
                                    <input type="text" name="opsName" id="checkOpsName" value="" readonly="readonly" class="form-control" size="25">
	    							<input type="hidden" name="opsId" id="checkOpsId" value="" readonly="readonly" class="form-control">
                                </div>
                                <div style="clear: both;"></div>
                      </div>
                      <div class="control-group">
                                <!-- Text input-->
                                <label class="control-label col-lg-4" for="checkopsType">运营位类型</label>
                                 
                                <div class="controls col-lg-5">
                                    <input type="text" name="opsType" id="checkopsType" value="" readonly="readonly" class="form-control">
                                </div>
                                <div style="clear: both;"></div>
                      </div>
                      <div class="control-group">
                                <!-- Text input-->
                                <label class="control-label col-lg-4" for="checkTypeId">专题ID</label>
                                 
                                <div class="controls col-lg-5">
                                   <input type="text" name="typeId" id="checkTypeId" value="" readonly="readonly" class="form-control">
                                </div>
                                <div style="clear: both;"></div>
                      </div>
                      <div class="control-group">
                                <!-- Text input-->
                                <label class="control-label col-lg-4" for="checkAuditStatus">审核状态</label>
                                
                                <div class="controls col-lg-5">
                                   <input type="text" name="auditStatus" id="checkAuditStatus" value="" readonly="readonly" class="form-control">
                                </div>
                                <div style="clear: both;"></div>
                      </div>
	    				<!-- <div class="form-group">
	    							<label for="" class="col-lg-3">运营名称</label>
	    							<div class="col-lg-9">
	    								<input type="text" name="opsName" id="checkOpsName" value="" readonly="readonly">
	    								<input type="hidden" name="opsId" id="checkOpsId" value="" readonly="readonly">
	    							</div>
	    				</div>
	    				
	    				<div class="form-group">
	    							<label for="" class="col-lg-3">运营位类型</label>
	    							<div class="col-lg-9">
	    								<input type="text" name="opsType" id="checkopsType" value="" readonly="readonly">
	    							</div>
	    				</div>	
	    				<div class="form-group">
	    							<label for="" class="col-lg-3">类型ID</label>
	    							<div class="col-lg-9">
	    								<input type="text" name="typeId" id="checkTypeId" value="" readonly="readonly">
	    							</div>
	    				</div>
	    				<div class="form-group">
	    							<label for="" class="col-lg-3">审核状态</label>
	    							<div class="col-lg-9">
	    								<input type="text" name="auditStatus" id="checkAuditStatus" value="" readonly="readonly">
	    							</div>
	    				</div>	 -->	
	    		</fieldset>
				    <div class="modal-footer" id="buttonDiv" >
				     <button type="button" class="button button-capitalize button-rounded button-primary button-small" onclick="checkOps('SUCCESS')">确定</button>
					 <button type="button" class="button button-capitalize button-rounded button-primary button-small" data-dismiss="modal">取消
				   </button>				    	
				    </div>
				</form>
				</div>
			</div>
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
				onPageClick: function(pageNumber, event) {
					var typeId="${page.typeId}";
					var auditStatus="${page.auditStatus}";
					var opsNumber="${page.opsNumber}";
					window.location.href='opassign?page='+pageNumber+"&auditStatus="+auditStatus+"&opsNumber="+opsNumber;
				}
        	});
         //删除方法
           function deleteCategory(opsId){
        	      $.messager.confirm("删除运营位", "您确定要删除此运营位!", function() { 
        	    	  $.ajax({
     	    		     type: 'POST',
     	    		     url: '/opassign/'+opsId,
     	    		     dataType: 'json',
     	    		    success : function(data) {
     	    		    	window.location.reload();
     	   			     },
     	   			    error : function() {
     	   				    alert("系统异常");
     	   			}

     	    		   });
        	        });
              
        	      }
         
           //弹框
           function ballModel(opsId,opsName,opsType,auditStatus){
        	    $.ajax({
	    		     type: 'GET',
	    		     url: '/opassign/'+opsId,
	    		     dataType: 'json',
	    		    success : function(data) {
	    		    	$('#buttonDiv').show();
	    		    	$('#checkOpsId').val(data.data.bopsOps.opsId);
	    		    	$('#checkOpsName').val(opsName);
	    		    	if(opsType=='TOPIC'){
	    		    		$('#checkopsType').val('专题');
	    		    	}
	    		    	if(opsType=='KOL'){
	    		    		$('#checkopsType').val('行家');
	    		    	}
	    		    	if(opsType=='GOODS'){
	    		    		$('#checkopsType').val('商品');
	    		    	}
	    		    	
	    		    	$('#checkTypeId').val(data.data.bopsOps.typeId);
	    		    	if(auditStatus=='WAIT_AUDIT'){
	    		    		$('#checkAuditStatus').val('待审核');
	    		    	}
	    		    	if(auditStatus=='SUCCESS'){
	    		    		$('#checkAuditStatus').val('审核通过');
	    		    	}
	    		    	if(auditStatus=='FAIL'){
	    		    		$('#checkAuditStatus').val('未通过');
	    		    	}
	    		    	
	    		    	 $('#ballModal').modal({
	    	        		   keyboard: true  
	    	              });
	   			     },
	   			    error : function() {
	   				    alert("系统异常");
	   			}
	    		   }); 
           }
           
           
         //查看
           function viewOps(opsId,opsName,opsType,auditStatus){
        	    $.ajax({
	    		     type: 'GET',
	    		     url: '/opassign/'+opsId,
	    		     dataType: 'json',
	    		    success : function(data) {
	    		    	$('#buttonDiv').hide();
	    		    	$('#checkOpsId').val(data.data.bopsOps.opsId);
	    		    	$('#checkOpsName').val(opsName);
	    		    	if(opsType=='TOPIC'){
	    		    		$('#checkopsType').val('专题');
	    		    	}
	    		    	if(opsType=='KOL'){
	    		    		$('#checkopsType').val('行家');
	    		    	}
	    		    	if(opsType=='GOODS'){
	    		    		$('#checkopsType').val('商品');
	    		    	}
	    		    	
	    		    	$('#checkTypeId').val(data.data.bopsOps.typeId);
	    		    	if(auditStatus=='WAIT_AUDIT'){
	    		    		$('#checkAuditStatus').val('待审核');
	    		    	}
	    		    	if(auditStatus=='SUCCESS'){
	    		    		$('#checkAuditStatus').val('审核通过');
	    		    	}
	    		    	if(auditStatus=='FAIL'){
	    		    		$('#checkAuditStatus').val('未通过');
	    		    	}
	    		    	
	    		    	 $('#ballModal').modal({
	    	        		   keyboard: true  
	    	              });
	   			     },
	   			    error : function() {
	   				    alert("系统异常");
	   			}
	    		   }); 
           }
           
          //审核
            function checkOps(auditStatus){
        	   var opsId=$('#checkOpsId').val();
        	   $.ajax({
	    		     type: 'POST',
	    		     url: '/opassign/audit/'+opsId+"?auditStatus="+auditStatus,
	    		     dataType: 'json',
	    		    success : function(data) {
	    		    	window.location.reload();
	   			     },
	   			    error : function() {
	   				    alert("系统异常");
	   			}
	    		   });       	   
           }
          //刷新
          function refresh(){
        	  location.reload();
          }
          //回车事件
          /* $("body").keydown(function() {
              if (event.keyCode == "13") {
            	  //keyCode=13是回车键
                  $('#searChops').click();
              }
          }); */
          $(window).bind("keydown",function(e){
	            var key = e.which;
	            if (key == 13) {
	                $("#searChops").click();
	            }
	        })
         </script>
    </body>
</html>