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
        <!-- <link rel="stylesheet" href="/resources/css/data  page.css" > -->
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
				                    <li class="active">专题创建管理</li>
				                </ol>
				            </div>
				        </div>
				
				        <div class="info">
				            <div class="info-top">
				                <span>内容列表</span>
				                <div class="back">
				                    <a href="" class="ops-pub-btn button button-rounded button-tiny">刷新</a>
				                    <a id="addTopic" href="javascript:;" class="ops-pub-btn button button-rounded button-tiny">新增</a>				                   
				                </div>
				            </div>
				            <div class="search-form">
				                <form class="form-inline" id="searchGoodsContent" method="get" action="/special/page">
				                    <div class="row">
					                    <div class="form-group col-xs-3">
					                        <label for="searchTopicId">专题ID</label>
					                        <input type="text" class="form-control" id="searchTopicId" name="topicId" value="${page.topicId }" tabindex="1">
					                    </div>
					                    <div class="form-group col-xs-3">
					                        <label for="searchTopicTitle">专题名称</label>					                       
					                        <input class="form-control" id="searchTopicTitle" name="topicTitle" value="${page.topicTitle }"  tabindex="2">			    							
					                    </div> 					                   
					                   </div>
					                 <div class="row">         
					                    <div class="form-group col-xs-3">
					                        <label for="">审核状态</label>					                        
					                        <select   name="auditStatus" class="form-control" tabindex="3">
			    									<option value="">全部</option>
			    									<option value="WAIT_AUDIT" <c:if test="${page.auditStatus=='WAIT_AUDIT'}">selected</c:if>>待审核</option>
													<option value="SUCCESS"    <c:if test="${page.auditStatus=='SUCCESS'}">selected</c:if>>通过</option>
													<option value="FAIL"       <c:if test="${page.auditStatus=='FAIL'}">selected</c:if>>未通过</option>
	    								  </select>
					                    </div>					        
				                   		<div class="form-group col-xs-3">
					                        <label for="addOpsType">专题所属分类</label>					                        
					                        <select  name="topicCategoryId" id="addOpsType" class="form-control" tabindex="4">
			    									 <option value="">全部</option>
			    									<c:forEach items="${categoryList }" var="category">
 									                  <option value="${category.topicCategoryId }" <c:if test="${page.topicCategoryId==category.topicCategoryId}">selected</c:if>>${category.topicCategoryName}</option>
 									                </c:forEach>
	    								  </select>
					                    </div>
					                    <div class="form-group col-xs-3">
				                  		 <button type="submit" onclick="return toVaild()" class="button button-primary button-raised button-small"><i class="fa fa-search"></i>&nbsp;&nbsp;搜索</button>
				                  		</div>
				                    </div>
				                </form>
				            </div>
				            <!--biaoge-->
				            <table class="table table-bordered table-striped table-condensed table-responsive">
				                <thead>
				                <tr>
				                	<th>专题ID</th>
				                    <th>专题名称</th>
				                    <th>专题所属分类</th>
				                    <th>H5页面</th>
				                    <th>审核状态</th>
				                    <th>专题权值</th>
				                    <th>生效时间</th>	
				                    <th>失效时间</th>				                  
				                    <th>操作</th>				                    
				                </tr>
				                </thead>
			                <tbody>
				            	 <c:forEach items="${list}" var="topic" varStatus="status">
			                        <tr>
			                            <td>${topic.topicId}</td>
			                            <td style="text-align:left;">${topic.topicTitle}</td>
			                            <td>${topic.categoryName}</td>
			                             <td><a href="${topic.visitUrl}" style="text-decoration:none; " target="_Blank">点击预览</a> </td>
			                            <td>
			                              <c:choose>
			                              <c:when test="${topic.auditStatus=='WAIT_AUDIT'}">待审核</c:when>
			                              <c:when test="${topic.auditStatus=='SUCCESS'}">成功</c:when>
			                              <c:when test="${topic.auditStatus=='FAIL'}">失败</c:when>
			                             </c:choose>
			                            </td>
			                            <td>${topic.topicScore}</td>
			                            <td><fmt:formatDate value="${topic.effDate}" type="both" /></td>
			                            <td><fmt:formatDate value="${topic.expDate}" type="both" /></td>
			                            <td> 
			                                 <a  href="javascript:;" onclick="toEditUrl('${topic.topicId}')" >编辑</a>                                                          
			                            <c:choose>
			                              <c:when test="${topic.auditStatus=='WAIT_AUDIT'}">
			                                | <a  href="javascript:;" onclick="ballModel('${topic.topicId}','${topic.topicTitle}')">审核</a>
			                              </c:when>
			                               <c:otherwise>
			                                | <a  href="javascript:;" onclick="ballAllocation('${topic.topicId}')">分配专题</a>
			                               </c:otherwise>
			                             </c:choose> 
			                                | <a  href="javascript:;" onclick="deleteCategory('${topic.topicId}')">删除</a>
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
                        <%-- <!--22222222222  -->
                            <div class="data-page-inner">
                                <div class="page-head">
                                    <!-- 页面标题 -->
                                    <h3 class="m-b-less">
                                        专题内容管理
                                    </h3>
                                    <!--面包屑导航-->
                                    <div class="state-information">
                                        <ol class="breadcrumb m-b-less bg-less">
                                            <li><a href="#">主页</a></li>
                                            <li><a href="#">专题内容管理</a></li>
                                            <li class="active">专题列表</li>
                                        </ol>
                                    </div>
                                </div>
                                <div class="wrapper">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <section class="panel">
                                                <header class="panel-heading ">
                                                    <!-- 列表名称 -->
													
                                                    内容列表
                                                    <span class="tools pull-right">
                                                        <a class="ops-pub-btn" href="">
														<i class="fa fa-refresh" ></i>
														<span >刷新</span>
														</a>
														<a class="ops-pub-btn" id="addTopic" href="javascript:;" target="_blank">
														<i class="fa fa-plus"></i>
														<span >新增专题</span>
														</a>
                                                    </span>
                                                </header>
                                                <div class="tbl-head">
                                                 <form id="searchGoodsContent" method="get" action="/special/page">
									              <div class="bops-pub-param-seach" >
									              <input type="text" id="searchTopicId" style="vertical-align: 1px; 
															   		  width: 200px;
															   		  padding-left: 18px;
															   		  display:inline-block;
															   		  height:32px" id="searchTopicTitle" name="topicId" value="${page.topicId }" placeholder="请输入专题ID搜索" class="ng-pristine ng-untouched ng-valid">
														<input type="text" style="vertical-align: 1px;
															   		  width: 200px;
															   		  padding-left: 18px;
															   		  display:inline-block;
															   		  height:32px" id="searchTopicTitle" name="topicTitle" value="${page.topicTitle }" placeholder="请输入专题名称搜索" class="ng-pristine ng-untouched ng-valid">
												          <select   name="auditStatus"  style="vertical-align: 1px;width: 200px;padding-left: 18px;display:inline-block;height:32px">
							    									<option value="">请选择状态</option>
							    									<option value="WAIT_AUDIT" <c:if test="${page.auditStatus=='WAIT_AUDIT'}">selected</c:if>>待审核</option>
																	<option value="SUCCESS"    <c:if test="${page.auditStatus=='SUCCESS'}">selected</c:if>>通过</option>
																	<option value="FAIL"       <c:if test="${page.auditStatus=='FAIL'}">selected</c:if>>未通过</option>
					    								  </select>
					    								  
					    								  <select  style="vertical-align: 1px;width: 200px;padding-left: 18px;display:inline-block;height:32px" name="topicCategoryId" id="addOpsType">
							    									 <option value="">请选择分类</option>
							    									<c:forEach items="${categoryList }" var="category">
	    									                        <option value="${category.topicCategoryId }" <c:if test="${page.topicCategoryId==category.topicCategoryId}">selected</c:if>>${category.topicCategoryName}</option>
	    									                         </c:forEach>
					    								  </select>
														
											              <button type="submit" class="btn btn-info" style="vertical-align:middle;margin-top:-2px;" onclick="return toVaild()">搜索</button>
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
                                                                    aria-sort="ascending" style="width: 50px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting_asc">
                                                                    专题ID
                                                                </th>
                                                                <th aria-label="Region : activate to sort column ascending" 
                                                                    style="width: 135px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    专题名称
                                                                </th>
                                                                <th aria-label="Rep : activate to sort column ascending" 
                                                                    style="width: 100px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    专题所属分类
                                                                </th>
                                                                <th aria-label="Item : activate to sort column ascending" 
                                                                    style="width: 80px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    H5页面
                                                                </th>
                                                                <th aria-label="Units : activate to sort column ascending" 
                                                                    style="width: 70px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    审核状态
                                                                </th>
																<th aria-label="Units : activate to sort column ascending" 
                                                                    style="width: 80px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    专题权值
                                                                </th>
                                                                										<th aria-label="Units : activate to sort column ascending" 
                                                                    style="width: 80px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    生效时间
                                                                </th>
                                                                										<th aria-label="Units : activate to sort column ascending" 
                                                                    style="width: 80px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    失效时间
                                                                </th>
																<th aria-label="Units : activate to sort column ascending" 
                                                                    style="width: 150px;" 
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
                                                              <c:forEach items="${list}" var="topic" varStatus="status">
                        <tr>
                            <td>${topic.topicId}</td>
                            <td>${topic.topicTitle}</td>
                            <td>${topic.categoryName}</td>
                             <td><a href="${topic.visitUrl}" style="text-decoration:none; " target="_Blank">点击预览</a> </td>
                            <td>
                              <c:choose>
                              <c:when test="${topic.auditStatus=='WAIT_AUDIT'}">
                                                                                        待审核
                              </c:when>
                              <c:when test="${topic.auditStatus=='SUCCESS'}">
                                                                                          成功
                              </c:when>
                              <c:when test="${topic.auditStatus=='FAIL'}">
                                                                                           失败
                              </c:when>
                             </c:choose>
                            </td>
                            <td>${topic.topicScore}</td>
                            <td><fmt:formatDate value="${topic.effDate}" type="both" /></td>
                            <td><fmt:formatDate value="${topic.expDate}" type="both" /></td>
                            <td> 
                                 <a  href="javascript:;" onclick="toEditUrl('${topic.topicId}')" >编辑</a>                                                          
                            <c:choose>
                              <c:when test="${topic.auditStatus=='WAIT_AUDIT'}">
                                | <a  href="javascript:;" onclick="ballModel('${topic.topicId}','${topic.topicTitle}')">审核</a>
                              </c:when>
                               <c:otherwise>
                                | <a  href="javascript:;" onclick="ballAllocation('${topic.topicId}')">分配专题</a>
                               </c:otherwise>
                             </c:choose> 
                                | <a  href="javascript:;" onclick="deleteCategory('${topic.topicId}')">删除</a>
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
                        <!-- 22222222 --> --%>
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
					<h4 class="modal-title" id="myModalLabel">审核专题</h4>
				</div>
				<div class="modal-body">
				    <!-- form表单或各种input -->
				    <div class="control-group">
                             <!-- Text input-->
                             <label class="control-label col-lg-3" for="topicTitle">专题名称</label>                           
                             <div class="controls col-lg-5">
                                <input type="text" name="topicTitle" id="topicTitle" value="" readonly="readonly" class="form-control">
				    			<input type="hidden" name="topicId" id="topicId" value=""  class="form-control">
                             </div>
                             <div style="clear: both;"></div>
                     </div>
				    <!-- <input type="text" name="topicTitle" id="topicTitle" value="" readonly="readonly" class="form-control">
				    <input type="hidden" name="topicId" id="topicId" value=""> -->
				</div>
				<div class="modal-footer">
					<button type="button" class="button button-capitalize button-rounded button-primary button-small" onclick="passTopic('SUCCESS')">通过</button>
					<button type="button" class="button button-capitalize button-rounded button-primary button-small" data-dismiss="modal">
					拒绝
					</button>
				</div>
			</div>
		</div>
	</div> 
	
<!-- 分配专题DIV -->
	<div class="modal fade" id="allocationModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">分配专题</h4>
				</div>
				<div class="modal-body">
				    <!-- form表单或各种input -->
				    <form class="form-horizontal" action="/topic/category" method="post">
	    				<fieldset>    					
		    				 <div class="control-group">
	                             <!-- Text input-->
	                             <label class="control-label col-lg-4" for="checkAuditStatus">专题类型</label>                           
	                             <div class="controls col-lg-5">	                               
					    			<select class="form-control" name="topicCategoryId" id="topicCategoryId">
	    									<c:forEach items="${categoryList }" var="category">
	    									<option value="${category.topicCategoryId }">${category.topicCategoryName}</option>
	    									</c:forEach>
	    							</select>
	                             </div>
	                             <div style="clear: both;"></div>
	                     	</div>
	                     	<div id="shijian"> 
	                     	<div class="control-group">
	    							<label for="" class="control-label col-lg-4">世间头图</label>
	    							<div class="controls col-lg-5">
	    								<input id="shijianToppic" data-url="/publicImageUpload" type="file" name="files">
	    								<input type="hidden" name="topPicKey" id="addtopPicKey" value="">
	    							</div>
	    							 <div style="clear: both;"></div>
	    						</div>	    							
							  <div class="control-group" id="topImgDiv" style="display: none;">
	    					   <label for="" class="control-label col-lg-4">图片预览</label>
							          <div class="controls col-lg-5">
									    <div class="imgbox"> 
										<img id="shijianTopImg" src="">
										<button type="button" class="close" onclick="removeImg('SHIJIANTOP')" >×</button>
									    </div>
								    </div><!-- end ngRepeat: img in imageList -->
								     <div style="clear: both;"></div>
						      </div> 
	    						<div class="control-group" id="shijian">
	    							<label for="" class="control-label col-lg-4">世间列表图</label>
	    							<div class="controls col-lg-5">
	    								<input id="shijianListpic" data-url="/publicImageUpload" type="file" name="files">
	    								<input type="hidden" name="listPicKey" id="addlistPicKey" value="">
	    							</div>
	    							 <div style="clear: both;"></div>
	    						</div>
	    						<div class="control-group" id="listImgDiv" style="display: none;">
	    					         <label for="" class="control-label col-lg-4">世间列表图</label>
							             <div class="controls col-lg-5">
									       <div class="imgbox">
										     <img id="shijianListImg" src="">
										     <button type="button" class="close" onclick="removeImg('SHIJIANLIST')">×</button>
									        </div>
								       </div><!-- end ngRepeat: img in imageList -->
								        <div style="clear: both;"></div>
						         </div>	    						
	    					</div>
	    						
	    					<div id="xinhuan" style="display: none;">
	    						<div class="control-group" id="newImgDiv" style="display: none;">
	    					         <label for="" class="control-label col-lg-4">新欢图</label>
							             <div class="controls col-lg-5">
									       <div class="imgbox">
										     <img id="newShowImg" src="">
										     <button type="button" class="close" onclick="removeImg('XINHUAN')">×</button>
									        </div>
								       </div><!-- end ngRepeat: img in imageList -->
								        <div style="clear: both;"></div>
						        </div>
	    						<div class="control-group" >
	    							<label for="" class="control-label col-lg-4">新欢展示图</label>
	    							<div class="controls col-lg-5">
	    								<input id="xinhuanToppic" data-url="/publicImageUpload" type="file" name="files">
	    								<input type="hidden" name="newPicKey" id="addnewPicKey" value="">
	    							</div>
	    							 <div style="clear: both;"></div>
	    						</div>		    							
	    				</div>
                     	<div class="control-group">
	   							<label for="topicScore" class="control-label col-lg-4">排序权值</label>
	   							<div class="controls col-lg-5">
	   								<input type="text" name="topicScore" id="topicScore" value="" class="form-control">
	   								<p class="warn">(权值越高，排序越靠前)</p>
	   							</div>
	   							 <div style="clear: both;"></div>
	    				</div>
	    				
	    				 <div class="control-group">
                           <label for="effDateString" class="control-label col-lg-4">有效时间</label>
                           <div class="controls col-lg-5">
                               <input type="text" id="effDateString" class="form-control"
                                      name="effDateString"  onfocus="WdatePicker({firstDayOfWeek:1,dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                            </div>
                            <div class="time-end">至</div>
                             <div style="clear: both;"></div>
	    				</div>
	    				
	    				 <div class="control-group">
                           <label for="expDateString" class="control-label col-lg-4"></label>
                           <div class="controls col-lg-5">
                               <input type="text" id="expDateString" name="expDateString" class="form-control"
                                      onfocus="WdatePicker({firstDayOfWeek:1,dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                                      <p class="warn">(精确到秒，不填永久有效)</p>
                           </div>
                            <div style="clear: both;"></div>
                         </div>
                         
	    				<input type="hidden" name="relationId" id="relationId" value="">
	    				<input type="hidden" name="topicId" id="allocationTopicId" value="">	
                     	</fieldset>
                     	
	    				 <%-- <label for="" class="col-lg-3">专题类型</label>
	    						<div class="col-lg-9">
	    								<select class="form-control" style="width:40%;" name="topicCategoryId" id="topicCategoryId">
	    									<c:forEach items="${categoryList }" var="category">
	    									<option value="${category.topicCategoryId }">${category.topicCategoryName}</option>
	    									</c:forEach>
	    								 </select>
	    						</div>
	    				</div>	 --%>    				
							<!-- <div id="shijian">
							  <div class="form-group" id="topImgDiv" style="display: none;">
	    					   <label for="" class="col-lg-3">世间头图</label>
							          <div class="col-lg-9">
									    <div>
										<img id="shijianTopImg" style="max-height: 80px;" src=""><button type="button" class="close" style="margin-right:65%;" onclick="removeImg('SHIJIANTOP')" >×</button>
									    </div>
								    </div>end ngRepeat: img in imageList
						      </div>
	    						<div class="form-group" >
	    							<label for="" class="col-lg-3">世间头图</label>
	    							<div class="col-lg-9">
	    								<input id="shijianToppic" data-url="/publicImageUpload" type="file" name="files">
	    								<input type="hidden" name="topPicKey" id="addtopPicKey" value="">
	    							</div>
	    							</div>
	    							
	    							<div class="form-group" id="listImgDiv" style="display: none;">
	    					         <label for="" class="col-lg-3">世间列表图</label>
							             <div class="col-lg-9">
									       <div>
										     <img id="shijianListImg" style="max-height: 80px;" src=""><button type="button" class="close" style="margin-right: 50%;" onclick="removeImg('SHIJIANLIST')">×</button>
									        </div>
								       </div>end ngRepeat: img in imageList
						            </div>
	    						<div class="form-group" id="shijian">
	    							<label for="" class="col-lg-3">世间列表图</label>
	    							<div class="col-lg-9">
	    								<input id="shijianListpic" data-url="/publicImageUpload" type="file" name="files">
	    								<input type="hidden" name="listPicKey" id="addlistPicKey" value="">
	    							</div>
	    						</div>
	    					</div>
	    						
	    					<div id="xinhuan" style="display: none;">
	    						<div class="form-group" id="newImgDiv" style="display: none;">
	    					         <label for="" class="col-lg-3">新欢图</label>
							             <div class="col-lg-9">
									       <div>
										     <img id="newShowImg" style="max-height: 80px;" src=""><button type="button" class="close" style="margin-right: 50%;" onclick="removeImg('XINHUAN')">×</button>
									        </div>
								       </div>end ngRepeat: img in imageList
						        </div>
	    						<div class="form-group" >
	    							<label for="" class="col-lg-3">新欢展示图</label>
	    							<div class="col-lg-9">
	    								<input id="xinhuanToppic" data-url="/publicImageUpload" type="file" name="files">
	    								<input type="hidden" name="newPicKey" id="addnewPicKey" value="">
	    							</div>
	    						</div>	
	    						
	
	    				</div>
	    					  -->
	   					  <!-- <div class="form-group">
	   							<label for="" class="col-lg-3">排序权值</label>
	   							<div class="col-lg-9">
	   								<input type="text" name="topicScore" id="topicScore" value=""><i>(权值越高，排序越靠前)</i>
	   							</div>
	    				</div>
	    				 <div class="form-group">
                           <label for="" class="col-lg-3">有效时间</label>
                           <div class="col-lg-9">
                               <input type="text" id="effDateString"
                                      name="effDateString" placeholder="开始时间"
                                      value="" onfocus="WdatePicker({firstDayOfWeek:1,dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                               <input type="text" id="expDateString" name="expDateString"
                                      placeholder="结束时间" value="" onfocus="WdatePicker({firstDayOfWeek:1,dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                                      <i>(精确到秒，不填永久有效)</i>
                           </div>
                            </div>
	    				<input type="hidden" name="relationId" id="relationId" value="">
	    				<input type="hidden" name="topicId" id="allocationTopicId" value="">	 -->
				    <div class="modal-footer">
				     <button type="button" class="button button-capitalize button-rounded button-primary button-small" onclick="allocationTopic()">确定</button>
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
        <!-- 侧面导航栏组件 js -->
        <script src="/resources/js/sidebar.js"></script>
        <script src="/resources/js/jquery.pagination.js"></script>
        <script src="/resources/js/jquery/jquery.bootstrap.min.js"></script>
          <!-- 文件上传 --> 
        <script src="/resources/js/jquery_upload/vendor/jquery.ui.widget.js"></script>
        <script src="/resources/js/jquery_upload/jquery.iframe-transport.js"></script>
        <script src="/resources/js/jquery_upload/jquery.fileupload.js"></script>
        <script src="/resources/js/My97DatePicker/WdatePicker.js"></script>
        <script type="text/javascript">
           $('#light-pagination').pagination({
        		pages: "${page.pageCount}",
        		cssStyle: 'light-theme',
        		currentPage: "${page.page}",
				onPageClick: function(pageNumber, event) {
					var topicTitle="${page.topicTitle}";
					var topicCategoryId="${page.topicCategoryId}";
					var topicId="${page.topicId}";
					var auditStatus="${page.auditStatus}";
					window.location.href='page?page='+pageNumber+"&topicTitle="+topicTitle+"&topicCategoryId="+topicCategoryId+"&topicId="+topicId+"&auditStatus="+auditStatus;
				}
        	});
           //删除方法
           function deleteCategory(topicId){
        	      $.messager.confirm("删除专题", "您确定要删除此专题!", function() { 
        	    	  $.ajax({
     	    		     type: 'POST',
     	    		     url: '/special/'+topicId,
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
           function ballModel(topicId,topicTitle){
        	   $('#topicId').val(topicId);
        	   $('#topicTitle').val(topicTitle);
        	   $('#ballModal').modal({
        		   keyboard: true  
               });
        	  
           }
           
           //审核
           function passTopic(auditStatus){
        	   var topicId=$('#topicId').val();
        	   $.ajax({
	    		     type: 'POST',
	    		     url: '/special/audit/'+topicId,
	    		     data:{"auditStatus":auditStatus},
	    		     dataType: 'json',
	    		    success : function(data) {
	    		    	window.location.reload();
	   			     },
	   			    error : function() {
	   				    alert("系统异常");
	   			}

	    		   });
        	   
        	   
           }
           
           //弹出分配DIV
           function ballAllocation(topicId){
        	 
        	   $.ajax({
 	    		     type: 'GET',
 	    		     url: '/topicCategory/'+topicId,
 	    		     dataType: 'json',
 	    		    success : function(data) {
 	    		    	 $('#allocationTopicId').val(topicId)
 	    		    	
 	    		    	if( data.data.sign=="true"){
 	    		    	 $('#relationId').val(data.data.topicRelation.relationId);
 	    		    	 $('#topicCategoryId').val(data.data.topicRelation.topicCategoryId);
 	    		    	 $('#topicScore').val(data.data.topicRelation.topicScore);
	    		    	 $('#effDateString').val(data.data.topicRelation.effDateString);
	    		    	 $('#expDateString').val(data.data.topicRelation.expDateString);
 	    	        	 
 	    		    	if(data.data.topicRelation.topicPosition=="XINHUAN"){
 	    		    	 $('#shijian').hide();
   	    		    	 $('#xinhuan').show();
   	    		    	 $('#newImgDiv').show();
   	    		    	 $('#newShowImg').attr("src",data.data.imgURl+data.data.topicRelation.newPicKey);
 	    		    	 $('#addnewPicKey').val(data.data.topicRelation.newPicKey);
 	    		    		
 	    		    	}else{
 	    		    		$('#shijian').show();
   	    		    		$('#xinhuan').hide();
   	    		    		$('#topImgDiv').show();
   	    		    		$('#listImgDiv').show();
   	    		    	    $('#shijianListImg').attr("src",data.data.imgURl+data.data.topicRelation.listPicKey);
   	    		    	    $('#shijianTopImg').attr("src",data.data.imgURl+data.data.topicRelation.topPicKey);
 	    		    	    $('#addlistPicKey').val(data.data.topicRelation.listPicKey);
 	 	    	            $('#addtopPicKey').val(data.data.topicRelation.topPicKey);
 	    		    		
 	    		    		
 	    		    	}
 	    		    	}else{
 	    		    	  $('#shijian').show();
 	    		    	  $('#xinhuan').hide();
 	    		    	  $('#relationId').val('');	
 	    		    	  $('#topicCategoryId').val('');
 	    		    	  $('#newShowImg').attr("src",'');
 	    		    	  $('#addnewPicKey').val('');
 	    		    	  $('#shijianListImg').attr("src",'');
	    		    	  $('#shijianTopImg').attr("src",'');
	    		    	  $('#addlistPicKey').val('');
	 	    	          $('#addtopPicKey').val('');
	 	    	          $('#topicScore').val('');
		    	          $('#effDateString').val('');
		    	          $('#expDateString').val('');
 	    		    	}
 	    		    	$('#allocationModal').modal({
	    	        		   keyboard: true  
	    	              });
 	   			     },
 	   			     error : function() {
 	   				    alert("系统异常");
 	   			       }

 	    		   }); 	 
        	  
        	   
        	   
           } 
          //select改变 
           $("#topicCategoryId").change(
        		function(){
        			var topicCategoryId= $(this).val(); 
        			 $.ajax({
   	    		     type: 'GET',
   	    		     url: '/topic/category/'+topicCategoryId,
   	    		     dataType: 'json',
   	    		    success : function(data) {
   	    		      /* $('#newShowImg').attr("src",'');
    		    	  $('#addnewPicKey').val('');
    		    	  $('#shijianListImg').attr("src",'');
   		    	      $('#shijianTopImg').attr("src",'');
   		    	      $('#addlistPicKey').val('');
	    	          $('#addtopPicKey').val('');
	    	          $('#topicScore').val('');
	    	          $('#effDateString').val('');
	    	          $('#expDateString').val(''); */
	    		    		
   	    		    	if(data.data.category.topicCategoryPosition=='XINHUAN'){
   	    		    		$('#shijian').hide();
   	    		    		$('#xinhuan').show();	
   	    		    	}else{
   	    		    		$('#shijian').show();
   	    		    		$('#xinhuan').hide();
   	    		    		
   	    		    		
   	    		    	}
   	    		      
   	   			     },
   	   			    error : function() {
   	   				    alert("系统异常");
   	   			}

   	    		   }); 	 
        			
        		   
        		}
            
           )
           
           
           //上传世间头图
           $("#shijianToppic").fileupload({
                // formData:{param1:"p1",param2:"p2"},//如果需要额外添加参数可以在这里添加
                done:function(e,result){
                    //done方法就是上传完毕的回调函数，其他回调函数可以自行查看api
                    //注意result要和jquery的ajax的data参数区分，这个对象包含了整个请求信息
                    //返回的数据在result.result中，假设我们服务器返回了一个json对象
                	//alert(result.result);
                    var url=result.result.data.imgurl;
                    var fileName = $.parseJSON(JSON.stringify(result.result.data.fileNames[0]));
                    $('#shijianTopImg').attr("src",url+fileName);
                    $('#addtopPicKey').val(fileName);
                    $('#topImgDiv').show();
                }
            })
            
             //上传世间列表图
           $("#shijianListpic").fileupload({
                // formData:{param1:"p1",param2:"p2"},//如果需要额外添加参数可以在这里添加
                done:function(e,result){
                    //done方法就是上传完毕的回调函数，其他回调函数可以自行查看api
                    //注意result要和jquery的ajax的data参数区分，这个对象包含了整个请求信息
                    //返回的数据在result.result中，假设我们服务器返回了一个json对象
                	//alert(result.result);
                    var url=result.result.data.imgurl;
                    var fileName = $.parseJSON(JSON.stringify(result.result.data.fileNames[0]));
                    $('#shijianListImg').attr("src",url+fileName);
                    $('#addlistPicKey').val(fileName);
                    $('#listImgDiv').show();
                }
            })
            
             //上传新欢图
           $("#xinhuanToppic").fileupload({
                // formData:{param1:"p1",param2:"p2"},//如果需要额外添加参数可以在这里添加
                done:function(e,result){
                    //done方法就是上传完毕的回调函数，其他回调函数可以自行查看api
                    //注意result要和jquery的ajax的data参数区分，这个对象包含了整个请求信息
                    //返回的数据在result.result中，假设我们服务器返回了一个json对象
                	//alert(result.result);
                    var url=result.result.data.imgurl;
                    var fileName = $.parseJSON(JSON.stringify(result.result.data.fileNames[0]));
                    $('#newShowImg').attr("src",url+fileName);
                    $('#addnewPicKey').val(fileName);
                    $('#newImgDiv').show();
                }
            })
            
            //分配专题
            function allocationTopic(){
        	  if(validateData()){
        	   var topicId= $('#allocationTopicId').val();
        	   var topicCategoryId= $('#topicCategoryId').val();
        	   var relationId= $('#relationId').val();
        	   var newPicKey= $('#addnewPicKey').val();
        	   var listPicKey= $('#addlistPicKey').val();
        	   var topPicKey= $('#addtopPicKey').val();
        	   var topicScore= $('#topicScore').val();
         	   var effDateString= $('#effDateString').val();
         	   var expDateString= $('#expDateString').val();
        	   $.ajax({
 	    		     type: 'POST',
 	    		     url: '/topicCategory/'+topicId,
 	    		     data:{"relationId":relationId,"topicId":topicId,"topicCategoryId":topicCategoryId,"newPicKey":newPicKey,
 	    	                "listPicKey":listPicKey,"topPicKey":topPicKey,'topicScore':topicScore,'effDateString':effDateString,'expDateString':expDateString},
 	    		     dataType: 'json',
 	    		    success : function(data) {
 	    		    	if(data.code==200){
 	    		    		window.location.reload();	
 	    		    	}else{
 	    		    		alert(data.msg);
 	    		    	}
 	   			     },
 	   			    error : function() {
 	   				    alert("系统异常");
 	   			}

 	    		   }); 	 
        	  
        	  }  
          }
          //刷新
           function refresh(){
         	  location.reload();
           }
          
          //跳转增加专题
          $("#addTopic").click(function(){
        	  window.open("/special/toAddUrl"); 
        	  
          }) 
          //删除图片
          function removeImg(position){
        	  if(position=="SHIJIANTOP"){
        		  $('#topImgDiv').hide();
	    	      $('#addtopPicKey').val(''); 
        	  }if(position=="SHIJIANLIST"){
        		  $('#listImgDiv').hide();
        		  $('#addlistPicKey').val('');
        	  }else{
        		  $('#newImgDiv').hide();
 		    	  $('#addnewPicKey').val('');  
        		  
        	  }
        	  
        	
          }
          //跳转编辑专题
          function toEditUrl(topicId){
        	  window.open("/special/toEditUrl?topicId="+topicId);  
          } 
          //正整数
          function isPInt(str) {
              var g = /^[1-9]*[1-9][0-9]*$/;
              return g.test(str);
          }
        //校验参数
  		function validateData(){ 
  			if($('#topicScore').val()==null||$('#topicScore').val()==''){
    			alert('专题权值不能为空');
    			return false;	 			
    		}else{
    			if(!isPInt($('#topicScore').val())){
    				alert('专题权值请填正整数');
    				return false;
    			}
    		}
  		    var beginValue= $('#effDateString').val();
    	    var endValue= $('#expDateString').val();
    	    if(beginValue!=''&&endValue!=''){
			var begin=new Array();
	        var end=new Array();
	        begin=beginValue.split("-");
	        end=endValue.split("-");
			var bday = new Array();
			bday = begin[2].split(" ");

			var eday = new Array();
			eday = end[2].split(" ");
						
			if(begin[0]>end[0]){
			alert("起始日期大于结束日期");
				return false;
			}
			else if(end[0]-begin[0]>0){
				if(bday[0]>eday[0]){
						return true;
				}
			}  			
			else if(begin[0]==end[0]){
				if(begin[1] > end[1]){
					alert("起始日期大于结束日期");
					return false;
				}				
				else if(end[1]-begin[1]>0){
					if(bday[0]>eday[0]){
						return true;
					}
				}
				else if(end[1]==begin[1]){
					if(bday[0] > eday[0]){
						alert("起始日期大于结束日期");
						return false;
					}
				}
       		}
			return true;
    	    }
			return true;
		}
        
        //搜索表单提交验证
        function toVaild(){
         if($('#searchTopicId').val()!=null&&$('#searchTopicId').val()!=''){
        	 if(!isPInt($('#searchTopicId').val())){
 				alert('专题ID输入有误');
 				return false;
 			}
         }
         return true;
        
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