<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- DataTables CSS -->
<link href="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"/>" rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="<c:url value="/assets/libs/datatables-responsive/css/dataTables.responsive.css" />" rel="stylesheet">

<!-- DataTables JavaScript -->
<script src="<c:url value="/assets/libs/datatables/media/js/jquery.dataTables.js"/>"></script>
<script src="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.js"/>"></script>

<script type="text/javascript" src="<c:url value="/assets/libs/inputDate/dist/js/moment.js" />"></script>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Kết quả</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<div id="map" style="height:100%">
    </div>
  
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover" id="table-pDL">
							<thead>
								<tr>
									<th>Mã</th>
									<th>Địa điểm đón</th>
									<th>Địa điểm trả</th>
									<th>Khoảng cách đến điểm tiếp theo</th>
									
								</tr>
							</thead>	
							<tbody>
							</tbody>						
						</table>
					</div>
					<!--/.dataTable_wrapper -->
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
</div>
<!-- /#page-wrapper -->

<script>
var map;
var checkedList=[];
var directionsService;

var data;
var pathList=[];// danh sach cac poliline
var markerList=[]; // danh sach cac marker
var indexRowTable=[0];
var colorInit=["#F7786B","#91A8D0","#91A8D0","#034F84","#FAE03C","#98DDDE","#9896A4","#DD4132","#B18F6A","#79C753","#B93A32","#AD5D5D","#006E51","#B76BA3","#5C7148","#D13076"]; // mang init mau
var serviceDistance;
$(document).ready(function(){
	var table = $('#table-pDL').DataTable({
		
	});
	// 10/9/2016 not view a table
});


function viewRoute(){
	for(var i=0;i<pathList.length;i++)
		pathList[i].setMap(null);
	for(var i=0;i<markerList.length;i++ )
		markerList[i].setMap(null);
	for(var i=0;i<checkedList.length;i++){
		list=data[checkedList[i]].listPoint;
		for(var j=0;j<list.length;j++){
			list[j].marker.setMap(map);
			if(list[j].marker.path!=-1)
				pathList[list[j].marker.path].setMap(map);
		}
	}
		
}
/*function getDistanceGoogleMap(p1,p2,indexOld,index){
	serviceDistance.getDistanceMatrix(
			  {
			    origins: [p1],
			    destinations: [p2],
			    travelMode: 'DRIVING',
			    unitSystem: google.maps.UnitSystem.METRIC,
			    avoidHighways: false,
			    avoidTolls: false,
			  }, function(response,status){
				  if(status!=='OK'){
					  alert("Fail!");
					  return null;
				  }else{
					  distanceMatrix[indexOld][index]={
							  duration: response.rows[0].elements[0].duration.value,
							  distance: response.rows[0].elements[0].distance.value
					  } 
					  resCount++;
				  }
			  });
}*/

/*function pushArriveTime2Table(listDuration,listType,startId,startTime){
	var dateTime= moment(startTime,"YYYY-MM-DD HH:mm:ss");
	for(var i=0;i<listDuration.length;i++){
		var cellsOfShipper=document.getElementById("table-pDL").rows[startId+i+1].cells;
		dateTime=dateTime.add(listDuration[i],"seconds");
		var strdate=dateTime.toObject();
		//console.log(type);
		if(listType[i]=="PICKUP")
		cellsOfShipper[3].innerHTML=strdate.years+"-"+strdate.months+"-"+strdate.date+" "+strdate.hours+":"+strdate.minutes+":"+strdate.seconds;
		else 
			cellsOfShipper[6].innerHTML=strdate.years+"-"+strdate.months+"-"+strdate.date+" "+strdate.hours+":"+strdate.minutes+":"+strdate.seconds;
		//cellsOfShipper[3].innerHTML=moment.duration(distanceTime,'seconds').days() + "ngày "+ moment.duration(distanceTime,'seconds').hours()+" giờ "+moment.duration(distanceTime,'seconds').minutes()+"phút";
	}
}*/
/*function makeArriveTimeRecursive(listPoint,listType,listDuration,startId,id,startTime){
	serviceDistance.getDistanceMatrix(
			  {
			    origins: [listPoint[id-1]],
			    destinations: [listPoint[id]],
			    travelMode: 'DRIVING',
			    unitSystem: google.maps.UnitSystem.METRIC,
			    avoidHighways: false,
			    avoidTolls: false,
			  }, function(response,status){
				  if(status!=='OK'){
					  alert("Fail!");
					  return null;
				  }else{
					  
					duration= response.rows[0].elements[0].duration.value;
					listDuration[id-1]=duration;
					if(id>= listPoint.length-1){
						
						pushArriveTime2Table(listDuration,listType,startId,startTime);
					} else {
						
						makeArriveTimeRecursive(listPoint,listType,listDuration,startId,id+1,startTime);
					}
				  }
			  });
}*/
function loadTable(data){
	console.log("data: "+data);
	console.log("here");
	var data=data["routes"];
	console.log(data);
	$("table#table-pDL tbody").html("");
	gray="#F0F0F0";
	white="#FFFFFF";
	var color=["#F0F0F0","#FFFFFF"];
	var idcolor=0;
	str=null;
	count=0;
	indexRowTable[0]=count;
	for(var i=0;i<data.length;i++){
		//console.log("i data[i].rddc_Group" +data[i].rddc_Group); 
		
		idcolor=(idcolor+1) % color.length;
		//console.log("id"+idcolor);
			//console.log("length "+ color.length+" "+idcolor % color.length);
		var totalDistance=0;
		var list=data[i].route;
		indexRowTable[i]=count;
		for(var j=0;j<list.length;j++){
			totalDistance+=list[j].distance2Next;
			str+="<tr"+" style='background-color:"+color[idcolor]+"' "+">";
			str+="<td>"+list[j].code+"</td>";
			if(list[j].action=="PICKUP") {
				str+="<td>"+list[j].address+"</td>";
				str+="<td>"+"--"+"</td>";
			} else {
				str+="<td>"+"--"+"</td>";
				str+="<td>"+list[j].address+"</td>";
			}
			str+="<td>"+list[j].distance2Next+"m   </td>";
			str+="</tr>"
			count++;
		}
		str+="<tr"+" style='background-color:"+"gray"+"' "+">";
		str+="<td colspan='3'>"+"Total :" +"</td>";
		str+="<td >"+totalDistance.toFixed(3) +"m  </td>";
		str+="</tr>"
	}
	$("table#table-pDL tbody").append(str);

}
function randomColor( len){
	/*	p1=Math.floor((Math.random() * 85));
		p2=Math.floor((Math.random() * 255));
		p3=Math.floor((Math.random() * 255));
		return "rgb("+p1+","+p2+","+p3+ ")";*/
		var colorArr=[];
		for(i=0;i<len;){
			var ii=Math.floor((Math.random() * colorInit.length));
			var xd=false;
			for(j=0;j<colorArr.length;j++)
				if(colorArr[j]==ii) xd=true;
			if(xd==false){
				colorArr.push(ii);
				i++;
			}
		}
		console.log(colorArr);
		var colorThis=[];
		for(i=0;i<len;i++){
			colorThis.push(colorInit[colorArr[i]]);
		}
		return colorThis;
	}
function viewMap(data){
	var data=data["routes"];
	console.log("viewMap");
	var route;
	var lineSymbol = {
			path: google.maps.SymbolPath.FORWARD_CLOSED_ARROW,
			strokeOpacity: 2,
			scale: 1.5
		}
	
	var xd=false;
	var colorLine=randomColor(data.length);
	console.log(colorLine);
	for(var i=0;i<data.length;i++){
		console.log(i);
		//console.log(JSON.stringify(response[i]));
		var list= data[i].route;
		
		route = new google.maps.Polyline({
			strokeColor: colorLine[i],
		    strokeOpacity: 1.0,
		    strokeWeight: 3,
		    icons: [{
				icon: lineSymbol,
				offset: '100%',
				repeat: '200px'
			}]
		});
		var listicon=["pickupmarker.png","deliverymarker.png"];
		pathList.push(route);
		var icon=0;
		for(var j=0;j<list.length;j++){
			strcontent="";
			if(list[j].action=="PICKUP"){
				var latlng=list[j].latlng;
				var ll= latlng.split(", ");
				var lat = parseFloat(ll[0]);
				var lng = parseFloat(ll[1]);
				icon=0;
				//strcontent="Address: "+ list[j].pickupAdress+"<br>Pickup Date Time: " +list[j].arriveTimePickup+"<br> Expected DateTime Pickup:"+list[j].expectedTimePickup+"<br> Quantity: "+list[j].volumn;
			} else if(list[j].action=="DELIVERY"){
				var latlng=list[j].latlng;
				var ll= latlng.split(", ");
				var lat = parseFloat(ll[0]);
				var lng = parseFloat(ll[1]);
				icon=1;
				//strcontent= "Address: "+ list[j].deliveryAdress+"<br>Delivery Date Time: " +list[j].arriveTimeDelivery+"<br> Expected DateTime Delivery:"+list[j].expectedTimeDelivery+"<br> Quantity: "+list[j].volumn;
			}
			var point = new google.maps.LatLng(lat,lng);
			/*var infowindow = new google.maps.InfoWindow({
			    content: strcontent
			  });
				*/
			var marker = new google.maps.Marker({
				position:point,
				map: map,
				label:null,
				icon: baseUrl+"/assets/icon/"+listicon[icon],
				path: pathList.indexOf(route)
				
			});
			console.log(marker);
			/*marker.addListener('click', function() {
			    this.infowindow.open(map, this);
			});*/
			//marker.setMap(map);
			list[j].marker=marker;
			markerList.push(marker);
			route.getPath().push(point);
			route.setMap(map);
		}		
		
		
	}
	
}
function initMap() {
	directionsService = new google.maps.DirectionsService;
	serviceDistance = new google.maps.DistanceMatrixService;
    var mapDiv = document.getElementById('map');
    map = new google.maps.Map(mapDiv, {
        center: {lat: 21.03, lng: 105.8},
        zoom: 14
    });
    var data=JSON.parse('${sol}');
    //console.log(data);
    loadTable(data);
    viewMap(data);
    //console.log(data);
    
	/*console.log("start");
    for(var  i=0;i< listRM.length;i++){
    	var listDuration=[];
    	var listPoint=[];
    	var listType=[];
    	for(var j=0;j < listSh.length ; j++)
    		if(listSh[j].SHP_Code==listRM[i].shipperCode){
    			listPoint.push({
        			'lat' : listSh[j].SHP_DepotLat,
        			'lng' : listSh[j].SHP_DepotLng
        		});
    			break;
    		}
    	for(var j=listRM[i].startId;j<listRM[i].startId+listRM[i].nStep;j++ ){
    		listType.push(listRD[j].type);
    		if(listRD[j].type=="PICKUP"){
    		listPoint.push({
    			'lat' : listRD[j].pickupLat,
    			'lng' : listRD[j].pickupLng
    		})} else {
    			listPoint.push({
        			'lat' : listRD[j].deliveryLat,
        			'lng' : listRD[j].deliveryLng
        		}) 
    		}
    	}
    	makeArriveTimeRecursive(listPoint,listType,listDuration,listRM[i].startId,1,listRM[i].timeStartRoute);
    }*/
  }
</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDEXgYFE4flSYrNfeA7VKljWB_IhrDwxL4&callback=initMap">
</script>