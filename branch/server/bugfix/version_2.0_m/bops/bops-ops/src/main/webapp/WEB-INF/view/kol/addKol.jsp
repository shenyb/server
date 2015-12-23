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
        <!-- <link rel="stylesheet" href="/resources/css/data page.css" > -->
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
                                    <div class="state-information">
                                        <ol class="breadcrumb m-b-less bg-less">
                                            <li><a href="#">主页</a></li>
                                            <li><a href="#">行家管理</a></li>
                                            <li class="active">行家列表</li>
                                        </ol>
                                    </div>
                                </div>
                                <div class="info">
					            <div class="info-top">
					                <span>新增行家</span>
					                <div class="back">
					                    <a href="javascript :;" onclick="javascript :history.back(-1);" class="button button-primary button-raised button-small">返回</a>				                 			                   
					                	<a href="" class="ops-pub-btn button button-primary button-raised button-small">刷新</a>
					                </div>
					            </div>
					            <div class="search-form">				                
				                    <div class="row">
					                    <div class="form-group col-xs-6">
					                        <div class="col-xs-5">请输入用户手机后点击查询</div>
					                        <input type="text" id="mobile" maxlength="11" 
										   		  class="form-control ng-valid ng-valid-maxlength ng-dirty ng-touched">
					                    </div>
					                    <div class="form-group col-xs-3">
					                        <a id="search-btn" class="button button-primary button-raised button-small" href="javascript:;" onclick="searchKol()" style="vertical-align:middle;margin-top:-5px;">
												<span id="search-btn-text" style="display: inline;"><i class="fa fa-search"></i>&nbsp;&nbsp;搜索</span>
												<div id="search-loading" class="spinner search-loading hide" style="display: none;">
													<div class="spinner-container container1">
														<div class="circle1"></div>
														<div class="circle2"></div>
														<div class="circle3"></div>
														<div class="circle4"></div>
													</div>
													<div class="spinner-container container2">
														<div class="circle1"></div>
														<div class="circle2"></div>
														<div class="circle3"></div>
														<div class="circle4"></div>
													</div>
													<div class="spinner-container container3">
														<div class="circle1"></div>
														<div class="circle2"></div>
														<div class="circle3"></div>
														<div class="circle4"></div>
													</div>
												</div>
											</a>
					                    </div>
					                 </div>   
					            </div>
					            
					            <!--  error-->
					            <div>
									<p id="error" class="hide" style="color: red; display: none;">
										<i class="fa fa-times-circle" style="margin-right:10px"></i>未查询到用户信息...
									</p>
									<p id="success" class="hide" style="color: green; display: none;">
										<i class="fa fa-check-circle" style="margin-right:10px"></i>添加成功
									</p>
									<p id="valiError" class="hide" style="color: red; display: none;">
										<i class="fa fa-times-circle" style="margin-right:10px"></i>请输入用户的电话号码进行查询...
									</p>
								</div>
					            <!--error  -->
				           		<form class="form-horizontal" id="addExpertclassify"  name="myForm"  style="display: none;">
									<table class="table table-bordered table-hover table-condensed table-responsive">
											<tr class="table-title">
									         	<td colspan="4">
									         	用户信息
									         	</td>												
											</tr>
											<tr>
									            <td>用户名</td>
						                        <td class="ng-binding" id="nickname"></td>
						                        <td>用户手机号</td>
						                        <td id="form-mobile" style="vertical-align: top;"></td>                       
						                    </tr>
						                    <tr>
									            <td>用户头像</td>
						                        <td colspan="3"><img id="profilePicKey" ng-src="" src=""><input type="text" id="form-kolId"  style="visibility:hidden" ></td> 
						                        						                                          
						                    </tr>
						                    <tr class="table-title">
									         	<td colspan="4">
									         	行家信息
									         	</td>												
											</tr>
											<tr>
									            <td>行家简介</td>
						                        <td colspan="3">
						                        <textarea name="brief" id="introduct" required="" class="form-control ng-pristine ng-untouched ng-invalid ng-invalid-required"></textarea></td> 						                                          
						                    </tr>
						                    <tr>
									            <td>认证信息</td>
						                        <td colspan="3">
						                        <textarea name="brief" id="certification"  required="" class="form-control ng-pristine ng-untouched ng-invalid ng-invalid-required"></textarea></td> 						                                          
						                    </tr>
						                    <tr>
											<td>行家类别</td>
											<td colspan="3">
												<div id="expertclassifyList">
													
												</div>
											</td>									
										</tr>	
										<tr>
											<td colspan="4">
												<a class="button button-primary button-raised button-small pull-right" href="javascript:;" onclick="add()">保存</a>
											</td>
										</tr>
												
									</table>
																												
																
								</form>
							 </div>
							 
							<!--22222222222  -->
                                <!-- <div class="wrapper">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <section class="panel">
                                                <header class="panel-heading ">
                                                    新增行家
                                                    <span class="tools pull-right">
															<a href="javascript :;" onclick="javascript :history.back(-1);" class="ops-pub-btn">
																<i class="fa fa-arrow-left"></i>
																<span>返回</span>
															</a>
															<a class="ops-pub-btn" href="" >
																<i class="fa fa-refresh"></i>
																<span>刷新</span>
															</a>
                                                    </span>
                                                </header>
												<div class="bops-pub-wrap-inner">
													
													<div class="bops-pub-body">
														<div class="bops-pub-param-body">
															<div class="bops-pub-param-seach">
																<input type="text" placeholder="请输入用户手机后点击查询" id="mobile" maxlength="11" style="vertical-align: 1px;
																	   		  width: 200px;
																	   		  padding-left: 18px;
																	   		  display:inline-block;
																	   		  height:32px" class="ng-valid ng-valid-maxlength ng-dirty ng-touched">
																<a id="search-btn" class="bops-pub-btn" href="javascript:;" onclick="searchKol()" style="vertical-align:middle;margin-top:-5px;">
																	<span id="search-btn-text" style="display: inline;">搜索</span>
																	<div id="search-loading" class="spinner search-loading hide" style="display: none;">
																		<div class="spinner-container container1">
																			<div class="circle1"></div>
																			<div class="circle2"></div>
																			<div class="circle3"></div>
																			<div class="circle4"></div>
																		</div>
																		<div class="spinner-container container2">
																			<div class="circle1"></div>
																			<div class="circle2"></div>
																			<div class="circle3"></div>
																			<div class="circle4"></div>
																		</div>
																		<div class="spinner-container container3">
																			<div class="circle1"></div>
																			<div class="circle2"></div>
																			<div class="circle3"></div>
																			<div class="circle4"></div>
																		</div>
																	</div>
																</a>
															</div>
															<div class="bops-pub-param-tips-line" style="margin: 14px 0;"></div>	
															<div>
																<p id="error" class="hide" style="color: red; display: none;">
																	<i class="fa fa-times-circle" style="margin-right:10px"></i>未查询到用户信息...
																</p>
																<p id="success" class="hide" style="color: green; display: none;">
																	<i class="fa fa-check-circle" style="margin-right:10px"></i>添加成功
																</p>
																<p id="valiError" class="hide" style="color: red; display: none;">
																	<i class="fa fa-times-circle" style="margin-right:10px"></i>请输入用户的电话号码进行查询...
																</p>
															</div>
															<form  id="addExpertclassify"  name="myForm"  style="display: none;">
																<div class="bops-pub-param-header">
																	<span class="bops-pub-param-title">
																		用户信息
																	</span>
																</div>
																<table class="bops-pub-param-table">
																	<tbody><tr>
																		<th>用户名</th>
																		<td class="ng-binding" id="nickname"></td>
																		<td></td>
																	</tr>
																	<tr>
																		<th style="vertical-align: top;">用户头像</th>
																		<td>
																			<img id="profilePicKey" style="width:100px;" ng-src="" src="">	
																		</td>
																		<td><input type="text" id="form-kolId"  style="visibility:hidden" ></td>
																	</tr>
																	<tr>
																		<th>用户手机</th>
																		
																		<td id="form-mobile" style="vertical-align: top;"></td>
																	</tr>
																	
																</tbody></table>
																<div class="bops-pub-param-header">
																	<span class="bops-pub-param-title">
																		行家信息
																	</span>
																</div>
																<table class="bops-pub-param-table">
																	<tbody><tr>
																		<th style="vertical-align: top;">行家简介</th>
																		<td>
																			<textarea name="brief" id="introduct" required="" class="ng-pristine ng-untouched ng-invalid ng-invalid-required"></textarea>
																		</td>
																		<td style="vertical-align: top;"></td>
																	</tr>
																	<tr>
																		<th style="vertical-align: top;">认证信息</th>
																		<td>
																			<textarea name="brief" id="certification"  required="" class="ng-pristine ng-untouched ng-invalid ng-invalid-required"></textarea>
																		</td>
																		<td style="vertical-align: top;"></td>
																	</tr>
																	<tr>
																		<th>行家类别</th>
																		<td>
																			<div id="expertclassifyList">
																				
																			</div>
																		</td>
																		<td></td>
																	</tr>
																</tbody></table>
																<div class="bops-pub-param-tips-line" style="margin: 14px 0;"></div>
																<div class="bops-pub-param-btnlist">
																	<a class="bops-pub-btn bops-pub-param-btn" href="javascript:;" onclick="add()">保存</a>
																</div>
															</form>
														</div>	
													</div>
												</div>
                                            </section>
                                        </div>
                                    </div>
                                </div>
                             --><!-- 22222222222 -->
                            
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
        
        function add(){
        	var categorys="";
        	var obj=document.getElementsByName('kolCategoriesList');
        	for(var i=0; i<obj.length; i++){
        	if(obj[i].checked){
        		categorys+=obj[i].value+',';
        	}	
        	}
        	categorys="["+categorys.substring(0, categorys.length-1)+"]";
        	var kolId=$("#form-kolId").val();
        	var nickname= $("#nickname").text();
        	var profilePicKey= $("#profilePicKey").attr("src");
        	var mobile= $("#form-mobile").text();
        	var introduct= $("#introduct").val();
        	var certification= $("#certification").val();
        	$.ajax({
   		     type: 'post',
   		     data:{
   		    	kolId:kolId,
   		    	mobile:mobile,
   		    	nickName:nickname,
   		    	profilePicKey:profilePicKey,
   		    	kolCategories:categorys,
   		    	certification:certification,
   		    	kolIntroduct:introduct
   		     },
   		     url: '/kol/add',
   		     dataType: 'json',
   		    success : function(data) {
   		    	if(data.code==200){
   		    		window.location.href="/kol/page";	
   		    		
   		    	}else{
   		    	  alert(data.msg);
   		    	}
   		    	
   		    
  			     },
  			 error : function() {
  			    alert("error");
  			}

   		   });
        }
        function searchKol(){
        
        	  var mobile= $("#mobile").val();
        		if(mobile==''||mobile==null){
            		alert("请输入手机号");
            		return false;
            	}
        	  $.ajax({
	    		     type: 'GET',
	    		     url: '/userbase/'+mobile,
	    		     dataType: 'json',
	    		    success : function(data) {
	    		    	var result="";
	    		    	if(data.code==200){
	    		    	 var len=data.data.kolCategoryList.length;
	    		    	 for(var i=0;i<=len-1;i++){
	    		    		 var category="<span ng-repeat='expert in expertclassify' style='margin:0 10px 10px 0;' class='ng-binding ng-scope'><input name=kolCategoriesList type='checkbox' style='margin-right:5px' value='"+data.data.kolCategoryList[i].categoryId+"'/>";
	    		    		 category+= data.data.kolCategoryList[i].categoryName;
	    		    		 category+="</span>";
	    		    		 result+=category;
	    		    	 }
	    		    	}
	    		    	if(data.code!=200){
	    		    		alert(data.msg);
		    		    	}
	    		       $("#form-kolId").val(data.data.userbase.userId);
	    		       $("#nickname").html(data.data.userbase.nickName);
	    		       $("#addExpertclassify").css("display","block");
	    		       $("#profilePicKey").attr("src",data.data.userbase.profilePicKey);
	    		       $("#form-mobile").html(data.data.userbase.mobile);
	    		       $("#expertclassifyList").html(result); 
	   			     },
	   			    error : function() {
	   			    alert("error");
	   			}

	    		   });
          }
        
        $(window).bind("keydown",function(e){
            var key = e.which;
            if (key == 13) {
                $("#search-btn").click();
            }
        })
         </script>
    </body>
</html>