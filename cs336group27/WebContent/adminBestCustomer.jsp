<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336group27.model.*"%>  
<%@page import="com.cs336group27.pkg.ApplicationDB"%>  
<%@page import="java.io.*,java.util.*,java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin: Best Customer</title>
</head>
<body>
<header>
<h1>Welcome to the Train Reservation System #27</h1>
<h2>Administrator Page</h2>
<h3>Best Customer Report</h3>
</header>
</head>
<body>
<% 
	ApplicationDB appDB = new ApplicationDB();
	String[][] resList = appDB.getRevenueList("customer");
	out.print("<p>Best Customer!</p>");
	out.print("<table>");
	out.print("<tr>");
	out.print("<td>");
	out.print("Customer ID");
	out.print("</td>");
	out.print("<td>");
	out.print("First Name");
	out.print("</td>");
	out.print("<td>");
	out.print("Last Name");
	out.print("</td>");
	out.print("<td>");
	out.print("Revenue");
	out.print("</td>");
	out.print("</tr>");
	for(int i = 0; i<1;i++) {
		out.print("<tr>");
		out.print("<td>");
		out.print(resList[i][0]);
		out.print("</td>");
		out.print("<td>");
		out.print(resList[i][1]);
		out.print("</td>");
		out.print("<td>");
		out.print(resList[i][2]);
		out.print("</td>");
		out.print("<td>");
		out.print(resList[i][3]);
		out.print("</td>");
		out.print("</tr>");
	}
	out.print("</table>");
%>
	<br>
	<form action="adminHomepage.jsp">
		<br>
		<input type="submit" value="Go Back to Admin Homepage">
	</form>
</body>
</html>