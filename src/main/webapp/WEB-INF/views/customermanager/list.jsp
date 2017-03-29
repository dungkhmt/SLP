<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!-- Datatable CSS -->
<link href="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"/>" rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="<c:url value="/assets/libs/datatables-responsive/css/dataTables.responsive.css" />" rel="stylesheet">

<!-- DataTables JavaScript -->
<script src="<c:url value="/assets/libs/datatables/media/js/jquery.dataTables.js"/>"></script>
<script src="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.js"/>"></script>


<div id="page-wrapper">
    <div class="row">
    	<div class="col-lg-12 center">
            <h1 class="page-header">Chức năng quản lý khách hàng</h1>
            
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    
    <div class="row">
    	<div class="col-sm-1 col-sm-offset-11">
    		<button class="btn btn-primary btn-add">Thêm</button>
    	</div>
    </div>
    
</div>
<!-- /#page-wrapper -->

<script>
$(document).ready(function(){
	$('.btn-add').click(function(){
		window.location = '${baseUrl}' + "/manage/customermanager/add";
	})
});
</script>