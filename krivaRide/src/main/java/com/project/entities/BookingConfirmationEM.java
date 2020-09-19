package com.project.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "tblbookingconfirmation")
@DynamicUpdate
public class BookingConfirmationEM {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bookingConfirmationId")
	private int bookingConfirmationId;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="bookingId",nullable=false,unique=true)
	private BookingsEM booking;
	
	@Column(name="bookingDistance",nullable=false)
	private String bookingDistance;
	
	@Column(name="bookingDuration",nullable=false)
	private String bookingDuration;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cabCaptainId",nullable=false,unique=true)
	private CabCaptainEM cabCaptain;
	
	@Column(name="bookingConfirmationCost",nullable=false)
	private float bookingConfirmationCost;
	
	@Column(name="bookingConfirmationTS",nullable=false)
	private Date bookingConfirmationTS;
	
	@Column(name="bookingConfirmationStatus",nullable=false)
	private String bookingConfirmationStatus;

	public int getBookingConfirmationId() {
		return bookingConfirmationId;
	}

	public void setBookingConfirmationId(int bookingConfirmationId) {
		this.bookingConfirmationId = bookingConfirmationId;
	}

	public BookingsEM getBooking() {
		return booking;
	}

	public void setBooking(BookingsEM booking) {
		this.booking = booking;
	}

	public String getBookingDistance() {
		return bookingDistance;
	}

	public void setBookingDistance(String bookingDistance) {
		this.bookingDistance = bookingDistance;
	}

	public String getBookingDuration() {
		return bookingDuration;
	}

	public void setBookingDuration(String bookingDuration) {
		this.bookingDuration = bookingDuration;
	}

	public CabCaptainEM getCabCaptain() {
		return cabCaptain;
	}

	public void setCabCaptain(CabCaptainEM cabCaptain) {
		this.cabCaptain = cabCaptain;
	}

	public float getBookingConfirmationCost() {
		return bookingConfirmationCost;
	}

	public void setBookingConfirmationCost(float bookingConfirmationCost) {
		this.bookingConfirmationCost = bookingConfirmationCost;
	}

	public Date getBookingConfirmationTS() {
		return bookingConfirmationTS;
	}

	public void setBookingConfirmationTS(Date bookingConfirmationTS) {
		this.bookingConfirmationTS = bookingConfirmationTS;
	}

	public String getBookingConfirmationStatus() {
		return bookingConfirmationStatus;
	}

	public void setBookingConfirmationStatus(String bookingConfirmationStatus) {
		this.bookingConfirmationStatus = bookingConfirmationStatus;
	}
	
	
	
}
