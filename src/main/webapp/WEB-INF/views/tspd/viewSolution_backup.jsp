<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">TSPD Solution</h1>
		</div>
	</div>
	<div class="row"> 
		<button class="btn btn-primary" onclick="view_tspdls_solution();">TSPD-LS</button>
		<button class="btn btn-primary" onclick="view_grasp_solution();">GRASP</button>
	</div>
	<div class="row">
		<div id="map" style="height:100%"></div>
	</div>
</div>

<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDEXgYFE4flSYrNfeA7VKljWB_IhrDwxL4&callback=initMap&sensor=true&libraries=geometry"></script>
<script>
var map;
var dataResponse = JSON.parse('${sol}');
var tours=dataResponse.tours
var makerDrone;
var makerTruck;
var directionsService ;
console.log("data response: "+JSON.stringify(dataResponse));
/* tính khoảng cách giữa 2 điểm địa lý */


function initMap(){
	map = new google.maps.Map(document.getElementById('map'),{
		center: {lat:21.03, lng:105.8},
		zoom: 12
	});
	directionsService = new google.maps.DirectionsService();
	google.maps.LatLng.prototype.distanceFrom = function(newLatLng) {
		var EarthRadiusMeters = 6378137.0; // meters
		var lat1 = this.lat();
		var lon1 = this.lng();
		var lat2 = newLatLng.lat();
		var lon2 = newLatLng.lng();
		var dLat = (lat2-lat1) * Math.PI / 180;
		var dLon = (lon2-lon1) * Math.PI / 180;
		var a = Math.sin(dLat/2) * Math.sin(dLat/2) +
		Math.cos(lat1 * Math.PI / 180 ) * Math.cos(lat2 * Math.PI / 180 ) *
		Math.sin(dLon/2) * Math.sin(dLon/2);
		var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		var d = EarthRadiusMeters * c;
		return d;
	}


	/* tinh độ dài đường đi giữ điểm địa lý */
	google.maps.Polygon.prototype.Distance = function(){
		var distance =0;
		for(var i=1; i< this.getPath().getLength(); i++){
			distance += this.getPath().getAt(i).distanceFrom(this.getPath().getAt(i-1));
		}
		return distance;
	}


	/* hàm tính toán tọa độ địa lý */
	google.maps.Polygon.prototype.GetPointAtDistance = function(metres) {
		
	    if (metres == 0) return this.getPath().getAt(0);  
	    if (metres < 0) return null;
	    if (this.getPath().getLength() < 2) return null;
	    var dist=0;
	    var olddist=0;
	    for (var i=1; (i < this.getPath().getLength() && dist < metres); i++) {
			olddist = dist;
			dist += this.getPath().getAt(i).distanceFrom(this.getPath().getAt(i-1));
		}
		if (dist < metres) {
			return null;
		}
		var p1= this.getPath().getAt(i-2);
		var p2= this.getPath().getAt(i-1);
		var m = (metres-olddist)/(dist-olddist);
		return new google.maps.LatLng( p1.lat() + (p2.lat()-p1.lat())*m, p1.lng() + (p2.lng()-p1.lng())*m);
	}

	/* Prototype của các hàm */
	google.maps.Polyline.prototype.Distance             = google.maps.Polygon.prototype.Distance;
	google.maps.Polyline.prototype.GetPointAtDistance   = google.maps.Polygon.prototype.GetPointAtDistance;
}

function view_tspdls_solution(){
	initMap();
	var tour_tspdls = tours[0];
	view_tour(tour_tspdls);
}

function view_grasp_solution(){
	initMap();
	var tour_grasp = tours[1];
	view_tour(tour_grasp);
}
var xdRendezvous=false;
var truckTour;
var droneDeliveries ;
function view_tour(data){
	truckTour = data.td.truck_tour;
	droneDeliveries = data.dd;
	console.log(data)
	for(var j=0;j<truckTour.length;j++){
		truckTour[j].obLauch_node=null;
		truckTour[j].obRendezvous_node=null;
	}
	var lineSymbol = {path: google.maps.SymbolPath.FORWARD_CLOSED_ARROW};
	for(var i=0;i<droneDeliveries.length;i++){
		for(var j=0;j<truckTour.length;j++){
			if(truckTour[j].id==droneDeliveries[i].lauch_node.id){
				truckTour[j].obLauch_node=droneDeliveries[i];
			}
			if(truckTour[j].id==droneDeliveries[i].rendezvous_node.id){
				truckTour[j].obRendezvous_node=droneDeliveries[i];
			}
		}
			
	}
	markerDrone = new google.maps.Marker({
		icon : baseUrl+"/assets/icon/drone-icon.png",
		position : null,
		speed: dataResponse.droneSpeed,
		isDrone: true,
		isRunning: -1
	});
	markerTruck = new google.maps.Marker({
		icon : "https://maps.gstatic.com/mapfiles/ms2/micons/truck.png",
		position : null,
		speed: dataResponse.truckSpeed,
		isDrone: false
	});
	

	for(var i=0;i<droneDeliveries.length;i++){
		marker_pi = new google.maps.Marker({
			map: map,
			icon : "https://www.google.com/mapfiles/marker_yellow.png",
			position : new google.maps.LatLng(droneDeliveries[i].drone_node.lat,droneDeliveries[i].drone_node.lat)
		})
		console.log(i);
	}
	animation(0);
	
}
function animation(i){
		console.log("in while");
		runTruck(new google.maps.LatLng(truckTour[0].lat,truckTour[i].lng),new google.maps.LatLng(truckTour[truckTour.length-1].lat,truckTour[truckTour.length-1].lng));
		

}
function runTruck(start,end){
	console.log("run Truck");
	set(start,end,markerTruck,false,false,true);
	
}
var xdTruck=false;
var xdDroneNode=false;
function distance2point(lat1,lon1 ,lat2,lon2){
	var EarthRadiusMeters = 6378137.0; // meters
	var dLat = (lat2-lat1) * Math.PI / 180;
	var dLon = (lon2-lon1) * Math.PI / 180;
	var a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	Math.cos(lat1 * Math.PI / 180 ) * Math.cos(lat2 * Math.PI / 180 ) *
	Math.sin(dLon/2) * Math.sin(dLon/2);
	var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	var d = EarthRadiusMeters * c;
	return d;
}
function checkWayPoint(lat,lng,speed){
	for(var i=0;i<truckTour.length;i++)
		if(distance2point(lat, lng, truckTour[i].lat, truckTour[i].lng) <speed) return i
	return -1;
}
var laughed;
function runDrone(lauch,drone,rendezvous,c){
	// make poliline
	console.log("rundrone");
	markerDrone.isRunning=c;
	markerDrone.setMap(map);
	polyline = new google.maps.Polyline({
					path: [],
					strokeColor: '#FF0000'
	});
	polyline.getPath().push(new google.maps.LatLng(lauch.lat,lauch.lng));
	polyline.getPath().push(new google.maps.LatLng(drone.lat,drone.lng));
	polyline.getPath().push(new google.maps.LatLng(rendezvous.lat,rendezvous.lng));
	polyline.setMap(map);
	startAnimation(markerDrone, polyline, new google.maps.LatLng(rendezvous.lat,rendezvous.lng))

}
//var geocoder = new google.maps.Geocoder();

/* đánh dấu marker trên bản đồ */
function setMarkersOnAll(map){
	for(var i=0; i<markers.length; i++){
		markers[i].setMap(map);
	}
}

function clearMarkers(){
	setMarkersOnAll(null);
}
/*chuyển động của các animation */
function set(start,end,marker){	
	console.log("set");
	marker.setPosition(start);
	marker.setMap(map);
	calculateAndDisplay(start,end,marker);
}

/* hàm tính toán đường đi và di chuyển marker */
function calculateAndDisplay(start, end, marker){
		var waypoints=[]
		for(var i=1;i<truckTour.length-1;i++){
			waypoints.push({
	              location:new google.maps.LatLng(truckTour[i].lat,truckTour[i].lng),
	              stopover: true
	            });
		}
		/* đối tượng yêu cầu đường đi*/
		request = {
			origin: start,
			destination: end,
			waypoints:waypoints,
			travelMode: google.maps.DirectionsTravelMode.DRIVING
		};
	
		/* hàm hiển thị kết quả */
		display=function(rep, status){
			
			if(status == google.maps.DirectionsStatus.OK){
				
				polyLine = new google.maps.Polyline({
					path: [],
					strokeColor: '#696969'
				});
				
				/* đặt kết quả trả về vào đối tượng rep: */		
				directionsDisplay = new google.maps.DirectionsRenderer();		
				directionsDisplay.setMap(map);
				directionsDisplay.setDirections(rep);

				startLocation = new Object();
				endLocation = new Object();

				var legs = rep.routes[0].legs;   
				for(var h=0; h<legs.length; h++){
					
					if(h==0){
						startLocation.latlng = legs[h].start_location;
						//marker[index] = addMarker(legs[h].start_location, map);
					}
					endLocation.latlng = legs[h].end_location; 
								
					var steps = legs[h].steps;   			
					for(var j = 0; j < steps.length; j++){
						var nextPoint = steps[j].path;  			
						for(var k = 0; k < nextPoint.length; k++){
							polyLine.getPath().push(nextPoint[k]); 		
						}
					}
				}
			}
			polyLine.setMap(map);
			startAnimation(marker,polyLine,end);	
		};
	
		directionsService.route(request, display);	
}
/* các hàm xử lý polyLine*/
var d; 
var count=0;

var time = 1000; 	// milisecond



/* bắt đầu chuyển động của animation */
function startAnimation(marker,polyLine,end){
	var step = marker.speed;
	//console.log("poliline"+poLyLine.getPath().length);
	distance = polyLine.Distance();
	setTimeout(function(){
		animate(marker,1,step,distance,polyLine,end);
	}, 100);
}
/* chuyển động của animation */


function animate(marker,d,step,distance,polyLine,end){
	if(d > distance){
		marker.setPosition(end);
		if (marker.isDrone==true) {
			markerDrone.isRunning=-1;
		}
		return;
	}
	var p = polyLine.GetPointAtDistance(d); 
	marker.setPosition(p);
	var c=checkWayPoint(p.lat(), p.lng(), step);
	if(c!=-1)
	console.log("c is"+truckTour[c].id);
	if(c!=-1) {
		//console.log("here");
		if(truckTour[c].obRendezvous_node!=null&& marker.isDrone==false){
			
			move = function( wait, newDestination) {
				//console.log("loop");
				console.log("markerDrone.isRunning "+markerDrone.isRunning);
        		if(markerDrone.isRunning==c && laughed!=c) {
          		// call the next "frame" of the animation
	          		setTimeout(function() { 
	            		move(wait); 
	          		}, wait);
        		} else{
        			console.log("running"+markerDrone.isRunning)
        			if(truckTour[c].obLauch_node!=null && markerDrone.isRunning==-1){
        				laughed=c;
        				runDrone(truckTour[c].obLauch_node.lauch_node,truckTour[c].obLauch_node.drone_node,truckTour[c].obLauch_node.rendezvous_node,c);			
        			} else {
        				markerDrone.setMap(null);
        			}
        			var a = d + step;
        			setTimeout(function(){
        				animate(marker,a,step,distance,polyLine,end);
        			}, 100);
        		}
        		
			}
			move(1000);
		}
		else{ 
			if(truckTour[c].obLauch_node!=null && markerDrone.isRunning==-1 && marker.isDrone==false){
				laughed=c;
				runDrone(truckTour[c].obLauch_node.lauch_node,truckTour[c].obLauch_node.drone_node,truckTour[c].obLauch_node.rendezvous_node,c);			
			}
			var a = d + step;
			setTimeout(function(){
				animate(marker,a,step,distance,polyLine,end);
			}, 100);
		}
	} else{
		//console.log("home");
		var a = d + step;
		setTimeout(function(){
			animate(marker,a,step,distance,polyLine,end);
		}, 100);
	}
}

</script>