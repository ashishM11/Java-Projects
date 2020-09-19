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
import com.project.entities.CabsEM;

public class SaveCabInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<String> errorList=null;
	CabsDAOImpl cabDaoImpl;
	RequestDispatcher rdp;
	public SaveCabInformation() {
		super();
		cabDaoImpl = new CabsDAOImpl();
		errorList = new ArrayList<>();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cabRNo = (request.getParameter("cabRNo").length() > 5) ? request.getParameter("cabRNo") : null;
		String cabType = (request.getParameter("cabType").length() !=0) ? request.getParameter("cabType"): null;
		String cabBrand = (request.getParameter("cabBrand").length()!=0) ? request.getParameter("cabBrand"): null;
		String cabColor = (request.getParameter("cabColor").length()!=0) ? request.getParameter("cabColor"): null;
		String isCabInsured = (request.getParameter("cabInsured").length()==0) ? "N" :"Y";
		String isCabActive = (request.getParameter("isCabActive").length()==0) ? "N" :"Y";
		
		if(null == cabRNo) {
			errorList.add("Cab Registeration number is missing.");
		}
		if((null ==  cabType) || "Select".equals(cabType)) {
			errorList.add("Cab Type is not selected.");
		}
		if((null ==  cabBrand) || "Select".equals(cabBrand)) {
			errorList.add("Cab Not Selected from List");
		}
		if(null == cabColor) {
			errorList.add("Choose Cab color from given pallet");
		}
		if(errorList.isEmpty()) {
			CabsEM cabObj =new CabsEM();
			cabObj.setCabRegNo(cabRNo);
			cabObj.setCabType(cabType);
			cabObj.setCabBrand(cabBrand);
			cabObj.setCabColor(cabColor);
			cabObj.setIsCabActive(isCabActive);
			cabObj.setCabInsured(isCabInsured);
			cabObj.setCabRegisterDate(new Date());
			cabObj.setIsAllocated("No");
			
			if(cabDaoImpl.saveCabInfo(cabObj) > 0) {
				request.setAttribute("success","Cab is registered successfully.");
			}else {
				errorList.add("Cab number is already registered.");
				request.setAttribute("errorList",errorList);
			}
			
		}else {
			request.setAttribute("errorList",errorList);
		}
		rdp = request.getRequestDispatcher("addCabInfo.jsp");
		rdp.forward(request, response);
	}

}
