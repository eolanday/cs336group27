<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336group27.model.*"%>  
<%@page import="com.cs336group27.pkg.ApplicationDB"%>  
<%@page import="java.io.*,java.util.*,java.sql.*"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Reservation Page</title>
</head>
<body>

<br><br>
<br><br>

<h1 style="text-align: center;">Reserve a Ticket</h1>
<br><br>		
<br>

	<form style="text-align: center;" action="AgeServlet" method = "post" name = "ticket_type">
	<div style="text-align: center;">
	<label style="font-weight: bold;" for="ticket_type">Ticket Type:</label>
	  <input type="radio" id="one_way" name="listType" value="one_way">
	  <label for="one_way">One-Way</label>
	</div>
	<div style="text-align: center;">
	  <input style="margin-left:8em;" type="radio" id="round_trip" name="listType" value="round_trip">
	  <label for="round_trip">Round-Trip</label>
	</div>
	<br>
	
<br>
		<div style="text-align: center;" class="container" id="choose">
		
		<!-- Enter Origin -->
			<label for="origin"><b>Origin:</b></label>
			<input type="text" placeholder="Enter Starting Point" name="origin">
			<br><br>
			
		<!-- Enter Destination -->
			<label for="destination"><b>Destination:</b></label>
			<input type="text" placeholder="Enter Destination" name="destination">
			<br><br>
		
		<!-- Enter Departure Date -->
			<label for="resdate"><b>Departure Date:</b></label>
			<input type="text" placeholder="YYYY-MM-DD" name="resdate">
			<br><br>
			
		<!-- Enter Departure Time -->
			<label for="restime"><b>Departure Time:</b></label>
			<input type="text" placeholder="24hr time = HH:mm:ss" name="restime">
			<br>
		</div>


	<br>
	<br>
		<div style="text-align: center;" class="container" id="submitAgeButton">
		<input type = "hidden" id = "user" name = "user" value = ${cust.getName()}>
			<label for="age">Select your age in years:</label>
			<select name="age" id = "age" required>		
			<%
				String temp = "";
			
				temp = "<option value='".concat("0-17").concat("'>").concat("0-17").concat("</option>");
				out.print(temp);
				System.out.println(temp);
				
				temp = "<option value='".concat("18-64").concat("'>").concat("18-64").concat("</option>");
				out.print(temp);
				System.out.println(temp);
				
				temp = "<option value='".concat("65+").concat("'>").concat("65+").concat("</option>");
				out.print(temp);
				System.out.println(temp);
			%>
			</select>
				<!--button type="submit">Submit</button><br>${message}  -->
		</div>
	<!-- /form> -->
	
	<br>
	<!--form action="AgeServlet" method="post"-->
	<div style="text-align: center;" class="container" id="check">
		<!--input type="hidden" id = "empID" name = "empID" value = <!--%out.print("");%>-->
		<label for="disableConfirm"> Disabled?</label>
		<input type="checkbox" id="disableConfirm" name="disableConfirm" value="check">
					<br><br>
		
		<button type="submit">Submit</button> 
		<br>
		${message}
		<br>
		${message1}
		<br>
		${message2}
		<br>
		${message3}
		<br>
		${message4}
	</div>
</form>
	
	<br><br>
	<form style="text-align: center;" action="loginGood.jsp">
		<br>
		<input type="submit" value="Go Back">
	</form>

</body>
</html>