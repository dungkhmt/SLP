<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
	<div class="navbar-header">
		<a href="<c:url value="/"/>"><span class="navbar-brand">Request Shipper Matching</span></a>
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
				<li><a href="<c:url value="${baseUrl}/requestshippermatching//upload-shipper-matching-request"/>">View request matching</a></li>
				<%-- <li><a href="<c:url value="${baseUrl}/mapstreetmanipulation/directionhome" />">Upload file</a></li> --%>
			</ul>
		</div>
	</div>
</nav>