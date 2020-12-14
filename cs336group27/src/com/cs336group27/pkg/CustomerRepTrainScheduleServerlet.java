package com.cs336group27.pkg;

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
@WebServlet("/CustomerRepTrainScheduleServerlet")
public class CustomerRepTrainScheduleServerlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ApplicationDB appDB = new ApplicationDB();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerRepTrainScheduleServerlet() {
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
        if(button.equals("add")) {
            try {
                appDB.addTrainSchedule(request.getParameter("atline"), request.getParameter("afare"), 
                        request.getParameter("atid"), request.getParameter("adate"));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if(button.equals("delete")) {
            try {
                appDB.deleteTrainSchedule(request.getParameter("dsid"));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if(button.equals("update")) {
            try {
                appDB.updateTrainSchedule(request.getParameter("usid"),request.getParameter("utline"), 
                        request.getParameter("ufare"), request.getParameter("utid"), request.getParameter("udate"));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if(button.equals("search")) {
            request.setAttribute("key", request.getParameter("key"));
        } else if(button.equals("remsearch")) {
            request.setAttribute("key", "");
        }
        RequestDispatcher rd = request.getRequestDispatcher("custRepTrainSchedule.jsp");
        rd.forward(request, response);
    }
}
