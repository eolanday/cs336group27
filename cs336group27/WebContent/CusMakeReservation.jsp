<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Reservation Page</title>
</head>
<body>

<h1>Reserve a Ticket</h1>

<label style="font-weight: bold;" for="ticket_type">Ticket Type:</label>
<form action="" method='post' name = "ticket_type" id = "ticket_type">
	<div>
	  <input type="radio" id="one_way" name="listType" value="one_way">
	  <label for="one_way">One-Way</label>
	</div>
	<div>
	  <input type="radio" id="round_trip" name="listType" value="round_trip">
	  <label for="round_trip">Round-Trip</label>
	</div>
	<br>
	<input type="submit" value="Select">
</form>





	<br>
	<form action="AgeServlet" method = "post">
		<div class="container" id="submitAgeButton">
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
	<div class="container" id="check">
		<!--input type="hidden" id = "empID" name = "empID" value = <!--%out.print("");%>-->
		<label for="disableConfirm"> Disabled?</label>
		<input type="checkbox" id="disableConfirm" name="disableConfirm" value="check">
		<button type="submit">Submit</button> 
		<br>
		${message}
		<br>
		${message1}
	</div>
</form>
	
	<br><br>
	<form action="loginGood.jsp">
		<br>
		<input type="submit" value="Go Back">
	</form>

</body>
</html>