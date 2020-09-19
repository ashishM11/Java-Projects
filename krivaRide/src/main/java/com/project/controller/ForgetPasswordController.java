package com.project.controller;

import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.implementation.UserDAOImpl;
import com.project.entities.PasswordEM;
import com.project.entities.UsersEM;
import com.project.utility.ClsMail;
import com.project.utility.ClsValidations;


@SuppressWarnings("serial")
public class ForgetPasswordController extends HttpServlet {

	UserDAOImpl userDAOImpl;	
	RequestDispatcher rdp;

	
    public ForgetPasswordController() {
        super();
        userDAOImpl = new UserDAOImpl();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(ClsValidations.validateEmail(request.getParameter("email").trim())) {
			UsersEM user = userDAOImpl.getUserDetailByEmailId(request.getParameter("email").trim());
			if(null != user) {
				PasswordEM pwdObj = userDAOImpl.getPasswordInfoByUserId(user.getUserId());
				if(pwdObj != null) {
					InetAddress localhost = InetAddress.getLocalHost();
					String mailBody = "Hi,<br> Forgot Your Password?; please click link belowand you will be allowed to set your new password.<br/>";
					mailBody +="http://"+localhost.getHostAddress()+":8080/krivaRide/resetPassword.jsp?uid="+pwdObj.getUser().getUserId()+"&data="+pwdObj.getPwdChar();
					mailBody +="<br/><br/>Regards,<br/>KRIVA CAB's";
					
					ClsMail.mailSent(request.getParameter("email").trim(),"Reset Password, Kriva Cab's", mailBody);
					request.setAttribute("success","Reset password link send to your mail id.");
					rdp=request.getRequestDispatcher("index.jsp");
					rdp.include(request,response);
					rdp.forward(request,response);
				}
			}else {
				request.setAttribute("error","No user found with associated mail id.");
				rdp=request.getRequestDispatcher("index.jsp");
				rdp.include(request,response);
				rdp.forward(request,response);
			}
		}
	}

}
