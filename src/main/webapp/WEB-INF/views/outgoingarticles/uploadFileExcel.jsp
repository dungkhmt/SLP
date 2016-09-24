<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>F-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link href="<c:url value="/assets/libs/fileinput/css/fileinput.min.css" />" media="all" rel="stylesheet" type="text/css">
	
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Upload danh sách hóa đơn</h1>
            <input id="input-file" name="timetable[]" type="file" multiple=true class="file-loading" />
        </div>
        <!-- /.col-lg-12 -->
    </div>
 </div>
 
<script src="<c:url value="/assets/libs/fileinput/js/fileinput.min.js"/>"></script>
<script>
$(document).ready(function(){
	 $("#input-file").fileinput({
	 	uploadUrl: "uploadOrdersFile.html",
		uploadAsync: true,
		allowedFileExtensions:['xlsx'],
		maxFileCount: 1
	  });
 });
 </script>