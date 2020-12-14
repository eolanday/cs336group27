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
<h2>Customer Homepage</h2>
<body>
	<br>
	<br>
	<h3>Would you like to make a reservation?</h3>
	<!-- Make a Reservation -->
	<form action="CusMakeReservation.jsp" method = "post">
		<div class="container" id="makeResButton">
			<button type="submit">Make Reservation</button>
		</div>
	</form>
	<br>
	<br>
	<h3>View Your Reservation Portfolio</h3>
	<!-- Reservation Portfolio-->
	<form action="ViewPortfolio" method = "post">
		<div class="container" id="viewPortfolioButton">
			<input type = "hidden" id = "user" name = "user" value = ${cust.getName()} >
			<button type="submit">View Portfolio</button>
		</div>
	</form>
	<br>
	
	<br>
		
	<h3>Search for train schedules!</h3>
<<<<<<< HEAD
	<br>
	<form action="scheduleSearchServlet" method='post'>
=======
	<form action="scheduleSearchServlet.java" method='post'>
>>>>>>> bdb201bd73db4ec26444cbd02e9b94840ef01b03

		Origin: <input type="text" name="origin">
		<br />
		Destination: <input type="text" name="destination">
		<br />
		Travel Date: <input type="text" name="travelDate" placeholder="yyyy-mm-dd">
		<br /> 
			<input type="radio" id="sortFare" name="sortFare" value="sortFare">
		  	<label for="sortFare">Sort by Fare</label>
		<br/>
			<input type="radio" id="sortArrival" name="sortArrival" value="sortArrival">
		  	<label for="sortArrival">Sort by Arrival Time</label>
	  	<br/>
		  	<input type="radio" id="sortDeparture" name="sortDeparture" value="sortDeparture">
		  	<label for="sortDeparture">Sort by Departure Time</label>
	  	<br/>
		<input type = "submit" value = "Submit" />
	</form>		
	
	<br>
	<br>
	<form action="LogoutServlet" method="post">
		<div class="container" id="logout">
			<!-- Click button to log out of account -->
			<button type="submit">Logout</button>
		</div>
	</form>
</body>
</html>