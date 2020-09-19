package com.project.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.implementation.UserDAOImpl;
import com.project.entities.UsersEM;
import com.project.utility.ClsValidations;

public class ResetUserPasswordController extends HttpServlet {

	UserDAOImpl userDAOImpl;
	private static final long serialVersionUID = 1L;
	RequestDispatcher rdp;
	int uid = 0;
	private String encrypted;
	private String error = null;

	public ResetUserPasswordController() {
		super();
		userDAOImpl = new UserDAOImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String requestFrom =(request.getParameter("changePwd") == null) ?request.getParameter("resetPwd")
				:request.getParameter("changePwd");
		
		if("changePwd".equals(requestFrom)) {
			if(request.getParameter("currentPwd").isEmpty()) {
				error= "Please Provide current password ..";
			}
		}else if(request.getParameter("newPwd").isEmpty()){
			error= "New Password Cannot be empty or Less then 5 Character";
		}else if(request.getParameter("repeatPwd").isEmpty()){
			error= "Repeat password cannot be empty or Less then 5 Character";
		}else if(!(request.getParameter("repeatPwd").equals(request.getParameter("newPwd")))) {
			error =  "Password & Confirm password doesn't match.";
		}
		
		if(null != error) {
			if("changePwd".equals(requestFrom)) {
				request.setAttribute("error",error);
				rdp=request.getRequestDispatcher("index.jsp");
			}else {
				request.setAttribute("errors",error);
				rdp=request.getRequestDispatcher("resetPassword.jsp?uid="+request.getParameter("uid")+"&data="+request.getParameter("data"));
			}
			rdp.forward(request, response);
		}else {
			if("resetPwd".equals(requestFrom)) {
				if(null == request.getParameter("oldPwdChar").toString() && ClsValidations.isNumber(request.getParameter("uid"))) {
					error = "Sorry, Password cannot be reset, please click on reset password link again..";
					response.sendRedirect("index.jsp");
				}else {
					uid = Integer.parseInt(request.getParameter("uid"));
					encrypted = request.getParameter("oldPwdChar");
					if (1 == userDAOImpl.updateUserPwd(uid, request.getParameter("newPwd").trim(), encrypted)) {
						request.setAttribute("success", "Password is changed.");
					} else {
						request.setAttribute("errors", "Something went wrong");
						rdp=request.getRequestDispatcher("resetPassword.jsp?uid=" + uid +"&data=" + encrypted);
						rdp.forward(request, response);
					}
				}
			}else {
				UsersEM user =(UsersEM)request.getSession(false).getAttribute("user");
				if (1 == userDAOImpl.updateUserPwd(user.getUserId(), request.getParameter("newPwd").trim(),userDAOImpl.getPasswordInfoByUserId(user.getUserId()).getPwdChar())) {
					request.setAttribute("success", "Password is changed.");
				} else {
					request.setAttribute("errors", "Something went wrong");
				}
				
				if(user.getUserType() == "Admin") {
					rdp = request.getRequestDispatcher("adminDashboard.jsp");
				}else if(user.getUserType() == "Customer"){
					rdp = request.getRequestDispatcher("index.jsp");
				}else {
					rdp = request.getRequestDispatcher("driverDashboard.jsp");
				}
				rdp.forward(request, response);
			}
		}
	}
}