package com.project.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.implementation.UserDAOImpl;
import com.project.entities.UsersEM;
import com.project.utility.ClsMail;
import com.project.utility.ClsValidations;


public class CreateUserController extends HttpServlet {
	
	UserDAOImpl userDAOImpl;
	List<String> errorList;	
	private static final long serialVersionUID = 1L;
	RequestDispatcher rdp;
	
    public CreateUserController() {
        super();
        userDAOImpl = new UserDAOImpl();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersEM user = new UsersEM();
		errorList = new ArrayList<String>();
		String fname = ClsValidations.checkStringLen(request.getParameter("fname"),3) ? request.getParameter("fname") :null;
		String lname = ClsValidations.checkStringLen(request.getParameter("lname"),3) ? request.getParameter("lname") :null;
		String email = ClsValidations.validateEmail(request.getParameter("emailId")) ? request.getParameter("emailId") :null;
		String mobile = ClsValidations.isValidMobile(request.getParameter("mobile")) ? request.getParameter("mobile") :null;

		if(null != fname) {
			user.setUserFname(fname);
		}else {
			errorList.add("First Name entry is missing or Length is less then 3 character");
		}
		
		if(null != lname) {
			user.setUserLname(lname);
		}else {
			errorList.add("Last Name entry is missing or Length is less then 3 character");
		}
		
		if(null != email) {
			user.setUserMailId(email);
		}else {
			errorList.add("Email ID is not in proper format");
		}
		if(null != mobile && ClsValidations.isNumber(mobile)){
			user.setUserMobileNo(mobile);
		}else {
			errorList.add("Mobile number is must");
		}
		
		if(null != request.getSession(false).getAttribute("user")) {
			user.setUserType("Driver");
		}else {
			user.setUserType("Customer");
		}
		
		if(errorList.size()==0) {
			int output = userDAOImpl.addUser(user);
			if(-99==output) {
				errorList.add("Email ID is already exists");
			}else if(-999 == output) {
				errorList.add("Mobile Number is already registered");
			}else {				
				String mailBody = "Hello,<br/> Your Account is Active now.<br/>";
				mailBody += "And, your generated password is :<h4>"+userDAOImpl.getRegisteredUserPwd(output)+"</h4>";
				mailBody +="<br/><br/>Thankyou,<br/>KRIVA CAB's";
				ClsMail.mailSent(email,"Welcome to KRIVA CAB's Service!!!", mailBody);
				request.setAttribute("success","Please visit to your email account for password.");
				if(null != request.getSession(false).getAttribute("user")) {
					rdp=request.getRequestDispatcher("addDriver.jsp");
					rdp.include(request,response);
					rdp.forward(request,response);
				}else {
					rdp=request.getRequestDispatcher("index.jsp");
					rdp.include(request,response);
					rdp.forward(request,response);
				}
			}
		}else {
			request.setAttribute("errorList",errorList);
			rdp=request.getRequestDispatcher("/index.jsp");
			rdp.include(request,response);
			rdp.forward(request,response);
		}
		
	}

}
