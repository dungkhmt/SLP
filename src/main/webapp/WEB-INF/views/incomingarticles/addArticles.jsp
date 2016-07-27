<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<link rel="stylesheet" type="text/css" href="<c:url value="/assets/libs/inputDate/dist/css/bootstrap-datetimepicker.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/assets/libs/inputDate/dist/css/bootstrap-datetimepicker.min.css"/>"/>

<script type="text/javascript" src="<c:url value="/assets/libs/inputDate/dist/js/moment.js" />"></script>
<script type="text/javascript" src="<c:url value="/assets/libs/inputDate/dist/js/bootstrap-datetimepicker.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/assets/libs/bootstrap/dist/js/collapse.js" />"></script>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Nhập hàng</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	
	<form:form class="form-horizontal" role="form" commandName="articleFormAdd" method="POST">	
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading">
					Thêm mặt hàng
				</div>
				<div class="panel-body">
					<div class="form-group">
						<label class="control-label col-sm-2">Số lượng</label>
						<div class="col-sm-6">
							<input type="text" class="form-control"/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2">Giá</label>
						<div class="col-sm-6">
							<input type="text" class="form-control"/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2">Nhà cung cấp</label>
						<div class="col-sm-6">
							<form:select path="articleCode" name="articleCode" class="form-control">
								<option value="">Chọn nhà cung cấp</option>
								<c:forEach items="${listSuppliers}" var="supplier">
									<option value="${supplier.Supplier_Name}">${supplier.Supplier_Name}</option>
								</c:forEach>
							</form:select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2">Ngày nhập</label>
		                <div class='input-group date col-sm-6' id='datetimepicker1'>
		                    <input type='text' class="form-control"	/>
		                    <span class="input-group-addon">
		                        <span class="glyphicon glyphicon-calendar"></span>
		                    </span>
		                </div>
		            </div>
				</div>
				<!--/.panel-body  -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.row -->
	</form:form>
</div>
<!-- /#page-wrapper -->

 <script type="text/javascript">
$(document).ready(function () {
	$('#datetimepicker1').datetimepicker();
});
</script>
