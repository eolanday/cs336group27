<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    <%@page import="com.cs336group27.model.*"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Representative</title>
</head>
<h1>Welcome to the Train Reservation System #27</h1>
<h2>Customer Representative Page</h2>
<h3>User: ${employee.getName()}</h3>
<body>
	<form action="custRepTrainSchedule.jsp">
		<div class="container" id="Edit Train Schedule">
			<button type="submit">Edit Train Schedule</button>
		</div>
	</form>
	<br>
	<form action="custRepQuestion.jsp" method="post">
		<div class="container" id="Question">
			<!-- Click button to log out of account -->
			<button type="submit">Answer Questions</button>
		</div>
	</form>
	<br>
	<form action="custRepReservations.jsp" method="post">
		<div class="container" id="Question">
			<!-- Click button to log out of account -->
			<button type="submit">Customer Reservations</button>
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
