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
        <!-- <link rel="stylesheet" href="/resources/css/datapage.css" > -->
        <link rel="stylesheet" href="/resources/css/profile-table.css"> 
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
                                            <li class="active">查看团便宜</li>
                                        </ol>
                                    </div>
                                </div>
                                <div class="info">
					            <div class="info-top">
					                <span>查看团便宜</span>					                
					                <div class="back">					                	
					                    <a href="javascript :;" onclick="javascript :history.back(-1);" class="button button-rounded button-tiny">返回</a>				                 			                   
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
											<td>商品编号</td>
											<td class="ng-binding">${cheap.goodsCode }</td>
											<td>商品名称</td>
											<td class="ng-binding">${cheap.goodsName }</td>
										</tr>
										
										<tr>
											<td>市场价</td>
											<td class="ng-binding">${cheap.onsalePrice }</td>
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
											</td>
										</tr>
										
										<tr>
											<td>商品推荐语</td>
											<td colspan="3">
												${cheap.goodsBrief }
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
												${cheap.cheapCount }
											</td>
											<td>成团价格</td>
											<td>
												${fn:substring(cheap.cheapPrice,0,fn:length(cheap.cheapPrice)-2)}元
											</td>
										</tr>										
										<tr>
											<td>日期范围</td>
											<td colspan="3">
											<div class="col-xs-5">
											开始时间:
											<fmt:formatDate value="${cheap.startTime }" type="both" /><br/>
	                                        </div>
	                                        <div class="col-xs-5">
	                                    	结束时间:
	                                        <fmt:formatDate value="${cheap.endTime }" type="both" />
											</div>
											</td>
											
										</tr>
										<tr>
											<td>持续时间</td>
											<td class="ng-binding">${cheap.duringTime }小时</td>
										
											
										</tr>
										<tr>
											<td>团便宜列表图</td>
											<td colspan="3">
												<div id="imageUpload">
													<!-- ngRepeat: img in imageList -->
													<img alt="pic" id="kolCategoryImg" src="${cheap.cheapPicKey }" />
												</div>											                    
											</td>
											
										</tr>
										<tr>
											<td>活动说明</td>
											<td colspan="3">
												${cheap.cheapDescription }
											</td>											
										</tr>										
									
										<tr class="table-title">
								         	<td colspan="4">
								         	微信分享设置
								         	</td>												
										</tr>
										
										<tr>
											<td>分享标题</td>
											<td class="ng-binding">${cheap.cheapShareTitle }</td>
											<td>分享内容</td>
											<td class="ng-binding">${cheap.cheapShareContent }</td>
										</tr>																
								
									</table> 									
								</form>
							 </div>
							<%--  <!--3333333333333  -->
                                <div class="wrapper">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <section class="panel">
                                                <header class="panel-heading ">
                                                    审核团便宜
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
																		<th>商品编号</th>
																		<td class="ng-binding">${cheap.goodsCode }</td>
																		<td></td>
																	</tr>
																	<tr>
																		<th style="vertical-align: top;">商品名称</th>
																		<td class="ng-binding">${cheap.goodsName }</td>
																		<td></td>
																	</tr>
																	<tr>
																		<th style="vertical-align: top;">市场价</th>
																		<td class="ng-binding">${cheap.onsalePrice }</td>
																		<td></td>
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
																		</c:if>
																		</td>
																	</tr>
																	<tr>
																		<th style="vertical-align: top;">商品推荐语</th>
																		<td>
																			${cheap.goodsBrief }
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
																			${cheap.cheapCount }
																		</td>
																		<td style="vertical-align: top;"></td>
																	</tr>
																	<tr>
																		<th style="vertical-align: top;">成团价格</th>
																		<td>
																			${fn:substring(cheap.cheapPrice,0,fn:length(cheap.cheapPrice)-2)}元
																		</td>
																		<td style="vertical-align: top;"></td>
																	</tr>
																	<tr>
																		<th style="vertical-align: top;">日期范围</th>
																		<td>
																			开始：<fmt:formatDate value="${cheap.startTime }" type="both" /><br/>截止：<fmt:formatDate value="${cheap.endTime }" type="both" />
																		</td>
																		<td style="vertical-align: top;"></td>
																	</tr>
																	<tr>
																		<th style="vertical-align: top;">持续时间</th>
																		<td class="ng-binding">${cheap.duringTime }小时</td>
																		<td></td>
																	</tr>
																	<tr>
																		<th style="vertical-align: top;">团便宜列表图</th>
																		<td>
																			<div id="imageUpload">
																				<!-- ngRepeat: img in imageList -->
																				<img alt="pic" style="width: 37%;display:block;"  id="kolCategoryImg" src="${cheap.cheapPicKey }" />
																			</div>
												
														                    
																		</td>
																		<td style="vertical-align: top;"></td>
																	</tr>
																	<tr>
																		<th style="vertical-align: top;">活动说明</th>
																		<td>
																		${cheap.cheapDescription }
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
																		<td class="ng-binding">${cheap.cheapShareTitle }</td>
																		<td></td>
																	</tr>
																	<tr>
																		<th style="vertical-align: top;">分享内容</th>
																		<td class="ng-binding">${cheap.cheapShareContent }</td>
																		<td></td>
																	</tr>
																	
																	
																</tbody></table>
																<div class="bops-pub-param-tips-line" style="margin: 14px 0;"></div>
																<div class="bops-pub-param-btnlist">
																	
																</div>
															</form>
														</div>	
													</div>
												</div>
                                            </section>
                                        </div>
                                    </div>
                                </div>
                            <!-- 333333333333333 --> --%>
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
        <script src="/resources/js/jquery/jquery.bootstrap.min.js"></script>
        <script src="/resources/js/My97DatePicker/WdatePicker.js"></script>
        <!-- 侧面导航栏组件 js -->
        <script src="/resources/js/sidebar.js"></script>
        <script src="/resources/js/jquery.pagination.js"></script>
        
        <script src="/resources/js/jquery_upload/vendor/jquery.ui.widget.js"></script>
        <script src="/resources/js/jquery_upload/jquery.iframe-transport.js"></script>
        <script src="/resources/js/jquery_upload/jquery.fileupload.js"></script>
        
        <script type="text/javascript">
     
       
         </script>
    </body>
</html>