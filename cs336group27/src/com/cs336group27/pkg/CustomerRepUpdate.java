package com.cs336group27.pkg;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerRepUpdate
 */
@WebServlet("/CustomerRepUpdate")
public class CustomerRepUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplicationDB appDB = new ApplicationDB();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerRepUpdate() {
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
		if(request.getParameter("email").contains("@")) {
			System.out.println(request.getParameter("manager"));
			int managerCheck = appDB.managerExistence(request.getParameter("manager"));
			if(managerCheck >= 1) {
				int stationCheck = appDB.stationExistence(request.getParameter("stationID"));
				if(stationCheck >= 1) {
					 int rowCount = appDB.updateCustomerRep(request.getParameter("empID"), request.getParameter("ssn"), request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("email"), request.getParameter("uname"), request.getParameter("password"), request.getParameter("stationID"));
					 if(rowCount > 0) {
						rowCount = appDB.setManager(request.getParameter("empID"), request.getParameter("manager"));
						if(rowCount>0) {
							String message = "Customer Representative Updated!";
							request.setAttribute("message", message);
							RequestDispatcher rd = request.getRequestDispatcher("adminCustRepEdit.jsp");
							rd.forward(request, response);
						}else {
							String message = "FATAL DATABASE ERROR";
							request.setAttribute("message", message);
							RequestDispatcher rd = request.getRequestDispatcher("adminCustRepEdit.jsp");
							rd.forward(request, response);
							
						}

					 }else {
						String message = "FATAL DATABASE ERROR";
						request.setAttribute("message", message);
						RequestDispatcher rd = request.getRequestDispatcher("adminCustRepEdit.jsp");
						rd.forward(request, response);
					 }
					
					
				}else {
					String message = "Invalid Station";
					request.setAttribute("message", message);
					RequestDispatcher rd = request.getRequestDispatcher("adminCustRepEdit.jsp");
					rd.forward(request, response);
					
				}

			}else {
				String message = "Invalid Manager";
				request.setAttribute("message", message);
				RequestDispatcher rd = request.getRequestDispatcher("adminCustRepEdit.jsp");
				rd.forward(request, response);
			}
		}else {
			String message = "Invalid Email";
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("adminCustRepEdit.jsp");
			rd.forward(request, response);
		}

	}

}
