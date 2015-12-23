<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<title>ERP管理系统</title>

<!-- 主体部分样式表 -->
<link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/bootstrap/button.css">
<link rel="stylesheet" href="/resources/css/font-awesome.min.css">
<link rel="stylesheet" href="/resources/css/layout.css">
<link rel="stylesheet" href="/resources/css/ops.css">
<link rel="stylesheet" href="/resources/css/sidebar.css">
<link rel="stylesheet" href="/resources/css/content-header.css">
<link rel="stylesheet" href="/resources/css/datapage.css">
<link rel="stylesheet" href="/resources/css/form.css">
<link rel="stylesheet" href="/resources/css/pagination.css">
<!-- 分页插件 css 样式 -->
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
					<div class="form-page-wrap">
						<div class="data-page-inner">
							<div class="page-head">
								<h3 class="m-b-less">分类新增</h3>
								<!--<span class="sub-title">Welcome to Static Table</span>-->
								<div class="state-information">
									<ol class="breadcrumb m-b-less bg-less">
										<li><a href="#">主页</a></li>
										<li><a href="#">采销管理</a></li>
										<li class="active">分类编辑</li>
									</ol>
								</div>
							</div>
							<div class="wrapper">
								<div class="row">
									<div class="col-sm-12">
										<form id="form" class="form-horizontal">
											<section class="panel">
												<header class="panel-heading">
													分类概要 
													<div class="back">
													
													<a href="/categories/page?page=1" class="button button-primary button-raised button-small pull-right">返回</a>
													</div>
												</header>
												<div class="panel-body">
													<div class="form-group">
														<label for="" class="col-lg-2">商品分类</label>
														<div class="col-lg-9">
															<select class="form-control"
																name="categoryLevel" id="categoryLevel"
																onChange="changeCategory()">
																<option value="1">一级分类</option>
																<option value="2">二级分类</option>
																<option value="3">三级分类</option>
															</select>
														</div>
													</div>
													<div class="form-group" style="display: none;" id="one">
														<label for="" class="col-lg-2">一级分类</label>
														<div class="col-lg-9">
															<select class="form-control"
																name="levelOne" id="levelOne" onChange="changeLevel()">
																<option value="">请选择</option>
															</select>
														</div>
													</div>
													<input type="hidden" value="" name="parentId" id="parentId">
													<div class="form-group" style="display: none;" id="two">
														<label for="" class="col-lg-2">二级分类</label>
														<div class="col-lg-9">
															<select class="form-control"
																name="levelTwo" id="levelTwo">
																<option value="">请选择</option>
															</select>
														</div>
													</div>
													<div class="form-group large-group">
														<label for="" class="col-lg-2">分类名称</label>
														<div class="col-lg-9">
															<input class="form-control" type="text" id="categoryName" name="categoryName" value="" required>
														</div>
													</div>
													<div class="form-group btnlist">
														<a class="button button-primary button-raised button-small pull-right" href="javascript:void(0);"
															id="save">提交</a>
													</div>
												</div>									
											</section>
										</form>
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
	<!-- 在 body的最底部引入js文件且一定保持 jquery 在 bootstrap 之前引入 -->
	<script src="/resources/js/jquery/jquery-2.1.4.min.js"></script>
	<script src="/resources/js/bootstrap/bootstrap.min.js"></script>
	<script src="/resources/js/jquery/jquery.validate.js"></script>
	<script src="/resources/js/jquery/jquery-validate-message.js"></script>
	<script src="/resources/js/jquery_upload/vendor/jquery.ui.widget.js"></script>
	<script src="/resources/js/jquery_upload/jquery.iframe-transport.js"></script>
	<script src="/resources/js/jquery_upload/jquery.fileupload.js"></script>
	<script src="/resources/js/goods/goods.js"></script>
	<!-- 侧面导航栏组件 js -->
	<script src="/resources/js/sidebar.js"></script>
	<script src="/resources/js/jquery.pagination.js"></script>
	<script type="text/javascript">
	
    $("#save").click(function () {
    	var categoryLevel=$("#categoryLevel").val();
		if(categoryLevel=="2"){
			var parentId=$("#levelOne").val();
			if("" == parentId || null == parentId){
				alert("请选择上级分类");
				return false;
			}
			document.getElementById("parentId").value=parentId;
		}
		if(categoryLevel=="3"){
			var parentId=$("#levelTwo").val();
			if("" == parentId || null == parentId){
				alert("请选择上级分类");
				return false;
			}
			document.getElementById("parentId").value=parentId;
		}
        $.ajax({
            type: "POST",
            url: "/categories/addCategory",
            data: $("#form").serialize(),
            cache: false,
            dataType: 'json',
            success: function (data) {
                if (data.code == 200) {
                    window.location.href = "/categories/page";
                } else {
                    alert("code: " + data.code + "\n" + data.msg);
                }
            }
        });
    });
    
    function changeCategory(){
    	var categoryLevel=$("#categoryLevel").val();
    	if(categoryLevel=="1"){
    		document.getElementById("one").style.display="none";
    		document.getElementById("two").style.display="none";
    	}
    	if(categoryLevel=="2"){
    		document.getElementById("one").style.display="block";
    		document.getElementById("two").style.display="none";
    		$("#levelOne").empty();
    		var parentLevel="1";
        	var url = "/categories/getNextLevel?categoryLevel=" + parentLevel;
        		$.getJSON(url,function(result){	
        			 $.each(result.data.list, function(key, val) {
        				 $("#levelOne").append(
        						'<option value="' + val.categoryId + '" >' + val.categoryName + '</option>');
        			}
        		);
        	}); 
    	}
    	if(categoryLevel=="3"){
    		document.getElementById("one").style.display="block";
    		document.getElementById("two").style.display="block";
    		$("#levelOne").empty();
    		var parentLevel="1";
        	var url = "/categories/getNextLevel?categoryLevel=" + parentLevel;
        		$.getJSON(url,function(result){	
        			 $.each(result.data.list, function(key, val) {
        				 $("#levelOne").append(
        						'<option value="' + val.categoryId + '" >' + val.categoryName + '</option>');
        			}
        		);
        			 var levelTwo=$("#levelTwo").val();
        		     	if(null != levelTwo){
        		     		$("#levelTwo").empty();
        		        	var parentLevel="2";
        		        	var parentId=$("#levelOne").val();
        		        	var url = "/categories/getNextLevel?categoryLevel=" + parentLevel+"&parentId="+parentId;
        		    		$.getJSON(url,function(result){	
        		    			 $.each(result.data.list, function(key, val) {
        		    				 $("#levelTwo").append(
        		    						'<option value="' + val.categoryId + '" >' + val.categoryName + '</option>');
        		    				}
        		    			 );
        		    		}); 
        		     	}	 
        	}); 
    	}
    	
    }
    
     function changeLevel(){
    	var categoryLevel=$("#categoryLevel").val();
     	if(categoryLevel=="3"){
     		$("#levelTwo").empty();
        	var parentLevel="2";
        	var parentId=$("#levelOne").val();
        	var url = "/categories/getNextLevel?categoryLevel=" + parentLevel+"&parentId="+parentId;
    		$.getJSON(url,function(result){	
    			 $.each(result.data.list, function(key, val) {
    				 $("#levelTwo").append(
    						'<option value="' + val.categoryId + '" >' + val.categoryName + '</option>');
    				}
    			 );
    		}); 
     	}
    
    }
	</script>
</body>
</html>