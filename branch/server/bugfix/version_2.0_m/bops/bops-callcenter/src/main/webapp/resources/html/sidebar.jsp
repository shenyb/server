<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 左侧导航栏 html 页面 -->
<div class="sidebar">
    <div class="sidebar-top">
        <div class="logo">
            <a href="#">
                <span class="brand-name"></span>
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
                    <c:choose>
						<c:when test="${request_path != null }">
							<li class="dashboard">
						</c:when>
						<c:otherwise>
							<li class="dashboard active">
						</c:otherwise>
					</c:choose>
                        <a href="#">
                            <i class="fa fa-home"></i>
                            <span>总览</span>
                        </a>
                    </li>
                     <c:choose>
						<c:when test="${request_path >10 && request_path<20}">
							<li class="menu-list nav-active">
						</c:when>
						<c:otherwise>
							<li class="menu-list">
						</c:otherwise>
					</c:choose>
                        <a href="#">
                            <i class="fa fa-gamepad"></i>
                            <span>交易管理</span>
                            <i class="fa fa-angle-right ico"></i>
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
                            <a href="/trade/page">交易列表</a></li>
                            <c:choose>

							<c:when test="${request_path ==12 }">
								<li class="menu-list active">
							</c:when>
							<c:otherwise>
								<li>
							</c:otherwise>
						</c:choose>
                            <a href="/tradeBatch/page">批次查询</a></li>
                        </ul>
                    </li>
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
                    
                </ul>
            </div>
        </div>
    </div>
</div>
        <!-- 高亮 - 变量 -->
        <input id="menu_location" hidden="${location}"/>