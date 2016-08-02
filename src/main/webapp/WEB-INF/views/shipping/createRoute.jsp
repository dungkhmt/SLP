<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="page-wrapper">
	<div id="googleMap" style="width:100%;height:80%"></div>	
	
	<div class="row" style="margin-top:10px;">
		<div class="col-sm-2">
			<div class="form-group">
				<select class="form-control" id="lstShippers">
					<c:forEach items="${listShippers}" var="shipper" varStatus="id">
						<option value="${id.index}">${shipper.SHP_Code}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<!-- /.col-sm-2 -->
		<div class="col-sm-1">
			<button class="btn btn-warning" id="btnRemove" onclick="btnRemove_cf();">Xóa điểm</button>
		</div>
		<!-- /.col-sm-1 -->
		<div class="col-sm-1" id="divBtnCancel">
			
		</div>
		<!-- /.col-sm-1 -->
		<div class="table-reponsive">
			<table class="table table-striped table-bordered table-hover" id="tblRouteOfShippers">
				<thead>
					<tr>
						<th>Người giao hàng</th>
						<th>Các địa điểm</th>
						<th>Tổng quãng đường</th>
						<th>Tổng thời gian</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listShippers}" var="routeOfshipper" varStatus="count">
						<tr>
							<td><c:out value="${routeOfshipper.SHP_Code}"/></td>
							<td id="route${count.index}"></td>
							<td id="distance${count.index}"></td>
							<td id="time${count.index}"></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<!-- /.table-reponsive -->
		<button class="btn btn-primary">Lưu</button>
		<button class="btn btn-primary">Hủy</button>
	</div>
	<!-- /.row -->
</div>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAruL-HLFSNh6G2MLhjS-eBTea7r7EFa5A&libraries=places&callback=initialize" async defer></script>

<script>
var lstOrders;
var marker = [];
var routePath=[];//route to save lat and lng of each shipper
var map;
var nShippers = ${nShippers};
var routeOfShipper = [[]];//route to display table of each shipper

function initialize() {
	//construct google map
	var mapProp = {
		center: {lat: 21.033333, lng: 105.849998},
		zoom: 13,
		mapTypeId: google.maps.MapTypeId.ROADMAP
	};
	map=new google.maps.Map(document.getElementById("googleMap"),mapProp);
	
	//construct routePath and routeOfShipper
	for(var i=0; i<nShippers; i++){
		routePath[i] = new google.maps.Polyline({
		    strokeColor: getRandomColor(),
		    strokeOpacity: 1.0,
		    strokeWeight: 3,
		});
		routePath[i].setMap(map);	
		routeOfShipper[i] = [];
	}
	
	lstOrders = ${listOrdersJson};
	
	//add listener for each marker
	for(var i=0; i<lstOrders.length; i++){
		var location = new google.maps.LatLng(lstOrders[i].O_DeliveryLat,lstOrders[i].O_DeliveryLng);
		
		marker[i] = new google.maps.Marker({position:location});
		marker[i].setMap(map);
		marker[i].addListener('dblclick',changeRoute);
		marker[i].addListener('click',openInfowindow);
	}
}

//add and remove one point of route for each shipper
function changeRoute(event){
	var indexOfPath = parseInt($('select#lstShippers').find(":selected").val());
	var indexOfMarker = marker.indexOf(this);
	var path = routePath[indexOfPath].getPath();
	var indexOfRemove = path.indexOf(event.latLng);
	if($('#btnRemove').is((":disabled"))){
		if(indexOfRemove >= 0){
			path.removeAt(indexOfRemove);
			for(var i=0; i<routeOfShipper[indexOfPath].length; i++){
				if(routeOfShipper[indexOfPath][i]==indexOfMarker){
					routeOfShipper[indexOfPath].splice(i,1);
					i--;
				}
			}
			
		}
	}else{
		if(indexOfRemove < 0){
			path.push(event.latLng);	
			routeOfShipper[indexOfPath].push(indexOfMarker);
		}
	}
	caculateTimeAndDistance(indexOfPath);
	$('#route'+indexOfPath).html(routeOfShipper[indexOfPath]);
}

function caculateTimeAndDistance(indexOfShipper){
	//alert(marker[routeOfShipper[indexOfShipper][0]].getPosition());
	if(routeOfShipper[indexOfShipper].length>1){
		var distance = 0;
		var time = 0;
		var service = new google.maps.DistanceMatrixService; 
		for(var i=0; i<routeOfShipper[indexOfShipper].length; i++){
			service.getDistanceMatrix({
				origins: [marker[routeOfShipper[indexOfShipper][0]].getPosition()],
			    destinations: [marker[routeOfShipper[indexOfShipper][1]].getPosition()],
			    travelMode: google.maps.TravelMode.DRIVING,
			    unitSystem: google.maps.UnitSystem.METRIC,
		        avoidHighways: false,
		        avoidTolls: false
			},function(response,status){
				//alert(JSON.stringify(response));
				distance += response.rows[0].elements[0].distance.value;
				time += response.rows[0].elements[0].duration.value;
				$('#distance'+indexOfShipper).html(distance+"m");
				$('#time'+indexOfShipper).html(time+"s");
			});
		}
	}else{
		$('#distance'+indexOfShipper).html("0m");
		$('#time'+indexOfShipper).html("0s");
	}
}

function openInfowindow(){
	var indexOfMarker = marker.indexOf(this);
	var infowindow = new google.maps.InfoWindow({
		content:"Date: "+lstOrders[indexOfMarker].O_DueDate+"<br>Time: "+lstOrders[indexOfMarker].O_TimeLate,
	});
	infowindow.open(map,this);
}

function getRandomColor() {
    var letters = '0123456789ABCDEF';
    var color = '#';
    for (var i = 0; i < 6; i++ ) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}

function btnRemove_cf(){
	$('#divBtnCancel').html('<button class="btn btn-primary" id="btnCancel" onclick="btnCancel_cf();">Hủy</button>');
	$('#btnRemove').prop('disabled',true);
}

function btnCancel_cf(){
	$('#btnRemove').prop('disabled',false);
	$("#btnCancel").remove();
}
</script>