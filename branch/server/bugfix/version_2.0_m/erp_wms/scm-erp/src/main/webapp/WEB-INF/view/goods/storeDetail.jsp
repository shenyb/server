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
<link rel="stylesheet" href="/resources/css/profile-table.css">
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
								<h3 class="m-b-less">添加库存</h3>
								<!--<span class="sub-title">Welcome to Static Table</span>-->
								<div class="state-information">
									<ol class="breadcrumb m-b-less bg-less">
										<li><a href="#">主页</a></li>
										<li><a href="#">采销管理</a></li>
										<li class="active">库存</li>
									</ol>
								</div>
							</div>
							<div class="wrapper">
								<div class="row">
									<div class="col-sm-12">
									<form id="goodsStoreDetail_form" class="form-horizontal">
											<div class="info">
									            <table class="table table-bordered table-hover table-condensed table-responsive">
									            <header class="panel-heading">
													库存 
													<div class="back">
														<a href="javascript:void(0);" onclick="toBackPage();" class="button button-primary button-raised button-small">返回</a>
													</div>
												</header>
									                <!--<tbody>-->							                
									                    <tr>
									                        <td>商品编号</td>
									                        <td>
									                        	${goods.goodsCode}
															</td>
									                        <td>国际条形码</td>
									                        <td>${goods.goodsBarcode}</td>                       
									                    </tr>
									                   									                									                    
									                    <tr>									                        
									                     	<td>商品名称</td>
									                        <td>${goods.goodsName}</td>   
									                        <td>商品原价</td>
									                        <td>${goods.onsalePrice}</td>                     
									                    </tr>

									                    
									                    <tr>
									                       <td>商品折扣价</td>
									                        <td>${goods.discountPrice}</td>
									                                             
									                    </tr>
									                    
									                   
									                    
									                    	<tr>
									                    	<td>
									                    		场景图
									                    	</td>
									                    	<td colspan="3">
									                    		<img height="60px" alt="商品场景图" src="${picAddress}${goods.scenePicKey}">
									                    	</td>
									                    </tr>
									                
										         <!-- <tr class="table-title">
										         	<td colspan="4">
										         	库存
										         	</td>												
												</tr> -->
												<tr>
								                    <td>总库存</td>
								                    <td>
								                    	${goods.allStore}
								                    </td>
								                     <td>可销售数量</td>
								                    <td> 									                    	
								                    	${goods.allowSellCount}
								                	</td>									                   
								                </tr>
									                <tr>
									                     <td>占用库存</td>
									                    <td>
									                    	${goods.lockedCount}
									                    </td>
									                     <td>残品库存</td>
									                    <td> 
									                    	${goods.defectiveStore}
									                	</td>									                   
									                </tr>	
									                 <tr>
									                    
									                     <td>添加类型</td>
									                    <td> 
									                    	<select required id="storeType" name="storeType" class="form-control">
																<option value="">-请选择-</option>
																<option value="GOOD">正品库存</option>
																<option value="BAD">残品库存</option>
															</select>
									                	</td>	
									                	 <td>已销售数量</td>
									                    <td>
									                    	${goods.soldCount}
									                    </td>								                   
									                </tr>	
									                <tr>
									                     <td>添加数量</td>
									                    <td>
									                    	<input type="text" id="nowStoreCount" name="nowStoreCount" value="" required number="number" class="form-control"> 
															<input type="hidden" name="goodsId" value="${goods.goodsId}" class="form-control">
									                    </td>
									                     									                    									                   
									                </tr>								               
									                </tbody>
									            </table>
									            <div class="form-group btnlist">
													<!-- <button id="goodsEditSave" class="btn btn-info" >提交</button> -->
													<a class="button button-primary button-raised button-small pull-right" href="javascript:void(0);"
														id="a_submitGoodsStoreEditSave">提交</a>
												</div>
									        </div>																							
										</form>
									
									<%-- <!-- 1111111111111 -->
										<section class="panel">
											<header class="panel-heading">
												库存
												<div class="back">
												 <a href="/store/page?page=${page}" class="button button-primary button-raised button-small">返回</a>
												 </div>
											</header>
											<div class="panel-body">
												<div class="form-horizontal">
													<div class="form-group">
														<label for="" class="col-lg-2">商品编号</label>
														<div class="col-lg-9">${goods.goodsCode}</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">国际条形码</label>
														<div class="col-lg-9">${goods.goodsBarcode}</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">商品名称</label>
														<div class="col-lg-9">${goods.goodsName}</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">商品原价</label>
														<div class="col-lg-9">${goods.onsalePrice}</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">商品折扣价</label>
														<div class="col-lg-9">${goods.discountPrice}</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">场景图</label>
														<div class="col-lg-9">
															<img class="form-img" alt="商品场景图" src="${picAddress}${goods.scenePicKey}">
														</div>
													</div>
												</div>
											</div>
										</section>
										<section class="panel">
											<header class="panel-heading">库存</header>
											<div class="panel-body">
												<form class="form-horizontal" id="goodsStoreDetail_form">
													<div class="form-group">
														<label for="" class="col-lg-2">总库存</label>
														<div class="col-lg-9">
															${goods.allStore}
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">可销售数量</label>
														<div class="col-lg-9">${goods.allowSellCount}</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">占用库存</label>
														<div class="col-lg-9">
															${goods.lockedCount}
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">残品库存</label>
														<div class="col-lg-9">
															${goods.defectiveStore}
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">已销售数量</label>
														<div class="col-lg-9">${goods.soldCount}</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">添加类型</label>
														<div class="col-lg-9">
															<select required name="storeType" class="form-control">
																<option value="">-请选择-</option>
																<option value="GOOD">正品库存</option>
																<option value="BAD">残品库存</option>
															</select>
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">添加数量</label>
														<div class="col-lg-9">
															<input type="text" name="nowStoreCount" value="" required number="number" class="form-control"> 
															<input type="hidden" name="goodsId" value="${goods.goodsId}" class="form-control">
														</div>
													</div>
													<div class="form-group btnlist">
														<!-- <button id="goodsEditSave" class="btn btn-info" >提交</button> -->
														<a class="button button-primary button-raised button-small pull-right" href="javascript:void(0);"
															id="a_submitGoodsStoreEditSave">提交</a>
													</div>
												</form>
											</div>
										</section>
									<!-- 11111111 --> --%>
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
	<!-- 侧面导航栏组件 js -->
	<script src="/resources/js/sidebar.js"></script>
	<script src="/resources/js/jquery.pagination.js"></script>
	<script type="text/javascript">
		
	</script>
	<script type="text/javascript">
	
	function toBackPage(){
		window.location.href=document.referrer;
	}
	$("#a_submitGoodsStoreEditSave").click(function(){
		/* alert($("#goodsEditProfile").serialize()); */
		if(!$("#goodsStoreDetail_form").validate().form()){
			return ;
		}
		var nowStoreCount = ${goods.allowSellCount};
		var defectiveStore = ${goods.defectiveStore};
		if($("#storeType").val() == "GOOD" &&(nowStoreCount + Number($("#nowStoreCount").val()) < 0)){
			alert("添加的负库存不允许大于当前正品库存");
			return ;
		}
		if($("#storeType").val() == "BAD" && (defectiveStore + Number($("#nowStoreCount").val()) < 0)){
			alert("添加的负库存不允许大于当前残品品库存");
			return ;
		}
		$.ajax({
			type : "post",
			url : "/store/addNewStore",
			data: $("#goodsStoreDetail_form").serialize(),
			cache : false,
			dataType : 'json',
			success : function(data) {
				if(data.code == 200){
					/* window.location.href = "/store/page?page=${page}"; */
					window.location.href=document.referrer;
				}
				else{
					alert("code: " + data.code + "\n" + data.msg);
				}
			}
		});
	});
	</script>

</body>
</html>