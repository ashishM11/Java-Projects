package com.project.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.implementation.CabsDAOImpl;
import com.project.entities.BookingsEM;
import com.project.entities.UsersEM;


public class SearchNearByCab extends HttpServlet {

	private static final long serialVersionUID = 1L;
	CabsDAOImpl cabDaoImpl;
	List<String> errorList;
	RequestDispatcher rdp;
	BookingsEM bookingInfo;

	public SearchNearByCab() {
		super();
		cabDaoImpl = new CabsDAOImpl();
		bookingInfo = new BookingsEM();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bookingSource = null;
		String bookingDestination = null;
		String bookingForWhen = null;
		String bookingDate = null;
		String bookingTime = null;
		Date bookingEntryCreation = new Date();
		
		if (request.getParameter("from").isEmpty() || (null == request.getParameter("from"))) {
			errorList.add("Source feild is empty");
		} else {
			bookingSource = request.getParameter("from").trim();
		}

		if (request.getParameter("to").isEmpty() || (null == request.getParameter("to"))) {
			errorList.add("Destination feild is empty");
		} else {
			bookingDestination = request.getParameter("to").trim();
		}

		if (request.getParameter("when").isEmpty() || request.getParameter("when").equals("0")) {
			errorList.add("Please select an option when you want to ride.");
		} else {
			bookingForWhen = request.getParameter("when").trim();
		}
		try {
			if (request.getParameter("when").equals("now")) {
				bookingDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
				bookingTime = new SimpleDateFormat("hh:mm a").format(new Date());
			} else {
				if (request.getParameter("futureDate").isEmpty() || (null == request.getParameter("futureDate"))) {
					errorList.add("Please choose date from date picker.");
				} else {
					bookingDate = request.getParameter("futureDate");
				}

				if (request.getParameter("futureTime").isEmpty() || (null == request.getParameter("futureTime"))) {
					errorList.add("Please enter time details.");
				} else {
					DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_TIME;
					bookingTime = LocalTime.parse(request.getParameter("futureTime").concat(":00"),dtf).toString();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (null != errorList) {
			request.setAttribute("errorList", errorList);
			rdp = request.getRequestDispatcher("/index.jsp");
			rdp.include(request, response);
			rdp.forward(request, response);
		} else {
			bookingInfo.setSource(bookingSource);
			bookingInfo.setDestination(bookingDestination);
			bookingInfo.setStrWhen(bookingForWhen);
			bookingInfo.setCabDate(bookingDate);
			bookingInfo.setCabTime(bookingTime);
			bookingInfo.setBookingEntryCreation(bookingEntryCreation);
			bookingInfo.setUser((UsersEM) request.getSession(false).getAttribute("user"));
			
			if(cabDaoImpl.saveSearchBookingInfo(bookingInfo) > 0) {
				request.setAttribute("bookingInfo",cabDaoImpl.getLastBookingInfo((UsersEM)request.getSession(false).getAttribute("user")));
				rdp = request.getRequestDispatcher("getRouteInfo.jsp");
				rdp.forward(request, response);
			}
		}

	}

}
