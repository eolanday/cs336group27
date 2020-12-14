<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336group27.model.Customer"%>
<%@page import="com.cs336group27.model.*"%>  
<%@page import="com.cs336group27.pkg.ApplicationDB"%>  
<%@page import="java.io.*,java.util.*,java.sql.*"%>

<%--Customer custer =(Customer)htpSession.getAttribute("cust");--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Representative</title>
</head>
<h1>Welcome to the Train Reservation System #27</h1>
<h2>Customer Representative Page</h2>
<h3>User: ${employee.getName()}</h3>
<body>
	Filter based off schedule and date:
	<form action="CustRepReservationServlet" method="post">
		<label for="sid"><b>Enter Transit Line</b></label>
		<input type="text" placeholder="Enter transitLine" name="sid">
		<br>
		<label for="sid"><b>Enter Date</b></label>
		<input type="text" placeholder="Enter date" pattern="\d{4}-\d{1,2}-\d{1,2}" name="date">
		<br>
		<div class="container" id="Edit">
			<button type="submit" name="button" value="reply">Filter</button>
		</div>
	</form>
	<br>
	<% 
	ApplicationDB appDB = new ApplicationDB();
	String[][] resList = appDB.getRepReservations((String)request.getAttribute("sid"),(String)request.getAttribute("date"));
	
	out.print("<table>");
	out.print("<tr>");
	
	out.print("<td>");
	out.print("Reservation Number");
	out.print("</td>");
	
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
	out.print("Reservation Date");
	out.print("</td>");
	
	out.print("<td>");
	out.print("Total Fare");
	out.print("</td>");
	
	out.print("<td>");
	out.print("Reservation Type");
	out.print("</td>");
	
	out.print("<td>");
	out.print("Transit Line");
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
