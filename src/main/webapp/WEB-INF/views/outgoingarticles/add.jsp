<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/outgoingarticles/addAOrder.html" var="url"/>
<a href="<c:out value='${url}'/>">...</a>
<link href="<c:url value="/assets/libs/bootstrap-datepicker/css/bootstrap-datepicker.css" />" rel="stylesheet" type="text/css" media="all" />
<script src="<c:url value="/assets/libs/bootstrap-datepicker/js/bootstrap-datepicker.js"/>"></script>
<link href="<c:url value="/assets/libs/bootstrap-timepicker/css/bootstrap-timepicker.css" />" rel="stylesheet" type="text/css" media="all" />
<script src="<c:url value="/assets/libs/bootstrap-timepicker/js/bootstrap-timepicker.js"/>"></script>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Thêm mới hóa đơn</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<form:form action="${baseUrl}/cp/save-a-order.html" method="POST" commandName="orderFormAdd" role="form">
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					Thêm mới hóa đơn khách hàng
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
							<div class="form-group">
								<label class="control-label col-sm-2">Số điện thoại</label>
								<form:input path="orderClientCode" class="form-control" name="orderClientCode" placeholder="Number Phone"></form:input>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2">Tên Khách Hàng</label>
								<form:input path="orderClientName" class="form-control" name="orderClientName" placeholder="Client Name"></form:input>
								
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2">Địa chỉ</label>
								<form:input path="orderAdress" class="form-control" name="orderAdress" placeholder="Adress"></form:input>
							</div>
							<div class="form-group col-lg-6">
								<label class="control-label col-sm-2">Ngày giao hàng</label>
								<form:input  path="orderDate" class="form-control datepicker" name="orderDate" placeholder="Date" ></form:input>
							</div>
							<div class="form-group col-lg-6">
								<label class="control-label col-sm-2">Từ:</label>
								<form:input  path="orderTimeEarly" class="form-control timepicker" name="orderTimeEarly" placeholder="TimeEarly" ></form:input>
							</div>
							<div class="form-group col-lg-6">
								<label class="control-label col-sm-2">Đến:</label>
								<form:input  path="orderTimeLate" class="form-control timepicker" name="orderTimeLate" placeholder="TimeLate" ></form:input>
							</div
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</form:form>
</div>
<!-- Javascript -->
<script>
	$(function() {
        $( ".datepicker" ).datepicker();
        
    });
	$('#timepicker').timepicker();
 </script>
