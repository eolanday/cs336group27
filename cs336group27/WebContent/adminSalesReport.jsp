<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336group27.model.*"%>  
<%@page import="com.cs336group27.pkg.ApplicationDB"%>  
<%@page import="java.io.*,java.util.*,java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Monthly Report</title>
</head>
<body>
<header>
<h1>Welcome to the Train Reservation System #27</h1>
<h2>Administrator Page</h2>
<h3>Monthly Sales Report</h3>
</header>
	<% 
	ApplicationDB appDB = new ApplicationDB();
	String[][] mthReport = appDB.getMonthlyReport();
	out.print("<table>");
	out.print("<tr>");
	out.print("<td>");
	out.print("Month");
	out.print("</td>");
	out.print("<td>");
	out.print("Total Fare");
	out.print("</td>");
	out.print("<td>");
	out.print("Number of Reservations");
	out.print("</td>");
	out.print("</tr>");
	for(int i = 0; i<mthReport.length;i++) {
		out.print("<tr>");
		out.print("<td>");
		out.print(mthReport[i][0]);
		out.print("</td>");
		out.print("<td>");
		out.print(mthReport[i][1]);
		out.print("</td>");
		out.print("<td>");
		out.print(mthReport[i][2]);
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