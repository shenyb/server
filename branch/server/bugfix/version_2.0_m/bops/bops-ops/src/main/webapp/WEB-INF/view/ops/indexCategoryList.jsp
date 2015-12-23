<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!-- IE=edge告诉IE使用最新的引擎渲染网页 -->
<!-- chrome=1则可以激活Chrome Frame , Chrome Frame可以让旧版IE浏览器使用Chrome的WebKit渲染引擎处理网页 -->
<meta
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0"
	name="viewport">
<!-- 强制让文档的宽度与设备的宽度保持1:1，并且文档最大的宽度比例是1.0，且不允许用户点击屏幕放大浏览 -->

<meta content="yes" name="apple-mobile-web-app-capable" />
<!-- iphone设备中的safari私有meta标签，它表示：允许全屏模式浏览 -->

<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<!-- iphone的私有标签，它指定的iphone中safari顶端的状态条的样式 -->

<meta content="telephone=no" name="format-detection" />
<!-- 告诉设备忽略将页面中的数字识别为电话号码 -->

<meta content="email=no" name="format-detection" />
<!-- android设备忽略将页面中的email识别为电子邮箱 -->

<link rel="shortcut icon" href="/resources/img/common/favicon.ico"
	type="image/x-icon" />

<title>OPS管理系统</title>

<!-- 主体部分样式表 -->
<link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/bootstrap/button.css">
<link rel="stylesheet" href="/resources/css/font-awesome.min.css">
<link rel="stylesheet" href="/resources/css/layout.css">
<link rel="stylesheet" href="/resources/css/ops.css">
<link rel="stylesheet" href="/resources/css/sidebar.css">
<link rel="stylesheet" href="/resources/css/content-header.css">
<!-- <link rel="stylesheet" href="/resources/css/data page.css"> -->
<link rel="stylesheet" href="/resources/css/category.css">
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
			<%@ include file="/resources/html/sidebar.jsp"%>
		</div>
		<div class="layout-right">
			<div class="layout-right-inner">
				<div class="layout-right-top">
					<%@ include file="/resources/html/content_header.jsp"%>
				</div>
				<div class="layout-right-bottom">
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
				                    <li class="active">商品分类</li>
				                </ol>
				            </div>
				        </div>				
				        <div class="info">
				            <div class="info-top">
				                <span>商品分类列表</span>
				                <div class="back">
				                    <a class="button button-primary button-raised button-small" href="/indexCategory/toAddCategory">新增</a>			                   
				                </div>
				            </div>
				            <div class="search-form">
				            </div>
				            <div class="nava">
							<div class="panel-group" id="accordion">
								<c:forEach items="${levelList}" var="list"
									varStatus="status">
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title">
												<a data-toggle="collapse" data-parent="#accordion"
													id="${list.categoryId}" href="#"
													onClick="one('a${list.categoryId}')">
													${list.categoryName}
												</a>
												<div class="editor">
													<a href="/indexCategory/toEditCategory?categoryId=${list.categoryId}">
														<i class="fa fa-pencil-square-o"></i>
													</a>
													 <a href="/indexCategory/categoryDetial?categoryId=${list.categoryId}">
													 	<i class="fa fa-eye"></i>
													</a>
													 <a href="javascript:;" onclick="deleteOpsGoods('${list.categoryId}','${list.categoryLevel}')">
													 	<i class="fa fa-minus-square"></i>
													</a>
												</div>
											</h4>
										</div>
										<div class="panel-collapse collapse" id="a${list.categoryId}">
											<c:forEach items="${list.levelTwoList}" var="list1"
												varStatus="status">
												<div class="panel-body ">
													<a href="javascript:;" class="pclick" id="" onClick="one('b${list1.categoryId}')">
														<span>${list1.categoryName} &nbsp;<img width="20px;" alt="ICON" src="${imgUrl }${list1.categoryPicKey}"></span>
													</a>
													<div class="editor">
														<a href="/indexCategory/toEditCategory?categoryId=${list1.categoryId}">
														<i class="fa fa-pencil-square-o"></i>
														</a>
														 <a href="/indexCategory/categoryDetial?categoryId=${list1.categoryId}">
														 <i class="fa fa-eye"></i>
														</a>
														 <a href="javascript:;" onclick="deleteOpsGoods('${list1.categoryId}','${list1.categoryLevel}')">
													 	<i class="fa fa-minus-square"></i>
													     </a>
													</div>
												</div>
											</c:forEach>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>										
				     </div>
				     </div>
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


	<div class="modal fade" id="editGoodsModalDiv" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog"></div>
	</div>

	<!-- 在 body的最底部引入js文件且一定保持 jquery 在 bootstrap 之前引入 -->
	<script src="/resources/js/jquery/jquery-2.1.4.min.js"></script>
	<script src="/resources/js/bootstrap/bootstrap.min.js"></script>
	<script src="/resources/js/jquery/jquery.bootstrap.min.js"></script>
	<!-- 侧面导航栏组件 js -->
	<script src="/resources/js/sidebar.js"></script>
	<script src="/resources/js/jquery.pagination.js"></script>

	<script type="text/javascript">
	function one(id){
		$("#"+id).slideToggle();
	}
	$(".lists li").click(function(){
        var index=$(".lists li").index(this);
        $(".lists li").css({background:"#fff"})
        $($(".lists li")[index]).css({background:"#CBCBCB"})
    })
    
               //删除单条数据
           function deleteOpsGoods(categoryId,level){
        	   $.messager.confirm("删除分类", "您确定要删除此分类!", function() { 
      	    	  $.ajax({
   	    		     type: 'POST',
   	    		     url: '/indexCategory/deleteCategory',
   	    		     data:{'categoryId':categoryId,'level':level},
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
	</script>
</body>
</html>