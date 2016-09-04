<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- DataTables CSS -->
<link href="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"/>" rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="<c:url value="/assets/libs/datatables-responsive/css/dataTables.responsive.css" />" rel="stylesheet">
<link href="<c:url value="/assets/libs/fileinput/css/fileinput.min.css" />" media="all" rel="stylesheet" type="text/css">
<script src="<c:url value="/assets/libs/fileinput/js/fileinput.min.js"/>"></script>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Danh sách hóa đơn đã nhận</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<div class="row">
      	<label class="control-label ">Chọn file cần upload</label>
		<input id="input-file" name="timetable[]" type="file" multiple=true class="file-loading" style="height:60%" />		
	</div>
	<!-- /.row -->
</div>
<script>
$(document).ready(function(){
	 $("#input-file").fileinput({
	 	uploadUrl:  "${baseUrl}/containerdelivery/upload-file-pickupdelivery-orders", // server upload action
		uploadAsync: true,
		allowedFileExtensions:['xlsx'],
		maxFileCount: 1
	  }).on('fileuploaded', function() {
		  window.location=baseUrl+"/containerdelivery/list-pickupdelivery-order";
	 });
});
</script>