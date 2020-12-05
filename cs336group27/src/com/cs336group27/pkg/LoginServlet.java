package com.cs336group27.pkg;

import com.cs336group27.model.*;
import java.io.IOException;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class ControllerServie
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ApplicationDB appDB = new ApplicationDB();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		Customer cust = new Customer();
		cust.setName(request.getParameter("uname"));
		cust.setPassword(request.getParameter("psw"));
		int checkLogin=appDB.loginCheck(cust.getName(), cust.getPassword());
		if (checkLogin == 1) {
			HttpSession session = request.getSession();
			Integer count = (Integer)session.getAttribute("COUNT");
			if(count == null) {
				count = new Integer (1);
				session.setAttribute("COUNT", count);
				session.setAttribute("cust",cust);
				RequestDispatcher rd = request.getRequestDispatcher("loginGood.jsp");
				rd.forward(request, response);
			}else {
				String message = "Login Failed. User is Already Signed In.";
				request.setAttribute("message", message);
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
			
		}else {
			String message = "Login Failed. Invalid username or password.";
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
	}

}
