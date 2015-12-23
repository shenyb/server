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
        <link rel="stylesheet" href="/resources/css/ops-addkol.css" >
        <link rel="stylesheet" href="/resources/css/sidebar.css" >
        <link rel="stylesheet" href="/resources/css/content-header.css" >
        <!-- <link rel="stylesheet" href="/resources/css/datapage.css" > -->
       <link rel="stylesheet" href="/resources/css/profile-table.css"> 
       <link rel="stylesheet" href="/resources/css/modal.css">  
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
                        <div class="form-page-wrap">
                            <div class="data-page-inner">
                                <div class="page-head">                                  
                                    <!--面包屑导航-->
                                    <div class="state-information">
                                        <ol class="breadcrumb m-b-less bg-less">
                                            <li><a href="#">主页</a></li>
                                            <li><a href="#">团便宜管理</a></li>
                                            <li class="active">新增团便宜</li>
                                        </ol>
                                    </div>
                                </div>
                                <div class="info">
					            <div class="info-top">
					                <span>新增团便宜</span>
					                
					                <div class="back">
					                	
					                    <a href="javascript :;" onclick="javascript :history.back(-1);" class="button button-rounded button-tiny">返回</a>				                 			                   
					                </div>
					            </div>
				           		<form  action=""   method="post"  id="cheapForm"  name="myForm" >
																
									<table class="table table-bordered table-hover table-condensed table-responsive">
									<tr class="table-title">
							         	<td colspan="4">
							         	商品信息
							         	<a class="button button-action button-raised button-small" href="javascript:;" onclick="openmodel()">
	                                       <i class="fa fa-plus"></i>
	                                       <span>选择商品</span>
	                                    </a>
							         	</td>												
									</tr>
										<tbody><tr style="display:none;">
											<td>商品编号</td>
											<td class="ng-binding"><input type="text" id="goodsCode" name="goodsCode"  ></td>
											<td></td>
										</tr>
										<tr style="display:none;">
											<td>商品ID</td>
											<td class="ng-binding"  ><input type="text" id="goodsId" name="goodsId"  ></td>
											<td></td>
										</tr>
										<tr>
											<td>商品名称</td>
											<td class="ng-binding" id="show-goodsName"></td>
											<td><input type="text" id="goodsName" name="goodsName"  style="display:none;"></td>
										</tr>
										<tr>
											<td>市场价</td>
											<td class="ng-binding" id="show-onsalePrice"></td>
											<td><input type="text" id="onsalePrice" name="onsalePrice"  style="display:none;"></td>
										</tr>
										<tr>
											<td>仓库类型</td>
											
											<td id="show-warehouseType"></td>
											<td id="form-mobile"><input type="text" id="warehouseType" name="warehouseType"  style="display:none;"></td>
										</tr>
										<tr>
											<td>商品推荐语</td>
											<td colspan="3">
												<textarea name="goodsBrief"  required class="form-control ng-pristine ng-untouched ng-invalid ng-invalid-required"></textarea>
											</td>
											
										</tr>
										
									</tbody></table>
									
										<table class="table table-bordered table-hover table-condensed table-responsive">
										<tr class="table-title">
								         	<td colspan="4">
								         	团便宜信息
								         	</td>												
										</tr>
										<tbody>
										<tr>
											<td>成团人数</td>
											<td>
												<input type="text" class="form-control"  name="cheapCount" id="cheapCount" style="ime-mode:disabled" onKeyUp="this.value=this.value.replace(/[^\.\d]/g,'');this.value=this.value.replace('.','');" />
											</td>
											<td>成团价格</td>
											<td class="form-group small-group">
												<input type="text" class="form-control"  name="cheapPrice" id="cheapPrice" style="ime-mode:disabled" onKeyUp="this.value=this.value.replace(/[^\.\d]/g,'');" />
												<em class="cake">元</em>
											</td>
										</tr>
										
										<tr>
											<td>日期范围</td>
											<td colspan="3">
											<div class="col-xs-5">
											<b>开始时间</b>
											<input id="d4311" class="form-control" name="startTimeString" type="text" onFocus="WdatePicker({minDate:'%y-%M-#{%d}  HH:mm:ss',firstDayOfWeek:1,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})"/> 
	                                        </div>
	                                        <div class="col-xs-5">
	                                        <b>结束时间</b>
	                                        <input id="d4312" class="form-control" name="endTimeString" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'2020-10-01'})"/>
											</div>
											</td>
											
										</tr>
										<tr>
											<td>持续时间</td>
											<td class="form-group small-group">
											<input type="text" class="form-control"  name="duringTime" id="duringTime" style="ime-mode:disabled" onKeyUp="this.value=this.value.replace(/[^\.\d]/g,'');this.value=this.value.replace('.','');" />
											<em class="cake">小时</em>
											</td>
											
										</tr>
										<tr>
											<td>团便宜列表图</td>
											<td colspan="3">
												<div id="imageUpload">
													<!-- ngRepeat: img in imageList -->
													<img alt="pic" style="display:none;"  id="kolCategoryImg" src="" />
													<input type="text" id="fileName" name="cheapPicKey" style="visibility:hidden" />
												</div>
												<input id="mainpic" data-url="/publicImageUpload" type="file" name="files" />												                    
											</td>
											
										</tr>
										<tr>
											<td>活动说明</td>
											<td colspan="3">
												<textarea  name="cheapDescription"  required class="form-control ng-pristine ng-untouched ng-invalid ng-invalid-required"></textarea>
											</td>											
										</tr>										
									</tbody>
									</table>									
										<table class="table table-bordered table-hover table-condensed table-responsive">
										<tr class="table-title">
								         	<td colspan="4">
								         	微信分享设置
								         	</td>												
										</tr>
										<tbody>
										<tr>
											<td>分享标题</td>
											<td class="ng-binding"><input type="text" class="form-control" name="cheapShareTitle"  ></td>
											<td>分享内容</td>
											<td class="ng-binding"><input type="text" class="form-control" name="cheapShareContent" ></td>
										</tr>
										<tr>
											<td colspan="4">
											<a class="button button-primary button-raised button-small pull-right " href="javascript:;" id=save>保存草稿</a>
											<a class="button button-primary button-raised button-small pull-right " href="javascript:;" id=submitAudit>提交审核</a>
											</td>
										</tr>							
									</tbody></table> 
									<!-- <div class="bops-pub-param-tips-line" style="margin: 14px 0;"></div>
									<div class="bops-pub-param-btnlist">
										<a class="bops-pub-btn bops-pub-param-btn" href="javascript:;" id=save>保存草稿</a>
										<a class="bops-pub-btn bops-pub-param-btn" href="javascript:;" id=submitAudit>提交审核</a>
									</div> -->
								</form>
							 </div>
							
                                <%-- <!-- 2222222222222222 -->
                                <div class="wrapper">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <section class="panel">
                                                <header class="panel-heading ">
                                                    新增团便宜
                                                    <span class="tools pull-right">
															<a href="javascript :;" onclick="javascript :history.back(-1);" class="ops-pub-btn">
																<i class="fa fa-arrow-left"></i>
																<span>返回</span>
															</a>
															 
                                                    </span>
                                                </header>
												<div class="bops-pub-wrap-inner">
													<a class="bops-pub-btn" href="javascript:;" onclick="openmodel()">
				                                        <i class="fa fa-plus"></i>
				                                        <span>选择商品</span>
			                                            </a>
													<div class="bops-pub-body">
														<div class="bops-pub-param-body">
															
															<form  action=""   method="post"  id="cheapForm"  name="myForm" >
																<div class="bops-pub-param-header">
																	<span class="bops-pub-param-title">
																		商品信息
																	</span>
																</div>
																<table class="bops-pub-param-table">
																	<tbody><tr style="display:none;">
																		<th>商品编号</th>
																		<td class="ng-binding"><input type="text" id="goodsCode" name="goodsCode"  ></td>
																		<td></td>
																	</tr>
																	<tr style="display:none;">
																		<th>商品ID</th>
																		<td class="ng-binding"  ><input type="text" id="goodsId" name="goodsId"  ></td>
																		<td></td>
																	</tr>
																	<tr>
																		<th style="vertical-align: top;">商品名称</th>
																		<td class="ng-binding" id="show-goodsName"></td>
																		<td><input type="text" id="goodsName" name="goodsName"  style="display:none;"></td>
																	</tr>
																	<tr>
																		<th style="vertical-align: top;">市场价</th>
																		<td class="ng-binding" id="show-onsalePrice"></td>
																		<td><input type="text" id="onsalePrice" name="onsalePrice"  style="display:none;"></td>
																	</tr>
																	<tr>
																		<th>仓库类型</th>
																		
																		<td  style="vertical-align: top;" id="show-warehouseType"></td>
																		<td id="form-mobile" style="vertical-align: top;"><input type="text" id="warehouseType" name="warehouseType"  style="display:none;"></td>
																	</tr>
																	<tr>
																		<th style="vertical-align: top;">商品推荐语</th>
																		<td>
																			<textarea name="goodsBrief"  required="" class="ng-pristine ng-untouched ng-invalid ng-invalid-required"></textarea>
																		</td>
																		<td style="vertical-align: top;"></td>
																	</tr>
																	
																</tbody></table>
																<div class="bops-pub-param-header">
																	<span class="bops-pub-param-title">
																		团便宜信息
																	</span>
																</div>
																<table class="bops-pub-param-table">
																	<tbody><tr>
																		<th style="vertical-align: top;">成团人数</th>
																		<td>
																			<input type="text" name="cheapCount" id="cheapCount" style="ime-mode:disabled" onKeyUp="this.value=this.value.replace(/[^\.\d]/g,'');this.value=this.value.replace('.','');" />
																		</td>
																		<td style="vertical-align: top;"></td>
																	</tr>
																	<tr>
																		<th style="vertical-align: top;">成团价格</th>
																		<td>
																			<input type="text" name="cheapPrice" id="cheapPrice" style="ime-mode:disabled" onKeyUp="this.value=this.value.replace(/[^\.\d]/g,'');" />元
																		</td>
																		<td style="vertical-align: top;"></td>
																	</tr>
																	<tr>
																		<th style="vertical-align: top;">日期范围</th>
																		<td>
																		<input id="d4311" class="Wdate" name="startTimeString" placeholder="开始时间"  type="text" onFocus="WdatePicker({minDate:'%y-%M-#{%d}  HH:mm:ss',firstDayOfWeek:1,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})"/> 
                                                                        <input id="d4312" class="Wdate" name="endTimeString" placeholder="截止时间" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'2020-10-01'})"/>
																		</td>
																		<td style="vertical-align: top;"></td>
																	</tr>
																	<tr>
																		<th style="vertical-align: top;">持续时间</th>
																		<td class="ng-binding">
																		<input type="text" name="duringTime" id="duringTime" style="ime-mode:disabled" onKeyUp="this.value=this.value.replace(/[^\.\d]/g,'');this.value=this.value.replace('.','');" />小时
																		</td>
																		<td></td>
																	</tr>
																	<tr>
																		<th style="vertical-align: top;">团便宜列表图</th>
																		<td>
																			<div id="imageUpload">
																				<!-- ngRepeat: img in imageList -->
																				<img alt="pic" style="width: 37%;display:none;"  id="kolCategoryImg" src="" />
																				<input type="text" id="fileName" name="cheapPicKey" style="visibility:hidden" />
																			</div>
																			<input id="mainpic" data-url="/publicImageUpload" type="file" name="files" />
												
														                    
																		</td>
																		<td style="vertical-align: top;"></td>
																	</tr>
																	<tr>
																		<th style="vertical-align: top;">活动说明</th>
																		<td>
																			<textarea  name="cheapDescription"  required="" class="ng-pristine ng-untouched ng-invalid ng-invalid-required"></textarea>
																		</td>
																		<td style="vertical-align: top;"></td>
																	</tr>
																	
																</tbody></table>
																<div class="bops-pub-param-header">
																	<span class="bops-pub-param-title">
																		微信分享设置：
																	</span>
																</div>
																<table class="bops-pub-param-table">
																	<tbody><tr>
																		<th>分享标题</th>
																		<td class="ng-binding"><input type="text" name="cheapShareTitle"  ></td>
																		<td></td>
																	</tr>
																	<tr>
																		<th style="vertical-align: top;">分享内容</th>
																		<td class="ng-binding"><input type="text" name="cheapShareContent" ></td>
																		<td></td>
																	</tr>
																	
																	
																</tbody></table>
																<div class="bops-pub-param-tips-line" style="margin: 14px 0;"></div>
																<div class="bops-pub-param-btnlist">
																	<a class="bops-pub-btn bops-pub-param-btn" href="javascript:;" id=save>保存草稿</a>
																	<a class="bops-pub-btn bops-pub-param-btn" href="javascript:;" id=submitAudit>提交审核</a>
																</div>
															</form>
														</div>	
													</div>
												</div>
                                            </section>
                                        </div>
                                    </div>
                                </div>
                            	<!-- 2222 222222222 -->     --%>
                            	
                            	
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
        
        
         <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content">
		         <div class="modal-header">
		            <button type="button" class="close"
		               data-dismiss="modal" aria-hidden="true">
		                  &times;
		            </button>
		            <h4 class="modal-title" id="myModalLabel">
		               选择商品
		            </h4>
		         </div>
		         <div class="modal-body">
				    <!-- form表单或各种input -->
				    <form class="form-horizontal">
				    	<fieldset>
					    	<div class="control-group">
	                              <!-- Text input-->
	                              <label class="control-label col-lg-4" for="search-goodsCode">商品编码</label>                              
	                              <div class="controls col-lg-5">	                                
	                              	 <input type="text" id="search-goodsCode"  class="form-control ng-pristine ng-valid ng-touched">
	                              </div>
	                              <div style="clear: both;"></div>
	                        </div>
	                        <div class="control-group">
	                              <!-- Text input-->
	                              <label class="control-label col-lg-4" for="search-goodsName">商品名称</label>                              
	                              <div class="controls col-lg-5">	                                
	                              	<input type="text" id="search-goodsName" class="form-control ng-pristine ng-valid ng-touched">
	                              </div>
	                              <div style="clear: both;"></div>
	                        </div>
	                        <div class="control-group">
	                              <a id="search-btn" class=" button-primary button-raised button-small pull-right "" onclick="SearchGoods()" style="vertical-align:middle;margin-top:-2px;">
									<span id="search-btn-text">搜索</span>
									<div id="search-loading" class="spinner search-loading hide">
										
									</div>
								</a>
	                        </div>
	                     </fieldset>
	                  </form>
	                </div>
		         <div class="bops-pub-dialog-inner">

						<div class="bops-pub-dialog-body">
							<!-- <div class="bops-pub-param-seach">
								<input type="text" placeholder="请输入商品编码进行查询" id="search-goodsCode" style="vertical-align: 1px;
									   		  width: 200px;
									   		  padding-left: 18px;
									   		  display:inline-block;
									   		  height:32px" class="ng-pristine ng-valid ng-touched">
								<input type="text" placeholder="请输入商品名称进行查询" id="search-goodsName" style="vertical-align: 1px;
									   		  width: 200px;
									   		  padding-left: 18px;
									   		  display:inline-block;
									   		  height:32px" class="ng-pristine ng-valid ng-touched">
								<a id="search-btn" class="bops-pub-btn" onclick="SearchGoods()" style="vertical-align:middle;margin-top:-2px;">
									<span id="search-btn-text">搜索</span>
									<div id="search-loading" class="spinner search-loading hide">
										
									</div>
								</a>
								
							</div> -->
							<div id="goodsList"  style="height:387px;overflow-y:auto;display:none">
								<table class="bops-pub-table">
									<thead>
										<tr>
											<th>
												
											</th>
											<th>商品主图</th>
											<th>商品编号</th>
											<th>商品名称</th>
											<th>商品原价</th>
											<th>商品折扣价</th>
										</tr>
									</thead>
									<tbody id="goodsListTable">
										<!-- ngRepeat: data in goodsList -->
									</tbody>
								</table>
							</div>
						</div>
					</div>
					</div>
		        <!--  <div class="modal-footer">
		            <button type="button" class="btn btn-default"
		               data-dismiss="modal">取消
		            </button>
		            <button type="button" onclick="submit()"  class="btn btn-primary">
		               提交
		            </button>
		         </div> -->
		      </div><!-- /.modal-content -->
		</div><!-- /.modal -->
        <!-- 在 body的最底部引入js文件且一定保持 jquery 在 bootstrap 之前引入 -->
        <script src="/resources/js/jquery/jquery-2.1.4.min.js"></script>
        <script src="/resources/js/bootstrap/bootstrap.min.js"></script>
        <script src="/resources/js/jquery/jquery.bootstrap.min.js"></script>
        <script src="/resources/js/My97DatePicker/WdatePicker.js"></script>
        <!-- 侧面导航栏组件 js -->
        <script src="/resources/js/sidebar.js"></script>
        <script src="/resources/js/jquery.pagination.js"></script>
        
        <script src="/resources/js/jquery_upload/vendor/jquery.ui.widget.js"></script>
        <script src="/resources/js/jquery_upload/jquery.iframe-transport.js"></script>
        <script src="/resources/js/jquery_upload/jquery.fileupload.js"></script>
        
        <script type="text/javascript">
      /*   function save(url){
        	
        document.myform.action =url;
        $("#addExpertclassify").submit();	
        }
        function submitAudit(url){
        	document.myform.action =url;
            $("#addExpertclassify").submit();	
            } */
            $("#save").click(function () {
            	var count=$("#cheapCount").val();
            	if(count<2){
            		alert("参团人数不能小于2");
            		return;
            	}
                var data = $("#cheapForm").serialize();
                $.ajax({
                    type: "POST",
                    url: "/cheap/save",
                    data: data,
                    cache: false,
                    dataType: 'json',
                    success: function (data) {
                        if (data.code == 200) {
                            window.location.href = "/cheap/page";
                        } else {
                            alert("code: " + data.code + "\n" + data.msg);
                        }
                    },
                    error: function(XMLHttpRequest, textStatus, errorThrown) {
                        alert(errorThrown);
                    }
                });
            });
            $("#submitAudit").click(function () {
            	var count=$("#cheapCount").val();
            	if(count<2){
            		alert("参团人数不能小于2");
            		return;
            	}
                var data = $("#cheapForm").serialize();
                $.ajax({
                    type: "POST",
                    url: "/cheap/add",
                    data: data,
                    cache: false,
                    dataType: 'json',
                    success: function (data) {
                        if (data.code == 200) {
                            window.location.href = "/cheap/page";
                        } else {
                            alert("code: " + data.code + "\n" + data.msg);
                        }
                    },
                    error: function(XMLHttpRequest, textStatus, errorThrown) {
                        alert(errorThrown);
                    }
                });
            });
        
        function unselectall(goods){
        	$('#goodsCode').val($(goods).attr("data-goodscode"));
        	$('#show-goodsName').html($(goods).attr("data-goodsName"));
        	$('#goodsName').val($(goods).attr("data-goodsName"));
        	$('#onsalePrice').val($(goods).attr("data-onsalePrice"));
        	$('#show-onsalePrice').html($(goods).attr("data-onsalePrice"));
        	$('#warehouseType').val($(goods).attr("data-warehouseType"));
        	
        	if($(goods).attr("data-warehouseType")=="TAX_WAREHOUSE"){
        	$('#show-warehouseType').html("保税仓");        		
        	}
        	if($(goods).attr("data-warehouseType")=="SELF_WAREHOUSE"){
            $('#show-warehouseType').html("自营仓");        		
            }
        	if($(goods).attr("data-warehouseType")=="OVERSEA_WAREHOUSE"){
            $('#show-warehouseType').html("海外直邮");        		
            }
        	$('#goodsId').val($(goods).attr("data-goodsId"));
        	$('#myModal').modal('hide') 
        }
      
        
        function  openmodel(){
        	
        	$('#myModal').modal('show')          
   	      }  
        function SearchGoods(){
        	  $.ajax({
	    		     type: 'get',
	    		     data:{
	    		    	 goodsCode:$("#search-goodsCode").val(),
	    		    	 goodsName:$("#search-goodsName").val(),
	    		     },
	    		     url: '/goods',
	    		     dataType: 'json',
	    		    success : function(data) {
	    		    	var result="";
	    		    	var len=data.data.list.length;
	    		    	 for(var i=0;i<=len-1;i++){
	    		    		 result+="<tr ng-repeat='data in goodsList' class='ng-scope'><td style='vertical-align:middle'><input type='checkbox' name='Fruit' onclick='unselectall(this)'  data-goodscode='"+data.data.list[i].goodsCode+"' data-goodsName="+data.data.list[i].goodsName+" data-warehouseType='"+data.data.list[i].warehouseType+"' data-onsalePrice='"+data.data.list[i].onsalePrice+"' data-goodsId='"+data.data.list[i].goodsId+"' ></td><td style='vertical-align:middle'><img ng-src='"+data.data.picDomain+data.data.list[i].scenePicKey+"' style='width:48px;' src='"+data.data.picDomain+data.data.list[i].scenePicKey+"'></td><td style='vertical-align:middle' class='ng-binding'>"+data.data.list[i].goodsCode+"</td><td style='vertical-align:middle' class='ng-binding'>"+data.data.list[i].goodsName+"</td><td style='vertical-align:middle' class='ng-binding'>"+data.data.list[i].onsalePrice+"</td><td style='vertical-align:middle' class='ng-binding'>"+data.data.list[i].discountPrice+"</td></tr>"
	    		    	 }
	    		    	 $("#goodsListTable").html(result);
	    		    	 $("#goodsList").css("display","block")
	    		    	
	   			     },
	   			    error : function() {
	   			} 

	    		   });
     	   
        }
/*         function submit(){
        	$('#goodsCode').val($("#goodsRadio").attr("data-goodscode"));
        	$('#goodsName').val($("#goodsRadio").attr("data-goodsName"));
        	$('#onsalePrice').val($("#goodsRadio").attr("data-onsalePrice"));
        	$('#warehouseType').val($("#goodsRadio").attr("data-warehouseType"));
        } */
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
              if(jsonObj.code==7){
            	  alert("不符合上传图片规格");
            	  return;
              }
		    	$('#fileName').val(jsonObj.data.fileNames[0]);
		    	$('#kolCategoryImg').attr("src",jsonObj.data.imgurl+jsonObj.data.fileNames[0]);
		    	$("#kolCategoryImg").css('display', 'block');
            }
        })
         $("#shareImgUpLoad").fileupload({
            // formData:{param1:"p1",param2:"p2"},//如果需要额外添加参数可以在这里添加
            done:function(e,result){
                //done方法就是上传完毕的回调函数，其他回调函数可以自行查看api
                //注意result要和jquery的ajax的data参数区分，这个对象包含了整个请求信息
                //返回的数据在result.result中，假设我们服务器返回了一个json对象
                console.log(JSON.stringify(result.result));
             //   alert(JSON.stringify(result.result));
                
                var jsonObj=$.parseJSON(JSON.stringify(result.result));
              //  alert(JSON.stringify(jsonObj.data.fileNames[0]));
		    	$('#cheapSharePicKey').val(jsonObj.data.fileNames[0]);
		    	$('#shareImg').attr("src",jsonObj.data.imgurl+jsonObj.data.fileNames[0]);
		    	$("#shareImg").css('display', 'block');
            }
        })
          
         </script>
    </body>
</html>