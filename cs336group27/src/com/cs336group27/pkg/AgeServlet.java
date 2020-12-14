package com.cs336group27.pkg;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;

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
			
			String origin = (String)request.getParameter("origin");
			String dest = (String)request.getParameter("destination");
			String date = (String)request.getParameter("resdate");
			String time = (String)request.getParameter("restime");
			String user = (String)request.getParameter("user");
			request.setAttribute("type", request.getParameter("listType"));
			String type = (String)request.getAttribute("type");
			String age = (String)request.getParameter("age");
			String disabled = (String)request.getParameter("disableConfirm");
			
			// Age Discount
			 if(age.equals("0-17")) {
				String message = "Child: 25% Discount";
				request.setAttribute("message", message);
			 }else if(age.equals("18-64")) {
					String message = "";
					request.setAttribute("message", message);
			 }else if(age.equals("65+")) {
					String message = "Senior: 35% Discount";
					request.setAttribute("message", message);
			 }
			 // Disability Discount
			 if(!(disabled == null)) {
					String message1 = "Disabled: 50% Discount";
					request.setAttribute("message1", message1);
				}
			 
			 //Ticket Type
				if(request.getAttribute("type").equals("one_way")) {
					String message2 = "One Way Ticket";
					request.setAttribute("message2", message2);
				} else {
					String message2 = "Round-Trip Ticket";
					request.setAttribute("message2", message2);
				}
				
				// Origin/Destination
				int res = appDB.createReservation(origin, dest, date, time, user, type, age, request.getParameter("disableConfirm"));
				if(res > 0) {
					String message3 = "Thank you for making a reservation. Your Reservation Number is: " + res;
					request.setAttribute("message3", message3);
				 }else {
					String message3 = "FATAL DATABASE ERROR";
					request.setAttribute("message3", message3);
				 }
				
				
				// Calculate Fare
				float fare = appDB.calculateFare(origin, dest, date, type, age, disabled);
				if(fare > 0) {
					DecimalFormat df = new DecimalFormat("#.##");
					df.setRoundingMode(RoundingMode.CEILING);
					    String f = df.format(fare);
					String message4 = "Your total fare is: $" + f;
					request.setAttribute("message4", message4);
				 }else {
					String message4 = "Cannot calculate fare";
					request.setAttribute("message4", message4);
				 }
		}catch(Exception e){
			// Age Discount
			e.printStackTrace();
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher("CusMakeReservation.jsp");
			rd.forward(request, response);
		}
	}
}
