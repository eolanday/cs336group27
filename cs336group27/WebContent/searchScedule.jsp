<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336group27.model.*"%>  
<%@page import="com.cs336group27.pkg.ApplicationDB"%>  
<%@page import="java.io.*,java.util.*,java.sql.*"%> 


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Schedule Search</title>
</head>
<body>
	<header>
		<h1>Welcome to the Train Reservation System #27</h1>
		<h2>Customer Page</h2>
		<h3>Train Schedule</h3>
	</header>
	
	
	<!-- Java Code -->
	<%
		ApplicationDB appDB = new ApplicationDB();
		String[][] resList = appDB.getRevenueList((String)request.getAttribute("type"));
	%>
</body>
</html>