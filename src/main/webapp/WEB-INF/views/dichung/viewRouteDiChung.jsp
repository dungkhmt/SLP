<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="<c:url value="/assets/libs/datatables/media/js/jquery.dataTables.js"/>"></script>
<script src="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.js"/>"></script>
<script src="<c:url value="/assets/libs/jquery-ui-1.12.0/jquery-ui.js"/>"> </script>
<script src="<c:url value="/assets/libs/bootstrap-slider/bootstrap-slider.js"/>"> </script>
<link href="<c:url value="/assets/libs/bootstrap-slider/css/bootstrap-slider.css" />" rel="stylesheet" type="text/css" media="all" />
<link href="<c:url value="/assets/libs/bootstrap-datepicker/css/bootstrap-datepicker.css" />" rel="stylesheet" type="text/css" media="all" />
<script src="<c:url value="/assets/libs/bootstrap-datepicker/js/bootstrap-datepicker.js"/>"></script>
<script type="text/javascript" src="<c:url value="/assets/libs/inputDate/dist/js/moment.js" />"></script>
	
<div id="page-wrapper">
	<div class="form-group ">
		<label class="control-label col-sm-2" >Chọn Lô</label>
		<div class="col-sm-2">
			<select class="form-control batchselect" onchange="loadRoute()"  >
				<option value="">Chọn Lô</option>
				<c:forEach items="${listBatch}" var="lbatch">
                	<option value="${lbatch.REQBAT_Code}">${lbatch.REQBAT_Code}</option>
                </c:forEach>
            </select>
         </div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Chi tiết các tuyến</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<div class="row">
		
		<!-- /.form-group .col-sm-3 col-sm-offset-6 col-lg-6  -->
		<h3 class=""><b> Bộ lọc  </b> </h3>
		<div class="form-group">
			<label class="control-label col-lg-1">Ngày</label>
			<div class="col-lg-2">
				
				<input  class="form-control datepicker " name="dateFilter" onchange="updateFilter()" placeholder="Date" ></input>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-1">Ngày</label>
			<div class="col-lg-5">
				
				<input id="time" type="text" class="form-control " onchange="updateFilter()" value="" data-slider-min="0" data-slider-max="86400" data-slider-step="1" data-slider-value="[0,86399]"/><b> Time Range </b> <i id ="timeearly"></i> to <i id ="timelate"></i>
			</div>
		</div>
		
    </div>
    <div id="map" style="height:100%">
    		</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover" id="tableRoute">
							<thead>
								<tr>
									<th>TicketCode</th>
									<th>Address</th>
									<th>DeliveryAddress</th>
									<th>PickupDateTime</th>
									<th>DistanceToNext </th>
									<th>TravelTimeToNext </th>
									<th>Sequence </th>
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
<<style>
<!--
.slider.slider-horizontal {
  width: 900px;
  height: 20px;
}
-->
</style>
<script>
var pathList=[];// danh sach cac poliline
var markerList=[]; // danh sach cac marker
var sortListData=[]; //danh sach index data sorted
var colorInit=["#F7786B","#91A8D0","#91A8D0","#034F84","#FAE03C","#98DDDE","#9896A4","#DD4132","#B18F6A","#79C753","#B93A32","#AD5D5D","#006E51","#B76BA3","#5C7148","#D13076"]; // mang init mau
var table;
var data;
$(function() {
    $( ".datepicker" ).datepicker({
    	format:"yyyy-mm-dd"
    });
    
});
function initMap() {
	directionsService = new google.maps.DirectionsService;
	serviceDistance = new google.maps.DistanceMatrixService;
    var mapDiv = document.getElementById('map');
    map = new google.maps.Map(mapDiv, {
        center: {lat: 21.03, lng: 105.8},
        zoom: 8
    });
	console.log("start");
}
$(document).ready(function(){
	table=$("#tableRoute").DataTable({
		
	});
	console.log(table);
});
var slider = new Slider('#time', {});
console.log(slider);
function loadRoute(){
	var batchCode= $(".batchselect").val();
	console.log(batchCode);
	$.ajax({ 
	    type:"POST", 
	    url:"${baseUrl}/dichung/load-route-in-batch",
	    data: batchCode,
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    success: function(response){
	        // Success Message Handler
	        //console.log(response);
	        data=response;
	        init(data);
	        loadTable(response);
	        viewMap(response);
	        
	    }
    });
	
}
function pushMomentObject(){
	for(var i=0;i<data.length;i++){
		data[i].momentObject=	moment(data[i].rddc_PickupDateTime,"YYYY-MM-DD HH:mm:ss");
	}
	console.log(data);
}
function randomColor(){
/*	p1=Math.floor((Math.random() * 85));
	p2=Math.floor((Math.random() * 255));
	p3=Math.floor((Math.random() * 255));
	return "rgb("+p1+","+p2+","+p3+ ")";*/
	return colorInit[Math.floor((Math.random() * colorInit.length))];
}
function viewMap(data){
	var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
	var route;
	var labelIndex=0;
	var xd=false;
	for(var i=0;i<data.length;i++){
		//console.log(i);
		//console.log(JSON.stringify(response[i]));
		
		if(data[i].rddc_Sequence==0){
		if(i<data.length-1)
		if(data[i+1].rddc_Sequence==1)
			xd=true;
		else xd=false
		if(xd==true){
		var point = new google.maps.LatLng(21.2187149,105.80417090000003);
		//if(route!=undefined)
		//route.getPath().push(point);
		route = new google.maps.Polyline({
			strokeColor: randomColor(),
		    strokeOpacity: 1.0,
		    strokeWeight: 3,
		});
		pathList.push(route);
		}
		labelIndex=0;
		}
		
		var latlng=data[i].rddc_LatLng;
		var lat = latlng.substring(0,latlng.indexOf(',')) ;
		var lng = latlng.substring(latlng.indexOf(',')+1,latlng.length) ;
		var point = new google.maps.LatLng(lat,lng);
		var infowindow = new google.maps.InfoWindow({
		    content: "Pickup Address: "+ data[i].rddc_Address +"<br> Delivery Address: " + data[i].rddc_DeliveryAddress+"<br> Pickup Date Time:"+data[i].rddc_PickupDateTime
		  });
		var marker = new google.maps.Marker({
				position:point,
				label:labels[labelIndex % labels.length],
				map: map,
				icon: baseUrl+"/assets/img/marker20_20.png",
				path: pathList.indexOf(route),
				infowindow: infowindow
		});
		
		marker.addListener('click', function() {
		    this.infowindow.open(map, this);
		  });
		markerList.push(marker);
		if(xd==true){
		route.getPath().push(point);
		route.setMap(map);
		labelIndex++;
		}
	}
	console.log(sortListData);
}
function sortList(data){ // thua
	for(var i=0;i<data.length;i++)
		sortListData[i]=i;
	for(var i=0;i<data.length-1;i++){
		for(var j=i+1;j<data.length;j++){
			if(data[sortListData[i]].momentObject.isAfter(data[sortListData[j]].momentObject)){
				var tmp=sortListData[i];
				sortListData[i]=sortListData[j];
				sortListData[j]=tmp;
			}
		}
	}
	console.log(sortListData);
	
}

function updateFilter(){
	var dateFilter=$(".datePicker").val();
	var sliderVal=slider.getValue()+'';
	var val=sliderVal.split(',');
	console.log(val);
	var dateTimeFilterEarly=moment(dateFilter,"YYYY-MM-DD");
	var dateTimeFilterLate=moment(dateFilter,"YYYY-MM-DD");
	dateTimeFilterEarly.add(val[0],'s');
	dateTimeFilterLate.add(val[1],'s');
	console.log("dateTimeFilterEarly "+dateTimeFilterEarly.format("YYYY-MM-DD HH:mm:ss"));
	console.log("dateTimeFilterLate "+dateTimeFilterLate.format("YYYY-MM-DD HH:mm:ss"));
	pushNewFilterTime(dateTimeFilterEarly.format("HH:mm:ss"),dateTimeFilterLate.format("HH:mm:ss"))
	for(var i=0;i<pathList.length;i++)
		pathList[i].setMap(null);
	for(var i=0;i<sortListData.length;i++ ){
		if(data[sortListData[i]].momentObject.isSameOrAfter(dateTimeFilterEarly) && data[sortListData[i]].momentObject.isSameOrBefore(dateTimeFilterLate)){
			markerList[sortListData[i]].setMap(map);
			pathList[markerList[sortListData[i]].path].setMap(map);
		} else{
			markerList[sortListData[i]].setMap(null);
		}
	}
}
function loadTable(data){
	//console.log(data);
	$("table#tableRoute tbody").html("");
	gray="#F0F0F0";
	white="#FFFFFF";
	var color=["#F0F0F0","#FFFFFF"];
	var idcolor=0;
	str=null;
	for(var i=0;i<data.length;i++){
		//console.log("i data[i].rddc_Group" +data[i].rddc_Group);
		if(i>0)
		if(data[i].rddc_Group!=data[i-1].rddc_Group){ 
			idcolor=idcolor+1;
			idcolor=idcolor % color.length;
			//console.log("id"+idcolor);
			//console.log("length "+ color.length+" "+idcolor % color.length);
		}
		str+="<tr"+" style='background-color:"+color[idcolor]+"' "+">";
		str+="<td>"+data[i].rddc_TicketCode+"</td>"
		str+="<td>"+data[i].rddc_Address+"</td>"
		str+="<td>"+data[i].rddc_DeliveryAddress+"</td>"
		str+="<td>"+data[i].rddc_PickupDateTime+"</td>"
		str+="<td>"+data[i].rddc_DistanceToNext+"</td>"
		str+="<td>"+data[i].rddc_TravelTimeToNext+"</td>"
		str+="<td>"+data[i].rddc_Sequence+"</td>"
		str+="</tr>"
	}
	$("table#tableRoute tbody").append(str);

}
function init(data){
	pathList=[];
	markerList=[];
	sortListData=[]; 
	pushMomentObject();
	sortList(data);
	//console.log(data);
	
}
function pushNewFilterTime(timeEarly, timeLate){
	//console.log(timeEarly+" "+timeLate);
	$("#timeearly").html(timeEarly);
	$("#timelate").html(timeLate);
} 
</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDEXgYFE4flSYrNfeA7VKljWB_IhrDwxL4&callback=initMap">
</script>