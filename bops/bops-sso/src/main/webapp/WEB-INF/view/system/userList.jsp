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
        <link rel="stylesheet" href="/resources/css/font-awesome.min.css" >
        <link rel="stylesheet" href="/resources/css/layout.css" >
        <link rel="stylesheet" href="/resources/css/ops.css" >
        <link rel="stylesheet" href="/resources/css/sidebar.css" >
        <link rel="stylesheet" href="/resources/css/content-header.css" >
        <link rel="stylesheet" href="/resources/css/datapage.css" >
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

         <form class="ng-pristine ng-valid">
				<table class="bops-pub-dialog-table">
					<tbody><tr>
						<th>登陆名：</th>
						<td>
							<input id="userName1"  type="text"  class="ng-pristine ng-untouched ng-valid" disabled='disabled'>
						</td>
					</tr>
					<tr>
						<th>用户姓名：</th>
						<td>
							<input id="userRealName1" type="text"  class="ng-pristine ng-untouched ng-valid" disabled='disabled'>
						</td>
					</tr>
					<tr>
						<th>手机号：</th>
						<td>
							<input id="userMobile1"  type="text"  class="ng-pristine ng-untouched ng-valid" disabled='disabled'>
						</td>
					</tr>
					<tr>
						<th>工作邮箱：</th>
						<td>
							<input id="userEmail1" type="text"  class="ng-pristine ng-untouched ng-valid" disabled='disabled'>
						</td>
					</tr>
					<tr>
						<th>所属部门:</th>
						<td>
							<!-- <input id="userDept1" type="text"  class="ng-pristine ng-untouched ng-valid" disabled='disabled'> -->
							<select id="userDept1"  class="ng-pristine ng-valid ng-touched">
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
							<div id="updateRoleList" >
							 <c:forEach items="${roleList}" var="role" varStatus="status">
								<!-- ngRepeat: data in roleList --><label ng-repeat="data in roleList" class="ng-scope">
									<input id='check_${role.roleId}' name="check_user" type="checkbox" value="${role.roleId}" disabled='disabled'>
									<span class="ng-binding">${role.roleName}</span>
								</label><!-- end ngRepeat: data in roleList -->
							</c:forEach>	
							</div>
						</td>
					</tr>
				</tbody></table>
			</form>

            </div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary " data-dismiss="modal" onclick="">确定</button>
				<button type="button"  class="btn btn-default" data-dismiss="modal">取消</button>
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
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h4 class="modal-title" id="myModalLabel">修改用户</h4>
			</div>
			<div class="modal-body">

         <form class="ng-pristine ng-valid">
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

            </div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary " data-dismiss="modal" onclick="submit()">确定</button>
				<button type="button"  class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<!-- 新建模态框 -->
<div class="modal fade" id="myModal3" tabindex="-1" role="dialog"  aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h4 class="modal-title" id="myModalLabel">创建用户</h4>
			</div>
			<div class="modal-body">

         <form class="ng-pristine ng-valid">
				<table class="bops-pub-dialog-table">
					<tbody><tr>
						<th>登陆名：</th>
						<td>
							<input id="userName2"  type="text"  class="ng-pristine ng-untouched ng-valid">
						</td>
					</tr>
					<tr>
						<th>用户姓名：</th>
						<td>
							<input id="userRealName2" id="userRealName2" type="text"  class="ng-pristine ng-untouched ng-valid">
						</td>
					</tr>
					<tr>
						<th>初始密码：</th>
						<td>
							<input id="password2" type="password" ng-model="addData.userPwd" class="ng-pristine ng-untouched ng-valid">
						</td>
					</tr>
					<tr>
						<th>手机号：</th>
						<td>
							<input id="userMobile2"  type="text"  class="ng-pristine ng-untouched ng-valid">
						</td>
					</tr>
					<tr>
						<th>工作邮箱：</th>
						<td>
							<input id="userEmail2" type="text"  class="ng-pristine ng-untouched ng-valid">
						</td>
					</tr>
					<tr>
						<th>所属部门：</th>
						<td>
							<!-- <input id="userDept2" type="text"  class="ng-pristine ng-untouched ng-valid">  -->
							<select id="userDept2"  class="ng-pristine ng-valid ng-touched">
								<option value="财务部">财务部</option>
								<option value="人事行政部">人事行政部</option>
								<option value="产品技术部">产品技术部</option>
								<option value="平台运营部">平台运营部</option>
								<option value="采购供应部">采购供应部</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>分配角色</th>
						<td>
							<div id="updateRoleList2">
								<c:forEach items="${roleList}" var="role" varStatus="status">
								<!-- ngRepeat: data in roleList --><label ng-repeat="data in roleList" class="ng-scope">
									<input id='create_${role.roleId}' name="create_user" type="checkbox" value="${role.roleId}" >
									<span class="ng-binding">${role.roleName}</span>
								</label><!-- end ngRepeat: data in roleList -->
							</c:forEach>
							</div>
						</td>
					</tr>
				</tbody></table>
			</form>

            </div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary " data-dismiss="modal" onclick="submit_add()">确定</button>
				<button type="button"  class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
        
        <!-- 在 body的最底部引入js文件且一定保持 jquery 在 bootstrap 之前引入 -->
        <script src="/resources/js/jquery/jquery-2.1.4.min.js"></script>
        <script src="/resources/js/bootstrap/bootstrap.min.js"></script>
        <script src="/resources/js/jquery/jquery.bootstrap.min.js"></script>
        <script src="/resources/js/ajax.js"></script>
        <!-- 侧面导航栏组件 js -->
        <script src="/resources/js/sidebar.js"></script>
        <script src="/resources/js/jquery.pagination.js"></script>
        <script type="text/javascript">
           $('#light-pagination').pagination({
        		pages: "${page.pageCount}",
        		cssStyle: 'light-theme',
        		currentPage: "${page.page}",
				onPageClick: function(pageNumber, event) {
					window.location.href='page?page='+pageNumber;
				}
        	});
           
           //删除方法
           function deleteById(userId){
        	      $.messager.confirm("删除用户", "您确定要删除此用户!", function() { 
        	    	  $.ajax({
     	    		     type: 'POST',
     	    		     url: '/user/'+userId,
     	    		     dataType: 'json',
     	    		    success : function(data) {
     	    		    	window.location.reload();
     	   			     },
     	   			    error : function() {
     	   				    alert("系统异常");
     	   			}

     	    		   });
        	        });
              
        	      }
   //查看方法
    function showById(uid){
        to_ajax('/user/' + uid, 'GET', {}, callback, '服务忙，请稍后重试！');
	   }
	function callback(data) {
		if (data.code == 200) {
			//alert(JSON.stringify(data));
			clear();
			$('#userName1').val(data.data.bopsUser.userName);
			$('#userRealName1').val(data.data.bopsUser.userRealName);
			$('#userMobile1').val(data.data.bopsUser.userMobile);
			$('#userEmail1').val(data.data.bopsUser.userEmail);
			$('#userDept1').val(data.data.bopsUser.userDept);
			var roleIds = eval(data.data.bopsUser.roleIds);
			var k=0;
			var idss = [];
			for(k=0;k<roleIds.length;k++){
			  $('#check_'+parseInt(roleIds[k])).prop("checked",true);  
			  idss[k]=parseInt(roleIds[k]);
			 }

		    }else{
			alert(data.msg);
			window.location.reload();
		}
	}
    
     //修改
 
	function callback_fix(data) {
		if (data.code == 200) {
            //alert(JSON.stringify(data));
			clear();
			$('#userName').val(data.data.bopsUser.userName);
			$('#userRealName').val(data.data.bopsUser.userRealName);
			$('#userMobile').val(data.data.bopsUser.userMobile);
			$('#userEmail').val(data.data.bopsUser.userEmail);
			$('#userDept').val(data.data.bopsUser.userDept);
			var roleIds = eval(data.data.bopsUser.roleIds);
			var k=0;
			var idss = [];
			for(k=0;k<roleIds.length;k++){
			  $('#edit_'+parseInt(roleIds[k])).prop("checked",true);  
			  idss[k]=parseInt(roleIds[k]);
			 }
		}else{
			alert(data.msg);
			window.location.reload();
		}
	}
     
     
	//修改
	var usrid = '';
    function fixById(uid){
    	to_ajax('/user/' + uid, 'GET', {}, callback_fix, '服务忙，请稍后重试！');
        usrid = '';
        usrid= uid;
	   }
	function submit(){
		 var auths = "[";
			//	 var k = 0;
				 var role = document.getElementsByName("edit_user");
				 var lenth= role.length;
				 for(var m=0;m<lenth;m++){
				    if(role[m].checked){
					    	//auths+= "\""+m+"\",";
					    	tmp="\"";
					    	tmp+= role[m].value;
					    	tmp += "\",";
					    	auths+= tmp;
				//	    	k++;
					   }
				    }
				 auths = auths.substring(0,auths.length-1);
				 auths+= "]";

		  var datas = {"userId":usrid,"userName":$('#userName').val(),'userRealName':$('#userRealName').val(),'userMobile':$('#userMobile').val(),"userEmail":$('#userEmail').val(),"userDept":$('#userDept').val(),"roleIds":auths};
		  to_ajax('/user/put/', 'POST',datas, callback_fixed, '服务忙，请稍后重试！');
		  clear1();
	 
	}
	
	function callback_fixed(data){
		alert(data.msg);
		window.location.reload();
	}
     
     //清理
      function clear(){
        	$('#userName1').val('');
        	$('#userRealName1').val('');
 			$('#userMobile1').val('');
 			$('#userEmail1').val('');
 			$('#userDept1').val('');
 			$("input[name='check_user']").removeAttr("checked"); 
         } 
     
      function clear1(){
      	$('#userName').val('');
      	$('#userRealName').val('');
			$('#userMobile').val('');
			$('#userEmail').val('');
			$('#userDept').val('');
			$("input[name='edit_user']").removeAttr("checked"); 
			
       } 
      function clear2(){
        	$('#userName2').val('');
        	$('#userRealName2').val('');
  			$('#userMobile2').val('');
  			$('#userEmail2').val('');
  			$('#userDept2').val('');
  			$('#password2').val('');
  			$("input[name='create_user']").removeAttr("checked");
         } 
     
   /*   function disable(){
    	 $('#userName').attr("disabled","disabeld");
    	 $('#userRealName').attr("disabled","disabeld");
    	 $('#userMobile').attr("disabled","disabeld");
    	 $('#userEmail').attr("disabled","disabeld");
    	 $('#userDept').attr("disabled","disabeld");
    	 for(var j=84;j<=90;j++){
 		 $('#auth_'+j).attr("disabled","disabeld");
 		}
     } */
     
     //新增用户
        function addUser(){
        	clear2();
     }   
     
    	function submit_add(){
		 var auths = "[";
	//	 var k = 0;
		 var role = document.getElementsByName("create_user");
		 var lenth= role.length;
		 for(var m=0;m<lenth;m++){
		    if(role[m].checked){
			    	//auths+= "\""+m+"\",";
			    	tmp="\"";
			    	tmp+= role[m].value;
			    	tmp += "\",";
			    	auths+= tmp;
		//	    	k++;
			   }
		    }
		 auths = auths.substring(0,auths.length-1);
		 auths+= "]";
	     var datas = {"userName":$('#userName2').val(),"userPwd":$('#password2').val(),'userRealName':$('#userRealName2').val(),'userMobile':$('#userMobile2').val(),"userEmail":$('#userEmail2').val(),"userDept":$('#userDept2').val(),"roleIds":auths};
		  to_ajax('/user/add/', 'POST',datas, callback_add, '服务忙，请稍后重试！');
		  clear2();
}
	
	function callback_add(data){
		//alert(JSON.stringify(data));
		alert(data.msg);
		document.location.reload();
	}
     
     
         </script>
    </body>
</html>