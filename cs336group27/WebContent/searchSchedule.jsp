<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336group27.model.*"%>  
<%@page import="com.cs336group27.pkg.ApplicationDB"%>  
<%@page import="java.io.*,java.util.*,java.sql.*"%> 


<!DOCTYPE html>
<html>
	<head>
		<style>
			h3 {text-align: center;}
			table {
			  font-family: arial, sans-serif;
			  border-collapse: collapse;
			  width: 100%;
			}
			
			td, th {
			  border: 1px solid #dddddd;
			  text-align: center;
			  padding: 7px;
			}
			
			tr:nth-child(even) {
			  background-color: #D6EAF8;
			}
		</style>
	<meta charset="UTF-8">
	<title>Schedule Search</title>
	</head>
<body>
	<header>
		<h1>Welcome to the Train Reservation System #27</h1>
		<h2>Customer Page</h2>
		<h3>Search Results</h3>
	</header>
	
	
	<!-- Java Code -->
	<%
		ApplicationDB appDB = new ApplicationDB();
		String[][] schedule = appDB.getTrainSchedule((String)request.getAttribute("start"), (String)request.getAttribute("end"), (String)request.getAttribute("travelDate"), (String)request.getAttribute("sortType"));
		String[][] tripInfo = appDB.getTripInfo((String)request.getAttribute("start"), (String)request.getAttribute("end"), (String)request.getAttribute("travelDate"));

		out.print("<table>");
		out.print("<tr>");
		out.print("<td><h4>");
		out.print("Transit Line");
		out.print("</td></h4>");
		out.print("<td><h4>");
		out.print("Train ID");
		out.print("</td></h4>");
		out.print("<td><h4>");
		out.print("Travel Date");
		out.print("</td></h4>");
		out.print("<td><h4>");
		out.print("Station Name");
		out.print("</td></h4>");
		out.print("<td><h4>");
		out.print("Arrival Time");
		out.print("</td></h4>");
		out.print("<td><h4>");
		out.print("Departure Time");
		out.print("</td></h4>");
		out.print("<td><h4>");
		out.print("Fare");
		out.print("</td></h4>");
		out.print("</tr>");
		for(int i = 0; i<tripInfo.length;i++) {
			out.print("<tr>");
			out.print("<td>");
			out.print(tripInfo[i][0]);
			out.print("</td>");
			out.print("<td>");
			out.print(tripInfo[i][1]);
			out.print("</td>");
			out.print("<td>");
			out.print(tripInfo[i][2]);
			out.print("</td>");
			out.print("<td>");
			out.print(tripInfo[i][3]);
			out.print("</td>");
			out.print("<td>");
			out.print(tripInfo[i][4]);
			out.print("</td>");
			out.print("<td>");
			out.print(tripInfo[i][5]);
			out.print("</td>");
			out.print("<td>");
			out.print(tripInfo[i][6]);
			out.print("</td>");
			out.print("</tr>");
		}
		out.print("</table>");
		out.print("<br>");
		out.print("<br>");
		
		out.print("<h3>Train Schedule</h3>");
		out.print("<table>");
		out.print("<tr>");
		out.print("<td><h4>");
		out.print("Transit Line");
		out.print("</td></h4>");
		out.print("<td><h4>");
		out.print("Train ID");
		out.print("</td></h4>");
		out.print("<td><h4>");
		out.print("Travel Date");
		out.print("</td></h4>");
		out.print("<td><h4>");
		out.print("Station Name");
		out.print("</td></h4>");
		out.print("<td><h4>");
		out.print("Arrival Time");
		out.print("</td></h4>");
		out.print("<td><h4>");
		out.print("Departure Time");
		out.print("</td></h4>");
		out.print("<td><h4>");
		out.print("One Way Fare From Origin");
		out.print("</td></h4>");
		out.print("<td width=30\'%'><h4>");
		out.print("Stop Number");
		out.print("</td></h4>");
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