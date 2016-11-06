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
			<h1 class="page-header">Tìm điểm giao cắt</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div id="googleMap" style="width:100%; height:100%; margin-bottom:10px;"></div>
	<div class="row">
		<div class="col-sm-offset-11 col-sm-1">
			<button class="btn btn-primary" style="margin-bottom:10px;" onclick="findIntersections()">Tìm</button>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body">
			<div class="dataTable_wrapper">
				<table class="table table-striped table-bordered table-hove" id="tbl-listStreeets">
					<thead>
						<tr>
							<th>Tên đường</th>
							<th>Loại đường</th>
							<th>Tốc độ tối đa</th>
							<th><input type="checkbox" onClick="checkAll(this)" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listRoads}" var="road">
							<tr>
								<td><c:out value="${road.roadName}"/></td>
								<td><c:out value="${road.roadTypeCode }"/></td>
								<td><c:out value="${road.roadMaxSpeed }"/></td>
								<td><input type="checkbox" name="checkStreet" onchange="addStreets('${road.roadCode}',this)"></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAruL-HLFSNh6G2MLhjS-eBTea7r7EFa5A&libraries=places&callback=initialize" async defer></script>
<script>
var map;
var roadSelected = [];
var roads = ${jsonRoads};
var segments = ${jsonRoadSegments};
var points = ${jsonRoadPoints};

function initialize(){
	var mapProp = {
		center : {lat:21.03333, lng: 105.849998},
		zoom : 12,
		mapTypeIDd : google.maps.MapTypeId.ROADMAP
	}
	map = new google.maps.Map(document.getElementById("googleMap"),mapProp);
	var checkIntersectPoint = [];
	for(var i=0; i<points.length; i++){
		checkIntersectPoint[i] = 0;
	}
	var listMarker = [];
	
	for(var i=0; i<segments.length; i++){
		var fromPointCode = segments[i].RSEG_FromPoint;
		var toPointCode = segments[i].RSEG_ToPoint;
		var fromPointLatLng = null;
		var toPointLatLng = null;
		var indexFromPoint=-1;
		var indexToPoint=-1;
		for(var j=0; j<points.length; j++){
			if(fromPointCode == points[j].RP_Code){
				fromPointLatLng = points[j].RP_LatLng;
				checkIntersectPoint[j]++;
				indexFromPoint = j;
			}
			if(toPointCode == points[j].RP_Code){
				toPointLatLng = points[j].RP_LatLng;
				checkIntersectPoint[j]++;
				indexToPoint = j;
			}
			if(fromPointLatLng != null && toPointLatLng != null){
				break;
			}
		}
		var indexCutFromPoint = fromPointLatLng.indexOf(',');
		var indexCutToPoint = toPointLatLng.indexOf(',');
		var fromPointLat = fromPointLatLng.substring(0,indexCutFromPoint);
		var fromPointLng = fromPointLatLng.substring(indexCutFromPoint+1,fromPointLatLng.length);
		var toPointLat = toPointLatLng.substring(0,indexCutToPoint);
		var toPointLng = toPointLatLng.substring(indexCutToPoint+1,toPointLatLng.length);
		
		var polyLine = new google.maps.Polyline({
			strokeColor: '#FF0000',
			strokeOpacity : 1.0,
			strokeWeight :3,
			path : [new google.maps.LatLng(fromPointLat,fromPointLng), new google.maps.LatLng(toPointLat,toPointLng)]
		})
		polyLine.setMap(map);
		
		/*
		 * check intersect point to set color to marker of point	
		*/
		if(listMarker[indexFromPoint] && listMarker[indexFromPoint].setMap){
			listMarker[indexFromPoint].setMap(null);
		}
		if(listMarker[indexToPoint] && listMarker[indexToPoint].setMap){
			listMarker[indexToPoint].setMap(null);
		}
		
		if(checkIntersectPoint[indexFromPoint] >=3 ){
			listMarker[indexFromPoint] = new google.maps.Marker({
				map : map,
				position : new google.maps.LatLng(fromPointLat,fromPointLng),
				icon : baseUrl + "/assets/icon/oval_green.png"
			});
		}else{
			listMarker[indexFromPoint] = new google.maps.Marker({
				map : map,
				position : new google.maps.LatLng(fromPointLat,fromPointLng),
				icon : baseUrl + "/assets/icon/oval_blue.png"
			});
		}
		
		if(checkIntersectPoint[indexToPoint] >=3 ){
			listMarker[indexToPoint] = new google.maps.Marker({
				map : map,
				position : new google.maps.LatLng(toPointLat,toPointLat),
				icon : baseUrl + "/assets/icon/oval_green.png"
			});
		}else{
			listMarker[indexToPoint] = new google.maps.Marker({
				map : map,
				position : new google.maps.LatLng(toPointLat,toPointLng),
				icon : baseUrl + "/assets/icon/oval_blue.png"
			});
		}
	}
}

function addStreets(roadCode,elem){
	var indexRoad = roadSelected.indexOf(roadCode);
	if(elem.checked){
		if(indexRoad == -1){
			roadSelected.push(roadCode);
		}
	}else{
		if(indexRoad != -1){
			roadSelected.splice(indexRoad,1);
		}
	}
}

function checkAll(elem){
	var checkBoxes = document.getElementsByName('checkStreet');
	if(elem.checked){
		for(var i=0; i<roads.length; i++){
			if(roadSelected.indexOf(roads[i].RoadCode) == -1){
				roadSelected.push(roads[i].RoadCode);
			}
		}
	}else{
		for(var i=0; i<roads.length; i++){
			var test = roadSelected.indexOf(roads[i].RoadCode);
			if( test != -1){
				roadSelected.splice(test,1);
			}	
		}
	}
	
	for(var i=0; i<checkBoxes.length; i++){
		checkBoxes[i].checked = elem.checked;
	}
}

function findIntersections(){
	dataSend = roadSelected.join(";");
	$.ajax({
		type : 'POST',
		url : baseUrl + '/mapstreetmanipulation/findAndSaveIntersectionPoints',
		data : dataSend,
		contentType : 'application/text',
		success: function(response){
			alert("ok");
			window.location = baseUrl + "/mapstreetmanipulation/findIntersectionPoints";
		}
	});
}
</script>