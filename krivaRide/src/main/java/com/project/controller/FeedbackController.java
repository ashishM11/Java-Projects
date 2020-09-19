package com.project.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.project.dao.implementation.UserDAOImpl;
import com.project.entities.FeedbacksEM;
import com.project.entities.UsersEM;
import com.project.utility.ClsValidations;

@SuppressWarnings("serial")
public class FeedbackController extends HttpServlet {

	UserDAOImpl userDAOImpl;
	RequestDispatcher rdp;
	
	public FeedbackController() {
		userDAOImpl = new UserDAOImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (ClsValidations.checkStringLen(req.getParameter("feeedbackSub"),2) && ClsValidations.checkStringLen(req.getParameter("feebackComments"),2) 
				&& null == req.getParameter("rating")) {
			req.setAttribute("error", "Sorry you need to provide all details to submit your feedback.");
			rdp = req.getRequestDispatcher("/index.jsp");
			rdp.include(req, resp);
			rdp.forward(req, resp);
		} else {
			FeedbacksEM feedbackObj = new FeedbacksEM();
			feedbackObj.setFeedbackSub(req.getParameter("feeedbackSub").toString().trim());
			feedbackObj.setFeedbackMsg(req.getParameter("feebackComments").toString().trim());
			feedbackObj.setFeedbackRating(Integer.parseInt(req.getParameter("rating")));
			feedbackObj.setUser((UsersEM)req.getSession(false).getAttribute("user"));
			feedbackObj.setFeedbackDate(new Date());

			if (userDAOImpl.addUserFeedback(feedbackObj) > 0) {
				req.setAttribute("success", "Thanks your feedback.");
				rdp = req.getRequestDispatcher("/index.jsp");
				
			}else {
				req.setAttribute("error", "Feedback not saved due to some technical errors.");
				rdp = req.getRequestDispatcher("/index.jsp");
			}
			rdp.include(req, resp);
			rdp.forward(req, resp);
		}
	}

}
