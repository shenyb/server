<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 左侧导航栏 html 页面 -->
<div class="sidebar">
	<div class="sidebar-top">
		<div class="logo">
			<a href="javascript:;">
				<img src="/resources/img/common/ops-logo.png">
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

					<!-- 运营管理 -->
					<c:choose>
						<c:when test="${request_path >10 && request_path<20}">
							<li class="menu-list nav-active">
						</c:when>
						<c:otherwise>
							<li class="menu-list">
						</c:otherwise>
					</c:choose>
					<a href="#"> <i class="fa fa-user"></i> <span>运营管理</span> <i
						class="fa fa-angle-right ico"></i>
					</a>
					<ul class="child-list">
						<c:choose>
							<c:when test="${request_path ==11 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/opassign">专题投放管理</a>
						</li>
						<c:choose>
							<c:when test="${request_path ==12 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/special/page">专题创建管理</a>
						</li>
						<c:choose>
							<c:when test="${request_path ==13 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/topic/category/page">场景分类管理</a>
						</li>
						<c:choose>
							<c:when test="${request_path ==14 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/opsPosition/page">专题位管理</a>
						</li>
						
 					  <%-- <c:choose>
							<c:when test="${request_path ==15 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/indexCategory/page">商品分类</a>
						</li>	
						
					   <c:choose>
							<c:when test="${request_path ==16 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/goods/page">商品管理</a>
						</li> --%>
						
												
					</ul>
					</li>
					<!-- 运营管理  end-->
                    <!-- 行家分类管理-->
					<c:choose>
						<c:when test="${request_path >110 && request_path<120}">
							<li class="menu-list nav-active">
						</c:when>
						<c:otherwise>
							<li class="menu-list">
						</c:otherwise>
					</c:choose>
					<a href="#"><i class="fa fa-shopping-bag"></i> <span>商品管理</span> <i
						class="fa fa-angle-right ico"></i> </a>
					<ul class="child-list">
						<c:choose>
							<c:when test="${request_path ==111 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/goods/page">商品列表</a>
						</li>
						<c:choose>
							<c:when test="${request_path ==112 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/indexCategory/page">商品分类</a>
						</li>
					</ul>
					</li>
					<!-- 行家分类管理 end-->
					<!-- 行家分类管理-->
					<c:choose>
						<c:when test="${request_path >20 && request_path<30}">
							<li class="menu-list nav-active">
						</c:when>
						<c:otherwise>
							<li class="menu-list">
						</c:otherwise>
					</c:choose>
					<a href="#"><i class="fa fa-heart"></i> <span>行家管理</span> <i
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
						<a href="/expertclassify/page">行家分类管理</a>
						</li>
						<c:choose>
							<c:when test="${request_path ==22 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/kol/page">行家管理</a>
						</li>
					</ul>
					</li>
					<!-- 行家分类管理 end-->

					<!-- 优惠券管理-->
					<c:choose>
						<c:when test="${request_path >30 && request_path<40}">
							<li class="menu-list nav-active">
						</c:when>
						<c:otherwise>
							<li class="menu-list">
						</c:otherwise>
					</c:choose>
					<a href="#"> <i class="fa fa-ticket"></i> <span>优惠券管理</span> 
						<i class="fa fa-angle-right ico"></i>
					</a>
					<ul class="child-list">
						<c:choose>
							<c:when test="${request_path ==31 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/coupon/page">模版管理</a>
						</li>
						<c:choose>
							<c:when test="${request_path ==32 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/coupon/statistics">核销管理</a>
						</li>
						<c:choose>
							<c:when test="${request_path ==33 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/coupon/exchange/page">兑换管理</a>
						</li>
						<c:choose>
							<c:when test="${request_path ==34 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/coupon/discount/page">领取管理</a>
						</li>
					</ul>
					</li>
					<!-- 优惠券管理 end-->
					
					<!-- 团便宜管理-->
					<c:choose>
						<c:when test="${request_path >60 && request_path<70}">
							<li class="menu-list nav-active">
						</c:when>
						<c:otherwise>
							<li class="menu-list">
						</c:otherwise>
					</c:choose>
					<a href="#"> <i class="fa fa-puzzle-piece"></i> <span>团便宜管理</span> 
					<i class="fa fa-angle-right ico"></i>
					</a>
					<ul class="child-list">
						<c:choose>
							<c:when test="${request_path ==61 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/cheap/page">团便宜管理</a>
						</li>
						<c:choose>
							<c:when test="${request_path ==62 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/cheap/cheapStat">团便宜统计</a>
						</li>
						
					</ul>
					</li>
					<!-- 优惠券管理 end-->
					
			     <!-- 商品组合管理-->
					<c:choose>
						<c:when test="${request_path >70 && request_path<80}">
							<li class="menu-list nav-active">
						</c:when>
						<c:otherwise>
							<li class="menu-list">
						</c:otherwise>
					</c:choose>
					<a href="#"> <i class="fa fa-percent"></i> <span>组合促销管理</span> <i
						class="fa fa-angle-right ico"></i>
					</a>
					<ul class="child-list">
						<c:choose>
							<c:when test="${request_path ==71 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/goodsGroup/page">促销列表</a>
						</li>
						
					</ul>
					</li>
					 <!-- 分销管理中心-->
					<c:choose>
						<c:when test="${request_path >90 && request_path<100}">
							<li class="menu-list nav-active">
						</c:when>
						<c:otherwise>
							<li class="menu-list">
						</c:otherwise>
					</c:choose>
					<a href="#"> <i class="fa fa-money"></i> <span>商品分销管理</span> <i
						class="fa fa-angle-right ico"></i>
					</a>
					<ul class="child-list">
						<c:choose>
							<c:when test="${request_path ==91 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/distribution/page">商品分销列表</a>
						</li>
						<c:choose>
							<c:when test="${request_path ==92 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/distribution/goods/page">分销商品统计</a>
						</li>
						<c:choose>
							<c:when test="${request_path ==93 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/distribution/user/page">用户佣金统计</a>
						</li>
						
					</ul>
					</li>
                                         
                                         
                                         <!-- feed管理-->
					<c:choose>
						<c:when test="${request_path >100 && request_path<110}">
							<li class="menu-list nav-active">
						</c:when>
						<c:otherwise>
							<li class="menu-list">
						</c:otherwise>
					</c:choose>
					<a href="#"> <i class="fa fa-star-half-o"></i> <span>feed管理</span> 
					<i class="fa fa-angle-right ico"></i>
					</a>
					<ul class="child-list">
						<c:choose>
							<c:when test="${request_path ==101 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/user/feed/page">feed管理</a>
						</li>
						<c:choose>
							<c:when test="${request_path ==102 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/user/feed/report/page">feed未处理举报管理</a>
						</li>
						<c:choose>
							<c:when test="${request_path ==103 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/user/feed/handledReport/page">feed已处理举报管理</a>
						</li>
					</ul>
					</li>
					<!-- feed管理 end-->
                                         
                                         
					 <!-- 举报管理中心
					<c:choose>
						<c:when test="${request_path >80 && request_path<90}">
							<li class="menu-list nav-active">
						</c:when>
						<c:otherwise>
							<li class="menu-list">
						</c:otherwise>
					</c:choose>
					<a href="#"> <i class="fa fa-map-o"></i> <span>举报管理</span> <i
						class="fa fa-angle-right ico"></i>
					</a>
					<ul class="child-list">
						<c:choose>
							<c:when test="${request_path ==81 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/feedInform/page">Feed未处理</a>
						</li>
						<c:choose>
							<c:when test="${request_path ==82 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/feedInform/page">Feed已处理</a>
						</li>
						
					</ul>
					</li>
					-->
					<!-- 优惠券管理 end-->
					
					

					<!-- 系统管理-->
					<c:choose>
						<c:when test="${request_path >40 && request_path<50}">
							<li class="menu-list nav-active">
						</c:when>
						<c:otherwise>
							<li class="menu-list">
						</c:otherwise>
					</c:choose>
					<a href="#"> <i class="fa fa-laptop"></i> <span>OPS系统管理</span> <i
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
						<c:choose>
							<c:when test="${request_path ==44 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/systemSetting/page">系统设置</a>
						</li>
					</ul>
					</li>
					<!-- 系统管理 end-->

					<!-- 安全中心 -->
					<c:choose>
						<c:when test="${request_path >50 && request_path<60}">
							<li class="menu-list nav-active">
						</c:when>
						<c:otherwise>
							<li class="menu-list">
						</c:otherwise>
					</c:choose>
					<a href="#"> <i class="fa fa-shield"></i> <span>安全中心</span> <i
						class="fa fa-angle-right ico"></i>
					</a>
					<ul class="child-list">
						<c:choose>
							<c:when test="${request_path ==51 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/feed/page">Feed管理</a>
						</li>
						<c:choose>
							<c:when test="${request_path ==52 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/feedComment/page">评论管理</a>
						</li>
						<c:choose>
							<c:when test="${request_path ==53 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
						<a href="/device/feedback/page">意见管理</a>
						</li>
					</ul>
					</li>
				</ul>

			</div>
		</div>
	</div>
</div>
<!-- 高亮 - 变量 -->
<input id="menu_location" hidden="${request_path}" />