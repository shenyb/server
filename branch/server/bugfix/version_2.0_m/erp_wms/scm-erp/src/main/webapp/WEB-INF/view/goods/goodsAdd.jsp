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
<link rel="stylesheet" href="/resources/css/jquery.bigautocomplete.css">
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
													商品概要 
													<div class="back">
														<a href="javascript:void(0);" onclick="toBackPage();" class="button button-primary button-raised button-small">返回</a>
													</div>
												</header>
									                <!--<tbody>-->							                
									                    <tr>									                       
									                        <td><i class="fa fa-asterisk"></i>国际条形码</td>
									                        <td><input type="text" value="" name="goodsBarcode" class="form-control" required>
									                        	<input type="hidden" value="${picAddress}" id="hiddenPicAddress">
									                        </td>                       
									                    </tr>
									                    <tr>
									                        <td><i class="fa fa-asterisk"></i>商品分类</td>
									                        <td colspan="3">
									                        	<select class="form-control" required id="oneGoodsCategoryId">
																	<c:forEach items="${goodsCategoryList}"
																		var="goodsCategory" varStatus="status">
																		<option value="${goodsCategory.categoryId}">${goodsCategory.categoryName}</option>
																	</c:forEach>
																</select>
																<select class="form-control" required id="twogoodsCategoryId">
																	<option value="">请选择</option>
																</select>
																<select  class="form-control" required name="goodsCategoryId" id="threeGoodsCategoryId">
																	<option value="">请选择</option>
																</select>
									                        </td>                       
									                    </tr>
									                    <tr>
									                        <td><i class="fa fa-asterisk"></i>商品品牌</td>
									                        <td>
									                        	<%-- <select  class="form-control" required name="brandId" id="brandId">
																	<c:forEach items="${brandList}" var="goodsBrand"
																		varStatus="status">
																		<option value="${goodsBrand.brandId}">${goodsBrand.brandName}</option>
																	</c:forEach>
																</select> --%>
														   	 	<input type="hidden" name="brandId" id="brandId" value="">
																<input type="text" name="brandName" value=""  id="brandName"  class="form-control" />
									                        </td>
									                        <td>是否有效期管理</td>
									                        <td>
									                        	<div class="form-group col-xs-6">
																	<input name="isValidDate" type="radio" value="TRUE" />是 
																	
																	 <input name="isValidDate" type="radio" value="FALSE" checked="checked" />否 
																</div>
																
									                        </td>
									                    </tr>
									                    <tr>	
									                    	<td>有效期</td>
									                        <td>
										                        <div class="small-group">									                        	
																<input name="validDate" type="text" value="" class="form-control" /><b class="cake">月</b>														
										                        </div>
									                        </td>							                       
									                        <td>是否赠品</td>
									                        <td>									                        	
																<input name="isGift" type="radio" value="TRUE" />是 
																<input name="isGift" type="radio" value="FALSE" checked="checked" />否 														
									                        </td>                        
									                    </tr>
									                     <tr style="display:none;">	
									                    	<td>是否单品</td>
									                        <td>
										                        <input name="goodsType" type="radio" value="SINGLE" checked="checked" />单品
										                        <input name="goodsType" type="radio" value="MORE" />多品 
									                        </td>							                       
									                                             
									                    </tr>
									                    <tr>
									                        <td><i class="fa fa-asterisk"></i>仓库类型</td>
									                        <td>
									                        	<select class="form-control" name="warehouseType" id="warehouseType">
																	<option value="">请选择</option>
																	<option value="SELF_WAREHOUSE">自营仓</option>
																	<option value="TAX_WAREHOUSE">保税仓</option>
																	<option value="OVERSEA_WAREHOUSE">海外直邮</option>
																</select>														
									                        </td>
									                     	                  
									                    </tr>

									                    <tr class="huanguanGuojian">
									                    	<td>海关计量单位</td>
									                        <td>
									                        	<select name="haiguanCount"  class="form-control" ng-model="addData.haiguanCount">
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
									                        </td>
									                        
									                         <td>国检计量单位</td>
									                        <td>
									                        	<select name="guojianCount" class="form-control" ng-model="addData.guojianCount">
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
									                        </td> 
									                    </tr>
									                     <tr class="huanguanGuojian">
									                    	<td>海关国家编码</td>
									                        <td>
									                        	<select name="haiguanCountryCode" class="form-control" id="" ng-model="addData.haiguanCountryCode">
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
									                        </td>
									                        
									                         <td>国检国家编码</td>
									                        <td>
									                        	<select name="guojianCountryCode" class="form-control" id="" ng-model="addData.guojianCountryCode">
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
																		<option value="0">有国家地区</option>
																	</select>
									                        </td> 
									                    </tr>
									                    <tr class="huanguanGuojian">
									                    	<td>海关商品产地</td>
									                        <td>
									                        	<select name="haiguanGoodsPlace" class="form-control"  id="" ng-model="addData.haiguanGoodsPlace">
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
									                        </td>
									                        
									                         <td>国检商品产地</td>
									                        <td>
									                        	<select name="guojianGoodsPlace" class="form-control" id="" ng-model="addData.guojianGoodsPlace">
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
									                        </td> 
									                    </tr>
									                    <tr>
									                    	<td><i class="fa fa-asterisk"></i>商品名称</td>
									                    	<td colspan="3">
									                    	<div class="large-group">	
									                    		<input  type="text" name="goodsName" value="" required class="form-control">
									                    	</div>
									                    	</td>
									                    </tr>
									                    <tr>
									                       <td><i class="fa fa-asterisk"></i>商品短名</td>
									                        <td><input type="text" name="shortName" value="" class="form-control" required></td>
									                         <td><i class="fa fa-asterisk"></i>商品原价</td>
									                        <td><input type="text" name="onsalePrice" value="" number="true" min="0" required class="form-control"></td>                        
									                    </tr>
									                    
									                    <!-- 商品简介 display:none-->
									                    
									                    <tr style="display:none ;">
															<td for="" class="col-lg-2" style="display:none ;">商品简介</td>
															<td colspan="3">
																
																<textarea name="brief" class="form-control" rows="3" cols="70"></textarea>
															</td>
														</tr>
														
									                     <tr>
									                       <td><i class="fa fa-asterisk"></i>Need价</td>
									                        <td><input type="text" name="discountPrice" value="" number="true" min="0" class="form-control"></td>
									                         <td><i class="fa fa-asterisk"></i>采购价</td>
									                        <td><input type="text" name="purchasePrice" value="" required min="0" class="form-control"></td>                        
									                    </tr>
									                    <tr>
									                       <td><i class="fa fa-asterisk"></i>采购经理</td>
									                        <td>
									                        	<select class="form-control" name="purchaseManager" id="purchaseManager">
																	<c:forEach items="${userList}" var="list" varStatus="status">
																		<option value="${list.userId}">${list.userRealName}</option>
																	</c:forEach>
																</select>
									                        </td>
									                         <td><i class="fa fa-asterisk"></i>采购主管</td>
									                        <td>
									                        	<select class="form-control" name="purchaseLeader" id="purchaseLeader">
																	<c:forEach items="${userList}" var="list"
																		varStatus="status">
																		<option value="${list.userId}">${list.userRealName}</option>
																	</c:forEach>
																</select>
									                        </td>                        
									                    </tr>
									                    <!-- 商品图片 display:none -->
									                    <tr style="display:none;">
									                    	<td>
									                    		商品主图
									                    	</td>
									                    	<td colspan="3">
									                    		<input id="hiddenTopPicKeys" name="topPicKeys" type="hidden" value=""> 
									                    		<input id="topPicKeysUpload" data-url="/publicImageUpload"
																	type="file" name="files" multiple>
									                    	</td>
									                    </tr>
									                    
									                    <tr style="display:none;">
									                    	<td>
									                    		场景图
									                    	</td>
									                    	<td colspan="3">
									                    		<input id="hiddenScenePicKeys" name="scenePicKey" type="hidden" value="">
									                    		 <input id="scenePicKeysUpload" data-url="/publicImageUpload"
																	type="file" name="files">
									                    	</td>
									                    </tr>
									                  </table>
									                  
									         <!--商品详情  display:none  -->
									       <table class="table table-bordered table-hover table-condensed table-responsive" style="display:none;">
									         <tr class="table-title">
									         	<td colspan="2">
									         	商品详情
									         	</td>												
											</tr>
											<tr>
												<td>详情大图</td>
												<td>
													<input id="hiddenDetailPicKeys" name="detailPicKeys" type="hidden" value=""> 
													<input id="detailPicKeysUpload" data-url="/publicImageUpload" type="file" name="files" multiple>
												</td>
											</tr>
											<tr>
												<td>
													详情介绍
												</td>
												<td>
													
													<textarea name="goodsDesc" class="form-control" rows="3" cols="70"></textarea>
												</td>
											</tr>
											</table>
											
									       <table class="table table-bordered table-hover table-condensed table-responsive">    									         
									               	<tr class="table-title"><td colspan="4">产品参数</td></tr>
									               	<tr >
									                     <td>商品尺寸</td>
									                    <td>
									                    	<input type="text" name="size" value="" class="form-control">
									                    </td>
									                     <td>商品颜色</td>
									                    <td> 									                    	
									                    	<input type="text" name="color" value="" class="form-control">															
									                	</td>									                   
									                </tr>
									                <tr>
									                     <td><i class="fa fa-asterisk"></i>商品产地</td>
									                    <td>
									                    	<input type="text" name="originPlace" value="" class="form-control">
									                    </td>
									                     <td><i class="fa fa-asterisk"></i>商品重量</td>
									                    <td> 
									                    	<div class="small-group">
									                    		<input type="text" name="weight" value="" number="true" min="0" required class="form-control">
																<b class="cake">KG</b>
															</div>
									                	</td>									                   
									                </tr>								               
									                </tbody>
									               
									            </table>
									       <div class="form-group btnlist">
												<!-- <input type="submit" id="submitGoodsEidt" style="display:none;" value="提交" class="btn btn-info"> -->
												<!-- <button id="goodsEditSave" class="btn btn-info" >提交</button> -->
												<a class="button button-primary button-raised button-small pull-right" href="javascript:void(0);"
													id="a_submitGoodsEditSave">提交</a>
										   </div>	
									        </div>
										
													
										</form> 
									 
									<%-- <!-- 33333333 -->
										<form id="goodsEditProfile" class="form-horizontal">
											<section class="panel">
												<header class="panel-heading">
													商品概要 
													<div class="back">
														<a href="/goods/page?page=1" class="button button-primary button-raised button-small">返回</a>
													</div>
												</header>
												<div class="panel-body">
													<div class="form-horizontal">
														<div class="form-group">
															<label for="" class="col-lg-2">
															<i>* </i>国条码</label>
															<div class="col-lg-9">
																<input type="text" value="" name="goodsBarcode" required class="form-control">
																<input type="hidden" value="${picAddress}" id="hiddenPicAddress" class="form-control">
															</div>
														</div>
														<div class="form-group">
															<label for="" class="col-lg-2">
															<i>* </i>商品分类</label>
															<div class="col-lg-3">
																<select class="form-control" required id="oneGoodsCategoryId">
																	<c:forEach items="${goodsCategoryList}"
																		var="goodsCategory" varStatus="status">
																		<option value="${goodsCategory.categoryId}">${goodsCategory.categoryName}</option>
																	</c:forEach>
																</select>
															</div>
															<div class="col-lg-3">
																<select class="form-control" required id="twogoodsCategoryId">
																	<option value="">请选择</option>
																</select>
															</div>
															<div class="col-lg-3">
																<select class="form-control" required name="goodsCategoryId" id="threeGoodsCategoryId">
																	<option value="">请选择</option>
																</select>
															</div>
														</div>
														<!-- 选择品牌 -->
														<div class="form-group">
															<label for="" class="col-lg-2">
															<i>* </i>商品品牌</label>
															<div class="col-lg-9">
																<select class="form-control" required name="brandId" id="brandId">
																	<c:forEach items="${brandList}" var="goodsBrand"
																		varStatus="status">
																		<option value="${goodsBrand.brandId}">${goodsBrand.brandName}</option>
																	</c:forEach>
																</select>
															</div>
														</div>

														<!--是否有效期管理  -->
														<div class="form-group">
															<label for="" class="col-lg-2">是否效期管理</label>
															<div class="col-lg-9">
																<div class="col-lg-1"><input name="isValidDate" type="radio" value="TRUE" checked="checked" />是 </div> 
																<div class="col-lg-1"><input name="isValidDate" type="radio" value="FALSE"/>否 </div>
																<div class="col-lg-1"></div>
																<div id="validDate" class="form-group small-group">
																	<label for="" class="col-lg-2">有效期</label>
																	<div class="col-lg-3">
																		<input name="validDate" class="form-control" type="text" value="" /> 
																		<b class="cake">月</b>
																	</div>
																</div>
															</div>															
														</div>
														
														<div class="form-group">
															<label for="" class="col-lg-2">是否赠品</label>
															<div class="col-lg-9">
																<div class="col-lg-1"><input name="isGift" type="radio" value="TRUE" />是 </div> 
																<div class="col-lg-1"><input name="isGift" type="radio" value="FALSE" checked="checked" />否 </div>
															</div>
														</div>

														<div class="form-group" style="display: none;">
															<label for="" class="col-lg-2">是否单品</label>
															<div class="col-lg-9">
																<label><input name="goodsType" type="radio" value="SINGLE" checked="checked" />单品 </label> 
																<label><input name="goodsType" type="radio" value="MORE" />多品 </label>
															</div>
														</div>
														<div class="form-group">
															<label for="" class="col-lg-2">
															<i>* </i>仓库类型</label>
															<div class="col-lg-9">
																<select class="form-control" name="warehouseType" id="warehouseType">
																	<option value="">请选择</option>
																	<option value="SELF_WAREHOUSE">自营仓</option>
																	<option value="TAX_WAREHOUSE">保税仓</option>
																	<option value="OVERSEA_WAREHOUSE">海外直邮</option>
																</select>
															</div>
														</div>
														<div style="display: none;" id="huanguanGuojian">
															<div class="form-group">
																<label for="" class="col-lg-2">海关计量单位</label>
																<div class="col-lg-9">
																	<select name="haiguanCount" class="form-control"  ng-model="addData.haiguanCount">
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
															<div class="form-group">
																<label for="" class="col-lg-2">国检计量单位</label>
																<div class="col-lg-9">
																	<select name="guojianCount" class="form-control" id="" ng-model="addData.guojianCount">
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
															<div class="form-group">
																<label for="" class="col-lg-2">海关国家编码</label>
																<div class="col-lg-9">
																	<select name="haiguanCountryCode" class="form-control" ng-model="addData.haiguanCountryCode">
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
															<div class="form-group">
																<label for="" class="col-lg-2">国检国家编码</label>
																<div class="col-lg-9">
																	<select name="guojianCountryCode" class="form-control" ng-model="addData.guojianCountryCode">
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
															<div class="form-group">
																<label for="" class="col-lg-2">海关商品产地</label>
																<div class="col-lg-9">
																	<select name="haiguanGoodsPlace" class="form-control" ng-model="addData.haiguanGoodsPlace">
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
															<div class="form-group">
																<label for="" class="col-lg-2">国检商品产地</label>
																<div class="col-lg-9">
																	<select name="guojianGoodsPlace" class="form-control" ng-model="addData.guojianGoodsPlace">
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

														<div class="form-group large-group">
															<label for="" class="col-lg-2">
															<i>* </i>商品名称</label>
															<div class="col-lg-9">
																<input type="text" class="form-control" maxlength="50" name="goodsName" value="" required>
															</div>
														</div>
														<div class="form-group">
															<label for="" class="col-lg-2">
															<i>* </i>商品短名</label>
															<div class="col-lg-9">
																<input type="text" name="shortName" value="" class="form-control" required>
															</div>
														</div>
														<div class="form-group" style="display: none;">
															<label for="" class="col-lg-1">商品简介</label>
															<div class="col-lg-9">
																<textarea name="brief" class="form-control" rows="3"></textarea>
															</div>
														</div>
														<div class="form-group">
															<label for="" class="col-lg-2">
															<i>* </i>商品原价</label>
															<div class="col-lg-9">
																<input type="text" name="onsalePrice" value="" class="form-control" number="true" min="0" required>
															</div>
														</div>
														<div class="form-group">
															<label for="" class="col-lg-2">
															<i>* </i>折扣价</label>
															<div class="col-lg-9">
																<input type="text" name="discountPrice" value="" class="form-control" number="true" min="0">
															</div>
														</div>
														<div class="form-group">
															<label for="" class="col-lg-2">
															<i>* </i>采购价</label>
															<div class="col-lg-9">
																<input type="text" name="purchasePrice" value="" class="form-control" required min="0">
															</div>
														</div>
														<div class="form-group">
															<label for="" class="col-lg-2">
															<i>* </i>采购经理</label>
															<div class="col-lg-9">
																<select class="form-control" name="purchaseManager" id="purchaseManager">
																	<c:forEach items="${userList}" var="list" varStatus="status">
																		<option value="${list.userId}">${list.userRealName}</option>
																	</c:forEach>
																</select>
															</div>

														</div>
														<div class="form-group">
															<label for="" class="col-lg-2">
															<i>* </i>采购主管</label>
															<div class="col-lg-9">
																<select class="form-control" name="purchaseLeader" id="purchaseLeader">
																	<c:forEach items="${userList}" var="list" varStatus="status">
																		<option value="${list.userId}">${list.userRealName}</option>
																	</c:forEach>
																</select>
															</div>
														</div>
														<div class="form-group" style="display: none;">
															<label for="" class="col-lg-2">商品主图</label>
															<div class="col-lg-9" id="divTopPicKeys">
																<input id="hiddenTopPicKeys" name="topPicKeys" type="hidden" value="" class="form-control"> 
																<input id="topPicKeysUpload" data-url="/publicImageUpload" type="file" name="files" multiple >
															</div>
														</div>
														<div class="form-group" style="display: none;">
															<label for="" class="col-lg-2">场景图</label>
															<div class="col-lg-9" id="divScenePicKeys">
																<input id="hiddenScenePicKeys" name="scenePicKey" type="hidden" value="" class="form-control"> 
																<input id="scenePicKeysUpload" data-url="/publicImageUpload" type="file" name="files" class="form-control">
															</div>
														</div>
													</div>
												</div>
											</section>
											<section class="panel" style="display: none;">
												<header class="panel-heading">商品详情</header>
												<div class="panel-body">
													<div class="form-horizontal">
														<div class="form-group" style="display: none;">
															<label for="" class="col-lg-2">详情大图</label>
															<div class="col-lg-9" id="divDetailPicKeys">
																<input id="hiddenDetailPicKeys" name="detailPicKeys" class="form-control" type="hidden" value="">
																 <input id="detailPicKeysUpload" data-url="/publicImageUpload" type="file" name="files" multiple>
															</div>
														</div>
														<div class="form-group" style="display: none;">
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
														<div class="form-group" >
															<label for="" class="col-lg-2">商品尺寸</label>
															<div class="col-lg-9">
																<input type="text" name="size" value="" class="form-control">
															</div>
														</div>
														<div class="form-group" >
															<label for="" class="col-lg-2">商品颜色</label>
															<div class="col-lg-9">
																<input type="text" name="color" value="" class="form-control">
															</div>
														</div>
														<div class="form-group">
															<label for="" class="col-lg-2">
															<i>* </i>商品产地</label>
															<div class="col-lg-9">
																<input type="text" name="originPlace" value="" class="form-control">
															</div>
														</div>
														<div class="form-group small-group">
															<label for="" class="col-lg-2">
															<i>* </i>商品重量</label>
															<div class="col-lg-3">
																<input type="text" name="weight" value="" number="true" min="0" required class="form-control"> 
																<b class="cake">KG</b>
															</div>
														</div>
														<div class="form-group btnlist">
															<!-- <input type="submit" id="submitGoodsEidt" style="display:none;" value="提交" class="btn btn-info"> -->
															<!-- <button id="goodsEditSave" class="btn btn-info" >提交</button> -->
															<a class="button button-primary button-raised button-small pull-right" href="javascript:void(0);"
																id="a_submitGoodsEditSave">提交</a>
														</div>
													</div>
												</div>
											</section>
										</form>
									<!-- 3333333 --> --%>
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
	<script src="/resources/js/jquery.bigautocomplete.js"></script>
	<script src="/resources/js/goods/goods.js"></script>
	<!-- 侧面导航栏组件 js -->
	<script src="/resources/js/sidebar.js"></script>
	<script src="/resources/js/jquery.pagination.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			var data = ${jsonObject};
			$("#brandName").bigAutocomplete({
		        width:200,
		        data:data,
		        callback:function(data){
		            $("#brandName").val(data.brandName); 
		            $("#brandId").val(data.brandId);
		        }
		    });
			//$(".none_global").hide();
			$("#warehouseType").change(function() {
				var warehouseType = $("#warehouseType").val();
				if (warehouseType == 'TAX_WAREHOUSE') {
					$(".huanguanGuojian").show();
				} else {
					$(".huanguanGuojian").hide();
				}
			});
		});
		//二级联动
		$('#oneGoodsCategoryId').click(function(){
		 var parentId =$("#oneGoodsCategoryId").val();
		 $("#twogoodsCategoryId option").remove();
		 $("#threeGoodsCategoryId option").remove();
      	  $.ajax({
 		     type: 'GET',
 		     url: '/categories/getPreLevel',
 		     data: {'parentId':parentId},
 		     dataType: 'json',
 		     success : function(data) {
                if(data.code==200){
                	 $.each(data.data.preList, function(key, val) {
                		 var $option = $("<option></option>");
                			$option.attr("value",val.categoryId);
                			$option.text(val.categoryName);
                			console.info( $("#twogoodsCategoryId") )
                			$("#twogoodsCategoryId").append($option);
                		 
                	 })
                }else{
             	   
             	   alert(data.msg);
                }
			     },
			    error : function() {
				    alert("系统异常");
			}

 		   }); 
		})
		
		//三级联动
		$('#twogoodsCategoryId').click(function(){
			//alert(111);
			 var parentId =$("#twogoodsCategoryId").val();
			 $("#threeGoodsCategoryId option").remove();
	      	  $.ajax({
	 		     type: 'GET',
	 		     url: '/categories/getPreLevel',
	 		     data: {'parentId':parentId},
	 		     dataType: 'json',
	 		     success : function(data) {
	                if(data.code==200){
	                	 $.each(data.data.preList, function(key, val) {
	                		 var $option = $("<option></option>");
	                			$option.attr("value",val.categoryId);
	                			$option.text(val.categoryName);
	                			console.info( $("#threeGoodsCategoryId") )
	                			$("#threeGoodsCategoryId").append($option);
	                		 
	                	 })
	                }else{	             	   
	             	   alert(data.msg);
	                }
				     },
				    error : function() {
					    alert("系统异常");
				}

	 		   }); 
		})
		
		
	</script>
</body>
</html>