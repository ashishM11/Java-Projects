package com.project.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.implementation.UserDAOImpl;
import com.project.entities.ComplaintsEM;
import com.project.entities.UsersEM;

@SuppressWarnings("serial")
public class SaveComplaintController extends HttpServlet {
       
	UserDAOImpl userDAOImpl;
	RequestDispatcher rdp;
	
    public SaveComplaintController() {
        super();
        userDAOImpl = new UserDAOImpl();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(null == request.getParameter("compSub") || null==request.getParameter("compMatter") || null==request.getParameter("uid")) {
			request.setAttribute("error", "Provide all details to submit your complaints.");
			rdp.include(request, response);
			rdp.forward(request, response);
		}else {
			ComplaintsEM complaintObj= new ComplaintsEM();
			complaintObj.setComplaintSub(request.getParameter("compSub").trim().toLowerCase());
			complaintObj.setComplaintMsg(request.getParameter("compMatter").trim().toLowerCase());
			complaintObj.setUser((UsersEM)request.getSession(false).getAttribute("user"));
			complaintObj.setComplaintDate(new Date());
			
			if (userDAOImpl.saveUserComplaints(complaintObj) > 0) {
				request.setAttribute("success", "We will surely getback to you with reply.");
				rdp = request.getRequestDispatcher("/index.jsp");
				rdp.include(request, response);
				rdp.forward(request, response);
				
			}else {
				request.setAttribute("error", "Complaints not saved due to some technical errors.");
				rdp = request.getRequestDispatcher("/index.jsp");
				rdp.include(request,response);
				rdp.forward(request, response);
			}

		}
	}

}
