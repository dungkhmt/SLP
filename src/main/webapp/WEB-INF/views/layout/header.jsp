<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row">
	<div class="col-md-5">
	<h1>
		Welcome to MSOLab
	</h1>
	</div>
	<c:if test="${currentUser != null}">		
		<div class="col-md-2 col-md-offset-5" style="margin-top:30px;">
			<span>
				Hello, ${currentUser.username}		
			</span>
			<a class="btn btn-primary" href="<c:url value="/j_spring_security_logout" />">Logout</a>
		</div>
	</c:if>
</div>