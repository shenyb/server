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
								<h3 class="m-b-less">商品新增</h3>
								<!--<span class="sub-title">Welcome to Static Table</span>-->
								<div class="state-information">
									<ol class="breadcrumb m-b-less bg-less">
										<li><a href="#">主页</a></li>
										<li><a href="#">采销管理</a></li>
										<li class="active">商品编辑</li>
									</ol>
								</div>
							</div>
							<div class="wrapper">
								<div class="row">
									<div class="col-sm-12">
									<form id="goodsEditProfile" class="form-horizontal">
											<div class="info">
									            <table class="table table-bordered table-hover table-condensed table-responsive">
									            <header class="panel-heading">
													套装商品主信息
													<div class="back">
														<a href="/goods/page?page=1" class="button button-primary button-raised button-small">返回</a>
													</div>
												</header>
									                    <tr>
									                        <td><i class="fa fa-asterisk"></i>商品名称</td>
									                        <td><input type="text" name="goodsName" id="goodsName" value="" class="form-control" required></td>
									                        <td><i class="fa fa-asterisk"></i>商品仓库</td>
									                        <td>
									                        	<select  class="form-control" required name="warehouseType" id="warehouseType">
																	<option value="SELF_WAREHOUSE">自营仓</option>
																	<option value="OVERSEA_WAREHOUSE">海外直邮</option>
																</select>
									                        </td>
									                    </tr>
									                    <tr>	
									                    	<td><i class="fa fa-asterisk"></i>商品短名</td>
									                        <td><input type="text" name="shortName" id="shortName" value="" class="form-control" required></td>                     
									                    </tr>
														<tr>
									                        <td><i class="fa fa-asterisk"></i>商品原价</td>
									                        <td><span id="onsalePrice" name="onsalePrice"></span></td>
									                        <td><i class="fa fa-asterisk"></i>Need价</td>
									                        <td><span id="discountPrice" name="discountPrice"></span></td>                        
									                    </tr>
									                    <tr>
									                        <td><i class="fa fa-asterisk"></i>商品采购价</td>
									                        <td><span id="purchasePrice" name="discountPrice"></span></td>
									                        <td><i class="fa fa-asterisk"></i>商品重量</td>
									                        <td><span id="weight"></span>kg</td>                        
									                    </tr>
									                  </table>
											
									       <table id="goods" class="table table-bordered table-hover table-condensed table-responsive">    									         
									               	<tr class="table-title"><td colspan="4">套装中单品商品信息 <span style="color:red;">&nbsp;&nbsp;&nbsp;&nbsp;只能添加本仓库下面已上架并且库存数大于套装数量的单品</span></td><td colspan="4"><input style="float:right;" type="button" onclick="addGoods();" value="添加" /></td></tr>
									               	<tr>
									                    <td>商品编号</td>
									                    <td>商品名称</td>
									                	<td>数量</td>
									                	<td>上下架状态</td>
									                	<td>正品库存</td>
									                	<td>Need价</td>
									                	<td>操作</td>
									                </tr>
									       </table>
									       <div class="form-group btnlist">
												<a class="button button-primary button-raised button-small pull-right" href="javascript:void(0);"
													onClick="doIt()">提交</a>
										   </div>	
									       </div>
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
	var flag="1";
	 //使用规则添加一条
    function addGoods(){
		var goodsName=$("#goodsName").val();
		var shortName=$("#shortName").val();
		if(goodsName == null || goodsName == ""){
			alert("商品名称不能为空");
			return false;
		}
		if(shortName == null || shortName == ""){
			alert("商品短名不能为空");
			return false;
		}
    	var tr="<tr><td><input type='text' name='goodsCodeItem' value='' onBlur='getGoodsDetail(this)' onkeydown='selectDetail(this)'/></td> <td name='aaa'></td> <td><input type='text' name='goodsCount' value='1' onChange='changePrice(this)' onkeydown='selectPrice(this)'/></td> <td></td> <td></td> <td></td> <td><a onClick=removeThis(this)>移除</a></td><input type='hidden' name='onsalePriceItem' value=''><input type='hidden' name='discountPriceItem' value=''><input type='hidden' name='purchasePriceItem' value=''><input type='hidden' name='weightItem' value=''></tr>";
    	$("#goods").append(tr);
	}
    function removeThis(obj) {
        var couponParent=$(obj).parent().parent();
       	$(couponParent).remove();
       	changePrice();
    };
    function getGoodsDetail(obj){
    	flag="1";
    	var couponParent=$(obj).parent().parent();
    	$(couponParent).css("background-color","#FFFFFF");
    	var warehouseType =$("#warehouseType").val();
    	var code=obj.value;
    	var goodsCodeItem = $("input[name='goodsCodeItem']");
    	if(goodsCodeItem.length > 1){
    		for(var i=0;i<goodsCodeItem.length-1;i++){
    			var name=goodsCodeItem[i].value;
    			for(var j=i+1;j<goodsCodeItem.length;j++){
    				if(name == goodsCodeItem[j].value){
    					alert("商品编号不能重复");
        				return false;
    				}
    			}
    		}
    	}
    	
		 $.ajax({
		     type: 'GET',
		     url: '/goods/getGoodsDetail',
		     data: {'goodsCode':obj.value,warehouseType:warehouseType},
		     dataType: 'json',
		     success : function(data) {
	           if(data.code==200){
	           	 $.each(data.data, function(key, val) {
	           		 $(obj).parent().next().html(val.goodsName);
					 $(obj).parent().next().next().next().html(val.goodsStatus);
					 $(obj).parent().next().next().next().next().html(val.nowStoreCount);
					 $(obj).parent().next().next().next().next().next().html(val.discountPrice);
					 $(obj).parent().next().next().next().next().next().next().next().val(val.onsalePrice);
					 $(obj).parent().next().next().next().next().next().next().next().next().val(val.discountPrice);
					 $(obj).parent().next().next().next().next().next().next().next().next().next().val(val.purchasePrice);
					 $(obj).parent().next().next().next().next().next().next().next().next().next().next().val(val.weight);
					 changePrice();
	           	 })
	           }else{
	               $(couponParent).css("background-color","#FF3355");
	               flag="2";
	           }
			 },
			 error : function() {
				 alert("系统异常");
			}

		   }); 
    }
    
    function changePrice(obj){
    	var goodsCodeItem = $("input[name='goodsCodeItem']");
		var goodsCount=$("input[name='goodsCount']");
		var arr = new Array();
		for(var i=0;i<goodsCodeItem.length;i++){
			var test = goodsCodeItem[i].value+","+goodsCount[i].value+"@";
			arr.push(test);
			data= goodsCodeItem[i].value;
		}
		 $.ajax({
		     type: 'GET',
		     url: '/goods/changePrice',
		     data : {
	            	dataAll : arr.toString(),
	            },
		     dataType: 'json',
		     success : function(data) {
	           if(data.code==200){
		       		$("#onsalePrice").html(data.data.priceGoods.oPrice);
		       		$("#discountPrice").html(data.data.priceGoods.nPrice);
		       		$("#purchasePrice").html(data.data.priceGoods.pPrice);
		       		$("#weight").html(data.data.priceGoods.wPrice);
	           }else{
	        	   alert(data.msg);
	           }
			 },
			 error : function() {
				 alert("系统异常");
			}

		   }); 
		
    }
    
    function doIt() {
    	if("2" == flag){
    		alert("请移除或修改标注红色的商品记录");
    		return false;
    	}
		var goodsCodeItem = $("input[name='goodsCodeItem']");
		var goodsCount=$("input[name='goodsCount']");
		var goodsName=$("#goodsName").val();
		var warehouseType=$("#warehouseType").val();
		var shortName=$("#shortName").val();
		var onsalePrice=$("#onsalePrice").html();
		var discountPrice=$("#discountPrice").html();
		var purchasePrice=$("#purchasePrice").html();
		var weight=$("#weight").html();
		if(goodsCodeItem.length > 1){
    		for(var i=0;i<goodsCodeItem.length-1;i++){
    			var name=goodsCodeItem[i].value;
    			for(var j=i+1;j<goodsCodeItem.length;j++){
    				if(name == goodsCodeItem[j].value){
    					alert("商品编号不能重复");
        				return false;
    				}
    			}
    		}
    	}
		var arr = new Array();
		if(0 == goodsCodeItem.length){
			alert("请添加商品");
			return false;
		}
		
		for(var i=0;i<goodsCodeItem.length;i++){
			var test = goodsCodeItem[i].value+","+goodsCount[i].value+"@";
			arr.push(test);
			data= goodsCodeItem[i].value;
		}
        $.ajax({
            type : "POST",
            url : "/goods/AddNewGoodsGroup",
            datatype : "json",
            data : {
            	dataAll : arr.toString(),
            	goodsName:goodsName,
            	warehouseType:warehouseType,
            	shortName:shortName,
            	onsalePrice:onsalePrice,
            	discountPrice:discountPrice,
            	purchasePrice:purchasePrice,
            	weight:weight,
            },
            success : function(data) {
                if (data.code == 200) {
                    window.location.href = "/goods/page";
                } else {
                    alert("code: " + data.code + "\n" + data.msg);
                }
            },
            error: function(data){
            }
            
        }); 
    }
    function selectPrice(obj){
    	$(obj).keydown(function (e){
    		if(e.which == "13"){
    			changePrice(obj);
    		}
    	})

    }
    function selectDetail(obj){
    	$(obj).keydown(function (e){
    		if(e.which == "13"){
    			getGoodsDetail(obj);
    		}
    	})
    }
	</script>
</body>
</html>