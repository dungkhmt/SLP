<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
	<div class="navbar-header">
		<a href="<c:url value="/"/>"><span class="navbar-brand">Hệ thống quản lý bán hàng</span></a>
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
				<!--  
				<li><a href="<c:url value="${baseUrl}/incomingArticles/list"/>">Nhập hàng</a></li>
				-->
				<li><a href="<c:url value="${baseUrl}/outgoingarticles/list"/>">Nhận đơn hàng đơn lẻ</a></li>
				
				<li><a href="<c:url value="${baseUrl}/outgoingarticles/uploadFile"/>">Nhận đơn hàng qua Excel</a></li>
				<!-- 
				<li><a href="<c:url value="${baseUrl}/ship/createRoute"/>">Lập tuyến giao hàng</a></li>
				<li><a href="<c:url value="${baseUrl}/ship/getRoutes"/>">Xem Route</a></li>
				 -->
				 <li><a href="<c:url value="${baseUrl}/outgoingarticles/createAutoRoute"/>">Lập kế hoạch vận chuyển</a></li>
				 <li><a href="<c:url value="${baseUrl}/outgoingarticles/viewAutoRoute"/>">Xem kế hoạch vận chuyển</a></li>
			</ul>
		</div>
	</div>
</nav>