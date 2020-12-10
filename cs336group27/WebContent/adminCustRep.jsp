<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336group27.model.*"%>  
<%@page import="com.cs336group27.pkg.ApplicationDB"%>  
<%@page import="java.io.*,java.util.*,java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Train Administrator</title>
</head>
<body>
<header>
<h1>Welcome to the Train Reservation System #27</h1>
<h2>Administrator Page</h2>
<h3>Customer Representatives</h3>
</header>
	<% 
	ApplicationDB appDB = new ApplicationDB();
	String[][] custReps = appDB.getAllCustomerReps();
	out.print("<table>");
	out.print("<tr>");
	out.print("<td>");
	out.print("EmployeeID");
	out.print("</td>");
	out.print("<td>");
	out.print("First Name");
	out.print("</td>");
	out.print("<td>");
	out.print("Last Name");
	out.print("</td>");
	out.print("<td>");
	out.print("Username");
	out.print("</td>");
	out.print("<td>");
	out.print("SSN");
	out.print("</td>");
	out.print("<td>");
	out.print("Email");
	out.print("</td>");
	out.print("<td>");
	out.print("StationID");
	out.print("</td>");
	out.print("</tr>");
	for(int i = 0; i<custReps.length;i++) {
		out.print("<tr>");
		out.print("<td>");
		out.print(custReps[i][0]);
		out.print("</td>");
		out.print("<td>");
		out.print(custReps[i][1]);
		out.print("</td>");
		out.print("<td>");
		out.print(custReps[i][2]);
		out.print("</td>");
		out.print("<td>");
		out.print(custReps[i][3]);
		out.print("</td>");
		out.print("<td>");
		out.print(custReps[i][4]);
		out.print("</td>");
		out.print("<td>");
		out.print(custReps[i][5]);
		out.print("</td>");
		out.print("<td>");
		out.print(custReps[i][6]);
		out.print("</td>");
		out.print("</tr>");
	}
	out.print("</table>");
	%>
	<br>
	<form action = "adminCustRepEdit.jsp">
		<label for = "empID">EmployeeID:</label>
		<select name="empID" id = "cars">
		<%
			String temp = "";
			for(int i = 0; i<custReps.length ; i++){
				temp = "<option value='".concat(custReps[i][0]).concat("'>").concat(custReps[i][0]).concat("</option>");
				out.print(temp);
				System.out.println(temp);
			}
		%>
		</select>
		<br><br>
		<input type="submit" value="Edit Customer Representative"> ${message}
	</form>
	<form action="createEmployee.jsp">
		<br>
		<input type="submit" value="Create Customer Representativee">
	</form>
	<form action="adminHomepage.jsp">
		<br>
		<input type="submit" value="Go Back to Admin Homepage">
	</form>
</body>
</html>