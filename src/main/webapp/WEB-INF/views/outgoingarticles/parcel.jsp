<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- DataTables CSS -->
<link href="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"/>" rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="<c:url value="/assets/libs/datatables-responsive/css/dataTables.responsive.css" />" rel="stylesheet">

<!-- DataTables JavaScript -->
<script src="<c:url value="/assets/libs/datatables/media/js/jquery.dataTables.min.js"/>"></script>
<script src="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.js"/>"></script>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Danh sách các lô hàng</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<button type="button" class="btn btn-primary pull-right" data-toggle="modal" data-target="#addNewBatch">Thêm</button>
	<div class="row">
		<!-- Modal -->
	  <div class="modal fade" id="addNewBatch" role="dialog">
	    <div class="modal-dialog">
	    
	      <!-- Modal content-->
	      <div style="top:180px" class="modal-content">
	        <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	          <h4 class="modal-title">Tạo mới lô hàng</h4>
	        </div>
	        <div class="modal-body">
	          <form>
			    <div class="form-group">
			      <label>Description:</label>
			      <input type="text" class="form-control" id="description">
			    </div>
		    	</form>
	        </div>
	        <div class="modal-footer">
	        	<button type="button" class="btn btn-success" onclick="save()">Save</button>
	         	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        </div>
	      </div>
	      
	    </div>
	  </div>
	  
	  <!-- Modal -->
	  <div class="modal fade" id="changeBatch" role="dialog">
	    <div class="modal-dialog">
	    
	      <!-- Modal content-->
	      <div style="top:180px" class="modal-content">
	        <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	          <h4 class="modal-title">Sửa lô hàng</h4>
	        </div>
	        <div class="modal-body">
	          <form>
			    <div class="form-group">
			      <label>Description:</label>
			      <input type="text" class="form-control" id="oldDescription">
			    </div>
		    	</form>
	        </div>
	        <div class="modal-footer">
	        	<button type="button" class="btn btn-success" onclick="update()">Update</button>
	         	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        </div>
	      </div>
	      
	    </div>
	  </div>
	  
	  
		<div class="col-lg-12">
		
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover" id="dataTabels-outarticles">
							<thead>
								<tr>
									<th>ID</th>
									<th>Code</th>
									<th>Description</th>
									<th>CustomerCode</th>
									<th></th>
								</tr>
							</thead>
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
var table;
var listBatch;
var sessionIndex;

function save() {
	$.post(window.location.href + "/save-batch",
        {
			"description": $("#description").val()
        },
        function(newBatch,status){
        	newBatch.change = '<span style = "color: green; margin:10px; cursor:pointer" class="glyphicon glyphicon-pencil" onclick="editBatch(' + listBatch.length + ')" data-toggle="modal" data-target="#changeBatch"></span>'
			+	'<span style = "color: red; cursor:pointer" class="glyphicon glyphicon-trash" onclick="deleteBatch(' + listBatch.length + ')"></span>';
			listBatch.push(newBatch);
			table.row.add(newBatch).draw();
			$('#addNewBatch').modal('hide');
			document.getElementById("description").value = "";
        });
}

function update() {
	$.post(window.location.href + "/edit/" + listBatch[sessionIndex].reqbat_ID,
	        {
				"description": $("#oldDescription").val()
	        },
	        function(newBatch,status){
	        	listBatch[sessionIndex].reqbat_Description = $("#oldDescription").val();
	        	var temp = table.row(sessionIndex).data();
	        	temp.reqbat_Description = $("#oldDescription").val();
	        	table.row(sessionIndex).data(temp).draw();
				$('#changeBatch').modal('hide');
	        });
}	


function editBatch(index) {
	sessionIndex = index;
	document.getElementById("oldDescription").value = listBatch[index].reqbat_Description;
};

function deleteBatch(index) {
	var r = confirm("Are you sure to delete \'" + listBatch[index].reqbat_Code + '\'');
	if (r == true) {
		$.post(window.location.href + "/delete?id=" + listBatch[index].reqbat_ID, {},
	        function(data,status){
				table.row(index).remove().draw();
				listBatch.splice(index, 1);
        });
	}
};

$(document).ready(function(){
	$.get(window.location.href + "/getBatchList", function(data) {
		for(var i = 0; i < data.length; ++i) {
			data[i].change = '<span style = "color: green; margin:10px; cursor:pointer" class="glyphicon glyphicon-pencil" onclick="editBatch(' + i + ')" data-toggle="modal" data-target="#changeBatch"></span>'
						+	'<span style = "color: red; cursor:pointer" class="glyphicon glyphicon-trash" onclick="deleteBatch(' + i + ')"></span>';
		}
		if(data == "") {
			listBatch = [];
		} else {
			listBatch = data;
		}
		table = $('#dataTabels-outarticles').DataTable({
			 data: data,
	        columns: [
	            { "data": "reqbat_ID" },
	            { "data": "reqbat_Code" },
	            { "data": "reqbat_Description" },
	            { "data": "reqbat_CustomerCode" },
	            { "data": "change"}
	        ],
	        "bSort": false
	    });
	});
});
</script>