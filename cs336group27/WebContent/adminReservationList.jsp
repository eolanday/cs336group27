<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336group27.model.*"%>  
<%@page import="com.cs336group27.pkg.ApplicationDB"%>  
<%@page import="java.io.*,java.util.*,java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Reservations</title>
</head>
<body>
<header>
<h1>Welcome to the Train Reservation System #27</h1>
<h2>Administrator Page</h2>
<h3>Reservation List Report</h3>
</header>
<form action="adminReservationListServlet" method='post'>
	<div>
	  <input type="radio" id="transitLine" name="listType" value="transitLine">
	  <label for="transitLine">Transit Line</label>
	</div>
	<div>
	  <input type="radio" id="customer" name="listType" value="customer">
	  <label for="customer">Customer Name</label>
	</div>
	<input type="submit" value="Reorder">
</form>
	<% 
	ApplicationDB appDB = new ApplicationDB();
	String[][] resList = appDB.getReservationList((String)request.getAttribute("type"));
	
	if(((String)request.getAttribute("type")).equals("customer")){
		String temp = resList[0][5];
		out.print("<p>Customer Name:"+resList[0][7]+" "+resList[0][8]+"</p>");
		out.print("<table>");
		out.print("<tr>");
		out.print("<td>");
		out.print("Reservation Number");
		out.print("</td>");
		out.print("<td>");
		out.print("Reservation Date");
		out.print("</td>");
		out.print("<td>");
		out.print("Total Fare");
		out.print("</td>");
		out.print("<td>");
		out.print("Train ID");
		out.print("</td>");
		out.print("<td>");
		out.print("scheduleID");
		out.print("</td>");
		out.print("<td>");
		out.print("Reservation Type");
		out.print("</td>");
		out.print("</tr>");
		for(int i = 0; i<resList.length;i++) {
			if(!(resList[i][5].equals(temp))){
				out.print("</table>");
				out.print("<br>");
				out.print("<p>Customer Name:"+resList[i][7]+" "+resList[i][8]+"</p>");
				out.print("<table>");
				out.print("<tr>");
				out.print("<td>");
				out.print("Reservation Number");
				out.print("</td>");
				out.print("<td>");
				out.print("Reservation Date");
				out.print("</td>");
				out.print("<td>");
				out.print("Total Fare");
				out.print("</td>");
				out.print("<td>");
				out.print("Train ID");
				out.print("</td>");
				out.print("<td>");
				out.print("scheduleID");
				out.print("</td>");
				out.print("<td>");
				out.print("Reservation Type");
				out.print("</td>");
				out.print("</tr>");
			}
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
			out.print("<td>");
			out.print(resList[i][4]);
			out.print("</td>");
			out.print("<td>");
			out.print(resList[i][6]);
			out.print("</td>");
			out.print("</tr>");
		}
	}else{
		String temp = resList[0][4];
		out.print("<p>Transit ID:"+resList[0][4]+"</p>");
		out.print("<table>");
		out.print("<tr>");
		out.print("<td>");
		out.print("Reservation Number");
		out.print("</td>");
		out.print("<td>");
		out.print("Reservation Date");
		out.print("</td>");
		out.print("<td>");
		out.print("Total Fare");
		out.print("</td>");
		out.print("<td>");
		out.print("Train ID");
		out.print("</td>");
		out.print("<td>");
		out.print("First Name");
		out.print("</td>");
		out.print("<td>");
		out.print("Last Name");
		out.print("</td>");
		out.print("<td>");
		out.print("Reservation Type");
		out.print("</td>");
		out.print("</tr>");
		for(int i = 0; i<resList.length;i++) {
			if(!(resList[i][4].equals(temp))){
				out.print("</table>");
				out.print("<br>");
				out.print("<p>Transit ID:"+resList[i][4]+"</p>");
				out.print("<table>");
				out.print("<tr>");
				out.print("<td>");
				out.print("Reservation Number");
				out.print("</td>");
				out.print("<td>");
				out.print("Reservation Date");
				out.print("</td>");
				out.print("<td>");
				out.print("Total Fare");
				out.print("</td>");
				out.print("<td>");
				out.print("Train ID");
				out.print("</td>");
				out.print("<td>");
				out.print("First Name");
				out.print("</td>");
				out.print("<td>");
				out.print("Last Name");
				out.print("</td>");
				out.print("<td>");
				out.print("Reservation Type");
				out.print("</td>");
				out.print("</tr>");
			}
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
			out.print("<td>");
			out.print(resList[i][7]);
			out.print("</td>");
			out.print("<td>");
			out.print(resList[i][8]);
			out.print("</td>");
			out.print("<td>");
			out.print(resList[i][6]);
			out.print("</td>");
			out.print("</tr>");
		}
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