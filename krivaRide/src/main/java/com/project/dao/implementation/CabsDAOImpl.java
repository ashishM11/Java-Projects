package com.project.dao.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.project.dao.CabsDAO;
import com.project.entities.BookingConfirmationEM;
import com.project.entities.BookingsEM;
import com.project.entities.CabCaptainEM;
import com.project.entities.CabsEM;
import com.project.entities.UsersEM;
import com.project.utility.HibernateUtils;

public class CabsDAOImpl implements CabsDAO {

	@Override
	public int saveSearchBookingInfo(BookingsEM bookingInfo) {
		int opRes = 0;
		Session sesObj = HibernateUtils.getSessionFactory().openSession();
		try {
			sesObj.beginTransaction();
			opRes = (Integer) sesObj.save(bookingInfo);
			sesObj.getTransaction().commit();
			sesObj.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sesObj.close();
		}
		return opRes;
	}

	public BookingsEM getLastBookingInfo(UsersEM user) {
		BookingsEM bookinfoObj = null;
		if (null != user) {
			try (Session sesObj = HibernateUtils.getSessionFactory().openSession()) {
				String hql = "From BookingsEM Where userId=? order by bookingEntryCreation desc";
				Query query = sesObj.createQuery(hql);
				query.setParameter(0, user.getUserId());
				bookinfoObj = (BookingsEM) query.getResultList().get(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return (null != bookinfoObj) ? bookinfoObj : null;
	}

	public int saveCabInfo(CabsEM cabObj) {
		int opRes = 0;
		if (cabObj != null) {
			try (Session sesObj = HibernateUtils.getSessionFactory().openSession()) {
				sesObj.beginTransaction();
				opRes = (Integer) sesObj.save(cabObj);
				sesObj.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return opRes;
	}

	@Override
	public List<CabsEM> getUnassignedCabList() {
		List<CabsEM> cabslist=new ArrayList<>();
		try(Session sesObj = HibernateUtils.getSessionFactory().openSession()){
			String hql = "From CabsEM Where isAllocated=?";
			Query query = sesObj.createQuery(hql);
			query.setParameter(0,"No");
			for(Object cabObj : query.getResultList()) {
				cabslist.add((CabsEM) cabObj);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return cabslist;
	}

	@Override
	public int saveCabCaptainInfo(CabCaptainEM cabCapt) {
		int opRes = 0;
		if (cabCapt != null) {
			try (Session sesObj = HibernateUtils.getSessionFactory().openSession()) {
				sesObj.beginTransaction();
				opRes = (Integer) sesObj.save(cabCapt);
				sesObj.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return opRes;
	}

	@Override
	public int saveBookingConfirm(BookingConfirmationEM bookingCObj) {
		int opRes = 0;
		if (bookingCObj != null) {
			try (Session sesObj = HibernateUtils.getSessionFactory().openSession()) {
				sesObj.beginTransaction();
				opRes = (Integer) sesObj.save(bookingCObj);
				sesObj.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return opRes;
	}
	
	@Override
	public CabsEM getCabInfo(int cabId) {
		CabsEM cabObj = null;
		Session sesObj = HibernateUtils.getSessionFactory().openSession();
		if (0 != cabId) {
			try {
				Query query = sesObj.createQuery("FROM CabsEM where cabId=?");
				query.setParameter(0, cabId);
				cabObj = (CabsEM) query.getSingleResult();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sesObj.close();
			}
		}
		return (null != cabObj) ? cabObj : null;
	}

	@Override
	public int updateCabAllocation(int cabId) {
		int opRes = 0;
		if(0 != cabId) {
			try(Session sesObj = HibernateUtils.getSessionFactory().openSession()){
				Query query = sesObj.createQuery("Update CabsEM SET isAllocated=? where cabId=?");
				query.setParameter(0,"Yes");
				query.setParameter(1, cabId);
				sesObj.beginTransaction();
				opRes = query.executeUpdate();
				sesObj.getTransaction().commit();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return opRes;
	}

	@Override
	public List<CabCaptainEM> getCabCaptainDetails(String cabType) {
		List<CabCaptainEM> cabCaptList=new ArrayList<>();
		if(null != cabType) {
			try(Session sesObj = HibernateUtils.getSessionFactory().openSession()){
				String hql= "Select cabCapt From CabCaptainEM cabCapt JOIN FETCH cabCapt.cab cab JOIN FETCH cabCapt.user user Where cabCapt.cabCaptLevel=? and user.userType=?";
				Query query = sesObj.createQuery(hql);
				query.setParameter(0,cabType);
				query.setParameter(1,"Driver");
				for(Object obj:query.getResultList()) {
					cabCaptList.add((CabCaptainEM) obj);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return (cabCaptList.isEmpty() || null==cabCaptList) ? null :cabCaptList;
	}

	@Override
	public CabCaptainEM getCabCaptainInfo(int cabCaptId) {
		CabCaptainEM cabCaptObj = null;
		Session sesObj = HibernateUtils.getSessionFactory().openSession();
		if (0 != cabCaptId) {
			try {
				Query query = sesObj.createQuery("FROM CabCaptainEM where cabCaptID=?");
				query.setParameter(0, cabCaptId);
				cabCaptObj = (CabCaptainEM) query.getSingleResult();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sesObj.close();
			}
		}
		return (null != cabCaptObj) ? cabCaptObj : null;
	}
}
