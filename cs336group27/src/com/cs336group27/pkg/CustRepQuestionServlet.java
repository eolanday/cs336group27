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
@WebServlet("/CustRepQuestionServlet")
public class CustRepQuestionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ApplicationDB appDB = new ApplicationDB();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustRepQuestionServlet() {
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
        String button = request.getParameter("button");
        Employee cust = (Employee)request.getSession().getAttribute("employee");
        
        if(button.equals("reply")) {
            try {
                appDB.replyQuestion(cust.getEmployeeID(), request.getParameter("qid"), request.getParameter("reply"));
            } catch (Exception e) { 
                e.printStackTrace(); 
            }
        } else {
            request.setAttribute("keyword", (String)request.getParameter("keyword"));
        }
        RequestDispatcher rd = request.getRequestDispatcher("custRepQuestion.jsp");
        rd.forward(request, response);
    }
}
