<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">VRP-Load3D Solution</h1>
		</div>
	</div>
	<div class="row">
		<div id="map" style="height:100%"></div>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="modal-load3d" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Load3D of <span id="vehicle-name"></span></h4>
			</div>
			<div class="modal-body">
				<div class="row" id="canvas-load3d">
	
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDEXgYFE4flSYrNfeA7VKljWB_IhrDwxL4&callback=initMap"></script>

<script src="<c:url value="/assets/libs/processing/processing.js"/>"></script>

<script>
var colorInit=["red","green","blue","yellow","black"]; // mang init mau
var map;
var dataResponse = JSON.parse('${sol}');
//console.log("dataResponse::"+JSON.stringify(dataResponse));
var routes = dataResponse.routes;
var loads = dataResponse.loads;
var polyline_route = [];
var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';

function f_latlng(slatlng){
	var index_cut = slatlng.indexOf(',');
	var latlng = [];
	var lat = slatlng.substring(0,index_cut);
	latlng[0] = parseFloat(lat);
	var lng = slatlng.substring(index_cut+1);
	latlng[1] = parseFloat(lng);
	
	return latlng;
}

function initMap(){
	map = new google.maps.Map(document.getElementById('map'),{
		center: {lat:21.03, lng:105.8},
		zoom: 12
	});
	
	for(var i=0; i<routes.length; i++){
		var elements = routes[i].elements;
		
		var lineSymbol = {
			path: google.maps.SymbolPath.FORWARD_CLOSED_ARROW,
			strokeOpacity: 2,
			scale: 1.5
		}
		
		polyline_route[i] = new google.maps.Polyline({
			map : map,
			strokeColor : colorInit[i],
			strokeOpacity : 1.0,
			strokeWeight : 3,
			icons: [{
				icon: lineSymbol,
				offset: '100%',
				repeat: '200px'
			}]
		});
		//polyline_route[i].setMap(map);
		var labelIndex = 0;
		var prelat = -1;
		var prelng = -1;
		for(var j=0; j<elements.length; j++){
			var latlng = f_latlng(elements[j].latlng);
			var lat = latlng[0];
			var lng = latlng[1];
			
			if(prelat == lat && prelng == lng){
				continue;
			}
			
			prelat = lat;
			prelng = lng;
			
			console.log("route["+i+"].element["+elements[j].code+"] = ("+lat+","+lng+")");
			var pos = new google.maps.LatLng(lat,lng);
			
			if(elements[j].code == 'depot'){
				var marker = new google.maps.Marker({
					map : map,
					position : pos,
					label: "DP"
				});
			}else{
				var marker = new google.maps.Marker({
					map : map,
					position : pos,
					label: labels[labelIndex++ % labels.length]
				});
			}
			polyline_route[i].getPath().push(pos);
		}
	}
	
	for(var i=0; i<routes.length; i++){
		google.maps.event.addListener(polyline_route[i],'click',function(event){
			var index_route = polyline_route.indexOf(this);
			console.log("index_route clicked = "+index_route);
			f_view_load3d(index_route);
		});
	}
}
var edgecolor = color(34, 68, 204);
function f_view_load3d (index_route){
	$('#modal-load3d').modal();
	$('#vehicle-name').text(loads[index_route].vehicle.code);
	$('#canvas-load3d').html("<canvas id='canvas1'></canvas>")
	var sketch = new Processing.Sketch();
	//sketch.use3DContext = true;
	sketch.attachFunction = function(processing){
		var createCuboid = function(x, y, z, w, h, d) { 
			var nodes = [[x,   y,   z  ],
             			 [x,   y,   z+d],
              			 [x,   y+h, z  ],
             			 [x,   y+h, z+d],
             			 [x+w, y,   z  ],
             			 [x+w, y,   z+d],
             			 [x+w, y+h, z  ],
             			 [x+w, y+h, z+d]];
			var edges = [[0, 1], [1, 3], [3, 2], [2, 0],
            			 [4, 5], [5, 7], [7, 6], [6, 4],
             			 [0, 4], [1, 5], [2, 6], [3, 7]];
			return { 'nodes': nodes, 'edges': edges };
		};

		var objects = [];
		var load = loads[index_route];
		var vehicle = load.vehicle;
		var object = createCuboid(0,0,0,vehicle.width,vehicle.length,vehicle.height);
		objects.push(object);
		var loadElements = load.loadElements;
		for(var i=0; i<loadElements.length; i++){
			var e = loadElements[i];
			var tmp_o = createCuboid(e.posWidth,e.posLength,e.posHeight,e.item.w,e.item.l,e.item.h);
			objects.push(tmp_o);
		}
		// var nodes = object.nodes;
		// var edges = object.edges;

		// Rotate shape around the z-axis
		var rotateZ3D = function(theta,nodes) {
		    var sin_t = Math.sin(theta);
		    var cos_t = Math.cos(theta);
		    
		    for (var n=0; n<nodes.length; n++) {
		        var node = nodes[n];
		        var x = node[0];
		        var y = node[1];
		        node[0] = x * cos_t - y * sin_t;
		        node[1] = y * cos_t + x * sin_t;
		    }
		};

		var rotateY3D = function(theta, nodes) {
		    var sin_t = Math.sin(theta);
		    var cos_t = Math.cos(theta);
		    
		    for (var n=0; n<nodes.length; n++) {
		        var node = nodes[n];
		        var x = node[0];
		        var z = node[2];
		        node[0] = x * cos_t - z * sin_t;
		        node[2] = z * cos_t + x * sin_t;
		    }
		};

		var rotateX3D = function(theta, nodes) {
		    var sin_t = Math.sin(theta);
		    var cos_t = Math.cos(theta);
		    
		    for (var n=0; n<nodes.length; n++) {
		        var node = nodes[n];
		        var y = node[1];
		        var z = node[2];
		        node[1] = y * cos_t - z * sin_t;
		        node[2] = z * cos_t + y * sin_t;
		    }
		};

		for(var o=0; o< objects.length; o++){
			var ob = objects[o];
			var nodes = ob.nodes;
			//var edges = ob.edges;
			rotateX3D(120,nodes);
			rotateY3D(120,nodes);
			rotateZ3D(120,nodes);
		}
		
		processing.setup = function(){
			processing.size(600,500);
			processing.background(255,255,255);
			processing.noLoop();
			//processing.translate(400, 200);
		}
		
		processing.draw = function(){
			//var o = 0;
			
			//setTimeout(function(){
				//if(o<objects.length){
			//console.log("pre draw");
			//f_draw_box(processing,objects);
			
			var o = 0;
			f_draw_box = function (processing, objects){
				console.log("draw box "+o);
				setTimeout(function(){
					if(o==0){
						processing.translate(400, 200);	
					}
					processing.stroke(34, 68, 204);
					var edges = objects[o].edges;
					var nodes = objects[o].nodes;
					for (var e=0; e<edges.length; e++) {
						var n0 = edges[e][0];
						var n1 = edges[e][1];
						var node0 = nodes[n0];
						var node1 = nodes[n1];
						processing.line(node0[0], node0[1], node1[0], node1[1]);
					}
				
				    // Draw nodes
				    processing.fill(40, 168, 107);
				    processing.noStroke();
				    for (var n=0; n<nodes.length; n++) {
				        var node = nodes[n];
				        processing.ellipse(node[0], node[1], 4, 4);
				    }
				    o++;
				    if(o < objects.length){
				    	f_draw_box(processing,objects);
			    	}
				},1000);
			};
			f_draw_box(processing,objects);
			
			//console.log("draw");
			//	}
			//	o++;
			//},3000);
			//processing.translate(400, 200);			
			/*for(var o=0; o<objects.length; o++){
				processing.stroke(34, 68, 204);
				var edges = objects[o].edges;
				var nodes = objects[o].nodes;
				for (var e=0; e<edges.length; e++) {
					var n0 = edges[e][0];
					var n1 = edges[e][1];
					var node0 = nodes[n0];
					var node1 = nodes[n1];
					processing.line(node0[0], node0[1], node1[0], node1[1]);
				}
			
			    // Draw nodes
			    processing.fill(40, 168, 107);
			    processing.noStroke();
			    for (var n=0; n<nodes.length; n++) {
			        var node = nodes[n];
			        processing.ellipse(node[0], node[1], 4, 4);
			    }	
			}*/
		}
	}
	var canvas = document.getElementById("canvas1");
	var p = new Processing(canvas,sketch);
}
</script>