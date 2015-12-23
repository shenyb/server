<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
         <link rel="stylesheet" href="/resources/css/ops-addkol.css" >
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
                        <%@ include file="/resources/html/content_header.html" %>
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
				                    <li><a href="#">社会化分销管理</a></li>
				                    <li class="active">社会化分销列表</li>
				                </ol>
				            </div>
				        </div>
				
				        <div class="info">
				            <div class="info-top">
				                <span>社会化分销列表</span>
				                <div class="back">
				                    <a href="" class="ops-pub-btn button button-rounded button-tiny">刷新</a>	
				                    <a href="javascript:;" onclick="openmodel()" class="ops-pub-btn button button-rounded button-tiny">导入分销商品</a>		                   
				                </div>
				            </div>
				            <div class="search-form">
				                <form class="form-inline" action="/distribution/page" method="get">
				                    <div class="row">
					                    <div class="form-group col-xs-3">
					                        <label for="search-nickName">商品名称</label>
					                        <input type="text" name="goodsName" id="search-nickName" class="form-control" tabindex="1">
					                    </div>
					                    <div class="form-group col-xs-3">
					                        <label for="search-goodsCode">商品编号</label>					                       
					                        <input type="text" name="goodsCode" id="search-goodsCode" class="form-control" tabindex="2"> 
					                    </div>
					                    <div class="form-group col-xs-3">
					                        <label for="search-goodsCode">审核状态</label>					                       
					                        <select  name="distributionStatus" id="distributionStatus" class="form-control" tabindex="5">
				    							<option value="">全部</option>
				    							<option value="WAIT_TO_AUDIT">待审核</option>
				    							<option value="ALLOW_USE">已审核</option>
				    							<option value="FREEZE">已冻结</option>
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
				                	<th>商品编号</th>
                                    <th>商品名称</th>
                                    <th>佣金</th>
                                    <th>开始时间</th>
                                    <th>结束时间</th>
                                    <th>当前状态</th>
                                    <th>创建人</th>	
                                    <th>操作</th>			                    
				                </tr>
				                </thead>
			                <tbody>
							       <c:forEach items="${list}" var="distribution" varStatus="status">
				                        <tr>
				                            <td>${distribution.goodsCode} </td>
				                            <td>${distribution.goodsName} </td>
				                            <td>${distribution.commission/100} </td>
				                            <td><fmt:formatDate value="${distribution.startTime}" type="both" /></td>
				                            <td><fmt:formatDate value="${distribution.endTime}" type="both" /></td>
				                            
				                            <td>
				                             <c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
				                            <c:choose>
					                              <c:when test="${distribution.distributionStatus=='WAIT_TO_AUDIT'}">
					                                                                                        待审核
					                              </c:when>
					                              <c:when test="${distribution.distributionStatus=='FREEZE'}">
					                                                                                        冻结
					                              </c:when>
					                              <c:when test="${distribution.distributionStatus=='ALLOW_USE' and nowDate-distribution.startTime.time>0 and nowDate-distribution.endTime.time<0}">
					                                                                                        生效
					                              </c:when>
					                             
					                               <c:when test="${nowDate-distribution.startTime.time<0}">
					                                                                                        未开始
					                              </c:when>
					                               <c:when test="${nowDate-distribution.endTime.time>0}">
					                                                                                        过期
					                              </c:when>
					                             
					                             </c:choose>
				                            </td>
				                             <td>${distribution.creatorName}</td>
				                           <td style="vertical-align:middle">																						 
					                              <c:if test="${distribution.distributionStatus=='WAIT_TO_AUDIT'}">					                             
					                              <a href="javascript:;" onclick="audit('${distribution.id}')" >审核</a>
					                              </c:if>
					                              <c:if test="${distribution.distributionStatus=='WAIT_TO_AUDIT' or distribution.distributionStatus=='REJECT'}">
					                              |
					                              <a href="/distribution/toEditPage/${distribution.id}" >编辑</a>                                                           
					                              </c:if>
												  <c:if test="${distribution.distributionStatus=='ALLOW_USE'  and nowDate-distribution.startTime.time>0 and nowDate-distribution.endTime.time<0}">
					                          	  <a  href="javascript:;" onclick="frozen('${distribution.id}')">冻结</a>
					                          </c:if>   					                       
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
                       <%--  <!--22222222222222  -->
                            <div class="data-page-inner">
                                <div class="page-head">
                                    <!-- 页面标题 -->
                                    <h3 class="m-b-less">
                                       社会化分销管理
                                    </h3>
                                    <!--面包屑导航-->
                                    <div class="state-information">
                                        <ol class="breadcrumb m-b-less bg-less">
                                            <li><a href="#">主页</a></li>
                                            <li><a href="#">社会化分销管理</a></li>
                                            <li class="active">社会化分销列表</li>
                                        </ol>
                                    </div>
                                </div>
                                <div class="wrapper">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <section class="panel">
                                                <header class="panel-heading ">
                                                    
													
                                                    社会化分销列表
                                                    <span class="tools pull-right">
                                                       <a class="ops-pub-btn" href="" >
																<i class="fa fa-refresh"></i>
																<span>刷新</span>
															</a>
                                                         <a class="ops-pub-btn"  href="javascript:;" onclick="openmodel()">
				                                        <i class="fa fa-plus"></i>
				                                        <span>导入分销商品</span></a>
                                                    </span>
                                                </header>
                                                <div class="dataTables_wrapper form-inline dt-bootstrap no-footer" 
                                                     id="DataTables_Table_0_wrapper">
                                                    <div class="tbl-head">
                                                    <form action="/distribution/page" method="get">
                                                       <div class="bops-pub-param-seach" style="margin-top:10px">
															<input type="text" placeholder="商品名称" name="goodsName" id="search-nickName"  style="vertical-align: 1px;
																   		  width: 200px;
																   		  padding-left: 9px;
																   		  display:inline-block;
																   		  height:32px" class="ng-pristine ng-valid ng-touched">
															<input type="text" placeholder="商品编号" name="goodsCode" id="search-goodsCode"  style="vertical-align: 1px;
																   		  width: 200px;
																   		  padding-left: 9px;
																   		  display:inline-block;
																   		  height:32px" class="ng-pristine ng-valid ng-touched">
															<input type="submit" class="btn btn-info" value="搜索" />
														</div>
														</form>
                                                    </div>
                                                    <table aria-describedby="DataTables_Table_0_info" 
                                                           role="grid" id="DataTables_Table_0" 
                                                           class="table convert-data-table data-table dataTable no-footer">
                                                        <thead>
                                                            <tr role="row">
                                                             <th aria-label="OrderDate : activate to sort column descending" 
                                                                    aria-sort="ascending" style="width: 97px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting_asc">
                                                                    商品编号
                                                                </th>
                                                                <th aria-label="OrderDate : activate to sort column descending" 
                                                                    aria-sort="ascending" style="width: 150px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting_asc">
                                                                    商品名称
                                                                </th>
                                                                  <th aria-label="OrderDate : activate to sort column descending" 
                                                                    aria-sort="ascending" style="width: 110px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting_asc">
                                                                  佣金
                                                                </th>
                                                                <th  aria-label="Region : activate to sort column ascending" 
                                                                    style="width: 135px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                   开始时间
                                                                </th>
                                                                <th aria-label="Rep : activate to sort column ascending" 
                                                                    style="width: 100px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                   结束时间
                                                                </th>
                                                                <th aria-label="Item : activate to sort column ascending" 
                                                                    style="width: 152px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    当前状态
                                                                </th>
																
																<th aria-label="Rep : activate to sort column ascending" 
                                                                    style="width: 120px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                   创建人
                                                                </th>
                                                               
																<th aria-label="Item : activate to sort column ascending" 
                                                                    style="width: 204px;" 
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
                                                          <c:forEach items="${list}" var="distribution" varStatus="status">
											                        <tr>
											                            <td>${distribution.goodsCode} </td>
											                            <td>${distribution.goodsName} </td>
											                            <td>${distribution.commission/100} </td>
											                            <td><fmt:formatDate value="${distribution.startTime}" type="both" /></td>
											                            <td><fmt:formatDate value="${distribution.endTime}" type="both" /></td>
											                            
											                            <td>
											                             <c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
											                            <c:choose>
												                              <c:when test="${distribution.distributionStatus=='WAIT_TO_AUDIT'}">
												                                                                                        待审核
												                              </c:when>
												                              <c:when test="${distribution.distributionStatus=='FREEZE'}">
												                                                                                        冻结
												                              </c:when>
												                              <c:when test="${distribution.distributionStatus=='ALLOW_USE' and nowDate-distribution.startTime.time>0 and nowDate-distribution.endTime.time<0}">
												                                                                                        生效
												                              </c:when>
												                             
												                               <c:when test="${nowDate-distribution.startTime.time<0}">
												                                                                                        未开始
												                              </c:when>
												                               <c:when test="${nowDate-distribution.endTime.time>0}">
												                                                                                        过期
												                              </c:when>
												                             
												                             </c:choose>
											                            </td>
											                             <td>${distribution.creatorName}</td>
											                            
												                            
												                        
											                           <td style="vertical-align:middle">
																		
																		 
												                              <c:if test="${distribution.distributionStatus=='WAIT_TO_AUDIT'}">
												                             
												                              <a href="javascript:;" onclick="audit('${distribution.id}')" >
												                                                                                        审核
												                              </a>
												                              </c:if>
												                              <c:if test="${distribution.distributionStatus=='WAIT_TO_AUDIT' or distribution.distributionStatus=='REJECT'}">
												                              |&nbsp;
												                              <a href="/distribution/toEditPage/${distribution.id}" >
												                                                                                         编辑
												                               </a>                                                           
												                              </c:if>
												                             
												                             
												                          <c:if test="${distribution.distributionStatus=='ALLOW_USE'  and nowDate-distribution.startTime.time>0 and nowDate-distribution.endTime.time<0}">
												                          |&nbsp;
												                          <a  href="javascript:;" onclick="frozen('${distribution.id}')">冻结</a>
												                          </c:if>   
												                       
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
         <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content">
		         <div class="modal-header">
		            <button type="button" class="close"
		               data-dismiss="modal" aria-hidden="true">
		                  &times;
		            </button>
		            <h4 class="modal-title" id="myModalLabel">
		               导入分销商品
		            </h4>
		         </div>
		         <div class="modal-body">
		         
		            <table class="bops-pub-dialog-table">
							<tbody><tr>
								<th>导入文件文件</th>
								<td><input type="file" name="files" id="distributionFile" required /></td>
								<td><a class="button button-action button-rounded button-primary button-small" href="javascript:void(0);" onclick="downloadTemplate();">分销模板下载</a></td>
							  </tr>
						</tbody></table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="button button-capitalize button-rounded button-primary button-small" data-dismiss="modal">取消
		            </button>
		            <button type="button" onclick="submit()"  class="button button-capitalize button-rounded button-primary button-small">
		               提交
		            </button>
		         </div>
		        </div>
		      </div><!-- /.modal-content -->
		</div><!-- /.modal -->
	
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
				window.location.href='page?page='+pageNumber+"&goodsName=${page.goodsName}&goodsCode=${page.goodsCode}&distributionStatus=${page.distributionStatus}";
			}
    	});
        
        
        
        
        var isSubmit = false;
        function  openmodel(){
        	$('#kolCategoryImg').attr("src","");
        	$("#categoryName").val("");
        	$("#modal-categoryId").val("");
        	$("#kolCategoryImg").css('display', 'none');
        	$('#myModal').modal('show')          
   	      }  
        
        function  submit(){
        	if(isSubmit){
				alert("再次提交请刷新页面");				
				return ;
			}
			if('' == $("#distributionFile").val()){
				alert("请上传EXCEL文件");
				return ;
			}
			//alert($("#goodsPriceFile").val().indexOf('.xlsx'));
			if($("#distributionFile").val().indexOf('.xlsx')<0 
					&& $("#distributionFile").val().indexOf('.xls')<0){
				alert("请上传EXCEL文件");
				return ;
			}
			/* checkValidity() 
			if (!document.getElementById("goods_price_form_content").checkValidity()) {
				$("#subimt_form").trigger("click");
				return;
			}*/
			var formData = new FormData();
			formData.append("files", $("#distributionFile")[0].files[0]);
			
			$.ajax({
				url : '/distribution/add',
				type : 'post',
				data : formData,
				async : false,
				cache : false,
				dataType : "json",
				contentType : false,
				processData : false,
				success : function(data) {
					alert(data.msg);
					alert('添加失败的商品：'+data.data.badGoods);
					window.location.reload();
				}
			});
   	      }  
        
        
        function downloadTemplate(){
    		var url = "/distribution/templateDownload";
    		autoDownload(url);
    	}
        var autoDownload = function( _url ){
    		// var _params = $.param( _obj );
    		var iframEle = $('<ifram></ifram>');
    		    iframEle.css({
    		        'width': 0,
    		        'height': 0,
    		        'display': 'none'
    		    });
    		    iframEle.appendTo('body');
    		    // var url = '/need-bops/bops-api/goods/export?'+_params;
    		    // console.info( url);
    		var formEle = $('<form action="'+_url+'" method="post"></form>');
    		var inputEle = $('<input type="test" name="file" value="">');
            	inputEle.appendTo( formEle );
    		    formEle.appendTo( iframEle ).submit().remove();
    		    iframEle.remove();
    	}
        
        //审核方法
        function audit(id){
     	      $.messager.confirm("审核权限", "审核分销商品是否通过", function() { 
     	    	  $.ajax({
  	    		     type: 'POST',
  	    		     url: '/distribution/audit/'+id,
  	    		     dataType: 'json',
  	    		    success : function(data) {
  	    		    	alert(data.msg);
  	    		    	window.location.reload();
  	   			     },
  	   			    error : function() {
  	   			    alert(XMLResponse.responseText);
  	   			}

  	    		   });
     	        });
           
     	      }
        
        //frozen
        function frozen(id){
     	      $.messager.confirm("冻结", "确定是否要冻结", function() { 
     	    	  $.ajax({
  	    		     type: 'POST',
  	    		     url: '/distribution/frozen/'+id,
  	    		     dataType: 'json',
  	    		    success : function(data) {
  	    		    	alert(data.msg);
  	    		    	window.location.reload();
  	   			     },
  	   			    error : function() {
  	   			    alert(XMLResponse.responseText);
  	   			}

  	    		   });
     	        });
           
     	      }
         </script>
    </body>
</html>