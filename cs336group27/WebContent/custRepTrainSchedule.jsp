<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@page import="com.cs336group27.model.*"%>  
<%@page import="com.cs336group27.pkg.ApplicationDB"%>  
<%@page import="java.io.*,java.util.*,java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Representative Edit Train Schedule</title>
</head>
<h1>Welcome to the Train Reservation System #27</h1>
<h2>Customer Representative Page</h2>
<h3>User: ${employee.getName()}</h3>
<body>
	Add New Schedule:
	<form action="CustomerRepTrainScheduleServerlet" method="post">
		<label for="sid"><b>Enter Transit Line</b></label>
		<input type="text" placeholder="Enter transitLine" name="atline" required>
		<br>
		<label for="sid"><b>Enter Fare</b></label>
		<input type="number" min="0.01" step="0.01" type="number" placeholder="Enter fare" name="afare" required>
		<br>
		<label for="sid"><b>Enter Train ID</b></label>
		<input type="number" placeholder="Enter trainID" name="atid" required>
		<br>
		<label for="sid"><b>Enter Travel Date</b></label>
		<input type="number" placeholder="Enter travelDate" name="adate" pattern="\d{4}-\d{1,2}-\d{1,2}" required>
		<br>
		<div class="container" id="Edit">
			<button type="submit" name="button" value="add">Add Train Schedule</button>
		</div>
	</form>
	<br>
	Delete a Schedule:
	<form action="CustomerRepTrainScheduleServerlet" method="post">
		<label for="sid"><b>Enter scheduleID</b></label>
		<input type="number" placeholder="Enter scheduleID" name="dsid" required>
		<br>
		<div class="container" id="Edit">
			<button type="submit" name="button" value="delete">Delete Train Schedule</button>
		</div>
	</form>
	<br>
	Update a Schedule:
	<form action="CustomerRepTrainScheduleServerlet" method="post">
		<label for="sid"><b>Enter scheduleID</b></label>
		<input type="number" placeholder="Enter scheduleID" name="usid" required>
		<br>
		<label for="sid"><b>Enter New Transit Line</b></label>
		<input type="text" placeholder="Enter scheduleName" name="utline">
		<br>
		<label for="sid"><b>Enter New Fare</b></label>
		<input type="number" min="0.01" step="0.01" placeholder="Enter fare" name="ufare">
		<br>
		<label for="sid"><b>Enter New Train ID</b></label>
		<input type="number" placeholder="Enter trainID" name="utid">
		<br>
		<label for="sid"><b>Enter New Travel Date</b></label>
		<input type="number" placeholder="Enter travelDate" name="udate" pattern="\d{4}-\d{1,2}-\d{1,2}">
		<br>
		<div class="container" id="Edit">
			<button type="submit" name="button" value="update">Update Train Schedule</button>
		</div>
	</form>
	<br>
	Search for a Schedule:
	<form action="CustomerRepTrainScheduleServerlet" method="post">
		<label for="sid"><b>Enter Station Name</b></label>
		<input type="text" placeholder="Enter stationName" name="key" required>
		<br>
		<div class="container" id="Edit">
			<button type="submit" name="button" value="search">Search Train Schedule</button>
		</div>
	</form>
	<form action="CustomerRepTrainScheduleServerlet" method="post">
		<div class="container" id="Edit">
			<button type="submit" name="button" value="remsearch">Remove Search</button>
		</div>
	</form>
	<br>
	<% 
	ApplicationDB appDB = new ApplicationDB();
	String[][] resList = appDB.getTrainSchedules((String)request.getAttribute("key"));
	
	out.print("<table>");
	out.print("<tr>");
	
	out.print("<td>");
	out.print("Transit Line");
	out.print("</td>");
	
	out.print("<td>");
	out.print("Schedule ID");
	out.print("</td>");
	
	out.print("<td>");
	out.print("Train ID");
	out.print("</td>");
	
	out.print("<td>");
	out.print("Travel Time");
	out.print("</td>");
	
	out.print("<td>");
	out.print("Fare");
	out.print("</td>");
	
	out.print("<td>");
	out.print("Travel Date");
	out.print("</td>");
	
	out.print("<td>");
	out.print("Origin");
	out.print("</td>");
	
	out.print("<td>");
	out.print("Destination");
	out.print("</td>");

	out.print("</tr>");
	for(int i = 0; i < resList.length;i++) {
	    out.print("<tr>");
	    for(int j = 0; j < resList[i].length; j++) {
			out.print("<td>");
			out.print(resList[i][j]);
			out.print("</td>");
	    }
		out.print("</tr>");
	}
	out.print("</table>");
	%>
	<form action="custRepHomepage.jsp">
		<div class="container" id="Go Back">
			<button type="submit">Go Back</button>
		</div>
	</form>
</body>
</html>
