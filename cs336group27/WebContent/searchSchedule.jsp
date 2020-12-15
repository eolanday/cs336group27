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
		String[][] schedule = appDB.getTrainSchedule((String)request.getAttribute("origin"), (String)request.getAttribute("destination"), (String)request.getAttribute("travelDate")); // (String)request.getAttribute("sortType"));
		String[][] tripInfo = appDB.getTripInfo((String)request.getAttribute("origin"), (String)request.getAttribute("destination"), (String)request.getAttribute("travelDate")); // (String)request.getAttribute("sortType"));

		out.print("<p>Search results</p>");
		
		out.print("<table>");
		out.print("<tr>");
		out.print("<td>");
		out.print("Transit Line");
		out.print("</td>");
		out.print("<td>");
		out.print("Train ID");
		out.print("</td>");
		out.print("<td>");
		out.print("Travel Date");
		out.print("</td>");
		out.print("<td>");
		out.print("Station Name");
		out.print("</td>");
		out.print("<td>");
		out.print("Arrival Time");
		out.print("</td>");
		out.print("<td>");
		out.print("Departure Time");
		out.print("</td>");
		out.print("<td>");
		out.print("Fare");
		out.print("</td>");
		out.print("<td>");
		out.print("</tr>");
		for(int i = 0; i<tripInfo.length;i++) {
			out.print("<tr>");
			out.print("<td>");
			out.print(schedule[i][0]);
			out.print("</td>");
			out.print("<td>");
			out.print(schedule[i][1]);
			out.print("</td>");
			out.print("<td>");
			out.print(schedule[i][2]);
			out.print("</td>");
			out.print("<td>");
			out.print(schedule[i][3]);
			out.print("</td>");
			out.print("<td>");
			out.print(schedule[i][4]);
			out.print("</td>");
			out.print("<td>");
			out.print(schedule[i][5]);
			out.print("</td>");
			out.print("<td>");
			out.print(schedule[i][6]);
			out.print("</td>");
			out.print("<td>");
			out.print("</tr>");
		}
		out.print("</table>");
		out.print("<br>");
		out.print("<br>");
		
	
		out.print("<table>");
			out.print("<tr>");
				out.print("<td>");
					out.print("Transit Line");
				out.print("</td>");
				out.print("<td>");
					out.print("Train ID");
				out.print("</td>");
				out.print("<td>");
					out.print("Travel Date");
				out.print("</td>");
				out.print("<td>");
					out.print("Station Name");
				out.print("</td>");
				out.print("<td>");
					out.print("Arrival Time");
				out.print("</td>");
				out.print("<td>");
					out.print("Departure Time");
				out.print("</td>");
				out.print("<td>");
					out.print("Fare");
				out.print("</td>");
				out.print("<td>");
					out.print("Stop Number");
				out.print("</td>");
		out.print("</tr>");
		for(int i = 0; i<schedule.length;i++) {
			out.print("<tr>");
			out.print("<td>");
			out.print(schedule[i][0]);
			out.print("</td>");
			out.print("<td>");
			out.print(schedule[i][1]);
			out.print("</td>");
			out.print("<td>");
			out.print(schedule[i][2]);
			out.print("</td>");
			out.print("<td>");
			out.print(schedule[i][3]);
			out.print("</td>");
			out.print("<td>");
			out.print(schedule[i][4]);
			out.print("</td>");
			out.print("<td>");
			out.print(schedule[i][5]);
			out.print("</td>");
			out.print("<td>");
			out.print(schedule[i][6]);
			out.print("</td>");
			out.print("<td>");
			out.print(schedule[i][7]);
			out.print("</td>");
			out.print("</tr>");
		}
		out.print("</table>");
	%>
</body>
</html>
