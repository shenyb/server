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
         <link rel="stylesheet" href="/resources/css/list-content.css">
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
                        <div class="data-page-wrap">
                        <div class="con">
				        <div class="page-head">		 		           
				            <div class="state-information">
				                <ol class="breadcrumb m-b-less bg-less"> 
				                    <li><a href="#">主页</a></li> 
				                    <li><a href="#">商品组合管理</a></li>
				                    <li class="active">商品列表</li>
				                </ol>
				            </div>
				        </div>
				
				        <div class="info">
				            <div class="info-top">
				                <span>商品列表</span>
				                <div class="back">
				                    <a href="" class="ops-pub-btn button button-rounded button-tiny">刷新</a>	
				                    <a onclick="javascript :history.back(-1);" href="javascript:void(0)" class="button button-rounded button-tiny">返回</a>		                   
				                </div>
				            </div>
				            
				            <table class="table table-bordered table-hover table-condensed table-responsive table-top">											
									<tr class="table-title">
							         	<td colspan="2">
							         	组合信息
							         	</td>												
									</tr>	
									<tr>
							        	<td class="col-xs-2">促销名称</td>
				                       <td colspan="">
				                       		${group.groupName}
				                       </td>						                                  
				                    </tr>				                   
				                    <tr>
							            <td>促销说明</td>
				                        <td>${group.groupBrief}</td>				                                          
				                    </tr>
				                    
				                    <tr>
											<td>规则设置</td>
											<td colspan="3">									
											<div class="col-xs-3">
											<input type="radio"  name="ruleType" value="DISCOUNT"  <c:if test="${group.ruleType=='DISCOUNT'}">checked='checked'</c:if> disabled/> 折扣
                                             &nbsp;&nbsp;
                                           <input type="radio"  name="ruleType" value="FIXEDPRICE" <c:if test="${group.ruleType=='FIXEDPRICE'}">checked='checked'</c:if> disabled/> 一口价
											
											</div>
                                      	</td>
										</tr>
										<tr name="DISCOUNTTR">
		                                    <td></td>		                                   
		                                    <td>
		                                    满 &nbsp;&nbsp;${group.ruleOne }&nbsp;&nbsp;件&nbsp;&nbsp;${group.ruleOneDisc }&nbsp;&nbsp;折
		                                    </td>
		                                   
		                                  </tr>
		                                  <tr name="DISCOUNTTR">
		                                    <td></td>
		                                   
		                                      <td>
	                                                满 &nbsp;&nbsp;${group.ruleTwo }&nbsp;&nbsp;件&nbsp;&nbsp;${group.ruleTwoDisc }&nbsp;&nbsp;折
	                                            </td>
		                               		</tr>
		                                  <tr name="DISCOUNTTR">
		                                    <td></td>
		                                     <td>
                                                 满 &nbsp;&nbsp;${group.ruleThree }&nbsp;&nbsp;件&nbsp;&nbsp;${group.ruleThreeDisc }&nbsp;&nbsp;折
                                             </td>		                                   										
					                   </tr>
					                    <tr id="FIXEDPRICEDIV" style="display: none;">
		                                    <td></td>
		                                    <td>
                                                满 &nbsp;&nbsp;${group.ruleFixed }&nbsp;&nbsp;件&nbsp;&nbsp;${group.ruleFixedPrice }&nbsp;&nbsp;元
                                            </td>		                                    
		                                </tr>
						          </table>
				            <div class="search-form">
				                <form class="form-inline" id="searchGoodsContent" method="get" action="/goodsGroup/viewGoods">
				                    <div class="row">
				                    <input type="hidden" name="groupBatch" value="${page.groupBatch }">
					                    <div class="form-group col-xs-3">
					                        <label for="searchGoodsCode">商品编码</label>
					                        <input type="text" id="searchGoodsCode"  name="goodsCode" value="${page.goodsCode }" class="form-control">
					                    </div>
					                    <div class="form-group col-xs-3">
					                        <label for="searchGoodsName">商品名称</label>					                       
					                       <input type="text" id="searchGoodsName"  name="goodsName" value="${page.goodsName }" class="form-control">
					                    </div> 
					                    <div class="form-group col-xs-3">
					                        <label for="warehouseType">仓库类型</label>					                       
					                       <select  id="warehouseType" name="warehouseType" class="form-control">
                                                     <option  value="">全部</option>
                                                     <option value="TAX_WAREHOUSE" <c:if test="${page.warehouseType=='TAX_WAREHOUSE'}">selected</c:if>>保税仓</option>
                                                     <option value="SELF_WAREHOUSE"    <c:if test="${page.warehouseType=='SELF_WAREHOUSE'}">selected</c:if>>自营仓</option>
                                                     <option value="OVERSEA_WAREHOUSE"       <c:if test="${page.warehouseType=='OVERSEA_WAREHOUSE'}">selected</c:if>>海外直邮</option>
                                            </select>
					                    </div> 					                 
					                    <div class="form-group col-xs-3">      					                   				                 
				                  		 <button type="submit" id="searChops" class="button button-primary button-raised button-small"><i class="fa fa-search"></i>&nbsp;&nbsp;搜索</button>
				                  		</div>	
				                    </div>
				                </form>
				            </div>
				            <!--biaoge-->
				            <table class="table table-bordered table-striped table-condensed table-responsive">
				                <thead>
				                <tr>
				                	<th>组合批次</th>
                                    <th>商品编码</th>
                                    <th>商品名称</th>
                                    <th>商品场景图</th>
                                    <th>商品售价</th>
                                    <th>商品库存</th>
                                    <th>商品状态</th>	
                                    <th>仓库</th>	
                                    <th>操作</th>			                    
				                </tr>
				                </thead>
			                <tbody>
								<c:forEach items="${list}" var="goods" varStatus="status">
                              <tr>
                                   <td>${goods.groupBatch}</td>
                                  <td>${goods.goodsCode}</td>
                                   <td>
                                   ${goods.goodsName }
                                  </td>
                                  <td>
                                    <img width="80px;" alt="" src="${imgUrl}${goods.scenePicKey}">
                                  </td>
                        
                                  <td>
                                   ${goods.goodsPrice} 
                                  
                                  </td>
                                  <td>
                                   ${goods.storeCount} 
                                  
                                  </td>
                                   <td>
                                  <c:choose>
                                        <c:when test="${goods.goodsStatus=='ONLINE'}">上架</c:when>
                                        <c:when test="${goods.goodsStatus=='OFFLINE'}">下架 </c:when>
                                       </c:choose>                                
                                  </td>                                
                                   <td>
                                      <c:choose>
                                        <c:when test="${goods.warehouseType=='TAX_WAREHOUSE'}">保税仓</c:when>
                                        <c:when test="${goods.warehouseType=='SELF_WAREHOUSE'}">自营仓 </c:when>
                                        <c:when test="${goods.warehouseType=='OVERSEA_WAREHOUSE'}">海外直邮 </c:when>
                                       </c:choose>
                                   </td>
                                    <td>
                                   <c:choose>
                                        <c:when test="${group.groupStatus=='VALID'}">已审核，不可再操作</c:when>
                                        <c:when test="${group.groupStatus=='INVALID'}">
                                         <a href="javascript:;" onclick="deleteOpsGoods('${goods.goodsId}','${goods.groupBatch }')">删除</a>
                                        </c:when>                                   
                                       </c:choose>
                                   </td>                              
                              </tr>
                              </c:forEach>       
				            </tbody>
				            </table>
				             <div class="page">					               
								<div class="pagenum">
									<div aria-live="polite" role="status" id="DataTables_Table_0_info"class="dataTables_info">
										共有 ${page.total}条, 每页显示  ${page.pageSize} 条 
									</div> 
								</div>								
								<div class="tbl-pagin pull-right">
									<div id="light-pagination"></div>
								</div>
				            </div>
				        </div>
				    </div>   
                      
                        <%-- <!-- 2222222222222 -->
                            <div class="data-page-inner">
                                <div class="page-head">
                                    <!-- 页面标题 -->
                                    <h3 class="m-b-less">
                                        商品组合管理view
                                    </h3>
                                    <!--面包屑导航-->
                                    <div class="state-information">
                                        <ol class="breadcrumb m-b-less bg-less">
                                            <li><a href="#">主页</a></li>
                                            <li><a href="#">商品组合管理</a></li>
                                            <li class="active">商品列表</li>
                                        </ol>
                                    </div>
                                </div>
                                <div class="wrapper">
                                    <div class="row">
                                        <div class="col-sm-12">
                                         <section class="panel">
                                           <header class="panel-heading " style="height: 50px;">
                                         <span class="tools pull-right">
                                                        <a class="ops-pub-btn" href="">
														<i class="fa fa-refresh" ></i>
														<span>刷新</span>
														</a>
														<a class="ops-pub-btn" onclick="javascript :history.back(-1);" href="javascript:void(0)">
														<i class="fa fa-arrow-left" ></i>
														<span>返回</span>
														</a>
                                                    </span>
                                                    </header>
                                         <div class="bops-pub-body">
									<div class="bops-pub-param-body">
                                    <div class="bops-pub-param-header">
                                
										<span class="bops-pub-param-title">
											组合信息
										</span>
									</div>
																<table class="bops-pub-param-table">
																	<tbody>
																	<tr>
																		<th style="vertical-align: top;font-weight:normal;"> 促销名称</th>
																		<td>
																			
																			${group.groupName}
																		</td>
																	</tr>
																	<!-- <tr>
																		<th style="vertical-align: top;font-weight:normal;"><i style="color:red;">* </i> 持续时间</th>
																		<td>
																		    <input type="text" id="startTimeString"
                                                                                name="startTimeString" placeholder="开始时间"
                                                                                value="" onfocus="WdatePicker({firstDayOfWeek:1,dateFmt:'yyyy-MM-dd HH:mm:ss'})" required:teue/>
                                                                            &nbsp;&nbsp;至&nbsp;&nbsp;
                                                                           <input type="text" id="endTimeString" name="endTimeString"
                                                                               placeholder="结束时间" value="" onfocus="WdatePicker({firstDayOfWeek:1,dateFmt:'yyyy-MM-dd HH:mm:ss'})"required:true/>
																		</td>
																	</tr> -->
																	<tr>
																		<th style="vertical-align: top;font-weight:normal;">促销说明</th>
																		<td>
																			${group.groupBrief}
																		</td>
																	</tr>
																</tbody>
																</table>
															 <table class="bops-pub-param-table">
																<tbody>
																	<tr>
																		<th style="vertical-align: top;font-weight:normal;"> 规则设置</th>
																		<td>
																			<input type="radio"  name="ruleType" value="DISCOUNT"  <c:if test="${group.ruleType=='DISCOUNT'}">checked='checked'</c:if> disabled/> 折扣
																			&nbsp;&nbsp;
																			<input type="radio"  name="ruleType" value="FIXEDPRICE" <c:if test="${group.ruleType=='FIXEDPRICE'}">checked='checked'</c:if> disabled/> 一口价
																		</td>
																	</tr>
																	
																	<tr name="DISCOUNTTR">
																		<th style="vertical-align: top;font-weight:normal;"></th>
																		<td>
																			满 &nbsp;&nbsp;${group.ruleOne }&nbsp;&nbsp;件&nbsp;&nbsp;${group.ruleOneDisc }&nbsp;&nbsp;折
																		</td>
																		<td style="vertical-align: top;"></td>
																	</tr>
																	<tr name="DISCOUNTTR">
																		<th style="vertical-align: top;font-weight:normal;"></th>
																		<td>
																			满 &nbsp;&nbsp;${group.ruleTwo }&nbsp;&nbsp;件&nbsp;&nbsp;${group.ruleTwoDisc }&nbsp;&nbsp;折
																		</td>
																		<td style="vertical-align: top;"></td>
																	</tr>
																	<tr name="DISCOUNTTR">
																		<th style="vertical-align: top;font-weight:normal;"></th>
																		<td>
																			满 &nbsp;&nbsp;${group.ruleThree }&nbsp;&nbsp;件&nbsp;&nbsp;${group.ruleThreeDisc }&nbsp;&nbsp;折
																		</td>
																		<td style="vertical-align: top;"></td>
																	</tr>
																
																	
																	 <tr id="FIXEDPRICEDIV" style="display: none;">
																		<th style="vertical-align: top;font-weight:normal;"></th>
																		<td>
																			满 &nbsp;&nbsp;${group.ruleFixed }&nbsp;&nbsp;件&nbsp;&nbsp;${group.ruleFixedPrice }&nbsp;&nbsp;元
																		</td>
																		<td style="vertical-align: top;"></td>
																	 </tr>
																	
																    </tbody>
																</table>
															
                                         </div>
                                         </div>
                                         
                                         
                                         </section>
                                        
                                        
                                            <section class="panel">
                                              
                            <div class="bops-pub-param-header">
                                
										<span class="bops-pub-param-title">
											商品列表
										</span>
									</div>
                                                    
                                                </header>
                                              <!-- 搜索DIV -->                                                  
                                             <div class="tbl-head">
                                              <form id="searchGoodsContent" method="get" action="/goodsGroup/viewGoods">
									              <div class="bops-pub-param-seach" >
									              <input type="hidden" name="groupBatch" value="${page.groupBatch }">
									              <input type="text" id="searchGoodsCode" style="vertical-align: 1px; 
															   		  width: 200px;
															   		  padding-left: 18px;
															   		  display:inline-block;
															   		  height:32px;"  name="goodsCode" value="${page.goodsCode }" placeholder="商品编码" class="ng-pristine ng-untouched ng-valid">
					                             <input type="text" id="searchGoodsName" style="vertical-align: 1px; 
															   		  width: 200px;
															   		  padding-left: 18px;
															   		  display:inline-block;
															   		  height:32px;" name="goodsName" value="${page.goodsName }" placeholder="商品名称" class="ng-pristine ng-untouched ng-valid">
									           <select  id="warehouseType" name="warehouseType"  style="vertical-align: 1px;width: 200px;padding-left: 18px;display:inline-block;height:32px">
				    									<option  value="">仓库类型</option>
				    								    <option value="TAX_WAREHOUSE" <c:if test="${page.warehouseType=='TAX_WAREHOUSE'}">selected</c:if>>保税仓</option>
														<option value="SELF_WAREHOUSE"    <c:if test="${page.warehouseType=='SELF_WAREHOUSE'}">selected</c:if>>自营仓</option>
														<option value="OVERSEA_WAREHOUSE"       <c:if test="${page.warehouseType=='OVERSEA_WAREHOUSE'}">selected</c:if>>海外直邮</option>
		    								   </select>
											        <button type="submit" class="btn btn-info" style="vertical-align:middle;margin-top:-2px;"  id="searChops">搜索</button>
													</div> 
													</form> 
                                                    </div>
                                                <div class="dataTables_wrapper form-inline dt-bootstrap no-footer" 
                                                     id="DataTables_Table_0_wrapper">
                                                    <table aria-describedby="DataTables_Table_0_info" 
                                                           role="grid" id="DataTables_Table_0" 
                                                           class="table convert-data-table data-table dataTable no-footer">
                                                        <thead>
                                                            <tr role="row">
                                                             <th aria-label="OrderDate : activate to sort column descending" 
                                                                    aria-sort="ascending" style="width: 80px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting_asc">
                                                                    组合批次
                                                                </th>
                                                                <th aria-label="OrderDate : activate to sort column descending" 
                                                                    aria-sort="ascending" style="width: 80px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting_asc">
                                                                    商品编码
                                                                </th>
                                                                <th aria-label="Region : activate to sort column ascending" 
                                                                    style="width: 150px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                     商品名称    
                                                                </th>
                                                                <th aria-label="Rep : activate to sort column ascending" 
                                                                    style="width: 80px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                     商品场景图
                                                                </th>
                                                                <th aria-label="Item : activate to sort column ascending" 
                                                                    style="width: 80px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                     商品售价
                                                                </th>
                                                                  <th aria-label="Item : activate to sort column ascending" 
                                                                    style="width: 80px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                     商品库存
                                                                </th>
                                                                 <th aria-label="Item : activate to sort column ascending" 
                                                                    style="width: 80px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                     商品状态
                                                                </th>
                                                                
                                                                <th aria-label="Item : activate to sort column ascending" 
                                                                    style="width: 100px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    仓库
                                                                </th>
                                                                 <th aria-label="Item : activate to sort column ascending" 
                                                                    style="width: 100px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    操作
                                                                </th>
                                                                                             
                                                            </tr>
                                                        </thead>
                                                        <tbody>
										                     <c:forEach items="${list}" var="goods" varStatus="status">
										                        <tr>
										                             <td>${goods.groupBatch}</td>
										                            <td>${goods.goodsCode}</td>
										                             <td>
										                             ${goods.goodsName }
										                            </td>
										                            <td>
										                              <img width="80px;" alt="" src="${imgUrl}${goods.scenePicKey}">
										                            </td>
										                  
										                            <td>
										                             ${goods.goodsPrice} 
										                            
										                            </td>
										                            <td>
										                             ${goods.storeCount} 
										                            
										                            </td>
										                             <td>
										                            <c:choose>
											                              <c:when test="${goods.goodsStatus=='ONLINE'}">
											                                                                                             上架
											                              </c:when>
											                              <c:when test="${goods.goodsStatus=='OFFLINE'}">
											                                                                                             下架
											                              </c:when>
											                             </c:choose>
										                            
										                            </td>
										                           
										                             <td>
										                                <c:choose>
											                              <c:when test="${goods.warehouseType=='TAX_WAREHOUSE'}">
											                                                                                             保税仓
											                              </c:when>
											                              <c:when test="${goods.warehouseType=='SELF_WAREHOUSE'}">
											                                                                                             自营仓
											                              </c:when>
											                              <c:when test="${goods.warehouseType=='OVERSEA_WAREHOUSE'}">
											                                                                                             海外直邮
											                              </c:when>
											                             </c:choose>
										                             </td>
										                              <td>
										                             <c:choose>
											                              <c:when test="${group.groupStatus=='VALID'}">
											                                                                                            已审核，不可再操作
											                              </c:when>
											                              <c:when test="${group.groupStatus=='INVALID'}">
											                               <a href="javascript:;" onclick="deleteOpsGoods('${goods.goodsId}','${goods.groupBatch }')">删除</a>
											                              </c:when>
											                         
											                             </c:choose>
										                             </td>
										                        

										                        </tr>
										                        </c:forEach>
                                                         </tbody>
                                                    </table>
                                                    <div class="tbl-footer clearfix">
                                                        <div class="tbl-info pull-left">
                                                            <div aria-live="polite" 
                                                                 role="status" 
                                                                 id="DataTables_Table_0_info" 
                                                                 class="dataTables_info">共有 ${page.total}条, 每页显示  ${page.pageSize} </div>
                                                        </div>
                                                        <div class="tbl-pagin pull-right">
                                                            <div id="light-pagination"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </section>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        <!-- 22222222222222 --> --%>
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
        $(document).ready(function(){  
            var ruleType= "${group.ruleType}";
        	if(ruleType=='DISCOUNT'){
        	 $("tr[name='DISCOUNTTR']").show();
       		 $('#FIXEDPRICEDIV').hide();
        		
        	} else{
        	 $("tr[name='DISCOUNTTR']").hide();
       		 $('#FIXEDPRICEDIV').show();
        		
        	}
        });   
        
        $('#light-pagination').pagination({
        		pages: "${page.pageCount}",
        		cssStyle: 'light-theme',
        		currentPage: "${page.page}",
				onPageClick: function(pageNumber, event) {
					var groupBatch='${page.groupBatch}'
				    var goodsName='${page.goodsName}'
				    var goodsCode='${page.goodsCode}'
				    var warehouseType='${page.warehouseType}'
					window.location.href='/goodsGroup/viewGoods?page='+pageNumber+'&groupBatch='+groupBatch+'&goodsName='+goodsName+'&goodsCode='+goodsCode+'&warehouseType='+warehouseType;
				}
        	});
        
        //删除单条数据
        function deleteOpsGoods(goodsId,groupBatch){
     	   $.messager.confirm("删除商品", "您确定要删除此商品!", function() { 
   	    	  $.ajax({
	    		     type: 'POST',
	    		     url: '/goodsGroup/deleteGoods',
	    		     data:{'goodsId':goodsId,'groupBatch':groupBatch},
	    		     dataType: 'json',
	    		    success : function(data) {
	    		    	window.location.reload();
	   			     },
	   			    error : function() {
	   				    alert("系统异常");
	   			}

	    		   });
   	        });
         
  	      };
         </script>
    </body>
</html>