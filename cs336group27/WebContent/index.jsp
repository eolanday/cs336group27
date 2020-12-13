<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.cs336group27.pkg.LoginServlet"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hello</title>
</head>
<body>
<header>
<h1>Welcome to the Train Reservation System #27</h1>
<h2>Customer Action Page</h2>
</header>


<!-- Login Form: Log into train reservation system using already created username and password -->
<form action="LoginServlet" method="post">
	<div class="container" id="login">
	
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
<form action="employeeLogin.jsp">
	<br>
	<input type="submit" value="Employee Login">
</form>
<!-- Create Account: Create an Account by clicking the button -->
<form action="createUser.jsp">
	<br>
	<input type="submit" value="Create New Account">
</form>
</body>
</html>