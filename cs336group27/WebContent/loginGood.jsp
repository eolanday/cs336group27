<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336group27.model.Customer"%>  
<%--Customer custer =(Customer)htpSession.getAttribute("cust");--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome!</title>
</head>

<!-- Welcome screen for successful customer logins -->
<h1>Welcome to the Main Customer Page</h1>
<body>
	<div>
		<!-- Returns User's Username -->
		User:${cust.getName()}
		<br>
	</div>
	
	<form action="LogoutServlet" method="post">
	<div class="container" id="logout">
		<!-- Click button to log out of account -->
		<button type="submit">Logout</button>
	</div>
</form>
</body>
</html>