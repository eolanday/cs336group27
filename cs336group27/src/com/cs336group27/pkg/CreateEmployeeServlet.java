package com.cs336group27.pkg;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateEmployee
 */
@WebServlet("/CreateEmployee")
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
			if(request.getParameter("psw").equals(request.getParameter("pswc"))) {
				if(request.getParameter("email").contains("@")) {
					int checkUName=appDB.userNameExistence(request.getParameter("uname"),"Employee");
					if(checkUName == 0) {
						String custRep = "No";
						String admin = "No";
						 if (request.getParameter("custrep")!=null) {
							 custRep = "Yes";
						 }
						 if(request.getParameter("admin") != null) {
							 admin = "Yes";
						 }
						 int empID = appDB.createEmployee(request.getParameter("fname"), request.getParameter("lname"), request.getParameter("email"), request.getParameter("uname"), request.getParameter("psw"),request.getParameter("ssn"),request.getParameter("stationID"),custRep,admin);
						 if(empID > 0) {
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
			String message = "Must Select Employee Type";
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("createEmployee.jsp");
			rd.forward(request, response);
		}
	}
}
