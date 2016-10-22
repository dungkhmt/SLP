<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Tạo tuyến đường</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div id="googleMap" style="width:100%;height:100%"></div>
		<div class="panel panel-default">
			
			<div class="panel-body">
				<div class="form-group">
						<label class="control-label col-lg-2 ">Tên đường</label>
						<div class="col-lg-4">
							<input  id="nameroad" class="form-control"  placeholder="Tên đường"></input>
						</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 phoneinput">Kiểu đường</label>
					<div class="col-lg-4">
					<select class="form-control" id="sel-roadtype" >
						<c:forEach items="${listRoadType}" var="roadTypes">
							<option value="${roadTypes.roadTypeCode}">${roadTypes.roadTypeName}</option>
						</c:forEach>
					</select>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 phoneinput">Tỉnh</label>
					<div class="col-lg-4">
					<select class="form-control" id="sel-provice">
						<c:forEach items="${listProvince}" var="provices">
							<option value="${provices.PROV_Code}">${provices.PROV_Name}</option>
						</c:forEach>
					</select>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 ">Hai chiều/Một chiều</label>
					<div class="col-lg-4">
					<select class="form-control" id="sel-option">
						<option value="1">Một chiều</option>
						<option value="2">Hai chiều</option>
					</select>
					</div>
				</div>
				<div class="form-group">
						<label class="control-label col-lg-2 ">Vận tốc</label>
						<div class="col-lg-4">
							<input  id="maxspeed" class="form-control"  placeholder="Max Speed"></input>
						</div>
				</div>
				<div class="form-group">
						<label class="control-label col-lg-2 ">Các tỉnh đi qua</label>
						<div class="col-lg-4">
							<input  id="provicespass" class="form-control"  placeholder="Các tỉnh đi qua"></input>
						</div>
				</div>
				<button type="button" class="btn btn-primary active" onclick="saveRoad()" >Save</button>
			</div>
		</div>
	</div>
	
</div>
<!-- /#page-wrapper -->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAruL-HLFSNh6G2MLhjS-eBTea7r7EFa5A&libraries=places&callback=initialize" async defer></script>
<script>
var map;

var listMarker=[];
function initialize() {
	//construct google map
	var mapProp = {
		center: {lat: 21.033333, lng: 105.849998},
		zoom: 12,
		mapTypeId: google.maps.MapTypeId.ROADMAP
	};
	map=new google.maps.Map(document.getElementById("googleMap"),mapProp);
	map.addListener('click', function(event){
		console.log(event.latLng.lat());
		listMarker.push(new google.maps.Marker({
			map:map,
			position:event.latLng,
			icon:"https://www.google.com/mapfiles/marker_green.png"
			}));
		console.log(listMarker[0].getPosition());
	});
}

function saveRoad(){
	data={
		"nameStreet": $('#nameroad').val(),
		"roadType":$('#sel-roadtype').val(),
		"provice":$('#sel-provice').val(),
		"optionRoad":$('#sel-option').val(),
		"maxSpeed":$('#maxspeed').val(),
		"ProvicesPass":$('#provicespass').val()
	};
	data["listPoint"]=[];
	for(var i=0;i<listMarker.length;i++){
		data.listPoint.push({
			"lat":listMarker[i].getPosition().lat() ,
			"lng":listMarker[i].getPosition().lng()
		})
	}
	console.log(data);
	$.ajax({
		type: 'POST',
		url: baseUrl+'/mapstreetmanipulation/save-A-Road',
		data: data,
		contentType: 'application/json',
		success: function(response){
			console.log("success--"+response);
			if(response=="400"){
				window.location = baseUrl + '/';
			}
			if(response=="404"){
				alert("Error");
			}
		},
		error: function(response){
			console.log("error--"+JSON.stringify(response));
		}
	});
}
</script>
