<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

        <title>优惠券模板管理</title>

        <!-- 主体部分样式表 -->
        <link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="/resources/css/bootstrap/button.css" >
        <link rel="stylesheet" href="/resources/css/font-awesome.min.css">
        <link rel="stylesheet" href="/resources/css/layout.css">
        <link rel="stylesheet" href="/resources/css/ops.css">
        <link rel="stylesheet" href="/resources/css/sidebar.css">
        <link rel="stylesheet" href="/resources/css/content-header.css">
        <!-- <link rel="stylesheet" href="/resources/css/datapage.css">
        <link rel="stylesheet" href="/resources/css/form.css"> -->
        <link rel="stylesheet" href="/resources/css/pagination.css">
        <link rel="stylesheet" href="/resources/css/profile-table.css">
        <link rel="stylesheet" href="/resources/css/modal.css" >
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
                        <!-- 每页的部分 -->
                        <div class="form-page-wrap">
                            <div class="data-page-inner">
                                <div class="page-head">                                   
                                    <div class="state-information">
                                        <ol class="breadcrumb m-b-less bg-less">
                                            <li><a href="#">主页</a></li>
                                            <li><a href="#">优惠券模板管理</a></li>
                                            <li class="active">优惠券模板列表</li>
                                        </ol>
                                    </div>
                                </div>
                                <div class="info">
					            <div class="info-top">
					                <span>基本信息</span>
					                <div class="back">
					                    <a href="javascript :;" onclick="javascript :history.back(-1);" class="button button-primary button-raised button-small">返回</a>				                 			                   					                	
					                </div>
					            </div>
					            
				           		<form id="couponForm" class="form-horizontal">
									<table class="table table-bordered table-hover table-condensed table-responsive">											
											<tr>
									            <td>优惠券编号</td>
						                       <td>
						                       		<input name="couponTemplateId" class="form-control" value="${coupon.couponTemplateId}" type="hidden">
                                    				<input name="couponStatus" class="form-control" id="couponStatusInput" value="${coupon.couponStatus}" type="hidden">
                                   				 	${coupon.couponTemplateNo}
						                       </td>
						                        <td>优惠券名称</td>
						                        <td>${coupon.couponName}</td>                     
						                    </tr>
						                    <tr>
									            <td>优惠券类型</td>
						                        <td colspan="3" class="large-group">
						                        	<select class="form-control" name="couponType" id="couponType" disabled="disabled" value="${coupon.couponType}">
				                                        <option value="fullCut"<c:if test="${coupon.couponType=='fullCut'}">selected</c:if>>普通满减券</option>
				                                        <option value="useReturn"<c:if test="${coupon.couponType=='useReturn'}">selected</c:if>>用户使用后返还分享人相同面额的满减券</option>
				                                        <option value="newUser"<c:if test="${coupon.couponType=='newUser'}">selected</c:if>>新人券</option>
				                                        <option value="cash"<c:if test="${coupon.couponType=='cash'}">selected</c:if>>现金券</option>
				                                    </select>
                                                  </td> 						                                          
						                    </tr>
						                   
											<tr>
									            <td>指定商品sku</td>
						                        <td colspan="3"> <input type="text" name="goodsSku" value="${coupon.goodsSku}" disabled="disabled"></td> 						                                          
						                    </tr>
						                    <tr>
									            <td>有效时间(精确到秒)</td>
						                        <td colspan="3">
						                        	<div class="col-xs-5">
						                        	<b>开始时间</b><input type="text" class="form-control" name="startTime" value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' value='${coupon.startTime}' type='both' />" disabled="disabled">
                                                   </div>
                                                   <div class="col-xs-5">
                                                   <b>结束时间</b><input type="text" class="form-control" name="endTime" value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss'  value='${coupon.endTime}' type='both' />" disabled="disabled">
						                       		</div>
						                        </td>						                                          
						                    </tr>
						               <tr>
											<td>券模板图片</td>
											<td id="couponPicDiv">
												<input id="couponPicInput" name="couponPicKey" value="${coupon.couponPicKey}" type="hidden">
				                                    <span id="couponPic" style="display:${couponPicDisplay}">
				                                        <img alt="券模板图片" src="${picHost}${coupon.couponPicKey}">
				                                    </span>
											</td>
											<td>券模板失效图片</td>
											<td id="disabledPicDiv">
												<input id="disabledPicInput" name="disabledPicKey" value="${coupon.disabledPicKey}" type="hidden">
				                                    <span id="disabledPic" style="display:${disabledPicDisplay}">
				                                        <img alt="券模板失效图片" src="${picHost}${coupon.disabledPicKey}">
				                                    </span>
											</td>									
										</tr>	
										<tr>
											<td>优惠券介绍</td>
											<td colspan="3">
											${coupon.description}
											</td>
										</tr>
										<tr class="table-title">
									         	<td colspan="4">
									         	使用规则
									         	</td>												
										</tr>
										<tr>
								            <td>使用规则</td>
					                        <td colspan="3">
					                        	<div class="col-xs-5">
					                        	<b>满</b><input type="text" class="form-control" name="mincost" value="${coupon.minCost}" disabled="disabled"> 
					                        	</div>
					                        	<div class="col-xs-5">
					                        	<b>减</b><input type="text" class="form-control" name="value" value="${coupon.value}" disabled="disabled">
					                        	</div>
					                        </td>						                                          
					                    </tr>
					                    <tr>
								            <td>使用规则描述</td>
					                        <td colspan="3">
					                        	${coupon.couponRule}
					                        </td>						                                          
					                    </tr>
					                    <tr>
								            <td>分享推荐语</td>
					                        <td colspan="3">
					                        	${coupon.couponRecommend}
					                        </td>						                                          
					                    </tr>
					                    <tr>
								            <td>分享标题</td>
					                        <td colspan="3">
					                        	${coupon.couponShareTitle}
					                        </td>						                                          
					                    </tr>
					                     <tr>
								            <td>用户可领取数量/天</td>
					                        <td>
					                        	${coupon.dailyCount}
					                        </td>	
					                        <td>用户一共可领取最大数量</td>
					                        <td>
					                        	${coupon.maxReceiveCount}
					                        </td>						                                          
					                    </tr>
					                    <tr>
								            <td>初使数量</td>
					                        <td>
					                        	${coupon.maxCount}
					                        </td>						                       					                                          
					                    </tr>
					                    <tr class="table-title">
									         	<td colspan="4">
									         	备注信息
									         	</td>												
										</tr>
										<tr>
								            <td>备注</td>
					                        <td colspan="3">
					                        	${coupon.remark}
					                        </td>						                                          
					                    </tr>
					                    
									</table>																																												
								</form>
							 </div>
                                
                                <%-- <!-- 33333333333333333333 -->
                                <div class="wrapper">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <form id="couponForm" class="form-horizontal" method="post">
                                                <section class="panel">
                                                    <header class="panel-heading">基本信息<a href="javascript:history.go(-1)" class="btn btn-info pull-right">返回</a></header>
                                                    <div class="panel-body">
                                                        <div class="form-group">
                                                            <label for="" class="col-lg-2">优惠券编号</label>
                                                            <div class="col-lg-9">
                                                                <input name="couponTemplateId" value="${coupon.couponTemplateId}" type="hidden">
                                                                <input name="couponStatus" id="couponStatusInput" value="${coupon.couponStatus}" type="hidden">
                                                                <input type="text" name="couponTemplateNo" value="${coupon.couponTemplateNo}" disabled="disabled">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="" class="col-lg-2">优惠券名称</label>
                                                            <div class="col-lg-9">
                                                                <input type="text" name="couponName" value="${coupon.couponName}" disabled="disabled">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="" class="col-lg-2">优惠券类型</label>
                                                            <div class="col-lg-9">
                                                                <select class="form-control" name="couponType" id="couponType" disabled="disabled" value="${coupon.couponType}">
                                                                    <option value="fullCut"<c:if test="${coupon.couponType=='fullCut'}">selected</c:if>>普通满减券</option>
                                                                    <option value="useReturn"<c:if test="${coupon.couponType=='useReturn'}">selected</c:if>>用户使用后返还分享人相同面额的满减券</option>
                                                                    <option value="newUser"<c:if test="${coupon.couponType=='newUser'}">selected</c:if>>新人券</option>
                                                                    <option value="cash"<c:if test="${coupon.couponType=='cash'}">selected</c:if>>现金券</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="" class="col-lg-2">指定商品sku</label>
                                                            <div class="col-lg-9">
                                                                <input type="text" name="goodsSku" value="${coupon.goodsSku}" disabled="disabled">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="" class="col-lg-2">有效时间</label>
                                                            <div class="col-lg-9">
                                                                <input type="text" name="startTime" value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' value='${coupon.startTime}' type='both' />" disabled="disabled">
                                                                <input type="text" name="endTime" value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' value='${coupon.endTime}' type='both' />" disabled="disabled">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="" class="col-lg-2">券模板图片</label>
                                                            <div class="col-lg-9" id="couponPicDiv">
                                                                <input id="couponPicInput" name="couponPicKey" value="${coupon.couponPicKey}" type="hidden">
                                                                <span id="couponPic" style="display:${couponPicDisplay}">
                                                                    <img width="60px" alt="券模板图片" 
                                                                         src="${picHost}${coupon.couponPicKey}">
                                                                </span>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="" class="col-lg-2">券模板失效图片</label>
                                                            <div class="col-lg-9" id="disabledPicDiv">
                                                                <input id="disabledPicInput" name="disabledPicKey" value="${coupon.disabledPicKey}" type="hidden">
                                                                <span id="disabledPic" style="display:${disabledPicDisplay}">
                                                                    <img width="60px" alt="券模板失效图片" 
                                                                         src="${picHost}${coupon.disabledPicKey}">
                                                                </span>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="" class="col-lg-2">优惠券介绍</label>
                                                            <div class="col-lg-9">
                                                                <textarea class="form-control" name="description" rows="3" disabled="disabled">${coupon.description}</textarea>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </section>
                                                <section class="panel">
                                                    <header class="panel-heading">使用规则</header>
                                                    <div class="panel-body">
                                                        <div class="form-group">
                                                            <label for="" class="col-lg-2">使用规则</label>
                                                            <div class="col-lg-9">
                                                                满 <input type="text" name="mincost" value="${coupon.minCost}" disabled="disabled"> 减 <input type="text" name="value" value="${coupon.value}" disabled="disabled">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="" class="col-lg-2">使用规则描述</label>
                                                            <div class="col-lg-9">
                                                                <textarea class="form-control" name="couponRule" rows="3" disabled="disabled">${coupon.couponRule}</textarea>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="" class="col-lg-2">分享推荐语</label>
                                                            <div class="col-lg-9">
                                                                <textarea class="form-control" name="couponRecommend" rows="3" disabled="disabled">${coupon.couponRecommend}</textarea>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="" class="col-lg-2">分享标题</label>
                                                            <div class="col-lg-9">
                                                                <textarea class="form-control" name="couponShareTitle" rows="3" disabled="disabled">${coupon.couponShareTitle}</textarea>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="" class="col-lg-2">用户可领取数量/天</label>
                                                            <div class="col-lg-9">
                                                                <input type="text" name="dailyCount" value="${coupon.dailyCount}" disabled="disabled">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="" class="col-lg-2">用户一共可领取最大数量</label>
                                                            <div class="col-lg-9">
                                                                <input type="text" name="maxReceiveCount" value="${coupon.maxReceiveCount}" disabled="disabled">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="" class="col-lg-2">初使数量</label>
                                                            <div class="col-lg-9">
                                                                <input type="text" name="maxCount" value="${coupon.maxCount}" disabled="disabled">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </section>
                                                <section class="panel">
                                                    <header class="panel-heading">备注信息</header>
                                                    <div class="panel-body">
                                                        <div class="form-group">
                                                            <label for="" class="col-lg-2">备注</label>
                                                            <div class="col-lg-9">
                                                                <textarea class="form-control" name="remark" rows="3" disabled="disabled">${coupon.remark}</textarea>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </section>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            	<!-- 3333333333333333333 --> --%>
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
        <!-- 侧面导航栏组件 js -->
        <script src="/resources/js/sidebar.js"></script>
        <script type="text/javascript">
            function() {
                
            }
        </script>

    </body>
</html>