<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h2> Add member</h2>

<form:form id="form" class="form-horizontal" method="POST" commandName="userForm" action="${baseUrl}/auth/register">
<div class="row" style="margin-top:20px;">                   
	<div class="form-group">
    	<div class="col-md-1 col-md-offset-1">
        	<label for="Name">Username</label>
        </div>
        <div class="col-md-5">
            <form:input path="username" id="username" class="form-control" name="username" type="text"></form:input>
            <span style="color:red;"><form:errors path="username"></form:errors></span>
        </div>
        <div class="col-md-5">
        	<span style="color:red;">${status}</span>
        </div>
    </div>
</div>
<div class="row">                   
	<div class="form-group">
    	<div class="col-md-1 col-md-offset-1">
        	<label for="Name">Password</label>
        </div>
        <div class="col-md-5">
            <form:input path="password" id="password" class="form-control" name="password" type="password"></form:input>
            <span style="color:red;"><form:errors path="password"></form:errors></span>
        </div>
    </div>
</div>
<div class="row">                   
	<div class="form-group">
    	<div class="col-md-1 col-md-offset-1">
        	<label for="Name">Retype password</label>
        </div>
        <div class="col-md-5">
            <form:input path="repassword" id="repassword" class="form-control" name="repassword" type="password"></form:input>
            <span style="color:red;"><form:errors path="repassword"></form:errors></span>
        </div>
    </div>
</div>

<div class="row">                   
	<div class="form-group">
    	<div class="col-md-2 col-md-offset-2">
        	<div class="btn btn-success" id="save_button">Register</div>
        	<div class="btn btn-primary" id="cancel_button">Back</div>
        </div>        
    </div>
</div>
</form:form>
<script>
$(document).ready(function() {
	$("#username").val('${username}');
	$("#password").val('${password}');
	$("#repassword").val('${repassword}');
});

$("#save_button").click(function () {    
	$("#form").submit();  
});
$("#cancel_button").click(function () {    
	window.location = '${baseUrl}'+"/auth/login.html";   
});
	
	
</script>

