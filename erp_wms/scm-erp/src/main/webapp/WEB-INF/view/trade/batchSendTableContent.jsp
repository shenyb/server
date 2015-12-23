<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<section class="panel">
	<header class="panel-heading ">
		<!-- 列表名称 -->
		<span style="font-weight: bold; color: red; font-size: 150%;">发货失败列表</span>
	</header>
	<table aria-describedby="DataTables_Table_0_info" role="grid"
		class="table convert-data-table data-table dataTable no-footer">
		<thead>
			<tr role="row">
				<th aria-label="OrderDate : activate to sort column descending"
					aria-sort="ascending" style="width: 80px;" colspan="1" rowspan="1"
					aria-controls="DataTables_Table_0" tabindex="0" class="sorting_asc">订单号</th>
				<th aria-label="Region : activate to sort column ascending"
					style="width: 90px;" colspan="1" rowspan="1"
					aria-controls="DataTables_Table_0" tabindex="0" class="sorting">快递公司ID</th>
				<th aria-label="Rep : activate to sort column ascending"
					style="width: 120px;" colspan="1" rowspan="1"
					aria-controls="DataTables_Table_0" tabindex="0" class="sorting">快递单号</th>
				<th aria-label="Rep : activate to sort column ascending"
					style="width: 120px;" colspan="1" rowspan="1"
					aria-controls="DataTables_Table_0" tabindex="0" class="sorting">备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listFail}" var="trade" varStatus="status">
				<tr class="odd" role="row">
					<td>${trade.orderNo}</td>
					<td>${trade.serviceProvider}</td>
					<td>${trade.logisticsNo}</td>
					<td>${trade.message}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</section>

<section class="panel">
	<header class="panel-heading ">
		<!-- 列表名称 -->
		<span style="font-weight: bold; color: green; font-size: 150%;">发货成功列表</span>
	</header>
	<table aria-describedby="DataTables_Table_0_info" role="grid"
		class="table convert-data-table data-table dataTable no-footer">
		<thead>
			<tr role="row">
				<th aria-label="OrderDate : activate to sort column descending"
					aria-sort="ascending" style="width: 80px;" colspan="1" rowspan="1"
					aria-controls="DataTables_Table_0" tabindex="0" class="sorting_asc">订单号</th>
				<th aria-label="Region : activate to sort column ascending"
					style="width: 90px;" colspan="1" rowspan="1"
					aria-controls="DataTables_Table_0" tabindex="0" class="sorting">快递公司ID</th>
				<th aria-label="Rep : activate to sort column ascending"
					style="width: 120px;" colspan="1" rowspan="1"
					aria-controls="DataTables_Table_0" tabindex="0" class="sorting">快递单号</th>
				<th aria-label="Rep : activate to sort column ascending"
					style="width: 120px;" colspan="1" rowspan="1"
					aria-controls="DataTables_Table_0" tabindex="0" class="sorting">备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listSuccess}" var="trade" varStatus="status">
				<tr class="odd" role="row">
					<td>${trade.orderNo}</td>
					<td>${trade.serviceProvider}</td>
					<td>${trade.logisticsNo}</td>
					<td>${trade.message}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</section>
