<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336group27.model.Customer"%>  

<%--Customer custer =(Customer)htpSession.getAttribute("cust");--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome!</title>
</head>

<!-- Welcome screen for successful customer logins -->
<h1>Welcome ${cust.getName()}!</h1>
<body>

	<br>
	<p>Would you like to make a reservation?</p>
	<!-- Make a Reservation -->
	<form action="CusMakeReservation.jsp">
		<div class="container" id="makeResButton">
			<button type="submit">Make Reservation</button>
		</div>
	</form>
	<br>
	
	<p>Would you like to cancel a reservation?</p>
	<!-- Cancel a Reservation -->
	<form action="CusDeleteReservation" method = "post">
		<div class="container" id="cancelResButton">
			<button type="submit">Cancel Reservation</button>
		</div>
	</form>
	<br>
	
	<h3>View Your Reservation Portfolio</h3>
	<!-- Reservation Portfolio-->
	<form action="ViewPortfolio" method = "post">
		<div class="container" id="viewPortfolioButton">
			<button type="submit">View Portfolio</button>
		</div>
	</form>
	<br>
	
	<br>
	<form action="LogoutServlet" method="post">
		<div class="container" id="logout">
			<!-- Click button to log out of account -->
			<button type="submit">Logout</button>
		</div>
	</form>
		
	<h3>Search for train schedules!</h3>
	<br>
	<form action="scheduleSearchServlet" method='post'>
		<div>
			<input type="radio" id="origin" name="searchType" value="origin">
			<label for="origin">Origin</label>
		</div>
		<div>
			<input type="radio" id="destination" name="searchType" value="destination">
			<label for="destination">Destination</label>
		</div>
		<div>
			<input type="radio" id="travelDate" name="searchType" value="travelDate">
			<label for="origin">Travel Date</label>
		</div>
			<input type="submit" value="Reorder">
			
		<div>
			<input type="text" name="searchParam" class="form-control" placeholder="Search by * select above *">
			<!-- Click button to start search -->
			<button type="submit" name="save" class="btn btn-primary">Search</button>
		</div>
	</form>
		
		

</body>
</html>