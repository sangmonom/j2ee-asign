<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registeration</title>
<jsp:include page="resources/common.jsp"></jsp:include>
</head>
<body>
	<div class="container">
		<jsp:include page="resources/menu.jsp"></jsp:include>
		<div class="row mt-2">
			<div class="col-8">
				<h3>All Registerations</h3>
			</div>
			<div class="col">
				<c:url var="add" value="/reg-add"></c:url>
				<a href="${add }" class="btn btn-success">Add New Regisetration</a>
			</div>


		</div>
		<table class="table mt-2">
			<thead>
				<tr>
					<th>ID</th>
					<th>Student</th>
					<th>Class</th>
					<th>Registeration Date</th>
					<th>Paid Amount</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<!-- registration List -->
				<c:forEach items="${registeration}" var="r">
					<tr>
						<td>${r.id }</td>
						<td>${r.student.name }</td>
						<td>${r.classes.name}</td>
						<td>${r.reg_date }</td>
						<td>${r.paid_amt }</td>
						<td><c:url var="edit" value="/reg-edit">
								<c:param name="regid">${r.id}</c:param>
							</c:url> <a href="${edit}" class="btn btn-outline-success">Edit</a></td>
						<td><c:url var="delete" value="/reg-delete">
								<c:param name="regid">${r.id }</c:param>
							</c:url> <a href="${delete}" class="btn btn-outline-danger">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>