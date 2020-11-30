<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.cs336group27.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	try {
		
		//Get the database connection
		ApplicationDB db = new ApplicationDB();	
		Connection con = db.getConnection();
		
		//Create a SQL statement
		Statement stmt = con.createStatement();
		//Get input username and password from the index.jsp
		String username = request.getParameter("uname");
		String password = request.getParameter("psw");
		//Make a SELECT query from the table specified by the 'command' parameter at the index.jsp
		String check = "select count(*) from Customers where username = (?) and password_customer = (?) group by username";
		//Create a Prepared SQL statement allowing you to introduce the parameters of the query
		PreparedStatement ps = con.prepareStatement(check);
		
		//Add parameters of the query
		ps.setString(1, username);
		ps.setString(2, password);
		int userCount = ps.executeUpdate();
		
		//close the connection.
		con.close();
		
		
	} catch (Exception ex) {
		out.print(ex);
		out.print("login attempt failed");
	}
	%>
</body>
</html>