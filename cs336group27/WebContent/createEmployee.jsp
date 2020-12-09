<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin: Create Employeee</title>
</head>
<header>
<h1>Welcome to the Train Reservation System #27</h1>
<h2>Administrator Page</h2>
<h3>Create Employee</h3>
</header>
<body>
<form action="createEmployeeServlet" method="post">
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
		<label for="ssn"><b>SSN</b></label>
		<input type=text placeholder="Enter SSN" name="ssn" required>
		<br>
		<label for="stationID"><b>Station ID</b></label>
		<input type="number" placeholder="Enter StationID" name="stationID" required>
		<br>
		<!-- Enter Password -->
		<label for="psw"><b>Password</b></label>
		<input type="password" placeholder="Enter Password" name="psw" required>
		<br>
		
		<!-- Confirm Password -->
		<label for="pswc"><b>Confirm Password</b></label>
		<input type="password" placeholder="Confirm Password" name="pswc" required>
		<br><br>
		<label for="custrep"> Customer Representative</label>
		<input type="checkbox" id="custrep" name="custrep" value="custrep">
		<label for="admin"> Administrator</label>
		<input type="checkbox" id="admin" name="admin" value="admin">
		<!-- Create an Account by clicking the button. Account gets stored into database. -->
		<button type="submit">Create Account</button> ${message}
	</div>
</form>
</body>
</html>