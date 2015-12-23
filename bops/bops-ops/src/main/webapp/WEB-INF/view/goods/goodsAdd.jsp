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

<title>运营管理系统</title>

<!-- 主体部分样式表 -->
<link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css">
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
								<h3 class="m-b-less">商品新增</h3>
								<!--<span class="sub-title">Welcome to Static Table</span>-->
								<div class="state-information">
									<ol class="breadcrumb m-b-less bg-less">
										<li><a href="#">主页</a></li>
										<li><a href="#">商品管理</a></li>
										<li class="active">商品添加</li>
									</ol>
								</div>
							</div>
							<div class="wrapper">
								<div class="row">
									<div class="col-sm-12">
									<form id="goodsEditProfile" class="form-horizontal">
										<section class="panel">
											<header class="panel-heading">商品概要 <a href="/goods/page?page=1" class="btn btn-info pull-right">返回</a></header>
											<div class="panel-body">
												<div class="form-horizontal" >
													<div class="form-group">
														<label for="" class="col-lg-2">商品编号</label>
														<div class="col-lg-9">
															<input type="text" name="goodsCode" value="" />
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">国际条形码</label>
														<div class="col-lg-9">
															<input type="text" value="" name="goodsBarcode">
														</div>
													</div>
													<!-- <div class="form-group">
														<label for="" class="col-lg-2">是否为保税仓</label>
														<div class="col-lg-9">
															<select style="width:200px;" class="form-control" name="isGlobal" id="isGlobal">
																<option value="FALSE">否</option>
																<option value="TRUE">是</option>
															</select>
														</div>
													</div> -->
													<div class="form-group">
														<label for="" class="col-lg-2">商品分类</label>
														<div class="col-lg-9">
															<select style="width:200px;" class="form-control" name="goodsCategoryId" id="goodsCategoryId">
																<c:forEach items="${goodsCategoryList}" var="goodsCategory" varStatus="status">
																	<option value="${goodsCategory.categoryId}">${goodsCategory.categoryName}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">仓库类型</label>
														<div class="col-lg-9">
															<select style="width:200px;" class="form-control" name="warehouseType" id="warehouseType">
																<!-- <option value="">仓库类型</option> -->
								    							<option value="SELF_WAREHOUSE">自营仓</option>
								    							<option value="TAX_WAREHOUSE">保税仓</option>
								    							<option value="OVERSEA_WAREHOUSE">海外直邮</option>
															</select>
														</div>
													</div>
													<div style="display:none;" id="huanguanGuojian">
													<div class="form-group">
														<label for="" class="col-lg-2">海关计量单位</label>
														<div class="col-lg-9">
															<select name="haiguanCount" class="form-control" style="width:200px;"  ng-model="addData.haiguanCount" >
																<option value="006">套</option>
																<option value="007">个</option>
																<option value="008">只</option>
																<option value="010">张</option>
																<option value="011">件</option>
																<option value="012">支</option>
																<option value="014">根</option>
																<option value="015">条</option>
																<option value="017">块</option>
																<option value="018">卷</option>
																<option value="019">副</option>
																<option value="020">片</option>
																<option value="021">组</option>
																<option value="022">份</option>
																<option value="025">双</option>
																<option value="026">对</option>
																<option value="035">千克</option>
																<option value="036">克</option>
																<option value="095">升</option>
																<option value="096">毫升</option>
																<option value="120">箱</option>
																<option value="121">批</option>
																<option value="122">罐</option>
																<option value="123">桶</option>
																<option value="125">包</option>
																<option value="134">枚</option>
																<option value="136">袋</option>
																<option value="139">粒</option>
																<option value="140">盒</option>
																<option value="141">合</option>
																<option value="142">瓶</option>
															</select>
														</div>
													</div>
													<div class="form-group" >
														<label for="" class="col-lg-2">国检计量单位</label>
														<div class="col-lg-9">
															<select name="guojianCount" class="form-control" style="width:200px;" id="" ng-model="addData.guojianCount">
																<option value="006">套</option>
																<option value="007">个</option>
																<option value="008">只</option>
																<option value="010">张</option>
																<option value="011">件</option>
																<option value="012">支</option>
																<option value="014">根</option>
																<option value="015">条</option>
																<option value="017">块</option>
																<option value="018">卷</option>
																<option value="019">副</option>
																<option value="020">片</option>
																<option value="021">组</option>
																<option value="022">份</option>
																<option value="025">双</option>
																<option value="026">对</option>
																<option value="122">罐</option>
																<option value="123">桶</option>
																<option value="125">包</option>
																<option value="136">袋</option>
																<option value="139">粒</option>
																<option value="140">盒</option>
																<option value="141">合</option>
																<option value="142">瓶</option>
																<option value="503">枚</option>
															</select>
														</div>
													</div>
													<div class="form-group" >
														<label for="" class="col-lg-2">海关国家编码</label>
														<div class="col-lg-9">
															<select name="haiguanCountryCode" class="form-control" style="width:200px;" ng-model="addData.haiguanCountryCode">
																<option value="110">中国香港</option>
																<option value="116">日本</option>
																<option value="121">中国澳门</option>
																<option value="122">马来西亚</option>
																<option value="125">尼泊尔</option>
																<option value="132">新加坡</option>
																<option value="133">韩国</option>
																<option value="136">泰国</option>
																<option value="141">越南</option>
																<option value="142">中国</option>
																<option value="143">台澎金马关税区</option>
																<option value="301">比利时</option>
																<option value="302">丹麦</option>
																<option value="303">英国</option>
																<option value="304">德国</option>
																<option value="305">法国</option>
																<option value="306">爱尔兰</option>
																<option value="309">荷兰</option>
																<option value="310">希腊</option>
																<option value="311">葡萄牙</option>
																<option value="312">西班牙</option>
																<option value="315">奥地利</option>
																<option value="318">芬兰</option>
																<option value="326">挪威</option>
																<option value="327">波兰</option>
																<option value="330">瑞典</option>
																<option value="331">瑞士</option>
																<option value="410">巴西</option>
																<option value="429">墨西哥</option>
																<option value="501">加拿大</option>
																<option value="502">美国</option>
																<option value="601">澳大利亚</option>
																<option value="609">新西兰</option>
																<option value="701">国(地)别不详的</option>
																<option value="702">联合国及机构和国际组织</option>
																<option value="999">中性包装原产级别</option>
															</select>
														</div>
													</div>
													<div class="form-group" >
														<label for="" class="col-lg-2">国检国家编码</label>
														<div class="col-lg-9">
															<select name="guojianCountryCode" class="form-control" style="width:200px;" ng-model="addData.guojianCountryCode">
																<option value="36">澳大利亚</option>
																<option value="40">奥地利</option>
																<option value="56">比利时</option>
																<option value="76">巴西</option>
																<option value="100">保加利亚</option>
																<option value="124">加拿大</option>
																<option value="152">智利</option>
																<option value="156">中国</option>
																<option value="158">中国台湾</option>
																<option value="170">哥伦比亚</option>
																<option value="208">丹麦</option>
																<option value="246">芬兰</option>
																<option value="250">法国</option>
																<option value="276">德国</option>
																<option value="300">希腊</option>
																<option value="344">中国香港</option>
																<option value="356">印度</option>
																<option value="372">爱尔兰</option>
																<option value="380">意大利</option>
																<option value="392">日本</option>
																<option value="410">韩国</option>
																<option value="446">中国澳门</option>
																<option value="458">马来西亚</option>
																<option value="484">墨西哥</option>
																<option value="528">荷兰</option>
																<option value="554">新西兰</option>
																<option value="578">挪威</option>
																<option value="643">俄罗斯</option>
																<option value="702">新加坡</option>
																<option value="704">越南</option>
																<option value="710">南非</option>
																<option value="724">西班牙</option>
																<option value="752">瑞典</option>
																<option value="756">瑞士</option>
																<option value="764">泰国</option>
																<option value="826">英国</option>
																<option value="840">美国</option>
																<option value="903">亚洲</option>
																<option value="906">非洲</option>
																<option value="909">欧洲</option>
																<option value="991">保税区</option>
																<option value="992">未列出的国家或地区</option>
																<option value="0">所有国家地区</option>
															</select>
														</div>
													</div>
													<div class="form-group" >
														<label for="" class="col-lg-2">海关商品产地</label>
														<div class="col-lg-9">
															<select name="haiguanGoodsPlace" class="form-control" style="width:200px;" ng-model="addData.haiguanGoodsPlace">
																<option value="36">澳大利亚</option>
																<option value="56">比利时</option>
																<option value="124">加拿大</option>
																<option value="156">中国</option>
																<option value="158">中国台湾</option>
																<option value="208">丹麦</option>
																<option value="250">法国</option>
																<option value="276">德国</option>
																<option value="300">希腊</option>
																<option value="344">中国香港</option>
																<option value="380">意大利</option>
																<option value="392">日本</option>
																<option value="410">韩国</option>
																<option value="458">马来西亚</option>
																<option value="554">新西兰</option>
																<option value="643">俄罗斯</option>
																<option value="702">新加坡</option>
																<option value="724">西班牙</option>
																<option value="764">泰国</option>
																<option value="826">英国</option>
																<option value="840">美国</option>
															</select>
														</div>
													</div>
													<div class="form-group" >
														<label for="" class="col-lg-2">国检商品产地</label>
														<div class="col-lg-9">
															<select name="guojianGoodsPlace" class="form-control" style="width:200px;" ng-model="addData.guojianGoodsPlace">
																<option value="110">中国香港</option>
																<option value="116">日本</option>
																<option value="122">马来西亚</option>
																<option value="132">新加坡</option>
																<option value="133">韩国</option>
																<option value="136">泰国</option>
																<option value="142">中国</option>
																<option value="158">中国台湾</option>
																<option value="301">比利时</option>
																<option value="302">丹麦</option>
																<option value="303">英国</option>
																<option value="304">德国</option>
																<option value="305">法国</option>
																<option value="309">荷兰</option>
																<option value="310">希腊</option>
																<option value="311">葡萄牙</option>
																<option value="312">西班牙</option>
																<option value="315">奥地利</option>
																<option value="429">墨西哥</option>
																<option value="502">美国</option>
																<option value="601">澳大利亚</option>
																<option value="609">新西兰</option>
															</select>
														</div>
													</div>
													</div>
													
													<div class="form-group">
														<label for="" class="col-lg-2">商品名称</label>
														<div class="col-lg-9">
															<input style="width:400px;" type="text" name="goodsName" value="">
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">商品简介</label>
														<div class="col-lg-9">
															<textarea name="brief" class="form-control" rows="3"></textarea>
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">商品原价</label>
														<div class="col-lg-9">
															<input type="text" name="onsalePrice" value="">
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">商品折扣价</label>
														<div class="col-lg-9">
															<input type="text" name="discountPrice" value="">
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">商品主图</label>
														<div class="col-lg-9" id="divTopPicKeys">
															<input id="hiddenTopPicKeys" name="topPicKeys" type="hidden" value="" >
															<input id="topPicKeysUpload" data-url="/publicImageUpload" type="file" name="files" multiple>
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">场景图</label>
														<div class="col-lg-9" id="divScenePicKeys">
															<input id="hiddenScenePicKeys" name="scenePicKey" type="hidden" value="" >
															<input id="scenePicKeysUpload" data-url="/publicImageUpload" type="file" name="files" >
														</div>
													</div>
												</div>
											</div>
										</section>
										<section class="panel">
											<header class="panel-heading">商品详情</header>
											<div class="panel-body">
												<div class="form-horizontal">
													<div class="form-group">
														<label for="" class="col-lg-2">详情大图</label>
														<div class="col-lg-9" id="divDetailPicKeys" >
															<input id="hiddenDetailPicKeys" name="detailPicKeys" type="hidden" value="" >
															<input id="detailPicKeysUpload" data-url="/publicImageUpload" type="file" name="files" multiple>
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">详情介绍</label>
														<div class="col-lg-9">
															<textarea name="goodsDesc" class="form-control" rows="3"></textarea>
														</div>
													</div>
												</div>
											</div>
										</section>
										<section class="panel">
											<header class="panel-heading">产品参数</header>
											<div class="panel-body">
												<div class="form-horizontal">
													<div class="form-group" style="display: none;">
														<label for="" class="col-lg-2">商品尺寸</label>
														<div class="col-lg-9">
															<input type="text" name="size" value="">
														</div>
													</div>
													<div class="form-group" style="display: none;">
														<label for="" class="col-lg-2">商品颜色</label>
														<div class="col-lg-9">
															<input type="text" name="color" value="">
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">商品产地</label>
														<div class="col-lg-9">
															<input type="text" name="originPlace" value="">
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">商品重量</label>
														<div class="col-lg-9">
															<input type="text" name="weight" value="">
														</div>
													</div>
													<div class="form-group btnlist">
														<!-- <input type="submit" id="submitGoodsEidt" style="display:none;" value="提交" class="btn btn-info"> -->
														<!-- <button id="goodsEditSave" class="btn btn-info" >提交</button> -->
														<a class="btn btn-info" href="javascript:void(0);" id="a_submitGoodsEditSave">提交</a>
													</div>
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
	
	<script src="/resources/js/jquery_upload/vendor/jquery.ui.widget.js"></script>
    <script src="/resources/js/jquery_upload/jquery.iframe-transport.js"></script>
    <script src="/resources/js/jquery_upload/jquery.fileupload.js"></script>
	<!-- 侧面导航栏组件 js -->
	<script src="/resources/js/sidebar.js"></script>
	<script src="/resources/js/jquery.pagination.js"></script>
	<script type="text/javascript">
	$(function(){
		
	});
	</script>
	<script type="text/javascript">
	var topPicLength = 0;
	var detailPicLength = 0;
	$("#topPicKeysUpload").fileupload({
        // formData:{param1:"p1",param2:"p2"},//如果需要额外添加参数可以在这里添加
        done:function(e,result){
        	
            //done方法就是上传完毕的回调函数，其他回调函数可以自行查看api
            //注意result要和jquery的ajax的data参数区分，这个对象包含了整个请求信息
            //返回的数据在result.result中，假设我们服务器返回了一个json对象
            //alert(result);
            var fileName = JSON.stringify(result.result.data.fileNames[0]);
            fileName = fileName.substring(1, fileName.length-1);
            $("#hiddenTopPicKeys").val($("#hiddenTopPicKeys").val()+","+fileName);
            if($("#hiddenTopPicKeys").val().charAt(0) == ','){
            	$("#hiddenTopPicKeys").val($("#hiddenTopPicKeys").val().substring(1));
            }
            console.log(JSON.stringify(result.result));
            //alert(JSON.stringify(result.result.data.fileNames[0]));
            //alert($("#hiddenTopPicKeys").val());
           
            $("#divTopPicKeys").append("<span id='topPicKey_"
            		+ fileName +"'><img "
            		+ " width='60px' src='${picAddress}"
            		+ fileName + "''><a href='javascript:void(0);' onclick=shanchu('topPicKey_"
            		+ fileName + "'," + topPicLength + ")>x</a>&nbsp;&nbsp;&nbsp;</span>");
            topPicLength++;
        }
    });
	
	$("#scenePicKeysUpload").fileupload({
        // formData:{param1:"p1",param2:"p2"},//如果需要额外添加参数可以在这里添加
        done:function(e,result){
            //done方法就是上传完毕的回调函数，其他回调函数可以自行查看api
            //注意result要和jquery的ajax的data参数区分，这个对象包含了整个请求信息
            //返回的数据在result.result中，假设我们服务器返回了一个json对象
            $("#divScenePicKeys").find("span").remove();
            var fileName = JSON.stringify(result.result.data.fileNames[0]);
            fileName = fileName.substring(1, fileName.length-1);
            $("#hiddenScenePicKeys").val(fileName);
            console.log(JSON.stringify(result.result));
            $("#divScenePicKeys").append("<span id='span_scenePicKey'><img "
            		+ " width='60px' src='${picAddress}"
            		+ fileName + "''><a href='javascript:void(0);' onclick=shanchuScenePicKey('span_scenePicKey')>x</a>&nbsp;&nbsp;&nbsp;</span>");
        }
    });
	$("#detailPicKeysUpload").fileupload({
        // formData:{param1:"p1",param2:"p2"},//如果需要额外添加参数可以在这里添加
        done:function(e,result){
        	
            //done方法就是上传完毕的回调函数，其他回调函数可以自行查看api
            //注意result要和jquery的ajax的data参数区分，这个对象包含了整个请求信息
            //返回的数据在result.result中，假设我们服务器返回了一个json对象
            var fileName = JSON.stringify(result.result.data.fileNames[0]);
            fileName = fileName.substring(1, fileName.length-1);
            $("#hiddenDetailPicKeys").val($("#hiddenDetailPicKeys").val()+","+fileName);
            if($("#hiddenDetailPicKeys").val().charAt(0) == ','){
            	$("#hiddenDetailPicKeys").val($("#hiddenDetailPicKeys").val().substring(1));
            }
            console.log(JSON.stringify(result.result));
           // alert(JSON.stringify(result.result.data.fileNames[0]));
           // alert($("#hiddenTopPicKeys").val());
           
            $("#divDetailPicKeys").append("<span id='detailPicKey_"
            		+ fileName +"'><img "
            		+ " width='60px' src='${picAddress}"
            		+ fileName + "''><a href='javascript:void(0);' onclick=shanchuDetailPic('detailPicKey_"
            		+ fileName + "'," + detailPicLength + ")>x</a>&nbsp;&nbsp;&nbsp;</span>");
            detailPicLength++;
        }
    });
	function shanchu(id, picIndex){
		//alert(id + "\n" + picIndex);
		$("#"+id).remove();
		var hiddenTopPicKeys = $("#hiddenTopPicKeys").val();
		//alert(hiddenTopPicKeys);
		
		hiddenTopPicKeys = hiddenTopPicKeys.replace((id.split("_")[1]+","),'')
								.replace(id.split("_")[1],'');
		if(hiddenTopPicKeys.charAt(hiddenTopPicKeys.length-1) == ','){
			hiddenTopPicKeys = hiddenTopPicKeys.substring(0, hiddenTopPicKeys.length-1);
		}
		/* var topPicKeysString = new Array();
		topPicKeysString = hiddenTopPicKeys.split(",");
		topPicKeysString.splice(picIndex, 1);
		hiddenTopPicKeys = topPicKeysString.join(","); */
		//alert(hiddenTopPicKeys);
		$("#hiddenTopPicKeys").val(hiddenTopPicKeys);
	}
	function shanchuDetailPic(id, picIndex){
		//alert(id + "\n" + picIndex);
		$("#"+id).remove();
		var hiddenDetailPicKeys = $("#hiddenDetailPicKeys").val();
		//alert(hiddenTopPicKeys);
		hiddenDetailPicKeys = hiddenDetailPicKeys.replace((id.split("_")[1]+","),'')
								.replace(id.split("_")[1],'');
		if(hiddenDetailPicKeys.charAt(hiddenDetailPicKeys.length-1) == ','){
			hiddenDetailPicKeys = hiddenDetailPicKeys.substring(0, hiddenDetailPicKeys.length-1);
		}
		/* var detailPicKeysString = new Array();
		detailPicKeysString = hiddenDetailPicKeys.split(",");
		detailPicKeysString.splice(picIndex, 1);
		hiddenDetailPicKeys = detailPicKeysString.join(","); */
		//alert(hiddenTopPicKeys);
		$("#hiddenDetailPicKeys").val(hiddenDetailPicKeys);
	}
	function shanchuScenePicKey(id){
		//alert(id + "\n" + picIndex);
		$("#"+id).remove();
		$("#hiddenScenePicKeys").val('');
	}
	
		$("#a_submitGoodsEditSave").click(function(){
			/* alert($("#goodsEditProfile").serialize()); */
			$.ajax({
				type : "post",
				url : "/goods/addNewGoods",
				data: $("#goodsEditProfile").serialize(),
				cache : false,
				dataType : 'json',
				success : function(data) {
					if(data.code == 200){
						window.location.href = "/goods/page?page=1";
					}
					else{
						alert("code: " + data.code + "\n" + data.msg);
					}
				}
			});
		});
	
	</script>
	<script type="text/javascript">
		$(document).ready(function(){
			//$(".none_global").hide();
			$("#warehouseType").change(function(){
				var warehouseType = $("#warehouseType").val();
				if(warehouseType == 'TAX_WAREHOUSE'){
					$("#huanguanGuojian").show();
				}
				else{
					$("#huanguanGuojian").hide();
				}
			});
		});
	</script>
</body>
</html>