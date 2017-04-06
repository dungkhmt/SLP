<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">TSPD</h1>
		</div>
	</div>
	
	<div class="row">
		<div id="googleMap" style="width:100%;height:100%"></div>
		<form:form action="${baseUrl}/tsp-drone/tspd-solve" method="POST" commandName="tspd" role="form" class="form-horizontal">
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="row">
					<div class="form-group">
						<label class="control-label col-lg-3">Truck speed</label>
						<div class="col-lg-3">
							<form:input path="truckSpeed" name="truckSpeed" id="truckSpeed" class="form-control" placeholder="Truck speed"/>
						</div>
						<label class="control-label col-lg-3">Drone speed</label>
						<div class="col-lg-3">
							<form:input path="droneSpeed" name="droneSpeed" id="droneSpeed" class="form-control" placeholder="Drone speed"/>
						</div>
					</div>
				</div>
				<div class="row" style="margin-top:10px">
					<div class="form-group">
						<label class="control-label col-lg-3">Cost per unit of truck</label>
						<div class="col-lg-3">
							<form:input path="truckCost" name="truckCost" id="truckCost" class="form-control" placeholder="Cost per unit of truck"/>
						</div>
						<label class="control-label col-lg-3">Cost per unit of drone</label>
						<div class="col-lg-3">
							<form:input path="droneCost" name="droneCost" id="droneCost" class="form-control" placeholder="Cost per unit of drone"/>
						</div>
					</div>
				</div>
				<div class="row" style="margin-top:10px">
					<div class="form-group">
						<label class="control-label col-lg-3">Wait time (Delta)</label>
						<div class="col-lg-3">
							<form:input path="delta" name="delta" id="delta" class="form-control" placeholder="Wait time" />
						</div>
						<label class="control-label col-lg-3">Drone endurance (e)</label>
						<div class="col-lg-3">
							<form:input path="endurance" name="endurance" id="endurance" class="form-control" placeholder="endurance"/>
						</div>
					</div>
				</div>
				<div class="row" style="margin-top:10px">
					<form:input path="listPoints" name="listPoints" id="listPoints" type="hidden"/>
					<button class="btn btn-primary col-lg-offset-5 btn-solve" type="submit" name="action" value="tspd-ls">TSPD-LS</button>
					<button class="btn btn-primary btn-solve" type="submit" name="action" value="grasp">GRASP</button>
				</div>
			</div>		
		</div>
		</form:form>
	</div>
</div>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAruL-HLFSNh6G2MLhjS-eBTea7r7EFa5A&libraries=places&callback=initialize" async defer></script>
<script>
var map;
var listMarker = [];
function initialize(){
	var mapProp = {
		center: {lat: 21.033333, lng: 105.849998},
		zoom: 12,
		mapTypeId: google.maps.MapTypeId.ROADMAP
	};
	
	map=new google.maps.Map(document.getElementById("googleMap"),mapProp);
	
	map.addListener('click',function(event){
		var pos = event.latLng;
	
		var markerPoint = new google.maps.Marker({
			map: map,
			position: pos,
			draggable: true,
			icon: "https://www.google.com/mapfiles/marker_green.png"
		});
		
		listMarker.push(markerPoint);
		
		markerPoint.addListener('click',function(){
			this.setMap(null);
			var indexMarker = listMarker.indexOf(this);
			listMarker.splice(indexMarker,1);
		});
	});
}
$(document).ready(function(){
	$('.btn-solve').click(function(){
		var data = [];
		for(var i=0; i<listMarker.length; i++){
			data.push({
				"ID" : i,
				"lat" : listMarker[i].getPosition().lat(),
				"lng" : listMarker[i].getPosition().lng()
			});
		}
		$('#listPoints').val(JSON.stringify(data));
	});	
});

/*
function tspd_solve(){
	//console.log("-----------")
	var data = {
		"truckSpeed" : $('#truckSpeed').val(),
		"droneSpeed" : $('#droneSpeed').val(),
		"truckCost" : $('#truckCost').val(),
		"droneCost" : $('#droneCost').val(),
		"delta" : $('#delta').val(),
		"endurance" : $('#endurance').val()
	}
	data["listPoints"] = [];
	for(var i=0; i<listMarker.length; i++){
		data.listPoints.push({
			"ID" : i,
			"lat" : listMarker[i].getPosition().lat(),
			"lng" : listMarker[i].getPosition().lng()
		});
	}
	console.log("data send: "+JSON.stringify(data));
	$.ajax({
		type : 'POST',
		url : baseUrl + '/tsp-drone/tspd-solve',
		data : JSON.stringify(data),
		contentType : 'application/json',
	});
}*/


</script>