<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336group27.model.*"%>  
<%@page import="com.cs336group27.pkg.ApplicationDB"%>  
<%@page import="java.io.*,java.util.*,java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Customer Rep</title>
</head>
<header>
<h1>Welcome to the Train Reservation System #27</h1>
<h2>Administrator Page</h2>
<h3>Editing Customer Representative Information </h3>
</header>
<body>
	<% 
	ApplicationDB appDB = new ApplicationDB();
	String[] repInfo = appDB.getCustomerRepInfo(Integer.parseInt(request.getParameter("empID")));
	%>
<form action="CustomerRepUpdate" method="post">
	<div class="container" id="edit">
		<label for = "empID"><b>EmployeeID</b></label>
		<input type="text" value=<%out.print(repInfo[0]);%> name="empID" readonly>
		<br>
		<label for="uname"><b>Username</b></label>
		<input type="text" value=<%out.print(repInfo[3]);%> name="uname" required>
		<br>
		<label for="password"><b>Password</b></label>
		<input type="text" value=<%out.print(repInfo[7]);%> name="password" required>
		<br>
		<label for="firstName"><b>First Name</b></label>
		<input type="text" value=<%out.print(repInfo[1]);%> name="firstName" required>
		<br>
		<label for="lastName"><b>Last Name</b></label>
		<input type="text" value=<%out.print(repInfo[2]);%> name="lastName" required>
		<br>
		<label for="ssn"><b>SSN</b></label>
		<input type="text" value=<%out.print(repInfo[4]);%> name="ssn" required>
		<br>
		<label for="email"><b>Email</b></label>
		<input type="text" value=<%out.print(repInfo[5]);%> name="email" required>
		<br>
		<label for="stationID"><b>StationID</b></label>
		<input type="text" value=<%out.print(repInfo[6]);%> name="stationID" required>
		<br><br>
		<button type="submit">Save Information</button> ${message}
	</div>
</form>
<br>
<form action="CustomerRepDelete" method="post">
	<div class="container" id="delete">
		<input type="hidden" id = "empID", name = "empID", value = <%out.print(repInfo[0]);%>>
		<label for="deleteConfirm"> Delete Customer Rep?</label>
		<input type="checkbox" id="deleteConfirm" name="deleteConfirm" value="delete">
		<button type="submit">Delete Customer Representative</button> ${message1}
	</div>
</form>
	<form action="adminCustRep.jsp">
		<br>
		<input type="submit" value="Go Back">
	</form>
</body>
</html>