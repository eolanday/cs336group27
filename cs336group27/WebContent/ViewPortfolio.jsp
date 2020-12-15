<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336group27.model.*"%>  
<%@page import="com.cs336group27.pkg.ApplicationDB"%>  
<%@page import="java.io.*,java.util.*,java.sql.*"%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Portfolio</title>
</head>
<h1>${cust.getName()}'s Reservation Portfolio</h1>
<body>
<% 
	ApplicationDB appDB = new ApplicationDB();
	String[][] resList = appDB.getPortfolio((String)request.getAttribute("currentUser"));
		out.print("<p><b>Customer Name</b>: "+resList[0][9]+" "+resList[0][10]+"</p>");
		out.print("<table>");
		out.print("<tr>");
		out.print("<td>");
		out.print("<b>Reservation Number</b>");
		out.print("</td>");
		out.print("<td>");
		out.print("<b>Reservation Date</b>");
		out.print("</td>");
		out.print("<td>");
		out.print("<b>Total Fare</b>");
		out.print("</td>");
		out.print("<td>");
		out.print("<b>Train ID</b>");
		out.print("</td>");
		out.print("<td>");
		out.print("<b>Transit Line</b>");
		out.print("</td>");
		out.print("<td>");
		out.print("<b>Reservation Type</b>");
		out.print("</td>");
		out.print("<td>");
		out.print("<b>Travel Date</b>");
		out.print("</td>");
		out.print("<td>");
		out.print("<b>Origin</b>");
		out.print("</td>");
		out.print("<td>");
		out.print("<b>Destination</b>");
		out.print("</td>");
		out.print("</tr>");
		for(int i = 0; i < resList.length;i++) {
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
			out.print(resList[i][5]);
			out.print("</td>");
			out.print("<td>");
			out.print(resList[i][6]);
			out.print("</td>");
			out.print("<td>");
			out.print(resList[i][7]);
			out.print("</td>");
			out.print("<td>");
			out.print(resList[i][8]);
			out.print("</td>");
			out.print("</tr>");
		}
	
	out.print("</table>");
	%>
	
	<br>
	<br>
	<!-- Cancel a Reservation -->
	<label style="font-weight: bold;" for="deleteResForm">Would you like to cancel a reservation?</label>
		<form action = "resDeleteConfirm.jsp" method = "post" name = "deleteResForm">
			<div class="container" id="cancelResButton">
		
				<label for="res_num">Reservation Number:</label>
				<select name="res_num" id = "res_num">		
				<%
					String temp = "";
					for(int i = 0; i<resList.length ; i++){
						temp = "<option value='".concat(resList[i][0]).concat("'>").concat(resList[i][0]).concat("</option>");
						out.print(temp);
						System.out.println(temp);
					}
				%>
				</select>
			<button type="submit">Cancel Reservation</button>
			</div>
		</form>
	
	<br>
	<br>
	<form action="loginGood.jsp">
		<br>
		<input type="submit" value="Go Back">
	</form>
</body>
</html>