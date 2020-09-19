package com.project.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.dao.implementation.UserDAOImpl;
import com.project.entities.UsersEM;
import com.project.utility.ClsValidations;

@SuppressWarnings("serial")
public class AuthenticateUserController extends HttpServlet {

	UserDAOImpl userDAOImpl;
	HttpSession objSession;
	RequestDispatcher rdp;

	public AuthenticateUserController() {
		userDAOImpl = new UserDAOImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		
		UsersEM user = null;
		String userIdenInput = (ClsValidations.validateEmail(request.getParameter("mailId").trim()))
				? request.getParameter("mailId").trim()
				: null;

		String userPassword = request.getParameter("password").trim();
				
		if(userIdenInput == null) {
			request.setAttribute("error","Please provide email ID");
		}else if(userPassword == null || userPassword.isEmpty()){
			request.setAttribute("error","Please enter password");
		}else {
			if (userIdenInput != null && userPassword != null) {
				int userId = userDAOImpl.isUserValid(userIdenInput, userPassword);
				if (0 != userId) {
					user = userDAOImpl.getUserDetail(userId);
					objSession = request.getSession();
					objSession.setAttribute("user",user);
				}else {
					request.setAttribute("error","Authentication failed, email & password not matched.");
				}
			}
		}
		
		if(null != request.getAttribute("error")) {
			rdp=request.getRequestDispatcher("/index.jsp");
			rdp.include(request,response);
			rdp.forward(request,response);
		}else {
			if (user.getUserType().equalsIgnoreCase("Customer")) {
				response.sendRedirect("index.jsp");
			} else if (user.getUserType().equalsIgnoreCase("Admin")) {
				response.sendRedirect("adminDashboard.jsp");
			} 		
		}
	}
}
