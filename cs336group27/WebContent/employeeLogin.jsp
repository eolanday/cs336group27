<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.cs336group27.pkg.EmployeeLoginServlet"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Login</title>
</head>
<h1>Welcome to the Train Reservation System #27</h1>
<h2>Employee Login Page</h2>
<body>
	<!-- Login Form: Log into train reservation system using already created username and password -->
<form action="EmployeeLoginServlet" method="post">
	<div class="container" id="login">
	
	<!-- Enter Employee ID -->
		<label for="eid"><b>Employee ID</b></label>
		<input type="number" placeholder="Enter Employee ID" name="eid" required>
		<br>
	<!-- Enter Username -->
		<label for="uname"><b>Username</b></label>
		<input type="text" placeholder="Enter Username" name="uname" required>
		<br>
		
	<!-- Enter Password -->
		<label for="psw"><b>Password</b></label>
		<input type="password" placeholder="Enter Password" name="psw" required>
		<br>${message}
		<br><br>
		
		<!-- Click button to login -->
		<button type="submit">Login</button>
	</div>
</form>
<form action="index.jsp">
	<br>
	<!-- Go back to previous page by clicking the button -->
	<input type="submit" value="Go Back">
</form>
</body>
</html>