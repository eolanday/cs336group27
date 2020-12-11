<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336group27.model.*"%>  
<%@page import="com.cs336group27.pkg.ApplicationDB"%>  
<%@page import="java.io.*,java.util.*,java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Best Transit Lines</title>
</head>
<body>
<header>
<h1>Welcome to the Train Reservation System #27</h1>
<h2>Administrator Page</h2>
<h3>Best Transit Line Report</h3>
</header>
</head>
<body>
<% 
	ApplicationDB appDB = new ApplicationDB();
	String[][] resList = appDB.getRevenueList("besttransitlines");
	out.print("<p>Top 5 Most Active Transit Lines</p>");
	out.print("<table>");
	out.print("<tr>");
	out.print("<td>");
	out.print("Transit Line ID");
	out.print("</td>");
	out.print("<td>");
	out.print("Number of Reservations");
	out.print("</td>");

	out.print("</tr>");
	for(int i = 0; i<resList.length;i++) {
		out.print("<tr>");
		out.print("<td>");
		out.print(resList[i][0]);
		out.print("</td>");
		out.print("<td>");
		out.print(resList[i][1]);
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