<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/assets/libs/jquery-ui-1.12.0/jquery-ui.css"/>" />
<script
	src="<c:url value="/assets/libs/jquery-ui-1.12.0/jquery-ui.js"/>">
	
</script>

<script type="text/javascript"
	src="<c:url value="/assets/libs/inputDate/dist/js/moment.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/libs/inputDate/dist/js/bootstrap-datetimepicker.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/assets/libs/bootstrap/dist/js/collapse.js" />"></script>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12 center">
			<h1 class="page-header">Thêm mới một user</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<form:form action="${baseUrl}/manage/customermanager/save-a-customer" id="form-add-user"
		method="POST" commandName="customerForm" role="form"
		class="form-horizontal">
		<div class="form-group">
			<label class="control-label col-lg-3">Customer Code</label>
			<div class="col-lg-6">
				<form:input path="Cus_Code" class="form-control" name="Cus_Code"></form:input>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-3">Tên Khách hàng</label>
			<div class="col-lg-6">
				<form:input path="Cus_Name" class="form-control" name="Cus_Name"></form:input>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-3">Phone</label>
			<div class="col-lg-6">
				<form:input path="Cus_Phone" class="form-control" name="Cus_Phone"></form:input>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-3">Địa chỉ</label>
			<div class="col-lg-6">
				<form:input path="Cus_Address" class="form-control" name="Cus_Address"></form:input>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-3">Lat</label>
			<div class="col-lg-6">
				<form:input path="Cus_Lat" class="form-control" name="Cus_Lat"></form:input>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-3">Lng</label>
			<div class="col-lg-6">
				<form:input path="Cus_Lng" class="form-control" name="Cus_Lng"></form:input>
			</div>
		</div>
		<div>
			<button class="btn btn-primary" type="submit">Lưu</button>
		</div>
	</form:form>
</div>

<script type="text/javascript">
	
</script>