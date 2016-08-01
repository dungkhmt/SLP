<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="page-wrapper">
	<div id="googleMap" style="width:100%;height:80%"></div>	
	
	<div class="row" style="margin-top:10px;">
		<div class="col-sm-2">
			<div class="form-group">
				<select class="form-control">
					<c:forEach items="${listShippers}" var="shipper">
						<option value="${shipper.SHP_Code}">${shipper.SHP_Code}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<!-- /.col-sm-2 -->
	</div>
	<!-- /.row -->
</div>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAruL-HLFSNh6G2MLhjS-eBTea7r7EFa5A&libraries=places&callback=initialize" async defer></script><script>
function initialize() {
	var mapProp = {
		center: {lat: 21.033333, lng: 105.849998},
		zoom: 13,
		mapTypeId: google.maps.MapTypeId.ROADMAP
	};
	var map=new google.maps.Map(document.getElementById("googleMap"),mapProp);
	
	var lstOrders = ${listOrdersJson};
	var marker = [];
	var infowindow = [];
	
	for(var i=0; i<lstOrders.length; i++){
		var location = new google.maps.LatLng(lstOrders[i].O_DeliveryLat,lstOrders[i].O_DeliveryLng);
		
		marker[i] = new google.maps.Marker({position:location});
		marker[i].setMap(map);
		
		infowindow[i] = new google.maps.InfoWindow({
			content:"Date: "+lstOrders[i].O_DueDate+"<br>Time: "+lstOrders[i].O_TimeLate,
		});
		infowindow[i].open(map,marker[i]);
	}
}
</script>