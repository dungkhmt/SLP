<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12 center">
            <h1 class="page-header">Chỉnh sửa điểm nút bản đồ</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
    	<div id="googleMap" style="width:100%;height:100%"></div>
    	<div class="panel panel-default">
    		<div class="panel-body">
    			<div class="form-group">
    				<div class="col-sm-3">
						<select class="form-control" id="select-listProvince">
							<c:forEach items="${lstProvinces}" var="province">
								<option value="${province.PROV_Code}"><c:out value="${province.PROV_Name}"/></option>
							</c:forEach>
						</select>
					</div>
				</div>
				<button type="button" class="btn btn-primary active" onclick="getListInRectangle()" >View</button>
				
    		</div>
    	</div>
    </div>
</div>
<!-- /#page-wrapper -->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAruL-HLFSNh6G2MLhjS-eBTea7r7EFa5A&libraries=places&callback=initialize" async defer></script>
<script>
var map;
var rectangle;
function initialize() {
	//construct google map
	var mapProp = {
		center: {lat: 21.033333, lng: 105.849998},
		zoom: 12,
		mapTypeId: google.maps.MapTypeId.ROADMAP
	};
	map=new google.maps.Map(document.getElementById("googleMap"),mapProp);
	var bounds = {
	          north: 21.0,
	          south:  21.5,
	          east: 106.3,
	          west: 105.0
	        };
	rectangle = new google.maps.Rectangle({
        bounds: bounds,
        editable: true,
        draggable: true
      });
	rectangle.setMap(map);
	console.log(rectangle);
}
function getListInRectangle(){
	var ne = rectangle.getBounds().getNorthEast();
    var sw = rectangle.getBounds().getSouthWest();
    var json=[ {
    		lat : ne.lat(),
    		lng : ne.lng()
    	}, {
    		lat: sw.lat(),
    		lng: sw.lng()
    		}];
    console.log(json);
    json=JSON.stringify(json);
	$.ajax({
		type: 'POST',
		url: baseUrl+'/mapstreetmanipulation/get-point-segment-in-range',
		data: json,
		contentType: 'application/json',
		success: function(response){
			
		
		}
	});
}
</script>