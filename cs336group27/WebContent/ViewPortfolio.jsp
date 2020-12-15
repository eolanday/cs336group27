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


<label style="font-weight: bold;" for = "view_choice">View:</label>
	<form action="SwitchView" method = "post" name = "view_choice">
		<div>
		  <input type="radio" id="past" name="switch" value="past">
		  <label for="past">Past Reservations</label>
		</div>
		<div>
		  <input type="radio" id="future" name="switch" value="future">
		  <label for="future">Future Reservations</label>
		  <br>
		 </div>
		  <input type = "hidden" id = "user" name = "user" value = ${cust.getName()} >
		  <button type="submit"> Submit</button>
	</form>
		
		<br>


<% 
	ApplicationDB appDB = new ApplicationDB();
	String user = (String)request.getAttribute("currentUser");
	String type = (String)request.getAttribute("type");
	//System.out.println(user);
	//System.out.println(type);
	String[][] resList = appDB.getPortfolio(user, type);
	for(int i = 0; i<resList.length ; i++){
		for(int j = 0; j<resList.length; j++){
			System.out.println(resList[i][j]);
		}
		
	}
		out.print("<p><b>Customer Name</b>: "+resList[0][11]+" "+resList[0][12]+"</p>");
		out.print("<table>");
		out.print("<tr>");
		out.print("<td>");
		out.print("<b>Reservation Number</b>");
		out.print("</td>");
		out.print("<td>");
		out.print("<b>Reservation Date</b>");
		out.print("</td>");
		out.print("<td>");
		out.print("<b>Travel Date</b>");
		out.print("</td>");
		out.print("<td>");
		out.print("<b>Reservation Type</b>");
		out.print("</td>");
		out.print("<td>");
		out.print("<b>Transit Line</b>");
		out.print("</td>");
		out.print("<td>");
		out.print("<b>Train Number</b>");
		out.print("</td>");
		out.print("<td>");
		out.print("<b>Origin</b>");
		out.print("</td>");
		out.print("<td>");
		out.print("<b>Destination</b>");
		out.print("</td>");
		out.print("<td>");
		out.print("<b>Arrival Time</b>");
		out.print("</td>");
		out.print("<td>");
		out.print("<b>Departure Time</b>");
		out.print("</td>");
		out.print("<td>");
		out.print("<b>Total Fare</b>");
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
			out.print("<td>");
			out.print(resList[i][9]);
			out.print("</td>");
			out.print("<td>");
			out.print(resList[i][10]);
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