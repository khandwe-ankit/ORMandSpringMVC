<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<title>Customer Directory</title>

<script>
function clearSearch(){
	var inputs = document.getElementsByTagName('input')
	for(var i=0;i<inputs.length;i++)
        inputs[i].value = '';
	document.querySelector('.clear-search').addEventListener("click", function(event){
		  event.preventDefault();})
}
</script>

</head>

<body>

	<div class="container">

		<h3>Customer Details</h3>
		<hr>

		<!-- Add a search form -->

		<form action="/GlobalCRM/customers/search" class="form-inline">

			<!-- Add a button -->
			<a href="/GlobalCRM/customers/add"
				class="btn btn-primary btn-sm mb-3"> Add Customer </a> 
				<input type="search" name="firstName" placeholder="First Name"
				value="${ searchedCustData.firstName}"
				class="form-control-sm ml-5 mr-2 mb-3 input" /> 
				<input type="search" name="lastName" placeholder="Last Name"
				value="${ searchedCustData.lastName}"
				class="form-control-sm mr-2 mb-3 input" /> 
				<input type="search" name="email" placeholder="Email" 
				value="${ searchedCustData.email}"
				class="form-control-sm mr-2 mb-3 input" />
			<button style="display: block;" class="btn btn-primary btn-sm mb-3 mr-1 clear-search" onclick=clearSearch()>Clear</button>
			<button type="submit" class="btn btn-success btn-sm mb-3">Search</button>

		</form>

		<table class="table table-bordered table-striped">
			<thead class="thead-dark">
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${customers}" var="tempCustomer">
					<tr>
						<td><c:out value="${tempCustomer.firstName}" /></td>
						<td><c:out value="${tempCustomer.lastName}" /></td>
						<td><c:out value="${tempCustomer.email}" /></td>
						<td>
							<!-- Add "update" button/link --> <a
							href="/GlobalCRM/customers/update?id=${tempCustomer.id}"
							class="btn btn-info btn-sm"> Update </a> <!-- Add "delete" button/link -->
							<a href="/GlobalCRM/customers/delete?id=${tempCustomer.id}"
							class="btn btn-danger btn-sm"
							onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">
								Delete </a>

						</td>

					</tr>
				</c:forEach>

			</tbody>
		</table>

	</div>

</body>
</html>



