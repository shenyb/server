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
				                    <li class="active">运营位商品列表</li>
				                </ol>
				            </div>
				        </div>
				
				        <div class="info">
				            <div class="info-top">
				                <span>运营位商品列表</span>
				                <div class="back">
				                    <a href="" class="ops-pub-btn button button-rounded button-tiny">刷新</a>	
				                    <a class="bops-pub-btn button button-rounded button-tiny" id="addGoods" href="javascript:;">运营位新增商品</a>	
				                    <a class="bops-pub-btn button button-rounded button-tiny" id="deleteall" href="javascript:;">删除所选商品</a>			                   
				                </div>
				            </div>
				            <div class="search-form">
				                <form class="form-inline" id="searchGoodsContent" method="get" action="/opsXinhuanGoods/xinhuanGoods">
				                    <div class="row">
					                    <div class="form-group col-xs-3">
					                        <input type="hidden" name="opsId" value="${page.opsId }">
					                        <label for="searchGoodsCode">商品编码</label>
					                        <input type="text" class="form-control" id="searchGoodsCode" name="goodsCode" value="${page.goodsCode }" tabindex="1">
					                    </div>
					                    <div class="form-group col-xs-3">
					                        <label for="searchGoodsName">商品名称</label>					                       
					                        <input type="text" id="searchGoodsName" name="goodsName" value="${page.goodsName }" class="form-control" tabindex="2">
					                    </div> 
					                     <div class="form-group col-xs-3">
					                        <label for="searchGoodsName">仓库类型</label>					                       
					                         <select  id="warehouseType" name="warehouseType" class="form-control" tabindex="3">
													<option  value="">全部</option>
												    <option value="TAX_WAREHOUSE" <c:if test="${page.warehouseType=='TAX_WAREHOUSE'}">selected</c:if>>保税仓</option>
													<option value="SELF_WAREHOUSE"    <c:if test="${page.warehouseType=='SELF_WAREHOUSE'}">selected</c:if>>自营仓</option>
													<option value="OVERSEA_WAREHOUSE"       <c:if test="${page.warehouseType=='OVERSEA_WAREHOUSE'}">selected</c:if>>海外直邮</option>
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
				                	<th><label style="margin-right:8px;"><input id="checkAll" type="checkbox" value="" /></label>全选</th>
				                    <th>商品编码</th>
				                    <th>商品名称</th>
				                    <th>商品场景图</th>
				                    <th>商品权值</th>	
				                    <th>仓库</th>			                    		                  
				                    <th>操作</th>				                    
				                </tr>
				                </thead>
			                <tbody>
							     <c:forEach items="${opsGoodslist}" var="opsGoods" varStatus="status">
			                        <tr>
			                            <td><input  type="checkbox" name="id" value="${opsGoods.id }" /></td>
			                            <td>${opsGoods.goodsCode}</td>
			                            <td>${opsGoods.goodsName}</td>
			                            <td><img style="width:60px;" src="${picDomain }${opsGoods.scenePicKey}"></td>
			                            <td>${opsGoods.goodsScore}</td>
			                             <td>
			                             <c:choose>
			                              <c:when test="${opsGoods.warehouseType=='TAX_WAREHOUSE'}">
			                                                                                             保税仓
			                              </c:when>
			                              <c:when test="${opsGoods.warehouseType=='SELF_WAREHOUSE'}">
			                                                                                             自营仓
			                              </c:when>
			                              <c:when test="${opsGoods.warehouseType=='OVERSEA_WAREHOUSE'}">
			                                                                                             海外直邮
			                              </c:when>
			                             </c:choose>
			                            </td>
			                            <td>
			                            <a id="${opsGoods.id}" href="javascript:;" onclick="showOpsGoods('${opsGoods.id}','${opsGoods.goodsCode}','${opsGoods.goodsName}','${opsGoods.goodsScore}')">编辑</a>
			                           | <a href="javascript:;" onclick="deleteOpsGoods('${opsGoods.id}')">删除</a>
			                            
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
                        
                       <%--  <!-- 22222222222 -->
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
                                            <li><a href="#">运营位管理</a></li>
                                            <li class="active">运营位商品列表</li>
                                        </ol>
                                    </div>
                                </div>
                                <div class="wrapper">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <section class="panel">
                                                <header class="panel-heading ">
                                                    <!-- 列表名称 -->
													
                                                    运营位商品列表
                                                    <span class="tools pull-right">
                                                        <a class="ops-pub-btn" href="">
														<i class="fa fa-refresh" ></i>
														<span>刷新</span>
														</a>
														<a class="bops-pub-btn" id="addGoods" href="javascript:;">
														<i class="fa fa-plus"></i>
														<span>运营位新增商品</span>
														</a>
														<a class="bops-pub-btn" id="deleteall" href="javascript:;">
														<span>删除所选商品</span>
														</a>
                                                    </span>
                                                </header>
                                              <!-- 搜索DIV -->                                                  
                                              <div class="tbl-head">
                                                 <form id="searchGoodsContent" method="get" action="/opsXinhuanGoods/xinhuanGoods">
									              <div class="bops-pub-param-seach" >
									              <input type="hidden" name="opsId" value="${page.opsId }">
									              <input type="text" id="searchGoodsCode" style="vertical-align: 1px; 
															   		  width: 200px;
															   		  padding-left: 18px;
															   		  display:inline-block;
															   		  height:32px;"  name="goodsCode" value="${page.goodsCode }" placeholder="商品编码" class="ng-pristine ng-untouched ng-valid">
					                             <input type="text" id="searchGoodsName" style="vertical-align: 1px; 
															   		  width: 200px;
															   		  padding-left: 18px;
															   		  display:inline-block;
															   		  height:32px;" name="goodsName" value="${page.goodsName }" placeholder="商品名称" class="ng-pristine ng-untouched ng-valid">
									           <select  id="warehouseType" name="warehouseType"  style="vertical-align: 1px;width: 200px;padding-left: 18px;display:inline-block;height:32px">
				    									<option  value="">仓库类型</option>
				    								    <option value="TAX_WAREHOUSE" <c:if test="${page.warehouseType=='TAX_WAREHOUSE'}">selected</c:if>>保税仓</option>
														<option value="SELF_WAREHOUSE"    <c:if test="${page.warehouseType=='SELF_WAREHOUSE'}">selected</c:if>>自营仓</option>
														<option value="OVERSEA_WAREHOUSE"       <c:if test="${page.warehouseType=='OVERSEA_WAREHOUSE'}">selected</c:if>>海外直邮</option>
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
                                                              <th aria-label="Region : activate to sort column ascending" 
                                                                    style="width: 50px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                <label><input id="checkAll" type="checkbox" value="" />全选</label>
                                                                </th>
                                                                <th aria-label="Region : activate to sort column ascending" 
                                                                    style="width: 50px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    商品编码
                                                                </th>
                                                                <th aria-label="Rep : activate to sort column ascending" 
                                                                    style="width: 150px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    商品名称
                                                                </th>
                                                                <th aria-label="Item : activate to sort column ascending" 
                                                                    style="width: 80px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    商品场景图
                                                                </th>
                                                                                                           
                                                                <th aria-label="Item : activate to sort column ascending" 
                                                                    style="width: 100px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    商品权值
                                                                </th>
                                                                
                                                                <th aria-label="Item : activate to sort column ascending" 
                                                                    style="width: 80px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    仓库
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
                   <c:forEach items="${opsGoodslist}" var="opsGoods" varStatus="status">
                        <tr>
                            <td><input  type="checkbox" name="id" value="${opsGoods.id }" /></td>
                            <td>${opsGoods.goodsCode}</td>
                            <td>${opsGoods.goodsName}</td>
                            <td><img style="width:60px;" src="${picDomain }${opsGoods.scenePicKey}"></td>
                            <td>${opsGoods.goodsScore}</td>
                             <td>
                             <c:choose>
                              <c:when test="${opsGoods.warehouseType=='TAX_WAREHOUSE'}">
                                                                                             保税仓
                              </c:when>
                              <c:when test="${opsGoods.warehouseType=='SELF_WAREHOUSE'}">
                                                                                             自营仓
                              </c:when>
                              <c:when test="${opsGoods.warehouseType=='OVERSEA_WAREHOUSE'}">
                                                                                             海外直邮
                              </c:when>
                             </c:choose>
                            </td>
                            <td>
                            <a id="${opsGoods.id}" href="javascript:;" onclick="showOpsGoods('${opsGoods.id}','${opsGoods.goodsCode}','${opsGoods.goodsName}','${opsGoods.goodsScore}')">编辑</a>
                           | <a href="javascript:;" onclick="deleteOpsGoods('${opsGoods.id}')">删除</a>
                            
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
                        <!--222222222222  --> --%>
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
     
          <!-- 新增框 -->   
      <div class="modal fade" id="ballModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">添加商品编码</h4>
				</div>
				<div class="modal-body">
				 <form class="form-horizontal"  action="" method="post">
				     <fieldset>
				      <input type="hidden" name="topicCategoryId" id="topicCategoryId" >
				      	<div class="control-group large-group">                              
                              <label class="control-label col-lg-3" for="goodsCodes">商品编号</label>                             
                              <div class="controls col-lg-8">
                                  <textarea name="content" rows="5" id="goodsCodes"  class="form-control" onpropertychange="if(this.scrollHeight>50) this.style.posHeight=this.scrollHeight+5"></textarea>
                              		<p class="warn">(请输入商品编号，以英文逗号分隔)</p>
                              </div>
                              <div style="clear: both;"></div>
                      </div>
                      </fieldset>
				   <!--  <form class="form-horizontal" action="" method="post">
	    			 <div class="form-group">
	    					<div class="form-group">
	    							<label for="" class="col-lg-2" style="margin-left: 15px;">商品编号</label>
	    							<div class="col-lg-9">
	    								<textarea name="content" rows="5" id="goodsCodes" cols="50" onpropertychange="if(this.scrollHeight>50) this.style.posHeight=this.scrollHeight+5"></textarea>
	    								<p>(请输入商品编号，以英文逗号分隔)</p>
	    							</div>
	    				</div>		
	    				</div> -->
				    <div class="modal-footer" id="buttonDiv" >
				     <button type="button" class="button button-capitalize button-rounded button-primary button-small" onclick="addOpsGoods()">确定</button>
					 <button type="button" class="button button-capitalize button-rounded button-primary button-small"  data-dismiss="modal">取消</button>				    	
				    </div>
				</form>
				</div>
			</div>
		</div>
	</div> 
     
     
    <!-- 审核DIV -->
	<div class="modal fade" id="editGoodsModal" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="ModalLabel">编辑商品权值</h4>
				</div>
				<div class="modal-body">
				    <!-- form表单或各种input -->
				    <form class="form-horizontal">
				    		<fieldset>
				    		<div class="control-group">                              
		                              <label class="control-label col-lg-4" for="editGoodsCode">商品编码</label>                             
		                              <div class="controls col-lg-5">
		                              <input type="text" name="editGoodsCode" class="form-control" id="editGoodsCode" readonly="readonly">		                                  
		                              </div>
		                              <div style="clear: both;"></div>
		                      </div>
		                      <div class="control-group">                              
		                              <label class="control-label col-lg-4" for="editGoodsName">商品名称</label>                             
		                              <div class="controls col-lg-5">
		                              <input type="text" name="editGoodsName" class="form-control" id="editGoodsName" readonly="readonly">		                               
		                              </div>
		                              <div style="clear: both;"></div>
		                      </div>
		                      <div class="control-group small-group">                              
		                              <label class="control-label col-lg-4" for="editGoodsScore">商品权值</label>                             
		                              <div class="controls col-lg-5">
		                                  <input type="text" name="editGoodsScore" class="form-control" id="editGoodsScore" >
	    								<p class="warn">(请输入整数，分数越高，序位越高)</p>
		                              </div>
		                              <div style="clear: both;"></div>
		                      </div>
		                       <input type="hidden" id="editId" value="">
		                      </fieldset>
				   <!--  <form class="form-horizontal" >
				      <input type="hidden" name="topicCategoryId" id="topicCategoryId" >
				    	<div class="form-group">
	    							<label for="" class="col-lg-2">商品编码</label>
	    							<div class="col-lg-9">
	    								<input type="text" name="editGoodsCode" id="editGoodsCode" readonly="readonly">
	    							</div>
	    			    </div>
	    			    <div class="form-group">
	    							<label for="" class="col-lg-2">商品名称</label>
	    							<div class="col-lg-9">
	    								 <input type="text" name="editGoodsName" id="editGoodsName" readonly="readonly">
	    							</div>
	    			    </div>
	    			    
	    			    <div class="form-group">
	    							<label for="" class="col-lg-2">商品权值</label>
	    							<div class="col-lg-9">
	    								 <input type="text" name="editGoodsScore" id="editGoodsScore" >
	    								 <i>(请输入整数，分数越高，序位越高)</i>
	    							</div>
	    			    </div> -->
				        
				<div class="modal-footer">
					<button type="button" class="button button-capitalize button-rounded button-primary button-small" onclick="passCategory()">通过</button>
					<button type="button" class="button button-capitalize button-rounded button-primary button-small" data-dismiss="modal">
					 取消
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
					 var opsId ="${xinhuanOpsId}";
					window.location.href='xinhuanGoods?page='+pageNumber+"&opsId="+opsId;
				}
        	});
     
           
           //删除单条数据
           function deleteOpsGoods(id){
        	   $.messager.confirm("删除商品", "您确定要删除此商品!", function() { 
      	    	  $.ajax({
   	    		     type: 'POST',
   	    		     url: '/opsXinhuanGoods/deleteGoods',
   	    		     data:{'id':id},
   	    		     dataType: 'json',
   	    		    success : function(data) {
   	    		    	window.location.reload();
   	   			     },
   	   			    error : function() {
   	   				    alert("系统异常");
   	   			}

   	    		   });
      	        });
            
     	      };
           
           //弹出框
           $("#addGoods").click(function(){
        	 $("#goodsCodes").val("");
        	   $('#ballModal').modal({
        		   keyboard: true  
              });
        	   
           });
           
                      
            //运营位添加商品 
           function addOpsGoods(){
               var xinhuanOpsId ="${xinhuanOpsId}";
        	   var goodsCodes=$("#goodsCodes").val();
        	  if(goodsCodes==null||goodsCodes==""){
        		  alert("请输入商品编码");
        		  return false;
        	  }if(goodsCodes.indexOf("，")!=-1){
        		  alert("分隔符有误");
        		  return false;
        		  
        	  }
  	    	  $.ajax({
	    		     type: 'POST',
	    		     url: '/opsXinhuanGoods/addGoods',
	    		     data:{'goodsCodes':goodsCodes,"opsId":xinhuanOpsId},
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
          //正整数
           function isPInt(str) {
               var g = /^[1-9]*[1-9][0-9]*$/;
               return g.test(str);
           }
            
            
           //修改商品排序权值
           function showOpsGoods(id,goodsCode,goodsName,goodsScore){
        	   $("#editId").val(id);
        	   $("#editGoodsCode").val(goodsCode);
        	   $("#editGoodsName").val(goodsName);
        	   $("#editGoodsScore").val(goodsScore);
        	   $('#editGoodsModal').modal({
        		   keyboard: true  
              });
        	   

        	   
           }
           //修改权值
           function passCategory(){
        	  var id=$("#editId").val();
        	  var goodsScore= $("#editGoodsScore").val();
         	  if($("#editGoodsScore").val()!=null){
        		  if(!isPInt($('#editGoodsScore').val())){
        			  
        			  alert("商品权值请填写正整数");
        		  }else{
        			  
          	    	  $.ajax({
     	    		     type: 'POST',
     	    		     url: '/opsXinhuanGoods/editGoods',
     	    		     data:{"id":id,"goodsScore":goodsScore},
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
        		  
        		  
        	  } else{
        		  
        		  alert("商品权值为空")
        	  } 
        	   
        	   
        	   
        	   
           }
           //全选
           $("#checkAll").click(function() {
   			//alert(this.checked);
   			if (this.checked) {
   				$("input[type='checkbox']").each(function() {
   					this.checked = true;
   				});
   			} else {
   				$("input[type='checkbox']").each(function() {
   					this.checked = false;
   				});

   			}

   		});
           
           //删除所有商品
           
           $("#deleteall").click(function() {
   			deleteUser();
   		});
           
           function deleteUser() {
       		var f = document.getElementsByName("id");
       		var c = 0;
       		var ids = "";
       		var id = "";
       		var i = 0;
       		for ( var j = 0; j < f.length; j++) {
       			if (f[j].checked) {
       				i++;
       				id = f[j].value;
       				ids = ids + "&id=" + id;
       			}
       		}

       		if (i == 0) {
       			alert("请至少选择一个商品!");
       			return;
       		}

       		if (confirm("确定要删除吗?")) {
       		 $.ajax({
	    		     type: 'POST',
	    		     url: '/opsXinhuanGoods/deleteAllGoods?' + ids,
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
           //回车
           $(window).bind("keydown",function(e){
 	            var key = e.which;
 	            if (key == 13) {
 	                $("#searChops").click();
 	            }
 	        })
         </script>
    </body>
</html>