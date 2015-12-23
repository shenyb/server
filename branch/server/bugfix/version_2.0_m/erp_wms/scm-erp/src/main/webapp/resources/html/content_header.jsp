<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!-- 右侧顶部动作条 -->
<div class="content-header">
	<a class="toggle-btn"> <i class="fa fa-outdent"></i>
	</a>
	<div class="navbar-collapse navbar-collapse collapse yamm mega-menu">
		<ul class="nav navbar-nav">
			<li class="dropdown"><a href="javascript:;"
				class="dropdown-toggle" data-toggle="dropdown"> Mege <b
					class="fa fa-angle-down"></b>
			</a></li>
			<li class="dropdown"><a href="javascript:;"
				class="dropdown-toggle" data-toggle="dropdown"> English <b
					class="fa fa-angle-down"></b>
			</a></li>
		</ul>
	</div>
	<div class="notification-wrap">
		<div class="right-notification">
			<ul class="notification-menu">
				<li><a href="javascript:;"
					class="btn btn-default dropdown-toggle" data-toggle="dropdown">
						<span class="notification-header-wrap"> <img
							src="http://www.huabian.com/uploadfile/2014/0910/20140910012324173.jpg"
							alt=""> ${userInfoResult.userName }
					</span> <span> ${userInfoResult.userName } </span> <span
						class=" fa fa-angle-down"></span>
				</a>
					<ul class="dropdown-menu dropdown-usermenu purple pull-right">

						<li>
						<a href="javascript:;" onclick="changePSW()">修改密码</a>
						<a href="/session/toLoginOut"><i
								class="fa fa-sign-out pull-right"></i>退出</a></li>
					</ul></li>
				<li>
					<div class="sb-toggle-right">
						<i class="fa fa-indent"></i>
					</div>
				</li>
			</ul>
		</div>
	</div>
</div>
<div class="modal fade" id="changePSWModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">修改密码</h4>
				</div>
				<div class="modal-body">
				    <form class="form-horizontal">
				   
				    	<fieldset>		
	    				<div class="control-group">
	    							<label for="" class="control-label col-lg-4">原始密码</label>
	    							<div class="controls col-lg-5">
	    								<input type="password" name="oldPwd" id="oldPwd" value="" class="form-control">
	    							</div>
	    							<div style="clear: both;"></div>
	    				</div>
	    				<div class="control-group">
	    							<label for="" class="control-label col-lg-4">新密码</label>
	    							<div class="controls col-lg-5">
	    								<input type="password" name="newPwd" id="newPwd" class="form-control">
	    							</div>
	    							<div style="clear: both;"></div>
	    				</div>
	    				<div class="control-group">
	    							<label for="" class="control-label col-lg-4">确认新密码</label>
	    							<div class="controls col-lg-5">
	    								<input type="password" name="repeatNewPwd" id="repeatNewPwd" class="form-control">
	    							</div>
	    							<div style="clear: both;"></div>
	    				</div>
	    				</fieldset>
				    <div class="modal-footer" id="buttonDiv" >
				     <button type="button" class="button button-capitalize button-rounded button-primary button-small" onclick="changePassWord()">确定</button>
					 <button type="button" class="button button-capitalize button-rounded button-primary button-small" data-dismiss="modal">取消
				   </button>				    	
				    </div>
				</form>
				</div>
			</div>
		</div>
	</div>   
	
	 <script type="text/javascript">
	       function changePSW(){
	            $('#changePSWModal').modal({
	    	        		   keyboard: true  
	    	              });
	            document.getElementById("oldPwd").value="";
	       } 
	       
	       function changePassWord(){
	    	  var oldPassword=$("#oldPwd").val();
	    	  var reg = /^[0-9 | A-Z | a-z]{6,16}$/;
	    	  if(!oldPassword.match(reg)){ 
	    		  alert("密码只能输入6-16位的字母或数字");
	    		  return false;
	    	  } 
	    	  if($('#newPwd').val()!=$('#repeatNewPwd').val()){
	    		  alert('两次输入的密码不一致')
	    		  return false;
	    	  }else{
	    		  var oldPwd=$('#oldPwd').val();
	    		  var newPwd=$('#newPwd').val();
	    		  $.ajax({
		    		     type: 'POST',
		    		     url: '/user/changePwd',
		    		     data:{'oldPwd':oldPwd,'newPwd':newPwd},
		    		     dataType: 'json',
		    		    success : function(data) {
		    		    	if(data.code==200){
		    		    		alert('修改成功');
		    		    		window.location.reload();	
		    		    	}else{	
		    		    		 alert(data.msg);
		    		    	}
		   			     },
		   			    error : function() {
		   				    alert("系统异常");
		   			}

		    		   }); 
	    		  
	    		  
	    	  }	    	   
	    	   
	       }
	    </script> 