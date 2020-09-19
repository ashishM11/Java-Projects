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
import com.project.dao.implementation.UserDAOImpl;
import com.project.entities.CabCaptainEM;


@SuppressWarnings("serial")
public class SaveCabCaptainController extends HttpServlet {

	CabsDAOImpl cabDaoImpl;
	RequestDispatcher rdp;
	List<String> errorList;
	public SaveCabCaptainController() {
        super();
        cabDaoImpl = new CabsDAOImpl();
        errorList = new ArrayList<>();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cabType = (request.getParameter("cabType").length() > 0) ? request.getParameter("cabType").trim(): null;
		int cabId =  (request.getParameter("cabId").length() > 0) ? Integer.parseInt(request.getParameter("cabId")): 0;
		int userId = (request.getParameter("user").length() > 0) ? Integer.parseInt(request.getParameter("user").split(",")[0]): 0;
		
		if(null == cabType || "Select".equalsIgnoreCase(request.getParameter("cabType"))) {
			errorList.add("Please select cabbie level of driving.");
		}
		
		if(0 == cabId) {
			errorList.add("Cab is not selected");
		}
		
		if(0 == userId) {
			errorList.add("Cab is not assigned to any Cabbie.");
		}
		
		if(errorList.isEmpty()) {
			CabCaptainEM cabCapt = new CabCaptainEM();
			cabCapt.setCabCaptJoinDate(new Date());
			cabCapt.setCabCaptLevel(cabType);
			cabCapt.setUser(new UserDAOImpl().getUserDetail(userId));
			cabCapt.setCab(new CabsDAOImpl().getCabInfo(cabId));
			if(cabDaoImpl.saveCabCaptainInfo(cabCapt) > 0) {
				if(cabDaoImpl.updateCabAllocation(cabId) > 0) {
					request.setAttribute("success","Cab is associated to Cabbiee successfully");
					rdp=request.getRequestDispatcher("cabAllocatement.jsp");
					rdp.include(request,response);
					rdp.forward(request,response);
				}
			}else {
				request.setAttribute("error","Oops its look like captain is already assigned cab.");
				rdp=request.getRequestDispatcher("cabAllocatement.jsp");
				rdp.include(request,response);
				rdp.forward(request,response);
			}
			
		}else {
			request.setAttribute("errorList",errorList);
			rdp=request.getRequestDispatcher("cabAllocatement.jsp");
			rdp.include(request,response);
			rdp.forward(request,response);
		}
	}

}
