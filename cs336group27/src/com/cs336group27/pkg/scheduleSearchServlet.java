package com.cs336group27.pkg;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class scheduleSearchServlet
 */
@WebServlet("/scheduleSearchServlet")
public class scheduleSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public scheduleSearchServlet() {
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
		//Text Boxes
		request.setAttribute("start", request.getParameter("origin"));
		request.setAttribute("end", request.getParameter("destination"));
		request.setAttribute("travelDate", request.getParameter("travelDate"));
		
		//Radio Button value for sorting
		request.setAttribute("sortType", request.getParameter("sortType"));
		
		RequestDispatcher rd = request.getRequestDispatcher("searchSchedule.jsp");
		rd.forward(request, response);
	}
}
