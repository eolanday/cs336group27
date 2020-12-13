package com.cs336group27.pkg;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CusDeleteReservation
 */
@WebServlet("/CusDeleteReservation")
public class CusDeleteReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplicationDB appDB = new ApplicationDB();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CusDeleteReservation() {
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
				 int rowCount = appDB.deleteReservation(request.getParameter("res_num"));
				 if(rowCount > 0) {
					String message = "Reservation Deleted!";
					request.setAttribute("message", message);
					RequestDispatcher rd = request.getRequestDispatcher("resDeleteConfirm.jsp");
					rd.forward(request, response);
				 }
			
		}catch(Exception e){
			String message = e.getMessage();
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("resDeleteConfirm.jsp");
			rd.forward(request, response);
		}


	}
	

}
