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
		isDrone: true
	});
	markerTruck = new google.maps.Marker({
		icon : "https://maps.gstatic.com/mapfiles/ms2/micons/truck.png",
		position : null,
		speed: dataResponse.truckSpeed,
		isDrone: false
	});
	
	for(var i=0; i<truckTour.length-1; i++){
		var pi = truckTour[i];
		pi = new google.maps.LatLng(pi.lat,pi.lng);
		
		var marker_pi;
		if(i == 0){
			marker_pi = new google.maps.Marker({
				map: map,
				icon : "https://www.google.com/mapfiles/marker_yellow.png",
				position : pi
			})
		};
		
		var pj = truckTour[i+1];
		pj = new google.maps.LatLng(pj.lat,pj.lng);
	}	
	
		/* var polyline = new google.maps.Polyline({
			path: [pi,pj],
			strokeColor: "blue",
			strokeOpacity: 1.0,
			strokeWeight: 2,
			icons: [{
	            icon: lineSymbol,
	            offset: '100%'
	        }],
			map: map
		}) */

	
	/* for(var i=0; i<droneDeliveries.length; i++){
		var dd = droneDeliveries[i];
		var marker = new google.maps.Marker({
			map: map,
			icon: "https://www.google.com/mapfiles/marker_green.png",
			position: dd.drone_node
				
		})
		
		var lauch_node = new google.maps.LatLng(dd.lauch_node.lat, dd.lauch_node.lng);
		var drone_node = new google.maps.LatLng(dd.drone_node.lat, dd.drone_node.lng);
		var rendezvous_node = new google.maps.LatLng(dd.rendezvous_node.lat,dd.rendezvous_node.lng);
		
		var polyline = new google.maps.Polyline({
			path: [lauch_node,drone_node],
			strokeColor: "red",
			strokeOpacity: 1.0,
			strokeWeight: 2,
			icons: [{
	            icon: lineSymbol,
	            offset: '100%'
	        }],
			map: map
		}) 
		polyline = new google.maps.Polyline({
			path: [drone_node,rendezvous_node],
			strokeColor: "red",
			strokeOpacity: 1.0,
			strokeWeight: 2,
			icons: [{
	            icon: lineSymbol,
	            offset: '100%'
	        }],
			map: map
		})
	} */
	animation(0);
	
}
function animation(i){
		console.log("in while");
		if(truckTour[i].obRendezvous_node!=null){
			move = function( wait, newDestination) {
        		if(xdRendezvous != true) {
          		// call the next "frame" of the animation
	          		setTimeout(function() { 
	            		move(wait); 
	          		}, wait);
        		} else{
        			markerDrone.setMap(null);
        			if(truckTour[i].obLauch_node!=null){
        				runDrone(truckTour[i].obLauch_node.lauch_node,truckTour[i].obLauch_node.drone_node,truckTour[i].obLauch_node.rendezvous_node);
        			}
        			runTruck(new google.maps.LatLng(truckTour[i].lat,truckTour[i].lng),new google.maps.LatLng(truckTour[i+1].lat,truckTour[i+1].lng));
        			
        		}
        		
			}
			move(1000);
		}
		else{ 
			xdRendezvous=false;
			if(truckTour[i].obLauch_node!=null){
				runDrone(truckTour[i].obLauch_node.lauch_node,truckTour[i].obLauch_node.drone_node,truckTour[i].obLauch_node.rendezvous_node);			
			}
			runTruck(new google.maps.LatLng(truckTour[i].lat,truckTour[i].lng),new google.maps.LatLng(truckTour[i+1].lat,truckTour[i+1].lng));
		}
		
		run= function( wait) {
    		if(xdTruck != true) {
          		setTimeout(function() { 
            		run( wait); 
          		}, wait);
    		} else{
    			i=i+1;
    			xdTruck=false;
    			console.log("i is"+i);
    			if(i>=truckTour.length-1 ) return;
    			animation(i);
    		}
    		
		}
		console.log("xdTruck " +xdTruck);
		run(1000); 
}
function runTruck(start,end){
	console.log("run Truck");
	set(start,end,markerTruck,false,false,true);
	
}
var xdTruck=false;
var xdDroneNode=false;
function runDrone(lauch,drone,rendezvous){
	xdRendezvous=false;
	xdDroneNode=false;
	set(lauch,drone,markerDrone,false,true,false);
	
	move = function( wait) {
		if(xdDroneNode != true) {
      		setTimeout(function() { 
        		move( wait); 
      		}, wait);
		} else{
			xdDroneNode=false;
			set(lauch,drone,markerDrone,true,false,false);		
		}
		
	}
	move(1000);
	move2 = function( wait) {
		//console.log("index"+index);
		if(xdRendezvous != true) {
      		setTimeout(function() { 
        		move( wait); 
      		}, wait);
		} else{
			return		
		}
		
	}
	move2(1000);
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
function set(start,end,marker,isRendezNode,isDroneNode,isTruck){	
	console.log("set");
	marker.setPosition(start);
	marker.setMap(map);
	calculateAndDisplay(start,end,marker,isRendezNode,isDroneNode,isTruck);
}

/* hàm tính toán đường đi và di chuyển marker */
function calculateAndDisplay(start, end, marker,isRendezNode,isDroneNode,isTruck){
	
		/* đối tượng yêu cầu đường đi*/
		request = {
			origin: start,
			destination: end,
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
			startAnimation(marker,polyLine,end,isRendezNode,isDroneNode,isTruck);	
		};
	
		directionsService.route(request, display);	
}
	

	
	

/* các hàm xử lý polyLine*/
var d; 
var count=0;

var time = 1000; 	// milisecond



/* bắt đầu chuyển động của animation */
function startAnimation(marker,poLyLine,end,isRendezNode,isDroneNode,isTruck){
	var step = marker.speed;
	distance = polyLine.Distance();
	setTimeout(function(){
		animate(marker,1,step,distance,poLyLine,end,isRendezNode,isDroneNode,isTruck);
	}, 100);
}
/* chuyển động của animation */
function animate(marker,d,step,distance,poLyLine,end,isRendezNode,isDroneNode,isTruck){
	
	if(d > distance){
		console.log("is Truck"+isTruck);
		marker.setPosition(end);
		if (isRendezNode==true) xdRendezvous=true;
		if(isTruck==true) xdTruck=true;
		if(isDroneNode==true) xdDroneNode=true
		return;
	}
	var p = polyLine.GetPointAtDistance(d); 
	marker.setPosition(p);
	var a = d + step;
	setTimeout(function(){
		animate(marker,a,step,distance,poLyLine,end,isRendezNode,isDroneNode,isTruck);
	}, 100);
}



</script>