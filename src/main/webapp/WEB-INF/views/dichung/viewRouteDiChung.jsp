<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="<c:url value="/assets/libs/datatables/media/js/jquery.dataTables.js"/>"></script>
<script src="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.js"/>"></script>

	
<div id="page-wrapper">
	<div class="form-group ">
		<label class="control-label col-sm-2" >Chọn Lô</label>
		<div class="col-sm-2">
			<select class="form-control batchselect" onchange="loadRoute()"  >
				<option value="">Chọn Lô</option>
				<c:forEach items="${listBatch}" var="lbatch">
                	<option value="${lbatch.REQBAT_Code}">${lbatch.REQBAT_Code}</option>
                </c:forEach>
            </select>
         </div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Chi tiết các tuyến</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover" id="tableRoute">
							<thead>
								<tr>
									<th>TicketCode</th>
									<th>Address</th>
									<th>PickupDateTime</th>
									<th>DistanceToNext </th>
									<th>TravelTimeToNext </th>
									<th>Sequence </th>
								</tr>
							</thead>
							<tbody>
								
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
	$("#tableRoute").DataTable({
		
	});
});
function loadRoute(){
	var batchCode= $(".batchselect").val();
	console.log(batchCode);
	$.ajax({ 
	    type:"POST", 
	    url:"${baseUrl}/dichung/load-route-in-batch",
	    data: batchCode,
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    success: function(response){
	        // Success Message Handler
	        //console.log(response);
	        loadTable(response);
	    }
    });
	
}

function loadTable(data){
	console.log("loaddata");
	$("table#tableRoute tbody").html("");
	for(var i=0;i<data.length;i++){
		str="<tr>";
		str+="<td>"+data[i].rddc_TicketCode+"</td>"
		str+="<td>"+data[i].rddc_Address+"</td>"
		str+="<td>"+"-"+"</td>"
		str+="<td>"+data[i].rddc_DistanceToNext+"</td>"
		str+="<td>"+data[i].rddc_TravelTimeToNext+"</td>"
		str+="<td>"+data[i].rddc_Sequence+"</td>"
		str+="</tr>"
	}
	$("table#tableRoute tbody").append(str);

}
</script>