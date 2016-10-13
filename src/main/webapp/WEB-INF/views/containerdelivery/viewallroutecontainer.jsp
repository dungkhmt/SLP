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
			<h1 class="page-header">Danh sách các tuyến chuyển hàng</h1>
		</div>
		<div class="col-sm-4">
			<div class="form-group">
				<select class="form-control batchselect"  onchange="loadRoute()"  id="select-listBatch">
					<option>Chọn batch</option>
					<c:forEach items="${listBatch}" var="batch">
						<option value="${batch.REQBAT_Code}"><c:out value="${batch.REQBAT_Code}"/></option>
					</c:forEach>
				</select>
			</div>
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
									<th>Mã KH</th>
									<th>Tên KH</th>
									<th>Địa điểm đón hàng</th>
									<th>Thời gian đón hàng dự kiến </th>
									<th>Thời gian đón hàng yêu cầu </th>
									<th>Địa điểm trả hàng</th>
									<th>Thời gian trả hàng dự kiến</th>
									<th>Thời  gian trả hàng yêu cầu</th>
									<th>Số lượng</th>
									<th>Số thứ tự</th>
									<th>Tài xế</th>
									<th>Check</th>
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
var directionsService;
var listSh=JSON.parse('${listShJson}');
var data;
var indexRowTable=[0];
var serviceDistance;
$(document).ready(function(){
	var table = $('#dataTabels-pDL').DataTable({
		
	});
	// 10/9/2016 not view a table
});
function getDistanceGoogleMap(p1,p2,indexOld,index){
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
}
function loadRoute(){
	var batchCode= $(".batchselect").val();
	
	$.ajax({ 
	    type:"POST", 
	    url:"${baseUrl}/containerdelivery/load-route-in-batch",
	    data: batchCode,
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    success: function(response){
	        // Success Message Handler
	       console.log(response);
	       data=response;
	       loadTable(data); 
	    }
    });
	
}
function pushArriveTime2Table(listDuration,listType,startId,startTime){
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
}
function makeArriveTimeRecursive(listPoint,listType,listDuration,startId,id,startTime){
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
}
function loadTable(data){
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
		console.log("id"+idcolor);
			//console.log("length "+ color.length+" "+idcolor % color.length);
		
		var list=data[i].listPoint;
		indexRowTable[i]=count;
		for(var j=0;j<list.length;j++){
			str+="<tr"+" style='background-color:"+color[idcolor]+"' "+">";
			str+="<td>"+list[j].clientCode+"</td>"
			str+="<td>"+list[j].clientName+"</td>"
			str+="<td>"+list[j].pickupAdress+"</td>"
			str+="<td>"+list[j].arriveTimePickup+"</td>"
			str+="<td>"+list[j].expectedTimePickup+"</td>"
			str+="<td>"+list[i].deliveryAdress+"</td>";
			str+="<td>"+list[i].arriveTimeDelivery+"</td>";
			str+="<td>"+list[i].expectedTimeDelivery+"</td>";
			str+="<td>"+list[i].volumn+"</td>";
			str+="<td>"+list[i].sequence+"</td>";
			str+="<td>"+data[i].route_Shipper_Code+"</td>";
			if(j==0)
				str+="<td>"+"<div class='checkbox'> <label><input type='checkbox' onchange=updateCheckList("+i+") value=''></label></div>"+"</td>";
				else str+="<td>"+"</td>";
			str+="</tr>"
			count++;
		}
	}
	$("table#table-pDL tbody").append(str);

}
function initMap() {
	directionsService = new google.maps.DirectionsService;
	serviceDistance = new google.maps.DistanceMatrixService;
    var mapDiv = document.getElementById('map');
    map = new google.maps.Map(mapDiv, {
        center: {lat: 21.03, lng: 105.8},
        zoom: 8
    });
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