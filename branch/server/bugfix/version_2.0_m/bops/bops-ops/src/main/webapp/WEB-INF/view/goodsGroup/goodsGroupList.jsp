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
				                    <li><a href="#">商品组合管理</a></li>
				                    <li class="active">商品组合列表</li>
				                </ol>
				            </div>
				        </div>
				
				        <div class="info">
				            <div class="info-top">
				                <span>商品组合列表</span>
				                <div class="back">
				                    <a href="" class="ops-pub-btn button button-rounded button-tiny">刷新</a>	
				                    <a class="button button-rounded button-tiny" id="addGroup" href="javascript:;">新增促销组合</a>			                   
				                </div>
				            </div>
				            <div class="search-form">
				                <form class="form-inline" id="searchGoodsContent" method="get" action="/goodsGroup/page">
				                    <div class="row">
					                    <div class="form-group col-xs-3">
					                        <label for="searchGroupBatch">组合批次</label>
					                        <input type="text" id="searchGroupBatch" name="groupBatch" value="${page.groupBatch }" class="form-control ng-pristine ng-untouched ng-valid">
					                    </div>
					                    <div class="form-group col-xs-3">
					                        <label for="searchGroupName">组合名称</label>					                       
					                        <input type="text" id="searchGroupName" name="groupName" value="${page.groupName }" class="form-control ng-pristine ng-untouched ng-valid">  
					                    </div> 
					                  </div>
					                   <div class="row">
					                   <div class="form-group col-xs-3">
					                        <label for="createId">创建人</label>					                       
					                        <select class="form-control" name="createId" id="createId" value="${page.createId}">
                                                 <option value="">全部</option>
                                                  <c:forEach items="${userList }" var="user">
                                                 <option value="${user.userId }" <c:if test="${page.createId==user.userId}">selected</c:if>>${user.userRealName}</option>
                                                 </c:forEach>                                                      
                                              </select> 
					                    </div> 
					                    <div class="form-group col-xs-3">
					                        <label for="groupStatus">审核状态</label>					                       
					                        <select  class="form-control" id="groupStatus" name="groupStatus">
                                                    <option  value="">全部</option>
                                                    <option value="INVALID" <c:if test="${page.groupStatus=='INVALID'}">selected</c:if>>待审核</option>
                                                    <option value="VALID"    <c:if test="${page.groupStatus=='VALID'}">selected</c:if>>通过</option>
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
				                	<th>组合批次</th>
				                    <th>组合名称</th>
				                    <th>规则类型</th>
				                    <th>组合商品数</th>				                    		                  
				                    <th>销售商品数</th>
				                    <th>状态</th>
				                    <th>创建人</th>
				                    <th>操作</th>				                    		                  					                    
				                </tr>
				                </thead>
			                <tbody>
							      <c:forEach items="${list}" var="goodsGroup" varStatus="status">
                                   <tr>
                                       <td>${goodsGroup.groupBatch}</td>
                                        <td>
                                        ${goodsGroup.groupName }
                                       </td>
                                       <td>
                                       <c:if test="${goodsGroup.ruleType=='DISCOUNT' }">折扣
                                       </c:if>
                                       <c:if test="${goodsGroup.ruleType=='FIXEDPRICE' }"> 一口价
                                       </c:if>
                                       </td>
                                       <td>${goodsGroup.goodsNumber} </td>
                                       <td>${goodsGroup.saledNumber}</td>
                             
                                       <td>
                                        <c:choose>
                                         <c:when test="${goodsGroup.groupStatus=='INVALID'}">待审核
                                         </c:when>
                                         <c:when test="${goodsGroup.groupStatus=='VALID'}">通过
                                         </c:when>
                                         <c:when test="${goodsGroup.groupStatus=='FROZEN'}">冻结
                                         </c:when>
                                        </c:choose>                                     
                                       </td>                                     
                                        <td>                                      
                                        <c:choose>
                                         <c:when test="${goodsGroup.createId==0 }">超级管理员</c:when>
                                         <c:otherwise>
                                          ${goodsGroup.userRealName }
                                         </c:otherwise>
                                        </c:choose>
                                        </td>
                                   
                                       <td>
                                       <a  href="javascript:;" onclick="viewGoods('${goodsGroup.groupBatch}')">查看</a>
                                       <c:choose>
                                         <c:when test="${goodsGroup.groupStatus=='INVALID'}">
                                          <a  href="javascript:;" onclick="editGroup('${goodsGroup.groupBatch}')">| 编辑</a>
                                         <a  href="javascript:;" onclick="ballModel('${goodsGroup.groupBatch}','${goodsGroup.groupName}','${goodsGroup.groupStatus}')">| 审核</a>
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
                        
                       <%--  <!-- 2222222222222 -->
                            <div class="data-page-inner">
                                <div class="page-head">
                                    <!-- 页面标题 -->
                                    <h3 class="m-b-less">
                                        商品组合管理123
                                    </h3>
                                    <!--面包屑导航-->
                                    <div class="state-information">
                                        <ol class="breadcrumb m-b-less bg-less">
                                            <li><a href="#">主页</a></li>
                                            <li><a href="#">商品组合管理</a></li>
                                            <li class="active">商品组合列表</li>
                                        </ol>
                                    </div>
                                </div>
                                <div class="wrapper">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <section class="panel">
                                                <header class="panel-heading ">
                                                    <!-- 列表名称 -->
													
                                                    商品组合列表
                                                    <span class="tools pull-right">
                                                        <a class="ops-pub-btn" href="">
														<i class="fa fa-refresh" ></i>
														<span>刷新</span>
														</a>
														<a class="bops-pub-btn" id="addGroup" href="javascript:;">
														<i class="fa fa-plus"></i>
														<span>新增促销组合</span>
														</a>
                                                    </span>
                                                </header>
                                              <!-- 搜索DIV -->                                                  
                                             <div class="tbl-head">
                                                 <form id="searchGoodsContent" method="get" action="/goodsGroup/page">
									              <div class="bops-pub-param-seach" >
									               <input type="text"  style="vertical-align: 1px; 
															   		  width: 200px;
															   		  padding-left: 18px;
															   		  display:inline-block;
															   		  height:32px" id="searchGroupBatch" name="groupBatch" value="${page.groupBatch }" placeholder="组合批次" class="ng-pristine ng-untouched ng-valid">
												    
												    <input type="text"  style="vertical-align: 1px; 
															   		  width: 200px;
															   		  padding-left: 18px;
															   		  display:inline-block;
															   		  height:32px" id="searchGroupName" name="groupName" value="${page.groupName }" placeholder="组合名称" class="ng-pristine ng-untouched ng-valid">     
												         <select class="form-control" name="createId" style="vertical-align: 1px;width: 200px;padding-left: 18px;display:inline-block;height:32px" id="createId" value="${page.createId}">
                                                            <option value>创建人</option>
                                                             <c:forEach items="${userList }" var="user">
                                                            <option value="${user.userId }" <c:if test="${page.createId==user.userId}">selected</c:if>>${user.userRealName}</option>
                                                            </c:forEach>                                                      
                                                         </select>
												           
												          <select  id="groupStatus" name="groupStatus"  style="vertical-align: 1px;width: 200px;padding-left: 18px;display:inline-block;height:32px">
							    									<option  value="">审核状态</option>
							    								    <option value="INVALID" <c:if test="${page.groupStatus=='INVALID'}">selected</c:if>>待审核</option>
																	<option value="VALID"    <c:if test="${page.groupStatus=='VALID'}">selected</c:if>>通过</option>
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
                                                                    组合批次
                                                                </th>
                                                                <th aria-label="Region : activate to sort column ascending" 
                                                                    style="width: 100px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    组合名称
                                                                </th>
                                                                 <th aria-label="Region : activate to sort column ascending" 
                                                                    style="width: 100px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    规则类型
                                                                </th>
                                                                <th aria-label="Rep : activate to sort column ascending" 
                                                                    style="width: 80px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    组合商品数
                                                                </th>
                                                                <th aria-label="Item : activate to sort column ascending" 
                                                                    style="width: 80px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    销售商品数
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
                                                                                                                              <th aria-label="Item : activate to sort column ascending" 
                                                                    style="width: 150px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    创建人
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
										                     <c:forEach items="${list}" var="goodsGroup" varStatus="status">
										                        <tr>
										                            <td>${goodsGroup.groupBatch}</td>
										                             <td>
										                             ${goodsGroup.groupName }
										                            </td>
										                            <td>
										                            <c:if test="${goodsGroup.ruleType=='DISCOUNT' }">
										                                                                               折扣
										                            </c:if>
										                            <c:if test="${goodsGroup.ruleType=='FIXEDPRICE' }">
										                                                                              一口价
										                            </c:if>
										                            </td>
										                            <td>${goodsGroup.goodsNumber} </td>
										                            <td>${goodsGroup.saledNumber}</td>
										                  
										                            <td>
										                             <c:choose>
										                              <c:when test="${goodsGroup.groupStatus=='INVALID'}">
										                                                                                        待审核
										                              </c:when>
										                              <c:when test="${goodsGroup.groupStatus=='VALID'}">
										                                                                                          通过
										                              </c:when>
										                              <c:when test="${goodsGroup.groupStatus=='FROZEN'}">
										                                                                                           冻结
										                              </c:when>
										                             </c:choose>
										                            
										                            </td>
										                           
										                             <td>
										                             
										                             <c:choose>
										                              <c:when test="${goodsGroup.createId==0 }">
										                                                                                        超级管理员
										                              </c:when>
										                              <c:otherwise>
										                               ${goodsGroup.userRealName }
										                              </c:otherwise>
										                             </c:choose>
										                             </td>
										                        
										                            <td>
										                            <a  href="javascript:;" onclick="viewGoods('${goodsGroup.groupBatch}')">查看</a>
										                            <c:choose>
										                              <c:when test="${goodsGroup.groupStatus=='INVALID'}">
										                               <a  href="javascript:;" onclick="editGroup('${goodsGroup.groupBatch}')">| 编辑</a>
										                              <a  href="javascript:;" onclick="ballModel('${goodsGroup.groupBatch}','${goodsGroup.groupName}','${goodsGroup.groupStatus}')">| 审核</a>
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
                        <!-- 2222222222222 --> --%>
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
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel" style="font-weight:bold;" >审核组合</h4>
				</div>
				<div class="modal-body">
				
				    <form class="form-horizontal" action="/topic/category" method="post">
				    <fieldset>
				    	
	    				<div class="control-group">
   							<label for="" class="control-label col-lg-4">组合批次</label>
   							<div class="controls col-lg-5">
   								<input type="text"  class="form-control" name="groupBatch" id="checkgroupBatch" value="" readonly="readonly">
   								<input type="hidden"  class="form-control" name="groupBatch" id="checkGoodsGroupBatch" value="" readonly="readonly">
   							</div>
   							<div style="clear: both;"></div>
	    				</div>
	    				<div class="control-group">
  							<label for="" class="control-label col-lg-4">组合名称</label>
  							<div class="controls col-lg-5">
  								<input type="text" name="groupName" id="checkgroupName" value="" readonly="readonly"  class="form-control">
  							</div>
  							<div style="clear: both;"></div>
	    				</div>
	    			</fieldset>
				    <div class="modal-footer" id="buttonDiv" >
				     <button type="button" class="button button-capitalize button-rounded button-primary button-small" onclick="checkGroup('VALID')">确定</button>
					 <button type="button" class="button button-capitalize button-rounded button-primary button-small" data-dismiss="modal">取消
				   </button>				    	
				    </div>
				</form>
				</div>
			</div>
		</div>
	</div> 
	
	
	<!-- 新增框 -->   
      <div class="modal fade" id="addGoodsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">添加商品编码</h4>
				</div>
				<div class="modal-body">
				    <form class="form-horizontal" action="" method="post">
	    			 <div class="form-group">
	    					<div class="form-group">
	    							<label for="" class="col-lg-2" style="margin-left: 15px;">商品编号</label>
	    							<div class="col-lg-9">
	    								<textarea name="content" rows="5" id="goodsCodes" cols="50" onpropertychange="if(this.scrollHeight>50) this.style.posHeight=this.scrollHeight+5"></textarea>
	    								<p>(请输入商品编号，以英文逗号分隔)</p>
	    							</div>
	    							<input type="hidden" name="groupBatch" id="addGoodsGroupBatch" value="" readonly="readonly">
	    				</div>		
	    				</div>
				    <div class="modal-footer" id="buttonDiv" >
				     <button type="button" class="btn btn-primary" onclick="addGroupGoods()">确定</button>
					 <button type="button" class="btn btn-default" data-dismiss="modal">取消
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
					var groupBatch="${page.groupBatch}";
					var groupName="${page.groupName}";
					var createId="${page.createId}";
					var groupStatus="${page.groupStatus}";
					window.location.href='/goodsGroup/page?page='+pageNumber+'&groupBatch='+groupBatch+'&groupName='+groupName+'&createId='+createId+'&groupStatus='+groupStatus;
				}
        	});
           
           $("#addGroup").click(function(){
        	   
        	   window.location.href='/goodsGroup/toAdd';
           })
           
           
            //弹框
           function ballModel(groupId,groupName,auditStatus){
        	   $("#checkGoodsGroupBatch").val(groupId);
        	   $("#checkgroupBatch").val(groupId);
        	   $("#checkgroupName").val(groupName);
        	   $('#ballModal').modal({
        		   keyboard: true  
              });
           }
           
           
           //审核
           function checkGroup(auditStatus){
        	   //alert(auditStatus);
       	   var groupBatch=$('#checkGoodsGroupBatch').val();
       	   $.ajax({
	    		     type: 'POST',
	    		     url: '/goodsGroup/audit?groupBatch='+groupBatch+"&groupStatus="+auditStatus,
	    		     dataType: 'json',
	    		    success : function(data) {
	    		    	window.location.reload();
	   			     },
	   			    error : function() {
	   				    alert("系统异常");
	   			}

	    		   });
       	   
       	   
          }
           
           function ballAddModel(groupId){
        	   $("#addGoodsGroupBatch").val(groupId)
        	   $('#addGoodsModal').modal({
        		   keyboard: true  
              });
        	   
           }

          function addGroupGoods(){
        	  var groupBatch= $("#addGoodsGroupBatch").val();
        	  var goodsCodes=$("#goodsCodes").val();
        	  alert(groupBatch);
 	    	  $.ajax({
	    		     type: 'POST',
	    		     url: '/goodsGroup/addGroupGoods',
	    		     data:{'goodsCodes':goodsCodes,"groupBatch":groupBatch},
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
           
          
         function viewGoods(groupBatch){
        	 window.location.href="/goodsGroup/viewGoods?groupBatch="+groupBatch;
         } 
         
         function editGroup(groupBatch){
        	 window.location.href="/goodsGroup/toEdit?groupBatch="+groupBatch;
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