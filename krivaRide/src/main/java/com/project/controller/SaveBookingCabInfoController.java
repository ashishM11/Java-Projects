package com.project.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.implementation.CabsDAOImpl;
import com.project.entities.BookingConfirmationEM;
import com.project.entities.BookingsEM;
import com.project.entities.CabCaptainEM;
import com.project.entities.UsersEM;


@SuppressWarnings("serial")
public class SaveBookingCabInfoController extends HttpServlet {
       
	CabsDAOImpl cabDaoImpl;
	RequestDispatcher rdp;
	List<String> errorList;
	BookingsEM bookingObj;
	CabCaptainEM cabCapt;
    public SaveBookingCabInfoController() {
        super();
        cabDaoImpl = new CabsDAOImpl();
        errorList = new ArrayList<>();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String duration = (request.getParameter("duration").length() > 0) ? request.getParameter("duration"):null;
		String distance = (request.getParameter("distance").length() > 0) ? request.getParameter("distance"):null;
		float cost =  (request.getParameter("cost").length() > 0) ? Float.parseFloat(request.getParameter("cost")):0;
		int cabCaptId = (request.getParameter("cabCaptId").length() > 0) ? Integer.parseInt(request.getParameter("cabCaptId")):0;
		if(null == duration) {
			errorList.add("Duration is missing.");
		}
		if(null == distance){
			errorList.add("Distance is missing");
		}
		
		if(null != request.getSession(false).getAttribute("user")) {
			bookingObj = cabDaoImpl.getLastBookingInfo((UsersEM) request.getSession(false).getAttribute("user"));
		}else {
			errorList.add("Searching info is not present");
		}
		
		if(0==cost) {
			errorList.add("Cost Calucation is not properly done");
		}
		
		if(0 == cabCaptId) {
			errorList.add("Please select cab from list");
		}else {
			cabCapt = cabDaoImpl.getCabCaptainInfo(cabCaptId);
		}
		
		
		if(errorList.isEmpty()) {
			BookingConfirmationEM bookConfirm=new BookingConfirmationEM();
			bookConfirm.setBookingDistance(distance);
			bookConfirm.setBookingDuration(duration);
			bookConfirm.setBookingConfirmationTS(new Date());
			bookConfirm.setBookingConfirmationCost(cost);
			bookConfirm.setCabCaptain(cabCapt);
			bookConfirm.setBooking(bookingObj);
			bookConfirm.setBookingConfirmationStatus("Confirmed");
			if(cabDaoImpl.saveBookingConfirm(bookConfirm) > 0) {
				request.setAttribute("success","Booking is confirmed.");
				rdp=request.getRequestDispatcher("index.jsp");
				rdp.include(request,response);
				rdp.forward(request,response);
			}
		}else {
			request.setAttribute("errorList",errorList);
			rdp=request.getRequestDispatcher("getRouteInfo.jsp");
			rdp.include(request,response);
			rdp.forward(request,response);
		}
	}

}
