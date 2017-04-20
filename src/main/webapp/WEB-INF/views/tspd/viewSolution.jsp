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
	<div id="map" style="height:100%"></div>
</div>

<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDEXgYFE4flSYrNfeA7VKljWB_IhrDwxL4&callback=initMap"></script>
<script>
var map;
var dataResponse = JSON.parse('${tour}');
console.log("data response: "+JSON.stringify(dataResponse));

function initMap(){
	map = new google.maps.Map(document.getElementById('map'),{
		center: {lat:21.03, lng:105.8},
		zoom: 14
	});
}

function view_tspdls_solution(){
	initMap();
	var tour_tspdls = dataResponse[0];
	view_tour(tour_tspdls);
}

function view_grasp_solution(){
	//initMap();
	//var tour_grasp = dataResponse[1];
	//view_tour(tour_tspdls);
}

function view_tour(data){
	var truckTour = data.td.truck_tour;
	var droneDeliveries = data.dd;
	console.log(data)
	
	var lineSymbol = {path: google.maps.SymbolPath.FORWARD_CLOSED_ARROW};
	
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
		}else{
			marker_pi = new google.maps.Marker({
				map:map,
				position: pi
			})	
		}
		
		var pj = truckTour[i+1];
		pj = new google.maps.LatLng(pj.lat,pj.lng);
		var marker_pj;
		if(i!=truckTour.length-2){
			marker_pj = new google.maps.Marker({
				map:map,
				position: pj
			})	
		}
	
		var polyline = new google.maps.Polyline({
			path: [pi,pj],
			strokeColor: "blue",
			strokeOpacity: 1.0,
			strokeWeight: 2,
			icons: [{
	            icon: lineSymbol,
	            offset: '100%'
	        }],
			map: map
		})
	}
	
	for(var i=0; i<droneDeliveries.length; i++){
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
	}
}


var marker=[];
var marker1;
var markers=[];
var polyLine=[];
var startLocation = [];
var endLocation =[];
var start= [];
var end= [];
var speed=[];
//var geocoder = new google.maps.Geocoder();



/* đánh dấu marker trên bản đồ */
function addMarker1(location, map){
	var marker1 = new google.maps.Marker({
		position: location,
		map: map
	});
	markers.push(marker1);
}

function setMarkersOnAll(map){
	for(var i=0; i<markers.length; i++){
		markers[i].setMap(map);
	}
}

function clearMarkers(){
	setMarkersOnAll(null);
}


// hàm tạo animation marker
function addMarker(location, map){
	var marker = new google.maps.Marker({
		position: location,
		map: map,
		icon: "icon_oto.png",
	});
	return marker;
}


/*chuyển động của các animation */
function set(){
	addStartEnd();		
	clearMarkers();		
	var i=0;
	for( i=0; i< start.length; i= i+2){
		calculateAndDisplay(directionsService, directionsDisplay[i], i);
	}
}

//thêm điểm bắt đầu và kết thúc của animation
function addStartEnd(){
	for(var i=0; i< markers.length; i=i+2){
		start[i] = markers[i].position;
		end[i] = markers[i+1].position;
	}
}

/* các đối tượng và mảng tìm đường */
var directionsService = new google.maps.DirectionsService;
var directionsDisplay = [];		
var request = [];	
var display = [];	




/* hàm tính toán đường đi và di chuyển marker */
function calculateAndDisplay(directionsService, directionsDisplay, index){
	
		/* đối tượng yêu cầu đường đi*/
		request[index] = {
			origin: start[index],
			destination: end[index],
			travelMode: google.maps.DirectionsTravelMode.DRIVING
		};
	
		/* hàm hiển thị kết quả */
		display[index]=function(rep, status){
			
			if(status == google.maps.DirectionsStatus.OK){
				
				polyLine[index] = new google.maps.Polyline({
					path: [],
					strokeColor: '#696969'
				});
				
				/* đặt kết quả trả về vào đối tượng rep: */		
				directionsDisplay = new google.maps.DirectionsRenderer();		
				directionsDisplay.setMap(map);
				directionsDisplay.setDirections(rep);

				startLocation[index] = new Object();
				endLocation[index] = new Object();

				var legs = rep.routes[0].legs;   
				for(var h=0; h<legs.length; h++){
					
					if(h==0){
						startLocation[index].latlng = legs[h].start_location;
						marker[index] = addMarker(legs[h].start_location, map);
					}
					endLocation[index].latlng = legs[h].end_location; 
								
					var steps = legs[h].steps;   			
					for(var j = 0; j < steps.length; j++){
						var nextPoint = steps[j].path;  			
						for(var k = 0; k < nextPoint.length; k++){
							polyLine[index].getPath().push(nextPoint[k]); 		
						}
					}
				}
			}
			polyLine[index].setMap(map);
			startAnimation(index);	
		};
	
		directionsService.route(request[index], display[index]);	
}
	

	
	

/* các hàm xử lý polyLine*/
var d; 
var count=0;
var distance=[];
var time = 1000; 	// milisecond



/* bắt đầu chuyển động của animation */
function startAnimation(number){
	var a = document.getElementById("a"); 	// meters
	var step = parseFloat(a.value);
	distance[number] = polyLine[number].Distance();
	setTimeout("animate("+number+",1,"+step+")", 2000);
}




/* chuyển động của animation */
function animate(number,d,step){
	
	if(d > distance[number]){
		marker[number].setPosition(endLocation[number].latlng);
		return;
	}
	var p = polyLine[number].GetPointAtDistance(d); 
	marker[number].setPosition(p);
	var a = d + step;
	setTimeout("animate("+number+","+a+","+step+")", time);
}




/* hàm Geocoding */
function geocodeAddress(geocoder, resultsMap) {
	        	
	var address = document.getElementById('address').value; 
				
	geocoder.geocode({'address': address}, function(results, status) {
				if (status === google.maps.GeocoderStatus.OK) {
				var positions = results[0].geometry.location;   
				resultsMap.setCenter(positions);  		
				addMarker1(positions, resultsMap);		
			} else {
				alert('Khong the ma hoa dia chi dia ly vi : ' + status);
			}	
	   	}
	);
}


/* tính khoảng cách giữa 2 điểm địa lý */
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
		console.log(dist+" "+metres);
	}
	if (dist < metres) {
		return null;
	}
	var p1= this.getPath().getAt(i-2);
	var p2= this.getPath().getAt(i-1);
	var m = (metres-olddist)/(dist-olddist);
	console.log(m);
	return new google.maps.LatLng( p1.lat() + (p2.lat()-p1.lat())*m, p1.lng() + (p2.lng()-p1.lng())*m);
}

/* Prototype của các hàm */
google.maps.Polyline.prototype.Distance             = google.maps.Polygon.prototype.Distance;
google.maps.Polyline.prototype.GetPointAtDistance   = google.maps.Polygon.prototype.GetPointAtDistance;
</script>