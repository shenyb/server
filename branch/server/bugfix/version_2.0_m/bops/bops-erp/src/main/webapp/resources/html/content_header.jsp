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

						<li><a href="/session/toLoginOut"><i
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