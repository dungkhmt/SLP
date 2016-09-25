<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div id="page-wrapper">
    <form action="${baseUrl}/outgoingarticles/callServiceCreateRoute" method="POST" role="form">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Lập tuyến giao hàng tự động</h1>
		</div>
		<div class="col-sm-4">
			<div class="form-group">
				<select class="form-control">
					<option>Chọn Batch</option>
					<c:forEach items="${lstreBatch}" var="reBatch">
						<option value="${reBatch.REQBAT_Code}"><c:out value="${reBatch.REQBAT_Code}"/></option>
					</c:forEach>
				</select>
			</div>
		</div>
		<button type="submit" class="btn btn-primary">Lập tuyến</button>
  	</form>
 </div>
 