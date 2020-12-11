package com.cs336group27.pkg;
import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.http.*;
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
}
