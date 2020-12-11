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
</body>
</html>