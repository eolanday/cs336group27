package com.cs336group27.pkg;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SwitchView
 */
@WebServlet("/SwitchView")
public class SwitchView extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ApplicationDB appDB = new ApplicationDB();
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SwitchView() {
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
		request.setAttribute("currentUser", request.getParameter("user"));
		request.setAttribute("type", request.getParameter("switch"));
		String type = (String)request.getAttribute("type");
		System.out.println(type + "one");
		RequestDispatcher rd = request.getRequestDispatcher("ViewPortfolio.jsp");
		rd.forward(request, response);
	}

}
