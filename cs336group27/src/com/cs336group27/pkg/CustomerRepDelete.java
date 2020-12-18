package com.cs336group27.pkg;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerRepDelete
 */
@WebServlet("/CustomerRepDelete")
public class CustomerRepDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplicationDB appDB = new ApplicationDB();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerRepDelete() {
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
		try {
			if(request.getParameter("deleteConfirm")!=null) {
				 int rowCount = appDB.deleteCustomerRep(request.getParameter("empID"));
				 if(rowCount > 0) {
					String message = "Customer Representative Deleted!";
					request.setAttribute("message", message);
					RequestDispatcher rd = request.getRequestDispatcher("adminCustRep.jsp");
					rd.forward(request, response);
				 }else {
					String message = "FATAL DATABASE ERROR";
					request.setAttribute("message1", message);
					RequestDispatcher rd = request.getRequestDispatcher("adminCustRepEdit.jsp");
					rd.forward(request, response);
				 }
			}else {
				String message = "Please Confirm Account Deletion";
				request.setAttribute("message1", message);
				RequestDispatcher rd = request.getRequestDispatcher("adminCustRepEdit.jsp");
				rd.forward(request, response);
			}
		}catch(Exception e){
			String message = e.getMessage();
			request.setAttribute("message1", message);
			RequestDispatcher rd = request.getRequestDispatcher("adminCustRepEdit.jsp");
			rd.forward(request, response);
		}


	}

}
