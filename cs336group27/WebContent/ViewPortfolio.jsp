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
	ApplicationDB appDB = new ApplicationDB();
	String[][] resList = appDB.getPortfolio((String)request.getAttribute("type"));
	String temp = resList[0][5];
		out.print("<p>Customer Name:"+resList[0][7]+" "+resList[0][8]+"</p>");
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
	    	if(!(resList[i][5].equals(temp))){
				out.print("</table>");
				out.print("<br>");
				out.print("<p>Customer Name:"+resList[i][7]+" "+resList[i][8]+"</p>");
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
			}
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
			out.print(resList[i][6]);
			out.print("</td>");
			out.print("</tr>");
		}
	<br>
	<form action="loginGood.jsp">
		<br>
		<input type="submit" value="Go Back">
	</form>
</body>
</html>