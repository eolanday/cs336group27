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
		String[][] resList = appDB.getTrainSchedule((String)request.getAttribute("origin"), (String)request.getAttribute("destination"), (String)request.getAttribute("travelDate"));
		out.print("<p>Train Schedule</p>");
		out.print("<table>");
		out.print("<tr>");
		out.print("<td>");
		out.print("Train ID");
		out.print("</td>");
		out.print("<td>");
		out.print("Origin");
		out.print("</td>");
		out.print("<td>");
		out.print("Destination");
		out.print("</td>");
		out.print("<td>");
		out.print("Travel Date");
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
</body>
</html>