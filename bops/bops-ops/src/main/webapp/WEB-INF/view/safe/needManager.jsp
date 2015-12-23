<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 公共头 -->
<%@ include file="/resources/html/header.jsp"%>

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
				<!-- 每页的部分 -->
				<div class="data-page-wrap">
					<div class="con">
				        <div class="page-head">				           
				            <div class="state-information">
				                <ol class="breadcrumb m-b-less bg-less">
				                    <li><a href="#">安全中心</a></li>
				                    <li><a href="#">FEED管理</a></li>
				                    <li class="active">FEED列表</li>
				                </ol>
				            </div>
				        </div>
				
				        <div class="info">
				            <div class="info-top">
				                <span>行家分类列表</span>
				                
				            </div>
				            <div class="search-form">
				                
				            </div>
				            <!--biaoge-->
				            <table class="table table-bordered table-striped table-condensed table-responsive">
				                <thead>
				                <tr>
				                	<th>商品图片</th>
				                	<th>FeedID</th>
                                    <th>发布者</th>                                    
                                    <th>发布时间</th>
                                    <th>Feed内容</th>
                                    <th>商品名称</th>                              		
                                    <th>商品状态</th>
                                    <th>操作</th>	                    
				                </tr>
				                </thead>
			                <tbody>
							      <c:forEach items="${list}" var="feed" varStatus="status">
										<tr class="odd" role="row">
										<td>
											<img alt="商品图片" src="${picHost}${feed.scenePicKey}"></img></td>
											<td class="sorting_1">${feed.feedId}</td>
											<td>${feed.nickName}</td>
											<td><fmt:formatDate value="${feed.createTime}"
													type="both" /></td>
											<td>${feed.memo}</td>
											<td>${feed.goodsName}</td>
											
											<td>
											<c:choose>
											<c:when test="${feed.feedStatus == 'VALID'}">未删除</c:when>
											<c:otherwise>已删除</c:otherwise>
											</c:choose>
											</td>
											<td><a href="javascript:;" data-toggle="modal" data-target="#myModal" onclick="pushVal('${feed.feedId}')">删除</a></td>
										</tr>
									</c:forEach>
				                </tbody>
				            </table>
				             <div class="page">					               
								<div class="pagenum">
									<div aria-live="polite" role="status" id="DataTables_Table_0_info"class="dataTables_info">
										共有 ${page.total}条, 每页显示  ${page.pageSize} 条 
									</div> 
								</div>								
								<div class="tbl-pagin pull-right">
									<div id="light-pagination"></div>
								</div>
				            </div>
				        </div>
				    </div> 
					
					<%-- <!-- 2222222222 -->
					<div class="page-head">
						<h3 class="m-b-less">安全中心</h3>
						<!--<span class="sub-title">Welcome to Static Table</span>-->
						<div class="state-information">
							<ol class="breadcrumb m-b-less bg-less">
								<li><a href="#">安全中心</a></li>
								<li><a href="#">FEED管理</a></li>
								<li class="active">FEED列表</li>
							</ol>
						</div>
					</div>
					<div class="wrapper">
						<div class="row">
							<div class="col-sm-12">
								<section class="panel">
									<header class="panel-heading ">
										行家分类列表 <span class="tools pull-right"> <a
											class="fa fa-repeat box-refresh" href="javascript:;"></a> <a
											class="t-close fa fa-times" href="javascript:;"></a>
										</span>
									</header>
									<div
										class="dataTables_wrapper form-inline dt-bootstrap no-footer"
										id="DataTables_Table_0_wrapper">
										<div class="tbl-head"></div>
										<table aria-describedby="DataTables_Table_0_info" role="grid"
											id="DataTables_Table_0"
											class="table convert-data-table data-table dataTable no-footer">
											<thead>
												<tr role="row">
													<th
														aria-label="OrderDate : activate to sort column descending"
														aria-sort="ascending" style="width: 184px;" colspan="1"
														rowspan="1" aria-controls="DataTables_Table_0"
														tabindex="0" class="sorting_asc">FeedID</th>
													<th aria-label="Region : activate to sort column ascending"
														style="width: 135px;" colspan="1" rowspan="1"
														aria-controls="DataTables_Table_0" tabindex="0"
														class="sorting">发布者</th>
													<th aria-label="Rep : activate to sort column ascending"
														style="width: 181px;" colspan="1" rowspan="1"
														aria-controls="DataTables_Table_0" tabindex="0"
														class="sorting">发布时间</th>
													<th aria-label="Item : activate to sort column ascending"
														style="width: 133px;" colspan="1" rowspan="1"
														aria-controls="DataTables_Table_0" tabindex="0"
														class="sorting">Feed内容</th>
													<th aria-label="Units : activate to sort column ascending"
														style="width: 105px;" colspan="1" rowspan="1"
														aria-controls="DataTables_Table_0" tabindex="0"
														class="sorting">商品名称</th>
													<th
														aria-label="Unit Cost : activate to sort column ascending"
														style="width: 165px;" colspan="1" rowspan="1"
														aria-controls="DataTables_Table_0" tabindex="0"
														class="sorting">商品图片</th>
													<th aria-label="Total: activate to sort column ascending"
														style="width: 145px;" colspan="1" rowspan="1"
														aria-controls="DataTables_Table_0" tabindex="0"
														class="sorting">商品状态</th>
													<th aria-label="Total: activate to sort column ascending"
														style="width: 100px;" colspan="1" rowspan="1"
														aria-controls="DataTables_Table_0" tabindex="0"
														class="sorting">操作</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${list}" var="feed" varStatus="status">
													<tr class="odd" role="row">
														<td class="sorting_1">${feed.feedId}</td>
														<td>${feed.nickName}</td>
														<td><fmt:formatDate value="${feed.createTime}"
																type="both" /></td>
														<td>${feed.memo}</td>
														<td>${feed.goodsName}</td>
														<td> <img  width="60px" alt="商品图片" src="${picHost}${feed.scenePicKey}"></img></td>
														<td>
														<c:choose>
														<c:when test="${feed.feedStatus == 'VALID'}">未删除</c:when>
														<c:otherwise>已删除</c:otherwise>
														</c:choose>
														</td>
														<td><a href="javascript:;" data-toggle="modal" data-target="#myModal" onclick="pushVal('${feed.feedId}')">删除</a></td>
													</tr>
												</c:forEach>

											</tbody>
										</table>
										<div class="tbl-footer clearfix">
											<div class="tbl-info pull-left">
												<div aria-live="polite" role="status"
													id="DataTables_Table_0_info" class="dataTables_info">共有
													${page.total} 条, 每页显示 10 条</div>
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
					<!--2222222222  --> --%>
				</div>

			</div>
		</div>
	</div>
</section>


<!-- 按钮触发模态框
<a  data-toggle="modal" data-target="#myModal">开始演示模态框</a>
 -->
 
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h4 class="modal-title" id="myModalLabel">删除评论</h4>
			</div>
			<div class="modal-body">确定要删除？</div>
			<div class="modal-footer">
				<button type="button" class="button button-capitalize button-rounded button-primary button-small" onclick="del()">确定</button>
				<button type="button"  class="button button-capitalize button-rounded button-primary button-small" data-dismiss="modal">取消</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<!-- 每页的部分 结束 -->
<!-- 在 body的最底部引入js文件且一定保持 jquery 在 bootstrap 之前引入 -->
<script src="/resources/js/jquery/jquery-2.1.4.min.js"></script>
<script src="/resources/js/bootstrap/bootstrap.min.js"></script>
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
           
    function del(){
        to_ajax('/feed/' + fid_val, 'DELETE', {}, callback, 'error');
	}
	function callback(data) {
		//alert('in');
		
		if (data.code == 200) {
			window.location.reload();
		}else{
			alert(data.msg);
			window.location.reload();
		}
	}
	function pushVal(fid){
		fid_val = fid;
	}
	var fid_val = '';
	
</script>

</body>
</html>