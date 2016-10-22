<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
	<div class="navbar-header">
		<a href="<c:url value="/"/>"><span class="navbar-brand">Hệ thống quản lý vận chuyển container</span></a>
	</div>
	
	<ul class="nav navbar-top-links navbar-right">
		<li class="dropdown">
			<a class="drpdown-toggle" data-toggle="dropdown" href="#">
				${currentUser.username}
				<i class="fa fa-user fa-fw"></i>
				<i class="fa fa-caret-down"></i>
			</a>
			<ul class="dropdown-menu dropdown-user">
				<li><a href="<c:url value="/j_spring_security_logout" />"><i class="fa fa-sign-out fa-fw"> Đăng xuất</i></a></li>
			</ul>
		</li>
	</ul>

	<div class="navbar-default sidebar" id="sidebar" role="navigation">
		<div class="sidebar-nav navbar-collapse">
			<ul	class="nav" id="side-menu">
				
				<li><a href="<c:url value="${baseUrl}/containerdelivery/list-pickupdelivery-order"/>">Xem danh sách hóa đơn chuyển hàng</a></li>
				<li><a href="<c:url value="${baseUrl}/containerdelivery/add-pickupdelivery-orders-by-xls"/>">Nhập đơn hàng qua file excel</a></li>
				<li><a href="<c:url value="${baseUrl}/containerdelivery/create-route-auto"/>">Lập tuyến tự động</a></li>
				<%-- <li><a href="<c:url value="${baseUrl}/ship/create-pickupdelivery-route"/>">Lập tuyến giao hàng container</a></li> --%>
				<li><a href="<c:url value="${baseUrl}/containerdelivery/view-route-detail-container"/>">Xem cac route</a></li>
			</ul>
		</div>
	</div>
</nav>