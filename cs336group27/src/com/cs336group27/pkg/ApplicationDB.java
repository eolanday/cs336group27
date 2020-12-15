package com.cs336group27.pkg;
import java.io.*;
import java.math.RoundingMode;
import java.util.*;
import java.sql.*;
import java.sql.Date;

import javax.servlet.http.*;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
			String query = "select first_name,last_name,isAdmin,isCusRep from Employees where employeeID = (?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, Integer.toString(eid));
			ResultSet rs = ps.executeQuery();
			rs.next();
			String first = rs.getString("first_name");
			String last = rs.getString("last_name");
			String admin = rs.getString("isAdmin");
			String cusRep = rs.getString("isCusRep");
			con.close();
			rs.close();
			return new String[] {first,last,admin,cusRep};
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new String[] {"ERROR","ERROR"};
		}	
	}
	public int userNameExistence(String u, String type) {
		try {
			ApplicationDB db = new ApplicationDB();	
			Connection con = db.getConnection();
			String check = "";
			if(type.equals("Customer")) {
				check = "select count(*) ct from Customers where username = (?)";
			}else {
				check = "select count(*) ct from Employees where username = (?)";
			}
			PreparedStatement ps = con.prepareStatement(check);
			ps.setString(1, u);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int userCount = rs.getInt("ct");
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
			String[][] custReps = new String[rs2.getInt("counts")][7];
			int arrayCount  = 0;
			while (rs1.next()) {
				custReps[arrayCount][0]=(rs1.getString("employeeID"));
				custReps[arrayCount][1]=(rs1.getString("first_name"));
				custReps[arrayCount][2]=(rs1.getString("last_name"));
				custReps[arrayCount][3]=(rs1.getString("username"));
				custReps[arrayCount][4]=(rs1.getString("ssn"));
				custReps[arrayCount][5]=(rs1.getString("email"));
				custReps[arrayCount][6]=(rs1.getString("stationID"));
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
	public String[] getCustomerRepInfo(int eid) {
		try {
			ApplicationDB db = new ApplicationDB();	
			Connection con = db.getConnection();
			String query = "select * from Employees where employeeID = (?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, Integer.toString(eid));
			ResultSet rs = ps.executeQuery();
			rs.next();
			String[] repInfo = new String[8];		
			repInfo[0]=(rs.getString("employeeID"));
			repInfo[1]=(rs.getString("first_name"));
			repInfo[2]=(rs.getString("last_name"));
			repInfo[3]=(rs.getString("username"));
			repInfo[4]=(rs.getString("ssn"));
			repInfo[5]=(rs.getString("email"));
			repInfo[6]=(rs.getString("stationID"));
			repInfo[7]=(rs.getString("password_employee"));
			con.close();
			rs.close();
			return repInfo;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}	
	}
	public int updateCustomerRep(String id, String ssnVal, String fname, String lname, String email, String uname, String pw, String stationID) {
		try {
			ApplicationDB db = new ApplicationDB();	
			Connection con = db.getConnection();
			Statement stmt = con.createStatement();
			String query ="SET SESSION TRANSACTION ISOLATION LEVEL READ UNCOMMITTED";
			stmt.executeQuery(query);
			query = "update Employees set ssn = (?),first_name = (?), last_name = (?), email = (?), username = (?), password_employee = (?), stationID = (?) where employeeID = (?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, ssnVal);
			ps.setString(2, fname);
			ps.setString(3, lname);
			ps.setString(4, email);
			ps.setString(5, uname);
			ps.setString(6, pw);
			ps.setInt(7, Integer.parseInt(stationID));
			ps.setInt(8, Integer.parseInt(id));
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
	public int deleteCustomerRep(String id) throws Exception {
		try {
			ApplicationDB db = new ApplicationDB();	
			Connection con = db.getConnection();
			Statement stmt = con.createStatement();
			String query ="SET SESSION TRANSACTION ISOLATION LEVEL READ UNCOMMITTED";
			stmt.executeQuery(query);
			query = "delete from Employees where employeeID = (?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(id));
			int rows = ps.executeUpdate();
			query ="SET SESSION TRANSACTION ISOLATION LEVEL REPEATABLE READ";
			stmt.executeQuery(query);
			stmt.close();
			con.close();
			return rows;
		}catch(Exception e) {
			throw e;
		}
	}
	public int createEmployee(String fn, String ln, String email, String uname, String pw, String ssn, String stationID, String custRep, String admin) {
		
		try {
			ApplicationDB db = new ApplicationDB();	
			Connection con = db.getConnection();
			Statement stmt = con.createStatement();
			String query ="SET SESSION TRANSACTION ISOLATION LEVEL READ UNCOMMITTED";
			stmt.executeQuery(query);
			query ="Select @newID := MAX(employeeID) + 1 FROM Employees";
			stmt.executeQuery(query);
			query = "insert into Employees (employeeID,ssn,first_name,last_name,email,username,password_employee,stationID,isAdmin,isCusRep) values(@newID,(?),(?),(?),(?),(?),(?),(?),(?),(?))";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, ssn);
			ps.setString(2, fn);
			ps.setString(3, ln);
			ps.setString(4, email);
			ps.setString(5, uname);
			ps.setString(6, pw);
			ps.setInt(7, Integer.parseInt(stationID));
			ps.setString(8, custRep);
			ps.setString(9, admin);
			int rows = ps.executeUpdate();
			if (rows <= 0) {
				return -1;
			}
			query = "select employeeID from Employees where username = (?)";
			ps = con.prepareStatement(query);
			ps.setString(1, uname);
			ResultSet rs = ps.executeQuery();
			rs.next();
			rows=(rs.getInt("employeeID"));
			query ="SET SESSION TRANSACTION ISOLATION LEVEL REPEATABLE READ";
			stmt.executeQuery(query);
			stmt.close();
			rs.close();
			con.close();
			return rows;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}
	public String[][] getMonthlyReport() throws Exception {
		try {
			ApplicationDB db = new ApplicationDB();	
			Connection con = db.getConnection();
			String check = "select MONTH(reservation_date) as month,Round(sum(total_fare),2) TotalFare, count(*) ReservationCount from Reservations group by month order by month";
			String preCount = "select count(distinct(MONTH(reservation_date))) as monthCount from Reservations";
			PreparedStatement ps1 = con.prepareStatement(check);
			PreparedStatement ps2 = con.prepareStatement(preCount);
			ResultSet rs1 = ps1.executeQuery();
			ResultSet rs2 = ps2.executeQuery();
			rs2.next();
			String[][] monthReport = new String[rs2.getInt("monthCount")][3];
			int arrayCount  = 0;
			while (rs1.next()) {
				monthReport[arrayCount][0]=(Integer.toString(rs1.getInt("month")));
				monthReport[arrayCount][1]=(Float.toString(rs1.getFloat("TotalFare")));
				monthReport[arrayCount][2]=(Integer.toString(rs1.getInt("ReservationCount")));
				arrayCount++;
			}
			con.close();
			rs1.close();
			rs2.close();
			return monthReport;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
	public String[][] getReservationList(String by) throws Exception {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			ApplicationDB db = new ApplicationDB();	
			Connection con = db.getConnection();
			String check = "";
			if(by.equals("customer")) {
				check = "select * from Reservations res inner join Customers cust on res.customerid = cust.customerID order by cust.customerID;";
			}else {
				//(by.equals("transit"))
				check = "select * from Reservations res inner join Customers cust on res.customerid = cust.customerID order by res.scheduleID;";
			}
			String preCount = "select count(*) tupleCount from Reservations";
			PreparedStatement ps1 = con.prepareStatement(check);
			PreparedStatement ps2 = con.prepareStatement(preCount);
			ResultSet rs1 = ps1.executeQuery();
			ResultSet rs2 = ps2.executeQuery();
			rs2.next();
			String[][] resList = new String[rs2.getInt("tupleCount")][9];
			int arrayCount  = 0;
			while (rs1.next()) {
				resList[arrayCount][0]=(Integer.toString(rs1.getInt("reservation_number")));
				resList[arrayCount][1]=(formatter.format(rs1.getDate("reservation_date")));
				resList[arrayCount][2]=(Integer.toString(rs1.getInt("total_fare")));
				resList[arrayCount][3]=(Integer.toString(rs1.getInt("trainID")));
				resList[arrayCount][4]=(Integer.toString(rs1.getInt("scheduleID")));
				resList[arrayCount][5]=(Integer.toString(rs1.getInt("customerid")));
				resList[arrayCount][6]=((rs1.getString("reservation_type")));
				resList[arrayCount][7]=((rs1.getString("first_name")));
				resList[arrayCount][8]=((rs1.getString("last_name")));
				arrayCount++;
			}
			con.close();
			rs1.close();
			rs2.close();
			return resList;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
	public String[][] getRevenueList(String by) throws Exception {
		try {
			//SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			ApplicationDB db = new ApplicationDB();	
			Connection con = db.getConnection();
			String check = "";
			String preCount= "";
			String[][] resList;
			ResultSet rs1,rs2;
			if(by.equals("customer")) {
				check = "select cust.customerID, first_name, last_name,round(sum(total_fare),2) fare  from Reservations res inner join Customers cust on res.customerid = cust.customerID group by cust.customerID order by fare desc";
				preCount = "select count(distinct cust.customerID) tupleCount from Reservations res inner join Customers cust on res.customerid = cust.customerID";
				PreparedStatement ps1 = con.prepareStatement(check);
				PreparedStatement ps2 = con.prepareStatement(preCount);
				rs1 = ps1.executeQuery();
				rs2 = ps2.executeQuery();
				rs2.next();
				resList = new String[rs2.getInt("tupleCount")][4];
				int arrayCount  = 0;
				while (rs1.next()) {
					resList[arrayCount][0]=(Integer.toString(rs1.getInt("customerID")));
					resList[arrayCount][1]=(rs1.getString("first_name"));
					resList[arrayCount][2]=(rs1.getString("last_name"));
					resList[arrayCount][3]=(Float.toString(rs1.getFloat("fare")));
					arrayCount++;
				}
			}else if(by.equals("besttransitlines")) {
				check = "select res.scheduleID,count(reservation_number) resCount from Reservations res inner join Customers cust on res.customerid = cust.customerID group by res.scheduleID order by resCount desc limit 5;";
				preCount = "select count(distinct scheduleID) tupleCount from Reservations res inner join Customers cust on res.customerid = cust.customerID";
				PreparedStatement ps1 = con.prepareStatement(check);
				PreparedStatement ps2 = con.prepareStatement(preCount);
				rs1 = ps1.executeQuery();
				rs2 = ps2.executeQuery();
				rs2.next();
				resList = new String[rs2.getInt("tupleCount")][2];
				int arrayCount  = 0;
				while (rs1.next()) {
					resList[arrayCount][0]=(Integer.toString(rs1.getInt("scheduleID")));
					resList[arrayCount][1]=(Float.toString(rs1.getFloat("resCount")));
					arrayCount++;
				}
			}else{
				//(by.equals("transit"))
				check = "select scheduleID,round(sum(total_fare),2) fare  from Reservations res inner join Customers cust on res.customerid = cust.customerID group by scheduleID order by fare desc";
				preCount = "select count(distinct scheduleID) tupleCount from Reservations res inner join Customers cust on res.customerid = cust.customerID";
				PreparedStatement ps1 = con.prepareStatement(check);
				PreparedStatement ps2 = con.prepareStatement(preCount);
				rs1 = ps1.executeQuery();
				rs2 = ps2.executeQuery();
				rs2.next();
				resList = new String[rs2.getInt("tupleCount")][2];
				int arrayCount  = 0;
				while (rs1.next()) {
					resList[arrayCount][0]=(Integer.toString(rs1.getInt("scheduleID")));
					resList[arrayCount][1]=(Float.toString(rs1.getFloat("fare")));

					arrayCount++;
				}
			}
			con.close();
			rs1.close();
			rs2.close();
			return resList;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
	public String[][] getPortfolio(String user, String type) throws Exception {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			ApplicationDB db = new ApplicationDB();	
			Connection con = db.getConnection();
			String check = "";
			String s = "";
			String preCount = "";
			String[][] resList;
			ResultSet rs1,rs2;
			long millis=System.currentTimeMillis();  
			Date now = new java.sql.Date(millis); 
			System.out.println(type + "two");
			
			check = "select * from Reservations res inner join Customers cust on res.customerid = cust.customerID inner join Train_Schedule ts on res.scheduleID = ts.scheduleID inner join Stops sp on ts.origin = sp.stationName where cust.username = (?) and Date(travelDate) < (?) order by ts.travelDate, sp.arrival_time;";
			s = "Reservations res inner join Customers cust on res.customerid = cust.customerID inner join Train_Schedule ts on res.scheduleID = ts.scheduleID inner join Stops sp on ts.origin = sp.stationName where cust.username = (?) and Date(travelDate) < (?) order by ts.travelDate, sp.arrival_time;";
			preCount = "select count(*) tupleCount from " + s;
			PreparedStatement ps1 = con.prepareStatement(check);
			ps1.setString(1, user);
			ps1.setDate(2, now);
			PreparedStatement ps2 = con.prepareStatement(preCount);
			ps2.setString(1, user);
			ps2.setDate(2, now);
			rs1 = ps1.executeQuery();
			rs2 = ps2.executeQuery();
			rs2.next();
			resList = new String[rs2.getInt("tupleCount")][13];
			int arrayCount  = 0;
			while (rs1.next()) {
				resList[arrayCount][0]=(Integer.toString(rs1.getInt("reservation_number")));
				resList[arrayCount][1]=(formatter.format(rs1.getDate("reservation_date")));
				resList[arrayCount][2]=(formatter.format(rs1.getDate("travelDate")));
				resList[arrayCount][3]=((rs1.getString("reservation_type")));
				resList[arrayCount][4]=((rs1.getString("transitLine")));
				resList[arrayCount][5]=(Integer.toString(rs1.getInt("trainID")));
				resList[arrayCount][6]=((rs1.getString("origin")));
				resList[arrayCount][7]=((rs1.getString("destination")));
				resList[arrayCount][8]=(formatter.format(rs1.getTimestamp("arrival_time")));
				resList[arrayCount][9]=(formatter.format(rs1.getTimestamp("departure_time")));
				resList[arrayCount][10]=(Integer.toString(rs1.getInt("total_fare")));
				resList[arrayCount][11]=((rs1.getString("first_name")));
				resList[arrayCount][12]=((rs1.getString("last_name")));

				arrayCount++;
			}

			
			
			
			
			
			
			
			
			
			
			
			
			if(type.equals("past")) {
				check = "select * from Reservations res inner join Customers cust on res.customerid = cust.customerID inner join Train_Schedule ts on res.scheduleID = ts.scheduleID inner join Stops sp on ts.origin = sp.stationName where cust.username = (?) and Date(travelDate) < (?) order by ts.travelDate, sp.arrival_time;";
				s = "Reservations res inner join Customers cust on res.customerid = cust.customerID inner join Train_Schedule ts on res.scheduleID = ts.scheduleID inner join Stops sp on ts.origin = sp.stationName where cust.username = (?) and Date(travelDate) < (?) order by ts.travelDate, sp.arrival_time;";
				preCount = "select count(*) tupleCount from " + s;
				PreparedStatement ps1 = con.prepareStatement(check);
				ps1.setString(1, user);
				ps1.setDate(2, now);
				PreparedStatement ps2 = con.prepareStatement(preCount);
				ps2.setString(1, user);
				ps2.setDate(2, now);
				rs1 = ps1.executeQuery();
				rs2 = ps2.executeQuery();
				rs2.next();
				resList = new String[rs2.getInt("tupleCount")][13];
				int arrayCount  = 0;
				while (rs1.next()) {
					resList[arrayCount][0]=(Integer.toString(rs1.getInt("reservation_number")));
					resList[arrayCount][1]=(formatter.format(rs1.getDate("reservation_date")));
					resList[arrayCount][2]=(formatter.format(rs1.getDate("travelDate")));
					resList[arrayCount][3]=((rs1.getString("reservation_type")));
					resList[arrayCount][4]=((rs1.getString("transitLine")));
					resList[arrayCount][5]=(Integer.toString(rs1.getInt("trainID")));
					resList[arrayCount][6]=((rs1.getString("origin")));
					resList[arrayCount][7]=((rs1.getString("destination")));
					resList[arrayCount][8]=(formatter.format(rs1.getTimestamp("arrival_time")));
					resList[arrayCount][9]=(formatter.format(rs1.getTimestamp("departure_time")));
					resList[arrayCount][10]=(Integer.toString(rs1.getInt("total_fare")));
					resList[arrayCount][11]=((rs1.getString("first_name")));
					resList[arrayCount][12]=((rs1.getString("last_name")));

					arrayCount++;
				}

			}else{
				check = "select * from Reservations res inner join Customers cust on res.customerid = cust.customerID inner join Train_Schedule ts on res.scheduleID = ts.scheduleID inner join Stops sp on ts.origin = sp.stationName where cust.username = (?) and Date(travelDate) >= (?) order by ts.travelDate, sp.arrival_time;";
				s = "Reservations res inner join Customers cust on res.customerid = cust.customerID inner join Train_Schedule ts on res.scheduleID = ts.scheduleID inner join Stops sp on ts.origin = sp.stationName where cust.username = (?) and Date(travelDate) >= (?) order by ts.travelDate, sp.arrival_time;";
				preCount = "select count(*) tupleCount from " + s;
				PreparedStatement ps1 = con.prepareStatement(check);
				ps1.setString(1, user);
				ps1.setDate(2, now);
				PreparedStatement ps2 = con.prepareStatement(preCount);
				ps2.setString(1, user);
				ps2.setDate(2, now);
				rs1 = ps1.executeQuery();
				rs2 = ps2.executeQuery();
				rs2.next();
				resList = new String[rs2.getInt("tupleCount")][12];
				int arrayCount  = 0;
				while (rs1.next()) {
					resList[arrayCount][0]=(Integer.toString(rs1.getInt("reservation_number")));
					resList[arrayCount][1]=(formatter.format(rs1.getDate("reservation_date")));
					resList[arrayCount][2]=(formatter.format(rs1.getDate("travelDate")));
					resList[arrayCount][3]=((rs1.getString("reservation_type")));
					resList[arrayCount][4]=((rs1.getString("transitLine")));
					resList[arrayCount][5]=(Integer.toString(rs1.getInt("trainID")));
					resList[arrayCount][6]=((rs1.getString("origin")));
					resList[arrayCount][7]=((rs1.getString("destination")));
					resList[arrayCount][8]=(formatter.format(rs1.getTimestamp("arrival_time")));
					resList[arrayCount][9]=(formatter.format(rs1.getTimestamp("departure_time")));
					resList[arrayCount][10]=(Integer.toString(rs1.getInt("total_fare")));
					resList[arrayCount][11]=((rs1.getString("first_name")));
					resList[arrayCount][12]=((rs1.getString("last_name")));

					arrayCount++;
				}
			}
			
			
			con.close();
			rs1.close();
			rs2.close();
			return resList;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
	public int deleteReservation(String num) throws Exception {
		try {
			ApplicationDB db = new ApplicationDB();	
			Connection con = db.getConnection();
			String query = "delete from Reservations where reservation_number = (?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, num);
			int rows = ps.executeUpdate();
			con.close();
			return rows;
		}catch(Exception e) {
			throw e;
		}
	}
	public float calculateFare(String origin, String dest, String date, String type, String age, String disabled) throws Exception {
		try {
			ApplicationDB db = new ApplicationDB();	
			Connection con = db.getConnection();
			Float fare = 16.00f;
			
			
			String query = "select ts.transitLine from Train_Schedule ts inner join Stops sp  on ts.transitLine = sp.transitLine where stationName = (?) and travelDate = (?);";
			PreparedStatement ps1 = con.prepareStatement(query);
			ps1.setString(1, origin);
			ps1.setString(2, date);
			ResultSet rsa = ps1.executeQuery();
			rsa.next();
			String line = rsa.getString("transitLine");
			
			// One-Way Ticket 
			query = "select (new_fare*stop_interval) as total_fare from (select (fare/stops) as new_fare from (select fare from Train_Schedule where transitLine = (?) and travelDate = (?)) fare, (select count(*) stops from Stops where transitLine = (?)) stops) new_fare, (select (destination - origin) as stop_interval from (select stop_num as origin from Stops where transitLine = (?) and stationName = (?)) origin, (select stop_num as destination from Stops where transitLine = (?) and stationName = (?)) destination) stop_interval;";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, line);
			ps.setString(2, date);
			ps.setString(3, line);
			ps.setString(4, line);	
			ps.setString(5, origin);
			ps.setString(6, line);
			ps.setString(7, dest);		
			ResultSet rs = ps.executeQuery();
			rs.next();
			fare = rs.getFloat("total_fare");
			
			// Round Trip Ticket
			if(type.equals("round_trip")) {
				fare *= 2.0f;
			}
			
			// Discounts
			if(age.equals("0-17")) {
				fare *= 0.75f;
			}else if(age.equals("65+")){
				fare *= 0.65f;
			}
			if(!(disabled == null)) {
				fare *= 0.50f;			
			}
			DecimalFormat df = new DecimalFormat("#.##");
			df.setRoundingMode(RoundingMode.CEILING);
			String f = df.format(fare);
			fare = Float.parseFloat(f);
			con.close();
			rs.close();
			rsa.close();
			return fare;
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
public int createReservation(String origin, String dest, String date, String time, String user, String type, String age, String disable) {
		
		try {
			ApplicationDB db = new ApplicationDB();	
			Connection con = db.getConnection();
			Float fare = 0.00f;
			String query = "";
			long millis=System.currentTimeMillis();  
			Date now = new java.sql.Date(millis); 
			System.out.println(now);
			System.out.println(user);
			
			// Gets customerid to find reservation_number
			query = "select customerID from Customers where username = (?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, user);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String id = Integer.toString(rs.getInt("customerID"));

			// Get trainID, scheduleID, transitLine
			query = "select trainID, scheduleID, ts.transitLine from Train_Schedule ts inner join Stops sp  on ts.transitLine = sp.transitLine where stationName = (?) and travelDate = (?);";
			PreparedStatement ps1 = con.prepareStatement(query);
			ps1.setString(1, origin);
			ps1.setString(2, date);
			ResultSet rsa = ps1.executeQuery();
			rsa.next();
			int train = rsa.getInt("trainID");
			int sched = rsa.getInt("scheduleID");
			
			// Fare Calculator
			fare = calculateFare(origin, dest, date, type, age, disable);
			
			// Inserts reservation
			query= "insert into Reservations (reservation_date, total_fare, trainID, scheduleID, customerid, reservation_type) values((?),(?),(?),(?),(?),(?))";
			PreparedStatement ps2 = con.prepareStatement(query);
			ps2.setDate(1, now);
			ps2.setFloat(2, fare);
			ps2.setInt(3, train); 
			ps2.setInt(4, sched);
			ps2.setString(5, id);
			ps2.setString(6, type);
			int rows = ps2.executeUpdate();
			if (rows <= 0) {
				return -1;
			}
			
			// Get highest res_num
			query = "select reservation_number from Reservations order by reservation_number desc";
			PreparedStatement ps3 = con.prepareStatement(query);
			ResultSet rsb = ps3.executeQuery();
			rsb.next();
			rows = rsb.getInt("reservation_number"); 
			
			rs.close();
			rsa.close();
			rsb.close();
			con.close();
			return rows;
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	public String[][] getTripInfo(String origin, String destination, String travelDate) throws Exception{
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			ApplicationDB db = new ApplicationDB();	
			Connection con = db.getConnection();
			
			String tripInfo = "SELECT t.transitLine, t.trainID, t.travelDate, s.stationName, s.arrival_time, s.departure_time, t.fare, s.stop_num FROM trainsys1.Stops s, trainsys1.Train_Schedule t WHERE (s.stationName = '"+origin+"' OR s.stationName = '"+destination+"') AND s.transitLine = t.transitLine AND t.travelDate ='"+travelDate+"';";
			String count = "SELECT COUNT(*) tuplesCount FROM trainsys1.Stops s, trainsys1.Train_Schedule t WHERE (s.stationName = '"+origin+"' OR s.stationName = '"+destination+"') AND s.transitLine = t.transitLine AND t.travelDate ='"+travelDate+"';";
			String fare = "SELECT t.fare FROM trainsys1.Train_Schedule t, trainsys1.Stops s WHERE s.stationName = '"+origin+"' AND s.transitLine = t.transitLine AND t.travelDate = '"+travelDate+"';";
			
			PreparedStatement ps1 = con.prepareStatement(tripInfo);
			PreparedStatement ps2 = con.prepareStatement(count);
			PreparedStatement ps3 = con.prepareStatement(fare);
			ResultSet rs1 = ps1.executeQuery();
			ResultSet rs2 = ps2.executeQuery();
			ResultSet rs3 = ps3.executeQuery();
			//Calculate the fare
			rs1.next();
			rs3.next();
			float pricePerStop = (rs3.getFloat("fare"))/10;
			float originNum = rs1.getInt("stop_num");
			rs1.next();
			float destNum = rs1.getInt("stop_num");
			float oneWayPrice = (destNum - originNum) * pricePerStop;
			rs1.close();
			//Re-deploying script to reset rs1
			ps1 = con.prepareStatement(tripInfo);
			rs1 = ps1.executeQuery();
			
			rs2.next();
			String[][] resList = new String[rs2.getInt("tuplesCount")][7];
			int arrayCount = 0;
			while(rs3.next()) {
				resList[arrayCount][0] = rs3.getString("transitLine");
				resList[arrayCount][1] = Integer.toString(rs3.getInt("trainID"));
				resList[arrayCount][2] = formatter.format(rs3.getDate("travelDate"));
				resList[arrayCount][3] = rs3.getString("stationName");
				resList[arrayCount][4] = rs3.getString("arrival_time");
				resList[arrayCount][5] = rs3.getString("departure_time");
				resList[arrayCount][6] = "ONE WAY PRICE: " ;//+ String.valueOf(oneWayPrice);
				
				arrayCount++;
			}
			con.close();
			rs1.close();
			rs2.close();
			rs3.close();
			return resList;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
	
	
	public String[][] getTrainSchedules(String key) throws Exception {
		try {
		    ApplicationDB db = new ApplicationDB(); 
		    Connection con = db.getConnection();
		    key = key == null ? "":key;
		    String check = "SELECT * FROM Train_Schedule" + (key == "" ? "": (" where origin='" + key + "' or destination='" + key +"'"));
		    String preCount = "select count(*) tupleCount from Train_Schedule" + (key == "" ? "": (" where origin='" + key + "' or destination='" + key +"'"));
		    PreparedStatement ps1 = con.prepareStatement(check);
		    PreparedStatement ps2 = con.prepareStatement(preCount);
		    ResultSet rs1 = ps1.executeQuery();
		    ResultSet rs2 = ps2.executeQuery();
		    rs2.next();
		    String[][] resList = new String[rs2.getInt("tupleCount")][8];
		    int arrayCount  = 0;
		    while (rs1.next()) {
			resList[arrayCount][0]=(rs1.getString("transitLine"));
			resList[arrayCount][1]=(Integer.toString(rs1.getInt("scheduleID")));
			resList[arrayCount][2]=(Integer.toString(rs1.getInt("trainID")));
			resList[arrayCount][3]=(Integer.toString(rs1.getInt("travel_time")));
			resList[arrayCount][4]=(Double.toString(Math.round(rs1.getFloat("fare") * 100.0) / 100.0));
			resList[arrayCount][5]=(rs1.getDate("travelDate").toString());
			resList[arrayCount][6]=(rs1.getString("origin"));
			resList[arrayCount][7]=(rs1.getString("destination"));
			arrayCount++;
		    }
		    con.close();
		    rs1.close();
		    rs2.close();
		    return resList;
		}catch(Exception e) {
		    System.out.println(e.getMessage());
		    throw e;
		}
	}
	
	public String[][] getTrainSchedules(String key) throws Exception {
        try {
            ApplicationDB db = new ApplicationDB(); 
            Connection con = db.getConnection();
            key = key == null ? "":key;
            String check = "SELECT * FROM Train_Schedule" + (key == "" ? "": (" where origin='" + key + "' or destination='" + key +"'"));
            String preCount = "select count(*) tupleCount from Train_Schedule" + (key == "" ? "": (" where origin='" + key + "' or destination='" + key +"'"));
            PreparedStatement ps1 = con.prepareStatement(check);
            PreparedStatement ps2 = con.prepareStatement(preCount);
            ResultSet rs1 = ps1.executeQuery();
            ResultSet rs2 = ps2.executeQuery();
            rs2.next();
            String[][] resList = new String[rs2.getInt("tupleCount")][8];
            int arrayCount  = 0;
            while (rs1.next()) {
                resList[arrayCount][0]=(rs1.getString("transitLine"));
                resList[arrayCount][1]=(Integer.toString(rs1.getInt("scheduleID")));
                resList[arrayCount][2]=(Integer.toString(rs1.getInt("trainID")));
                resList[arrayCount][3]=(Integer.toString(rs1.getInt("travel_time")));
                resList[arrayCount][4]=(Double.toString(Math.round(rs1.getFloat("fare") * 100.0) / 100.0));
                resList[arrayCount][5]=(rs1.getDate("travelDate").toString());
                resList[arrayCount][6]=(rs1.getString("origin"));
                resList[arrayCount][7]=(rs1.getString("destination"));
                arrayCount++;
            }
            con.close();
            rs1.close();
            rs2.close();
            return resList;
        }catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
	public boolean addTrainSchedule(String tline, String fare, String trainID, String tdate) throws Exception {
        try {
            ApplicationDB db = new ApplicationDB(); 
            Connection con = db.getConnection();
            
            String add = "INSERT INTO Train_Schedule (`transitLine`,`travel_time`,`fare`,`trainID`,`travelDate`) "
                    + "VALUES('" + tline + "',0," + fare +  "," + trainID + ",'" + tdate + "')";
            PreparedStatement ps1 = con.prepareStatement(add);
            ps1.execute();
            con.close();
            
            con = db.getConnection();
            String update = "select scheduleID, transitLine from Train_Schedule where destination is NULL;";
            String precount = "select count(*) tupleCount from Train_Schedule where destination is NULL;";
            PreparedStatement ps2 = con.prepareStatement(update);
            PreparedStatement ps3 = con.prepareStatement(precount);
            ResultSet rs1 = ps2.executeQuery();
            ResultSet rs2 = ps3.executeQuery();
            rs2.next();
            String[][] resList = new String[rs2.getInt("tupleCount")][2];
            int arrayCount  = 0;
            while (rs1.next()) {
                resList[arrayCount][0] = rs1.getString("scheduleID");
                resList[arrayCount][1] = rs1.getString("transitLine");
                arrayCount++;
            }
            con.close();
            
            for(int i = 0; i < resList.length; i++) {
                this.autoUpdateTrainSchedule(resList[i][0], resList[i][1]);
            }
            
            return true;
        }catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
	public boolean deleteTrainSchedule(String sid) throws Exception {
        try {
            ApplicationDB db = new ApplicationDB(); 
            Connection con = db.getConnection();
            
            String add = "delete from Train_Schedule where scheduleID=" + sid + ";";
            PreparedStatement ps1 = con.prepareStatement(add);
            ps1.execute();
            con.close();
            return true;
        }catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
	public boolean updateTrainSchedule(String sid, String tline, String fare, String trainID, String tdate) throws Exception {
        try {
            ApplicationDB db = new ApplicationDB(); 
            Connection con = db.getConnection();
            
            if(!tline.equals("")) {
                String add = "update Train_Schedule set transitLine='" + tline + "' where scheduleID=" + sid + ";";
                PreparedStatement ps1 = con.prepareStatement(add);
                ps1.execute();
            }
            
            if(!fare.equals("")) {
                String add = "update Train_Schedule set fare=" + fare + " where scheduleID=" + sid + ";";
                PreparedStatement ps1 = con.prepareStatement(add);
                ps1.execute();
            }
            
            if(!trainID.equals("")) {
                String add = "update Train_Schedule set trainID=" + trainID + " where scheduleID=" + sid + ";";
                PreparedStatement ps1 = con.prepareStatement(add);
                ps1.execute();
            }
            
            if(!tdate.equals("")) {
                String add = "update Train_Schedule set travelDate=;" + trainID + "' where scheduleID=" + sid + ";";
                PreparedStatement ps1 = con.prepareStatement(add);
                ps1.execute();
            }
            
            con.close();
            this.autoUpdateTrainSchedule(sid,tline);
            return true;
        }catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
	private boolean autoUpdateTrainSchedule(String sid, String tline) throws Exception {
        try {
            ApplicationDB db = new ApplicationDB();
            Connection con = db.getConnection();
            
            String check = "select * from Stops where transitLine='" + tline + "' order by arrival_time asc;";
            String preCount = "select count(*) tupleCount from Stops where transitLine='" + tline + "';";
            PreparedStatement ps1 = con.prepareStatement(check);
            PreparedStatement ps2 = con.prepareStatement(preCount);
            ResultSet rs1 = ps1.executeQuery();
            ResultSet rs2 = ps2.executeQuery();
            rs2.next();
            Time[] resList = new Time[rs2.getInt("tupleCount")];
            String[] resList2 = new String[rs2.getInt("tupleCount")];
            int arrayCount  = 0;
            while (rs1.next()) {
                resList[arrayCount] = rs1.getTime("arrival_time");
                resList2[arrayCount] = rs1.getString("stationName");
                arrayCount++;
            }
            rs1.close();
            rs2.close();
            
            long time = Math.abs(resList[0].getTime() - resList[resList.length-1].getTime()) / 60000;
            String stime = Integer.toString((int)time);
            
            String update = "update Train_Schedule set travel_time=" + stime + ", origin='" + resList2[0] + "', destination='" +
                    resList2[resList2.length-1] + "' where scheduleID=" + sid + ";";
            PreparedStatement ps3 = con.prepareStatement(update);
            ps3.execute();
            con.close();
            return true;
        }catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
	public String[][] getQuestions(String keyword) throws Exception {
        try {
            ApplicationDB db = new ApplicationDB(); 
            Connection con = db.getConnection();
            keyword = keyword == null ? "":keyword;
            String check = "select * from Questions where question like \"%" + keyword + "%\";";
            String preCount = "select count(*) tupleCount from Questions where question like \"%" + keyword + "%\";";
            PreparedStatement ps1 = con.prepareStatement(check);
            PreparedStatement ps2 = con.prepareStatement(preCount);
            ResultSet rs1 = ps1.executeQuery();
            ResultSet rs2 = ps2.executeQuery();
            rs2.next();
            String[][] resList = new String[rs2.getInt("tupleCount")][7];
            int arrayCount  = 0;
            while (rs1.next()) {
                resList[arrayCount][0]=(Integer.toString(rs1.getInt("qid")));
                resList[arrayCount][1]=(Integer.toString(rs1.getInt("customerID")));
                resList[arrayCount][2]=(rs1.getObject("employeeID")!=null ? Integer.toString(rs1.getInt("employeeID")):"Not Available");
                resList[arrayCount][3]=(rs1.getString("question"));
                resList[arrayCount][4]=(rs1.getObject("reply")!=null ? rs1.getString("reply"):"Not Available");
                
                Timestamp temp0 = rs1.getTimestamp("askDate");
                if(temp0 != null) {
                    String temp = temp0.toGMTString();
                    resList[arrayCount][5]=(temp.substring(0, temp.length()-4));
                } else {
                    resList[arrayCount][5]="Not Available";
                }
                temp0 = rs1.getTimestamp("replyDate");
                if(temp0 != null) {
                    String temp = temp0.toGMTString();
                    resList[arrayCount][6]=(temp.substring(0, temp.length()-4));
                } else {
                    resList[arrayCount][6]="Not Available";
                }
                arrayCount++;
            }
            con.close();
            rs1.close();
            rs2.close();
            return resList;
        }catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
	public boolean addQuestion(String eid, String question) throws Exception {
        try {
            ApplicationDB db = new ApplicationDB(); 
            Connection con = db.getConnection();
            
            String check = "select * from Customers where username='" + eid + "';";
            PreparedStatement ps2 = con.prepareStatement(check);
            ResultSet rs2 = ps2.executeQuery();
            rs2.next();
            eid = Integer.toString(rs2.getInt("customerID"));
            rs2.close();
            con.close();
            
            con = db.getConnection();
            
            String add = "INSERT INTO Questions (`customerID`,`question`,`askDate`) "
                    + "VALUES(" + eid + ",'" + question + "', CURRENT_TIMESTAMP());";
            PreparedStatement ps1 = con.prepareStatement(add);
            ps1.execute();
            
            con.close();
            return true;
        }catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
	public boolean replyQuestion(int eid, String qid, String reply) throws Exception {
        try {
            ApplicationDB db = new ApplicationDB(); 
            Connection con = db.getConnection();
            
            String add = "update Questions set employeeID=" + eid + ",reply='" + reply + "',replyDate=CURRENT_TIMESTAMP()"
                    + "where qid=" + qid + ";";
            PreparedStatement ps1 = con.prepareStatement(add);
            ps1.execute();
            
            con.close();
            return true;
        }catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
	public String[][] getRepReservations(String sid, String date) throws Exception {
	    try {
            ApplicationDB db = new ApplicationDB(); 
            Connection con = db.getConnection();
            
            String add = "SELECT r.reservation_number, c.customerID, c.first_name, c.last_name, "
                    + "r.reservation_date, r.total_fare, r.reservation_type, ts.transitLine "
                    + "FROM Reservations r "
                    + "join Train_Schedule ts on r.scheduleID=ts.scheduleID "
                    + "join Customers c on c.customerID=r.customerid";
            String preCount = "select count(*) tupleCount " 
                    + "FROM Reservations r "
                    + "join Train_Schedule ts on r.scheduleID=ts.scheduleID "
                    + "join Customers c on c.customerID=r.customerid";
            if(sid != null && !sid.equals("")) {
                add = add + " where ts.transitLine='" + sid + "'";
                preCount = preCount + " where ts.transitLine='" + sid + "'";
                if(date != null && !date.equals("")) {
                    add = add + " and r.reservation_date='" + date + "'";
                    preCount = preCount + " and r.reservation_date='" + date + "'";
                }
            } else if(date != null && !date.equals("")) {
                add = add + " where r.reservation_date='" + date + "'";
                preCount = preCount + " where r.reservation_date='" + date + "'";
            }
            System.out.println(add);
            PreparedStatement ps1 = con.prepareStatement(add);
            PreparedStatement ps2 = con.prepareStatement(preCount);
            ResultSet rs1 = ps1.executeQuery();
            ResultSet rs2 = ps2.executeQuery();
            rs2.next();
            String[][] resList = new String[rs2.getInt("tupleCount")][8];
            int arrayCount  = 0;
            while (rs1.next()) {
                resList[arrayCount][0]=(Integer.toString(rs1.getInt("reservation_number")));
                resList[arrayCount][1]=(Integer.toString(rs1.getInt("customerID")));
                resList[arrayCount][2]=(rs1.getString("first_name"));
                resList[arrayCount][3]=(rs1.getString("last_name"));
                resList[arrayCount][4]=(rs1.getDate("reservation_date").toString());
                resList[arrayCount][5]=(Double.toString(Math.round(rs1.getFloat("total_fare")  * 100.0) / 100.0));
                resList[arrayCount][6]=(rs1.getString("reservation_type"));
                resList[arrayCount][7]=(rs1.getString("transitLine"));
                arrayCount++;
            }
            
            rs1.close();
            rs2.close();
            con.close();
            return resList;
        }catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
	}
}
