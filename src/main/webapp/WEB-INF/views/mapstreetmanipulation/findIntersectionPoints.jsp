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
function initialize(){
	var mapProp = {
		center : {lat:21.03333, lng: 105.849998},
		zoom : 12,
		mapTypeIDd : google.maps.MapTypeId.ROADMAP
	}
	map = new google.maps.Map(document.getElementById("googleMap"),mapProp);
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
	$.ajax({
		type : 'POST',
		url : baseUrl + '/mapstreetmanipulation/findAndSaveIntersectionPoints',
		data : roadSelected,
		success : function(response){
			if(response == "400"){
				window.location = baseUrl + "/mapstreetmanipulation/findIntersectionPoints"
			}
		}
	});
}
</script>