package com.cs336group27.pkg;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.cs336group27.model.Employee;

/**
 * Servlet implementation class CreateEmployeeServlet
 */
@WebServlet("/CreateEmployeeServlet")
public class CreateEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplicationDB appDB = new ApplicationDB();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		if(request.getParameter("custrep")!=null || request.getParameter("admin")!=null) {
			if(request.getParameter("custrep")!=null && request.getParameter("manager")!=null) {
				int checkManager = appDB.managerExistence(request.getParameter("manager"));
				if(checkManager >= 1) {
					if(request.getParameter("psw").equals(request.getParameter("pswc"))) {
						if(request.getParameter("email").contains("@")) {
							int checkUName=appDB.userNameExistence(request.getParameter("uname"),"Employee");
							if(checkUName == 0) {
								int checkstationID = appDB.stationExistence(request.getParameter("stationID"));
								if (checkstationID >= 1){
									String custRep = "No";
									String admin = "No";
									 if (request.getParameter("custrep")!=null) {
										 System.out.println("not admin");
										 custRep = "Yes";
									 }
									 if(request.getParameter("admin") != null) {
										 System.out.println("admin");
										 admin = "Yes";
									 }
									 int empID = appDB.createEmployee(request.getParameter("fname"), request.getParameter("lname"), request.getParameter("email"), request.getParameter("uname"), request.getParameter("psw"),request.getParameter("ssn"),request.getParameter("stationID"),custRep,admin);
									 if(empID > 0) {
										
										if(custRep.equals("Yes")) {
											int empID1;
											if(admin.equals("Yes")) {
												 empID1 = appDB.setManager(String.valueOf(empID),String.valueOf(empID));
											}else {
												 empID1 = appDB.setManager(String.valueOf(empID),request.getParameter("manager"));
											}
											if(empID1>0) {
												String message = "User Created! Employee ID: " + empID;
												request.setAttribute("message", message);
												RequestDispatcher rd = request.getRequestDispatcher("createEmployee.jsp");
												rd.forward(request, response);
											}else {
												String message = "FATAL DATABASE ERROR";
												request.setAttribute("message", message);
												RequestDispatcher rd = request.getRequestDispatcher("createEmployee.jsp");
												rd.forward(request, response);
											}
										}else {
											String message = "User Created! Employee ID: " + empID;
											request.setAttribute("message", message);
											RequestDispatcher rd = request.getRequestDispatcher("createEmployee.jsp");
											rd.forward(request, response);
										} 
									 }else {
										String message = "FATAL DATABASE ERROR";
										request.setAttribute("message", message);
										RequestDispatcher rd = request.getRequestDispatcher("createEmployee.jsp");
										rd.forward(request, response);
									 }
									
								}else {
									if(checkUName == -1) {
										String message = "FATAL DATABASE ERROR";
										request.setAttribute("message", message);
										RequestDispatcher rd = request.getRequestDispatcher("createEmployee.jsp");
										rd.forward(request, response);
									}else {
										String message = "Station Does Not Exist";
										request.setAttribute("message", message);
										RequestDispatcher rd = request.getRequestDispatcher("createEmployee.jsp");
										rd.forward(request, response);
									}
									
								}

							}else {
								if(checkUName == -1) {
									String message = "FATAL DATABASE ERROR";
									request.setAttribute("message", message);
									RequestDispatcher rd = request.getRequestDispatcher("createEmployee.jsp");
									rd.forward(request, response);
								}else {
									String message = "Username Already Exists";
									request.setAttribute("message", message);
									RequestDispatcher rd = request.getRequestDispatcher("createEmployee.jsp");
									rd.forward(request, response);
								}
							}
						}else {
							String message = "Invalid Email";

							request.setAttribute("message", message);
							RequestDispatcher rd = request.getRequestDispatcher("createEmployee.jsp");
							rd.forward(request, response);
						}

					}else {
						String message = "Passwords Do Not Match";
						request.setAttribute("message", message);
						RequestDispatcher rd = request.getRequestDispatcher("createEmployee.jsp");
						rd.forward(request, response);
					}
					
				}else {
					String message = "Customer Rep Must Have a Valid Manager";

					request.setAttribute("message", message);
					RequestDispatcher rd = request.getRequestDispatcher("createEmployee.jsp");
					rd.forward(request, response);
				}
			}else {
				String message = "Customer Rep Must Have a Valid Manager";

				request.setAttribute("message", message);
				RequestDispatcher rd = request.getRequestDispatcher("createEmployee.jsp");
				rd.forward(request, response);
			}
		}else {
			String message = "Must Select Employee Type";
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("createEmployee.jsp");
			rd.forward(request, response);
		}
	}
}


