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
<meta charset="ISO-8859-1">
<title>View Portfolio</title>
</head>
			<br>

<h1 style="text-align: center;">${cust.getName()}'s Reservation Portfolio</h1>
<body>


	<form style='text-align: center;'action="SwitchView" method = "post" name = "view_choice">
		<div>
		<label style="font-weight: bold; text-align: center;" for = "view_choice">View:</label>
		  <input type="radio" id="past" name="switch" value="past">
		  <label for="past">Past Reservations</label>
		</div>
		<div>
		  <input style="margin-left:4.85em;" type="radio" id="future" name="switch" value="future">
		  <label for="future">Future Reservations</label>
		 </div>
		 <br>
		  <input type = "hidden" id = "user" name = "user" value = ${cust.getName()} >
		  <button type="submit"> Choose View</button>
	</form>
		
		<br>


<% 
	ApplicationDB appDB = new ApplicationDB();
	String user = (String)request.getAttribute("currentUser");
	String type = (String)request.getAttribute("type");
	//System.out.println(user);
	//System.out.println(type);
	String[][] resList = appDB.getPortfolio(user, type);
		out.print("<p><b>Customer Name</b>: "+resList[0][11]+" "+resList[0][12]+"</p>");
		out.print("<table style= 'margin-left:auto;margin-right:auto;vertical-align: middle;'>");
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
			out.print("<td style='text-align: center;'>");
			out.print(resList[i][0]);
			out.print("</td>");
			out.print("<td style='text-align: center;'>");
			out.print(resList[i][1]);
			out.print("</td>");
			out.print("<td style='text-align: center;'>");
			out.print(resList[i][2]);
			out.print("</td>");
			out.print("<td style='text-align: center;'>");
			out.print(resList[i][3]);
			out.print("</td>");
			out.print("<td style='text-align: center;'>");
			out.print(resList[i][4]);
			out.print("</td>");
			out.print("<td style='text-align: center;'>");
			out.print(resList[i][5]);
			out.print("</td>");
			out.print("<td style='text-align: center;'>");
			out.print(resList[i][6]);
			out.print("</td>");
			out.print("<td style='text-align: center;'>");
			out.print(resList[i][7]);
			out.print("</td>");
			out.print("<td style='text-align: center;'>");
			out.print(resList[i][8]);
			out.print("</td>");
			out.print("<td style='text-align: center;'>");
			out.print(resList[i][9]);
			out.print("</td>");
			out.print("<td style='text-align: center;'>");
			out.print(resList[i][10]);
			out.print("</td>");
			out.print("</tr>");
		}
	
	out.print("</table>");
	%>
	
	<br>
	<br>
	<!-- Cancel a Reservation -->
		<form style='text-align: center;' action = "resDeleteConfirm.jsp" method = "post" name = "deleteResForm">
			<div style='text-align: center;'class="container" id="cancelResButton">
			<label style="font-weight: bold;" for="deleteResForm">Would you like to cancel a reservation?</label>
				<br>
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
				<br><br>
			<input type="submit" value = "Cancel Reservation">
			</div>
		</form>
	
	<br>
	<br>
	<form style='text-align: center;' action="loginGood.jsp">
		<br>
		<input style='text-align: center;'  type="submit" value="Go Back">
	</form>
</body>
</html>
