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
        <link rel="stylesheet" href="/resources/css/bootstrap/button.css">
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
                                            <li><a href="#">行家管理</a></li>
                                            <li class="active">行家编辑</li>
                                        </ol>
                                    </div>
                                </div>
                                 <div class="info">
					            <div class="info-top">
					                <span>行家编辑</span>
					                <div class="back">
					                    <a href="javascript :;" onclick="javascript :history.back(-1);"  class="button button-rounded button-tiny">返回</a>				                 			                   
					                	<a href="" class="button button-rounded button-tiny">刷新</a>
					                </div>
					            </div>
					            
				           		<form class="form-horizontal ng-pristine ng-valid" id="addExpertclassify" name="myForm" ng-submit="add(myForm.$valid)" novalidate="">
									<table class="table table-bordered table-hover table-condensed table-responsive">
											<tr class="table-title">
									         	<td colspan="4">
									         	行家信息
									         	</td>												
											</tr>
											<tr>
									            <td class="col-xs-2">用户名</td>
						                       <td class="ng-binding">${kol.nickName}</td>
						                        <td>用户手机号</td>
						                        <td class="ng-binding">${kol.mobile}</td>                
						                    </tr>
						                    <tr>
									            <td>用户头像</td>
						                        <td colspan="2"><img ng-src="${kol.profilePicKey}" src="${kol.profilePicKey}">	</td> 						                                          
						                   		<td><input type="text" id="form-kolId" value="${kol.kolId}"  style="visibility:hidden" ></td>
						                    </tr>
						                   
											<tr>
									            <td>行家简介</td>
									            <td colspan="3">
													<textarea id="introduct" ng-model="expertDetailData.kolIntroduct" class="form-control ng-pristine ng-untouched ng-valid">${kol.kolIntroduct}</textarea>
												</td>			                                          
						                    </tr>
						                    <tr>
									            <td>认证信息</td>
						                        <td colspan="3">
													<textarea id="certification" ng-model="expertDetailData.certification" class="form-control ng-pristine ng-untouched ng-valid">${kol.certification}</textarea>
												</td>					                                          
						                    </tr>
						                    <tr>
											<td>行家类别</td>
											<td colspan="3">
												<div id="expertclassifyList">
													<!-- ngRepeat: expert in expertclassify -->
													<c:forEach items="${kolCategoryList}" var="kolCategory" varStatus="status">
													<span ng-repeat="expert in expertclassify" style="margin:0 10px 10px 0;" class="ng-binding ng-scope">
														
														 <c:set var="index" value="0" />
													 
													 <c:forEach items="${categorys}" var="categorys" varStatus="status">
													     <c:if test="${categorys==kolCategory.categoryId}">
													     <c:set var="index" value="${index+1}"/>
														</c:if>
														</c:forEach>
														<c:choose>
														<c:when test="${index>0}">  
														<input type="checkbox" name="kolCategoriesList" style="margin-right:5px" value="${kolCategory.categoryId}" ng-checked="expert.checked" checked="checked">
														</c:when>  
														<c:otherwise>
														<input type="checkbox" name="kolCategoriesList" style="margin-right:5px" value="${kolCategory.categoryId}" ng-checked="expert.checked">
														</c:otherwise>  
														</c:choose>
														${kolCategory.categoryName}
													</span><!-- end ngRepeat: expert in expertclassify -->
													</c:forEach>
												</div>
											</td>									
										</tr>
										<tr>
											<td colspan="4">
												<a class="button button-primary button-raised button-small pull-right" href="javascript:;" onclick="update()">保存</a>
												
											</td>
										</tr>											
									</table>																
								</form>
							 </div>
                                
                              <%--   <!-- 33333333333333 -->
                                <div class="wrapper">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <section class="panel">
                                                <header class="panel-heading ">
                                                    行家编辑
                                                    <span class="tools pull-right">
                                                        <a class="fa fa-repeat box-refresh" href="javascript:;"></a>
                                                        <a class="t-close fa fa-times" href="javascript:;"></a>
                                                        
                                                    </span>
                                                </header>
												<div class="bops-pub-wrap-inner">
													
													<div class="bops-pub-body">
														<div class="bops-pub-param-body">
															
															<div class="bops-pub-param-tips-line" style="margin: 14px 0;"></div>	
															
															<form id="addExpertclassify" name="myForm" ng-submit="add(myForm.$valid)" novalidate="" class="ng-pristine ng-valid">
																<div class="bops-pub-param-header">
																	<span class="bops-pub-param-title">
																		用户信息
																	</span>
																</div>
																<table class="bops-pub-param-table">
																	<tbody><tr>
																		<th>用户名</th>
																		<td class="ng-binding">${kol.nickName}</td>
																		<td></td>
																	</tr>
																	<tr>
																		<th style="vertical-align: top;">用户头像</th>
																		<td>
																			<img style="width:100px;" ng-src="${kol.profilePicKey}" src="${kol.profilePicKey}">	
																		</td>
																		<td><input type="text" id="form-kolId" value="${kol.kolId}"  style="visibility:hidden" ></td>
																	</tr>
																	<tr>
																		<th>用户手机</th>
																		<td class="ng-binding">
																			${kol.mobile}
																		</td>
																		<td style="vertical-align: top;"></td>
																	</tr>
																	
																</tbody></table>
																<div class="bops-pub-param-header">
																	<span class="bops-pub-param-title">
																		${kol.kolIntroduct}
																	</span>
																</div>
																<table class="bops-pub-param-table">
																	<tbody><tr>
																		<th style="vertical-align: top;">行家简介</th>
																		<td>
																			<textarea id="introduct" ng-model="expertDetailData.kolIntroduct" class="ng-pristine ng-untouched ng-valid">${kol.kolIntroduct}</textarea>
																		</td>
																		<td style="vertical-align: top;"></td>
																	</tr>
																	<tr>
																		<th style="vertical-align: top;">认证信息</th>
																		<td>
																			<textarea id="certification" ng-model="expertDetailData.certification" class="ng-pristine ng-untouched ng-valid">${kol.certification}</textarea>
																		</td>
																		<td style="vertical-align: top;"></td>
																	</tr>
																	<tr>
																		<th>行家类别</th>
																		<td>
																			<div id="expertclassifyList">
																				<!-- ngRepeat: expert in expertclassify -->
																				<c:forEach items="${kolCategoryList}" var="kolCategory" varStatus="status">
																				<span ng-repeat="expert in expertclassify" style="margin:0 10px 10px 0;" class="ng-binding ng-scope">
																					
																					 <c:set var="index" value="0" />
																				 
																				 <c:forEach items="${categorys}" var="categorys" varStatus="status">
																				     <c:if test="${categorys==kolCategory.categoryId}">
																				     <c:set var="index" value="${index+1}"/>
																					</c:if>
																					</c:forEach>
																					<c:choose>
																					<c:when test="${index>0}">  
																					<input type="checkbox" name="kolCategoriesList" style="margin-right:5px" value="${kolCategory.categoryId}" ng-checked="expert.checked" checked="checked">
																					</c:when>  
																					<c:otherwise>
																					<input type="checkbox" name="kolCategoriesList" style="margin-right:5px" value="${kolCategory.categoryId}" ng-checked="expert.checked">
																					</c:otherwise>  
																					</c:choose>
																					${kolCategory.categoryName}
																				</span><!-- end ngRepeat: expert in expertclassify -->
																				</c:forEach>
																			</div>
																		</td>
																		<td></td>
																	</tr>
																</tbody></table>
																<div class="bops-pub-param-tips-line"></div>
																<div class="bops-pub-param-btnlist">
																	<a class="bops-pub-btn bops-pub-param-btn" onclick="update()">保存</a>
																</div>
															</form>
														</div>	
													</div>
												</div>
                                            </section>
                                        </div>
                                    </div>
                                </div>
                           		<!-- 33333333333 --> --%>
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
        <!-- 侧面导航栏组件 js -->
        <script src="/resources/js/sidebar.js"></script>
        <script src="/resources/js/jquery.pagination.js"></script>
        <script type="text/javascript">
        
        function update(){
        	var categorys="";
        	var obj=document.getElementsByName('kolCategoriesList');
        	for(var i=0; i<obj.length; i++){
        	if(obj[i].checked){
        		categorys+=obj[i].value+',';
        	}	
        	}
        	categorys="["+categorys.substring(0, categorys.length-1)+"]";
        	var kolId=$("#form-kolId").val();
        	var introduct= $("#introduct").val();
        	var certification= $("#certification").val();
        	$.ajax({
   		     type: 'post',
   		     data:{
   		    	kolId:kolId,
   		    	kolCategories:categorys,
   		    	certification:certification,
   		    	kolIntroduct:introduct
   		     },
   		     url: '/kol/update',
   		     dataType: 'json',
   		    success : function(data) {
   		    	alert(data.msg);
   		    	if(data.code==200){
   		    	window.location.href="/kol/page";
   		    	}
  			     },
  			 error : function() {
  			    alert("error");
  			}

   		   });
        }
     
          
         </script>
    </body>
</html>