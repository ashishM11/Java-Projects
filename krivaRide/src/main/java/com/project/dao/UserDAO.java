package com.project.dao;

import com.project.entities.ComplaintsEM;
import com.project.entities.FeedbacksEM;
import com.project.entities.PasswordEM;
import com.project.entities.UsersEM;


public interface UserDAO {

	public int addUser(UsersEM user);
	public int isUserValid(String userIdent,String usrPwd);
	public UsersEM getUserDetail(int userId);
	public UsersEM getUserDetailByEmailId(String emailId);
	public String getRegisteredUserPwd(int userId);
	public int updateUserPwd(int userId,String newPwd,String oldPwdChar);
	public PasswordEM getPasswordInfoByUserId(int userId);
	public int addUserFeedback(FeedbacksEM feedbkObj);
	public int saveUserComplaints(ComplaintsEM complaintObj);
	public int updateUserinfo(int userId, String mobileNumber,String emailID);
}
