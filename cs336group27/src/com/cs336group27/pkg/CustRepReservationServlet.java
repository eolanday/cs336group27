package com.cs336group27.pkg;

import com.cs336group27.model.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CustomerRepDelete
 */
@WebServlet("/CustRepReservationServlet")
public class CustRepReservationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ApplicationDB appDB = new ApplicationDB();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustRepReservationServlet() {
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
        request.setAttribute("sid", (String)request.getParameter("sid"));
        request.setAttribute("date", (String)request.getParameter("date"));
        RequestDispatcher rd = request.getRequestDispatcher("custRepReservations.jsp");
        rd.forward(request, response);
    }
}
