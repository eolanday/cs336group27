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
	<form action="testServlet" method = "post">
		<div class="container" id="viewPortfolioButton">
			<input type = "hidden" id = "user" name = "user" value = ${cust.getName()}>
			<input type = "hidden" id="future" name="switch" value="future">
			<button type="submit">View Portfolio</button>
		</div>
	</form>
	<br>
	ß
	<h4>Have an issue? Browse or ask a question.</h4>
	<!-- Cancel a Reservation -->
	<form action="custQuestion.jsp">
		<div class="container" id="questionB">
			<button type="submit">Browse and Ask Questions</button>
		</div>
	</form>
	<br>
	
	<br>
		
	<h3>Search for train schedules!</h3>

	<br>
	<form action="scheduleSearchServlet" method='post'>

		Origin: <input type="text" name="origin" placeholder="*City* Station">
		<br />
		Destination: <input type="text" name="destination" placeholder="*City* Station">
		<br />
		Travel Date: <input type="text" name="travelDate" placeholder="yyyy-mm-dd">
		<br /> 
			<input type="radio" id="fare" name="sortType" value="fare">
		  	<label for="fare">Sort by Fare</label>
		<br/>
			<input type="radio" id="arrival_time" name="sortType" value="arrival_time">
		  	<label for="arrival_time">Sort by Arrival Time</label>
	  	<br/>
		  	<input type="radio" id="departure_time" name="sortType" value="departure_time">
		  	<label for="departure_time">Sort by Departure Time</label>
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
