package com.project.dao;

import java.util.List;

import com.project.entities.BookingConfirmationEM;
import com.project.entities.BookingsEM;
import com.project.entities.CabCaptainEM;
import com.project.entities.CabsEM;
import com.project.entities.UsersEM;

public interface CabsDAO {
	public int saveSearchBookingInfo(BookingsEM bookACab);
	public BookingsEM getLastBookingInfo(UsersEM user);
	public int saveCabInfo(CabsEM cabObj);
	public List<CabsEM> getUnassignedCabList(); 
	public int saveCabCaptainInfo(CabCaptainEM cabCapt);
	public int saveBookingConfirm(BookingConfirmationEM bookingCobj);
	public CabsEM getCabInfo(int cabId);
	public int updateCabAllocation(int cabId);
	public List<CabCaptainEM> getCabCaptainDetails(String cabType);
	public CabCaptainEM getCabCaptainInfo(int cabCaptId);
}
