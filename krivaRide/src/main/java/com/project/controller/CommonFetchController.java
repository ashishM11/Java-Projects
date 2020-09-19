package com.project.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.project.dao.implementation.CabsDAOImpl;
import com.project.dao.implementation.HelperDAOImpl;
import com.project.entities.BookingConfirmationEM;
import com.project.entities.CabCaptainEM;
import com.project.entities.CabsEM;
import com.project.entities.ComplaintsEM;
import com.project.entities.ConstantsEM;
import com.project.entities.FeedbacksEM;
import com.project.entities.UsersEM;

@SuppressWarnings("serial")
public class CommonFetchController extends HttpServlet {

	HelperDAOImpl helperDaoImplObj;
	CabsDAOImpl cabsDaoImpl;
	RequestDispatcher rdp;
	StringBuffer hql;
	JSONArray jsArr;
	JSONObject jsObj;
	public CommonFetchController() {
		super();
		helperDaoImplObj = new HelperDAOImpl();
		cabsDaoImpl = new CabsDAOImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestType = request.getParameter("reqType");
		switch (requestType) {
		case "fetchCount":
			String[] tablesName = { "UsersEM", "FeedbacksEM", "ComplaintsEM","ConstantsEM","CabsEM","BookingConfirmationEM"};
			Map<String, Integer> rowCounts = new HashMap<String, Integer>();
			if (tablesName.length != 0) {
				for (String tableName : tablesName) {
					rowCounts.put(tableName, helperDaoImplObj.getTableRowCount(tableName));
				}
				JSONObject rowCountJson = new JSONObject(rowCounts);
				response.getWriter().print(rowCountJson);
			}
			break;
		case "fetchConstantInfo":
			List<ConstantsEM> constList=new ArrayList<ConstantsEM>();
			if("CabType".equals(request.getParameter("reqFor").trim())) {
				hql=new StringBuffer("From ConstantsEM constantObj Where constantObj.constType=\'"+request.getParameter("reqFor").trim()+"\'");
			}else if("Cab".equals(request.getParameter("reqFor").trim())) {
				hql=new StringBuffer("From ConstantsEM const Where const.constType=\'"+request.getParameter("reqFor").trim()+"\' and const.constSubType=\'"+request.getParameter("subType").trim()+"\'");
			}else if("Rate".equals(request.getParameter("reqFor").trim())){
				hql=new StringBuffer("From ConstantsEM const Where const.constType=\'"+request.getParameter("reqFor").trim()+"\' and const.constSubType=\'"+request.getParameter("subType").trim()+"\'");	
			}
			
			for(Object obj :helperDaoImplObj.getTableData(hql.toString())) {
				constList.add((ConstantsEM)obj);
			}; 
			jsArr = new JSONArray(constList);
			response.getWriter().print(jsArr);
			break;
		case "fetchRiderInfo":
			if(request.getParameter("emailId").isEmpty() && request.getParameter("mobileNo").isEmpty())
			{
				request.setAttribute("error","Please provide either of the input to get cabbie info");
			}else{
				String emailId = (request.getParameter("emailId").isEmpty()) ? null:request.getParameter("emailId").trim();
				String mobileNo = (request.getParameter("mobileNo").isEmpty()) ? null:request.getParameter("mobileNo").trim();
				UsersEM userObj = helperDaoImplObj.getRiderInfo(emailId, mobileNo);
				jsObj = new JSONObject(userObj);
				response.getWriter().print(jsObj);
			}
			break;
		case "fetchAllRecords":
			switch (request.getParameter("forTable").trim()) {
			case "viewCustomers":
				List<UsersEM> usersList=new ArrayList<UsersEM>();
				hql=new StringBuffer("From UsersEM user Where user.userType=\'Customer\'");
				for(Object obj :helperDaoImplObj.getTableData(hql.toString())) {
					usersList.add((UsersEM)obj);
				}; 
				jsArr = new JSONArray(usersList);
				response.getWriter().print(jsArr);
				break;
			case "viewFeedbacks":
				List<FeedbacksEM> feedbackList = new ArrayList<>();
				hql=new StringBuffer("SELECT feedback FROM FeedbacksEM feedback JOIN FETCH feedback.user");
				for(Object obj :helperDaoImplObj.getTableData(hql.toString())) {
					feedbackList.add((FeedbacksEM)obj);
				}; 
				jsArr = new JSONArray(feedbackList);
				response.getWriter().print(jsArr);
				break;
			case "viewComplaints":
				List<ComplaintsEM> complaintsList = new ArrayList<>();
				hql=new StringBuffer("SELECT complaints FROM ComplaintsEM complaints JOIN FETCH complaints.user");
				for(Object obj :helperDaoImplObj.getTableData(hql.toString())) {
					complaintsList.add((ComplaintsEM)obj);
				}; 
				jsArr = new JSONArray(complaintsList);
				response.getWriter().print(jsArr);
				break;
			case "viewConstants":
				List<ConstantsEM> constantList = new ArrayList<>();
				hql=new StringBuffer("From ConstantsEM");
				for(Object obj :helperDaoImplObj.getTableData(hql.toString())) {
					constantList.add((ConstantsEM)obj);
				}; 
				jsArr = new JSONArray(constantList);
				response.getWriter().print(jsArr);
				break;
			case "viewDrivers":
				List<UsersEM> driverList=new ArrayList<UsersEM>();
				hql=new StringBuffer("From UsersEM user Where user.userType=\'Driver\'");
				for(Object obj :helperDaoImplObj.getTableData(hql.toString())) {
					driverList.add((UsersEM)obj);
				}; 
				jsArr = new JSONArray(driverList);
				response.getWriter().print(jsArr);
				break;
			case "viewCabs":
				List<CabsEM> cabList=new ArrayList<CabsEM>();
				hql=new StringBuffer("FROM CabsEM");
				for(Object obj :helperDaoImplObj.getTableData(hql.toString())) {
					cabList.add((CabsEM)obj);
				}; 
				jsArr = new JSONArray(cabList);
				response.getWriter().print(jsArr);
				break;
			case "viewConfirmation":
				List<BookingConfirmationEM> confirmedBKList=new ArrayList<>();
				hql=new StringBuffer("Select bookingC FROM BookingConfirmationEM bookingC ");
				hql.append("JOIN FETCH bookingC.booking book ");
				hql.append("JOIN FETCH book.user user ");
				hql.append("JOIN FETCH bookingC.cabCaptain cabCapt ");
				hql.append("JOIN FETCH cabCapt.cab cab ");
				hql.append("JOIN FETCH cabCapt.user rider");
				
				for(Object obj :helperDaoImplObj.getTableData(hql.toString())) {
					confirmedBKList.add((BookingConfirmationEM)obj);
				}; 
				jsArr = new JSONArray(confirmedBKList);
				response.getWriter().print(jsArr);
				break;
			default:
				break;
			}
			break;
		case "fetchCabInfo":
			List<CabsEM> cabList = cabsDaoImpl.getUnassignedCabList();
			jsArr = new JSONArray(cabList);
			response.getWriter().print(jsArr);
			break;
		case "fetchCabCaptainDetails":
			List<CabCaptainEM> cabCaptList = cabsDaoImpl.getCabCaptainDetails(request.getParameter("cabType").trim());
			jsArr = new JSONArray(cabCaptList);
			response.getWriter().print(jsArr);
			break;
		default:
			break;
		}

	}

}
