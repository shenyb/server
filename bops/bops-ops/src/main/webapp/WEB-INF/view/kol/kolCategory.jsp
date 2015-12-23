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
				                    <li><a href="#">行家分类管理</a></li>
				                    <li class="active">行家分类列表</li>
				                </ol>
				            </div>
				        </div>
				
				        <div class="info">
				            <div class="info-top">
				                <span>行家分类列表</span>	
				                <div class="back">
				                    <a href="" class="ops-pub-btn button button-rounded button-tiny">刷新</a>	
				                    <a class="bops-pub-btn button button-rounded button-tiny" href="javascript:;" onclick="openmodel()">新增分类</a>			                   
				                </div>			                
				            </div>
				            <div class="search-form"></div>
				            <!--biaoge-->
				            <table class="table table-bordered table-striped table-condensed table-responsive">
				                <thead>
				                <tr>
				                	<th>行家分类图片</th>
				                    <th>行家分类id</th>
				                    <th>行家分类名</th>
				                    <th>创建时间</th>				                    		                  				                    				                    		                  
				                    <th>操作</th>				                    
				                </tr>
				                </thead>
			                <tbody>
							      <c:forEach items="${list}" var="category"> 
                                        <tr class="odd" role="row">                                      
                                            <td class="sorting_1">                                                
                                                <img alt="" src="${imgurl}${category.categoryProfileKey}"> 
                                            </td>
                                            <td>
                                                ${category.categoryId}
                                            </td>
                                            <td>
                                                ${category.categoryName}
                                            </td>
                                            <td>
                                            <fmt:formatDate value="${category.createTime}" type="both" />                                                
                                            </td>
                                            <td>                                               
                                                <a  href="javascript:;" onclick="update('${category.categoryId}','${category.categoryProfileKey}','${category.categoryName}')">修改</a>&nbsp;|&nbsp;
                                                <a  href="javascript:;" onclick="deletekolcategory('${category.categoryId}')">删除</a>
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
                       
                       <%--  <!-- 22222222 -->
                            <div class="data-page-inner">
                                <div class="page-head">
                                    <!-- 页面标题 -->
                                    <h3 class="m-b-less">
                                        行家分类管理
                                    </h3>
                                    <!--面包屑导航-->
                                    <div class="state-information">
                                        <ol class="breadcrumb m-b-less bg-less">
                                            <li><a href="#">主页</a></li>
                                            <li><a href="#">行家分类管理</a></li>
                                            <li class="active">行家分类列表</li>
                                        </ol>
                                    </div>
                                </div>
                                <div class="wrapper">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <section class="panel">
                                                <header class="panel-heading ">
                                                    <!-- 列表名称 -->
                                                    行家分类列表
                                                    <span class="tools pull-right">
                                                         <a class="ops-pub-btn" href="" >
																<i class="fa fa-refresh"></i>
																<span>刷新</span>
															</a>
                                                        <a class="bops-pub-btn" href="javascript:;" onclick="openmodel()">
				                                        <i class="fa fa-plus"></i>
				                                        <span>新增分类</span>
			                                            </a>
                                                    </span>
                                                </header>
                                                <div class="dataTables_wrapper form-inline dt-bootstrap no-footer" 
                                                     id="DataTables_Table_0_wrapper">
                                                    <div class="tbl-head">
                                                        
                                                    </div>
                                                    <table aria-describedby="DataTables_Table_0_info" 
                                                           role="grid" id="DataTables_Table_0" 
                                                           class="table convert-data-table data-table dataTable no-footer">
                                                        <thead>
                                                            <tr role="row">
                                                                <th aria-label="OrderDate : activate to sort column descending" 
                                                                    aria-sort="ascending" style="width: 184px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting_asc">
                                                                    行家分类图片
                                                                </th>
                                                                <th aria-label="Region : activate to sort column ascending" 
                                                                    style="width: 135px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    行家分类id
                                                                </th>
                                                                <th aria-label="Rep : activate to sort column ascending" 
                                                                    style="width: 181px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    行家分类名
                                                                </th>
                                                                <th aria-label="Item : activate to sort column ascending" 
                                                                    style="width: 133px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    创建时间
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
                                                          <c:forEach items="${list}" var="category"> 
                                                            <tr class="odd" role="row">
                                                          
                                                                <td class="sorting_1">
                                                                    
                                                                    <img alt=""  style="width:25px;" src="${imgurl}${category.categoryProfileKey}"> 
                                                                </td>
                                                                <td>
                                                                    ${category.categoryId}
                                                                </td>
                                                                <td>
                                                                    ${category.categoryName}
                                                                </td>
                                                                <td>
                                                                <fmt:formatDate value="${category.createTime}" type="both" />
                                                                    
                                                                </td>
                                                                <td>
                                                                    
                                                                    <a  href="javascript:;" onclick="update('${category.categoryId}','${category.categoryProfileKey}','${category.categoryName}')">修改</a>&nbsp;|&nbsp;
                                                                    <a  href="javascript:;" onclick="deletekolcategory('${category.categoryId}')">删除</a>
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
                                                                 class="dataTables_info">共有 ${page.total} 条, 每页显示 10 条</div>
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
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		                  &times;
		            </button>
		            <h4 class="modal-title" id="myModalLabel">
		               行家分类管理
		            </h4>
		         </div>
		         <div class="modal-body">
		         <form class="form-horizontal" action="/topic/category" method="post">
				    	<fieldset>
					    	<div class="control-group">
	                              <!-- Text input-->
	                              <label class="control-label col-lg-4" for="categoryName">行家分类名称</label>                              
	                              <div class="controls col-lg-5">	
	                              	<input type="text" id="categoryName" name="categoryName" class="form-control ng-pristine ng-untouched ng-valid">	                              
	                              </div>
	                              <div style="clear: both;"></div>
	                        </div>
	                       <div class="control-group">	                              
	                              <label class="control-label col-lg-4" for=""></label>                              
	                              <div class="controls col-lg-5">	
	                              	<input type="text" id="modal-categoryId" class="form-control" style="visibility:hidden" >	                              
	                              </div>
	                              <div style="clear: both;"></div>
	                        </div>
	                        <div class="control-group">
	                              <!-- Text input-->
	                              <label class="control-label col-lg-4" for="">行家分类图片</label>                              
	                              <div class="controls col-lg-8">		                              	
										<input id="mainpic" data-url="/publicImageUpload" type="file" name="files" />	                              
	                              </div>
	                              <div style="clear: both;"></div>
	                        </div>
	                        <div class="control-group">
	                              <!-- Text input-->
	                              <label class="control-label col-lg-4" for="">图片预览</label>                              
	                              <div class="controls col-lg-5">	
	                              	<div id="imageUpload" class="imgbox">
										<!-- ngRepeat: img in imageList -->
										<img alt="pic" style="display:none;"  id="kolCategoryImg" src="" />
										<input type="text" id="fileName" name="categoryProfileKey" style="visibility:hidden" />
									</div>	                              
	                              </div>
	                              <div style="clear: both;"></div>
	                        </div>
	                   	</fieldset>
	               </form>
		           <!--  <table class="bops-pub-dialog-table">
							<tbody><tr>
								<th>行家分类名</th>
								<td>
									<input type="text" id="categoryName" name="categoryName" class="ng-pristine ng-untouched ng-valid">
								</td>
							</tr>
							<tr>						
								<td>
									<input type="text" id="modal-categoryId"  style="visibility:hidden" >
								</td>
							</tr>
							<tr>
								<th style="vertical-align:top">行家分类图片</th>
								<td>
									<div id="imageUpload">
										ngRepeat: img in imageList
										<img alt="pic" style="width: 37%;display:none;"  id="kolCategoryImg" src="" />
										<input type="text" id="fileName" name="categoryProfileKey" style="visibility:hidden" />
									</div>
									<input id="mainpic" data-url="/publicImageUpload" type="file" name="files" />
		
				                    
								</td>
							</tr>
						</tbody></table> -->
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="button button-capitalize button-rounded button-primary button-small" data-dismiss="modal">取消 </button>
		            <button type="button" onclick="submit()"  class="button button-capitalize button-rounded button-primary button-small">
		               提交
		            </button>
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
         <!-- 上传文件9 js -->
        <script src="/resources/js/jquery_upload/vendor/jquery.ui.widget.js"></script>
        <script src="/resources/js/jquery_upload/jquery.iframe-transport.js"></script>
        <script src="/resources/js/jquery_upload/jquery.fileupload.js"></script>
        
        
        <script type="text/javascript">
        
           $('#light-pagination').pagination({
        		pages: "${page.pageCount}",
        		cssStyle: 'light-theme',
        		currentPage: "${page.page}",
				onPageClick: function(pageNumber, event) {
					window.location.href='page?page='+pageNumber;
				}
        	});

            //删除方法
            function deletekolcategory(kolCategoryId){
         	      $.messager.confirm("删除权限", "您确定要删除此分类!", function() { 
         	    	  $.ajax({
      	    		     type: 'POST',
      	    		     url: '/expertclassify/delete/'+kolCategoryId,
      	    		     dataType: 'json',
      	    		    success : function(data) {
      	    		    	window.location.reload();
      	   			     },
      	   			    error : function() {
      	   			    alert(XMLResponse.responseText);
      	   			}

      	    		   });
         	        });
               
         	      }

            function  openmodel(){
            	$('#kolCategoryImg').attr("src","");
            	$("#categoryName").val("");
            	$("#modal-categoryId").val("");
            	$("#kolCategoryImg").css('display', 'none');
            	$('#myModal').modal('show')          
       	      }  
            function  update(categoryId,categoryProfileKey,categoryName){
            	$('#kolCategoryImg').attr("src","${imgurl}"+categoryProfileKey);
            	$("#categoryName").val(categoryName);
            	$("#fileName").val(categoryProfileKey);
            	$("#modal-categoryId").val(categoryId);
            	$("#kolCategoryImg").css('display', 'block');
            	$('#myModal').modal('show');
       	      } 
            
            function  submit(){
            	       var catId = $("#modal-categoryId").val();
            		   var catName = $("#categoryName").val();
            		   var picKey = $("#fileName").val();

            		   if(catName == ""  ){
            		     alert("分类名称不能为空");
            		     return false;
            		   }
            		   if(picKey == ""  ){
            		    alert("分类图片不能为空");
            		     return false;
            		   }
            		   if(catId == ""){
            		   $.ajax({
        	    		     type: 'POST',
        	    		     url: '/expertclassify/?categoryName='+catName+"&categoryProfileKey="+picKey,
        	    		     dataType: 'json',
        	    		    success : function(data) {
        	    		    	alert(data.msg);       	    		    
        	    		        if(data.code==200){
        	    		        	window.location.reload();
        	    		        }
        	   			     },
        	   			    error : function() {
        	   			    
        	   			}

        	    		   });
            		   }
            		   if(catId != ""){
                		   $.ajax({
            	    		     type: 'POST',
            	    		     url: '/expertclassify/update?categoryName='+catName+"&categoryProfileKey="+picKey+"&categoryId="+catId,
            	    		     dataType: 'json',
            	    		    success : function(data) {
            	    		    	alert(data.msg);       	    		    
            	    		        if(data.code==200){
            	    		        	window.location.reload();
            	    		        }
            	   			     },
            	   			    error : function() {
            	   			    
            	   			}

            	    		   });
                		   }
       	      }   
            //
            $("#mainpic").fileupload({
                // formData:{param1:"p1",param2:"p2"},//如果需要额外添加参数可以在这里添加
                done:function(e,result){
                    //done方法就是上传完毕的回调函数，其他回调函数可以自行查看api
                    //注意result要和jquery的ajax的data参数区分，这个对象包含了整个请求信息
                    //返回的数据在result.result中，假设我们服务器返回了一个json对象
                    console.log(JSON.stringify(result.result));
                 //   alert(JSON.stringify(result.result));
                    
                    var jsonObj=$.parseJSON(JSON.stringify(result.result));
                  //  alert(JSON.stringify(jsonObj.data.fileNames[0]));
    		    	$('#fileName').val(jsonObj.data.fileNames[0]);
    		    	$('#kolCategoryImg').attr("src",jsonObj.data.imgurl+jsonObj.data.fileNames[0]);
    		    	$("#kolCategoryImg").css('display', 'block');
                }
            })

         </script>
    </body>
</html>