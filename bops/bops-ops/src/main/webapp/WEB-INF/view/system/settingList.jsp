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
				                    <li><a href="#">系统管理</a></li>
				                    <li><a href="#">系统设置</a></li>
				                    <li class="active">设置列表</li>
				                </ol>
				            </div>
				        </div>
				
				        <div class="info">
				            <div class="info-top">
				                <span>设置列表</span>
				                <div class="back">
				                    <a href="" class="ops-pub-btn button button-rounded button-tiny">刷新</a>	
				                   <!--  <a class="bops-pub-btn button button-rounded button-tiny" href="javascript:;"  data-toggle="modal" data-target="#myModal3"  onclick="addUser()">新增规则</a> -->			                   
				                </div>
				            </div>
				            <div class="search-form">
				               
				            </div>
				            <!--biaoge-->
				            <table class="table table-bordered table-striped table-condensed table-responsive">
				                <thead>
				                <tr>
				                	<th>设置项</th>
				                    <th>设置内容</th>
				                    <th>创建时间</th>
				                    <th>修改时间</th>	
				                    <th>设置</th>				                    		                  		                    
				                </tr>
				                </thead>
			                <tbody>
							      <c:forEach items="${list}" var="setting" varStatus="status">
		                        <tr>
		                            <td>
		                               <c:choose>
			                              <c:when test="${setting.systemSettingName=='REGISTER_COUPON_TEMPLATE_ID'}">个人中心分享优惠券id</c:when>
			                              <c:when test="${setting.systemSettingName=='PAYED_COUPON_TEMPLATE_ID'}">支付完成分享优惠券id</c:when>
			                              <c:when test="${setting.systemSettingName=='TRANSPORT_EXPENSE_OF_OVERSEA_WAREHOUSE_LIMIT'}">海外仓包邮限制(单位分)</c:when>
			                              <c:when test="${setting.systemSettingName=='TRANSPORT_EXPENSE_OF_OVERSEA_WAREHOUSE'}">海外仓邮费(单位分)</c:when>
			                              <c:when test="${setting.systemSettingName=='TRANSPORT_EXPENSE_OF_SELF_WAREHOUSE_LIMIT'}">自营仓包邮限制(单位分)</c:when>
			                              <c:when test="${setting.systemSettingName=='TRANSPORT_EXPENSE_OF_SELF_WAREHOUSE'}">自营仓邮费(单位分)</c:when>
			                              <c:when test="${setting.systemSettingName=='TRANSPORT_EXPENSE_OF_TAX_WAREHOUSE_LIMIT'}">保税仓包邮限制(单位分)</c:when>
			                              <c:when test="${setting.systemSettingName=='TRANSPORT_EXPENSE_OF_TAX_WAREHOUSE'}">保税仓邮费(单位分)</c:when>
			                              <c:when test="${setting.systemSettingName=='USERINFO_IS_SUPPORT_SHARE'}">是否支持个人主页分享</c:when>
			                            </c:choose>
		                            </td>
		                            <td>${setting.systemSettingValue}</td>
		                            
		                            <td><fmt:formatDate value="${setting.createTime}" type="both" /></td>
		                            <td><fmt:formatDate value="${setting.updateTime}" type="both" /></td>
		                            <td>
		                            <a href="javascript:;" onclick="ballModel('${setting.systemSettingName}','${setting.systemSettingValue}')"> 修改</a>
		                            </td>
		                        </tr>
		                        </c:forEach>
				                </tbody>
				            </table>
				             <%-- <div class="page">					               
								<div class="pagenum">
									<div aria-live="polite" role="status" id="DataTables_Table_0_info"class="dataTables_info">
										共有 ${page.total}条, 每页显示  ${page.pageSize} 条 
									</div> 
								</div>								
								<div class="tbl-pagin pull-right">
									<div id="light-pagination"></div>
								</div>
				            </div> --%>
				        </div>
				    </div>
                       <%--  <!-- 222222222222 -->
                            <div class="data-page-inner">
                                <div class="page-head">
                                    <!-- 页面标题 -->
                                    <h3 class="m-b-less">
                                        系统管理
                                    </h3>
                                    <!--面包屑导航-->
                                    <div class="state-information">
                                        <ol class="breadcrumb m-b-less bg-less">
                                            <li><a href="#">系统管理</a></li>
                                            <li><a href="#">用户管理</a></li>
                                            <li class="active">用户列表</li>
                                        </ol>
                                    </div>
                                </div>
                                <div class="wrapper">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <section class="panel">
                                                <header class="panel-heading ">
                                                    
													
                                                    用户列表
                                                    <span class="tools pull-right">
                                                       <a class="ops-pub-btn" href="" >
																<i class="fa fa-refresh"></i>
																<span>刷新</span>
															</a>
                                                        <a class="bops-pub-btn ng-isolate-scope" href="javascript:;"  data-toggle="modal" data-target="#myModal3"  onclick="addUser()"><i class="fa fa-plus"></i><span>新增用户</span></a>
                                                    </span>
                                                </header>
                                                <div class="dataTables_wrapper form-inline dt-bootstrap no-footer" 
                                                     id="DataTables_Table_0_wrapper">
                                                    <div class="tbl-head">
                                                        
                                                    </div>
                                                    <table aria-describedby="DataTables_Table_0_info" 
                                                           role="grid" id="DataTables_Table_0" 
                                                           class="table convert-data-table data-table dataTable no-footer">
                                                        <thead>
                                                            <tr role="row">
                                                                <th aria-label="OrderDate : activate to sort column descending" 
                                                                    aria-sort="ascending" style="width: 184px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting_asc">
                                                                    用户账号
                                                                </th>
                                                                <th aria-label="Region : activate to sort column ascending" 
                                                                    style="width: 135px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    用户姓名
                                                                </th>
                                                                <th aria-label="Rep : activate to sort column ascending" 
                                                                    style="width: 181px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    用户邮箱
                                                                </th>
                                                                <th aria-label="Item : activate to sort column ascending" 
                                                                    style="width: 133px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    手机号
                                                                </th>
																
																<th aria-label="Rep : activate to sort column ascending" 
                                                                    style="width: 181px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    部门
                                                                </th>
                                                                <th aria-label="Item : activate to sort column ascending" 
                                                                    style="width: 133px;" 
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
                                                               <c:forEach items="${list}" var="user" varStatus="status">
                        <tr>
                            <td>
                             ${user.userName}
                            </td>
                            <td>${user.userRealName}</td>
                            <td>${user.userEmail}</td>
                            <td>${user.userMobile}</td>
                            <td>${user.userDept}</td>
                            <td>
                            <a href="javascript:;" data-toggle="modal" data-target="#myModal"  onclick="showById('${user.userId}')">查看 </a>|
                            <a href="javascript:;" data-toggle="modal" data-target="#myModal2"  onclick="fixById('${user.userId}')"> 修改</a> |
                            <a id="${user.userId}" href="javascript:;" onclick="deleteById('${user.userId}')">删除</a>
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
                                                                 class="dataTables_info">共有 ${page.total}条, 每页显示  ${page.pageSize}</div>
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
                       <!-- 2222222222 --> --%>
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
        
<!-- 查看模态框 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"  aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h4 class="modal-title" id="myModalLabel">查看用户</h4>
			</div>
			<div class="modal-body">
			<form class="form-horizontal">
				    	<fieldset>
					    	<div class="control-group">
	                              <!-- Text input-->
	                              <label class="control-label col-lg-4" for="">登陆名</label>                              
	                              <div class="controls col-lg-5">	                                
	                              	 <input id="userName1"  type="text"  class="form-control" disabled='disabled'>
	                              </div>
	                              <div style="clear: both;"></div>
	                        </div>
	                        <div class="control-group">
	                              <!-- Text input-->
	                              <label class="control-label col-lg-4" for="">用户姓名</label>                              
	                              <div class="controls col-lg-5">	                                
	                              	  <input id="userRealName1" type="text"  class="form-control" disabled='disabled'>
	                              </div>
	                              <div style="clear: both;"></div>
	                        </div>
							 <div class="control-group">
	                              <!-- Text input-->
	                              <label class="control-label col-lg-4" for="">手机号</label>                              
	                              <div class="controls col-lg-5">	                                
	                              	  <input id="userMobile1"  type="text"  class="form-control" disabled='disabled'>
	                              </div>
	                              <div style="clear: both;"></div>
	                        </div>
	                         <div class="control-group">
	                              <!-- Text input-->
	                              <label class="control-label col-lg-4" for="">工作邮箱</label>                              
	                              <div class="controls col-lg-5">	                                
	                              	  <input id="userEmail1" type="text"  class="form-control" disabled='disabled'>
	                              </div>
	                              <div style="clear: both;"></div>
	                        </div>
	                         <div class="control-group">
	                              <!-- Text input-->
	                              <label class="control-label col-lg-4" for="">所属部门</label>                              
	                              <div class="controls col-lg-5">	                                
	                              	  <select id="userDept1"  class="form-control">
			                                <option value="财务部">财务部</option>
			                                <option value="人事行政部">人事行政部</option>
			                                <option value="产品技术部">产品技术部</option>
			                                <option value="平台运营部">平台运营部</option>
			                                <option value="采购供应部">采购供应部</option>
			                            </select>
	                              </div>
	                              <div style="clear: both;"></div>
	                        </div>
	                        <div class="control-group">
	                              <!-- Text input-->
	                              <label class="control-label col-lg-4" for="">分配角色</label>                              
	                              <div class="controls col-lg-8">	                                
	                              	  <div id="updateRoleList" >
			                             <c:forEach items="${roleList}" var="role" varStatus="status">
			                                <!-- ngRepeat: data in roleList --><label ng-repeat="data in roleList" class="ng-scope">
			                                    <input id='check_${role.roleId}' name="check_user" type="checkbox" value="${role.roleId}" disabled='disabled'>
			                                    <span class="ng-binding">${role.roleName}</span>
			                                </label><!-- end ngRepeat: data in roleList -->
			                            </c:forEach>    
			                            </div>
	                              </div>
	                              <div style="clear: both;"></div>
	                        </div>
                        </fieldset>
			<div class="modal-footer">
				<button type="button" class="button button-capitalize button-rounded button-primary button-small" data-dismiss="modal" onclick="">确定</button>
				<button type="button"  class="button button-capitalize button-rounded button-primary button-small" data-dismiss="modal">取消</button>
			</div>
			</form>
			</div>		
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->


<!-- 修改模态框 -->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"  aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h4 class="modal-title" id="myModalLabel">修改用户</h4>
			</div>
			<div class="modal-body">
			<form class="form-horizontal">
				    	<fieldset>
					    	<div class="control-group">
	                              <!-- Text input-->
	                              <label class="control-label col-lg-4" for="">登陆名</label>                              
	                              <div class="controls col-lg-5">	                                
	                              	 <input id="userName"  type="text"  class="form-control">
	                              </div>
	                              <div style="clear: both;"></div>
	                        </div>
	                        <div class="control-group">
	                              <!-- Text input-->
	                              <label class="control-label col-lg-4" for="">用户姓名</label>                              
	                              <div class="controls col-lg-5">	                                
	                              	  <input id="userRealName" type="text"  class="form-control">
	                              </div>
	                              <div style="clear: both;"></div>
	                        </div>
							 <div class="control-group">
	                              <!-- Text input-->
	                              <label class="control-label col-lg-4" for="">手机号</label>                              
	                              <div class="controls col-lg-5">	                                
	                              	  <input id="userMobile"  type="text"  class="form-control">
	                              </div>
	                              <div style="clear: both;"></div>
	                        </div>
	                         <div class="control-group">
	                              <label class="control-label col-lg-4" for="">工作邮箱</label>                              
	                              <div class="controls col-lg-5">	                                
	                              	 <input id="userEmail" type="text"  class="form-control">
	                              </div>
	                              <div style="clear: both;"></div>
	                        </div>
	                         <div class="control-group">
	                              <!-- Text input-->
	                              <label class="control-label col-lg-4" for="">所属部门</label>                              
	                              <div class="controls col-lg-5">	                                
	                              	   <select id="userDept"  class="form-control">
			                                <option value="财务部">财务部</option>
			                                <option value="人事行政部">人事行政部</option>
			                                <option value="产品技术部">产品技术部</option>
			                                <option value="平台运营部">平台运营部</option>
			                                <option value="采购供应部">采购供应部</option>
			                            </select>
	                              </div>
	                              <div style="clear: both;"></div>
	                        </div>
	                        <div class="control-group">
	                              <!-- Text input-->
	                              <label class="control-label col-lg-4" for="">分配角色</label>                              
	                              <div class="controls col-lg-8">	                                
	                              	  <div id="updateRoleList">
			                            <c:forEach items="${roleList}" var="role" varStatus="status">
			                                <!-- ngRepeat: data in roleList --><label ng-repeat="data in roleList" class="ng-scope">
			                                    <input id='edit_${role.roleId}' name="edit_user" type="checkbox" value="${role.roleId}" >
			                                    <span class="ng-binding">${role.roleName}</span>
			                                </label><!-- end ngRepeat: data in roleList -->
			                            </c:forEach>    
			                            </div>
	                              </div>
	                              <div style="clear: both;"></div>
	                        </div>
                        </fieldset>
         <%-- <form class="ng-pristine ng-valid">
				<table class="bops-pub-dialog-table">
					<tbody><tr>
						<th>登陆名:</th>
						<td>
							<input id="userName"  type="text"  class="ng-pristine ng-untouched ng-valid">
						</td>
					</tr>
					<tr>
						<th>用户姓名:</th>
						<td>
							<input id="userRealName" type="text"  class="ng-pristine ng-untouched ng-valid">
						</td>
					</tr>
					<tr>
						<th>手机号:</th>
						<td>
							<input id="userMobile"  type="text"  class="ng-pristine ng-untouched ng-valid">
						</td>
					</tr>
					<tr>
						<th>工作邮箱:</th>
						<td>
							<input id="userEmail" type="text"  class="ng-pristine ng-untouched ng-valid">
						</td>
					</tr>
					<tr>
						<th>所属部门:</th>
						<td> 
							<!--  <input id="userDept" type="text"  class="ng-pristine ng-untouched ng-valid"> -->
							<select id="userDept"  class="ng-pristine ng-valid ng-touched">
								<option value="财务部">财务部</option>
								<option value="人事行政部">人事行政部</option>
								<option value="产品技术部">产品技术部</option>
								<option value="平台运营部">平台运营部</option>
								<option value="采购供应部">采购供应部</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>分配角色:</th>
						<td>
							<div id="updateRoleList">
							<c:forEach items="${roleList}" var="role" varStatus="status">
								<!-- ngRepeat: data in roleList --><label ng-repeat="data in roleList" class="ng-scope">
									<input id='edit_${role.roleId}' name="edit_user" type="checkbox" value="${role.roleId}" >
									<span class="ng-binding">${role.roleName}</span>
								</label><!-- end ngRepeat: data in roleList -->
							</c:forEach>	
							</div>
						</td>
					</tr>
				</tbody></table>
			</form>
 --%>
            
			<div class="modal-footer">
				<button type="button" class="button button-capitalize button-rounded button-primary button-small" data-dismiss="modal" onclick="submit()">确定</button>
				<button type="button"  class="button button-capitalize button-rounded button-primary button-small" data-dismiss="modal">取消</button>
			</div>
			</form>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
      
      <!-- 审核DIV -->
	<div class="modal fade" id="ballModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">设置修改</h4>
				</div>
				<div class="modal-body">
				    <form class="form-horizontal">
				     <fieldset>
				    <div class="control-group large-group">
                                <!-- Text input-->
                                <label class="control-label col-lg-3" for="checkOpsName">设置项</label>
                                 
                                <div class="controls col-lg-6">
                                <input type="text" id="settingName" value="" readonly="readonly" class="form-control" size="25">
	    					    <input type="hidden" name="systemSettingName" id="systemSettingName" value="" readonly="readonly" class="form-control">
                                </div>
                                <div style="clear: both;"></div>
                      </div>
                      <div class="control-group large-group">
                                <!-- Text input-->
                                <label class="control-label col-lg-3" for="checkOpsName">设置内容</label>
                                 
                                <div class="controls col-lg-6">
                                <input type="text" id="systemSettingValue" value=""  class="form-control" size="25">
                                </div>
                                <div style="clear: both;"></div>
                      </div>		
	    		</fieldset>
				    <div class="modal-footer" id="buttonDiv" >
				     <button type="button" class="button button-capitalize button-rounded button-primary button-small" onclick="editSetting()">确定</button>
					 <button type="button" class="button button-capitalize button-rounded button-primary button-small" data-dismiss="modal">取消
				   </button>				    	
				    </div>
				</form>
				</div>
			</div>
		</div>
	</div>  

        
        <!-- 在 body的最底部引入js文件且一定保持 jquery 在 bootstrap 之前引入 -->
        <script src="/resources/js/jquery/jquery-2.1.4.min.js"></script>
        <script src="/resources/js/bootstrap/bootstrap.min.js"></script>
        <script src="/resources/js/jquery/jquery.bootstrap.min.js"></script>
        <script src="/resources/js/ajax.js"></script>
        <!-- 侧面导航栏组件 js -->
        <script src="/resources/js/sidebar.js"></script>
        <script src="/resources/js/jquery.pagination.js"></script>
        <script type="text/javascript">
        //弹框
        function ballModel(settingName,settingValue){
        	    if(settingName=='REGISTER_COUPON_TEMPLATE_ID'){
        	    	$('#settingName').val('个人中心分享优惠券id');
        	    }else if(settingName=='PAYED_COUPON_TEMPLATE_ID'){
        	    	$('#settingName').val('支付完成分享优惠券id');
        	    	
        	    }else if(settingName=='TRANSPORT_EXPENSE_OF_OVERSEA_WAREHOUSE_LIMIT'){
        	    	$('#settingName').val('海外仓包邮限制');
        	    	
        	    }else if(settingName=='TRANSPORT_EXPENSE_OF_OVERSEA_WAREHOUSE'){
        	    	$('#settingName').val('海外仓邮费');
        	    	
        	    }else if(settingName=='TRANSPORT_EXPENSE_OF_SELF_WAREHOUSE_LIMIT'){
        	    	$('#settingName').val('自营仓包邮限制');
        	    	
        	    }else if(settingName=='TRANSPORT_EXPENSE_OF_SELF_WAREHOUSE'){
        	    	$('#settingName').val('自营仓邮费');
        	    	
        	    }else if(settingName=='TRANSPORT_EXPENSE_OF_TAX_WAREHOUSE_LIMIT'){
        	    	$('#settingName').val('保税仓包邮限制');
        	    	
        	    }else if(settingName=='TRANSPORT_EXPENSE_OF_TAX_WAREHOUSE'){
        	    	$('#settingName').val('保税仓邮费');
        	    	
        	    }else if(settingName=='USERINFO_IS_SUPPORT_SHARE'){
        	    	$('#settingName').val('是否支持个人主页分享');
        	    	
        	    }
        	        
        	    $('#systemSettingName').val(settingName);
        	    $('#systemSettingValue').val(settingValue);
        	
        	
        	
  		    	 $('#ballModal').modal({
  	        		   keyboard: true  
  	              });
 			    
        }
        
        function editSetting(){
          var systemSettingName=$('#systemSettingName').val();
          var systemSettingValue=$('#systemSettingValue').val();
     	   $.ajax({
  		     type: 'POST',
  		     url: '/systemSetting/edit',
  		     data:{'systemSettingName':systemSettingName,'systemSettingValue':systemSettingValue},
  		     dataType: 'json',
  		    success : function(data) {
  		    	window.location.reload();
 			     },
 			    error : function() {
 				    alert("系统异常");
 			}
  		   });
        	
        }
     
         </script>
    </body>
</html>