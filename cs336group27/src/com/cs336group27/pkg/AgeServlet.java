package com.cs336group27.pkg;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AgeServlet
 */
@WebServlet("/AgeServlet")
public class AgeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplicationDB appDB = new ApplicationDB();	
  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgeServlet() {
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
			 if(request.getParameter("age").equals("0-17")) {
				String message = "Child: 25% Discount";
				request.setAttribute("message", message);
			 }else if(request.getParameter("age").equals("18-64")) {
					String message = "";
					request.setAttribute("message", message);
			 }else if(request.getParameter("age").equals("65+")) {
					String message = "Senior: 35% Discount";
					request.setAttribute("message", message);
			 }
			 if(request.getParameter("disableConfirm") == null) {
					String message1 = "";
					request.setAttribute("message1", message1);
			 } else {
					String message1 = "Disabled: 50% Discount";
					request.setAttribute("message1", message1);
				}
		}catch(Exception e){
			String message = e.getMessage();
			request.setAttribute("message", message);
			String message1 = e.getMessage();
			request.setAttribute("message1", message1);
			
		} finally {
			//String[][] r = appDB.getDiscount(request.getParameter("age","disableConfirm"));
			RequestDispatcher rd = request.getRequestDispatcher("CusMakeReservation.jsp");
			rd.forward(request, response);
		}
	}
}
