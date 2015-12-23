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

<title>OPS管理系统</title>

<!-- 主体部分样式表 -->
<link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/bootstrap/button.css">
<link rel="stylesheet" href="/resources/css/font-awesome.min.css">
<link rel="stylesheet" href="/resources/css/layout.css">
<link rel="stylesheet" href="/resources/css/ops.css">
<link rel="stylesheet" href="/resources/css/sidebar.css">
<link rel="stylesheet" href="/resources/css/content-header.css">
<!-- <link rel="stylesheet" href="/resources/css/dat apage.css">
<link rel="stylesheet" href="/resources/css/fo rm.css"> -->
<link rel="stylesheet" href="/resources/css/pagination.css">
<link rel="stylesheet" href="/resources/css/profile-table.css">
<link rel="stylesheet" href="/resources/css/modal.css" >
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
								<!--<span class="sub-title">Welcome to Static Table</span>-->
								<div class="state-information">
									<ol class="breadcrumb m-b-less bg-less">
										<li><a href="#">主页</a></li>
										<li><a href="#">运营管理</a></li>
										<li class="active">分类编辑</li>
									</ol>
								</div>
							</div>
							<div class="info">
					            <div class="info-top">
					                <span>分类概要 </span>
					                <div class="back">
					                    <a href="javascript:void(0)" onclick="javascript :history.back(-1);" class="button button-rounded button-tiny">返回</a>	
					                 			                   
					                </div>
					            </div>
				           		<form id="form" class="form-horizontal">
										<div class="panel-body">
											<div class="form-group">
												<label for="" class="col-lg-2">商品分类</label>
												<div class="col-lg-9">
													<select  class="form-control" name="categoryLevel" id="categoryLevel"
														onChange="changeCategory()">
														<option value="1">一级分类</option>
														<option value="2">二级分类</option>
													</select>
												</div>
											</div>
											<div class="form-group" style="display: none;" id="one">
												<label for="" class="col-lg-2">一级分类</label>
												<div class="col-lg-9">
													<select  class="form-control" name="levelOne" id="levelOne" onChange="changeLevel()">
														<option value="">请选择</option>
													</select>
												</div>
											</div>
											<input type="hidden" value="" name="parentId" id="parentId">
											<div class="form-group" style="display: none;" id="two">
												<label for="" class="col-lg-2">二级分类</label>
												<div class="col-lg-9">
													<select class="form-control" name="levelTwo" id="levelTwo">
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
											<div class="form-group large-group">
												<label for="" class="col-lg-2">分类权值</label>
												<div class="col-lg-9">
													<input class="form-control" type="text" id="categoryScore" name="categoryScore" value="" required>
												</div>
											</div>
											<div class="form-group">
					    							<label for="" class="col-lg-2">上传图片</label>
					    							<div class="col-lg-9">
					    								<input id="mainpic" data-url="/publicImageUpload" type="file" name="files">
					    								<input type="hidden" name="categoryPicKey" id="addCategoryPic" value="">
					    							</div>
					    						</div>	
											<div class="form-group" id="imgDiv" style="display: none;">
						    					<label for="" class="col-lg-2">图片预览</label>
												<div class="col-lg-9">
													<div>
														<img id="categoryImg"  src=""><button type="button" class="close" style="margin-right:65%;" onclick="removeImg()" >×</button>
													</div>
												</div>
											</div>					
					    						
					    					
											<div class="form-group btnlist">
												<a class="button button-primary button-raised button-small pull-right" href="javascript:void(0);" id="save">提交</a>
											</div>
									</div>																			
								</form>
							 </div>
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
	<!-- 文件上传 -->
	<script src="/resources/js/jquery_upload/vendor/jquery.ui.widget.js"></script>
    <script src="/resources/js/jquery_upload/jquery.iframe-transport.js"></script>
    <script src="/resources/js/jquery_upload/jquery.fileupload.js"></script>
	<script type="text/javascript">
	 //正整数
     function isPInt(str) {
        var g = /^[1-9]*[1-9][0-9]*$/;
        return g.test(str);
    }
	 
    $("#save").click(function () {
    	var categoryLevel=$("#categoryLevel").val();
    	var categoryScore=$("#categoryScore").val();
		if(categoryLevel=="2"){
			var parentId=$("#levelOne").val();
			if("" == parentId || null == parentId){
				alert("请选择上级分类");
				return false;
			}
			document.getElementById("parentId").value=parentId;
		}
		 if(categoryScore!=null&&categoryScore!=""){
			if(!isPInt(categoryScore)){
				alert('分类权值请填正整数');
				return false;
			}
			
		 }
        $.ajax({
            type: "POST",
            url: "/indexCategory/addCategory",
            data: $("#form").serialize(),
            cache: false,
            dataType: 'json',
            success: function (data) {
                if (data.code == 200) {
                    window.location.href = "/indexCategory/page";
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
        	var url = "/indexCategory/getNextLevel?categoryLevel=" + parentLevel;
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
              $('#categoryImg').attr("src",url+fileName);
              $('#addCategoryPic').val(fileName);
              $('#imgDiv').show();
          }
      })
      
      function removeImg(position){
        	  $('#addOpsPic').val('');
              $('#imgDiv').hide();
        	  
        	  
          }
	</script>
</body>
</html>