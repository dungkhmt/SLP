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

<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDEXgYFE4flSYrNfeA7VKljWB_IhrDwxL4&callback=initMap"></script>
<script>
var map;
var dataResponse = JSON.parse('${tour}');
console.log("data response: "+JSON.stringify(dataResponse));

function initMap(){
	map = new google.maps.Map(document.getElementById('map'),{
		center: {lat:21.03, lng:105.8},
		zoom: 12
	});
}

function view_tspdls_solution(){
	initMap();
	var tour_tspdls = dataResponse[0];
	view_tour(tour_tspdls);
}

function view_grasp_solution(){
	initMap();
	var tour_grasp = dataResponse[1];
	view_tour(tour_grasp);
}

function view_tour(data){
	var truckTour = data.td.truck_tour;
	var droneDeliveries = data.dd;
	
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
</script>