<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Solution</h1>
		</div>
	</div>
	<!-- 
	<div class="row">
		<button class="btn btn-warning col-sm-1" id="buttonChangePolyline" onclick="hireNormalPolyline();">Hide</button>
		<a class="btn btn-primary"  onclick="saveSolution(this);">Save Solution</a>
	</div>
	 -->
	<div class="row">
		<div id="map" style="height:100%"></div>
	</div>
	
</div>

<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDnc2KpjaihZJNZUJnsa3hSyq_u073q9S8&callback=initMap&sensor=true&libraries=geometry"></script>
<script>
var dataResponse = JSON.parse('${sol}');
var map;
function initMap(){
	map = new google.maps.Map(document.getElementById('map'),{
		center: {lat:21.03, lng:105.8},
		zoom: 12
	});
	var lineSymbol = {
		path: google.maps.SymbolPath.FORWARD_CLOSED_ARROW,
		strokeOpacity: 2,
	    scale:1.5		
	};
	var route = new google.maps.Polyline({
		strokeColor: 'red',
	    strokeOpacity: 1.0,
	    strokeWeight: 3,
	    icons: [{icon: lineSymbol,
            offset: '100%',
            repeat:'200px'}]
	});
	var depotLat = dataResponse.currentPoint.lat;
	var depotLng = dataResponse.currentPoint.lng;
	var depotPos = new google.maps.LatLng(depotLat, depotLng);
	
	route.getPath().push(depotPos);
	
	var icon = {
	    url: baseUrl+"/assets/icon/tima.png", // url
	    scaledSize: new google.maps.Size(60, 60), // scaled size
	    origin: new google.maps.Point(0,0), // origin
	    anchor: new google.maps.Point(35,25) // anchor
	};
	
	var markerDepotPostion = new google.maps.Marker({
		position: depotPos,
		icon : icon,
		map: map
	});
	
	var clientPoints = dataResponse.listItems;
	var lstinfowindow = [];
	
	for(var i=0; i<clientPoints.length; i++){
		var lat = clientPoints[i].addressLatLng.lat;
		var lng = clientPoints[i].addressLatLng.lng;
		var point = new google.maps.LatLng(lat, lng);
		
		var infowindow = new google.maps.InfoWindow({
		    content: "requestID: "+ clientPoints[i].requestID +"</br>"+
		    		"address: " + clientPoints[i].address +"</br>"+
		    		"earlyComingTime: " + clientPoints[i].earlyComingTime + "</br>"+
		    		"lateComingTime: " + clientPoints[i].lateComingTime + "</br>"+
		    		"servingTime: " + clientPoints[i].servingTime + "</br>"+
		    		"computedArrivedTime: "+ clientPoints[i].computedArrivedTime
		});
		lstinfowindow.push(infowindow);
		var marker = new google.maps.Marker({
			position:point,
			icon: baseUrl+"/assets/icon/marker_black16.png",
			//label:labels[labelIndex++ % labels.length],
			map: map,
			infowindow: infowindow
		});
		marker.addListener('click', function() {
			//for(var t=0; t<lstinfowindow.length; t++){
				//lstinfowindow[t].close();
			//}
		    this.infowindow.open(map, this);
		});
		route.getPath().push(point);
	};
	route.setMap(map);	
}
</script>