<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="<c:url value="/assets/libs/jquery-ui-1.12.0/jquery-ui.js"/>"> </script>
<link href="<c:url value="/assets/libs/bootstrap-datepicker/css/bootstrap-datepicker.css" />" rel="stylesheet" type="text/css" media="all" />
<script src="<c:url value="/assets/libs/bootstrap-datepicker/js/bootstrap-datepicker.js"/>"></script>
<link href="<c:url value="/assets/libs/bootstrap-timepicker/css/bootstrap-timepicker.css" />" rel="stylesheet" type="text/css" media="all" />

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
<script src="<c:url value="/assets/libs/bootstrap-timepicker/js/bootstrap-timepicker.js"/>"></script>

<script src="<c:url value="/assets/js/source/shippingmanagement/selectAddress.js"/>"> </script>
<link href="<c:url value="/assets/css/source/shippingmanagement/selectAddress.css" />" rel="stylesheet" type="text/css" media="all" />
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDEXgYFE4flSYrNfeA7VKljWB_IhrDwxL4&libraries=places&callback=initAutocomplete" async defer></script>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Thêm mới hóa đơn</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<form:form action="${baseUrl}/outgoingarticles/save-an-order.html" method="POST" commandName="orderFormAdd" role="form" class="form-horizontal">
	<div class="row">
		<div class="col-sm-3">
			<div class="form-group">
				<div>
					<select class="form-control" name="batchCode" path="batchCode">
						<option>Chọn batch</option>
						<c:forEach items="${lstreBatch}" var="batch">
							<option value="${batch.REQBAT_Code}"><c:out value="${batch.REQBAT_Code}"></c:out></option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="panel panel-default">
			
			<div class="panel-heading">
					Thêm mới hóa đơn khách hàng
			</div>
			
		
		<div class="panel-body">
					<h4>${error}</h4>
					<div class="form-group">
						<label class="control-label col-lg-2 phoneinput">Số điện thoại</label>
						<div class="col-lg-6">
						<div class="ui-widget">
						<form:input path="orderClientCode" id="inputSearch" class="form-control" name="orderClientCode" placeholder="Number Phone"></form:input>
						</div>
						</div>
						<button type="button" class="btn btn-primary active saveAClientButton" onclick="pushPhoneModal()" data-toggle="modal" data-target="#saveAClient">Thêm Khách Hàng</button>
					</div>
					<div class="form-group">
						<label class="control-label col-lg-2">Tên Khách Hàng</label>
						<div class="col-lg-6">
						<form:input path="orderClientName" class="form-control" id="orderClientName" name="orderClientName" placeholder="Client Name"></form:input>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2">Địa chỉ</label>
						<div class="col-lg-6">
						<form:input path="orderAdress" class="form-control" id="orderAdress" name="orderAdress" placeholder="Adress"></form:input>
						</div>
						<button type="button" class="btn btn-primary active" onclick="enableSelectMap()"><span class="glyphicon glyphicon-map-marker"></span></button>
						<button type="button" class="btn btn-primary active ok-selectMap" onclick="pushValueLatLng()">OK</button>
						
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2">Thông tin tọa độ</label>
						<div class="col-lg-6">
						
							<div class="col-lg-3 orderDeliveryLat">
							<label class="control-label col-sm-1">Lat:</label>
							<form:input path="orderDeliveryLat" class="form-control" id="orderDeliveryLat" name="orderDeliveryLat" ></form:input>
							</div>
						
							<div class="col-lg-3 orderDeliveryLng">
							<label class="control-label col-sm-1 orderDeliveryLng">Lng:</label>
							<form:input path="orderDeliveryLng" class="form-control" id="orderDeliveryLng" name="orderDeliveryLng" ></form:input>
							</div>
						</div>
						
					</div>
					<div class=" selectMap">
						<label class="selectMapLabel Ilabel"></label>
						<input id="pac-input" class="controls" type="text" placeholder="Search Box">
						<div id="map" style="width:100%;height:80%"></div>
    				</div>
    				
					<div class="form-group">
						<label class="control-label col-lg-2">Ngày giao hàng</label>
						<div class="col-lg-2">
						<form:input  path="orderDate" class="form-control datepicker" name="orderDate" placeholder="Date" ></form:input>
						</div>
						<label class="control-label col-lg-1">Từ:</label>
						<div class="col-lg-2">
						<form:input  path="orderTimeEarly" class="form-control timepicker" name="orderTimeEarly" placeholder="TimeEarly" ></form:input>
						</div>
						<label class="control-label col-sm-1">Đến:</label>
						<div class="col-lg-2">
						<form:input  path="orderTimeLate" class="form-control timepicker" name="orderTimeLate" placeholder="TimeLate" ></form:input>
						</div>
					</div>
		</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
					Thêm các mặt hàng
			</div>
			<div class="panel-body">
				<div class="form-group ">
					<label class="control-label col-sm-2" for="categoryArticle">Chọn mặt hàng</label>
					<div class="col-sm-2">
						<select class="form-control categoryArticle" name="categoryArticle" >
							<option value="">Chọn mặt hàng</option>
							<c:forEach items="${listArticleCategory}" var="article">
                                     	<option value="${article.ARCat_Code}">${article.ARCat_Name}</option>
                            </c:forEach>
                    
                    	</select>
                    </div>
				</div>	
				
				<div class="form-group ">
					<label class="control-label col-sm-2 articleAmount">Số lượng</label>
					<div class="col-sm-2">
						<input class="form-control" name="amount" placeholder="" ></input>
                    </div>
				</div>	
				
				<div class="form-group ">
					<label class="control-label col-sm-2">Giá</label>
					<div class="col-sm-2">
						<input class="form-control articlePrice" name="price" placeholder="" ></input>
                    </div>
				</div>
				
				<button type="button" class="btn btn-primary btn-xs" onclick="v_fAddOrderArticle();">Thêm</button>
			</div>
		
		<div class="panel-body">
			<div class="panel panel-default">
				<div class="panel-heading">
	                  Hóa đơn
	            </div>
	            <div class="panel-body">
	            	<div class="table-responsive">
	            		<table class="table table-striped table-bordered table-hover" id="listOrderArticles">
	            			<thead>
	            			<tr>
	                        	<th>Mặt hàng</th>
	                            <th>Số lượng</th>
	                            <th>Giá/Đơn vị</th>
	                            <th>Xóa</th>
	                        </tr>
	                        </thead>
	                        <tbody>
	                        	<tr class="no-records-found">
	                            	<td colspan="6" align="center">Chưa có mặt hàng</td>
	                            </tr>
	                        </tbody>
	            		</table>
	            	</div>
	            	<div class="row">
	            	<div class="col-sm-offset-7 col-sm-5">
	            		<div class="form-group text-center">
							<h4 >Tổng giá trị hóa đơn là: <b><span id="price" class=""></span></b></h4>
							<form:input type="hidden" path="orderPrice"  id="orderPrice" name="orderPrice" ></form:input>
						</div>
	            	</div>
	            	</div>
	        	</div>
			</div>
		</div>
		</div>
		
		<button type="submit" class="btn btn-primary" id="addANewOrder">Lưu</button>
        <button type="button" class="btn btn-primary cancel" onclick="goBack();">Hủy</button>
	</div>
	</form:form>

			
</div>
			
<div id="saveAClient" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Nhập thông tin khách hàng</h4>
      </div>
      <div class="modal-body">
      
      <div class="row">
      <div class="panel panel-default">
      <div class="panel-body">
      	<div class="form-group">
			<label class="control-label col-lg-4">Số điện thoại</label>
			<div class="col-lg-6">
				<input class="form-control" type="text" id="clientPhone" name="clientPhone" placeholder="Phone"></input>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-4">Tên khách hàng  </label>
			<div class="col-lg-6">
				<input class="form-control" type="text" id="clientName" name="clientName" placeholder="Name"></input>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-4">Địa chỉ </label>
			<div class="col-lg-6">
				<input class="form-control" type="text" id="clientAddress" name="clientAddress" placeholder="Address"></input>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-4">Email</label>
			<div class="col-lg-6">
				<input class="form-control" type="text" id="clientEmail" name="clientEmail" placeholder="Email"></input>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-lg-4">FaceBook Account</label>
			<div class="col-lg-6">
				<input class="form-control" type="text" id="clientFacebook" name="clientFacebook" placeholder="Facebook"></input>
			</div>
		</div>
		
		</div>
		</div>
      </div>
      <div class="modal-footer">
     	<button type="button" class="btn btn-primary active" onclick="saveAClient()">Save</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
	</div>
  </div>
</div>
<!-- Javascript -->
<script>
	$(function() {
        $( ".datepicker" ).datepicker({
        		format: 'yyyy-mm-dd'
        		});
        
    });
	$("#pac-input").keyup(function(){
		$("#orderAdress").val($("#pac-input").val());		
	});

	$('.timepicker').timepicker({
		showMeridian: false,
        showSeconds: true,
		//timeFormat : 'HH:mm:ss'
	});
	$(".selectMap").hide();
	$(".ok-selectMap").hide();
	$(".orderDeliveryLat").hide();
	$(".orderDeliveryLng").hide();
	$(".saveAClientButton").hide();
</script>
<script type="text/javascript">
function goBack(){
	window.location = '${baseUrl}/outgoingarticles/list';
}
function pushPrice(){
	var price=0;
	var table = document.getElementById('listOrderArticles'), 
	rows = table.getElementsByTagName('tr');
	for (i = 0 ; i < rows.length; ++i) {
	    cells = rows[i].getElementsByTagName('td');
	    if (!cells.length) {
	        continue;
	    }
	    price += cells[1].innerHTML*cells[2].innerHTML;
	}
	console.log(price);
	$("#orderPrice").val(price);
	var sprice = ""+price;
	var printedPrice = "";
	var check =0;
	for(var i=sprice.length-1;i>=0; i--){
		printedPrice = sprice[i] + printedPrice;
    	check++;
    	if(check==3){
    		printedPrice = "." + printedPrice;
    		check = 0;
    	}
    }
	if(sprice.length % 3 == 0){
		printedPrice = printedPrice.substring(1);
    }
	document.getElementById("price").innerHTML=(printedPrice+" VNĐ");
	
}
function pushPhoneModal(){
	$("#clientPhone").val($("#inputSearch").val());
}
function saveAClient(){
	console.log("saveAClient");
	var phone =$("#clientPhone").val();
	var name=$("#clientName").val();
	var address=$("#clientAddress").val();
	var facebook=$("#clientFacebook").val();
	var email=$("#clientEmail").val();
	var jsonx={
		"phone":phone,
		"name":name,
		"address":address,
		"facebook":facebook,
		"email":email,
	};
	console.log(jsonx);
	$.ajax({ 
	    type:"POST", 
	    url:"${baseUrl}/cm/save-A-Client",
	    data: JSON.stringify(jsonx),
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    //Stringified Json Object
	    success: function(respose){
	        // Success Message Handler
	      console.log(respose);
	        if(respose){
	        	$( "#orderClientName" ).val( name );
	        	$( "#orderAdress" ).val( address );
	        	$("#saveAClient").modal('hide');
	        }
	    }
    });
	
	console.log(phone);
}
function pushValueLatLng(){
	$(".orderDeliveryLat").show();
	$(".orderDeliveryLng").show();
	$("#orderDeliveryLat").val(getLat());
	$("#orderDeliveryLng").val(getLng());
	$( "#orderAdress" ).val($("#pac-input").val());
	console.log($("#orderAdress").val());
	$(".selectMap").hide();
	$(".ok-selectMap").hide();
}
function enableSelectMap(){
	console.log("enableSelectMap");
	google.maps.event.trigger(map, 'resize');
	if ($(".selectMap").is(":visible")){
	$(".selectMap").hide();
	$(".ok-selectMap").hide();
	}
	else{ 
		$(".selectMap").show();
		$(".ok-selectMap").show();
	}
}
function v_fAddOrderArticle(){
	var categoryArticle= $("select.categoryArticle").find(":selected").val();;
	var articleAmount= document.getElementsByName("amount")[0].value;
	var articlePrice=document.getElementsByName("price")[0].value;
	var sAddedArticle="";
	if(categoryArticle != "" && articleAmount != "" && articlePrice!=""){
		// Remove no records found column
		$("table#listOrderArticles tbody tr.no-records-found").remove();
		//make orderArticleRecord
		sAddedArticle+="<tr>";
		sAddedArticle+="<td><span>"+categoryArticle+"</span><input name='orderArticles' type='hidden' value='"+categoryArticle+ " "+articleAmount+" "+articlePrice+" "+"'/></td>";
		sAddedArticle+="<td>"+articleAmount+"</td>";
		sAddedArticle+="<td>"+articlePrice+"</td>";
		sAddedArticle 	+= "<td><button type='button' onclick='v_fClearOrderArticle(this);' class='btn btn-warning btn-xs' title='Xóa hàng này' >Xóa</button></td>";
		sAddedArticle+="</tr>";
		//push orderArticle in table
		$("table#listOrderArticles tbody").append(sAddedArticle);
	}
	pushPrice();
}	

function v_fClearOrderArticle(the_oElement){
	$(the_oElement).parents("tr").remove();
	pushPrice();
}

$( function() {
	console.log("start");

    	$( "#inputSearch" ).autocomplete({
    		source: function( request, response ) {
    			var input= document.getElementById("inputSearch").value;
    			console.log(input);
    			var jsonx={
    		    		"inputString":input
    		    };
    	    	$.ajax({ 
    	    	    type:"POST", 
    	    	    url:"${baseUrl}/clientSearch-byPhone",
    	    	    data: JSON.stringify(jsonx),
    	    	    contentType: "application/json; charset=utf-8",
    	    	    dataType: "json",
    	    	    //Stringified Json Object
    	    	    success: function(resposeJsonObject){
    	    	        // Success Message Handler
    	    	        if((resposeJsonObject.length==0) || (resposeJsonObject==null)) $(".saveAClientButton").show();
    	    	        else {
    	    	        	$(".saveAClientButton").hide();
    	    	        }
    	    	        console.log(resposeJsonObject);
    	    	        availableTags=[
    	    	                       ];
    	    	        for(var i=0;i<resposeJsonObject.length;i++){
    	    	        	availableTags.push({
    	    	        		"label": resposeJsonObject[i].phone,
    	    	        		"value": resposeJsonObject[i].phone,
    	    	        		"value1":resposeJsonObject[i].adress,
    	    	        		"value2":resposeJsonObject[i].name
    	    	        		
    	    	        	});
    	    	        	
    	    	        }
    	    	        response(availableTags);
    	    	    }
    	        });
    	    },
    	    select: function(event, ui) {
    	        //assign value back to the form element
    	    	$( "#orderClientName" ).val( ui.item.value2 );
    	    	$( "#orderAdress" ).val( ui.item.value1 );
    	    }
    
    	});
    	
   
  //} );
});

</script>

