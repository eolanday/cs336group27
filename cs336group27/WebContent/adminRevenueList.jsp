<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336group27.model.*"%>  
<%@page import="com.cs336group27.pkg.ApplicationDB"%>  
<%@page import="java.io.*,java.util.*,java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Revenues</title>
</head>
<body>
<header>
<h1>Welcome to the Train Reservation System #27</h1>
<h2>Administrator Page</h2>
<h3>Revenue List Report</h3>
</header>
<form action="adminRevenueListsServlet" method='post'>
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
	String[][] resList = appDB.getRevenueList((String)request.getAttribute("type"));
	
	if(((String)request.getAttribute("type")).equals("customer")){
		out.print("<p>Revenue per Customer</p>");
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
	}else{
		out.print("<p>Revenue per Transit Line</p>");
		out.print("<table>");
		out.print("<tr>");
		out.print("<td>");
		out.print("Transit Line");
		out.print("</td>");
		out.print("<td>");
		out.print("Revenue");
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