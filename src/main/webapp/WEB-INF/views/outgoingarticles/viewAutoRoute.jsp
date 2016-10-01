<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- DataTables CSS -->
<link href="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"/>" rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="<c:url value="/assets/libs/datatables-responsive/css/dataTables.responsive.css" />" rel="stylesheet">

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Xem kế hoạch vận chuyển</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-4">
			<div class="form-group">
				<select class="form-control" id="select-listBatch">
					<option>Chọn batch</option>
					<c:forEach items="${lstBatch}" var="batch">
						<option value="${batch.REQBAT_Code}"><c:out value="${batch.REQBAT_Code}"/></option>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>
	<div class="row">
		<div id="googleMap" style="width:100%; height:100%"></div>	
	</div>
	<div class="row">
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="dataTable_wrapper">
					<table class="table table-striped table-bordered table-hover" id="tbl-infoOfRoutes">
						<thead>
							<tr>
								<th>Mã KH</th>
								<th>Địa chỉ KH</th>
								<th>Thời gian giao hàng dự kiến</th>
								<th>Thời gian giao hàng yêu cầu</th>
								<th>Số thứ tự</th>
								<th>Shipper</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAruL-HLFSNh6G2MLhjS-eBTea7r7EFa5A&libraries=places&callback=initialize" async defer></script>
<!-- DataTables JavaScript -->
<script src="<c:url value="/assets/libs/datatables/media/js/jquery.dataTables.js"/>"></script>
<script src="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.js"/>"></script>

<script>
var table;
var colorInit=["#F7786B","#91A8D0","#91A8D0","#034F84","#FAE03C","#98DDDE","#9896A4","#DD4132","#B18F6A","#79C753","#B93A32","#AD5D5D","#006E51","#B76BA3","#5C7148","#D13076"];
$(document).ready(function(){
	table = $("#tbl-infoOfRoutes").DataTable({
		"bSort" : false
	});
	$('#select-listBatch').change(function(){
		var batchSelected = $(this).val();
		$.ajax({
			type: 'POST',
			url: baseUrl + "/outgoingarticles/viewAssignedBatchRoute",
			data: batchSelected,
			contentType: 'application/text',
			success: displayInfo
		});
	});
});

var map;
function initialize() {
	//construct google map
	var mapProp = {
		center: {lat: 21.033333, lng: 105.849998},
		zoom: 12,
		mapTypeId: google.maps.MapTypeId.ROADMAP
	};
	map=new google.maps.Map(document.getElementById("googleMap"),mapProp);
}

function displayInfo(response){
	var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
	console.log("response: "+JSON.stringify(response));
	var storePosition = response[0].storeLatLng;
	console.log("storePostion: "+storePosition);
	var indexPo = storePosition.indexOf(",");
	var storePositionLat = storePosition.substring(0,indexPo);
	console.log("storePositionLat: "+storePositionLat);
	var storePositionLng = storePosition.substring(indexPo+1,storePosition.length);
	console.log("storePositionLng: "+storePositionLng);
	var storePos = new google.maps.LatLng(storePositionLat,storePositionLng);
	var markerStorePostion = new google.maps.Marker({
		position: storePos,
		icon : baseUrl+"/assets/icon/store.svg",
		map: map
	});
	
	for(var i=0;i<response.length; i++){
		//console.log(JSON.stringify(response[i]));		
		var route;
		if(i>=colorInit.length){
			route = new google.maps.Polyline({
				strokeColor: getRandomColor(),
			    strokeOpacity: 1.0,
			    strokeWeight: 3,
			});	
		}else{
			var color = colorInit[(i+2)%colorInit.length];
			route = new google.maps.Polyline({
				strokeColor: color,
			    strokeOpacity: 1.0,
			    strokeWeight: 3,
			});	
		}
		 
		var labelIndex=0;
		route.getPath().push(storePos);
		
		for(var j=0; j<response[i].routeElement.length; j++){
			var lat = response[i].routeElement[j].addLat;
			var lng = response[i].routeElement[j].addLng;
			var point = new google.maps.LatLng(lat,lng);
			var marker = new google.maps.Marker({
				position:point,
				label:labels[labelIndex++ % labels.length],
				map: map
			});
			route.getPath().push(point);
			table.row.add([
				response[i].routeElement[j].clientCode,
				response[i].routeElement[j].clientAddress,
				"",
				response[i].routeElement[j].expectedTime,
				labels[response[i].routeElement[j].routeSequence-1],
				response[i].shipperCode
			]).draw( false );
		}
		route.setMap(map);
	}
}

function getRandomColor() {
    var letters = '0123456789ABCDEF';
    var color = '#FF';
    for (var i = 0; i < 4; i++ ) {
        color += letters[Math.floor(Math.random() * 10)];
    }
    return color;
}
</script>