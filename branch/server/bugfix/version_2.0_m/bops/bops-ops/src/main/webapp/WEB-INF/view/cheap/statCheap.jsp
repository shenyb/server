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
       <!--  <link rel="stylesheet" href="/resources/css/datapage.css" > -->
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
				                    <li><a href="#">团便宜管理</a></li>
				                    <li class="active">团便宜统计列表</li>
				                </ol>
				            </div>
				        </div>
				
				        <div class="info">
				            <div class="info-top">
				                <span>团便宜统计列表</span>
				                <div class="back">
				                    <a href="" class="button button-rounded button-tiny">刷新</a>					                    		                   
				                </div>
				            </div>
				            <div class="search-form">
				                <form class="form-inline"  action="/cheap/cheapStat" method="get">
				                    <div class="row">
					                    <div class="form-group col-xs-3">
					                        <label for="">商品编号</label>
					                        <input type="text" name="goodsCode"  class="form-control ng-pristine ng-valid ng-touched">
					                    </div>
					                    
					                    <div class="form-group col-xs-3">
					                        <label for="search-nickName">商品名称</label>					                       
					                         <input type="text" name="goodsName" id="search-nickName"  class="form-control ng-pristine ng-valid ng-touched">
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
                                    <th>开团数量</th>
                                    <th>参团数量</th>
                                    <th>持续时间</th>
                                    <th>支付人数</th>
                                    <th>折扣比例</th>
                                    <th>成团数</th>                             			                    
				                </tr>
				                </thead>
			                <tbody>
							      <c:forEach items="${list}" var="cheapStatList" varStatus="status">
                                           <tr>
                                               <td>${cheapStatList.goodsCode} </td>
                                               <td style="text-align:left;">${cheapStatList.goodsName} </td>
                                               <td>${cheapStatList.openCount} </td>
                                               <td>${cheapStatList.joinCount}</td>
                                               <td>${cheapStatList.duringTime}</td>
                                               <td>${cheapStatList.payCount}</td>
                                               <td>
                                               ${fn:substring(cheapStatList.discount,0,fn:length(cheapStatList.discount)-2)}
                                               </td>
                                               <td>${cheapStatList.completeCount}</td>
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
                                        团便宜管理
                                    </h3>
                                    <!--面包屑导航-->
                                    <div class="state-information">
                                        <ol class="breadcrumb m-b-less bg-less">
                                            <li><a href="#">主页</a></li>
                                            <li><a href="#">团便宜管理</a></li>
                                            <li class="active">团便宜统计列表</li>
                                        </ol>
                                    </div>
                                </div>
                                <div class="wrapper">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <section class="panel">
                                                <header class="panel-heading ">
                                                    
													
                                                    团便宜统计列表
                                                    <span class="tools pull-right">
                                                       <a class="ops-pub-btn" href="" >
																<i class="fa fa-refresh"></i>
																<span>刷新</span>
															</a>
                                                        
                                                    </span>
                                                </header>
                                                <div class="dataTables_wrapper form-inline dt-bootstrap no-footer" 
                                                     id="DataTables_Table_0_wrapper">
                                                    <div class="tbl-head">
                                                    <form action="/cheap/cheapStat" method="get">
                                                       <div class="bops-pub-param-seach" style="margin-top:10px">
                                                       <input type="text" placeholder="商品编号" name="goodsCode"   style="vertical-align: 1px;
																   		  width: 200px;
																   		  padding-left: 9px;
																   		  display:inline-block;
																   		  height:32px" class="ng-pristine ng-valid ng-touched">
															<input type="text" placeholder="商品名称" name="goodsName" id="search-nickName"  style="vertical-align: 1px;
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
                                                                    aria-sort="ascending" style="width: 97px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting_asc">
                                                                    商品名称
                                                                </th>
                                                                <th aria-label="OrderDate : activate to sort column descending" 
                                                                    aria-sort="ascending" style="width: 97px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting_asc">
                                                                    开团数量
                                                                </th>
                                                                <th  aria-label="Region : activate to sort column ascending" 
                                                                    style="width: 135px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    参团数量
                                                                </th>
                                                                <th aria-label="Rep : activate to sort column ascending" 
                                                                    style="width: 181px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    持续时间
                                                                </th>
                                                                <th aria-label="Item : activate to sort column ascending" 
                                                                    style="width: 152px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    支付人数
                                                                </th>
																
																<th aria-label="Rep : activate to sort column ascending" 
                                                                    style="width: 145px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    折扣比例
                                                                </th>
                                                                <th aria-label="Item : activate to sort column ascending" 
                                                                    style="width: 137px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    成团数
                                                                </th>
																
                                                                
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                               <c:forEach items="${list}" var="cheapStatList" varStatus="status">
											                        <tr>
											                            <td>${cheapStatList.goodsCode} </td>
											                            <td>${cheapStatList.goodsName} </td>
											                            <td>${cheapStatList.openCount} </td>
											                            <td>${cheapStatList.joinCount}</td>
											                            <td>${cheapStatList.duringTime}</td>
											                            <td>${cheapStatList.payCount}</td>
											                            <td>
											                            ${fn:substring(cheapStatList.discount,0,fn:length(cheapStatList.discount)-2)}
											                            </td>
											                            <td>${cheapStatList.completeCount}</td>
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
                       <!-- 222222222222 --> --%>
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
					window.location.href='cheapStat?page='+pageNumber+'&goodsName=${page.goodsName}';
				}
        	});
          
           
           function searchkol(){
        	   $.ajax({
	    		     type: 'get',
	    		     data:{
	    		    	 nickName:$("#search-nickName").val(),
	    		    	 kolCategories:$("#search-mobile").val(),
	    		    	 mobile:$("#search-category").val()
	    		     },
	    		     url: '/kol/page'
	    		 //    dataType: 'json',
	    		 /*    success : function(data) {
	    		    	window.location.reload();
	   			     },
	   			    error : function() {
	   			    alert(XMLResponse.responseText);
	   			} */

	    		   });
        	   
           }
         </script>
    </body>
</html>