<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <link rel="stylesheet" href="/resources/css/profile-table.css"> 
       <!--  <link rel="stylesheet" href="/resources/css/datapage.css" > -->
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
                        <div class="form-page-wrap">
                            <div class="data-page-inner">
                                <div class="page-head">
                                   
                                    <!--面包屑导航-->
                                    <div class="state-information">
                                        <ol class="breadcrumb m-b-less bg-less">
                                            <li><a href="#">主页</a></li>
                                            <li><a href="#">团便宜管理</a></li>
                                            <li class="active">编辑团便宜</li>
                                        </ol>
                                    </div>
                                </div>
                                 <div class="info">
					            <div class="info-top">
					                <span>编辑团便宜</span>					                
					                <div class="back">					                	
					                    <a href="javascript :;" onclick="javascript :history.back(-1);" class="button button-primary button-raised button-small">返回</a>				                 			                   
					                </div>
					            </div>
				           		<form  action=""   method="post"  id="cheapForm"  name="myForm" >																
									<table class="table table-bordered table-hover table-condensed table-responsive">
									<tr class="table-title">
							         	<td colspan="4">
							         	商品信息							         	
							         	</td>												
									</tr>
										<tr>
											<td>商品ID</td>
											<td class="ng-binding" colspan="3">${cheap.goodsId }
											<input type="text" style=" display:none;" id="goodsId" name="goodsId" value="${cheap.goodsId }" ></td>
										</tr>
										<tr>
											<td>商品名称</td>
											<td class="ng-binding" colspan="3">${cheap.goodsName }
											<input type="text" style=" display:none;" id="goodsName" name="goodsName" value="${cheap.goodsName }"></td>
										</tr>
										
										<tr>
											<td>市场价</td>
											<td class="ng-binding">${cheap.onsalePrice }
											<input type="text" style=" display:none;" id="onsalePrice" name="onsalePrice" value="${cheap.onsalePrice }" ></td>
											<td>仓库类型</td>
											<td id="form-mobile">
												<c:if test="${cheap.warehouseType=='TAX_WAREHOUSE'}">
												  保税仓
												</c:if>
												<c:if test="${cheap.warehouseType=='SELF_WAREHOUSE'}">
												  自营仓
												</c:if>
												<c:if test="${cheap.warehouseType=='OVERSEA_WAREHOUSE'}">
												  海外直邮
												</c:if>
												<input type="text" style=" display:none;" id="warehouseType" name="warehouseType" value="${cheap.warehouseType }">
											</td>
										</tr>
										
										<tr>
											<td>商品推荐语</td>
											<td colspan="3">
												<textarea name="goodsBrief" value="${cheap.goodsBrief }" required="sdfsd"  class="form-control ng-pristine ng-untouched ng-invalid ng-invalid-required">${cheap.goodsBrief }</textarea>
											</td>											
										</tr>								
										<tr class="table-title">
								         	<td colspan="4">
								         	团便宜信息
								         	</td>												
										</tr>								
										<tr>
											<td>成团人数</td>
											<td>
												<input type="text" name="cheapCount" class="form-control" value="${cheap.cheapCount }" id="cheapCount" class="form-control" 
												style="ime-mode:disabled" onKeyUp="this.value=this.value.replace(/[^\.\d]/g,'');this.value=this.value.replace('.','');" />
												<input type="text" name="cheapNo" value="${cheap.cheapNo}" style="display:none"/>
											</td>
											<td>成团价格</td>
											<td class="form-group small-group">
												<input type="text" name="cheapPrice" class="form-control" value="${fn:substring(cheap.cheapPrice,0,fn:length(cheap.cheapPrice)-2)}" id="cheapPrice" 
												style="ime-mode:disabled" onKeyUp="this.value=this.value.replace(/[^\.\d]/g,'');" />
												<em class="cake">元</em>
											</td>
										</tr>										
										<tr>
											<td>日期范围</td>
											<td colspan="3">
											<div class="col-xs-5">
											<b>开始时间</b>
											<input id="d4311" class="form-control" name="startTimeString"  value="<fmt:formatDate value="${cheap.startTime }" type="both" />"
											  type="text" onFocus="WdatePicker({minDate:'%y-%M-#{%d}  HH:mm:ss',firstDayOfWeek:1,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})"/> 
	                                        </div>
	                                        <div class="col-xs-5">
	                                    	
	                                    	<b>结束时间</b>
	                                        <input id="d4312" class="form-control" name="endTimeString"  value="<fmt:formatDate value="${cheap.endTime }" type="both" />"  
	                                        type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'2020-10-01'})"/>
											</div>
											</td>
											
										</tr>
										<tr>
											<td>持续时间</td>
											<td class="form-group small-group">
												<input type="text" name="duringTime" value="${cheap.duringTime}" id="duringTime" style="ime-mode:disabled" class="form-control" 
												onKeyUp="this.value=this.value.replace(/[^\.\d]/g,'');this.value=this.value.replace('.','');" />
												<em class="cake">小时</em>
											</td>
										
											
										</tr>
										<tr>
											<td>团便宜列表图</td>
											<td colspan="3">
												<div id="imageUpload">
													<!-- ngRepeat: img in imageList -->
													<img alt="pic"  id="kolCategoryImg" src="${imgUrl}${cheap.cheapPicKey }" />
													<input type="text" id="fileName" name="cheapPicKey" value="${cheap.cheapPicKey }" style="visibility:hidden" />
												</div>
												<input id="mainpic" data-url="/publicImageUpload" type="file" name="files" />											                    
											</td>											
										</tr>
										<tr>
											<td>活动说明</td>
											<td colspan="3">
												<textarea  name="cheapDescription"  value="${cheap.cheapDescription }" required="" 
												class="form-control ng-pristine ng-untouched ng-invalid ng-invalid-required">${cheap.cheapDescription }</textarea>
											</td>											
										</tr>																			
										<tr class="table-title">
								         	<td colspan="4">
								         	微信分享设置
								         	</td>												
										</tr>									
										<tr>
											<td>分享标题</td>
											<td class="ng-binding"><input type="text" class="form-control" name="cheapShareTitle" value="${cheap.cheapShareTitle }" ></td>
											<td>分享内容</td>
											<td class="ng-binding"><input type="text" class="form-control" name="cheapShareContent" value="${cheap.cheapShareContent }" ></td>
										</tr>																
										<tr>
								         	<td colspan="4">
								         	<a class="button button-primary button-raised button-small pull-right" href="javascript:;" id=save>保存草稿</a>
											<a class="button button-primary button-raised button-small pull-right" href="javascript:;" id=submitAudit>提交审核</a>
								         	</td>												
										</tr>
									</table> 									
								</form>
							 </div>
							
                                <%-- <!--222222222222  -->
                                <div class="wrapper">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <section class="panel">
                                                <header class="panel-heading ">
                                                    编辑团便宜
                                                    <span class="tools pull-right">
															<a href="javascript :;" onclick="javascript :history.back(-1);" class="ops-pub-btn">
																<i class="fa fa-arrow-left"></i>
																<span>返回</span>
															</a>
															 
                                                    </span>
                                                </header>
												<div class="bops-pub-wrap-inner">
													
													<div class="bops-pub-body">
														<div class="bops-pub-param-body">
															
															<form  action=""   method="post"  id="cheapForm"  name="myForm" >
																<div class="bops-pub-param-header">
																	<span class="bops-pub-param-title">
																		商品信息
																	</span>
																</div>
																<table class="bops-pub-param-table">
																	<tbody>
																	<tr>
																		<th>商品ID</th>
																		<td class="ng-binding">${cheap.goodsId }</td>
																		<td><input type="text" style=" display:none;" id="goodsId" name="goodsId" value="${cheap.goodsId }" ></td>
																	</tr>
																	<tr>
																		<th style="vertical-align: top;">商品名称</th>
																		<td class="ng-binding">${cheap.goodsName }</td>
																		<td><input type="text" style=" display:none;" id="goodsName" name="goodsName" value="${cheap.goodsName }"></td>
																	</tr>
																	<tr>
																		<th style="vertical-align: top;">市场价</th>
																		<td class="ng-binding">${cheap.onsalePrice }</td>
																		<td><input type="text" style=" display:none;" id="onsalePrice" name="onsalePrice" value="${cheap.onsalePrice }" ></td>
																	</tr>
																	<tr>
																		<th>仓库类型</th>
																		
																		<td id="form-mobile" style="vertical-align: top;">
																		<c:if test="${cheap.warehouseType=='TAX_WAREHOUSE'}">
																		  保税仓
																		</c:if>
																		<c:if test="${cheap.warehouseType=='SELF_WAREHOUSE'}">
																		  自营仓
																		</c:if>
																		<c:if test="${cheap.warehouseType=='OVERSEA_WAREHOUSE'}">
																		  海外直邮
																		</c:if></td>
																		<td><input type="text" style=" display:none;" id="warehouseType" name="warehouseType" value="${cheap.warehouseType }"></td>
																	</tr>
																	<tr>
																		<th style="vertical-align: top;">商品推荐语</th>
																		<td>
																			<textarea name="goodsBrief" value="${cheap.goodsBrief }" required="sdfsd"  class="ng-pristine ng-untouched ng-invalid ng-invalid-required">${cheap.goodsBrief }</textarea>
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
																			<input type="text" name="cheapCount" value="${cheap.cheapCount }" id="cheapCount" style="ime-mode:disabled" onKeyUp="this.value=this.value.replace(/[^\.\d]/g,'');this.value=this.value.replace('.','');" />
																		</td>
																		<td style="vertical-align: top;"><input type="text" name="cheapNo" value="${cheap.cheapNo}" style="display:none"/></td>
																	</tr>
																	<tr>
																		<th style="vertical-align: top;">成团价格</th>
																		<td>
																			<input type="text" name="cheapPrice" value="${fn:substring(cheap.cheapPrice,0,fn:length(cheap.cheapPrice)-2)}" id="cheapPrice" style="ime-mode:disabled" onKeyUp="this.value=this.value.replace(/[^\.\d]/g,'');" />元
																		</td>
																		<td style="vertical-align: top;"></td>
																	</tr>
																	<tr>
																		<th style="vertical-align: top;">日期范围</th>
																		<td>
																		<input id="d4311" class="Wdate" name="startTimeString"  value="<fmt:formatDate value="${cheap.startTime }" type="both" />"  placeholder="开始时间"  type="text" onFocus="WdatePicker({minDate:'%y-%M-#{%d}  HH:mm:ss',firstDayOfWeek:1,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})"/> 
                                                                        <input id="d4312" class="Wdate" name="endTimeString"  value="<fmt:formatDate value="${cheap.endTime }" type="both" />"  placeholder="截止时间" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'2020-10-01'})"/>
																		</td>
																		<td style="vertical-align: top;"></td>
																	</tr>
																	<tr>
																		<th style="vertical-align: top;">持续时间</th>
																		<td class="ng-binding">
																		<input type="text" name="duringTime" value="${cheap.duringTime}" id="duringTime" style="ime-mode:disabled" onKeyUp="this.value=this.value.replace(/[^\.\d]/g,'');this.value=this.value.replace('.','');" />小时
																		</td>
																		<td></td>
																	</tr>
																	<tr>
																		<th style="vertical-align: top;">团便宜列表图</th>
																		<td>
																			<div id="imageUpload">
																				<!-- ngRepeat: img in imageList -->
																				<img alt="pic" style="width: 37%;display:block;"  id="kolCategoryImg" src="${imgUrl}${cheap.cheapPicKey }" />
																				<input type="text" id="fileName" name="cheapPicKey" value="${cheap.cheapPicKey }" style="visibility:hidden" />
																			</div>
																			<input id="mainpic" data-url="/publicImageUpload" type="file" name="files" />
												
														                    
																		</td>
																		<td style="vertical-align: top;"></td>
																	</tr>
																	<tr>
																		<th style="vertical-align: top;">活动说明</th>
																		<td>
																			<textarea  name="cheapDescription"  value="${cheap.cheapDescription }" required="" class="ng-pristine ng-untouched ng-invalid ng-invalid-required">${cheap.cheapDescription }</textarea>
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
																		<td class="ng-binding"><input type="text" name="cheapShareTitle" value="${cheap.cheapShareTitle }" ></td>
																		<td></td>
																	</tr>
																	<tr>
																		<th style="vertical-align: top;">分享内容</th>
																		<td class="ng-binding"><input type="text" name="cheapShareContent" value="${cheap.cheapShareContent }" ></td>
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
                            	<!-- 22222222222 --> --%>
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
		            <button type="button" class="close"  data-dismiss="modal" aria-hidden="true">
		                  &times;
		            </button>
		            <h4 class="modal-title" id="myModalLabel">
		               选择商品
		            </h4>
		         </div>
		         <div class="bops-pub-dialog-inner">

						<div class="bops-pub-dialog-body">
							<div class="bops-pub-param-seach">
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
								
							</div>
							<div id="goodsList"  style="height:387px;overflow-y:auto;display:none">
								<table class="bops-pub-table">
									<thead>
										<tr>
											<th>
												
											</th>
											<th>商品主图</th>
											<th>商品编号</th>
											<th>商品名称</th>
											<th>市场价</th>
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
                    url: "/cheap/editSave?auditStatus=DRAFT",
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
                    url: "/cheap/editSave?auditStatus=INVALID",
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
        
        
         
        $("#mainpic").fileupload({
            // formData:{param1:"p1",param2:"p2"},//如果需要额外添加参数可以在这里添加
            done:function(e,result){
                //done方法就是上传完毕的回调函数，其他回调函数可以自行查看api
                //注意result要和jquery的ajax的data参数区分，这个对象包含了整个请求信息
                //返回的数据在result.result中，假设我们服务器返回了一个json对象
                console.log(JSON.stringify(result.result));
             //   alert(JSON.stringify(result.result));
                
                var jsonObj=$.parseJSON(JSON.stringify(result.result));
                if(jsonObj.code==7){
              	  alert("不符合上传图片规格");
              	  return;
                }
              //  alert(JSON.stringify(jsonObj.data.fileNames[0]));
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