<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Deletion Confirmation</title>
</head>
<body>

<h1>Confirmation</h1>

<% String num = (String)request.getParameter("res_num"); %>

<form action="CusDeleteReservation" method = "post">
	<br>
<div class="container" id="resDeleteConfirmButton">
			<label for = "resDeleteConfirm">Are You Sure?<br></label>
			<input type = "hidden" id = "res_num" name = "res_num" value = <%out.print(num);%>>
			<button type="submit">Yes</button>${message}
		</div></form>

<br>
<br>
<form action="loginGood.jsp">
		<br>
		<input type="submit" value="Go Back to Customer Homepage">
	</form>


</body>
</html>