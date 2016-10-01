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
	<div id="map" style="height:100%">
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
									<th>DeliveryAddress</th>
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
var colorInit=["#F7786B","#91A8D0","#91A8D0","#034F84","#FAE03C","#98DDDE","#9896A4","#DD4132","#B18F6A","#79C753","#B93A32","#AD5D5D","#006E51","#B76BA3","#5C7148","#D13076"];
var table;
function initMap() {
	directionsService = new google.maps.DirectionsService;
	serviceDistance = new google.maps.DistanceMatrixService;
    var mapDiv = document.getElementById('map');
    map = new google.maps.Map(mapDiv, {
        center: {lat: 21.03, lng: 105.8},
        zoom: 8
    });
	console.log("start");
}
$(document).ready(function(){
	table=$("#tableRoute").DataTable({
		
	});
	console.log(table);
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
	        viewMap(response);
	    }
    });
	
}
function randomColor(){
/*	p1=Math.floor((Math.random() * 85));
	p2=Math.floor((Math.random() * 255));
	p3=Math.floor((Math.random() * 255));
	return "rgb("+p1+","+p2+","+p3+ ")";*/
	return colorInit[Math.floor((Math.random() * colorInit.length))];
}
function viewMap(data){
	var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
	var route;
	var labelIndex=0;
	var xd=false;
	for(var i=0;i<data.length;i++){
		//console.log(i);
		//console.log(JSON.stringify(response[i]));
		
		if(data[i].rddc_Sequence==0){
		if(i<data.length-1)
		if(data[i+1].rddc_Sequence==1)
			xd=true;
		else xd=false
		if(xd==true){
		var point = new google.maps.LatLng(21.2187149,105.80417090000003);
		//if(route!=undefined)
		//route.getPath().push(point);
		route = new google.maps.Polyline({
			strokeColor: randomColor(),
		    strokeOpacity: 1.0,
		    strokeWeight: 3,
		});
		}
		labelIndex=0;
		}
		
		var latlng=data[i].rddc_LatLng;
		var lat = latlng.substring(0,latlng.indexOf(',')) ;
		var lng = latlng.substring(latlng.indexOf(',')+1,latlng.length) ;
		var point = new google.maps.LatLng(lat,lng);
		var marker = new google.maps.Marker({
				position:point,
				label:labels[labelIndex % labels.length],
				map: map
		});
		if(xd==true){
		route.getPath().push(point);
		route.setMap(map);
		labelIndex++;
		}
	}
}
function loadTable(data){
	console.log(data);
	$("table#tableRoute tbody").html("");
	gray="#F0F0F0";
	white="#FFFFFF";
	var color=["#F0F0F0","#FFFFFF"];
	var idcolor=0;
	str=null;
	for(var i=0;i<data.length;i++){
		console.log("i data[i].rddc_Group" +data[i].rddc_Group);
		if(i>0)
		if(data[i].rddc_Group!=data[i-1].rddc_Group){ 
			idcolor=idcolor+1;
			idcolor=idcolor % color.length;
			console.log("id"+idcolor);
			console.log("length "+ color.length+" "+idcolor % color.length);
		}
		str+="<tr"+" style='background-color:"+color[idcolor]+"' "+">";
		str+="<td>"+data[i].rddc_TicketCode+"</td>"
		str+="<td>"+data[i].rddc_Address+"</td>"
		str+="<td>"+data[i].rddc_DeliveryAddress+"</td>"
		str+="<td>"+data[i].rddc_PickupDateTime+"</td>"
		str+="<td>"+data[i].rddc_DistanceToNext+"</td>"
		str+="<td>"+data[i].rddc_TravelTimeToNext+"</td>"
		str+="<td>"+data[i].rddc_Sequence+"</td>"
		str+="</tr>"
	}
	$("table#tableRoute tbody").append(str);

}
</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDEXgYFE4flSYrNfeA7VKljWB_IhrDwxL4&callback=initMap">
</script>