<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@page import="com.cs336group27.model.*"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Train Administrator</title>
</head>
<body>
<header>
<h1>Welcome to the Train Reservation System #27</h1>
<h2>Administrator Page</h2>
<h3>User: ${employee.getName()}</h3>
</header>
	<form action="adminCustRep.jsp">
		<div class="container" id="custRepAdmin">
			<button type="submit">Customer Representative Administration Tool</button>
		</div>
	</form>
	<br>
	<form action="adminSalesReport.jsp">
		<div class="container" id="monthSales">
			<button type="submit">Monthly Sales Report</button>
		</div>
	</form>
	<br>
	<form action="adminReservationListServlet" method='post'>
		<div class="container" id="resvList">
			<input type="hidden" id = "listType", name = "listType", value = "customer">
			<button type="submit">Reservation List</button>
		</div>
	</form>
	<br>
	<form action="adminRevenueListsServlet" method = 'post'>
		<div class="container" id="revenue">
			<input type="hidden" id = "listType", name = "listType", value = "customer">
			<button type="submit">Revenue Listings</button>
		</div>
	</form>
	<br>
	<form action="adminBestCustomer.jsp">
		<div class="container" id="bestCust">
			<button type="submit">Best Customer</button>
		</div>
	</form>
	<br>
	<form action="adminTransitLines.jsp">
		<div class="container" id="actvTransitLines">
			<button type="submit">Most Active Transit Lines</button>
		</div>
	</form>
	<br>
	<form action="EmployeeLogoutServlet" method="post">
		<div class="container" id="logout">
			<!-- Click button to log out of account -->
			<button type="submit">Logout</button>
		</div>
	</form>
</body>
</html>