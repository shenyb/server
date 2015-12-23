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
    <!--     <link rel="stylesheet" href="/resources/css/dat  apage.css" > -->
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
				                    <li><a href="#">场景分类管理</a></li>
				                    <li class="active">场景分类列表</li>
				                </ol>
				            </div>
				        </div>
				
				        <div class="info">
				            <div class="info-top">
				                <span>场景分类列表</span>
				                <div class="back">
				                    <a href="" class="ops-pub-btn button button-rounded button-tiny">刷新</a>
				                    <a class="bops-pub-btn button button-rounded button-tiny" id="addCategory" href="javascript:;">新增专题分类</a>				                   
				                </div>
				            </div>
				           <div class="search-form">
				           </div>
				            <!--biaoge-->
				            <table class="table table-bordered table-striped table-condensed table-responsive">
				                <thead>
				                <tr>
				                	<th>分类ID</th>
				                    <th>专题分类名</th>
				                    <th>专题分类位置</th>
				                    <th>场景分类权值</th>
				                    <th>状态</th>
				                    <th>创建时间</th>				                  			                  
				                    <th>操作</th>				                    
				                </tr>
				                </thead>
			                <tbody>
							      <c:forEach items="${list}" var="category" varStatus="status">
			                        <tr>
			                            <td>${category.topicCategoryId}</td>
			                            <td>${category.topicCategoryName}</td>
			                            <td>
			                              <c:if test="${category.topicCategoryPosition=='SHIJIAN'}">世间
			                             </c:if>
			                              <c:if test="${category.topicCategoryPosition=='XINHUAN'}">新欢
			                              </c:if>
			                            </td>
			                            <td>${category.categoryScore}</td>
			                            <td>
			                              <c:choose>
			                              <c:when test="${category.auditStatus=='WAIT_AUDIT'}">待审核
			                              </c:when>
			                              <c:when test="${category.auditStatus=='SUCCESS'}"> 成功
			                              </c:when>
			                              <c:when test="${category.auditStatus=='FAIL'}"> 失败
			                              </c:when>
			                             </c:choose>
			                            </td>
			                            <td>
			                              <fmt:formatDate value="${category.createTime}" type="both" />
			                            </td>
			                            <td>
			                              <a  href="javascript:;" onclick="editCategory('${category.topicCategoryId}')">修改</a>
			                            <c:choose>
			                              <c:when test="${category.auditStatus=='WAIT_AUDIT'}">
			                                | <a  href="javascript:;" onclick="ballModel('${category.topicCategoryId}')">审核</a>
			                              </c:when>
			                             </c:choose> 
			                                | <a id="${category.topicCategoryId}" href="javascript:;" onclick="deleteCategory('${category.topicCategoryId}')">删除</a>
			                             </td>
			                        </tr>
			                        </c:forEach>
				                </tbody>
				            </table>
				             <div class="page">					               
								<div class="pagenum">
									<div aria-live="polite" role="status" id="DataTables_Table_0_info"class="dataTables_info">
										共有 ${page.total}条, 每页显示  ${page.pageSize}条
									</div>
								</div>								
								<div class="tbl-pagin pull-right">
									<div id="light-pagination"></div>
								</div>
				            </div>
				        </div>
				    </div> 
                       <%--  <!--  222222222-->
                        <!--2222222222  -->
                            <div class="data-page-inner">
                                <div class="page-head">
                                    <!-- 页面标题 -->
                                    <h3 class="m-b-less">
                                        场景分类管理
                                    </h3>
                                    <!--面包屑导航-->
                                    <div class="state-information">
                                        <ol class="breadcrumb m-b-less bg-less">
                                            <li><a href="#">主页</a></li>
                                            <li><a href="#">场景分类管理</a></li>
                                            <li class="active">场景分类列表</li>
                                        </ol>
                                    </div>
                                </div>
                                <div class="wrapper">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <section class="panel">
                                                <header class="panel-heading ">
                                                    <!-- 列表名称 -->
                                                    场景分类列表
                                                    <span class="tools pull-right">
                                                       <a class="ops-pub-btn" href="">
														<i class="fa fa-refresh" ></i>
														<span >刷新</span>
														</a>
														<a class="bops-pub-btn" id="addCategory" href="javascript:;">
														<i class="fa fa-plus"></i>
														<span>新增专题分类</span>
														</a>
                                                        <!-- <a class="bops-pub-btn ng-isolate-scope" href="javascript:;" id="addCategory"><i class="fa fa-plus"></i><span>新增专题分类</span></a> -->
                                                    </span>
                                                </header>
                                                <div class="dataTables_wrapper form-inline dt-bootstrap no-footer" 
                                                     id="DataTables_Table_0_wrapper">
                                                    <table aria-describedby="DataTables_Table_0_info" 
                                                           role="grid" id="DataTables_Table_0" 
                                                           class="table convert-data-table data-table dataTable no-footer">
                                                        <thead>
                                                            <tr role="row">
                                                                <th aria-label="OrderDate : activate to sort column descending" 
                                                                    aria-sort="ascending" style="width: 80px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting_asc">
                                                                    分类ID
                                                                </th>
                                                                <th aria-label="Region : activate to sort column ascending" 
                                                                    style="width: 100px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    专题分类名
                                                                </th>
                                                                <th aria-label="Rep : activate to sort column ascending" 
                                                                    style="width: 100px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    专题分类位置
                                                                </th>
                                                                                                          <th aria-label="Rep : activate to sort column ascending" 
                                                                    style="width: 100px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    专题分类权值
                                                                </th>
                                                                <th aria-label="Item : activate to sort column ascending" 
                                                                    style="width: 80px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    状态
                                                                </th>
                                                                <th aria-label="Units : activate to sort column ascending" 
                                                                    style="width: 180px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    创建时间
                                                                </th>
                                                               <th aria-label="Units : activate to sort column ascending" 
                                                                    style="width: 130px;" 
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
                                                            <c:forEach items="${list}" var="category" varStatus="status">
                        <tr>
                            <td>${category.topicCategoryId}</td>
                            <td>${category.topicCategoryName}</td>
                            <td>
                              <c:if test="${category.topicCategoryPosition=='SHIJIAN'}">
                                                                                    世间
                             </c:if>
                              <c:if test="${category.topicCategoryPosition=='XINHUAN'}">
                                                                                    新欢
                              </c:if>
                            </td>
                            <td>${category.categoryScore}</td>
                            <td>
                              <c:choose>
                              <c:when test="${category.auditStatus=='WAIT_AUDIT'}">
                                                                                        待审核
                              </c:when>
                              <c:when test="${category.auditStatus=='SUCCESS'}">
                                                                                          成功
                              </c:when>
                              <c:when test="${category.auditStatus=='FAIL'}">
                                                                                           失败
                              </c:when>
                             </c:choose>
                            </td>
                            <td>
                              <fmt:formatDate value="${category.createTime}" type="both" />
                            </td>
                            <td>
                              <a  href="javascript:;" onclick="editCategory('${category.topicCategoryId}')">修改</a>
                            <c:choose>
                              <c:when test="${category.auditStatus=='WAIT_AUDIT'}">
                                | <a  href="javascript:;" onclick="ballModel('${category.topicCategoryId}')">审核</a>
                              </c:when>
                             </c:choose> 
                                | <a id="${category.topicCategoryId}" href="javascript:;" onclick="deleteCategory('${category.topicCategoryId}')">删除</a>
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
                            </div>
                        <!-- 22222222 -->
                       <!--  22222222--> --%>
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
					<h4 class="modal-title" id="myModalLabel">审核专题分类</h4>
				</div>
				<div class="modal-body">
				    <!-- form表单或各种input -->				    
				    <form class="form-horizontal" >
				     <fieldset>
				      <input type="hidden" name="topicCategoryId" id="topicCategoryId" >
				      	<div class="control-group">                              
                              <label class="control-label col-lg-4" for="topicCategoryName">场景名称</label>                             
                              <div class="controls col-lg-5">
                                  <input type="text" name="topicCategoryName" id="topicCategoryName" readonly="readonly" class="form-control">
                              </div>
                              <div style="clear: both;"></div>
                      </div>
                      <div class="control-group small-group">                              
                              <label class="control-label col-lg-4" for="categoryScore">场景权值</label>                             
                              <div class="controls col-lg-5">
                                  <input type="text" name="categoryScore" id="categoryScore" readonly="readonly" class="form-control">
                              </div>
                              <div style="clear: both;"></div>
                      </div>
                      
				    	<!-- <div class="form-group">
	    							<label for="" class="col-lg-2">场景名称</label>
	    							<div class="col-lg-9">
	    								<input type="text" name="topicCategoryName" id="topicCategoryName" readonly="readonly">
	    							</div>
	    			    </div> -->
	    			    <!-- <div class="form-group">
	    							<label for="" class="col-lg-2">场景权值</label>
	    							<div class="col-lg-9">
	    								 <input type="text" name="categoryScore" id="categoryScore" readonly="readonly">
	    							</div>
	    			    </div> -->
				</fieldset>
				<div class="modal-footer">
					<button type="button" class="button button-capitalize button-rounded button-primary button-small" onclick="passCategory()">通过</button>
					<button type="button" class="button button-capitalize button-rounded button-primary button-small" data-dismiss="modal">
					拒绝
					</button>
				</div>
				</form>
				</div>
				
			</div>
		</div>
	</div> 
	
	
	<!-- 新增DIV -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">新增专题分类</h4>
				</div>
				<div class="modal-body">
				    <!-- form表单或各种input -->
				    <form class="form-horizontal" action="/topic/category" method="post">
				    		<fieldset>
				    		<div class="control-group small-group">                              
		                              <label class="control-label col-lg-4" for="topicCategoryPosition">场景位置</label>                             
		                              <div class="controls col-lg-5">
		                                  <select class="form-control" name="topicCategoryPosition" id="topicCategoryPosition">
	    									<option value="SHIJIAN">世间</option>
	    									<option value="XINHUAN">新欢</option>
	    								 </select>
		                              </div>
		                              <div style="clear: both;"></div>
		                      </div>
		                      <div class="control-group">                              
		                              <label class="control-label col-lg-4" for="addTopicCategoryName">场景名称</label>                             
		                              <div class="controls col-lg-5">
		                                  <input type="text" name="topicCategoryName" id="addTopicCategoryName" value="" class="form-control">
		                              </div>
		                              <div style="clear: both;"></div>
		                      </div>
		                      <div class="control-group small-group">                              
		                              <label class="control-label col-lg-4" for="addCategoryScore">场景权值</label>                             
		                              <div class="controls col-lg-5">
		                                  <input type="text" name="categoryScore" id="addCategoryScore" class="form-control">
	    								<p class="warn">(请输入整数，分数越高，序位越高)</p>
		                              </div>
		                              <div style="clear: both;"></div>
		                      </div>
	    						<!-- <div class="form-group">
	    							<label for="" class="col-lg-2">场景位置</label>
	    							<div class="col-lg-9">
	    								<select class="form-control" style="width:25%;" name="topicCategoryPosition" id="topicCategoryPosition">
	    									<option value="SHIJIAN">世间</option>
	    									<option value="XINHUAN">新欢</option>
	    								 </select>
	    							</div>
	    						</div> -->
	    						<!-- <div class="form-group">
	    							<label for="" class="col-lg-2">场景名称</label>
	    							<div class="col-lg-9">
	    								<input type="text" name="topicCategoryName" id="addTopicCategoryName" value="">
	    							</div>
	    					   </div>	 -->
	    					   <!-- <div class="form-group">
	    							<label for="" class="col-lg-2">场景权值</label>
	    							<div class="col-lg-9">
	    								<input type="text" name="categoryScore" id="addCategoryScore">
	    								<i>(请输入整数，分数越高，序位越高)</i>
	    							</div>
	    					   </div> -->
	    									
				</fieldset>
				<div class="modal-footer">
				     <button type="submit" class="button button-capitalize button-rounded button-primary button-small" onclick ="return valid('add');">确定</button>
					 <button type="button" class="button button-capitalize button-rounded button-primary button-small" data-dismiss="modal">取消
					</button>					
				</div>
				</form>
			</div>
		</div>
	</div>  
	</div>
	
	<!-- 编辑DIV -->
	<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">修改专题分类</h4>
				</div>
				<div class="modal-body">
				    <!-- form表单或各种input -->
				    <form class="form-horizontal" action="/topic/category/edit" method="post">
				
		            <fieldset>
	    						<div class="control-group small-group">
	    							<label for="" class="control-label col-lg-4">场景位置</label>
	    							<div class="col-lg-5">
	    								<select class="form-control" name="topicCategoryPosition" id="editTopicCategoryPosition" disabled="disabled">
	    									<option value="SHIJIAN">世间</option>
	    									<option value="XINHUAN">新欢</option>
	    								 </select>
	    							</div>
	    							 <div style="clear: both;"></div>
	    						</div>
	    						 <div class="control-group">
	    							<label for="" class="control-label col-lg-4">场景名称</label>
	    							<div class="col-lg-5">
	    								<input type="text" name="topicCategoryName" id="editTopicCategoryName" value="" class="form-control">
	    								<input type="hidden" name="topicCategoryId" id="editTopicCategoryId">
	    							</div>
	    							 <div style="clear: both;"></div>
	    						</div>	
	    						
	    						<div class="control-group small-group">
	    							<label for="" class="control-label col-lg-4">场景权值</label>
	    							<div class="col-lg-5">
	    								<input type="text" name="categoryScore" id="editCategoryScore" class="form-control">
	    							</div>
	    							 <div style="clear: both;"></div>
	    						</div>		
				</fieldset>
				<div class="modal-footer">
				     <button type="submit" class="button button-capitalize button-rounded button-primary button-small" onclick ="return valid('edit');">确定</button>
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
           //分页
           $('#light-pagination').pagination({
        		pages: "${page.pageCount}",
        		cssStyle: 'light-theme',
        		currentPage: "${page.page}",
				onPageClick: function(pageNumber, event) {
					window.location.href='page?page='+pageNumber;
				}
        	});
           //删除方法
           function deleteCategory(topicCategoryId){
        	      $.messager.confirm("删除专题分类", "您确定要删除此分类!", function() { 
        	    	  $.ajax({
     	    		     type: 'POST',
     	    		     url: '/topic/category/'+topicCategoryId,
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
           function ballModel(topicCategoryId){
        	   $.ajax({
	    		     type: 'GET',
	    		     url: '/topic/category/'+topicCategoryId,
	    		     dataType: 'json',
	    		    success : function(data) {
	    		    	var jsonObj=$.parseJSON(JSON.stringify(data));
	    		    	$('#topicCategoryName').val(jsonObj.data.category.topicCategoryName);
	    		    	$('#categoryScore').val(jsonObj.data.category.categoryScore);
	    		    	$('#topicCategoryId').val(jsonObj.data.category.topicCategoryId);
	    		    	$('#topicCategoryName').attr('readonly',true);
	    		    	 $('#ballModal').modal({
	    	        		   keyboard: true  
	    	              });
	   			     },
	   			    error : function() {
	   				    alert("系统异常");
	   			}
	    		   });
        	  
           }
           //弹出新增框
           $("#addCategory").click(function(){
        	   $('#addTopicCategoryName').val('');
        	   $('#addCategoryScore').val('');
        	   $('#addModal').modal({
        		   keyboard: true  
              });
        	   
           })
           //编辑
           
          function editCategory(topicCategoryId){
        	  // alert(111);
        	    $.ajax({
	    		     type: 'GET',
	    		     url: '/topic/category/'+topicCategoryId,
	    		     dataType: 'json',
	    		    success : function(data) {
	    		    	var jsonObj=$.parseJSON(JSON.stringify(data));
	    		    	$('#editTopicCategoryName').val(jsonObj.data.category.topicCategoryName);
	    		    	$('#editCategoryScore').val(jsonObj.data.category.categoryScore);
	    		    	$('#editTopicCategoryPosition').val(jsonObj.data.category.topicCategoryPosition);
	    		    	$('#editTopicCategoryId').val(jsonObj.data.category.topicCategoryId);
	    		    	$('#editModal').modal({
	    	        		   keyboard: true  
	    	              });
	   			     },
	   			    error : function() {
	   				    alert("系统异常");
	   			}
	    		   }); 
        	   
        	   
           }
          
           
           //审核
           function passCategory(){
        	   var topicCategoryId=$('#topicCategoryId').val();
        	   $.ajax({
	    		     type: 'POST',
	    		     url: '/topic/category/audit/'+topicCategoryId+"?auditStatus=SUCCESS",
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
         //正整数
           function isPInt(str) {
               var g = /^[1-9]*[1-9][0-9]*$/;
               return g.test(str);
           }
         //表单校验
         function valid(action){
        	 if(action=='add'){
        		if($('#addTopicCategoryName').val()==null||$('#addTopicCategoryName').val()==''){
        			alert('场景名称不能为空');
        			return false;
        		} else if($('#addCategoryScore').val()==null||$('#addCategoryScore').val()==''){
        			alert('场景权值不能为空');
        			return false;	 			
        		}else{
        			if(!isPInt($('#addCategoryScore').val())){
        				alert('场景权值请填正整数');
        				return false;
        			}
        		}
        		return true; 
        	 }else{
        		 if($('#editTopicCategoryName').val()==null||$('#editTopicCategoryName').val()==''){
         			alert('场景名称不能为空');
         			return false;
         		} else if($('#editCategoryScore').val()==null||$('#editCategoryScore').val()==''){
         			alert('场景权值不能为空');
        			return false;
         			
         		}else{
         			if(!isPInt($('#editCategoryScore').val())){
         				alert('场景权值请填正整数');
         				return false;
         			}
         		}
        		 return true;
        		 
        	 }
        	 
         }
         </script>
    </body>
</html>