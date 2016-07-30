<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2> List of Msolab members</h2>
	<table class="table table-striped table-bordered table-hover" id="example">
			<thead>
				<tr>
					<th title="Role">Role</th>
					<th title="Name">Name</th>
					<th title="Utility"><div class="btn btn-primary" id="add_button">Add member</div></th>	
																		
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listMembers}" var="member">				
					<tr class="gradeX">					
						 <td><c:if test="${member.role == 'professor'}"> Professor </c:if>
							<c:if test="${member.role == 'student'}"> Student </c:if> </td>
						 <td>${member.name}</td>
						 <td><div class="btn btn-danger" onclick="deleteMember(${member.id})">Delete</div></td> 
						 
					</tr>
						 
				</c:forEach>
			</tbody>
		</table>
					
<script type="text/javascript" src="https://cdn.datatables.net/r/bs-3.3.5/jqc-1.11.3,dt-1.10.8/datatables.min.js"></script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#example').DataTable();
		$("#add_button").click(function () {            
			window.location = '${baseUrl}'+'/member/add.html';			
	    });	
	});
	function deleteMember(id){
		window.location = '${baseUrl}'+'/member/delete/'+id+'.html';
	}
</script>