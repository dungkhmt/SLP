<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2> Add member</h2>

<form id="form" class="form-horizontal" method="POST" action="/hellospring/member/saveAdd">
<div class="row" style="margin-top:20px;">                   
	<div class="form-group">
    	<div class="col-md-1 col-md-offset-1">
        	<label for="Name">Name</label>
        </div>
        <div class="col-md-5">
            <input path="name" id="name" class="form-control" name="name" type="text">
        </div>
    </div>
</div>
<div class="row">                   
	<div class="form-group">
    	<div class="col-md-1 col-md-offset-1">
        	<label for="Role">Role</label>
        </div>
        <div class="col-md-5">
        	<select path="role" id="role" class="form-control" name="role" type="text">
        		<option value="professor">Professor</option>
        		<option value="professor">Student</option>
            </select>
            <span id="idName" class="message"></span>
        </div>
    </div>
</div>
<div class="row">                   
	<div class="form-group">
    	<div class="col-md-2 col-md-offset-2">
        	<div class="btn btn-primary" id="save_button">Save</div>
        	<div class="btn btn-primary" id="cancel_button">Cancel</div>
        </div>        
    </div>
</div>
</form>
<script>
$("#save_button").click(function () {    
	$("#form").submit();  
});
$("#cancel_button").click(function () {    
	window.location = '${baseUrl}'+"/member.html";   
});
	
	
</script>

