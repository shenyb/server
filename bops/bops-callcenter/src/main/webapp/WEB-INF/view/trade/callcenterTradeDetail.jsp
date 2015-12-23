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

<title>客服系统</title>

<!-- 主体部分样式表 -->
<link rel="stylesheet" href="/resources/css/callcenter/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/callcenter/font-awesome.min.css">
<link rel="stylesheet" href="/resources/css/callcenter/ops.css">
<link rel="stylesheet" href="/resources/css/callcenter/datapage.css">
<link rel="stylesheet" href="/resources/css/callcenter/pagination.css" />
<!-- 分页插件 css 样式 -->
<!-- 此部分注释内容兼容IE低版本 H5相关 **不要删除**-->
<!--[if lt IE 9]>
          <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
</head>

<body>
	<section class="layout-wrap">
		<div class="layout-right">
			<div class="layout-right-inner">
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
								 <span class="tools pull-right">
	                               <div class="bops-pub-right">
										<a href="javascript :;" onclick="javascript :history.back(-1);" class="ops-pub-btn">
											<i class="fa fa-arrow-left"></i>
											<span>返回</span>
										</a>
										<a class="ops-pub-btn" href="" >
											<i class="fa fa-refresh"></i>
											<span>刷新</span>
										</a>
									</div>
                                 </span>                       
							</div>
							<div class="wrapper">
								<div class="row">
									<div class="col-sm-12">
										<form id="goodsEditProfile" class="form-horizontal">
											<section class="panel">
												<header class="panel-heading">购物信息</header>
												<div class="panel-body">
													<table id="data" class="table table-hover">
														<thead>
															<tr>
																<th>商品编码:</th>
																<th>国际条形码:</th>
																<th>商品名称:</th>
																<th>购买数量:</th>
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
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
											</section>
											<section class="panel">
												<header class="panel-heading">交易信息</header>
												<div class="panel-body">
													<div class="form-horizontal">
														<div class="form-group">
															<label for="" class="col-lg-2">交易编号</label>
															<div class="col-lg-9">${trade.tradeNo}</div>
														</div>
														<div class="form-group">
															<label for="" class="col-lg-2">订单号</label>
															<div class="col-lg-9">${trade.userTradeNo}</div>
														</div>
														<div class="form-group">
															<label for="" class="col-lg-2">应支付总价</label>
															<div class="col-lg-9">${trade.duepay}</div>
														</div>
														<div class="form-group">
															<label for="" class="col-lg-2">商品总价</label>
															<div class="col-lg-9">${trade.totalFee}</div>
														</div>
														<div class="form-group">
															<label for="" class="col-lg-2">实际支付总价</label>
															<div class="col-lg-9">${trade.payPrice}</div>
														</div>
														<div class="form-group">
															<label for="" class="col-lg-2">运费</label>
															<div class="col-lg-9">${trade.yunfei}</div>
														</div>
														<div class="form-group">
															<label for="" class="col-lg-2">优惠金额</label>
															<div class="col-lg-9">${trade.privilege}</div>
														</div>
														<div class="form-group">
															<label for="" class="col-lg-2">是否为团便宜</label>
															<div class="col-lg-9">
																<c:if test="${trade.isCheap=='TRUE'}">是</c:if>
																<c:if test="${trade.isCheap=='FALSE'}">否</c:if>
															</div>
														</div>
														<div class="form-group">
															<label for="" class="col-lg-2">交易状态</label>
															<div class="col-lg-9">
																<c:choose>
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
																</c:choose>
															</div>
														</div>
														<div class="form-group">
															<label for="" class="col-lg-2">快递公司</label>
															<div class="col-lg-9">${trade.serviceProvider}</div>
														</div>
														<div class="form-group">
															<label for="" class="col-lg-2">快递单号</label>
															<div class="col-lg-9">${trade.logisticsNo}</div>
														</div>
														<div class="form-group">
															<label for="" class="col-lg-2">支付渠道</label>
															<div class="col-lg-9">${trade.payChannel}</div>
														</div>
														<div class="form-group">
															<label for="" class="col-lg-2">交易时间</label>
															<div class="col-lg-9">
																<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
																	value="${trade.tradeTime}" type="both" />
															</div>
														</div>
														<div class="form-group">
															<label for="" class="col-lg-2">支付时间</label>
															<div class="col-lg-9">
																<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
																	value="${trade.payTime}" type="both" />
															</div>
														</div>
													</div>
												</div>
											</section>
											<section class="panel">
												<header class="panel-heading">买家信息</header>
												<div class="panel-body">

													<div class="form-horizontal">
														<div class="form-group">
															<label for="" class="col-lg-2">用户名</label>
															<div class="col-lg-9">${trade.nickName}</div>
														</div>
													</div>
													<div class="form-horizontal">
														<div class="form-group">
															<label for="" class="col-lg-2">手机号</label>
															<div class="col-lg-9">${trade.mobile}</div>
														</div>
													</div>
												</div>
											</section>
											<section class="panel">
												<header class="panel-heading">收货信息</header>
												<div class="panel-body">
													<div class="form-group">
														<label for="" class="col-lg-2">收货人姓名</label>
														<div class="col-lg-9">${trade.receiver}</div>
													</div>

													<div class="form-group">
														<label for="" class="col-lg-2">收货人身份证号</label>
														<div class="col-lg-9">${trade.certificationCard}</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">收货人联系电话</label>
														<div class="col-lg-9">${trade.telephone}</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">收货地址</label>
														<div class="col-lg-9">${trade.address}</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">收货地址（省市区）</label>
														<div class="col-lg-9">${trade.logisticsValue}</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">身份证正面</label>
														<div class="col-lg-9">
															<c:if test="${!empty trade.certificationPositiveKey}">
																<a href="${picAddress}${trade.certificationPositiveKey}"
																	target="_blank"> <img width="60px" alt="身份证正面"
																	src="${picAddress}${trade.certificationPositiveKey}">
																</a>
															</c:if>
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-lg-2">身份证反面</label>
														<div class="col-lg-9">
															<c:if test="${!empty trade.certificationNegativeKey}">
																<a href="${picAddress}${trade.certificationNegativeKey}"
																	target="_blank"> <img width="60px" alt="身份证反面"
																	src="${picAddress}${trade.certificationNegativeKey}">
																</a>
															</c:if>
														</div>
													</div>
												</div>
											</section>
											<section class="panel">
												<header class="panel-heading">流转信息</header>
												<div class="panel-body">
													<table id="data" class="table table-hover">
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
																		${tradeStatsu.trackingDesc}
																	</td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
											</section>
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
	<script type="text/javascript">
</body>
</html>