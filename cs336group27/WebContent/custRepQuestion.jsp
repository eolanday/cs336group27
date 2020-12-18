<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.cs336group27.model.Customer"%>
<%@page import="com.cs336group27.model.*"%>  
<%@page import="com.cs336group27.pkg.ApplicationDB"%>  
<%@page import="java.io.*,java.util.*,java.sql.*"%>

<%--Customer custer =(Customer)htpSession.getAttribute("cust");--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Representative</title>
</head>
<h1>Welcome to the Train Reservation System #27</h1>
<h2>Customer Representative Page</h2>
<h3>User: ${employee.getName()}</h3>
<body>
	Reply to a Question:
	<form action="CustRepQuestionServlet" method="post">
		<label for="sid"><b>Enter Question ID</b></label>
		<input type="number" placeholder="Enter questionID" name="qid" required>
		<br>
		<label for="sid"><b>Enter Reply</b></label>
		<input type="text" placeholder="Enter reply" name="reply" required>
		<br>
		<div class="container" id="Edit">
			<button type="submit" name="button" value="reply">Reply</button>
		</div>
	</form>
	<br>
	Search by Keyword:
	<form action="CustRepQuestionServlet" method="post">
		<label for="sid"><b>Enter keyword</b></label>
		<input type="text" placeholder="Enter keyword" name="keyword" required>
		<br>
		<div class="container" id="Edit">
			<button type="submit" name="button" value="search">Search</button>
		</div>
	</form>
	<br>
	<% 
	ApplicationDB appDB = new ApplicationDB();
	String[][] resList = appDB.getQuestions((String)request.getAttribute("keyword"));
	
	if(resList.length != 0) {
		out.print("<table>");
		out.print("<tr>");
		out.print("<td>");
		out.print("Question ID");
		out.print("</td>");
		out.print("<td>");
		out.print("Asker ID");
		out.print("</td>");
		out.print("<td>");
		out.print("Replier ID");
		out.print("</td>");
		out.print("<td>");
		out.print("Question");
		out.print("</td>");
		out.print("<td>");
		out.print("Reply");
		out.print("</td>");
		out.print("<td>");
		out.print("Post Date");
		out.print("</td>");
		out.print("<td>");
		out.print("Reply Date");
		out.print("</td>");
		out.print("</tr>");
		for(int i = 0; i < resList.length;i++) {
		    out.print("<tr>");
		    for(int j = 0; j < resList[i].length; j++) {
				out.print("<td>");
				out.print(resList[i][j]);
				out.print("</td>");
		    }
			out.print("</tr>");
		}
		out.print("</table>");
	} else {
	    out.print("No results found.");
	}
	%>
	<form action="custRepHomepage.jsp">
		<div class="container" id="Go Back">
			<button type="submit">Go Back</button>
		</div>
	</form>
</body>
</html>
