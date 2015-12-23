<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 左侧导航栏 html 页面 -->
<div class="sidebar">
	<div class="sidebar-top">
		<div class="logo">
			<a href="#"> <span class="brand-name"></span>
			</a>
		</div>
	</div>
	<div class="sidebar-main">
		<div class="sidebar-main-inner">
			<!-- 导航栏菜单部分信息 -->
			<div class="sidebar-info" id="sidebar">
				<ul class="nav side-navigation">
					<!-- 导航栏菜单分类 title -->
					<li class="nav-title">
						<h3 class="navigation-title">Navigation</h3>
					</li>
					<!-- 总览 -->
					<c:choose>
						<c:when test="${request_path != null }">
							<li class="dashboard">
						</c:when>
						<c:otherwise>
							<li class="dashboard active">
						</c:otherwise>
					</c:choose>
					<a href="#"> <i class="fa fa-home"></i> <span>总览</span>
					</a>
					</li>
					<!-- 总览 end -->

					<!--采销管理 -->
					<c:choose>
						<c:when test="${request_path >10 && request_path<20}">
							<li class="menu-list nav-active">
						</c:when>
						<c:otherwise>
							<li class="menu-list">
						</c:otherwise>
					</c:choose>
					<a href="#"> <i class="fa fa-gamepad"></i> <span>采销管理</span> <i
						class="fa fa-angle-right ico"></i>
					</a>
					<ul class="child-list">
						<c:choose>
							<c:when test="${request_path ==14 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/categories/page">商品分类</a>
						</li>
						<c:choose>
							<c:when test="${request_path ==15 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/brand/page">品牌管理</a>
						</li>
						<c:choose>
							<c:when test="${request_path ==11 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/goods/page">商品管理</a>
						</li>
						<c:choose>
							<c:when test="${request_path ==12 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/store/page">库存管理</a>
						</li>
						<c:choose>
							<c:when test="${request_path ==16 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/goodsNew/page">商品价格修改</a>
						</li>
						<c:choose>
							<c:when test="${request_path ==17 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/goodsNew/goodsPriceDetailsList">商品价格修改历史</a>
						</li>
						<c:choose>
							<c:when test="${request_path ==18 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/purchase/page">采购单管理</a>
						</li>
						<c:choose>
							<c:when test="${request_path ==19 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/vendor/page">供应商物流商管理</a>
						</li>

					</ul>
					</li>
					<!-- 采销管理  end-->

					<!-- 订单管理-->
					<c:choose>
						<c:when test="${request_path >20 && request_path<30}">
							<li class="menu-list nav-active">
						</c:when>
						<c:otherwise>
							<li class="menu-list">
						</c:otherwise>
					</c:choose>
					<a href="#"><i class="fa fa-heart"></i> <span> 订单管理</span> <i
						class="fa fa-angle-right ico"></i> </a>
					<ul class="child-list">
						<c:choose>
							<c:when test="${request_path ==21 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/trade/page">订单列表</a>
						</li>
						<c:choose>
							<c:when test="${request_path ==22 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/tradeBatch/page">批次查询</a>
						</li>
						<c:choose>
							<c:when test="${request_path ==23 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/trade/toExportPage">订单导出</a>
						</li>
					</ul>
					</li>
					<!--  订单管理 end-->

					<!-- 系统管理-->
					<c:choose>
						<c:when test="${request_path >40 && request_path<50}">
							<li class="menu-list nav-active">
						</c:when>
						<c:otherwise>
							<li class="menu-list">
						</c:otherwise>
					</c:choose>
					<a href="#"> <i class="fa fa-laptop"></i> <span>系统管理</span> <i
						class="fa fa-angle-right ico"></i>
					</a>
					<ul class="child-list">
						<c:choose>
							<c:when test="${request_path ==41 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/user/page">用户管理</a>
						</li>
						<c:choose>
							<c:when test="${request_path ==42 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/role/page">角色管理</a>
						</li>
						<c:choose>
							<c:when test="${request_path ==43 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/auth/page">权限管理</a>
						</li>
					</ul>
					</li>
					<!-- 系统管理 end-->

				</ul>

			</div>
		</div>
	</div>
</div>
<!-- 高亮 - 变量 -->
<input id="menu_location" hidden="${request_path}" />