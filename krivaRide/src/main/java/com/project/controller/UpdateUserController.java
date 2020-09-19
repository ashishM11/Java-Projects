package com.project.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.implementation.UserDAOImpl;
import com.project.entities.UsersEM;

public class UpdateUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserDAOImpl userDaoImpl;
	String error = "";
	RequestDispatcher rdp;

	public UpdateUserController() {
		super();
		userDaoImpl = new UserDAOImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (null == request.getParameter("userMailId") || request.getParameter("userMailId").isEmpty()) {
			error = "Mail Id field is empty";
		} else if (null == request.getParameter("userMobile") || request.getParameter("userMobile").isEmpty()) {
			error = "Mobile Number is empty";
		} else {
			if(null != request.getSession(false).getAttribute("user")) {
				UsersEM user = (UsersEM) request.getSession(false).getAttribute("user");
				int op = userDaoImpl.updateUserinfo(user.getUserId(), request.getParameter("userMobile").trim(),request.getParameter("userMailId").trim()) ;
				if(op > 0) {
					request.setAttribute("user",userDaoImpl.getUserDetail(user.getUserId()));
					request.setAttribute("success","Profile Updated successfully");
					if(user.getUserType() == "Admin") {
						rdp = request.getRequestDispatcher("adminDashboard.jsp");
						rdp.forward(request, response);
					}else if(user.getUserType() == "Customer"){
						rdp = request.getRequestDispatcher("index.jsp");
						rdp.forward(request, response);
					}else {
						rdp = request.getRequestDispatcher("driverDashboard.jsp");
						rdp.forward(request, response);
					}
				}else {
					request.setAttribute("error","Profile is not updated");
					rdp = request.getRequestDispatcher("index.jsp");
				}
			}
		}

	}

}
