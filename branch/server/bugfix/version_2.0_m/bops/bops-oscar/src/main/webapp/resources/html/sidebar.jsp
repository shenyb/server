<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

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
                    <li class="dashboard active">
                        <a href="#">
                            <i class="fa fa-home"></i>
                            <span>总览</span>
                        </a>
                    </li>
                    <li class="menu-list">
                        <a href="#">
                            <i class="fa fa-gamepad"></i>
                            <span>运营管理</span>
                            <i class="fa fa-angle-right ico"></i>
                        </a>
                        <ul class="child-list">
                            <li><a href="/opassign">运营位审核</a></li>
                            <li><a href="/special/page">内容管理</a></li>
                            <li><a href="/topic/category/page">场景分类管理</a></li>
                            <li><a href="/opsPosition/page">运营位管理</a></li>
                        </ul>
                    </li>
                    <li class="menu-list">
                        <a href="#">
                            <i class="fa fa-heart"></i>
                            <span>行家管理</span>
                            <i class="fa fa-angle-right ico"></i>
                        </a>
                        <ul class="child-list">
                            <li><a href="/expertclassify/page">行家分类管理</a></li>
                            <li><a href="/kol/page">行家管理</a></li>
                        </ul>
                    </li>
                    <li class="menu-list">
                        <a href="#">
                            <i class="fa fa-map-o"></i>
                            <span>优惠券管理</span>
                            <i class="fa fa-angle-right ico"></i>
                        </a>
                        <ul class="child-list">
                            <li><a href="/coupon/page">模版管理</a></li>
                            <li><a href="/coupon/statistics">核销管理</a></li>
                        </ul>
                    </li>
                    <li class="menu-list">
                        <a href="#">
                            <i class="fa fa-laptop"></i>
                            <span>系统管理</span>
                            <i class="fa fa-angle-right ico"></i>
                        </a>
                        <ul class="child-list">
                            <li><a href="/user/page">用户管理</a></li>
                            <li><a href="/role/page">角色管理</a></li>
                            <li><a href="/auth/page">权限管理</a></li>
                        </ul>
                    </li>
                    <li class="menu-list">
                        <a href="#">
                            <i class="fa fa-shield"></i>
                            <span>安全中心</span>
                            <i class="fa fa-angle-right ico"></i>
                        </a>
                        <ul class="child-list">
                            <li><a href="#">Feed管理</a></li>
                            <li><a href="#">评论管理</a></li>
                            <li><a href="/device/feedback/page">意见管理</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
        <!-- 高亮 - 变量 -->
        <input id="menu_location" hidden="${location}"/>