<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TRS: User Create</title>
</head>
<body>
<h1>Welcome to the Train Reservation System #27</h1>
<h2>Account Creation</h2>

<!-- Create An Account Page: Fill out your personal information and create a username and password to store into the database -->
<form action="CustCreateServlet" method="post">
	<div class="container" id="createUser">
		
		<!-- Enter Username -->
		<label for="uname"><b>Username</b></label>
		<input type="text" placeholder="Enter User Name" name="uname" required>
		<br>
		
		<!-- Enter First Name -->
		<label for="fname"><b>First Name</b></label>
		<input type="text" placeholder="Enter First Name" name="fname" required>
		<br>
		
		<!-- Enter Last Name -->
		<label for="lname"><b>Last Name</b></label>
		<input type="text" placeholder="Enter Last Name" name="lname" required>
		<br>
		
		<!-- Enter Email -->
		<label for="email"><b>Email</b></label>
		<input type="text" placeholder="Enter Email Address" name="email" required>
		<br>
		
		<!-- Enter Password -->
		<label for="psw"><b>Password</b></label>
		<input type="password" placeholder="Enter Password" name="psw" required>
		<br>
		
		<!-- Confirm Password -->
		<label for="pswc"><b>Confirm Password</b></label>
		<input type="password" placeholder="Confirm Password" name="pswc" required>
		<br><br>
		
		<!-- Create an Account by clicking the button. Account gets stored into database. -->
		<button type="submit">Create Account</button> ${message}
	</div>
</form>
<form action="index.jsp">
	<br>
	
	<!-- Go back to previous page by clicking the button -->
	<input type="submit" value="Go Back">
</form>
</body>
</html>