<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div id="page-wrapper">
	<div id="googleMap" style="width:100%;height:100%;margin-bottom:10px;"></div>
	
	<div class="row">
		<div class="col-sm-4">
			<div class="form-group">
				<select class="form-control" id="select-listStreet">
					<option>Chọn tên đường</option>
				</select>
			</div>
		</div>
		<div class="col-sm-3">
			<div class="form-group">
				<select class="form-control" id="select-listProvince">
					<option>Chọn Tỉnh/Thành Phố</option>
					<c:forEach items="${lstProvinces}" var="province">
						<option value="${province.PROV_Code}"><c:out value="${province.PROV_Name}"/></option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="col-sm-1">
			<button class="btn btn-primary" id="btn-saveRoad">Lưu</button>
		</div>
	</div>
</div>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAruL-HLFSNh6G2MLhjS-eBTea7r7EFa5A&libraries=places&callback=initialize" async defer></script>
<script>
var map;
var dataResponse;
//var listPoint=[];
var road;
function initialize() {
	//construct google map
	var mapProp = {
		center: {lat: 21.033333, lng: 105.849998},
		zoom: 12,
		mapTypeId: google.maps.MapTypeId.ROADMAP
	};
	map=new google.maps.Map(document.getElementById("googleMap"),mapProp);
	road = new google.maps.Polyline({
		strokeColor: '#FF0000',
	    strokeOpacity: 1.0,
	    strokeWeight: 3
	    
	});
	road.setMap(map);
	map.addListener('click', function(event){
		var markerPoint = new google.maps.Marker({
			map:map,
			position:event.latLng,
			icon:"https://www.google.com/mapfiles/marker_green.png"
		});

		road.getPath().push(event.latLng);
		markerPoint.addListener('click',function(){
			markerPoint.setMap(null);
			var index = road.getPath().indexOf(this.getPosition());
			road.getPath().splice(index,1);
		});
	});
}

$(document).ready(function(){
	$('#select-listProvince').change(function(){
		var province = $(this).val();
		console.log("province code"+province);
		$.ajax({
			type: 'GET',
			url: baseUrl + "/mapstreetmanipulation/getListStreetName/"+province,
			contentType: 'application/json',
			success: function(response){
				console.log("response:"+response);
				dataResponse = response;
				for(var i=0; i<response.length; i++){
					$('#select-listStreet').append($('<option>', {
					    value: response[i].RoadCode,
					    text: response[i].RoadName
					}));
				}
			}
		});
	});
	
	
	$('#select-listStreet').change(function(){
		var street = $(this).val();
		for(var i=0; dataResponse.length; i++){
			if(dataResponse[i].RoadCode==street){
				//roadCodeSelected = dataResponse[i].RoadCode;
				var roadPoints = dataResponse[i].RoadPoints;
				var roadPointLatLngs = roadPoints.split(":");
				for(var j=0; j<roadPointLatLngs.length; j++){
					var point = new google.maps.LatLng(roadPointLatLngs[j]);
					var markerPoint = new google.maps.Marker({
						map:map,
						position:point,
						icon:"https://www.google.com/mapfiles/marker_green.png"
					});

					road.getPath().push(point);
					markerPoint.addListener('click',function(){
						markerPoint.setMap(null);
						var index = road.getPath().indexOf(this.getPosition());
						road.getPath().splice(index,1);
					});
				}
				break;
			}
		}
	});
	$('#btn-saveRoad').click(function(){
		var roadCodeSelected = $('#select-listStreet').val();
		var dataSend = road.getPath().join(":");
		console.log("dataSend when click button: "+dataSend);
		$.ajax({
			type: 'POST',
			url : baseUrl + "/mapstreetmanipulation/updateRoad/" + roadCodeSelected,
			
		})
	});
});
</script>