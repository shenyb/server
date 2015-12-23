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
       <!--  <link rel="stylesheet" href="/resources/css/datapage.css" > -->
       <link rel="stylesheet" href="/resources/css/list-content2.css">
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
				                    <li><a href="#">运营位管理</a></li>
				                    <li class="active">运营位列表</li>
				                </ol>
				            </div>
				        </div>
				
				        <div class="info">
				            <div class="info-top">
				                <span>运营位列表</span>
				                <div class="back">
				                    <a href="" class="ops-pub-btn button button-rounded button-tiny">刷新</a>	
				                    <a class="bops-pub-btn button button-rounded button-tiny" id="addops" href="javascript:;">新增运营位</a>			                   
				                </div>
				            </div>
				            <div class="search-form">
				                <form class="form-inline" id="searchGoodsContent" method="get" action="/opsPosition/page">
				                    <div class="row">
					                    <div class="form-group col-xs-3">
					                        <label for="searchopsName">运营位名称</label>
					                        <input type="text" class="form-control" id="searchopsName" name="opsName" value="${page.opsName}" tabindex="1">
					                    </div>
					                    <div class="form-group large-group col-xs-4">
					                        <label for="searchOpsType">运营位位置</label>					                       
					                        <select  id="searchOpsType" name="opsType"  class="form-control">
	    									    <option  value="">全部</option>
	    								        <option value="START_BANNER" <c:if test="${page.opsType=='START_BANNER'}">selected</c:if>>欢迎运营位</option>
			 									<option value="HOME_BANNER" <c:if test="${page.opsType=='HOME_BANNER'}">selected</c:if>>首页运营位</option>
			 									<option value="SHIJIAN_BANNER" <c:if test="${page.opsType=='SHIJIAN_BANNER'}">selected</c:if>>商品-世间-BANNER</option>
			 									<option value="SHIJIAN_SCROLL" <c:if test="${page.opsType=='SHIJIAN_SCROLL'}">selected</c:if>>商品-世间-SCROLL</option>
			 									<option value="XINHUAN" <c:if test="${page.opsType=='XINHUAN'}">selected</c:if>>商品-新欢(1.2)</option>
			 									<option value="XINHUAN_BANNER" <c:if test="${page.opsType=='XINHUAN_BANNER'}">selected</c:if>>新欢-BANNER(1.3)</option>
			 									<option value="XINHUAN_SCROLL" <c:if test="${page.opsType=='XINHUAN_SCROLL'}">selected</c:if>>新欢-品类运营位(1.3)</option>
			 									<option value="XINHUAN_PREFECTURE" <c:if test="${page.opsType=='XINHUAN_PREFECTURE'}">selected</c:if>>新欢-特价专区(1.3)</option>
			 									<option value="KOLCATEGORY" <c:if test="${page.opsType=='KOLCATEGORY'}">selected</c:if>>行家类别运营位</option>
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
				                	<th>运营位图片</th>
				                    <th>运营位名称</th>
				                    <th>运营位位置</th>
				                    <th>创建时间</th>				                    		                  
				                    <th>操作</th>				                    
				                </tr>
				                </thead>
			                <tbody>
							      <c:forEach items="${list}" var="ops" varStatus="status">
			                        <tr>
			                            <td>
			                             <img alt="运营位图片" src="${picDomain}${ops.opsPic}">
			                            </td>
			                            <td>${ops.opsName}</td>
			                            <td>
			                             <c:if test="${ops.opsType=='START_BANNER'}">启动页运营位</c:if>
			                             <c:if test="${ops.opsType=='HOME_BANNER'}">首页BANNER运营位</c:if>
			                             <c:if test="${ops.opsType=='SHIJIAN_BANNER'}">世间BANNER运营位</c:if>
			                             <c:if test="${ops.opsType=='SHIJIAN_SCROLL'}">世间SCROLL运营位</c:if>
			                             <c:if test="${ops.opsType=='XINHUAN'}">新欢商品运营位(1.2)</c:if>
			                              <c:if test="${ops.opsType=='KOLCATEGORY'}">行家分类运营位</c:if>
			                             <c:if test="${ops.opsType=='XINHUAN_BANNER'}">新欢BANNER运营位(1.3)</c:if>
			                             <c:if test="${ops.opsType=='XINHUAN_SCROLL'}">新欢品类运营位(1.3)</c:if>
			                             <c:if test="${ops.opsType=='XINHUAN_PREFECTURE'}">新欢特价专区(1.3)</c:if>			                            			                            
			                            </td>
			                            <td>
			                              <fmt:formatDate value="${ops.createTime}" type="both" />
			                            </td>
			                            <td>
			                             <a  href="javascript:;" id="editOps" onclick="ballEdit('${ops.opsId}','${ops.opsName}','${ops.opsType}','${ops.opsPic}')">修改</a>
			                            <c:choose>
			                              <c:when test="${ops.opsType=='XINHUAN'||ops.opsType=='XINHUAN_PREFECTURE'}">
			                                | <a  href="/opsXinhuanGoods/xinhuanGoods?opsId=${ops.opsId}" id="allocationOps" >分配商品</a>
			                              </c:when>
			                              <c:otherwise>
			                                |  <a  href="javascript:;" id="allocationOps" onclick="ballAllocation('${ops.opsId}','${ops.opsType}')">分配专题</a>
			                              </c:otherwise>
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
                        
                        <%-- <!--22222222222  -->
                            <div class="data-page-inner">
                                <div class="page-head">
                                    <!-- 页面标题 -->
                                    <h3 class="m-b-less">
                                        运营位管理
                                    </h3>
                                    <!--面包屑导航-->
                                    <div class="state-information">
                                        <ol class="breadcrumb m-b-less bg-less">
                                            <li><a href="#">主页</a></li>
                                            <li><a href="#">运营位管理</a></li>
                                            <li class="active">运营位列表</li>
                                        </ol>
                                    </div>
                                </div>
                                <div class="wrapper">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <section class="panel">
                                                <header class="panel-heading ">
                                                    
													
                                                    运营位列表
                                                    <span class="tools pull-right">
                                                        <a class="ops-pub-btn" href="">
														<i class="fa fa-refresh" ></i>
														<span>刷新</span>
														</a>
														<a class="bops-pub-btn" id="addops" href="javascript:;">
														<i class="fa fa-plus"></i>
														<span>新增运营位</span>
														</a>
                                                    </span>
                                                </header>
                                            <!-- 搜索DIV -->                                                  
                                             <div class="tbl-head">
                                                 <form id="searchGoodsContent" method="get" action="/opsPosition/page">
									              <div class="bops-pub-param-seach" >
									              <input type="text" id="searchopsName" style="vertical-align: 1px; 
															   		  width: 200px;
															   		  padding-left: 18px;
															   		  display:inline-block;
															   		  height:32px" id="searchTypeId" name="opsName" value="${page.opsName}" placeholder="运营位名称" class="ng-pristine ng-untouched ng-valid">
												             <select  id="searchOpsType" name="opsType"  style="vertical-align: 1px;width: 200px;padding-left: 18px;display:inline-block;height:32px">
							    									     <option  value="">运营位位置</option>
							    								        <option value="START_BANNER" <c:if test="${page.opsType=='START_BANNER'}">selected</c:if>>欢迎运营位</option>
									 									<option value="HOME_BANNER" <c:if test="${page.opsType=='HOME_BANNER'}">selected</c:if>>首页运营位</option>
									 									<option value="SHIJIAN_BANNER" <c:if test="${page.opsType=='SHIJIAN_BANNER'}">selected</c:if>>商品-世间-BANNER</option>
									 									<option value="SHIJIAN_SCROLL" <c:if test="${page.opsType=='SHIJIAN_SCROLL'}">selected</c:if>>商品-世间-SCROLL</option>
									 									<option value="XINHUAN" <c:if test="${page.opsType=='XINHUAN'}">selected</c:if>>商品-新欢(1.2)</option>
									 									<option value="XINHUAN_BANNER" <c:if test="${page.opsType=='XINHUAN_BANNER'}">selected</c:if>>新欢-BANNER(1.3)</option>
									 									<option value="XINHUAN_SCROLL" <c:if test="${page.opsType=='XINHUAN_SCROLL'}">selected</c:if>>新欢-品类运营位(1.3)</option>
									 									<option value="XINHUAN_PREFECTURE" <c:if test="${page.opsType=='XINHUAN_PREFECTURE'}">selected</c:if>>新欢-特价专区(1.3)</option>
									 									<option value="KOLCATEGORY" <c:if test="${page.opsType=='KOLCATEGORY'}">selected</c:if>>行家类别运营位</option>
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
                                                                    运营位图片
                                                                </th>
                                                                <th aria-label="Region : activate to sort column ascending" 
                                                                    style="width: 135px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    运营位名称
                                                                </th>
                                                                <th aria-label="Rep : activate to sort column ascending" 
                                                                    style="width: 181px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    运营位位置
                                                                </th>
                                                                                                         <th aria-label="Rep : activate to sort column ascending" 
                                                                    style="width: 181px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    创建时间
                                                                </th>
                                                                <th aria-label="Item : activate to sort column ascending" 
                                                                    style="width: 133px;" 
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
                                                              <c:forEach items="${list}" var="ops" varStatus="status">
                        <tr>
                            <td>
                             <img width="60px" alt="运营位图片" src="${picDomain}${ops.opsPic}">
                            </td>
                            <td>${ops.opsName}</td>
                            <td>
                             <c:if test="${ops.opsType=='START_BANNER'}">
                                                                                     启动页运营位
                             </c:if>
                             <c:if test="${ops.opsType=='HOME_BANNER'}">
                                                                                     首页BANNER运营位
                             </c:if>
                             <c:if test="${ops.opsType=='SHIJIAN_BANNER'}">
                                                                                    世间BANNER运营位
                             </c:if>
                             <c:if test="${ops.opsType=='SHIJIAN_SCROLL'}">
                                                                                     世间SCROLL运营位
                             </c:if>
                             <c:if test="${ops.opsType=='XINHUAN'}">
                                                                                     新欢商品运营位(1.2)
                             </c:if>
                              <c:if test="${ops.opsType=='KOLCATEGORY'}">
                                                                                     行家分类运营位
                             </c:if>
                             <c:if test="${ops.opsType=='XINHUAN_BANNER'}">
                                                                                     新欢BANNER运营位(1.3)
                             </c:if>
                             <c:if test="${ops.opsType=='XINHUAN_SCROLL'}">
                                                                                      新欢品类运营位(1.3)
                             </c:if>
                             <c:if test="${ops.opsType=='XINHUAN_PREFECTURE'}">
                                                                                      新欢特价专区(1.3)
                             </c:if>
                            
                            
                            </td>
                            <td>
                              <fmt:formatDate value="${ops.createTime}" type="both" />
                            </td>
                            <td>
                             <a  href="javascript:;" id="editOps" onclick="ballEdit('${ops.opsId}','${ops.opsName}','${ops.opsType}','${ops.opsPic}')">修改</a>
                            <c:choose>
                              <c:when test="${ops.opsType=='XINHUAN'||ops.opsType=='XINHUAN_SCROLL'||ops.opsType=='XINHUAN_PREFECTURE'}">
                                | <a  href="/opsXinhuanGoods/xinhuanGoods?opsId=${ops.opsId}" id="allocationOps" >分配商品</a>
                              </c:when>
                              <c:otherwise>
                                |  <a  href="javascript:;" id="allocationOps" onclick="ballAllocation('${ops.opsId}','${ops.opsType}')">分配专题</a>
                              </c:otherwise>
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
                        <!-- 22222222222 -->
                        </div> --%>
                        <!-- 
                            这部分写各自页面对应的内容
                            注意锁紧格式
                            范围 end -
                         -->
                    </div>
                </div>
            </div>
        </section>
       
   <!-- 新增DIV -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">运营位管理</h4>
				</div>
				<div class="modal-body">
				    <!-- form表单或各种input -->
				    <form class="form-horizontal" action="/topic/category" method="post">
				    	<fieldset>
					    	<div class="control-group">
	                              <!-- Text input-->
	                              <label class="control-label col-lg-4" for="addOpsName">运营位名称</label>                              
	                              <div class="controls col-lg-5">	                                
	                              	 <input type="text" name="opsName" id="addOpsName" value="" class="form-control">
	    							 <input type="hidden" name="opsId" id="addOpsId" value="" class="form-control">
	                              </div>
	                              <div style="clear: both;"></div>
	                        </div>
	                        <div class="control-group">
	                              <!-- Text input-->
	                              <label class="control-label col-lg-4" for="addOpsType">运营位位置</label>                              
	                              <div class="controls col-lg-5">	                                
	                              	 <select class="form-control" name="opsType" id="addOpsType">
	    									<option value="START_BANNER">欢迎运营位</option>
	    									<option value="HOME_BANNER">首页运营位</option>
	    									<option value="SHIJIAN_BANNER">商品-世间-BANNER</option>
	    									<option value="SHIJIAN_SCROLL">商品-世间-SCROLL</option>
	    									<option value="XINHUAN">商品-新欢(1.2)</option>
	    									<option value="XINHUAN_BANNER">新欢-BANNER(1.3)</option>
	    									<option value="XINHUAN_SCROLL">新欢-品类运营位(1.3)</option>
	    									<option value="XINHUAN_PREFECTURE">新欢-特价专区(1.3)</option>
	    									<option value="KOLCATEGORY">行家类别运营位</option>
	    								 </select>
	                              </div>
	                              <div style="clear: both;"></div>
	                        </div>
	                       
	                        <div class="control-group">
	                              <!-- Text input-->
	                              <label class="control-label col-lg-4" for="mainpic">上传图片</label>                              
	                              <div class="controls col-lg-6">	                                
	                              	 	<input id="mainpic" data-url="/publicImageUpload" type="file" name="files">
	    								<input type="hidden" name="opsPic" id="addOpsPic" value="">
	                              </div>
	                              <div style="clear: both;"></div>
	                        </div>
	                         <div class="control-group" id="imgDiv" style="display: none;">	                            
	                              <label class="control-label col-lg-4" for="opsImg">图片预览</label>                              
	                              <div class="controls col-lg-5">	                                
	                              	 <div class="imgbox">
								       <img id="opsImg" src="">
								       <button type="button" class="close" onclick="removeImg()" >×</button>
							        </div>
	                              </div>
	                              <div style="clear: both;"></div>
	                        </div>
                        </fieldset>
                   <!-- 22222222 -->
	    						<!-- <div class="form-group">
	    							<label for="" class="col-lg-3">运营位名称</label>
	    							<div class="col-lg-9">
	    								<input type="text" name="opsName" id="addOpsName" value="">
	    								<input type="hidden" name="opsId" id="addOpsId" value="">
	    							</div>
	    						</div>	
	    						<div class="form-group">
	    							<label for="" class="col-lg-3">运营位类型位置</label>
	    							<div class="col-lg-9">
	    								<select class="form-control" style="width:40%;" name="opsType" id="addOpsType">
	    									<option value="START_BANNER">欢迎运营位</option>
	    									<option value="HOME_BANNER">首页运营位</option>
	    									<option value="SHIJIAN_BANNER">商品-世间-BANNER</option>
	    									<option value="SHIJIAN_SCROLL">商品-世间-SCROLL</option>
	    									<option value="XINHUAN">商品-新欢(1.2)</option>
	    									<option value="XINHUAN_BANNER">新欢-BANNER(1.3)</option>
	    									<option value="XINHUAN_SCROLL">新欢-品类运营位(1.3)</option>
	    									<option value="XINHUAN_PREFECTURE">新欢-特价专区(1.3)</option>
	    									<option value="KOLCATEGORY">行家类别运营位</option>
	    								 </select>
	    							</div>
	    						</div>
	    					<div class="form-group" id="imgDiv" style="display: none;">
	    					<label for="" class="col-lg-3">图片预览</label>
							<div class="col-lg-9">
									<div>
										<img id="opsImg" style="max-height: 80px;" src=""><button type="button" class="close" style="margin-right:65%;" onclick="removeImg()" >×</button>
									</div>
								</div>end ngRepeat: img in imageList
							</div>
							
	    						<div class="form-group">
	    							<label for="" class="col-lg-3">上传图片</label>
	    							<div class="col-lg-9">
	    								<input id="mainpic" data-url="/publicImageUpload" type="file" name="files">
	    								<input type="hidden" name="opsPic" id="addOpsPic" value="">
	    							</div>
	    						</div>	 -->			
				
				<div class="modal-footer">
				     <button type="button" class="button button-capitalize button-rounded button-primary button-small" onclick="submitOps()">确定</button>
					 <button type="button" class="button button-capitalize button-rounded button-primary button-small" data-dismiss="modal">取消
					</button>					
				</div>
				</form>
			</div>
		</div>
	</div>  
</div>
	
	<!-- 分配DIV -->
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
	                              <label class="control-label col-lg-4" for="allocationOpsType">运营位类型</label>                              
	                              <div class="controls col-lg-5">	                                
	                              	 <select class="form-control" name="opsType" id="allocationOpsType">
	    									<option value="TOPIC">专题</option>
	    									<option value="KOL">行家</option>
	    									<option value="GOODS">商品</option>
	    									<option value="COUPON">优惠券</option>
	    									<option value="CHEAP">团便宜</option>
	    									<option value="DISCOUNT">促销活动</option>
	    								 </select>	
	                              </div>
	                              <div style="clear: both;"></div>
	                        </div>
	                        <div class="control-group" id="kolCategory" style="display: none;">
	                              <!-- Text input-->
	                              <label class="control-label col-lg-4" for="allocationCategoryId">行家分类</label>                              
	                              <div class="controls col-lg-5">	                                
	                              	 <select class="form-control" name="categoryId" id="allocationCategoryId">
	    									<c:forEach items="${categoryList}" var="category">
	    									<option value="${category.categoryId }">${category.categoryName }</option>
	    									</c:forEach>
	    							 </select>
	                              </div>
	                              <div style="clear: both;"></div>
	                        </div>
	                        <div class="control-group small-group" id="hideCheap">
	                              <!-- Text input-->
	                              <label class="control-label col-lg-4" for="typeId">专题ID</label>                              
	                              <div class="controls col-lg-5">	                                
	                              	 	<input type="text" name="typeId" id="typeId" value="" class="form-control">
	    								<input type="hidden" name="opsId" id="allocationopsId" value="" class="form-control">
	    								<input type="hidden" name="opsNumber" id="allocationopsNumber" value="" class="form-control">	
	                              </div>
	                              <div style="clear: both;"></div>
	                        </div>
	                         <div class="control-group small-group">
	                              <!-- Text input-->
	                              <label class="control-label col-lg-4" for="topicScore">专题权值</label>                              
	                              <div class="controls col-lg-5">	                                
	                              	 	<input type="text" name="topicScore" id="topicScore" value="" class="form-control">	
	                              	 	<p class="warn">(请输入整数，权值越高，序位越高)</p>
	                              </div>
	                              <div style="clear: both;"></div>
	                        </div>
	                        <div class="control-group">
	                              <!-- Text input-->
	                              <label class="control-label col-lg-4" for="">时间</label>                              
	                              <div class="controls col-lg-5">	                                
	                              	 	<input type="text" id="effDateString" name="effDateString" class="form-control"
	                              	 	onfocus="WdatePicker({firstDayOfWeek:1,dateFmt:'yyyy-MM-dd HH:mm:ss'})">                          	 	
	                              </div>
	                               <div class="time-end">至</div>
	                              <div style="clear: both;"></div>
	                        </div>
	                        <div class="control-group">
	                              <!-- Text input-->
	                              <label class="control-label col-lg-4" for=""></label>                              
	                              <div class="controls col-lg-5">	                                
	                              	 	<input type="text" id="expDateString" name="expDateString" class="form-control"
                                      		 onfocus="WdatePicker({firstDayOfWeek:1,dateFmt:'yyyy-MM-dd HH:mm:ss'})">
	                              	 	<p class="warn">(精确到秒，不填永久有效)</p>
	                              </div>	                               
	                              <div style="clear: both;"></div>
	                        </div>
	                   	</fieldset>
	                   	
	    				<!-- <div class="form-group">
	    				 <label for="" class="col-lg-3">运营位类型</label>
	    						<div class="col-lg-9">
	    								<select class="form-control" style="width:40%;" name="opsType" id="allocationOpsType">
	    									<option value="TOPIC">专题</option>
	    									<option value="KOL">行家</option>
	    									<option value="GOODS">商品</option>
	    									<option value="COUPON">优惠券</option>
	    									<option value="CHEAP">团便宜</option>
	    									<option value="DISCOUNT">促销活动</option>
	    								 </select>		 
	    						</div>
	    				</div>	 -->
	    				
	    				<%-- <div class="form-group" id="kolCategory" style="display: none;">
	    				 <label for="" class="col-lg-3">行家分类</label>
	    						<div class="col-lg-9">
	    								<select class="form-control" style="width:40%;" name="categoryId" id="allocationCategoryId">
	    									<c:forEach items="${categoryList}" var="category">
	    									<option value="${category.categoryId }">${category.categoryName }</option>
	    									</c:forEach>
	    								 </select>
	    						</div>
	    				</div>	 --%>
	    				
	    				
	    				<!-- <div class="form-group" id="hideCheap">
	    							<label for="" class="col-lg-3">专题ID</label>
	    							<div class="col-lg-9">
	    								<input type="text" name="typeId" id="typeId" value="">
	    								<input type="hidden" name="opsId" id="allocationopsId" value="">
	    								<input type="hidden" name="opsNumber" id="allocationopsNumber" value="">
	    							</div>
	    				</div> -->
	    				
	    				<!-- <div class="form-group">
	    							<label for="" class="col-lg-3">专题权值</label>
	    							<div class="col-lg-9">
	    								<input type="text" name="topicScore" id="topicScore" value=""><i>(请输入整数，权值越高，序位越高)</i>
	    							</div>
	    				</div>
	    				 <div class="form-group">
                           <label for="" class="col-lg-3">时间（精确到秒）</label>
                           <div class="col-lg-9">
                               <input type="text" id="effDateString"
                                      name="effDateString" placeholder="开始时间"
                                      value="" onfocus="WdatePicker({firstDayOfWeek:1,dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                               <input type="text" id="expDateString" name="expDateString"
                                      placeholder="结束时间" value="" onfocus="WdatePicker({firstDayOfWeek:1,dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                                      <i>(不填永久有效)</i>
                           </div>
                            </div>	 -->	
				    <div class="modal-footer">
				     <button type="button" class="button button-capitalize button-rounded button-primary button-small" onclick="allocationOps()">确定</button>
					 <button type="button" class="button button-capitalize button-rounded button-primary button-small" data-dismiss="modal">取消</button>	
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
					var opsName="${page.opsName}";
					var opsType="${page.opsType}";
					window.location.href='page?page='+pageNumber+'&opsName='+opsName+'&opsType='+opsType;
				}
        	});
           
           //弹出新增框
           $("#addops").click(function(){
        	   $('#addOpsName').val('');
        	   $('#addOpsType').val('');
        	   $('#addOpsPic').val('');
        	   $('#imgDiv').hide();
        	   $('#mainpic').attr("value","");
        	   $('#addOpsId').val('');
        	   $('#addModal').modal({
        		   keyboard: true  
              });
        	   
           })
           
           //弹出编辑框
           function ballEdit(opsId,opsName,opsType,opsPic){
        	   $('#addOpsName').val(opsName);
        	   $('#addOpsType').val(opsType);
        	   $('#addOpsPic').val(opsPic);
        	   $('#addOpsId').val(opsId);
        	   $('#imgDiv').show();
        	   $('#opsImg').attr("src","${picDomain}"+opsPic);
        	   $('#mainpic').attr("value","");
        	   $('#addModal').modal({
        		   keyboard: true  
              });
        	   
           }
           //弹出分配DIV
           function ballAllocation(opsNumber,opsType){
        	   $('#allocationopsNumber').val(opsNumber);
        	   $.ajax({
	    		     type: 'GET',
	    		     url: '/assignTopic/'+opsNumber,
	    		     dataType: 'json',
	    		    success : function(data) {
	    		    	if(JSON.stringify(data.data)!="{}"){
	    		    	    var jsonObj=$.parseJSON(JSON.stringify(data));
	    		    	    $('#allocationOpsType').val(jsonObj.data.bopsOps.opsType);
	    		    	    $('#typeId').val(jsonObj.data.bopsOps.typeId);
	    		    	    $('#allocationopsId').val(jsonObj.data.bopsOps.opsId);
	    		    	    
	    		    	    $('#allocationCategoryId').val(jsonObj.data.bopsOps.categoryId);
	    		    	    $('#topicScore').val(jsonObj.data.bopsOps.topicScore);
	    		    	    $('#effDateString').val(jsonObj.data.bopsOps.effDateString);
	    		    	    $('#expDateString').val(jsonObj.data.bopsOps.expDateString);
	    		    	  
	    		    	}else{
	    		    		$('#allocationOpsType').val('');
		    		    	$('#typeId').val('');
		    		    	$('#allocationopsId').val('');
		    		    	$('#topicScore').val('');
		    	        	$('#effDateString').val('');
		    	        	$('#expDateString').val('');
	    		    		
	    		    	}
	    		    	 if(opsType=="KOLCATEGORY"){
	    		    		   $('#kolCategory').show();
	    		    	   }else{
	    		    		   $('#kolCategory'). hide();
	    		    		   $('#allocationCategoryId').val('');
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
           
           
           
           //上传图片
           $("#mainpic").fileupload({
                // formData:{param1:"p1",param2:"p2"},//如果需要额外添加参数可以在这里添加
                done:function(e,result){
                    //done方法就是上传完毕的回调函数，其他回调函数可以自行查看api
                    //注意result要和jquery的ajax的data参数区分，这个对象包含了整个请求信息
                    //返回的数据在result.result中，假设我们服务器返回了一个json对象
                	//alert(result.result);
                    var url=result.result.data.imgurl;
                    var fileName = $.parseJSON(JSON.stringify(result.result.data.fileNames[0]));
                    $('#opsImg').attr("src",url+fileName);
                    $('#addOpsPic').val(fileName);
                    $('#imgDiv').show();
                }
            })
         //新增运营位
         function submitOps(){
        	var opsName = $('#addOpsName').val();
        	var opsPosition= $('#addOpsType').val();
        	var opsPic= $('#addOpsPic').val();
        	var opsId= $('#addOpsId').val();
        	if(opsId==null||opsId.length== 0){
        	 $.ajax({
	    		     type: 'POST',
	    		     url: '/opsPosition',
	    		     data: {'opsName':opsName,'opsType':opsPosition,'opsPic':opsPic},
	    		     dataType: 'json',
	    		    success : function(data) {
                       if(JSON.stringify(data.code)==200){
	    		    	   window.location.reload();
                       }else{ 
                    	   alert(JSON.stringify(data.msg));
                       }
	   			     },
	   			    error : function() {
	   				    alert("系统异常");
	   			}

	    		   });
        	}else{
        		$.ajax({
	    		     type: 'POST',
	    		     url: '/opsPosition/edit',
	    		     data: {'opsId':opsId,'opsName':opsName,'opsType':opsPosition,'opsPic':opsPic},
	    		     dataType: 'json',
	    		    success : function(data) {
                      if(JSON.stringify(data.code)==200){
	    		    	   window.location.reload();
                      }else{ 
                   	   alert(JSON.stringify(data.msg));
                      }
	   			     },
	   			    error : function() {
	   				    alert("系统异常");
	   			}

	    		   });
        		
        		
        	}        	   
           }
          //分配运营位
          function allocationOps(){
        	  if(validateData()){
        	  var opsType= $('#allocationOpsType').val();
        	  var typeId= $('#typeId').val();
        	  var opsId= $('#allocationopsId').val();
        	  var opsNumber= $('#allocationopsNumber').val();
        	  var categoryId= $('#allocationCategoryId').val();
        	  var topicScore= $('#topicScore').val();
        	  var effDateString= $('#effDateString').val();
        	  var expDateString= $('#expDateString').val();
        	  var opsPosition= $('#addOpsType').val();
        	  $.ajax({
	    		     type: 'POST',
	    		     url: '/assignTopic',
	    		     data: {'categoryId':categoryId,'opsId':opsId,'typeId':typeId,'opsType':opsType,'opsNumber':opsNumber,'topicScore':topicScore,'effDateString':effDateString,'expDateString':expDateString,'opsPosition':opsPosition},
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
          //回车
          $(window).bind("keydown",function(e){
	            var key = e.which;
	            if (key == 13) {
	                $("#searChops").click();
	            }
	        })
        //删除图片
          function removeImg(position){
        	  $('#addOpsPic').val('');
              $('#imgDiv').hide();
        	  
        	  
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
        
        $("#allocationOpsType").change(function(){
        	if($(this).val()=='CHEAP'||$(this).val()=='COUPON'){
        		$('#hideCheap').hide();
        		
        	}else{
        		
        		$('#hideCheap').show();
        	}
        	
        	
        })
         </script>
    </body>
</html>