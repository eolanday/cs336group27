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
		out.print("<p>Customer Name:"+resList[0][6]+" "+resList[0][7]+"</p>");
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
			out.print("</tr>");
		}
	
	out.print("</table>");
	%>
	
	<br>
	<br>
	<h2>Would you like to cancel a reservation?</h2>
	<!-- Cancel a Reservation -->
	
	
	<br>
	<form action = "resDeleteConfirm.jsp" method = "post">
		<label for="res_num"><b>Reservation Number:</b></label>
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
		<br><br>
	<div class="container" id="cancelResButton">
			<button type="submit">Cancel Reservation</button>
		</div>
	</form>
	
	
	
	<!--
	<br>
	<form action="CusDeleteReservation" method = "post">
		<label for="res_num"><b>Input the reservation number.</b></label>
		<input type="number" placeholder="Reservation Number" name="res_num" required>
		
		<div class="container" id="cancelResButton">
			<button type="submit">Cancel Reservation</button>
		</div>
	</form>
	-->
	
	<br>
	<br>
	<form action="loginGood.jsp">
		<br>
		<input type="submit" value="Go Back">
	</form>
</body>
</html>