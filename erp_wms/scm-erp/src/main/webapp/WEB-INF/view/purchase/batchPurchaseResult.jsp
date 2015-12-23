<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${bopsPurchaseVOAll.flag =='1'}">
<section class="panel" id="fail">
<header class="panel-heading ">
<!-- 列表名称 -->
<span style="font-weight: bold;color: red;font-size: 150%;">导入采购单失败列表</span>
</header>
<table aria-describedby="DataTables_Table_0_info"
role="grid" 
class="table convert-data-table data-table dataTable no-footer">
<thead>
	<tr role="row">
		<th aria-label="OrderDate : activate to sort column descending"
			aria-sort="ascending" style="width: 80px;" colspan="1" rowspan="1"
			aria-controls="DataTables_Table_0" tabindex="0" class="sorting_asc">商品编号</th>
		<th aria-label="Region : activate to sort column ascending"
			style="width: 90px;" colspan="1" rowspan="1"
			aria-controls="DataTables_Table_0" tabindex="0" class="sorting">国际条形码</th>
		<th aria-label="Rep : activate to sort column ascending"
			style="width: 120px;" colspan="1" rowspan="1"
			aria-controls="DataTables_Table_0" tabindex="0" class="sorting">商品名称</th>
		<th aria-label="Rep : activate to sort column ascending"
			style="width: 120px;" colspan="1" rowspan="1"
			aria-controls="DataTables_Table_0" tabindex="0" class="sorting">采购单价</th>
		<th aria-label="Rep : activate to sort column ascending"
			style="width: 120px;" colspan="1" rowspan="1"
			aria-controls="DataTables_Table_0" tabindex="0" class="sorting">采购数量</th>
		<th aria-label="Rep : activate to sort column ascending"
			style="width: 120px;" colspan="1" rowspan="1"
			aria-controls="DataTables_Table_0" tabindex="0" class="sorting">备注</th>
	</tr>
</thead>
<tbody>
	<c:forEach items="${failList}" var="trade" varStatus="status">
		<tr class="odd" role="row">
			<td>${trade.goodsCode}</td>
			<td>${trade.goodsBarcode}</td>
			<td>${trade.goodsName}</td>
			<td>${trade.purchasePrice}</td>
			<td>${trade.purchaseCount}</td>
			<td>${trade.message}</td>
		</tr>
	</c:forEach>
</tbody>
</table>
</section>
</c:if>
<c:if test="${bopsPurchaseVOAll.flag =='0'}">
<section class="panel" id="success">
<header class="panel-heading ">
<!-- 列表名称 -->
	<span style="font-weight: bold;color: green;font-size: 150%;">导入采购单成功列表</span>
</header>
<table aria-describedby="DataTables_Table_0_info"
role="grid" 
class="table convert-data-table data-table dataTable no-footer">
<thead>
	总采购金额: ${bopsPurchaseVOAll.purchasePriceAll} (${bopsPurchaseVOAll.purchaseCurrency})&nbsp;&nbsp;&nbsp;&nbsp; 总采购数量: ${bopsPurchaseVOAll.purchaseCountAll}
	<tr role="row">
		<th aria-label="OrderDate : activate to sort column descending"
			aria-sort="ascending" style="width: 80px;" colspan="1" rowspan="1"
			aria-controls="DataTables_Table_0" tabindex="0" class="sorting_asc">商品编号</th>
		<th aria-label="Region : activate to sort column ascending"
			style="width: 90px;" colspan="1" rowspan="1"
			aria-controls="DataTables_Table_0" tabindex="0" class="sorting">国际条形码</th>
		<th aria-label="Rep : activate to sort column ascending"
			style="width: 120px;" colspan="1" rowspan="1"
			aria-controls="DataTables_Table_0" tabindex="0" class="sorting">商品名称</th>
		<th aria-label="Rep : activate to sort column ascending"
			style="width: 120px;" colspan="1" rowspan="1"
			aria-controls="DataTables_Table_0" tabindex="0" class="sorting">采购单价</th>
		<th aria-label="Rep : activate to sort column ascending"
			style="width: 120px;" colspan="1" rowspan="1"
			aria-controls="DataTables_Table_0" tabindex="0" class="sorting">采购数量</th>
	</tr>
</thead>
<tbody>
	<c:forEach items="${successList}" var="trade" varStatus="status">
		<tr class="odd" role="row">
			<td>${trade.goodsCode}</td>
			<td>${trade.goodsBarcode}</td>
			<td>${trade.goodsName}</td>
			<td>${trade.purchasePrice}</td>
			<td>${trade.purchaseCount}</td>
		</tr>
	</c:forEach>
</tbody>
</table>
<input type="hidden" name="successList" value='${successJson}'>
<input type="hidden" name="bopsPurchaseVOAll" value='${purchaseAll}'>
<div class="form-group btnlist">
<a class="btn btn-info" id="save">提交</a>
<input type="hidden" name="flag" id="flag" value="1">
</div>
</section>
</c:if>
