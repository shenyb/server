<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<section class="panel">
	<header class="panel-heading ">
		<!-- 列表名称 -->
		<span style="font-weight: bold;"> 导入后-无错误数据展示如下 </span>
	</header>
	<table aria-describedby="DataTables_Table_0_info" role="grid"
		class="table convert-data-table data-table dataTable no-footer">
		<thead>
			<tr role="row">
				<th aria-label="OrderDate : activate to sort column descending"
					aria-sort="ascending" style="width: 80px;" colspan="1" rowspan="1"
					aria-controls="DataTables_Table_0" tabindex="0" class="sorting_asc">*商品编号</th>
				<th aria-label="Region : activate to sort column ascending"
					style="width: 90px;" colspan="1" rowspan="1"
					aria-controls="DataTables_Table_0" tabindex="0" class="sorting">*Need价</th>
				<th aria-label="Rep : activate to sort column ascending"
					style="width: 120px;" colspan="1" rowspan="1"
					aria-controls="DataTables_Table_0" tabindex="0" class="sorting">恢复价格</th>
				<th aria-label="Rep : activate to sort column ascending"
					style="width: 120px;" colspan="1" rowspan="1"
					aria-controls="DataTables_Table_0" tabindex="0" class="sorting">商品采购价</th>
				<th aria-label="Rep : activate to sort column ascending"
					style="width: 120px;" colspan="1" rowspan="1"
					aria-controls="DataTables_Table_0" tabindex="0" class="sorting">商品名称</th>
				<th aria-label="Rep : activate to sort column ascending"
					style="width: 120px;" colspan="1" rowspan="1"
					aria-controls="DataTables_Table_0" tabindex="0" class="sorting">毛利</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="goodsPrice" varStatus="status">
				<tr class="odd" role="row">
					<td>${goodsPrice.goodsCode}</td>
					<td>${goodsPrice.discountPrice}</td>
					<td>${goodsPrice.originalPrice}</td>
					<td>${goodsPrice.purchasePrice}</td>
					<td>${goodsPrice.goodsName}</td>
					<td>${goodsPrice.profit}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</section>