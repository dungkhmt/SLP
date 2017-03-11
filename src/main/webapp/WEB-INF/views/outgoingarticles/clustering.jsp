<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- DataTables CSS -->
<link href="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"/>" rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="<c:url value="/assets/libs/datatables-responsive/css/dataTables.responsive.css" />" rel="stylesheet">

<!-- Selectize CSS -->
<link href="<c:url value="/assets/libs/selectize/css/selectize.css" />" rel="stylesheet">

<!-- DataTables JavaScript -->
<script src="<c:url value="/assets/libs/datatables/media/js/jquery.dataTables.js"/>"></script>
<script src="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.js"/>"></script>

<!-- Selectize JavaScript -->
<script src="<c:url value="/assets/libs/selectize/js/selectize.min.js"/>"></script>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Danh sách hóa đơn đã nhận</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>	
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Danh các hóa đơn chưa được giao: <b id=batchCodeInfo></b></div>
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover" id="dataTabels-clustering">
							<thead>
								<tr>
									<th>Mã Hóa Đơn</th>
									<th>Mã khách hàng</th>
									<th>Ngày giao</th>
									<th>Thời gian giao</th>
									<th>Địa điểm giao</th>
									<th>Giá</th>
									<th>Lô</th>
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
<style>
	td{
        font-size: 15px;
	}
	
	.hide{
		display: none;
	}
	
</style>


<script>
var table;
var data = ${mOrders};
function saveClustering(index) {
	$.post(window.location.href + "/save",
	        {
				"O_Code": data[index].O_Code,
				"O_BatchCode": $("#selectize_batch"+index).val()
	        },
	        function(){
	        	data[index].O_BatchCode = $("#selectize_batch"+index).val();
	        	$(".selectize_batch" + index).addClass("hide");
	        });
}

function closeClustering(index) {
	$("#selectize_batch" + index)[0].selectize.setValue(data[index].O_BatchCode);
	$(".selectize_batch" + index).addClass("hide");
}

$(document).ready(function(){
	for(var i = 0; i < data.length; ++i) {
		data[i].O_Time = data[i].O_TimeEarly + "-" + data[i].O_TimeLate;
		data[i].clustering = '<div class="selectize_batch'+i+' hide">'
			+'<i class="fa fa-check" aria-hidden="true" style="color:green; cursor:pointer; font-size: 20px" onClick="saveClustering('+i+')"></i>'
			+ '<i class="fa fa-times" style="color:gray; cursor:pointer;font-size: 20px" onClick="closeClustering('+i+')" aria-hidden="true"></i>'
			+'</div>';
		data[i].selectize_BatchCode = '<input style="width:250px" id="selectize_batch'+i+'">';
	}
	
	
	
	$.get(window.location.origin + "/slp/outgoingarticles/parcel/getBatchList", function(batchList) {
		/**var group = [], groupSelectize = [], temp;
		for(var i = 0; i < batchList.length; ++i) {
			temp = batchList[i].reqbat_Code.substr(0,10);
			batchList[i].group = temp;
			if(group.indexOf(temp) == -1) {
				group.push(temp);
				groupSelectize.push({"date": temp});
			}
		}
		console.log(groupSelectize);aza
		console.log(batchList);
		**/
		var $wrapper = $('#page-wrapper');
		table = $('#dataTabels-clustering').DataTable({
		 	data: data,
			columns: [
			    { "data": "O_Code" },
			    { "data": "O_ClientCode" },
			    { "data": "O_DueDate" },
			    { "data": "O_Time" },
			    { "data": "O_DeliveryAddress" },
			    { "data": "O_Price" },
			    { "data": 'selectize_BatchCode' },
			    { "data": "clustering" }
			],
			"columnDefs": [
			               { "width": "18%", "targets": 4 }
			             ],
	       "bSort": false,
	       "fnInfoCallback": function( oSettings, iStart, iEnd, iMax, iTotal, sPre ) {
	    	   var indexData = oSettings.aiDisplay;
		    	   for(var i = iStart - 1; i < iEnd; ++i) {
		   			var $selectize = $("#selectize_batch"+indexData[i]).selectize({
		   				options: batchList,
		   				maxItems: 1,
		   				valueField: 'reqbat_Description',
		   				labelField: 'reqbat_Description',
		   				searchField: ['reqbat_Description']
		   			});
		   			$selectize[0].selectize.setValue(data[indexData[i]].O_BatchCode);
		   		}
		    	   $('select.selectized,input.selectized', $wrapper).each(function(index) {
		    			var local = indexData[index + iStart - 1];
		    		   var update = function(e) {
		    				if(data[local].O_BatchCode != $("#selectize_batch" + local)[0].selectize.items[0]) {
		    					$(".selectize_batch" + local).removeClass("hide");
		    				} else {
		    					
		    					$(".selectize_batch" + local).addClass("hide");
		    				}
		    			};

		    			$(this).on('change', update);
		    		});
	    	  }
	   });
		
	});
});
</script>