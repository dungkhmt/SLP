<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form class="form login-form" action="<c:url value="/j_spring_security_check" />" method="post">
<c:if test="${status != null}">

<div class="alert-success">
	${status}
</div>
</c:if>
<div class="row" style="margin-top:20px;">                   
	<div class="form-group">
    	<div class="col-md-1 col-md-offset-1">
        	<label for="Name">Username</label>
        </div>
        <div class="col-md-4">
            <input type="text" class="form-control" name="j_username" id="j_username" value="${param.j_username}" placeholder="username"/>
        </div>
        <div class="col-md-6">
    		<span style="color:red;">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</span>
    	</div>
    </div>
</div>
<div class="row">                   
	<div class="form-group">
    	<div class="col-md-1 col-md-offset-1">
        	<label for="Role">Password</label>
        </div>
        <div class="col-md-4">
        	<input type="password" class="form-control" name="j_password" id="j_password" value="${param.j_password }" placeholder="password"/>
        </div>
    </div>
</div>

<div class="row">                   
	<div class="form-group">
    	<div class="col-md-10 col-md-offset-2" style="margin-top:10px;">
        	<button type="submit" class="btn btn-success" value="submit">Login</button>
        	<span>If you don't have an account, <a href="${baseUrl}/auth/register.html">Register</a></span>
        </div>        
    </div>
</div>
</form>
<c:if test="${status != null}">
<script>
$(document).ready(function() {
	$("#j_username").val('${username}');
	$("#j_password").val('${password}');	
	
});
</script>
</c:if>