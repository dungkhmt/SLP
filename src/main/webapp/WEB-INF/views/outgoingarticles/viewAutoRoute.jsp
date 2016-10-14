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
				<div class="col-sm-2">
					<div class = "form-group">
						<select class="form-control">
						</select>
					</div>
				</div>
				<div class="col-sm-2">
					<button class="btn btn-primary">Assign Shipper</button>
				</div>
				<div class="col-sm-1">
					<button class="btn btn-primary">View Routes</button>
				</div>
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
								<th>Check</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
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
//var colorInit=["#F7786B","#91A8D0","#91A8D0","#034F84","#FAE03C","#98DDDE","#9896A4","#DD4132","#B18F6A","#79C753","#B93A32","#AD5D5D","#006E51","#B76BA3","#5C7148","#D13076"];
var colorInit=["#B0171F","#FF1493","#8B5F65","#000080","#006400","#a52a2a","#ff0000","#ff1493","#9400d3"];
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
	//var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
	console.log("response: "+JSON.stringify(response));
	var storePosition = response[0].storeLatLng;
	//console.log("storePostion: "+storePosition);
	var indexPo = storePosition.indexOf(",");
	var storePositionLat = storePosition.substring(0,indexPo);
	//console.log("storePositionLat: "+storePositionLat);
	var storePositionLng = storePosition.substring(indexPo+1,storePosition.length);
	//console.log("storePositionLng: "+storePositionLng);
	var storePos = new google.maps.LatLng(storePositionLat,storePositionLng);
	var markerStorePostion = new google.maps.Marker({
		position: storePos,
		icon : baseUrl+"/assets/icon/store-icon.png",
		map: map
	});
	var lstinfowindow = [];
	//var rows = "";
	var colorOfRow = ["#F9F9F9","#FFFFFF"];
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
			var lineSymbol = {
				path: google.maps.SymbolPath.FORWARD_CLOSED_ARROW,
				strokeOpacity: 2,
			    scale:1.5		
			};
			route = new google.maps.Polyline({
				strokeColor: color,
			    strokeOpacity: 1.0,
			    strokeWeight: 3,
			    icons: [{icon: lineSymbol,
		            offset: '100%',
		            repeat:'200px'}]
			});	
		}
		 
		var labelIndex=0;
		route.getPath().push(storePos);
		
		var firstRowNode = table.row.add([
			"",
		    "",
		    "",
		    "",
		    "",
		    "",
		    "<input type='checkbox'>"
		]).draw().node();
		var rowColor = colorOfRow[i%2];
		$(firstRowNode).css('background-color',rowColor);
		for(var j=0; j<response[i].routeElement.length; j++){
			var lat = response[i].routeElement[j].addLat;
			var lng = response[i].routeElement[j].addLng;
			var point = new google.maps.LatLng(lat,lng);
			var time = response[i].routeElement[j].expectedTime;
			var timeEarly = time.substring(0,19);
			var timeLate = time.substring(20,time.length);
			var infowindow = new google.maps.InfoWindow({
			    content: "STT giao hàng: "+ response[i].routeElement[j].routeSequence +"</br>"+
			    		"Mã khách hàng: " + response[i].routeElement[j].clientCode +"</br>"+
			    		"Địa chỉ: " + response[i].routeElement[j].clientAddress +"</br>"+
			    		"Thời gian giao hàng sớm nhất: " + timeEarly + "</br>"+
			    		"Thời gian giao hàng muộn nhất: " + timeLate + "</br>"+
			    		"Người giao hàng: "+ response[i].shipperCode
			});
			lstinfowindow.push(infowindow);
			var marker = new google.maps.Marker({
				position:point,
				icon: baseUrl+"/assets/icon/marker_black16.png",
				//label:labels[labelIndex++ % labels.length],
				map: map,
				infowindow: infowindow
			});
			marker.addListener('click', function() {
				for(var t=0; t<lstinfowindow.length; t++){
					lstinfowindow[t].close();
				}
			    this.infowindow.open(map, this);
			});
			route.getPath().push(point);
			var rowNode = table.row.add([
				response[i].routeElement[j].clientCode,
				response[i].routeElement[j].clientAddress,
				"",
				response[i].routeElement[j].expectedTime,
				response[i].routeElement[j].routeSequence,
				response[i].shipperCode,
				"<input type='checkbox'>"
			]).draw().node();
			//var rowColor = colorOfRow[i%2];
			$(rowNode).css('background-color',rowColor);
			/*
			//table.row.add([
			rows += "<tr style='background-color:"+colorOfRow[i%2]+"'>";
			rows += "<td>" + response[i].routeElement[j].clientCode +"</td>"
			rows += "<td>" + response[i].routeElement[j].clientAddress + "</td>";
			rows += "<td></td>"; 
			rows += "<td>"+response[i].routeElement[j].expectedTime+"</td>";
			rows += "<td>"+response[i].routeElement[j].routeSequence+"</td>";
			rows += "<td>"+response[i].shipperCode+"</td>";
			rows += "</tr>";
			//]).draw( false );*/
		}
		route.setMap(map);
	}
	//$("table#tbl-infoOfRoutes tbody").append(rows);
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