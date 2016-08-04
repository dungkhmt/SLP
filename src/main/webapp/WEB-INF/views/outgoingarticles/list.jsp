<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- DataTables CSS -->
<link href="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"/>" rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="<c:url value="/assets/libs/datatables-responsive/css/dataTables.responsive.css" />" rel="stylesheet">

<!-- DataTables JavaScript -->
<script src="<c:url value="/assets/libs/datatables/media/js/jquery.dataTables.js"/>"></script>
<script src="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.js"/>"></script>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Danh sách hóa đơn đã nhận</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		
		<!-- /.form-group .col-sm-3  -->
		<div class="col-sm-offset-9 col-sm-1">
			<button type="button" class="btn btn-primary addOutOrder">Thêm</button>
		</div>
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Danh các hóa đơn chưa được giao: <b id="dateInfo"></b></div>
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover" id="dataTabels-outarticles">
							<thead>
								<tr>
									<th>Mã Hóa Đơn</th>
									<th>Mã khách hàng</th>
									<th>Ngày giao</th>
									<th>Thời gian giao</th>
									<th>Địa điểm giao</th>
									<th>Giá</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${outArtList}" var="outArt">
									<tr>
										<td><c:out value="${outArt.o_Code}"/></td>
										<td><c:out value="${outArt.o_ClientCode}"/></td>
										<td><c:out value="${outArt.o_DueDate}"/></td>
										<td><c:out value="${outArt.o_TimeEarly}+${outArt.o_TimeLate}"/></td>
										<td><c:out value="${outArt.o_DeliveryAddress }"/></td>	
										<td><c:out value=""/>${outArt.o_Price }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!--/.dataTable_wrapper -->
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
</div>
<!-- /#page-wrapper -->

<script>


$(document).ready(function(){
	var table = $('#dataTabels-outarticles').DataTable();
	
	$('.addOutOrder').click(function(){
		window.location = '${baseUrl}' + "/outgoingarticles/add-an-order.html";
	});
	
	$('#dateInfo').text($('#inArtDate').val());
	
	$("#inArtDate").click(function(){
		$('#dateInfo').text($(this).val());
		table.draw();
	})
});
</script>