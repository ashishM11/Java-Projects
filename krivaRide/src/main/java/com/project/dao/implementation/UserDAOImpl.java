package com.project.dao.implementation;

import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;

import com.project.dao.UserDAO;
import com.project.entities.ComplaintsEM;
import com.project.entities.FeedbacksEM;
import com.project.entities.PasswordEM;
import com.project.entities.UsersEM;
import com.project.utility.HibernateUtils;

public class UserDAOImpl implements UserDAO {

	public int addUser(UsersEM user) {
		int userId = 0;
		Session sesObj = HibernateUtils.getSessionFactory().openSession();
		try {
			StoredProcedureQuery spQuery = sesObj.createStoredProcedureQuery("sp_CreateUsers");
			spQuery.registerStoredProcedureParameter("p_userFname", String.class, ParameterMode.IN);
			spQuery.registerStoredProcedureParameter("p_userLname", String.class, ParameterMode.IN);
			spQuery.registerStoredProcedureParameter("p_userMailID", String.class, ParameterMode.IN);
			spQuery.registerStoredProcedureParameter("p_userMobileNo", String.class, ParameterMode.IN);
			spQuery.registerStoredProcedureParameter("p_userType", String.class, ParameterMode.IN);
			spQuery.registerStoredProcedureParameter("p_userCreationOP", Integer.class, ParameterMode.OUT);

			spQuery.setParameter("p_userFname", user.getUserFname());
			spQuery.setParameter("p_userLname", user.getUserLname());
			spQuery.setParameter("p_userMailID", user.getUserMailId());
			spQuery.setParameter("p_userMobileNo", user.getUserMobileNo());
			spQuery.setParameter("p_userType", user.getUserType());

			spQuery.execute();
			userId = (Integer) spQuery.getOutputParameterValue("p_userCreationOP");
			sesObj.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sesObj.close();
		}
		return userId;
	}

	public int isUserValid(String userIdent, String usrPwd) {
		int userId = 0;
		Session sesObj = HibernateUtils.getSessionFactory().openSession();
		try {
			StoredProcedureQuery query = sesObj.getEntityManagerFactory().createEntityManager()
					.createStoredProcedureQuery("sp_UserLogin");
			query.registerStoredProcedureParameter("userIdenInput", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("userPassword", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("userId", Integer.class, ParameterMode.OUT);

			query.setParameter("userIdenInput", userIdent);
			query.setParameter("userPassword", usrPwd);

			query.execute();
			userId = (Integer) query.getOutputParameterValue("userId");
			sesObj.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sesObj.close();
		}

		return userId;
	}

	public UsersEM getUserDetail(int userId) {
		UsersEM user = null;
		Session sesObj = HibernateUtils.getSessionFactory().openSession();
		if (0 != userId) {
			try {
				Query query = sesObj.createQuery("FROM UsersEM where userId=?");
				query.setParameter(0, userId);
				user = (UsersEM) query.getSingleResult();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sesObj.close();
			}
		}
		return (null != user) ? user : null;
	}

	public String getRegisteredUserPwd(int userId) {
		PasswordEM userPwd = null;
		Session sesObj = HibernateUtils.getSessionFactory().openSession();
		if (0 != userId) {
			try {
				Query query = sesObj.createQuery("FROM PasswordEM where userId=?", PasswordEM.class);
				query.setParameter(0, userId);
				userPwd = (PasswordEM) query.getSingleResult();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sesObj.close();
			}
		}
		return (null != userPwd) ? userPwd.getUsrPwd() : null;
	}

	public int updateUserPwd(int userId, String newPwd, String oldPwdChar) {
		int op = 0;
		Session sesObj = HibernateUtils.getSessionFactory().openSession();
		if (0 != userId) {
			try {
				StoredProcedureQuery query = sesObj.createStoredProcedureQuery("sp_resetNewPassword");
				query.registerStoredProcedureParameter("p_newPwd", String.class, ParameterMode.IN);
				query.registerStoredProcedureParameter("p_oldPwdChar", String.class, ParameterMode.IN);
				query.registerStoredProcedureParameter("p_userId", Integer.class, ParameterMode.IN);
				query.registerStoredProcedureParameter("p_resultOp", Integer.class, ParameterMode.OUT);

				query.setParameter("p_newPwd", newPwd);
				query.setParameter("p_oldPwdChar", oldPwdChar);
				query.setParameter("p_userId", userId);

				query.execute();
				op = (Integer) query.getOutputParameterValue("p_resultOp");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sesObj.close();
			}
		}
		return op;
	}

	public UsersEM getUserDetailByEmailId(String emailId) {
		UsersEM user = null;
		Session sesObj = HibernateUtils.getSessionFactory().openSession();
		if (null != emailId) {
			try {
				Query query = sesObj.createQuery("FROM UsersEM where userMailId=?");
				query.setParameter(0, emailId);
				user = (UsersEM) query.getSingleResult();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sesObj.close();
			}
		}
		return (null != user) ? user : null;
	}

	public PasswordEM getPasswordInfoByUserId(int userId) {
		PasswordEM pwdObj = null;
		Session sesObj = HibernateUtils.getSessionFactory().openSession();
		if (0 != userId) {
			try {
				Query query = sesObj.createQuery("FROM PasswordEM where userId=?");
				query.setParameter(0, userId);
				pwdObj = (PasswordEM) query.getSingleResult();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sesObj.close();
			}
		}
		return (null != pwdObj) ? pwdObj : null;
	}

	public int addUserFeedback(FeedbacksEM feedbkObj) {
		int opRes = 0;
		Session sesObj = HibernateUtils.getSessionFactory().openSession();
		try {
			sesObj.beginTransaction();
			opRes = (Integer) sesObj.save(feedbkObj);
			sesObj.getTransaction().commit();
			sesObj.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sesObj.close();
		}
		return opRes;
	}

	public int saveUserComplaints(ComplaintsEM complaintObj) {
		int opRes = 0;
		Session sesObj = HibernateUtils.getSessionFactory().openSession();
		try {
			sesObj.beginTransaction();
			opRes = (Integer) sesObj.save(complaintObj);
			sesObj.getTransaction().commit();
			sesObj.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sesObj.close();
		}
		return opRes;
	}

	@Override
	public int updateUserinfo(int userId, String mobileNumber, String emailID) {
		int opRes = 0;
		try(Session sesObj = HibernateUtils.getSessionFactory().openSession()){
			StoredProcedureQuery query = sesObj.createStoredProcedureQuery("sp_updateUsersProfile");
			query.registerStoredProcedureParameter("p_userId", Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("new_userMobile", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("new_userEmail", String.class, ParameterMode.IN);			
			query.registerStoredProcedureParameter("p_OutRes", Integer.class, ParameterMode.OUT);

			query.setParameter("p_userId", userId);
			query.setParameter("new_userMobile", mobileNumber);
			query.setParameter("new_userEmail", emailID);
			
			query.execute();
			opRes =  (Integer) query.getOutputParameterValue("p_OutRes");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return opRes;
	}

}
