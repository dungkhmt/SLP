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
			<h1 class="page-header">Danh sách các tuyến chuyển hàng</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover" id="dataTabels-pDL">
							<thead>
								<tr>
									<th>Mã KH</th>
									<th>Tên KH</th>
									<th>Địa điểm đón hàng</th>
									<th>Thời gian đón hàng dự kiến </th>
									<th>Thời gian đón hàng yêu cầu </th>
									<th>Địa điểm trả hàng</th>
									<th>Thời gian trả hàng dự kiến</th>
									<th>Thời  gian trả hàng yêu cầu</th>
									<th>Số lượng</th>
									<th>Số thứ tự</th>
									<th>Tài xế</th>
									<th>Giá</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${piDelist}" var="pDL">
									<tr>
										<td><c:out value="${pDL.OPD_Code}"/></td>
										<td><c:out value="${pDL.OPD_ClientCode}"/></td>
										<td><c:out value="${pDL.OPD_EarlyPickupDateTime} -${pDL.OPD_LatePickupDateTime}"/></td>
										<td><c:out value="${pDL.OPD_PickupAddress}"/></td>
										<td><c:out value="${pDL.OPD_EarlyDeliveryDateTime} -${pDL.OPD_LateDeliveryDateTime}"/></td>
										<td><c:out value="${pDL.OPD_DeliveryAddress}"/></td>
										<td><c:out value=""/>${pDL.OPD_Price }</td>
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
	var table = $('#dataTabels-pDL').DataTable();
	
	$('.addOutOrder').click(function(){
		window.location = '${baseUrl}' + "/containerdelivery/add-a-pickupdelivery-order.html";
	});
	

});
</script>