<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Lập tuyến giao hàng ngày <span id="tile_routeDate"></span></h1>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-2">
			<div class="form-group">
				<select class="form-control" id="select-listOrderDate">
				</select>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-8" style="padding:0px;">
			<div id="googleMap" style="width:100%;height:500"></div>	
		</div>
		<div class="col-lg-4" style="padding:0px;">
			<div class="panel panel-default" style="width:350px;" id="routeDetailPanel">
				<div class="panel-body" style="padding:5px;min-height:500;max-height:500;overflow-y:scroll;">
					<table class="table table-bordered" id="tblRouteDetail">
						<thead>
							<tr>
								<th>Tên KH</th>
								<th>T/g dự kiến</th>	
								<th>T/g yêu cầu</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /#routeDetailPanel -->
		</div>
	</div>
	<!-- /.row(googlemap+panel) -->
	<div class="row" style="margin-top:0px;">
		<div class="col-sm-2">
			<div class="form-group">
				<select class="form-control" id="lstShippers">
				</select>
			</div>
		</div>
		<!-- /.col-sm-2 -->
		<div class="col-sm-1" style="padding:0px" id="divTimeStartOfShipper">
		</div>
		<div class="col-sm-1">
			<button class="btn btn-info" value="change" id="btnChangeTime">Change</button>
		</div>
		<div class="col-sm-1">
			<button class="btn btn-warning" id="btnResetRoute" onclick="btnResetRoute_cf();">Reset</button>
		</div>
		<!-- /.col-sm-1 -->
		<div class="col-sm-1">
			<button class="btn btn-warning" id="btnRemove" value="cancel">Xóa</button>
		</div>
		<!-- /.col-sm-1 -->
		
		<div class="table-reponsive">
			<table class="table table-bordered table-hover" id="tblRouteOfShippers">
				<thead>
					<tr>
						<th>Người giao hàng</th>
						<th>Các địa điểm</th>
						<th>Tổng quãng đường</th>
						<th>Tổng thời gian</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		<!-- /.table-reponsive -->
		<button class="btn btn-primary" id="saveRouteCreated">Lưu</button>
		<button class="btn btn-primary btnCancelCreateRoute">Hủy</button>
	</div>
	<!-- /.row -->
</div>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAruL-HLFSNh6G2MLhjS-eBTea7r7EFa5A&libraries=places&callback=initialize" async defer></script>
<script src="${assetsUrl}/js/source/shippingmanagement/createRoute.js"></script>