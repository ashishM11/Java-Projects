package com.project.dao.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.project.dao.HelperDAO;
import com.project.entities.ConstantsEM;
import com.project.entities.UsersEM;
import com.project.utility.HibernateUtils;

public class HelperDAOImpl implements HelperDAO {

	public int getTableRowCount(String tableName) {
		int rowCount = 0;
		Session sesObj = HibernateUtils.getSessionFactory().openSession();
		if (null != tableName) {
			try {
				Query query = sesObj.createQuery("FROM "+tableName);
				rowCount = query.getResultList().size();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sesObj.close();
			}
		}
		return rowCount;
	}

	@SuppressWarnings("unchecked")
	public List<Object> getTableData(String hql) {
		List<Object> tblData = new ArrayList<Object>();
		Session sesObj = HibernateUtils.getSessionFactory().openSession();
		try {
			if(null != hql) {
				Query query = sesObj.createQuery(hql);
				tblData = query.getResultList();
				sesObj.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sesObj.close();
		}
		return tblData;
	}

	@Override
	public int saveConstants(ConstantsEM constantObj) {
		int opRes =0;
		if(null != constantObj) {
			try(Session sesObj = HibernateUtils.getSessionFactory().openSession()){
				sesObj.beginTransaction();
				opRes = (Integer) sesObj.save(constantObj);
				sesObj.getTransaction().commit();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return opRes;
	}
	
	public UsersEM getRiderInfo(String emailId,String mobileNo) {
		String hql = "";
		UsersEM userObj = null;
		Query query = null;
		Session sesObj = HibernateUtils.getSessionFactory().openSession();
		try {
			if(null != emailId && null != mobileNo) {				
				hql = "From UsersEM where userType = ? and userMailId=? and userMobileNo=?";
				query = sesObj.createQuery(hql);
				query.setParameter(0,"Driver");
				query.setParameter(1,emailId);
				query.setParameter(2,mobileNo);
			}else if(null != emailId) {
				hql = "From UsersEM where userType = ? and userMailId=?";
				query = sesObj.createQuery(hql);
				query.setParameter(0,"Driver");
				query.setParameter(1,emailId);
			}else {
				hql = "From UsersEM where userType = ? and userMobileNo=?";
				query = sesObj.createQuery(hql);
				query.setParameter(0,"Driver");
				query.setParameter(1,mobileNo);
			}
			userObj = (UsersEM) query.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sesObj.close();
		}
		return userObj;
	}

}
