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
								<h3 class="m-b-less">品牌新增</h3>
								<!--<span class="sub-title">Welcome to Static Table</span>-->
								<div class="state-information">
									<ol class="breadcrumb m-b-less bg-less">
										<li><a href="#">主页</a></li>
										<li><a href="#">采销管理</a></li>
										<li class="active">品牌编辑</li>
									</ol>
								</div>
							</div>
							<div class="wrapper">
								<div class="row">
									<div class="col-sm-12">
										<form id="form" class="form-horizontal">
											<section class="panel">
												<header class="panel-heading">
													品牌概要 
													<div class="back">
														<a href="javascript :;" onClick="javascript :history.back(-1);" class="button button-primary button-raised button-small pull-right">返回</a>
													</div>
												</header>
												<div class="panel-body">
													<div class="form-group large-group">
														<label for="brandName" class="col-lg-2">*品牌名称</label>
														<div class="col-lg-9">
															<input type="text" id="brandName" class="form-control" maxlength="50" name="brandName" value="" required>
															<span>最多50个汉字，且不可重复</span>
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">*品牌归属地</label>
														<div class="col-lg-9">
															<select name="brandArea" class="form-control" required>
																<option value="">请选择</option>
																<option value="中国香港">中国香港</option>
																<option value="日本">日本</option>
																<option value="中国澳门">中国澳门</option>
																<option value="马来西亚">马来西亚</option>
																<option value="尼泊尔">尼泊尔</option>
																<option value="新加坡">新加坡</option>
																<option value="韩国">韩国</option>
																<option value="泰国">泰国</option>
																<option value="越南">越南</option>
																<option value="中国">中国</option>
																<option value="台澎金马关税区">台澎金马关税区</option>
																<option value="比利时">比利时</option>
																<option value="丹麦">丹麦</option>
																<option value="英国">英国</option>
																<option value="德国">德国</option>
																<option value="法国">法国</option>
																<option value="爱尔兰">爱尔兰</option>
																<option value="荷兰">荷兰</option>
																<option value="希腊">希腊</option>
																<option value="葡萄牙">葡萄牙</option>
																<option value="西班牙">西班牙</option>
																<option value="奥地利">奥地利</option>
																<option value="芬兰">芬兰</option>
																<option value="挪威">挪威</option>
																<option value="波兰">波兰</option>
																<option value="瑞典">瑞典</option>
																<option value="瑞士">瑞士</option>
																<option value="巴西">巴西</option>
																<option value="墨西哥">墨西哥</option>
																<option value="加拿大">加拿大</option>
																<option value="美国">美国</option>
																<option value="澳大利亚">澳大利亚</option>
																<option value="新西兰">新西兰</option>
																<option value="国(地)别不详的">国(地)别不详的</option>
																<option value="联合国及机构和国际组织">联合国及机构和国际组织</option>
																<option value="中性包装原产级别">中性包装原产级别</option>
																<option value="印度">印度</option>
																<option value="南非">南非</option>
																<option value="拉脱维亚">拉脱维亚</option>
																<option value="其他">其他</option>
															</select>
														</div>
													</div>
													<div class="form-group">
														<label for="brandWebsite" class="col-lg-2">官网网站</label>
														<div class="col-lg-9">
															<input class="form-control" type="text" id="brandWebsite" name="brandWebsite" value="" required>
														</div>
													</div>
													<div class="form-group">
														<label for="afterPhone" class="col-lg-2">售货服务电话</label>
														<div class="col-lg-9">
															<input class="form-control" type="text" id="afterPhone" name="afterPhone" value="" required>
														</div>
													</div>

													<div class="form-group">
														<label for="" class="col-lg-2">品牌介绍文本</label>
														<div class="col-lg-9">
															<textarea name="brandDescriptionText" class="form-control" rows="3"></textarea>
															<span>最多支持100个汉字</span>
														</div>
													</div>
													<div class="form-group">
														<label for="fileName" class="col-lg-2">品牌介绍图片</label>

														<div id="imageUpload" class="col-lg-9">
															<!-- ngRepeat: img in imageList -->
															<img alt="pic" style="display: none;" id="brandPicKey" src="" />
															<a href="javascript:;" id="remove" style="display: none">删除</a>
																 <input type="text" class="form-control" id="fileName" name="brandPicKey" style="visibility: hidden" /> 
																 <input id="mainpic"  data-url="/publicImageUpload" type="file" name="files" />
															<span>最大支持450kb，尺寸：宽690PX，高度不限制</span>
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
		 if($("#brandPicKey").attr("src") != null && $("#brandPicKey").attr("src") != ''){
			 var img = null;  
		 	    img = document.getElementById("brandPicKey"); 
		 	    var imgwidth = img.offsetWidth;  
		 	    var imgheight = img.offsetHeight;   
		 	    if(imgwidth != 690) {  
		 	        alert("图的宽度应该是690px");  
		 	        return false;  
		 	    }   
		 }
		
     	
         var data = $(".form-horizontal").serialize();
         $.ajax({
             type: "POST",
             url: "/brand/add",
             data: data,
             cache: false,
             dataType: 'json',
             success: function (data) {
                 if (data.code == 200) {
                	 alert(data.msg);
                     window.location.href = "/brand/page";
                 } else {
                     alert("code: " + data.code + "\n" + data.msg);
                 }
             },
             error: function(XMLHttpRequest, textStatus, errorThrown) {
                 alert(errorThrown);
             }
         });
     });
   $("#remove").click(function(){
	    $('#fileName').val('');
   		$('#brandPicKey').attr("src","");
   		$("#brandPicKey").css('display', 'none');
   		$("#remove").css('display', 'none');
   })
    
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
	    	$('#brandPicKey').attr("src",jsonObj.data.imgurl+jsonObj.data.fileNames[0]);
	    	$("#brandPicKey").css('display', 'block');
	    	$("#remove").css('display', 'block');
        }
    })
	</script>
</body>
</html>