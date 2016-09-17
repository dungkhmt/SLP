
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> 
<html>
<head>
	<title><tiles:insertAttribute name="title"/></title>
	
	<!-- Bootstrap Core CSS -->
	<link href="<c:url value="/assets/libs/bootstrap/dist/css/bootstrap.css" />" rel="stylesheet" type="text/css" media="all" />
	<!-- Custom Fonts -->
	<link href="<c:url value="/assets/libs/font-awesome/css/font-awesome.css" />" rel="stylesheet" type="text/css" media="all">
	
	<!-- jQuery -->
   	<script src="<c:url value="/assets/libs/jquery/dist/jquery.js"/>"></script>
   	
   	<!-- Bootstrap Core JavaScript -->
	<script src="<c:url value="/assets/libs/bootstrap/dist/js/bootstrap.min.js"/>"></script>
   	    	
</head>
 
<body>
	<div class="container">
    <div class="row">
        <div class="col-lg-12 center">
            <h1 class="page-header">Hệ thống quản lý bán hàng</h1>
            <h2>Appications</h2>
            <div class="card">
  				<h3 class="card-header">Featured</h3>
  				<div class="card-block">
  					<h4 class="card-title">Special title treatment</h4>
    				<p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
    				<a href="#" class="btn btn-primary">Go somewhere</a>
  				</div>
			</div>
            <figure class="card">
  				<img src="<c:url value="/assets/img/home/container.jpg"/>" class="figure-img img-fluid img-rounded" alt="A generic square placeholder image with rounded corners in a figure."/>
  				<figcaption class="figure-caption text-xs-right">A caption for the above image.</figcaption>
			</figure>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
	</div>
	<!-- /#page-wrapper -->	
	<script type="text/javascript">
		var baseUrl = '${baseUrl}';
		var assetsUrl = '${assetsUrl}';
	</script>
</body>
</html>