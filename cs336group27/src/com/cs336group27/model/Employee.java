package com.cs336group27.model;

public class Employee {
	private String name,password,username;
	private boolean isAdmin;
	private int stationID,employeeID;
	public String getUsername() {  
	    return username;  
	}  
	public void setUsername(String name) {  
	    this.username = name;  
	}
	public String getName() {  
	    return name;  
	}  
	public void setName(String first,String last) {  
	    this.name = first.concat(" ").concat(last);  
	}  
	public String getPassword() {  
	    return password;  
	}  
	public void setPassword(String password) {  
	    this.password = password;  
	}
	public boolean getAdmin() {  
	    return isAdmin;  
	}  
	public void setAdmin(boolean adm) {  
	    this.isAdmin = adm;  
	}
	public int getStationID() {
		return stationID;
	}
	public void setStationID(int id) {
		this.stationID = id;
	}
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int id) {
		this.employeeID = id;
	}
}
