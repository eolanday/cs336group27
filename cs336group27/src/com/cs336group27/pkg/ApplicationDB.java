package com.cs336group27.pkg;
import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.http.*;

import javax.servlet.*;
public class ApplicationDB {
	
	public ApplicationDB(){
		
	}

	public Connection getConnection(){
		
		//Create a connection string
		String connectionUrl = "jdbc:mysql://cs336db27.clxsclmtm5hy.us-east-2.rds.amazonaws.com:3306/trainsys1";
		Connection connection = null;
		
		try {
			//Load JDBC driver - the interface standardizing the connection procedure. Look at WEB-INF\lib for a mysql connector jar file, otherwise it fails.
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			//Create a connection to your DB
			connection = DriverManager.getConnection(connectionUrl,"admindb27", "zS7W6&!7Pswk");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
		
	}
	
	public void closeConnection(Connection connection){
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public static void main(String[] args) {
		ApplicationDB dao = new ApplicationDB();
		Connection connection = dao.getConnection();
		
		System.out.println(connection);		
		dao.closeConnection(connection);
	}
	
	public int loginCheck(String u, String p) {
		try {
			ApplicationDB db = new ApplicationDB();	
			Connection con = db.getConnection();
			String check = "select count(*) custCount from Customers where username = (?) and password_customer = (?)";
			PreparedStatement ps = con.prepareStatement(check);
			ps.setString(1, u);
			ps.setString(2, p);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int userCount = rs.getInt("custCount");
			con.close();
			rs.close();
			return userCount;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}
	public int employeeLoginCheck(String u, String p, int eid) {
		try {
			ApplicationDB db = new ApplicationDB();	
			Connection con = db.getConnection();
			String check = "select count(*) eCount from Employees where username = (?) and password_employee = (?) and employeeID = (?)";
			PreparedStatement ps = con.prepareStatement(check);
			ps.setString(1, u);
			ps.setString(2, p);
			ps.setString(3, Integer.toString(eid));
			ResultSet rs = ps.executeQuery();
			rs.next();
			int eCount = rs.getInt("eCount");
			con.close();
			rs.close();
			return eCount;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}
	public String employeeIsAdmin(String u, String p, int eid) {
		try {
			ApplicationDB db = new ApplicationDB();	
			Connection con = db.getConnection();
			String query = "select isAdmin from Employees where username = (?) and password_employee = (?) and employeeID = (?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, u);
			ps.setString(2, p);
			ps.setString(3, Integer.toString(eid));
			ResultSet rs = ps.executeQuery();
			rs.next();
			String empType = rs.getString("isAdmin");
			con.close();
			rs.close();
			return empType;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return "ERROR";
		}	
	}
	public String[] employeeInfo(int eid) {
		try {
			ApplicationDB db = new ApplicationDB();	
			Connection con = db.getConnection();
			String query = "select first_name,last_name from Employees where employeeID = (?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, Integer.toString(eid));
			ResultSet rs = ps.executeQuery();
			rs.next();
			String first = rs.getString("first_name");
			String last = rs.getString("last_name");
			con.close();
			rs.close();
			return new String[] {first,last};
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new String[] {"ERROR","ERROR"};
		}	
	}
	public int userNameExistence(String u) {
		try {
			ApplicationDB db = new ApplicationDB();	
			Connection con = db.getConnection();
			String check = "select count(*) custCount from Customers where username = (?)";
			PreparedStatement ps = con.prepareStatement(check);
			ps.setString(1, u);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int userCount = rs.getInt("custCount");
			con.close();
			rs.close();
			return userCount;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}
	public int createCustomerUser(String fn, String ln, String email, String uname, String pw) {
		
		try {
			ApplicationDB db = new ApplicationDB();	
			Connection con = db.getConnection();
			Statement stmt = con.createStatement();
			String query ="SET SESSION TRANSACTION ISOLATION LEVEL READ UNCOMMITTED";
			stmt.executeQuery(query);
			query ="Select @newID := MAX(customerID) + 1 FROM Customers";
			stmt.executeQuery(query);
			query = "insert into Customers (customerID,first_name,last_name,email,username,password_customer) values(@newID,(?),(?),(?),(?),(?))";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, fn);
			ps.setString(2, ln);
			ps.setString(3, email);
			ps.setString(4, uname);
			ps.setString(5, pw);
			int rows = ps.executeUpdate();
			query ="SET SESSION TRANSACTION ISOLATION LEVEL REPEATABLE READ";
			stmt.executeQuery(query);
			stmt.close();
			con.close();
			return rows;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}
	public String[][] getAllCustomerReps() throws Exception {
		try {
			ApplicationDB db = new ApplicationDB();	
			Connection con = db.getConnection();
			String check = "select * from Employees where isCusRep = 'Yes'";
			String preCount = "select count(*) counts from Employees where isCusRep = 'Yes'";
			PreparedStatement ps1 = con.prepareStatement(check);
			PreparedStatement ps2 = con.prepareStatement(preCount);
			ResultSet rs1 = ps1.executeQuery();
			ResultSet rs2 = ps2.executeQuery();
			rs2.next();
			String[][] custReps = new String[rs2.getInt("counts")][6];
			int arrayCount  = 0;
			while (rs1.next()) {
				custReps[arrayCount][0]=(rs1.getString("first_name"));
				custReps[arrayCount][1]=(rs1.getString("last_name"));
				custReps[arrayCount][2]=(rs1.getString("username"));
				custReps[arrayCount][3]=(rs1.getString("ssn"));
				custReps[arrayCount][4]=(rs1.getString("email"));
				custReps[arrayCount][5]=(rs1.getString("stationID"));
				arrayCount++;
			}
			con.close();
			rs1.close();
			rs2.close();
			return custReps;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

}
