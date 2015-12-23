<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!-- IE=edge告诉IE使用最新的引擎渲染网页 -->
<!-- chrome=1则可以激活Chrome Frame , Chrome Frame可以让旧版IE浏览器使用Chrome的WebKit渲染引擎处理网页 -->
<meta
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0"
	name="viewport">
<!-- 强制让文档的宽度与设备的宽度保持1:1，并且文档最大的宽度比例是1.0，且不允许用户点击屏幕放大浏览 -->

<meta content="yes" name="apple-mobile-web-app-capable" />
<!-- iphone设备中的safari私有meta标签，它表示：允许全屏模式浏览 -->

<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<!-- iphone的私有标签，它指定的iphone中safari顶端的状态条的样式 -->

<meta content="telephone=no" name="format-detection" />
<!-- 告诉设备忽略将页面中的数字识别为电话号码 -->

<meta content="email=no" name="format-detection" />
<!-- android设备忽略将页面中的email识别为电子邮箱 -->

<link rel="shortcut icon" href="/resources/img/common/favicon.ico"
	type="image/x-icon" />

<title>ERP管理系统</title>

<!-- 主体部分样式表 -->
<link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/bootstrap/button.css">
<link rel="stylesheet" href="/resources/css/font-awesome.min.css">
<link rel="stylesheet" href="/resources/css/layout.css">
<link rel="stylesheet" href="/resources/css/ops.css">
<link rel="stylesheet" href="/resources/css/sidebar.css">
<link rel="stylesheet" href="/resources/css/content-header.css">
<link rel="stylesheet" href="/resources/css/datapage.css">
<link rel="stylesheet" href="/resources/css/form.css">
<link rel="stylesheet" href="/resources/css/pagination.css">
<link rel="stylesheet" href="/resources/css/trade.css">

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
					<!-- 
                            这部分写各自页面对应的内容
                            注意锁紧格式
                            范围 start -
                         -->

					<div class="form-page-wrap">
						<div class="data-page-inner">
							<div class="page-head">
								<h3 class="m-b-less">查看交易详细信息</h3>
								<div class="back">
									<a href="javascript:javascript:window.history.back(-1);"
										class="button button-primary button-raised button-small" role="button">返回</a> <a
										href="javascript:javascript:window.location.reload();"
										class="button button-primary button-raised button-small" role="button">刷新</a>
								</div>
							</div>
							<div class="wrapper">
								<div class="row">
									<div class="col-sm-12">
										<form id="goodsEditProfile" class="form-horizontal">
											<div class="info">
												<table
													class="table table-bordered table-hover table-condensed table-responsive">
													<!--<tbody>-->
													<tr class="table-title">
														<td colspan="6">订单详情</td>
													</tr>
													<tr>
														<td>订单号</td>
														<td>${trade.userTradeNo}</td>
														<td>支付渠道</td>
														<td>${trade.payChannel}</td>
														<td>应支付总价</td>
														<td>${trade.duepay}</td>
													</tr>
													<tr>
														<td>实际支付总价</td>
														<td>${trade.payPrice}</td>
														<td>商品总价</td>
														<td>${trade.totalFee}</td>
														<td>优惠金额</td>
														<td>${trade.privilege}</td>
													</tr>
													<tr>
														<td>运费(元)</td>
														<td>${trade.yunfei}</td>
														<td>交易状态</td>
														<td><c:choose>
																	<c:when test="${trade.tradeStatus=='WAIT_PAY'}">
                                	待付款
                              </c:when>
																	<c:when
																		test="${trade.tradeStatus=='WAIT_PLATFORM_SEND'}">
                                	待发货
                              </c:when>
																	<c:when test="${trade.tradeStatus=='WAIT_RECEIVE'}">
                                	待收货
                              </c:when>
																	<c:when test="${trade.tradeStatus=='TRADE_SUCCESS'}">
                                	交易成功
                              </c:when>
																	<c:when test="${trade.tradeStatus=='TRADE_CLOSE'}">
                                	交易关闭
                              </c:when>
																	<c:otherwise>
                                	退款成功
                              </c:otherwise>
																</c:choose></td>
														<td></td>
														<td></td>
													</tr>
													<tr>
														<td>交易时间</td>
														<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
																	value="${trade.tradeTime}" type="both" /></td>
														<td>支付时间</td>
														<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
																	value="${trade.payTime}" type="both" /></td>
														<td>订单仓库</td>
														<td>${trade.warehouseType}</td>
													</tr>

													<tr class="table-title">
														<td colspan="6">收货人信息</td>
													</tr>
													<tr>
														<td>买家用户名</td>
														<td>${trade.nickName}</td>
														<td>买家手机号</td>
														<td>${trade.mobile}</td>
														<td></td>
														<td></td>
													</tr>
													<tr>
														<td>收货人姓名</td>
														<td>${trade.receiver}</td>
														<td>收件人手机号</td>
														<td>${trade.telephone}</td>
														<td>收货人身份证号</td>
														<td>${trade.certificationCard}</td>
													</tr>
													<tr>
														<td>收货区域</td>
														<td>${trade.logisticsValue}</td>
														<td>收货地址</td>
														<td>${trade.address}</td>
														<td></td>
														<td></td>
													</tr>
													<tr>
														<td>身份证正面照片</td>
														<td>
														<c:if test="${look=='TRUE'}">
														<c:if test="${!empty trade.certificationPositiveKey}">
																<a href="${picAddress}${trade.certificationPositiveKey}"
																	target="_blank"> <img width="60px" alt="身份证正面"
																	src="${picAddress}${trade.certificationPositiveKey}">
																</a>
															</c:if>
															</c:if></td>
														<td>身份证反面照片</td>
														<td><c:if test="${look=='TRUE'}">
														<c:if test="${!empty trade.certificationNegativeKey}">
																<a href="${picAddress}${trade.certificationNegativeKey}"
																	target="_blank"> <img width="60px" alt="身份证反面"
																	src="${picAddress}${trade.certificationNegativeKey}">
																</a>
															</c:if>
															</c:if></td>
														<td></td>
														<td></td>
													</tr>
												</table>

												<table
													class="table table-bordered table-hover table-condensed table-responsive">
													<div class="table-title">商品详细</div>
													<thead>
														<tr>
															<th>商品编号</th>
															<th>国际条形码</th>
															<th>商品名称</th>
															<th>商品数量</th>
															<th>Need价</th>
														</tr>
													</thead>
													<tbody>
													<c:forEach items="${trade.goodsList}" var="goods"
																varStatus="status">
																<tr>
																	<td>${goods.goodsCode}</td>
																	<td>${goods.goodsBarcode}</td>
																	<td>${goods.goodsName}</td>
																	<td>${goods.goodsCount}</td>
																	<td>${goods.discountPrice}</td>
																</tr>
															</c:forEach>
													</tbody>
												</table>
												<table
													class="table table-bordered table-hover table-condensed table-responsive">
													<div class="table-title">快递信息</div>
													<thead>
														<tr>
															<th>快递公司</th>
															<th>${trade.serviceProvider}</th>
															<th>快递单号</th>
															<th>${trade.logisticsNo}</th>
														</tr>
													</thead>
												</table>
												<table
													class="table table-bordered table-hover table-condensed table-responsive">
													<div class="table-title">流转信息</div>
													<thead>
														<tr>
															<th>流转状态</th>
															<th>时间</th>
															<th>流转信息</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${tradeStatusList}" var="tradeStatus"
																varStatus="status">
																<tr>
																	<td>
																	${tradeStatus.trackingStatus}
																	</td>
																	<td><fmt:formatDate value="${tradeStatus.createTime}"
																			type="both" /></td>
																	<td>
																	${tradeStatus.trackingDesc}
																	</td>
																</tr>
															</c:forEach>
													</tbody>
												</table>
											</div>
										</form>
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

	<!-- 在 body的最底部引入js文件且一定保持 jquery 在 bootstrap 之前引入 -->
	<script src="/resources/js/jquery/jquery-2.1.4.min.js"></script>
	<script src="/resources/js/bootstrap/bootstrap.min.js"></script>
	<script src="/resources/js/jquery_upload/vendor/jquery.ui.widget.js"></script>
	<script src="/resources/js/jquery_upload/jquery.iframe-transport.js"></script>
	<script src="/resources/js/jquery_upload/jquery.fileupload.js"></script>
	<!-- 侧面导航栏组件 js -->
	<script src="/resources/js/sidebar.js"></script>
	<script src="/resources/js/jquery.pagination.js"></script>
</body>
</html>