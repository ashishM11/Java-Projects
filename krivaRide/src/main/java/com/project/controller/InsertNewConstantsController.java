package com.project.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.implementation.HelperDAOImpl;
import com.project.entities.ConstantsEM;

public class InsertNewConstantsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RequestDispatcher rdp;
	HelperDAOImpl helpDaoImpl;
	List<String> errorList=null;
	
    public InsertNewConstantsController() {
        super();
        helpDaoImpl= new HelperDAOImpl();
        errorList = new ArrayList<>();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String constCode = (request.getParameter("constCode").length() > 0) ? request.getParameter("constCode") : null;
		String constCodeDesc= (request.getParameter("constCodeDesc").length() > 0) ? request.getParameter("constCodeDesc") : null;
		String constCodeType= (request.getParameter("constCodeType").length() > 0) ? request.getParameter("constCodeType") : null;
		String constCodeSubType= (request.getParameter("constCodeSubType").length() > 0) ? request.getParameter("constCodeSubType") : null;
		
		if(null == constCode) {
			errorList.add("Code Field cannot be empty");
		}
		if(null ==  constCodeDesc) {
			errorList.add("Code with description is must");
		}
		if(null ==  constCodeType) {
			errorList.add("Type of code creation is necessary.");
		}else {
			if(errorList.isEmpty()) {
				ConstantsEM constObj=new ConstantsEM();
				constObj.setConstCode(constCode);
				constObj.setConstCodeDesc(constCodeDesc);
				constObj.setConstType(constCodeType);
				constObj.setConstSubType(constCodeSubType);
				
				if(helpDaoImpl.saveConstants(constObj)>0) {
					request.setAttribute("success","New constants added to our system");
					rdp=request.getRequestDispatcher("addConstants.jsp");
				}else {
					request.setAttribute("errorList",errorList);
					rdp=request.getRequestDispatcher("addConstants.jsp");
				}
				rdp.forward(request, response);
			}
		}
		
		
	}

}
